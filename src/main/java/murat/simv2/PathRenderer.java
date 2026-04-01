package murat.simv2;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.VertexRendering;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.Collections;
import java.util.List;

public class PathRenderer {

    private static final float BOX_HALF = 0.05f;

    private static List<Vec3d> path = Collections.emptyList();

    public static void setPath(List<Vec3d> newPath) {
        path = List.copyOf(newPath);
    }

    public static void clearPath() {
        path = Collections.emptyList();
    }

    public static void register() {
        WorldRenderEvents.BEFORE_DEBUG_RENDER.register(PathRenderer::render);
    }

    private static void render(WorldRenderContext context) {
        List<Vec3d> currentPath = path;
        if (currentPath.isEmpty()) return;

        MatrixStack matrices = context.matrixStack();
        if (matrices == null) return;

        Vec3d cam = context.camera().getPos();

        try (BufferAllocator allocator = new BufferAllocator(1024)) {
            VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate(allocator);

            // Draw small filled box at each tick position
            for (int i = 0; i < currentPath.size(); i++) {
                Vec3d pos = currentPath.get(i);
                double x = pos.x - cam.x;
                double y = pos.y - cam.y;
                double z = pos.z - cam.z;

                // Color gradient: green (start) -> red (end)
                float t = currentPath.size() == 1 ? 0f : (float) i / (currentPath.size() - 1);
                float r = t;
                float g = 1f - t;
                float b = 0.2f;

                DebugRenderer.drawBox(
                    matrices, immediate,
                    new Box(
                        x - BOX_HALF, y, z - BOX_HALF,
                        x + BOX_HALF, y + BOX_HALF * 2, z + BOX_HALF
                    ),
                    r, g, b, 0.6f
                );
            }

            // Draw lines connecting consecutive positions
            VertexConsumer lineBuffer = immediate.getBuffer(RenderLayer.getLines());
            for (int i = 0; i < currentPath.size() - 1; i++) {
                Vec3d from = currentPath.get(i);
                Vec3d to = currentPath.get(i + 1);

                float fx = (float) (from.x - cam.x);
                float fy = (float) (from.y - cam.y);
                float fz = (float) (from.z - cam.z);
                float tx = (float) (to.x - cam.x);
                float ty = (float) (to.y - cam.y);
                float tz = (float) (to.z - cam.z);

                // Normal for the line segment (direction vector, normalized)
                float dx = tx - fx;
                float dy = ty - fy;
                float dz = tz - fz;
                float len = (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
                if (len < 1e-6f) continue;
                float nx = dx / len;
                float ny = dy / len;
                float nz = dz / len;

                float t = currentPath.size() == 2 ? 0f : (float) i / (currentPath.size() - 2);
                int r = (int) (t * 255);
                int g2 = (int) ((1f - t) * 255);

                lineBuffer.vertex(fx, fy, fz).color(r, g2, 50, 200).normal(nx, ny, nz);
                lineBuffer.vertex(tx, ty, tz).color(r, g2, 50, 200).normal(nx, ny, nz);
            }

            immediate.draw();
        }
    }
}
