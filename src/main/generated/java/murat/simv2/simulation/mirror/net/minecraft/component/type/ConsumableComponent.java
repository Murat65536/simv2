package murat.simv2.simulation.mirror.net.minecraft.component.type;

// Generated mirror stub for simulation closure.
public class ConsumableComponent {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.component.type.ConsumableComponent> CODEC;
    public static float DEFAULT_CONSUME_SECONDS;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.component.type.ConsumableComponent> PACKET_CODEC;
    public static int PARTICLES_AND_SOUND_TICK_INTERVAL;
    public static float PARTICLES_AND_SOUND_TICK_THRESHOLD;
    public float consumeSeconds;
    public boolean hasConsumeParticles;
    public java.util.List<java.lang.Object> onConsumeEffects;
    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent> sound;
    public murat.simv2.simulation.mirror.net.minecraft.item.consume.UseAction useAction;

    public ConsumableComponent(float p0, murat.simv2.simulation.mirror.net.minecraft.item.consume.UseAction p1, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p2, boolean p3, java.util.List p4) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.component.type.ConsumableComponent.Builder builder() {
        return null;
    }

    public boolean canConsume(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return false;
    }

    public float consumeSeconds() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult consume(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1, murat.simv2.simulation.mirror.net.minecraft.util.Hand p2) {
        return null;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack finishConsumption(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2) {
        return null;
    }

    public int getConsumeTicks() {
        return 0;
    }

    public boolean hasConsumeParticles() {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public java.util.List onConsumeEffects() {
        return null;
    }

    public boolean shouldSpawnParticlesAndPlaySounds(int p0) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry sound() {
        return null;
    }

    public void spawnParticlesAndPlaySound(murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2, int p3) {
    }

    public java.lang.String toString() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.consume.UseAction useAction() {
        return null;
    }

    public ConsumableComponent() {
    }

    public static class Builder extends java.lang.Object {
        public java.util.List<java.lang.Object> consumeEffects;
        public boolean consumeParticles;
        public float consumeSeconds;
        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent> sound;
        public murat.simv2.simulation.mirror.net.minecraft.item.consume.UseAction useAction;

        public Builder() {
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.ConsumableComponent build() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.ConsumableComponent.Builder consumeEffect(java.lang.Object p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.ConsumableComponent.Builder consumeParticles(boolean p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.ConsumableComponent.Builder consumeSeconds(float p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.ConsumableComponent.Builder finishSound(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.ConsumableComponent.Builder sound(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.ConsumableComponent.Builder useAction(murat.simv2.simulation.mirror.net.minecraft.item.consume.UseAction p0) {
            return null;
        }

    }

    public static interface ConsumableSoundProvider {
        public murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent getConsumeSound(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0);

    }

    // END GENERATED MIRROR NESTED STUBS
}
