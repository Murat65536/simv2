package murat.simv2.analysis;

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
import com.ibm.wala.util.config.FileOfClasses;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Backward slice from every {@code putfield Entity.pos} reachable in the call
 * graph, plus the derived (a) per-method line numbers, (b) per-field MOD/REF
 * categorization, all extracted from a single SDG traversal.
 */
final class WalaSlicer {
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

        HeapExclusions heapExcl = buildHeapExclusions();

        // One SDG covers data + control dependence in a single pass.
        long sdgStart = System.currentTimeMillis();
        SDG<InstanceKey> sdg = new SDG<>(cg, pa, ModRef.make(),
            DataDependenceOptions.NO_BASE_PTRS,
            ControlDependenceOptions.NO_EXCEPTIONAL_EDGES,
            heapExcl);
        long sdgMs = System.currentTimeMillis() - sdgStart;
        System.out.printf("SDG built in %.1fs (%d nodes)%n", sdgMs / 1000.0, sdg.getNumberOfNodes());

        Set<Statement> all = new HashSet<>();
        try {
            all.addAll(Slicer.computeBackwardSlice(sdg, seeds));
        } catch (Exception ex) {
            throw new RuntimeException("Backward slice failed", ex);
        }
        System.out.println("Slice contains " + all.size() + " statements");

        return analyzeStatements(all, entitySubtypes);
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
     */
    private List<Statement> findSeedStatements(Set<TypeReference> entitySubtypes) {
        List<Statement> seeds = new ArrayList<>();
        for (CGNode node : cg) {
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
    private SliceResult analyzeStatements(Collection<Statement> statements, Set<TypeReference> entitySubtypes) {
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

        return new SliceResult(statements.size(), Map.copyOf(lineByMethod), List.copyOf(fields));
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

    /** Result of one full slice. */
    record SliceResult(
        int statementsConsidered,
        Map<String, Map<String, Set<Integer>>> lineByMethod,
        List<FieldResult> fields
    ) {
    }
}
