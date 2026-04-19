package murat.simv2.simulation.mirror.net.minecraft.entity.damage;

// Generated mirror stub for simulation closure.
public class DamageTracker extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static int ATTACK_DAMAGE_COOLDOWN;
    public static int DAMAGE_COOLDOWN;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Style INTENTIONAL_GAME_DESIGN_ISSUE_LINK_STYLE;
    public int ageOnLastAttacked;
    public int ageOnLastDamage;
    public int ageOnLastUpdate;
    public murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity entity;
    public boolean hasDamage;
    public java.util.List<java.lang.Object> recentDamage;
    public boolean recentlyAttacked;

    public DamageTracker(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getAttackedFallDeathMessage(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.text.Text p1, java.lang.String p2, java.lang.String p3) {
        return null;
    }

    public java.lang.Object getBiggestFall() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getDeathMessage() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.Text getDisplayName(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getFallDeathMessage(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
        return null;
    }

    public int getTimeSinceLastAttack() {
        return 0;
    }

    public static boolean isAttackerLiving(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0) {
        return false;
    }

    public void onDamage(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1) {
    }

    public void update() {
    }

    public DamageTracker() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
