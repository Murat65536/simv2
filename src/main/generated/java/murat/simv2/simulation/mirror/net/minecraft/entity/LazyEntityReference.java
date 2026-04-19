package murat.simv2.simulation.mirror.net.minecraft.entity;

// Generated mirror stub for simulation closure.
public class LazyEntityReference<StoredEntityType> extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<? extends murat.simv2.simulation.mirror.net.minecraft.entity.LazyEntityReference<?>> CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<io.netty.buffer.ByteBuf, ? extends murat.simv2.simulation.mirror.net.minecraft.entity.LazyEntityReference<?>> PACKET_CODEC;
    public com.mojang.datafixers.util.Either<java.util.UUID, java.lang.Object> value;

    public LazyEntityReference(java.util.UUID p0) {
    }

    public LazyEntityReference(java.lang.Object p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.entity.UniquelyIdentifiable cast(murat.simv2.simulation.mirror.net.minecraft.world.entity.UniquelyIdentifiable p0, java.lang.Class p1) {
        return null;
    }

    public static com.mojang.serialization.Codec createCodec() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec createPacketCodec() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.entity.LazyEntityReference fromNbtOrPlayerName(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, java.lang.String p1, murat.simv2.simulation.mirror.net.minecraft.world.World p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.entity.LazyEntityReference fromNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, java.lang.String p1) {
        return null;
    }

    public java.util.UUID getUuid() {
        return null;
    }

    public static java.lang.Object resolve(murat.simv2.simulation.mirror.net.minecraft.entity.LazyEntityReference p0, java.lang.Object p1, java.lang.Class p2) {
        return null;
    }

    public java.lang.Object resolve(java.lang.Object p0, java.lang.Class p1) {
        return null;
    }

    public boolean uuidEquals(murat.simv2.simulation.mirror.net.minecraft.world.entity.UniquelyIdentifiable p0) {
        return false;
    }

    public void writeNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, java.lang.String p1) {
    }

    public LazyEntityReference() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
