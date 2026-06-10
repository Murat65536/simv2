package murat.simv2.analysis;

import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IField;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.ipa.callgraph.CGNode;
import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.TypeReference;
import com.ibm.wala.util.config.PatternsFilter;
import com.ibm.wala.util.config.StringFilter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Computes the Phase A scope pruning: which jar classes the precise Phase B
 * (0-1-CFA) run is allowed to see.
 *
 * <p>The kept set is the classes reachable in the cheap Phase A call graph,
 * expanded transitively over superclasses, interfaces, declared field types,
 * method signature types, and nested-class owners. The expansion keeps the
 * Phase B CHA well-formed (a class whose superclass is excluded would itself
 * be dropped from the CHA) and preserves the mirror-closure walk, which
 * follows exactly these edges.
 *
 * <p>Only classes that live in the Minecraft jar are ever pruned. The JDK
 * (primordial loader) is left untouched: unreachable JDK classes contribute
 * nothing to the points-to fixpoint, and pruning them risks breaking WALA's
 * synthetic models (natives.xml summaries, fake-root plumbing).
 *
 * <p>Soundness: reachability under the coarser Phase A abstraction is a
 * superset of reachability under 0-1-CFA, so restricting Phase B to this
 * closure cannot remove anything the precise run would have used — the final
 * slice is identical to an unpruned run.
 */
final class ScopePruner {

    private ScopePruner() {
    }

    /**
     * Slash-form binary names (e.g. {@code net/minecraft/entity/Entity}) of
     * every class file in the jar — the universe of prunable classes.
     */
    static Set<String> jarClassUniverse(Path jar) throws IOException {
        Set<String> universe = new HashSet<>();
        try (ZipFile zip = new ZipFile(jar.toFile())) {
            Enumeration<? extends ZipEntry> entries = zip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String name = entry.getName();
                if (name.endsWith(".class")) {
                    universe.add(name.substring(0, name.length() - ".class".length()));
                }
            }
        }
        return universe;
    }

    /**
     * The jar classes Phase B must keep: declaring classes of every Phase A
     * call-graph node (plus {@link AnalysisConfig#REQUIRED_PRIMARY_CLASSES}),
     * expanded to the hierarchy/field/signature reference closure.
     */
    static Set<String> keptClasses(CallGraph cg, IClassHierarchy cha, Set<String> universe) {
        Set<String> kept = new HashSet<>();
        Deque<IClass> work = new ArrayDeque<>();

        for (CGNode node : cg) {
            enqueue(node.getMethod().getDeclaringClass(), universe, kept, work);
        }
        for (String dotClass : AnalysisConfig.REQUIRED_PRIMARY_CLASSES) {
            enqueueName(dotClass.replace('.', '/'), cha, universe, kept, work);
        }

        while (!work.isEmpty()) {
            IClass klass = work.poll();

            // Immediate superclass only — the BFS pops it next and walks its
            // own superclass, so the chain converges without re-walking.
            enqueue(klass.getSuperclass(), universe, kept, work);
            for (IClass iface : klass.getAllImplementedInterfaces()) {
                enqueue(iface, universe, kept, work);
            }

            for (IField field : klass.getDeclaredInstanceFields()) {
                enqueueType(field.getFieldTypeReference(), cha, universe, kept, work);
            }
            for (IField field : klass.getDeclaredStaticFields()) {
                enqueueType(field.getFieldTypeReference(), cha, universe, kept, work);
            }

            for (IMethod method : klass.getDeclaredMethods()) {
                enqueueType(method.getReturnType(), cha, universe, kept, work);
                for (int i = 0; i < method.getNumberOfParameters(); i++) {
                    enqueueType(method.getParameterType(i), cha, universe, kept, work);
                }
            }

            // Nested-class owners, so the mirror can declare the full chain.
            String name = internalName(klass);
            int dollar = name.lastIndexOf('$');
            while (dollar > 0) {
                String owner = name.substring(0, dollar);
                enqueueName(owner, cha, universe, kept, work);
                dollar = owner.lastIndexOf('$');
                name = owner;
            }
        }

        return kept;
    }

    /**
     * Exclusion filter for the Phase B scope: the static
     * {@link AnalysisConfig#WALA_EXCLUSIONS} patterns, plus every jar class
     * that the Phase A closure did not keep.
     */
    static StringFilter prunedExclusions(List<String> basePatterns, Set<String> universe, Set<String> kept) {
        return new PrunedScopeFilter(new PatternsFilter(basePatterns.stream()), universe, kept);
    }

    private static void enqueue(IClass klass, Set<String> universe, Set<String> kept, Deque<IClass> work) {
        if (klass == null) return;
        String name = internalName(klass);
        if (!universe.contains(name)) return;
        if (kept.add(name)) {
            work.add(klass);
        }
    }

    private static void enqueueName(
        String slashName, IClassHierarchy cha, Set<String> universe, Set<String> kept, Deque<IClass> work) {
        if (slashName == null || !universe.contains(slashName)) return;
        if (!kept.add(slashName)) return;
        IClass klass = cha.lookupClass(TypeReference.findOrCreate(
            ClassLoaderReference.Application, "L" + slashName));
        if (klass != null) {
            work.add(klass);
        }
        // If the class is not in the CHA (already excluded by the base
        // patterns) we keep the name but cannot walk it — Phase B's base
        // exclusions drop it again anyway.
    }

    private static void enqueueType(
        TypeReference type, IClassHierarchy cha, Set<String> universe, Set<String> kept, Deque<IClass> work) {
        if (type == null) return;
        while (type.isArrayType()) {
            type = type.getArrayElementType();
        }
        if (type.isPrimitiveType()) return;
        String internal = type.getName().toString();
        if (!internal.startsWith("L")) return;
        enqueueName(internal.substring(1), cha, universe, kept, work);
    }

    /** Slash-form binary name without the leading {@code L}. */
    private static String internalName(IClass klass) {
        String name = klass.getName().toString();
        return name.startsWith("L") ? name.substring(1) : name;
    }

    /** Returns {@code true} (= exclude) for base-pattern matches and unkept jar classes. */
    private static final class PrunedScopeFilter implements StringFilter {
        private final PatternsFilter base;
        private final HashSet<String> universe;
        private final HashSet<String> kept;

        PrunedScopeFilter(PatternsFilter base, Set<String> universe, Set<String> kept) {
            this.base = base;
            this.universe = new HashSet<>(universe);
            this.kept = new HashSet<>(kept);
        }

        @Override
        public boolean test(String name) {
            if (base.test(name)) return true;
            return universe.contains(name) && !kept.contains(name);
        }

        @Override
        public Object toJson() {
            List<Object> json = new ArrayList<>();
            json.add(base.toJson());
            json.add("plus " + (universe.size() - kept.size()) + " jar classes outside the Phase A closure");
            return json;
        }
    }
}
