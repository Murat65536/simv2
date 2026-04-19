package murat.simv2.simulation.mirror.net.minecraft.advancement;

// Generated mirror stub for simulation closure.
public class PlayerAdvancementTracker extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.google.gson.Gson GSON;
    public static org.slf4j.Logger LOGGER;
    public java.lang.Object advancementManager;
    public java.lang.Object currentDisplayTab;
    public boolean dirty;
    public java.nio.file.Path filePath;
    public murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity owner;
    public java.lang.Object playerManager;
    public java.util.Map<java.lang.Object, java.lang.Object> progress;
    public com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.advancement.PlayerAdvancementTracker.ProgressMap> progressMapCodec;
    public java.util.Set<java.lang.Object> progressUpdates;
    public java.util.Set<java.lang.Object> updatedRoots;
    public java.util.Set<java.lang.Object> visibleAdvancements;

    public PlayerAdvancementTracker(com.mojang.datafixers.DataFixer p0, java.lang.Object p1, java.lang.Object p2, java.nio.file.Path p3, murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p4) {
    }

    public void beginTrackingAllAdvancements(java.lang.Object p0) {
    }

    public void beginTracking(java.lang.Object p0) {
    }

    public void beginTracking(java.lang.Object p0, java.lang.String p1, java.lang.Object p2) {
    }

    public void calculateDisplay(java.lang.Object p0, java.util.Set p1, java.util.Set p2) {
    }

    public void clearCriteria() {
    }

    public murat.simv2.simulation.mirror.net.minecraft.advancement.PlayerAdvancementTracker.ProgressMap createProgressMap() {
        return null;
    }

    public void endTrackingCompleted(java.lang.Object p0) {
    }

    public void endTrackingCompleted(java.lang.Object p0, java.lang.String p1, java.lang.Object p2) {
    }

    public java.lang.Object getProgress(java.lang.Object p0) {
        return null;
    }

    public boolean grantCriterion(java.lang.Object p0, java.lang.String p1) {
        return false;
    }

    public void initProgress(java.lang.Object p0, java.lang.Object p1) {
    }

    public void loadProgressMap(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.advancement.PlayerAdvancementTracker.ProgressMap p1) {
    }

    public void load(java.lang.Object p0) {
    }

    public void onStatusUpdate(java.lang.Object p0) {
    }

    public void reload(java.lang.Object p0) {
    }

    public boolean revokeCriterion(java.lang.Object p0, java.lang.String p1) {
        return false;
    }

    public void rewardEmptyAdvancements(java.lang.Object p0) {
    }

    public void save() {
    }

    public void sendUpdate(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0, boolean p1) {
    }

    public void setDisplayTab(java.lang.Object p0) {
    }

    public void setOwner(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
    }

    public PlayerAdvancementTracker() {
    }

    public static class ProgressMap {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.advancement.PlayerAdvancementTracker.ProgressMap> CODEC;
        public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, java.lang.Object> map;

        public ProgressMap(java.util.Map p0) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public void forEach(java.util.function.BiConsumer p0) {
        }

        public int hashCode() {
            return 0;
        }

        public java.util.Map map() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public ProgressMap() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
