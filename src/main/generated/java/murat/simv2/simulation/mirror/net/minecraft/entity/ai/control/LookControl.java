package murat.simv2.simulation.mirror.net.minecraft.entity.ai.control;

// Generated mirror stub for simulation closure.
public class LookControl extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public murat.simv2.simulation.mirror.net.minecraft.entity.mob.MobEntity entity;
    public int lookAtTimer;
    public float maxPitchChange;
    public float maxYawChange;
    public double x;
    public double y;
    public double z;

    public LookControl(murat.simv2.simulation.mirror.net.minecraft.entity.mob.MobEntity p0) {
    }

    public float changeAngle(float p0, float p1, float p2) {
        return 0.0F;
    }

    public void clampHeadYaw() {
    }

    public double getLookX() {
        return 0.0D;
    }

    public double getLookY() {
        return 0.0D;
    }

    public double getLookZ() {
        return 0.0D;
    }

    public static double getLookingHeightFor(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return 0.0D;
    }

    public java.util.Optional getTargetPitch() {
        return null;
    }

    public java.util.Optional getTargetYaw() {
        return null;
    }

    public boolean isLookingAtSpecificPosition() {
        return false;
    }

    public void lookAt(double p0, double p1, double p2) {
    }

    public void lookAt(double p0, double p1, double p2, float p3, float p4) {
    }

    public void lookAt(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void lookAt(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, float p1, float p2) {
    }

    public void lookAt(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
    }

    public boolean shouldStayHorizontal() {
        return false;
    }

    public void tick() {
    }

    public LookControl() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
