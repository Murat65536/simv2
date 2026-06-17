package murat.simv2.analysis;

import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.classLoader.Language;
import com.ibm.wala.core.util.config.AnalysisScopeReader;
import com.ibm.wala.ipa.callgraph.AnalysisCacheImpl;
import com.ibm.wala.ipa.callgraph.AnalysisOptions;
import com.ibm.wala.ipa.callgraph.AnalysisScope;
import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.ipa.callgraph.CallGraphBuilder;
import com.ibm.wala.ipa.callgraph.Entrypoint;
import com.ibm.wala.ipa.callgraph.IAnalysisCacheView;
import com.ibm.wala.ipa.callgraph.cha.CHACallGraph;
import com.ibm.wala.ipa.callgraph.impl.DefaultEntrypoint;
import com.ibm.wala.ipa.callgraph.impl.Util;
import com.ibm.wala.ipa.callgraph.propagation.InstanceKey;
import com.ibm.wala.ipa.callgraph.propagation.PointerAnalysis;
import com.ibm.wala.ipa.callgraph.propagation.SSAPropagationCallGraphBuilder;
import com.ibm.wala.ipa.callgraph.propagation.cfa.ZeroXCFABuilder;
import com.ibm.wala.ipa.callgraph.propagation.cfa.ZeroXInstanceKeys;
import com.ibm.wala.ipa.cha.ClassHierarchyFactory;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.MethodReference;
import com.ibm.wala.types.Selector;
import com.ibm.wala.types.TypeReference;
import com.ibm.wala.util.config.PatternsFilter;
import com.ibm.wala.util.config.StringFilter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

final class WalaPipelineRunner {

    void run(AnalysisRunConfig config) throws Exception {
        System.out.println("=== WALA Movement Slice ===");
        System.out.println("Minecraft jar:  " + config.minecraftJar());
        System.out.println("Output dir:     " + config.outputDir());

        Set<String> universe = ScopePruner.jarClassUniverse(config.minecraftJar());
        Set<String> kept = computePhaseAClosure(config, universe);

        System.out.println("\n--- Phase B: 0-1-CFA over the pruned scope ---");
        AnalysisScope scope = makeScope(config,
            ScopePruner.prunedExclusions(AnalysisConfig.WALA_EXCLUSIONS, universe, kept));

        System.out.println("\nBuilding pruned class hierarchy...");
        IClassHierarchy cha = ClassHierarchyFactory.make(scope);
        System.out.println("CHA: " + cha.getNumberOfClasses() + " classes (pruned scope)");

        Set<Entrypoint> entrypoints = createEntrypoints(cha);

        AnalysisOptions options = new AnalysisOptions(scope, entrypoints);

        options.setReflectionOptions(AnalysisOptions.ReflectionOptions.NONE);
        if (config.skipClinit()) {

            System.out.println("WARNING: static-initializer modeling DISABLED"
                + " (-PanalysisSkipClinit) — registry-driven dispatch (friction"
                + " etc.) may drop out of the slice. Validate the output.");
            options.setHandleStaticInit(false);
        }
        if (config.maxCgNodes() > 0) {

            options.setMaxNumberOfNodes(config.maxCgNodes());
        }

        int instancePolicy = ZeroXInstanceKeys.ALLOCATIONS
            | ZeroXInstanceKeys.SMUSH_STRINGS
            | ZeroXInstanceKeys.SMUSH_THROWABLES;
        System.out.println(
            "\nBuilding 0-1-CFA call graph (ALLOCATIONS | SMUSH_STRINGS | SMUSH_THROWABLES)...");
        IAnalysisCacheView cache = new AnalysisCacheImpl();
        Util.addDefaultSelectors(options, cha);
        Util.addDefaultBypassLogic(options, Util.class.getClassLoader(), cha);
        CallGraphBuilder<InstanceKey> builder = ZeroXCFABuilder.make(
            Language.JAVA, cha, options, cache, null, null, instancePolicy);
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

        cache.clear();

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

        Path outputDir = config.outputDir();
        Files.createDirectories(outputDir);
        AnalysisArtifacts.writeSlice(AnalysisArtifacts.slicePath(outputDir), slice.lineByMethod());
        AnalysisArtifacts.writeClosure(AnalysisArtifacts.closurePath(outputDir), closure);
        AnalysisArtifacts.writeFieldManifest(AnalysisArtifacts.fieldManifestPath(outputDir), slice.fields());

        // Track-2 codegen input: serialize the sliced methods' SSA IR while it is still live
        // (the call graph is built here and not after). Decompiler-independent.
        IrCapture.capture(cg, slice.lineByMethod(), outputDir.resolve("movement-ir.json"));

        System.out.println("\nWALA artifacts written to " + outputDir);

        Path strippedJar = outputDir.resolve("movement-stripped.jar");
        MovementJarStripper.run(config.minecraftJar(), slice.lineByMethod(),
            slice.fields(), strippedJar, JarStripper.Mode.MOVEMENT_ONLY);
    }

