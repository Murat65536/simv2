package murat.simv2.simulation.mirror.net.minecraft.world;

// Generated mirror stub for simulation closure.
public interface CollisionView extends murat.simv2.simulation.mirror.net.minecraft.world.BlockView {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public boolean canCollide(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public boolean canPlace(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext p2);

    public static void collectCollisionsBetween(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p2, murat.simv2.simulation.mirror.net.minecraft.world.BlockView.CollisionVisitor p3) {
    }

    public int countVerticalSections();

    public static murat.simv2.simulation.mirror.net.minecraft.world.HeightLimitView create(int p0, int p1) {
        return null;
    }

    public boolean doesNotIntersectEntities(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0);

    public boolean doesNotIntersectEntities(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p1);

    public java.util.Optional findClosestCollision(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2, double p3, double p4, double p5);

    public java.util.Optional findSupportingBlockPos(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public java.lang.Iterable getBlockCollisions(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity getBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public java.util.Optional getBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntityType p1);

    public java.lang.Iterable getBlockOrFluidCollisions(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState getBlockState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public int getBottomSectionCoord();

    public int getBottomY();

    public murat.simv2.simulation.mirror.net.minecraft.world.BlockView getChunkAsView(int p0, int p1);

    public murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult getCollisionsIncludingWorldBorder(murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext p0);

    public java.lang.Iterable getCollisions(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public double getDismountHeight(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public double getDismountHeight(murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p0, java.util.function.Supplier p1);

    public java.util.List getEntityCollisions(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState getFluidState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public int getHeight();

    public int getLuminance(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public int getSectionIndex(int p0);

    public java.util.stream.Stream getStatesInBox(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0);

    public int getTopSectionCoord();

    public int getTopYInclusive();

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getWorldBorderCollisions(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder getWorldBorder();

    public boolean isBlockSpaceEmpty(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public boolean isInHeightLimit(int p0);

    public boolean isOutOfHeightLimit(int p0);

    public boolean isOutOfHeightLimit(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public boolean isSpaceEmpty(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0);

    public boolean isSpaceEmpty(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public boolean isSpaceEmpty(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1, boolean p2);

    public boolean isSpaceEmpty(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0);

    public murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult raycastBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p3, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p4);

    public static java.lang.Object raycast(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, java.lang.Object p2, java.util.function.BiFunction p3, java.util.function.Function p4) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult raycast(murat.simv2.simulation.mirror.net.minecraft.world.BlockStateRaycastContext p0);

    public murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult raycast(murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext p0);

    public int sectionCoordToIndex(int p0);

    public int sectionIndexToCoord(int p0);

    // END GENERATED MIRROR NESTED STUBS
}
