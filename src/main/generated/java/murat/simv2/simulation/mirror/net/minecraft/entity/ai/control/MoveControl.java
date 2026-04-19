package murat.simv2.simulation.mirror.net.minecraft.entity.ai.control;

// Generated mirror stub for simulation closure.
public class MoveControl extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static float REACHED_DESTINATION_DISTANCE_SQUARED;
    public murat.simv2.simulation.mirror.net.minecraft.entity.mob.MobEntity entity;
    public static float field_30197;
    public static int field_30199;
    public float forwardMovement;
    public float sidewaysMovement;
    public double speed;
    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.control.MoveControl.State state;
    public double targetX;
    public double targetY;
    public double targetZ;

    public MoveControl(murat.simv2.simulation.mirror.net.minecraft.entity.mob.MobEntity p0) {
    }

    public float changeAngle(float p0, float p1, float p2) {
        return 0.0F;
    }

    public double getSpeed() {
        return 0.0D;
    }

    public double getTargetX() {
        return 0.0D;
    }

    public double getTargetY() {
        return 0.0D;
    }

    public double getTargetZ() {
        return 0.0D;
    }

    public boolean isMoving() {
        return false;
    }

    public boolean isPosWalkable(float p0, float p1) {
        return false;
    }

    public void moveTo(double p0, double p1, double p2, double p3) {
    }

    public void strafeTo(float p0, float p1) {
    }

    public void tick() {
    }

    public float wrapDegrees(float p0, float p1, float p2) {
        return 0.0F;
    }

    public MoveControl() {
    }

    public static class State {
        public static murat.simv2.simulation.mirror.net.minecraft.entity.ai.control.MoveControl.State JUMPING;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.ai.control.MoveControl.State MOVE_TO;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.ai.control.MoveControl.State STRAFE;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.ai.control.MoveControl.State WAIT;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.ai.control.MoveControl.State[] field_6375;

        public State(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.ai.control.MoveControl.State valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.ai.control.MoveControl.State[] values() {
            return null;
        }

        public State() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
