package murat.simv2.simulation.mirror.net.minecraft.entity.effect;

// Generated mirror stub for simulation closure.
public class StatusEffect {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static int AMBIENT_PARTICLE_ALPHA;
    public static com.mojang.serialization.Codec ENTRY_CODEC;
    public static java.lang.Object ENTRY_PACKET_CODEC;
    public java.util.Optional applySound;
    public java.util.Map attributeModifiers;
    public java.lang.Object category;
    public int color;
    public int fadeInTicks;
    public int fadeOutThresholdTicks;
    public int fadeOutTicks;
    public java.util.function.Function particleFactory;
    public java.lang.Object requiredFeatures;
    public java.lang.String translationKey;

    public StatusEffect(java.lang.Object p0, int p1) {
    }

    public StatusEffect(java.lang.Object p0, int p1, java.lang.Object p2) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffect addAttributeModifier(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p1, double p2, java.lang.Object p3) {
        return null;
    }

    public void applyInstantEffect(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p2, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p3, int p4, double p5) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffect applySound(murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p0) {
        return null;
    }

    public boolean applyUpdateEffect(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, int p2) {
        return false;
    }

    public boolean canApplyUpdateEffect(int p0, int p1) {
        return false;
    }

    public java.lang.Object createParticle(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffect fadeTicks(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffect fadeTicks(int p0, int p1, int p2) {
        return null;
    }

    public void forEachAttributeModifier(int p0, java.util.function.BiConsumer p1) {
    }

    public java.lang.Object getCategory() {
        return null;
    }

    public int getColor() {
        return 0;
    }

    public int getFadeInTicks() {
        return 0;
    }

    public int getFadeOutThresholdTicks() {
        return 0;
    }

    public int getFadeOutTicks() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getName() {
        return null;
    }

    public java.lang.Object getRequiredFeatures() {
        return null;
    }

    public java.lang.String getTranslationKey() {
        return null;
    }

    public boolean isBeneficial() {
        return false;
    }

    public boolean isEnabled(java.lang.Object p0) {
        return false;
    }

    public boolean isInstant() {
        return false;
    }

    public java.lang.String loadTranslationKey() {
        return null;
    }

    public void onApplied(java.lang.Object p0, int p1) {
    }

    public void onApplied(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, int p1) {
    }

    public void onEntityDamage(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, int p2, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p3, float p4) {
    }

    public void onEntityRemoval(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, int p2, murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p3) {
    }

    public void onRemoved(java.lang.Object p0) {
    }

    public void playApplySound(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, int p1) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffect requires(java.lang.Object[] p0) {
        return null;
    }

    public StatusEffect() {
    }

    public static class EffectAttributeModifierCreator {
        public double baseValue;
        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier id;
        public java.lang.Object operation;

        public EffectAttributeModifierCreator(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, double p1, java.lang.Object p2) {
        }

        public double baseValue() {
            return 0.0D;
        }

        public java.lang.Object createAttributeModifier(int p0) {
            return null;
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

        public java.lang.Object operation() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public EffectAttributeModifierCreator() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
