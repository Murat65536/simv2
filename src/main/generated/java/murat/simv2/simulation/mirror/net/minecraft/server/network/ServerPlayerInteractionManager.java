package murat.simv2.simulation.mirror.net.minecraft.server.network;

// Generated mirror stub for simulation closure.
public class ServerPlayerInteractionManager extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static org.slf4j.Logger LOGGER;
    public int blockBreakingProgress;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos failedMiningPos;
    public int failedStartMiningTime;
    public boolean failedToMine;
    public murat.simv2.simulation.mirror.net.minecraft.world.GameMode gameMode;
    public boolean mining;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos miningPos;
    public murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity player;
    public murat.simv2.simulation.mirror.net.minecraft.world.GameMode previousGameMode;
    public int startMiningTime;
    public int tickCounter;
    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld world;

    public ServerPlayerInteractionManager(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
    }

    public boolean changeGameMode(murat.simv2.simulation.mirror.net.minecraft.world.GameMode p0) {
        return false;
    }

    public float continueMining(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, int p2) {
        return 0.0F;
    }

    public void finishMining(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1, java.lang.String p2) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.GameMode getGameMode() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.GameMode getPreviousGameMode() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult interactBlock(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2, murat.simv2.simulation.mirror.net.minecraft.util.Hand p3, murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult p4) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult interactItem(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2, murat.simv2.simulation.mirror.net.minecraft.util.Hand p3) {
        return null;
    }

    public boolean isCreative() {
        return false;
    }

    public boolean isSurvivalLike() {
        return false;
    }

    public void onBlockBreakingAction(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, boolean p1, int p2, java.lang.String p3) {
    }

    public void processBlockBreakingAction(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p2, int p3, int p4) {
    }

    public void setGameMode(murat.simv2.simulation.mirror.net.minecraft.world.GameMode p0, murat.simv2.simulation.mirror.net.minecraft.world.GameMode p1) {
    }

    public void setWorld(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public boolean tryBreakBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public void update() {
    }

    public ServerPlayerInteractionManager() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
