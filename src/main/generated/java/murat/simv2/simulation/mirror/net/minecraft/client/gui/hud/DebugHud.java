package murat.simv2.simulation.mirror.net.minecraft.client.gui.hud;

// Generated mirror stub for simulation closure.
public class DebugHud extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.util.Map<murat.simv2.simulation.mirror.net.minecraft.world.Heightmap.Type, java.lang.String> HEIGHT_MAP_TYPES;
    public static int TEXT_COLOR;
    public murat.simv2.simulation.mirror.net.minecraft.client.gui.hud.DebugHud.AllocationRateCalculator allocationRateCalculator;
    public murat.simv2.simulation.mirror.net.minecraft.util.hit.HitResult blockHit;
    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk chunk;
    public java.util.concurrent.CompletableFuture<murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk> chunkFuture;
    public murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient client;
    public com.mojang.blaze3d.buffers.GpuBuffer debugCrosshairBuffer;
    public com.mojang.blaze3d.systems.RenderSystem.ShapeIndexBuffer debugCrosshairIndexBuffer;
    public static int field_32188;
    public static int field_32189;
    public static int field_32190;
    public static float field_56577;
    public static int field_57920;
    public murat.simv2.simulation.mirror.net.minecraft.util.hit.HitResult fluidHit;
    public java.lang.Object frameNanosLog;
    public boolean packetSizeAndPingChartsVisible;
    public java.lang.Object packetSizeChart;
    public java.lang.Object packetSizeLog;
    public java.lang.Object pieChart;
    public java.lang.Object pingChart;
    public java.lang.Object pingLog;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos pos;
    public java.util.Map<java.lang.Object, java.lang.Object> receivedDebugSamples;
    public boolean renderingAndTickChartsVisible;
    public java.lang.Object renderingChart;
    public boolean renderingChartVisible;
    public boolean showDebugHud;
    public murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer textRenderer;
    public java.lang.Object tickChart;
    public java.lang.Object tickNanosLog;

    public DebugHud(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p0) {
    }

    public void clear() {
    }

    public void drawLeftText(java.lang.Object p0) {
    }

    public void drawRightText(java.lang.Object p0) {
    }

    public void drawText(java.lang.Object p0, java.util.List p1, boolean p2) {
    }

    public static java.lang.String getBiomeString(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk getChunk() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.WorldChunk getClientChunk() {
        return null;
    }

    public java.util.List getLeftText() {
        return null;
    }

    public java.lang.Object getPacketSizeLog() {
        return null;
    }

    public java.lang.Object getPieChart() {
        return null;
    }

    public java.lang.Object getPingLog() {
        return null;
    }

    public java.util.List getRightText() {
        return null;
    }

    public java.lang.String getServerWorldDebugString() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld getServerWorld() {
        return null;
    }

    public java.lang.Object getTickNanosLog() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.World getWorld() {
        return null;
    }

    public java.lang.String propertyToString(java.util.Map.Entry p0) {
        return null;
    }

    public void pushToFrameLog(long p0) {
    }

    public void renderDebugCrosshair() {
    }

    public void render(java.lang.Object p0) {
    }

    public void resetChunk() {
    }

    public void set(long[] p0, java.lang.Object p1) {
    }

    public boolean shouldRenderTickCharts() {
        return false;
    }

    public boolean shouldShowDebugHud() {
        return false;
    }

    public boolean shouldShowPacketSizeAndPingCharts() {
        return false;
    }

    public boolean shouldShowRenderingChart() {
        return false;
    }

    public static long toMiB(long p0) {
        return 0L;
    }

    public void toggleDebugHud() {
    }

    public void togglePacketSizeAndPingCharts() {
    }

    public void toggleRenderingAndTickCharts() {
    }

    public void toggleRenderingChart() {
    }

    public DebugHud() {
    }

    public static class AllocationRateCalculator extends java.lang.Object {
        public static java.util.List<java.lang.management.GarbageCollectorMXBean> GARBAGE_COLLECTORS;
        public static int INTERVAL;
        public long allocatedBytes;
        public long allocationRate;
        public long collectionCount;
        public long lastCalculated;

        public AllocationRateCalculator() {
        }

        public static long getCollectionCount() {
            return 0L;
        }

        public long get(long p0) {
            return 0L;
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
