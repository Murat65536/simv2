package murat.simv2.simulation.mirror.net.minecraft.server.network;

// Generated mirror stub for simulation closure.
public class EntityTrackerEntry extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static org.slf4j.Logger LOGGER;
    public boolean alwaysUpdateVelocity;
    public java.util.List<murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.SerializedEntry<?>> changedEntries;
    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity entity;
    public static int field_29767;
    public static int field_44987;
    public static double field_44988;
    public static int field_44989;
    public java.util.function.BiConsumer<murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet<?>, java.util.List<java.util.UUID>> filteredWatchingSender;
    public boolean hadVehicle;
    public byte lastHeadYaw;
    public boolean lastOnGround;
    public java.util.List<murat.simv2.simulation.mirror.net.minecraft.entity.Entity> lastPassengers;
    public byte lastPitch;
    public byte lastYaw;
    public int tickInterval;
    public murat.simv2.simulation.mirror.net.minecraft.entity.TrackedPosition trackedPos;
    public int trackingTick;
    public int updatesWithoutVehicle;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d velocity;
    public java.util.function.Consumer<murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet<?>> watchingSender;
    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld world;

    public EntityTrackerEntry(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1, int p2, boolean p3, java.util.function.Consumer p4, java.util.function.BiConsumer p5) {
    }

    public float getHeadYaw() {
        return 0.0F;
    }

    public float getPitch() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getPos() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getVelocity() {
        return null;
    }

    public float getYaw() {
        return 0.0F;
    }

    public void sendPackets(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0, java.util.function.Consumer p1) {
    }

    public void sendSyncPacket(murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet p0) {
    }

    public void startTracking(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
    }

    public void stopTracking(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
    }

    public java.util.stream.Stream streamChangedPassengers(java.util.List p0) {
        return null;
    }

    public void syncEntityData() {
    }

    public void tickExperimentalMinecart(java.lang.Object p0, byte p1, byte p2, boolean p3) {
    }

    public void tick() {
    }

    public EntityTrackerEntry() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
