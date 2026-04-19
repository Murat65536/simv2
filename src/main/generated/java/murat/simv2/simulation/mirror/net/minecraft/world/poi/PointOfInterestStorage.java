package murat.simv2.simulation.mirror.net.minecraft.world.poi;

// Generated mirror stub for simulation closure.
public class PointOfInterestStorage {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static org.slf4j.Logger LOGGER;
    public static int field_30265;
    public static int field_30266;
    public murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage.PointOfInterestDistanceTracker pointOfInterestDistanceTracker;
    public it.unimi.dsi.fastutil.longs.LongSet preloadedChunks;
    public murat.simv2.simulation.mirror.net.minecraft.world.HeightLimitView world;

    public PointOfInterestStorage(java.lang.Object p0, java.nio.file.Path p1, com.mojang.datafixers.DataFixer p2, boolean p3, murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager p4, java.lang.Object p5, murat.simv2.simulation.mirror.net.minecraft.world.HeightLimitView p6) {
    }

    public void add(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p1) {
    }

    public void close() {
    }

    public long count(java.util.function.Predicate p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, int p2, murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus p3) {
        return 0L;
    }

    public static int getDataVersion(com.mojang.serialization.Dynamic p0) {
        return 0;
    }

    public int getDistanceFromNearestOccupied(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkSectionPos p0) {
        return 0;
    }

    public int getFreeTickets(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return 0;
    }

    public java.util.Optional getIfLoaded(long p0) {
        return null;
    }

    public java.util.stream.Stream getInChunk(java.util.function.Predicate p0, murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p1, murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus p2) {
        return null;
    }

    public java.util.stream.Stream getInCircle(java.util.function.Predicate p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, int p2, murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus p3) {
        return null;
    }

    public java.util.stream.Stream getInSquare(java.util.function.Predicate p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, int p2, murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus p3) {
        return null;
    }

    public java.util.Optional getNearestPosition(java.util.function.Predicate p0, java.util.function.Predicate p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, int p3, murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus p4) {
        return null;
    }

    public java.util.Optional getNearestPosition(java.util.function.Predicate p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, int p2, murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus p3) {
        return null;
    }

    public java.util.Optional getNearestTypeAndPosition(java.util.function.Predicate p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, int p2, murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus p3) {
        return null;
    }

    public java.lang.Object getOrCreate(long p0) {
        return null;
    }

    public java.util.stream.Stream getPositions(java.util.function.Predicate p0, java.util.function.Predicate p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, int p3, murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus p4) {
        return null;
    }

    public java.util.Optional getPosition(java.util.function.Predicate p0, java.util.function.BiPredicate p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, int p3) {
        return null;
    }

    public java.util.Optional getPosition(java.util.function.Predicate p0, java.util.function.Predicate p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, int p3, murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus p4) {
        return null;
    }

    public java.util.Optional getPosition(java.util.function.Predicate p0, java.util.function.Predicate p1, murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3, int p4, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p5) {
        return null;
    }

    public java.util.stream.Stream getSortedTypesAndPositions(java.util.function.Predicate p0, java.util.function.Predicate p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, int p3, murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus p4) {
        return null;
    }

    public java.util.stream.Stream getTypesAndPositions(java.util.function.Predicate p0, java.util.function.Predicate p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, int p3, murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus p4) {
        return null;
    }

    public java.util.Optional getType(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public java.util.Optional get(long p0) {
        return null;
    }

    public boolean hasTypeAt(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public boolean hasUnsavedElements() {
        return false;
    }

    public void initForPalette(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkSectionPos p0, java.lang.Object p1) {
    }

    public boolean isOccupied(long p0) {
        return false;
    }

    public boolean isPosInvalid(long p0) {
        return false;
    }

    public java.util.concurrent.CompletableFuture load(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0) {
        return null;
    }

    public void onLoad(long p0) {
    }

    public void onUpdate(long p0) {
    }

    public void preloadChunks(murat.simv2.simulation.mirror.net.minecraft.world.WorldView p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, int p2) {
    }

    public boolean releaseTicket(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public void remove(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public void saveChunk(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0) {
    }

    public void save() {
    }

    public void scanAndPopulate(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkSectionPos p1, java.util.function.BiConsumer p2) {
    }

    public static boolean shouldScan(java.lang.Object p0) {
        return false;
    }

    public boolean test(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.util.function.Predicate p1) {
        return false;
    }

    public void tick(java.util.function.BooleanSupplier p0) {
    }

    public PointOfInterestStorage() {
    }

    public static class OccupationStatus {
        public static murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus ANY;
        public static murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus HAS_SPACE;
        public static murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus IS_OCCUPIED;
        public static murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus[] field_18491;
        public java.util.function.Predicate<? super java.lang.Object> predicate;

        public OccupationStatus(java.lang.String p0, int p1, java.util.function.Predicate p2) {
        }

        public java.util.function.Predicate getPredicate() {
            return null;
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus[] values() {
            return null;
        }

        public OccupationStatus() {
        }

    }

    public static class PointOfInterestDistanceTracker {
        public it.unimi.dsi.fastutil.longs.Long2ByteMap distances;
        public murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage field_18485;
        public static long field_43397;
        public int levelCount;

        public PointOfInterestDistanceTracker(murat.simv2.simulation.mirror.net.minecraft.world.poi.PointOfInterestStorage p0) {
        }

        public int applyPendingUpdates(int p0) {
            return 0;
        }

        public int getInitialLevel(long p0) {
            return 0;
        }

        public int getLevel(long p0) {
            return 0;
        }

        public int getPendingUpdateCount() {
            return 0;
        }

        public int getPropagatedLevel(long p0, long p1, int p2) {
            return 0;
        }

        public boolean hasPendingUpdates() {
            return false;
        }

        public boolean isMarker(long p0) {
            return false;
        }

        public void propagateLevel(long p0, int p1, boolean p2) {
        }

        public void propagateLevel(long p0, long p1, int p2, boolean p3) {
        }

        public int recalculateLevel(long p0, long p1, int p2) {
            return 0;
        }

        public void removePendingUpdateIf(java.util.function.LongPredicate p0) {
        }

        public void removePendingUpdate(long p0) {
        }

        public void resetLevel(long p0) {
        }

        public void setLevel(long p0, int p1) {
        }

        public void updateLevel(long p0, long p1, int p2, boolean p3) {
        }

        public void update() {
        }

        public void update(long p0, int p1, boolean p2) {
        }

        public PointOfInterestDistanceTracker() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
