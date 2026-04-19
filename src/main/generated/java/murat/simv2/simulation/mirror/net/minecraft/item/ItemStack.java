package murat.simv2.simulation.mirror.net.minecraft.item;

// Generated mirror stub for simulation closure.
public class ItemStack extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.component.ComponentHolder {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.item.ItemStack> CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text DISABLED_TEXT;
    public static murat.simv2.simulation.mirror.net.minecraft.item.ItemStack EMPTY;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack> LENGTH_PREPENDED_OPTIONAL_PACKET_CODEC;
    public static org.slf4j.Logger LOGGER;
    public static com.mojang.serialization.MapCodec<murat.simv2.simulation.mirror.net.minecraft.item.ItemStack> MAP_CODEC;
    public static java.util.List<murat.simv2.simulation.mirror.net.minecraft.text.Text> OPERATOR_WARNINGS;
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.item.ItemStack> OPTIONAL_CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, java.util.List<murat.simv2.simulation.mirror.net.minecraft.item.ItemStack>> OPTIONAL_LIST_PACKET_CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack> OPTIONAL_PACKET_CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack> PACKET_CODEC;
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.item.ItemStack> REGISTRY_ENTRY_CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text UNBREAKABLE_TEXT;
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.item.ItemStack> UNCOUNTED_CODEC;
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.item.ItemStack> VALIDATED_CODEC;
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.item.ItemStack> VALIDATED_UNCOUNTED_CODEC;
    public int bobbingAnimationTime;
    public murat.simv2.simulation.mirror.net.minecraft.component.MergedComponentMap components;
    public int count;
    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity holder;
    public murat.simv2.simulation.mirror.net.minecraft.item.Item item;

    public ItemStack(java.lang.Void p0) {
    }

