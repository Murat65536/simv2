package murat.simv2.simulation.mirror.net.minecraft.structure;

// Generated mirror stub for simulation closure.
public class StructureTemplateManager extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static org.slf4j.Logger LOGGER;
    public static java.lang.String NBT_FILE_EXTENSION;
    public static java.lang.String SNBT_FILE_EXTENSION;
    public static java.lang.String STRUCTURES_DIRECTORY;
    public static java.lang.String STRUCTURE_DIRECTORY;
    public static java.lang.Object STRUCTURE_NBT_RESOURCE_FINDER;
    public java.lang.Object blockLookup;
    public com.mojang.datafixers.DataFixer dataFixer;
    public java.nio.file.Path generatedPath;
    public java.util.List<murat.simv2.simulation.mirror.net.minecraft.structure.StructureTemplateManager.Provider> providers;
    public murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager resourceManager;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, java.util.Optional<java.lang.Object>> templates;

    public StructureTemplateManager(murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p0, murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.Session p1, com.mojang.datafixers.DataFixer p2, java.lang.Object p3) {
    }

    public java.lang.Object createTemplate(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
        return null;
    }

    public java.lang.Object getTemplateOrBlank(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public java.nio.file.Path getTemplatePath(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, java.lang.String p1) {
        return null;
    }

    public java.util.Optional getTemplate(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public java.util.Optional loadTemplateFromFile(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public java.util.Optional loadTemplateFromGameTestFile(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public java.util.Optional loadTemplateFromResource(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public java.util.Optional loadTemplateFromSnbt(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, java.nio.file.Path p1) {
        return null;
    }

    public java.util.Optional loadTemplate(murat.simv2.simulation.mirror.net.minecraft.structure.StructureTemplateManager.TemplateFileOpener p0, java.util.function.Consumer p1) {
        return null;
    }

    public java.util.Optional loadTemplate(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public java.lang.Object readTemplate(java.io.InputStream p0) {
        return null;
    }

    public boolean saveTemplate(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return false;
    }

    public void setResourceManager(murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p0) {
    }

    public java.util.stream.Stream streamTemplatesFromFile() {
        return null;
    }

    public java.util.stream.Stream streamTemplatesFromGameTestFile() {
        return null;
    }

    public java.util.stream.Stream streamTemplatesFromResource() {
        return null;
    }

    public java.util.stream.Stream streamTemplates() {
        return null;
    }

    public void streamTemplates(java.nio.file.Path p0, java.lang.String p1, java.lang.String p2, java.util.function.Consumer p3) {
    }

    public java.lang.String toRelativePath(java.nio.file.Path p0, java.nio.file.Path p1) {
        return null;
    }

    public void unloadTemplate(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
    }

    public StructureTemplateManager() {
    }

    public static class Provider {
        public java.util.function.Supplier<java.util.stream.Stream<murat.simv2.simulation.mirror.net.minecraft.util.Identifier>> lister;
        public java.util.function.Function<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, java.util.Optional<java.lang.Object>> loader;

        public Provider(java.util.function.Function p0, java.util.function.Supplier p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public java.util.function.Supplier lister() {
            return null;
        }

        public java.util.function.Function loader() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public Provider() {
        }

    }

    public static interface TemplateFileOpener {
        public java.io.InputStream open();

    }

    // END GENERATED MIRROR NESTED STUBS
}
