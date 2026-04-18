package murat.simv2.simulation.mirror.net.minecraft.component.type;

// Generated mirror stub for simulation closure.
public class EquippableComponent {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static com.mojang.serialization.Codec CODEC;
    public static java.lang.Object PACKET_CODEC;
    public java.util.Optional allowedEntities;
    public java.util.Optional assetId;
    public java.util.Optional cameraOverlay;
    public boolean damageOnHurt;
    public boolean dispensable;
    public boolean equipOnInteract;
    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry equipSound;
    public murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot slot;
    public boolean swappable;

    public EquippableComponent(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p1, java.util.Optional p2, java.util.Optional p3, java.util.Optional p4, boolean p5, boolean p6, boolean p7, boolean p8) {
    }

    public java.util.Optional allowedEntities() {
        return null;
    }

    public boolean allows(murat.simv2.simulation.mirror.net.minecraft.entity.EntityType p0) {
        return false;
    }

    public java.util.Optional assetId() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.component.type.EquippableComponent.Builder builder(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
        return null;
    }

    public java.util.Optional cameraOverlay() {
        return null;
    }

    public boolean damageOnHurt() {
        return false;
    }

    public boolean dispensable() {
        return false;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public boolean equipOnInteract() {
        return false;
    }

    public java.lang.Object equipOnInteract(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry equipSound() {
        return null;
    }

    public java.lang.Object equip(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1) {
        return null;
    }

    public int hashCode() {
        return 0;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.component.type.EquippableComponent ofCarpet(java.lang.Object p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.component.type.EquippableComponent ofSaddle() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot slot() {
        return null;
    }

    public boolean swappable() {
        return false;
    }

    public java.lang.String toString() {
        return null;
    }

    public EquippableComponent() {
    }

    public static class Builder {
        public java.util.Optional allowedEntities;
        public java.util.Optional cameraOverlay;
        public boolean damageOnHurt;
        public boolean dispensable;
        public boolean equipOnInteract;
        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry equipSound;
        public java.util.Optional model;
        public murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot slot;
        public boolean swappable;

        public Builder(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.EquippableComponent.Builder allowedEntities(java.lang.Object p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.EquippableComponent.Builder allowedEntities(murat.simv2.simulation.mirror.net.minecraft.entity.EntityType[] p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.EquippableComponent build() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.EquippableComponent.Builder cameraOverlay(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.EquippableComponent.Builder damageOnHurt(boolean p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.EquippableComponent.Builder dispensable(boolean p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.EquippableComponent.Builder equipOnInteract(boolean p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.EquippableComponent.Builder equipSound(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.EquippableComponent.Builder model(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.component.type.EquippableComponent.Builder swappable(boolean p0) {
            return null;
        }

        public Builder() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
