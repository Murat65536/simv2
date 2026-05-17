package murat.simv2.analysis;

import com.ibm.wala.shrike.shrikeBT.shrikeCT.ClassInstrumenter;
import com.ibm.wala.shrike.shrikeBT.shrikeCT.OfflineInstrumenter;
import com.ibm.wala.shrike.shrikeBT.DupInstruction;
import com.ibm.wala.shrike.shrikeBT.ExceptionHandler;
import com.ibm.wala.shrike.shrikeBT.GotoInstruction;
import com.ibm.wala.shrike.shrikeBT.IConditionalBranchInstruction;
import com.ibm.wala.shrike.shrikeBT.IInstruction;
import com.ibm.wala.shrike.shrikeBT.ILoadInstruction;
import com.ibm.wala.shrike.shrikeBT.IStoreInstruction;
import com.ibm.wala.shrike.shrikeBT.MethodData;
import com.ibm.wala.shrike.shrikeBT.MethodEditor;
import com.ibm.wala.shrike.shrikeBT.MonitorInstruction;
import com.ibm.wala.shrike.shrikeBT.ReturnInstruction;
import com.ibm.wala.shrike.shrikeBT.SwapInstruction;
import com.ibm.wala.shrike.shrikeBT.SwitchInstruction;
import com.ibm.wala.shrike.shrikeBT.ThrowInstruction;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.analysis.Analyzer;
import org.objectweb.asm.tree.analysis.BasicValue;
import org.objectweb.asm.tree.analysis.BasicVerifier;
import org.objectweb.asm.util.CheckClassAdapter;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
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
        int keptWholeUnsafe = 0;
        ClassInstrumenter classInstrumenter;
        while ((classInstrumenter = instrumenter.nextClass()) != null) {
            String className = "<unknown>";
            try {
                className = classInstrumenter.getReader().getName();
                keptWholeUnsafe += pruneClass(classInstrumenter, slice);
                keptWholeUnsafe += emitWithUnsafeMethodFallback(
                    instrumenter, classInstrumenter, className);
            } catch (Throwable e) {
                if (e instanceof VirtualMachineError) throw e;
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
        System.out.printf(
            "  [bytecode-slicer] done: %d classes in %.1fs (%d failed, "
                + "%d methods kept whole: unsafe to prune)%n",
            classCount, elapsed, failed, keptWholeUnsafe);

        instrumenter.close();

        // WALA can emit structurally-valid bodies with stale StackMapTables
        // (silently swallowed FailureException). Recompute every pruned class's
        // frames and revert any still-broken method, per-method. The same pass
        // drops classes the slice never touches.
        repairModifiedClasses(inputJar, outputJar, slice);

        Set<String> bad = verifyOutputJar(outputJar);
        if (!bad.isEmpty()) {
            System.out.printf(
                "  [verify] restoring %d class(es) to original bytes "
                    + "(residual pruning defects): %s%n",
                bad.size(), bad);
            restoreOriginalClasses(inputJar, outputJar, bad);
            Set<String> stillBad = verifyOutputJar(outputJar);
            if (!stillBad.isEmpty()) {
                throw new IllegalStateException(
                    "Classes still invalid after restoring originals: " + stillBad);
            }
        }
    }

    /** Discards WALA's hard-coded internal {@code printStackTrace} spew. */
    private static final PrintStream NULL_ERR =
        new PrintStream(OutputStream.nullOutputStream());

    /**
     * Emits the (pruned) class to the output jar.
     *
     * <p>Unmodified classes ({@link ClassInstrumenter#isChanged()} false) are
     * passed through raw — no method changed, so WALA can't reject anything.
     *
     * <p>For a pruned class, if WALA throws {@code Error: Error compiling
     * method <m>: ...} (Shrike's CTCompiler hard-failed on stack-unbalanced
     * intermediate code), that one method is reverted to its original bytecode
     * via {@link ClassInstrumenter#resetMethod} and the emit retried. A reset
     * method is emitted verbatim from the original class — always valid, and a
     * superset of the slice is still sound (the never-under-keep rule), at
     * <em>method</em> granularity. Bounded by the method count.
     *
     * <p>WALA also has a second, silent failure mode: when our prune leaves a
     * structurally-valid body but the offset remap produces a stale
     * StackMapTable, {@code ClassInstrumenter} swallows the
     * {@code FailureException} (hard-coded {@code printStackTrace}, no rethrow)
     * and writes the method with a wrong table — the JVM later rejects it.
     * That can't be caught here, so it is repaired in a post-pass over the
     * finished jar ({@link #repairModifiedClasses}) which recomputes every
     * StackMapTable and reverts any still-broken method.
     *
     * <p>The emit runs under a suppressed {@code System.err}: WALA prints both
     * failure modes with a hard-coded {@code e.printStackTrace()}; we detect
     * and handle them ourselves, so the traces are pure noise.
     *
     * @return number of methods reverted (kept whole) for this class.
     */
    private static int emitWithUnsafeMethodFallback(
        OfflineInstrumenter instrumenter, ClassInstrumenter ci, String className)
        throws Exception {
        if (!ci.isChanged()) {
            instrumenter.outputModifiedClass(ci);
            return 0;
        }
        int reverted = 0;
        int methodCount = ci.getReader().getMethodCount();
        for (int attempt = 0; attempt <= methodCount; attempt++) {
            try {
                silently(() -> {
                    instrumenter.outputModifiedClass(ci);
                    return null;
                });
                return reverted;
            } catch (Throwable e) {
                if (e instanceof VirtualMachineError) throw e;
                String msg = e.getMessage();
                int badIdx = (msg != null && msg.startsWith("Error compiling method "))
                    ? findMethodIndex(ci, msg) : -1;
                if (badIdx < 0) {
                    // Not a recoverable per-method compile failure — let the
                    // caller log it as a class-level failure.
                    if (e instanceof Exception ex) throw ex;
                    throw new RuntimeException(e);
                }
                ci.resetMethod(badIdx);
                reverted++;
                logKeptWhole(ci, className, badIdx);
            }
        }
        throw new IllegalStateException(
            "Class " + className + " still fails to emit after reverting all methods");
    }

    /**
     * Runs {@code action} with {@code System.err} suppressed. WALA's
     * ClassInstrumenter swallows StackMapTable {@code FailureException}s with a
     * hard-coded {@code printStackTrace} and also prints before throwing
     * {@code "Error compiling method ..."}; neither is suppressible any other
     * way. We detect and handle both failures ourselves, so the traces are
     * pure noise. The slice loop is single-threaded, so swapping the global
     * stream here is safe.
     */
    private static <T> T silently(java.util.concurrent.Callable<T> action) throws Exception {
        PrintStream original = System.err;
        System.setErr(NULL_ERR);
        try {
            return action.call();
        } finally {
            System.setErr(original);
        }
    }

    private static void logKeptWhole(ClassInstrumenter ci, String className, int idx)
        throws Exception {
        System.err.printf(
            "  [bytecode-slicer] kept whole (unsafe to prune): %s.%s%s%n",
            className,
            ci.getReader().getMethodName(idx),
            ci.getReader().getMethodType(idx));
    }

    /**
     * Post-pass that makes every pruned {@code net.minecraft.*} class
     * JVM-valid, repairing the two defects WALA's emit cannot:
     * <ol>
     *   <li><b>Stale StackMapTables.</b> WALA remaps frame offsets through the
     *       prune; for stubbed/heavily-pruned methods the result is a table
     *       that no longer matches the code (JVM {@code VerifyError}). Every
     *       class is re-read with frames skipped and rewritten with ASM
     *       {@code COMPUTE_FRAMES}, regenerating correct tables. The custom
     *       {@link FrameClassWriter#getCommonSuperClass} never loads classes
     *       (the Minecraft type closure isn't on this classpath), so this adds
     *       no missing-dependency noise.</li>
     *   <li><b>Under-kept methods.</b> A method whose pruned body is
     *       structurally invalid (stack height/size) is detected with ASM's
     *       type-free {@link BasicVerifier} and its body replaced by the
     *       original class's — per-method revert (sound over-approximation, the
     *       never-under-keep rule), instead of restoring the whole class.</li>
     * </ol>
     * The output is a pure <b>overlay</b>: an entry is emitted only when it is a
     * {@code net.minecraft.*} {@code .class} the slicer actually modified
     * ({@code !}{@link #classNeverUsed}). Pass-through library classes,
     * never-used {@code net.minecraft} classes, and every non-class resource /
     * directory are dropped; the consumer resolves them from the original
     * Minecraft jar on the classpath. Safe because the sliced jar is a
     * write-only analysis artifact (loaded/executed by nothing in the repo).
     *
     * @return number of methods reverted to original across all classes.
     */
    private static int repairModifiedClasses(
        Path inputJar, Path outputJar, WalaSlicer.SliceResult slice) throws Exception {
        long t0 = System.currentTimeMillis();
        int classesRepaired = 0;
        int methodsReverted = 0;
        int kept = 0;
        int dropped = 0;
        Path tmp = Files.createTempFile("sliced-repair", ".jar");
        try (java.util.jar.JarFile in = new java.util.jar.JarFile(inputJar.toFile());
             java.util.jar.JarFile out = new java.util.jar.JarFile(outputJar.toFile());
             var zos = new java.util.zip.ZipOutputStream(Files.newOutputStream(tmp))) {
            var entries = out.entries();
            while (entries.hasMoreElements()) {
                java.util.jar.JarEntry je = entries.nextElement();
                String name = je.getName();
                boolean changed = !je.isDirectory()
                    && name.endsWith(".class")
                    && name.startsWith(AnalysisConfig.TARGET_PACKAGE_INTERNAL)
                    && !classNeverUsed(name, slice);
                if (!changed) {
                    // Pure overlay: emit only the net.minecraft classes the
                    // slicer actually modified. Everything else — pass-through
                    // library classes, never-used net.minecraft classes, all
                    // non-class resources and directory entries — is dropped;
                    // the consumer resolves them from the original Minecraft
                    // jar on the classpath.
                    dropped++;
                    continue;
                }
                byte[] bytes;
                try (var is = out.getInputStream(je)) {
                    bytes = is.readAllBytes();
                }
                int[] revertedOut = new int[1];
                bytes = repairClass(bytes, in, name, revertedOut);
                if (revertedOut[0] > 0) {
                    classesRepaired++;
                    methodsReverted += revertedOut[0];
                }
                zos.putNextEntry(new java.util.zip.ZipEntry(name));
                zos.write(bytes);
                zos.closeEntry();
                kept++;
            }
        }
        Files.move(tmp, outputJar, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        double elapsed = (System.currentTimeMillis() - t0) / 1000.0;
        System.out.printf(
            "  [repair] recomputed stack maps; reverted %d method(s) in %d class(es); "
                + "overlay: kept %d changed class(es), dropped %d entr(ies) in %.1fs%n",
            methodsReverted, classesRepaired, kept, dropped, elapsed);
        return methodsReverted;
    }

    /**
     * A {@code net.minecraft.*} class is <em>never used</em> when the slice
     * keeps no bytecode anywhere in it — its dotted name has no non-empty
     * keep-set in {@link WalaSlicer.SliceResult#bcIndexByMethod()} (absent,
     * empty, or every selector maps to an empty set). This is exactly the set
     * {@code pruneClass} reduces to constructor-only shells, so dropping the
     * whole entry removes nothing the emitted slice depends on. Entry names and
     * WALA's recorded class names both use {@code $} for nested classes, so
     * inner classes line up.
     */
    private static boolean classNeverUsed(String entryName, WalaSlicer.SliceResult slice) {
        String dotClass = entryName
            .substring(0, entryName.length() - ".class".length())
            .replace('/', '.');
        Map<String, Set<Integer>> methods = slice.bcIndexByMethod().get(dotClass);
        if (methods == null || methods.isEmpty()) {
            return true;
        }
        for (Set<Integer> kept : methods.values()) {
            if (kept != null && !kept.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Recomputes {@code classBytes}'s stack maps, reverting (to the original
     * class in {@code inputJar}) any method whose pruned body fails ASM's
     * structural verifier. Returns the repaired bytes; on any unexpected
     * failure returns the original class bytes (sound whole-class fallback —
     * {@link #verifyOutputJar} is the final backstop).
     */
    private static byte[] repairClass(
        byte[] classBytes, java.util.jar.JarFile inputJar, String entryName, int[] revertedOut)
        throws Exception {
        ClassNode cn = new ClassNode();
        new ClassReader(classBytes).accept(cn, ClassReader.SKIP_FRAMES);
        ClassNode original = null;
        for (int m = 0; m < cn.methods.size(); m++) {
            MethodNode mn = cn.methods.get(m);
            if ((mn.access & (java.lang.reflect.Modifier.ABSTRACT
                | java.lang.reflect.Modifier.NATIVE)) != 0) {
                continue;
            }
            if (methodStructurallyValid(cn.name, mn)) {
                continue;
            }
            if (original == null) {
                original = readOriginalClass(inputJar, entryName);
                if (original == null) {
                    return originalBytes(inputJar, entryName, classBytes);
                }
            }
            MethodNode orig = findMethod(original, mn.name, mn.desc);
            if (orig == null) {
                return originalBytes(inputJar, entryName, classBytes);
            }
            cn.methods.set(m, orig);
            revertedOut[0]++;
            System.err.printf(
                "  [repair] kept whole (unsafe to prune): %s.%s%s%n",
                cn.name, mn.name, mn.desc);
        }
        try {
            var cw = new FrameClassWriter(org.objectweb.asm.ClassWriter.COMPUTE_FRAMES);
            cn.accept(cw);
            return cw.toByteArray();
        } catch (Throwable t) {
            if (t instanceof VirtualMachineError vme) throw vme;
            // COMPUTE_FRAMES still couldn't frame the class — fall back to the
            // original (whole-class). Rare; verifyOutputJar is the backstop.
            byte[] orig = originalBytes(inputJar, entryName, classBytes);
            if (orig != classBytes) {
                revertedOut[0] = Math.max(revertedOut[0], 1);
            }
            return orig;
        }
    }

    private static boolean methodStructurallyValid(String owner, MethodNode mn) {
        try {
            new Analyzer<BasicValue>(new BasicVerifier()).analyze(owner, mn);
            return true;
        } catch (Throwable t) {
            if (t instanceof VirtualMachineError vme) throw vme;
            return false;
        }
    }

    private static ClassNode readOriginalClass(java.util.jar.JarFile inputJar, String entryName)
        throws Exception {
        java.util.jar.JarEntry e = inputJar.getJarEntry(entryName);
        if (e == null) return null;
        byte[] b;
        try (var is = inputJar.getInputStream(e)) {
            b = is.readAllBytes();
        }
        ClassNode cn = new ClassNode();
        new ClassReader(b).accept(cn, ClassReader.SKIP_FRAMES);
        return cn;
    }

    private static byte[] originalBytes(
        java.util.jar.JarFile inputJar, String entryName, byte[] fallback) throws Exception {
        java.util.jar.JarEntry e = inputJar.getJarEntry(entryName);
        if (e == null) return fallback;
        try (var is = inputJar.getInputStream(e)) {
            return is.readAllBytes();
        }
    }

    private static MethodNode findMethod(ClassNode cn, String name, String desc) {
        for (MethodNode mn : cn.methods) {
            if (mn.name.equals(name) && mn.desc.equals(desc)) return mn;
        }
        return null;
    }

    /**
     * ASM {@link org.objectweb.asm.ClassWriter} whose frame merge never loads a
     * class. The Minecraft type closure is not on this process's classpath, so
     * the default {@code getCommonSuperClass} (which calls {@code
     * Class.forName}) would throw {@code TypeNotPresentException} for nearly
     * every merge. Falling back to {@code java/lang/Object} yields verifiable
     * (if conservative) frames with zero classpath dependency.
     */
    private static final class FrameClassWriter extends org.objectweb.asm.ClassWriter {
        FrameClassWriter(int flags) {
            super(flags);
        }

        @Override
        protected String getCommonSuperClass(String type1, String type2) {
            return type1.equals(type2) ? type1 : "java/lang/Object";
        }
    }

    /**
     * Maps the {@code md} in Shrike's "Error compiling method &lt;md&gt;: ..."
     * to its method index. {@code md.toString()} is
     * {@code L<class>;.<name><descriptor>}.
     */
    private static int findMethodIndex(ClassInstrumenter ci, String message) throws Exception {
        var reader = ci.getReader();
        String classPrefix = "L" + reader.getName() + ";.";
        int methodCount = reader.getMethodCount();
        for (int i = 0; i < methodCount; i++) {
            String token = "Error compiling method " + classPrefix
                + reader.getMethodName(i) + reader.getMethodType(i) + ":";
            if (message.startsWith(token)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Walks the output JAR and runs ASM's bytecode verifier on the classes we
     * actually modified.
     *
     * <p>Only {@code net.minecraft.*} classes are pruned ({@link #pruneClass}
     * early-returns for everything else), so pass-through classes are
     * byte-identical to the input — verifying them tells us nothing about the
     * slice and only produces noise: ASM's {@code SimpleVerifier} resolves the
     * full type closure via {@code Class.forName}, so any class referencing
     * {@code org.joml}/{@code org.lwjgl}/{@code fastutil}/{@code blaze3d} (not
     * on this process's classpath) "fails" with {@code ClassNotFoundException}
     * regardless of slicing. Those are reported separately as
     * <em>unverifiable</em>, not failures.
     *
     * <p>Logs the first 10 genuine failures; does not abort.
     *
     * @return entry names of genuinely-invalid (pruning-broken) classes.
     */
    private static Set<String> verifyOutputJar(Path outputJar) throws Exception {
        long t0 = System.currentTimeMillis();
        int verified = 0;
        int failed = 0;
        int unverifiable = 0;
        int skipped = 0;
        int maxLogged = 10;
        Set<String> failedNames = new java.util.TreeSet<>();
        try (java.util.jar.JarFile jf = new java.util.jar.JarFile(outputJar.toFile())) {
            var entries = jf.entries();
            while (entries.hasMoreElements()) {
                java.util.jar.JarEntry je = entries.nextElement();
                if (!je.getName().endsWith(".class")) continue;
                // pruneClass only modifies net.minecraft.* — skip the rest.
                if (!je.getName().startsWith(AnalysisConfig.TARGET_PACKAGE_INTERNAL)) {
                    skipped++;
                    continue;
                }
                byte[] bytes;
                try (var in = jf.getInputStream(je)) {
                    bytes = in.readAllBytes();
                }
                StringWriter sw = new StringWriter();
                try {
                    CheckClassAdapter.verify(new ClassReader(bytes), false, new PrintWriter(sw));
                    String report = sw.toString();
                    if (report.isEmpty()) {
                        verified++;
                    } else if (isMissingDependency(report)) {
                        unverifiable++;
                    } else {
                        failed++;
                        failedNames.add(je.getName());
                        if (failed <= maxLogged) {
                            System.err.printf("  [verify] FAILED %s%n%s%n", je.getName(), report);
                        }
                    }
                } catch (Throwable t) {
                    if (isMissingDependency(t)) {
                        unverifiable++;
                    } else {
                        failed++;
                        failedNames.add(je.getName());
                        if (failed <= maxLogged) {
                            System.err.printf("  [verify] FAILED %s: %s%n", je.getName(), t.getMessage());
                        }
                    }
                }
            }
        }
        double elapsed = (System.currentTimeMillis() - t0) / 1000.0;
        System.out.printf(
            "  [verify] %d ok, %d failed, %d unverifiable (missing classpath dep), "
                + "%d skipped (pass-through) in %.1fs%n",
            verified, failed, unverifiable, skipped, elapsed);
        return failedNames;
    }

    /**
     * Backstop for residual pruning defects: any pruned class that still fails
     * real bytecode verification is rewritten with its original (unpruned)
     * bytes from the input JAR. The SSA→bytecode keep-set is inherently
     * approximate (branch-sensitive stack heights, complex CFG); a handful of
     * classes per run trip it. Restoring the original class is always
     * JVM-valid and a sound over-approximation of the slice (the
     * never-under-keep rule applied at class granularity).
     */
    private static void restoreOriginalClasses(
        Path inputJar, Path outputJar, Set<String> classes) throws Exception {
        Path tmp = Files.createTempFile("sliced-restore", ".jar");
        try (java.util.jar.JarFile in = new java.util.jar.JarFile(inputJar.toFile());
             java.util.jar.JarFile out = new java.util.jar.JarFile(outputJar.toFile());
             var zos = new java.util.zip.ZipOutputStream(Files.newOutputStream(tmp))) {
            var entries = out.entries();
            while (entries.hasMoreElements()) {
                java.util.jar.JarEntry je = entries.nextElement();
                zos.putNextEntry(new java.util.zip.ZipEntry(je.getName()));
                if (!je.isDirectory()) {
                    byte[] bytes;
                    if (classes.contains(je.getName())) {
                        java.util.jar.JarEntry orig = in.getJarEntry(je.getName());
                        try (var is = in.getInputStream(orig)) {
                            bytes = is.readAllBytes();
                        }
                    } else {
                        try (var is = out.getInputStream(je)) {
                            bytes = is.readAllBytes();
                        }
                    }
                    zos.write(bytes);
                }
                zos.closeEntry();
            }
        }
        Files.move(tmp, outputJar, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
    }

    /** A verifier complaint caused only by a type absent from this classpath. */
    private static boolean isMissingDependency(Throwable t) {
        for (Throwable c = t; c != null; c = c.getCause()) {
            if (c instanceof ClassNotFoundException
                || c instanceof NoClassDefFoundError
                || c instanceof TypeNotPresentException) {
                return true;
            }
        }
        return false;
    }

    private static boolean isMissingDependency(String report) {
        return report.contains("ClassNotFoundException")
            || report.contains("NoClassDefFoundError")
            || report.contains("TypeNotPresentException");
    }

    /** @return number of methods left unmodified because pruning was unsafe. */
    private static int pruneClass(ClassInstrumenter classInstrumenter, WalaSlicer.SliceResult slice) throws Exception {
        int keptWholeUnsafe = 0;
        String dotClass = classInstrumenter.getReader().getName().replace('/', '.');
        if (!dotClass.startsWith(AnalysisConfig.TARGET_PACKAGE_DOT)) {
            return keptWholeUnsafe;
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
            // Constructors that aren't in the slice stay untouched — deleting
            // <init> would leave the class non-instantiable and <clinit>
            // removal would drop static-field init. Constructors in the slice
            // get pruned like any other method.
            if (isInitOrClinit && (keepIndices == null || keepIndices.isEmpty())) {
                continue;
            }
            if (keepIndices == null || keepIndices.isEmpty()) {
                // Nothing of this method is in the slice — remove it entirely
                // rather than stubbing it with a default-return body.
                // Constructors are handled above; the sliced jar is an
                // analysis artifact (never executed), so dropping
                // never-sliced methods is sound and yields a smaller mirror.
                classInstrumenter.deleteMethod(i);
            } else {
                MethodData methodData = classInstrumenter.visitMethod(i);
                if (methodData == null) {
                    continue;
                }

                IInstruction[] instructions = methodData.getInstructions();
                // instructionsToBytecodes[j] is the original bytecode offset for
                // sequential instruction j. keepIndices stores bytecode offsets.
                int[] i2bc = methodData.getInstructionsToBytecodes();
                Set<Integer> keepSeq = expandKeepSet(
                    instructions, i2bc, keepIndices, methodData.getHandlers());
                if (keepSeq == null) {
                    // Unsafe to prune (linear stack model broke). Emit the
                    // method unchanged: original bytecode is always valid and
                    // a superset of the slice is still sound.
                    keptWholeUnsafe++;
                    continue;
                }

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
        return keptWholeUnsafe;
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
     *
     * <p>Returns {@code null} when the linear model cannot account for a
     * consumed value (the simulated stack underflows). That is precisely the
     * case where a derived keep set would under-keep and emit
     * stack-unbalanced bytecode; callers must then keep the whole method
     * unmodified (sound over-approximation — the never-under-keep rule).
     *
     * <p>Also seeds the keep set with every instruction inside an exception
     * handler's protected range and every handler (catch) entry. Deleting all
     * instructions a handler covers collapses its range to {@code start ==
     * end}, which the JVM classfile verifier rejects ("Empty try catch block
     * handler range"). Keeping covered instructions makes that impossible.
     */
    private static Set<Integer> expandKeepSet(
        IInstruction[] instructions, int[] i2bc, Set<Integer> keepIndices,
        ExceptionHandler[][] handlers) {
        List<List<Integer>> consumedBy = new ArrayList<>(instructions.length);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        boolean stackModelBroke = false;
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
                if (stack.isEmpty()) {
                    // Linear model lost track (branch join / handler entry):
                    // a consumed value has no recorded pusher. Pruning from
                    // here would under-keep -> bail to whole-method keep.
                    stackModelBroke = true;
                    break;
                }
                consumed.add(stack.pop());
            }
            for (int p = 0; p < pushes; p++) {
                stack.push(j);
            }
            consumedBy.add(consumed);
        }

        if (stackModelBroke) {
            return null;
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

        // Keep every instruction a handler protects, plus each catch entry, so
        // no exception-table range can collapse to empty after deletion.
        if (handlers != null) {
            for (int j = 0; j < handlers.length && j < instructions.length; j++) {
                ExceptionHandler[] hs = handlers[j];
                if (hs == null || hs.length == 0) continue;
                keep.add(j);
                for (ExceptionHandler h : hs) {
                    int target = h.getHandler();
                    if (target >= 0 && target < instructions.length) {
                        keep.add(target);
                    }
                }
            }
        }

        // Local-variable def-use. The stack model above does not track
        // locals: a kept `xload v` needs the `xstore v` that defined it, or
        // the verifier rejects "inexistant local variable v". Keep ALL stores
        // to a loaded local (sound over-approximation of reaching defs).
        Map<Integer, List<Integer>> storesByVar = new java.util.HashMap<>();
        for (int j = 0; j < instructions.length; j++) {
            if (instructions[j] instanceof IStoreInstruction st) {
                storesByVar.computeIfAbsent(st.getVarIndex(), k -> new ArrayList<>()).add(j);
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
            if (instructions[j] instanceof ILoadInstruction ld) {
                List<Integer> stores = storesByVar.get(ld.getVarIndex());
                if (stores != null) {
                    for (int s : stores) {
                        if (keep.add(s)) {
                            worklist.push(s);
                        }
                    }
                }
            }
        }
        return keep;
    }
}
