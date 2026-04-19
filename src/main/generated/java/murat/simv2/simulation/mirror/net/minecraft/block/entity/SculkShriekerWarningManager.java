package murat.simv2.simulation.mirror.net.minecraft.block.entity;

// Generated mirror stub for simulation closure.
public class SculkShriekerWarningManager extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.block.entity.SculkShriekerWarningManager> CODEC;
    public static int MAX_WARNING_LEVEL;
    public static int WARN_DECREASE_COOLDOWN;
    public static int WARN_INCREASE_COOLDOWN;
    public static double WARN_RANGE;
    public static int WARN_WARDEN_RANGE;
    public int cooldownTicks;
    public int ticksSinceLastWarning;
    public int warningLevel;

    public SculkShriekerWarningManager() {
    }

    public SculkShriekerWarningManager(int p0, int p1, int p2) {
    }

    public void copy(murat.simv2.simulation.mirror.net.minecraft.block.entity.SculkShriekerWarningManager p0) {
    }

    public void decreaseWarningLevel() {
    }

    public static java.util.List getPlayersInRange(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return null;
    }

    public int getWarningLevel() {
        return 0;
    }

    public void increaseWarningLevel() {
    }

    public boolean isInCooldown() {
        return false;
    }

    public static boolean isWardenNearby(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public void reset() {
    }

    public void setWarningLevel(int p0) {
    }

    public void tick() {
    }

    public static java.util.OptionalInt warnNearbyPlayers(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p2) {
        return null;
    }

    // END GENERATED MIRROR NESTED STUBS
}
