package murat.simv2.simulation.mirror.net.minecraft.client.util.tracy;

// Generated mirror stub for simulation closure.
public class TracyFrameCapturer extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static int MAX_HEIGHT;
    public static int MAX_WIDTH;
    public com.mojang.blaze3d.buffers.GpuBuffer buffer;
    public boolean captured;
    public static int field_54254;
    public com.mojang.blaze3d.textures.GpuTexture framebuffer;
    public int framebufferHeight;
    public int framebufferWidth;
    public int height;
    public int offset;
    public murat.simv2.simulation.mirror.net.minecraft.client.util.tracy.TracyFrameCapturer.Status status;
    public int width;

    public TracyFrameCapturer() {
    }

    public void capture(murat.simv2.simulation.mirror.net.minecraft.client.gl.Framebuffer p0) {
    }

    public void close() {
    }

    public void markFrame() {
    }

    public void resize(int p0, int p1) {
    }

    public void upload() {
    }

    public static class Status {
        public static murat.simv2.simulation.mirror.net.minecraft.client.util.tracy.TracyFrameCapturer.Status WAITING_FOR_CAPTURE;
        public static murat.simv2.simulation.mirror.net.minecraft.client.util.tracy.TracyFrameCapturer.Status WAITING_FOR_COPY;
        public static murat.simv2.simulation.mirror.net.minecraft.client.util.tracy.TracyFrameCapturer.Status WAITING_FOR_UPLOAD;
        public static murat.simv2.simulation.mirror.net.minecraft.client.util.tracy.TracyFrameCapturer.Status[] field_57837;

        public Status(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.util.tracy.TracyFrameCapturer.Status valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.client.util.tracy.TracyFrameCapturer.Status[] values() {
            return null;
        }

        public Status() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
