package murat.simv2.simulation.mirror.net.minecraft.client;

// Generated mirror stub for simulation closure.
public class MinecraftClient extends murat.simv2.simulation.mirror.net.minecraft.util.thread.ReentrantThreadExecutor implements murat.simv2.simulation.mirror.net.minecraft.client.WindowEventHandler {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier ALT_TEXT_RENDERER_ID;
    public static java.util.concurrent.CompletableFuture<murat.simv2.simulation.mirror.net.minecraft.util.Unit> COMPLETED_UNIT_FUTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier DEFAULT_FONT_ID;
    public static java.lang.String GL_ERROR_DIALOGUE;
    public static boolean IS_SYSTEM_MAC;
    public static org.slf4j.Logger LOGGER;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier REGIONAL_COMPLIANCIES_ID;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text SOCIAL_INTERACTIONS_NOT_AVAILABLE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier UNICODE_FONT_ID;
    public long UNIVERSE;
    public murat.simv2.simulation.mirror.net.minecraft.client.session.report.AbuseReportContext abuseReportContext;
    public murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer advanceValidatingTextRenderer;
    public int attackCooldown;
    public com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService authenticationService;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.model.BakedModelManager bakedModelManager;
    public murat.simv2.simulation.mirror.net.minecraft.client.color.block.BlockColors blockColors;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher blockEntityRenderDispatcher;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.block.BlockRenderManager blockRenderManager;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.BufferBuilderStorage bufferBuilders;
    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity cameraEntity;
    public boolean chunkCullingEnabled;
    public murat.simv2.simulation.mirror.net.minecraft.client.util.CommandHistoryManager commandHistoryManager;
    public java.util.function.Supplier<murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport> crashReportSupplier;
    public murat.simv2.simulation.mirror.net.minecraft.client.option.HotbarStorage creativeHotbarStorage;
    public murat.simv2.simulation.mirror.net.minecraft.util.hit.HitResult crosshairTarget;
    public static int currentFps;
    public murat.simv2.simulation.mirror.net.minecraft.client.gl.GlTimer.Query currentGlTimerQuery;
    public murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.Screen currentScreen;
    public com.mojang.datafixers.DataFixer dataFixer;
    public boolean debugChunkInfo;
    public boolean debugChunkOcclusion;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.debug.DebugRenderer debugRenderer;
    public murat.simv2.simulation.mirror.net.minecraft.resource.DefaultResourcePack defaultResourcePack;
    public boolean disconnecting;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.entity.EntityRenderDispatcher entityRenderDispatcher;
    public static int field_32145;
    public static long field_52421;
    public boolean finishedLoading;
    public murat.simv2.simulation.mirror.net.minecraft.client.font.FontManager fontManager;
    public int fpsCounter;
    public java.lang.String fpsDebugString;
    public murat.simv2.simulation.mirror.net.minecraft.client.gl.Framebuffer framebuffer;
    public java.util.concurrent.CompletableFuture<com.mojang.authlib.yggdrasil.ProfileResult> gameProfileFuture;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.GameRenderer gameRenderer;
    public java.lang.String gameVersion;
    public double gpuUtilizationPercentage;
    public murat.simv2.simulation.mirror.net.minecraft.client.texture.GuiAtlasManager guiAtlasManager;
    public murat.simv2.simulation.mirror.net.minecraft.client.gui.hud.InGameHud inGameHud;
    public murat.simv2.simulation.mirror.net.minecraft.client.option.InactivityFpsLimiter inactivityFpsLimiter;
    public static murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient instance;
    public murat.simv2.simulation.mirror.net.minecraft.network.ClientConnection integratedServerConnection;
    public boolean integratedServerRunning;
    public murat.simv2.simulation.mirror.net.minecraft.client.network.ClientPlayerInteractionManager interactionManager;
    public boolean isDemo;
    public murat.simv2.simulation.mirror.net.minecraft.client.item.ItemModelManager itemModelManager;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.item.ItemRenderer itemRenderer;
    public int itemUseCooldown;
    public murat.simv2.simulation.mirror.net.minecraft.client.Keyboard keyboard;
    public murat.simv2.simulation.mirror.net.minecraft.client.resource.language.LanguageManager languageManager;
    public long lastMetricsSampleTime;
    public murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage levelStorage;
    public murat.simv2.simulation.mirror.net.minecraft.client.texture.MapDecorationsAtlasManager mapDecorationsAtlasManager;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.MapRenderer mapRenderer;
    public murat.simv2.simulation.mirror.net.minecraft.client.texture.MapTextureManager mapTextureManager;
    public murat.simv2.simulation.mirror.net.minecraft.client.network.message.MessageHandler messageHandler;
    public long metricsSampleDuration;
    public murat.simv2.simulation.mirror.net.minecraft.client.Mouse mouse;
    public boolean multiplayerEnabled;
    public murat.simv2.simulation.mirror.net.minecraft.client.sound.MusicTracker musicTracker;
    public murat.simv2.simulation.mirror.net.minecraft.client.util.NarratorManager narratorManager;
    public murat.simv2.simulation.mirror.net.minecraft.client.gui.navigation.GuiNavigationType navigationType;
    public java.net.Proxy networkProxy;
    public long nextDebugInfoUpdateTime;
    public boolean onlineChatEnabled;
    public murat.simv2.simulation.mirror.net.minecraft.client.option.GameOptions options;
    public murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.Overlay overlay;
    public murat.simv2.simulation.mirror.net.minecraft.client.texture.PaintingManager paintingManager;
    public murat.simv2.simulation.mirror.net.minecraft.client.particle.ParticleManager particleManager;
    public boolean paused;
    public murat.simv2.simulation.mirror.net.minecraft.client.network.ClientPlayerEntity player;
    public murat.simv2.simulation.mirror.net.minecraft.client.session.ProfileKeys profileKeys;
    public murat.simv2.simulation.mirror.net.minecraft.client.QuickPlayLogger quickPlayLogger;
    public murat.simv2.simulation.mirror.net.minecraft.client.realms.RealmsPeriodicCheckers realmsPeriodicCheckers;
    public murat.simv2.simulation.mirror.net.minecraft.util.profiler.Recorder recorder;
    public murat.simv2.simulation.mirror.net.minecraft.client.resource.PeriodicNotificationManager regionalComplianciesManager;
    public java.util.Queue<java.lang.Runnable> renderTaskQueue;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter.Dynamic renderTickCounter;
    public long renderTime;
    public murat.simv2.simulation.mirror.net.minecraft.resource.ReloadableResourceManagerImpl resourceManager;
    public java.nio.file.Path resourcePackDir;
    public murat.simv2.simulation.mirror.net.minecraft.resource.ResourcePackManager resourcePackManager;
    public java.util.concurrent.CompletableFuture<java.lang.Void> resourceReloadFuture;
    public murat.simv2.simulation.mirror.net.minecraft.client.resource.ResourceReloadLogger resourceReloadLogger;
    public java.io.File runDirectory;
    public boolean running;
    public murat.simv2.simulation.mirror.net.minecraft.server.integrated.IntegratedServer server;
    public murat.simv2.simulation.mirror.net.minecraft.client.resource.server.ServerResourcePackLoader serverResourcePackLoader;
    public murat.simv2.simulation.mirror.net.minecraft.client.session.Session session;
    public com.mojang.authlib.minecraft.MinecraftSessionService sessionService;
    public murat.simv2.simulation.mirror.net.minecraft.client.gl.ShaderLoader shaderLoader;
    public murat.simv2.simulation.mirror.net.minecraft.client.texture.PlayerSkinProvider skinProvider;
    public boolean skipGameRender;
    public murat.simv2.simulation.mirror.net.minecraft.client.network.SocialInteractionsManager socialInteractionsManager;
    public murat.simv2.simulation.mirror.net.minecraft.client.toast.TutorialToast socialInteractionsToast;
    public murat.simv2.simulation.mirror.net.minecraft.client.sound.SoundManager soundManager;
    public murat.simv2.simulation.mirror.net.minecraft.client.resource.SplashTextResourceSupplier splashTextLoader;
    public long startTime;
    public murat.simv2.simulation.mirror.net.minecraft.client.texture.StatusEffectSpriteManager statusEffectSpriteManager;
    public murat.simv2.simulation.mirror.net.minecraft.util.path.SymlinkFinder symlinkFinder;
    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity targetedEntity;
    public murat.simv2.simulation.mirror.net.minecraft.client.session.telemetry.TelemetryManager telemetryManager;
    public murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer textRenderer;
    public murat.simv2.simulation.mirror.net.minecraft.client.texture.TextureManager textureManager;
    public java.lang.Thread thread;
    public murat.simv2.simulation.mirror.net.minecraft.util.profiler.TickTimeTracker tickTimeTracker;
    public murat.simv2.simulation.mirror.net.minecraft.client.toast.ToastManager toastManager;
    public int trackingTick;
    public murat.simv2.simulation.mirror.net.minecraft.client.util.tracy.TracyFrameCapturer tracyFrameCapturer;
    public murat.simv2.simulation.mirror.net.minecraft.client.tutorial.TutorialManager tutorialManager;
    public long uptimeInTicks;
    public com.mojang.authlib.minecraft.UserApiService userApiService;
    public java.util.concurrent.CompletableFuture<com.mojang.authlib.minecraft.UserApiService.UserProperties> userPropertiesFuture;
    public java.lang.String versionType;
    public murat.simv2.simulation.mirror.net.minecraft.client.resource.VideoWarningManager videoWarningManager;
    public murat.simv2.simulation.mirror.net.minecraft.client.util.Window window;
    public boolean windowFocused;
    public murat.simv2.simulation.mirror.net.minecraft.client.util.WindowProvider windowProvider;
    public boolean wireFrame;
    public murat.simv2.simulation.mirror.net.minecraft.client.world.ClientWorld world;
    public java.util.concurrent.atomic.AtomicReference<murat.simv2.simulation.mirror.net.minecraft.server.WorldGenerationProgressTracker> worldGenProgressTracker;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.WorldRenderer worldRenderer;

