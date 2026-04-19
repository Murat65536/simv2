package murat.simv2.analysis;

import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IField;
import com.ibm.wala.ipa.callgraph.CGNode;
import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.ipa.callgraph.propagation.InstanceKey;
import com.ibm.wala.ipa.callgraph.propagation.PointerAnalysis;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.ipa.slicer.NormalStatement;
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
import com.ibm.wala.types.FieldReference;
import com.ibm.wala.types.TypeReference;

import java.util.*;

public class SliceAnalysis {

    private final CallGraph cg;
    private final PointerAnalysis<InstanceKey> pa;
    private final IClassHierarchy cha;

    public SliceAnalysis(CallGraph cg, PointerAnalysis<InstanceKey> pa, IClassHierarchy cha) {
        this.cg = cg;
        this.pa = pa;
        this.cha = cha;
    }

    public Set<FieldInfo> analyze() {
        if (pa != null) {
            Set<FieldInfo> sliced = analyzeWithSlicing();
            if (!sliced.isEmpty()) {
                return sliced;
            }
            System.out.println("Backward slicing yielded no fields; falling back to IR scan.");
        }
        return analyzeWithIRScan();
    }

    /**
     * When pointer analysis is available, use backward slicing from seed fields/methods.
     */
    private Set<FieldInfo> analyzeWithSlicing() {
        System.out.println("Finding seed statements...");
        Collection<Statement> seeds = findSeedStatements();
        System.out.println("Found " + seeds.size() + " seed statements");

        if (seeds.isEmpty()) {
            System.err.println("WARNING: No seed statements found. Check entry points and seed fields.");
            return Collections.emptySet();
        }

        Set<Statement> allSliced = new HashSet<>();

        // Phase 1: Data flow slice
        System.out.println("Phase 1: Data-flow backward slice...");
        for (Statement seed : seeds) {
            try {
                Collection<Statement> slice = Slicer.computeBackwardSlice(
                    seed, cg, pa,
                    DataDependenceOptions.NO_BASE_PTRS,
                    ControlDependenceOptions.NONE
                );
                allSliced.addAll(slice);
            } catch (Exception e) {
                System.err.println("Slice failed for seed: " + seed + " — " + e.getMessage());
            }
        }
        System.out.println("Phase 1 collected " + allSliced.size() + " statements");

        // Phase 2: Control flow slice
        System.out.println("Phase 2: Control-flow backward slice...");
        for (Statement seed : seeds) {
            try {
                Collection<Statement> slice = Slicer.computeBackwardSlice(
                    seed, cg, pa,
                    DataDependenceOptions.NO_BASE_PTRS,
                    ControlDependenceOptions.NO_EXCEPTIONAL_EDGES
                );
                allSliced.addAll(slice);
            } catch (Exception e) {
                System.err.println("Slice failed for seed: " + seed + " — " + e.getMessage());
            }
        }
        System.out.println("Total sliced statements: " + allSliced.size());

        return extractFieldsFromStatements(allSliced);
    }

