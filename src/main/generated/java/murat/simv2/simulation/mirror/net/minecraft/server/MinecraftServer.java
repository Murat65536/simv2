package murat.simv2.simulation.mirror.net.minecraft.server;

// Generated mirror stub for simulation closure.
public class MinecraftServer extends murat.simv2.simulation.mirror.net.minecraft.util.thread.ReentrantThreadExecutor implements murat.simv2.simulation.mirror.net.minecraft.server.command.CommandOutput {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.authlib.GameProfile ANONYMOUS_PLAYER_PROFILE;
    public static java.lang.Object DEMO_LEVEL_INFO;
    public static org.slf4j.Logger LOGGER;
    public static int MAX_WORLD_BORDER_RADIUS;
    public static long OVERLOAD_THRESHOLD_NANOS;
    public static long OVERLOAD_WARNING_INTERVAL_NANOS;
    public static long PLAYER_SAMPLE_UPDATE_INTERVAL_NANOS;
    public static long PREPARE_START_REGION_TICK_DELAY_NANOS;
    public static java.lang.String VANILLA;
    public static java.util.concurrent.atomic.AtomicReference<java.lang.RuntimeException> WORLD_GEN_EXCEPTION;
    public murat.simv2.simulation.mirror.net.minecraft.util.ApiServices apiServices;
    public float averageTickTime;
    public java.lang.Object bossBarManager;
    public murat.simv2.simulation.mirror.net.minecraft.recipe.BrewingRecipeRegistry brewingRecipeRegistry;
    public java.lang.Object combinedDynamicRegistries;
    public java.lang.Object commandFunctionManager;
    public java.lang.Object dataCommandStorage;
    public com.mojang.datafixers.DataFixer dataFixer;
    public murat.simv2.simulation.mirror.net.minecraft.resource.ResourcePackManager dataPackManager;
    public murat.simv2.simulation.mirror.net.minecraft.server.MinecraftServer.DebugStart debugStart;
    public boolean demo;
    public com.mojang.jtracy.DiscontinuousFrame discontinuousFrame;
    public boolean enforceWhitelist;
    public murat.simv2.simulation.mirror.net.minecraft.server.ServerMetadata.Favicon favicon;
    public static float field_33212;
    public static int field_33213;
    public static int field_33218;
    public static int field_33220;
    public static int field_33221;
    public static int field_47144;
    public static int field_47146;
    public static int field_47149;
    public static int field_48466;
    public static long field_52421;
    public boolean flightEnabled;
    public murat.simv2.simulation.mirror.net.minecraft.item.FuelRegistry fuelRegistry;
    public boolean hasJustExecutedTask;
    public com.mojang.authlib.GameProfile hostProfile;
    public int idleTickCount;
    public java.security.KeyPair keyPair;
    public long lastFullTickLogTime;
    public long lastOverloadWarningNanos;
    public long lastPlayerSampleUpdate;
    public boolean loading;
    public murat.simv2.simulation.mirror.net.minecraft.server.ServerMetadata metadata;
    public java.lang.String motd;
    public boolean needsDebugSetup;
    public boolean needsRecorderSetup;
    public java.lang.Object networkIo;
    public boolean onlineMode;
    public int playerIdleTimeout;
    public java.lang.Object playerManager;
    public boolean preventProxyConnections;
    public java.net.Proxy proxy;
    public boolean pvpEnabled;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random random;
    public long recentTickTimesNanos;
    public murat.simv2.simulation.mirror.net.minecraft.util.profiler.Recorder recorder;
    public java.util.function.Consumer<java.nio.file.Path> recorderDumpConsumer;
    public java.util.function.Consumer<murat.simv2.simulation.mirror.net.minecraft.util.profiler.ProfileResult> recorderResultConsumer;
    public murat.simv2.simulation.mirror.net.minecraft.server.MinecraftServer.ResourceManagerHolder resourceManagerHolder;
    public boolean running;
    public java.lang.Object saveHandler;
    public java.lang.Object saveProperties;
    public boolean saving;
    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.ServerScoreboard scoreboard;
    public java.util.List<java.lang.Runnable> serverGuiTickables;
    public java.lang.String serverId;
    public java.lang.String serverIp;
    public int serverPort;
    public java.lang.Thread serverThread;
    public murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.Session session;
    public boolean stopped;
    public murat.simv2.simulation.mirror.net.minecraft.structure.StructureTemplateManager structureTemplateManager;
    public java.lang.Object suppressedExceptionsTracker;
    public long tasksStartTime;
    public long tickEndTimeNanos;
    public java.lang.Object tickManager;
    public long tickStartTimeNanos;
    public long[] tickTimes;
    public int ticks;
    public int ticksUntilAutosave;
    public long waitTime;
    public boolean waitingForNextTick;
    public java.util.concurrent.Executor workerExecutor;
    public java.lang.Object worldGenerationProgressListenerFactory;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<murat.simv2.simulation.mirror.net.minecraft.world.World>, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld> worlds;

