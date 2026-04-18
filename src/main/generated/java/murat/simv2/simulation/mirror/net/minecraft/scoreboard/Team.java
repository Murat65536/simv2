package murat.simv2.simulation.mirror.net.minecraft.scoreboard;

// Generated mirror stub for simulation closure.
public class Team extends murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.CollisionRule collisionRule;
    public murat.simv2.simulation.mirror.net.minecraft.util.Formatting color;
    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule deathMessageVisibilityRule;
    public murat.simv2.simulation.mirror.net.minecraft.text.Text displayName;
    public static int field_31884;
    public static int field_31885;
    public boolean friendlyFire;
    public java.lang.String name;
    public java.lang.Object nameStyle;
    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule nameTagVisibilityRule;
    public java.util.Set playerList;
    public murat.simv2.simulation.mirror.net.minecraft.text.Text prefix;
    public java.lang.Object scoreboard;
    public boolean showFriendlyInvisibles;
    public murat.simv2.simulation.mirror.net.minecraft.text.Text suffix;

    public Team(java.lang.Object p0, java.lang.String p1) {
    }

    public static java.lang.Object decorateName(murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam p0, murat.simv2.simulation.mirror.net.minecraft.text.Text p1) {
        return null;
    }

    public java.lang.Object decorateName(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
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

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getDisplayName() {
        return null;
    }

    public java.lang.Object getFormattedName() {
        return null;
    }

    public int getFriendlyFlagsBitwise() {
        return 0;
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

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getPrefix() {
        return null;
    }

    public java.lang.Object getScoreboard() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getSuffix() {
        return null;
    }

    public boolean isEqual(murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam p0) {
        return false;
    }

    public boolean isFriendlyFireAllowed() {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.Team.Packed pack() {
        return null;
    }

    public void setCollisionRule(murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.CollisionRule p0) {
    }

    public void setColor(murat.simv2.simulation.mirror.net.minecraft.util.Formatting p0) {
    }

    public void setDeathMessageVisibilityRule(murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule p0) {
    }

    public void setDisplayName(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public void setFriendlyFireAllowed(boolean p0) {
    }

    public void setFriendlyFlagsBitwise(int p0) {
    }

    public void setNameTagVisibilityRule(murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule p0) {
    }

    public void setPrefix(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public void setShowFriendlyInvisibles(boolean p0) {
    }

    public void setSuffix(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public boolean shouldShowFriendlyInvisibles() {
        return false;
    }

    public Team() {
    }

    public static class Packed {
        public static com.mojang.serialization.Codec CODEC;
        public boolean allowFriendlyFire;
        public murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.CollisionRule collisionRule;
        public java.util.Optional color;
        public murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule deathMessageVisibility;
        public java.util.Optional displayName;
        public murat.simv2.simulation.mirror.net.minecraft.text.Text memberNamePrefix;
        public murat.simv2.simulation.mirror.net.minecraft.text.Text memberNameSuffix;
        public java.lang.String name;
        public murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule nameTagVisibility;
        public java.util.List players;
        public boolean seeFriendlyInvisibles;

        public Packed(java.lang.String p0, java.util.Optional p1, java.util.Optional p2, boolean p3, boolean p4, murat.simv2.simulation.mirror.net.minecraft.text.Text p5, murat.simv2.simulation.mirror.net.minecraft.text.Text p6, murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule p7, murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule p8, murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.CollisionRule p9, java.util.List p10) {
        }

        public boolean allowFriendlyFire() {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.CollisionRule collisionRule() {
            return null;
        }

        public java.util.Optional color() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule deathMessageVisibility() {
            return null;
        }

        public java.util.Optional displayName() {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.text.Text memberNamePrefix() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.text.Text memberNameSuffix() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam.VisibilityRule nameTagVisibility() {
            return null;
        }

        public java.lang.String name() {
            return null;
        }

        public java.util.List players() {
            return null;
        }

        public boolean seeFriendlyInvisibles() {
            return false;
        }

        public java.lang.String toString() {
            return null;
        }

        public Packed() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
