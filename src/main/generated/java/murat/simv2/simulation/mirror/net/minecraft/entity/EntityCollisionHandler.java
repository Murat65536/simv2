package murat.simv2.simulation.mirror.net.minecraft.entity;

// Generated mirror stub for simulation closure.
public interface EntityCollisionHandler {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final murat.simv2.simulation.mirror.net.minecraft.entity.EntityCollisionHandler DUMMY = null;

    public void addEvent(murat.simv2.simulation.mirror.net.minecraft.entity.CollisionEvent p0);

    public void addPostCallback(murat.simv2.simulation.mirror.net.minecraft.entity.CollisionEvent p0, java.util.function.Consumer p1);

    public void addPreCallback(murat.simv2.simulation.mirror.net.minecraft.entity.CollisionEvent p0, java.util.function.Consumer p1);

    public static class Impl extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.entity.EntityCollisionHandler {
        public static murat.simv2.simulation.mirror.net.minecraft.entity.CollisionEvent[] ALL_EVENTS;
        public static int INVALID_VERSION;
        public java.util.Set<murat.simv2.simulation.mirror.net.minecraft.entity.CollisionEvent> activeEvents;
        public java.util.List<java.util.function.Consumer<murat.simv2.simulation.mirror.net.minecraft.entity.Entity>> callbacks;
        public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.entity.CollisionEvent, java.util.List<java.util.function.Consumer<murat.simv2.simulation.mirror.net.minecraft.entity.Entity>>> postCallbacks;
        public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.entity.CollisionEvent, java.util.List<java.util.function.Consumer<murat.simv2.simulation.mirror.net.minecraft.entity.Entity>>> preCallbacks;
        public int version;

        public Impl() {
        }

        public void addEvent(murat.simv2.simulation.mirror.net.minecraft.entity.CollisionEvent p0) {
        }

        public void addPostCallback(murat.simv2.simulation.mirror.net.minecraft.entity.CollisionEvent p0, java.util.function.Consumer p1) {
        }

        public void addPreCallback(murat.simv2.simulation.mirror.net.minecraft.entity.CollisionEvent p0, java.util.function.Consumer p1) {
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
