package murat.simv2.simulation.mirror.net.minecraft.item;

// Generated mirror stub for simulation closure.
public class FuelRegistry extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public it.unimi.dsi.fastutil.objects.Object2IntSortedMap<murat.simv2.simulation.mirror.net.minecraft.item.Item> fuelValues;

    public FuelRegistry(it.unimi.dsi.fastutil.objects.Object2IntSortedMap p0) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.item.FuelRegistry createDefault(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p0, murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.item.FuelRegistry createDefault(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p0, murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet p1, int p2) {
        return null;
    }

    public java.util.SequencedSet getFuelItems() {
        return null;
    }

    public int getFuelTicks(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return 0;
    }

    public boolean isFuel(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return false;
    }

    public FuelRegistry() {
    }

    public static class Builder extends java.lang.Object {
        public murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet enabledFeatures;
        public it.unimi.dsi.fastutil.objects.Object2IntSortedMap<murat.simv2.simulation.mirror.net.minecraft.item.Item> fuelValues;
        public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper<murat.simv2.simulation.mirror.net.minecraft.item.Item> itemLookup;

        public Builder(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p0, murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet p1) {
        }

        public void add(int p0, murat.simv2.simulation.mirror.net.minecraft.item.Item p1) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.FuelRegistry.Builder add(murat.simv2.simulation.mirror.net.minecraft.item.ItemConvertible p0, int p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.FuelRegistry.Builder add(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0, int p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.FuelRegistry build() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.FuelRegistry.Builder remove(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0) {
            return null;
        }

        public Builder() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
