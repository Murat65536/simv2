package murat.simv2.simulation.mirror.net.minecraft.world.level;

// Generated mirror stub for simulation closure.
public interface ServerWorldProperties extends murat.simv2.simulation.mirror.net.minecraft.world.MutableWorldProperties {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public boolean areCommandsAllowed();

    public int getClearWeatherTime();

    public murat.simv2.simulation.mirror.net.minecraft.world.Difficulty getDifficulty();

    public murat.simv2.simulation.mirror.net.minecraft.world.GameMode getGameMode();

    public murat.simv2.simulation.mirror.net.minecraft.world.GameRules getGameRules();

    public java.lang.String getLevelName();

    public int getRainTime();

    public java.lang.Object getScheduledEvents();

    public float getSpawnAngle();

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getSpawnPos();

    public int getThunderTime();

    public long getTimeOfDay();

    public long getTime();

    public java.util.UUID getWanderingTraderId();

    public int getWanderingTraderSpawnChance();

    public int getWanderingTraderSpawnDelay();

    public murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder.Properties getWorldBorder();

    public boolean isDifficultyLocked();

    public boolean isHardcore();

    public boolean isInitialized();

    public boolean isRaining();

    public boolean isThundering();

    public void populateCrashReport(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection p0, murat.simv2.simulation.mirror.net.minecraft.world.HeightLimitView p1);

    public void setClearWeatherTime(int p0);

    public void setGameMode(murat.simv2.simulation.mirror.net.minecraft.world.GameMode p0);

    public void setInitialized(boolean p0);

    public void setRainTime(int p0);

    public void setRaining(boolean p0);

    public void setSpawnPos(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, float p1);

    public void setThunderTime(int p0);

    public void setThundering(boolean p0);

    public void setTimeOfDay(long p0);

    public void setTime(long p0);

    public void setWanderingTraderId(java.util.UUID p0);

    public void setWanderingTraderSpawnChance(int p0);

    public void setWanderingTraderSpawnDelay(int p0);

    public void setWorldBorder(murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder.Properties p0);

    // END GENERATED MIRROR NESTED STUBS
}
