package murat.simv2.simulation.mirror.net.minecraft.state;

// Generated mirror stub for simulation closure.
public class StateManager<O, S> extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.util.regex.Pattern VALID_NAME_PATTERN;
    public java.lang.Object owner;
    public com.google.common.collect.ImmutableSortedMap<java.lang.String, murat.simv2.simulation.mirror.net.minecraft.state.property.Property<?>> properties;
    public com.google.common.collect.ImmutableList<java.lang.Object> states;

    public StateManager(java.util.function.Function p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.state.StateManager.Factory p2, java.util.Map p3) {
    }

    public static com.mojang.serialization.MapCodec addFieldToMapCodec(com.mojang.serialization.MapCodec p0, java.util.function.Supplier p1, java.lang.String p2, murat.simv2.simulation.mirror.net.minecraft.state.property.Property p3) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.state.State getDefaultState() {
        return null;
    }

    public java.lang.Object getOwner() {
        return null;
    }

    public java.util.Collection getProperties() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.state.property.Property getProperty(java.lang.String p0) {
        return null;
    }

    public com.google.common.collect.ImmutableList getStates() {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public StateManager() {
    }

    public static class Builder<O, S> extends java.lang.Object {
        public java.util.Map<java.lang.String, murat.simv2.simulation.mirror.net.minecraft.state.property.Property<?>> namedProperties;
        public java.lang.Object owner;

        public Builder(java.lang.Object p0) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.state.StateManager.Builder add(murat.simv2.simulation.mirror.net.minecraft.state.property.Property[] p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.state.StateManager build(java.util.function.Function p0, murat.simv2.simulation.mirror.net.minecraft.state.StateManager.Factory p1) {
            return null;
        }

        public void validate(murat.simv2.simulation.mirror.net.minecraft.state.property.Property p0) {
        }

        public Builder() {
        }

    }

    public static interface Factory<O, S> {
        public java.lang.Object create(java.lang.Object p0, it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap p1, com.mojang.serialization.MapCodec p2);

    }

    // END GENERATED MIRROR NESTED STUBS
}
