package murat.simv2.simulation.mirror.net.minecraft.block;

// Generated mirror stub for simulation closure.
public class GlowLichenBlock {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.MapCodec CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction[] DIRECTIONS;
    public static int FORCE_STATE;
    public static int FORCE_STATE_AND_SKIP_CALLBACKS_AND_DROPS;
    public static int MOVED;
    public static int NOTIFY_ALL;
    public static int NOTIFY_ALL_AND_REDRAW;
    public static int NOTIFY_LISTENERS;
    public static int NOTIFY_NEIGHBORS;
    public static int NO_REDRAW;
    public static int REDRAW_ON_MAIN_THREAD;
    public static int SKIP_BLOCK_ADDED_CALLBACK;
    public static int SKIP_BLOCK_ENTITY_REPLACED_CALLBACK;
    public static int SKIP_DROPS;
    public static int SKIP_REDRAW_AND_BLOCK_ENTITY_REPLACED_CALLBACK;
    public static int SKIP_REDSTONE_WIRE_STATE_REPLACEMENT;
    public static java.lang.Object STATE_IDS;
    public static java.lang.Object WATERLOGGED;
    public boolean collidable;
    public boolean dynamicBounds;
    public static float field_31023;
    public static float field_31024;
    public static int field_31025;
    public java.lang.Object grower;
    public float jumpVelocityMultiplier;
    public java.util.Optional lootTableKey;
    public boolean randomTicks;
    public java.lang.Object requiredFeatures;
    public float resistance;
    public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings settings;
    public float slipperiness;
    public murat.simv2.simulation.mirror.net.minecraft.sound.BlockSoundGroup soundGroup;
    public java.lang.Object stateManager;
    public java.lang.String translationKey;
    public float velocityMultiplier;

    public GlowLichenBlock(murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings p0) {
    }

    public void afterBreak(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3, java.lang.Object p4, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p5) {
    }

