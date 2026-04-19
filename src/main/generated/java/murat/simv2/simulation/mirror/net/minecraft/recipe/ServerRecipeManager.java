package murat.simv2.simulation.mirror.net.minecraft.recipe;

// Generated mirror stub for simulation closure.
public class ServerRecipeManager implements murat.simv2.simulation.mirror.net.minecraft.recipe.RecipeManager {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.lang.Object FINDER;
    public static org.slf4j.Logger LOGGER;
    public static java.util.Map<murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<java.lang.Object>, murat.simv2.simulation.mirror.net.minecraft.recipe.ServerRecipeManager.SoleIngredientGetter> SOLE_INGREDIENT_GETTERS;
    public java.lang.Object preparedRecipes;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<java.lang.Object>, java.lang.Object> propertySets;
    public java.util.List<murat.simv2.simulation.mirror.net.minecraft.recipe.ServerRecipeManager.ServerRecipe> recipes;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<java.lang.Object>, java.util.List<murat.simv2.simulation.mirror.net.minecraft.recipe.ServerRecipeManager.ServerRecipe>> recipesByKey;
    public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup registries;
    public java.lang.Object stonecutterRecipes;

    public ServerRecipeManager(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p0) {
    }

    public void apply(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p1, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p2) {
    }

    public static java.util.List collectServerRecipes(java.lang.Iterable p0, murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.recipe.ServerRecipeManager.SoleIngredientGetter cookingIngredientGetter(java.lang.Object p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.recipe.ServerRecipeManager.MatchGetter createCachedMatchGetter(java.lang.Object p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.recipe.RecipeEntry deserialize(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, com.google.gson.JsonObject p1, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p2) {
        return null;
    }

    public static java.util.List filterIngredients(murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet p0, java.util.List p1) {
        return null;
    }

    public void forEachRecipeDisplay(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, java.util.function.Consumer p1) {
    }

    public java.util.Optional getFirstMatch(java.lang.Object p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.world.World p2) {
        return null;
    }

    public java.util.Optional getFirstMatch(java.lang.Object p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.world.World p2, murat.simv2.simulation.mirror.net.minecraft.recipe.RecipeEntry p3) {
        return null;
    }

    public java.util.Optional getFirstMatch(java.lang.Object p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.world.World p2, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p3) {
        return null;
    }

    public java.lang.String getName() {
        return null;
    }

    public java.util.Map getPropertySets() {
        return null;
    }

    public java.lang.Object getPropertySet(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
        return null;
    }

    public java.lang.Object getStonecutterRecipeForSync() {
        return null;
    }

    public java.lang.Object getStonecutterRecipes() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.recipe.RecipeEntry get(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.recipe.ServerRecipeManager.ServerRecipe get(murat.simv2.simulation.mirror.net.minecraft.recipe.NetworkRecipeId p0) {
        return null;
    }

    public java.util.Optional get(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
        return null;
    }

    public void initialize(murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet p0) {
    }

    public static boolean isEnabled(murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet p0, java.lang.Object p1) {
        return false;
    }

    public java.lang.Object prepare(murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p0, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p1) {
        return null;
    }

    public java.util.concurrent.CompletableFuture reload(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p1, java.util.concurrent.Executor p2, java.util.concurrent.Executor p3) {
        return null;
    }

    public java.util.Collection values() {
        return null;
    }

    public ServerRecipeManager() {
    }

    public static interface MatchGetter<I> {
        public java.util.Optional getFirstMatch(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1);

    }

    public static class PropertySetBuilder extends java.lang.Object {
        public murat.simv2.simulation.mirror.net.minecraft.recipe.ServerRecipeManager.SoleIngredientGetter ingredientGetter;
        public java.util.List<java.lang.Object> ingredients;
        public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<java.lang.Object> propertySetKey;

        public PropertySetBuilder(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, murat.simv2.simulation.mirror.net.minecraft.recipe.ServerRecipeManager.SoleIngredientGetter p1) {
        }

        public void accept(java.lang.Object p0) {
        }

        public java.lang.Object build(murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet p0) {
            return null;
        }

        public PropertySetBuilder() {
        }

    }

    public static class ServerRecipe {
        public java.lang.Object display;
        public murat.simv2.simulation.mirror.net.minecraft.recipe.RecipeEntry<?> parent;

        public ServerRecipe(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.recipe.RecipeEntry p1) {
        }

        public java.lang.Object display() {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.recipe.RecipeEntry parent() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public ServerRecipe() {
        }

    }

    public static interface SoleIngredientGetter {
        public java.util.Optional apply(java.lang.Object p0);

    }

    // END GENERATED MIRROR NESTED STUBS
}