    /**
     * When no pointer analysis is available (CHA call graph), do a targeted
     * reachability walk from the entry point methods, collecting instance field
     * accesses on target entity classes along the way.
     *
     * Only follows calls within the target class hierarchy (Entity → ClientPlayerEntity)
     * to avoid pulling in every entity subclass that CHA resolves.
     */
    private Set<FieldInfo> analyzeWithIRScan() {
        System.out.println("Walking call graph from entry points within target class hierarchy...");

        // Build set of valid instance field names on target classes
        Set<String> validInstanceFields = new HashSet<>();
        for (String targetName : AnalysisConfig.TARGET_CLASSES) {
            TypeReference tr = TypeReference.findOrCreate(cha.getScope().getApplicationLoader(), targetName);
            IClass clazz = cha.lookupClass(tr);
            if (clazz == null) continue;
            for (IField f : clazz.getDeclaredInstanceFields()) {
                validInstanceFields.add(targetName + "." + f.getName());
            }
        }
        System.out.println("Valid instance fields on target classes: " + validInstanceFields.size());

        // Find the entry point CGNodes — match by method name+descriptor since
        // cha.resolveMethod may have resolved to a different declaring class
        Set<CGNode> worklist = new LinkedHashSet<>();
        Set<CGNode> visited = new HashSet<>();

        Set<String> entrySelectors = new HashSet<>();
        for (AnalysisConfig.EntryMethod em : AnalysisConfig.ENTRY_METHODS) {
            entrySelectors.add(em.methodName() + em.descriptor());
        }

        for (CGNode node : cg) {
            String nodeClass = node.getMethod().getDeclaringClass().getName().toString();
            if (!isTargetClass(nodeClass)) continue;
            String selector = node.getMethod().getName().toString() + node.getMethod().getDescriptor().toString();
            if (entrySelectors.contains(selector)) {
                worklist.add(node);
                System.out.println("  Matched entry CGNode: " + node.getMethod().getSignature());
            }
        }
        System.out.println("Starting walk from " + worklist.size() + " entry CGNodes");

        // BFS: follow calls, but only into methods declared on target classes or
        // methods that target classes call (one hop outside for utility methods)
        Set<FieldInfo> fields = new HashSet<>();
        int scanned = 0;

        while (!worklist.isEmpty()) {
            CGNode node = worklist.iterator().next();
            worklist.remove(node);
            if (!visited.add(node)) continue;

            IR ir = node.getIR();
            if (ir == null) continue;
            scanned++;

            String nodeClass = node.getMethod().getDeclaringClass().getName().toString();
            boolean isTargetMethod = isTargetClass(nodeClass);

            // Collect field accesses
            for (SSAInstruction instr : ir.getInstructions()) {
                if (instr == null) continue;

                if (instr instanceof SSAFieldAccessInstruction fieldInstr) {
                    FieldReference fieldRef = fieldInstr.getDeclaredField();
                    String declClass = fieldRef.getDeclaringClass().getName().toString();
                    String fieldKey = declClass + "." + fieldRef.getName();

                    if (validInstanceFields.contains(fieldKey)) {
                        boolean isWrite = instr instanceof SSAPutInstruction;
                        fields.add(new FieldInfo(
                            declClass,
                            fieldRef.getName().toString(),
                            fieldRef.getFieldType().getName().toString(),
                            isWrite
                        ));
                    }
                }
            }

            // Follow calls into target class methods (and one hop for non-target utility calls)
            Iterator<CGNode> succs = cg.getSuccNodes(node);
            while (succs.hasNext()) {
                CGNode succ = succs.next();
                if (visited.contains(succ)) continue;
                String succClass = succ.getMethod().getDeclaringClass().getName().toString();
                // Always follow calls within target hierarchy
                if (isTargetClass(succClass)) {
                    worklist.add(succ);
                }
                // From a target class method, also follow one hop into non-target methods
                // (e.g., Vec3d.multiply, Box.offset) — but don't follow their children
                else if (isTargetMethod) {
                    // Scan this node for field accesses but don't add to worklist
                    // (prevents explosion into unrelated code)
                    IR succIr = succ.getIR();
                    if (succIr != null && !visited.contains(succ)) {
                        for (SSAInstruction instr : succIr.getInstructions()) {
                            if (instr == null) continue;
                            if (instr instanceof SSAFieldAccessInstruction fieldInstr) {
                                FieldReference fieldRef = fieldInstr.getDeclaredField();
                                String declClass = fieldRef.getDeclaringClass().getName().toString();
                                String fieldKey = declClass + "." + fieldRef.getName();
                                if (validInstanceFields.contains(fieldKey)) {
                                    boolean isWrite = instr instanceof SSAPutInstruction;
                                    fields.add(new FieldInfo(
                                        declClass,
                                        fieldRef.getName().toString(),
                                        fieldRef.getFieldType().getName().toString(),
                                        isWrite
                                    ));
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Walked " + scanned + " methods (visited " + visited.size() + "), found " + fields.size() + " instance field accesses");
        return fields;
    }

    private Collection<Statement> findSeedStatements() {
        List<Statement> seeds = new ArrayList<>();

        for (CGNode node : cg) {
            IR ir = node.getIR();
            if (ir == null) continue;

            SSAInstruction[] instructions = ir.getInstructions();
            for (int i = 0; i < instructions.length; i++) {
                SSAInstruction instr = instructions[i];
                if (instr == null) continue;

                if (instr instanceof SSAPutInstruction put) {
                    FieldReference field = put.getDeclaredField();
                    String declClass = field.getDeclaringClass().getName().toString();
                    if (isTargetClass(declClass) && AnalysisConfig.SEED_FIELDS.contains(field.getName().toString())) {
                        seeds.add(new NormalStatement(node, i));
                    }
                }

                if (instr instanceof SSAAbstractInvokeInstruction invoke) {
                    String methodName = invoke.getDeclaredTarget().getName().toString();
                    String declClass = invoke.getDeclaredTarget().getDeclaringClass().getName().toString();
                    if (isTargetClass(declClass) && AnalysisConfig.SEED_METHODS.contains(methodName)) {
                        seeds.add(new NormalStatement(node, i));
                    }
                }
            }
        }

        return seeds;
    }

    private Set<FieldInfo> extractFieldsFromStatements(Set<Statement> slicedStatements) {
        Set<FieldInfo> fields = new HashSet<>();

        for (Statement stmt : slicedStatements) {
            if (stmt.getKind() != Statement.Kind.NORMAL) continue;
            NormalStatement ns = (NormalStatement) stmt;
            SSAInstruction instr = ns.getInstruction();

            if (instr instanceof SSAFieldAccessInstruction fieldInstr) {
                FieldReference fieldRef = fieldInstr.getDeclaredField();
                String declClass = fieldRef.getDeclaringClass().getName().toString();

                if (isTargetClass(declClass)) {
                    boolean isWrite = instr instanceof SSAPutInstruction;
                    fields.add(new FieldInfo(
                        declClass,
                        fieldRef.getName().toString(),
                        fieldRef.getFieldType().getName().toString(),
                        isWrite
                    ));
                }
            }
        }

        return fields;
    }

    private boolean isTargetClass(String className) {
        if (AnalysisConfig.TARGET_CLASSES.contains(className)) return true;

        TypeReference typeRef = TypeReference.findOrCreate(cha.getScope().getApplicationLoader(), className);
        IClass clazz = cha.lookupClass(typeRef);
        if (clazz == null) return false;

        for (String target : AnalysisConfig.TARGET_CLASSES) {
            TypeReference targetRef = TypeReference.findOrCreate(cha.getScope().getApplicationLoader(), target);
            IClass targetClass = cha.lookupClass(targetRef);
            if (targetClass != null && cha.isSubclassOf(clazz, targetClass)) {
                return true;
            }
        }
        return false;
    }

    public record FieldInfo(String declaringClass, String fieldName, String typeDescriptor, boolean isWrite) {
        public String key() {
            return declaringClass + "." + fieldName;
        }
    }
}
