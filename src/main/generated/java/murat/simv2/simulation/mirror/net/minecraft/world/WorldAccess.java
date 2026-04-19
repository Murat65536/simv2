package murat.simv2.simulation.mirror.net.minecraft.world;

// Generated mirror stub for simulation closure.
public interface WorldAccess extends murat.simv2.simulation.mirror.net.minecraft.world.LunarWorldView, murat.simv2.simulation.mirror.net.minecraft.world.RegistryWorldView, murat.simv2.simulation.mirror.net.minecraft.world.tick.ScheduledTickView {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public void addParticleClient(murat.simv2.simulation.mirror.net.minecraft.particle.ParticleEffect p0, double p1, double p2, double p3, double p4, double p5, double p6);

    public boolean breakBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, boolean p1);

    public boolean breakBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p2);

    public boolean breakBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p2, int p3);

    public boolean canCollide(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public boolean canPlace(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext p2);

    public static void collectCollisionsBetween(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p2, murat.simv2.simulation.mirror.net.minecraft.world.BlockView.CollisionVisitor p3) {
    }

    public boolean containsFluid(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0);

    public int countVerticalSections();

    public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper createCommandRegistryWrapper(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

    public murat.simv2.simulation.mirror.net.minecraft.world.tick.OrderedTick createOrderedTick(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.lang.Object p1, int p2);

    public murat.simv2.simulation.mirror.net.minecraft.world.tick.OrderedTick createOrderedTick(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.lang.Object p1, int p2, murat.simv2.simulation.mirror.net.minecraft.world.tick.TickPriority p3);

    public static murat.simv2.simulation.mirror.net.minecraft.world.HeightLimitView create(int p0, int p1) {
        return null;
    }

    public boolean doesNotIntersectEntities(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0);

    public boolean doesNotIntersectEntities(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p1);

    public void emitGameEvent(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2);

    public void emitGameEvent(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2);

    public void emitGameEvent(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.world.event.GameEvent.Emitter p2);

    public void emitGameEvent(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.world.event.GameEvent.Emitter p2);

    public void emitGameEvent(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.world.event.GameEvent.Emitter p2);

    public java.util.Optional findClosestCollision(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2, double p3, double p4, double p5);

    public java.util.Optional findSupportingBlockPos(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public int getAmbientDarkness();

    public int getBaseLightLevel(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1);

    public murat.simv2.simulation.mirror.net.minecraft.world.biome.source.BiomeAccess getBiomeAccess();

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry getBiomeForNoiseGen(int p0, int p1, int p2);

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry getBiome(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public java.lang.Iterable getBlockCollisions(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity getBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public java.util.Optional getBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntityType p1);

    public java.lang.Iterable getBlockOrFluidCollisions(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState getBlockState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public murat.simv2.simulation.mirror.net.minecraft.world.tick.QueryableTickScheduler getBlockTickScheduler();

    public int getBottomSectionCoord();

    public int getBottomY();

    public float getBrightness(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public float getBrightness(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0, boolean p1);

    public murat.simv2.simulation.mirror.net.minecraft.world.BlockView getChunkAsView(int p0, int p1);

    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.ChunkManager getChunkManager();

    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.Chunk getChunk(int p0, int p1);

    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.Chunk getChunk(int p0, int p1, murat.simv2.simulation.mirror.net.minecraft.world.chunk.ChunkStatus p2);

    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.Chunk getChunk(int p0, int p1, murat.simv2.simulation.mirror.net.minecraft.world.chunk.ChunkStatus p2, boolean p3);

    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.Chunk getChunk(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getClosestPlayer(double p0, double p1, double p2, double p3, boolean p4);

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getClosestPlayer(double p0, double p1, double p2, double p3, java.util.function.Predicate p4);

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getClosestPlayer(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, double p1);

    public murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult getCollisionsIncludingWorldBorder(murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext p0);

    public java.lang.Iterable getCollisions(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public int getColor(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.world.biome.ColorResolver p1);

    public murat.simv2.simulation.mirror.net.minecraft.world.Difficulty getDifficulty();

    public murat.simv2.simulation.mirror.net.minecraft.world.dimension.DimensionType getDimension();

    public double getDismountHeight(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public double getDismountHeight(murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p0, java.util.function.Supplier p1);

    public int getEmittedRedstonePower(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1);

    public int getEmittedRedstonePower(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1, boolean p2);

    public murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet getEnabledFeatures();

    public java.util.List getEntitiesByClass(java.lang.Class p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1, java.util.function.Predicate p2);

    public java.util.List getEntitiesByType(murat.simv2.simulation.mirror.net.minecraft.util.TypeFilter p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1, java.util.function.Predicate p2);

    public java.util.List getEntityCollisions(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState getFluidState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public murat.simv2.simulation.mirror.net.minecraft.world.tick.QueryableTickScheduler getFluidTickScheduler();

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry getGeneratorStoredBiome(int p0, int p1, int p2);

    public int getHeight();

    public murat.simv2.simulation.mirror.net.minecraft.world.WorldProperties getLevelProperties();

    public int getLightLevel(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public int getLightLevel(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1);

    public int getLightLevel(murat.simv2.simulation.mirror.net.minecraft.world.LightType p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1);

    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.light.LightingProvider getLightingProvider();

    public murat.simv2.simulation.mirror.net.minecraft.world.LocalDifficulty getLocalDifficulty(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public int getLuminance(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public long getLunarTime();

    public int getMoonPhase();

    public float getMoonSize();

    public java.util.List getNonSpectatingEntities(java.lang.Class p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public java.util.List getOtherEntities(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public java.util.List getOtherEntities(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1, java.util.function.Predicate p2);

    public float getPhototaxisFavor(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getPlayerByUuid(java.util.UUID p0);

    public java.util.List getPlayers();

    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random getRandom();

    public int getReceivedRedstonePower(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public int getReceivedStrongRedstonePower(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager getRegistryManager();

    public int getSeaLevel();

    public int getSectionIndex(int p0);

    public murat.simv2.simulation.mirror.net.minecraft.server.MinecraftServer getServer();

    public float getSkyAngle(float p0);

    public java.util.stream.Stream getStatesInBoxIfLoaded(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0);

    public java.util.stream.Stream getStatesInBox(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0);

    public int getStrongRedstonePower(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1);

    public long getTickOrder();

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getTopPosition(murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1);

    public int getTopSectionCoord();

    public int getTopYInclusive();

    public int getTopY(murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type p0, int p1, int p2);

    public int getTopY(murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1);

    public murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder getWorldBorder();

    public boolean isAir(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public boolean isBlockSpaceEmpty(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public boolean isChunkLoaded(int p0, int p1);

    public boolean isChunkLoaded(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public boolean isClient();

    public boolean isEmittingRedstonePower(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1);

    public boolean isInHeightLimit(int p0);

    public boolean isOutOfHeightLimit(int p0);

    public boolean isOutOfHeightLimit(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public boolean isPlayerInRange(double p0, double p1, double p2, double p3);

    public boolean isPosLoaded(int p0, int p1);

    public boolean isReceivingRedstonePower(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public boolean isRegionLoaded(int p0, int p1, int p2, int p3);

    public boolean isRegionLoaded(int p0, int p1, int p2, int p3, int p4, int p5);

    public boolean isRegionLoaded(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1);

    public boolean isSkyVisibleAllowingSea(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public boolean isSkyVisible(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public boolean isSpaceEmpty(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0);

    public boolean isSpaceEmpty(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public boolean isSpaceEmpty(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1, boolean p2);

    public boolean isSpaceEmpty(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0);

    public boolean isWater(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public void playSound(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p2, murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory p3);

    public void playSound(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p2, murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory p3, float p4, float p5);

    public murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult raycastBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p3, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p4);

    public static java.lang.Object raycast(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, java.lang.Object p2, java.util.function.BiFunction p3, java.util.function.Function p4) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult raycast(murat.simv2.simulation.mirror.net.minecraft.world.BlockStateRaycastContext p0);

    public murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult raycast(murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext p0);

    public boolean removeBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, boolean p1);

    public void replaceWithStateForNeighborUpdate(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3, int p4, int p5);

    public void scheduleBlockTick(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.Block p1, int p2);

    public void scheduleBlockTick(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.Block p1, int p2, murat.simv2.simulation.mirror.net.minecraft.world.tick.TickPriority p3);

    public void scheduleFluidTick(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid p1, int p2);

    public void scheduleFluidTick(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid p1, int p2, murat.simv2.simulation.mirror.net.minecraft.world.tick.TickPriority p3);

    public int sectionCoordToIndex(int p0);

    public int sectionIndexToCoord(int p0);

    public boolean setBlockState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, int p2);

    public boolean setBlockState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, int p2, int p3);

    public boolean spawnEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0);

    public void syncWorldEvent(int p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, int p2);

    public void syncWorldEvent(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, int p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, int p3);

    public boolean testBlockState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.util.function.Predicate p1);

    public boolean testFluidState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.util.function.Predicate p1);

    public void updateNeighbors(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.Block p1);

    // END GENERATED MIRROR NESTED STUBS
}
