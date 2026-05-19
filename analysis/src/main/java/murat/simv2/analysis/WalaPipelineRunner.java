package murat.simv2.analysis;

import com.ibm.wala.classLoader.IBytecodeMethod;
import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.core.util.config.AnalysisScopeReader;
import com.ibm.wala.ipa.callgraph.AnalysisCacheImpl;
import com.ibm.wala.ipa.callgraph.AnalysisOptions;
import com.ibm.wala.ipa.callgraph.AnalysisScope;
import com.ibm.wala.ipa.callgraph.CGNode;
import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.ipa.callgraph.CallGraphBuilder;
import com.ibm.wala.ipa.callgraph.Entrypoint;
import com.ibm.wala.ipa.callgraph.impl.DefaultEntrypoint;
import com.ibm.wala.ipa.callgraph.impl.Util;
import com.ibm.wala.ipa.callgraph.propagation.InstanceKey;
import com.ibm.wala.ipa.cha.ClassHierarchyFactory;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.shrike.shrikeBT.IInvokeInstruction;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.MethodReference;
import com.ibm.wala.types.Selector;
import com.ibm.wala.types.TypeReference;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Builds a call graph rooted at {@code ClientPlayerEntity#tickMovement()} and
 * emits everything the runtime sink-gate needs:
 *
 * <ol>
 *   <li>{@code chain.txt} — the entry entity's supertype chain (the only
 *       classes a movement tick runs through), used to restrict generated
 *       gates to concrete, client-tickable callers.</li>
 *   <li>{@code sink-callsites.txt} — every reachable call-site (caller on the
 *       chain) whose callee matches the {@link AnalysisConfig#MIRROR_SINKS}
 *       denylist: network sends, sounds, particles, game events, block
 *       callbacks, client-singleton mutators, and cross-entity state writes.</li>
 *   <li>The generated gate Mixins ({@link SinkMixinEmitter}) that suppress
 *       exactly those call-sites while {@code Prediction.ACTIVE}.</li>
 * </ol>
 *
 * <p>No bytecode and no backward slice: the runtime ticks a constructor-built
 * clone of the real player; the only thing the analysis must derive per MC
 * version is the reachable sink set, which comes straight from the call graph.
 */
final class WalaPipelineRunner {

    void run(AnalysisRunConfig config) throws Exception {
        System.out.println("=== WALA Sink-Gate Analysis ===");
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
            // Movement physics is not reflectively dispatched; resolving
            // reflection balloons the call graph.
            options.setReflectionOptions(AnalysisOptions.ReflectionOptions.NONE);
            CallGraphBuilder<InstanceKey> builder = Util.makeZeroOneContainerCFABuilder(
                options, new AnalysisCacheImpl(), cha);
            PrintingProgressMonitor progressMonitor = new PrintingProgressMonitor();
            long cgStart = System.currentTimeMillis();
            CallGraph cg = builder.makeCallGraph(options, progressMonitor);
            progressMonitor.done();
            long cgMs = System.currentTimeMillis() - cgStart;
            System.out.printf("Call graph: %d nodes in %.1fs%n",
                cg.getNumberOfNodes(), cgMs / 1000.0);
            if (cg.getNumberOfNodes() < 50) {
                throw new IllegalStateException(
                    "Call graph is suspiciously small (" + cg.getNumberOfNodes()
                        + " nodes). Check exclusions and entrypoints.");
            }

            Path outputDir = config.outputDir();
            Files.createDirectories(outputDir);

            // 1. The entry's supertype chain (the only classes we tick
            // through): restricts generated gates to concrete, client-tickable
            // callers and is emitted for inspection.
            Set<String> chain = entrySupertypeChain(cha);
            if (chain.isEmpty()) {
                throw new IllegalStateException(
                    "Entry supertype chain is empty — entry class or exclusions wrong.");
            }
            Path chainFile = outputDir.resolve("chain.txt");
            writeLines(chainFile, new TreeSet<>(chain));
            System.out.printf("Chain: %d class(es) written to %s%n",
                chain.size(), chainFile);

            // 2. Sink call-sites: every reachable call (caller on the chain)
            // whose callee is an escaping effect — derived by
            // SinkEffectAnalysis from the call graph, unioned with the
            // optional curated MIRROR_SINKS supplement. The whole CG is rooted
            // at the movement entry, so every node is reachable by construction.
            System.out.println("\nDeriving escaping-effect sinks...");
            SinkEffectAnalysis effects = new SinkEffectAnalysis(cha, cg);
            java.util.TreeMap<String, String> derivation = new java.util.TreeMap<>();
            TreeSet<String> sinkCallsites =
                collectSinkCallsites(cg, chain, effects, derivation);
            if (sinkCallsites.isEmpty()) {
                throw new IllegalStateException(
                    "No sink call-sites — escape roots / exclusions wrong for "
                        + "this mapping.");
            }
            Path sinkFile = outputDir.resolve("sink-callsites.txt");
            writeLines(sinkFile, sinkCallsites);

            // Diff report: DERIVED-ONLY / CURATED-ONLY / BOTH per distinct
            // callee. CURATED-ONLY must trend to empty as the analysis
            // subsumes the hand list.
            TreeSet<String> report = new TreeSet<>();
            long both = 0, derivedOnly = 0, curatedOnly = 0;
            for (var e : derivation.entrySet()) {
                report.add(e.getValue() + " " + e.getKey());
                switch (e.getValue()) {
                    case "BOTH" -> both++;
                    case "DERIVED-ONLY" -> derivedOnly++;
                    default -> curatedOnly++;
                }
            }
            writeLines(outputDir.resolve("sink-derivation.txt"), report);
            System.out.printf(
                "Sink call-sites: %d (%d distinct callee(s)) written to %s%n",
                sinkCallsites.size(), derivation.size(), sinkFile);
            System.out.printf(
                "Sink derivation: BOTH=%d DERIVED-ONLY=%d CURATED-ONLY=%d "
                    + "(CURATED-ONLY should trend to 0)%n",
                both, derivedOnly, curatedOnly);

            // 3. Code-generate the gating Mixins from the sink call-sites so
            // they re-derive per MC version (no hand-listed gates).
            SinkMixinEmitter.emit(outputDir, sinkCallsites);

            System.out.println("\nWALA artifacts written to " + outputDir);
        } finally {
            //noinspection ResultOfMethodCallIgnored
            exclusionsFile.delete();
        }
    }

    /**
     * The set of effect <em>methods</em> to gate callee-side. Line form:
     * {@code concreteOwnerSlash|calleeName|calleeDesc} — one record per
     * distinct effect method, de-duped across all callers.
     *
     * <p>Every reachable call (any net.minecraft caller — no chain
     * restriction, because the generated guard sits inside the effect method
     * so the caller is irrelevant) whose callee is an escaping effect
     * ({@link SinkEffectAnalysis} ∪ curated {@link SinkRules}) is mapped via
     * {@link SinkEffectAnalysis#gateOwners} to its concrete effect-leaf
     * owner(s). Transitive-only escaping parents resolve to no owner and are
     * not gated, so cancelling at HEAD never freezes clone-owned state.
     *
     * <p>The callee owner is read from the <em>raw Shrike bytecode</em>
     * invoke ({@code getClassType()}) so dispatch-aware resolution starts
     * from the exact constant-pool owner, version-robustly.
     */
    private TreeSet<String> collectSinkCallsites(
        CallGraph cg, Set<String> chain, SinkEffectAnalysis effects,
        java.util.Map<String, String> derivation) {
        TreeSet<String> out = new TreeSet<>();
        Set<IMethod> scanned = new HashSet<>();
        for (CGNode node : cg) {
            IMethod caller = node.getMethod();
            if (caller == null || caller.isSynthetic()
                || !(caller instanceof IBytecodeMethod)) {
                continue;
            }
            String callerType = caller.getDeclaringClass().getName().toString();
            if (!callerType.startsWith(AnalysisConfig.TARGET_PACKAGE_INTERNAL_L)) {
                continue;
            }
            // No chain restriction: the gate is callee-side (inside the
            // effect method), so ANY caller — chain classes, the tickables
            // loop (AmbientSoundPlayer/BiomeEffectSoundPlayer/…),
            // onTrackedDataSet callbacks, a deferred-queue enqueue — is
            // covered. We still only scan net.minecraft callers (TARGET
            // package) to find the effect call-sites and their true owners.
            if (!scanned.add(caller)) {
                continue; // a method may have many context-sensitive CG nodes
            }
            Object[] insns;
            try {
                @SuppressWarnings("rawtypes")
                IBytecodeMethod bm = (IBytecodeMethod) caller;
                insns = bm.getInstructions();
            } catch (Exception e) {
                continue;
            }
            if (insns == null) {
                continue;
            }
            for (Object insn : insns) {
                if (!(insn instanceof IInvokeInstruction ii)) {
                    continue;
                }
                String calleeOwner = stripL(ii.getClassType()); // true bytecode owner
                String calleeName = ii.getMethodName();
                String calleeDesc = ii.getMethodSignature();
                // MixinExtras @WrapOperation cannot wrap a bare <init> /
                // <clinit> invoke (a constructor call must be targeted as the
                // NEW instantiation, not the INVOKESPECIAL). Such a gate fails
                // mixin apply at runtime, so these are never sink call-sites.
                if (calleeName.equals("<init>") || calleeName.equals("<clinit>")) {
                    continue;
                }
                // Compiler-generated synthetic/bridge methods (e.g. an enum's
                // $values()) are never a movement effect; gating one returns
                // null on the prediction thread and NPEs the clone tick.
                if (effects.isSyntheticOrBridge(
                        calleeOwner, calleeName, calleeDesc)) {
                    continue;
                }
                boolean derived =
                    effects.isEffectfulCallee(calleeOwner, calleeName, calleeDesc);
                boolean curated = SinkRules.isSink(calleeOwner, calleeName);
                if (!derived && !curated) {
                    continue;
                }
                derivation.putIfAbsent(
                    calleeOwner + "#" + calleeName + calleeDesc,
                    derived && curated ? "BOTH"
                        : derived ? "DERIVED-ONLY" : "CURATED-ONLY");
                // Resolve to the concrete effect-LEAF owner(s) to gate
                // callee-side. Empty = a transitive-only escaping parent
                // (not curated): not gated, so HEAD-cancelling never freezes
                // the clone's own state evolution (its leaves are gated
                // independently).
                for (String ow : effects.gateOwners(
                        calleeOwner, calleeName, calleeDesc, curated)) {
                    // 4th field: S(tatic) | I(nstance) — Mixin needs the
                    // callback's static-ness to match the target method's.
                    String mod = effects.isStaticMethod(ow, calleeName, calleeDesc)
                        ? "S" : "I";
                    out.add(ow + "|" + calleeName + "|" + calleeDesc + "|" + mod);
                }
            }
        }
        return out;
    }

    /**
     * The entry entity's transitive superclass chain (itself included), as
     * internal slash names. These are the only classes a movement tick runs
     * through, so they are the only concrete callers a gate is generated for.
     */
    private Set<String> entrySupertypeChain(IClassHierarchy cha) {
        Set<String> chain = new TreeSet<>();
        TypeReference ref = TypeReference.findOrCreate(
            ClassLoaderReference.Application, AnalysisConfig.ENTRY_METHOD.classInternal());
        IClass c = cha.lookupClass(ref);
        while (c != null) {
            String internal = c.getName().toString(); // Lnet/minecraft/.../X
            if (internal.startsWith(AnalysisConfig.TARGET_PACKAGE_INTERNAL_L)) {
                chain.add(internal.substring(1)); // net/minecraft/.../X
            }
            c = c.getSuperclass();
        }
        return chain;
    }

    private static String stripL(String typeName) {
        String s = typeName.startsWith("L") ? typeName.substring(1) : typeName;
        return s.endsWith(";") ? s.substring(0, s.length() - 1) : s;
    }

    private static void writeLines(Path file, Iterable<String> lines) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line).append('\n');
        }
        Files.write(file, sb.toString().getBytes(StandardCharsets.UTF_8));
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
