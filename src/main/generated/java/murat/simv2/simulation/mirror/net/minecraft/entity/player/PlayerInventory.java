package murat.simv2.simulation.mirror.net.minecraft.entity.player;

// Generated mirror stub for simulation closure.
public class PlayerInventory extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.inventory.Inventory, murat.simv2.simulation.mirror.net.minecraft.util.Nameable {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static it.unimi.dsi.fastutil.ints.Int2ObjectMap<murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot> EQUIPMENT_SLOTS;
    public static int HOTBAR_SIZE;
    public static int ITEM_USAGE_COOLDOWN;
    public static int MAIN_SIZE;
    public static int NOT_FOUND;
    public static int OFF_HAND_SLOT;
    public int changeCount;
    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityEquipment equipment;
    public java.lang.Object main;
    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity player;
    public int selectedSlot;

    public PlayerInventory(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.EntityEquipment p1) {
    }

    public int addStack(int p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return 0;
    }

    public int addStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return 0;
    }

    public static boolean canPlayerUse(murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1) {
        return false;
    }

    public static boolean canPlayerUse(murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, float p2) {
        return false;
    }

    public boolean canPlayerUse(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
        return false;
    }

    public boolean canStackAddMore(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return false;
    }

    public boolean canTransferTo(murat.simv2.simulation.mirror.net.minecraft.inventory.Inventory p0, int p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2) {
        return false;
    }

    public void clear() {
    }

    public void clone(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerInventory p0) {
    }

    public boolean containsAny(java.util.Set p0) {
        return false;
    }

    public boolean containsAny(java.util.function.Predicate p0) {
        return false;
    }

    public boolean contains(java.util.function.Predicate p0) {
        return false;
    }

    public boolean contains(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return false;
    }

    public boolean contains(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0) {
        return false;
    }

    public int count(murat.simv2.simulation.mirror.net.minecraft.item.Item p0) {
        return 0;
    }

    public java.lang.Object createSlotSetPacket(int p0) {
        return null;
    }

    public void dropAll() {
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack dropSelectedItem(boolean p0) {
        return null;
    }

    public int getChangeCount() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getCustomName() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getDisplayName() {
        return null;
    }

    public int getEmptySlot() {
        return 0;
    }

    public static int getHotbarSize() {
        return 0;
    }

    public java.lang.Object getMainStacks() {
        return null;
    }

    public int getMatchingSlot(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return 0;
    }

    public int getMaxCountPerStack() {
        return 0;
    }

    public int getMaxCount(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getName() {
        return null;
    }

    public int getOccupiedSlotWithRoomForStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return 0;
    }

    public int getSelectedSlot() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getSelectedStack() {
        return null;
    }

    public int getSlotWithStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getStack(int p0) {
        return null;
    }

    public int getSwappableHotbarSlot() {
        return 0;
    }

    public boolean hasCustomName() {
        return false;
    }

    public boolean insertStack(int p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return false;
    }

    public boolean insertStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    public static boolean isValidHotbarIndex(int p0) {
        return false;
    }

    public boolean isValid(int p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return false;
    }

    public java.util.Iterator iterator() {
        return null;
    }

    public void markDirty() {
    }

    public void offerOrDrop(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public void offer(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, boolean p1) {
    }

    public void onClose(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
    }

    public void onOpen(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
    }

    public void populateRecipeFinder(java.lang.Object p0) {
    }

    public void readNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtList p0) {
    }

    public void removeOne(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack removeStack(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack removeStack(int p0, int p1) {
        return null;
    }

    public int remove(java.util.function.Predicate p0, int p1, murat.simv2.simulation.mirror.net.minecraft.inventory.Inventory p2) {
        return 0;
    }

    public void setSelectedSlot(int p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack setSelectedStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return null;
    }

    public void setStack(int p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
    }

    public int size() {
        return 0;
    }

    public void swapSlotWithHotbar(int p0) {
    }

    public void swapStackWithHotbar(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public void updateItems() {
    }

    public static boolean usableWhenFillingSlot(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtList writeNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtList p0) {
        return null;
    }

    public PlayerInventory() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
