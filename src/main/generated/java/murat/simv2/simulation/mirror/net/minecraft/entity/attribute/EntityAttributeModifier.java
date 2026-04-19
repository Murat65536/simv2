package murat.simv2.simulation.mirror.net.minecraft.entity.attribute;

// Generated mirror stub for simulation closure.
public class EntityAttributeModifier {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier> CODEC;
    public static com.mojang.serialization.MapCodec<murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier> MAP_CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<io.netty.buffer.ByteBuf, murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier> PACKET_CODEC;
    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier id;
    public murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier.Operation operation;
    public double value;

    public EntityAttributeModifier(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, double p1, murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier.Operation p2) {
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public boolean idMatches(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier id() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier.Operation operation() {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public double value() {
        return 0.0D;
    }

    public EntityAttributeModifier() {
    }

    public static class Operation implements murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable {
        public static murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier.Operation ADD_MULTIPLIED_BASE;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier.Operation ADD_MULTIPLIED_TOTAL;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier.Operation ADD_VALUE;
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier.Operation> CODEC;
        public static java.util.function.IntFunction<murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier.Operation> ID_TO_VALUE;
        public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<io.netty.buffer.ByteBuf, murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier.Operation> PACKET_CODEC;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier.Operation[] field_6333;
        public int id;
        public java.lang.String name;

        public Operation(java.lang.String p0, int p1, java.lang.String p2, int p3) {
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

        public int getId() {
            return 0;
        }

        public int ordinal() {
            return 0;
        }

        public static com.mojang.serialization.Keyable toKeyable(murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable[] p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier.Operation valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier.Operation[] values() {
            return null;
        }

        public Operation() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
