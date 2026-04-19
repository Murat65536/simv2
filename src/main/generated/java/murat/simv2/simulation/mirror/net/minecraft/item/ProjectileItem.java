package murat.simv2.simulation.mirror.net.minecraft.item;

// Generated mirror stub for simulation closure.
public interface ProjectileItem {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity createEntity(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Position p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3);

    public murat.simv2.simulation.mirror.net.minecraft.item.ProjectileItem.Settings getProjectileSettings();

    public void initializeProjectile(murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity p0, double p1, double p2, double p3, float p4, float p5);

    public static interface PositionFunction {
        public murat.simv2.simulation.mirror.net.minecraft.util.math.Position getDispensePosition(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1);

    }

    public static class Settings {
        public static murat.simv2.simulation.mirror.net.minecraft.item.ProjectileItem.Settings DEFAULT;
        public java.util.OptionalInt overrideDispenseEvent;
        public murat.simv2.simulation.mirror.net.minecraft.item.ProjectileItem.PositionFunction positionFunction;
        public float power;
        public float uncertainty;

        public Settings(murat.simv2.simulation.mirror.net.minecraft.item.ProjectileItem.PositionFunction p0, float p1, float p2, java.util.OptionalInt p3) {
        }

        public static murat.simv2.simulation.mirror.net.minecraft.item.ProjectileItem.Settings.Builder builder() {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public java.util.OptionalInt overrideDispenseEvent() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.ProjectileItem.PositionFunction positionFunction() {
            return null;
        }

        public float power() {
            return 0.0F;
        }

        public java.lang.String toString() {
            return null;
        }

        public float uncertainty() {
            return 0.0F;
        }

        public Settings() {
        }

        public static class Builder extends java.lang.Object {
            public java.util.OptionalInt overrideDispenserEvent;
            public murat.simv2.simulation.mirror.net.minecraft.item.ProjectileItem.PositionFunction positionFunction;
            public float power;
            public float uncertainty;

            public Builder() {
            }

            public murat.simv2.simulation.mirror.net.minecraft.item.ProjectileItem.Settings build() {
                return null;
            }

            public murat.simv2.simulation.mirror.net.minecraft.item.ProjectileItem.Settings.Builder overrideDispenseEvent(int p0) {
                return null;
            }

            public murat.simv2.simulation.mirror.net.minecraft.item.ProjectileItem.Settings.Builder positionFunction(murat.simv2.simulation.mirror.net.minecraft.item.ProjectileItem.PositionFunction p0) {
                return null;
            }

            public murat.simv2.simulation.mirror.net.minecraft.item.ProjectileItem.Settings.Builder power(float p0) {
                return null;
            }

            public murat.simv2.simulation.mirror.net.minecraft.item.ProjectileItem.Settings.Builder uncertainty(float p0) {
                return null;
            }

        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
