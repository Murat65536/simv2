package murat.simv2.simulation.mirror.net.minecraft.server.world;

// Generated mirror stub for simulation closure.
public class ServerWorld extends murat.simv2.simulation.mirror.net.minecraft.world.World {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.lang.Object CLEAR_THUNDER_WEATHER_DURATION_PROVIDER;
    public static java.lang.Object CLEAR_WEATHER_DURATION_PROVIDER;
    public static com.mojang.serialization.Codec CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey END;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos END_SPAWN_POS;
    public static int HORIZONTAL_LIMIT;
    public static org.slf4j.Logger LOGGER;
    public static int MAX_TICKS;
    public static int MAX_UPDATE_DEPTH;
    public static int MAX_Y;
    public static int MIN_Y;
    public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey NETHER;
    public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey OVERWORLD;
    public static java.lang.Object RAIN_WEATHER_DURATION_PROVIDER;
    public static int SERVER_IDLE_COOLDOWN;
    public static java.lang.Object THUNDER_WEATHER_DURATION_PROVIDER;
    public java.util.List blockEntityTickers;
    public java.util.List blockEventQueue;
    public java.lang.Object blockTickScheduler;
    public java.lang.Object chunkManager;
    public boolean duringListenerUpdate;
    public java.lang.Object enderDragonFight;
    public it.unimi.dsi.fastutil.ints.Int2ObjectMap enderDragonParts;
    public java.lang.Object entityList;
    public java.lang.Object entityManager;
    public static int field_30967;
    public static int field_30968;
    public static int field_30969;
    public java.lang.Object fluidTickScheduler;
    public java.lang.Object gameEventDispatchManager;
    public int idleTimeout;
    public boolean inBlockTick;
    public boolean isClient;
    public float lastRainGradient;
    public float lastThunderGradient;
    public int lcgBlockSeed;
    public int lcgBlockSeedIncrement;
    public java.util.Set loadedMobs;
    public java.lang.Object neighborUpdater;
    public java.lang.Object pathNodeTypeCache;
    public java.util.List players;
    public java.lang.Object portalForcer;
    public java.lang.Object properties;
    public java.lang.Object raidManager;
    public float rainGradient;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random random;
    public java.lang.Object randomSequences;
    public boolean savingDisabled;
    public java.lang.Object server;
    public boolean shouldTickTime;
    public java.lang.Object sleepManager;
    public int spawnChunkRadius;
    public java.util.List spawners;
    public java.lang.Object structureAccessor;
    public java.lang.Object structureLocator;
    public it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet syncedBlockEventQueue;
    public float thunderGradient;
    public java.lang.Object worldProperties;

    public ServerWorld(java.lang.Object p0, java.util.concurrent.Executor p1, java.lang.Object p2, java.lang.Object p3, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p4, java.lang.Object p5, java.lang.Object p6, boolean p7, long p8, java.util.List p9, boolean p10, java.lang.Object p11) {
    }

    public void addBlockBreakParticles(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1) {
    }

    public void addBlockEntityTicker(java.lang.Object p0) {
    }

    public void addBlockEntity(java.lang.Object p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection addDetailsToCrashReport(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport p0) {
        return null;
    }

    public void addEntities(java.util.stream.Stream p0) {
    }

    public boolean addEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public void addFireworkParticle(double p0, double p1, double p2, double p3, double p4, double p5, java.util.List p6) {
    }

    public void addImportantParticleClient(java.lang.Object p0, boolean p1, double p2, double p3, double p4, double p5, double p6, double p7) {
    }

    public void addImportantParticleClient(java.lang.Object p0, double p1, double p2, double p3, double p4, double p5, double p6) {
    }

    public void addParticleClient(java.lang.Object p0, boolean p1, boolean p2, double p3, double p4, double p5, double p6, double p7, double p8) {
    }

    public void addParticleClient(java.lang.Object p0, double p1, double p2, double p3, double p4, double p5, double p6) {
    }

    public void addPlayer(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
    }

    public void addSyncedBlockEvent(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.Block p1, int p2, int p3) {
    }

    public java.lang.String asString() {
        return null;
    }

    public boolean breakBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, boolean p1) {
        return false;
    }

