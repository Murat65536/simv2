package murat.simv2.simulation.mirror.net.minecraft.world;

// Generated mirror stub for simulation closure.
public class CommandBlockExecutor extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.server.command.CommandOutput {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.text.SimpleDateFormat DATE_FORMAT;
    public static int DEFAULT_LAST_EXECUTION;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text DEFAULT_NAME;
    public java.lang.String command;
    public murat.simv2.simulation.mirror.net.minecraft.text.Text customName;
    public long lastExecution;
    public murat.simv2.simulation.mirror.net.minecraft.text.Text lastOutput;
    public int successCount;
    public boolean trackOutput;
    public boolean updateLastExecution;

    public CommandBlockExecutor() {
    }

    public boolean cannotBeSilenced() {
        return false;
    }

    public boolean execute(murat.simv2.simulation.mirror.net.minecraft.world.World p0) {
        return false;
    }

    public java.lang.String getCommand() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getCustomName() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getLastOutput() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getName() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getPos() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource getSource() {
        return null;
    }

    public int getSuccessCount() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld getWorld() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult interact(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
        return null;
    }

    public boolean isEditable() {
        return false;
    }

    public boolean isTrackingOutput() {
        return false;
    }

    public void markDirty() {
    }

    public void readNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p1) {
    }

    public void sendMessage(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public void setCommand(java.lang.String p0) {
    }

    public void setCustomName(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public void setLastOutput(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public void setSuccessCount(int p0) {
    }

    public void setTrackOutput(boolean p0) {
    }

    public boolean shouldBroadcastConsoleToOps() {
        return false;
    }

    public boolean shouldReceiveFeedback() {
        return false;
    }

    public boolean shouldTrackOutput() {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound writeNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p1) {
        return null;
    }

    // END GENERATED MIRROR NESTED STUBS
}
