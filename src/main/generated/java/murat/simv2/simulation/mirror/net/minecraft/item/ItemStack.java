package murat.simv2.simulation.mirror.net.minecraft.item;

// Generated mirror stub for simulation closure.
public class ItemStack {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text DISABLED_TEXT;
    public static murat.simv2.simulation.mirror.net.minecraft.item.ItemStack EMPTY;
    public static java.lang.Object LENGTH_PREPENDED_OPTIONAL_PACKET_CODEC;
    public static org.slf4j.Logger LOGGER;
    public static com.mojang.serialization.MapCodec MAP_CODEC;
    public static java.util.List OPERATOR_WARNINGS;
    public static com.mojang.serialization.Codec OPTIONAL_CODEC;
    public static java.lang.Object OPTIONAL_LIST_PACKET_CODEC;
    public static java.lang.Object OPTIONAL_PACKET_CODEC;
    public static java.lang.Object PACKET_CODEC;
    public static com.mojang.serialization.Codec REGISTRY_ENTRY_CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text UNBREAKABLE_TEXT;
    public static com.mojang.serialization.Codec UNCOUNTED_CODEC;
    public static com.mojang.serialization.Codec VALIDATED_CODEC;
    public static com.mojang.serialization.Codec VALIDATED_UNCOUNTED_CODEC;
    public int bobbingAnimationTime;
    public java.lang.Object components;
    public int count;
    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity holder;
    public murat.simv2.simulation.mirror.net.minecraft.item.Item item;

    public ItemStack(java.lang.Object p0) {
    }

    public ItemStack(java.lang.Object p0, int p1) {
    }

    public ItemStack(java.lang.Object p0, int p1, java.lang.Object p2) {
    }

    public ItemStack(java.lang.Void p0) {
    }

    public ItemStack(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
    }

    public ItemStack(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, int p1) {
    }

    public ItemStack(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, int p1, java.lang.Object p2) {
    }

    public void addEnchantment(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, int p1) {
    }

    public void appendAttributeModifierTooltip(java.util.function.Consumer p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p2, java.lang.Object p3) {
    }

    public void appendAttributeModifiersTooltip(java.util.function.Consumer p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p2) {
    }

    public void appendComponentTooltip(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.item.Item.TooltipContext p1, java.lang.Object p2, java.util.function.Consumer p3, java.lang.Object p4) {
    }

    public void appendTooltip(murat.simv2.simulation.mirror.net.minecraft.item.Item.TooltipContext p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p2, java.lang.Object p3, java.util.function.Consumer p4) {
    }

    public void applyAttributeModifiers(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0, java.util.function.BiConsumer p1) {
    }

    public void applyAttributeModifier(murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot p0, java.util.function.BiConsumer p1) {
    }

    public void applyChanges(java.lang.Object p0) {
    }

