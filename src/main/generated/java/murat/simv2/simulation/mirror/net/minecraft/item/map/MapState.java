package murat.simv2.simulation.mirror.net.minecraft.item.map;

// Generated mirror stub for simulation closure.
public class MapState {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.item.map.MapState> CODEC;
    public static java.lang.String FRAME_PREFIX;
    public static org.slf4j.Logger LOGGER;
    public static int MAX_DECORATIONS;
    public static int MAX_SCALE;
    public static int SIZE;
    public static int SIZE_HALF;
    public java.util.Map<java.lang.String, java.lang.Object> banners;
    public int centerX;
    public int centerZ;
    public byte[] colors;
    public int decorationCount;
    public java.util.Map<java.lang.String, java.lang.Object> decorations;
    public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<murat.simv2.simulation.mirror.net.minecraft.world.World> dimension;
    public java.util.Map<java.lang.String, java.lang.Object> frames;
    public boolean locked;
    public byte scale;
    public boolean showDecorations;
    public boolean unlimitedTracking;
    public java.util.List<murat.simv2.simulation.mirror.net.minecraft.item.map.MapState.PlayerUpdateTracker> updateTrackers;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity, murat.simv2.simulation.mirror.net.minecraft.item.map.MapState.PlayerUpdateTracker> updateTrackersByPlayer;

    public MapState(int p0, int p1, byte p2, boolean p3, boolean p4, boolean p5, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p6) {
    }

    public MapState(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, int p1, int p2, byte p3, java.nio.ByteBuffer p4, boolean p5, boolean p6, boolean p7, java.util.List p8, java.util.List p9) {
    }

    public boolean addBanner(murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public static void addDecorationsNbt(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, java.lang.String p2, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p3) {
    }

    public void addDecoration(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p1, java.lang.String p2, double p3, double p4, double p5, murat.simv2.simulation.mirror.net.minecraft.text.Text p6) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.map.MapState copy() {
        return null;
    }

    public static java.lang.Object createStateType(murat.simv2.simulation.mirror.net.minecraft.component.type.MapIdComponent p0) {
        return null;
    }

    public boolean decorationCountNotLessThan(int p0) {
        return false;
    }

    public java.util.Collection getBanners() {
        return null;
    }

    public java.lang.Iterable getDecorations() {
        return null;
    }

    public static java.util.function.Predicate getEqualPredicate(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return null;
    }

    public static java.lang.String getFrameDecorationKey(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.map.MapState.Marker getMarker(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p1, double p2, float p3, float p4) {
        return null;
    }

    public com.mojang.datafixers.util.Pair getPlayerMarkerAndRotation(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p1, double p2, float p3, float p4) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet getPlayerMarkerPacket(murat.simv2.simulation.mirror.net.minecraft.component.type.MapIdComponent p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1) {
        return null;
    }

    public byte getPlayerMarkerRotation(murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p0, double p1) {
        return (byte) 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry getPlayerMarker(float p0, float p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.map.MapState.PlayerUpdateTracker getPlayerSyncData(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
        return null;
    }

    public boolean hasExplorationMapDecoration() {
        return false;
    }

    public static boolean hasMapInvisibilityEquipment(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
        return false;
    }

    public boolean isDirty() {
        return false;
    }

    public static boolean isInBounds(float p0, float p1) {
        return false;
    }

    public void markDecorationsDirty() {
    }

    public void markDirty() {
    }

    public void markDirty(int p0, int p1) {
    }

    public static byte offsetToMarkerPosition(float p0) {
        return (byte) 0;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.item.map.MapState of(byte p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.item.map.MapState of(double p0, double p1, byte p2, boolean p3, boolean p4, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p5) {
        return null;
    }

    public boolean putColor(int p0, int p1, byte p2) {
        return false;
    }

    public void removeBanner(murat.simv2.simulation.mirror.net.minecraft.world.BlockView p0, int p1, int p2) {
    }

    public void removeDecoration(java.lang.String p0) {
    }

    public void removeFrame(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1) {
    }

    public void replaceDecorations(java.util.List p0) {
    }

    public void setColor(int p0, int p1, byte p2) {
    }

    public void setDirty(boolean p0) {
    }

    public void update(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.map.MapState zoomOut() {
        return null;
    }

    public MapState() {
    }

    public static class Marker {
        public byte rot;
        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<java.lang.Object> type;
        public byte x;
        public byte y;

        public Marker(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, byte p1, byte p2, byte p3) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public byte rot() {
            return (byte) 0;
        }

        public java.lang.String toString() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry type() {
            return null;
        }

        public byte x() {
            return (byte) 0;
        }

        public byte y() {
            return (byte) 0;
        }

        public Marker() {
        }

    }

    public static class PlayerUpdateTracker extends java.lang.Object {
        public boolean decorationsDirty;
        public boolean dirty;
        public int emptyPacketsRequested;
        public int endX;
        public int endZ;
        public int field_131;
        public murat.simv2.simulation.mirror.net.minecraft.item.map.MapState field_132;
        public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity player;
        public int startX;
        public int startZ;

        public PlayerUpdateTracker(murat.simv2.simulation.mirror.net.minecraft.item.map.MapState p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.item.map.MapState.UpdateData getMapUpdateData() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet getPacket(murat.simv2.simulation.mirror.net.minecraft.component.type.MapIdComponent p0) {
            return null;
        }

        public void markDecorationsDirty() {
        }

        public void markDirty(int p0, int p1) {
        }

        public PlayerUpdateTracker() {
        }

    }

    public static class UpdateData {
        public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<io.netty.buffer.ByteBuf, java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.item.map.MapState.UpdateData>> CODEC;
        public byte[] colors;
        public int height;
        public int startX;
        public int startZ;
        public int width;

        public UpdateData(int p0, int p1, int p2, int p3, byte[] p4) {
        }

        public byte[] colors() {
            return null;
        }

        public static java.util.Optional decode(io.netty.buffer.ByteBuf p0) {
            return null;
        }

        public static void encode(io.netty.buffer.ByteBuf p0, java.util.Optional p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public int height() {
            return 0;
        }

        public void setColorsTo(murat.simv2.simulation.mirror.net.minecraft.item.map.MapState p0) {
        }

        public int startX() {
            return 0;
        }

        public int startZ() {
            return 0;
        }

        public java.lang.String toString() {
            return null;
        }

        public int width() {
            return 0;
        }

        public UpdateData() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
