package murat.simv2.simulation.mirror.net.minecraft.block;

// Generated mirror stub for simulation closure.
public class BlockState extends murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.AbstractBlockState {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.block.BlockState> CODEC;
    public static java.lang.String NAME;
    public static java.lang.String PROPERTIES;
    public com.mojang.serialization.MapCodec<java.lang.Object> codec;
    public java.lang.Object owner;

    public BlockState(murat.simv2.simulation.mirror.net.minecraft.block.Block p0, it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap p1, com.mojang.serialization.MapCodec p2) {
    }

    public boolean allowsSpawning(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.entity.EntityType p2) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState asBlockState() {
        return null;
    }

    public boolean blocksMovement() {
        return false;
    }

    public float calcBlockBreakingDelta(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return 0.0F;
    }

    public boolean canBucketPlace(murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid p0) {
        return false;
    }

    public boolean canPathfindThrough(murat.simv2.simulation.mirror.net.minecraft.entity.ai.pathing.NavigationType p0) {
        return false;
    }

    public boolean canPlaceAt(murat.simv2.simulation.mirror.net.minecraft.world.WorldView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public boolean canReplace(murat.simv2.simulation.mirror.net.minecraft.item.ItemPlacementContext p0) {
        return false;
    }

    public boolean contains(murat.simv2.simulation.mirror.net.minecraft.state.property.Property p0) {
        return false;
    }

    public static com.mojang.serialization.Codec createCodec(com.mojang.serialization.Codec p0, java.util.function.Function p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.screen.NamedScreenHandlerFactory createScreenHandlerFactory(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return null;
    }

    public void createWithMap(java.util.Map p0) {
    }

    public java.lang.Object cycle(murat.simv2.simulation.mirror.net.minecraft.state.property.Property p0) {
        return null;
    }

    public boolean emitsRedstonePower() {
        return false;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public boolean exceedsCube() {
        return false;
    }

    public float getAmbientOcclusionLightLevel(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntityTicker getBlockEntityTicker(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntityType p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.Block getBlock() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getCameraCollisionShape(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getCollisionShape(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getCollisionShape(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext p2) {
        return null;
    }

    public int getComparatorOutput(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getCullingFace(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getCullingShape() {
        return null;
    }

    public java.util.List getDroppedStacks(murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext.Builder p0) {
        return null;
    }

    public java.util.Map getEntries() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState getFluidState() {
        return null;
    }

    public float getHardness(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getInsideCollisionShape(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.enums.NoteBlockInstrument getInstrument() {
        return null;
    }

    public int getLuminance() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.MapColor getMapColor(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getModelOffset(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public static java.lang.Object getNext(java.util.List p0, java.lang.Object p1) {
        return null;
    }

    public int getOpacity() {
        return 0;
    }

    public java.util.Optional getOrEmpty(murat.simv2.simulation.mirror.net.minecraft.state.property.Property p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getOutlineShape(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getOutlineShape(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getPickStack(murat.simv2.simulation.mirror.net.minecraft.world.WorldView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, boolean p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.piston.PistonBehavior getPistonBehavior() {
        return null;
    }

    public java.util.Collection getProperties() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getRaycastShape(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry getRegistryEntry() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockRenderType getRenderType() {
        return null;
    }

    public long getRenderingSeed(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return 0L;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getSidesShape(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.sound.BlockSoundGroup getSoundGroup() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState getStateForNeighborUpdate(murat.simv2.simulation.mirror.net.minecraft.world.WorldView p0, murat.simv2.simulation.mirror.net.minecraft.world.tick.ScheduledTickView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p4, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p5, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p6) {
        return null;
    }

    public int getStrongRedstonePower(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p2) {
        return 0;
    }

    public int getWeakRedstonePower(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p2) {
        return 0;
    }

    public java.lang.Comparable get(murat.simv2.simulation.mirror.net.minecraft.state.property.Property p0) {
        return null;
    }

    public java.lang.Comparable get(murat.simv2.simulation.mirror.net.minecraft.state.property.Property p0, java.lang.Comparable p1) {
        return null;
    }

    public boolean hasBlockBreakParticles() {
        return false;
    }

    public boolean hasBlockEntity() {
        return false;
    }

    public boolean hasComparatorOutput() {
        return false;
    }

    public boolean hasEmissiveLighting(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public boolean hasModelOffset() {
        return false;
    }

    public boolean hasRandomTicks() {
        return false;
    }

    public boolean hasSidedTransparency() {
        return false;
    }

    public boolean hasSolidTopSurface(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p2) {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public void initShapeCache() {
    }

    public boolean isAir() {
        return false;
    }

    public boolean isBurnable() {
        return false;
    }

    public boolean isFullCube(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public boolean isIn(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList p0) {
        return false;
    }

    public boolean isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0) {
        return false;
    }

    public boolean isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0, java.util.function.Predicate p1) {
        return false;
    }

    public boolean isLiquid() {
        return false;
    }

    public boolean isOf(murat.simv2.simulation.mirror.net.minecraft.block.Block p0) {
        return false;
    }

    public boolean isOf(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return false;
    }

    public boolean isOpaqueFullCube() {
        return false;
    }

    public boolean isOpaque() {
        return false;
    }

    public boolean isReplaceable() {
        return false;
    }

    public boolean isSideInvisible(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1) {
        return false;
    }

    public boolean isSideSolidFullSquare(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p2) {
        return false;
    }

    public boolean isSideSolid(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p2, murat.simv2.simulation.mirror.net.minecraft.block.SideShapeType p3) {
        return false;
    }

    public boolean isSolidBlock(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public boolean isSolidSurface(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3) {
        return false;
    }

    public boolean isSolid() {
        return false;
    }

    public boolean isToolRequired() {
        return false;
    }

    public boolean isTransparent() {
        return false;
    }

    public boolean matchesKey(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState mirror(murat.simv2.simulation.mirror.net.minecraft.util.BlockMirror p0) {
        return null;
    }

    public void neighborUpdate(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.Block p2, murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation p3, boolean p4) {
    }

    public void onBlockAdded(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2, boolean p3) {
    }

    public void onBlockBreakStart(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p2) {
    }

    public void onEntityCollision(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p2, murat.simv2.simulation.mirror.net.minecraft.entity.EntityCollisionHandler p3) {
    }

    public void onExploded(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.world.explosion.Explosion p2, java.util.function.BiConsumer p3) {
    }

    public void onProjectileHit(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult p2, murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity p3) {
    }

    public void onStacksDropped(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2, boolean p3) {
    }

    public void onStateReplaced(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, boolean p2) {
    }

    public boolean onSyncedBlockEvent(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, int p2, int p3) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult onUseWithItem(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p2, murat.simv2.simulation.mirror.net.minecraft.util.Hand p3, murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult p4) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult onUse(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult p2) {
        return null;
    }

    public void prepare(murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, int p2) {
    }

    public void prepare(murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, int p2, int p3) {
    }

    public void randomTick(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p2) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState rotate(murat.simv2.simulation.mirror.net.minecraft.util.BlockRotation p0) {
        return null;
    }

    public void scheduledTick(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p2) {
    }

    public boolean shouldBlockVision(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public boolean shouldPostProcess(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public boolean shouldSuffocate(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public java.util.stream.Stream streamTags() {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public void updateNeighbors(murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, int p2) {
    }

    public void updateNeighbors(murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, int p2, int p3) {
    }

    public java.lang.Object withIfExists(murat.simv2.simulation.mirror.net.minecraft.state.property.Property p0, java.lang.Comparable p1) {
        return null;
    }

    public java.lang.Object with(murat.simv2.simulation.mirror.net.minecraft.state.property.Property p0, java.lang.Comparable p1) {
        return null;
    }

    public BlockState() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
