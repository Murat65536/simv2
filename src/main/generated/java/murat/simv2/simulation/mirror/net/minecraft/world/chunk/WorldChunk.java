package murat.simv2.simulation.mirror.net.minecraft.world.chunk;

// Generated mirror stub for simulation closure.
public class WorldChunk extends murat.simv2.simulation.mirror.net.minecraft.world.chunk.Chunk {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.world.chunk.BlockEntityTickInvoker EMPTY_BLOCK_ENTITY_TICKER;
    public static org.slf4j.Logger LOGGER;
    public static int MISSING_SECTION;
    public java.lang.Object blendingData;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos, murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity> blockEntities;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound> blockEntityNbts;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos, murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk.WrappedBlockEntityTickInvoker> blockEntityTickers;
    public java.lang.Object blockTickScheduler;
    public java.lang.Object chunkNoiseSampler;
    public java.lang.Object chunkSkyLight;
    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk.EntityLoader entityLoader;
    public java.lang.Object fluidTickScheduler;
    public it.unimi.dsi.fastutil.ints.Int2ObjectMap<java.lang.Object> gameEventDispatchers;
    public murat.simv2.simulation.mirror.net.minecraft.world.HeightLimitView heightLimitView;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type, murat.simv2.simulation.mirror.net.minecraft.world.Heightmap> heightmaps;
    public java.util.function.Supplier<java.lang.Object> levelTypeProvider;
    public boolean loadedToWorld;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos pos;
    public it.unimi.dsi.fastutil.shorts.ShortList[] postProcessingLists;
    public java.lang.Object[] sectionArray;
    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk.UnsavedListener unsavedListener;
    public java.lang.Object upgradeData;
    public murat.simv2.simulation.mirror.net.minecraft.world.World world;

    public WorldChunk(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk.EntityLoader p2) {
    }

    public WorldChunk(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p1) {
    }

    public WorldChunk(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p1, java.lang.Object p2, java.lang.Object p3, java.lang.Object p4, long p5, java.lang.Object[] p6, murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk.EntityLoader p7, java.lang.Object p8) {
    }

    public void addBlockEntity(murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity p0) {
    }

