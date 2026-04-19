package murat.simv2.simulation.mirror.net.minecraft.component.type;

// Generated mirror stub for simulation closure.
public class AttributeModifiersComponent {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifiersComponent> CODEC;
    public static java.text.DecimalFormat DECIMAL_FORMAT;
    public static murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifiersComponent DEFAULT;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifiersComponent> PACKET_CODEC;
    public java.util.List<murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifiersComponent.Entry> modifiers;

    public AttributeModifiersComponent(java.util.List p0) {
    }

    public void applyModifiers(murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot p0, java.util.function.BiConsumer p1) {
    }

    public void applyModifiers(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0, java.util.function.BiConsumer p1) {
    }

    public double applyOperations(double p0, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p1) {
        return 0.0D;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifiersComponent.Builder builder() {
        return null;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public java.util.List modifiers() {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifiersComponent with(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier p1, murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot p2) {
        return null;
    }

    public AttributeModifiersComponent() {
    }

    public static class Builder extends java.lang.Object {
        public com.google.common.collect.ImmutableList.Builder<murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifiersComponent.Entry> entries;

        public Builder() {
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifiersComponent.Builder add(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier p1, murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot p2) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifiersComponent build() {
            return null;
        }

    }

    public static class Entry {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifiersComponent.Entry> CODEC;
        public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifiersComponent.Entry> PACKET_CODEC;
        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute> attribute;
        public murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier modifier;
        public murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot slot;

        public Entry(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier p1, murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot p2) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry attribute() {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public boolean matches(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p1) {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier modifier() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot slot() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public Entry() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
