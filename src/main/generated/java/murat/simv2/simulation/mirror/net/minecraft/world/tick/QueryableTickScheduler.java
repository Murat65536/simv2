package murat.simv2.simulation.mirror.net.minecraft.world.tick;

// Generated mirror stub for simulation closure.
public interface QueryableTickScheduler<T> {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public int getTickCount();

    public boolean isQueued(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.lang.Object p1);

    public boolean isTicking(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.lang.Object p1);

    public void scheduleTick(murat.simv2.simulation.mirror.net.minecraft.world.tick.OrderedTick p0);

    // END GENERATED MIRROR NESTED STUBS
}
