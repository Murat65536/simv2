package murat.simv2.simulation.mirror.net.minecraft.server.network;

// Generated mirror stub for simulation closure.
public class ServerRecipeBook {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static org.slf4j.Logger LOGGER;
    public static com.mojang.serialization.Codec<java.util.List<murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<java.lang.Object>>> RECIPES_CODEC;
    public static java.lang.String RECIPE_BOOK_KEY;
    public murat.simv2.simulation.mirror.net.minecraft.server.network.ServerRecipeBook.DisplayCollector collector;
    public java.util.Set<murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<java.lang.Object>> highlighted;
    public java.lang.Object options;
    public java.util.Set<murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<java.lang.Object>> unlocked;

    public ServerRecipeBook(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerRecipeBook.DisplayCollector p0) {
    }

    public void copyFrom(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerRecipeBook p0) {
    }

    public java.lang.Object getOptions() {
        return null;
    }

    public void handleList(java.util.List p0, java.util.function.Consumer p1, java.util.function.Predicate p2) {
    }

    public boolean isFilteringCraftable(java.lang.Object p0) {
        return false;
    }

    public boolean isGuiOpen(java.lang.Object p0) {
        return false;
    }

    public boolean isUnlocked(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
        return false;
    }

    public int lockRecipes(java.util.Collection p0, murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p1) {
        return 0;
    }

    public void lock(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
    }

    public void markHighlighted(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
    }

    public void readNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, java.util.function.Predicate p1) {
    }

    public void sendInitRecipesPacket(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
    }

    public void setCategoryOptions(java.lang.Object p0, boolean p1, boolean p2) {
    }

    public void setFilteringCraftable(java.lang.Object p0, boolean p1) {
    }

    public void setGuiOpen(java.lang.Object p0, boolean p1) {
    }

    public void setOptions(java.lang.Object p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound toNbt() {
        return null;
    }

    public int unlockRecipes(java.util.Collection p0, murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p1) {
        return 0;
    }

    public void unlock(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
    }

    public void unmarkHighlighted(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
    }

    public ServerRecipeBook() {
    }

    public static interface DisplayCollector {
        public void displaysForRecipe(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, java.util.function.Consumer p1);

    }

    // END GENERATED MIRROR NESTED STUBS
}
