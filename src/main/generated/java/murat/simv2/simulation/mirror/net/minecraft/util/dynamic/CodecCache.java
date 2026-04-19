package murat.simv2.simulation.mirror.net.minecraft.util.dynamic;

// Generated mirror stub for simulation closure.
public class CodecCache extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public com.google.common.cache.LoadingCache<murat.simv2.simulation.mirror.net.minecraft.util.dynamic.CodecCache.Key<?, ?>, com.mojang.serialization.DataResult<?>> cache;

    public CodecCache(int p0) {
    }

    public com.mojang.serialization.Codec wrap(com.mojang.serialization.Codec p0) {
        return null;
    }

    public CodecCache() {
    }

    public static class Key<A, T> {
        public com.mojang.serialization.Codec<java.lang.Object> codec;
        public com.mojang.serialization.DynamicOps<java.lang.Object> ops;
        public java.lang.Object value;

        public Key(com.mojang.serialization.Codec p0, java.lang.Object p1, com.mojang.serialization.DynamicOps p2) {
        }

        public com.mojang.serialization.Codec codec() {
            return null;
        }

        public com.mojang.serialization.DataResult encode() {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public com.mojang.serialization.DynamicOps ops() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public java.lang.Object value() {
            return null;
        }

        public Key() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
