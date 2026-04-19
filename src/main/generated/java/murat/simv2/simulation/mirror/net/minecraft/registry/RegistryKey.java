package murat.simv2.simulation.mirror.net.minecraft.registry;

// Generated mirror stub for simulation closure.
public class RegistryKey<T> extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.util.concurrent.ConcurrentMap<murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey.RegistryIdPair, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<?>> INSTANCES;
    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier registry;
    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier value;

    public RegistryKey(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p1) {
    }

    public static com.mojang.serialization.Codec createCodec(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec createPacketCodec(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey getRegistryRef() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier getRegistry() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier getValue() {
        return null;
    }

    public boolean isOf(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
        return false;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey ofRegistry(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey of(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey of(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p1) {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public java.util.Optional tryCast(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
        return null;
    }

    public RegistryKey() {
    }

    public static class RegistryIdPair {
        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier id;
        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier registry;

        public RegistryIdPair(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier id() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier registry() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public RegistryIdPair() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
