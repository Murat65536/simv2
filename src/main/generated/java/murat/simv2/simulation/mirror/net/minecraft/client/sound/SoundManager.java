package murat.simv2.simulation.mirror.net.minecraft.client.sound;

// Generated mirror stub for simulation closure.
public class SoundManager {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier EMPTY_ID;
    public static com.google.gson.Gson GSON;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier INTENTIONALLY_EMPTY_ID;
    public static java.lang.Object INTENTIONALLY_EMPTY_SOUND;
    public static java.lang.Object INTENTIONALLY_EMPTY_SOUND_SET;
    public static org.slf4j.Logger LOGGER;
    public static java.lang.Object MISSING_SOUND;
    public static java.lang.String SOUNDS_JSON;
    public static com.google.gson.reflect.TypeToken<java.util.Map<java.lang.String, java.lang.Object>> TYPE;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, java.lang.Object> soundResources;
    public java.lang.Object soundSystem;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, java.lang.Object> sounds;

    public SoundManager(murat.simv2.simulation.mirror.net.minecraft.client.option.GameOptions p0) {
    }

    public void apply(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p1, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p2) {
    }

    public void apply(murat.simv2.simulation.mirror.net.minecraft.client.sound.SoundManager.SoundList p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p1, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p2) {
    }

    public void close() {
    }

    public java.lang.String getDebugString() {
        return null;
    }

    public java.util.Collection getKeys() {
        return null;
    }

    public java.lang.Object getListenerTransform() {
        return null;
    }

    public java.lang.String getName() {
        return null;
    }

    public java.util.List getSoundDevices() {
        return null;
    }

    public java.lang.Object get(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public boolean isPlaying(java.lang.Object p0) {
        return false;
    }

    public static boolean isSoundResourcePresent(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p1, java.lang.Object p2) {
        return false;
    }

    public void pauseAll() {
    }

    public void playNextTick(java.lang.Object p0) {
    }

    public void play(java.lang.Object p0) {
    }

    public void play(java.lang.Object p0, int p1) {
    }

    public java.lang.Object prepare(murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p0, murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler p1) {
        return null;
    }

    public void registerListener(java.lang.Object p0) {
    }

    public void reloadSounds() {
    }

    public java.util.concurrent.CompletableFuture reload(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p1, java.util.concurrent.Executor p2, java.util.concurrent.Executor p3) {
        return null;
    }

    public void resumeAll() {
    }

    public void setVolume(java.lang.Object p0, float p1) {
    }

    public void stopAbruptly() {
    }

    public void stopAll() {
    }

    public void stopSounds(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory p1) {
    }

    public void stop(java.lang.Object p0) {
    }

    public void tick(boolean p0) {
    }

    public void unregisterListener(java.lang.Object p0) {
    }

    public void updateListenerPosition(java.lang.Object p0) {
    }

    public void updateSoundVolume(murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory p0, float p1) {
    }

    public SoundManager() {
    }

    public static class SoundList extends java.lang.Object {
        public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, java.lang.Object> foundSounds;
        public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, java.lang.Object> loadedSounds;

        public SoundList() {
        }

        public void findSounds(murat.simv2.simulation.mirror.net.minecraft.resource.ResourceManager p0) {
        }

        public void register(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, java.lang.Object p1) {
        }

        public void reload(java.util.Map p0, java.util.Map p1, java.lang.Object p2) {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