    public MinecraftClient(murat.simv2.simulation.mirror.net.minecraft.client.RunArgs p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport addDetailsToCrashReport(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport p0) {
        return null;
    }

    public static void addSystemDetailsToCrashReport(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p0, murat.simv2.simulation.mirror.net.minecraft.client.resource.language.LanguageManager p1, java.lang.String p2, murat.simv2.simulation.mirror.net.minecraft.client.option.GameOptions p3, murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport p4) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.SystemDetails addSystemDetailsToCrashReport(murat.simv2.simulation.mirror.net.minecraft.util.SystemDetails p0, murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p1, murat.simv2.simulation.mirror.net.minecraft.client.resource.language.LanguageManager p2, java.lang.String p3, murat.simv2.simulation.mirror.net.minecraft.client.option.GameOptions p4) {
        return null;
    }

    public void addUptimesToCrashReport(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection p0) {
    }

    public boolean canExecute(java.lang.Runnable p0) {
        return false;
    }

    public void cancelTasks() {
    }

    public void checkGameData() {
    }

    public void cleanUpAfterCrash() {
    }

    public void close() {
    }

    public void collectLoadTimes(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient.LoadingContext p0) {
    }

    public void createInitScreens(java.util.List p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.integrated.IntegratedServerLoader createIntegratedServerLoader() {
        return null;
    }

    public java.util.List createSamplers() {
        return null;
    }

    public java.lang.Runnable createTask(java.lang.Runnable p0) {
        return null;
    }

    public com.mojang.authlib.minecraft.UserApiService createUserApiService(com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService p0, murat.simv2.simulation.mirror.net.minecraft.client.RunArgs p1) {
        return null;
    }

    public void disconnect() {
    }

    public void disconnect(murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.Screen p0) {
    }

    public void disconnect(murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.Screen p0, boolean p1) {
    }

    public boolean doAttack() {
        return false;
    }

    public void doItemPick() {
    }

    public void doItemUse() {
    }

    public void endMonitor(boolean p0, murat.simv2.simulation.mirror.net.minecraft.util.TickDurationMonitor p1) {
    }

    public void ensureAbuseReportContext(murat.simv2.simulation.mirror.net.minecraft.client.session.report.ReporterEnvironment p0) {
    }

    public void enterReconfiguration(murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.Screen p0) {
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

    public void forceStopRecorder() {
    }

    public boolean forcesUnicodeFont() {
        return false;
    }

    public static java.lang.String formatSeconds(double p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.session.report.AbuseReportContext getAbuseReportContext() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.render.model.BakedModelManager getBakedModelManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.color.block.BlockColors getBlockColors() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher getBlockEntityRenderDispatcher() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.render.block.BlockRenderManager getBlockRenderManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.render.BufferBuilderStorage getBufferBuilders() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getCameraEntity() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient.ChatRestriction getChatRestriction() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.util.CommandHistoryManager getCommandHistoryManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.option.HotbarStorage getCreativeHotbarStorage() {
        return null;
    }

    public int getCurrentFps() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.network.ServerInfo getCurrentServerEntry() {
        return null;
    }

    public com.mojang.datafixers.DataFixer getDataFixer() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.gui.hud.DebugHud getDebugHud() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.resource.DefaultResourcePack getDefaultResourcePack() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.render.entity.EntityRenderDispatcher getEntityRenderDispatcher() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.gl.Framebuffer getFramebuffer() {
        return null;
    }

    public com.mojang.authlib.GameProfile getGameProfile() {
        return null;
    }

    public java.lang.String getGameVersion() {
        return null;
    }

    public double getGpuUtilizationPercentage() {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.texture.GuiAtlasManager getGuiAtlasManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.option.InactivityFpsLimiter getInactivityFpsLimiter() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient getInstance() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.item.ItemModelManager getItemModelManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.render.item.ItemRenderer getItemRenderer() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.resource.language.LanguageManager getLanguageManager() {
        return null;
    }

    public static java.lang.String getLauncherBrand() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage getLevelStorage() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.render.entity.model.LoadedEntityModels getLoadedEntityModels() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.texture.MapDecorationsAtlasManager getMapDecorationsAtlasManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.render.MapRenderer getMapRenderer() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.texture.MapTextureManager getMapTextureManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.network.message.MessageHandler getMessageHandler() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.ModStatus getModStatus() {
        return null;
    }

    public com.mojang.authlib.minecraft.BanDetails getMultiplayerBanDetails() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.sound.MusicInstance getMusicInstance() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.sound.MusicTracker getMusicTracker() {
        return null;
    }

    public java.lang.String getName() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.util.NarratorManager getNarratorManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.gui.navigation.GuiNavigationType getNavigationType() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.network.ClientPlayNetworkHandler getNetworkHandler() {
        return null;
    }

    public java.net.Proxy getNetworkProxy() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.Overlay getOverlay() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.texture.PaintingManager getPaintingManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.session.ProfileKeys getProfileKeys() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.QuickPlayLogger getQuickPlayLogger() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.realms.RealmsPeriodicCheckers getRealmsPeriodicCheckers() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter getRenderTickCounter() {
        return null;
    }

    public long getRenderTime() {
        return 0L;
    }

    public murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager getResourceManager() {
        return null;
    }

    public java.nio.file.Path getResourcePackDir() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.resource.ResourcePackManager getResourcePackManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.resource.server.ServerResourcePackLoader getServerResourcePackProvider() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.integrated.IntegratedServer getServer() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.network.encryption.SignatureVerifier getServicesSignatureVerifier() {
        return null;
    }

    public com.mojang.authlib.minecraft.MinecraftSessionService getSessionService() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.session.Session getSession() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.gl.ShaderLoader getShaderLoader() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.texture.PlayerSkinProvider getSkinProvider() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.network.SocialInteractionsManager getSocialInteractionsManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.sound.SoundManager getSoundManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.resource.SplashTextResourceSupplier getSplashTextLoader() {
        return null;
    }

    public java.util.function.Function getSpriteAtlas(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.texture.StatusEffectSpriteManager getStatusEffectSpriteManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.path.SymlinkFinder getSymlinkFinder() {
        return null;
    }

    public float getTargetMillisPerTick(float p0) {
        return 0.0F;
    }

    public int getTaskCount() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.session.telemetry.TelemetryManager getTelemetryManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.texture.TextureManager getTextureManager() {
        return null;
    }

    public java.lang.Thread getThread() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.toast.ToastManager getToastManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.tutorial.TutorialManager getTutorialManager() {
        return null;
    }

    public com.mojang.authlib.minecraft.UserApiService.UserProperties getUserProperties() {
        return null;
    }

    public java.lang.String getVersionType() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.resource.VideoWarningManager getVideoWarningManager() {
        return null;
    }

    public java.lang.String getWindowTitle() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.util.Window getWindow() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.WorldGenerationProgressTracker getWorldGenerationProgressTracker() {
        return null;
    }

    public void handleBlockBreaking(boolean p0) {
    }

    public void handleGlErrorByDisableVsync(int p0, long p1) {
    }

    public void handleInputEvents() {
    }

    public void handleResourceReloadException(java.lang.Throwable p0, murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient.LoadingContext p1) {
    }

    public boolean hasOutline(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public boolean hasReducedDebugInfo() {
        return false;
    }

    public boolean hasRunningTasks() {
        return false;
    }

    public static boolean isAmbientOcclusionEnabled() {
        return false;
    }

    public boolean isConnectedToLocalServer() {
        return false;
    }

    public boolean isConnectedToServer() {
        return false;
    }

    public static boolean isCountrySetTo(java.lang.Object p0) {
        return false;
    }

    public boolean isDemo() {
        return false;
    }

    public static boolean isFabulousGraphicsOrBetter() {
        return false;
    }

    public static boolean isFancyGraphicsOrBetter() {
        return false;
    }

    public boolean isFinishedLoading() {
        return false;
    }

    public static boolean isHudEnabled() {
        return false;
    }

    public boolean isInSingleplayer() {
        return false;
    }

    public boolean isIntegratedServerRunning() {
        return false;
    }

    public static boolean isMemoryError(java.lang.Throwable p0) {
        return false;
    }

    public boolean isMultiplayerEnabled() {
        return false;
    }

    public boolean isOnThread() {
        return false;
    }

    public boolean isOptionalTelemetryEnabledByApi() {
        return false;
    }

    public boolean isOptionalTelemetryEnabled() {
        return false;
    }

    public boolean isPaused() {
        return false;
    }

    public boolean isRealmsEnabled() {
        return false;
    }

    public boolean isRunning() {
        return false;
    }

    public boolean isTelemetryEnabledByApi() {
        return false;
    }

    public boolean isUsernameBanned() {
        return false;
    }

    public boolean isWindowFocused() {
        return false;
    }

    public void joinWorld(murat.simv2.simulation.mirror.net.minecraft.client.world.ClientWorld p0, murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.DownloadingTerrainScreen.WorldEntryReason p1) {
    }

    public void loadBlockList() {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.thread.TaskExecutor of(java.lang.String p0, java.util.concurrent.Executor p1) {
        return null;
    }

    public void onCursorEnterChanged() {
    }

    public void onDisconnected() {
    }

    public void onFinishedLoading(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient.LoadingContext p0) {
    }

    public void onFontOptionsChanged() {
    }

    public void onForcedResourceReloadFailure() {
    }

    public java.lang.Runnable onInitFinished(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient.LoadingContext p0) {
        return null;
    }

    public void onResolutionChanged() {
    }

    public void onResourceReloadFailure(java.lang.Throwable p0, murat.simv2.simulation.mirror.net.minecraft.text.Text p1, murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient.LoadingContext p2) {
    }

    public void onShaderResourceReloadFailure(java.lang.Exception p0) {
    }

    public void onWindowFocusChanged(boolean p0) {
    }

    public void openChatScreen(java.lang.String p0) {
    }

    public void openGameMenu(boolean p0) {
    }

    public void printCrashReport() {
    }

    public static void printCrashReport(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p0, java.io.File p1, murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport p2) {
    }

    public void printCrashReport(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport p0) {
    }

    public boolean providesProfileKeys() {
        return false;
    }

    public java.util.concurrent.CompletableFuture reloadResourcesConcurrently() {
        return null;
    }

    public java.util.concurrent.CompletableFuture reloadResources() {
        return null;
    }

    public java.util.concurrent.CompletableFuture reloadResources(boolean p0, murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient.LoadingContext p1) {
        return null;
    }

    public void render(boolean p0) {
    }

    public void reset(murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.Screen p0) {
    }

    public void runTasks() {
    }

    public void runTasks(java.util.function.BooleanSupplier p0) {
    }

    public boolean runTask() {
        return false;
    }

    public void run() {
    }

    public static int saveCrashReport(java.io.File p0, murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport p1) {
        return 0;
    }

    public java.nio.file.Path saveProfilingResult(murat.simv2.simulation.mirror.net.minecraft.util.SystemDetails p0, java.util.List p1) {
        return null;
    }

    public void scheduleStop() {
    }

    public void send(java.lang.Runnable p0) {
    }

    public void setCameraEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void setCrashReportSupplierAndAddDetails(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport p0) {
    }

    public void setCrashReportSupplier(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport p0) {
    }

    public void setMipmapLevels(int p0) {
    }

    public void setNavigationType(murat.simv2.simulation.mirror.net.minecraft.client.gui.navigation.GuiNavigationType p0) {
    }

    public void setOverlay(murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.Overlay p0) {
    }

    public void setScreenAndRender(murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.Screen p0) {
    }

    public void setScreen(murat.simv2.simulation.mirror.net.minecraft.client.gui.screen.Screen p0) {
    }

    public void setWorld(murat.simv2.simulation.mirror.net.minecraft.client.world.ClientWorld p0) {
    }

    public boolean shouldBlockMessages(java.util.UUID p0) {
        return false;
    }

    public boolean shouldExecuteAsync() {
        return false;
    }

    public boolean shouldFilterText() {
        return false;
    }

    public boolean shouldTick() {
        return false;
    }

    public void showResourceReloadFailureToast(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public void startIntegratedServer(murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.Session p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourcePackManager p1, murat.simv2.simulation.mirror.net.minecraft.server.SaveLoader p2, boolean p3) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler startMonitor(boolean p0, murat.simv2.simulation.mirror.net.minecraft.util.TickDurationMonitor p1) {
        return null;
    }

    public void stopRecorder() {
    }

    public void stop() {
    }

    public void submitAndJoin(java.lang.Runnable p0) {
    }

    public java.util.concurrent.CompletableFuture submit(java.lang.Runnable p0) {
        return null;
    }

    public java.util.concurrent.CompletableFuture submit(java.util.function.Supplier p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text takePanorama(java.io.File p0, int p1, int p2) {
        return null;
    }

    public void tick() {
    }

    public boolean toggleDebugProfiler(java.util.function.Consumer p0) {
        return false;
    }

    public void updateWindowTitle() {
    }

    public boolean uuidEquals(java.util.UUID p0) {
        return false;
    }

    public void waitForTasks() {
    }

    public MinecraftClient() {
    }

    public static class ChatRestriction {
        public static murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient.ChatRestriction DISABLED_BY_LAUNCHER;
        public static murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient.ChatRestriction DISABLED_BY_OPTIONS;
        public static murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient.ChatRestriction DISABLED_BY_PROFILE;
        public static murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient.ChatRestriction ENABLED;
        public static murat.simv2.simulation.mirror.net.minecraft.text.Text MORE_INFO_TEXT;
        public murat.simv2.simulation.mirror.net.minecraft.text.Text description;
        public static murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient.ChatRestriction[] field_28945;

        public ChatRestriction(java.lang.String p0, int p1, murat.simv2.simulation.mirror.net.minecraft.text.Text p2) {
        }

        public boolean allowsChat(boolean p0) {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.text.Text getDescription() {
            return null;
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient.ChatRestriction valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient.ChatRestriction[] values() {
            return null;
        }

        public ChatRestriction() {
        }

    }

    public static class LoadingContext {
        public murat.simv2.simulation.mirror.net.minecraft.client.RunArgs.QuickPlay quickPlayData;
        public java.lang.Object realmsClient;

        public LoadingContext(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.client.RunArgs.QuickPlay p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.client.RunArgs.QuickPlay quickPlayData() {
            return null;
        }

        public java.lang.Object realmsClient() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public LoadingContext() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
