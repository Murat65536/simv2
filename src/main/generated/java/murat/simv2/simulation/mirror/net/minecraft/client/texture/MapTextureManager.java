package murat.simv2.simulation.mirror.net.minecraft.client.texture;

// Generated mirror stub for simulation closure.
public class MapTextureManager extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public murat.simv2.simulation.mirror.net.minecraft.client.texture.TextureManager textureManager;
    public it.unimi.dsi.fastutil.ints.Int2ObjectMap<murat.simv2.simulation.mirror.net.minecraft.client.texture.MapTextureManager.MapTexture> texturesByMapId;

    public MapTextureManager(murat.simv2.simulation.mirror.net.minecraft.client.texture.TextureManager p0) {
    }

    public void clear() {
    }

    public void close() {
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.texture.MapTextureManager.MapTexture getMapTexture(murat.simv2.simulation.mirror.net.minecraft.component.type.MapIdComponent p0, murat.simv2.simulation.mirror.net.minecraft.item.map.MapState p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.Identifier getTextureId(murat.simv2.simulation.mirror.net.minecraft.component.type.MapIdComponent p0, murat.simv2.simulation.mirror.net.minecraft.item.map.MapState p1) {
        return null;
    }

    public void setNeedsUpdate(murat.simv2.simulation.mirror.net.minecraft.component.type.MapIdComponent p0, murat.simv2.simulation.mirror.net.minecraft.item.map.MapState p1) {
    }

    public MapTextureManager() {
    }

    public static class MapTexture extends java.lang.Object {
        public boolean needsUpdate;
        public murat.simv2.simulation.mirror.net.minecraft.item.map.MapState state;
        public java.lang.Object texture;
        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier textureId;

        public MapTexture(murat.simv2.simulation.mirror.net.minecraft.client.texture.MapTextureManager p0, int p1, murat.simv2.simulation.mirror.net.minecraft.item.map.MapState p2) {
        }

        public void close() {
        }

        public void setNeedsUpdate() {
        }

        public void setState(murat.simv2.simulation.mirror.net.minecraft.item.map.MapState p0) {
        }

        public void updateTexture() {
        }

        public MapTexture() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
