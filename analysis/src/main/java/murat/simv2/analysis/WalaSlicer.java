package murat.simv2.analysis;

import com.ibm.wala.dataflow.IFDS.PartiallyBalancedTabulationProblem;
import com.ibm.wala.dataflow.IFDS.PartiallyBalancedTabulationSolver;
import com.ibm.wala.dataflow.IFDS.PathEdge;
import com.ibm.wala.dataflow.IFDS.TabulationResult;
import com.ibm.wala.classLoader.IBytecodeMethod;
import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IField;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.ipa.callgraph.CGNode;
import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.ipa.callgraph.propagation.InstanceKey;
import com.ibm.wala.ipa.callgraph.propagation.PointerAnalysis;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.ipa.modref.ModRef;
import com.ibm.wala.ipa.slicer.HeapExclusions;
import com.ibm.wala.ipa.slicer.NormalStatement;
import com.ibm.wala.ipa.slicer.PDG;
import com.ibm.wala.ipa.slicer.SDG;
import com.ibm.wala.ipa.slicer.Slicer;
import com.ibm.wala.ipa.slicer.Slicer.ControlDependenceOptions;
import com.ibm.wala.ipa.slicer.Slicer.DataDependenceOptions;
import com.ibm.wala.ipa.slicer.Statement;
import com.ibm.wala.ssa.IR;
import com.ibm.wala.ssa.SSAFieldAccessInstruction;
import com.ibm.wala.ssa.SSAGetInstruction;
import com.ibm.wala.ssa.SSAInstruction;
import com.ibm.wala.ssa.SSAPutInstruction;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.FieldReference;
import com.ibm.wala.types.TypeReference;
import com.ibm.wala.util.CancelException;
import com.ibm.wala.util.MonitorUtil.IProgressMonitor;
import com.ibm.wala.util.config.FileOfClasses;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Backward slice from every {@code putfield Entity.pos} reachable in the call
 * graph, plus the derived (a) per-method line numbers, (b) per-field MOD/REF
 * categorization, all extracted from a single SDG traversal.
 */
final class WalaSlicer {
    private static final long SLICE_HEARTBEAT_MILLIS = 10_000L;

    private final CallGraph cg;
    private final PointerAnalysis<InstanceKey> pa;
    private final IClassHierarchy cha;

    WalaSlicer(CallGraph cg, PointerAnalysis<InstanceKey> pa, IClassHierarchy cha) {
        this.cg = cg;
        this.pa = pa;
        this.cha = cha;
    }

    SliceResult slice() {
        Set<TypeReference> entitySubtypes = collectEntitySubtypes();
        List<Statement> seeds = findSeedStatements(entitySubtypes);
        if (seeds.isEmpty()) {
            throw new IllegalStateException(
                "No Entity.pos writes were reached by the call graph. "
                    + "Either the entry method is unreachable, or the exclusions are too aggressive.");
        }
        System.out.println("Seeds: " + seeds.size() + " putfield Entity.pos statements");

        // Build the SDG over the FULL call graph. A backward slice from a
        // pos-write must follow callee return values — getWorld(),
        // getBlockState(), the vector math, the collision helpers — back into
        // those methods, since they compute the values written to pos. An
        // earlier version pruned the CG to only the *callers* (ancestors) of
        // pos-writers; that silently dropped every such helper, so the slice
        // captured the entity "spine" but none of the code it calls. The lazy
        // SDG keeps the full-CG SDG affordable: it materializes a PDG only for
        // the methods the slice actually reaches.
        HeapExclusions heapExcl = buildHeapExclusions();
        long t0 = System.currentTimeMillis();
        SDG<InstanceKey> sdg;
        try (PhaseHeartbeat ignored = PhaseHeartbeat.start("SDG build", SLICE_HEARTBEAT_MILLIS)) {
            // NO_BASE_PTRS: track heap data flow (a field write in one method
            // feeding a field read in another), but ignore the dependence of a
            // heap access on the computation of its base pointer — those base-ptr
            // edges are noise for value flow and roughly double PDG size.
            //
            // Heap flow is what links the *velocity* physics to the *pos* write:
            // applyGravity()/setVelocity()/addVelocity()/jump()/knockback() write
            // this.velocity, and move() reads it (getVelocity()) to compute the new
            // position. Under NO_BASE_NO_HEAP that write->read link is severed, so
            // gravity, jumping, knockback and the friction math all fall out of the
            // slice. Turning the heap back on recovers them *without* hard-coding
            // velocity as a second seed — the dependence is discovered, not asserted.
            //
            // The cost is memory: the heap-on slice is much heavier (it OOM'd around
            // 6.5 GB on a 16 GB box). It is bounded by heapExcl — world/collision/
            // chunk/block heap is excluded wholesale (movement reads those via method
            // *returns*, kept as explicit dataflow), while entity (velocity/pos) and
            // util/math (Vec3d) heap is tracked. Give it the RAM (-PanalysisXmx) to
            // finish; ExitOnOutOfMemoryError makes a too-small budget fail cleanly.
            sdg = new SDG<>(cg, pa, ModRef.make(),
                DataDependenceOptions.NO_BASE_PTRS,
                ControlDependenceOptions.NO_EXCEPTIONAL_EDGES,
                heapExcl);
        }
        // The SDG is lazy: PDGs are materialized on demand. Do NOT call
        // sdg.getNumberOfNodes()/iterator()/toString() here — each triggers
        // SDG.eagerConstruction(), which builds and retains a PDG for *every*
        // node in the call graph at once (tens of GB on Minecraft). The IFDS
        // slicer below pulls only the PDGs reachable backward from the seeds.
        System.out.printf("SDG ready (lazy) in %.1fs over %d CG nodes%n",
            (System.currentTimeMillis() - t0) / 1000.0, cg.getNumberOfNodes());

        long sliceStart = System.currentTimeMillis();
        Collection<Statement> all;
        try {
            all = computeBackwardSliceWithTelemetry(sdg, seeds);
        } catch (Exception ex) {
            throw new RuntimeException("Backward slice failed: " + ex.getMessage(), ex);
        }
        System.out.printf("Slice: %d statements in %.1fs%n",
            all.size(), (System.currentTimeMillis() - sliceStart) / 1000.0);

        return analyzeStatements(all, entitySubtypes, seeds.size());
    }