    public void addChunkTickSchedulers(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public void addEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void addPendingBlockEntityNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    public void addStructureReference(java.lang.Object p0, long p1) {
    }

    public boolean areSectionsEmptyBetween(int p0, int p1) {
        return false;
    }

    public boolean canTickBlockEntities() {
        return false;
    }

    public boolean canTickBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public void clear() {
    }

    public static void collectCollisionsBetween(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p2, murat.simv2.simulation.mirror.net.minecraft.world.BlockView.CollisionVisitor p3) {
    }

    public int countVerticalSections() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity createBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.world.HeightLimitView create(int p0, int p1) {
        return null;
    }

    public void disableTickSchedulers(long p0) {
    }

    public void forEachBlockMatchingPredicate(java.util.function.Predicate p0, java.util.function.BiConsumer p1) {
    }

    public void forEachLightSource(java.util.function.BiConsumer p0) {
    }

    public java.lang.Object getBelowZeroRetrogen() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry getBiomeForNoiseGen(int p0, int p1, int p2) {
        return null;
    }

    public java.lang.Object getBlendingData() {
        return null;
    }

    public java.util.Map getBlockEntities() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound getBlockEntityNbt(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public java.util.Set getBlockEntityPositions() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity getBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public java.util.Optional getBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntityType p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity getBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk.CreationType p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState getBlockState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public java.lang.Object getBlockTickScheduler() {
        return null;
    }

    public int getBottomSectionCoord() {
        return 0;
    }

    public int getBottomY() {
        return 0;
    }

    public java.lang.Object getChunkSkyLight() {
        return null;
    }

    public double getDismountHeight(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return 0.0D;
    }

    public double getDismountHeight(murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p0, java.util.function.Supplier p1) {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState getFluidState(int p0, int p1, int p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState getFluidState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public java.lang.Object getFluidTickScheduler() {
        return null;
    }

    public java.lang.Object getGameEventDispatcher(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.HeightLimitView getHeightLimitView() {
        return null;
    }

    public java.util.Collection getHeightmaps() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.Heightmap getHeightmap(murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type p0) {
        return null;
    }

    public int getHeight() {
        return 0;
    }

    public int getHighestNonEmptySectionYOffset() {
        return 0;
    }

    public int getHighestNonEmptySection() {
        return 0;
    }

    public long getInhabitedTime() {
        return 0L;
    }

    public java.lang.Object getLevelType() {
        return null;
    }

    public static it.unimi.dsi.fastutil.shorts.ShortList getList(it.unimi.dsi.fastutil.shorts.ShortList[] p0, int p1) {
        return null;
    }

    public int getLuminance(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.ChunkStatus getMaxStatus() {
        return null;
    }

    public java.lang.Object getOrCreateChunkNoiseSampler(java.util.function.Function p0) {
        return null;
    }

    public java.lang.Object getOrCreateGenerationSettings(java.util.function.Supplier p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound getPackedBlockEntityNbt(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p1) {
        return null;
    }

    public it.unimi.dsi.fastutil.shorts.ShortList[] getPostProcessingLists() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos getPos() {
        return null;
    }

    public java.lang.Object[] getSectionArray() {
        return null;
    }

    public int getSectionIndex(int p0) {
        return 0;
    }

    public java.lang.Object getSection(int p0) {
        return null;
    }

    public java.util.stream.Stream getStatesInBox(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.ChunkStatus getStatus() {
        return null;
    }

    public java.util.Map getStructureReferences() {
        return null;
    }

    public it.unimi.dsi.fastutil.longs.LongSet getStructureReferences(java.lang.Object p0) {
        return null;
    }

    public java.util.Map getStructureStarts() {
        return null;
    }

    public java.lang.Object getStructureStart(java.lang.Object p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.Chunk.TickSchedulers getTickSchedulers(long p0) {
        return null;
    }

    public int getTopSectionCoord() {
        return 0;
    }

    public int getTopYInclusive() {
        return 0;
    }

    public java.lang.Object getUpgradeData() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.World getWorld() {
        return null;
    }

    public boolean hasBelowZeroRetrogen() {
        return false;
    }

    public boolean hasHeightmap(murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type p0) {
        return false;
    }

    public boolean hasStructureReferences() {
        return false;
    }

    public void increaseInhabitedTime(long p0) {
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isInHeightLimit(int p0) {
        return false;
    }

    public boolean isLightOn() {
        return false;
    }

    public boolean isOutOfHeightLimit(int p0) {
        return false;
    }

    public boolean isOutOfHeightLimit(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean isSectionEmpty(int p0) {
        return false;
    }

    public boolean isSerializable() {
        return false;
    }

    public void loadBiomeFromPacket(java.lang.Object p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity loadBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p1) {
        return null;
    }

    public void loadEntities() {
    }

    public void loadFromPacket(java.lang.Object p0, java.util.Map p1, java.util.function.Consumer p2) {
    }

    public void markBlockForPostProcessing(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public void markBlocksForPostProcessing(it.unimi.dsi.fastutil.shorts.ShortList p0, int p1) {
    }

    public void markNeedsSaving() {
    }

    public boolean needsSaving() {
        return false;
    }

    public void populateBiomes(java.lang.Object p0, java.lang.Object p1) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult raycastBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p3, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p4) {
        return null;
    }

    public static java.lang.Object raycast(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, java.lang.Object p2, java.util.function.BiFunction p3, java.util.function.Function p4) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult raycast(murat.simv2.simulation.mirror.net.minecraft.world.BlockStateRaycastContext p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult raycast(murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext p0) {
        return null;
    }

    public void refreshSurfaceY() {
    }

    public void removeBlockEntityTicker(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public void removeBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public void removeChunkTickSchedulers(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public void removeGameEventDispatcher(int p0) {
    }

    public void removeGameEventListener(murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1) {
    }

    public void runPostProcessing(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public int sampleHeightmap(murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type p0, int p1, int p2) {
        return 0;
    }

    public int sectionCoordToIndex(int p0) {
        return 0;
    }

    public int sectionIndexToCoord(int p0) {
        return 0;
    }

    public void setBlockEntity(murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState setBlockState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState setBlockState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, int p2) {
        return null;
    }

    public void setHeightmap(murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type p0, long[] p1) {
    }

    public void setInhabitedTime(long p0) {
    }

    public void setLevelTypeProvider(java.util.function.Supplier p0) {
    }

    public void setLightOn(boolean p0) {
    }

    public void setLoadedToWorld(boolean p0) {
    }

    public void setStructureReferences(java.util.Map p0) {
    }

    public void setStructureStarts(java.util.Map p0) {
    }

    public void setStructureStart(java.lang.Object p0, java.lang.Object p1) {
    }

    public void setUnsavedListener(murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk.UnsavedListener p0) {
    }

    public boolean tryMarkSaved() {
        return false;
    }

    public void updateAllBlockEntities() {
    }

    public void updateGameEventListener(murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1) {
    }

    public void updateTicker(murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity p0) {
    }

    public boolean usesOldNoise() {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.BlockEntityTickInvoker wrapTicker(murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity p0, murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntityTicker p1) {
        return null;
    }

    public WorldChunk() {
    }

    public static class CreationType {
        public static murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk.CreationType CHECK;
        public static murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk.CreationType IMMEDIATE;
        public static murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk.CreationType QUEUED;
        public static murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk.CreationType[] field_12862;

        public CreationType(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk.CreationType valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk.CreationType[] values() {
            return null;
        }

        public CreationType() {
        }

    }

    public static class DirectBlockEntityTickInvoker<T> extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.world.chunk.BlockEntityTickInvoker {
        public java.lang.Object blockEntity;
        public boolean hasWarned;
        public murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntityTicker<java.lang.Object> ticker;
        public murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk worldChunk;

        public DirectBlockEntityTickInvoker(murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk p0, murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity p1, murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntityTicker p2) {
        }

        public java.lang.String getName() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getPos() {
            return null;
        }

        public boolean isRemoved() {
            return false;
        }

        public void tick() {
        }

        public java.lang.String toString() {
            return null;
        }

        public DirectBlockEntityTickInvoker() {
        }

    }

    public static interface EntityLoader {
        public void run(murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk p0);

    }

    public static interface UnsavedListener {
        public void setUnsaved(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0);

    }

    public static class WrappedBlockEntityTickInvoker extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.world.chunk.BlockEntityTickInvoker {
        public murat.simv2.simulation.mirror.net.minecraft.world.chunk.BlockEntityTickInvoker wrapped;

        public WrappedBlockEntityTickInvoker(murat.simv2.simulation.mirror.net.minecraft.world.chunk.BlockEntityTickInvoker p0) {
        }

        public java.lang.String getName() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getPos() {
            return null;
        }

        public boolean isRemoved() {
            return false;
        }

        public void setWrapped(murat.simv2.simulation.mirror.net.minecraft.world.chunk.BlockEntityTickInvoker p0) {
        }

        public void tick() {
        }

        public java.lang.String toString() {
            return null;
        }

        public WrappedBlockEntityTickInvoker() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
