package murat.simv2.simulation.mirror.net.minecraft.registry;

// Generated mirror stub for simulation closure.
public interface RegistryWrapper<T> {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public java.util.Optional getOptional(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

    public java.util.Optional getOptional(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0);

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference getOrThrow(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Named getOrThrow(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0);

    public java.util.stream.Stream getTags();

    public java.util.stream.Stream streamEntries();

    public java.util.stream.Stream streamKeys();

    public java.util.stream.Stream streamTagKeys();

    public static interface Impl<T> extends murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryOwner {
        public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey getKey();

        public com.mojang.serialization.Lifecycle getLifecycle();

        public java.util.Optional getOptional(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

        public java.util.Optional getOptional(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0);

        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference getOrThrow(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Named getOrThrow(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0);

        public java.util.stream.Stream getTags();

        public boolean ownerEquals(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryOwner p0);

        public java.util.stream.Stream streamEntries();

        public java.util.stream.Stream streamKeys();

        public java.util.stream.Stream streamTagKeys();

        public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.Impl withFeatureFilter(murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet p0);

        public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.Impl withPredicateFilter(java.util.function.Predicate p0);

        public static interface Delegating<T> extends murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.Impl {
            public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.Impl getBase();

            public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey getKey();

            public com.mojang.serialization.Lifecycle getLifecycle();

            public java.util.Optional getOptional(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

            public java.util.Optional getOptional(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0);

            public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference getOrThrow(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

            public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Named getOrThrow(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0);

            public java.util.stream.Stream getTags();

            public boolean ownerEquals(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryOwner p0);

            public java.util.stream.Stream streamEntries();

            public java.util.stream.Stream streamKeys();

            public java.util.stream.Stream streamTagKeys();

            public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.Impl withFeatureFilter(murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet p0);

            public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.Impl withPredicateFilter(java.util.function.Predicate p0);

        }

    }

    public static interface WrapperLookup {
        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference getEntryOrThrow(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

        public com.mojang.serialization.Lifecycle getLifecycle();

        public java.lang.Object getOps(com.mojang.serialization.DynamicOps p0);

        public java.util.Optional getOptionalEntry(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

        public java.util.Optional getOptional(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

        public java.lang.Object getOrThrow(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

        public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup of(java.util.stream.Stream p0) {
            return null;
        }

        public java.util.stream.Stream streamAllRegistryKeys();

        public java.util.stream.Stream stream();

    }

    // END GENERATED MIRROR NESTED STUBS
}
