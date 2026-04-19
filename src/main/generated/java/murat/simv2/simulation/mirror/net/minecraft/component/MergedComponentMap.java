package murat.simv2.simulation.mirror.net.minecraft.component;

// Generated mirror stub for simulation closure.
public class MergedComponentMap extends java.lang.Object implements murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap baseComponents;
    public it.unimi.dsi.fastutil.objects.Reference2ObjectMap<murat.simv2.simulation.mirror.net.minecraft.component.ComponentType<?>, java.util.Optional<?>> changedComponents;
    public boolean copyOnWrite;

    public MergedComponentMap(murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap p0) {
    }

    public MergedComponentMap(murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap p0, it.unimi.dsi.fastutil.objects.Reference2ObjectMap p1, boolean p2) {
    }

    public void applyChanges(murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges p0) {
    }

    public void applyChange(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, java.util.Optional p1) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap.Builder builder() {
        return null;
    }

    public void clearChanges() {
    }

    public boolean contains(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.MergedComponentMap copy() {
        return null;
    }

    public static com.mojang.serialization.Codec createCodecFromValueMap(com.mojang.serialization.Codec p0) {
        return null;
    }

    public static com.mojang.serialization.Codec createCodec(com.mojang.serialization.Codec p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.component.MergedComponentMap create(murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap p0, murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges p1) {
        return null;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap filtered(java.util.function.Predicate p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges getChanges() {
        return null;
    }

    public java.lang.Object getOrDefault(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, java.lang.Object p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.Component getTyped(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0) {
        return null;
    }

    public java.util.Set getTypes() {
        return null;
    }

    public java.lang.Object get(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0) {
        return null;
    }

    public boolean hasChanged(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0) {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap immutableCopy() {
        return null;
    }

    public boolean isEmpty() {
        return false;
    }

    public java.util.Iterator iterator() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap of(murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap p0, murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap p1) {
        return null;
    }

    public void onWrite() {
    }

    public java.lang.Object remove(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0) {
        return null;
    }

    public void setAll(murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap p0) {
    }

    public void setChanges(murat.simv2.simulation.mirror.net.minecraft.component.ComponentChanges p0) {
    }

    public java.lang.Object set(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, java.lang.Object p1) {
        return null;
    }

    public static boolean shouldReuseChangesMap(murat.simv2.simulation.mirror.net.minecraft.component.ComponentMap p0, it.unimi.dsi.fastutil.objects.Reference2ObjectMap p1) {
        return false;
    }

    public int size() {
        return 0;
    }

    public java.util.stream.Stream stream() {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public MergedComponentMap() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
