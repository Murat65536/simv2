package murat.simv2.simulation.mirror.net.minecraft.client.toast;

// Generated mirror stub for simulation closure.
public class ToastManager extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static int SPACES;
    public murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient client;
    public static int field_52786;
    public java.util.BitSet occupiedSpaces;
    public java.util.Set<murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent> queuedToastSounds;
    public java.util.Deque<java.lang.Object> toastQueue;
    public java.util.List<murat.simv2.simulation.mirror.net.minecraft.client.toast.ToastManager.Entry<?>> visibleEntries;

    public ToastManager(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p0) {
    }

    public void add(java.lang.Object p0) {
    }

    public void clear() {
    }

    public void draw(java.lang.Object p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient getClient() {
        return null;
    }

    public int getEmptySpaceCount() {
        return 0;
    }

    public double getNotificationDisplayTimeMultiplier() {
        return 0.0D;
    }

    public java.lang.Object getToast(java.lang.Class p0, java.lang.Object p1) {
        return null;
    }

    public int getTopIndex(int p0) {
        return 0;
    }

    public void update() {
    }

    public ToastManager() {
    }

    public static class Entry<T> extends java.lang.Object {
        public static long DISAPPEAR_TIME;
        public murat.simv2.simulation.mirror.net.minecraft.client.toast.ToastManager field_2245;
        public boolean finishedRendering;
        public long fullyVisibleTime;
        public java.lang.Object instance;
        public int requiredSpaceCount;
        public long showTime;
        public long startTime;
        public int topIndex;
        public java.lang.Object visibility;
        public float visibleWidthPortion;

        public Entry(murat.simv2.simulation.mirror.net.minecraft.client.toast.ToastManager p0, java.lang.Object p1, int p2, int p3) {
        }

        public void draw(java.lang.Object p0, int p1) {
        }

        public java.lang.Object getInstance() {
            return null;
        }

        public boolean isFinishedRendering() {
            return false;
        }

        public void updateVisibleWidthPortion(long p0) {
        }

        public void update() {
        }

        public Entry() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
