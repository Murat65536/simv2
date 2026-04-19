package murat.simv2.simulation.mirror.net.minecraft.client.render;

// Generated mirror stub for simulation closure.
public class WorldRenderer extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier ENTITY_OUTLINE;
    public static org.slf4j.Logger LOGGER;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier TRANSPARENCY;
    public it.unimi.dsi.fastutil.ints.Int2ObjectMap<java.lang.Object> blockBreakingInfos;
    public it.unimi.dsi.fastutil.longs.Long2ObjectMap<java.util.SortedSet<java.lang.Object>> blockBreakingProgressions;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher blockEntityRenderDispatcher;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.BufferBuilderStorage bufferBuilders;
    public it.unimi.dsi.fastutil.objects.ObjectArrayList<java.lang.Object> builtChunks;
    public int cameraChunkX;
    public int cameraChunkY;
    public int cameraChunkZ;
    public java.lang.Object capturedFrustum;
    public java.lang.Object chunkBuilder;
    public int chunkIndex;
    public java.lang.Object chunkRenderingDataPreparer;
    public java.lang.Object chunks;
    public murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient client;
    public java.lang.Object cloudRenderer;
    public murat.simv2.simulation.mirror.net.minecraft.client.gl.Framebuffer entityOutlineFramebuffer;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.entity.EntityRenderDispatcher entityRenderDispatcher;
    public static int field_32759;
    public static int field_34812;
    public static int field_54162;
    public static int field_54163;
    public java.lang.Object framebufferSet;
    public java.lang.Object frustum;
    public double lastCameraPitch;
    public double lastCameraX;
    public double lastCameraY;
    public double lastCameraYaw;
    public double lastCameraZ;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos lastTranslucencySortCameraPos;
    public it.unimi.dsi.fastutil.objects.ObjectArrayList<java.lang.Object> nearbyChunks;
    public java.util.Set<murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity> noCullingBlockEntities;
    public java.util.List<murat.simv2.simulation.mirror.net.minecraft.entity.Entity> renderedEntities;
    public int renderedEntitiesCount;
    public boolean shouldCaptureFrustum;
    public java.lang.Object skyRendering;
    public int ticks;
    public int viewDistance;
    public java.lang.Object weatherRendering;
    public murat.simv2.simulation.mirror.net.minecraft.client.world.ClientWorld world;
    public java.lang.Object worldBorderRendering;

    public WorldRenderer(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p0, murat.simv2.simulation.mirror.net.minecraft.client.render.entity.EntityRenderDispatcher p1, murat.simv2.simulation.mirror.net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher p2, murat.simv2.simulation.mirror.net.minecraft.client.render.BufferBuilderStorage p3) {
    }

    public void addBuiltChunk(java.lang.Object p0) {
    }

    public void addParticle(murat.simv2.simulation.mirror.net.minecraft.particle.ParticleEffect p0, boolean p1, boolean p2, double p3, double p4, double p5, double p6, double p7, double p8) {
    }

    public void addParticle(murat.simv2.simulation.mirror.net.minecraft.particle.ParticleEffect p0, boolean p1, double p2, double p3, double p4, double p5, double p6, double p7) {
    }

    public void addParticle(murat.simv2.simulation.mirror.net.minecraft.particle.ParticleEffect p0, double p1, double p2, double p3, double p4, double p5, double p6) {
    }

    public void addWeatherParticlesAndSound(java.lang.Object p0) {
    }

    public void applyFrustum(java.lang.Object p0) {
    }

    public boolean canDrawEntityOutlines() {
        return false;
    }

    public void captureFrustum() {
    }

    public void checkEmpty(java.lang.Object p0) {
    }

    public void clear() {
    }

    public void close() {
    }

    public void drawBlockOutline(java.lang.Object p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p2, double p3, double p4, double p5, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p6, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p7, int p8) {
    }

    public void drawEntityOutlinesFramebuffer() {
    }

    public it.unimi.dsi.fastutil.objects.ObjectArrayList getBuiltChunks() {
        return null;
    }

    public java.lang.Object getCapturedFrustum() {
        return null;
    }

    public java.lang.Object getChunkBuilder() {
        return null;
    }

    public double getChunkCount() {
        return 0.0D;
    }

    public java.lang.Object getChunkRenderingDataPreparer() {
        return null;
    }

    public java.lang.String getChunksDebugString() {
        return null;
    }

    public java.lang.Object getCloudRenderer() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.gl.Framebuffer getCloudsFramebuffer() {
        return null;
    }

    public int getCompletedChunkCount() {
        return 0;
    }

    public java.lang.String getEntitiesDebugString() {
        return null;
    }

    public boolean getEntitiesToRender(java.lang.Object p0, java.lang.Object p1, java.util.List p2) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.gl.Framebuffer getEntityFramebuffer() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.gl.Framebuffer getEntityOutlinesFramebuffer() {
        return null;
    }

    public static int getLightmapCoordinates(murat.simv2.simulation.mirror.net.minecraft.client.render.WorldRenderer.BrightnessGetter p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockRenderView p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3) {
        return 0;
    }

    public static int getLightmapCoordinates(murat.simv2.simulation.mirror.net.minecraft.world.BlockRenderView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return 0;
    }

    public java.lang.String getName() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.gl.Framebuffer getParticlesFramebuffer() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.particle.ParticlesMode getRandomParticleSpawnChance(boolean p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.gl.Framebuffer getTranslucentFramebuffer() {
        return null;
    }

    public java.lang.Object getTransparencyPostEffectProcessor() {
        return null;
    }

    public double getViewDistance() {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.gl.Framebuffer getWeatherFramebuffer() {
        return null;
    }

    public boolean hasBlindnessOrDarkness(java.lang.Object p0) {
        return false;
    }

    public boolean isRenderingReady(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean isSkyDark(float p0) {
        return false;
    }

    public boolean isTerrainRenderComplete() {
        return false;
    }

    public void killFrustum() {
    }

    public void loadEntityOutlinePostProcessor() {
    }

    public static java.lang.Object offsetFrustum(java.lang.Object p0) {
        return null;
    }

    public void onChunkUnload(long p0) {
    }

    public void onResized(int p0, int p1) {
    }

    public void reload() {
    }

    public java.util.concurrent.CompletableFuture reload(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p1, java.util.concurrent.Executor p2, java.util.concurrent.Executor p3) {
        return null;
    }

    public void reload(murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p0) {
    }

    public void removeBlockBreakingInfo(java.lang.Object p0) {
    }

    public void renderBlockDamage(java.lang.Object p0, java.lang.Object p1, java.lang.Object p2) {
    }

    public void renderBlockEntities(java.lang.Object p0, java.lang.Object p1, java.lang.Object p2, java.lang.Object p3, float p4) {
    }

    public void renderClouds(java.lang.Object p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2, float p3, int p4, float p5) {
    }

    public void renderEntities(java.lang.Object p0, java.lang.Object p1, java.lang.Object p2, murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter p3, java.util.List p4) {
    }

    public void renderEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, double p1, double p2, double p3, float p4, java.lang.Object p5, java.lang.Object p6) {
    }

    public void renderLateDebug(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, java.lang.Object p2) {
    }

    public void renderLayer(java.lang.Object p0, double p1, double p2, double p3, org.joml.Matrix4f p4, org.joml.Matrix4f p5) {
    }

    public void renderMain(java.lang.Object p0, java.lang.Object p1, java.lang.Object p2, org.joml.Matrix4f p3, org.joml.Matrix4f p4, java.lang.Object p5, boolean p6, boolean p7, murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter p8, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p9) {
    }

    public void renderParticles(java.lang.Object p0, java.lang.Object p1, float p2, java.lang.Object p3) {
    }

    public void renderSky(java.lang.Object p0, java.lang.Object p1, float p2, java.lang.Object p3) {
    }

    public void renderTargetBlockOutline(java.lang.Object p0, java.lang.Object p1, java.lang.Object p2, boolean p3) {
    }

    public void renderWeather(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, float p2, java.lang.Object p3) {
    }

    public void render(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter p1, boolean p2, java.lang.Object p3, murat.simv2.simulation.mirror.net.minecraft.client.render.GameRenderer p4, org.joml.Matrix4f p5, org.joml.Matrix4f p6) {
    }

    public void scheduleBlockRenders(int p0, int p1, int p2, int p3, int p4, int p5) {
    }

    public void scheduleBlockRerenderIfNeeded(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2) {
    }

    public void scheduleChunkRenders3x3x3(int p0, int p1, int p2) {
    }

    public void scheduleChunkRenders(int p0, int p1, int p2, int p3, int p4, int p5) {
    }

    public void scheduleChunkRender(int p0, int p1, int p2) {
    }

    public void scheduleChunkRender(int p0, int p1, int p2, boolean p3) {
    }

    public void scheduleChunkTranslucencySort(java.lang.Object p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2, boolean p3, boolean p4) {
    }

    public void scheduleNeighborUpdates(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0) {
    }

    public void scheduleSectionRender(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, boolean p1) {
    }

    public void scheduleTerrainUpdate() {
    }

    public void setBlockBreakingInfo(int p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, int p2) {
    }

    public void setWorld(murat.simv2.simulation.mirror.net.minecraft.client.world.ClientWorld p0) {
    }

    public void setupFrustum(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, org.joml.Matrix4f p1, org.joml.Matrix4f p2) {
    }

    public void setupTerrain(java.lang.Object p0, java.lang.Object p1, boolean p2, boolean p3) {
    }

    public java.lang.Object spawnParticle(murat.simv2.simulation.mirror.net.minecraft.particle.ParticleEffect p0, boolean p1, boolean p2, double p3, double p4, double p5, double p6, double p7, double p8) {
        return null;
    }

    public java.lang.Object spawnParticle(murat.simv2.simulation.mirror.net.minecraft.particle.ParticleEffect p0, boolean p1, double p2, double p3, double p4, double p5, double p6, double p7) {
        return null;
    }

    public void tick() {
    }

    public void translucencySort(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
    }

    public void updateBlock(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3, int p4) {
    }

    public void updateChunks(java.lang.Object p0) {
    }

    public void updateNoCullingBlockEntities(java.util.Collection p0, java.util.Collection p1) {
    }

    public WorldRenderer() {
    }

    public static interface BrightnessGetter {
        public static final murat.simv2.simulation.mirror.net.minecraft.client.render.WorldRenderer.BrightnessGetter DEFAULT = null;

        public int packedBrightness(murat.simv2.simulation.mirror.net.minecraft.world.BlockRenderView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1);

    }

    // END GENERATED MIRROR NESTED STUBS
}
