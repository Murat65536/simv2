package murat.simv2.simulation.mirror.net.minecraft.world;

// Generated mirror stub for simulation closure.
public interface BlockRenderView extends murat.simv2.simulation.mirror.net.minecraft.world.BlockView {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static void collectCollisionsBetween(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p2, murat.simv2.simulation.mirror.net.minecraft.world.BlockView.CollisionVisitor p3) {
    }

    public int countVerticalSections();

    public static murat.simv2.simulation.mirror.net.minecraft.world.HeightLimitView create(int p0, int p1) {
        return null;
    }

    public int getBaseLightLevel(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1);

    public murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity getBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public java.util.Optional getBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntityType p1);

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState getBlockState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public int getBottomSectionCoord();

    public int getBottomY();

    public float getBrightness(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0, boolean p1);

    public int getColor(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.world.biome.ColorResolver p1);

    public double getDismountHeight(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public double getDismountHeight(murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p0, java.util.function.Supplier p1);

    public murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState getFluidState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public int getHeight();

    public int getLightLevel(murat.simv2.simulation.mirror.net.minecraft.world.LightType p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1);

    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.light.LightingProvider getLightingProvider();

    public int getLuminance(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public int getSectionIndex(int p0);

    public java.util.stream.Stream getStatesInBox(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0);

    public int getTopSectionCoord();

    public int getTopYInclusive();

    public boolean isInHeightLimit(int p0);

    public boolean isOutOfHeightLimit(int p0);

    public boolean isOutOfHeightLimit(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public boolean isSkyVisible(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

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
