package murat.simv2.simulation.mirror.net.minecraft.entity.attribute;

// Generated mirror stub for simulation closure.
public class AttributeContainer extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute>, murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeInstance> custom;
    public murat.simv2.simulation.mirror.net.minecraft.entity.attribute.DefaultAttributeContainer fallback;
    public java.util.Set<murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeInstance> pendingUpdate;
    public java.util.Set<murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeInstance> tracked;

    public AttributeContainer(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.DefaultAttributeContainer p0) {
    }

    public void addPersistentModifiersFrom(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.AttributeContainer p0) {
    }

    public void addTemporaryModifiers(com.google.common.collect.Multimap p0) {
    }

    public java.util.Collection getAttributesToSend() {
        return null;
    }

    public double getBaseValue(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeInstance getCustomInstance(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return null;
    }

    public double getModifierValue(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p1) {
        return 0.0D;
    }

    public java.util.Set getPendingUpdate() {
        return null;
    }

    public java.util.Set getTracked() {
        return null;
    }

    public double getValue(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return 0.0D;
    }

    public boolean hasAttribute(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return false;
    }

    public boolean hasModifierForAttribute(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.util.Identifier p1) {
        return false;
    }

    public void readNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtList p0) {
    }

    public void removeModifiers(com.google.common.collect.Multimap p0) {
    }

    public boolean resetToBaseValue(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return false;
    }

    public void setBaseFrom(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.AttributeContainer p0) {
    }

    public void setFrom(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.AttributeContainer p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtList toNbt() {
        return null;
    }

    public void updateTrackedStatus(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeInstance p0) {
    }

    public AttributeContainer() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
