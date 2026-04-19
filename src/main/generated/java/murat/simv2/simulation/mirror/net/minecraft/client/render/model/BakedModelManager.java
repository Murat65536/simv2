package murat.simv2.simulation.mirror.net.minecraft.client.render.model;

// Generated mirror stub for simulation closure.
public class BakedModelManager extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, murat.simv2.simulation.mirror.net.minecraft.util.Identifier> LAYERS_TO_LOADERS;
    public static org.slf4j.Logger LOGGER;
    public static java.lang.Object MODELS_FINDER;
    public java.lang.Object atlasManager;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, java.lang.Object> bakedItemModels;
    public java.lang.Object blockEntityModels;
    public java.lang.Object blockModelCache;
    public murat.simv2.simulation.mirror.net.minecraft.client.color.block.BlockColors colorMap;
    public murat.simv2.simulation.mirror.net.minecraft.client.render.entity.model.LoadedEntityModels entityModels;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, java.lang.Object> itemProperties;
    public int mipmapLevels;
    public java.lang.Object missingModels;
    public it.unimi.dsi.fastutil.objects.Object2IntMap<murat.simv2.simulation.mirror.net.minecraft.block.BlockState> modelGroups;

    public BakedModelManager(murat.simv2.simulation.mirror.net.minecraft.client.texture.TextureManager p0, murat.simv2.simulation.mirror.net.minecraft.client.color.block.BlockColors p1, int p2) {
    }

    public static java.util.concurrent.CompletableFuture bake(java.util.Map p0, java.lang.Object p1, it.unimi.dsi.fastutil.objects.Object2IntMap p2, murat.simv2.simulation.mirror.net.minecraft.client.render.entity.model.LoadedEntityModels p3, java.lang.Object p4, java.util.concurrent.Executor p5) {
        return null;
    }

    public void close() {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.client.render.model.BakedModelManager.Models collect(java.util.Map p0, java.lang.Object p1, java.lang.Object p2) {
        return null;
    }

    public java.lang.Object getAtlas(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public java.util.function.Supplier getBlockEntityModelsSupplier() {
        return null;
    }

    public java.lang.Object getBlockModels() {
        return null;
    }

    public java.util.function.Supplier getEntityModelsSupplier() {
        return null;
    }

    public java.lang.Object getItemModel(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public java.lang.Object getItemProperties(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public java.lang.Object getMissingModel() {
        return null;
    }

    public java.lang.String getName() {
        return null;
    }

    public static it.unimi.dsi.fastutil.objects.Object2IntMap group(murat.simv2.simulation.mirror.net.minecraft.client.color.block.BlockColors p0, java.lang.Object p1) {
        return null;
    }

    public static java.util.concurrent.CompletableFuture reloadModels(murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p0, java.util.concurrent.Executor p1) {
        return null;
    }

    public java.util.concurrent.CompletableFuture reload(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p1, java.util.concurrent.Executor p2, java.util.concurrent.Executor p3) {
        return null;
    }

    public void setMipmapLevels(int p0) {
    }

    public boolean shouldRerender(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1) {
        return false;
    }

    public static java.util.Map toStateMap(java.util.Map p0, java.lang.Object p1) {
        return null;
    }

    public void upload(murat.simv2.simulation.mirror.net.minecraft.client.render.model.BakedModelManager.BakingResult p0, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p1) {
    }

    public BakedModelManager() {
    }

    public static class BakingResult {
        public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, java.lang.Object> atlasPreparations;
        public java.lang.Object bakedModels;
        public murat.simv2.simulation.mirror.net.minecraft.client.render.entity.model.LoadedEntityModels entityModelSet;
        public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.block.BlockState, java.lang.Object> modelCache;
        public it.unimi.dsi.fastutil.objects.Object2IntMap<murat.simv2.simulation.mirror.net.minecraft.block.BlockState> modelGroups;
        public java.util.concurrent.CompletableFuture<java.lang.Void> readyForUpload;
        public java.lang.Object specialBlockModelRenderer;

        public BakingResult(java.lang.Object p0, it.unimi.dsi.fastutil.objects.Object2IntMap p1, java.util.Map p2, java.util.Map p3, murat.simv2.simulation.mirror.net.minecraft.client.render.entity.model.LoadedEntityModels p4, java.lang.Object p5, java.util.concurrent.CompletableFuture p6) {
        }

        public java.util.Map atlasPreparations() {
            return null;
        }

        public java.lang.Object bakedModels() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.client.render.entity.model.LoadedEntityModels entityModelSet() {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public java.util.Map modelCache() {
            return null;
        }

        public it.unimi.dsi.fastutil.objects.Object2IntMap modelGroups() {
            return null;
        }

        public java.util.concurrent.CompletableFuture readyForUpload() {
            return null;
        }

        public java.lang.Object specialBlockModelRenderer() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public BakingResult() {
        }

    }

    public static class Models {
        public java.lang.Object missing;
        public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, java.lang.Object> models;

        public Models(java.lang.Object p0, java.util.Map p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public java.lang.Object missing() {
            return null;
        }

        public java.util.Map models() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public Models() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
