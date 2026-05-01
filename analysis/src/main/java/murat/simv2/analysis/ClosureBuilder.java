package murat.simv2.analysis;

import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IField;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.TypeReference;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Computes the mirror closure: every {@code net.minecraft.*} type that the
 * mirror module must declare in order for the sliced bodies to compile and
 * the runtime side to instantiate {@code ClientPlayerEntity}.
 *
 * <p>Seeds:
 * <ul>
 *   <li>{@link AnalysisConfig#REQUIRED_PRIMARY_CLASSES} — always present.</li>
 *   <li>Every class that owns a method with sliced lines.</li>
 *   <li>Every class that owns a sliced field.</li>
 * </ul>
 *
 * <p>Then we expand transitively over: superclasses, interfaces, declared
 * field types, and method parameter / return types of every class in the
 * closure. This is enough to make a stub class fragment compile.
 */
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

            // Hierarchy.
            for (IClass sup : superClassChain(klass)) {
                addIfNew(closure, work, classNameOf(sup));
            }
            for (IClass iface : klass.getAllImplementedInterfaces()) {
                addIfNew(closure, work, classNameOf(iface));
            }

            // Field types.
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

            // Method signatures.
            for (IMethod method : klass.getDeclaredMethods()) {
                String descriptor = method.getDescriptor().toString();
                for (String referenced : descriptorReferences(descriptor)) {
                    addIfNew(closure, work, referenced);
                }
            }

            // Owning class for nested types — keep the chain mirrorable.
            int dollar = dotClass.lastIndexOf('$');
            while (dollar > 0) {
                String owner = dotClass.substring(0, dollar);
                addIfNew(closure, work, owner);
                dollar = owner.lastIndexOf('$');
                dotClass = owner;
            }
        }

        Map<String, Set<String>> sliced = new LinkedHashMap<>();
        for (var e : new TreeMap<>(slice.lineByMethod()).entrySet()) {
            sliced.put(e.getKey(), Set.copyOf(new LinkedHashSet<>(e.getValue().keySet())));
        }
        return new MirrorClosure(Set.copyOf(closure), Map.copyOf(sliced));
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

    private static Iterable<IClass> superClassChain(IClass klass) {
        Set<IClass> chain = new LinkedHashSet<>();
        IClass current = klass.getSuperclass();
        while (current != null && chain.add(current)) {
            current = current.getSuperclass();
        }
        return chain;
    }

    /**
     * Pulls every {@code Lnet/minecraft/...;} reference out of a JVM method
     * descriptor like {@code (Lnet/minecraft/util/math/Vec3d;)V}.
     */
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

    /**
     * Returns the dot-form class name referenced by a single JVM type
     * descriptor or internal name. Handles {@code [Lnet/minecraft/...;},
     * {@code Lnet/minecraft/...;}, and bare internal names.
     */
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
        // Bare internal name (rare, but WALA emits these for non-array refs).
        if (internal.startsWith("net/minecraft/")) {
            return internal.replace('/', '.');
        }
        if (internal.startsWith("Lnet/minecraft/")) {
            return internal.substring(1).replace('/', '.');
        }
        return null;
    }
}
