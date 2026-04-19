package murat.simv2.simulation.mirror.net.minecraft.resource;

// Generated mirror stub for simulation closure.
public interface ResourceManager {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public java.util.Map findAllResources(java.lang.String p0, java.util.function.Predicate p1);

    public java.util.Map findResources(java.lang.String p0, java.util.function.Predicate p1);

    public static java.lang.Object fromMap(java.util.Map p0) {
        return null;
    }

    public java.util.Set getAllNamespaces();

    public java.util.List getAllResources(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0);

    public java.lang.Object getResourceOrThrow(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0);

    public java.util.Optional getResource(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0);

    public java.io.BufferedReader openAsReader(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0);

    public java.io.InputStream open(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0);

    public java.util.stream.Stream streamResourcePacks();

    public static class Empty implements murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager {
        public static murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager.Empty INSTANCE;
        public static murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager.Empty[] field_25352;

        public Empty(java.lang.String p0, int p1) {
        }

        public java.util.Map findAllResources(java.lang.String p0, java.util.function.Predicate p1) {
            return null;
        }

        public java.util.Map findResources(java.lang.String p0, java.util.function.Predicate p1) {
            return null;
        }

        public static java.lang.Object fromMap(java.util.Map p0) {
            return null;
        }

        public java.util.Set getAllNamespaces() {
            return null;
        }

        public java.util.List getAllResources(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
            return null;
        }

        public java.lang.Object getResourceOrThrow(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
            return null;
        }

        public java.util.Optional getResource(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
            return null;
        }

        public java.io.BufferedReader openAsReader(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
            return null;
        }

        public java.io.InputStream open(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
            return null;
        }

        public int ordinal() {
            return 0;
        }

        public java.util.stream.Stream streamResourcePacks() {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager.Empty valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager.Empty[] values() {
            return null;
        }

        public Empty() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
