package murat.simv2.simulation.mirror.net.minecraft.util;

// Generated mirror stub for simulation closure.
public interface StringIdentifiable {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final int CACHED_MAP_THRESHOLD = 0;

    public java.lang.String asString();

    public static com.mojang.serialization.Codec createBasicCodec(java.util.function.Supplier p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable.EnumCodec createCodec(java.util.function.Supplier p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable.EnumCodec createCodec(java.util.function.Supplier p0, java.util.function.Function p1) {
        return null;
    }

    public static java.util.function.Function createMapper(murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable[] p0, java.util.function.Function p1) {
        return null;
    }

    public static com.mojang.serialization.Keyable toKeyable(murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable[] p0) {
        return null;
    }

    public static class BasicCodec<S> extends java.lang.Object {
        public com.mojang.serialization.Codec<java.lang.Object> codec;

        public BasicCodec(murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable[] p0, java.util.function.Function p1, java.util.function.ToIntFunction p2) {
        }

        public com.mojang.serialization.DataResult decode(com.mojang.serialization.DynamicOps p0, java.lang.Object p1) {
            return null;
        }

        public com.mojang.serialization.DataResult encode(murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable p0, com.mojang.serialization.DynamicOps p1, java.lang.Object p2) {
            return null;
        }

        public BasicCodec() {
        }

    }

    public static class EnumCodec<E> extends murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable.BasicCodec {
        public java.util.function.Function<java.lang.String, java.lang.Object> idToIdentifiable;

        public EnumCodec(java.lang.Enum[] p0, java.util.function.Function p1) {
        }

        public java.lang.Enum byId(java.lang.String p0) {
            return null;
        }

        public java.lang.Enum byId(java.lang.String p0, java.lang.Enum p1) {
            return null;
        }

        public java.lang.Enum byId(java.lang.String p0, java.util.function.Supplier p1) {
            return null;
        }

        public com.mojang.serialization.DataResult decode(com.mojang.serialization.DynamicOps p0, java.lang.Object p1) {
            return null;
        }

        public com.mojang.serialization.DataResult encode(murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable p0, com.mojang.serialization.DynamicOps p1, java.lang.Object p2) {
            return null;
        }

        public EnumCodec() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
