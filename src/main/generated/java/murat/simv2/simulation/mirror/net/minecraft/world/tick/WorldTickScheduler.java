package murat.simv2.simulation.mirror.net.minecraft.world.tick;

// Generated mirror stub for simulation closure.
public class WorldTickScheduler<T> extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.world.tick.QueryableTickScheduler {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.util.Comparator<java.lang.Object> COMPARATOR;
    public it.unimi.dsi.fastutil.longs.Long2ObjectMap<java.lang.Object> chunkTickSchedulers;
    public java.util.Set<murat.simv2.simulation.mirror.net.minecraft.world.tick.OrderedTick<?>> copiedTickableTicksList;
    public it.unimi.dsi.fastutil.longs.Long2LongMap nextTriggerTickByChunkPos;
    public java.util.function.BiConsumer<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.world.tick.OrderedTick<java.lang.Object>> queuedTickConsumer;
    public java.util.Queue<java.lang.Object> tickableChunkTickSchedulers;
    public java.util.Queue<murat.simv2.simulation.mirror.net.minecraft.world.tick.OrderedTick<java.lang.Object>> tickableTicks;
    public java.util.List<murat.simv2.simulation.mirror.net.minecraft.world.tick.OrderedTick<java.lang.Object>> tickedTicks;
    public java.util.function.LongPredicate tickingFutureReadyPredicate;

    public WorldTickScheduler(java.util.function.LongPredicate p0) {
    }

    public void addChunkTickScheduler(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0, java.lang.Object p1) {
    }

    public void addTickableTicks(java.util.Queue p0, java.lang.Object p1, long p2, int p3) {
    }

    public void addTickableTicks(long p0, int p1) {
    }

    public void addTickableTick(murat.simv2.simulation.mirror.net.minecraft.world.tick.OrderedTick p0) {
    }

    public void clearNextTicks(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockBox p0) {
    }

    public void clear() {
    }

    public void collectTickableChunkTickSchedulers(long p0) {
    }

    public void collectTickableTicks(long p0, int p1, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p2) {
    }

    public void copyTickableTicksList() {
    }

    public void delayAllTicks() {
    }

    public int getTickCount() {
        return 0;
    }

    public boolean isQueued(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.lang.Object p1) {
        return false;
    }

    public boolean isTickableTicksCountUnder(int p0) {
        return false;
    }

    public boolean isTicking(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.lang.Object p1) {
        return false;
    }

    public void removeChunkTickScheduler(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0) {
    }

    public void scheduleTicks(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockBox p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p1) {
    }

    public void scheduleTicks(murat.simv2.simulation.mirror.net.minecraft.world.tick.WorldTickScheduler p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockBox p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i p2) {
    }

    public void scheduleTick(murat.simv2.simulation.mirror.net.minecraft.world.tick.OrderedTick p0) {
    }

    public void schedule(murat.simv2.simulation.mirror.net.minecraft.world.tick.OrderedTick p0) {
    }

    public void tick(java.util.function.BiConsumer p0) {
    }

    public void tick(long p0, int p1, java.util.function.BiConsumer p2) {
    }

    public void visitChunks(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockBox p0, murat.simv2.simulation.mirror.net.minecraft.world.tick.WorldTickScheduler.ChunkVisitor p1) {
    }

    public WorldTickScheduler() {
    }

    public static interface ChunkVisitor<T> {
        public void accept(long p0, java.lang.Object p1);

    }

    // END GENERATED MIRROR NESTED STUBS
}
