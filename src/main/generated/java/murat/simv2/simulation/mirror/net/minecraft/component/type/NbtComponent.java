package murat.simv2.simulation.mirror.net.minecraft.component.type;

// Generated mirror stub for simulation closure.
public class NbtComponent extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.component.type.NbtComponent> CODEC;
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.component.type.NbtComponent> CODEC_WITH_ID;
    public static murat.simv2.simulation.mirror.net.minecraft.component.type.NbtComponent DEFAULT;
    public static java.lang.String ID_KEY;
    public static org.slf4j.Logger LOGGER;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<io.netty.buffer.ByteBuf, murat.simv2.simulation.mirror.net.minecraft.component.type.NbtComponent> PACKET_CODEC;
    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound nbt;

    public NbtComponent(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    public boolean applyToBlockEntity(murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p1) {
        return false;
    }

    public void applyToEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.type.NbtComponent apply(java.util.function.Consumer p0) {
        return null;
    }

    public boolean contains(java.lang.String p0) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound copyNbt() {
        return null;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier getId() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound getNbt() {
        return null;
    }

    public java.lang.Object getRegistryValueOfId(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p1) {
        return null;
    }

    public int getSize() {
        return 0;
    }

    public com.mojang.serialization.DataResult get(com.mojang.serialization.DynamicOps p0, com.mojang.serialization.MapDecoder p1) {
        return null;
    }

    public com.mojang.serialization.DataResult get(com.mojang.serialization.MapDecoder p0) {
        return null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean matches(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
        return false;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.component.type.NbtComponent of(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
        return null;
    }

    public static void set(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1, java.util.function.Consumer p2) {
    }

    public static void set(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p2) {
    }

    public java.lang.String toString() {
        return null;
    }

    public com.mojang.serialization.DataResult with(com.mojang.serialization.DynamicOps p0, com.mojang.serialization.MapEncoder p1, java.lang.Object p2) {
        return null;
    }

    public NbtComponent() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
