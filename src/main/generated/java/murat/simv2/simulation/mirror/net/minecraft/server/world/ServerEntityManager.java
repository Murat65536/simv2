package murat.simv2.simulation.mirror.net.minecraft.server.world;

// Generated mirror stub for simulation closure.
public class ServerEntityManager<T> extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static org.slf4j.Logger LOGGER;
    public java.lang.Object cache;
    public java.lang.Object dataAccess;
    public java.util.Set<java.util.UUID> entityUuids;
    public java.lang.Object handler;
    public java.lang.Object index;
    public java.util.Queue<java.lang.Object> loadingQueue;
    public murat.simv2.simulation.mirror.net.minecraft.world.entity.EntityLookup<java.lang.Object> lookup;
    public it.unimi.dsi.fastutil.longs.Long2ObjectMap<murat.simv2.simulation.mirror.net.minecraft.server.world.ServerEntityManager.Status> managedStatuses;
    public it.unimi.dsi.fastutil.longs.LongSet pendingUnloads;
    public it.unimi.dsi.fastutil.longs.Long2ObjectMap<java.lang.Object> trackingStatuses;

    public ServerEntityManager(java.lang.Class p0, java.lang.Object p1, java.lang.Object p2) {
    }

    public void addEntities(java.util.stream.Stream p0) {
    }

    public boolean addEntityUuid(murat.simv2.simulation.mirror.net.minecraft.world.entity.EntityLike p0) {
        return false;
    }

    public boolean addEntity(murat.simv2.simulation.mirror.net.minecraft.world.entity.EntityLike p0) {
        return false;
    }

    public boolean addEntity(murat.simv2.simulation.mirror.net.minecraft.world.entity.EntityLike p0, boolean p1) {
        return false;
    }

    public void close() {
    }

    public void dump(java.io.Writer p0) {
    }

    public void entityLeftSection(long p0, java.lang.Object p1) {
    }

    public void flush() {
    }

    public java.lang.String getDebugString() {
        return null;
    }

    public int getIndexSize() {
        return 0;
    }

    public it.unimi.dsi.fastutil.longs.LongSet getLoadedChunks() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.entity.EntityLookup getLookup() {
        return null;
    }

    public static java.lang.Object getNeededLoadStatus(murat.simv2.simulation.mirror.net.minecraft.world.entity.EntityLike p0, java.lang.Object p1) {
        return null;
    }

    public boolean has(java.util.UUID p0) {
        return false;
    }

    public boolean isLoaded(long p0) {
        return false;
    }

    public void loadChunks() {
    }

    public void loadEntities(java.util.stream.Stream p0) {
    }

    public void readIfFresh(long p0) {
    }

    public void save() {
    }

    public void scheduleRead(long p0) {
    }

    public boolean shouldTickTest(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0) {
        return false;
    }

    public boolean shouldTick(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean shouldTick(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0) {
        return false;
    }

    public void startTicking(murat.simv2.simulation.mirror.net.minecraft.world.entity.EntityLike p0) {
    }

    public void startTracking(murat.simv2.simulation.mirror.net.minecraft.world.entity.EntityLike p0) {
    }

    public void stopTicking(murat.simv2.simulation.mirror.net.minecraft.world.entity.EntityLike p0) {
    }

    public void stopTracking(murat.simv2.simulation.mirror.net.minecraft.world.entity.EntityLike p0) {
    }

    public void tick() {
    }

    public boolean trySave(long p0, java.util.function.Consumer p1) {
        return false;
    }

    public void unloadChunks() {
    }

    public boolean unload(long p0) {
        return false;
    }

    public void unload(murat.simv2.simulation.mirror.net.minecraft.world.entity.EntityLike p0) {
    }

    public void updateTrackingStatus(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0, java.lang.Object p1) {
    }

    public ServerEntityManager() {
    }

    public static class Listener extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.world.entity.EntityChangeListener {
        public java.lang.Object entity;
        public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerEntityManager manager;
        public java.lang.Object section;
        public long sectionPos;

        public Listener(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerEntityManager p0, murat.simv2.simulation.mirror.net.minecraft.world.entity.EntityLike p1, long p2, java.lang.Object p3) {
        }

        public void remove(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p0) {
        }

        public void updateEntityPosition() {
        }

        public void updateLoadStatus(java.lang.Object p0, java.lang.Object p1) {
        }

        public Listener() {
        }

    }

    public static class Status {
        public static murat.simv2.simulation.mirror.net.minecraft.server.world.ServerEntityManager.Status FRESH;
        public static murat.simv2.simulation.mirror.net.minecraft.server.world.ServerEntityManager.Status LOADED;
        public static murat.simv2.simulation.mirror.net.minecraft.server.world.ServerEntityManager.Status PENDING;
        public static murat.simv2.simulation.mirror.net.minecraft.server.world.ServerEntityManager.Status[] field_27278;

        public Status(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.server.world.ServerEntityManager.Status valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.server.world.ServerEntityManager.Status[] values() {
            return null;
        }

        public Status() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
