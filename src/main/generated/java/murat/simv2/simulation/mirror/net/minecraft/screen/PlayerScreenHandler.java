package murat.simv2.simulation.mirror.net.minecraft.screen;

// Generated mirror stub for simulation closure.
public class PlayerScreenHandler {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static int CRAFTING_INPUT_COUNT;
    public static int CRAFTING_INPUT_END;
    public static int CRAFTING_INPUT_START;
    public static int CRAFTING_RESULT_ID;
    public static java.util.Map<murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot, murat.simv2.simulation.mirror.net.minecraft.util.Identifier> EMPTY_ARMOR_SLOT_TEXTURES;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier EMPTY_BOOTS_SLOT_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier EMPTY_CHESTPLATE_SLOT_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier EMPTY_HELMET_SLOT_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier EMPTY_LEGGINGS_SLOT_TEXTURE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier EMPTY_OFF_HAND_SLOT_TEXTURE;
    public static int EMPTY_SPACE_SLOT_INDEX;
    public static int EQUIPMENT_COUNT;
    public static int EQUIPMENT_END;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot[] EQUIPMENT_SLOT_ORDER;
    public static int EQUIPMENT_START;
    public static int HOTBAR_END;
    public static int HOTBAR_START;
    public static int INVENTORY_END;
    public static int INVENTORY_START;
    public static int OFFHAND_ID;
    public java.lang.Object craftingInventory;
    public java.lang.Object craftingResultInventory;
    public static int field_30731;
    public static int field_30732;
    public static int field_30733;
    public static int field_30734;
    public static int field_30735;
    public static int field_30736;
    public static int field_30737;
    public static int field_30802;
    public static int field_52557;
    public static int field_52558;
    public static int field_52570;
    public static int field_52571;
    public boolean onServer;
    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity owner;
    public java.lang.Object slots;
    public int syncId;

    public PlayerScreenHandler(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerInventory p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p2) {
    }

    public void addInputSlots(int p0, int p1) {
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

    public murat.simv2.simulation.mirror.net.minecraft.screen.slot.Slot addResultSlot(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, int p1, int p2) {
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

    public static void checkDataCount(java.lang.Object p0, int p1) {
    }

    public static void checkSize(murat.simv2.simulation.mirror.net.minecraft.inventory.Inventory p0, int p1) {
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

    public java.lang.Object fillInputSlots(boolean p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.recipe.RecipeEntry p2, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p3, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerInventory p4) {
        return null;
    }

    public java.lang.Object getCategory() {
        return null;
    }

    public java.lang.Object getCraftingInput() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getCursorStack() {
        return null;
    }

    public int getHeight() {
        return 0;
    }

    public java.util.List getInputSlots() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.screen.slot.Slot getOutputSlot() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getPlayer() {
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

    public int getWidth() {
        return 0;
    }

    public boolean insertItem(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, int p1, int p2, boolean p3) {
        return false;
    }

    public static boolean isInHotbar(int p0) {
        return false;
    }

    public boolean isValid(int p0) {
        return false;
    }

    public int nextRevision() {
        return 0;
    }

    public boolean onButtonClick(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, int p1) {
        return false;
    }

    public void onClosed(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
    }

    public void onContentChanged(murat.simv2.simulation.mirror.net.minecraft.inventory.Inventory p0) {
    }

    public void onInputSlotFillFinish(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.recipe.RecipeEntry p1) {
    }

    public void onInputSlotFillStart() {
    }

    public void onSlotClick(int p0, int p1, java.lang.Object p2, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p3) {
    }

    public static int packQuickCraftData(int p0, int p1) {
        return 0;
    }

    public void populateRecipeFinder(java.lang.Object p0) {
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

    public PlayerScreenHandler() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