    private Set<String> computePhaseAClosure(AnalysisRunConfig config, Set<String> universe)
        throws Exception {
        System.out.println("\n--- Phase A: " + config.phaseAMode()
            + " reachability pre-pass (scope pruning) ---");
        AnalysisScope scope = makeScope(config,
            new PatternsFilter(AnalysisConfig.WALA_EXCLUSIONS.stream()));
        IClassHierarchy cha = ClassHierarchyFactory.make(scope);
        System.out.println("CHA: " + cha.getNumberOfClasses() + " classes (full scope)");

        Set<Entrypoint> entrypoints = createEntrypoints(cha);

        long t0 = System.currentTimeMillis();
        CallGraph cg;
        if (config.phaseAMode() == AnalysisRunConfig.PhaseAMode.CHA) {

            CHACallGraph chaCg = new CHACallGraph(cha, true);
            chaCg.init(entrypoints);
            cg = chaCg;
        } else {

            AnalysisOptions options = new AnalysisOptions(scope, entrypoints);
            options.setReflectionOptions(AnalysisOptions.ReflectionOptions.NONE);
            if (config.maxCgNodes() > 0) {
                options.setMaxNumberOfNodes(config.maxCgNodes());
            }
            IAnalysisCacheView cache = new AnalysisCacheImpl();
            SSAPropagationCallGraphBuilder builder =
                Util.makeZeroCFABuilder(Language.JAVA, options, cache, cha);
            PrintingProgressMonitor monitor = new PrintingProgressMonitor();
            cg = builder.makeCallGraph(options, monitor);
            monitor.done();
        }
        System.out.printf("Phase A call graph: %d nodes in %.1fs%n",
            cg.getNumberOfNodes(), (System.currentTimeMillis() - t0) / 1000.0);
        if (cg.getNumberOfNodes() < 50) {
            throw new IllegalStateException(
                "Phase A call graph is suspiciously small (" + cg.getNumberOfNodes()
                    + " nodes). Check exclusions and entrypoints.");
        }

        Set<String> kept = ScopePruner.keptClasses(cg, cha, universe);
        System.out.printf("Phase A closure: keeping %d / %d jar classes for Phase B%n",
            kept.size(), universe.size());
        if (kept.size() < 50) {
            throw new IllegalStateException(
                "Phase A closure is suspiciously small (" + kept.size()
                    + " classes). Check exclusions and entrypoints.");
        }
        return kept;
    }

    private AnalysisScope makeScope(AnalysisRunConfig config, StringFilter exclusions)
        throws Exception {
        AnalysisScope scope = AnalysisScopeReader.instance.makeJavaBinaryAnalysisScope(
            config.minecraftJar().toString(), null);

        scope.setExclusions(exclusions);
        return scope;
    }

    private Set<Entrypoint> createEntrypoints(IClassHierarchy cha) {
        // Entry defaults to AnalysisConfig.ENTRY_METHOD but can be overridden so we
        // can widen the scope (e.g. ClientPlayerEntity.tick()V, which covers all
        // per-tick movement, vs the narrower tickMovement()V) without recompiling.
        AnalysisConfig.EntryMethod em = new AnalysisConfig.EntryMethod(
            System.getProperty("analysis.entryClass", AnalysisConfig.ENTRY_METHOD.classInternal()),
            "", // name unused; selector carries name+descriptor
            "");
        String entrySelector =
            System.getProperty("analysis.entrySelector", AnalysisConfig.ENTRY_METHOD.selector());
        TypeReference owner = TypeReference.findOrCreate(
            ClassLoaderReference.Application, em.classInternal());
        IClass ownerClass = cha.lookupClass(owner);
        if (ownerClass == null) {
            throw new IllegalStateException("Entry owner class not in CHA: " + em.classInternal());
        }
        MethodReference ref = MethodReference.findOrCreate(
            owner, Selector.make(entrySelector));
        IMethod resolved = cha.resolveMethod(ref);
        if (resolved == null) {
            throw new IllegalStateException(
                "Entry method not found: " + em.classInternal() + "." + entrySelector);
        }
        System.out.println("Entry: " + em.classInternal() + "." + entrySelector
            + " (-> " + resolved.getDeclaringClass().getName() + ")");
        return Set.of(new DefaultEntrypoint(ref, cha));
    }
}
