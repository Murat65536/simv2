package murat.simv2.simulation.mirror.net.minecraft.client.util;

// Generated mirror stub for simulation closure.
public class Window extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static org.slf4j.Logger LOGGER;
    public boolean currentFullscreen;
    public org.lwjgl.glfw.GLFWErrorCallback errorCallback;
    public murat.simv2.simulation.mirror.net.minecraft.client.WindowEventHandler eventHandler;
    public static int field_52250;
    public static int field_52251;
    public int framebufferHeight;
    public int framebufferWidth;
    public boolean fullscreen;
    public java.util.Optional<java.lang.Object> fullscreenVideoMode;
    public boolean fullscreenVideoModeDirty;
    public long handle;
    public int height;
    public boolean minimized;
    public java.lang.Object monitorTracker;
    public java.lang.String phase;
    public double scaleFactor;
    public int scaledHeight;
    public int scaledWidth;
    public boolean vsync;
    public int width;
    public int windowedHeight;
    public int windowedWidth;
    public int windowedX;
    public int windowedY;
    public int x;
    public int y;
    public boolean zeroWidthOrHeight;

    public Window(murat.simv2.simulation.mirror.net.minecraft.client.WindowEventHandler p0, java.lang.Object p1, java.lang.Object p2, java.lang.String p3, java.lang.String p4) {
    }

    public static void acceptError(java.util.function.BiConsumer p0) {
    }

    public void applyFullscreenVideoMode() {
    }

    public int calculateScaleFactor(int p0, boolean p1) {
        return 0;
    }

    public void close() {
    }

    public int getFramebufferHeight() {
        return 0;
    }

    public int getFramebufferWidth() {
        return 0;
    }

    public java.util.Optional getFullscreenVideoMode() {
        return null;
    }

    public static java.lang.String getGlfwPlatform() {
        return null;
    }

    public long getHandle() {
        return 0L;
    }

    public int getHeight() {
        return 0;
    }

    public java.lang.Object getMonitor() {
        return null;
    }

    public int getRefreshRate() {
        return 0;
    }

    public double getScaleFactor() {
        return 0.0D;
    }

    public int getScaledHeight() {
        return 0;
    }

    public int getScaledWidth() {
        return 0;
    }

    public int getWidth() {
        return 0;
    }

    public int getX() {
        return 0;
    }

    public int getY() {
        return 0;
    }

    public boolean hasZeroWidthOrHeight() {
        return false;
    }

    public boolean isFullscreen() {
        return false;
    }

    public boolean isMinimized() {
        return false;
    }

    public void logGlError(int p0, long p1) {
    }

    public void logOnGlError() {
    }

    public void onCursorEnterChanged(long p0, boolean p1) {
    }

    public void onFramebufferSizeChanged(long p0, int p1, int p2) {
    }

    public void onMinimizeChanged(long p0, boolean p1) {
    }

    public void onWindowFocusChanged(long p0, boolean p1) {
    }

    public void onWindowPosChanged(long p0, int p1, int p2) {
    }

    public void onWindowSizeChanged(long p0, int p1, int p2) {
    }

    public void setCloseCallback(java.lang.Runnable p0) {
    }

    public void setFramebufferHeight(int p0) {
    }

    public void setFramebufferWidth(int p0) {
    }

    public void setFullscreenVideoMode(java.util.Optional p0) {
    }

    public void setIcon(java.lang.Object p0, java.lang.Object p1) {
    }

    public void setPhase(java.lang.String p0) {
    }

    public void setRawMouseMotion(boolean p0) {
    }

    public void setScaleFactor(double p0) {
    }

    public void setTitle(java.lang.String p0) {
    }

    public void setVsync(boolean p0) {
    }

    public void setWindowedSize(int p0, int p1) {
    }

    public boolean shouldClose() {
        return false;
    }

    public void swapBuffers(murat.simv2.simulation.mirror.net.minecraft.client.util.tracy.TracyFrameCapturer p0) {
    }

    public static void throwGlError(int p0, long p1) {
    }

    public void throwOnGlError() {
    }

    public void toggleFullscreen() {
    }

    public void updateFramebufferSize() {
    }

    public void updateFullscreen(boolean p0, murat.simv2.simulation.mirror.net.minecraft.client.util.tracy.TracyFrameCapturer p1) {
    }

    public void updateWindowRegion() {
    }

    public Window() {
    }

    public static class GlErroredException {
        public GlErroredException(java.lang.String p0) {
        }

        public GlErroredException() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