    public ItemStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemConvertible p0) {
    }

    public ItemStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemConvertible p0, int p1) {
    }

    public ItemStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemConvertible p0, int p1, murat.simv2.simulation.mirror.net.minecraft.component.MergedComponentMap p2) {
    }

    public ItemStack(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
    }

    public ItemStack(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, int p1) {
    }

    public ItemStack(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, int p1, murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges p2) {
    }

    public void addEnchantment(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, int p1) {
    }

    public void appendAttributeModifierTooltip(java.util.function.Consumer p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p2, murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier p3) {
    }

    public void appendAttributeModifiersTooltip(java.util.function.Consumer p0, murat.simv2.simulation.mirror.net.minecraft.component.type.TooltipDisplayComponent p1, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p2) {
    }

    public void appendComponentTooltip(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, murat.simv2.simulation.mirror.net.minecraft.item.Item.TooltipContext p1, murat.simv2.simulation.mirror.net.minecraft.component.type.TooltipDisplayComponent p2, java.util.function.Consumer p3, murat.simv2.simulation.mirror.net.minecraft.item.tooltip.TooltipType p4) {
    }

    public void appendTooltip(murat.simv2.simulation.mirror.net.minecraft.item.Item.TooltipContext p0, murat.simv2.simulation.mirror.net.minecraft.component.type.TooltipDisplayComponent p1, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p2, murat.simv2.simulation.mirror.net.minecraft.item.tooltip.TooltipType p3, java.util.function.Consumer p4) {
    }

    public void applyAttributeModifiers(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0, java.util.function.BiConsumer p1) {
    }

    public void applyAttributeModifier(murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot p0, java.util.function.BiConsumer p1) {
    }

    public void applyChanges(murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges p0) {
    }

    public void applyComponentsFrom(murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack applyRemainderAndCooldown(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return null;
    }

    public void applyUnvalidatedChanges(murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges p0) {
    }

    public java.lang.Object apply(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, java.lang.Object p1, java.lang.Object p2, java.util.function.BiFunction p3) {
        return null;
    }

    public java.lang.Object apply(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, java.lang.Object p1, java.util.function.UnaryOperator p2) {
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

    public boolean canBreak(murat.simv2.simulation.mirror.net.minecraft.block.pattern.CachedBlockPosition p0) {
        return false;
    }

    public boolean canMine(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p3) {
        return false;
    }

    public boolean canPlaceOn(murat.simv2.simulation.mirror.net.minecraft.block.pattern.CachedBlockPosition p0) {
        return false;
    }

    public boolean canRepairWith(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return false;
    }

    public void capCount(int p0) {
    }

    public boolean contains(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack copyAndEmpty() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack copyComponentsToNewStackIgnoreEmpty(murat.simv2.simulation.mirror.net.minecraft.item.ItemConvertible p0, int p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack copyComponentsToNewStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemConvertible p0, int p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack copyWithCount(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack copy() {
        return null;
    }

    public void copy(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, murat.simv2.simulation.mirror.net.minecraft.component.ComponentsAccess p1) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec createExtraValidatingPacketCodec(murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec p0) {
        return null;
    }

    public static com.mojang.serialization.MapCodec createOptionalCodec(java.lang.String p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec createOptionalPacketCodec(murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec p0) {
        return null;
    }

    public void damage(int p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p2) {
    }

    public void damage(int p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack damage(int p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemConvertible p1, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p2, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p3) {
        return null;
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

    public static java.util.Optional fromNbt(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p0, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtElement p1) {
        return null;
    }

    public int getBobbingAnimationTime() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges getComponentChanges() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap getComponents() {
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

    public murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap getDefaultComponents() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.type.ItemEnchantmentsComponent getEnchantments() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getFormattedName() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.decoration.ItemFrameEntity getFrame() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getHolder() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap getImmutableComponents() {
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

    public java.lang.Object getOrDefault(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, java.lang.Object p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.Rarity getRarity() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry getRegistryEntry() {
        return null;
    }

    public java.util.Optional getTooltipData() {
        return null;
    }

    public java.util.List getTooltip(murat.simv2.simulation.mirror.net.minecraft.item.Item.TooltipContext p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, murat.simv2.simulation.mirror.net.minecraft.item.tooltip.TooltipType p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.Component getTyped(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.consume.UseAction getUseAction() {
        return null;
    }

    public java.lang.Object get(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0) {
        return null;
    }

    public boolean hasChangedComponent(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0) {
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

    public boolean isIn(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntryList p0) {
        return false;
    }

    public boolean isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0) {
        return false;
    }

    public boolean isItemBarVisible() {
        return false;
    }

    public boolean isItemEnabled(murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet p0) {
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

    public boolean onClicked(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.screen.slot.Slot p1, murat.simv2.simulation.mirror.net.minecraft.util.ClickType p2, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p3, murat.simv2.simulation.mirror.net.minecraft.inventory.StackReference p4) {
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

    public boolean onStackClicked(murat.simv2.simulation.mirror.net.minecraft.screen.slot.Slot p0, murat.simv2.simulation.mirror.net.minecraft.util.ClickType p1, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p2) {
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

    public java.lang.Object remove(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0) {
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

    public java.lang.Object set(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, java.lang.Object p1) {
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

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtElement toNbt(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtElement toNbt(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p0, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtElement p1) {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public void usageTick(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, int p2) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult useOnBlock(murat.simv2.simulation.mirror.net.minecraft.item.ItemUsageContext p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult useOnEntity(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, murat.simv2.simulation.mirror.net.minecraft.util.Hand p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult use(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, murat.simv2.simulation.mirror.net.minecraft.util.Hand p2) {
        return null;
    }

    public static com.mojang.serialization.DataResult validateComponents(murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap p0) {
        return null;
    }

    public static com.mojang.serialization.DataResult validate(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return null;
    }

    public boolean willBreakNextUse() {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack withItem(murat.simv2.simulation.mirror.net.minecraft.item.ItemConvertible p0) {
        return null;
    }

    public ItemStack() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
