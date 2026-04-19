package murat.simv2.simulation.mirror.net.minecraft.client.network;

// Generated mirror stub for simulation closure.
public class ClientPlayerInteractionManager extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static org.slf4j.Logger LOGGER;
    public int blockBreakingCooldown;
    public float blockBreakingSoundCooldown;
    public boolean breakingBlock;
    public murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient client;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos currentBreakingPos;
    public float currentBreakingProgress;
    public murat.simv2.simulation.mirror.net.minecraft.world.GameMode gameMode;
    public int lastSelectedSlot;
    public murat.simv2.simulation.mirror.net.minecraft.client.network.ClientPlayNetworkHandler networkHandler;
    public murat.simv2.simulation.mirror.net.minecraft.world.GameMode previousGameMode;
    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack selectedStack;

    public ClientPlayerInteractionManager(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p0, murat.simv2.simulation.mirror.net.minecraft.client.network.ClientPlayNetworkHandler p1) {
    }

    public boolean attackBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1) {
        return false;
    }

    public void attackEntity(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
    }

    public boolean breakBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public void cancelBlockBreaking() {
    }

    public void clickButton(int p0, int p1) {
    }

    public void clickCreativeStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, int p1) {
    }

    public void clickRecipe(int p0, murat.simv2.simulation.mirror.net.minecraft.recipe.NetworkRecipeId p1, boolean p2) {
    }

    public void clickSlot(int p0, int p1, int p2, java.lang.Object p3, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p4) {
    }

    public void copyAbilities(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.network.ClientPlayerEntity createPlayer(murat.simv2.simulation.mirror.net.minecraft.client.world.ClientWorld p0, murat.simv2.simulation.mirror.net.minecraft.stat.StatHandler p1, murat.simv2.simulation.mirror.net.minecraft.client.recipebook.ClientRecipeBook p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.network.ClientPlayerEntity createPlayer(murat.simv2.simulation.mirror.net.minecraft.client.world.ClientWorld p0, murat.simv2.simulation.mirror.net.minecraft.stat.StatHandler p1, murat.simv2.simulation.mirror.net.minecraft.client.recipebook.ClientRecipeBook p2, boolean p3, boolean p4) {
        return null;
    }

    public void dropCreativeStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public int getBlockBreakingProgress() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.GameMode getCurrentGameMode() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.GameMode getPreviousGameMode() {
        return null;
    }

    public boolean hasExperienceBar() {
        return false;
    }

    public boolean hasLimitedAttackSpeed() {
        return false;
    }

    public boolean hasRidingInventory() {
        return false;
    }

    public boolean hasStatusBars() {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult interactBlockInternal(murat.simv2.simulation.mirror.net.minecraft.client.network.ClientPlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.Hand p1, murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult interactBlock(murat.simv2.simulation.mirror.net.minecraft.client.network.ClientPlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.Hand p1, murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult interactEntityAtLocation(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1, murat.simv2.simulation.mirror.net.minecraft.util.hit.EntityHitResult p2, murat.simv2.simulation.mirror.net.minecraft.util.Hand p3) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult interactEntity(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1, murat.simv2.simulation.mirror.net.minecraft.util.Hand p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult interactItem(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.Hand p1) {
        return null;
    }

    public boolean isBreakingBlock() {
        return false;
    }

    public boolean isCurrentlyBreaking(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean isFlyingLocked() {
        return false;
    }

    public void pickItemFromBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, boolean p1) {
    }

    public void pickItemFromEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, boolean p1) {
    }

    public void sendSequencedPacket(murat.simv2.simulation.mirror.net.minecraft.client.world.ClientWorld p0, java.lang.Object p1) {
    }

    public void setGameModes(murat.simv2.simulation.mirror.net.minecraft.world.GameMode p0, murat.simv2.simulation.mirror.net.minecraft.world.GameMode p1) {
    }

    public void setGameMode(murat.simv2.simulation.mirror.net.minecraft.world.GameMode p0) {
    }

    public void slotChangedState(int p0, int p1, boolean p2) {
    }

    public void stopUsingItem(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
    }

    public void syncSelectedSlot() {
    }

    public void tick() {
    }

    public boolean updateBlockBreakingProgress(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1) {
        return false;
    }

    public ClientPlayerInteractionManager() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