    /** Collects {@code Entity} and every reachable subclass we may see in the CG. */
    private Set<TypeReference> collectEntitySubtypes() {
        TypeReference entityRef = TypeReference.findOrCreate(
            ClassLoaderReference.Application, AnalysisConfig.ENTITY_INTERNAL);
        IClass entityClass = cha.lookupClass(entityRef);
        if (entityClass == null) {
            throw new IllegalStateException(
                "Entity class missing from CHA: " + AnalysisConfig.ENTITY_INTERNAL);
        }
        Set<TypeReference> subtypes = new HashSet<>();
        subtypes.add(entityRef);
        for (IClass sub : cha.computeSubClasses(entityRef)) {
            subtypes.add(sub.getReference());
        }
        return Set.copyOf(subtypes);
    }

    /**
     * Seeds: every {@code putfield} of a field named {@code pos} declared on
     * {@code Entity} or a subclass, reached anywhere in the call graph.
     *
     * <p>Pre-filters CG nodes by declaring class — {@code putfield} of an
     * instance field can only be emitted by code on the declaring class or a
     * subclass, so iterating the full CG (and forcing {@link CGNode#getIR()})
     * for non-entity methods is wasted work.
     */
    private List<Statement> findSeedStatements(Set<TypeReference> entitySubtypes) {
        Set<String> entityInternalNames = new HashSet<>(entitySubtypes.size() * 2);
        for (TypeReference t : entitySubtypes) {
            entityInternalNames.add(t.getName().toString());
        }
        List<Statement> seeds = new ArrayList<>();
        for (CGNode node : cg) {
            String declInternal = node.getMethod().getDeclaringClass().getName().toString();
            if (!entityInternalNames.contains(declInternal)) continue;
            IR ir = node.getIR();
            if (ir == null) continue;
            SSAInstruction[] insns = ir.getInstructions();
            for (int i = 0; i < insns.length; i++) {
                SSAInstruction insn = insns[i];
                if (!(insn instanceof SSAPutInstruction put)) continue;
                if (put.isStatic()) continue;
                FieldReference field = put.getDeclaredField();
                if (!AnalysisConfig.SEED_FIELD_NAME.equals(field.getName().toString())) continue;
                if (!entitySubtypes.contains(field.getDeclaringClass())) continue;
                seeds.add(new NormalStatement(node, i));
            }
        }
        return seeds;
    }

