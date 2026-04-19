package murat.simv2.simulation.mirror.net.minecraft.world;

// Generated mirror stub for simulation closure.
public interface HeightLimitView {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public int countVerticalSections();

    public static murat.simv2.simulation.mirror.net.minecraft.world.HeightLimitView create(int p0, int p1) {
        return null;
    }

    public int getBottomSectionCoord();

    public int getBottomY();

    public int getHeight();

    public int getSectionIndex(int p0);

    public int getTopSectionCoord();

    public int getTopYInclusive();

    public boolean isInHeightLimit(int p0);

    public boolean isOutOfHeightLimit(int p0);

    public boolean isOutOfHeightLimit(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0);

    public int sectionCoordToIndex(int p0);

    public int sectionIndexToCoord(int p0);

    // END GENERATED MIRROR NESTED STUBS
}
