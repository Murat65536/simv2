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

/**
 * Runs the two-phase WALA pipeline:
 * <ol>
 *   <li><b>Phase A</b>: cheap reachability pre-pass (0-CFA or CHA) from
 *       {@code ClientPlayerEntity#tickMovement()} over the full jar, used only
 *       to prune the Phase B scope down to the reachable-class closure.</li>
 *   <li><b>Phase B</b>: precise 0-1-CFA call graph + pointer analysis over the
 *       pruned scope.</li>
 *   <li>Compute the backward slice from every {@code putfield Entity.pos} in the CG.</li>
 *   <li>Derive (a) per-method bytecode line numbers, (b) MOD/REF field categories,
 *       (c) the class closure from the slice.</li>
 *   <li>Persist the WALA artifacts.</li>
 *   <li>Strip the Minecraft jar down to the slice ({@code movement-stripped.jar}).</li>
 * </ol>
 *
 * <p>The pruning is output-preserving: reachability under the coarser Phase A
 * abstraction is a superset of reachability under 0-1-CFA, so nothing the
 * precise run would use is removed. It exists because the vanilla single-phase
 * 0-1-CFA fixpoint over the full universe (~31k MC classes + JDK) peaked past
 * ~164 GB and never converged even on a 170 GB box.
 */
final class WalaPipelineRunner {

