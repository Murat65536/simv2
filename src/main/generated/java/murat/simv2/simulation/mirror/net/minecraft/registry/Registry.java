package murat.simv2.simulation.mirror.net.minecraft.registry;

// Generated mirror stub for simulation closure.
public interface Registry<T> extends murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.Impl, murat.simv2.simulation.mirror.net.minecraft.util.collection.IndexedIterable {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public boolean containsId(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0);

    public boolean contains(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference createEntry(java.lang.Object p0);

    public murat.simv2.simulation.mirror.net.minecraft.registry.Registry freeze();

    public com.mojang.serialization.Codec getCodec();

    public java.util.Optional getDefaultEntry();

    public com.mojang.serialization.Codec getEntryCodec();

    public java.util.Optional getEntryInfo(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

    public java.util.Set getEntrySet();

    public java.util.Optional getEntry(int p0);

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry getEntry(java.lang.Object p0);

    public java.util.Optional getEntry(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0);

    public java.util.Set getIds();

    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier getId(java.lang.Object p0);

    public murat.simv2.simulation.mirror.net.minecraft.util.collection.IndexedIterable getIndexedEntries();

    public java.util.Set getKeys();

    public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey getKey();

    public java.util.Optional getKey(java.lang.Object p0);

    public com.mojang.serialization.Lifecycle getLifecycle();

    public java.util.Optional getOptionalValue(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

    public java.util.Optional getOptionalValue(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0);

    public java.util.Optional getOptional(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

    public java.util.Optional getOptional(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0);

    public java.lang.Object getOrThrow(int p0);

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference getOrThrow(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Named getOrThrow(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0);

    public java.util.Optional getRandomEntry(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1);

    public java.util.Optional getRandom(murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p0);

    public int getRawIdOrThrow(java.lang.Object p0);

    public int getRawId(java.lang.Object p0);

    public com.mojang.serialization.Codec getReferenceEntryCodec();

    public java.util.stream.Stream getTags();

    public java.lang.Object getValueOrThrow(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

    public java.lang.Object get(int p0);

    public java.lang.Object get(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

    public java.lang.Object get(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0);

    public java.lang.Iterable iterateEntries(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0);

    public java.util.stream.Stream keys(com.mojang.serialization.DynamicOps p0);

    public boolean ownerEquals(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryOwner p0);

    public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference registerReference(murat.simv2.simulation.mirror.net.minecraft.registry.Registry p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p1, java.lang.Object p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference registerReference(murat.simv2.simulation.mirror.net.minecraft.registry.Registry p0, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p1, java.lang.Object p2) {
        return null;
    }

    public static java.lang.Object register(murat.simv2.simulation.mirror.net.minecraft.registry.Registry p0, java.lang.String p1, java.lang.Object p2) {
        return null;
    }

    public static java.lang.Object register(murat.simv2.simulation.mirror.net.minecraft.registry.Registry p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p1, java.lang.Object p2) {
        return null;
    }

    public static java.lang.Object register(murat.simv2.simulation.mirror.net.minecraft.registry.Registry p0, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p1, java.lang.Object p2) {
        return null;
    }

    public int size();

    public murat.simv2.simulation.mirror.net.minecraft.registry.Registry.PendingTagLoad startTagReload(java.lang.Object p0);

    public java.util.stream.Stream streamEntries();

    public java.util.stream.Stream streamKeys();

    public java.util.stream.Stream streamTagKeys();

    public java.util.stream.Stream streamTags();

    public java.util.stream.Stream stream();

    public com.mojang.serialization.DataResult validateReference(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0);

    public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.Impl withFeatureFilter(murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet p0);

    public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.Impl withPredicateFilter(java.util.function.Predicate p0);

    public static interface PendingTagLoad<T> {
        public void apply();

        public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey getKey();

        public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.Impl getLookup();

        public int size();

    }

    // END GENERATED MIRROR NESTED STUBS
}
