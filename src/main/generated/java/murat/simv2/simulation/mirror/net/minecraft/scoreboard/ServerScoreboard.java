package murat.simv2.simulation.mirror.net.minecraft.scoreboard;

// Generated mirror stub for simulation closure.
public class ServerScoreboard extends murat.simv2.simulation.mirror.net.minecraft.scoreboard.Scoreboard {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.lang.Object STATE_TYPE;
    public static java.lang.String field_47542;
    public murat.simv2.simulation.mirror.net.minecraft.server.MinecraftServer server;
    public java.util.Set<java.lang.Object> syncableObjectives;
    public java.util.List<java.lang.Runnable> updateListeners;

    public ServerScoreboard(murat.simv2.simulation.mirror.net.minecraft.server.MinecraftServer p0) {
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

    public void addUpdateListener(java.lang.Runnable p0) {
    }

    public void clearDeadEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public boolean clearTeam(java.lang.String p0) {
        return false;
    }

    public int countDisplaySlots(java.lang.Object p0) {
        return 0;
    }

    public java.util.List createChangePackets(java.lang.Object p0) {
        return null;
    }

    public java.util.List createRemovePackets(java.lang.Object p0) {
        return null;
    }

    public java.lang.Object createState() {
        return null;
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

    public void runUpdateListeners() {
    }

    public void setObjectiveSlot(java.lang.Object p0, java.lang.Object p1) {
    }

    public void startSyncing(java.lang.Object p0) {
    }

    public void stopSyncing(java.lang.Object p0) {
    }

    public java.lang.Object unpackState(java.lang.Object p0) {
        return null;
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

    public ServerScoreboard() {
    }

    public static class UpdateMode {
        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ServerScoreboard.UpdateMode CHANGE;
        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ServerScoreboard.UpdateMode REMOVE;
        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ServerScoreboard.UpdateMode[] field_13429;

        public UpdateMode(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ServerScoreboard.UpdateMode valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ServerScoreboard.UpdateMode[] values() {
            return null;
        }

        public UpdateMode() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
