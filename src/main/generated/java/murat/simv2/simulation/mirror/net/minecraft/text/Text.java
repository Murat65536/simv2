package murat.simv2.simulation.mirror.net.minecraft.text;

// Generated mirror stub for simulation closure.
public interface Text extends murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public murat.simv2.simulation.mirror.net.minecraft.text.OrderedText asOrderedText();

    public java.lang.String asTruncatedString(int p0);

    public static murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable concat(java.util.List p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable concat(murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable[] p0) {
        return null;
    }

    public boolean contains(murat.simv2.simulation.mirror.net.minecraft.text.Text p0);

    public murat.simv2.simulation.mirror.net.minecraft.text.MutableText copyContentOnly();

    public murat.simv2.simulation.mirror.net.minecraft.text.MutableText copy();

    public static murat.simv2.simulation.mirror.net.minecraft.text.MutableText empty() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.TextContent getContent();

    public java.lang.String getLiteralString();

    public java.util.List getSiblings();

    public java.lang.String getString();

    public murat.simv2.simulation.mirror.net.minecraft.text.Style getStyle();

    public java.util.List getWithStyle(murat.simv2.simulation.mirror.net.minecraft.text.Style p0);

    public static murat.simv2.simulation.mirror.net.minecraft.text.MutableText keybind(java.lang.String p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.MutableText literal(java.lang.String p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.MutableText nbt(java.lang.String p0, boolean p1, java.util.Optional p2, murat.simv2.simulation.mirror.net.minecraft.text.NbtDataSource p3) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.Text of(com.mojang.brigadier.Message p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.Text of(java.lang.String p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.Text of(java.net.URI p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.Text of(java.util.Date p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.Text of(java.util.UUID p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.Text of(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.Text of(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable plain(java.lang.String p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.MutableText score(java.lang.String p0, java.lang.String p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.MutableText score(murat.simv2.simulation.mirror.net.minecraft.text.ParsedSelector p0, java.lang.String p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.MutableText selector(murat.simv2.simulation.mirror.net.minecraft.text.ParsedSelector p0, java.util.Optional p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.MutableText stringifiedTranslatable(java.lang.String p0, java.lang.Object[] p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable styled(java.lang.String p0, murat.simv2.simulation.mirror.net.minecraft.text.Style p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.MutableText translatableWithFallback(java.lang.String p0, java.lang.String p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.MutableText translatableWithFallback(java.lang.String p0, java.lang.String p1, java.lang.Object[] p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.MutableText translatable(java.lang.String p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.MutableText translatable(java.lang.String p0, java.lang.Object[] p1) {
        return null;
    }

    public java.util.Optional visit(murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable.StyledVisitor p0, murat.simv2.simulation.mirror.net.minecraft.text.Style p1);

    public java.util.Optional visit(murat.simv2.simulation.mirror.net.minecraft.text.StringVisitable.Visitor p0);

    public java.util.List withoutStyle();

    public static class Serialization extends java.lang.Object {
        public static com.google.gson.Gson GSON;

        public Serialization() {
        }

        public static murat.simv2.simulation.mirror.net.minecraft.text.MutableText fromJsonTree(com.google.gson.JsonElement p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p1) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.text.MutableText fromJson(com.google.gson.JsonElement p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p1) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.text.MutableText fromJson(java.lang.String p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p1) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.text.MutableText fromLenientJson(java.lang.String p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p1) {
            return null;
        }

        public static java.lang.String toJsonString(murat.simv2.simulation.mirror.net.minecraft.text.Text p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p1) {
            return null;
        }

        public static com.google.gson.JsonElement toJson(murat.simv2.simulation.mirror.net.minecraft.text.Text p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p1) {
            return null;
        }

    }

    public static class Serializer extends java.lang.Object {
        public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup registries;

        public Serializer(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p0) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.text.MutableText deserialize(com.google.gson.JsonElement p0, java.lang.reflect.Type p1, com.google.gson.JsonDeserializationContext p2) {
            return null;
        }

        public com.google.gson.JsonElement serialize(murat.simv2.simulation.mirror.net.minecraft.text.Text p0, java.lang.reflect.Type p1, com.google.gson.JsonSerializationContext p2) {
            return null;
        }

        public Serializer() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
