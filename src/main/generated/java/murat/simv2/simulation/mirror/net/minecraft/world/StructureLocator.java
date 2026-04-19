package murat.simv2.simulation.mirror.net.minecraft.world;

// Generated mirror stub for simulation closure.
public class StructureLocator extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static org.slf4j.Logger LOGGER;
    public static int START_NOT_PRESENT_REFERENCE;
    public java.lang.Object biomeSource;
    public it.unimi.dsi.fastutil.longs.Long2ObjectMap<it.unimi.dsi.fastutil.objects.Object2IntMap<java.lang.Object>> cachedStructuresByChunkPos;
    public java.lang.Object chunkGenerator;
    public java.lang.Object chunkIoWorker;
    public com.mojang.datafixers.DataFixer dataFixer;
    public java.util.Map<java.lang.Object, it.unimi.dsi.fastutil.longs.Long2BooleanMap> generationPossibilityByStructure;
    public java.lang.Object noiseConfig;
    public murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager registryManager;
    public long seed;
    public murat.simv2.simulation.mirror.net.minecraft.structure.StructureTemplateManager structureTemplateManager;
    public murat.simv2.simulation.mirror.net.minecraft.world.HeightLimitView world;
    public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<murat.simv2.simulation.mirror.net.minecraft.world.World> worldKey;

    public StructureLocator(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager p1, murat.simv2.simulation.mirror.net.minecraft.structure.StructureTemplateManager p2, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p3, java.lang.Object p4, java.lang.Object p5, murat.simv2.simulation.mirror.net.minecraft.world.HeightLimitView p6, java.lang.Object p7, long p8, com.mojang.datafixers.DataFixer p9) {
    }

    public void cache(long p0, it.unimi.dsi.fastutil.objects.Object2IntMap p1) {
    }

    public void cache(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0, java.util.Map p1) {
    }

    public it.unimi.dsi.fastutil.objects.Object2IntMap collectStructuresAndReferences(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
        return null;
    }

    public static it.unimi.dsi.fastutil.objects.Object2IntMap createMapIfEmpty(it.unimi.dsi.fastutil.objects.Object2IntMap p0) {
        return null;
    }

    public java.lang.Object getStructurePresence(it.unimi.dsi.fastutil.objects.Object2IntMap p0, java.lang.Object p1, boolean p2) {
        return null;
    }

    public java.lang.Object getStructurePresence(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0, java.lang.Object p1, boolean p2, long p3) {
        return null;
    }

    public java.lang.Object getStructurePresence(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0, java.lang.Object p1, java.lang.Object p2, boolean p3) {
        return null;
    }

    public void incrementReferences(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0, java.lang.Object p1) {
    }

    public boolean isGenerationPossible(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0, java.lang.Object p1) {
        return false;
    }

    public StructureLocator() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
