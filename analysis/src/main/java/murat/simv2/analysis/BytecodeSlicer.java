package murat.simv2.analysis;

import com.ibm.wala.shrike.shrikeBT.shrikeCT.ClassInstrumenter;
import com.ibm.wala.shrike.shrikeBT.shrikeCT.OfflineInstrumenter;
import com.ibm.wala.shrike.shrikeBT.MethodData;
import com.ibm.wala.shrike.shrikeBT.MethodEditor;
import com.ibm.wala.shrike.shrikeBT.ConstantInstruction;
import com.ibm.wala.shrike.shrikeBT.ReturnInstruction;
import com.ibm.wala.shrike.shrikeBT.Util;

import java.nio.file.Path;
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

        ClassInstrumenter classInstrumenter;
        while ((classInstrumenter = instrumenter.nextClass()) != null) {
            pruneClass(classInstrumenter, slice);
            instrumenter.outputModifiedClass(classInstrumenter);
        }

        instrumenter.close();
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
            if ("<init>".equals(methodName) || "<clinit>".equals(methodName)) {
                continue;
            }

            String selector = methodName
                + classInstrumenter.getReader().getMethodType(i);
            Set<Integer> keepIndices = methodToBcIndices == null ? null : methodToBcIndices.get(selector);
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
            }
        }
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