    public void applyComponentsFrom(java.lang.Object p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack applyRemainderAndCooldown(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return null;
    }

    public void applyUnvalidatedChanges(java.lang.Object p0) {
    }

    public java.lang.Object apply(java.lang.Object p0, java.lang.Object p1, java.lang.Object p2, java.util.function.BiFunction p3) {
        return null;
    }

    public java.lang.Object apply(java.lang.Object p0, java.lang.Object p1, java.util.function.UnaryOperator p2) {
        return null;
    }

    public static boolean areEqual(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return false;
    }

    public static boolean areItemsAndComponentsEqual(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return false;
    }

    public static boolean areItemsEqual(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return false;
    }

    public int calculateDamage(int p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p2) {
        return 0;
    }

    public boolean canBreak(java.lang.Object p0) {
        return false;
    }

    public boolean canMine(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p3) {
        return false;
    }

    public boolean canPlaceOn(java.lang.Object p0) {
        return false;
    }

    public boolean canRepairWith(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return false;
    }

    public void capCount(int p0) {
    }

    public boolean contains(java.lang.Object p0) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack copyAndEmpty() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack copyComponentsToNewStackIgnoreEmpty(java.lang.Object p0, int p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack copyComponentsToNewStack(java.lang.Object p0, int p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack copyWithCount(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack copy() {
        return null;
    }

    public void copy(java.lang.Object p0, java.lang.Object p1) {
    }

    public static java.lang.Object createExtraValidatingPacketCodec(java.lang.Object p0) {
        return null;
    }

    public static com.mojang.serialization.MapCodec createOptionalCodec(java.lang.String p0) {
        return null;
    }

    public static java.lang.Object createOptionalPacketCodec(java.lang.Object p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack damage(int p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p2, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p3) {
        return null;
    }

    public void damage(int p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p2) {
    }

    public void damage(int p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1) {
    }

    public void damage(int p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p2, java.util.function.Consumer p3) {
    }

    public void decrementUnlessCreative(int p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1) {
    }

    public void decrement(int p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack finishUsing(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1) {
        return null;
    }

    public static java.util.Optional fromNbt(java.lang.Object p0, java.lang.Object p1) {
        return null;
    }

    public int getBobbingAnimationTime() {
        return 0;
    }

    public java.lang.Object getComponentChanges() {
        return null;
    }

    public java.lang.Object getComponents() {
        return null;
    }

    public int getCount() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getCustomName() {
        return null;
    }

    public int getDamage() {
        return 0;
    }

    public java.lang.Object getDefaultComponents() {
        return null;
    }

    public java.lang.Object getEnchantments() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getFormattedName() {
        return null;
    }

    public java.lang.Object getFrame() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getHolder() {
        return null;
    }

    public java.lang.Object getImmutableComponents() {
        return null;
    }

    public int getItemBarColor() {
        return 0;
    }

    public int getItemBarStep() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getItemName() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.Item getItem() {
        return null;
    }

    public int getMaxCount() {
        return 0;
    }

    public int getMaxDamage() {
        return 0;
    }

    public int getMaxUseTime(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
        return 0;
    }

    public float getMiningSpeedMultiplier(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getName() {
        return null;
    }

    public java.lang.Object getOrDefault(java.lang.Object p0, java.lang.Object p1) {
        return null;
    }

    public java.lang.Object getRarity() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry getRegistryEntry() {
        return null;
    }

    public java.util.Optional getTooltipData() {
        return null;
    }

    public java.util.List getTooltip(murat.simv2.simulation.mirror.net.minecraft.item.Item.TooltipContext p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, java.lang.Object p2) {
        return null;
    }

    public java.lang.Object getTyped(java.lang.Object p0) {
        return null;
    }

    public java.lang.Object getUseAction() {
        return null;
    }

    public java.lang.Object get(java.lang.Object p0) {
        return null;
    }

    public boolean hasChangedComponent(java.lang.Object p0) {
        return false;
    }

    public boolean hasEnchantments() {
        return false;
    }

    public boolean hasGlint() {
        return false;
    }

    public static int hashCode(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return 0;
    }

    public void increment(int p0) {
    }

    public void inventoryTick(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p2) {
    }

    public boolean isDamageable() {
        return false;
    }

    public boolean isDamaged() {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isEnchantable() {
        return false;
    }

    public boolean isInFrame() {
        return false;
    }

    public boolean isIn(java.lang.Object p0) {
        return false;
    }

    public boolean isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0) {
        return false;
    }

    public boolean isItemBarVisible() {
        return false;
    }

    public boolean isItemEnabled(java.lang.Object p0) {
        return false;
    }

    public boolean isOf(murat.simv2.simulation.mirror.net.minecraft.item.Item p0) {
        return false;
    }

    public boolean isStackable() {
        return false;
    }

    public boolean isSuitableFor(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return false;
    }

    public boolean isUsedOnRelease() {
        return false;
    }

    public boolean itemMatches(java.util.function.Predicate p0) {
        return false;
    }

    public boolean itemMatches(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return false;
    }

    public static int listHashCode(java.util.List p0) {
        return 0;
    }

    public boolean onClicked(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, java.lang.Object p1, java.lang.Object p2, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p3, java.lang.Object p4) {
        return false;
    }

    public void onCraftByCrafter(murat.simv2.simulation.mirror.net.minecraft.world.World p0) {
    }

    public void onCraftByPlayer(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, int p1) {
    }

    public void onDurabilityChange(int p0, murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p1, java.util.function.Consumer p2) {
    }

    public void onItemEntityDestroyed(murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity p0) {
    }

    public boolean onStackClicked(java.lang.Object p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p2) {
        return false;
    }

    public void onStoppedUsing(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, int p2) {
    }

    public void postDamageEntity(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1) {
    }

    public boolean postHit(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1) {
        return false;
    }

    public void postMine(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p3) {
    }

    public java.lang.Object remove(java.lang.Object p0) {
        return null;
    }

    public void setBobbingAnimationTime(int p0) {
    }

    public void setCount(int p0) {
    }

    public void setDamage(int p0) {
    }

    public void setHolder(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public java.lang.Object set(java.lang.Object p0, java.lang.Object p1) {
        return null;
    }

    public boolean shouldBreak() {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack splitUnlessCreative(int p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack split(int p0) {
        return null;
    }

    public static boolean stacksEqual(java.util.List p0, java.util.List p1) {
        return false;
    }

    public java.util.stream.Stream streamAll(java.lang.Class p0) {
        return null;
    }

    public java.util.stream.Stream streamTags() {
        return null;
    }

    public boolean takesDamageFrom(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text toHoverableText() {
        return null;
    }

    public java.lang.Object toNbt(java.lang.Object p0) {
        return null;
    }

    public java.lang.Object toNbt(java.lang.Object p0, java.lang.Object p1) {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public void usageTick(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, int p2) {
    }

    public java.lang.Object useOnBlock(java.lang.Object p0) {
        return null;
    }

    public java.lang.Object useOnEntity(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, murat.simv2.simulation.mirror.net.minecraft.util.Hand p2) {
        return null;
    }

    public java.lang.Object use(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, murat.simv2.simulation.mirror.net.minecraft.util.Hand p2) {
        return null;
    }

    public static com.mojang.serialization.DataResult validateComponents(java.lang.Object p0) {
        return null;
    }

    public static com.mojang.serialization.DataResult validate(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return null;
    }

    public boolean willBreakNextUse() {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack withItem(java.lang.Object p0) {
        return null;
    }

    public ItemStack() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
