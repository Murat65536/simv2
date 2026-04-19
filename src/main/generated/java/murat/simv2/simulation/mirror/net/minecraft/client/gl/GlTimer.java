package murat.simv2.simulation.mirror.net.minecraft.client.gl;

// Generated mirror stub for simulation closure.
public class GlTimer extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public int queryId;

    public GlTimer() {
    }

    public void beginProfile() {
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.gl.GlTimer.Query endProfile() {
        return null;
    }

    public static java.util.Optional getInstance() {
        return null;
    }

    public static class InstanceHolder extends java.lang.Object {
        public static java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.client.gl.GlTimer> INSTANCE;

        public InstanceHolder() {
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.gl.GlTimer create() {
            return null;
        }

    }

    public static class Query extends java.lang.Object {
        public static long CLOSED;
        public static long MISSING;
        public int queryId;
        public long result;

        public Query(int p0) {
        }

        public void close() {
        }

        public boolean isResultAvailable() {
            return false;
        }

        public long queryResult() {
            return 0L;
        }

        public Query() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
