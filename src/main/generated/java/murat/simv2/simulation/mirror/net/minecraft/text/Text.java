package murat.simv2.simulation.mirror.net.minecraft.text;

// Generated mirror stub for simulation closure.
public interface Text {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public java.lang.Object asOrderedText();

    public java.lang.String asTruncatedString(int p0);

    public static java.lang.Object concat(java.lang.Object[] p0) {
        return null;
    }

    public static java.lang.Object concat(java.util.List p0) {
        return null;
    }

    public boolean contains(murat.simv2.simulation.mirror.net.minecraft.text.Text p0);

    public java.lang.Object copyContentOnly();

    public java.lang.Object copy();

    public static java.lang.Object empty() {
        return null;
    }

    public java.lang.Object getContent();

    public java.lang.String getLiteralString();

    public java.util.List getSiblings();

    public java.lang.String getString();

    public java.lang.Object getStyle();

    public java.util.List getWithStyle(java.lang.Object p0);

    public static java.lang.Object keybind(java.lang.String p0) {
        return null;
    }

    public static java.lang.Object literal(java.lang.String p0) {
        return null;
    }

    public static java.lang.Object nbt(java.lang.String p0, boolean p1, java.util.Optional p2, java.lang.Object p3) {
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

    public static java.lang.Object plain(java.lang.String p0) {
        return null;
    }

    public static java.lang.Object score(java.lang.Object p0, java.lang.String p1) {
        return null;
    }

    public static java.lang.Object score(java.lang.String p0, java.lang.String p1) {
        return null;
    }

    public static java.lang.Object selector(java.lang.Object p0, java.util.Optional p1) {
        return null;
    }

    public static java.lang.Object stringifiedTranslatable(java.lang.String p0, java.lang.Object[] p1) {
        return null;
    }

    public static java.lang.Object styled(java.lang.String p0, java.lang.Object p1) {
        return null;
    }

    public static java.lang.Object translatableWithFallback(java.lang.String p0, java.lang.String p1) {
        return null;
    }

    public static java.lang.Object translatableWithFallback(java.lang.String p0, java.lang.String p1, java.lang.Object[] p2) {
        return null;
    }

    public static java.lang.Object translatable(java.lang.String p0) {
        return null;
    }

    public static java.lang.Object translatable(java.lang.String p0, java.lang.Object[] p1) {
        return null;
    }

    public java.util.Optional visit(java.lang.Object p0);

    public java.util.Optional visit(java.lang.Object p0, java.lang.Object p1);

    public java.util.List withoutStyle();

    public static class Serialization {
        public static com.google.gson.Gson GSON;

        public Serialization() {
        }

        public static java.lang.Object fromJsonTree(com.google.gson.JsonElement p0, java.lang.Object p1) {
            return null;
        }

        public static java.lang.Object fromJson(com.google.gson.JsonElement p0, java.lang.Object p1) {
            return null;
        }

        public static java.lang.Object fromJson(java.lang.String p0, java.lang.Object p1) {
            return null;
        }

        public static java.lang.Object fromLenientJson(java.lang.String p0, java.lang.Object p1) {
            return null;
        }

        public static java.lang.String toJsonString(murat.simv2.simulation.mirror.net.minecraft.text.Text p0, java.lang.Object p1) {
            return null;
        }

        public static com.google.gson.JsonElement toJson(murat.simv2.simulation.mirror.net.minecraft.text.Text p0, java.lang.Object p1) {
            return null;
        }

    }

    public static class Serializer {
        public java.lang.Object registries;

        public Serializer(java.lang.Object p0) {
        }

        public java.lang.Object deserialize(com.google.gson.JsonElement p0, java.lang.reflect.Type p1, com.google.gson.JsonDeserializationContext p2) {
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
