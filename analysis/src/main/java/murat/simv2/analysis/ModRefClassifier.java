package murat.simv2.analysis;

import com.ibm.wala.ipa.callgraph.CGNode;
import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.ipa.callgraph.propagation.InstanceKey;
import com.ibm.wala.ipa.callgraph.propagation.PointerAnalysis;
import com.ibm.wala.ipa.callgraph.propagation.PointerKey;
import com.ibm.wala.ipa.callgraph.propagation.InstanceFieldKey;
import com.ibm.wala.ipa.callgraph.propagation.StaticFieldKey;
import com.ibm.wala.ipa.modref.ModRef;
import com.ibm.wala.classLoader.IField;
import com.ibm.wala.ssa.IR;
import com.ibm.wala.ssa.SSAFieldAccessInstruction;
import com.ibm.wala.ssa.SSAGetInstruction;
import com.ibm.wala.ssa.SSAInstruction;
import com.ibm.wala.ssa.SSAPutInstruction;
import com.ibm.wala.types.FieldReference;
import com.ibm.wala.util.intset.OrdinalSet;

import java.util.*;

public class ModRefClassifier {

    private final CallGraph cg;
    private final PointerAnalysis<InstanceKey> pa;

    public ModRefClassifier(CallGraph cg, PointerAnalysis<InstanceKey> pa) {
        this.cg = cg;
        this.pa = pa;
    }

    public List<FieldResult> classify(Set<SliceAnalysis.FieldInfo> slicedFields) {
        if (pa != null) {
            return classifyWithModRef(slicedFields);
        } else {
            return classifyFromFieldInfo(slicedFields);
        }
    }

    /**
     * When pointer analysis is available, use WALA's ModRef.
     */
    private List<FieldResult> classifyWithModRef(Set<SliceAnalysis.FieldInfo> slicedFields) {
        System.out.println("Running Mod/Ref analysis...");

        ModRef<InstanceKey> modRef = ModRef.make();
        Map<CGNode, OrdinalSet<PointerKey>> modMap = modRef.computeMod(cg, pa);
        Map<CGNode, OrdinalSet<PointerKey>> refMap = modRef.computeRef(cg, pa);

        Set<String> allModFields = new HashSet<>();
        Set<String> allRefFields = new HashSet<>();

        for (CGNode node : cg) {
            collectFieldKeys(modMap.get(node), allModFields);
            collectFieldKeys(refMap.get(node), allRefFields);
        }

        System.out.println("Mod fields: " + allModFields.size() + ", Ref fields: " + allRefFields.size());

        return buildResults(slicedFields, allModFields, allRefFields);
    }

    /**
     * When no pointer analysis, classify based on read/write info from IR scan.
     * Also scan all reachable IRs for additional mod/ref info.
     */
    private List<FieldResult> classifyFromFieldInfo(Set<SliceAnalysis.FieldInfo> slicedFields) {
        System.out.println("Classifying from IR scan data (no pointer analysis)...");

        // Build mod/ref sets from CG IR scan
        Set<String> modFields = new HashSet<>();
        Set<String> refFields = new HashSet<>();

        for (CGNode node : cg) {
            IR ir = node.getIR();
            if (ir == null) continue;

            for (SSAInstruction instr : ir.getInstructions()) {
                if (instr == null) continue;
                if (instr instanceof SSAPutInstruction put) {
                    FieldReference fr = put.getDeclaredField();
                    modFields.add(fr.getDeclaringClass().getName().toString().replace('/', '.') + "." + fr.getName());
                } else if (instr instanceof SSAGetInstruction get) {
                    FieldReference fr = get.getDeclaredField();
                    refFields.add(fr.getDeclaringClass().getName().toString().replace('/', '.') + "." + fr.getName());
                }
            }
        }

        System.out.println("IR-based mod fields: " + modFields.size() + ", ref fields: " + refFields.size());

        return buildResults(slicedFields, modFields, refFields);
    }

    private List<FieldResult> buildResults(Set<SliceAnalysis.FieldInfo> slicedFields,
                                            Set<String> allModFields, Set<String> allRefFields) {
        List<FieldResult> results = new ArrayList<>();
        for (SliceAnalysis.FieldInfo field : slicedFields) {
            String key = field.declaringClass().replace('/', '.') + "." + field.fieldName();

            boolean isMod = allModFields.contains(key) || field.isWrite();
            boolean isRef = allRefFields.contains(key) || !field.isWrite();

            FieldResult.FieldCategory category;
            if (isMod && isRef) {
                category = FieldResult.FieldCategory.MOD_REF;
            } else if (isMod) {
                category = FieldResult.FieldCategory.MOD;
            } else {
                category = FieldResult.FieldCategory.REF;
            }

            results.add(new FieldResult(
                field.declaringClass(),
                field.fieldName(),
                field.typeDescriptor(),
                category
            ));
        }

        // Deduplicate
        Map<String, FieldResult> deduped = new LinkedHashMap<>();
        for (FieldResult r : results) {
            deduped.merge(r.key(), r, (existing, incoming) -> {
                if (existing.category() == FieldResult.FieldCategory.MOD_REF) return existing;
                if (incoming.category() == FieldResult.FieldCategory.MOD_REF) return incoming;
                if (existing.category() == FieldResult.FieldCategory.REF) return existing;
                return incoming;
            });
        }

        List<FieldResult> sorted = new ArrayList<>(deduped.values());
        sorted.sort(Comparator.comparing(FieldResult::declaringClass).thenComparing(FieldResult::fieldName));

        System.out.println("Classified " + sorted.size() + " unique fields");
        return sorted;
    }

    private void collectFieldKeys(OrdinalSet<PointerKey> keys, Set<String> output) {
        if (keys == null) return;
        for (PointerKey pk : keys) {
            if (pk instanceof InstanceFieldKey ifk) {
                IField field = ifk.getField();
                String key = field.getDeclaringClass().getName().toString().replace('/', '.') + "." + field.getName().toString();
                output.add(key);
            } else if (pk instanceof StaticFieldKey sfk) {
                IField field = sfk.getField();
                String key = field.getDeclaringClass().getName().toString().replace('/', '.') + "." + field.getName().toString();
                output.add(key);
            }
        }
    }
}
