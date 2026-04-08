package murat.simv2.simulation.sliced;

import net.minecraft.world.World;

import java.util.UUID;

public final class SlicedLazyEntityReference<T> {

    private final UUID uuid;
    private final T value;

    public SlicedLazyEntityReference(T value) {
        this.value = value;
        this.uuid = null;
    }

    public SlicedLazyEntityReference(UUID uuid) {
        this.value = null;
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public <R> R resolve(World world, Class<R> type) {
        if (type.isInstance(this.value)) {
            return type.cast(this.value);
        }
        return null;
    }
}
