package murat.simv2.simulation.mirror.net.minecraft.state.property;

// Generated mirror stub for simulation closure.
public class Property<T> extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public com.mojang.serialization.Codec<java.lang.Object> codec;
    public java.lang.Integer hashCodeCache;
    public java.lang.String name;
    public java.lang.Class<java.lang.Object> type;
    public com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.state.property.Property.Value<java.lang.Object>> valueCodec;

    public Property(java.lang.String p0, java.lang.Class p1) {
    }

    public int computeHashCode() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.state.property.Property.Value createValue(java.lang.Comparable p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.state.property.Property.Value createValue(murat.simv2.simulation.mirror.net.minecraft.state.State p0) {
        return null;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public com.mojang.serialization.Codec getCodec() {
        return null;
    }

    public java.lang.String getName() {
        return null;
    }

    public java.lang.Class getType() {
        return null;
    }

    public com.mojang.serialization.Codec getValueCodec() {
        return null;
    }

    public java.util.List getValues() {
        return null;
    }

    public int hashCode() {
        return 0;
    }

    public java.lang.String name(java.lang.Comparable p0) {
        return null;
    }

    public int ordinal(java.lang.Comparable p0) {
        return 0;
    }

    public com.mojang.serialization.DataResult parse(com.mojang.serialization.DynamicOps p0, murat.simv2.simulation.mirror.net.minecraft.state.State p1, java.lang.Object p2) {
        return null;
    }

    public java.util.Optional parse(java.lang.String p0) {
        return null;
    }

    public java.util.stream.Stream stream() {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public Property() {
    }

    public static class Value<T> {
        public murat.simv2.simulation.mirror.net.minecraft.state.property.Property<java.lang.Object> property;
        public java.lang.Object value;

        public Value(murat.simv2.simulation.mirror.net.minecraft.state.property.Property p0, java.lang.Comparable p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.state.property.Property property() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public java.lang.Comparable value() {
            return null;
        }

        public Value() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
