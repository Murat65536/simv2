package murat.simv2.simulation.mirror.net.minecraft.entity.damage;

// Generated mirror stub for simulation closure.
public class DamageType {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageType> CODEC;
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageType>> ENTRY_CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageType>> ENTRY_PACKET_CODEC;
    public murat.simv2.simulation.mirror.net.minecraft.entity.damage.DeathMessageType deathMessageType;
    public murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageEffects effects;
    public float exhaustion;
    public java.lang.String msgId;
    public murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageScaling scaling;

    public DamageType(java.lang.String p0, float p1) {
    }

    public DamageType(java.lang.String p0, float p1, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageEffects p2) {
    }

    public DamageType(java.lang.String p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageScaling p1, float p2) {
    }

    public DamageType(java.lang.String p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageScaling p1, float p2, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageEffects p3) {
    }

    public DamageType(java.lang.String p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageScaling p1, float p2, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageEffects p3, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DeathMessageType p4) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.damage.DeathMessageType deathMessageType() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageEffects effects() {
        return null;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public float exhaustion() {
        return 0.0F;
    }

    public int hashCode() {
        return 0;
    }

    public java.lang.String msgId() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageScaling scaling() {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public DamageType() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
