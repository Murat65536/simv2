package murat.simv2.simulation.mirror.net.minecraft.client;

// Generated mirror stub for simulation closure.
public class Mouse extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static org.slf4j.Logger LOGGER;
    public int activeButton;
    public murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient client;
    public int controlLeftClicks;
    public double cursorDeltaX;
    public double cursorDeltaY;
    public boolean cursorLocked;
    public java.lang.Object cursorXSmoother;
    public java.lang.Object cursorYSmoother;
    public int field_1796;
    public double glfwTime;
    public boolean hasResolutionChanged;
    public double lastTickTime;
    public boolean leftButtonClicked;
    public boolean middleButtonClicked;
    public boolean rightButtonClicked;
    public java.lang.Object scroller;
    public double x;
    public double y;

    public Mouse(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p0) {
    }

    public void addCrashReportSection(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection p0, murat.simv2.simulation.mirror.net.minecraft.client.util.Window p1) {
    }

    public void drawScaledPos(murat.simv2.simulation.mirror.net.minecraft.client.font.TextRenderer p0, java.lang.Object p1) {
    }

    public double getScaledX(murat.simv2.simulation.mirror.net.minecraft.client.util.Window p0) {
        return 0.0D;
    }

    public double getScaledY(murat.simv2.simulation.mirror.net.minecraft.client.util.Window p0) {
        return 0.0D;
    }

    public double getX() {
        return 0.0D;
    }

    public double getY() {
        return 0.0D;
    }

    public boolean isCursorLocked() {
        return false;
    }

    public void lockCursor() {
    }

    public void onCursorPos(long p0, double p1, double p2) {
    }

    public void onFilesDropped(long p0, java.util.List p1, int p2) {
    }

    public void onMouseButton(long p0, int p1, int p2, int p3) {
    }

    public void onMouseScroll(long p0, double p1, double p2) {
    }

    public void onResolutionChanged() {
    }

    public static double scaleX(murat.simv2.simulation.mirror.net.minecraft.client.util.Window p0, double p1) {
        return 0.0D;
    }

    public static double scaleY(murat.simv2.simulation.mirror.net.minecraft.client.util.Window p0, double p1) {
        return 0.0D;
    }

    public void setResolutionChanged() {
    }

    public void setup(long p0) {
    }

    public void tick() {
    }

    public void unlockCursor() {
    }

    public void updateMouse(double p0) {
    }

    public boolean wasLeftButtonClicked() {
        return false;
    }

    public boolean wasMiddleButtonClicked() {
        return false;
    }

    public boolean wasRightButtonClicked() {
        return false;
    }

    public Mouse() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
