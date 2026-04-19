package murat.simv2.simulation.mirror.net.minecraft.registry;

// Generated mirror stub for simulation closure.
public interface DynamicRegistryManager extends murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager.Immutable EMPTY = null;
    public static final org.slf4j.Logger LOGGER = null;

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference getEntryOrThrow(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

    public com.mojang.serialization.Lifecycle getLifecycle();

    public java.lang.Object getOps(com.mojang.serialization.DynamicOps p0);

    public java.util.Optional getOptionalEntry(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

    public java.util.Optional getOptional(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

    public murat.simv2.simulation.mirror.net.minecraft.registry.Registry getOrThrow(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

    public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup of(java.util.stream.Stream p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager.Immutable of(murat.simv2.simulation.mirror.net.minecraft.registry.Registry p0) {
        return null;
    }

    public java.util.stream.Stream streamAllRegistries();

    public java.util.stream.Stream streamAllRegistryKeys();

    public java.util.stream.Stream stream();

    public murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager.Immutable toImmutable();

    public static class Entry<T> {
        public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<? extends murat.simv2.simulation.mirror.net.minecraft.registry.Registry<java.lang.Object>> key;
        public murat.simv2.simulation.mirror.net.minecraft.registry.Registry<java.lang.Object> value;

        public Entry(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, murat.simv2.simulation.mirror.net.minecraft.registry.Registry p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager.Entry freeze() {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey key() {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager.Entry of(java.util.Map.Entry p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager.Entry of(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, murat.simv2.simulation.mirror.net.minecraft.registry.Registry p1) {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.Registry value() {
            return null;
        }

        public Entry() {
        }

    }

    public static interface Immutable extends murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager {
        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference getEntryOrThrow(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

        public com.mojang.serialization.Lifecycle getLifecycle();

        public java.lang.Object getOps(com.mojang.serialization.DynamicOps p0);

        public java.util.Optional getOptionalEntry(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

        public java.util.Optional getOptional(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

        public murat.simv2.simulation.mirror.net.minecraft.registry.Registry getOrThrow(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0);

        public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup of(java.util.stream.Stream p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager.Immutable of(murat.simv2.simulation.mirror.net.minecraft.registry.Registry p0) {
            return null;
        }

        public java.util.stream.Stream streamAllRegistries();

        public java.util.stream.Stream streamAllRegistryKeys();

        public java.util.stream.Stream stream();

        public murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager.Immutable toImmutable();

    }

    public static class ImmutableImpl extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager {
        public java.util.Map<? extends murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<? extends murat.simv2.simulation.mirror.net.minecraft.registry.Registry<?>>, ? extends murat.simv2.simulation.mirror.net.minecraft.registry.Registry<?>> registries;

        public ImmutableImpl(java.util.List p0) {
        }

        public ImmutableImpl(java.util.Map p0) {
        }

        public ImmutableImpl(java.util.stream.Stream p0) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference getEntryOrThrow(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
            return null;
        }

        public com.mojang.serialization.Lifecycle getLifecycle() {
            return null;
        }

        public java.lang.Object getOps(com.mojang.serialization.DynamicOps p0) {
            return null;
        }

        public java.util.Optional getOptionalEntry(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
            return null;
        }

        public java.util.Optional getOptional(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.Registry getOrThrow(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup of(java.util.stream.Stream p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager.Immutable of(murat.simv2.simulation.mirror.net.minecraft.registry.Registry p0) {
            return null;
        }

        public java.util.stream.Stream streamAllRegistries() {
            return null;
        }

        public java.util.stream.Stream streamAllRegistryKeys() {
            return null;
        }

        public java.util.stream.Stream stream() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager.Immutable toImmutable() {
            return null;
        }

        public ImmutableImpl() {
        }

    }

    public static class Immutablized extends murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager.ImmutableImpl implements murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager.Immutable {
        public Immutablized(murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager p0, java.util.stream.Stream p1) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference getEntryOrThrow(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
            return null;
        }

        public com.mojang.serialization.Lifecycle getLifecycle() {
            return null;
        }

        public java.lang.Object getOps(com.mojang.serialization.DynamicOps p0) {
            return null;
        }

        public java.util.Optional getOptionalEntry(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
            return null;
        }

        public java.util.Optional getOptional(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.Registry getOrThrow(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup of(java.util.stream.Stream p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager.Immutable of(murat.simv2.simulation.mirror.net.minecraft.registry.Registry p0) {
            return null;
        }

        public java.util.stream.Stream streamAllRegistries() {
            return null;
        }

        public java.util.stream.Stream streamAllRegistryKeys() {
            return null;
        }

        public java.util.stream.Stream stream() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager.Immutable toImmutable() {
            return null;
        }

        public Immutablized() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
