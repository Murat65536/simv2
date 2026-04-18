package murat.simv2.simulation.mirror.net.minecraft.world;

// Generated mirror stub for simulation closure.
public interface BlockView {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final int field_54147 = 0;

    public static int collectCollisionsBetween(it.unimi.dsi.fastutil.longs.LongSet p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p3, murat.simv2.simulation.mirror.net.minecraft.world.BlockView.CollisionVisitor p4) {
        return 0;
    }

    public static void collectCollisionsBetween(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p2, murat.simv2.simulation.mirror.net.minecraft.world.BlockView.CollisionVisitor p3) {
    }

    public default int countVerticalSections() {
        return 0;
    }

    public static java.lang.Object create(int p0, int p1) {
        return null;
    }

    public default java.lang.Object getBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public default java.util.Optional getBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.lang.Object p1) {
        return null;
    }

    public default murat.simv2.simulation.mirror.net.minecraft.block.BlockState getBlockState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public default int getBottomSectionCoord() {
        return 0;
    }

    public default int getBottomY() {
        return 0;
    }

    public default double getDismountHeight(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return 0.0D;
    }

    public default double getDismountHeight(murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p0, java.util.function.Supplier p1) {
        return 0.0D;
    }

    public default murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState getFluidState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public default int getHeight() {
        return 0;
    }

    public default int getLuminance(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return 0;
    }

    public default int getSectionIndex(int p0) {
        return 0;
    }

    public default java.util.stream.Stream getStatesInBox(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0) {
        return null;
    }

    public default int getTopSectionCoord() {
        return 0;
    }

    public default int getTopYInclusive() {
        return 0;
    }

    public default boolean isInHeightLimit(int p0) {
        return false;
    }

    public default boolean isOutOfHeightLimit(int p0) {
        return false;
    }

    public default boolean isOutOfHeightLimit(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public default murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult raycastBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p3, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p4) {
        return null;
    }

    public default murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult raycast(java.lang.Object p0) {
        return null;
    }

    public static java.lang.Object raycast(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, java.lang.Object p2, java.util.function.BiFunction p3, java.util.function.Function p4) {
        return null;
    }

    public default murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult raycast(murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext p0) {
        return null;
    }

    public default int sectionCoordToIndex(int p0) {
        return 0;
    }

    public default int sectionIndexToCoord(int p0) {
        return 0;
    }

    public static interface CollisionVisitor {
        public default void visit(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1) {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
