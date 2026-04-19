package murat.simv2.simulation.mirror.net.minecraft.entity;

// Generated mirror stub for simulation closure.
public class EquipmentSlot implements murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot BODY;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot CHEST;
    public static murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable.EnumCodec<murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot> CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot FEET;
    public static java.util.function.IntFunction<murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot> FROM_INDEX;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot HEAD;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot LEGS;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot MAINHAND;
    public static int NO_MAX_COUNT;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot OFFHAND;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<io.netty.buffer.ByteBuf, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot> PACKET_CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot SADDLE;
    public static java.util.List<murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot> VALUES;
    public int entityId;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot[] field_6176;
    public int index;
    public int maxCount;
    public java.lang.String name;
    public murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot.Type type;

    public EquipmentSlot(java.lang.String p0, int p1, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot.Type p2, int p3, int p4, int p5, java.lang.String p6) {
    }

    public EquipmentSlot(java.lang.String p0, int p1, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot.Type p2, int p3, int p4, java.lang.String p5) {
    }

    public java.lang.String asString() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot byName(java.lang.String p0) {
        return null;
    }

    public static com.mojang.serialization.Codec createBasicCodec(java.util.function.Supplier p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable.EnumCodec createCodec(java.util.function.Supplier p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable.EnumCodec createCodec(java.util.function.Supplier p0, java.util.function.Function p1) {
        return null;
    }

    public static java.util.function.Function createMapper(murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable[] p0, java.util.function.Function p1) {
        return null;
    }

    public int getEntitySlotId() {
        return 0;
    }

    public int getIndex() {
        return 0;
    }

    public java.lang.String getName() {
        return null;
    }

    public int getOffsetEntitySlotId(int p0) {
        return 0;
    }

    public int getOffsetIndex(int p0) {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot.Type getType() {
        return null;
    }

    public boolean increasesDroppedExperience() {
        return false;
    }

    public boolean isArmorSlot() {
        return false;
    }

    public int ordinal() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack split(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return null;
    }

    public static com.mojang.serialization.Keyable toKeyable(murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable[] p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot valueOf(java.lang.String p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot[] values() {
        return null;
    }

    public EquipmentSlot() {
    }

    public static class Type {
        public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot.Type ANIMAL_ARMOR;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot.Type HAND;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot.Type HUMANOID_ARMOR;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot.Type SADDLE;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot.Type[] field_6179;

        public Type(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot.Type valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot.Type[] values() {
            return null;
        }

        public Type() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
