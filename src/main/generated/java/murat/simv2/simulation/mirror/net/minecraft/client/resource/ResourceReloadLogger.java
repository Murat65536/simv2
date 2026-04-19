package murat.simv2.simulation.mirror.net.minecraft.client.resource;

// Generated mirror stub for simulation closure.
public class ResourceReloadLogger extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static org.slf4j.Logger LOGGER;
    public int reloadCount;
    public murat.simv2.simulation.mirror.net.minecraft.client.resource.ResourceReloadLogger.ReloadState reloadState;

    public ResourceReloadLogger() {
    }

    public void addReloadSection(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport p0) {
    }

    public void finish() {
    }

    public void recover(java.lang.Throwable p0) {
    }

    public void reload(murat.simv2.simulation.mirror.net.minecraft.client.resource.ResourceReloadLogger.ReloadReason p0, java.util.List p1) {
    }

    public static class RecoveryEntry extends java.lang.Object {
        public java.lang.Throwable throwable;

        public RecoveryEntry(java.lang.Throwable p0) {
        }

        public void addRecoverySection(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection p0) {
        }

        public RecoveryEntry() {
        }

    }

    public static class ReloadReason {
        public static murat.simv2.simulation.mirror.net.minecraft.client.resource.ResourceReloadLogger.ReloadReason INITIAL;
        public static murat.simv2.simulation.mirror.net.minecraft.client.resource.ResourceReloadLogger.ReloadReason MANUAL;
        public static murat.simv2.simulation.mirror.net.minecraft.client.resource.ResourceReloadLogger.ReloadReason UNKNOWN;
        public static murat.simv2.simulation.mirror.net.minecraft.client.resource.ResourceReloadLogger.ReloadReason[] field_33706;
        public java.lang.String name;

        public ReloadReason(java.lang.String p0, int p1, java.lang.String p2) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.resource.ResourceReloadLogger.ReloadReason valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.resource.ResourceReloadLogger.ReloadReason[] values() {
            return null;
        }

        public ReloadReason() {
        }

    }

    public static class ReloadState extends java.lang.Object {
        public boolean finished;
        public java.util.List<java.lang.String> packs;
        public murat.simv2.simulation.mirror.net.minecraft.client.resource.ResourceReloadLogger.ReloadReason reason;
        public murat.simv2.simulation.mirror.net.minecraft.client.resource.ResourceReloadLogger.RecoveryEntry recovery;

        public ReloadState(murat.simv2.simulation.mirror.net.minecraft.client.resource.ResourceReloadLogger.ReloadReason p0, java.util.List p1) {
        }

        public void addReloadSection(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection p0) {
        }

        public ReloadState() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
