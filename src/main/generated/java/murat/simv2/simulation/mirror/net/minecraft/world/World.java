package murat.simv2.simulation.mirror.net.minecraft.world;

// Generated mirror stub for simulation closure.
public class World {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey END;
    public static int HORIZONTAL_LIMIT;
    public static int MAX_UPDATE_DEPTH;
    public static int MAX_Y;
    public static int MIN_Y;
    public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey NETHER;
    public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey OVERWORLD;
    public int ambientDarkness;
    public java.lang.Object biomeAccess;
    public java.util.List blockEntityTickers;
    public murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder border;
    public java.lang.Object damageSources;
    public boolean debugWorld;
    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry dimensionEntry;
    public static int field_30967;
    public static int field_30968;
    public static int field_30969;
    public boolean isClient;
    public boolean iteratingTickingBlockEntities;
    public float lastRainGradient;
    public float lastThunderGradient;
    public int lcgBlockSeed;
    public int lcgBlockSeedIncrement;
    public java.lang.Object neighborUpdater;
    public java.util.List pendingBlockEntityTickers;
    public java.lang.Object properties;
    public float rainGradient;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random random;
    public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey registryKey;
    public java.lang.Object registryManager;
    public java.lang.Thread thread;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random threadSafeRandom;
    public float thunderGradient;
    public long tickOrder;

    public World(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p1, java.lang.Object p2, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p3, boolean p4, boolean p5, long p6, int p7) {
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

    public void calculateAmbientDarkness() {
    }

    public boolean canCollide(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1) {
        return false;
    }

    public boolean canEntityModifyAt(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public boolean canHaveWeather() {
        return false;
    }

    public boolean canPlace(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext p2) {
        return false;
    }

    public void close() {
    }

    public static void collectCollisionsBetween(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p2, murat.simv2.simulation.mirror.net.minecraft.world.BlockView.CollisionVisitor p3) {
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

    public void disconnect() {
    }

    public boolean doesNotIntersectEntities(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public boolean doesNotIntersectEntities(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p1) {
        return false;
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

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getClosestPlayer(double p0, double p1, double p2, double p3, boolean p4) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getClosestPlayer(double p0, double p1, double p2, double p3, java.util.function.Predicate p4) {
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

    public java.util.Collection getEnderDragonParts() {
        return null;
    }

    public java.util.List getEntitiesByClass(java.lang.Class p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1, java.util.function.Predicate p2) {
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

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getEntity(java.util.UUID p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState getFluidState(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public java.lang.Object getFluidTickScheduler() {
        return null;
    }

    public java.lang.Object getFuelRegistry() {
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

    public java.lang.Object getLocalDifficulty(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
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

    public java.util.List getOtherEntities(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1) {
        return null;
    }

    public java.util.List getOtherEntities(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1, java.util.function.Predicate p2) {
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

    public float getRainGradient(float p0) {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getRandomPosInChunk(int p0, int p1, int p2, int p3) {
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

    public boolean hasRain(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
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

    public boolean isInBuildLimit(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean isInHeightLimit(int p0) {
        return false;
    }

    public static boolean isInvalidVertically(int p0) {
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

    public boolean isTopSolid(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
        return false;
    }

    public static boolean isValidHorizontally(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public static boolean isValid(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean isWater(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public void markDirty(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public void onBlockStateChanged(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2) {
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

    public void replaceWithStateForNeighborUpdate(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3, int p4, int p5) {
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

    public void setLightningTicksLeft(int p0) {
    }

    public void setMobSpawnOptions(boolean p0) {
    }

    public void setRainGradient(float p0) {
    }

    public void setThunderGradient(float p0) {
    }

    public boolean shouldTickBlockPos(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean shouldTickBlocksInChunk(long p0) {
        return false;
    }

    public boolean shouldUpdatePostDeath(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public boolean spawnEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
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

    public void tickEntity(java.util.function.Consumer p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
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

    public World() {
    }

    public static class ExplosionSourceType {
        public static murat.simv2.simulation.mirror.net.minecraft.world.World.ExplosionSourceType BLOCK;
        public static com.mojang.serialization.Codec CODEC;
        public static murat.simv2.simulation.mirror.net.minecraft.world.World.ExplosionSourceType MOB;
        public static murat.simv2.simulation.mirror.net.minecraft.world.World.ExplosionSourceType NONE;
        public static murat.simv2.simulation.mirror.net.minecraft.world.World.ExplosionSourceType TNT;
        public static murat.simv2.simulation.mirror.net.minecraft.world.World.ExplosionSourceType TRIGGER;
        public static murat.simv2.simulation.mirror.net.minecraft.world.World.ExplosionSourceType[] field_40892;
        public java.lang.String id;

        public ExplosionSourceType(java.lang.String p0, int p1, java.lang.String p2) {
        }

        public java.lang.String asString() {
            return null;
        }

        public static com.mojang.serialization.Codec createBasicCodec(java.util.function.Supplier p0) {
            return null;
        }

        public static java.lang.Object createCodec(java.util.function.Supplier p0) {
            return null;
        }

        public static java.lang.Object createCodec(java.util.function.Supplier p0, java.util.function.Function p1) {
            return null;
        }

        public static java.util.function.Function createMapper(java.lang.Object[] p0, java.util.function.Function p1) {
            return null;
        }

        public int ordinal() {
            return 0;
        }

        public static com.mojang.serialization.Keyable toKeyable(java.lang.Object[] p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.World.ExplosionSourceType valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.World.ExplosionSourceType[] values() {
            return null;
        }

        public ExplosionSourceType() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
