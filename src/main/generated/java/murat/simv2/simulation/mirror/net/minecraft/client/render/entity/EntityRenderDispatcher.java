package murat.simv2.simulation.mirror.net.minecraft.client.render.entity;

// Generated mirror stub for simulation closure.
public class EntityRenderDispatcher extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.lang.Object SHADOW_LAYER;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.block.BlockRenderManager blockRenderManager;
    public java.lang.Object camera;
    public java.util.function.Supplier<murat.simv2.simulation.mirror.net.minecraft.client.render.entity.model.LoadedEntityModels> entityModelsGetter;
    public java.lang.Object equipmentModelLoader;
    public static float field_43377;
    public static float field_43378;
    public murat.simv2.simulation.mirror.net.minecraft.client.option.GameOptions gameOptions;
    public java.lang.Object heldItemRenderer;
    public murat.simv2.simulation.mirror.net.minecraft.client.item.ItemModelManager itemModelManager;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.MapRenderer mapRenderer;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.client.util.SkinTextures.Model, java.lang.Object> modelRenderers;
    public boolean renderHitboxes;
    public boolean renderShadows;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.entity.EntityType<?>, java.lang.Object> renderers;
    public org.joml.Quaternionf rotation;
    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity targetedEntity;
    public murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer textRenderer;
    public murat.simv2.simulation.mirror.net.minecraft.client.texture.TextureManager textureManager;
    public murat.simv2.simulation.mirror.net.minecraft.world.World world;

    public EntityRenderDispatcher(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p0, murat.simv2.simulation.mirror.net.minecraft.client.texture.TextureManager p1, murat.simv2.simulation.mirror.net.minecraft.client.item.ItemModelManager p2, murat.simv2.simulation.mirror.net.minecraft.client.render.item.ItemRenderer p3, murat.simv2.simulation.mirror.net.minecraft.client.render.MapRenderer p4, murat.simv2.simulation.mirror.net.minecraft.client.render.block.BlockRenderManager p5, murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer p6, murat.simv2.simulation.mirror.net.minecraft.client.option.GameOptions p7, java.util.function.Supplier p8, java.lang.Object p9) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection addRendererDetails(double p0, double p1, double p2, java.lang.Object p3, murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport p4) {
        return null;
    }

    public void configure(murat.simv2.simulation.mirror.net.minecraft.world.World p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p2) {
    }

    public static void drawFireVertex(java.lang.Object p0, java.lang.Object p1, float p2, float p3, float p4, float p5, float p6) {
    }

    public static void drawShadowVertex(java.lang.Object p0, java.lang.Object p1, int p2, float p3, float p4, float p5, float p6, float p7) {
    }

    public java.lang.Object getHeldItemRenderer() {
        return null;
    }

    public int getLight(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, float p1) {
        return 0;
    }

    public java.lang.String getName() {
        return null;
    }

    public java.lang.Object getRenderer(java.lang.Object p0) {
        return null;
    }

    public java.lang.Object getRenderer(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return null;
    }

    public org.joml.Quaternionf getRotation() {
        return null;
    }

    public double getSquaredDistanceToCamera(double p0, double p1, double p2) {
        return 0.0D;
    }

    public double getSquaredDistanceToCamera(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return 0.0D;
    }

    public java.util.concurrent.CompletableFuture reload(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p1, java.util.concurrent.Executor p2, java.util.concurrent.Executor p3) {
        return null;
    }

    public void reload(murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p0) {
    }

    public void renderFire(java.lang.Object p0, java.lang.Object p1, java.lang.Object p2, org.joml.Quaternionf p3) {
    }

    public static void renderHitboxes(java.lang.Object p0, java.lang.Object p1, java.lang.Object p2, float p3) {
    }

    public void renderHitboxes(java.lang.Object p0, java.lang.Object p1, java.lang.Object p2, java.lang.Object p3) {
    }

    public static void renderHitbox(java.lang.Object p0, java.lang.Object p1, java.lang.Object p2) {
    }

    public static void renderShadowPart(java.lang.Object p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.world.chunk.Chunk p2, murat.simv2.simulation.mirror.net.minecraft.world.WorldView p3, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p4, double p5, double p6, double p7, float p8, float p9) {
    }

    public static void renderShadow(java.lang.Object p0, java.lang.Object p1, java.lang.Object p2, float p3, murat.simv2.simulation.mirror.net.minecraft.world.WorldView p4, float p5) {
    }

    public void render(java.lang.Object p0, double p1, double p2, double p3, java.lang.Object p4, java.lang.Object p5, int p6) {
    }

    public void render(java.lang.Object p0, double p1, double p2, double p3, java.lang.Object p4, java.lang.Object p5, int p6, java.lang.Object p7) {
    }

    public void render(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, double p1, double p2, double p3, float p4, java.lang.Object p5, java.lang.Object p6, int p7) {
    }

    public void render(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, double p1, double p2, double p3, float p4, java.lang.Object p5, java.lang.Object p6, int p7, java.lang.Object p8) {
    }

    public void setRenderHitboxes(boolean p0) {
    }

    public void setRenderShadows(boolean p0) {
    }

    public void setRotation(org.joml.Quaternionf p0) {
    }

    public void setWorld(murat.simv2.simulation.mirror.net.minecraft.world.World p0) {
    }

    public boolean shouldRenderHitboxes() {
        return false;
    }

    public boolean shouldRender(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, java.lang.Object p1, double p2, double p3, double p4) {
        return false;
    }

    public EntityRenderDispatcher() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
