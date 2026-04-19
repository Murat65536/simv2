package murat.simv2.simulation.mirror.net.minecraft.loot.context;

// Generated mirror stub for simulation closure.
public class LootWorldContext extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext.DynamicDrop> dynamicDrops;
    public float luck;
    public murat.simv2.simulation.mirror.net.minecraft.util.context.ContextParameterMap parameters;
    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld world;

    public LootWorldContext(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.context.ContextParameterMap p1, java.util.Map p2, float p3) {
    }

    public void addDynamicDrops(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, java.util.function.Consumer p1) {
    }

    public float getLuck() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.context.ContextParameterMap getParameters() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld getWorld() {
        return null;
    }

    public LootWorldContext() {
    }

    public static class Builder extends java.lang.Object {
        public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext.DynamicDrop> dynamicDrops;
        public float luck;
        public murat.simv2.simulation.mirror.net.minecraft.util.context.ContextParameterMap.Builder parameters;
        public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld world;

        public Builder(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext.Builder addDynamicDrop(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext.DynamicDrop p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext.Builder addOptional(murat.simv2.simulation.mirror.net.minecraft.util.context.ContextParameter p0, java.lang.Object p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext.Builder add(murat.simv2.simulation.mirror.net.minecraft.util.context.ContextParameter p0, java.lang.Object p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext build(murat.simv2.simulation.mirror.net.minecraft.util.context.ContextType p0) {
            return null;
        }

        public java.lang.Object getOptional(murat.simv2.simulation.mirror.net.minecraft.util.context.ContextParameter p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld getWorld() {
            return null;
        }

        public java.lang.Object get(murat.simv2.simulation.mirror.net.minecraft.util.context.ContextParameter p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext.Builder luck(float p0) {
            return null;
        }

        public Builder() {
        }

    }

    public static interface DynamicDrop {
        public void add(java.util.function.Consumer p0);

    }

    // END GENERATED MIRROR NESTED STUBS
}
