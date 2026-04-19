package murat.simv2.simulation.mirror.net.minecraft.world.chunk;

// Generated mirror stub for simulation closure.
public class Chunk extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.world.biome.source.BiomeAccess.Storage {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static it.unimi.dsi.fastutil.longs.LongSet EMPTY_STRUCTURE_REFERENCES;
    public static org.slf4j.Logger LOGGER;
    public static int MISSING_SECTION;
    public java.lang.Object blendingData;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos, murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity> blockEntities;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound> blockEntityNbts;
    public java.lang.Object chunkNoiseSampler;
    public java.lang.Object chunkSkyLight;
    public java.lang.Object generationSettings;
    public murat.simv2.simulation.mirror.net.minecraft.world.HeightLimitView heightLimitView;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type, murat.simv2.simulation.mirror.net.minecraft.world.Heightmap> heightmaps;
    public long inhabitedTime;
    public boolean lightOn;
    public boolean needsSaving;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos pos;
    public it.unimi.dsi.fastutil.shorts.ShortList[] postProcessingLists;
    public java.lang.Object[] sectionArray;
    public java.util.Map<java.lang.Object, it.unimi.dsi.fastutil.longs.LongSet> structureReferences;
    public java.util.Map<java.lang.Object, java.lang.Object> structureStarts;
    public java.lang.Object upgradeData;

    public Chunk(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.world.HeightLimitView p2, murat.simv2.simulation.mirror.net.minecraft.registry.Registry p3, long p4, java.lang.Object[] p5, java.lang.Object p6) {
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

    public static void collectCollisionsBetween(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p2, murat.simv2.simulation.mirror.net.minecraft.world.BlockView.CollisionVisitor p3) {
    }

    public int countVerticalSections() {
        return 0;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.world.HeightLimitView create(int p0, int p1) {
        return null;
    }

    public static void fillSectionArray(murat.simv2.simulation.mirror.net.minecraft.registry.Registry p0, java.lang.Object[] p1) {
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

    public void removeBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
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

    public void setLightOn(boolean p0) {
    }

    public void setStructureReferences(java.util.Map p0) {
    }

    public void setStructureStarts(java.util.Map p0) {
    }

    public void setStructureStart(java.lang.Object p0, java.lang.Object p1) {
    }

    public boolean tryMarkSaved() {
        return false;
    }

    public boolean usesOldNoise() {
        return false;
    }

    public Chunk() {
    }

    public static class TickSchedulers {
        public java.util.List<java.lang.Object> blocks;
        public java.util.List<java.lang.Object> fluids;

        public TickSchedulers(java.util.List p0, java.util.List p1) {
        }

        public java.util.List blocks() {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public java.util.List fluids() {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public java.lang.String toString() {
            return null;
        }

        public TickSchedulers() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
