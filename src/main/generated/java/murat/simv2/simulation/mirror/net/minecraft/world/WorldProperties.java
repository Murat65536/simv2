package murat.simv2.simulation.mirror.net.minecraft.world;

// Generated mirror stub for simulation closure.
public interface WorldProperties {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public murat.simv2.simulation.mirror.net.minecraft.world.Difficulty getDifficulty();

    public float getSpawnAngle();

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getSpawnPos();

    public long getTimeOfDay();

    public long getTime();

    public boolean isDifficultyLocked();

    public boolean isHardcore();

    public boolean isRaining();

    public boolean isThundering();

    public void populateCrashReport(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection p0, murat.simv2.simulation.mirror.net.minecraft.world.HeightLimitView p1);

    public void setRaining(boolean p0);

    // END GENERATED MIRROR NESTED STUBS
}
