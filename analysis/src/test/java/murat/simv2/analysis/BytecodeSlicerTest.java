package murat.simv2.analysis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.analysis.Analyzer;
import org.objectweb.asm.tree.analysis.BasicValue;
import org.objectweb.asm.tree.analysis.BasicVerifier;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class BytecodeSlicerTest {
    @TempDir
    Path tempDir;

    @Test
    void sliceJarWritesOnlyTheJar() throws Exception {
        Path sourceDir = Files.createDirectories(tempDir.resolve("sources"));
        Path classesDir = Files.createDirectories(tempDir.resolve("classes"));
        Path inputJar = tempDir.resolve("input.jar");
        Path outputJar = tempDir.resolve("output.jar");

        Path sourceFile = sourceDir.resolve(Path.of("testproject", "Sample.java"));
        Files.createDirectories(sourceFile.getParent());
        Files.writeString(sourceFile, """
            package testproject;

            public class Sample {
                public int answer() {
                    return 42;
                }
            }
            """);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            fail("JDK compiler not available");
        }
        int compileResult = compiler.run(null, null, null,
            "-d", classesDir.toString(),
            sourceFile.toString());
        assertEquals(0, compileResult);

        Path classFile = classesDir.resolve(Path.of("testproject", "Sample.class"));
        try (JarOutputStream jos = new JarOutputStream(Files.newOutputStream(inputJar))) {
            addEntry(jos, "testproject/Sample.class", Files.readAllBytes(classFile));
            addEntry(jos, "assets/data.txt", "hello".getBytes(java.nio.charset.StandardCharsets.UTF_8));
        }

        WalaSlicer.SliceResult slice = new WalaSlicer.SliceResult(
            0,
            0,
            Map.of(),
            Map.of(),
            List.of());

        BytecodeSlicer.sliceJar(inputJar, outputJar, slice);

        assertTrue(Files.exists(outputJar));
        assertFalse(Files.exists(tempDir.resolve("generated")));

        try (JarFile jarFile = new JarFile(outputJar.toFile())) {
            assertTrue(jarFile.getEntry("testproject/Sample.class") != null);
            assertTrue(jarFile.getEntry("assets/data.txt") != null);
            assertEquals(0, jarFile.stream().filter(entry -> entry.getName().endsWith(".java")).count());
            try (InputStream data = jarFile.getInputStream(jarFile.getEntry("assets/data.txt"))) {
                assertEquals("hello", new String(data.readAllBytes(), java.nio.charset.StandardCharsets.UTF_8));
            }
        }
    }

    /**
     * Methods with nothing in the slice are removed entirely (not stubbed);
     * kept methods stay structurally valid and JVM-loadable. Asserts the
     * fully-pruned methods are absent from the emitted class, every class
     * passes ASM's type-free structural verifier, and the kept method loads
     * and runs without a {@code VerifyError}.
     */
    @Test
    void fullyPrunedMethodsAreRemovedKeptMethodsStayValid() throws Exception {
        Path sourceDir = Files.createDirectories(tempDir.resolve("sources"));
        Path classesDir = Files.createDirectories(tempDir.resolve("classes"));
        Path inputJar = tempDir.resolve("input.jar");
        Path outputJar = tempDir.resolve("output.jar");

        // net.minecraft.* so BytecodeSlicer.pruneClass actually prunes it.
        Path sourceFile = sourceDir.resolve(
            Path.of("net", "minecraft", "testpkg", "Sample.java"));
        Files.createDirectories(sourceFile.getParent());
        Files.writeString(sourceFile, """
            package net.minecraft.testpkg;

            public class Sample {
                // Self-contained so it stays correct after the others are
                // removed.
                public int answer() {
                    int a = 21;
                    return a * 2;
                }

                int compute() {
                    int a = 21;
                    int b = 2;
                    return a * b;
                }

                int loopy(int n) {
                    int s = 0;
                    for (int i = 0; i < n; i++) {
                        s += i;
                    }
                    return s;
                }
            }
            """);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            fail("JDK compiler not available");
        }
        assertEquals(0, compiler.run(null, null, null,
            "-d", classesDir.toString(), sourceFile.toString()));

        Path classFile = classesDir.resolve(
            Path.of("net", "minecraft", "testpkg", "Sample.class"));
        try (JarOutputStream jos = new JarOutputStream(Files.newOutputStream(inputJar))) {
            addEntry(jos, "net/minecraft/testpkg/Sample.class",
                Files.readAllBytes(classFile));
            addEntry(jos, "assets/data.txt",
                "hello".getBytes(java.nio.charset.StandardCharsets.UTF_8));
        }

        // Keep only answer(); compute()/loopy() are in no slice -> removed.
        WalaSlicer.SliceResult slice = new WalaSlicer.SliceResult(
            1, 0,
            Map.of(),
            Map.of("net.minecraft.testpkg.Sample", Map.of("answer()I", Set.of(0))),
            List.of());

        BytecodeSlicer.sliceJar(inputJar, outputJar, slice);

        assertTrue(Files.exists(outputJar));

        // Every emitted net.minecraft class must pass ASM's structural verifier
        // (the exact check BytecodeSlicer uses to revert under-kept methods).
        try (JarFile jarFile = new JarFile(outputJar.toFile())) {
            assertTrue(jarFile.getEntry("assets/data.txt") != null);
            var entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry je = entries.nextElement();
                if (!je.getName().endsWith(".class")) continue;
                byte[] bytes;
                try (InputStream in = jarFile.getInputStream(je)) {
                    bytes = in.readAllBytes();
                }
                ClassNode cn = new ClassNode();
                new ClassReader(bytes).accept(
                    cn, ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG);
                if (je.getName().equals("net/minecraft/testpkg/Sample.class")) {
                    Set<String> names = new java.util.HashSet<>();
                    for (MethodNode mn : cn.methods) names.add(mn.name);
                    assertTrue(names.contains("answer"), "kept method present");
                    assertFalse(names.contains("compute"),
                        "fully-pruned compute() must be removed");
                    assertFalse(names.contains("loopy"),
                        "fully-pruned loopy() must be removed");
                }
                for (MethodNode mn : cn.methods) {
                    if ((mn.access & (java.lang.reflect.Modifier.ABSTRACT
                        | java.lang.reflect.Modifier.NATIVE)) != 0) {
                        continue;
                    }
                    try {
                        new Analyzer<BasicValue>(new BasicVerifier())
                            .analyze(cn.name, mn);
                    } catch (Throwable t) {
                        fail("structurally invalid bytecode in " + je.getName()
                            + " " + mn.name + mn.desc + ": " + t.getMessage());
                    }
                }
            }
        }

        // The JVM's own verifier (StackMapTable) must also accept it: load the
        // pruned class, invoke the kept method (no VerifyError, correct
        // result), and confirm the removed methods are gone.
        try (URLClassLoader cl = new URLClassLoader(
            new URL[] { outputJar.toUri().toURL() },
            getClass().getClassLoader())) {
            Class<?> sample = Class.forName(
                "net.minecraft.testpkg.Sample", true, cl);
            Object instance = sample.getDeclaredConstructor().newInstance();
            assertEquals(42, sample.getMethod("answer").invoke(instance));
            assertThrows(NoSuchMethodException.class,
                () -> sample.getDeclaredMethod("compute"));
            assertThrows(NoSuchMethodException.class,
                () -> sample.getDeclaredMethod("loopy", int.class));
        }
    }

    /**
     * A {@code net.minecraft.*} class the slice never touches is dropped from
     * the jar entirely (not left as a constructor-only shell). The kept class
     * still loads and runs, and every remaining class stays structurally valid.
     */
    @Test
    void neverUsedClassesAreRemoved() throws Exception {
        Path sourceDir = Files.createDirectories(tempDir.resolve("sources"));
        Path classesDir = Files.createDirectories(tempDir.resolve("classes"));
        Path inputJar = tempDir.resolve("input.jar");
        Path outputJar = tempDir.resolve("output.jar");

        Path pkgDir = sourceDir.resolve(Path.of("net", "minecraft", "testpkg"));
        Files.createDirectories(pkgDir);
        Path sampleSrc = pkgDir.resolve("Sample.java");
        Files.writeString(sampleSrc, """
            package net.minecraft.testpkg;

            public class Sample {
                public int answer() {
                    int a = 21;
                    return a * 2;
                }
            }
            """);
        Path unusedSrc = pkgDir.resolve("Unused.java");
        Files.writeString(unusedSrc, """
            package net.minecraft.testpkg;

            // No slice entry -> the whole class must be removed.
            public class Unused {
                int compute() {
                    int a = 21;
                    return a * 2;
                }

                int loopy(int n) {
                    int s = 0;
                    for (int i = 0; i < n; i++) {
                        s += i;
                    }
                    return s;
                }
            }
            """);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            fail("JDK compiler not available");
        }
        assertEquals(0, compiler.run(null, null, null,
            "-d", classesDir.toString(),
            sampleSrc.toString(), unusedSrc.toString()));

        Path baseDir = classesDir.resolve(Path.of("net", "minecraft", "testpkg"));
        try (JarOutputStream jos = new JarOutputStream(Files.newOutputStream(inputJar))) {
            addEntry(jos, "net/minecraft/testpkg/Sample.class",
                Files.readAllBytes(baseDir.resolve("Sample.class")));
            addEntry(jos, "net/minecraft/testpkg/Unused.class",
                Files.readAllBytes(baseDir.resolve("Unused.class")));
            addEntry(jos, "assets/data.txt",
                "hello".getBytes(java.nio.charset.StandardCharsets.UTF_8));
        }

        // Keep only Sample.answer(); Unused is in no slice -> removed wholesale.
        WalaSlicer.SliceResult slice = new WalaSlicer.SliceResult(
            1, 0,
            Map.of(),
            Map.of("net.minecraft.testpkg.Sample", Map.of("answer()I", Set.of(0))),
            List.of());

        BytecodeSlicer.sliceJar(inputJar, outputJar, slice);

        assertTrue(Files.exists(outputJar));
        try (JarFile jarFile = new JarFile(outputJar.toFile())) {
            assertTrue(jarFile.getEntry("net/minecraft/testpkg/Sample.class") != null,
                "sliced class is kept");
            assertEquals(null, jarFile.getEntry("net/minecraft/testpkg/Unused.class"),
                "never-used class must be removed entirely");
            assertTrue(jarFile.getEntry("assets/data.txt") != null,
                "non-class resources are untouched");

            var entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry je = entries.nextElement();
                if (!je.getName().endsWith(".class")) continue;
                byte[] bytes;
                try (InputStream in = jarFile.getInputStream(je)) {
                    bytes = in.readAllBytes();
                }
                ClassNode cn = new ClassNode();
                new ClassReader(bytes).accept(
                    cn, ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG);
                for (MethodNode mn : cn.methods) {
                    if ((mn.access & (java.lang.reflect.Modifier.ABSTRACT
                        | java.lang.reflect.Modifier.NATIVE)) != 0) {
                        continue;
                    }
                    try {
                        new Analyzer<BasicValue>(new BasicVerifier())
                            .analyze(cn.name, mn);
                    } catch (Throwable t) {
                        fail("structurally invalid bytecode in " + je.getName()
                            + " " + mn.name + mn.desc + ": " + t.getMessage());
                    }
                }
            }
        }

        try (URLClassLoader cl = new URLClassLoader(
            new URL[] { outputJar.toUri().toURL() },
            getClass().getClassLoader())) {
            Class<?> sample = Class.forName(
                "net.minecraft.testpkg.Sample", true, cl);
            Object instance = sample.getDeclaredConstructor().newInstance();
            assertEquals(42, sample.getMethod("answer").invoke(instance));
            assertThrows(ClassNotFoundException.class,
                () -> Class.forName("net.minecraft.testpkg.Unused", true, cl));
        }
    }

    private static void addEntry(JarOutputStream jos, String name, byte[] bytes) throws Exception {
        JarEntry entry = new JarEntry(name);
        jos.putNextEntry(entry);
        jos.write(bytes);
        jos.closeEntry();
    }
}
