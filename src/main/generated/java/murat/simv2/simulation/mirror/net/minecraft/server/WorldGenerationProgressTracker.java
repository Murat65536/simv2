package murat.simv2.simulation.mirror.net.minecraft.server;

// Generated mirror stub for simulation closure.
public class WorldGenerationProgressTracker extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.server.WorldGenerationProgressListener {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public int centerSize;
    public it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap<murat.simv2.simulation.mirror.net.minecraft.world.chunk.ChunkStatus> chunkStatuses;
    public java.lang.Object progressLogger;
    public int radius;
    public boolean running;
    public int size;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos spawnPos;

    public WorldGenerationProgressTracker(java.lang.Object p0, int p1, int p2, int p3) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.server.WorldGenerationProgressTracker create(int p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.server.WorldGenerationProgressTracker forSpawnChunks(int p0) {
        return null;
    }

    public int getCenterSize() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.chunk.ChunkStatus getChunkStatus(int p0, int p1) {
        return null;
    }

    public int getProgressPercentage() {
        return 0;
    }

    public int getSize() {
        return 0;
    }

    public static int getStartRegionSize(int p0) {
        return 0;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.server.WorldGenerationProgressTracker noSpawnChunks() {
        return null;
    }

    public void setChunkStatus(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0, murat.simv2.simulation.mirror.net.minecraft.world.chunk.ChunkStatus p1) {
    }

    public void start() {
    }

    public void start(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0) {
    }

    public void stop() {
    }

    public WorldGenerationProgressTracker() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
