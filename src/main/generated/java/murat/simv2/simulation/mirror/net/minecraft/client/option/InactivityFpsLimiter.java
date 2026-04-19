package murat.simv2.simulation.mirror.net.minecraft.client.option;

// Generated mirror stub for simulation closure.
public class InactivityFpsLimiter extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static int AFK_STAGE_1_FPS;
    public static long AFK_STAGE_1_THRESHOLD;
    public static int AFK_STAGE_2_FPS;
    public static long AFK_STAGE_2_THRESHOLD;
    public static int IN_GUI_FPS;
    public static int MINIMIZED_FPS;
    public murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient client;
    public long lastInputTime;
    public int maxFps;
    public murat.simv2.simulation.mirror.net.minecraft.client.option.GameOptions options;

    public InactivityFpsLimiter(murat.simv2.simulation.mirror.net.minecraft.client.option.GameOptions p0, murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p1) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.option.InactivityFpsLimiter.LimitReason getLimitReason() {
        return null;
    }

    public void onInput() {
    }

    public void setMaxFps(int p0) {
    }

    public boolean shouldDisableProfilerTimeout() {
        return false;
    }

    public int update() {
        return 0;
    }

    public InactivityFpsLimiter() {
    }

    public static class LimitReason {
        public static murat.simv2.simulation.mirror.net.minecraft.client.option.InactivityFpsLimiter.LimitReason LONG_AFK;
        public static murat.simv2.simulation.mirror.net.minecraft.client.option.InactivityFpsLimiter.LimitReason NONE;
        public static murat.simv2.simulation.mirror.net.minecraft.client.option.InactivityFpsLimiter.LimitReason OUT_OF_LEVEL_MENU;
        public static murat.simv2.simulation.mirror.net.minecraft.client.option.InactivityFpsLimiter.LimitReason SHORT_AFK;
        public static murat.simv2.simulation.mirror.net.minecraft.client.option.InactivityFpsLimiter.LimitReason WINDOW_ICONIFIED;
        public static murat.simv2.simulation.mirror.net.minecraft.client.option.InactivityFpsLimiter.LimitReason[] field_55848;

        public LimitReason(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.option.InactivityFpsLimiter.LimitReason valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.option.InactivityFpsLimiter.LimitReason[] values() {
            return null;
        }

        public LimitReason() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
