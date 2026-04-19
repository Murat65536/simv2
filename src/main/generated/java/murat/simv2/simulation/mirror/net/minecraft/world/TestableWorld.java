package murat.simv2.simulation.mirror.net.minecraft.world;

// Generated mirror stub for simulation closure.
public interface TestableWorld {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public java.util.Optional getBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntityType p1);

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getTopPosition(murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1);

    public boolean testBlockState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.util.function.Predicate p1);

    public boolean testFluidState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.util.function.Predicate p1);

    // END GENERATED MIRROR NESTED STUBS
}
