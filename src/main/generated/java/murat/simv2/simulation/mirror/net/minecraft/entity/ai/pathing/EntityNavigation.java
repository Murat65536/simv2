package murat.simv2.simulation.mirror.net.minecraft.entity.ai.pathing;

// Generated mirror stub for simulation closure.
public class EntityNavigation extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static int RECALCULATE_COOLDOWN;
    public int currentDistance;
    public long currentNodeMs;
    public double currentNodeTimeout;
    public java.lang.Object currentPath;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos currentTarget;
    public murat.simv2.simulation.mirror.net.minecraft.entity.mob.MobEntity entity;
    public static int field_41545;
    public static float field_41546;
    public boolean inRecalculationCooldown;
    public long lastActiveTickMs;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i lastNodePosition;
    public long lastRecalculateTime;
    public float maxFollowRange;
    public boolean nearPathStartPos;
    public java.lang.Object nodeMaker;
    public float nodeReachProximity;
    public java.lang.Object pathNodeNavigator;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d pathStartPos;
    public int pathStartTime;
    public float rangeMultiplier;
    public double speed;
    public int tickCount;
    public murat.simv2.simulation.mirror.net.minecraft.world.World world;

    public EntityNavigation(murat.simv2.simulation.mirror.net.minecraft.entity.mob.MobEntity p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1) {
    }

    public void adjustPath() {
    }

    public double adjustTargetY(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return 0.0D;
    }

    public boolean canJumpToNext(murat.simv2.simulation.mirror.net.minecraft.entity.ai.pathing.PathNodeType p0) {
        return false;
    }

    public boolean canPathDirectlyThrough(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
        return false;
    }

    public boolean canSwim() {
        return false;
    }

    public void checkTimeouts(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
    }

    public void continueFollowingPath() {
    }

    public java.lang.Object createPathNodeNavigator(int p0) {
        return null;
    }

    public static boolean doesNotCollide(murat.simv2.simulation.mirror.net.minecraft.entity.mob.MobEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2, boolean p3) {
        return false;
    }

    public java.lang.Object findPathToAny(java.util.Set p0, int p1, boolean p2, int p3, float p4) {
        return null;
    }

    public java.lang.Object findPathToAny(java.util.stream.Stream p0, int p1) {
        return null;
    }

    public java.lang.Object findPathTo(double p0, double p1, double p2, int p3) {
        return null;
    }

    public java.lang.Object findPathTo(java.util.Set p0, int p1) {
        return null;
    }

    public java.lang.Object findPathTo(java.util.Set p0, int p1, boolean p2, int p3) {
        return null;
    }

    public java.lang.Object findPathTo(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, int p1) {
        return null;
    }

    public java.lang.Object findPathTo(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1) {
        return null;
    }

    public java.lang.Object findPathTo(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1, int p2) {
        return null;
    }

    public java.lang.Object getCurrentPath() {
        return null;
    }

    public float getMaxFollowRange() {
        return 0.0F;
    }

    public java.lang.Object getNodeMaker() {
        return null;
    }

    public float getNodeReachProximity() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getPos() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getTargetPos() {
        return null;
    }

    public boolean isAtValidPosition() {
        return false;
    }

    public boolean isFollowingPath() {
        return false;
    }

    public boolean isIdle() {
        return false;
    }

    public boolean isNearPathStartPos() {
        return false;
    }

    public boolean isValidPosition(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public void recalculatePath() {
    }

    public void resetNodeAndStop() {
    }

    public void resetNode() {
    }

    public void resetRangeMultiplier() {
    }

    public void setCanSwim(boolean p0) {
    }

    public void setMaxFollowRange(float p0) {
    }

    public void setRangeMultiplier(float p0) {
    }

    public void setSpeed(double p0) {
    }

    public boolean shouldJumpToNextNode(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return false;
    }

    public boolean shouldRecalculatePath(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean startMovingAlong(java.lang.Object p0, double p1) {
        return false;
    }

    public boolean startMovingTo(double p0, double p1, double p2, double p3) {
        return false;
    }

    public boolean startMovingTo(double p0, double p1, double p2, int p3, double p4) {
        return false;
    }

    public boolean startMovingTo(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, double p1) {
        return false;
    }

    public void stop() {
    }

    public void tick() {
    }

    public void updateRange() {
    }

    public EntityNavigation() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
