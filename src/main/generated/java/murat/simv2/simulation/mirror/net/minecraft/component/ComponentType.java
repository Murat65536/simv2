package murat.simv2.simulation.mirror.net.minecraft.component;

// Generated mirror stub for simulation closure.
public interface ComponentType<T> {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.component.ComponentType<?>> CODEC = null;
    public static final murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.component.ComponentType<?>> PACKET_CODEC = null;
    public static final com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.component.ComponentType<?>> PERSISTENT_CODEC = null;
    public static final com.mojang.serialization.Codec<java.util.Map<murat.simv2.simulation.mirror.net.minecraft.component.ComponentType<?>, java.lang.Object>> TYPE_TO_VALUE_MAP_CODEC = null;

    public static murat.simv2.simulation.mirror.net.minecraft.component.ComponentType.Builder builder() {
        return null;
    }

    public com.mojang.serialization.Codec getCodecOrThrow();

    public com.mojang.serialization.Codec getCodec();

    public murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec getPacketCodec();

    public boolean shouldSkipSerialization();

    public static class Builder<T> extends java.lang.Object {
        public boolean cache;
        public com.mojang.serialization.Codec<java.lang.Object> codec;
        public murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<? super java.lang.Object, java.lang.Object> packetCodec;

        public Builder() {
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.ComponentType build() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.ComponentType.Builder cache() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.ComponentType.Builder codec(com.mojang.serialization.Codec p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.ComponentType.Builder packetCodec(murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec p0) {
            return null;
        }

        public static class SimpleDataComponentType<T> extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.component.ComponentType {
            public com.mojang.serialization.Codec<java.lang.Object> codec;
            public murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<? super java.lang.Object, java.lang.Object> packetCodec;

            public SimpleDataComponentType(com.mojang.serialization.Codec p0, murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec p1) {
            }

            public static murat.simv2.simulation.mirror.net.minecraft.component.ComponentType.Builder builder() {
                return null;
            }

            public com.mojang.serialization.Codec getCodecOrThrow() {
                return null;
            }

            public com.mojang.serialization.Codec getCodec() {
                return null;
            }

            public murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec getPacketCodec() {
                return null;
            }

            public boolean shouldSkipSerialization() {
                return false;
            }

            public java.lang.String toString() {
                return null;
            }

            public SimpleDataComponentType() {
            }

        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
