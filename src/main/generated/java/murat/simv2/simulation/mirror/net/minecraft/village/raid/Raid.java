package murat.simv2.simulation.mirror.net.minecraft.village.raid;

// Generated mirror stub for simulation closure.
public class Raid extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.MapCodec<murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid> CODEC;
    public static int DEFAULT_PRE_RAID_TICKS;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text DEFEAT_TITLE;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text EVENT_TEXT;
    public static int MAX_ACTIVE_TICKS;
    public static int MAX_DESPAWN_COUNTER;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text OMINOUS_BANNER_TRANSLATION_KEY;
    public static java.lang.String RAIDERS_REMAINING_TRANSLATION_KEY;
    public static java.lang.Object RAVAGER_SPAWN_LOCATION;
    public static int SQUARED_MAX_RAIDER_DISTANCE;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text VICTORY_TITLE;
    public boolean active;
    public murat.simv2.simulation.mirror.net.minecraft.entity.boss.ServerBossBar bar;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos center;
    public static int field_30669;
    public static int field_30671;
    public static int field_30672;
    public static int field_30673;
    public static int field_30674;
    public static int field_30676;
    public static int field_30680;
    public static int field_30681;
    public static int field_30682;
    public static int field_30685;
    public static int field_30687;
    public static int field_30688;
    public static int field_53977;
    public static int field_53978;
    public int finishCooldown;
    public java.util.Set<java.util.UUID> heroesOfTheVillage;
    public int postRaidTicks;
    public java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos> preCalculatedRaidersSpawnLocation;
    public int preRaidTicks;
    public int raidOmenLevel;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random random;
    public boolean started;
    public murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid.Status status;
    public long ticksActive;
    public float totalHealth;
    public int waveCount;
    public java.util.Map<java.lang.Integer, java.lang.Object> waveToCaptain;
    public java.util.Map<java.lang.Integer, java.util.Set<java.lang.Object>> waveToRaiders;
    public int wavesSpawned;

    public Raid(boolean p0, boolean p1, long p2, int p3, int p4, int p5, int p6, float p7, int p8, murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid.Status p9, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p10, java.util.Set p11) {
    }

    public Raid(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.world.Difficulty p1) {
    }

    public void addHero(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void addRaider(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, int p1, java.lang.Object p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3, boolean p4) {
    }

    public boolean addToWave(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, int p1, java.lang.Object p2) {
        return false;
    }

    public boolean addToWave(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, int p1, java.lang.Object p2, boolean p3) {
        return false;
    }

    public boolean canSpawnRaiders() {
        return false;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.item.ItemStack createOminousBanner(java.lang.Object p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos findRandomRaidersSpawnLocation(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, int p1) {
        return null;
    }

    public java.util.Set getAllRaiders() {
        return null;
    }

    public int getBadOmenLevel() {
        return 0;
    }

    public int getBonusCount(murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid.Member p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1, int p2, murat.simv2.simulation.mirror.net.minecraft.world.LocalDifficulty p3, boolean p4) {
        return 0;
    }

    public java.lang.Object getCaptain(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getCenter() {
        return null;
    }

    public int getCount(murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid.Member p0, int p1, boolean p2) {
        return 0;
    }

    public float getCurrentRaiderHealth() {
        return 0.0F;
    }

    public float getEnchantmentChance() {
        return 0.0F;
    }

    public int getGroupsSpawned() {
        return 0;
    }

    public int getMaxAcceptableBadOmenLevel() {
        return 0;
    }

    public int getMaxWaves(murat.simv2.simulation.mirror.net.minecraft.world.Difficulty p0) {
        return 0;
    }

    public int getRaiderCount() {
        return 0;
    }

    public java.util.Optional getRaidersSpawnLocation(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
        return null;
    }

    public float getTotalHealth() {
        return 0.0F;
    }

    public boolean hasExtraWave() {
        return false;
    }

    public boolean hasLost() {
        return false;
    }

    public boolean hasSpawnedExtraWave() {
        return false;
    }

    public boolean hasSpawnedFinalWave() {
        return false;
    }

    public boolean hasSpawned() {
        return false;
    }

    public boolean hasStarted() {
        return false;
    }

    public boolean hasStopped() {
        return false;
    }

    public boolean hasWon() {
        return false;
    }

    public void invalidate() {
    }

    public boolean isActive() {
        return false;
    }

    public boolean isFinished() {
        return false;
    }

    public java.util.function.Predicate isInRaidDistance() {
        return null;
    }

    public boolean isPreRaid() {
        return false;
    }

    public boolean isSpawningExtraWave() {
        return false;
    }

    public void markDirty(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public void moveRaidCenter(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public void playRaidHorn(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
    }

    public void removeFromWave(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, java.lang.Object p1, boolean p2) {
    }

    public void removeLeader(int p0) {
    }

    public void removeObsoleteRaiders(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public void setBadOmenLevel(int p0) {
    }

    public void setCenter(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public void setWaveCaptain(int p0, java.lang.Object p1) {
    }

    public boolean shouldSpawnMoreGroups() {
        return false;
    }

    public void spawnNextWave(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
    }

    public boolean start(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
        return false;
    }

    public void tick(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public void updateBarToPlayers(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public void updateBar() {
    }

    public Raid() {
    }

    public static class Member {
        public static murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid.Member EVOKER;
        public static murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid.Member PILLAGER;
        public static murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid.Member RAVAGER;
        public static murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid.Member[] VALUES;
        public static murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid.Member VINDICATOR;
        public static murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid.Member WITCH;
        public int[] countInWave;
        public static murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid.Member[] field_16632;
        public murat.simv2.simulation.mirror.net.minecraft.entity.EntityType<? extends java.lang.Object> type;

        public Member(java.lang.String p0, int p1, murat.simv2.simulation.mirror.net.minecraft.entity.EntityType p2, int[] p3) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid.Member valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid.Member[] values() {
            return null;
        }

        public Member() {
        }

    }

    public static class Status implements murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid.Status> CODEC;
        public static murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid.Status LOSS;
        public static murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid.Status ONGOING;
        public static murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid.Status STOPPED;
        public static murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid.Status VICTORY;
        public static murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid.Status[] field_19031;
        public java.lang.String id;

        public Status(java.lang.String p0, int p1, java.lang.String p2) {
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

        public int ordinal() {
            return 0;
        }

        public static com.mojang.serialization.Keyable toKeyable(murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable[] p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid.Status valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid.Status[] values() {
            return null;
        }

        public Status() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
