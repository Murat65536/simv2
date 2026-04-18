package murat.simv2.simulation.mirror.net.minecraft.util;

// Generated mirror stub for simulation closure.
public class Util {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static int BACKUP_ATTEMPTS;
    public static java.time.format.DateTimeFormatter DATE_TIME_FORMATTER;
    public static java.lang.Object DOWNLOAD_WORKER_EXECUTOR;
    public static java.lang.Object IO_WORKER_EXECUTOR;
    public static java.nio.file.spi.FileSystemProvider JAR_FILE_SYSTEM_PROVIDER;
    public static org.slf4j.Logger LOGGER;
    public static java.lang.Object MAIN_WORKER_EXECUTOR;
    public static java.lang.String MAX_BG_THREADS_PROPERTY;
    public static int MAX_PARALLELISM;
    public static java.util.UUID NIL_UUID;
    public static java.util.Set SUPPORTED_URI_PROTOCOLS;
    public static com.google.common.base.Ticker TICKER;
    public static long field_45714;
    public static int field_46220;
    public static java.util.function.Consumer missingBreakpointHandler;
    public static java.lang.Object nanoTimeSupplier;

    public Util() {
    }

    public static java.util.function.Consumer addPrefix(java.lang.String p0, java.util.function.Consumer p1) {
        return null;
    }

    public static java.util.function.Predicate allOf(java.util.List p0) {
        return null;
    }

    public static java.util.function.Predicate and() {
        return null;
    }

    public static java.util.function.Predicate and(java.util.function.Predicate p0) {
        return null;
    }

    public static java.util.function.Predicate and(java.util.function.Predicate p0, java.util.function.Predicate p1) {
        return null;
    }

    public static java.util.function.Predicate and(java.util.function.Predicate p0, java.util.function.Predicate p1, java.util.function.Predicate p2) {
        return null;
    }

    public static java.util.function.Predicate and(java.util.function.Predicate p0, java.util.function.Predicate p1, java.util.function.Predicate p2, java.util.function.Predicate p3) {
        return null;
    }

    public static java.util.function.Predicate and(java.util.function.Predicate p0, java.util.function.Predicate p1, java.util.function.Predicate p2, java.util.function.Predicate p3, java.util.function.Predicate p4) {
        return null;
    }

    public static java.util.function.Predicate and(java.util.function.Predicate[] p0) {
        return null;
    }

    public static java.util.function.Predicate anyOf(java.util.List p0) {
        return null;
    }

    public static com.mojang.datafixers.Typed apply(com.mojang.datafixers.Typed p0, com.mojang.datafixers.types.Type p1, java.util.function.UnaryOperator p2) {
        return null;
    }

    public static boolean attemptTasks(int p0, java.lang.String p1, java.util.function.BooleanSupplier[] p2) {
        return false;
    }

    public static boolean attemptTasks(java.util.function.BooleanSupplier[] p0) {
        return false;
    }

    public static void backupAndReplace(java.nio.file.Path p0, java.nio.file.Path p1, java.nio.file.Path p2) {
    }

    public static boolean backupAndReplace(java.nio.file.Path p0, java.nio.file.Path p1, java.nio.file.Path p2, boolean p3) {
        return false;
    }

    public static java.lang.Object cachedMapper(java.util.function.Function p0) {
        return null;
    }

    public static java.util.concurrent.CompletableFuture combineCancellable(java.util.List p0) {
        return null;
    }

    public static java.util.concurrent.CompletableFuture combineSafe(java.util.List p0) {
        return null;
    }

    public static java.util.concurrent.CompletableFuture combine(java.util.List p0) {
        return null;
    }

    public static java.util.concurrent.CompletableFuture combine(java.util.List p0, java.util.function.Consumer p1) {
        return null;
    }

    public static java.util.List copyShuffled(it.unimi.dsi.fastutil.objects.ObjectArrayList p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1) {
        return null;
    }

    public static java.util.List copyShuffled(java.lang.Object[] p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1) {
        return null;
    }

    public static java.util.List copyShuffled(java.util.stream.Stream p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1) {
        return null;
    }

    public static java.lang.Object createIoWorker(java.lang.String p0, boolean p1) {
        return null;
    }

    public static java.lang.String createTranslationKey(java.lang.String p0, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p1) {
        return null;
    }

    public static java.lang.Object createWorker(java.lang.String p0) {
        return null;
    }

    public static java.lang.Runnable debugRunnable(java.lang.Runnable p0, java.util.function.Supplier p1) {
        return null;
    }

    public static java.util.function.Supplier debugSupplier(java.util.function.Supplier p0, java.util.function.Supplier p1) {
        return null;
    }

