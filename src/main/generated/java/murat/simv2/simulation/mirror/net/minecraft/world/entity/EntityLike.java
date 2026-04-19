package murat.simv2.simulation.mirror.net.minecraft.world.entity;

// Generated mirror stub for simulation closure.
public interface EntityLike extends murat.simv2.simulation.mirror.net.minecraft.world.entity.UniquelyIdentifiable {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getBlockPos();

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box getBoundingBox();

    public int getId();

    public java.util.UUID getUuid();

    public boolean isPlayer();

    public boolean isRemoved();

    public void setChangeListener(murat.simv2.simulation.mirror.net.minecraft.world.entity.EntityChangeListener p0);

    public void setRemoved(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p0);

    public boolean shouldSave();

    public java.util.stream.Stream streamPassengersAndSelf();

    public java.util.stream.Stream streamSelfAndPassengers();

    // END GENERATED MIRROR NESTED STUBS
}
