package murat.simv2.simulation.mirror.net.minecraft.component;

// Generated mirror stub for simulation closure.
public interface ComponentMap extends murat.simv2.simulation.mirror.net.minecraft.component.ComponentsAccess {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap> CODEC = null;
    public static final murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap EMPTY = null;

    public static murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap.Builder builder() {
        return null;
    }

    public boolean contains(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0);

    public static com.mojang.serialization.Codec createCodecFromValueMap(com.mojang.serialization.Codec p0) {
        return null;
    }

    public static com.mojang.serialization.Codec createCodec(com.mojang.serialization.Codec p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap filtered(java.util.function.Predicate p0);

    public java.lang.Object getOrDefault(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, java.lang.Object p1);

    public murat.simv2.simulation.mirror.net.minecraft.component.Component getTyped(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0);

    public java.util.Set getTypes();

    public java.lang.Object get(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0);

    public boolean isEmpty();

    public java.util.Iterator iterator();

    public static murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap of(murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap p0, murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap p1) {
        return null;
    }

    public int size();

    public java.util.stream.Stream stream();

    public static class Builder extends java.lang.Object {
        public it.unimi.dsi.fastutil.objects.Reference2ObjectMap<murat.simv2.simulation.mirror.net.minecraft.component.ComponentType<?>, java.lang.Object> components;

        public Builder() {
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap.Builder addAll(murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap.Builder add(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, java.lang.Object p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap build() {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap build(java.util.Map p0) {
            return null;
        }

        public void put(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, java.lang.Object p1) {
        }

        public static class SimpleComponentMap implements murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap {
            public it.unimi.dsi.fastutil.objects.Reference2ObjectMap<murat.simv2.simulation.mirror.net.minecraft.component.ComponentType<?>, java.lang.Object> map;

            public SimpleComponentMap(it.unimi.dsi.fastutil.objects.Reference2ObjectMap p0) {
            }

            public static murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap.Builder builder() {
                return null;
            }

            public boolean contains(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0) {
                return false;
            }

            public static com.mojang.serialization.Codec createCodecFromValueMap(com.mojang.serialization.Codec p0) {
                return null;
            }

            public static com.mojang.serialization.Codec createCodec(com.mojang.serialization.Codec p0) {
                return null;
            }

            public boolean equals(java.lang.Object p0) {
                return false;
            }

            public murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap filtered(java.util.function.Predicate p0) {
                return null;
            }

            public java.lang.Object getOrDefault(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, java.lang.Object p1) {
                return null;
            }

            public murat.simv2.simulation.mirror.net.minecraft.component.Component getTyped(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0) {
                return null;
            }

            public java.util.Set getTypes() {
                return null;
            }

            public java.lang.Object get(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0) {
                return null;
            }

            public int hashCode() {
                return 0;
            }

            public boolean isEmpty() {
                return false;
            }

            public java.util.Iterator iterator() {
                return null;
            }

            public it.unimi.dsi.fastutil.objects.Reference2ObjectMap map() {
                return null;
            }

            public static murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap of(murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap p0, murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap p1) {
                return null;
            }

            public int size() {
                return 0;
            }

            public java.util.stream.Stream stream() {
                return null;
            }

            public java.lang.String toString() {
                return null;
            }

            public SimpleComponentMap() {
            }

        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
