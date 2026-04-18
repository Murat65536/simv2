package murat.simv2.simulation.mirror.net.minecraft.loot;

// Generated mirror stub for simulation closure.
public class LootTable {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec CODEC;
    public static long DEFAULT_SEED;
    public static murat.simv2.simulation.mirror.net.minecraft.loot.LootTable EMPTY;
    public static com.mojang.serialization.Codec ENTRY_CODEC;
    public static java.lang.Object GENERIC;
    public static org.slf4j.Logger LOGGER;
    public static com.mojang.serialization.Codec TABLE_KEY;
    public java.util.function.BiFunction combinedFunction;
    public java.util.List functions;
    public java.util.List pools;
    public java.util.Optional randomSequenceId;
    public java.lang.Object type;

    public LootTable(java.lang.Object p0, java.util.Optional p1, java.util.List p2, java.util.List p3) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.loot.LootTable.Builder builder() {
        return null;
    }

    public it.unimi.dsi.fastutil.objects.ObjectArrayList generateLoot(java.lang.Object p0) {
        return null;
    }

    public void generateLoot(java.lang.Object p0, java.util.function.Consumer p1) {
    }

    public it.unimi.dsi.fastutil.objects.ObjectArrayList generateLoot(murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext p0) {
        return null;
    }

    public void generateLoot(murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext p0, java.util.function.Consumer p1) {
    }

    public it.unimi.dsi.fastutil.objects.ObjectArrayList generateLoot(murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext p0, long p1) {
        return null;
    }

    public void generateLoot(murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext p0, long p1, java.util.function.Consumer p2) {
    }

    public it.unimi.dsi.fastutil.objects.ObjectArrayList generateLoot(murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1) {
        return null;
    }

    public void generateUnprocessedLoot(java.lang.Object p0, java.util.function.Consumer p1) {
    }

    public void generateUnprocessedLoot(murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext p0, java.util.function.Consumer p1) {
    }

    public java.util.List getFreeSlots(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1) {
        return null;
    }

    public java.lang.Object getType() {
        return null;
    }

    public static java.util.function.Consumer processStacks(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, java.util.function.Consumer p1) {
        return null;
    }

    public void spreadStacks(it.unimi.dsi.fastutil.objects.ObjectArrayList p0, int p1, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p2) {
    }

    public void supplyInventory(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext p1, long p2) {
    }

    public void validate(java.lang.Object p0) {
    }

    public LootTable() {
    }

    public static class Builder {
        public com.google.common.collect.ImmutableList.Builder functions;
        public com.google.common.collect.ImmutableList.Builder pools;
        public java.util.Optional randomSequenceId;
        public java.lang.Object type;

        public Builder() {
        }

        public java.lang.Object apply(java.lang.Iterable p0, java.util.function.Function p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.loot.LootTable.Builder apply(java.lang.Object p0) {
            return null;
        }

        public java.lang.Object apply(java.lang.Object[] p0, java.util.function.Function p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.loot.LootTable build() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.loot.LootTable.Builder getThisFunctionConsumingBuilder() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.loot.LootTable.Builder pool(java.lang.Object p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.loot.LootTable.Builder randomSequenceId(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.loot.LootTable.Builder type(java.lang.Object p0) {
            return null;
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
