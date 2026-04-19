package murat.simv2.simulation.mirror.net.minecraft.scoreboard;

// Generated mirror stub for simulation closure.
public class ScoreboardCriterion extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion AIR;
    public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion ARMOR;
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion> CODEC;
    public static java.util.Map<java.lang.String, murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion> CRITERIA;
    public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion DEATH_COUNT;
    public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion DUMMY;
    public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion FOOD;
    public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion HEALTH;
    public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion[] KILLED_BY_TEAMS;
    public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion LEVEL;
    public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion PLAYER_KILL_COUNT;
    public static java.util.Map<java.lang.String, murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion> SIMPLE_CRITERIA;
    public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion[] TEAM_KILLS;
    public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion TOTAL_KILL_COUNT;
    public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion TRIGGER;
    public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion XP;
    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion.RenderType defaultRenderType;
    public java.lang.String name;
    public boolean readOnly;

    public ScoreboardCriterion(java.lang.String p0) {
    }

    public ScoreboardCriterion(java.lang.String p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion.RenderType p2) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion create(java.lang.String p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion create(java.lang.String p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion.RenderType p2) {
        return null;
    }

    public static java.util.Set getAllSimpleCriteria() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion.RenderType getDefaultRenderType() {
        return null;
    }

    public java.lang.String getName() {
        return null;
    }

    public static java.util.Optional getOrCreateStatCriterion(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p1) {
        return null;
    }

    public static java.util.Optional getOrCreateStatCriterion(java.lang.String p0) {
        return null;
    }

    public boolean isReadOnly() {
        return false;
    }

    public ScoreboardCriterion() {
    }

    public static class RenderType implements murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable {
        public static murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable.EnumCodec<murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion.RenderType> CODEC;
        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion.RenderType HEARTS;
        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion.RenderType INTEGER;
        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion.RenderType[] field_1473;
        public java.lang.String name;

        public RenderType(java.lang.String p0, int p1, java.lang.String p2) {
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

        public java.lang.String getName() {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion.RenderType getType(java.lang.String p0) {
            return null;
        }

        public int ordinal() {
            return 0;
        }

        public static com.mojang.serialization.Keyable toKeyable(murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable[] p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion.RenderType valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion.RenderType[] values() {
            return null;
        }

        public RenderType() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
