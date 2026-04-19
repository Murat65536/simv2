package murat.simv2.simulation.mirror.net.minecraft.component.type;

// Generated mirror stub for simulation closure.
public class ItemEnchantmentsComponent extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.component.type.ItemEnchantmentsComponent> CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.component.type.ItemEnchantmentsComponent DEFAULT;
    public static com.mojang.serialization.Codec<java.lang.Integer> ENCHANTMENT_LEVEL_CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.component.type.ItemEnchantmentsComponent> PACKET_CODEC;
    public it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap<murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<java.lang.Object>> enchantments;

    public ItemEnchantmentsComponent(it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap p0) {
    }

    public void appendTooltip(murat.simv2.simulation.mirror.net.minecraft.item.Item.TooltipContext p0, java.util.function.Consumer p1, murat.simv2.simulation.mirror.net.minecraft.item.tooltip.TooltipType p2, murat.simv2.simulation.mirror.net.minecraft.component.ComponentsAccess p3) {
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public java.util.Set getEnchantmentEntries() {
        return null;
    }

    public java.util.Set getEnchantments() {
        return null;
    }

    public int getLevel(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return 0;
    }

    public int getSize() {
        return 0;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList getTooltipOrderList(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p1, murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p2) {
        return null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public java.lang.String toString() {
        return null;
    }

    public ItemEnchantmentsComponent() {
    }

    public static class Builder extends java.lang.Object {
        public it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap<murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<java.lang.Object>> enchantments;

        public Builder(murat.simv2.simulation.mirror.net.minecraft.component.type.ItemEnchantmentsComponent p0) {
        }

        public void add(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, int p1) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.ItemEnchantmentsComponent build() {
            return null;
        }

        public java.util.Set getEnchantments() {
            return null;
        }

        public int getLevel(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
            return 0;
        }

        public void remove(java.util.function.Predicate p0) {
        }

        public void set(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, int p1) {
        }

        public Builder() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
