package murat.simv2.simulation.mirror.net.minecraft.text;

// Generated mirror stub for simulation closure.
public interface TextContent {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public murat.simv2.simulation.mirror.net.minecraft.text.TextContent.Type getType();

    public murat.simv2.simulation.mirror.net.minecraft.text.MutableText parse(murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1, int p2);

    public java.util.Optional visit(murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable.StyledVisitor p0, murat.simv2.simulation.mirror.net.minecraft.text.Style p1);

    public java.util.Optional visit(murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable.Visitor p0);

    public static class Type<T> implements murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable {
        public com.mojang.serialization.MapCodec<java.lang.Object> codec;
        public java.lang.String id;

        public Type(com.mojang.serialization.MapCodec p0, java.lang.String p1) {
        }

        public java.lang.String asString() {
            return null;
        }

        public com.mojang.serialization.MapCodec codec() {
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

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public java.lang.String id() {
            return null;
        }

        public static com.mojang.serialization.Keyable toKeyable(murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable[] p0) {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public Type() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
