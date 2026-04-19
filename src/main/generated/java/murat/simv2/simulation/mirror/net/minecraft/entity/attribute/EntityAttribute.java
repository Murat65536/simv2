package murat.simv2.simulation.mirror.net.minecraft.entity.attribute;

// Generated mirror stub for simulation closure.
public class EntityAttribute extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute>> CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute>> PACKET_CODEC;
    public murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute.Category category;
    public double fallback;
    public boolean tracked;
    public java.lang.String translationKey;

    public EntityAttribute(java.lang.String p0, double p1) {
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

    public EntityAttribute() {
    }

    public static class Category {
        public static murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute.Category NEGATIVE;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute.Category NEUTRAL;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute.Category POSITIVE;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute.Category[] field_51888;

        public Category(java.lang.String p0, int p1) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.Formatting getFormatting(boolean p0) {
            return null;
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute.Category valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute.Category[] values() {
            return null;
        }

        public Category() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
