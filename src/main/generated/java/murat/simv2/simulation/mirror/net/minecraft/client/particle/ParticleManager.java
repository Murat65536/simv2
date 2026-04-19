package murat.simv2.simulation.mirror.net.minecraft.client.particle;

// Generated mirror stub for simulation closure.
public class ParticleManager extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.lang.Object FINDER;
    public static org.slf4j.Logger LOGGER;
    public static int MAX_PARTICLE_COUNT;
    public static java.util.List<java.lang.Object> PARTICLE_TEXTURE_SHEETS;
    public it.unimi.dsi.fastutil.ints.Int2ObjectMap<java.lang.Object> factories;
    public it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap<java.lang.Object> groupCounts;
    public java.util.Queue<java.lang.Object> newEmitterParticles;
    public java.util.Queue<java.lang.Object> newParticles;
    public java.lang.Object particleAtlasTexture;
    public java.util.Map<java.lang.Object, java.util.Queue<java.lang.Object>> particles;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random random;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, murat.simv2.simulation.mirror.net.minecraft.client.particle.ParticleManager.SimpleSpriteProvider> spriteAwareFactories;
    public murat.simv2.simulation.mirror.net.minecraft.client.world.ClientWorld world;

    public ParticleManager(murat.simv2.simulation.mirror.net.minecraft.client.world.ClientWorld p0, murat.simv2.simulation.mirror.net.minecraft.client.texture.TextureManager p1) {
    }

    public void addBlockBreakParticles(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1) {
    }

    public void addBlockBreakingParticles(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1) {
    }

    public void addEmitter(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.particle.ParticleEffect p1) {
    }

    public void addEmitter(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.particle.ParticleEffect p1, int p2) {
    }

    public void addParticle(java.lang.Object p0) {
    }

    public java.lang.Object addParticle(murat.simv2.simulation.mirror.net.minecraft.particle.ParticleEffect p0, double p1, double p2, double p3, double p4, double p5, double p6) {
        return null;
    }

    public void addTo(java.lang.Object p0, int p1) {
    }

    public boolean canAdd(java.lang.Object p0) {
        return false;
    }

    public void clearAtlas() {
    }

    public void clearParticles() {
    }

    public java.lang.Object createParticle(murat.simv2.simulation.mirror.net.minecraft.particle.ParticleEffect p0, double p1, double p2, double p3, double p4, double p5, double p6) {
        return null;
    }

    public java.lang.String getDebugString() {
        return null;
    }

    public java.lang.String getName() {
        return null;
    }

    public java.util.Optional loadTextureList(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, java.lang.Object p1) {
        return null;
    }

    public void registerBlockLeakFactory(murat.simv2.simulation.mirror.net.minecraft.particle.ParticleType p0, java.lang.Object p1) {
    }

    public void registerDefaultFactories() {
    }

    public void registerFactory(murat.simv2.simulation.mirror.net.minecraft.particle.ParticleType p0, java.lang.Object p1) {
    }

    public void registerFactory(murat.simv2.simulation.mirror.net.minecraft.particle.ParticleType p0, murat.simv2.simulation.mirror.net.minecraft.client.particle.ParticleManager.SpriteAwareFactory p1) {
    }

    public java.util.concurrent.CompletableFuture reload(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p1, java.util.concurrent.Executor p2, java.util.concurrent.Executor p3) {
        return null;
    }

    public static void renderCustomParticles(java.lang.Object p0, float p1, java.lang.Object p2, java.util.Queue p3) {
    }

    public void renderParticles(java.lang.Object p0, float p1, java.lang.Object p2) {
    }

    public static void renderParticles(java.lang.Object p0, float p1, java.lang.Object p2, java.lang.Object p3, java.util.Queue p4) {
    }

    public void setWorld(murat.simv2.simulation.mirror.net.minecraft.client.world.ClientWorld p0) {
    }

    public void tickParticles(java.util.Collection p0) {
    }

    public void tickParticle(java.lang.Object p0) {
    }

    public void tick() {
    }

    public ParticleManager() {
    }

    public static class ReloadResult {
        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier id;
        public java.util.Optional<java.util.List<murat.simv2.simulation.mirror.net.minecraft.util.Identifier>> sprites;

        public ReloadResult(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, java.util.Optional p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.Identifier id() {
            return null;
        }

        public java.util.Optional sprites() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public ReloadResult() {
        }

    }

    public static class SimpleSpriteProvider extends java.lang.Object {
        public java.util.List<java.lang.Object> sprites;

        public SimpleSpriteProvider() {
        }

        public java.lang.Object getSprite(int p0, int p1) {
            return null;
        }

        public java.lang.Object getSprite(murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p0) {
            return null;
        }

        public void setSprites(java.util.List p0) {
        }

    }

    public static interface SpriteAwareFactory<T> {
        public java.lang.Object create(java.lang.Object p0);

    }

    // END GENERATED MIRROR NESTED STUBS
}
