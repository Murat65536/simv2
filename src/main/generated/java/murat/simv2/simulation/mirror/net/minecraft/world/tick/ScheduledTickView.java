package murat.simv2.simulation.mirror.net.minecraft.world.tick;

// Generated mirror stub for simulation closure.
public interface ScheduledTickView {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public murat.simv2.simulation.mirror.net.minecraft.world.tick.OrderedTick createOrderedTick(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.lang.Object p1, int p2);

    public murat.simv2.simulation.mirror.net.minecraft.world.tick.OrderedTick createOrderedTick(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.lang.Object p1, int p2, murat.simv2.simulation.mirror.net.minecraft.world.tick.TickPriority p3);

    public murat.simv2.simulation.mirror.net.minecraft.world.tick.QueryableTickScheduler getBlockTickScheduler();

    public murat.simv2.simulation.mirror.net.minecraft.world.tick.QueryableTickScheduler getFluidTickScheduler();

    public void scheduleBlockTick(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.Block p1, int p2);

    public void scheduleBlockTick(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.Block p1, int p2, murat.simv2.simulation.mirror.net.minecraft.world.tick.TickPriority p3);

    public void scheduleFluidTick(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid p1, int p2);

    public void scheduleFluidTick(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid p1, int p2, murat.simv2.simulation.mirror.net.minecraft.world.tick.TickPriority p3);

    // END GENERATED MIRROR NESTED STUBS
}
