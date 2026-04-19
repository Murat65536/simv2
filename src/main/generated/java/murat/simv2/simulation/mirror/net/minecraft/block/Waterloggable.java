package murat.simv2.simulation.mirror.net.minecraft.block;

// Generated mirror stub for simulation closure.
public interface Waterloggable extends murat.simv2.simulation.mirror.net.minecraft.block.FluidDrainable, murat.simv2.simulation.mirror.net.minecraft.block.FluidFillable {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public boolean canFillWithFluid(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3, murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid p4);

    public java.util.Optional getBucketFillSound();

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack tryDrainFluid(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3);

    public boolean tryFillWithFluid(murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2, murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p3);

    // END GENERATED MIRROR NESTED STUBS
}