    /**
     * Buckets the slice into per-method line sets and per-field MOD/REF.
     * Anything outside {@code net.minecraft.*} is dropped — primordial
     * helpers don't need mirroring.
     */
    private SliceResult analyzeStatements(
        Collection<Statement> statements, Set<TypeReference> entitySubtypes, int seedCount) {
        Map<String, Map<String, Set<Integer>>> lineByMethod = new TreeMap<>();
        Map<String, FieldResult.Category> categoryByField = new HashMap<>();
        Map<String, FieldRecord> fieldsByKey = new HashMap<>();

        for (Statement stmt : statements) {
            if (stmt.getKind() != Statement.Kind.NORMAL) continue;
            NormalStatement ns = (NormalStatement) stmt;
            CGNode node = ns.getNode();
            IMethod method = node.getMethod();
            String declClassInternal = method.getDeclaringClass().getName().toString();
            if (!isMinecraftClass(declClassInternal)) continue;

            String dotClass = toDotClass(declClassInternal);
            String selector = method.getName().toString() + method.getDescriptor().toString();
            int line = sourceLine(method, ns.getInstructionIndex());
            if (line > 0) {
                lineByMethod
                    .computeIfAbsent(dotClass, k -> new TreeMap<>())
                    .computeIfAbsent(selector, k -> new TreeSet<>())
                    .add(line);
            }

            SSAInstruction insn = ns.getInstruction();
            if (insn instanceof SSAFieldAccessInstruction fieldInsn && !fieldInsn.isStatic()) {
                recordField(fieldInsn, fieldsByKey, categoryByField);
            }
        }

        // Always include the seed field itself — it's the slice's purpose.
        FieldRecord seed = lookupSeedField(entitySubtypes);
        if (seed != null) {
            String key = seed.declaringClass + "." + seed.fieldName;
            fieldsByKey.put(key, seed);
            categoryByField.merge(key, FieldResult.Category.MOD, FieldResult::mergeWith);
        }

        List<FieldResult> fields = new ArrayList<>();
        for (var e : new TreeMap<>(fieldsByKey).entrySet()) {
            FieldRecord r = e.getValue();
            FieldResult.Category cat = categoryByField.get(e.getKey());
            fields.add(new FieldResult(r.declaringClass, r.fieldName, r.descriptor, cat));
        }

        return new SliceResult(statements.size(), seedCount, Map.copyOf(lineByMethod), List.copyOf(fields));
    }

    private void recordField(SSAFieldAccessInstruction insn,
                             Map<String, FieldRecord> fields,
                             Map<String, FieldResult.Category> categories) {
        FieldReference ref = insn.getDeclaredField();
        String declInternal = ref.getDeclaringClass().getName().toString();
        if (!isMinecraftClass(declInternal)) return;
        String declClass = toDotClass(declInternal);
        String name = ref.getName().toString();
        String descriptor = ref.getFieldType().getName().toString();
        String key = declClass + "." + name;
        fields.putIfAbsent(key, new FieldRecord(declClass, name, descriptor));
        FieldResult.Category category = insn instanceof SSAPutInstruction
            ? FieldResult.Category.MOD
            : (insn instanceof SSAGetInstruction ? FieldResult.Category.REF : FieldResult.Category.MOD_REF);
        categories.merge(key, category, FieldResult::mergeWith);
    }

    private FieldRecord lookupSeedField(Set<TypeReference> entitySubtypes) {
        TypeReference entityRef = TypeReference.findOrCreate(
            ClassLoaderReference.Application, AnalysisConfig.ENTITY_INTERNAL);
        IClass entity = cha.lookupClass(entityRef);
        if (entity == null) return null;
        for (IField field : entity.getDeclaredInstanceFields()) {
            if (AnalysisConfig.SEED_FIELD_NAME.equals(field.getName().toString())) {
                return new FieldRecord(
                    toDotClass(AnalysisConfig.ENTITY_INTERNAL),
                    field.getName().toString(),
                    field.getFieldTypeReference().getName().toString());
            }
        }
        return null;
    }

    private int sourceLine(IMethod method, int instructionIndex) {
        if (!(method instanceof IBytecodeMethod<?> bc)) return -1;
        try {
            int bcIndex = bc.getBytecodeIndex(instructionIndex);
            return bc.getLineNumber(bcIndex);
        } catch (Exception ignored) {
            return -1;
        }
    }

