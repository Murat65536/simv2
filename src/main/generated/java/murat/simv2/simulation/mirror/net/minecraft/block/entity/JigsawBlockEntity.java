package murat.simv2.simulation.mirror.net.minecraft.block.entity;

// Generated mirror stub for simulation closure.
public class JigsawBlockEntity extends murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.lang.String DEFAULT_FINAL_STATE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier DEFAULT_NAME;
    public static int DEFAULT_PLACEMENT_PRIORITY;
    public static int DEFAULT_SELECTION_PRIORITY;
    public static java.lang.String FINAL_STATE_KEY;
    public static java.lang.String JOINT_KEY;
    public static java.lang.String NAME_KEY;
    public static java.lang.String PLACEMENT_PRIORITY_KEY;
    public static java.lang.String POOL_KEY;
    public static java.lang.String SELECTION_PRIORITY_KEY;
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<java.lang.Object>> STRUCTURE_POOL_KEY_CODEC;
    public static java.lang.String TARGET_KEY;
    public java.lang.String finalState;
    public murat.simv2.simulation.mirror.net.minecraft.block.entity.JigsawBlockEntity.Joint joint;
    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier name;
    public int placementPriority;
    public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<java.lang.Object> pool;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos pos;
    public boolean removed;
    public int selectionPriority;
    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier target;
    public murat.simv2.simulation.mirror.net.minecraft.world.World world;

    public JigsawBlockEntity(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1) {
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

    public void generate(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, int p1, boolean p2) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState getCachedState() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap getComponents() {
        return null;
    }

    public java.lang.String getFinalState() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.entity.JigsawBlockEntity.Joint getJoint() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier getName() {
        return null;
    }

    public int getPlacementPriority() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey getPool() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getPos() {
        return null;
    }

    public int getSelectionPriority() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier getTarget() {
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

    public void setCachedState(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
    }

    public void setComponents(murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap p0) {
    }

    public void setFinalState(java.lang.String p0) {
    }

    public void setJoint(murat.simv2.simulation.mirror.net.minecraft.block.entity.JigsawBlockEntity.Joint p0) {
    }

    public void setName(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
    }

    public void setPlacementPriority(int p0) {
    }

    public void setPool(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
    }

    public void setSelectionPriority(int p0) {
    }

    public void setTarget(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
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

    public static void writeIdToNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntityType p1) {
    }

    public void writeNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p1) {
    }

    public JigsawBlockEntity() {
    }

    public static class Joint implements murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable {
        public static murat.simv2.simulation.mirror.net.minecraft.block.entity.JigsawBlockEntity.Joint ALIGNED;
        public static murat.simv2.simulation.mirror.net.minecraft.util.StringIdentifiable.EnumCodec<murat.simv2.simulation.mirror.net.minecraft.block.entity.JigsawBlockEntity.Joint> CODEC;
        public static murat.simv2.simulation.mirror.net.minecraft.block.entity.JigsawBlockEntity.Joint ROLLABLE;
        public static murat.simv2.simulation.mirror.net.minecraft.block.entity.JigsawBlockEntity.Joint[] field_23332;
        public java.lang.String name;

        public Joint(java.lang.String p0, int p1, java.lang.String p2) {
        }

        public java.lang.String asString() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.text.Text asText() {
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

        public static murat.simv2.simulation.mirror.net.minecraft.block.entity.JigsawBlockEntity.Joint valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.block.entity.JigsawBlockEntity.Joint[] values() {
            return null;
        }

        public Joint() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
