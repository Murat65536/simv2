package murat.simv2.analysis;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.InvokeDynamicInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.MultiANewArrayInsnNode;
import org.objectweb.asm.tree.TryCatchBlockNode;
import org.objectweb.asm.tree.TypeInsnNode;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

final class JarStripper {

    static final String MC_PREFIX = "net/minecraft/";

    record MethodRef(String owner, String name, String desc) {
    }

    record FieldRef(String owner, String name) {
    }

    record Stats(
        int classesIn, int classesEmitted, int classesDropped,
        int movementClasses, int stubOnlyClasses, int dataOnlyClasses, int shellClasses,
        int methodsKept, int methodsStubbed, int methodsDropped,
        int fieldsKept, int fieldsDropped, long bytesOut) {
    }

    enum Mode {

        LOADABLE,

        MOVEMENT_ONLY
    }

    private final Map<String, byte[]> classBytes;
    private final Map<String, ClassNode> nodeCache = new HashMap<>();

    private final Set<String> keepClasses = new HashSet<>();
    private final Set<MethodRef> keepWhole = new HashSet<>();
    private final Set<MethodRef> stubMethods = new HashSet<>();
    private final Set<FieldRef> keepFields = new HashSet<>();
    private final Set<String> protectedClasses = new HashSet<>();

    private final ArrayDeque<MethodRef> scanQueue = new ArrayDeque<>();
    private final Set<MethodRef> scanned = new HashSet<>();

    private JarStripper(Map<String, byte[]> classBytes) {
        this.classBytes = classBytes;
    }

    static Stats strip(
        Path inJar,
        Set<MethodRef> movementMethods,
        Set<FieldRef> movementFields,
        Set<String> protectedInternal,
        Path outJar,
        Mode mode) throws IOException {
        Map<String, byte[]> bytes = readClasses(inJar);
        JarStripper s = new JarStripper(bytes);
        return s.run(movementMethods, movementFields, protectedInternal, outJar, mode);
    }

    private Stats run(
        Set<MethodRef> movementMethods,
        Set<FieldRef> movementFields,
        Set<String> protectedInternal,
        Path outJar,
        Mode mode) throws IOException {
        int classesIn = classBytes.size();

        for (String c : protectedInternal) {
            if (existsMc(c)) {
                protectedClasses.add(c);
                keepClasses.add(c);
            }
        }
        for (FieldRef f : movementFields) {
            if (existsMc(f.owner())) {
                keepFields.add(f);
                keepClasses.add(f.owner());
            }
        }
        for (MethodRef m : movementMethods) {
            if (existsMc(m.owner())) {
                keepClasses.add(m.owner());
                enqueueWhole(m);
            }
        }

        while (!scanQueue.isEmpty()) {
            scanMethod(scanQueue.poll());
        }

        if (mode == Mode.LOADABLE) {
            structuralClosure();
        }

        return emit(outJar, classesIn, mode);
    }

    private void enqueueWhole(MethodRef m) {
        stubMethods.remove(m);
        if (keepWhole.add(m) && scanned.add(m)) {
            scanQueue.add(m);
        }
    }

    private void scanMethod(MethodRef ref) {
        ClassNode cn = node(ref.owner());
        if (cn == null) return;
        MethodNode mn = find(cn, ref.name(), ref.desc());
        if (mn == null) return;
        noteMethodDesc(ref.desc());
        if (mn.exceptions != null) mn.exceptions.forEach(this::noteInternal);
        if (mn.tryCatchBlocks != null) {
            for (TryCatchBlockNode tcb : mn.tryCatchBlocks) noteInternal(tcb.type);
        }
        if (mn.instructions == null) return;
        for (AbstractInsnNode insn : mn.instructions) {
            switch (insn) {
                case TypeInsnNode t -> noteType(t.desc);
                case MultiANewArrayInsnNode ma -> noteType(ma.desc);
                case FieldInsnNode f -> {
                    noteInternal(f.owner);
                    noteType(f.desc);
                    if (isMc(f.owner)) keepFields.add(new FieldRef(f.owner, f.name));
                }
                case MethodInsnNode mi -> refMethod(mi.owner, mi.name, mi.desc);
                case InvokeDynamicInsnNode id -> {
                    noteMethodDesc(id.desc);
                    if (id.bsm != null) refHandle(id.bsm);
                    if (id.bsmArgs != null) {
                        for (Object a : id.bsmArgs) noteConstant(a);
                    }
                }
                case LdcInsnNode ldc -> noteConstant(ldc.cst);
                case FrameNode fr -> {
                    noteFrameTypes(fr.local);
                    noteFrameTypes(fr.stack);
                }
                default -> {
                }
            }
        }
    }