    public MinecraftServer(java.lang.Thread p0, murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.Session p1, murat.simv2.simulation.mirror.net.minecraft.resource.ResourcePackManager p2, murat.simv2.simulation.mirror.net.minecraft.server.SaveLoader p3, java.net.Proxy p4, com.mojang.datafixers.DataFixer p5, murat.simv2.simulation.mirror.net.minecraft.util.ApiServices p6, java.lang.Object p7) {
    }

    public boolean acceptsStatusQuery() {
        return false;
    }

    public boolean acceptsTransfers() {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.SystemDetails addExtraSystemDetails(murat.simv2.simulation.mirror.net.minecraft.util.SystemDetails p0) {
        return null;
    }

    public void addServerGuiTickable(java.lang.Runnable p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.SystemDetails addSystemDetails(murat.simv2.simulation.mirror.net.minecraft.util.SystemDetails p0) {
        return null;
    }

    public int adjustTrackingDistance(int p0) {
        return 0;
    }

    public boolean areCommandBlocksEnabled() {
        return false;
    }

    public boolean canExecute(java.lang.Object p0) {
        return false;
    }

    public boolean canExecute(java.lang.Runnable p0) {
        return false;
    }

    public void cancelTasks() {
    }

    public boolean cannotBeSilenced() {
        return false;
    }

    public static boolean checkWorldGenException() {
        return false;
    }

    public void close() {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport createCrashReport(java.lang.Throwable p0) {
        return null;
    }

    public static java.lang.Object createDataPackSettings(murat.simv2.simulation.mirror.net.minecraft.resource.ResourcePackManager p0, boolean p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.filter.TextStream createFilterer(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.ServerMetadata.Players createMetadataPlayers() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.ServerMetadata createMetadata() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashException createMisplacementException(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p1) {
        return null;
    }

    public java.util.List createSamplers() {
        return null;
    }

    public java.lang.Runnable createTask(java.lang.Runnable p0) {
        return null;
    }

    public void createWorlds(murat.simv2.simulation.mirror.net.minecraft.server.WorldGenerationProgressListener p0) {
    }

    public void dumpClasspath(java.nio.file.Path p0) {
    }

    public void dumpGamerules(java.nio.file.Path p0) {
    }

    public void dumpNativeModules(java.nio.file.Path p0) {
    }

    public void dumpProperties(java.nio.file.Path p0) {
    }

    public void dumpStats(java.nio.file.Path p0) {
    }

    public void dumpThreads(java.nio.file.Path p0) {
    }

    public void dump(java.nio.file.Path p0) {
    }

    public void endTickMetrics() {
    }

    public java.util.concurrent.CompletableFuture executeAsync(java.util.function.Consumer p0) {
        return null;
    }

    public void executeSync(java.lang.Runnable p0) {
    }

    public void executeTask(java.lang.Object p0) {
    }

    public void executeTask(java.lang.Runnable p0) {
    }

    public void execute(java.lang.Runnable p0) {
    }

    public void exit() {
    }

    public static void forceEnableRequestedFeatures(murat.simv2.simulation.mirror.net.minecraft.resource.ResourcePackManager p0, murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet p1) {
    }

    public void forcePlayerSampleUpdate() {
    }

    public void forceStopRecorder() {
    }

    public void generateKeyPair() {
    }

    public java.lang.Object getAdvancementLoader() {
        return null;
    }

    public int getAutosaveInterval() {
        return 0;
    }

    public long getAverageNanosPerTick() {
        return 0L;
    }

    public float getAverageTickTime() {
        return 0.0F;
    }

    public java.lang.Object getBossBarManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.recipe.BrewingRecipeRegistry getBrewingRecipeRegistry() {
        return null;
    }

    public java.lang.Object getCombinedDynamicRegistries() {
        return null;
    }

    public java.lang.Object getCommandFunctionManager() {
        return null;
    }

    public java.lang.Object getCommandManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource getCommandSource() {
        return null;
    }

    public int getCurrentPlayerCount() {
        return 0;
    }

    public java.lang.Object getDataCommandStorage() {
        return null;
    }

    public com.mojang.datafixers.DataFixer getDataFixer() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.resource.ResourcePackManager getDataPackManager() {
        return null;
    }

    public java.lang.Object getDebugSampleLog() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.GameMode getDefaultGameMode() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.GameMode getForcedGameMode() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.FuelRegistry getFuelRegistry() {
        return null;
    }

    public int getFunctionPermissionLevel() {
        return 0;
    }

    public com.mojang.authlib.GameProfileRepository getGameProfileRepo() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.GameRules getGameRules() {
        return null;
    }

    public com.mojang.authlib.GameProfile getHostProfile() {
        return null;
    }

    public java.util.Optional getIconFile() {
        return null;
    }

    public java.security.KeyPair getKeyPair() {
        return null;
    }

    public int getMaxChainedNeighborUpdates() {
        return 0;
    }

    public int getMaxPlayerCount() {
        return 0;
    }

    public int getMaxWorldBorderRadius() {
        return 0;
    }

    public java.lang.Object getMessageDecorator() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ModStatus getModStatus() {
        return null;
    }

    public java.lang.String getName() {
        return null;
    }

    public int getNetworkCompressionThreshold() {
        return 0;
    }

    public java.lang.Object getNetworkIo() {
        return null;
    }

    public int getOpPermissionLevel() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld getOverworld() {
        return null;
    }

    public java.nio.file.Path getPath(java.lang.String p0) {
        return null;
    }

    public int getPauseWhenEmptySeconds() {
        return 0;
    }

    public int getPermissionLevel(com.mojang.authlib.GameProfile p0) {
        return 0;
    }

    public int getPlayerIdleTimeout() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerInteractionManager getPlayerInteractionManager(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
        return null;
    }

    public java.lang.Object getPlayerManager() {
        return null;
    }

    public java.lang.String[] getPlayerNames() {
        return null;
    }

    public java.net.Proxy getProxy() {
        return null;
    }

    public int getRateLimit() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.recipe.ServerRecipeManager getRecipeManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager.Immutable getRegistryManager() {
        return null;
    }

    public java.lang.Object getReloadableRegistries() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager getResourceManager() {
        return null;
    }

    public java.util.Optional getResourcePackProperties() {
        return null;
    }

    public java.nio.file.Path getRunDirectory() {
        return null;
    }

    public java.nio.file.Path getSavePath(java.lang.Object p0) {
        return null;
    }

    public java.lang.Object getSaveProperties() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.ServerScoreboard getScoreboard() {
        return null;
    }

    public java.lang.String getServerIp() {
        return null;
    }

    public java.lang.Object getServerLinks() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.ServerMetadata getServerMetadata() {
        return null;
    }

    public java.lang.String getServerModName() {
        return null;
    }

    public java.lang.String getServerMotd() {
        return null;
    }

    public int getServerPort() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.network.encryption.SignatureVerifier getServicesSignatureVerifier() {
        return null;
    }

    public com.mojang.authlib.minecraft.MinecraftSessionService getSessionService() {
        return null;
    }

    public int getSpawnProtectionRadius() {
        return 0;
    }

    public int getSpawnRadius(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.structure.StructureTemplateManager getStructureTemplateManager() {
        return null;
    }

    public int getTaskCount() {
        return 0;
    }

    public java.lang.Thread getThread() {
        return null;
    }

    public java.lang.Object getTickManager() {
        return null;
    }

    public long[] getTickTimes() {
        return null;
    }

    public int getTicks() {
        return 0;
    }

    public long getTimeReference() {
        return 0L;
    }

    public java.lang.Object getUserCache() {
        return null;
    }

    public java.lang.String getVersion() {
        return null;
    }

    public java.util.Set getWorldRegistryKeys() {
        return null;
    }

    public java.lang.Iterable getWorlds() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld getWorld(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
        return null;
    }

    public boolean hasGui() {
        return false;
    }

    public boolean hasRunningTasks() {
        return false;
    }

    public boolean hideOnlinePlayers() {
        return false;
    }

    public void initScoreboard(murat.simv2.simulation.mirror.net.minecraft.world.PersistentStateManager p0) {
    }

    public boolean isDebugRunning() {
        return false;
    }

    public boolean isDedicated() {
        return false;
    }

    public boolean isDemo() {
        return false;
    }

    public boolean isEnforceWhitelist() {
        return false;
    }

    public boolean isFlightEnabled() {
        return false;
    }

    public boolean isHardcore() {
        return false;
    }

    public boolean isHost(com.mojang.authlib.GameProfile p0) {
        return false;
    }

    public boolean isLoading() {
        return false;
    }

    public static boolean isMemoryError(java.lang.Throwable p0) {
        return false;
    }

    public boolean isMonsterSpawningEnabled() {
        return false;
    }

    public boolean isOnThread() {
        return false;
    }

    public boolean isOnlineMode() {
        return false;
    }

    public boolean isPaused() {
        return false;
    }

    public boolean isPvpEnabled() {
        return false;
    }

    public boolean isRecorderActive() {
        return false;
    }

    public boolean isRemote() {
        return false;
    }

    public boolean isRunning() {
        return false;
    }

    public boolean isSaving() {
        return false;
    }

    public boolean isSingleplayer() {
        return false;
    }

    public boolean isSpawnProtected(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p2) {
        return false;
    }

    public boolean isStopped() {
        return false;
    }

    public boolean isStopping() {
        return false;
    }

    public boolean isUsingNativeTransport() {
        return false;
    }

    public boolean isWorldAllowed(murat.simv2.simulation.mirror.net.minecraft.world.World p0) {
        return false;
    }

    public void kickNonWhitelistedPlayers(murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource p0) {
    }

    public static java.lang.Object loadDataPacks(murat.simv2.simulation.mirror.net.minecraft.resource.ResourcePackManager p0, java.lang.Object p1, boolean p2, boolean p3) {
        return null;
    }

    public static java.lang.Object loadDataPacks(murat.simv2.simulation.mirror.net.minecraft.resource.ResourcePackManager p0, java.util.Collection p1, murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet p2, boolean p3) {
        return null;
    }

    public java.util.Optional loadFavicon() {
        return null;
    }

    public void loadWorld() {
    }

    public void logChatMessage(murat.simv2.simulation.mirror.net.minecraft.text.Text p0, murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType.Parameters p1, java.lang.String p2) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.thread.TaskExecutor of(java.lang.String p0, java.util.concurrent.Executor p1) {
        return null;
    }

    public void onChunkLoadFailure(java.lang.Throwable p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p2) {
    }

    public void onChunkMisplacement(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p1, java.lang.Object p2) {
    }

    public void onChunkSaveFailure(java.lang.Throwable p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p2) {
    }

    public void onPacketException(java.lang.Throwable p0, java.lang.Object p1) {
    }

    public boolean openToLan(murat.simv2.simulation.mirror.net.minecraft.world.GameMode p0, boolean p1, int p2) {
        return false;
    }

    public void prepareStartRegion(murat.simv2.simulation.mirror.net.minecraft.server.WorldGenerationProgressListener p0) {
    }

    public void pushFullTickLog() {
    }

    public void pushPerformanceLogs() {
    }

    public void pushTickLog(long p0) {
    }

    public java.util.concurrent.CompletableFuture reloadResources(java.util.Collection p0) {
        return null;
    }

    public boolean requireResourcePack() {
        return false;
    }

    public void resetRecorder() {
    }

    public void runAutosave() {
    }

    public boolean runOneTask() {
        return false;
    }

    public void runServer() {
    }

    public void runTasksTillTickEnd() {
    }

    public void runTasks() {
    }

    public void runTasks(java.util.function.BooleanSupplier p0) {
    }

    public boolean runTask() {
        return false;
    }

    public boolean saveAll(boolean p0, boolean p1, boolean p2) {
        return false;
    }

    public boolean save(boolean p0, boolean p1, boolean p2) {
        return false;
    }

    public void sendDifficulty(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
    }

    public void sendMessage(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public void sendTimeUpdatePackets() {
    }

    public void sendTimeUpdatePackets(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public void send(java.lang.Runnable p0) {
    }

    public void setCrashReport(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport p0) {
    }

    public void setDefaultGameMode(murat.simv2.simulation.mirror.net.minecraft.world.GameMode p0) {
    }

    public void setDemo(boolean p0) {
    }

    public void setDifficultyLocked(boolean p0) {
    }

    public void setDifficulty(murat.simv2.simulation.mirror.net.minecraft.world.Difficulty p0, boolean p1) {
    }

    public void setEnforceWhitelist(boolean p0) {
    }

    public void setFlightEnabled(boolean p0) {
    }

    public void setHostProfile(com.mojang.authlib.GameProfile p0) {
    }

    public void setMotd(java.lang.String p0) {
    }

    public void setOnlineMode(boolean p0) {
    }

    public void setPlayerIdleTimeout(int p0) {
    }

    public void setPlayerManager(java.lang.Object p0) {
    }

    public void setPreventProxyConnections(boolean p0) {
    }

    public void setPvpEnabled(boolean p0) {
    }

    public void setServerId(java.lang.String p0) {
    }

    public void setServerIp(java.lang.String p0) {
    }

    public void setServerPort(int p0) {
    }

    public void setToDebugWorldProperties(java.lang.Object p0) {
    }

    public static void setWorldGenException(java.lang.RuntimeException p0) {
    }

    public void setupRecorder(java.util.function.Consumer p0, java.util.function.Consumer p1) {
    }

    public boolean setupServer() {
        return false;
    }

    public static void setupSpawn(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.world.level.ServerWorldProperties p1, boolean p2, boolean p3) {
    }

    public boolean shouldBroadcastConsoleToOps() {
        return false;
    }

    public boolean shouldBroadcastRconToOps() {
        return false;
    }

    public boolean shouldEnforceSecureProfile() {
        return false;
    }

    public boolean shouldExecuteAsync() {
        return false;
    }

    public boolean shouldKeepTicking() {
        return false;
    }

    public boolean shouldLogIps() {
        return false;
    }

    public boolean shouldPreventProxyConnections() {
        return false;
    }

    public boolean shouldPushTickTimeLog() {
        return false;
    }

    public boolean shouldReceiveFeedback() {
        return false;
    }

    public boolean shouldTrackOutput() {
        return false;
    }

    public void shutdown() {
    }

    public void startDebug() {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.server.MinecraftServer startServer(java.util.function.Function p0) {
        return null;
    }

    public void startTaskPerformanceLog() {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler startTickMetrics() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.profiler.ProfileResult stopDebug() {
        return null;
    }

    public void stopRecorder() {
    }

    public void stop(boolean p0) {
    }

    public void submitAndJoin(java.lang.Runnable p0) {
    }

    public java.util.concurrent.CompletableFuture submit(java.lang.Runnable p0) {
        return null;
    }

    public java.util.concurrent.CompletableFuture submit(java.util.function.Supplier p0) {
        return null;
    }

    public void subscribeToDebugSample(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0, java.lang.Object p1) {
    }

    public boolean syncChunkWrites() {
        return false;
    }

    public void tickNetworkIo() {
    }

    public void tickWorlds(java.util.function.BooleanSupplier p0) {
    }

    public void tick(java.util.function.BooleanSupplier p0) {
    }

    public void updateAutosaveTicks() {
    }

    public void updateDifficulty() {
    }

    public void updateMobSpawnOptions() {
    }

    public void waitForTasks() {
    }

    public void writeChunkIoReport(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport p0, murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p1, java.lang.Object p2) {
    }

    public MinecraftServer() {
    }

    public static class DebugStart extends java.lang.Object {
        public int tick;
        public long time;

        public DebugStart(long p0, int p1) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.profiler.ProfileResult end(long p0, int p1) {
            return null;
        }

        public DebugStart() {
        }

    }

    public static class ResourceManagerHolder {
        public java.lang.Object dataPackContents;
        public java.lang.Object resourceManager;

        public ResourceManagerHolder(java.lang.Object p0, java.lang.Object p1) {
        }

        public void close() {
        }

        public java.lang.Object dataPackContents() {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public java.lang.Object resourceManager() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public ResourceManagerHolder() {
        }

    }

    public static class ServerResourcePackProperties {
        public java.lang.String hash;
        public java.util.UUID id;
        public boolean isRequired;
        public murat.simv2.simulation.mirror.net.minecraft.text.Text prompt;
        public java.lang.String url;

        public ServerResourcePackProperties(java.util.UUID p0, java.lang.String p1, java.lang.String p2, boolean p3, murat.simv2.simulation.mirror.net.minecraft.text.Text p4) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public java.lang.String hash() {
            return null;
        }

        public java.util.UUID id() {
            return null;
        }

        public boolean isRequired() {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.text.Text prompt() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public java.lang.String url() {
            return null;
        }

        public ServerResourcePackProperties() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
