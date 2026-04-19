package murat.simv2.simulation.mirror.net.minecraft.loot.context;

// Generated mirror stub for simulation closure.
public class LootContext extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public java.util.Set<murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContext.Entry<?>> activeEntries;
    public java.lang.Object lookup;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random random;
    public murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext worldContext;

    public LootContext(murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1, java.lang.Object p2) {
    }

    public void drop(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, java.util.function.Consumer p1) {
    }

    public java.lang.Object getLookup() {
        return null;
    }

    public float getLuck() {
        return 0.0F;
    }

    public java.lang.Object getOrThrow(murat.simv2.simulation.mirror.net.minecraft.util.context.ContextParameter p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random getRandom() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld getWorld() {
        return null;
    }

    public java.lang.Object get(murat.simv2.simulation.mirror.net.minecraft.util.context.ContextParameter p0) {
        return null;
    }

    public boolean hasParameter(murat.simv2.simulation.mirror.net.minecraft.util.context.ContextParameter p0) {
        return false;
    }

    public boolean isActive(murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContext.Entry p0) {
        return false;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContext.Entry itemModifier(java.lang.Object p0) {
        return null;
    }

    public boolean markActive(murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContext.Entry p0) {
        return false;
    }

    public void markInactive(murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContext.Entry p0) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContext.Entry predicate(java.lang.Object p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContext.Entry table(murat.simv2.simulation.mirror.net.minecraft.loot.LootTable p0) {
        return null;
    }

    public LootContext() {
    }

    public static class Builder extends java.lang.Object {
        public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random random;
        public murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext worldContext;

        public Builder(murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext p0) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContext build(java.util.Optional p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld getWorld() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContext.Builder random(long p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContext.Builder random(murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p0) {
            return null;
        }

        public Builder() {
        }

    }

    public static class EntityTarget implements murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable {
        public static murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContext.EntityTarget ATTACKER;
        public static murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContext.EntityTarget ATTACKING_PLAYER;
        public static murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable.EnumCodec<murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContext.EntityTarget> CODEC;
        public static murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContext.EntityTarget DIRECT_ATTACKER;
        public static murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContext.EntityTarget THIS;
        public static murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContext.EntityTarget[] field_940;
        public murat.simv2.simulation.mirror.net.minecraft.util.context.ContextParameter<? extends murat.simv2.simulation.mirror.net.minecraft.entity.Entity> parameter;
        public java.lang.String type;

        public EntityTarget(java.lang.String p0, int p1, java.lang.String p2, murat.simv2.simulation.mirror.net.minecraft.util.context.ContextParameter p3) {
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

        public static murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContext.EntityTarget fromString(java.lang.String p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.context.ContextParameter getParameter() {
            return null;
        }

        public int ordinal() {
            return 0;
        }

        public static com.mojang.serialization.Keyable toKeyable(murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable[] p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContext.EntityTarget valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContext.EntityTarget[] values() {
            return null;
        }

        public EntityTarget() {
        }

    }

    public static class Entry<T> {
        public java.lang.Object type;
        public java.lang.Object value;

        public Entry(java.lang.Object p0, java.lang.Object p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public java.lang.String toString() {
            return null;
        }

        public java.lang.Object type() {
            return null;
        }

        public java.lang.Object value() {
            return null;
        }

        public Entry() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
