package murat.simv2.simulation.mirror.net.minecraft.entity;

// Generated mirror stub for simulation closure.
public interface Leashable {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final java.lang.String LEASH_NBT_KEY = null;
    public static final double MAX_LEASH_LENGTH = 0.0D;
    public static final double SHORT_LEASH_LENGTH = 0.0D;

    public void applyLeashElasticity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, float p1);

    public static void applyLeashElasticity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1, float p2) {
    }

    public void attachLeash(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, boolean p1);

    public static void attachLeash(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1, boolean p2) {
    }

    public boolean beforeLeashTick(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, float p1);

    public void breakLongLeash();

    public boolean canBeLeashed();

    public boolean canLeashAttachTo();

    public void detachLeashWithoutDrop();

    public void detachLeash();

    public static void detachLeash(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, boolean p1, boolean p2) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Leashable.LeashData getLeashData();

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getLeashHolder();

    public static murat.simv2.simulation.mirror.net.minecraft.entity.Entity getLeashHolder(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return null;
    }

    public boolean isLeashed();

    public boolean mightBeLeashed();

    public void onLeashRemoved();

    public void onShortLeashTick(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0);

    public void readLeashDataFromNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0);

    public static void resolveLeashData(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.Leashable.LeashData p1) {
    }

    public void setLeashData(murat.simv2.simulation.mirror.net.minecraft.entity.Leashable.LeashData p0);

    public void setUnresolvedLeashHolderId(int p0);

    public static void tickLeash(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
    }

    public void writeLeashDataToNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, murat.simv2.simulation.mirror.net.minecraft.entity.Leashable.LeashData p1);

    public static class LeashData extends java.lang.Object {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.entity.Leashable.LeashData> CODEC;
        public murat.simv2.simulation.mirror.net.minecraft.entity.Entity leashHolder;
        public com.mojang.datafixers.util.Either<java.util.UUID, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos> unresolvedLeashData;
        public int unresolvedLeashHolderId;

        public LeashData(com.mojang.datafixers.util.Either p0) {
        }

        public LeashData(int p0) {
        }

        public LeashData(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        }

        public void setLeashHolder(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        }

        public LeashData() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
