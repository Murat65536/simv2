package murat.simv2.simulation.mirror.net.minecraft.block;

// Generated mirror stub for simulation closure.
public class AbstractBlock extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.ToggleableFeature {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction[] DIRECTIONS;
    public boolean collidable;
    public boolean dynamicBounds;
    public float jumpVelocityMultiplier;
    public java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<murat.simv2.simulation.mirror.net.minecraft.loot.LootTable>> lootTableKey;
    public boolean randomTicks;
    public murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet requiredFeatures;
    public float resistance;
    public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings settings;
    public float slipperiness;
    public murat.simv2.simulation.mirror.net.minecraft.sound.BlockSoundGroup soundGroup;
    public java.lang.String translationKey;
    public float velocityMultiplier;

    public AbstractBlock(murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings p0) {
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

    public boolean canPathfindThrough(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.entity.ai.pathing.NavigationType p1) {
        return false;
    }

    public boolean canPlaceAt(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.WorldView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return false;
    }

    public boolean canReplace(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemPlacementContext p1) {
        return false;
    }

    public static com.mojang.serialization.MapCodec createCodec(java.util.function.Function p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.screen.NamedScreenHandlerFactory createScreenHandlerFactory(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return null;
    }

    public static com.mojang.serialization.codecs.RecordCodecBuilder createSettingsCodec() {
        return null;
    }

    public boolean emitsRedstonePower(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return false;
    }

    public float getAmbientOcclusionLightLevel(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return 0.0F;
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

    public java.util.List getDroppedStacks(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext.Builder p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState getFluidState(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return null;
    }

    public float getHardness() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getInsideCollisionShape(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p3) {
        return null;
    }

    public java.util.Optional getLootTableKey() {
        return null;
    }

    public float getMaxHorizontalModelOffset() {
        return 0.0F;
    }

    public int getOpacity(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getOutlineShape(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext p3) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getPickStack(murat.simv2.simulation.mirror.net.minecraft.world.WorldView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2, boolean p3) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getRaycastShape(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockRenderType getRenderType(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return null;
    }

    public long getRenderingSeed(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return 0L;
    }

    public murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet getRequiredFeatures() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings getSettings() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape getSidesShape(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.sound.BlockSoundGroup getSoundGroup(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState getStateForNeighborUpdate(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.WorldView p1, murat.simv2.simulation.mirror.net.minecraft.world.tick.ScheduledTickView p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p4, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p5, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p6, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p7) {
        return null;
    }

    public int getStrongRedstonePower(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3) {
        return 0;
    }

    public java.lang.String getTranslationKey() {
        return null;
    }

    public float getVerticalModelOffsetMultiplier() {
        return 0.0F;
    }

    public int getWeakRedstonePower(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p3) {
        return 0;
    }

    public boolean hasComparatorOutput(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return false;
    }

    public boolean hasRandomTicks(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return false;
    }

    public boolean hasSidedTransparency(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return false;
    }

    public boolean isEnabled(murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet p0) {
        return false;
    }

    public boolean isShapeFullCube(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return false;
    }

    public boolean isSideInvisible(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p2) {
        return false;
    }

    public boolean isTransparent(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState mirror(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.BlockMirror p1) {
        return null;
    }

    public void neighborUpdate(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.Block p3, murat.simv2.simulation.mirror.net.minecraft.world.block.WireOrientation p4, boolean p5) {
    }

    public void onBlockAdded(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3, boolean p4) {
    }

    public void onBlockBreakStart(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p3) {
    }

    public void onEntityCollision(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p3, murat.simv2.simulation.mirror.net.minecraft.entity.EntityCollisionHandler p4) {
    }

    public void onExploded(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.world.explosion.Explosion p3, java.util.function.BiConsumer p4) {
    }

    public void onProjectileHit(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult p2, murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity p3) {
    }

    public void onStacksDropped(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p3, boolean p4) {
    }

    public void onStateReplaced(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, boolean p3) {
    }

    public boolean onSyncedBlockEvent(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, int p3, int p4) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult onUseWithItem(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, murat.simv2.simulation.mirror.net.minecraft.world.World p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p4, murat.simv2.simulation.mirror.net.minecraft.util.Hand p5, murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult p6) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult onUse(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p3, murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult p4) {
        return null;
    }

    public void prepare(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, int p3, int p4) {
    }

    public void randomTick(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p3) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState rotate(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.BlockRotation p1) {
        return null;
    }

    public void scheduledTick(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p3) {
    }

    public AbstractBlock() {
    }

    public static class AbstractBlockState extends murat.simv2.simulation.mirror.net.minecraft.state.State {
        public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction[] DIRECTIONS;
        public static murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape[] EMPTY_CULLING_FACES;
        public static murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape[] FULL_CULLING_FACES;
        public static java.lang.String NAME;
        public static java.lang.String PROPERTIES;
        public boolean blockBreakParticles;
        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.ContextPredicate blockVisionPredicate;
        public boolean burnable;
        public com.mojang.serialization.MapCodec<java.lang.Object> codec;
        public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape[] cullingFaces;
        public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape cullingShape;
        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.ContextPredicate emissiveLightingPredicate;
        public murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState fluidState;
        public float hardness;
        public boolean hasSidedTransparency;
        public murat.simv2.simulation.mirror.net.minecraft.block.enums.NoteBlockInstrument instrument;
        public boolean isAir;
        public boolean liquid;
        public int luminance;
        public murat.simv2.simulation.mirror.net.minecraft.block.MapColor mapColor;
        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Offsetter offsetter;
        public int opacity;
        public boolean opaque;
        public boolean opaqueFullCube;
        public java.lang.Object owner;
        public murat.simv2.simulation.mirror.net.minecraft.block.piston.PistonBehavior pistonBehavior;
        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.ContextPredicate postProcessPredicate;
        public boolean replaceable;
        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.AbstractBlockState.ShapeCache shapeCache;
        public boolean solid;
        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.ContextPredicate solidBlockPredicate;
        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.ContextPredicate suffocationPredicate;
        public boolean ticksRandomly;
        public boolean toolRequired;
        public boolean transparent;

        public AbstractBlockState(murat.simv2.simulation.mirror.net.minecraft.block.Block p0, it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap p1, com.mojang.serialization.MapCodec p2) {
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

        public boolean shouldBeSolid() {
            return false;
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

        public AbstractBlockState() {
        }

        public static class ShapeCache extends java.lang.Object {
            public static murat.simv2.simulation.mirror.net.minecraft.util.math.Direction[] DIRECTIONS;
            public static int SHAPE_TYPE_LENGTH;
            public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape collisionShape;
            public boolean exceedsCube;
            public boolean isFullCube;
            public boolean[] solidSides;

            public ShapeCache(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
            }

            public static int indexSolidSide(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0, murat.simv2.simulation.mirror.net.minecraft.block.SideShapeType p1) {
                return 0;
            }

            public boolean isSideSolid(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0, murat.simv2.simulation.mirror.net.minecraft.block.SideShapeType p1) {
                return false;
            }

            public ShapeCache() {
            }

        }

    }

    public static interface ContextPredicate {
        public boolean test(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2);

    }

    public static class OffsetType {
        public static murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.OffsetType NONE;
        public static murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.OffsetType XYZ;
        public static murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.OffsetType XZ;
        public static murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.OffsetType[] field_10658;

        public OffsetType(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.OffsetType valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.OffsetType[] values() {
            return null;
        }

        public OffsetType() {
        }

    }

    public static interface Offsetter {
        public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d evaluate(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1);

    }

    public static class Settings extends java.lang.Object {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings> CODEC;
        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.TypedContextPredicate<murat.simv2.simulation.mirror.net.minecraft.entity.EntityType<?>> allowsSpawningPredicate;
        public boolean blockBreakParticles;
        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.ContextPredicate blockVisionPredicate;
        public boolean burnable;
        public boolean collidable;
        public boolean dynamicBounds;
        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.ContextPredicate emissiveLightingPredicate;
        public boolean forceNotSolid;
        public boolean forceSolid;
        public float hardness;
        public murat.simv2.simulation.mirror.net.minecraft.block.enums.NoteBlockInstrument instrument;
        public boolean isAir;
        public float jumpVelocityMultiplier;
        public boolean liquid;
        public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKeyedValue<murat.simv2.simulation.mirror.net.minecraft.block.Block, java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<murat.simv2.simulation.mirror.net.minecraft.loot.LootTable>>> lootTable;
        public java.util.function.ToIntFunction<murat.simv2.simulation.mirror.net.minecraft.block.BlockState> luminance;
        public java.util.function.Function<murat.simv2.simulation.mirror.net.minecraft.block.BlockState, murat.simv2.simulation.mirror.net.minecraft.block.MapColor> mapColorProvider;
        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Offsetter offsetter;
        public boolean opaque;
        public murat.simv2.simulation.mirror.net.minecraft.block.piston.PistonBehavior pistonBehavior;
        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.ContextPredicate postProcessPredicate;
        public boolean randomTicks;
        public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<murat.simv2.simulation.mirror.net.minecraft.block.Block> registryKey;
        public boolean replaceable;
        public murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet requiredFeatures;
        public float resistance;
        public float slipperiness;
        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.ContextPredicate solidBlockPredicate;
        public murat.simv2.simulation.mirror.net.minecraft.sound.BlockSoundGroup soundGroup;
        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.ContextPredicate suffocationPredicate;
        public boolean toolRequired;
        public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKeyedValue<murat.simv2.simulation.mirror.net.minecraft.block.Block, java.lang.String> translationKey;
        public float velocityMultiplier;

        public Settings() {
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings air() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings allowsSpawning(murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.TypedContextPredicate p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings blockVision(murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.ContextPredicate p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings breakInstantly() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings burnable() {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings copyShallow(murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings copy(murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings create() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings dropsNothing() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings dynamicBounds() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings emissiveLighting(murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.ContextPredicate p0) {
            return null;
        }

        public java.util.Optional getLootTableKey() {
            return null;
        }

        public java.lang.String getTranslationKey() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings hardness(float p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings instrument(murat.simv2.simulation.mirror.net.minecraft.block.enums.NoteBlockInstrument p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings jumpVelocityMultiplier(float p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings liquid() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings lootTable(java.util.Optional p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings luminance(java.util.function.ToIntFunction p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings mapColor(java.util.function.Function p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings mapColor(murat.simv2.simulation.mirror.net.minecraft.block.MapColor p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings mapColor(murat.simv2.simulation.mirror.net.minecraft.util.DyeColor p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings noBlockBreakParticles() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings noCollision() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings nonOpaque() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings notSolid() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings offset(murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.OffsetType p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings overrideTranslationKey(java.lang.String p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings pistonBehavior(murat.simv2.simulation.mirror.net.minecraft.block.piston.PistonBehavior p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings postProcess(murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.ContextPredicate p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings registryKey(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings replaceable() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings requiresTool() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings requires(murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureFlag[] p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings resistance(float p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings slipperiness(float p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings solidBlock(murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.ContextPredicate p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings solid() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings sounds(murat.simv2.simulation.mirror.net.minecraft.sound.BlockSoundGroup p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings strength(float p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings strength(float p0, float p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings suffocates(murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.ContextPredicate p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings ticksRandomly() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.block.AbstractBlock.Settings velocityMultiplier(float p0) {
            return null;
        }

    }

    public static interface TypedContextPredicate<A> {
        public boolean test(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, java.lang.Object p3);

    }

    // END GENERATED MIRROR NESTED STUBS
}
