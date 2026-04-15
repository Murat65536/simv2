package murat.simv2.analysis;

import com.ibm.wala.classLoader.IBytecodeMethod;
import com.ibm.wala.classLoader.IClass;
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
import com.ibm.wala.util.config.FileOfClasses;
import com.ibm.wala.ssa.IR;
import com.ibm.wala.ssa.SSAAbstractInvokeInstruction;
import com.ibm.wala.ssa.SSAInstruction;
import com.ibm.wala.ssa.SSAPutInstruction;
import com.ibm.wala.types.FieldReference;
import com.ibm.wala.types.TypeReference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
        // HeapExclusions prevent the SDG from tracking heap flow through
        // noisy JDK types (String, collections, etc.) that explode the solver
        System.out.println("Building SDG (data deps, NO_BASE_PTRS + heap exclusions)...");
        HeapExclusions heapExcl = buildHeapExclusions();
        long sdgStart = System.currentTimeMillis();
        SDG<InstanceKey> dataSDG = new SDG<>(cg, pa,
            ModRef.make(),
            DataDependenceOptions.NO_BASE_PTRS,
            ControlDependenceOptions.NONE,
            heapExcl);
        long sdgTime = System.currentTimeMillis() - sdgStart;
        System.out.println("SDG built in " + (sdgTime / 1000) + "s (" + dataSDG.getNumberOfNodes() + " nodes)");

        Set<Statement> allSliced = new HashSet<>();
        int succeeded = 0;
        int failed = 0;
        for (Statement seed : seeds) {
            try {
                Collection<Statement> slice = Slicer.computeBackwardSlice(dataSDG, seed);
                allSliced.addAll(slice);
                succeeded++;
                System.out.println("  Seed " + (succeeded + failed) + "/" + seeds.size()
                    + ": +" + slice.size() + " stmts (total: " + allSliced.size() + ")");
            } catch (OutOfMemoryError | Exception e) {
                failed++;
                System.err.println("  Seed " + (succeeded + failed) + "/" + seeds.size()
                    + ": FAILED " + e.getClass().getSimpleName());
            }
        }
        System.out.println("Phase 1 complete: " + succeeded + " succeeded, " + failed + " failed, "
            + allSliced.size() + " statements in slice");

        // Phase 2: add control-dependent statements
        System.out.println("Building SDG (control deps, NO_EXCEPTIONAL_EDGES + heap exclusions)...");
        sdgStart = System.currentTimeMillis();
        SDG<InstanceKey> ctrlSDG = new SDG<>(cg, pa,
            ModRef.make(),
            DataDependenceOptions.NO_BASE_PTRS,
            ControlDependenceOptions.NO_EXCEPTIONAL_EDGES,
            heapExcl);
        sdgTime = System.currentTimeMillis() - sdgStart;
        System.out.println("SDG built in " + (sdgTime / 1000) + "s (" + ctrlSDG.getNumberOfNodes() + " nodes)");

        int phase2Count = 0;
        for (int i = 0; i < seeds.size(); i++) {
            try {
                Collection<Statement> slice = Slicer.computeBackwardSlice(ctrlSDG, seeds.get(i));
                int before = allSliced.size();
                allSliced.addAll(slice);
                phase2Count += allSliced.size() - before;
                System.out.println("  Seed " + (i + 1) + "/" + seeds.size()
                    + ": +" + (allSliced.size() - before) + " new stmts");
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

            // Keep all Minecraft classes reached by the slice, not only the
            // primary entity/player hierarchy classes.
            if (!isMinecraftClass(className)) continue;

            String methodName = method.getName().toString();

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

    private HeapExclusions buildHeapExclusions() {
        // These types stay in the CG/CHA but the SDG won't track heap data flow through them.
        // This prevents the IFDS solver from exploding on String/collection internals.
        String patterns = String.join("\n",
            // JDK types that dominate SDG solver time
            "java/lang/String",
            "java/lang/AbstractStringBuilder",
            "java/lang/StringBuilder",
            "java/lang/StringBuffer",
            "java/lang/StringUTF16",
            "java/lang/StringLatin1",
            "java/lang/StringConcatHelper",
            "java/lang/Throwable",
            "java/lang/Exception",
            "java/lang/RuntimeException",
            "java/lang/NullPointerException",
            "java/lang/NumberFormatException",
            "java/lang/Integer",
            "java/lang/Enum",
            "java/lang/Class",
            "java/lang/Thread",
            "java/lang/Module.*",
            "java/lang/SecurityManager",
            "java/util/.*",
            // MC types with massive <clinit>s irrelevant to movement
            "net/minecraft/item/.*",
            "net/minecraft/block/.*",
            "net/minecraft/state/.*",
            "net/minecraft/entity/EntityType",
            "net/minecraft/entity/EntityType\\$.*",
            "net/minecraft/entity/damage/.*",
            "net/minecraft/entity/attribute/.*",
            "net/minecraft/entity/effect/StatusEffects",
            "net/minecraft/world/event/GameEvent",
            "net/minecraft/world/Difficulty",
            "net/minecraft/world/GameMode",
            "net/minecraft/world/GameRules.*",
            "net/minecraft/util/Identifier",
            "net/minecraft/util/Formatting",
            "net/minecraft/util/DyeColor",
            "net/minecraft/util/Rarity"
        ) + "\n";
        try {
            FileOfClasses classes = new FileOfClasses(
                new ByteArrayInputStream(patterns.getBytes(StandardCharsets.UTF_8)));
            return new HeapExclusions(classes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to build heap exclusions", e);
        }
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

    private boolean isMinecraftClass(String className) {
        return className != null
            && (className.startsWith("Lnet/minecraft/") || className.startsWith("net/minecraft/"));
    }
}
