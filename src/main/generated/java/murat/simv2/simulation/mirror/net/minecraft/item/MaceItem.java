package murat.simv2.simulation.mirror.net.minecraft.item;

// Generated mirror stub for simulation closure.
public class MaceItem extends murat.simv2.simulation.mirror.net.minecraft.item.Item {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static int ATTACK_DAMAGE_MODIFIER_VALUE;
    public static float ATTACK_SPEED_MODIFIER_VALUE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier BASE_ATTACK_DAMAGE_MODIFIER_ID;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier BASE_ATTACK_SPEED_MODIFIER_ID;
    public static java.util.Map<murat.simv2.simulation.mirror.net.minecraft.block.Block, murat.simv2.simulation.mirror.net.minecraft.item.Item> BLOCK_ITEMS;
    public static int DEFAULT_BLOCKS_ATTACKS_MAX_USE_TIME;
    public static int DEFAULT_MAX_COUNT;
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.item.Item>> ENTRY_CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.item.Item>> ENTRY_PACKET_CODEC;
    public static float HEAVY_SMASH_SOUND_FALL_DISTANCE_THRESHOLD;
    public static int ITEM_BAR_STEPS;
    public static float KNOCKBACK_POWER;
    public static float KNOCKBACK_RANGE;
    public static int MAX_MAX_COUNT;
    public static float MINING_SPEED_MULTIPLIER;
    public java.lang.String translationKey;

    public MaceItem(murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings p0) {
    }

    public void appendTooltip(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.item.Item.TooltipContext p1, murat.simv2.simulation.mirror.net.minecraft.component.type.TooltipDisplayComponent p2, java.util.function.Consumer p3, murat.simv2.simulation.mirror.net.minecraft.item.tooltip.TooltipType p4) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.Item asItem() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.item.Item byRawId(int p0) {
        return null;
    }

    public boolean canBeNested() {
        return false;
    }

    public boolean canMine(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1, murat.simv2.simulation.mirror.net.minecraft.world.World p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p4) {
        return false;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifiersComponent createAttributeModifiers() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.component.type.ToolComponent createToolComponent() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack finishUsing(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.item.Item fromBlock(murat.simv2.simulation.mirror.net.minecraft.block.Block p0) {
        return null;
    }

    public float getBonusAttackDamage(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, float p1, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p2) {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap getComponents() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getCurrentExplosionImpactPos(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource getDamageSource(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getDefaultStack() {
        return null;
    }

    public int getItemBarColor(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return 0;
    }

    public int getItemBarStep(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return 0;
    }

    public static java.util.function.Predicate getKnockbackPredicate(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
        return null;
    }

    public static double getKnockback(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2) {
        return 0.0D;
    }

    public int getMaxCount() {
        return 0;
    }

    public int getMaxUseTime(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1) {
        return 0;
    }

    public float getMiningSpeed(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1) {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getName() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getName(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return null;
    }

    public static int getRawId(murat.simv2.simulation.mirror.net.minecraft.item.Item p0) {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getRecipeRemainder() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference getRegistryEntry() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet getRequiredFeatures() {
        return null;
    }

    public java.util.Optional getTooltipData(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return null;
    }

    public java.lang.String getTranslationKey() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.consume.UseAction getUseAction(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return null;
    }

    public boolean hasGlint(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return false;
    }

    public void inventoryTick(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p2, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p3) {
    }

    public boolean isCorrectForDrops(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1) {
        return false;
    }

    public boolean isEnabled(murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet p0) {
        return false;
    }

    public boolean isItemBarVisible(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return false;
    }

    public boolean isUsedOnRelease(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return false;
    }

    public static void knockbackNearbyEntities(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p2) {
    }

    public boolean onClicked(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1, murat.simv2.simulation.mirror.net.minecraft.screen.slot.Slot p2, murat.simv2.simulation.mirror.net.minecraft.util.ClickType p3, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p4, murat.simv2.simulation.mirror.net.minecraft.inventory.StackReference p5) {
        return false;
    }

    public void onCraftByPlayer(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1) {
    }

    public void onCraft(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1) {
    }

    public void onItemEntityDestroyed(murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity p0) {
    }

    public boolean onStackClicked(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.screen.slot.Slot p1, murat.simv2.simulation.mirror.net.minecraft.util.ClickType p2, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p3) {
        return false;
    }

    public boolean onStoppedUsing(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p2, int p3) {
        return false;
    }

    public void postDamageEntity(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p2) {
    }

    public void postHit(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p2) {
    }

    public boolean postMine(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p4) {
        return false;
    }

    public void postProcessComponents(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult raycast(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.FluidHandling p2) {
        return null;
    }

    public static boolean shouldDealAdditionalDamage(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
        return false;
    }

    public boolean shouldShowOperatorBlockWarnings(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1) {
        return false;
    }

    public java.lang.String toString() {
        return null;
    }

    public void usageTick(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2, int p3) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult useOnBlock(murat.simv2.simulation.mirror.net.minecraft.item.ItemUsageContext p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult useOnEntity(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p2, murat.simv2.simulation.mirror.net.minecraft.util.Hand p3) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult use(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, murat.simv2.simulation.mirror.net.minecraft.util.Hand p2) {
        return null;
    }

    public MaceItem() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
