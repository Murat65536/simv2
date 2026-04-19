package murat.simv2.simulation.mirror.net.minecraft.world.block;

// Generated mirror stub for simulation closure.
public interface NeighborUpdater {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final murat.simv2.simulation.mirror.net.minecraft.util.math.Direction[] UPDATE_ORDER = null;

    public void replaceWithStateForNeighborUpdate(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3, int p4, int p5);

    public static void replaceWithStateForNeighborUpdate(murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p4, int p5, int p6) {
    }

    public static void tryNeighborUpdate(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.Block p3, murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation p4, boolean p5) {
    }

    public void updateNeighbors(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.Block p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p2, murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation p3);

    public void updateNeighbor(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.Block p2, murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation p3, boolean p4);

    public void updateNeighbor(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.Block p1, murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation p2);

    // END GENERATED MIRROR NESTED STUBS
}
