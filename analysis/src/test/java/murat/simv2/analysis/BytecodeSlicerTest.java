package murat.simv2.analysis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    private static void addEntry(JarOutputStream jos, String name, byte[] bytes) throws Exception {
        JarEntry entry = new JarEntry(name);
        jos.putNextEntry(entry);
        jos.write(bytes);
        jos.closeEntry();
    }
}