    private void refMethod(String owner, String name, String desc) {
        noteInternal(owner);
        noteMethodDesc(desc);
        if (!isMc(owner)) return;
        MethodRef r = new MethodRef(owner, name, desc);

        if (name.equals("<init>") || name.startsWith("lambda$")) {
            enqueueWhole(r);
        } else if (!keepWhole.contains(r)) {
            stubMethods.add(r);
        }
    }

    private void refHandle(Handle h) {
        if (h == null) return;
        noteInternal(h.getOwner());
        if (h.getDesc() != null && h.getDesc().startsWith("(")) {
            noteMethodDesc(h.getDesc());
            refMethod(h.getOwner(), h.getName(), h.getDesc());
        } else {
            noteType(h.getDesc());
        }
    }

    private void noteConstant(Object cst) {
        if (cst instanceof Type t) {
            noteTypeObj(t);
        } else if (cst instanceof Handle h) {
            refHandle(h);
        }
    }

    private void noteFrameTypes(List<Object> entries) {
        if (entries == null) return;
        for (Object o : entries) {
            if (o instanceof String s) noteInternal(s);
        }
    }

    private void noteMethodDesc(String desc) {
        if (desc == null || !desc.startsWith("(")) return;
        for (Type t : Type.getArgumentTypes(desc)) noteTypeObj(t);
        noteTypeObj(Type.getReturnType(desc));
    }

    private void noteType(String s) {
        if (s == null) return;
        if (s.startsWith("[") || (s.startsWith("L") && s.endsWith(";"))) {
            noteTypeObj(Type.getType(s));
        } else {
            noteInternal(s);
        }
    }

    private void noteTypeObj(Type t) {
        if (t == null) return;
        if (t.getSort() == Type.ARRAY) t = t.getElementType();
        if (t.getSort() == Type.OBJECT) noteInternal(t.getInternalName());
    }

    private void noteInternal(String internal) {
        if (internal != null && isMc(internal) && classBytes.containsKey(internal)) {
            keepClasses.add(internal);
        }
    }

