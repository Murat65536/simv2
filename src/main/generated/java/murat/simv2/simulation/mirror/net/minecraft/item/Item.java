package murat.simv2.simulation.mirror.net.minecraft.item;

// Generated mirror stub for simulation closure.
public class Item {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier BASE_ATTACK_DAMAGE_MODIFIER_ID;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier BASE_ATTACK_SPEED_MODIFIER_ID;
    public static java.util.Map BLOCK_ITEMS;
    public static int DEFAULT_BLOCKS_ATTACKS_MAX_USE_TIME;
    public static int DEFAULT_MAX_COUNT;
    public static com.mojang.serialization.Codec ENTRY_CODEC;
    public static java.lang.Object ENTRY_PACKET_CODEC;
    public static int ITEM_BAR_STEPS;
    public static org.slf4j.Logger LOGGER;
    public static int MAX_MAX_COUNT;
    public java.lang.Object components;
    public murat.simv2.simulation.mirror.net.minecraft.item.Item recipeRemainder;
    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry.Reference registryEntry;
    public java.lang.Object requiredFeatures;
    public java.lang.String translationKey;

    public Item(murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings p0) {
    }

    public void appendTooltip(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.item.Item.TooltipContext p1, java.lang.Object p2, java.util.function.Consumer p3, java.lang.Object p4) {
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

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack finishUsing(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.item.Item fromBlock(murat.simv2.simulation.mirror.net.minecraft.block.Block p0) {
        return null;
    }

    public float getBonusAttackDamage(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, float p1, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p2) {
        return 0.0F;
    }

    public java.lang.Object getComponents() {
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

    public java.lang.Object getRequiredFeatures() {
        return null;
    }

    public java.util.Optional getTooltipData(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return null;
    }

    public java.lang.String getTranslationKey() {
        return null;
    }

    public java.lang.Object getUseAction(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
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

    public boolean isEnabled(java.lang.Object p0) {
        return false;
    }

    public boolean isItemBarVisible(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return false;
    }

    public boolean isUsedOnRelease(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return false;
    }

    public boolean onClicked(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1, java.lang.Object p2, java.lang.Object p3, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p4, java.lang.Object p5) {
        return false;
    }

    public void onCraftByPlayer(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1) {
    }

    public void onCraft(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1) {
    }

    public void onItemEntityDestroyed(murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity p0) {
    }

    public boolean onStackClicked(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, java.lang.Object p1, java.lang.Object p2, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p3) {
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

    public boolean shouldShowOperatorBlockWarnings(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1) {
        return false;
    }

    public java.lang.String toString() {
        return null;
    }

    public void usageTick(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2, int p3) {
    }

    public java.lang.Object useOnBlock(java.lang.Object p0) {
        return null;
    }

    public java.lang.Object useOnEntity(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p2, murat.simv2.simulation.mirror.net.minecraft.util.Hand p3) {
        return null;
    }

    public java.lang.Object use(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, murat.simv2.simulation.mirror.net.minecraft.util.Hand p2) {
        return null;
    }

    public Item() {
    }

    public static class Settings {
        public static java.lang.Object BLOCK_PREFIXED_TRANSLATION_KEY;
        public static java.lang.Object ITEM_PREFIXED_TRANSLATION_KEY;
        public java.lang.Object components;
        public java.lang.Object modelId;
        public murat.simv2.simulation.mirror.net.minecraft.item.Item recipeRemainder;
        public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey registryKey;
        public java.lang.Object requiredFeatures;
        public java.lang.Object translationKey;

        public Settings() {
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings armor(murat.simv2.simulation.mirror.net.minecraft.item.equipment.ArmorMaterial p0, murat.simv2.simulation.mirror.net.minecraft.item.equipment.EquipmentType p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings attributeModifiers(java.lang.Object p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings axe(murat.simv2.simulation.mirror.net.minecraft.item.ToolMaterial p0, float p1, float p2) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings component(java.lang.Object p0, java.lang.Object p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings enchantable(int p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings equippableUnswappable(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings equippable(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings fireproof() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings food(java.lang.Object p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings food(java.lang.Object p0, java.lang.Object p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier getModelId() {
            return null;
        }

        public java.lang.String getTranslationKey() {
            return null;
        }

        public java.lang.Object getValidatedComponents(murat.simv2.simulation.mirror.net.minecraft.text.Text p0, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p1) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings hoe(murat.simv2.simulation.mirror.net.minecraft.item.ToolMaterial p0, float p1, float p2) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings horseArmor(murat.simv2.simulation.mirror.net.minecraft.item.equipment.ArmorMaterial p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings jukeboxPlayable(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings maxCount(int p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings maxDamage(int p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings pickaxe(murat.simv2.simulation.mirror.net.minecraft.item.ToolMaterial p0, float p1, float p2) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings rarity(java.lang.Object p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings recipeRemainder(murat.simv2.simulation.mirror.net.minecraft.item.Item p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings registryKey(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings repairable(murat.simv2.simulation.mirror.net.minecraft.item.Item p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings repairable(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings requires(java.lang.Object[] p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings shovel(murat.simv2.simulation.mirror.net.minecraft.item.ToolMaterial p0, float p1, float p2) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings sword(murat.simv2.simulation.mirror.net.minecraft.item.ToolMaterial p0, float p1, float p2) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings tool(murat.simv2.simulation.mirror.net.minecraft.item.ToolMaterial p0, murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p1, float p2, float p3, float p4) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings translationKey(java.lang.String p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings trimMaterial(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings useBlockPrefixedTranslationKey() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings useCooldown(float p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings useItemPrefixedTranslationKey() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings useRemainder(murat.simv2.simulation.mirror.net.minecraft.item.Item p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.Item.Settings wolfArmor(murat.simv2.simulation.mirror.net.minecraft.item.equipment.ArmorMaterial p0) {
            return null;
        }

    }

    public static interface TooltipContext {
        public static final murat.simv2.simulation.mirror.net.minecraft.item.Item.TooltipContext DEFAULT = null;

        public static murat.simv2.simulation.mirror.net.minecraft.item.Item.TooltipContext create(java.lang.Object p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.item.Item.TooltipContext create(murat.simv2.simulation.mirror.net.minecraft.world.World p0) {
            return null;
        }

        public java.lang.Object getMapState(java.lang.Object p0);

        public java.lang.Object getRegistryLookup();

        public float getUpdateTickRate();

    }

    // END GENERATED MIRROR NESTED STUBS
}
