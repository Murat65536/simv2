package murat.simv2.simulation.mirror.net.minecraft.entity.effect;

// Generated mirror stub for simulation closure.
public class StatusEffect extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.ToggleableFeature {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static int AMBIENT_PARTICLE_ALPHA;
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffect>> ENTRY_CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffect>> ENTRY_PACKET_CODEC;
    public java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent> applySound;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute>, murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffect.EffectAttributeModifierCreator> attributeModifiers;
    public murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectCategory category;
    public int color;
    public int fadeInTicks;
    public int fadeOutThresholdTicks;
    public int fadeOutTicks;
    public java.util.function.Function<murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance, murat.simv2.simulation.mirror.net.minecraft.particle.ParticleEffect> particleFactory;
    public murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet requiredFeatures;
    public java.lang.String translationKey;

    public StatusEffect(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectCategory p0, int p1) {
    }

    public StatusEffect(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectCategory p0, int p1, murat.simv2.simulation.mirror.net.minecraft.particle.ParticleEffect p2) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffect addAttributeModifier(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p1, double p2, murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier.Operation p3) {
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

    public murat.simv2.simulation.mirror.net.minecraft.particle.ParticleEffect createParticle(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0) {
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

    public murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectCategory getCategory() {
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

    public murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet getRequiredFeatures() {
        return null;
    }

    public java.lang.String getTranslationKey() {
        return null;
    }

    public boolean isBeneficial() {
        return false;
    }

    public boolean isEnabled(murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet p0) {
        return false;
    }

    public boolean isInstant() {
        return false;
    }

    public java.lang.String loadTranslationKey() {
        return null;
    }

    public void onApplied(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, int p1) {
    }

    public void onApplied(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.AttributeContainer p0, int p1) {
    }

    public void onEntityDamage(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, int p2, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p3, float p4) {
    }

    public void onEntityRemoval(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, int p2, murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p3) {
    }

    public void onRemoved(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.AttributeContainer p0) {
    }

    public void playApplySound(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, int p1) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffect requires(murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureFlag[] p0) {
        return null;
    }

    public StatusEffect() {
    }

    public static class EffectAttributeModifierCreator {
        public double baseValue;
        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier id;
        public murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier.Operation operation;

        public EffectAttributeModifierCreator(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, double p1, murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier.Operation p2) {
        }

        public double baseValue() {
            return 0.0D;
        }

        public murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier createAttributeModifier(int p0) {
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

        public murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier.Operation operation() {
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
