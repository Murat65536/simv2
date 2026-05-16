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
import com.ibm.wala.util.config.FileOfClasses;
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

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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
        FieldRecord seedField = lookupSeedField(entitySubtypes);
        if (seedField == null) {
            throw new IllegalStateException(
                "Seed field '" + AnalysisConfig.SEED_FIELD_NAME
                    + "' not declared on " + AnalysisConfig.ENTITY_INTERNAL
                    + " or any of its superclasses. Check SEED_FIELD_NAME for the correct mapping (Yarn vs intermediary vs MojMap).");
        }
        List<Statement> seeds = findSeedStatements(entitySubtypes);
        if (seeds.isEmpty()) {
            throw new IllegalStateException(
                "Seed field '" + seedField.declaringClass + "." + seedField.fieldName
                    + "' exists in CHA but no putfield to it was reached by the call graph. "
                    + "Check entry point (" + AnalysisConfig.ENTRY_METHOD.classInternal()
                    + "." + AnalysisConfig.ENTRY_METHOD.selector()
                    + ") and exclusions.");
        }
        System.out.println("Seeds: " + seeds.size() + " putfield Entity.pos statements");

        // Build the SDG over the whole call graph. A backward slice from
        // putfield-pos sites needs both callers of the seed methods (actual
        // params flowing in) and callees (return values flowing back); for a
        // call graph with a single entry, that closure is the entire CG, so
        // there is no sound non-trivial prune. Scoping happens at the entry
        // point (AnalysisConfig.ENTRY_METHOD) instead.
        //
        // Single SDG with combined data + control dependence; the combined
        // edge set produces the same closure in one IFDS pass.
        String heapExclusionPattern = String.join("|", AnalysisConfig.SLICER_HEAP_EXCLUSIONS);
        HeapExclusions heapExclusions;
        try {
            heapExclusions = new HeapExclusions(
                new FileOfClasses(new ByteArrayInputStream(
                    heapExclusionPattern.getBytes(StandardCharsets.UTF_8))));
        } catch (java.io.IOException e) {
            throw new RuntimeException("Failed to build heap exclusions", e);
        }

        long t0 = System.currentTimeMillis();
        SDG<InstanceKey> sdg;
        try (PhaseHeartbeat ignored = PhaseHeartbeat.start()) {
            sdg = new SDG<>(cg, pa, ModRef.make(),
                // Thin slice: explicit SSA def-use only, no heap/base-pointer
                // dependence. FULL heap dependence makes the IFDS supergraph
                // diverge on Minecraft (frontier never drains). The slice is
                // now under-approximate w.r.t. flows through fields — the
                // pruned JAR MUST be re-validated (verifyOutputJar + runtime).
                DataDependenceOptions.NO_BASE_NO_HEAP,
                // Exceptional control edges (every potentially-throwing insn)
                // massively inflate each PDG. Dropping exception-path control
                // dependence is a minor precision loss, acceptable for a
                // movement slice, and a large IFDS win.
                ControlDependenceOptions.NO_EXCEPTIONAL_EDGES,
                heapExclusions);
        }
        // Deliberately not calling sdg.getNumberOfNodes() — it would eagerly
        // materialize every PDG in the CG. The IFDS solver builds PDGs lazily
        // as it reaches them.
        System.out.printf("SDG ready (lazy) in %.1fs%n",
            (System.currentTimeMillis() - t0) / 1000.0);

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
     * <p>Scans all CG nodes — any class with access to the seed field (public,
     * protected, or package-private) can emit {@code putfield}, not only entity
     * subtypes.
     */
    private List<Statement> findSeedStatements(Set<TypeReference> entitySubtypes) {
        return java.util.stream.StreamSupport.stream(cg.spliterator(), true)
            .flatMap(node -> {
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
     * Buckets the slice into per-method line sets and per-field MOD/REF.
     * Anything outside {@code net.minecraft.*} is dropped — primordial
     * helpers don't need mirroring.
     */
    private SliceResult analyzeStatements(
        Collection<Statement> statements, Set<TypeReference> entitySubtypes, int seedCount) {
        Map<String, Map<String, Set<Integer>>> lineByMethod = new java.util.concurrent.ConcurrentSkipListMap<>();
        Map<String, Map<String, Set<Integer>>> bcIndexByMethod = new java.util.concurrent.ConcurrentSkipListMap<>();
        Map<String, FieldResult.Category> categoryByField = new java.util.concurrent.ConcurrentHashMap<>();
        Map<String, FieldRecord> fieldsByKey = new java.util.concurrent.ConcurrentHashMap<>();

        statements.parallelStream().forEach(stmt -> {
            if (stmt.getKind() != Statement.Kind.NORMAL) return;
            NormalStatement ns = (NormalStatement) stmt;
            CGNode node = ns.getNode();
            IMethod method = node.getMethod();
            String declClassInternal = method.getDeclaringClass().getName().toString();
            if (isNotTargetClass(declClassInternal)) return;

            String dotClass = toDotClass(declClassInternal);
            String selector = method.getName().toString() + method.getDescriptor().toString();

            int bcIndex = bytecodeIndex(method, ns.getInstructionIndex());
            if (bcIndex >= 0) {
                bcIndexByMethod
                    .computeIfAbsent(dotClass, k -> new java.util.concurrent.ConcurrentSkipListMap<>())
                    .computeIfAbsent(selector, k -> new java.util.concurrent.ConcurrentSkipListSet<>())
                    .add(bcIndex);
            }

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

        return new SliceResult(statements.size(), seedCount, Map.copyOf(lineByMethod), Map.copyOf(bcIndexByMethod), List.copyOf(fields));
    }

    private void recordField(SSAFieldAccessInstruction insn,
                             Map<String, FieldRecord> fields,
                             Map<String, FieldResult.Category> categories) {
        FieldReference ref = insn.getDeclaredField();
        String declInternal = ref.getDeclaringClass().getName().toString();
        if (isNotTargetClass(declInternal)) return;
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
        IClass cls = cha.lookupClass(entityRef);
        while (cls != null) {
            for (IField field : cls.getDeclaredInstanceFields()) {
                if (AnalysisConfig.SEED_FIELD_NAME.equals(field.getName().toString())) {
                    return new FieldRecord(
                        toDotClass(cls.getName().toString()),
                        field.getName().toString(),
                        field.getFieldTypeReference().getName().toString());
                }
            }
            cls = cls.getSuperclass();
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

    private int bytecodeIndex(IMethod method, int instructionIndex) {
        if (!(method instanceof IBytecodeMethod<?> bc)) return -1;
        try {
            return bc.getBytecodeIndex(instructionIndex);
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

    private static boolean isNotTargetClass(String internal) {
        return internal == null || !internal.startsWith(AnalysisConfig.TARGET_PACKAGE_INTERNAL_L);
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
            try {
                scheduler.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static final class SliceProgressSolver
        extends PartiallyBalancedTabulationSolver<Statement, PDG<?>, Object> {
        private final long heartbeatNanos;
        private final long startNanos;
        private long lastReportNanos = -1L;
        // WALA's tabulation solver calls addToWorkList/popFromWorkList from a
        // single thread, so plain longs are safe here.
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
                System.out.printf(Locale.ROOT,
                        "  [slice-progress] done %.1fs processed=%d frontier=%d peak=%d queued=%d rate=%.1f/s%n",
                    elapsedSeconds, processed, frontier, maxFrontier, enqueued, rate);
            } else {
                System.out.printf(Locale.ROOT,
                        "  [slice-progress] %.1fs processed=%d frontier=%d rate=%.1f/s%n",
                    elapsedSeconds, processed, frontier, rate);
            }
            lastReportNanos = nowNanos;
        }
    }

    /** Result of one full slice. */
    public record SliceResult(
        int statementsConsidered,
        int seedCount,
        Map<String, Map<String, Set<Integer>>> lineByMethod,
        Map<String, Map<String, Set<Integer>>> bcIndexByMethod,
        List<FieldResult> fields
    ) {
    }
}
