package murat.simv2.simulation.mirror.net.minecraft.entity.effect;

// Generated mirror stub for simulation closure.
public class StatusEffectInstance {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec CODEC;
    public static int INFINITE;
    public static org.slf4j.Logger LOGGER;
    public static int MAX_AMPLIFIER;
    public static int MIN_AMPLIFIER;
    public static java.lang.Object PACKET_CODEC;
    public boolean ambient;
    public int amplifier;
    public int duration;
    public murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance.Fading fading;
    public murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance hiddenEffect;
    public boolean showIcon;
    public boolean showParticles;
    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry type;

    public StatusEffectInstance(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0) {
    }

    public StatusEffectInstance(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
    }

    public StatusEffectInstance(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, int p1) {
    }

    public StatusEffectInstance(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, int p1, int p2) {
    }

    public StatusEffectInstance(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, int p1, int p2, boolean p3, boolean p4) {
    }

    public StatusEffectInstance(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, int p1, int p2, boolean p3, boolean p4, boolean p5) {
    }

    public StatusEffectInstance(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, int p1, int p2, boolean p3, boolean p4, boolean p5, murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p6) {
    }

    public StatusEffectInstance(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance.Parameters p1) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance.Parameters asParameters() {
        return null;
    }

    public int compareTo(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0) {
        return 0;
    }

    public void copyFadingFrom(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0) {
    }

    public void copyFrom(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0) {
    }

    public java.lang.Object createParticle() {
        return null;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public boolean equals(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return false;
    }

    public int getAmplifier() {
        return 0;
    }

    public java.lang.String getDurationString() {
        return null;
    }

    public int getDuration() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry getEffectType() {
        return null;
    }

    public float getFadeFactor(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, float p1) {
        return 0.0F;
    }

    public java.lang.String getTranslationKey() {
        return null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isActive() {
        return false;
    }

    public boolean isAmbient() {
        return false;
    }

    public boolean isDurationBelow(int p0) {
        return false;
    }

    public boolean isInfinite() {
        return false;
    }

    public boolean lastsShorterThan(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0) {
        return false;
    }

    public int mapDuration(it.unimi.dsi.fastutil.ints.Int2IntFunction p0) {
        return 0;
    }

    public void onApplied(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
    }

    public void onEntityDamage(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p2, float p3) {
    }

    public void onEntityRemoval(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p2) {
    }

    public void playApplySound(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
    }

    public boolean shouldShowIcon() {
        return false;
    }

    public boolean shouldShowParticles() {
        return false;
    }

    public void skipFading() {
    }

    public void tickClient() {
    }

    public boolean tickHiddenEffect() {
        return false;
    }

    public java.lang.String toString() {
        return null;
    }

    public void updateDuration() {
    }

    public boolean update(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, java.lang.Runnable p2) {
        return false;
    }

    public boolean upgrade(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance withScaledDuration(float p0) {
        return null;
    }

    public StatusEffectInstance() {
    }

    public static class Fading {
        public float factor;
        public float lastFactor;

        public Fading() {
        }

        public float calculate(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, float p1) {
            return 0.0F;
        }

        public void copyFrom(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance.Fading p0) {
        }

        public static boolean shouldFadeIn(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0) {
            return false;
        }

        public void skipFading(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0) {
        }

        public void update(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0) {
        }

    }

    public static class Parameters {
        public static com.mojang.serialization.MapCodec CODEC;
        public static java.lang.Object PACKET_CODEC;
        public boolean ambient;
        public int amplifier;
        public int duration;
        public java.util.Optional hiddenEffect;
        public boolean showIcon;
        public boolean showParticles;

        public Parameters(int p0, int p1, boolean p2, boolean p3, boolean p4, java.util.Optional p5) {
        }

        public boolean ambient() {
            return false;
        }

        public int amplifier() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance.Parameters create(int p0, int p1, boolean p2, boolean p3, java.util.Optional p4, java.util.Optional p5) {
            return null;
        }

        public int duration() {
            return 0;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public java.util.Optional hiddenEffect() {
            return null;
        }

        public boolean showIcon() {
            return false;
        }

        public boolean showParticles() {
            return false;
        }

        public java.lang.String toString() {
            return null;
        }

        public Parameters() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
