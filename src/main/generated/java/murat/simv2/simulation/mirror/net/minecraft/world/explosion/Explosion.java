package murat.simv2.simulation.mirror.net.minecraft.world.explosion;

// Generated mirror stub for simulation closure.
public interface Explosion {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public boolean canTriggerBlocks();

    public static murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource createDamageSource(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity getCausingEntity();

    public static murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity getCausingEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.explosion.Explosion.DestructionType getDestructionType();

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getEntity();

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getPosition();

    public float getPower();

    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld getWorld();

    public boolean preservesDecorativeEntities();

    public static class DestructionType {
        public static murat.simv2.simulation.mirror.net.minecraft.world.explosion.Explosion.DestructionType DESTROY;
        public static murat.simv2.simulation.mirror.net.minecraft.world.explosion.Explosion.DestructionType DESTROY_WITH_DECAY;
        public static murat.simv2.simulation.mirror.net.minecraft.world.explosion.Explosion.DestructionType KEEP;
        public static murat.simv2.simulation.mirror.net.minecraft.world.explosion.Explosion.DestructionType TRIGGER_BLOCK;
        public boolean destroysBlocks;
        public static murat.simv2.simulation.mirror.net.minecraft.world.explosion.Explosion.DestructionType[] field_18688;

        public DestructionType(java.lang.String p0, int p1, boolean p2) {
        }

        public boolean destroysBlocks() {
            return false;
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.explosion.Explosion.DestructionType valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.explosion.Explosion.DestructionType[] values() {
            return null;
        }

        public DestructionType() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
