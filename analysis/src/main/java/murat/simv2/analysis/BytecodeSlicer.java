package murat.simv2.analysis;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public final class BytecodeSlicer {
    public static void sliceJar(Path inputJar, Path outputJar, Path outputDir, WalaSlicer.SliceResult slice) throws Exception {
        Path generatedDir = outputDir.resolve("generated");
        java.nio.file.Files.createDirectories(generatedDir);

        try (ZipFile zipFile = new ZipFile(inputJar.toFile());
             ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outputJar.toFile()))) {

            var entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String name = entry.getName();
                
                if (entry.isDirectory()) {
                    zos.putNextEntry(new ZipEntry(name));
                    zos.closeEntry();
                    continue;
                }

                try (InputStream is = zipFile.getInputStream(entry)) {
                    byte[] originalBytes = is.readAllBytes();
                    byte[] processedBytes = null;

                    if (name.endsWith(".class") && !name.equals("module-info.class")) {
                        processedBytes = processClass(originalBytes, slice);
                        if (processedBytes != null) {
                            Path classOut = generatedDir.resolve(name);
                            java.nio.file.Files.createDirectories(classOut.getParent());
                            java.nio.file.Files.write(classOut, processedBytes);
                        }
                    } else if (name.endsWith(".java")) {
                        processedBytes = processSource(name, originalBytes, slice);
                        if (processedBytes != null) {
                            Path sourceOut = generatedDir.resolve(name);
                            java.nio.file.Files.createDirectories(sourceOut.getParent());
                            java.nio.file.Files.write(sourceOut, processedBytes);
                        }
                    }

                    byte[] finalBytes = processedBytes != null ? processedBytes : originalBytes;
                    ZipEntry newEntry = new ZipEntry(name);
                    zos.putNextEntry(newEntry);
                    zos.write(finalBytes);
                    zos.closeEntry();
                }
            }
        }
    }

    private static byte[] processSource(String name, byte[] sourceBytes, WalaSlicer.SliceResult slice) {
        String dotClass = name.replace('/', '.');
        if (dotClass.endsWith(".java")) {
            dotClass = dotClass.substring(0, dotClass.length() - 5);
        }

        Map<String, Set<Integer>> methodToLines = slice.lineByMethod().get(dotClass);
        if (methodToLines == null || methodToLines.isEmpty()) {
            // Class not in slice. Provide a stubbed source to match the stubbed class.
            String source = new String(sourceBytes, java.nio.charset.StandardCharsets.UTF_8);
            String[] lines = source.split("\\r?\\n", -1);
            StringBuilder sb = new StringBuilder();
            for (String line : lines) {
                String trimmed = line.trim();
                if (trimmed.startsWith("package ") || trimmed.startsWith("import ") || trimmed.contains("class ") || trimmed.contains("interface ") || trimmed.equals("{") || trimmed.equals("}")) {
                    sb.append(line).append("\n");
                } else if (!trimmed.isEmpty()) {
                    sb.append("// [pruned class] ").append(line).append("\n");
                }
            }
            return sb.toString().getBytes(java.nio.charset.StandardCharsets.UTF_8);
        }

        Set<Integer> allValidLines = new java.util.HashSet<>();
        methodToLines.values().forEach(allValidLines::addAll);

        String source = new String(sourceBytes, java.nio.charset.StandardCharsets.UTF_8);
        String[] lines = source.split("\\r?\\n", -1);
        StringBuilder sb = new StringBuilder();

        // Very simple line-based pruner.
        // It keeps lines that are in the slice.
        // It also keeps lines that look like class/method declarations (heuristically)
        // to maintain basic valid Java structure.
        for (int i = 0; i < lines.length; i++) {
            int lineNum = i + 1;
            String line = lines[i];
            String trimmed = line.trim();

            if (allValidLines.contains(lineNum)
                || trimmed.startsWith("package ")
                || trimmed.startsWith("import ")
                || trimmed.contains("class ")
                || trimmed.contains("interface ")
                || trimmed.contains("public ")
                || trimmed.contains("private ")
                || trimmed.contains("protected ")
                || trimmed.equals("{")
                || trimmed.equals("}")
                || trimmed.isEmpty()) {
                sb.append(line).append("\n");
            } else {
                // Pruned line. Replace with empty to preserve line numbers.
                sb.append("// [pruned] ").append(line).append("\n");
            }
        }
        return sb.toString().getBytes(java.nio.charset.StandardCharsets.UTF_8);
    }

    private static byte[] processClass(byte[] classBytes, WalaSlicer.SliceResult slice) {
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn, 0);

        String dotClass = cn.name.replace('/', '.');
        if (!dotClass.startsWith(AnalysisConfig.TARGET_PACKAGE_DOT)) {
            return classBytes;
        }

        Map<String, Set<Integer>> methodToBcIndices = slice.bcIndexByMethod().get(dotClass);
        if (methodToBcIndices == null) {
            methodToBcIndices = Map.of();
        }

        boolean modified = false;
        for (MethodNode mn : cn.methods) {
            String selector = mn.name + mn.desc;
            Set<Integer> keepIndices = methodToBcIndices.get(selector);

            if (keepIndices == null || keepIndices.isEmpty()) {
                // Method not in slice at all. Clear its body.
                if ((mn.access & Opcodes.ACC_ABSTRACT) == 0 && (mn.access & Opcodes.ACC_NATIVE) == 0) {
                    mn.instructions.clear();
                    mn.tryCatchBlocks.clear();
                    mn.localVariables.clear();

                    Type returnType = Type.getReturnType(mn.desc);
                    if (returnType == Type.VOID_TYPE) {
                        mn.instructions.add(new InsnNode(Opcodes.RETURN));
                    } else if (returnType.getSort() == Type.OBJECT || returnType.getSort() == Type.ARRAY) {
                        mn.instructions.add(new InsnNode(Opcodes.ACONST_NULL));
                        mn.instructions.add(new InsnNode(Opcodes.ARETURN));
                    } else if (returnType.getSort() == Type.LONG) {
                        mn.instructions.add(new InsnNode(Opcodes.LCONST_0));
                        mn.instructions.add(new InsnNode(Opcodes.LRETURN));
                    } else if (returnType.getSort() == Type.DOUBLE) {
                        mn.instructions.add(new InsnNode(Opcodes.DCONST_0));
                        mn.instructions.add(new InsnNode(Opcodes.DRETURN));
                    } else if (returnType.getSort() == Type.FLOAT) {
                        mn.instructions.add(new InsnNode(Opcodes.FCONST_0));
                        mn.instructions.add(new InsnNode(Opcodes.FRETURN));
                    } else {
                        mn.instructions.add(new InsnNode(Opcodes.ICONST_0));
                        mn.instructions.add(new InsnNode(Opcodes.IRETURN));
                    }
                    mn.maxStack = 2;
                    mn.maxLocals = Math.max(mn.maxLocals, 2);
                    modified = true;
                }
            } else {
                // Method is in slice.
                // For safety and compatibility with the JVM verifier, we only prune at the method level.
                // Removing individual instructions within a method disrupts stack frames (e.g. popping arguments 
                // for method calls that were removed) and causes VerifyErrors.
            }
        }

        if (modified) {
            // COMPUTE_FRAMES is critical to reconstruct stack map frames for modified classes
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            cn.accept(cw);
            return cw.toByteArray();
        }

        return classBytes;
    }
}