    public static com.mojang.serialization.DataResult decodeFixedLengthArray(java.util.stream.IntStream p0, int p1) {
        return null;
    }

    public static com.mojang.serialization.DataResult decodeFixedLengthArray(java.util.stream.LongStream p0, int p1) {
        return null;
    }

    public static com.mojang.serialization.DataResult decodeFixedLengthList(java.util.List p0, int p1) {
        return null;
    }

    public static java.util.function.BooleanSupplier deleteTask(java.nio.file.Path p0) {
        return null;
    }

    public static java.util.function.BooleanSupplier deletionVerifyTask(java.nio.file.Path p0) {
        return null;
    }

    public static java.util.function.BooleanSupplier existenceCheckTask(java.nio.file.Path p0) {
        return null;
    }

    public static int getAvailableBackgroundThreads() {
        return 0;
    }

    public static com.mojang.datafixers.types.Type getChoiceTypeInternal(com.mojang.datafixers.DSL.TypeReference p0, java.lang.String p1) {
        return null;
    }

    public static com.mojang.datafixers.types.Type getChoiceType(com.mojang.datafixers.DSL.TypeReference p0, java.lang.String p1) {
        return null;
    }

    public static java.lang.Object getDownloadWorkerExecutor() {
        return null;
    }

    public static long getEpochTimeMs() {
        return 0L;
    }

    public static java.lang.Throwable getFatalOrPause(java.lang.Throwable p0) {
        return null;
    }

    public static java.lang.String getFormattedCurrentTime() {
        return null;
    }

    public static java.lang.String getInnermostMessage(java.lang.Throwable p0) {
        return null;
    }

    public static java.lang.Object getIoWorkerExecutor() {
        return null;
    }

    public static java.util.stream.Stream getJVMFlags() {
        return null;
    }

    public static java.lang.Object getLast(java.util.List p0) {
        return null;
    }

    public static java.lang.Object getMainWorkerExecutor() {
        return null;
    }

    public static int getMaxBackgroundThreads() {
        return 0;
    }

    public static long getMeasuringTimeMs() {
        return 0L;
    }

