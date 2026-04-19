package murat.simv2.simulation.mirror.net.minecraft.entity.ai;

// Generated mirror stub for simulation closure.
public class TargetPredicate extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.entity.ai.TargetPredicate DEFAULT;
    public static double MIN_DISTANCE;
    public boolean attackable;
    public double baseMaxDistance;
    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.TargetPredicate.EntityPredicate predicate;
    public boolean respectsVisibility;
    public boolean useDistanceScalingFactor;

    public TargetPredicate(boolean p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.TargetPredicate copy() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.entity.ai.TargetPredicate createAttackable() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.entity.ai.TargetPredicate createNonAttackable() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.TargetPredicate ignoreDistanceScalingFactor() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.TargetPredicate ignoreVisibility() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.TargetPredicate setBaseMaxDistance(double p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.TargetPredicate setPredicate(murat.simv2.simulation.mirror.net.minecraft.entity.ai.TargetPredicate.EntityPredicate p0) {
        return null;
    }

    public boolean test(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p2) {
        return false;
    }

    public TargetPredicate() {
    }

    public static interface EntityPredicate {
        public boolean test(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1);

    }

    // END GENERATED MIRROR NESTED STUBS
}
