package murat.simv2.simulation.mirror.net.minecraft.screen.slot;

// Generated mirror stub for simulation closure.
public class Slot extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public int id;
    public int index;
    public murat.simv2.simulation.mirror.net.minecraft.inventory.Inventory inventory;
    public int x;
    public int y;

    public Slot(murat.simv2.simulation.mirror.net.minecraft.inventory.Inventory p0, int p1, int p2, int p3) {
    }

    public boolean canBeHighlighted() {
        return false;
    }

    public boolean canInsert(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return false;
    }

    public boolean canTakeItems(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
        return false;
    }

    public boolean canTakePartial(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
        return false;
    }

    public boolean disablesDynamicDisplay() {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier getBackgroundSprite() {
        return null;
    }

    public int getIndex() {
        return 0;
    }

    public int getMaxItemCount() {
        return 0;
    }

    public int getMaxItemCount(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getStack() {
        return null;
    }

    public boolean hasStack() {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack insertStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack insertStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, int p1) {
        return null;
    }

    public boolean isEnabled() {
        return false;
    }

    public void markDirty() {
    }

    public void onCrafted(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public void onCrafted(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, int p1) {
    }

    public void onQuickTransfer(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
    }

    public void onTakeItem(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
    }

    public void onTake(int p0) {
    }

    public void setStackNoCallbacks(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public void setStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public void setStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack takeStackRange(int p0, int p1, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack takeStack(int p0) {
        return null;
    }

    public java.util.Optional tryTakeStackRange(int p0, int p1, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p2) {
        return null;
    }

    public Slot() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
