package murat.simv2.simulation.mirror.net.minecraft.component.type;

// Generated mirror stub for simulation closure.
public class FoodComponent {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.component.type.FoodComponent> CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.component.type.FoodComponent> PACKET_CODEC;
    public boolean canAlwaysEat;
    public int nutrition;
    public float saturation;

    public FoodComponent(int p0, float p1, boolean p2) {
    }

    public boolean canAlwaysEat() {
        return false;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public int nutrition() {
        return 0;
    }

    public void onConsume(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2, murat.simv2.simulation.mirror.net.minecraft.component.type.ConsumableComponent p3) {
    }

    public float saturation() {
        return 0.0F;
    }

    public java.lang.String toString() {
        return null;
    }

    public FoodComponent() {
    }

    public static class Builder extends java.lang.Object {
        public boolean canAlwaysEat;
        public int nutrition;
        public float saturationModifier;

        public Builder() {
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.FoodComponent.Builder alwaysEdible() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.FoodComponent build() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.FoodComponent.Builder nutrition(int p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.FoodComponent.Builder saturationModifier(float p0) {
            return null;
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
