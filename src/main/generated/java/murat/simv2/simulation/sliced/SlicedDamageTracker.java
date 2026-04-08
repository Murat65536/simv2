package murat.simv2.simulation.sliced;

import net.minecraft.entity.damage.DamageSource;

public final class SlicedDamageTracker {

    private final Object owner;

    public SlicedDamageTracker(Object owner) {
        this.owner = owner;
    }

    public Object getOwner() {
        return this.owner;
    }

    public void onDamage(DamageSource source, float amount) {
    }
}