    public boolean breakBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p2) {
        return false;
    }

    public boolean breakBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p2, int p3) {
        return false;
    }

    public void cacheStructures(java.lang.Object p0) {
    }

    public void calculateAmbientDarkness() {
    }

    public boolean canCollide(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1) {
        return false;
    }

    public boolean canEntityModifyAt(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public boolean canPlace(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext p2) {
        return false;
    }

    public boolean canSpawnEntitiesAt(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0) {
        return false;
    }

    public void clearUpdatesInArea(java.lang.Object p0) {
    }

    public void close() {
    }

    public static void collectCollisionsBetween(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p2, murat.simv2.simulation.mirror.net.minecraft.world.BlockView.CollisionVisitor p3) {
    }

    public void collectEntitiesByType(java.lang.Object p0, java.util.function.Predicate p1, java.util.List p2) {
    }

    public void collectEntitiesByType(java.lang.Object p0, java.util.function.Predicate p1, java.util.List p2, int p3) {
    }

    public void collectEntitiesByType(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1, java.util.function.Predicate p2, java.util.List p3) {
    }

    public void collectEntitiesByType(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1, java.util.function.Predicate p2, java.util.List p3, int p4) {
    }

    public boolean containsFluid(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0) {
        return false;
    }

    public int countVerticalSections() {
        return 0;
    }

    public java.lang.Object createCommandRegistryWrapper(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
        return null;
    }

    public void createExplosion(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, double p1, double p2, double p3, float p4, boolean p5, murat.simv2.simulation.mirror.net.minecraft.world.World.ExplosionSourceType p6) {
    }

    public void createExplosion(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, double p1, double p2, double p3, float p4, murat.simv2.simulation.mirror.net.minecraft.world.World.ExplosionSourceType p5) {
    }

    public void createExplosion(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1, java.lang.Object p2, double p3, double p4, double p5, float p6, boolean p7, murat.simv2.simulation.mirror.net.minecraft.world.World.ExplosionSourceType p8) {
    }

    public void createExplosion(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1, java.lang.Object p2, double p3, double p4, double p5, float p6, boolean p7, murat.simv2.simulation.mirror.net.minecraft.world.World.ExplosionSourceType p8, java.lang.Object p9, java.lang.Object p10, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p11) {
    }

    public void createExplosion(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1, java.lang.Object p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p3, float p4, boolean p5, murat.simv2.simulation.mirror.net.minecraft.world.World.ExplosionSourceType p6) {
    }

    public java.lang.Object createOrderedTick(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.lang.Object p1, int p2) {
        return null;
    }

    public java.lang.Object createOrderedTick(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.lang.Object p1, int p2, java.lang.Object p3) {
        return null;
    }

    public static java.lang.Object create(int p0, int p1) {
        return null;
    }

    public void disableTickSchedulers(java.lang.Object p0) {
    }

    public void disconnect() {
    }

    public boolean doesNotIntersectEntities(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public boolean doesNotIntersectEntities(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p1) {
        return false;
    }

    public void dumpBlockEntities(java.io.Writer p0) {
    }

    public static void dumpEntities(java.io.Writer p0, java.lang.Iterable p1) {
    }

    public void dump(java.nio.file.Path p0) {
    }

    public void emitGameEvent(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
    }

    public void emitGameEvent(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2) {
    }

    public void emitGameEvent(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.world.event.GameEvent.Emitter p2) {
    }

    public void emitGameEvent(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.world.event.GameEvent.Emitter p2) {
    }

    public void emitGameEvent(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.world.event.GameEvent.Emitter p2) {
    }

    public java.util.Optional findClosestCollision(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2, double p3, double p4, double p5) {
        return null;
    }

    public java.util.Optional findSupportingBlockPos(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1) {
        return null;
    }

    public java.util.List getAliveEnderDragons() {
        return null;
    }

    public int getAmbientDarkness() {
        return 0;
    }

    public int getBaseLightLevel(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1) {
        return 0;
    }

    public java.lang.Object getBiomeAccess() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry getBiomeForNoiseGen(int p0, int p1, int p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry getBiome(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public java.lang.Iterable getBlockCollisions(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1) {
        return null;
    }

    public int getBlockColor(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return 0;
    }

    public java.lang.Object getBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public java.util.Optional getBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.lang.Object p1) {
        return null;
    }

    public java.lang.Iterable getBlockOrFluidCollisions(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1) {
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

    public java.lang.Object getBrewingRecipeRegistry() {
        return null;
    }

    public float getBrightness(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return 0.0F;
    }

    public float getBrightness(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0, boolean p1) {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.BlockView getChunkAsView(int p0, int p1) {
        return null;
    }

    public java.lang.Object getChunkManager() {
        return null;
    }

    public java.lang.Object getChunk(int p0, int p1) {
        return null;
    }

    public java.lang.Object getChunk(int p0, int p1, java.lang.Object p2) {
        return null;
    }

    public java.lang.Object getChunk(int p0, int p1, java.lang.Object p2, boolean p3) {
        return null;
    }

    public java.lang.Object getChunk(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity getClosestEntity(java.lang.Class p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p2, double p3, double p4, double p5, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p6) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity getClosestEntity(java.util.List p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p2, double p3, double p4, double p5) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getClosestPlayer(double p0, double p1, double p2, double p3, boolean p4) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getClosestPlayer(double p0, double p1, double p2, double p3, java.util.function.Predicate p4) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getClosestPlayer(java.lang.Object p0, double p1, double p2, double p3) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getClosestPlayer(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getClosestPlayer(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, double p2, double p3, double p4) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getClosestPlayer(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, double p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult getCollisionsIncludingWorldBorder(murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext p0) {
        return null;
    }

    public java.lang.Iterable getCollisions(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1) {
        return null;
    }

    public int getColor(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.lang.Object p1) {
        return 0;
    }

    public java.util.List getCrammedEntities(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1) {
        return null;
    }

    public java.lang.Object getDamageSources() {
        return null;
    }

    public java.lang.String getDebugString() {
        return null;
    }

    public java.lang.Object getDestructionType(murat.simv2.simulation.mirror.net.minecraft.world.GameRules.Key p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.Difficulty getDifficulty() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry getDimensionEntry() {
        return null;
    }

    public java.lang.Object getDimension() {
        return null;
    }

    public double getDismountHeight(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return 0.0D;
    }

    public double getDismountHeight(murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p0, java.util.function.Supplier p1) {
        return 0.0D;
    }

    public int getEmittedRedstonePower(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1) {
        return 0;
    }

    public int getEmittedRedstonePower(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1, boolean p2) {
        return 0;
    }

    public java.lang.Object getEnabledFeatures() {
        return null;
    }

    public java.lang.Object getEnderDragonFight() {
        return null;
    }

    public java.util.Collection getEnderDragonParts() {
        return null;
    }

    public java.util.List getEntitiesByClass(java.lang.Class p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1, java.util.function.Predicate p2) {
        return null;
    }

    public java.util.List getEntitiesByType(java.lang.Object p0, java.util.function.Predicate p1) {
        return null;
    }

    public java.util.List getEntitiesByType(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1, java.util.function.Predicate p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getEntityById(int p0) {
        return null;
    }

    public java.util.List getEntityCollisions(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1) {
        return null;
    }

    public java.lang.Object getEntityLookup() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getEntityOrDragonPart(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getEntity(java.util.UUID p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState getFluidState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public java.lang.Object getFluidTickScheduler() {
        return null;
    }

    public it.unimi.dsi.fastutil.longs.LongSet getForcedChunks() {
        return null;
    }

    public java.lang.Object getFuelRegistry() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.GameRules getGameRules() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry getGeneratorStoredBiome(int p0, int p1, int p2) {
        return null;
    }

    public int getHeight() {
        return 0;
    }

    public java.lang.Object getLevelProperties() {
        return null;
    }

    public int getLightLevel(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return 0;
    }

    public int getLightLevel(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return 0;
    }

    public int getLightLevel(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1) {
        return 0;
    }

    public java.lang.Object getLightingProvider() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getLightningPos(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public java.util.Optional getLightningRodPos(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public java.lang.Object getLocalDifficulty(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public int getLogicalHeight() {
        return 0;
    }

    public int getLuminance(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return 0;
    }

    public long getLunarTime() {
        return 0L;
    }

    public java.lang.Object getMapState(java.lang.Object p0) {
        return null;
    }

    public int getMoonPhase() {
        return 0;
    }

    public float getMoonSize() {
        return 0.0F;
    }

    public java.util.List getNonSpectatingEntities(java.lang.Class p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1) {
        return null;
    }

    public int getOccupiedPointOfInterestDistance(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkSectionPos p0) {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random getOrCreateRandom(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public java.util.List getOtherEntities(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1) {
        return null;
    }

    public java.util.List getOtherEntities(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1, java.util.function.Predicate p2) {
        return null;
    }

    public java.lang.Object getPathNodeTypeCache() {
        return null;
    }

    public java.lang.Object getPersistentStateManager() {
        return null;
    }

    public float getPhototaxisFavor(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getPlayerByUuid(java.util.UUID p0) {
        return null;
    }

    public java.util.List getPlayers() {
        return null;
    }

    public java.util.List getPlayers(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p2) {
        return null;
    }

    public java.util.List getPlayers(java.util.function.Predicate p0) {
        return null;
    }

    public java.util.List getPlayers(java.util.function.Predicate p0, int p1) {
        return null;
    }

    public java.lang.Object getPointOfInterestStorage() {
        return null;
    }

    public java.lang.Object getPortalForcer() {
        return null;
    }

    public java.lang.Object getRaidAt(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public java.lang.Object getRaidManager() {
        return null;
    }

    public float getRainGradient(float p0) {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity getRandomAlivePlayer() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getRandomPosInChunk(int p0, int p1, int p2, int p3) {
        return null;
    }

    public java.lang.Object getRandomSequences() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random getRandom() {
        return null;
    }

    public int getReceivedRedstonePower(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return 0;
    }

    public int getReceivedStrongRedstonePower(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return 0;
    }

    public java.lang.Object getRecipeManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey getRegistryKey() {
        return null;
    }

    public java.lang.Object getRegistryManager() {
        return null;
    }

    public java.lang.Object getScoreboard() {
        return null;
    }

    public int getSeaLevel() {
        return 0;
    }

    public int getSectionIndex(int p0) {
        return 0;
    }

    public long getSeed() {
        return 0L;
    }

    public java.lang.Object getServer() {
        return null;
    }

    public float getSkyAngleRadians(float p0) {
        return 0.0F;
    }

    public float getSkyAngle(float p0) {
        return 0.0F;
    }

    public float getSpawnAngle() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getSpawnPos() {
        return null;
    }

    public java.util.stream.Stream getStatesInBoxIfLoaded(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0) {
        return null;
    }

    public java.util.stream.Stream getStatesInBox(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0) {
        return null;
    }

    public int getStrongRedstonePower(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1) {
        return 0;
    }

    public java.lang.Object getStructureAccessor() {
        return null;
    }

    public java.lang.Object getStructureTemplateManager() {
        return null;
    }

    public java.util.List getTargets(java.lang.Class p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p3) {
        return null;
    }

    public float getThunderGradient(float p0) {
        return 0.0F;
    }

    public java.lang.Object getTickManager() {
        return null;
    }

    public long getTickOrder() {
        return 0L;
    }

    public long getTimeOfDay() {
        return 0L;
    }

    public long getTime() {
        return 0L;
    }

    public static java.lang.String getTopFive(java.lang.Iterable p0, java.util.function.Function p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getTopPosition(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return null;
    }

    public int getTopSectionCoord() {
        return 0;
    }

    public int getTopYInclusive() {
        return 0;
    }

    public int getTopY(java.lang.Object p0, int p1, int p2) {
        return 0;
    }

    public int getTopY(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder getWorldBorder() {
        return null;
    }

    public java.lang.Object getWorldChunk(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public void handleInteraction(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1, java.lang.Object p2) {
    }

    public boolean hasRaidAt(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean hasRain(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public java.lang.Object increaseAndGetMapId() {
        return null;
    }

    public void initWeatherGradients() {
    }

    public boolean isAir(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean isBlockSpaceEmpty(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1) {
        return false;
    }

    public boolean isChunkLoaded(int p0, int p1) {
        return false;
    }

    public boolean isChunkLoaded(long p0) {
        return false;
    }

    public boolean isChunkLoaded(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean isClient() {
        return false;
    }

    public boolean isDay() {
        return false;
    }

    public boolean isDebugWorld() {
        return false;
    }

    public boolean isDirectionSolid(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p2) {
        return false;
    }

    public boolean isEmittingRedstonePower(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1) {
        return false;
    }

    public boolean isFlat() {
        return false;
    }

    public boolean isInBlockTick() {
        return false;
    }

    public boolean isInBuildLimit(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean isInHeightLimit(int p0) {
        return false;
    }

    public boolean isNearOccupiedPointOfInterest(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean isNearOccupiedPointOfInterest(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1) {
        return false;
    }

    public boolean isNearOccupiedPointOfInterest(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkSectionPos p0) {
        return false;
    }

    public boolean isNightAndNatural() {
        return false;
    }

    public boolean isNight() {
        return false;
    }

    public boolean isOutOfHeightLimit(int p0) {
        return false;
    }

    public boolean isOutOfHeightLimit(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean isPlayerInRange(double p0, double p1, double p2, double p3) {
        return false;
    }

    public boolean isPosLoaded(int p0, int p1) {
        return false;
    }

    public boolean isPosLoaded(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean isRaining() {
        return false;
    }

    public boolean isReceivingRedstonePower(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean isRegionLoaded(int p0, int p1, int p2, int p3) {
        return false;
    }

    public boolean isRegionLoaded(int p0, int p1, int p2, int p3, int p4, int p5) {
        return false;
    }

    public boolean isRegionLoaded(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public boolean isSavingDisabled() {
        return false;
    }

    public boolean isSkyVisibleAllowingSea(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean isSkyVisible(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean isSleepingEnabled() {
        return false;
    }

    public boolean isSpaceEmpty(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public boolean isSpaceEmpty(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1) {
        return false;
    }

    public boolean isSpaceEmpty(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1, boolean p2) {
        return false;
    }

    public boolean isSpaceEmpty(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0) {
        return false;
    }

    public boolean isThundering() {
        return false;
    }

    public boolean isTickingFutureReady(long p0) {
        return false;
    }

    public boolean isTopSolid(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
        return false;
    }

    public boolean isValidForSetBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public static boolean isValid(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean isWater(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public java.lang.Iterable iterateEntities() {
        return null;
    }

    public void loadEntities(java.util.stream.Stream p0) {
    }

    public com.mojang.datafixers.util.Pair locateBiome(java.util.function.Predicate p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, int p2, int p3, int p4) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos locateStructure(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, int p2, boolean p3) {
        return null;
    }

    public void markDirty(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public void onBlockStateChanged(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2) {
    }

    public void onDimensionChanged(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void onPlayerConnected(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
    }

    public void onPlayerRespawned(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
    }

    public void playSoundAtBlockCenterClient(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p1, murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory p2, float p3, float p4, boolean p5) {
    }

    public void playSoundClient(double p0, double p1, double p2, murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p3, murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory p4, float p5, float p6, boolean p7) {
    }

    public void playSoundClient(murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p0, murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory p1, float p2, float p3) {
    }

    public void playSoundFromEntityClient(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p1, murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory p2, float p3, float p4) {
    }

    public void playSoundFromEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p2, murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory p3, float p4, float p5, long p6) {
    }

    public void playSoundFromEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1, murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p2, murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory p3, float p4, float p5) {
    }

    public void playSound(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, double p1, double p2, double p3, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p4, murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory p5, float p6, float p7) {
    }

    public void playSound(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, double p1, double p2, double p3, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p4, murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory p5, float p6, float p7, long p8) {
    }

    public void playSound(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, double p1, double p2, double p3, murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p4, murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory p5) {
    }

    public void playSound(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, double p1, double p2, double p3, murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p4, murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory p5, float p6, float p7) {
    }

    public void playSound(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, double p1, double p2, double p3, murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p4, murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory p5, float p6, float p7, long p8) {
    }

    public void playSound(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p2, murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory p3) {
    }

    public void playSound(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p2, murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory p3, float p4, float p5) {
    }

    public boolean processBlockEvent(java.lang.Object p0) {
        return false;
    }

    public void processSyncedBlockEvents() {
    }

    public void putMapState(java.lang.Object p0, java.lang.Object p1) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult raycastBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p3, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p4) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult raycast(java.lang.Object p0) {
        return null;
    }

    public static java.lang.Object raycast(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, java.lang.Object p2, java.util.function.BiFunction p3, java.util.function.Function p4) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult raycast(murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext p0) {
        return null;
    }

    public void removeBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public boolean removeBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, boolean p1) {
        return false;
    }

    public void removePlayer(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p1) {
    }

    public void replaceWithStateForNeighborUpdate(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3, int p4, int p5) {
    }

    public void resetIdleTimeout() {
    }

    public void resetWeather() {
    }

    public void savePersistentState(boolean p0) {
    }

    public void save(java.lang.Object p0, boolean p1, boolean p2) {
    }

    public void scheduleBlockRerenderIfNeeded(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2) {
    }

    public void scheduleBlockTick(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.Block p1, int p2) {
    }

    public void scheduleBlockTick(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.Block p1, int p2, java.lang.Object p3) {
    }

    public void scheduleFluidTick(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid p1, int p2) {
    }

    public void scheduleFluidTick(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid p1, int p2, java.lang.Object p3) {
    }

    public int sectionCoordToIndex(int p0) {
        return 0;
    }

    public int sectionIndexToCoord(int p0) {
        return 0;
    }

    public void sendEntityDamage(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1) {
    }

    public void sendEntityStatus(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, byte p1) {
    }

    public void sendPacket(java.lang.Object p0) {
    }

    public void sendSleepingStatus() {
    }

    public boolean sendToPlayerIfNearby(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0, boolean p1, double p2, double p3, double p4, java.lang.Object p5) {
        return false;
    }

    public void setBlockBreakingInfo(int p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, int p2) {
    }

    public boolean setBlockState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1) {
        return false;
    }

    public boolean setBlockState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, int p2) {
        return false;
    }

    public boolean setBlockState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, int p2, int p3) {
        return false;
    }

    public boolean setChunkForced(int p0, int p1, boolean p2) {
        return false;
    }

    public void setCurrentlyGeneratingStructureName(java.util.function.Supplier p0) {
    }

    public void setEnderDragonFight(java.lang.Object p0) {
    }

    public void setLightningTicksLeft(int p0) {
    }

    public void setMobSpawnOptions(boolean p0) {
    }

    public void setRainGradient(float p0) {
    }

    public void setSpawnPos(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, float p1) {
    }

    public void setThunderGradient(float p0) {
    }

    public void setTimeOfDay(long p0) {
    }

    public void setWeather(int p0, int p1, boolean p2, boolean p3) {
    }

    public boolean shouldTickBlockAt(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean shouldTickBlockPos(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean shouldTickBlocksInChunk(long p0) {
        return false;
    }

    public boolean shouldTickChunkAt(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0) {
        return false;
    }

    public boolean shouldTickEntityAt(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean shouldTickTestAt(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0) {
        return false;
    }

    public boolean shouldUpdatePostDeath(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public void spawnEntityAndPassengers(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public boolean spawnEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public boolean spawnNewEntityAndPassengers(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public int spawnParticles(java.lang.Object p0, boolean p1, boolean p2, double p3, double p4, double p5, int p6, double p7, double p8, double p9, double p10) {
        return 0;
    }

    public int spawnParticles(java.lang.Object p0, double p1, double p2, double p3, int p4, double p5, double p6, double p7, double p8) {
        return 0;
    }

    public boolean spawnParticles(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0, java.lang.Object p1, boolean p2, boolean p3, double p4, double p5, double p6, int p7, double p8, double p9, double p10, double p11) {
        return false;
    }

    public void syncGlobalEvent(int p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, int p2) {
    }

    public void syncWorldEvent(int p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, int p2) {
    }

    public void syncWorldEvent(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, int p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, int p3) {
    }

    public boolean testBlockState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.util.function.Predicate p1) {
        return false;
    }

    public boolean testFluidState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.util.function.Predicate p1) {
        return false;
    }

    public void tickBlockEntities() {
    }

    public void tickBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.Block p1) {
    }

    public void tickChunk(java.lang.Object p0, int p1) {
    }

    public void tickEntity(java.util.function.Consumer p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
    }

    public void tickEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void tickFluid(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid p1) {
    }

    public void tickIceAndSnow(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public void tickPassenger(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
    }

    public void tickSpawners(boolean p0, boolean p1) {
    }

    public void tickThunder(java.lang.Object p0) {
    }

    public void tickTime() {
    }

    public void tickWeather() {
    }

    public void tick(java.util.function.BooleanSupplier p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld toServerWorld() {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public boolean tryLoadEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public void unloadEntities(java.lang.Object p0) {
    }

    public void updateComparators(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.Block p1) {
    }

    public void updateListeners(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2, int p3) {
    }

    public void updateNeighborsAlways(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.Block p1, java.lang.Object p2) {
    }

    public void updateNeighborsExcept(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.Block p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p2, java.lang.Object p3) {
    }

    public void updateNeighbors(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.Block p1) {
    }

    public void updateNeighbor(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.Block p2, java.lang.Object p3, boolean p4) {
    }

    public void updateNeighbor(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.Block p1, java.lang.Object p2) {
    }

    public void updateSleepingPlayers() {
    }

    public void wakeSleepingPlayers() {
    }

    public ServerWorld() {
    }

    public static class ServerEntityHandler {
        public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld field_26936;

        public ServerEntityHandler(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
        }

        public void create(java.lang.Object p0) {
        }

        public void create(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        }

        public void destroy(java.lang.Object p0) {
        }

        public void destroy(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        }

        public void startTicking(java.lang.Object p0) {
        }

        public void startTicking(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        }

        public void startTracking(java.lang.Object p0) {
        }

        public void startTracking(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        }

        public void stopTicking(java.lang.Object p0) {
        }

        public void stopTicking(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        }

        public void stopTracking(java.lang.Object p0) {
        }

        public void stopTracking(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        }

        public void updateLoadStatus(java.lang.Object p0) {
        }

        public void updateLoadStatus(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        }

        public ServerEntityHandler() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
