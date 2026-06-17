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
import com.ibm.wala.ipa.callgraph.propagation.PointerKey;
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
import com.ibm.wala.ssa.SSAAbstractInvokeInstruction;
import com.ibm.wala.ssa.SSAFieldAccessInstruction;
import com.ibm.wala.ssa.SSAGetInstruction;
import com.ibm.wala.ssa.SSAInstruction;
import com.ibm.wala.ssa.SSAPutInstruction;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.FieldReference;
import com.ibm.wala.types.TypeReference;
import com.ibm.wala.util.CancelException;
import com.ibm.wala.util.MonitorUtil.IProgressMonitor;
import com.ibm.wala.util.config.PatternsFilter;
import com.ibm.wala.util.intset.OrdinalSet;

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
        List<Statement> seeds = findSeedStatements();
        if (seeds.isEmpty()) {
            throw new IllegalStateException(
                "No player position/velocity mutations were reached by the call graph. "
                    + "Either the entry method is unreachable, or the exclusions / receiver "
                    + "filter are too aggressive.");
        }
        System.out.println("Seeds: " + seeds.size()
            + " player position/velocity mutation call sites");

        // Backward IFDS slice over an SDG carrying register + interprocedural
        // (parameter/return) def-use edges and control dependence, but NO heap data
        // dependences. Because IFDS tabulation is context-SENSITIVE, the slice stays
        // pinned to the real movement core and converges in well under a second here.
        //
        // Heap-aware alternatives were measured and rejected on this scope:
        //   - Full heap-on slicing (DataDependenceOptions.NO_BASE_PTRS) never
        //     converges — the context-sensitive heap reaching-defs blow the IFDS
        //     tabulation up to millions of path edges (hours / OOM).
        //   - WALA's context-INSENSITIVE thin slicing either under-connects
        //     (data-only: misses velocity and the rest of the core state) or, with
        //     control dependence on, over-approximates into ~600 spurious classes.
        // NO_HEAP is the sweet spot: it still visits every movement method reachable
        // by def-use/control, so their field accesses land in the manifest (this is
        // what produces the committed movement-fields.txt).
        HeapExclusions heapExcl = buildHeapExclusions();
        long t0 = System.currentTimeMillis();
        SDG<InstanceKey> sdg;
        try (PhaseHeartbeat ignored = PhaseHeartbeat.start("SDG build", SLICE_HEARTBEAT_MILLIS)) {
            sdg = new SDG<>(cg, pa, ModRef.make(),
                DataDependenceOptions.NO_HEAP,
                ControlDependenceOptions.NO_EXCEPTIONAL_EDGES,
                heapExcl);
        }
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

    // Seed on calls that mutate an entity's position/velocity whose receiver lies
    // entirely within the movement (LivingEntity) hierarchy. Backward-slicing such a
    // call captures the computed position/velocity — including collision adjustment,
    // which feeds the setPosition arguments inside Entity.move. Orb/item positioning
    // happens in their constructors on non-LivingEntity receivers, so it is excluded;
    // unknown (empty points-to) receivers are excluded too, since we cannot confirm
    // they are the player. See AnalysisConfig.MOVEMENT_RECEIVER_INTERNAL.
    private List<Statement> findSeedStatements() {
        IClass movementReceiver = cha.lookupClass(TypeReference.findOrCreate(
            ClassLoaderReference.Application, AnalysisConfig.MOVEMENT_RECEIVER_INTERNAL));
        if (movementReceiver == null) {
            throw new IllegalStateException(
                "Movement receiver type missing from CHA: " + AnalysisConfig.MOVEMENT_RECEIVER_INTERNAL);
        }
        List<Statement> seeds = new ArrayList<>();
        for (CGNode node : cg) {
            IR ir = node.getIR();
            if (ir == null) continue;
            SSAInstruction[] insns = ir.getInstructions();
            for (int i = 0; i < insns.length; i++) {
                if (!(insns[i] instanceof SSAAbstractInvokeInstruction call) || call.isStatic()) continue;
                if (!AnalysisConfig.POSITION_MUTATORS.contains(
                        call.getDeclaredTarget().getName().toString())) continue;
                if (!isMinecraftClass(
                        call.getDeclaredTarget().getDeclaringClass().getName().toString())) continue;
                if (!receiverInMovementHierarchy(node, call.getReceiver(), movementReceiver)) continue;
                seeds.add(new NormalStatement(node, i));
            }
        }
        return seeds;
    }

    // True iff the receiver's points-to set is non-empty and every concrete type it
    // may hold is in the movement (LivingEntity) hierarchy. A single non-LivingEntity
    // type (e.g. ExperienceOrbEntity, ItemEntity) or an unknown/empty points-to set
    // disqualifies the call site.
    private boolean receiverInMovementHierarchy(
        CGNode node, int receiverValueNumber, IClass movementReceiver) {
        PointerKey pk = pa.getHeapModel().getPointerKeyForLocal(node, receiverValueNumber);
        if (pk == null) return false;
        OrdinalSet<InstanceKey> pts = pa.getPointsToSet(pk);
        if (pts == null || pts.isEmpty()) return false;
        for (InstanceKey ik : pts) {
            IClass type = ik.getConcreteType();
            if (type == null || !cha.isAssignableFrom(movementReceiver, type)) return false;
        }
        return true;
    }

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

        return new HeapExclusions(
            new PatternsFilter(AnalysisConfig.SLICER_HEAP_EXCLUSIONS.stream()));
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

    record SliceResult(
        int statementsConsidered,
        int seedCount,
        Map<String, Map<String, Set<Integer>>> lineByMethod,
        List<FieldResult> fields
    ) {
    }
}
