package murat.simv2.simulation.mirror.net.minecraft.client.render;

// Generated mirror stub for simulation closure.
public class GameRenderer extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier BLUR_ID;
    public static float CAMERA_DEPTH;
    public static org.slf4j.Logger LOGGER;
    public boolean blockOutlineEnabled;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.BufferBuilderStorage buffers;
    public java.lang.Object camera;
    public murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient client;
    public static int field_32687;
    public static float field_44940;
    public static int field_49904;
    public static float field_55869;
    public static float field_55870;
    public java.lang.Object firstPersonRenderer;
    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack floatingItem;
    public float floatingItemHeight;
    public int floatingItemTimeLeft;
    public float floatingItemWidth;
    public float fovMultiplier;
    public boolean hasWorldIcon;
    public float lastFovMultiplier;
    public float lastSkyDarkness;
    public long lastWindowFocusedTime;
    public long lastWorldIconUpdate;
    public java.lang.Object lightmapTextureManager;
    public float nauseaEffectSpeed;
    public float nauseaEffectTime;
    public java.lang.Object overlayTexture;
    public java.lang.Object pool;
    public boolean postProcessorEnabled;
    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier postProcessorId;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random random;
    public boolean renderHand;
    public boolean renderingPanorama;
    public murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager resourceManager;
    public float skyDarkness;
    public float viewDistanceBlocks;
    public float zoom;
    public float zoomX;
    public float zoomY;

    public GameRenderer(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p2, murat.simv2.simulation.mirror.net.minecraft.client.render.BufferBuilderStorage p3) {
    }

    public void bobView(java.lang.Object p0, float p1) {
    }

    public void clearPostProcessor() {
    }

    public void close() {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.hit.HitResult ensureTargetInRange(murat.simv2.simulation.mirror.net.minecraft.util.hit.HitResult p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, double p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.hit.HitResult findCrosshairTarget(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, double p1, double p2, float p3) {
        return null;
    }

    public org.joml.Matrix4f getBasicProjectionMatrix(float p0) {
        return null;
    }

    public java.lang.Object getCamera() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient getClient() {
        return null;
    }

    public float getFarPlaneDistance() {
        return 0.0F;
    }

    public float getFov(java.lang.Object p0, float p1, boolean p2) {
        return 0.0F;
    }

    public java.lang.Object getLightmapTextureManager() {
        return null;
    }

    public static float getNightVisionStrength(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, float p1) {
        return 0.0F;
    }

    public java.lang.Object getOverlayTexture() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier getPostProcessorId() {
        return null;
    }

    public float getSkyDarkness(float p0) {
        return 0.0F;
    }

    public float getViewDistanceBlocks() {
        return 0.0F;
    }

    public boolean isRenderingPanorama() {
        return false;
    }

    public void onCameraEntitySet(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void onResized(int p0, int p1) {
    }

    public void preloadPrograms(java.lang.Object p0) {
    }

    public void renderBlur() {
    }

    public void renderFloatingItem(java.lang.Object p0, float p1) {
    }

    public void renderHand(java.lang.Object p0, float p1, org.joml.Matrix4f p2) {
    }

    public void renderWithZoom(float p0, float p1, float p2) {
    }

    public void renderWorld(murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter p0) {
    }

    public void render(murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter p0, boolean p1) {
    }

    public void reset() {
    }

    public void setBlockOutlineEnabled(boolean p0) {
    }

    public void setPostProcessor(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
    }

    public void setRenderHand(boolean p0) {
    }

    public void setRenderingPanorama(boolean p0) {
    }

    public boolean shouldRenderBlockOutline() {
        return false;
    }

    public void showFloatingItem(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public void tick() {
    }

    public void tiltViewWhenHurt(java.lang.Object p0, float p1) {
    }

    public void togglePostProcessorEnabled() {
    }

    public void updateCrosshairTarget(float p0) {
    }

    public void updateFovMultiplier() {
    }

    public void updateWorldIcon() {
    }

    public void updateWorldIcon(java.nio.file.Path p0) {
    }

    public GameRenderer() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
