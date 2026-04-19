package murat.simv2.simulation.mirror.net.minecraft.network.packet.s2c.play;

// Generated mirror stub for simulation closure.
public class EntitySpawnS2CPacket extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket> CODEC;
    public static double MAX_ABSOLUTE_VELOCITY;
    public static double VELOCITY_SCALE;
    public int entityData;
    public int entityId;
    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityType<?> entityType;
    public byte headYaw;
    public byte pitch;
    public java.util.UUID uuid;
    public int velocityX;
    public int velocityY;
    public int velocityZ;
    public double x;
    public double y;
    public byte yaw;
    public double z;

    public EntitySpawnS2CPacket(int p0, java.util.UUID p1, double p2, double p3, double p4, float p5, float p6, murat.simv2.simulation.mirror.net.minecraft.entity.EntityType p7, int p8, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p9, double p10) {
    }

    public EntitySpawnS2CPacket(java.lang.Object p0) {
    }

    public EntitySpawnS2CPacket(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, int p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
    }

    public EntitySpawnS2CPacket(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.server.network.EntityTrackerEntry p1) {
    }

    public EntitySpawnS2CPacket(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.server.network.EntityTrackerEntry p1, int p2) {
    }

    public void apply(java.lang.Object p0) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec createCodec(java.lang.Object p0, java.lang.Object p1) {
        return null;
    }

    public int getEntityData() {
        return 0;
    }

    public int getEntityId() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityType getEntityType() {
        return null;
    }

    public float getHeadYaw() {
        return 0.0F;
    }

    public java.lang.Object getPacketType() {
        return null;
    }

    public float getPitch() {
        return 0.0F;
    }

    public java.util.UUID getUuid() {
        return null;
    }

    public double getVelocityX() {
        return 0.0D;
    }

    public double getVelocityY() {
        return 0.0D;
    }

    public double getVelocityZ() {
        return 0.0D;
    }

    public double getX() {
        return 0.0D;
    }

    public float getYaw() {
        return 0.0F;
    }

    public double getY() {
        return 0.0D;
    }

    public double getZ() {
        return 0.0D;
    }

    public boolean isWritingErrorSkippable() {
        return false;
    }

    public boolean transitionsNetworkState() {
        return false;
    }

    public void write(java.lang.Object p0) {
    }

    public EntitySpawnS2CPacket() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
