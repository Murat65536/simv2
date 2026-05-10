package murat.simv2.analysis;

import com.ibm.wala.shrike.shrikeBT.shrikeCT.ClassInstrumenter;
import com.ibm.wala.shrike.shrikeBT.shrikeCT.OfflineInstrumenter;
import com.ibm.wala.shrike.shrikeBT.DupInstruction;
import com.ibm.wala.shrike.shrikeBT.GotoInstruction;
import com.ibm.wala.shrike.shrikeBT.IConditionalBranchInstruction;
import com.ibm.wala.shrike.shrikeBT.IInstruction;
import com.ibm.wala.shrike.shrikeBT.MethodData;
import com.ibm.wala.shrike.shrikeBT.MethodEditor;
import com.ibm.wala.shrike.shrikeBT.MonitorInstruction;
import com.ibm.wala.shrike.shrikeBT.ConstantInstruction;
import com.ibm.wala.shrike.shrikeBT.ReturnInstruction;
import com.ibm.wala.shrike.shrikeBT.SwapInstruction;
import com.ibm.wala.shrike.shrikeBT.SwitchInstruction;
import com.ibm.wala.shrike.shrikeBT.ThrowInstruction;
import com.ibm.wala.shrike.shrikeBT.Util;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.CheckClassAdapter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class BytecodeSlicer {
    public static void sliceJar(Path inputJar, Path outputJar, WalaSlicer.SliceResult slice) throws Exception {
        OfflineInstrumenter instrumenter = new OfflineInstrumenter();
        // Add only class files and non-.java resources to avoid duplicating source files in the output JAR.
        try (java.util.jar.JarFile jf = new java.util.jar.JarFile(inputJar.toFile())) {
            var entries = jf.entries();
            while (entries.hasMoreElements()) {
                java.util.jar.JarEntry je = entries.nextElement();
                if (je.isDirectory()) continue;
                String name = je.getName();
                // Skip Java source files; keep .class and other resources
                if (name.endsWith(".java")) continue;
                instrumenter.addInputJarEntry(inputJar.toFile(), name);
            }
        }

        instrumenter.setOutputJar(outputJar.toFile());
        instrumenter.setPassUnmodifiedClasses(true);
        instrumenter.beginTraversal();

        long t0 = System.currentTimeMillis();
        int classCount = 0;
        int failed = 0;
        ClassInstrumenter classInstrumenter;
        while ((classInstrumenter = instrumenter.nextClass()) != null) {
            String className = "<unknown>";
            try {
                className = classInstrumenter.getReader().getName();
                pruneClass(classInstrumenter, slice);
                instrumenter.outputModifiedClass(classInstrumenter);
            } catch (Exception e) {
                failed++;
                System.err.printf("  [bytecode-slicer] FAILED %s: %s%n", className, e.getMessage());
            }
            classCount++;
            if (classCount % 1000 == 0) {
                double elapsed = (System.currentTimeMillis() - t0) / 1000.0;
                System.out.printf("  [bytecode-slicer] %d classes in %.1fs (%d failed)%n",
                    classCount, elapsed, failed);
            }
        }
        double elapsed = (System.currentTimeMillis() - t0) / 1000.0;
        System.out.printf("  [bytecode-slicer] done: %d classes in %.1fs (%d failed)%n",
            classCount, elapsed, failed);

        instrumenter.close();

        verifyOutputJar(outputJar);
    }

    /**
     * Walks the output JAR and runs ASM's bytecode verifier on every class.
     * Logs the first 10 failures with class+method context; does not abort on
     * failure (so we still see telemetry about the rest of the JAR).
     */
    private static void verifyOutputJar(Path outputJar) throws Exception {
        long t0 = System.currentTimeMillis();
        int verified = 0;
        int failed = 0;
        int maxLogged = 10;
        try (java.util.jar.JarFile jf = new java.util.jar.JarFile(outputJar.toFile())) {
            var entries = jf.entries();
            while (entries.hasMoreElements()) {
                java.util.jar.JarEntry je = entries.nextElement();
                if (!je.getName().endsWith(".class")) continue;
                byte[] bytes;
                try (var in = jf.getInputStream(je)) {
                    bytes = in.readAllBytes();
                }
                StringWriter sw = new StringWriter();
                try {
                    CheckClassAdapter.verify(new ClassReader(bytes), false, new PrintWriter(sw));
                    String report = sw.toString();
                    if (!report.isEmpty()) {
                        failed++;
                        if (failed <= maxLogged) {
                            System.err.printf("  [verify] FAILED %s%n%s%n", je.getName(), report);
                        }
                    } else {
                        verified++;
                    }
                } catch (Throwable t) {
                    failed++;
                    if (failed <= maxLogged) {
                        System.err.printf("  [verify] FAILED %s: %s%n", je.getName(), t.getMessage());
                    }
                }
            }
        }
        double elapsed = (System.currentTimeMillis() - t0) / 1000.0;
        System.out.printf("  [verify] %d ok, %d failed in %.1fs%n", verified, failed, elapsed);
    }

    private static void pruneClass(ClassInstrumenter classInstrumenter, WalaSlicer.SliceResult slice) throws Exception {
        String dotClass = classInstrumenter.getReader().getName().replace('/', '.');
        if (!dotClass.startsWith(AnalysisConfig.TARGET_PACKAGE_DOT)) {
            return;
        }

        Map<String, Set<Integer>> methodToBcIndices = slice.bcIndexByMethod().get(dotClass);
        int methodCount = classInstrumenter.getReader().getMethodCount();
        for (int i = 0; i < methodCount; i++) {
            int access = classInstrumenter.getReader().getMethodAccessFlags(i);
            if ((access & (java.lang.reflect.Modifier.ABSTRACT | java.lang.reflect.Modifier.NATIVE)) != 0) {
                continue;
            }

            String methodName = classInstrumenter.getReader().getMethodName(i);
            String selector = methodName
                + classInstrumenter.getReader().getMethodType(i);
            Set<Integer> keepIndices = methodToBcIndices == null ? null : methodToBcIndices.get(selector);
            boolean isInitOrClinit = "<init>".equals(methodName) || "<clinit>".equals(methodName);
            // Constructors that aren't in the slice stay untouched — stubbing
            // them out with a default return would break object construction
            // (superclass call, final field init). Constructors in the slice
            // get pruned like any other method.
            if (isInitOrClinit && (keepIndices == null || keepIndices.isEmpty())) {
                continue;
            }
            if (keepIndices == null || keepIndices.isEmpty()) {
                MethodData methodData = classInstrumenter.visitMethod(i);
                if (methodData == null) {
                    continue;
                }

                MethodEditor editor = new MethodEditor(methodData);
                editor.beginPass();
                editor.insertAtStart(new MethodEditor.Patch() {
                    @Override
                    public void emitTo(MethodEditor.Output w) {
                        emitDefaultReturn(w, Util.getReturnType(methodData.getSignature()));
                    }
                });
                editor.applyPatches();
                editor.endPass();
            } else {
                MethodData methodData = classInstrumenter.visitMethod(i);
                if (methodData == null) {
                    continue;
                }

                IInstruction[] instructions = methodData.getInstructions();
                // instructionsToBytecodes[j] is the original bytecode offset for
                // sequential instruction j. keepIndices stores bytecode offsets.
                int[] i2bc = methodData.getInstructionsToBytecodes();
                Set<Integer> keepSeq = expandKeepSet(instructions, i2bc, keepIndices);

                MethodEditor editor = new MethodEditor(methodData);
                editor.beginPass();
                for (int j = 0; j < instructions.length; j++) {
                    if (keepSeq.contains(j)) continue;
                    editor.replaceWith(j, new MethodEditor.Patch() {
                        @Override
                        public void emitTo(MethodEditor.Output w) { /* delete */ }
                    });
                }
                editor.applyPatches();
                editor.endPass();
            }
        }
    }

    /**
     * Expands WALA's SSA-level keep set to a bytecode-level keep set that
     * preserves stack balance and control-flow integrity.
     *
     * <p>WALA's slice operates on SSA and emits one bytecode offset per kept
     * SSA instruction. But bytecode is stack-based: a kept {@code invokevirtual}
     * needs its receiver and arguments already on the stack, even though
     * loading them is implicit at the SSA level. This method:
     * <ol>
     *   <li>Simulates the stack linearly through the method, recording for each
     *       instruction the list of earlier instructions that pushed the values
     *       it consumes.</li>
     *   <li>Seeds the keep set with: WALA's slice (bytecode offsets in {@code
     *       keepIndices}) plus all returns, branches, throws, monitor ops
     *       (required for valid bytecode / unchanged CFG).</li>
     *   <li>Propagates backwards: any kept instruction's pushers are also kept,
     *       transitively. This pulls in {@code aload}/{@code iconst}/{@code
     *       getfield}/etc. that feed kept calls.</li>
     * </ol>
     *
     * <p>Limitations: linear simulation, so stack state across branches and
     * exception-handler entries may be approximate. Conservatively keeps all
     * control flow so any over-approximation results in over-keeping, never
     * under-keeping.
     */
    private static Set<Integer> expandKeepSet(
        IInstruction[] instructions, int[] i2bc, Set<Integer> keepIndices) {
        List<List<Integer>> consumedBy = new ArrayList<>(instructions.length);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int j = 0; j < instructions.length; j++) {
            IInstruction instr = instructions[j];
            int pops;
            int pushes;
            if (instr instanceof DupInstruction d) {
                pops = d.getSize() + d.getDelta();
                pushes = 2 * d.getSize() + d.getDelta();
            } else if (instr instanceof SwapInstruction) {
                pops = 2;
                pushes = 2;
            } else {
                pops = instr.getPoppedCount();
                pushes = instr.getPushedWordSize() > 0 ? 1 : 0;
            }
            List<Integer> consumed = new ArrayList<>(pops);
            for (int p = 0; p < pops; p++) {
                if (stack.isEmpty()) break;
                consumed.add(stack.pop());
            }
            for (int p = 0; p < pushes; p++) {
                stack.push(j);
            }
            consumedBy.add(consumed);
        }

        Set<Integer> keep = new HashSet<>();
        for (int j = 0; j < instructions.length; j++) {
            IInstruction instr = instructions[j];
            boolean isControl = instr instanceof ReturnInstruction
                || instr instanceof GotoInstruction
                || instr instanceof IConditionalBranchInstruction
                || instr instanceof SwitchInstruction
                || instr instanceof ThrowInstruction
                || instr instanceof MonitorInstruction;
            if (isControl || keepIndices.contains(i2bc[j])) {
                keep.add(j);
            }
        }

        ArrayDeque<Integer> worklist = new ArrayDeque<>(keep);
        while (!worklist.isEmpty()) {
            int j = worklist.pop();
            for (int pusher : consumedBy.get(j)) {
                if (keep.add(pusher)) {
                    worklist.push(pusher);
                }
            }
        }
        return keep;
    }

    private static void emitDefaultReturn(MethodEditor.Output w, String returnType) {
        if ("V".equals(returnType)) {
            w.emit(ReturnInstruction.make(returnType));
        } else if ("J".equals(returnType)) {
            w.emit(ConstantInstruction.make(0L));
            w.emit(ReturnInstruction.make(returnType));
        } else if ("D".equals(returnType)) {
            w.emit(ConstantInstruction.make(0.0d));
            w.emit(ReturnInstruction.make(returnType));
        } else if ("F".equals(returnType)) {
            w.emit(ConstantInstruction.make(0.0f));
            w.emit(ReturnInstruction.make(returnType));
        } else if (returnType.startsWith("L") || returnType.startsWith("[")) {
            w.emit(ConstantInstruction.make(returnType, null));
            w.emit(ReturnInstruction.make(returnType));
        } else {
            w.emit(ConstantInstruction.make(0));
            w.emit(ReturnInstruction.make(returnType));
        }
    }
}
