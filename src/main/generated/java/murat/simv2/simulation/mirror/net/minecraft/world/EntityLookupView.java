package murat.simv2.simulation.mirror.net.minecraft.world;

// Generated mirror stub for simulation closure.
public interface EntityLookupView extends murat.simv2.simulation.mirror.net.minecraft.world.EntityView {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public boolean doesNotIntersectEntities(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape p1);

    public murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity getClosestEntity(java.lang.Class p0, murat.simv2.simulation.mirror.net.minecraft.entity.ai.TargetPredicate p1, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p2, double p3, double p4, double p5, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p6);

    public murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity getClosestEntity(java.util.List p0, murat.simv2.simulation.mirror.net.minecraft.entity.ai.TargetPredicate p1, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p2, double p3, double p4, double p5);

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getClosestPlayer(double p0, double p1, double p2, double p3, boolean p4);

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getClosestPlayer(double p0, double p1, double p2, double p3, java.util.function.Predicate p4);

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getClosestPlayer(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, double p1);

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getClosestPlayer(murat.simv2.simulation.mirror.net.minecraft.entity.ai.TargetPredicate p0, double p1, double p2, double p3);

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getClosestPlayer(murat.simv2.simulation.mirror.net.minecraft.entity.ai.TargetPredicate p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1);

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getClosestPlayer(murat.simv2.simulation.mirror.net.minecraft.entity.ai.TargetPredicate p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, double p2, double p3, double p4);

    public java.util.List getEntitiesByClass(java.lang.Class p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1, java.util.function.Predicate p2);

    public java.util.List getEntitiesByType(murat.simv2.simulation.mirror.net.minecraft.util.TypeFilter p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1, java.util.function.Predicate p2);

    public java.util.List getEntityCollisions(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public java.util.List getNonSpectatingEntities(java.lang.Class p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public java.util.List getOtherEntities(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1);

    public java.util.List getOtherEntities(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1, java.util.function.Predicate p2);

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getPlayerByUuid(java.util.UUID p0);

    public java.util.List getPlayers();

    public java.util.List getPlayers(murat.simv2.simulation.mirror.net.minecraft.entity.ai.TargetPredicate p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p2);

    public java.util.List getTargets(java.lang.Class p0, murat.simv2.simulation.mirror.net.minecraft.entity.ai.TargetPredicate p1, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p3);

    public boolean isPlayerInRange(double p0, double p1, double p2, double p3);

    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld toServerWorld();

    // END GENERATED MIRROR NESTED STUBS
}
