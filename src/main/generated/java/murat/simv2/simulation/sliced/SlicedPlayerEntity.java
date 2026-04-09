package murat.simv2.simulation.sliced;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ProjectileDeflection;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stat;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import static net.minecraft.client.network.AbstractClientPlayerEntity.*;
import static net.minecraft.client.network.ClientPlayerEntity.*;
import static net.minecraft.entity.Entity.*;
import static net.minecraft.entity.LivingEntity.*;
import static net.minecraft.entity.player.PlayerEntity.*;

// Sliced from net.minecraft.entity.player.PlayerEntity
// Movement-relevant statements only (WALA backward slice + Spoon AST pruning)
// Generated — do not edit

public abstract class SlicedPlayerEntity extends SlicedLivingEntity {

  protected float damageTiltYaw;
  private final Map<RegistryEntry<StatusEffect>, StatusEffectInstance> activeStatusEffects =
      Maps.<RegistryEntry<StatusEffect>, StatusEffectInstance>newHashMap();
  protected int lastAttackedTicks;
  protected float riptideAttackDamage;
  private Vec3d velocity = Vec3d.ZERO;
  public double fallDistance;

  public void attack(Entity target) {
    if (target.isAttackable()) {
      if (!target.handleAttack((PlayerEntity) this.entityBridge)) {
        float f =
            (this.isUsingRiptide())
                ? this.riptideAttackDamage
                : ((float) (this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE)));
        ItemStack itemStack = this.getWeaponStack();
        DamageSource damageSource =
            ((DamageSource)
                (Optional.ofNullable(
                        itemStack.getItem().getDamageSource((PlayerEntity) this.entityBridge))
                    .orElse(
                        this.getDamageSources().playerAttack((PlayerEntity) this.entityBridge))));
        float g = this.getDamageAgainst(target, f, damageSource) - f;
        float h = this.getAttackCooldownProgress(0.5F);
        f *= 0.2F + ((h * h) * 0.8F);
        g *= h;
        if ((target.getType().isIn(EntityTypeTags.REDIRECTABLE_PROJECTILE)
                && (target instanceof ProjectileEntity projectileEntity))
            && projectileEntity.deflect(
                ProjectileDeflection.REDIRECTED,
                (PlayerEntity) this.entityBridge,
                (PlayerEntity) this.entityBridge,
                true)) {
        } else if ((f > 0.0F) || (g > 0.0F)) {
          boolean bl = h > 0.9F;
          boolean bl2 = false;
          boolean bl3 =
              (((((((bl && (this.fallDistance > 0.0)) && (!this.isOnGround()))
                                      && (!this.isClimbing()))
                                  && (!this.isTouchingWater()))
                              && (!this.hasStatusEffect(StatusEffects.BLINDNESS)))
                          && (!this.hasVehicle()))
                      && (target instanceof LivingEntity))
                  && (!this.isSprinting());
          float i = f + g;
          boolean bl4 = false;
          float j = 0.0F;
          Vec3d vec3d = target.getVelocity();
          boolean bl5 = target.sidedDamage(damageSource, i);
          if (bl5) {
            float k = this.getAttackKnockbackAgainst(target, damageSource) + (bl2 ? 1.0F : 0.0F);
            if (k > 0.0F) {
              this.setVelocity(this.getVelocity().multiply(0.6, 1.0, 0.6));
            }
            if ((target instanceof ServerPlayerEntity) && target.velocityModified) {
              target.setVelocity(vec3d);
            }
            Entity entity = target;
            boolean bl6 = false;
          }
        }
      }
    }
  }

  protected boolean canChangeIntoPose(EntityPose pose) {
    return this.getWorld()
        .isSpaceEmpty(
            (PlayerEntity) this.entityBridge,
            this.getDimensions(pose).getBoxAt(this.getPos()).contract(1.0E-7));
  }

  protected boolean clipAtLedge() {
    return this.isSneaking();
  }

  public float getAttackCooldownProgress(float baseTime) {
    return MathHelper.clamp(
        (this.lastAttackedTicks + baseTime) / this.getAttackCooldownProgressPerTick(), 0.0F, 1.0F);
  }

  public float getAttackCooldownProgressPerTick() {
    return ((float) ((1.0 / this.getAttributeValue(EntityAttributes.ATTACK_SPEED)) * 20.0));
  }

  protected float getDamageAgainst(Entity target, float baseDamage, DamageSource damageSource) {
    return baseDamage;
  }

  public float getDamageTiltYaw() {
    return this.damageTiltYaw;
  }

  private boolean isSpaceAroundPlayerEmpty(double offsetX, double offsetZ, double d) {
    Box box = this.getBoundingBox();
    return this.getWorld()
        .isSpaceEmpty(
            (PlayerEntity) this.entityBridge,
            new Box(
                (box.minX + 1.0E-7) + offsetX,
                (box.minY - d) - 1.0E-7,
                (box.minZ + 1.0E-7) + offsetZ,
                (box.maxX - 1.0E-7) + offsetX,
                box.minY,
                (box.maxZ - 1.0E-7) + offsetZ));
  }

  private boolean method_30263(float f) {
    return this.isOnGround()
        || ((this.fallDistance < f)
            && (!this.isSpaceAroundPlayerEmpty(0.0, 0.0, f - this.fallDistance)));
  }

  public void wakeUp(boolean skipSleepTimer, boolean updateSleepingPlayers) {
    this.wakeUp();
  }

  public double getAttributeValue(RegistryEntry<EntityAttribute> attribute) {
    return this.getAttributes().getValue(attribute);
  }

  public boolean hasStatusEffect(RegistryEntry<StatusEffect> effect) {
    return this.activeStatusEffects.containsKey(effect);
  }

  public void setVelocity(Vec3d velocity) {
    this.velocity = velocity;
  }
}
