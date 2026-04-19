package murat.simv2.simulation.mirror.net.minecraft.entity;

// Generated mirror stub for simulation closure.
public class PositionInterpolator extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static int DEFAULT_INTERPOLATION_DURATION;
    public java.util.function.Consumer<murat.simv2.simulation.mirror.net.minecraft.entity.PositionInterpolator> callback;
    public murat.simv2.simulation.mirror.net.minecraft.entity.PositionInterpolator.Data data;
    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity entity;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d lastPos;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f lastRotation;
    public int lerpDuration;

    public PositionInterpolator(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public PositionInterpolator(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, int p1) {
    }

    public PositionInterpolator(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, int p1, java.util.function.Consumer p2) {
    }

    public PositionInterpolator(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, java.util.function.Consumer p1) {
    }

    public void clear() {
    }

    public float getLerpedPitch() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getLerpedPos() {
        return null;
    }

    public float getLerpedYaw() {
        return 0.0F;
    }

    public boolean isInterpolating() {
        return false;
    }

    public void refreshPositionAndAngles(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, float p1, float p2) {
    }

    public void setLerpDuration(int p0) {
    }

    public void tick() {
    }

    public PositionInterpolator() {
    }

    public static class Data extends java.lang.Object {
        public float pitch;
        public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d pos;
        public int step;
        public float yaw;

        public Data(int p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, float p2, float p3) {
        }

        public void addPos(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        }

        public void addRotation(float p0, float p1) {
        }

        public void tick() {
        }

        public Data() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
