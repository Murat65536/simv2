package murat.simv2.simulation.mirror.net.minecraft.entity.mob;

// Generated mirror stub for simulation closure.
public interface Angerable {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final java.lang.String ANGER_TIME_KEY = null;
    public static final java.lang.String ANGRY_AT_KEY = null;

    public boolean canTarget(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0);

    public void chooseRandomAngerTime();

    public void forgive(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1);

    public int getAngerTime();

    public java.util.UUID getAngryAt();

    public murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity getAttacker();

    public murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity getTarget();

    public boolean hasAngerTime();

    public boolean isUniversallyAngry(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0);

    public void readAngerFromNbt(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p1);

    public void setAngerTime(int p0);

    public void setAngryAt(java.util.UUID p0);

    public void setAttacker(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0);

    public void setTarget(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0);

    public boolean shouldAngerAt(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1);

    public void stopAnger();

    public void tickAngerLogic(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, boolean p1);

    public void universallyAnger();

    public void writeAngerToNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0);

    // END GENERATED MIRROR NESTED STUBS
}
