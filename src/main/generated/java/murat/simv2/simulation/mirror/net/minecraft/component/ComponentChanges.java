package murat.simv2.simulation.mirror.net.minecraft.component;

// Generated mirror stub for simulation closure.
public class ComponentChanges extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges> CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges EMPTY;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges> LENGTH_PREPENDED_PACKET_CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges> PACKET_CODEC;
    public static java.lang.String REMOVE_PREFIX;
    public it.unimi.dsi.fastutil.objects.Reference2ObjectMap<murat.simv2.simulation.mirror.net.minecraft.component.ComponentType<?>, java.util.Optional<?>> changedComponents;

    public ComponentChanges(it.unimi.dsi.fastutil.objects.Reference2ObjectMap p0) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges.Builder builder() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec createPacketCodec(murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges.PacketCodecFunction p0) {
        return null;
    }

    public java.util.Set entrySet() {
        return null;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public java.util.Optional get(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0) {
        return null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges.AddedRemovedPair toAddedRemovedPair() {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public static java.lang.String toString(it.unimi.dsi.fastutil.objects.Reference2ObjectMap p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges withRemovedIf(java.util.function.Predicate p0) {
        return null;
    }

    public ComponentChanges() {
    }

    public static class AddedRemovedPair {
        public static murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges.AddedRemovedPair EMPTY;
        public murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap added;
        public java.util.Set<murat.simv2.simulation.mirror.net.minecraft.component.ComponentType<?>> removed;

        public AddedRemovedPair(murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap p0, java.util.Set p1) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap added() {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public java.util.Set removed() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public AddedRemovedPair() {
        }

    }

    public static class Builder extends java.lang.Object {
        public it.unimi.dsi.fastutil.objects.Reference2ObjectMap<murat.simv2.simulation.mirror.net.minecraft.component.ComponentType<?>, java.util.Optional<?>> changes;

        public Builder() {
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges.Builder add(murat.simv2.simulation.mirror.net.minecraft.component.Component p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges.Builder add(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, java.lang.Object p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges build() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges.Builder remove(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0) {
            return null;
        }

    }

    public static interface PacketCodecFunction {
        public murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec apply(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0);

    }

    public static class Type {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges.Type> CODEC;
        public boolean removed;
        public murat.simv2.simulation.mirror.net.minecraft.component.ComponentType<?> type;

        public Type(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, boolean p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public com.mojang.serialization.Codec getValueCodec() {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public boolean removed() {
            return false;
        }

        public java.lang.String toString() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.ComponentType type() {
            return null;
        }

        public Type() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
