package murat.simv2.simulation.mirror.net.minecraft.registry.entry;

// Generated mirror stub for simulation closure.
public interface RegistryEntryList<T> {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public boolean contains(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0);

    public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList empty() {
        return null;
    }

    public java.util.Optional getRandom(murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p0);

    public com.mojang.datafixers.util.Either getStorage();

    public java.util.Optional getTagKey();

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry get(int p0);

    public boolean isBound();

    public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Direct of(java.util.List p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Direct of(java.util.function.Function p0, java.lang.Object[] p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Direct of(java.util.function.Function p0, java.util.Collection p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Named of(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryOwner p0, murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Direct of(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry[] p0) {
        return null;
    }

    public boolean ownerEquals(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryOwner p0);

    public int size();

    public java.util.stream.Stream stream();

    public static class Direct<T> extends murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.ListBacked {
        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Direct<?> EMPTY;
        public java.util.List<murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<java.lang.Object>> entries;
        public java.util.Set<murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<java.lang.Object>> entrySet;

        public Direct(java.util.List p0) {
        }

        public boolean contains(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
            return false;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList empty() {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public java.util.List getEntries() {
            return null;
        }

        public java.util.Optional getRandom(murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p0) {
            return null;
        }

        public com.mojang.datafixers.util.Either getStorage() {
            return null;
        }

        public java.util.Optional getTagKey() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry get(int p0) {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public boolean isBound() {
            return false;
        }

        public java.util.Iterator iterator() {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Direct of(java.util.List p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Direct of(java.util.function.Function p0, java.lang.Object[] p1) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Direct of(java.util.function.Function p0, java.util.Collection p1) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Named of(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryOwner p0, murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p1) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Direct of(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry[] p0) {
            return null;
        }

        public boolean ownerEquals(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryOwner p0) {
            return false;
        }

        public int size() {
            return 0;
        }

        public java.util.Spliterator spliterator() {
            return null;
        }

        public java.util.stream.Stream stream() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public Direct() {
        }

    }

    public static class ListBacked<T> extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList {
        public ListBacked() {
        }

        public boolean contains(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
            return false;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList empty() {
            return null;
        }

        public java.util.List getEntries() {
            return null;
        }

        public java.util.Optional getRandom(murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p0) {
            return null;
        }

        public com.mojang.datafixers.util.Either getStorage() {
            return null;
        }

        public java.util.Optional getTagKey() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry get(int p0) {
            return null;
        }

        public boolean isBound() {
            return false;
        }

        public java.util.Iterator iterator() {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Direct of(java.util.List p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Direct of(java.util.function.Function p0, java.lang.Object[] p1) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Direct of(java.util.function.Function p0, java.util.Collection p1) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Named of(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryOwner p0, murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p1) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Direct of(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry[] p0) {
            return null;
        }

        public boolean ownerEquals(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryOwner p0) {
            return false;
        }

        public int size() {
            return 0;
        }

        public java.util.Spliterator spliterator() {
            return null;
        }

        public java.util.stream.Stream stream() {
            return null;
        }

    }

    public static class Named<T> extends murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.ListBacked {
        public java.util.List<murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<java.lang.Object>> entries;
        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryOwner<java.lang.Object> owner;
        public murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey<java.lang.Object> tag;

        public Named(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryOwner p0, murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p1) {
        }

        public boolean contains(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
            return false;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList empty() {
            return null;
        }

        public java.util.List getEntries() {
            return null;
        }

        public java.util.Optional getRandom(murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p0) {
            return null;
        }

        public com.mojang.datafixers.util.Either getStorage() {
            return null;
        }

        public java.util.Optional getTagKey() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey getTag() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry get(int p0) {
            return null;
        }

        public boolean isBound() {
            return false;
        }

        public java.util.Iterator iterator() {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Direct of(java.util.List p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Direct of(java.util.function.Function p0, java.lang.Object[] p1) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Direct of(java.util.function.Function p0, java.util.Collection p1) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Named of(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryOwner p0, murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p1) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList.Direct of(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry[] p0) {
            return null;
        }

        public boolean ownerEquals(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryOwner p0) {
            return false;
        }

        public void setEntries(java.util.List p0) {
        }

        public int size() {
            return 0;
        }

        public java.util.Spliterator spliterator() {
            return null;
        }

        public java.util.stream.Stream stream() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public Named() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