    void run(AnalysisRunConfig config) throws Exception {
        System.out.println("=== WALA Movement Slice ===");
        System.out.println("Minecraft jar:  " + config.minecraftJar());
        System.out.println("Output dir:     " + config.outputDir());

        // --- Phase A: cheap reachability pre-pass over the full jar. ---
        Set<String> universe = ScopePruner.jarClassUniverse(config.minecraftJar());
        Set<String> kept = computePhaseAClosure(config, universe);
        // Phase A's CHA/CG/IR (full 31k-class universe) are unreachable now;
        // the Phase B allocations below can reclaim that heap.

        // --- Phase B: precise 0-1-CFA over the pruned scope. ---
        System.out.println("\n--- Phase B: 0-1-CFA over the pruned scope ---");
        AnalysisScope scope = makeScope(config,
            ScopePruner.prunedExclusions(AnalysisConfig.WALA_EXCLUSIONS, universe, kept));

        System.out.println("\nBuilding pruned class hierarchy...");
        IClassHierarchy cha = ClassHierarchyFactory.make(scope);
        System.out.println("CHA: " + cha.getNumberOfClasses() + " classes (pruned scope)");

        Set<Entrypoint> entrypoints = createEntrypoints(cha);

        AnalysisOptions options = new AnalysisOptions(scope, entrypoints);
        // Reflection modeling (default FULL) is pure overhead here: the
        // movement path is direct/virtual calls, never reflective, and FULL
        // reflection handling inflates CG construction badly on a jar this
        // size. NONE drops it soundly for our purpose.
        options.setReflectionOptions(AnalysisOptions.ReflectionOptions.NONE);
        if (config.skipClinit()) {
            // Escape hatch: skip <clinit> modeling. MC's registry initializers
            // (Blocks, Items, ...) are a points-to bomb, but without them
            // registry-object dispatch (e.g. block.getVelocityMultiplier() for
            // friction) can lose its targets and fall out of the slice.
            // Validate the output when this is on.
            System.out.println("WARNING: static-initializer modeling DISABLED"
                + " (-PanalysisSkipClinit) — registry-driven dispatch (friction"
                + " etc.) may drop out of the slice. Validate the output.");
            options.setHandleStaticInit(false);
        }
        if (config.maxCgNodes() > 0) {
            // Fail fast instead of grinding into swap on a runaway call graph.
            options.setMaxNumberOfNodes(config.maxCgNodes());
        }

        // 0-1-CFA with targeted smushing. Precision is the main lever on
        // OUTPUT size, and the output is the deliverable — a minimal movement
        // core reused across millions of simulations: coarse 0-CFA merges all
        // Vec3d / entity instances into one abstraction, inventing heap
        // dependences that drag particle / AI / render code into the slice as
        // false positives; allocation-site keys separate those instances and
        // prune them. Unlike the old vanilla builder we DO smush the types
        // that cannot carry physics dataflow:
        //  - SMUSH_STRINGS: string/StringBuffer allocation sites are the
        //    classic points-to blowup, and string heap is already excluded
        //    from the slice (SLICER_HEAP_EXCLUSIONS) — merging them cannot
        //    change the movement slice.
        //  - SMUSH_THROWABLES: exception objects, disambiguated by type only.
        // Deliberately NOT used:
        //  - SMUSH_PRIMITIVE_HOLDERS merges all instances of classes with no
        //    reference fields — that includes Vec3d (three doubles) and
        //    BlockPos, exactly the instances 0-1-CFA must keep separate.
        //  - SMUSH_MANY re-merges >25 same-type allocation sites per method,
        //    which could conflate entity/vector instances on the physics path.
        System.out.println(
            "\nBuilding 0-1-CFA call graph (ALLOCATIONS | SMUSH_STRINGS | SMUSH_THROWABLES)...");
        IAnalysisCacheView cache = new AnalysisCacheImpl();
        Util.addDefaultSelectors(options, cha);
        Util.addDefaultBypassLogic(options, Util.class.getClassLoader(), cha);
        CallGraphBuilder<InstanceKey> builder = ZeroXCFABuilder.make(
            Language.JAVA, cha, options, cache, null, null,
            ZeroXInstanceKeys.ALLOCATIONS
                | ZeroXInstanceKeys.SMUSH_STRINGS
                | ZeroXInstanceKeys.SMUSH_THROWABLES);
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

        // Drop the IR/DefUse cache populated for EVERY reachable CG method before
        // the memory-heavy slice. It is soft-referenced, so it would otherwise stay
        // resident until the slice pushes the heap near OOM. The slice only needs IR
        // for the subset of methods it reaches backward from the seeds, which it
        // recomputes deterministically on demand — result-identical, with a lower
        // slice-phase peak (we keep IR for the slice subset, not the whole CG).
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

        // Persist artifacts.
        Path outputDir = config.outputDir();
        Files.createDirectories(outputDir);
        AnalysisArtifacts.writeSlice(AnalysisArtifacts.slicePath(outputDir), slice.lineByMethod());
        AnalysisArtifacts.writeClosure(AnalysisArtifacts.closurePath(outputDir), closure);
        AnalysisArtifacts.writeFieldManifest(AnalysisArtifacts.fieldManifestPath(outputDir), slice.fields());

        System.out.println("\nWALA artifacts written to " + outputDir);

        // Emit the movement-only jar from the same slice — the deliverable.
        Path strippedJar = outputDir.resolve("movement-stripped.jar");
        MovementJarStripper.run(config.minecraftJar(), slice.lineByMethod(),
            slice.fields(), strippedJar, JarStripper.Mode.MOVEMENT_ONLY);
    }

    /**
     * Phase A: build a cheap call graph from the entry method over the full
     * jar and return the kept-class closure for the Phase B scope. Everything
     * allocated here (full CHA, Phase A CG, IR caches) is dropped on return.
     */
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
            // No points-to at all: every virtual call resolves to all CHA
            // targets. Coarsest closure, but guaranteed cheap. applicationOnly
            // is fine — only jar classes are ever pruned, the JDK stays whole.
            CHACallGraph chaCg = new CHACallGraph(cha, true);
            chaCg.init(entrypoints);
            cg = chaCg;
        } else {
            // 0-CFA: type-based instance keys — dramatically cheaper than
            // allocation-site keys, and its reachable set is a sound superset
            // of the 0-1-CFA one.
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
        // The filter is tested against whole slash-form class names; matching
        // classes are kept out of the scope and never loaded into the CHA.
        scope.setExclusions(exclusions);
        return scope;
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
}
