package murat.simv2.simulation.mirror.net.minecraft.block;

// Generated mirror stub for simulation closure.
public interface ShapeContext {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext absent() {
        return null;
    }

    public default boolean canWalkOnFluid(murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p0, murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p1) {
        return false;
    }

    public default murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getCollisionShape(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return null;
    }

    public default boolean isAbove(murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, boolean p2) {
        return false;
    }

    public default boolean isDescending() {
        return false;
    }

    public default boolean isHolding(murat.simv2.simulation.mirror.net.minecraft.item.Item p0) {
        return false;
    }

    public default boolean isPlacement() {
        return false;
    }

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
