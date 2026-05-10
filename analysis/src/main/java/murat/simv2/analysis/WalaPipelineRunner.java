package murat.simv2.analysis;

import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.core.util.config.AnalysisScopeReader;
import com.ibm.wala.ipa.callgraph.AnalysisCacheImpl;
import com.ibm.wala.ipa.callgraph.AnalysisOptions;
import com.ibm.wala.ipa.callgraph.AnalysisScope;
import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.ipa.callgraph.CallGraphBuilder;
import com.ibm.wala.ipa.callgraph.Entrypoint;
import com.ibm.wala.ipa.callgraph.impl.DefaultEntrypoint;
import com.ibm.wala.ipa.callgraph.impl.Util;
import com.ibm.wala.ipa.callgraph.propagation.InstanceKey;
import com.ibm.wala.ipa.callgraph.propagation.PointerAnalysis;
import com.ibm.wala.ipa.cha.ClassHierarchyFactory;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.MethodReference;
import com.ibm.wala.types.Selector;
import com.ibm.wala.types.TypeReference;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Runs the WALA pipeline:
 * <ol>
 *   <li>Build call graph + pointer analysis from {@code ClientPlayerEntity#tickMovement()}.</li>
 *   <li>Compute the backward slice from every {@code putfield Entity.pos} in the CG.</li>
 *   <li>Derive (a) per-method bytecode line numbers, (b) MOD/REF field categories,
 *       (c) the class closure from the slice.</li>
 *   <li>Persist the WALA artifacts.</li>
 * </ol>
 */
final class WalaPipelineRunner {

    void run(AnalysisRunConfig config) throws Exception {
        System.out.println("=== WALA Movement Slice ===");
        System.out.println("Minecraft jar:  " + config.minecraftJar());
        System.out.println("Output dir:     " + config.outputDir());

        File exclusionsFile = writeExclusionsFile();
        try {
            AnalysisScope scope = AnalysisScopeReader.instance.makeJavaBinaryAnalysisScope(
                config.minecraftJar().toString(), exclusionsFile);

            System.out.println("\nBuilding class hierarchy...");
            IClassHierarchy cha = ClassHierarchyFactory.make(scope);
            System.out.println("CHA: " + cha.getNumberOfClasses() + " classes");

            Set<Entrypoint> entrypoints = createEntrypoints(cha);
            if (entrypoints.isEmpty()) {
                throw new IllegalStateException("No entrypoint resolved for "
                    + AnalysisConfig.ENTRY_METHOD.classInternal() + "."
                    + AnalysisConfig.ENTRY_METHOD.selector());
            }

            System.out.println("\nBuilding 0-1-Container-CFA call graph...");
            AnalysisOptions options = new AnalysisOptions(scope, entrypoints);
            CallGraphBuilder<InstanceKey> builder = Util.makeZeroOneContainerCFABuilder(
                options, new AnalysisCacheImpl(), cha);
            PrintingProgressMonitor progressMonitor = new PrintingProgressMonitor();
            long cgStart = System.currentTimeMillis();
            CallGraph cg = builder.makeCallGraph(options, progressMonitor);
            progressMonitor.done();
            PointerAnalysis<InstanceKey> pa = builder.getPointerAnalysis();
            long cgMs = System.currentTimeMillis() - cgStart;
            System.out.printf("Call graph: %d nodes in %.1fs%n",
                cg.getNumberOfNodes(), cgMs / 1000.0);
            if (cg.getNumberOfNodes() < 50) {
                throw new IllegalStateException(
                    "Call graph is suspiciously small (" + cg.getNumberOfNodes()
                        + " nodes). Check exclusions and entrypoints.");
            }

            System.out.println("\nRunning backward slice from Entity.pos writes...");
            WalaSlicer.SliceResult slice = new WalaSlicer(cg, pa, cha).slice();
            int slicedMethods = slice.lineByMethod().values().stream().mapToInt(Map::size).sum();
            System.out.printf(
                "Slice: %d statements -> %d classes, %d methods, %d fields%n",
                slice.statementsConsidered(),
                slice.lineByMethod().size(),
                slicedMethods,
                slice.fields().size());
            if (slice.lineByMethod().isEmpty()
                || slice.statementsConsidered() <= slice.seedCount()
                || slice.fields().isEmpty()) {
                throw new IllegalStateException(
                    "Slice is suspiciously small (statements=" + slice.statementsConsidered()
                        + ", seeds=" + slice.seedCount()
                        + ", classes=" + slice.lineByMethod().size()
                        + ", fields=" + slice.fields().size()
                        + "). Exclusions may be too aggressive.");
            }

            MirrorClosure closure = ClosureBuilder.build(slice, cha);
            System.out.println("Closure: " + closure.classes().size() + " classes ("
                + slice.lineByMethod().size() + " sliced)");

            // Persist artifacts.
            Path outputDir = config.outputDir();
            Files.createDirectories(outputDir);
            AnalysisArtifacts.writeSlice(AnalysisArtifacts.slicePath(outputDir), slice.lineByMethod());
            AnalysisArtifacts.writeClosure(AnalysisArtifacts.closurePath(outputDir), closure);
            AnalysisArtifacts.writeFieldManifest(AnalysisArtifacts.fieldManifestPath(outputDir), slice.fields());

            Path outputJar = outputDir.resolve("test-sliced.jar");
            System.out.println("\nGenerating sliced JAR: " + outputJar);
            BytecodeSlicer.sliceJar(config.minecraftJar(), outputJar, outputDir, slice);

            System.out.println("\nWALA artifacts written to " + outputDir);
        } finally {
            //noinspection ResultOfMethodCallIgnored
            exclusionsFile.delete();
        }
    }

    private Set<Entrypoint> createEntrypoints(IClassHierarchy cha) {
        AnalysisConfig.EntryMethod em = AnalysisConfig.ENTRY_METHOD;
        TypeReference owner = TypeReference.findOrCreate(
            ClassLoaderReference.Application, em.classInternal());
        IClass ownerClass = cha.lookupClass(owner);
        if (ownerClass == null) {
            throw new IllegalStateException("Entry owner class not in CHA: " + em.classInternal());
        }
        MethodReference ref = MethodReference.findOrCreate(
            owner, Selector.make(em.selector()));
        IMethod resolved = cha.resolveMethod(ref);
        if (resolved == null) {
            throw new IllegalStateException(
                "Entry method not found: " + em.classInternal() + "." + em.selector());
        }
        System.out.println("Entry: " + em.classInternal() + "." + em.selector()
            + " (-> " + resolved.getDeclaringClass().getName() + ")");
        return Set.of(new DefaultEntrypoint(ref, cha));
    }

    private File writeExclusionsFile() throws Exception {
        File file = File.createTempFile("wala-exclusions", ".txt");
        file.deleteOnExit();
        Files.writeString(file.toPath(), String.join("\n", AnalysisConfig.WALA_EXCLUSIONS) + "\n");
        return file;
    }
}
