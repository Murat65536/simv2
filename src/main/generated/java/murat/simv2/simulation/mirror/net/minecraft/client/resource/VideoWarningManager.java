package murat.simv2.simulation.mirror.net.minecraft.client.resource;

// Generated mirror stub for simulation closure.
public class VideoWarningManager {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier GPU_WARNLIST_ID;
    public static org.slf4j.Logger LOGGER;
    public boolean cancelledAfterWarning;
    public boolean warned;
    public boolean warningScheduled;
    public com.google.common.collect.ImmutableMap<java.lang.String, java.lang.String> warnings;

    public VideoWarningManager() {
    }

    public void acceptAfterWarnings() {
    }

    public void apply(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p1, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p2) {
    }

    public void apply(murat.simv2.simulation.mirror.net.minecraft.client.resource.VideoWarningManager.WarningPatternLoader p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p1, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p2) {
    }

    public boolean canWarn() {
        return false;
    }

    public void cancelAfterWarnings() {
    }

    public static void compilePatterns(com.google.gson.JsonArray p0, java.util.List p1) {
    }

    public java.lang.String getName() {
        return null;
    }

    public java.lang.String getRendererWarning() {
        return null;
    }

    public java.lang.String getVendorWarning() {
        return null;
    }

    public java.lang.String getVersionWarning() {
        return null;
    }

    public java.lang.String getWarningsAsString() {
        return null;
    }

    public boolean hasCancelledAfterWarning() {
        return false;
    }

    public boolean hasWarning() {
        return false;
    }

    public static com.google.gson.JsonObject loadWarnlist(murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p0, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p1) {
        return null;
    }

    public java.lang.Object prepare(murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p0, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p1) {
        return null;
    }

    public java.util.concurrent.CompletableFuture reload(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p1, java.util.concurrent.Executor p2, java.util.concurrent.Executor p3) {
        return null;
    }

    public void reset() {
    }

    public void scheduleWarning() {
    }

    public boolean shouldWarn() {
        return false;
    }

    public static class WarningPatternLoader extends java.lang.Object {
        public java.util.List<java.util.regex.Pattern> rendererPatterns;
        public java.util.List<java.util.regex.Pattern> vendorPatterns;
        public java.util.List<java.util.regex.Pattern> versionPatterns;

        public WarningPatternLoader(java.util.List p0, java.util.List p1, java.util.List p2) {
        }

        public com.google.common.collect.ImmutableMap buildWarnings() {
            return null;
        }

        public static java.lang.String buildWarning(java.util.List p0, java.lang.String p1) {
            return null;
        }

        public WarningPatternLoader() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
