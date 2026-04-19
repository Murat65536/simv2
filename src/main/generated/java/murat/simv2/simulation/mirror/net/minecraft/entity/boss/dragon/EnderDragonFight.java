package murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon;

// Generated mirror stub for simulation closure.
public class EnderDragonFight extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static int CHECK_DRAGON_SEEN_INTERVAL;
    public static int CRYSTAL_COUNTING_INTERVAL;
    public static int ISLAND_SIZE;
    public static org.slf4j.Logger LOGGER;
    public static int PLAYER_COUNTING_INTERVAL;
    public static int SPAWN_Y;
    public murat.simv2.simulation.mirror.net.minecraft.entity.boss.ServerBossBar bossBar;
    public int crystalCountTimer;
    public java.util.List<java.lang.Object> crystals;
    public boolean doLegacyCheck;
    public boolean dragonKilled;
    public int dragonSeenTimer;
    public java.lang.Object dragonSpawnState;
    public java.util.UUID dragonUuid;
    public int endCrystalsAlive;
    public java.lang.Object endPortalPattern;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos exitPortalLocation;
    public static int field_31441;
    public static int field_31445;
    public static int field_31448;
    public it.unimi.dsi.fastutil.objects.ObjectArrayList<java.lang.Integer> gateways;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos origin;
    public int playerUpdateTimer;
    public boolean previouslyKilled;
    public java.util.function.Predicate<murat.simv2.simulation.mirror.net.minecraft.entity.Entity> showBossBarPredicate;
    public boolean skipChunksLoadedCheck;
    public int spawnStateTimer;
    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld world;

    public EnderDragonFight(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, long p1, murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonFight.Data p2) {
    }

    public EnderDragonFight(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, long p1, murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonFight.Data p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3) {
    }

    public boolean areChunksLoaded() {
        return false;
    }

    public void checkDragonSeen() {
    }

    public void clearGatewaysList() {
    }

    public void convertFromLegacy() {
    }

    public void countAliveCrystals() {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonEntity createDragon() {
        return null;
    }

    public void crystalDestroyed(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1) {
    }

    public void dragonKilled(murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonEntity p0) {
    }

    public java.lang.Object findEndPortal() {
        return null;
    }

    public void generateEndGateway(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public void generateEndPortal(boolean p0) {
    }

    public void generateNewEndGateway() {
    }

    public int getAliveEndCrystals() {
        return 0;
    }

    public java.util.UUID getDragonUuid() {
        return null;
    }

    public boolean hasPreviouslyKilled() {
        return false;
    }

    public void resetEndCrystals() {
    }

    public void respawnDragon() {
    }

    public void respawnDragon(java.util.List p0) {
    }

    public void setSkipChunksLoadedCheck() {
    }

    public void setSpawnState(java.lang.Object p0) {
    }

    public void tick() {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonFight.Data toData() {
        return null;
    }

    public void updateFight(murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonEntity p0) {
    }

    public void updatePlayers() {
    }

    public boolean worldContainsEndPortal() {
        return false;
    }

    public EnderDragonFight() {
    }

    public static class Data {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonFight.Data> CODEC;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonFight.Data DEFAULT;
        public boolean dragonKilled;
        public java.util.Optional<java.util.UUID> dragonUUID;
        public java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos> exitPortalLocation;
        public java.util.Optional<java.util.List<java.lang.Integer>> gateways;
        public boolean isRespawning;
        public boolean needsStateScanning;
        public boolean previouslyKilled;

        public Data(boolean p0, boolean p1, boolean p2, boolean p3, java.util.Optional p4, java.util.Optional p5, java.util.Optional p6) {
        }

        public boolean dragonKilled() {
            return false;
        }

        public java.util.Optional dragonUUID() {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public java.util.Optional exitPortalLocation() {
            return null;
        }

        public java.util.Optional gateways() {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public boolean isRespawning() {
            return false;
        }

        public boolean needsStateScanning() {
            return false;
        }

        public boolean previouslyKilled() {
            return false;
        }

        public java.lang.String toString() {
            return null;
        }

        public Data() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
