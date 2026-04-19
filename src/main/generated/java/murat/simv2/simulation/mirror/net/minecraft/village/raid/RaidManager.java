package murat.simv2.simulation.mirror.net.minecraft.village.raid;

// Generated mirror stub for simulation closure.
public class RaidManager {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.village.raid.RaidManager> CODEC;
    public static java.lang.Object END_STATE_TYPE;
    public static java.lang.String RAIDS;
    public static java.lang.Object STATE_TYPE;
    public int currentTime;
    public int nextAvailableId;
    public it.unimi.dsi.fastutil.ints.Int2ObjectMap<murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid> raids;

    public RaidManager() {
    }

    public RaidManager(java.util.List p0, int p1, int p2) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.village.raid.RaidManager fromNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid getOrCreateRaid(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return null;
    }

    public static java.lang.Object getPersistentStateType(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid getRaidAt(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1) {
        return null;
    }

    public java.util.OptionalInt getRaidId(murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid getRaid(int p0) {
        return null;
    }

    public boolean isDirty() {
        return false;
    }

    public static boolean isValidRaiderFor(java.lang.Object p0) {
        return false;
    }

    public void markDirty() {
    }

    public int nextId() {
        return 0;
    }

    public void setDirty(boolean p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid startRaid(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return null;
    }

    public void tick(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public static class RaidWithId {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.village.raid.RaidManager.RaidWithId> CODEC;
        public int id;
        public murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid raid;

        public RaidWithId(int p0, murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.village.raid.RaidManager.RaidWithId fromMapEntry(it.unimi.dsi.fastutil.ints.Int2ObjectMap.Entry p0) {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public int id() {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.village.raid.Raid raid() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public RaidWithId() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
