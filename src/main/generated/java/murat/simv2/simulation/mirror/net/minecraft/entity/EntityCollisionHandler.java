package murat.simv2.simulation.mirror.net.minecraft.entity;

// Generated mirror stub for simulation closure.
public interface EntityCollisionHandler {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final murat.simv2.simulation.mirror.net.minecraft.entity.EntityCollisionHandler DUMMY = null;

    public default void addEvent(java.lang.Object p0) {
    }

    public default void addPostCallback(java.lang.Object p0, java.util.function.Consumer p1) {
    }

    public default void addPreCallback(java.lang.Object p0, java.util.function.Consumer p1) {
    }

    public static class Impl implements murat.simv2.simulation.mirror.net.minecraft.entity.EntityCollisionHandler {
        public static java.lang.Object[] ALL_EVENTS;
        public static int INVALID_VERSION;
        public java.util.Set activeEvents;
        public java.util.List callbacks;
        public java.util.Map postCallbacks;
        public java.util.Map preCallbacks;
        public int version;

        public Impl() {
        }

        public void addEvent(java.lang.Object p0) {
        }

        public void addPostCallback(java.lang.Object p0, java.util.function.Consumer p1) {
        }

        public void addPreCallback(java.lang.Object p0, java.util.function.Consumer p1) {
        }

        public void runCallbacks(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        }

        public void updateIfNecessary(int p0) {
        }

        public void update() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
