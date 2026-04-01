package murat.simv2.analysis;

import com.ibm.wala.classLoader.IBytecodeMethod;
import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IMethod;
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
import com.ibm.wala.ssa.SSAInstruction;
import com.ibm.wala.ssa.SSAPutInstruction;
import com.ibm.wala.types.FieldReference;
import com.ibm.wala.types.TypeReference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Runs WALA backward slicing from movement seed fields and exports
 * the slice as source line numbers per class/method.
 */
public class BackwardSliceExporter {

    private final CallGraph cg;
    private final PointerAnalysis<InstanceKey> pa;
    private final IClassHierarchy cha;

    public BackwardSliceExporter(CallGraph cg, PointerAnalysis<InstanceKey> pa, IClassHierarchy cha) {
        this.cg = cg;
        this.pa = pa;
        this.cha = cha;
    }

    /**
     * Computes the backward slice and returns line numbers grouped by class and method.
     * Structure: className -> methodSelector -> Set of line numbers
     */
    public Map<String, Map<String, Set<Integer>>> computeSliceLines() {
        System.out.println("Finding seed statements for backward slice...");
        List<Statement> seeds = findSeedStatements();
        System.out.println("Found " + seeds.size() + " seed statements");

        if (seeds.isEmpty()) {
            System.err.println("WARNING: No seed statements found for backward slice.");
            return Collections.emptyMap();
        }

        // Phase 1: data deps with heap tracking (NO_BASE_PTRS)
        // Container-CFA provides precise heap model for collections
        Set<Statement> allSliced = new HashSet<>();
        int succeeded = 0;
        int failed = 0;
        for (Statement seed : seeds) {
            try {
                Collection<Statement> slice = Slicer.computeBackwardSlice(
                    seed, cg, pa,
                    DataDependenceOptions.NO_BASE_PTRS,
                    ControlDependenceOptions.NONE
                );
                allSliced.addAll(slice);
                succeeded++;
            } catch (OutOfMemoryError | Exception e) {
                failed++;
                System.err.println("  Slice failed for seed #" + (succeeded + failed) + ": " + e.getClass().getSimpleName());
            }
        }
        System.out.println("Phase 1 complete: " + succeeded + " succeeded, " + failed + " failed, "
            + allSliced.size() + " statements in slice");

        // Phase 2: add control-dependent statements
        System.out.println("Phase 2: Adding control-dependent statements...");
        int phase2Count = 0;
        for (Statement seed : seeds) {
            try {
                Collection<Statement> slice = Slicer.computeBackwardSlice(
                    seed, cg, pa,
                    DataDependenceOptions.NO_BASE_PTRS,
                    ControlDependenceOptions.NO_EXCEPTIONAL_EDGES
                );
                int before = allSliced.size();
                allSliced.addAll(slice);
                phase2Count += allSliced.size() - before;
            } catch (OutOfMemoryError | Exception e) {
                // Control dep slice is optional — continue if it fails
            }
        }
        System.out.println("Phase 2 added " + phase2Count + " control-dependent statements (total: " + allSliced.size() + ")");

        return extractLineNumbers(allSliced);
    }

    private List<Statement> findSeedStatements() {
        List<Statement> seeds = new ArrayList<>();

        for (CGNode node : cg) {
            String nodeClass = node.getMethod().getDeclaringClass().getName().toString();
            if (!isTargetClass(nodeClass)) continue;

            IR ir = node.getIR();
            if (ir == null) continue;

            SSAInstruction[] instructions = ir.getInstructions();
            for (int i = 0; i < instructions.length; i++) {
                SSAInstruction instr = instructions[i];
                if (instr == null) continue;

                // Seed: field writes to pos/velocity/boundingBox/onGround/fallDistance
                if (instr instanceof SSAPutInstruction put) {
                    FieldReference field = put.getDeclaredField();
                    String declClass = field.getDeclaringClass().getName().toString();
                    if (isTargetClass(declClass) && AnalysisConfig.SEED_FIELDS.contains(field.getName().toString())) {
                        seeds.add(new NormalStatement(node, i));
                    }
                }

                // Seed: calls to setPosition/setVelocity/setPos/move
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

    private Map<String, Map<String, Set<Integer>>> extractLineNumbers(Set<Statement> statements) {
        // className (dot notation) -> methodSelector -> line numbers
        Map<String, Map<String, Set<Integer>>> result = new TreeMap<>();

        for (Statement stmt : statements) {
            if (stmt.getKind() != Statement.Kind.NORMAL) continue;
            NormalStatement ns = (NormalStatement) stmt;

            CGNode node = ns.getNode();
            IMethod method = node.getMethod();
            String className = method.getDeclaringClass().getName().toString();

            // Only keep lines in target classes
            if (!isTargetClass(className)) continue;

            // Only keep lines in target methods
            String methodName = method.getName().toString();
            if (!AnalysisConfig.TARGET_METHODS.contains(methodName)) continue;

            // Get source line number
            int line = getSourceLine(method, ns.getInstructionIndex());
            if (line < 0) continue;

            String dotClassName = className.substring(1).replace('/', '.');
            String methodSelector = methodName + method.getDescriptor().toString();

            result.computeIfAbsent(dotClassName, k -> new TreeMap<>())
                  .computeIfAbsent(methodSelector, k -> new TreeSet<>())
                  .add(line);
        }

        return result;
    }

    private int getSourceLine(IMethod method, int instructionIndex) {
        if (method instanceof IBytecodeMethod<?> bcMethod) {
            try {
                int bcIndex = bcMethod.getBytecodeIndex(instructionIndex);
                return bcMethod.getLineNumber(bcIndex);
            } catch (Exception e) {
                return -1;
            }
        }
        return -1;
    }

    public void exportToJson(Map<String, Map<String, Set<Integer>>> sliceLines, Path outputPath) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Files.writeString(outputPath, gson.toJson(sliceLines));
        System.out.println("Exported slice line numbers to " + outputPath);

        // Print summary
        int totalMethods = 0;
        int totalLines = 0;
        for (var classEntry : sliceLines.entrySet()) {
            for (var methodEntry : classEntry.getValue().entrySet()) {
                totalMethods++;
                totalLines += methodEntry.getValue().size();
                System.out.println("  " + classEntry.getKey() + "." + methodEntry.getKey()
                    + " → " + methodEntry.getValue().size() + " lines");
            }
        }
        System.out.println("Total: " + totalMethods + " methods, " + totalLines + " slice lines");
    }

    private boolean isTargetClass(String className) {
        if (AnalysisConfig.TARGET_CLASSES.contains(className)) return true;

        TypeReference typeRef = TypeReference.findOrCreate(cha.getScope().getApplicationLoader(), className);
        IClass clazz = cha.lookupClass(typeRef);
        if (clazz == null) return false;

        for (String target : AnalysisConfig.TARGET_CLASSES) {
            TypeReference targetRef = TypeReference.findOrCreate(cha.getScope().getApplicationLoader(), target);
            IClass targetClass = cha.lookupClass(targetRef);
            if (targetClass != null && cha.isSubclassOf(targetClass, clazz)) {
                return true;
            }
        }
        return false;
    }
}
