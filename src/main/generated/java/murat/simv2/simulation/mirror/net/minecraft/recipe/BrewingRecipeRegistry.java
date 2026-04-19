package murat.simv2.simulation.mirror.net.minecraft.recipe;

// Generated mirror stub for simulation closure.
public class BrewingRecipeRegistry extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.recipe.BrewingRecipeRegistry EMPTY;
    public static int field_30942;
    public java.util.List<murat.simv2.simulation.mirror.net.minecraft.recipe.BrewingRecipeRegistry.Recipe<murat.simv2.simulation.mirror.net.minecraft.item.Item>> itemRecipes;
    public java.util.List<murat.simv2.simulation.mirror.net.minecraft.recipe.BrewingRecipeRegistry.Recipe<java.lang.Object>> potionRecipes;
    public java.util.List<java.lang.Object> potionTypes;

    public BrewingRecipeRegistry(java.util.List p0, java.util.List p1, java.util.List p2) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack craft(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.recipe.BrewingRecipeRegistry create(murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet p0) {
        return null;
    }

    public boolean hasItemRecipe(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return false;
    }

    public boolean hasPotionRecipe(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return false;
    }

    public boolean hasRecipe(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return false;
    }

    public boolean isBrewable(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return false;
    }

    public boolean isItemRecipeIngredient(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return false;
    }

    public boolean isPotionRecipeIngredient(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return false;
    }

    public boolean isPotionType(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return false;
    }

    public boolean isValidIngredient(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return false;
    }

    public static void registerDefaults(murat.simv2.simulation.mirror.net.minecraft.recipe.BrewingRecipeRegistry.Builder p0) {
    }

    public BrewingRecipeRegistry() {
    }

    public static class Builder extends java.lang.Object {
        public murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet enabledFeatures;
        public java.util.List<murat.simv2.simulation.mirror.net.minecraft.recipe.BrewingRecipeRegistry.Recipe<murat.simv2.simulation.mirror.net.minecraft.item.Item>> itemRecipes;
        public java.util.List<murat.simv2.simulation.mirror.net.minecraft.recipe.BrewingRecipeRegistry.Recipe<java.lang.Object>> potionRecipes;
        public java.util.List<java.lang.Object> potionTypes;

        public Builder(murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet p0) {
        }

        public static void assertPotion(murat.simv2.simulation.mirror.net.minecraft.item.Item p0) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.recipe.BrewingRecipeRegistry build() {
            return null;
        }

        public void registerItemRecipe(murat.simv2.simulation.mirror.net.minecraft.item.Item p0, murat.simv2.simulation.mirror.net.minecraft.item.Item p1, murat.simv2.simulation.mirror.net.minecraft.item.Item p2) {
        }

        public void registerPotionRecipe(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.item.Item p1, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p2) {
        }

        public void registerPotionType(murat.simv2.simulation.mirror.net.minecraft.item.Item p0) {
        }

        public void registerRecipes(murat.simv2.simulation.mirror.net.minecraft.item.Item p0, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p1) {
        }

        public Builder() {
        }

    }

    public static class Recipe<T> {
        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<java.lang.Object> from;
        public java.lang.Object ingredient;
        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<java.lang.Object> to;

        public Recipe(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p2) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry from() {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public java.lang.Object ingredient() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry to() {
            return null;
        }

        public Recipe() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
