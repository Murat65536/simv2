package murat.simv2.simulation.mirror.net.minecraft.entity.ai.goal;

// Generated mirror stub for simulation closure.
public class Goal extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public java.util.EnumSet<murat.simv2.simulation.mirror.net.minecraft.entity.ai.goal.Goal.Control> controls;

    public Goal() {
    }

    public boolean canStart() {
        return false;
    }

    public boolean canStop() {
        return false;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld castToServerWorld(murat.simv2.simulation.mirror.net.minecraft.world.World p0) {
        return null;
    }

    public java.util.EnumSet getControls() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld getServerWorld(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return null;
    }

    public int getTickCount(int p0) {
        return 0;
    }

    public void setControls(java.util.EnumSet p0) {
    }

    public boolean shouldContinue() {
        return false;
    }

    public boolean shouldRunEveryTick() {
        return false;
    }

    public void start() {
    }

    public void stop() {
    }

    public void tick() {
    }

    public static int toGoalTicks(int p0) {
        return 0;
    }

    public java.lang.String toString() {
        return null;
    }

    public static class Control {
        public static murat.simv2.simulation.mirror.net.minecraft.entity.ai.goal.Goal.Control JUMP;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.ai.goal.Goal.Control LOOK;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.ai.goal.Goal.Control MOVE;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.ai.goal.Goal.Control TARGET;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.ai.goal.Goal.Control[] field_18409;

        public Control(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.ai.goal.Goal.Control valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.ai.goal.Goal.Control[] values() {
            return null;
        }

        public Control() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