    public static long getMeasuringTimeNano() {
        return 0L;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.Util.OperatingSystem getOperatingSystem() {
        return null;
    }

    public static java.util.Optional getRandomOrEmpty(java.util.List p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1) {
        return null;
    }

    public static int getRandom(int[] p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1) {
        return 0;
    }

    public static java.lang.Object getRandom(java.lang.Object[] p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1) {
        return null;
    }

    public static java.lang.Object getRandom(java.util.List p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1) {
        return null;
    }

    public static java.lang.String getValueAsString(java.lang.Object p0, java.lang.Object p1) {
        return null;
    }

    public static java.util.Optional ifPresentOrElse(java.util.Optional p0, java.util.function.Consumer p1, java.lang.Runnable p2) {
        return null;
    }

    public static boolean isSymmetrical(int p0, int p1, java.util.List p2) {
        return false;
    }

    public static java.util.function.ToIntFunction lastIdentityIndexGetter(java.util.List p0) {
        return null;
    }

    public static java.util.function.ToIntFunction lastIndexGetter(java.util.List p0) {
        return null;
    }

    public static void logErrorOrPause(java.lang.String p0) {
    }

    public static void logErrorOrPause(java.lang.String p0, java.lang.Throwable p1) {
    }

    public static java.lang.Object make(java.lang.Object p0, java.util.function.Consumer p1) {
        return null;
    }

    public static java.lang.Object make(java.util.function.Supplier p0) {
        return null;
    }

    public static java.util.Map mapEnum(java.lang.Class p0, java.util.function.Function p1) {
        return null;
    }

    public static java.util.Map mapWith(java.util.Map p0, java.lang.Object p1, java.lang.Object p2) {
        return null;
    }

    public static java.util.function.BiFunction memoize(java.util.function.BiFunction p0) {
        return null;
    }

    public static java.util.function.Function memoize(java.util.function.Function p0) {
        return null;
    }

    public static int moveCursor(java.lang.String p0, int p1, int p2) {
        return 0;
    }

    public static int nextCapacity(int p0, int p1) {
        return 0;
    }

    public static java.lang.Object next(java.lang.Iterable p0, java.lang.Object p1) {
        return null;
    }

    public static java.util.function.Predicate or() {
        return null;
    }

    public static java.util.function.Predicate or(java.util.function.Predicate p0) {
        return null;
    }

    public static java.util.function.Predicate or(java.util.function.Predicate p0, java.util.function.Predicate p1) {
        return null;
    }

    public static java.util.function.Predicate or(java.util.function.Predicate p0, java.util.function.Predicate p1, java.util.function.Predicate p2) {
        return null;
    }

    public static java.util.function.Predicate or(java.util.function.Predicate p0, java.util.function.Predicate p1, java.util.function.Predicate p2, java.util.function.Predicate p3) {
        return null;
    }

    public static java.util.function.Predicate or(java.util.function.Predicate p0, java.util.function.Predicate p1, java.util.function.Predicate p2, java.util.function.Predicate p3, java.util.function.Predicate p4) {
        return null;
    }

    public static java.util.function.Predicate or(java.util.function.Predicate[] p0) {
        return null;
    }

    public static void pause(java.lang.String p0) {
    }

    public static java.lang.Object previous(java.lang.Iterable p0, java.lang.Object p1) {
        return null;
    }

    public static com.mojang.datafixers.Typed readTyped(com.mojang.datafixers.types.Type p0, com.mojang.serialization.Dynamic p1) {
        return null;
    }

    public static com.mojang.datafixers.Typed readTyped(com.mojang.datafixers.types.Type p0, com.mojang.serialization.Dynamic p1, boolean p2) {
        return null;
    }

    public static java.lang.String registryValueToString(java.lang.Object p0, java.lang.Object p1) {
        return null;
    }

    public static void relativeCopy(java.nio.file.Path p0, java.nio.file.Path p1, java.nio.file.Path p2) {
    }

    public static java.util.function.BooleanSupplier renameTask(java.nio.file.Path p0, java.nio.file.Path p1) {
        return null;
    }

    public static java.lang.String replaceInvalidChars(java.lang.String p0, java.lang.Object p1) {
        return null;
    }

    public static void runInNamedZone(java.lang.Runnable p0, java.lang.String p1) {
    }

    public static void setMissingBreakpointHandler(java.util.function.Consumer p0) {
    }

    public static void shuffle(java.util.List p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1) {
    }

    public static it.unimi.dsi.fastutil.ints.IntArrayList shuffle(java.util.stream.IntStream p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1) {
        return null;
    }

    public static void shutdownExecutors() {
    }

    public static void startTimerHack() {
    }

    public static void throwUnchecked(java.lang.Throwable p0) {
    }

    public static java.util.stream.Collector toArrayList() {
        return null;
    }

    public static java.util.stream.Collector toMap() {
        return null;
    }

    public static java.util.Map transformMapValuesLazy(java.util.Map p0, com.google.common.base.Function p1) {
        return null;
    }

    public static java.util.Map transformMapValues(java.util.Map p0, java.util.function.Function p1) {
        return null;
    }

    public static void uncaughtExceptionHandler(java.lang.Thread p0, java.lang.Throwable p1) {
    }

    public static java.net.URI validateUri(java.lang.String p0) {
        return null;
    }

    public static java.util.concurrent.CompletableFuture waitAndApply(java.util.function.Function p0) {
        return null;
    }

    public static java.lang.Object waitAndApply(java.util.function.Function p0, java.util.function.Predicate p1) {
        return null;
    }

    public static java.util.List withAppended(java.util.List p0, java.lang.Object p1) {
        return null;
    }

    public static java.util.List withPrepended(java.lang.Object p0, java.util.List p1) {
        return null;
    }

    public static class OperatingSystem {
        public static murat.simv2.simulation.mirror.net.minecraft.util.Util.OperatingSystem LINUX;
        public static murat.simv2.simulation.mirror.net.minecraft.util.Util.OperatingSystem OSX;
        public static murat.simv2.simulation.mirror.net.minecraft.util.Util.OperatingSystem SOLARIS;
        public static murat.simv2.simulation.mirror.net.minecraft.util.Util.OperatingSystem UNKNOWN;
        public static murat.simv2.simulation.mirror.net.minecraft.util.Util.OperatingSystem WINDOWS;
        public static murat.simv2.simulation.mirror.net.minecraft.util.Util.OperatingSystem[] field_1136;
        public java.lang.String name;

        public OperatingSystem(java.lang.String p0, int p1, java.lang.String p2) {
        }

        public java.lang.String getName() {
            return null;
        }

        public java.lang.String[] getURIOpenCommand(java.net.URI p0) {
            return null;
        }

        public void open(java.io.File p0) {
        }

        public void open(java.lang.String p0) {
        }

        public void open(java.net.URI p0) {
        }

        public void open(java.nio.file.Path p0) {
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.Util.OperatingSystem valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.util.Util.OperatingSystem[] values() {
            return null;
        }

        public OperatingSystem() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
