package murat.simv2.simulation.mirror.net.minecraft.world;

// Generated mirror stub for simulation closure.
public interface ModifiableTestableWorld extends murat.simv2.simulation.mirror.net.minecraft.world.ModifiableWorld, murat.simv2.simulation.mirror.net.minecraft.world.TestableWorld {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public boolean breakBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, boolean p1);

    public boolean breakBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p2);

    public boolean breakBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p2, int p3);

    public java.util.Optional getBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntityType p1);

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getTopPosition(murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1);

    public boolean removeBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, boolean p1);

    public boolean setBlockState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, int p2);

    public boolean setBlockState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, int p2, int p3);

    public boolean spawnEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0);

    public boolean testBlockState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.util.function.Predicate p1);

    public boolean testFluidState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.util.function.Predicate p1);

    // END GENERATED MIRROR NESTED STUBS
}
