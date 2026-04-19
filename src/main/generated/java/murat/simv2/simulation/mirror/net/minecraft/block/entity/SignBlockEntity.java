package murat.simv2.simulation.mirror.net.minecraft.block.entity;

// Generated mirror stub for simulation closure.
public class SignBlockEntity extends murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static boolean DEFAULT_WAXED;
    public static org.slf4j.Logger LOGGER;
    public static int MAX_TEXT_WIDTH;
    public static int TEXT_LINE_HEIGHT;
    public java.lang.Object backText;
    public java.util.UUID editor;
    public java.lang.Object frontText;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos pos;
    public boolean removed;
    public boolean waxed;
    public murat.simv2.simulation.mirror.net.minecraft.world.World world;

    public SignBlockEntity(murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntityType p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2) {
    }

    public SignBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1) {
    }

    public void addComponents(murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap.Builder p0) {
    }

    public boolean canRunCommandClickEvent(boolean p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1) {
        return false;
    }

    public void cancelRemoval() {
    }

    public boolean changeText(java.util.function.UnaryOperator p0, boolean p1) {
        return false;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource createCommandSource(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap createComponentMap() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound createComponentlessNbtWithIdentifyingData(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound createComponentlessNbt(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity createFromNbt(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p2, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p3) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound createNbtWithIdentifyingData(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound createNbtWithId(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound createNbt(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p0) {
        return null;
    }

    public java.lang.Object createText() {
        return null;
    }

    public java.lang.Object getBackText() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState getCachedState() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap getComponents() {
        return null;
    }

    public java.util.UUID getEditor() {
        return null;
    }

    public java.lang.Object getFrontText() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent getInteractionFailSound() {
        return null;
    }

    public int getMaxTextWidth() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getPos() {
        return null;
    }

    public int getTextLineHeight() {
        return 0;
    }

    public java.lang.Object getTextWithMessages(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, java.util.List p1, java.lang.Object p2) {
        return null;
    }

    public java.lang.Object getText(boolean p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntityType getType() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.World getWorld() {
        return null;
    }

    public boolean hasWorld() {
        return false;
    }

    public boolean isPlayerFacingFront(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
        return false;
    }

    public boolean isPlayerTooFarToEdit(java.util.UUID p0) {
        return false;
    }

    public boolean isRemoved() {
        return false;
    }

    public boolean isWaxed() {
        return false;
    }

    public void markDirty() {
    }

    public static void markDirty(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2) {
    }

    public void markRemoved() {
    }

    public void onBlockReplaced(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1) {
    }

    public boolean onSyncedBlockEvent(int p0, int p1) {
        return false;
    }

    public java.lang.Object parseLines(java.lang.Object p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text parseLine(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
        return null;
    }

    public void populateCrashReport(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection p0) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos posFromNbt(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p1) {
        return null;
    }

    public void readComponentlessNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p1) {
    }

    public void readComponents(murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap p0, murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges p1) {
    }

    public void readComponents(murat.simv2.simulation.mirror.net.minecraft.component.ComponentsAccess p0) {
    }

    public void readComponents(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public void readNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p1) {
    }

    public void read(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p1) {
    }

    public void removeFromCopiedStackNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    public boolean runCommandClickEvent(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, boolean p3) {
        return false;
    }

    public boolean setBackText(java.lang.Object p0) {
        return false;
    }

    public void setCachedState(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
    }

    public void setComponents(murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap p0) {
    }

    public void setEditor(java.util.UUID p0) {
    }

    public boolean setFrontText(java.lang.Object p0) {
        return false;
    }

    public boolean setText(java.lang.Object p0, boolean p1) {
        return false;
    }

    public boolean setWaxed(boolean p0) {
        return false;
    }

    public void setWorld(murat.simv2.simulation.mirror.net.minecraft.world.World p0) {
    }

    public boolean supports(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return false;
    }

    public static void tick(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2, murat.simv2.simulation.mirror.net.minecraft.block.entity.SignBlockEntity p3) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound toInitialChunkDataNbt(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet toUpdatePacket() {
        return null;
    }

    public void tryChangeText(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, boolean p1, java.util.List p2) {
    }

    public void tryClearInvalidEditor(murat.simv2.simulation.mirror.net.minecraft.block.entity.SignBlockEntity p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, java.util.UUID p2) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.Text tryParseCustomName(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtElement p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p1) {
        return null;
    }

    public void updateListeners() {
    }

    public static void writeIdToNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntityType p1) {
    }

    public void writeNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p1) {
    }

    public SignBlockEntity() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
