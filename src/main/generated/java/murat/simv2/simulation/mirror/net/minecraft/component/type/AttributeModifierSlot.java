package murat.simv2.simulation.mirror.net.minecraft.component.type;

// Generated mirror stub for simulation closure.
public class AttributeModifierSlot implements murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot ANY;
    public static murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot ARMOR;
    public static murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot BODY;
    public static murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot CHEST;
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot> CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot FEET;
    public static murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot HAND;
    public static murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot HEAD;
    public static java.util.function.IntFunction<murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot> ID_TO_VALUE;
    public static murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot LEGS;
    public static murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot MAINHAND;
    public static murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot OFFHAND;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<io.netty.buffer.ByteBuf, murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot> PACKET_CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot SADDLE;
    public static murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot[] field_49231;
    public int id;
    public java.lang.String name;
    public java.util.function.Predicate<murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot> slotPredicate;
    public java.util.List<murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot> slots;

    public AttributeModifierSlot(java.lang.String p0, int p1, int p2, java.lang.String p3, java.util.function.Predicate p4) {
    }

    public AttributeModifierSlot(java.lang.String p0, int p1, int p2, java.lang.String p3, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p4) {
    }

    public java.lang.String asString() {
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

    public static murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot forEquipmentSlot(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
        return null;
    }

    public java.util.List getSlots() {
        return null;
    }

    public java.util.Iterator iterator() {
        return null;
    }

    public boolean matches(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
        return false;
    }

    public int ordinal() {
        return 0;
    }

    public static com.mojang.serialization.Keyable toKeyable(murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable[] p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot valueOf(java.lang.String p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot[] values() {
        return null;
    }

    public AttributeModifierSlot() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