    public void appendProperties(java.lang.Object p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.Block asBlock() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.Item asItem() {
        return null;
    }

    public float calcBlockBreakingDelta(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3) {
        return 0.0F;
    }

    public boolean canBucketPlace(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid p1) {
        return false;
    }

    public boolean canFillWithFluid(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3, murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid p4) {
        return false;
    }

    public static boolean canGrowOn(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p2) {
        return false;
    }

    public static boolean canGrowOn(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3) {
        return false;
    }

    public boolean canGrowWithDirection(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3) {
        return false;
    }

    public boolean canGrow(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3) {
        return false;
    }

    public boolean canHaveDirection(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0) {
        return false;
    }

    public boolean canMobSpawnInside(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return false;
    }

    public boolean canPathfindThrough(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, java.lang.Object p1) {
        return false;
    }

    public boolean canPlaceAt(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return false;
    }

    public boolean canReplace(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, java.lang.Object p1) {
        return false;
    }

    public static boolean canSpread(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2) {
        return false;
    }

    public static boolean cannotConnect(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return false;
    }

    public static java.util.Set collectDirections(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return null;
    }

    public static com.mojang.serialization.MapCodec createCodec(java.util.function.Function p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape createColumnShape(double p0, double p1, double p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape createColumnShape(double p0, double p1, double p2, double p3) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape createCubeShape(double p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape createCuboidShape(double p0, double p1, double p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape createCuboidShape(double p0, double p1, double p2, double p3, double p4, double p5) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape createCuboidZShape(double p0, double p1, double p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape createCuboidZShape(double p0, double p1, double p2, double p3) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape createCuboidZShape(double p0, double p1, double p2, double p3, double p4) {
        return null;
    }

    public java.lang.Object createScreenHandlerFactory(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return null;
    }

    public static com.mojang.serialization.codecs.RecordCodecBuilder createSettingsCodec() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape[] createShapeArray(int p0, java.util.function.IntFunction p1) {
        return null;
    }

    public java.util.function.Function createShapeFunction(java.util.function.Function p0) {
        return null;
    }

    public java.util.function.Function createShapeFunction(java.util.function.Function p0, java.lang.Object[] p1) {
        return null;
    }

    public static byte directionsToFlag(java.util.Collection p0) {
        return (byte) 0;
    }

    public void dropExperienceWhenMined(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2, java.lang.Object p3) {
    }

    public void dropExperience(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, int p2) {
    }

    public static void dropStacks(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, java.lang.Object p3) {
    }

    public static void dropStacks(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
    }

    public static void dropStacks(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, java.lang.Object p3, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p4, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p5) {
    }

    public static void dropStack(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2) {
    }

    public static void dropStack(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p2, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p3) {
    }

    public boolean emitsRedstonePower(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return false;
    }

    public static java.util.Optional findPosToSpreadTo(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2) {
        return null;
    }

    public static java.util.Set flagToDirections(byte p0) {
        return null;
    }

    public float getAmbientOcclusionLightLevel(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return 0.0F;
    }

    public float getBlastResistance() {
        return 0.0F;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.block.Block getBlockFromItem(murat.simv2.simulation.mirror.net.minecraft.item.Item p0) {
        return null;
    }

    public java.util.Optional getBucketFillSound() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getCameraCollisionShape(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext p3) {
        return null;
    }

    public com.mojang.serialization.MapCodec getCodec() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getCollisionShape(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext p3) {
        return null;
    }

    public int getComparatorOutput(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getCullingShape(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.MapColor getDefaultMapColor() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState getDefaultState() {
        return null;
    }

    public java.util.List getDroppedStacks(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext.Builder p1) {
        return null;
    }

    public static java.util.List getDroppedStacks(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, java.lang.Object p3) {
        return null;
    }

    public static java.util.List getDroppedStacks(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, java.lang.Object p3, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p4, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p5) {
        return null;
    }

    public java.lang.Object getFertilizableType() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getFertilizeParticlePos(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState getFluidState(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return null;
    }

    public java.lang.Object getGrower() {
        return null;
    }

    public float getHardness() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getInsideCollisionShape(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p3) {
        return null;
    }

    public float getJumpVelocityMultiplier() {
        return 0.0F;
    }

    public java.util.Optional getLootTableKey() {
        return null;
    }

    public static java.util.function.ToIntFunction getLuminanceSupplier(int p0) {
        return null;
    }

    public float getMaxHorizontalModelOffset() {
        return 0.0F;
    }

    public java.lang.Object getName() {
        return null;
    }

    public int getOpacity(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getOutlineShape(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext p3) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getPickStack(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2, boolean p3) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState getPlacementState(java.lang.Object p0) {
        return null;
    }

    public static java.lang.Object getProperty(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0) {
        return null;
    }

    public static int getRawIdFromState(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getRaycastShape(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference getRegistryEntry() {
        return null;
    }

    public java.lang.Object getRenderType(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return null;
    }

    public long getRenderingSeed(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return 0L;
    }

    public java.lang.Object getRequiredFeatures() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings getSettings() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getSidesShape(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return null;
    }

    public float getSlipperiness() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.sound.BlockSoundGroup getSoundGroup(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState getStateForNeighborUpdate(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, java.lang.Object p1, java.lang.Object p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p4, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p5, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p6, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p7) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.block.BlockState getStateFromRawId(int p0) {
        return null;
    }

    public java.lang.Object getStateManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState getStateWithProperties(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return null;
    }

    public int getStrongRedstonePower(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3) {
        return 0;
    }

    public java.lang.String getTranslationKey() {
        return null;
    }

    public float getVelocityMultiplier() {
        return 0.0F;
    }

    public float getVerticalModelOffsetMultiplier() {
        return 0.0F;
    }

    public int getWeakRedstonePower(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3) {
        return 0;
    }

    public void grow(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3) {
    }

    public static boolean hasAnyDirection(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return false;
    }

    public boolean hasComparatorOutput(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return false;
    }

    public static boolean hasDirection(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1) {
        return false;
    }

    public boolean hasDynamicBounds() {
        return false;
    }

    public boolean hasRandomTicks(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return false;
    }

    public boolean hasSidedTransparency(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return false;
    }

    public static boolean hasTopRim(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public boolean isEnabled(java.lang.Object p0) {
        return false;
    }

    public static boolean isFaceFullSquare(murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1) {
        return false;
    }

    public boolean isFertilizable(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2) {
        return false;
    }

    public boolean isShapeFullCube(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return false;
    }

    public static boolean isShapeFullCube(murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p0) {
        return false;
    }

    public boolean isSideInvisible(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p2) {
        return false;
    }

    public boolean isTransparent(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState mirror(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, java.lang.Object p1) {
        return null;
    }

    public void neighborUpdate(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.Block p3, java.lang.Object p4, boolean p5) {
    }

    public void onBlockAdded(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3, boolean p4) {
    }

    public void onBlockBreakStart(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p3) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState onBreak(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p3) {
        return null;
    }

    public void onBroken(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2) {
    }

    public void onDestroyedByExplosion(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, java.lang.Object p2) {
    }

    public void onEntityCollision(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p3, murat.simv2.simulation.mirror.net.minecraft.entity.EntityCollisionHandler p4) {
    }

    public void onEntityLand(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
    }

    public void onExploded(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, java.lang.Object p3, java.util.function.BiConsumer p4) {
    }

    public void onLandedUpon(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p3, double p4) {
    }

    public void onPlaced(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p3, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p4) {
    }

    public void onProjectileHit(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult p2, murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity p3) {
    }

    public void onStacksDropped(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p3, boolean p4) {
    }

    public void onStateReplaced(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, boolean p3) {
    }

    public void onSteppedOn(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p3) {
    }

    public boolean onSyncedBlockEvent(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, int p3, int p4) {
        return false;
    }

    public java.lang.Object onUseWithItem(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, murat.simv2.simulation.mirror.net.minecraft.world.World p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p4, murat.simv2.simulation.mirror.net.minecraft.util.Hand p5, murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult p6) {
        return null;
    }

    public java.lang.Object onUse(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p3, murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult p4) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.block.BlockState postProcessState(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return null;
    }

    public void precipitationTick(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, java.lang.Object p3) {
    }

    public void prepare(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, int p3, int p4) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.block.BlockState pushEntitiesUpBeforeBlockChange(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, java.lang.Object p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3) {
        return null;
    }

    public void randomDisplayTick(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p3) {
    }

    public void randomTick(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p3) {
    }

    public static void replace(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, java.lang.Object p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3, int p4) {
    }

    public static void replace(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, java.lang.Object p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3, int p4, int p5) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState rotate(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, java.lang.Object p1) {
        return null;
    }

    public void scheduledTick(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p3) {
    }

    public void setDefaultState(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
    }

    public static boolean shouldDrawSide(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p2) {
        return false;
    }

    public boolean shouldDropItemsOnExplosion(java.lang.Object p0) {
        return false;
    }

    public static boolean sideCoversSmallSquare(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p2) {
        return false;
    }

    public void spawnBreakParticles(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3) {
    }

    public java.lang.String toString() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack tryDrainFluid(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3) {
        return null;
    }

    public boolean tryFillWithFluid(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2, murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p3) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState withDirection(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3) {
        return null;
    }

    public GlowLichenBlock() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
