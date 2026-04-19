package murat.simv2.simulation.mirror.net.minecraft.text;

// Generated mirror stub for simulation closure.
public interface HoverEvent {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent> CODEC = null;

    public murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent.Action getAction();

    public static class Action implements murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent.Action> CODEC;
        public static murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent.Action SHOW_ENTITY;
        public static murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent.Action SHOW_ITEM;
        public static murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent.Action SHOW_TEXT;
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent.Action> UNVALIDATED_CODEC;
        public com.mojang.serialization.MapCodec<? extends murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent> codec;
        public static murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent.Action[] field_55910;
        public java.lang.String name;
        public boolean parsable;

        public Action(java.lang.String p0, int p1, java.lang.String p2, boolean p3, com.mojang.serialization.MapCodec p4) {
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

        public boolean isParsable() {
            return false;
        }

        public int ordinal() {
            return 0;
        }

        public static com.mojang.serialization.Keyable toKeyable(murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable[] p0) {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public static com.mojang.serialization.DataResult validate(murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent.Action p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent.Action valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent.Action[] values() {
            return null;
        }

        public Action() {
        }

    }

    public static class EntityContent extends java.lang.Object {
        public static com.mojang.serialization.MapCodec<murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent.EntityContent> CODEC;
        public murat.simv2.simulation.mirror.net.minecraft.entity.EntityType<?> entityType;
        public java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.text.Text> name;
        public java.util.List<murat.simv2.simulation.mirror.net.minecraft.text.Text> tooltip;
        public java.util.UUID uuid;

        public EntityContent(murat.simv2.simulation.mirror.net.minecraft.entity.EntityType p0, java.util.UUID p1, java.util.Optional p2) {
        }

        public EntityContent(murat.simv2.simulation.mirror.net.minecraft.entity.EntityType p0, java.util.UUID p1, murat.simv2.simulation.mirror.net.minecraft.text.Text p2) {
        }

        public java.util.List asTooltip() {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public EntityContent() {
        }

    }

    public static class ShowEntity implements murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent {
        public static com.mojang.serialization.MapCodec<murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent.ShowEntity> CODEC;
        public murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent.EntityContent entity;

        public ShowEntity(murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent.EntityContent p0) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent.EntityContent entity() {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent.Action getAction() {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public java.lang.String toString() {
            return null;
        }

        public ShowEntity() {
        }

    }

    public static class ShowItem implements murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent {
        public static com.mojang.serialization.MapCodec<murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent.ShowItem> CODEC;
        public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack item;

        public ShowItem(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent.Action getAction() {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack item() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public ShowItem() {
        }

    }

    public static class ShowText implements murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent {
        public static com.mojang.serialization.MapCodec<murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent.ShowText> CODEC;
        public murat.simv2.simulation.mirror.net.minecraft.text.Text value;

        public ShowText(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent.Action getAction() {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public java.lang.String toString() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.text.Text value() {
            return null;
        }

        public ShowText() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