    private void structuralClosure() {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (String c : new ArrayList<>(keepClasses)) {
                ClassNode cn = node(c);
                if (cn == null) continue;
                if (isMc(cn.superName) && classBytes.containsKey(cn.superName)
                    && keepClasses.add(cn.superName)) {
                    changed = true;
                }
            }
        }
    }

    private Stats emit(Path outJar, int classesIn, Mode mode) throws IOException {
        Map<String, ClassNode> out = new TreeMap<>();
        int kept = 0;
        int stubbed = 0;
        int dropped = 0;
        int fKept = 0;
        int fDropped = 0;

        for (String c : keepClasses) {
            ClassNode cn = node(c);
            if (cn == null) continue;

            boolean hasWhole = false;
            for (MethodNode mn : cn.methods) {
                if (keepWhole.contains(new MethodRef(c, mn.name, mn.desc))) {
                    hasWhole = true;
                    break;
                }
            }

            if (mode == Mode.MOVEMENT_ONLY && !hasWhole) continue;

            List<MethodNode> methods = new ArrayList<>();
            for (MethodNode mn : cn.methods) {
                MethodRef r = new MethodRef(c, mn.name, mn.desc);
                if (keepWhole.contains(r)) {
                    methods.add(mn);
                    kept++;
                } else if (mode == Mode.LOADABLE && stubMethods.contains(r)) {
                    methods.add(stub(mn));
                    stubbed++;
                } else {
                    dropped++;
                }
            }
            cn.methods = methods;

            List<FieldNode> fields = new ArrayList<>();
            for (FieldNode fn : cn.fields) {
                if (keepFields.contains(new FieldRef(c, fn.name))) {
                    fields.add(fn);
                    fKept++;
                } else {
                    fDropped++;
                }
            }
            cn.fields = fields;

            if (mode == Mode.LOADABLE && cn.interfaces != null && !cn.interfaces.isEmpty()) {
                List<String> ifaces = new ArrayList<>();
                for (String i : cn.interfaces) {
                    if (!isMc(i) || keepClasses.contains(i)) ifaces.add(i);
                }
                cn.interfaces = ifaces;
            }
            out.put(c, cn);
        }

        int classesDropped = mode == Mode.LOADABLE ? pruneEmptyUnused(out) : 0;

        int movementClasses = 0;
        int stubOnly = 0;
        int dataOnly = 0;
        int shells = 0;
        for (ClassNode cn : out.values()) {
            boolean whole = false;
            for (MethodNode mn : cn.methods) {
                if (keepWhole.contains(new MethodRef(cn.name, mn.name, mn.desc))) {
                    whole = true;
                    break;
                }
            }
            if (whole) movementClasses++;
            else if (!cn.methods.isEmpty()) stubOnly++;
            else if (!cn.fields.isEmpty()) dataOnly++;
            else shells++;
        }

        long bytesOut = 0;
        Files.createDirectories(outJar.toAbsolutePath().getParent());
        try (JarOutputStream jos = new JarOutputStream(Files.newOutputStream(outJar))) {
            for (ClassNode cn : out.values()) {
                ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                cn.accept(cw);
                byte[] data = cw.toByteArray();
                JarEntry entry = new JarEntry(cn.name + ".class");
                jos.putNextEntry(entry);
                jos.write(data);
                jos.closeEntry();
                bytesOut += data.length;
            }
        }

        return new Stats(classesIn, out.size(), classesDropped,
            movementClasses, stubOnly, dataOnly, shells,
            kept, stubbed, dropped, fKept, fDropped, bytesOut);
    }

    private int pruneEmptyUnused(Map<String, ClassNode> out) {
        int removed = 0;
        boolean changed = true;
        while (changed) {
            changed = false;
            Set<String> structural = new HashSet<>();
            for (ClassNode cn : out.values()) {
                if (cn.superName != null) structural.add(cn.superName);
                if (cn.interfaces != null) structural.addAll(cn.interfaces);
            }
            var it = out.entrySet().iterator();
            while (it.hasNext()) {
                ClassNode cn = it.next().getValue();
                boolean empty = cn.methods.isEmpty() && cn.fields.isEmpty();
                boolean needed = structural.contains(cn.name) || protectedClasses.contains(cn.name);
                if (empty && !needed) {
                    it.remove();
                    removed++;
                    changed = true;
                }
            }
        }
        return removed;
    }

    private MethodNode stub(MethodNode mn) {
        String[] ex = mn.exceptions == null ? null : mn.exceptions.toArray(new String[0]);
        MethodNode s = new MethodNode(Opcodes.ASM9,
            mn.access & ~Opcodes.ACC_NATIVE, mn.name, mn.desc, null, ex);
        if ((mn.access & Opcodes.ACC_ABSTRACT) != 0) {

            s.access = mn.access;
            s.instructions = new InsnList();
            return s;
        }
        InsnList il = new InsnList();
        Type ret = Type.getReturnType(mn.desc);
        switch (ret.getSort()) {
            case Type.VOID -> il.add(new InsnNode(Opcodes.RETURN));
            case Type.BOOLEAN, Type.CHAR, Type.BYTE, Type.SHORT, Type.INT -> {
                il.add(new InsnNode(Opcodes.ICONST_0));
                il.add(new InsnNode(Opcodes.IRETURN));
            }
            case Type.LONG -> {
                il.add(new InsnNode(Opcodes.LCONST_0));
                il.add(new InsnNode(Opcodes.LRETURN));
            }
            case Type.FLOAT -> {
                il.add(new InsnNode(Opcodes.FCONST_0));
                il.add(new InsnNode(Opcodes.FRETURN));
            }
            case Type.DOUBLE -> {
                il.add(new InsnNode(Opcodes.DCONST_0));
                il.add(new InsnNode(Opcodes.DRETURN));
            }
            default -> {
                il.add(new InsnNode(Opcodes.ACONST_NULL));
                il.add(new InsnNode(Opcodes.ARETURN));
            }
        }
        s.instructions = il;
        return s;
    }

    private static MethodNode find(ClassNode cn, String name, String desc) {
        for (MethodNode mn : cn.methods) {
            if (mn.name.equals(name) && mn.desc.equals(desc)) return mn;
        }
        return null;
    }

    private ClassNode node(String internal) {
        if (nodeCache.containsKey(internal)) return nodeCache.get(internal);
        byte[] b = classBytes.get(internal);
        ClassNode cn = null;
        if (b != null) {
            cn = new ClassNode();
            new ClassReader(b).accept(cn, 0);
        }
        nodeCache.put(internal, cn);
        return cn;
    }

    private boolean existsMc(String internal) {
        return isMc(internal) && classBytes.containsKey(internal);
    }

    private static boolean isMc(String internal) {
        return internal != null && internal.startsWith(MC_PREFIX);
    }

    private static Map<String, byte[]> readClasses(Path jar) throws IOException {
        Map<String, byte[]> map = new HashMap<>();
        try (JarFile jf = new JarFile(jar.toFile())) {
            var entries = jf.entries();
            while (entries.hasMoreElements()) {
                JarEntry e = entries.nextElement();
                if (e.isDirectory() || !e.getName().endsWith(".class")) continue;
                String internal = e.getName().substring(0, e.getName().length() - ".class".length());
                try (InputStream is = jf.getInputStream(e)) {
                    map.put(internal, is.readAllBytes());
                }
            }
        }
        return map;
    }
}
