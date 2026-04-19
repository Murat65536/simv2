package murat.simv2.simulation.mirror.net.minecraft.client.resource;

// Generated mirror stub for simulation closure.
public class PeriodicNotificationManager {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<java.util.Map<java.lang.String, java.util.List<murat.simv2.simulation.mirror.net.minecraft.client.resource.PeriodicNotificationManager.Entry>>> CODEC;
    public static org.slf4j.Logger LOGGER;
    public it.unimi.dsi.fastutil.objects.Object2BooleanFunction<java.lang.String> countryPredicate;
    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier id;
    public murat.simv2.simulation.mirror.net.minecraft.client.resource.PeriodicNotificationManager.NotifyTask task;
    public java.util.Timer timer;

    public PeriodicNotificationManager(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, it.unimi.dsi.fastutil.objects.Object2BooleanFunction p1) {
    }

    public void apply(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p1, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p2) {
    }

    public void apply(java.util.Map p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p1, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p2) {
    }

    public void cancelTimer() {
    }

    public void close() {
    }

    public long getMinDelay(java.util.List p0) {
        return 0L;
    }

    public java.lang.String getName() {
        return null;
    }

    public long getPeriod(java.util.List p0, long p1) {
        return 0L;
    }

    public java.lang.Object prepare(murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p0, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p1) {
        return null;
    }

    public java.util.concurrent.CompletableFuture reload(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p1, java.util.concurrent.Executor p2, java.util.concurrent.Executor p3) {
        return null;
    }

    public PeriodicNotificationManager() {
    }

    public static class Entry {
        public long delay;
        public java.lang.String message;
        public long period;
        public java.lang.String title;

        public Entry(long p0, long p1, java.lang.String p2, java.lang.String p3) {
        }

        public long delay() {
            return 0L;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public java.lang.String message() {
            return null;
        }

        public long period() {
            return 0L;
        }

        public java.lang.String title() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public Entry() {
        }

    }

    public static class NotifyTask extends java.util.TimerTask {
        public murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient client;
        public java.util.concurrent.atomic.AtomicLong delayMs;
        public java.util.List<murat.simv2.simulation.mirror.net.minecraft.client.resource.PeriodicNotificationManager.Entry> entries;
        public long periodMs;

        public NotifyTask(java.util.List p0, long p1, long p2) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.client.resource.PeriodicNotificationManager.NotifyTask reload(java.util.List p0, long p1) {
            return null;
        }

        public void run() {
        }

        public NotifyTask() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
