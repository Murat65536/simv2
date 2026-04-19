package murat.simv2.simulation.mirror.net.minecraft.screen;

// Generated mirror stub for simulation closure.
public class ScreenHandler extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static int EMPTY_SPACE_SLOT_INDEX;
    public static org.slf4j.Logger LOGGER;
    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack cursorStack;
    public boolean disableSync;
    public static int field_30731;
    public static int field_30732;
    public static int field_30733;
    public static int field_30734;
    public static int field_30735;
    public static int field_30736;
    public static int field_30737;
    public static int field_52557;
    public static int field_52558;
    public java.util.List<murat.simv2.simulation.mirror.net.minecraft.screen.ScreenHandlerListener> listeners;
    public java.util.List<java.lang.Object> properties;
    public int quickCraftButton;
    public java.util.Set<murat.simv2.simulation.mirror.net.minecraft.screen.slot.Slot> quickCraftSlots;
    public int quickCraftStage;
    public int revision;
    public java.lang.Object slots;
    public murat.simv2.simulation.mirror.net.minecraft.screen.ScreenHandlerSyncHandler syncHandler;
    public int syncId;
    public java.lang.Object trackedCursorSlot;
    public it.unimi.dsi.fastutil.ints.IntList trackedPropertyValues;
    public java.lang.Object trackedSlots;
    public java.lang.Object trackedStacks;
    public java.lang.Object type;

    public ScreenHandler(java.lang.Object p0, int p1) {
    }

    public void addListener(murat.simv2.simulation.mirror.net.minecraft.screen.ScreenHandlerListener p0) {
    }

    public void addPlayerHotbarSlots(murat.simv2.simulation.mirror.net.minecraft.inventory.Inventory p0, int p1, int p2) {
    }

    public void addPlayerInventorySlots(murat.simv2.simulation.mirror.net.minecraft.inventory.Inventory p0, int p1, int p2) {
    }

    public void addPlayerSlots(murat.simv2.simulation.mirror.net.minecraft.inventory.Inventory p0, int p1, int p2) {
    }

    public void addProperties(java.lang.Object p0) {
    }

    public java.lang.Object addProperty(java.lang.Object p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.screen.slot.Slot addSlot(murat.simv2.simulation.mirror.net.minecraft.screen.slot.Slot p0) {
        return null;
    }

    public static int calculateComparatorOutput(murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity p0) {
        return 0;
    }

    public static int calculateComparatorOutput(murat.simv2.simulation.mirror.net.minecraft.inventory.Inventory p0) {
        return 0;
    }

    public static int calculateStackSize(java.util.Set p0, int p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2) {
        return 0;
    }

    public boolean canInsertIntoSlot(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.screen.slot.Slot p1) {
        return false;
    }

    public boolean canInsertIntoSlot(murat.simv2.simulation.mirror.net.minecraft.screen.slot.Slot p0) {
        return false;
    }

    public static boolean canInsertItemIntoSlot(murat.simv2.simulation.mirror.net.minecraft.screen.slot.Slot p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1, boolean p2) {
        return false;
    }

    public static boolean canUse(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, murat.simv2.simulation.mirror.net.minecraft.block.Block p2) {
        return false;
    }

    public boolean canUse(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
        return false;
    }

    public void checkCursorStackUpdates() {
    }

    public static void checkDataCount(java.lang.Object p0, int p1) {
    }

    public void checkPropertyUpdates(int p0, int p1) {
    }

    public static void checkSize(murat.simv2.simulation.mirror.net.minecraft.inventory.Inventory p0, int p1) {
    }

    public void checkSlotUpdates(int p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1, java.util.function.Supplier p2) {
    }

    public void copySharedSlots(murat.simv2.simulation.mirror.net.minecraft.screen.ScreenHandler p0) {
    }

    public void disableSyncing() {
    }

    public void dropInventory(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.inventory.Inventory p1) {
    }

    public void enableSyncing() {
    }

    public void endQuickCraft() {
    }

    public murat.simv2.simulation.mirror.net.minecraft.inventory.StackReference getCursorStackReference() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getCursorStack() {
        return null;
    }

    public int getRevision() {
        return 0;
    }

    public java.util.OptionalInt getSlotIndex(murat.simv2.simulation.mirror.net.minecraft.inventory.Inventory p0, int p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.screen.slot.Slot getSlot(int p0) {
        return null;
    }

    public java.lang.Object getStacks() {
        return null;
    }

    public java.lang.Object getType() {
        return null;
    }

    public boolean handleSlotClick(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.ClickType p1, murat.simv2.simulation.mirror.net.minecraft.screen.slot.Slot p2, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p3, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p4) {
        return false;
    }

    public boolean insertItem(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, int p1, int p2, boolean p3) {
        return false;
    }

    public void internalOnSlotClick(int p0, int p1, java.lang.Object p2, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p3) {
    }

    public boolean isValid(int p0) {
        return false;
    }

    public int nextRevision() {
        return 0;
    }

    public void notifyPropertyUpdate(int p0, int p1) {
    }

    public static void offerOrDropStack(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
    }

    public boolean onButtonClick(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, int p1) {
        return false;
    }

    public void onClosed(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
    }

    public void onContentChanged(murat.simv2.simulation.mirror.net.minecraft.inventory.Inventory p0) {
    }

    public void onSlotClick(int p0, int p1, java.lang.Object p2, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p3) {
    }

    public static int packQuickCraftData(int p0, int p1) {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack quickMove(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, int p1) {
        return null;
    }

    public void removeListener(murat.simv2.simulation.mirror.net.minecraft.screen.ScreenHandlerListener p0) {
    }

    public void selectBundleStack(int p0, int p1) {
    }

    public void sendContentUpdates() {
    }

    public void setCursorStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public void setProperty(int p0, int p1) {
    }

    public void setReceivedCursorHash(java.lang.Object p0) {
    }

    public void setReceivedHash(int p0, java.lang.Object p1) {
    }

    public void setReceivedStack(int p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
    }

    public void setStackInSlot(int p0, int p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2) {
    }

    public static boolean shouldQuickCraftContinue(int p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1) {
        return false;
    }

    public void syncState() {
    }

    public static int unpackQuickCraftButton(int p0) {
        return 0;
    }

    public static int unpackQuickCraftStage(int p0) {
        return 0;
    }

    public void updateSlotStacks(int p0, java.util.List p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2) {
    }

    public void updateSyncHandler(murat.simv2.simulation.mirror.net.minecraft.screen.ScreenHandlerSyncHandler p0) {
    }

    public void updateToClient() {
    }

    public void updateTrackedSlot(int p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1, java.util.function.Supplier p2) {
    }

    public ScreenHandler() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
