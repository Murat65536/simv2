package murat.simv2.simulation.mirror.net.minecraft.stat;

// Generated mirror stub for simulation closure.
public class ServerStatHandler extends murat.simv2.simulation.mirror.net.minecraft.stat.StatHandler {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<java.util.Map<murat.simv2.simulation.mirror.net.minecraft.stat.Stat<?>, java.lang.Integer>> CODEC;
    public static org.slf4j.Logger LOGGER;
    public java.io.File file;
    public java.util.Set<murat.simv2.simulation.mirror.net.minecraft.stat.Stat<?>> pendingStats;
    public murat.simv2.simulation.mirror.net.minecraft.server.MinecraftServer server;
    public it.unimi.dsi.fastutil.objects.Object2IntMap<murat.simv2.simulation.mirror.net.minecraft.stat.Stat<?>> statMap;

    public ServerStatHandler(murat.simv2.simulation.mirror.net.minecraft.server.MinecraftServer p0, java.io.File p1) {
    }

    public java.lang.String asString() {
        return null;
    }

    public static com.mojang.serialization.Codec createCodec(java.lang.Object p0) {
        return null;
    }

    public int getStat(java.lang.Object p0, java.lang.Object p1) {
        return 0;
    }

    public int getStat(murat.simv2.simulation.mirror.net.minecraft.stat.Stat p0) {
        return 0;
    }

    public void increaseStat(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.stat.Stat p1, int p2) {
    }

    public void parse(com.mojang.datafixers.DataFixer p0, java.lang.String p1) {
    }

    public void save() {
    }

    public void sendStats(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
    }

    public void setStat(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.stat.Stat p1, int p2) {
    }

    public java.util.Set takePendingStats() {
        return null;
    }

    public void updateStatSet() {
    }

    public ServerStatHandler() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
