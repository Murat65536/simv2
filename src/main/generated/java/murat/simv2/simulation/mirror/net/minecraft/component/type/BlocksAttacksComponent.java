package murat.simv2.simulation.mirror.net.minecraft.component.type;

// Generated mirror stub for simulation closure.
public class BlocksAttacksComponent {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec CODEC;
    public static java.lang.Object PACKET_CODEC;
    public float blockDelaySeconds;
    public java.util.Optional blockSound;
    public java.util.Optional bypassedBy;
    public java.util.List damageReductions;
    public float disableCooldownScale;
    public java.util.Optional disableSound;
    public murat.simv2.simulation.mirror.net.minecraft.component.type.BlocksAttacksComponent.ItemDamage itemDamage;

    public BlocksAttacksComponent(float p0, float p1, java.util.List p2, murat.simv2.simulation.mirror.net.minecraft.component.type.BlocksAttacksComponent.ItemDamage p3, java.util.Optional p4, java.util.Optional p5, java.util.Optional p6) {
    }

    public void applyShieldCooldown(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, float p2, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p3) {
    }

    public float blockDelaySeconds() {
        return 0.0F;
    }

    public java.util.Optional blockSound() {
        return null;
    }

    public java.util.Optional bypassedBy() {
        return null;
    }

    public int convertCooldownToTicks(float p0) {
        return 0;
    }

    public java.util.List damageReductions() {
        return null;
    }

    public float disableCooldownScale() {
        return 0.0F;
    }

    public java.util.Optional disableSound() {
        return null;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public int getBlockDelayTicks() {
        return 0;
    }

    public float getDamageReductionAmount(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1, double p2) {
        return 0.0F;
    }

    public int hashCode() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.type.BlocksAttacksComponent.ItemDamage itemDamage() {
        return null;
    }

    public void onShieldHit(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p2, murat.simv2.simulation.mirror.net.minecraft.util.Hand p3, float p4) {
    }

    public void playBlockSound(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1) {
    }

    public java.lang.String toString() {
        return null;
    }

    public BlocksAttacksComponent() {
    }

    public static class DamageReduction {
        public static com.mojang.serialization.Codec CODEC;
        public static java.lang.Object PACKET_CODEC;
        public float base;
        public float factor;
        public float horizontalBlockingAngle;
        public java.util.Optional type;

        public DamageReduction(float p0, java.util.Optional p1, float p2, float p3) {
        }

        public float base() {
            return 0.0F;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public float factor() {
            return 0.0F;
        }

        public float getReductionAmount(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1, double p2) {
            return 0.0F;
        }

        public int hashCode() {
            return 0;
        }

        public float horizontalBlockingAngle() {
            return 0.0F;
        }

        public java.lang.String toString() {
            return null;
        }

        public java.util.Optional type() {
            return null;
        }

        public DamageReduction() {
        }

    }

    public static class ItemDamage {
        public static com.mojang.serialization.Codec CODEC;
        public static murat.simv2.simulation.mirror.net.minecraft.component.type.BlocksAttacksComponent.ItemDamage DEFAULT;
        public static java.lang.Object PACKET_CODEC;
        public float base;
        public float factor;
        public float threshold;

        public ItemDamage(float p0, float p1, float p2) {
        }

        public float base() {
            return 0.0F;
        }

        public int calculate(float p0) {
            return 0;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public float factor() {
            return 0.0F;
        }

        public int hashCode() {
            return 0;
        }

        public float threshold() {
            return 0.0F;
        }

        public java.lang.String toString() {
            return null;
        }

        public ItemDamage() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
