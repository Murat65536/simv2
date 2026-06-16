package murat.simv2.analysis;

import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IField;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.TypeReference;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

final class ClosureBuilder {
    private ClosureBuilder() {
    }

    static MirrorClosure build(WalaSlicer.SliceResult slice, IClassHierarchy cha) {
        Set<String> closure = new TreeSet<>(AnalysisConfig.REQUIRED_PRIMARY_CLASSES);
        closure.addAll(slice.lineByMethod().keySet());
        for (FieldResult f : slice.fields()) {
            closure.add(f.declaringClass());
            String referenced = referenceFromDescriptor(f.typeDescriptor());
            if (referenced != null) {
                closure.add(referenced);
            }
        }

        Deque<String> work = new ArrayDeque<>(closure);
        while (!work.isEmpty()) {
            String dotClass = work.poll();
            IClass klass = lookup(cha, dotClass);
            if (klass == null) continue;

            IClass sup = klass.getSuperclass();
            if (sup != null) {
                addIfNew(closure, work, classNameOf(sup));
            }
            for (IClass iface : klass.getAllImplementedInterfaces()) {
                addIfNew(closure, work, classNameOf(iface));
            }

            for (IField field : klass.getDeclaredInstanceFields()) {
                String typeName = field.getFieldTypeReference().getName().toString();
                String referenced = referenceFromInternal(typeName);
                if (referenced != null) {
                    addIfNew(closure, work, referenced);
                }
            }
            for (IField field : klass.getDeclaredStaticFields()) {
                String typeName = field.getFieldTypeReference().getName().toString();
                String referenced = referenceFromInternal(typeName);
                if (referenced != null) {
                    addIfNew(closure, work, referenced);
                }
            }

            for (IMethod method : klass.getDeclaredMethods()) {
                String descriptor = method.getDescriptor().toString();
                for (String referenced : descriptorReferences(descriptor)) {
                    addIfNew(closure, work, referenced);
                }
            }

            int dollar = dotClass.lastIndexOf('$');
            while (dollar > 0) {
                String owner = dotClass.substring(0, dollar);
                addIfNew(closure, work, owner);
                dollar = owner.lastIndexOf('$');
                dotClass = owner;
            }
        }

        return new MirrorClosure(Set.copyOf(closure));
    }

    private static void addIfNew(Set<String> closure, Deque<String> work, String name) {
        if (name == null || name.isBlank()) return;
        if (!name.startsWith("net.minecraft.")) return;
        if (closure.add(name)) {
            work.add(name);
        }
    }

    private static IClass lookup(IClassHierarchy cha, String dotClass) {
        String internal = "L" + dotClass.replace('.', '/');
        TypeReference ref = TypeReference.findOrCreate(
            ClassLoaderReference.Application, internal);
        return cha.lookupClass(ref);
    }

    private static String classNameOf(IClass klass) {
        String internal = klass.getName().toString();
        if (!internal.startsWith("Lnet/minecraft/")) return null;
        return internal.substring(1).replace('/', '.');
    }

    private static Set<String> descriptorReferences(String descriptor) {
        if (descriptor == null) return Set.of();
        Set<String> out = new LinkedHashSet<>();
        int i = 0;
        while (i < descriptor.length()) {
            char c = descriptor.charAt(i);
            if (c == 'L') {
                int end = descriptor.indexOf(';', i);
                if (end < 0) break;
                String internal = descriptor.substring(i + 1, end);
                if (internal.startsWith("net/minecraft/")) {
                    out.add(internal.replace('/', '.'));
                }
                i = end + 1;
            } else {
                i++;
            }
        }
        return out;
    }

    private static String referenceFromDescriptor(String descriptor) {
        if (descriptor == null || descriptor.isEmpty()) return null;
        int i = 0;
        while (i < descriptor.length() && descriptor.charAt(i) == '[') i++;
        if (i >= descriptor.length()) return null;
        char c = descriptor.charAt(i);
        if (c != 'L') return null;
        int end = descriptor.indexOf(';', i);
        String internal = end < 0
            ? descriptor.substring(i + 1)
            : descriptor.substring(i + 1, end);
        return internal.startsWith("net/minecraft/")
            ? internal.replace('/', '.')
            : null;
    }

    private static String referenceFromInternal(String internal) {
        if (internal == null || internal.isEmpty()) return null;
        int i = 0;
        while (i < internal.length() && internal.charAt(i) == '[') i++;
        if (i >= internal.length()) return null;
        char c = internal.charAt(i);
        if (c == 'L') {
            int end = internal.indexOf(';', i);
            String name = end < 0
                ? internal.substring(i + 1)
                : internal.substring(i + 1, end);
            return name.startsWith("net/minecraft/") ? name.replace('/', '.') : null;
        }

        if (internal.startsWith("net/minecraft/")) {
            return internal.replace('/', '.');
        }
        if (internal.startsWith("Lnet/minecraft/")) {
            return internal.substring(1).replace('/', '.');
        }
        return null;
    }
}
