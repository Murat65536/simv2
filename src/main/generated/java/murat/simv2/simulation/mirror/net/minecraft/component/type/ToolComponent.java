package murat.simv2.simulation.mirror.net.minecraft.component.type;

// Generated mirror stub for simulation closure.
public class ToolComponent {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.component.type.ToolComponent> CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.component.type.ToolComponent> PACKET_CODEC;
    public boolean canDestroyBlocksInCreative;
    public int damagePerBlock;
    public float defaultMiningSpeed;
    public java.util.List<murat.simv2.simulation.mirror.net.minecraft.component.type.ToolComponent.Rule> rules;

    public ToolComponent(java.util.List p0, float p1, int p2, boolean p3) {
    }

    public boolean canDestroyBlocksInCreative() {
        return false;
    }

    public int damagePerBlock() {
        return 0;
    }

    public float defaultMiningSpeed() {
        return 0.0F;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public float getSpeed(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return 0.0F;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isCorrectForDrops(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return false;
    }

    public java.util.List rules() {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public ToolComponent() {
    }

    public static class Rule {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.component.type.ToolComponent.Rule> CODEC;
        public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.component.type.ToolComponent.Rule> PACKET_CODEC;
        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList<murat.simv2.simulation.mirror.net.minecraft.block.Block> blocks;
        public java.util.Optional<java.lang.Boolean> correctForDrops;
        public java.util.Optional<java.lang.Float> speed;

        public Rule(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList p0, java.util.Optional p1, java.util.Optional p2) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList blocks() {
            return null;
        }

        public java.util.Optional correctForDrops() {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.component.type.ToolComponent.Rule ofAlwaysDropping(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList p0, float p1) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.component.type.ToolComponent.Rule ofNeverDropping(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.component.type.ToolComponent.Rule of(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList p0, float p1) {
            return null;
        }

        public java.util.Optional speed() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public Rule() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
