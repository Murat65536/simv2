package murat.simv2.simulation.mirror.net.minecraft.entity.player;

// Generated mirror stub for simulation closure.
public class ItemCooldownManager extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, murat.simv2.simulation.mirror.net.minecraft.entity.player.ItemCooldownManager.Entry> entries;
    public int tick;

    public ItemCooldownManager() {
    }

    public float getCooldownProgress(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, float p1) {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier getGroup(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return null;
    }

    public boolean isCoolingDown(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return false;
    }

    public void onCooldownUpdate(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
    }

    public void onCooldownUpdate(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, int p1) {
    }

    public void remove(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
    }

    public void set(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, int p1) {
    }

    public void set(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, int p1) {
    }

    public void update() {
    }

    public static class Entry {
        public int endTick;
        public int startTick;

        public Entry(int p0, int p1) {
        }

        public int endTick() {
            return 0;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public int startTick() {
            return 0;
        }

        public java.lang.String toString() {
            return null;
        }

        public Entry() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
