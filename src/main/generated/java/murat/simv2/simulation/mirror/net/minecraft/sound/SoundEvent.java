package murat.simv2.simulation.mirror.net.minecraft.sound;

// Generated mirror stub for simulation closure.
public class SoundEvent {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent> CODEC;
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent>> ENTRY_CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent>> ENTRY_PACKET_CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<io.netty.buffer.ByteBuf, murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent> PACKET_CODEC;
    public java.util.Optional<java.lang.Float> fixedRange;
    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier id;

    public SoundEvent(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, java.util.Optional p1) {
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public java.util.Optional fixedRange() {
        return null;
    }

    public float getDistanceToTravel(float p0) {
        return 0.0F;
    }

    public int hashCode() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier id() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent of(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent of(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, float p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent of(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, java.util.Optional p1) {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public SoundEvent() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
