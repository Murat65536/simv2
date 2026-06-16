package murat.simv2.analysis;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.analysis.Analyzer;
import org.objectweb.asm.tree.analysis.BasicValue;
import org.objectweb.asm.tree.analysis.BasicVerifier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class MovementJarStripper {
    private MovementJarStripper() {
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            throw new IllegalArgumentException(
                "Usage: MovementJarStripper <mcJar> <artifactsDir> <outJar> [movement-only|loadable]");
        }
        Path mcJar = Path.of(args[0]);
        Path artifactsDir = Path.of(args[1]);
        Path outJar = Path.of(args[2]);
        JarStripper.Mode mode = args.length >= 4 && !args[3].isBlank()
            ? JarStripper.Mode.valueOf(args[3].trim().toUpperCase(Locale.ROOT).replace('-', '_'))
            : JarStripper.Mode.MOVEMENT_ONLY;
        if (!Files.exists(mcJar)) {
            throw new IllegalStateException("Minecraft jar not found: " + mcJar);
        }

        Map<String, Map<String, Set<Integer>>> slice =
            AnalysisArtifacts.readSlice(AnalysisArtifacts.slicePath(artifactsDir));
        List<FieldResult> fields =
            AnalysisArtifacts.readFieldManifest(AnalysisArtifacts.fieldManifestPath(artifactsDir));

        run(mcJar, slice, fields, outJar, mode);
    }

    public static JarStripper.Stats run(
        Path mcJar,
        Map<String, Map<String, Set<Integer>>> slice,
        List<FieldResult> fields,
        Path outJar,
        JarStripper.Mode mode) throws IOException {

        Set<JarStripper.MethodRef> methods = new HashSet<>();
        for (var classEntry : slice.entrySet()) {
            String owner = internal(classEntry.getKey());
            for (String selector : classEntry.getValue().keySet()) {
                int paren = selector.indexOf('(');
                if (paren < 0) continue;
                methods.add(new JarStripper.MethodRef(
                    owner, selector.substring(0, paren), selector.substring(paren)));
            }
        }
        Set<JarStripper.FieldRef> fieldRefs = new HashSet<>();
        for (FieldResult f : fields) {
            fieldRefs.add(new JarStripper.FieldRef(internal(f.declaringClass()), f.fieldName()));
        }
        Set<String> protectedInternal = new TreeSet<>();
        for (String c : AnalysisConfig.REQUIRED_PRIMARY_CLASSES) protectedInternal.add(internal(c));

        System.out.println("\n=== Movement jar strip ===");
        System.out.println("Minecraft jar: " + mcJar);
        System.out.println("Output jar:    " + outJar);
        System.out.println("Mode:          " + mode);
        System.out.printf("Seeds: %d movement methods, %d fields%n",
            methods.size(), fieldRefs.size());

        long t0 = System.currentTimeMillis();
        JarStripper.Stats stats = JarStripper.strip(
            mcJar, methods, fieldRefs, protectedInternal, outJar, mode);
        double secs = (System.currentTimeMillis() - t0) / 1000.0;

        System.out.printf("Classes:  %d in -> %d emitted (%d dropped empty/unused)%n",
            stats.classesIn(), stats.classesEmitted(), stats.classesDropped());
        System.out.printf("          %d carry movement code, %d stub-only (called by movement), "
                + "%d data-only, %d structural shells%n",
            stats.movementClasses(), stats.stubOnlyClasses(),
            stats.dataOnlyClasses(), stats.shellClasses());
        System.out.printf("Methods:  %d kept whole, %d stubbed, %d dropped%n",
            stats.methodsKept(), stats.methodsStubbed(), stats.methodsDropped());
        System.out.printf("Fields:   %d kept, %d dropped%n",
            stats.fieldsKept(), stats.fieldsDropped());
        System.out.printf("Jar:      %.2f MB of classes in %.1fs%n",
            stats.bytesOut() / (1024.0 * 1024.0), secs);

        verify(outJar);
        return stats;
    }

    private static void verify(Path jar) throws IOException {
        int classes = 0;
        int bad = 0;
        int shown = 0;
        try (JarFile jf = new JarFile(jar.toFile())) {
            var entries = jf.entries();
            while (entries.hasMoreElements()) {
                JarEntry e = entries.nextElement();
                if (e.isDirectory() || !e.getName().endsWith(".class")) continue;
                classes++;
                byte[] bytes;
                try (var is = jf.getInputStream(e)) {
                    bytes = is.readAllBytes();
                }
                ClassNode cn = new ClassNode();
                new ClassReader(bytes).accept(cn, 0);
                String problem = null;
                for (MethodNode mn : cn.methods) {
                    try {
                        new Analyzer<BasicValue>(new BasicVerifier()).analyze(cn.name, mn);
                    } catch (Exception ex) {
                        problem = mn.name + mn.desc + ": " + ex.getMessage();
                        break;
                    }
                }
                if (problem != null) {
                    bad++;
                    if (shown < 8) {
                        shown++;
                        System.out.println("  [verify] " + cn.name + "#" + problem);
                    }
                }
            }
        }
        System.out.printf("Verify:   %d classes, %d with structural problems (BasicVerifier)%n",
            classes, bad);
    }

    private static String internal(String dotClass) {
        return dotClass.replace('.', '/');
    }
}
