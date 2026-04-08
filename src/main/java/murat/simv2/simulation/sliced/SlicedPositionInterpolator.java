package murat.simv2.simulation.sliced;

public final class SlicedPositionInterpolator {

    private final Object owner;

    public SlicedPositionInterpolator(Object owner) {
        this.owner = owner;
    }

    public Object getOwner() {
        return this.owner;
    }

    public boolean isInterpolating() {
        return false;
    }
}
