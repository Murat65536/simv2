package murat.simv2.simulation.mirror.net.minecraft.registry.entry;

// Generated mirror stub for simulation closure.
public interface RegistryEntry<T> {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public default java.lang.String getIdAsString() {
        return null;
    }

    public default com.mojang.datafixers.util.Either getKeyOrValue() {
        return null;
    }

    public default java.util.Optional getKey() {
        return null;
    }

    public default murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Type getType() {
        return null;
    }

    public default boolean hasKeyAndValue() {
        return false;
    }

    public default boolean isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0) {
        return false;
    }

    public default boolean matchesId(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return false;
    }

    public default boolean matchesKey(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
        return false;
    }

    public default boolean matches(java.util.function.Predicate p0) {
        return false;
    }

    public default boolean matches(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return false;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry of(java.lang.Object p0) {
        return null;
    }

    public default boolean ownerEquals(java.lang.Object p0) {
        return false;
    }

    public default java.util.stream.Stream streamTags() {
        return null;
    }

    public default java.lang.Object value() {
        return null;
    }

    public static class Direct<T> implements murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry {
        public java.lang.Object value;

        public Direct(java.lang.Object p0) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public java.lang.String getIdAsString() {
            return null;
        }

        public com.mojang.datafixers.util.Either getKeyOrValue() {
            return null;
        }

        public java.util.Optional getKey() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Type getType() {
            return null;
        }

        public boolean hasKeyAndValue() {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public boolean isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0) {
            return false;
        }

        public boolean matchesId(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
            return false;
        }

        public boolean matchesKey(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
            return false;
        }

        public boolean matches(java.util.function.Predicate p0) {
            return false;
        }

        public boolean matches(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
            return false;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry of(java.lang.Object p0) {
            return null;
        }

        public boolean ownerEquals(java.lang.Object p0) {
            return false;
        }

        public java.util.stream.Stream streamTags() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public java.lang.Object value() {
            return null;
        }

        public Direct() {
        }

    }

    public static class Reference<T> implements murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry {
        public java.lang.Object owner;
        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference.Type referenceType;
        public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey registryKey;
        public java.util.Set tags;
        public java.lang.Object value;

        public Reference(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference.Type p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p2, java.lang.Object p3) {
        }

        public java.lang.String getIdAsString() {
            return null;
        }

        public com.mojang.datafixers.util.Either getKeyOrValue() {
            return null;
        }

        public java.util.Optional getKey() {
            return null;
        }

        public java.util.Set getTags() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Type getType() {
            return null;
        }

        public boolean hasKeyAndValue() {
            return false;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference intrusive(java.lang.Object p0, java.lang.Object p1) {
            return null;
        }

        public boolean isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0) {
            return false;
        }

        public boolean matchesId(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
            return false;
        }

        public boolean matchesKey(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
            return false;
        }

        public boolean matches(java.util.function.Predicate p0) {
            return false;
        }

        public boolean matches(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
            return false;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry of(java.lang.Object p0) {
            return null;
        }

        public boolean ownerEquals(java.lang.Object p0) {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey registryKey() {
            return null;
        }

        public void setRegistryKey(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
        }

        public void setTags(java.util.Collection p0) {
        }

        public void setValue(java.lang.Object p0) {
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference standAlone(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p1) {
            return null;
        }

        public java.util.stream.Stream streamTags() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public java.lang.Object value() {
            return null;
        }

        public Reference() {
        }

        public static class Type {
            public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference.Type INTRUSIVE;
            public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference.Type STAND_ALONE;
            public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference.Type[] field_36456;

            public Type(java.lang.String p0, int p1) {
            }

            public int ordinal() {
                return 0;
            }

            public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference.Type valueOf(java.lang.String p0) {
                return null;
            }

            public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference.Type[] values() {
                return null;
            }

            public Type() {
            }

        }

    }

    public static class Type {
        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Type DIRECT;
        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Type REFERENCE;
        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Type[] field_36448;

        public Type(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Type valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Type[] values() {
            return null;
        }

        public Type() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
