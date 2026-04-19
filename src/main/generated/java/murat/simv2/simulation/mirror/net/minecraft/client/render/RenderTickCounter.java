package murat.simv2.simulation.mirror.net.minecraft.client.render;

// Generated mirror stub for simulation closure.
public interface RenderTickCounter {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter ONE = null;
    public static final murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter ZERO = null;

    public float getDynamicDeltaTicks();

    public float getFixedDeltaTicks();

    public float getTickProgress(boolean p0);

    public static class Constant extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter {
        public float value;

        public Constant(float p0) {
        }

        public float getDynamicDeltaTicks() {
            return 0.0F;
        }

        public float getFixedDeltaTicks() {
            return 0.0F;
        }

        public float getTickProgress(boolean p0) {
            return 0.0F;
        }

        public Constant() {
        }

    }

    public static class Dynamic extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.client.render.RenderTickCounter {
        public float dynamicDeltaTicks;
        public float fixedDeltaTicks;
        public long lastTimeMillis;
        public boolean paused;
        public it.unimi.dsi.fastutil.floats.FloatUnaryOperator targetMillisPerTick;
        public boolean tickFrozen;
        public float tickProgress;
        public float tickProgressBeforePause;
        public float tickTime;
        public long timeMillis;

        public Dynamic(float p0, long p1, it.unimi.dsi.fastutil.floats.FloatUnaryOperator p2) {
        }

        public int beginRenderTick(long p0) {
            return 0;
        }

        public int beginRenderTick(long p0, boolean p1) {
            return 0;
        }

        public float getDynamicDeltaTicks() {
            return 0.0F;
        }

        public float getFixedDeltaTicks() {
            return 0.0F;
        }

        public float getTickProgress(boolean p0) {
            return 0.0F;
        }

        public void setTickFrozen(boolean p0) {
        }

        public void setTimeMillis(long p0) {
        }

        public void tickPaused() {
        }

        public void tickUnpaused() {
        }

        public void tick(boolean p0) {
        }

        public Dynamic() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
