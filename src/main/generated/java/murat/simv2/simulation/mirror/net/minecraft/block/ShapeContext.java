package murat.simv2.simulation.mirror.net.minecraft.block;

// Generated mirror stub for simulation closure.
public interface ShapeContext {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext absent() {
        return null;
    }

    public boolean canWalkOnFluid(murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p0, murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p1);

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getCollisionShape(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.CollisionView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2);

    public boolean isAbove(murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, boolean p2);

    public boolean isDescending();

    public boolean isHolding(murat.simv2.simulation.mirror.net.minecraft.item.Item p0);

    public boolean isPlacement();

    public static murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext ofPlacement(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext of(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext of(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, boolean p1) {
        return null;
    }

    // END GENERATED MIRROR NESTED STUBS
}
