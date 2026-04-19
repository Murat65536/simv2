package murat.simv2.simulation.mirror.net.minecraft.client.render.debug;

// Generated mirror stub for simulation closure.
public class DebugRenderer extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public java.lang.Object beeDebugRenderer;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.debug.DebugRenderer.Renderer blockOutlineDebugRenderer;
    public java.lang.Object breezeDebugRenderer;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.debug.DebugRenderer.Renderer chunkBorderDebugRenderer;
    public java.lang.Object chunkDebugRenderer;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.debug.DebugRenderer.Renderer chunkLoadingDebugRenderer;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.debug.DebugRenderer.Renderer collisionDebugRenderer;
    public java.lang.Object gameEventDebugRenderer;
    public java.lang.Object gameTestDebugRenderer;
    public java.lang.Object goalSelectorDebugRenderer;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.debug.DebugRenderer.Renderer heightmapDebugRenderer;
    public java.lang.Object lightDebugRenderer;
    public java.lang.Object neighborUpdateDebugRenderer;
    public java.lang.Object octreeDebugRenderer;
    public java.lang.Object pathfindingDebugRenderer;
    public java.lang.Object raidCenterDebugRenderer;
    public java.lang.Object redstoneUpdateOrderDebugRenderer;
    public boolean showChunkBorder;
    public boolean showOctree;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.debug.DebugRenderer.Renderer skyLightDebugRenderer;
    public java.lang.Object structureDebugRenderer;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.debug.DebugRenderer.Renderer supportingBlockDebugRenderer;
    public java.lang.Object villageDebugRenderer;
    public java.lang.Object villageSectionsDebugRenderer;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.debug.DebugRenderer.Renderer waterDebugRenderer;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.debug.DebugRenderer.Renderer worldGenAttemptDebugRenderer;

    public DebugRenderer(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p0) {
    }

    public static void drawBlockBox(java.lang.Object p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, float p3, float p4, float p5, float p6) {
    }

    public static void drawBox(java.lang.Object p0, java.lang.Object p1, double p2, double p3, double p4, double p5, double p6, double p7, float p8, float p9, float p10, float p11) {
    }

    public static void drawBox(java.lang.Object p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, float p3, float p4, float p5, float p6, float p7) {
    }

    public static void drawBox(java.lang.Object p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3, float p4, float p5, float p6, float p7) {
    }

    public static void drawBox(java.lang.Object p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p2, float p3, float p4, float p5, float p6) {
    }

    public static void drawString(java.lang.Object p0, java.lang.Object p1, java.lang.String p2, double p3, double p4, double p5, int p6) {
    }

    public static void drawString(java.lang.Object p0, java.lang.Object p1, java.lang.String p2, double p3, double p4, double p5, int p6, float p7) {
    }

    public static void drawString(java.lang.Object p0, java.lang.Object p1, java.lang.String p2, double p3, double p4, double p5, int p6, float p7, boolean p8, float p9, boolean p10) {
    }

    public static void drawString(java.lang.Object p0, java.lang.Object p1, java.lang.String p2, int p3, int p4, int p5, int p6) {
    }

    public static void drawVoxelShapeOutlines(java.lang.Object p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p2, double p3, double p4, double p5, float p6, float p7, float p8, float p9, boolean p10) {
    }

    public static java.util.Optional getTargetedEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, int p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d hueToRgb(float p0) {
        return null;
    }

    public void renderLate(java.lang.Object p0, java.lang.Object p1, double p2, double p3, double p4) {
    }

    public void render(java.lang.Object p0, java.lang.Object p1, java.lang.Object p2, double p3, double p4, double p5) {
    }

    public void reset() {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d shiftHue(float p0, float p1, float p2, float p3) {
        return null;
    }

    public boolean toggleShowChunkBorder() {
        return false;
    }

    public boolean toggleShowOctree() {
        return false;
    }

    public DebugRenderer() {
    }

    public static interface Renderer {
        public void clear();

        public void render(java.lang.Object p0, java.lang.Object p1, double p2, double p3, double p4);

    }

    // END GENERATED MIRROR NESTED STUBS
}
