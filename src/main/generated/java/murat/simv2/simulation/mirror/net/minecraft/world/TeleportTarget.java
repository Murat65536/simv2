package murat.simv2.simulation.mirror.net.minecraft.world;

// Generated mirror stub for simulation closure.
public class TeleportTarget {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.world.TeleportTarget.PostDimensionTransition ADD_PORTAL_CHUNK_TICKET;
    public static murat.simv2.simulation.mirror.net.minecraft.world.TeleportTarget.PostDimensionTransition NO_OP;
    public static murat.simv2.simulation.mirror.net.minecraft.world.TeleportTarget.PostDimensionTransition SEND_TRAVEL_THROUGH_PORTAL_PACKET;
    public boolean asPassenger;
    public boolean missingRespawnBlock;
    public float pitch;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d position;
    public murat.simv2.simulation.mirror.net.minecraft.world.TeleportTarget.PostDimensionTransition postTeleportTransition;
    public java.util.Set<java.lang.Object> relatives;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d velocity;
    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld world;
    public float yaw;

    public TeleportTarget(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1, murat.simv2.simulation.mirror.net.minecraft.world.TeleportTarget.PostDimensionTransition p2) {
    }

    public TeleportTarget(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2, float p3, float p4, boolean p5, boolean p6, java.util.Set p7, murat.simv2.simulation.mirror.net.minecraft.world.TeleportTarget.PostDimensionTransition p8) {
    }

    public TeleportTarget(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2, float p3, float p4, java.util.Set p5, murat.simv2.simulation.mirror.net.minecraft.world.TeleportTarget.PostDimensionTransition p6) {
    }

    public TeleportTarget(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2, float p3, float p4, murat.simv2.simulation.mirror.net.minecraft.world.TeleportTarget.PostDimensionTransition p5) {
    }

    public static void addPortalChunkTicket(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.TeleportTarget asPassenger() {
        return null;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getWorldSpawnPos(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
        return null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean missingRespawnBlock() {
        return false;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.world.TeleportTarget missingSpawnBlock(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1, murat.simv2.simulation.mirror.net.minecraft.world.TeleportTarget.PostDimensionTransition p2) {
        return null;
    }

    public float pitch() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d position() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.TeleportTarget.PostDimensionTransition postTeleportTransition() {
        return null;
    }

    public java.util.Set relatives() {
        return null;
    }

    public static void sendTravelThroughPortalPacket(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public java.lang.String toString() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d velocity() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.TeleportTarget withPosition(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.TeleportTarget withRotation(float p0, float p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld world() {
        return null;
    }

    public float yaw() {
        return 0.0F;
    }

    public TeleportTarget() {
    }

    public static interface PostDimensionTransition {
        public void onTransition(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0);

        public murat.simv2.simulation.mirror.net.minecraft.world.TeleportTarget.PostDimensionTransition then(murat.simv2.simulation.mirror.net.minecraft.world.TeleportTarget.PostDimensionTransition p0);

    }

    // END GENERATED MIRROR NESTED STUBS
}
