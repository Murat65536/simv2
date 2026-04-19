package murat.simv2.simulation.mirror.net.minecraft.component.type;

// Generated mirror stub for simulation closure.
public class TooltipDisplayComponent {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.component.type.TooltipDisplayComponent> CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.component.type.TooltipDisplayComponent DEFAULT;
    public static com.mojang.serialization.Codec<java.util.SequencedSet<murat.simv2.simulation.mirror.net.minecraft.component.ComponentType<?>>> HIDDEN_COMPONENTS_CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.component.type.TooltipDisplayComponent> PACKET_CODEC;
    public java.util.SequencedSet<murat.simv2.simulation.mirror.net.minecraft.component.ComponentType<?>> hiddenComponents;
    public boolean hideTooltip;

    public TooltipDisplayComponent(boolean p0, java.util.SequencedSet p1) {
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public java.util.SequencedSet hiddenComponents() {
        return null;
    }

    public boolean hideTooltip() {
        return false;
    }

    public boolean shouldDisplay(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0) {
        return false;
    }

    public java.lang.String toString() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.type.TooltipDisplayComponent with(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, boolean p1) {
        return null;
    }

    public TooltipDisplayComponent() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
