package murat.simv2.simulation.mirror.net.minecraft.inventory;

// Generated mirror stub for simulation closure.
public interface Inventory {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static final float DEFAULT_MAX_INTERACTION_RANGE = 0.0F;

    public static boolean canPlayerUse(murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1) {
        return false;
    }

    public static boolean canPlayerUse(murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, float p2) {
        return false;
    }

    public boolean canPlayerUse(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0);

    public boolean canTransferTo(murat.simv2.simulation.mirror.net.minecraft.inventory.Inventory p0, int p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2);

    public void clear();

    public boolean containsAny(java.util.Set p0);

    public boolean containsAny(java.util.function.Predicate p0);

    public int count(murat.simv2.simulation.mirror.net.minecraft.item.Item p0);

    public int getMaxCountPerStack();

    public int getMaxCount(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0);

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getStack(int p0);

    public boolean isEmpty();

    public boolean isValid(int p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1);

    public java.util.Iterator iterator();

    public void markDirty();

    public void onClose(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0);

    public void onOpen(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0);

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack removeStack(int p0);

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack removeStack(int p0, int p1);

    public void setStack(int p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1);

    public int size();

    public static class Iterator extends java.lang.Object {
        public int index;
        public murat.simv2.simulation.mirror.net.minecraft.inventory.Inventory inventory;
        public int size;

        public Iterator(murat.simv2.simulation.mirror.net.minecraft.inventory.Inventory p0) {
        }

        public boolean hasNext() {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack next() {
            return null;
        }

        public Iterator() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