    private HeapExclusions buildHeapExclusions() {
        try {
            String text = String.join("\n", AnalysisConfig.SLICER_HEAP_EXCLUSIONS) + "\n";
            return new HeapExclusions(new FileOfClasses(
                new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8))));
        } catch (Exception ex) {
            throw new RuntimeException("Failed to build heap exclusions", ex);
        }
    }

    private Collection<Statement> computeBackwardSliceWithTelemetry(
        SDG<InstanceKey> sdg, Collection<Statement> roots) throws CancelException {
        PartiallyBalancedTabulationProblem<Statement, PDG<?>, Object> problem =
            new Slicer.SliceProblem(roots, sdg, true);
        SliceProgressSolver solver = new SliceProgressSolver(problem, null, SLICE_HEARTBEAT_MILLIS);
        TabulationResult<Statement, PDG<?>, Object> result = solver.solve();
        solver.printSummary();
        return result.getSupergraphNodesReached();
    }

    private static boolean isMinecraftClass(String internal) {
        return internal != null && internal.startsWith("Lnet/minecraft/");
    }

    private static String toDotClass(String internal) {
        String stripped = internal.startsWith("L") ? internal.substring(1) : internal;
        if (stripped.endsWith(";")) {
            stripped = stripped.substring(0, stripped.length() - 1);
        }
        return stripped.replace('/', '.');
    }

    private record FieldRecord(String declaringClass, String fieldName, String descriptor) {
    }

    private static final class PhaseHeartbeat implements AutoCloseable {
        private final ScheduledExecutorService scheduler;
        private final String phase;
        private final long startNanos;

        private PhaseHeartbeat(String phase, long intervalMillis) {
            if (intervalMillis <= 0L) {
                throw new IllegalArgumentException("intervalMillis must be > 0");
            }
            this.phase = phase;
            this.startNanos = System.nanoTime();
            this.scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
                Thread t = new Thread(r, "analysis-progress-heartbeat");
                t.setDaemon(true);
                return t;
            });
            System.out.println("  [slice-progress] " + phase + " started");
            scheduler.scheduleAtFixedRate(this::printTick, intervalMillis, intervalMillis, TimeUnit.MILLISECONDS);
        }

        static PhaseHeartbeat start(String phase, long intervalMillis) {
            return new PhaseHeartbeat(phase, intervalMillis);
        }

        private void printTick() {
            double elapsedSeconds = Math.max(0L, System.nanoTime() - startNanos) / 1_000_000_000.0;
            System.out.println(String.format(Locale.ROOT,
                "  [slice-progress] %s running %.1fs", phase, elapsedSeconds));
        }

        @Override
        public void close() {
            scheduler.shutdownNow();
        }
    }

    private static final class SliceProgressSolver
        extends PartiallyBalancedTabulationSolver<Statement, PDG<?>, Object> {
        private final long heartbeatNanos;
        private final long startNanos;
        private long lastReportNanos = -1L;
        private long enqueued = 0L;
        private long processed = 0L;
        private long frontier = 0L;
        private long maxFrontier = 0L;

        SliceProgressSolver(
            PartiallyBalancedTabulationProblem<Statement, PDG<?>, Object> problem,
            IProgressMonitor monitor,
            long heartbeatMillis
        ) {
            super(problem, monitor);
            if (heartbeatMillis <= 0) {
                throw new IllegalArgumentException("heartbeatMillis must be > 0");
            }
            this.heartbeatNanos = heartbeatMillis * 1_000_000L;
            this.startNanos = System.nanoTime();
            System.out.println("Slicing IFDS loop (telemetry: elapsed/processed/frontier/rate)...");
        }

        @Override
        protected void addToWorkList(Statement s_p, int i, Statement n, int j) {
            super.addToWorkList(s_p, i, n, j);
            enqueued++;
            frontier++;
            if (frontier > maxFrontier) {
                maxFrontier = frontier;
            }
        }

        @Override
        protected PathEdge<Statement> popFromWorkList() {
            PathEdge<Statement> edge = super.popFromWorkList();
            processed++;
            if (frontier > 0L) {
                frontier--;
            }
            maybeReport();
            return edge;
        }

        void printSummary() {
            long now = System.nanoTime();
            if (lastReportNanos < 0 || processed > 0) {
                printReport(now, true);
            }
        }

        private void maybeReport() {
            long now = System.nanoTime();
            if (lastReportNanos < 0 || (now - lastReportNanos) >= heartbeatNanos) {
                printReport(now, false);
            }
        }

        private void printReport(long nowNanos, boolean done) {
            double elapsedSeconds = Math.max(0L, nowNanos - startNanos) / 1_000_000_000.0;
            double rate = elapsedSeconds > 0.0 ? processed / elapsedSeconds : 0.0;
            if (done) {
                System.out.println(String.format(Locale.ROOT,
                    "  [slice-progress] done %.1fs processed=%d frontier=%d peak=%d queued=%d rate=%.1f/s",
                    elapsedSeconds, processed, frontier, maxFrontier, enqueued, rate));
            } else {
                System.out.println(String.format(Locale.ROOT,
                    "  [slice-progress] %.1fs processed=%d frontier=%d rate=%.1f/s",
                    elapsedSeconds, processed, frontier, rate));
            }
            lastReportNanos = nowNanos;
        }
    }

    /** Result of one full slice. */
    record SliceResult(
        int statementsConsidered,
        int seedCount,
        Map<String, Map<String, Set<Integer>>> lineByMethod,
        List<FieldResult> fields
    ) {
    }
}
