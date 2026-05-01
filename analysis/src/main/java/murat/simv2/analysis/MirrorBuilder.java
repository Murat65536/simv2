package murat.simv2.analysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Spoon half of the pipeline.
 *
 * <p>Given a {@link MirrorClosure} and a slice produced by the WALA phase,
 * emits a parallel package tree {@code murat.simv2.simulation.mirror.<pkg>}
 * where:
 *
 * <ul>
 *   <li>Classes that have a {@code .java} entry in the sources jar are
 *       round-tripped through Spoon: their package is rewritten, every
 *       {@code net.minecraft.X} reference is rewritten to its mirror,
 *       and methods on the five primary entity-hierarchy classes are
 *       statement-pruned to the slice line set.</li>
 *   <li>Classes in the closure that have no source entry are stubbed via
 *       ASM, recreating just enough of the public/protected API for the
 *       sliced bodies to compile.</li>
 * </ul>
 *
 * <p>This class never throws on a single bad class — instead it logs and
 * degrades to an empty stub, which keeps the rest of the build moving.
 */
final class MirrorBuilder {

    void run(AnalysisRunConfig config) throws IOException {
        Path outputDir = config.outputDir();
        Path mirrorRoot = outputDir.resolve("java/murat/simv2/simulation/mirror");
        deleteRecursively(mirrorRoot);
        Files.createDirectories(mirrorRoot);

        MirrorClosure closure = AnalysisArtifacts.readClosure(AnalysisArtifacts.closurePath(outputDir));
        Map<String, Map<String, Set<Integer>>> slice = AnalysisArtifacts.readSlice(
            AnalysisArtifacts.slicePath(outputDir));

        Set<String> primary = new HashSet<>(AnalysisConfig.REQUIRED_PRIMARY_CLASSES);
        Map<String, byte[]> sourcesByClass = extractSources(config.sourcesJar(), closure.classes());
        System.out.println("Sources jar contributes " + sourcesByClass.size() + " of "
            + closure.classes().size() + " closure classes");

        Set<String> emitted = new TreeSet<>();
        MirrorSourceTransformer transformer = new MirrorSourceTransformer(slice, primary);
        if (!sourcesByClass.isEmpty()) {
            transformer.transformAll(sourcesByClass, mirrorRoot, emitted);
        }

        Set<String> needsStub = new TreeSet<>(closure.classes());
        needsStub.removeAll(emitted);
        if (!needsStub.isEmpty()) {
            System.out.println("Stubbing " + needsStub.size() + " classes from bytecode");
            try (MirrorBytecodeStubber stubber = new MirrorBytecodeStubber(config.minecraftJar())) {
                for (String className : needsStub) {
                    if (stubber.emitStub(className, mirrorRoot)) {
                        emitted.add(className);
                    }
                }
            }
        }

        System.out.println("Mirror complete: " + emitted.size() + " classes under " + mirrorRoot);
    }

    /**
     * Extracts the {@code .java} bytes for every closure class that has a
     * source entry in the sources jar, keyed by dot-form class name.
     */
    private Map<String, byte[]> extractSources(Path sourcesJar, Set<String> classes) throws IOException {
        Map<String, byte[]> out = new LinkedHashMap<>();
        if (sourcesJar == null) {
            return out;
        }
        // For nested classes, the source lives in the top-level entry.
        Set<String> topLevelEntries = new HashSet<>();
        Map<String, String> topLevelByDotClass = new LinkedHashMap<>();
        for (String dotClass : classes) {
            int dollar = dotClass.indexOf('$');
            String topLevel = dollar < 0 ? dotClass : dotClass.substring(0, dollar);
            String entry = topLevel.replace('.', '/') + ".java";
            topLevelEntries.add(entry);
            topLevelByDotClass.put(dotClass, entry);
        }

        Map<String, byte[]> bytesByEntry = new LinkedHashMap<>();
        try (JarFile jar = new JarFile(sourcesJar.toFile())) {
            var enumeration = jar.entries();
            while (enumeration.hasMoreElements()) {
                JarEntry entry = enumeration.nextElement();
                if (entry.isDirectory()) continue;
                if (!topLevelEntries.contains(entry.getName())) continue;
                try (var in = jar.getInputStream(entry)) {
                    bytesByEntry.put(entry.getName(), in.readAllBytes());
                }
            }
        }
        for (var e : topLevelByDotClass.entrySet()) {
            byte[] bytes = bytesByEntry.get(e.getValue());
            if (bytes != null) {
                out.put(e.getKey(), bytes);
            }
        }
        return out;
    }

    private static void deleteRecursively(Path root) throws IOException {
        if (!Files.exists(root)) return;
        try (var walk = Files.walk(root)) {
            List<Path> sorted = walk.sorted(Comparator.reverseOrder()).toList();
            for (Path p : sorted) {
                Files.deleteIfExists(p);
            }
        }
    }
}
