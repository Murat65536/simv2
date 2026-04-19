package murat.simv2.simulation.mirror.net.minecraft.world;

// Generated mirror stub for simulation closure.
public class PersistentStateManager extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static org.slf4j.Logger LOGGER;
    public java.lang.Object context;
    public com.mojang.datafixers.DataFixer dataFixer;
    public java.nio.file.Path directory;
    public java.util.Map<java.lang.Object, java.util.Optional<java.lang.Object>> loadedStates;
    public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup registries;
    public java.util.concurrent.CompletableFuture<?> savingFuture;

    public PersistentStateManager(java.lang.Object p0, java.nio.file.Path p1, com.mojang.datafixers.DataFixer p2, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p3) {
    }

    public void close() {
    }

    public java.util.Map collectStatesToSave() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound encode(java.lang.Object p0, java.lang.Object p1, java.lang.Object p2) {
        return null;
    }

    public java.nio.file.Path getFile(java.lang.String p0) {
        return null;
    }

    public java.lang.Object getOrCreate(java.lang.Object p0) {
        return null;
    }

    public java.lang.Object get(java.lang.Object p0) {
        return null;
    }

    public boolean isCompressed(java.io.PushbackInputStream p0) {
        return false;
    }

    public java.lang.Object readFromFile(java.lang.Object p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound readNbt(java.lang.String p0, java.lang.Object p1, int p2) {
        return null;
    }

    public void save() {
    }

    public void save(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p1) {
    }

    public void set(java.lang.Object p0, java.lang.Object p1) {
    }

    public java.util.concurrent.CompletableFuture startSaving() {
        return null;
    }

    public PersistentStateManager() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
