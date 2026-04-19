package murat.simv2.simulation.mirror.net.minecraft.entity.ai.brain;

// Generated mirror stub for simulation closure.
public class Brain<E> extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static int ACTIVITY_REFRESH_COOLDOWN;
    public static org.slf4j.Logger LOGGER;
    public long activityStartTime;
    public java.util.function.Supplier<com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.entity.ai.brain.Brain<java.lang.Object>>> codecSupplier;
    public java.util.Set<java.lang.Object> coreActivities;
    public java.lang.Object defaultActivity;
    public java.util.Map<java.lang.Object, java.util.Set<java.lang.Object>> forgettingActivityMemories;
    public java.util.Map<java.lang.Object, java.util.Optional<? extends java.lang.Object>> memories;
    public java.util.Set<java.lang.Object> possibleActivities;
    public java.util.Map<java.lang.Object, java.util.Set<com.mojang.datafixers.util.Pair<java.lang.Object, java.lang.Object>>> requiredActivityMemories;
    public java.lang.Object schedule;
    public java.util.Map<java.lang.Object, java.lang.Object> sensors;
    public java.util.Map<java.lang.Integer, java.util.Map<java.lang.Object, java.util.Set<java.lang.Object>>> tasks;

    public Brain(java.util.Collection p0, java.util.Collection p1, com.google.common.collect.ImmutableList p2, java.util.function.Supplier p3) {
    }

    public boolean canDoActivity(java.lang.Object p0) {
        return false;
    }

    public void clear() {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.brain.Brain copy() {
        return null;
    }

    public static com.mojang.serialization.Codec createBrainCodec(java.util.Collection p0, java.util.Collection p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.entity.ai.brain.Brain.Profile createProfile(java.util.Collection p0, java.util.Collection p1) {
        return null;
    }

    public void doExclusively(java.lang.Object p0) {
    }

    public com.mojang.serialization.DataResult encode(com.mojang.serialization.DynamicOps p0) {
        return null;
    }

    public void forgetAll() {
    }

    public void forgetIrrelevantMemories(java.lang.Object p0) {
    }

    public void forget(java.lang.Object p0) {
    }

    public java.util.Optional getFirstPossibleNonCoreActivity() {
        return null;
    }

    public java.util.Map getMemories() {
        return null;
    }

    public long getMemoryExpiry(java.lang.Object p0) {
        return 0L;
    }

    public java.util.Optional getOptionalMemory(java.lang.Object p0) {
        return null;
    }

    public java.util.Optional getOptionalRegisteredMemory(java.lang.Object p0) {
        return null;
    }

    public java.util.Set getPossibleActivities() {
        return null;
    }

    public java.util.List getRunningTasks() {
        return null;
    }

    public java.lang.Object getSchedule() {
        return null;
    }

    public boolean hasActivity(java.lang.Object p0) {
        return false;
    }

    public boolean hasMemoryModuleWithValue(java.lang.Object p0, java.lang.Object p1) {
        return false;
    }

    public boolean hasMemoryModule(java.lang.Object p0) {
        return false;
    }

    public com.google.common.collect.ImmutableList indexTaskList(int p0, com.google.common.collect.ImmutableList p1) {
        return null;
    }

    public boolean isEmptyCollection(java.lang.Object p0) {
        return false;
    }

    public boolean isMemoryInState(java.lang.Object p0, java.lang.Object p1) {
        return false;
    }

    public void refreshActivities(long p0, long p1) {
    }

    public void remember(java.lang.Object p0, java.lang.Object p1) {
    }

    public void remember(java.lang.Object p0, java.lang.Object p1, long p2) {
    }

    public void remember(java.lang.Object p0, java.util.Optional p1) {
    }

    public void resetPossibleActivities() {
    }

    public void resetPossibleActivities(java.lang.Object p0) {
    }

    public void resetPossibleActivities(java.util.List p0) {
    }

    public void setCoreActivities(java.util.Set p0) {
    }

    public void setDefaultActivity(java.lang.Object p0) {
    }

    public void setMemory(java.lang.Object p0, java.util.Optional p1) {
    }

    public void setSchedule(java.lang.Object p0) {
    }

    public void setTaskList(java.lang.Object p0, com.google.common.collect.ImmutableList p1) {
    }

    public void setTaskList(java.lang.Object p0, com.google.common.collect.ImmutableList p1, java.util.Set p2) {
    }

    public void setTaskList(java.lang.Object p0, com.google.common.collect.ImmutableList p1, java.util.Set p2, java.util.Set p3) {
    }

    public void setTaskList(java.lang.Object p0, int p1, com.google.common.collect.ImmutableList p2) {
    }

    public void setTaskList(java.lang.Object p0, int p1, com.google.common.collect.ImmutableList p2, java.lang.Object p3) {
    }

    public void setTaskList(java.lang.Object p0, int p1, com.google.common.collect.ImmutableList p2, java.util.Set p3) {
    }

    public void startTasks(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1) {
    }

    public void stopAllTasks(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1) {
    }

    public java.util.stream.Stream streamMemories() {
        return null;
    }

    public void tickMemories() {
    }

    public void tickSensors(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1) {
    }

    public void tick(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1) {
    }

    public void updateTasks(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1) {
    }

    public Brain() {
    }

    public static class MemoryEntry<U> extends java.lang.Object {
        public java.util.Optional<? extends java.lang.Object> data;
        public java.lang.Object type;

        public MemoryEntry(java.lang.Object p0, java.util.Optional p1) {
        }

        public void apply(murat.simv2.simulation.mirror.net.minecraft.entity.ai.brain.Brain p0) {
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.ai.brain.Brain.MemoryEntry of(java.lang.Object p0, java.util.Optional p1) {
            return null;
        }

        public void serialize(com.mojang.serialization.DynamicOps p0, com.mojang.serialization.RecordBuilder p1) {
        }

        public MemoryEntry() {
        }

    }

    public static class Profile<E> extends java.lang.Object {
        public com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.entity.ai.brain.Brain<java.lang.Object>> codec;
        public java.util.Collection<? extends java.lang.Object> memoryModules;
        public java.util.Collection<? extends java.lang.Object> sensors;

        public Profile(java.util.Collection p0, java.util.Collection p1) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.entity.ai.brain.Brain deserialize(com.mojang.serialization.Dynamic p0) {
            return null;
        }

        public Profile() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
