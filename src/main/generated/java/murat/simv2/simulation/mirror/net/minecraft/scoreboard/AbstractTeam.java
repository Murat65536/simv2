package murat.simv2.simulation.mirror.net.minecraft.scoreboard;

// Generated mirror stub for simulation closure.
public class AbstractTeam extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public AbstractTeam() {
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.MutableText decorateName(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.CollisionRule getCollisionRule() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.Formatting getColor() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule getDeathMessageVisibilityRule() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule getNameTagVisibilityRule() {
        return null;
    }

    public java.lang.String getName() {
        return null;
    }

    public java.util.Collection getPlayerList() {
        return null;
    }

    public boolean isEqual(murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam p0) {
        return false;
    }

    public boolean isFriendlyFireAllowed() {
        return false;
    }

    public boolean shouldShowFriendlyInvisibles() {
        return false;
    }

    public static class CollisionRule implements murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable {
        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.CollisionRule ALWAYS;
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.CollisionRule> CODEC;
        public static java.util.function.IntFunction<murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.CollisionRule> INDEX_MAPPER;
        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.CollisionRule NEVER;
        public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<io.netty.buffer.ByteBuf, murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.CollisionRule> PACKET_CODEC;
        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.CollisionRule PUSH_OTHER_TEAMS;
        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.CollisionRule PUSH_OWN_TEAM;
        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.CollisionRule[] field_1439;
        public int index;
        public java.lang.String name;

        public CollisionRule(java.lang.String p0, int p1, java.lang.String p2, int p3) {
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

        public murat.simv2.simulation.mirror.net.minecraft.text.Text getDisplayName() {
            return null;
        }

        public int ordinal() {
            return 0;
        }

        public static com.mojang.serialization.Keyable toKeyable(murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable[] p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.CollisionRule valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.CollisionRule[] values() {
            return null;
        }

        public CollisionRule() {
        }

    }

    public static class VisibilityRule implements murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable {
        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule ALWAYS;
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule> CODEC;
        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule HIDE_FOR_OTHER_TEAMS;
        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule HIDE_FOR_OWN_TEAM;
        public static java.util.function.IntFunction<murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule> INDEX_MAPPER;
        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule NEVER;
        public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<io.netty.buffer.ByteBuf, murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule> PACKET_CODEC;
        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule[] field_1448;
        public int index;
        public java.lang.String name;

        public VisibilityRule(java.lang.String p0, int p1, java.lang.String p2, int p3) {
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

        public murat.simv2.simulation.mirror.net.minecraft.text.Text getDisplayName() {
            return null;
        }

        public int ordinal() {
            return 0;
        }

        public static com.mojang.serialization.Keyable toKeyable(murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable[] p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule[] values() {
            return null;
        }

        public VisibilityRule() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
