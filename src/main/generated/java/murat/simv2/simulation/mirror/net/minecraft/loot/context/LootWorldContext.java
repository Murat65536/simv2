package murat.simv2.simulation.mirror.net.minecraft.loot.context;

// Generated mirror stub for simulation closure.
public class LootWorldContext {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public java.util.Map dynamicDrops;
    public float luck;
    public java.lang.Object parameters;
    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld world;

    public LootWorldContext(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, java.lang.Object p1, java.util.Map p2, float p3) {
    }

    public void addDynamicDrops(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, java.util.function.Consumer p1) {
    }

    public float getLuck() {
        return 0.0F;
    }

    public java.lang.Object getParameters() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld getWorld() {
        return null;
    }

    public LootWorldContext() {
    }

    public static class Builder {
        public java.util.Map dynamicDrops;
        public float luck;
        public java.lang.Object parameters;
        public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld world;

        public Builder(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext.Builder addDynamicDrop(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext.DynamicDrop p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext.Builder addOptional(java.lang.Object p0, java.lang.Object p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext.Builder add(java.lang.Object p0, java.lang.Object p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext build(java.lang.Object p0) {
            return null;
        }

        public java.lang.Object getOptional(java.lang.Object p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld getWorld() {
            return null;
        }

        public java.lang.Object get(java.lang.Object p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext.Builder luck(float p0) {
            return null;
        }

        public Builder() {
        }

    }

    public static interface DynamicDrop {
        public default void add(java.util.function.Consumer p0) {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
