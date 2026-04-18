package murat.simv2.simulation.mirror.net.minecraft.world;

// Generated mirror stub for simulation closure.
public class RaycastContext {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d end;
    public murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.FluidHandling fluid;
    public murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext shapeContext;
    public murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.ShapeType shapeType;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d start;

    public RaycastContext(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.ShapeType p2, murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.FluidHandling p3, murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext p4) {
    }

    public RaycastContext(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.ShapeType p2, murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.FluidHandling p3, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p4) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getBlockShape(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getEnd() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getFluidShape(murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getStart() {
        return null;
    }

    public RaycastContext() {
    }

    public static class FluidHandling {
        public static murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.FluidHandling ANY;
        public static murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.FluidHandling NONE;
        public static murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.FluidHandling SOURCE_ONLY;
        public static murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.FluidHandling WATER;
        public static murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.FluidHandling[] field_1349;
        public java.util.function.Predicate predicate;

        public FluidHandling(java.lang.String p0, int p1, java.util.function.Predicate p2) {
        }

        public boolean handled(murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p0) {
            return false;
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.FluidHandling valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.FluidHandling[] values() {
            return null;
        }

        public FluidHandling() {
        }

    }

    public static interface ShapeProvider {
        public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape get(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext p3);

    }

    public static class ShapeType implements murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.ShapeProvider {
        public static murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.ShapeType COLLIDER;
        public static murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.ShapeType FALLDAMAGE_RESETTING;
        public static murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.ShapeType OUTLINE;
        public static murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.ShapeType VISUAL;
        public static murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.ShapeType[] field_17561;
        public murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.ShapeProvider provider;

        public ShapeType(java.lang.String p0, int p1, murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.ShapeProvider p2) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape get(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext p3) {
            return null;
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.ShapeType valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.ShapeType[] values() {
            return null;
        }

        public ShapeType() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
