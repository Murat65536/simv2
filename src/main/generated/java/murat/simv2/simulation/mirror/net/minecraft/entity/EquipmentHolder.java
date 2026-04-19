package murat.simv2.simulation.mirror.net.minecraft.entity;

// Generated mirror stub for simulation closure.
public interface EquipmentHolder {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public void equipStack(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1);

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getEquippedStack(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0);

    public murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot getSlotForStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, java.util.List p1);

    public void setEquipmentDropChance(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0, float p1);

    public void setEquipmentFromTable(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentTable p0, murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext p1);

    public void setEquipmentFromTable(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext p1, java.util.Map p2);

    public void setEquipmentFromTable(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext p1, long p2, java.util.Map p3);

    // END GENERATED MIRROR NESTED STUBS
}
