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
import com.ibm.wala.ipa.callgraph.pruned.PrunedCallGraph;
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
import java.util.ArrayDeque;
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
        Set<CGNode> sdgNodes = collectBackwardReachableSeedAncestors(seeds);
        CallGraph sdgCg = new PrunedCallGraph(cg, sdgNodes);
        double keptPct = cg.getNumberOfNodes() == 0
            ? 0.0
            : (100.0 * sdgCg.getNumberOfNodes() / cg.getNumberOfNodes());
        System.out.printf(Locale.ROOT,
            "SDG prune: %d -> %d CG nodes (%.1f%% kept)%n",
            cg.getNumberOfNodes(), sdgCg.getNumberOfNodes(), keptPct);

        // Single SDG with combined data + control dependence. Building two
        // SDGs sequentially (and re-running the IFDS solver) is the heaviest
        // step in the pipeline; the combined edge set produces the same
        // closure in one pass.
        long t0 = System.currentTimeMillis();
        SDG<InstanceKey> sdg;
        try (PhaseHeartbeat ignored = PhaseHeartbeat.start()) {
            sdg = new SDG<>(sdgCg, pa, ModRef.make(),
                DataDependenceOptions.FULL,
                ControlDependenceOptions.FULL,
                null);
        }
        System.out.printf("SDG built in %.1fs (%d nodes)%n",
            (System.currentTimeMillis() - t0) / 1000.0, sdg.getNumberOfNodes());

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
        return java.util.stream.StreamSupport.stream(cg.spliterator(), true)
            .flatMap(node -> {
                String declInternal = node.getMethod().getDeclaringClass().getName().toString();
                if (!entityInternalNames.contains(declInternal)) return java.util.stream.Stream.empty();
                IR ir = node.getIR();
                if (ir == null) return java.util.stream.Stream.empty();
                SSAInstruction[] insns = ir.getInstructions();
                List<Statement> localSeeds = new ArrayList<>();
                for (int i = 0; i < insns.length; i++) {
                    SSAInstruction insn = insns[i];
                    if (!(insn instanceof SSAPutInstruction put)) continue;
                    if (put.isStatic()) continue;
                    FieldReference field = put.getDeclaredField();
                    if (!AnalysisConfig.SEED_FIELD_NAME.equals(field.getName().toString())) continue;
                    if (!entitySubtypes.contains(field.getDeclaringClass())) continue;
                    localSeeds.add(new NormalStatement(node, i));
                }
                return localSeeds.stream();
            })
            .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Keep only methods that can reach a seed-containing method along call edges.
     * This bounds SDG/PDG construction to methods that may contribute to the
     * backward slice from {@code Entity.pos} writes.
     */
    private Set<CGNode> collectBackwardReachableSeedAncestors(Collection<Statement> seeds) {
        Set<CGNode> keep = new HashSet<>();
        ArrayDeque<CGNode> worklist = new ArrayDeque<>();
        for (Statement seed : seeds) {
            if (!(seed instanceof NormalStatement ns)) continue;
            CGNode node = ns.getNode();
            if (keep.add(node)) {
                worklist.addLast(node);
            }
        }
        while (!worklist.isEmpty()) {
            CGNode node = worklist.removeFirst();
            var preds = cg.getPredNodes(node);
            while (preds.hasNext()) {
                CGNode pred = preds.next();
                if (keep.add(pred)) {
                    worklist.addLast(pred);
                }
            }
        }
        return Set.copyOf(keep);
    }

    /**
     * Buckets the slice into per-method line sets and per-field MOD/REF.
     * Anything outside {@code net.minecraft.*} is dropped — primordial
     * helpers don't need mirroring.
     */
    private SliceResult analyzeStatements(
        Collection<Statement> statements, Set<TypeReference> entitySubtypes, int seedCount) {
        Map<String, Map<String, Set<Integer>>> lineByMethod = new java.util.concurrent.ConcurrentSkipListMap<>();
        Map<String, FieldResult.Category> categoryByField = new java.util.concurrent.ConcurrentHashMap<>();
        Map<String, FieldRecord> fieldsByKey = new java.util.concurrent.ConcurrentHashMap<>();

        statements.parallelStream().forEach(stmt -> {
            if (stmt.getKind() != Statement.Kind.NORMAL) return;
            NormalStatement ns = (NormalStatement) stmt;
            CGNode node = ns.getNode();
            IMethod method = node.getMethod();
            String declClassInternal = method.getDeclaringClass().getName().toString();
            if (!isMinecraftClass(declClassInternal)) return;

            String dotClass = toDotClass(declClassInternal);
            String selector = method.getName().toString() + method.getDescriptor().toString();
            int line = sourceLine(method, ns.getInstructionIndex());
            if (line > 0) {
                lineByMethod
                    .computeIfAbsent(dotClass, k -> new java.util.concurrent.ConcurrentSkipListMap<>())
                    .computeIfAbsent(selector, k -> new java.util.concurrent.ConcurrentSkipListSet<>())
                    .add(line);
            }

            SSAInstruction insn = ns.getInstruction();
            if (insn instanceof SSAFieldAccessInstruction fieldInsn && !fieldInsn.isStatic()) {
                recordField(fieldInsn, fieldsByKey, categoryByField);
            }
        });

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
        return internal != null && internal.startsWith(AnalysisConfig.TARGET_PACKAGE_INTERNAL_L);
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

        static PhaseHeartbeat start() {
            return new PhaseHeartbeat("SDG build", WalaSlicer.SLICE_HEARTBEAT_MILLIS);
        }

        private void printTick() {
            double elapsedSeconds = Math.max(0L, System.nanoTime() - startNanos) / 1_000_000_000.0;
            System.out.printf(Locale.ROOT,
                    "  [slice-progress] %s running %.1fs%n", phase, elapsedSeconds);
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
