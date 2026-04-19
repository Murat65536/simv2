package murat.simv2.simulation.mirror.net.minecraft.block;

// Generated mirror stub for simulation closure.
public class EntityShapeContext extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext ABSENT;
    public boolean descending;
    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity entity;
    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack heldItem;
    public double minY;
    public boolean placement;
    public java.util.function.Predicate<murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState> walkOnFluidPredicate;

    public EntityShapeContext(boolean p0, boolean p1, double p2, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p3, java.util.function.Predicate p4, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p5) {
    }

    public EntityShapeContext(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, boolean p1, boolean p2) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext absent() {
        return null;
    }

    public boolean canWalkOnFluid(murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p0, murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p1) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getCollisionShape(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.CollisionView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getEntity() {
        return null;
    }

    public boolean isAbove(murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, boolean p2) {
        return false;
    }

    public boolean isDescending() {
        return false;
    }

    public boolean isHolding(murat.simv2.simulation.mirror.net.minecraft.item.Item p0) {
        return false;
    }

    public boolean isPlacement() {
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

    public EntityShapeContext() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
