package murat.simv2.simulation.mirror.net.minecraft.entity.attribute;

// Generated mirror stub for simulation closure.
public class ClampedEntityAttribute extends murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute>> CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute>> PACKET_CODEC;
    public double maxValue;
    public double minValue;

    public ClampedEntityAttribute(java.lang.String p0, double p1, double p2, double p3) {
    }

    public double clamp(double p0) {
        return 0.0D;
    }

    public double getDefaultValue() {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.Formatting getFormatting(boolean p0) {
        return null;
    }

    public double getMaxValue() {
        return 0.0D;
    }

    public double getMinValue() {
        return 0.0D;
    }

    public java.lang.String getTranslationKey() {
        return null;
    }

    public boolean isTracked() {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute setCategory(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute.Category p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute setTracked(boolean p0) {
        return null;
    }

    public ClampedEntityAttribute() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
