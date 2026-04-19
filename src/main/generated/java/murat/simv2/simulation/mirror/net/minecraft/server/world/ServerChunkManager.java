package murat.simv2.simulation.mirror.net.minecraft.server.world;

// Generated mirror stub for simulation closure.
public class ServerChunkManager extends murat.simv2.simulation.mirror.net.minecraft.world.chunk.ChunkManager {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static int CACHE_SIZE;
    public static org.slf4j.Logger LOGGER;
    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.Chunk[] chunkCache;
    public java.lang.Object chunkLoadingManager;
    public long[] chunkPosCache;
    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.ChunkStatus[] chunkStatusCache;
    public java.util.Set<java.lang.Object> chunksToBroadcastUpdate;
    public long lastTickTime;
    public java.lang.Object levelManager;
    public java.lang.Object lightingProvider;
    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerChunkManager.MainThreadExecutor mainThreadExecutor;
    public murat.simv2.simulation.mirror.net.minecraft.world.PersistentStateManager persistentStateManager;
    public java.lang.Thread serverThread;
    public boolean spawnAnimals;
    public java.lang.Object spawnInfo;
    public boolean spawnMonsters;
    public java.util.List<murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk> spawningChunks;
    public java.lang.Object ticketManager;
    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld world;

    public ServerChunkManager(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.Session p1, com.mojang.datafixers.DataFixer p2, murat.simv2.simulation.mirror.net.minecraft.structure.StructureTemplateManager p3, java.util.concurrent.Executor p4, java.lang.Object p5, int p6, int p7, boolean p8, murat.simv2.simulation.mirror.net.minecraft.server.WorldGenerationProgressListener p9, java.lang.Object p10, java.util.function.Supplier p11) {
    }

    public void addTicket(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p1) {
    }

    public void addTicket(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p1, int p2) {
    }

    public void applySimulationDistance(int p0) {
    }

    public void applyViewDistance(int p0) {
    }

    public void broadcastUpdates(murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p0) {
    }

    public void close() {
    }

    public boolean executeQueuedTasks() {
        return false;
    }

    public java.util.concurrent.CompletableFuture getChunkFutureSyncOnMainThread(int p0, int p1, murat.simv2.simulation.mirror.net.minecraft.world.chunk.ChunkStatus p2, boolean p3) {
        return null;
    }

    public java.util.concurrent.CompletableFuture getChunkFuture(int p0, int p1, murat.simv2.simulation.mirror.net.minecraft.world.chunk.ChunkStatus p2, boolean p3) {
        return null;
    }

    public java.lang.Object getChunkGenerator() {
        return null;
    }

    public java.lang.Object getChunkHolder(long p0) {
        return null;
    }

    public java.lang.Object getChunkIoWorker() {
        return null;
    }

    public java.lang.String getChunkLoadingDebugInfo(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0) {
        return null;
    }

    public java.lang.Object getChunk(int p0, int p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.Chunk getChunk(int p0, int p1, murat.simv2.simulation.mirror.net.minecraft.world.chunk.ChunkStatus p2, boolean p3) {
        return null;
    }

    public java.lang.String getDebugString() {
        return null;
    }

    public it.unimi.dsi.fastutil.longs.LongSet getForcedChunks() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.light.LightingProvider getLightingProvider() {
        return null;
    }

    public int getLoadedChunkCount() {
        return 0;
    }

    public java.lang.Object getNoiseConfig() {
        return null;
    }

    public int getPendingTasks() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.PersistentStateManager getPersistentStateManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage getPointOfInterestStorage() {
        return null;
    }

    public java.lang.Object getSpawnInfo() {
        return null;
    }

    public java.lang.Object getStructurePlacementCalculator() {
        return null;
    }

    public int getTotalChunksLoadedCount() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk getWorldChunk(int p0, int p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk getWorldChunk(int p0, int p1, boolean p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.BlockView getWorld() {
        return null;
    }

    public void ifChunkLoaded(long p0, java.util.function.Consumer p1) {
    }

    public void initChunkCaches() {
    }

    public boolean isChunkLoaded(int p0, int p1) {
        return false;
    }

    public boolean isMissingForLevel(java.lang.Object p0, int p1) {
        return false;
    }

    public boolean isTickingFutureReady(long p0) {
        return false;
    }

    public void loadEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void markForUpdate(java.lang.Object p0) {
    }

    public void markForUpdate(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public void onLightUpdate(murat.simv2.simulation.mirror.net.minecraft.world.LightType p0, murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkSectionPos p1) {
    }

    public void onSectionStatusChanged(int p0, int p1, int p2, boolean p3) {
    }

    public void putInCache(long p0, murat.simv2.simulation.mirror.net.minecraft.world.chunk.Chunk p1, murat.simv2.simulation.mirror.net.minecraft.world.chunk.ChunkStatus p2) {
    }

    public void removeTicket(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p1, int p2) {
    }

    public void save(boolean p0) {
    }

    public void sendToNearbyPlayers(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet p1) {
    }

    public void sendToOtherNearbyPlayers(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet p1) {
    }

    public boolean setChunkForced(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0, boolean p1) {
        return false;
    }

    public void setMobSpawnOptions(boolean p0) {
    }

    public void shutdown() {
    }

    public void tickChunks() {
    }

    public void tickChunks(murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p0, long p1) {
    }

    public void tickSpawningChunk(murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk p0, long p1, java.util.List p2, java.lang.Object p3) {
    }

    public void tick(java.util.function.BooleanSupplier p0, boolean p1) {
    }

    public void unloadEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public boolean updateChunks() {
        return false;
    }

    public void updatePosition(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
    }

    public ServerChunkManager() {
    }

    public static class MainThreadExecutor extends murat.simv2.simulation.mirror.net.minecraft.util.thread.ThreadExecutor {
        public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerChunkManager field_18810;
        public static long field_52421;

        public MainThreadExecutor(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerChunkManager p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1) {
        }

        public boolean canExecute(java.lang.Runnable p0) {
            return false;
        }

        public void cancelTasks() {
        }

        public void close() {
        }

        public java.util.List createSamplers() {
            return null;
        }

        public java.lang.Runnable createTask(java.lang.Runnable p0) {
            return null;
        }

        public java.util.concurrent.CompletableFuture executeAsync(java.util.function.Consumer p0) {
            return null;
        }

        public void executeSync(java.lang.Runnable p0) {
        }

        public void executeTask(java.lang.Runnable p0) {
        }

        public void execute(java.lang.Runnable p0) {
        }

        public java.lang.String getName() {
            return null;
        }

        public int getTaskCount() {
            return 0;
        }

        public java.lang.Thread getThread() {
            return null;
        }

        public static boolean isMemoryError(java.lang.Throwable p0) {
            return false;
        }

        public boolean isOnThread() {
            return false;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.thread.TaskExecutor of(java.lang.String p0, java.util.concurrent.Executor p1) {
            return null;
        }

        public void runTasks() {
        }

        public void runTasks(java.util.function.BooleanSupplier p0) {
        }

        public boolean runTask() {
            return false;
        }

        public void send(java.lang.Runnable p0) {
        }

        public boolean shouldExecuteAsync() {
            return false;
        }

        public void submitAndJoin(java.lang.Runnable p0) {
        }

        public java.util.concurrent.CompletableFuture submit(java.lang.Runnable p0) {
            return null;
        }

        public java.util.concurrent.CompletableFuture submit(java.util.function.Supplier p0) {
            return null;
        }

        public void waitForTasks() {
        }

        public MainThreadExecutor() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
