package murat.simv2.simulation.mirror.net.minecraft.entity.attribute;

// Generated mirror stub for simulation closure.
public class EntityAttributeInstance extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.lang.String BASE_NBT_KEY;
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute>> ENTRY_CODEC;
    public static java.lang.String ID_NBT_KEY;
    public static java.lang.String MODIFIERS_NBT_KEY;
    public double baseValue;
    public boolean dirty;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier> idToModifiers;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier.Operation, java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier>> operationToModifiers;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.util.Identifier, murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier> persistentModifiers;
    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute> type;
    public java.util.function.Consumer<murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeInstance> updateCallback;
    public double value;

    public EntityAttributeInstance(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, java.util.function.Consumer p1) {
    }

    public void addModifier(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier p0) {
    }

    public void addPersistentModifiers(java.util.Collection p0) {
    }

    public void addPersistentModifier(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier p0) {
    }

    public void addTemporaryModifier(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier p0) {
    }

    public void clearModifiers() {
    }

    public double computeValue() {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry getAttribute() {
        return null;
    }

    public double getBaseValue() {
        return 0.0D;
    }

    public java.util.Collection getModifiersByOperation(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier.Operation p0) {
        return null;
    }

    public java.util.Set getModifiers() {
        return null;
    }

    public java.util.Map getModifiers(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier.Operation p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier getModifier(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return null;
    }

    public java.util.Set getPersistentModifiers() {
        return null;
    }

    public double getValue() {
        return 0.0D;
    }

    public boolean hasModifier(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return false;
    }

    public void onUpdate() {
    }

    public void overwritePersistentModifier(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier p0) {
    }

    public void readNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    public void removeModifier(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier p0) {
    }

    public boolean removeModifier(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
        return false;
    }

    public void setBaseValue(double p0) {
    }

    public void setFrom(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeInstance p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound toNbt() {
        return null;
    }

    public void updateModifier(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier p0) {
    }

    public EntityAttributeInstance() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
