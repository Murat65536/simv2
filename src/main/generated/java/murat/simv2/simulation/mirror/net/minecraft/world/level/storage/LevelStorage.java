package murat.simv2.simulation.mirror.net.minecraft.world.level.storage;

// Generated mirror stub for simulation closure.
public class LevelStorage extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.lang.String ALLOWED_SYMLINKS_FILE_NAME;
    public static java.lang.String DATA_KEY;
    public static java.nio.file.PathMatcher DEFAULT_ALLOWED_SYMLINK_MATCHER;
    public static org.slf4j.Logger LOGGER;
    public static int MAX_LEVEL_DATA_SIZE;
    public static int RECOMMENDED_USABLE_SPACE_BYTES;
    public static java.time.format.DateTimeFormatter TIME_FORMATTER;
    public java.nio.file.Path backupsDirectory;
    public com.mojang.datafixers.DataFixer dataFixer;
    public java.nio.file.Path savesDirectory;
    public murat.simv2.simulation.mirror.net.minecraft.util.path.SymlinkFinder symlinkFinder;

    public LevelStorage(java.nio.file.Path p0, java.nio.file.Path p1, murat.simv2.simulation.mirror.net.minecraft.util.path.SymlinkFinder p2, com.mojang.datafixers.DataFixer p3) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.Session createSessionWithoutSymlinkCheck(java.lang.String p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.Session createSession(java.lang.String p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.path.SymlinkFinder createSymlinkFinder(java.nio.file.Path p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage create(java.nio.file.Path p0) {
        return null;
    }

    public java.nio.file.Path getBackupsDirectory() {
        return null;
    }

    public int getCurrentVersion() {
        return 0;
    }

    public java.lang.String getFormatName() {
        return null;
    }

    public static java.time.Instant getLastModifiedTime(java.nio.file.Path p0) {
        return null;
    }

    public static long getLastModifiedTime(murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.LevelSave p0) {
        return 0L;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.LevelList getLevelList() {
        return null;
    }

    public java.nio.file.Path getSavesDirectory() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.path.SymlinkFinder getSymlinkFinder() {
        return null;
    }

    public boolean isLevelNameValid(java.lang.String p0) {
        return false;
    }

    public boolean levelExists(java.lang.String p0) {
        return false;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.nbt.NbtElement loadCompactLevelData(java.nio.file.Path p0) {
        return null;
    }

    public java.util.concurrent.CompletableFuture loadSummaries(murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.LevelList p0) {
        return null;
    }

    public static java.lang.Object parseDataPackSettings(com.mojang.serialization.Dynamic p0) {
        return null;
    }

    public static java.lang.Object parseDataPacks(com.mojang.serialization.Dynamic p0, murat.simv2.simulation.mirror.net.minecraft.resource.ResourcePackManager p1, boolean p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.resource.featuretoggle.FeatureSet parseEnabledFeatures(com.mojang.serialization.Dynamic p0) {
        return null;
    }

    public static java.lang.Object parseSaveProperties(com.mojang.serialization.Dynamic p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.registry.Registry p2, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p3) {
        return null;
    }

    public java.lang.Object parseSummary(com.mojang.serialization.Dynamic p0, murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.LevelSave p1, boolean p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound readLevelProperties(java.nio.file.Path p0) {
        return null;
    }

    public static com.mojang.serialization.Dynamic readLevelProperties(java.nio.file.Path p0, com.mojang.datafixers.DataFixer p1) {
        return null;
    }

    public java.lang.Object readSummary(murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.LevelSave p0, boolean p1) {
        return null;
    }

    public java.nio.file.Path resolve(java.lang.String p0) {
        return null;
    }

    public LevelStorage() {
    }

    public static class LevelList {
        public java.util.List<murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.LevelSave> levels;

        public LevelList(java.util.List p0) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public boolean isEmpty() {
            return false;
        }

        public java.util.Iterator iterator() {
            return null;
        }

        public java.util.List levels() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public LevelList() {
        }

    }

    public static class LevelSave {
        public java.nio.file.Path path;

        public LevelSave(java.nio.file.Path p0) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public java.nio.file.Path getCorruptedLevelDatPath(java.time.LocalDateTime p0) {
            return null;
        }

        public java.nio.file.Path getIconPath() {
            return null;
        }

        public java.nio.file.Path getLevelDatOldPath() {
            return null;
        }

        public java.nio.file.Path getLevelDatPath() {
            return null;
        }

        public java.nio.file.Path getPath(java.lang.Object p0) {
            return null;
        }

        public java.nio.file.Path getRawLevelDatPath(java.time.LocalDateTime p0) {
            return null;
        }

        public java.lang.String getRootPath() {
            return null;
        }

        public java.nio.file.Path getSessionLockPath() {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public java.nio.file.Path path() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public LevelSave() {
        }

    }

    public static class Session extends java.lang.Object {
        public murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.LevelSave directory;
        public java.lang.String directoryName;
        public murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage field_23766;
        public java.lang.Object lock;
        public java.util.Map<java.lang.Object, java.nio.file.Path> paths;

        public Session(murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage p0, java.lang.String p1, java.nio.file.Path p2) {
        }

        public void backupLevelDataFile(murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager p0, java.lang.Object p1) {
        }

        public void backupLevelDataFile(murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p2) {
        }

        public void checkValid() {
        }

        public void close() {
        }

        public long createBackup() {
            return 0L;
        }

        public java.lang.Object createSaveHandler() {
            return null;
        }

        public void deleteSessionLock() {
        }

        public java.lang.String getDirectoryName() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage.LevelSave getDirectory() {
            return null;
        }

        public java.nio.file.Path getDirectory(java.lang.Object p0) {
            return null;
        }

        public java.util.Optional getIconFile() {
            return null;
        }

        public java.time.Instant getLastModifiedTime(boolean p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.world.level.storage.LevelStorage getLevelStorage() {
            return null;
        }

        public java.lang.Object getLevelSummary(com.mojang.serialization.Dynamic p0) {
            return null;
        }

        public long getUsableSpace() {
            return 0L;
        }

        public java.nio.file.Path getWorldDirectory(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
            return null;
        }

        public boolean levelDatExists() {
            return false;
        }

        public com.mojang.serialization.Dynamic readLevelProperties() {
            return null;
        }

        public com.mojang.serialization.Dynamic readLevelProperties(boolean p0) {
            return null;
        }

        public com.mojang.serialization.Dynamic readOldLevelProperties() {
            return null;
        }

        public void removePlayerAndSave(java.lang.String p0) {
        }

        public void save(java.lang.String p0) {
        }

        public void save(java.util.function.Consumer p0) {
        }

        public void save(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
        }

        public boolean shouldShowLowDiskSpaceWarning() {
            return false;
        }

        public void tryClose() {
        }

        public boolean tryRestoreBackup() {
            return false;
        }

        public Session() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
