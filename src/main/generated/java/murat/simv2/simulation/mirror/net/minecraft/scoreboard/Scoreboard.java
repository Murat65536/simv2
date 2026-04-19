package murat.simv2.simulation.mirror.net.minecraft.scoreboard;

// Generated mirror stub for simulation closure.
public class Scoreboard extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static org.slf4j.Logger LOGGER;
    public static java.lang.String field_47542;
    public java.util.Map<java.lang.Object, java.lang.Object> objectiveSlots;
    public it.unimi.dsi.fastutil.objects.Object2ObjectMap<java.lang.String, java.lang.Object> objectives;
    public it.unimi.dsi.fastutil.objects.Reference2ObjectMap<murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion, java.util.List<java.lang.Object>> objectivesByCriterion;
    public java.util.Map<java.lang.String, java.lang.Object> scores;
    public it.unimi.dsi.fastutil.objects.Object2ObjectMap<java.lang.String, murat.simv2.simulation.mirror.net.minecraft.scoreboard.Team> teams;
    public it.unimi.dsi.fastutil.objects.Object2ObjectMap<java.lang.String, murat.simv2.simulation.mirror.net.minecraft.scoreboard.Team> teamsByScoreHolder;

    public Scoreboard() {
    }

    public void addEntry(murat.simv2.simulation.mirror.net.minecraft.scoreboard.Scoreboard.PackedEntry p0) {
    }

    public void addObjective(java.lang.Object p0) {
    }

    public java.lang.Object addObjective(java.lang.String p0, murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion p1, murat.simv2.simulation.mirror.net.minecraft.text.Text p2, murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion.RenderType p3, boolean p4, java.lang.Object p5) {
        return null;
    }

    public boolean addScoreHolderToTeam(java.lang.String p0, murat.simv2.simulation.mirror.net.minecraft.scoreboard.Team p1) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.Team addTeam(java.lang.String p0) {
        return null;
    }

    public void addTeam(murat.simv2.simulation.mirror.net.minecraft.scoreboard.Team.Packed p0) {
    }

    public void clearDeadEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public boolean clearTeam(java.lang.String p0) {
        return false;
    }

    public void forEachScore(murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion p0, murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreHolder p1, java.util.function.Consumer p2) {
    }

    public java.util.Collection getKnownScoreHolders() {
        return null;
    }

    public java.lang.Object getNullableObjective(java.lang.String p0) {
        return null;
    }

    public java.lang.Object getObjectiveForSlot(java.lang.Object p0) {
        return null;
    }

    public java.util.Collection getObjectiveNames() {
        return null;
    }

    public java.util.Collection getObjectives() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreAccess getOrCreateScore(murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreHolder p0, java.lang.Object p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreAccess getOrCreateScore(murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreHolder p0, java.lang.Object p1, boolean p2) {
        return null;
    }

    public it.unimi.dsi.fastutil.objects.Object2IntMap getScoreHolderObjectives(murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreHolder p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.Team getScoreHolderTeam(java.lang.String p0) {
        return null;
    }

    public java.util.Collection getScoreboardEntries(java.lang.Object p0) {
        return null;
    }

    public java.lang.Object getScores(java.lang.String p0) {
        return null;
    }

    public java.lang.Object getScore(murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreHolder p0, java.lang.Object p1) {
        return null;
    }

    public java.util.Collection getTeamNames() {
        return null;
    }

    public java.util.Collection getTeams() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.Team getTeam(java.lang.String p0) {
        return null;
    }

    public void onScoreHolderRemoved(murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreHolder p0) {
    }

    public void onScoreRemoved(murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreHolder p0, java.lang.Object p1) {
    }

    public java.util.List pack() {
        return null;
    }

    public void removeObjective(java.lang.Object p0) {
    }

    public void removeScoreHolderFromTeam(java.lang.String p0, murat.simv2.simulation.mirror.net.minecraft.scoreboard.Team p1) {
    }

    public void removeScores(murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreHolder p0) {
    }

    public void removeScore(murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreHolder p0, java.lang.Object p1) {
    }

    public void removeTeam(murat.simv2.simulation.mirror.net.minecraft.scoreboard.Team p0) {
    }

    public void resetScore(murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreHolder p0, java.lang.Object p1) {
    }

    public void setObjectiveSlot(java.lang.Object p0, java.lang.Object p1) {
    }

    public void updateExistingObjective(java.lang.Object p0) {
    }

    public void updateObjective(java.lang.Object p0) {
    }

    public void updateRemovedObjective(java.lang.Object p0) {
    }

    public void updateRemovedTeam(murat.simv2.simulation.mirror.net.minecraft.scoreboard.Team p0) {
    }

    public void updateScoreboardTeamAndPlayers(murat.simv2.simulation.mirror.net.minecraft.scoreboard.Team p0) {
    }

    public void updateScoreboardTeam(murat.simv2.simulation.mirror.net.minecraft.scoreboard.Team p0) {
    }

    public void updateScore(murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreHolder p0, java.lang.Object p1, java.lang.Object p2) {
    }

    public static class PackedEntry {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.scoreboard.Scoreboard.PackedEntry> CODEC;
        public java.lang.String objective;
        public java.lang.String owner;
        public java.lang.Object score;

        public PackedEntry(java.lang.String p0, java.lang.String p1, java.lang.Object p2) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public java.lang.String objective() {
            return null;
        }

        public java.lang.String owner() {
            return null;
        }

        public java.lang.Object score() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public PackedEntry() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
