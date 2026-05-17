package murat.simv2.analysis;

import com.ibm.wala.classLoader.CallSiteReference;
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
import com.ibm.wala.ipa.callgraph.propagation.PointerAnalysis;
import com.ibm.wala.ipa.cha.ClassHierarchyFactory;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.ssa.IR;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.MethodReference;
import com.ibm.wala.types.Selector;
import com.ibm.wala.types.TypeReference;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Runs the WALA pipeline and emits the two artifacts the runtime
 * (Mixin sink-gate + snapshot/rollback) consumes:
 *
 * <ol>
 *   <li>Build the call graph + pointer analysis from
 *       {@code ClientPlayerEntity#tickMovement()}.</li>
 *   <li>Backward-slice from every {@code putfield Entity.pos} in the CG.</li>
 *   <li>{@code rollback-fields.txt} — every field the slice classifies as
 *       written ({@code MOD}/{@code MOD_REF}). This is the set the predictor
 *       snapshots before, and restores after, the look-ahead ticks so the
 *       real player is never perturbed.</li>
 *   <li>{@code sink-callsites.txt} — every reachable call-site whose callee
 *       matches the {@link AnalysisConfig#MIRROR_SINKS} denylist. This is the
 *       completeness oracle for the gating Mixin set: every distinct callee
 *       here must be gated by a Mixin behind {@code Prediction.ACTIVE}.</li>
 * </ol>
 *
 * <p>No bytecode is emitted: the previous child-classloaded mirror jar forked
 * the {@code Entity} type and could not be verified (identity boundary). The
 * runtime now runs the <em>real</em> movement code with side effects gated by
 * Mixins and the mutated state rolled back.
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
            // Movement physics is not reflectively dispatched; resolving
            // reflection balloons the CG (hence the SDG/IFDS supergraph).
            options.setReflectionOptions(AnalysisOptions.ReflectionOptions.NONE);
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

            Path outputDir = config.outputDir();
            Files.createDirectories(outputDir);

            // 1. Rollback set: every field the slice says movement writes.
            TreeSet<String> rollbackFields = new TreeSet<>();
            for (FieldResult f : slice.fields()) {
                if (f.category() == FieldResult.Category.REF) {
                    continue; // read-only — never written, nothing to roll back
                }
                rollbackFields.add(f.declaringClass() + "|" + f.fieldName()
                    + "|" + f.typeDescriptor());
            }
            Path rollbackFile = outputDir.resolve("rollback-fields.txt");
            writeLines(rollbackFile, rollbackFields);
            System.out.printf("Rollback fields: %d written to %s%n",
                rollbackFields.size(), rollbackFile);

            // 2. The entry's supertype chain (the only classes we tick
            // through): restricts generated gates to concrete, client-tickable
            // callers and is emitted for inspection.
            Set<String> chain = entrySupertypeChain(cha);
            Path chainFile = outputDir.resolve("chain.txt");
            writeLines(chainFile, new TreeSet<>(chain));
            System.out.printf("Chain: %d class(es) written to %s%n",
                chain.size(), chainFile);

            // 3. Sink call-sites: every reachable call (whose caller is on the
            // chain) whose callee is a denylisted side effect. The whole CG is
            // rooted at the movement entry, so every node is reachable from
            // tickMovement by construction.
            TreeSet<String> sinkCallsites = collectSinkCallsites(cg, chain);
            Path sinkFile = outputDir.resolve("sink-callsites.txt");
            writeLines(sinkFile, sinkCallsites);
            long distinctCallees = sinkCallsites.stream()
                .map(l -> l.split("\\|"))
                .map(p -> p[2] + "#" + p[3] + p[4])
                .distinct()
                .count();
            System.out.printf(
                "Sink call-sites: %d (%d distinct callee(s)) written to %s%n",
                sinkCallsites.size(), distinctCallees, sinkFile);

            // 4. Code-generate the gating Mixins from the sink call-sites so
            // they re-derive per MC version (no hand-listed gates).
            SinkMixinEmitter.emit(outputDir, sinkCallsites);

            System.out.println("\nWALA artifacts written to " + outputDir);
        } finally {
            //noinspection ResultOfMethodCallIgnored
            exclusionsFile.delete();
        }
    }

    /**
     * Every reachable call-site whose caller is on the entry supertype
     * {@code chain} and whose callee matches {@link SinkRules}. Line form:
     * {@code callerClassDot|callerSelector|calleeOwnerSlash|calleeName
     * |calleeDesc|invokeKind} where {@code invokeKind} is {@code STATIC} or
     * {@code INSTANCE} (the only distinction the generated handler needs).
     */
    private TreeSet<String> collectSinkCallsites(CallGraph cg, Set<String> chain) {
        TreeSet<String> out = new TreeSet<>();
        for (CGNode node : cg) {
            IMethod caller = node.getMethod();
            if (caller == null || caller.isSynthetic()) {
                continue;
            }
            String callerType = caller.getDeclaringClass().getName().toString();
            if (!callerType.startsWith(AnalysisConfig.TARGET_PACKAGE_INTERNAL_L)) {
                continue;
            }
            String callerInternal = stripL(callerType);
            if (!chain.contains(callerInternal)) {
                continue; // only gate concrete, client-tickable movement classes
            }
            IR ir = node.getIR();
            if (ir == null) {
                continue;
            }
            String callerClassDot = callerInternal.replace('/', '.');
            String callerSelector = caller.getName().toString()
                + caller.getDescriptor().toString();
            for (Iterator<CallSiteReference> it = ir.iterateCallSites(); it.hasNext(); ) {
                CallSiteReference csr = it.next();
                MethodReference mr = csr.getDeclaredTarget();
                String calleeOwner = stripL(mr.getDeclaringClass().getName().toString());
                String calleeName = mr.getName().toString();
                if (!SinkRules.isSink(calleeOwner, calleeName)) {
                    continue;
                }
                out.add(callerClassDot + "|" + callerSelector + "|"
                    + calleeOwner + "|" + calleeName + "|"
                    + mr.getDescriptor().toString() + "|"
                    + (csr.isStatic() ? "STATIC" : "INSTANCE"));
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
