package murat.simv2.simulation.mirror.net.minecraft.block.entity;

// Generated mirror stub for simulation closure.
public class CommandBlockBlockEntity extends murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static boolean DEFAULT_AUTO;
    public static boolean DEFAULT_CONDITION_MET;
    public static boolean DEFAULT_POWERED;
    public boolean auto;
    public murat.simv2.simulation.mirror.net.minecraft.world.CommandBlockExecutor commandExecutor;
    public boolean conditionMet;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos pos;
    public boolean powered;
    public boolean removed;
    public murat.simv2.simulation.mirror.net.minecraft.world.World world;

    public CommandBlockBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1) {
    }

    public void addComponents(murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap.Builder p0) {
    }

    public void cancelRemoval() {
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

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState getCachedState() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.entity.CommandBlockBlockEntity.Type getCommandBlockType() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.CommandBlockExecutor getCommandExecutor() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap getComponents() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getPos() {
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

    public boolean isAuto() {
        return false;
    }

    public boolean isConditionMet() {
        return false;
    }

    public boolean isConditionalCommandBlock() {
        return false;
    }

    public boolean isPowered() {
        return false;
    }

    public boolean isRemoved() {
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

    public void scheduleAutoTick() {
    }

    public void setAuto(boolean p0) {
    }

    public void setCachedState(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
    }

    public void setComponents(murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap p0) {
    }

    public void setPowered(boolean p0) {
    }

    public void setWorld(murat.simv2.simulation.mirror.net.minecraft.world.World p0) {
    }

    public boolean supports(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound toInitialChunkDataNbt(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet toUpdatePacket() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.Text tryParseCustomName(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtElement p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p1) {
        return null;
    }

    public void updateCommandBlock() {
    }

    public boolean updateConditionMet() {
        return false;
    }

    public static void writeIdToNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntityType p1) {
    }

    public void writeNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p1) {
    }

    public CommandBlockBlockEntity() {
    }

    public static class Type {
        public static murat.simv2.simulation.mirror.net.minecraft.block.entity.CommandBlockBlockEntity.Type AUTO;
        public static murat.simv2.simulation.mirror.net.minecraft.block.entity.CommandBlockBlockEntity.Type REDSTONE;
        public static murat.simv2.simulation.mirror.net.minecraft.block.entity.CommandBlockBlockEntity.Type SEQUENCE;
        public static murat.simv2.simulation.mirror.net.minecraft.block.entity.CommandBlockBlockEntity.Type[] field_11925;

        public Type(java.lang.String p0, int p1) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.block.entity.CommandBlockBlockEntity.Type valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.block.entity.CommandBlockBlockEntity.Type[] values() {
            return null;
        }

        public Type() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
