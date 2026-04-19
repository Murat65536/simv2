package murat.simv2.simulation.mirror.net.minecraft.world.border;

// Generated mirror stub for simulation closure.
public class WorldBorder extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder.Properties DEFAULT_BORDER;
    public static double MAX_CENTER_COORDINATES;
    public static double STATIC_AREA_SIZE;
    public murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder.Area area;
    public double centerX;
    public double centerZ;
    public double damagePerBlock;
    public java.util.List<murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorderListener> listeners;
    public int maxRadius;
    public double safeZone;
    public int warningBlocks;
    public int warningTime;

    public WorldBorder() {
    }

    public void addListener(murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorderListener p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape asVoxelShape() {
        return null;
    }

    public java.util.List calculateDistancesFromCamera(double p0, double p1) {
        return null;
    }

    public boolean canCollide(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos clampFloored(double p0, double p1, double p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos clampFloored(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos clampFloored(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d clamp(double p0, double p1, double p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d clamp(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return null;
    }

    public boolean contains(double p0, double p1) {
        return false;
    }

    public boolean contains(double p0, double p1, double p2) {
        return false;
    }

    public boolean contains(double p0, double p1, double p2, double p3) {
        return false;
    }

    public boolean contains(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean contains(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0) {
        return false;
    }

    public boolean contains(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p0) {
        return false;
    }

    public boolean contains(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return false;
    }

    public double getBoundEast() {
        return 0.0D;
    }

    public double getBoundNorth() {
        return 0.0D;
    }

    public double getBoundSouth() {
        return 0.0D;
    }

    public double getBoundWest() {
        return 0.0D;
    }

    public double getCenterX() {
        return 0.0D;
    }

    public double getCenterZ() {
        return 0.0D;
    }

    public double getDamagePerBlock() {
        return 0.0D;
    }

    public double getDistanceInsideBorder(double p0, double p1) {
        return 0.0D;
    }

    public double getDistanceInsideBorder(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return 0.0D;
    }

    public java.util.List getListeners() {
        return null;
    }

    public int getMaxRadius() {
        return 0;
    }

    public double getSafeZone() {
        return 0.0D;
    }

    public double getShrinkingSpeed() {
        return 0.0D;
    }

    public double getSizeLerpTarget() {
        return 0.0D;
    }

    public long getSizeLerpTime() {
        return 0L;
    }

    public double getSize() {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorderStage getStage() {
        return null;
    }

    public int getWarningBlocks() {
        return 0;
    }

    public int getWarningTime() {
        return 0;
    }

    public void interpolateSize(double p0, double p1, long p2) {
    }

    public void load(murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder.Properties p0) {
    }

    public void removeListener(murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorderListener p0) {
    }

    public void setCenter(double p0, double p1) {
    }

    public void setDamagePerBlock(double p0) {
    }

    public void setMaxRadius(int p0) {
    }

    public void setSafeZone(double p0) {
    }

    public void setSize(double p0) {
    }

    public void setWarningBlocks(int p0) {
    }

    public void setWarningTime(int p0) {
    }

    public void tick() {
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder.Properties write() {
        return null;
    }

    public static interface Area {
        public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape asVoxelShape();

        public murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder.Area getAreaInstance();

        public double getBoundEast();

        public double getBoundNorth();

        public double getBoundSouth();

        public double getBoundWest();

        public double getShrinkingSpeed();

        public double getSizeLerpTarget();

        public long getSizeLerpTime();

        public double getSize();

        public murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorderStage getStage();

        public void onCenterChanged();

        public void onMaxRadiusChanged();

    }

    public static class DistanceFromCamera {
        public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction direction;
        public double distance;

        public DistanceFromCamera(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p0, double p1) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction direction() {
            return null;
        }

        public double distance() {
            return 0.0D;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public java.lang.String toString() {
            return null;
        }

        public DistanceFromCamera() {
        }

    }

    public static class MovingArea extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder.Area {
        public murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder field_12743;
        public double newSize;
        public double oldSize;
        public double timeDuration;
        public long timeEnd;
        public long timeStart;

        public MovingArea(murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder p0, double p1, double p2, long p3) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape asVoxelShape() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder.Area getAreaInstance() {
            return null;
        }

        public double getBoundEast() {
            return 0.0D;
        }

        public double getBoundNorth() {
            return 0.0D;
        }

        public double getBoundSouth() {
            return 0.0D;
        }

        public double getBoundWest() {
            return 0.0D;
        }

        public double getShrinkingSpeed() {
            return 0.0D;
        }

        public double getSizeLerpTarget() {
            return 0.0D;
        }

        public long getSizeLerpTime() {
            return 0L;
        }

        public double getSize() {
            return 0.0D;
        }

        public murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorderStage getStage() {
            return null;
        }

        public void onCenterChanged() {
        }

        public void onMaxRadiusChanged() {
        }

        public MovingArea() {
        }

    }

    public static class Properties extends java.lang.Object {
        public double centerX;
        public double centerZ;
        public double damagePerBlock;
        public double safeZone;
        public double size;
        public double sizeLerpTarget;
        public long sizeLerpTime;
        public int warningBlocks;
        public int warningTime;

        public Properties(double p0, double p1, double p2, double p3, int p4, int p5, double p6, long p7, double p8) {
        }

        public Properties(murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder p0) {
        }

        public static murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder.Properties fromDynamic(com.mojang.serialization.DynamicLike p0, murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder.Properties p1) {
            return null;
        }

        public double getCenterX() {
            return 0.0D;
        }

        public double getCenterZ() {
            return 0.0D;
        }

        public double getDamagePerBlock() {
            return 0.0D;
        }

        public double getSafeZone() {
            return 0.0D;
        }

        public double getSizeLerpTarget() {
            return 0.0D;
        }

        public long getSizeLerpTime() {
            return 0L;
        }

        public double getSize() {
            return 0.0D;
        }

        public int getWarningBlocks() {
            return 0;
        }

        public int getWarningTime() {
            return 0;
        }

        public void writeNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
        }

        public Properties() {
        }

    }

    public static class StaticArea extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder.Area {
        public double boundEast;
        public double boundNorth;
        public double boundSouth;
        public double boundWest;
        public murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder field_12748;
        public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape shape;
        public double size;

        public StaticArea(murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder p0, double p1) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape asVoxelShape() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder.Area getAreaInstance() {
            return null;
        }

        public double getBoundEast() {
            return 0.0D;
        }

        public double getBoundNorth() {
            return 0.0D;
        }

        public double getBoundSouth() {
            return 0.0D;
        }

        public double getBoundWest() {
            return 0.0D;
        }

        public double getShrinkingSpeed() {
            return 0.0D;
        }

        public double getSizeLerpTarget() {
            return 0.0D;
        }

        public long getSizeLerpTime() {
            return 0L;
        }

        public double getSize() {
            return 0.0D;
        }

        public murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorderStage getStage() {
            return null;
        }

        public void onCenterChanged() {
        }

        public void onMaxRadiusChanged() {
        }

        public void recalculateBounds() {
        }

        public StaticArea() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
