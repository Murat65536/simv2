package murat.simv2.simulation.mirror.net.minecraft.entity;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.doubles.DoubleDoubleImmutablePair;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import murat.simv2.simulation.mirror.net.minecraft.block.BlockState;
import murat.simv2.simulation.mirror.net.minecraft.block.Blocks;
import murat.simv2.simulation.mirror.net.minecraft.block.LadderBlock;
import murat.simv2.simulation.mirror.net.minecraft.block.PowderSnowBlock;
import murat.simv2.simulation.mirror.net.minecraft.block.TrapdoorBlock;
import murat.simv2.simulation.mirror.net.minecraft.component.DataComponentTypes;
import murat.simv2.simulation.mirror.net.minecraft.component.type.AttributeModifierSlot;
import murat.simv2.simulation.mirror.net.minecraft.component.type.BlocksAttacksComponent;
import murat.simv2.simulation.mirror.net.minecraft.component.type.DeathProtectionComponent;
import murat.simv2.simulation.mirror.net.minecraft.component.type.EquippableComponent;
import murat.simv2.simulation.mirror.net.minecraft.enchantment.EnchantmentHelper;
import murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute;
import murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributes;
import murat.simv2.simulation.mirror.net.minecraft.entity.boss.WitherEntity;
import murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource;
import murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageType;
import murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageTypes;
import murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData;
import murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffect;
import murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance;
import murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffects;
import murat.simv2.simulation.mirror.net.minecraft.entity.passive.WolfEntity;
import murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity;
import murat.simv2.simulation.mirror.net.minecraft.entity.projectile.PersistentProjectileEntity;
import murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity;
import murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid;
import murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState;
import murat.simv2.simulation.mirror.net.minecraft.item.ItemStack;
import murat.simv2.simulation.mirror.net.minecraft.item.Items;
import murat.simv2.simulation.mirror.net.minecraft.loot.LootTable;
import murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContextParameters;
import murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContextTypes;
import murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext;
import murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey;
import murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry;
import murat.simv2.simulation.mirror.net.minecraft.registry.tag.BlockTags;
import murat.simv2.simulation.mirror.net.minecraft.registry.tag.DamageTypeTags;
import murat.simv2.simulation.mirror.net.minecraft.registry.tag.EntityTypeTags;
import murat.simv2.simulation.mirror.net.minecraft.registry.tag.FluidTags;
import murat.simv2.simulation.mirror.net.minecraft.registry.tag.ItemTags;
import murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey;
import murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld;
import murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory;
import murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent;
import murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvents;
import murat.simv2.simulation.mirror.net.minecraft.util.Hand;
import murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Box;
import murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d;
import murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler;
import murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profilers;
import murat.simv2.simulation.mirror.net.minecraft.world.GameRules;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// Mirrored from net.minecraft.entity.LivingEntity
// Movement-relevant statements only (WALA backward slice + Spoon AST pruning)
// Generated - do not edit
public abstract class LivingEntity extends Entity {
    public static final double GRAVITY = 0.08;

    public static final murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<Byte> LIVING_FLAGS = murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity.LIVING_FLAGS;

    public static final murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<Float> HEALTH = murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity.HEALTH;

    public static final murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions SLEEPING_DIMENSIONS = murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions.fixed(0.2F, 0.2F).withEyeHeight(0.2F);

    public final Map<murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffect>, murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance> activeStatusEffects = Maps.<murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffect>, murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance>newHashMap();

    public boolean noDrag = false;

    public float headYaw;

    @Nullable
    public murat.simv2.simulation.mirror.net.minecraft.entity.LazyEntityReference<murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity> attackingPlayer;

    public int playerHitTimer;

    public boolean dead;

    public float lastDamageTaken;

    public boolean jumping;

    public float sidewaysSpeed;

    public float upwardSpeed;

    public float forwardSpeed;

    public murat.simv2.simulation.mirror.net.minecraft.entity.PositionInterpolator interpolator = new murat.simv2.simulation.mirror.net.minecraft.entity.PositionInterpolator(this);

    public double serverHeadYaw;

    public int headTrackingIncrements;

    @Nullable
    public murat.simv2.simulation.mirror.net.minecraft.entity.LazyEntityReference<murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity> attackerReference;

    public int jumpingCooldown;

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack activeItemStack = murat.simv2.simulation.mirror.net.minecraft.item.ItemStack.EMPTY;

    public int itemUseTimeLeft;

    public int riptideTicks;

    public boolean experienceDroppingDisabled;

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityEquipment equipment;

    public double lastX;

    public double lastY;

    public double lastZ;

    public int timeUntilRegen;

    protected void fall(double heightDifference, boolean onGround, murat.simv2.simulation.mirror.net.minecraft.block.BlockState state, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos landedPosition) {
        if (!this.isTouchingWater()) {
            this.checkWaterState();
        }
        if (((this.getWorld() instanceof murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld serverWorld) && onGround) && (this.fallDistance > 0.0)) {
            double d = Math.max(0, murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.floor(this.getUnsafeFallDistance(this.fallDistance)));
            if ((d > 0.0) && (!state.isAir())) {
                double e = this.getX();
                double f = this.getY();
                double g = this.getZ();
                murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos = this.getBlockPos();
                if ((landedPosition.getX() != blockPos.getX()) || (landedPosition.getZ() != blockPos.getZ())) {
                    double h = (e - landedPosition.getX()) - 0.5;
                    double i = (g - landedPosition.getZ()) - 0.5;
                    double j = Math.max(Math.abs(h), Math.abs(i));
                    e = (landedPosition.getX() + 0.5) + ((h / j) * 0.5);
                    g = (landedPosition.getZ() + 0.5) + ((i / j) * 0.5);
                }
                double h = Math.min(0.2F + (d / 15.0), 2.5);
                int k = ((int) (150.0 * h));
            }
        }
        super.fall(heightDifference, onGround, state, landedPosition);
    }

    public static final int GLIDING_FLAG_INDEX = 7;

    public boolean wasInPowderSnow;

    protected float getVelocityMultiplier() {
        return murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.lerp(((float) (this.getAttributeValue(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributes.MOVEMENT_EFFICIENCY))), super.getVelocityMultiplier(), 1.0F);
    }

    protected void addPowderSnowSlowIfNeeded() {
        if (!this.getLandingBlockState().isAir()) {
            int i = this.getFrozenTicks();
        }
    }

    public boolean isBaby() {
        return false;
    }

    public boolean shouldDropExperience() {
        return !this.isBaby();
    }

    protected boolean shouldDropLoot() {
        return !this.isBaby();
    }

    public int getExperienceToDrop(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld world, @Nullable
    murat.simv2.simulation.mirror.net.minecraft.entity.Entity attacker) {
        return murat.simv2.simulation.mirror.net.minecraft.enchantment.EnchantmentHelper.getMobExperience(world, attacker, this, this.getExperienceToDrop(world));
    }

    @Nullable
    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getAttackingPlayer() {
        return ((murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity) (murat.simv2.simulation.mirror.net.minecraft.entity.LazyEntityReference.resolve(this.attackingPlayer, this.getWorld(), murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity.class)));
    }

    public void setAttacking(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity attackingPlayer, int playerHitTimer) {
        this.setAttacking(new murat.simv2.simulation.mirror.net.minecraft.entity.LazyEntityReference<>(attackingPlayer), playerHitTimer);
    }

    public void setAttacking(UUID attackingPlayer, int playerHitTimer) {
        this.setAttacking(new murat.simv2.simulation.mirror.net.minecraft.entity.LazyEntityReference<>(attackingPlayer), playerHitTimer);
    }

    private void setAttacking(murat.simv2.simulation.mirror.net.minecraft.entity.LazyEntityReference<murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity> attackingPlayer, int playerHitTimer) {
        this.attackingPlayer = attackingPlayer;
        this.playerHitTimer = playerHitTimer;
    }

    public void setAttacker(@Nullable
    murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity attacker) {
        this.attackerReference = (attacker != null) ? new murat.simv2.simulation.mirror.net.minecraft.entity.LazyEntityReference<>(attacker) : null;
    }

    public boolean hasNoDrag() {
        return this.noDrag;
    }

    public void onEquipStack(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot slot, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack oldStack, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack newStack) {
        if ((!this.getWorld().isClient()) && (!this.isSpectator())) {
            if ((!murat.simv2.simulation.mirror.net.minecraft.item.ItemStack.areItemsAndComponentsEqual(oldStack, newStack)) && (!this.firstUpdate)) {
                murat.simv2.simulation.mirror.net.minecraft.component.type.EquippableComponent equippableComponent = newStack.get(murat.simv2.simulation.mirror.net.minecraft.component.DataComponentTypes.EQUIPPABLE);
                if (((!this.isSilent()) && (equippableComponent != null)) && (slot == equippableComponent.slot())) {
                    this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), this.getEquipSound(slot, newStack, equippableComponent), this.getSoundCategory(), 1.0F, 1.0F, this.random.nextLong());
                }
            }
        }
    }

    public boolean hasStatusEffect(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffect> effect) {
        return this.activeStatusEffects.containsKey(effect);
    }

    @Nullable
    public murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance getStatusEffect(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffect> effect) {
        return ((murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance) (this.activeStatusEffects.get(effect)));
    }

    public float getHealth() {
        return this.dataTracker.get(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity.HEALTH);
    }

    public void setHealth(float health) {
        this.dataTracker.set(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity.HEALTH, murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.clamp(health, 0.0F, this.getMaxHealth()));
    }

    public boolean isDead() {
        return this.getHealth() <= 0.0F;
    }

    public void damage(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld world, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource source, float amount) {
        if (this.isInvulnerableTo(world, source)) {
        } else if (this.isDead()) {
        } else if (source.isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.DamageTypeTags.IS_FIRE) && this.hasStatusEffect(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffects.FIRE_RESISTANCE)) {
        } else {
            if (this.isSleeping()) {
                this.wakeUp();
            }
            float g = this.getDamageBlockedAmount(world, source, amount);
            amount -= g;
            boolean bl = g > 0.0F;
            if (source.isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.DamageTypeTags.IS_FREEZING) && this.getType().isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES)) {
                amount *= 5.0F;
            }
            if (source.isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.DamageTypeTags.DAMAGES_HELMET) && (!this.getEquippedStack(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot.HEAD).isEmpty())) {
                this.damageHelmet(source, amount);
                amount *= 0.75F;
            }
            boolean bl2 = true;
            if ((this.timeUntilRegen > 10.0F) && (!source.isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.DamageTypeTags.BYPASSES_COOLDOWN))) {
                this.applyDamage(world, source, amount - this.lastDamageTaken);
                this.lastDamageTaken = amount;
            } else {
                this.lastDamageTaken = amount;
                this.timeUntilRegen = 20;
                this.applyDamage(world, source, amount);
            }
            this.becomeAngry(source);
            this.setAttackingPlayer(source);
            if (bl2) {
                murat.simv2.simulation.mirror.net.minecraft.component.type.BlocksAttacksComponent blocksAttacksComponent = (murat.simv2.simulation.mirror.net.minecraft.component.type.BlocksAttacksComponent) this.getActiveItem().get(murat.simv2.simulation.mirror.net.minecraft.component.DataComponentTypes.BLOCKS_ATTACKS);
                if (!source.isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.DamageTypeTags.NO_KNOCKBACK)) {
                    double d = 0.0;
                    double e = 0.0;
                    if (source.getSource() instanceof murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity projectileEntity) {
                        DoubleDoubleImmutablePair doubleDoubleImmutablePair = projectileEntity.getKnockback(this, source);
                        d = -doubleDoubleImmutablePair.leftDouble();
                        e = -doubleDoubleImmutablePair.rightDouble();
                    } else if (source.getPosition() != null) {
                        d = source.getPosition().getX() - this.getX();
                        e = source.getPosition().getZ() - this.getZ();
                    }
                    this.takeKnockback(0.4F, d, e);
                }
            }
            if (this.isDead()) {
                if (!this.tryUseDeathProtector(source)) {
                    if (bl2) {
                        this.playSound(this.getDeathSound());
                        this.playThornsSound(source);
                    }
                    this.onDeath(source);
                }
            } else if (bl2) {
                this.playHurtSound(source);
                this.playThornsSound(source);
            }
            boolean bl3 = (!bl) || (amount > 0.0F);
        }
    }

    public float getDamageBlockedAmount(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld world, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource source, float amount) {
        if (amount <= 0.0F) {
            return 0.0F;
        } else {
            murat.simv2.simulation.mirror.net.minecraft.item.ItemStack itemStack = this.getBlockingItem();
            if (itemStack == null) {
                return 0.0F;
            } else {
                murat.simv2.simulation.mirror.net.minecraft.component.type.BlocksAttacksComponent blocksAttacksComponent = (murat.simv2.simulation.mirror.net.minecraft.component.type.BlocksAttacksComponent) itemStack.get(murat.simv2.simulation.mirror.net.minecraft.component.DataComponentTypes.BLOCKS_ATTACKS);
                if ((blocksAttacksComponent != null) && (!((Boolean) (blocksAttacksComponent.bypassedBy().map(tag -> source.isIn((murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey) tag)).orElse(false))))) {
                    if ((source.getSource() instanceof murat.simv2.simulation.mirror.net.minecraft.entity.projectile.PersistentProjectileEntity persistentProjectileEntity) && (persistentProjectileEntity.getPierceLevel() > 0)) {
                        return 0.0F;
                    } else {
                        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = source.getPosition();
                        double d = 0.0;
                        if (vec3d != null) {
                            murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d2 = this.getRotationVector(0.0F, this.getHeadYaw());
                            murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d3 = vec3d.subtract(this.getPos());
                            vec3d3 = new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d(vec3d3.x, 0.0, vec3d3.z).normalize();
                            d = Math.acos(vec3d3.dotProduct(vec3d2));
                        }
                        float f = blocksAttacksComponent.getDamageReductionAmount(source, amount, d);
                        return f;
                    }
                } else {
                    return 0.0F;
                }
            }
        }
    }

    private void playThornsSound(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource damageSource) {
        if (damageSource.isOf(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageTypes.THORNS)) {
            murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory soundCategory = (this instanceof murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity) ? murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory.PLAYERS : murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory.HOSTILE;
            this.getWorld().playSound(null, this.getPos().x, this.getPos().y, this.getPos().z, murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvents.ENCHANT_THORNS_HIT, soundCategory);
        }
    }

    protected void becomeAngry(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource damageSource) {
        if (((damageSource.getAttacker() instanceof murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity livingEntity) && (!damageSource.isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.DamageTypeTags.NO_ANGER))) && ((!damageSource.isOf(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageTypes.WIND_CHARGE)) || (!this.getType().isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.EntityTypeTags.NO_ANGER_FROM_WIND_CHARGE)))) {
            this.setAttacker(livingEntity);
        }
    }

    @Nullable
    protected murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity setAttackingPlayer(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource damageSource) {
        murat.simv2.simulation.mirror.net.minecraft.entity.Entity entity = damageSource.getAttacker();
        if (entity instanceof murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity playerEntity) {
            this.setAttacking(playerEntity, 100);
        } else if ((entity instanceof murat.simv2.simulation.mirror.net.minecraft.entity.passive.WolfEntity wolfEntity) && wolfEntity.isTamed()) {
            if (wolfEntity.getOwnerReference() != null) {
                this.setAttacking(wolfEntity.getOwnerReference().getUuid(), 100);
            } else {
                this.attackingPlayer = null;
                this.playerHitTimer = 0;
            }
        }
        return ((murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity) (murat.simv2.simulation.mirror.net.minecraft.entity.LazyEntityReference.resolve(this.attackingPlayer, this.getWorld(), murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity.class)));
    
        return null;}

    private boolean tryUseDeathProtector(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource source) {
        if (source.isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return false;
        } else {
            murat.simv2.simulation.mirror.net.minecraft.item.ItemStack itemStack = null;
            murat.simv2.simulation.mirror.net.minecraft.component.type.DeathProtectionComponent deathProtectionComponent = null;
            for (murat.simv2.simulation.mirror.net.minecraft.util.Hand hand : murat.simv2.simulation.mirror.net.minecraft.util.Hand.values()) {
                murat.simv2.simulation.mirror.net.minecraft.item.ItemStack itemStack2 = this.getStackInHand(hand);
                deathProtectionComponent = (murat.simv2.simulation.mirror.net.minecraft.component.type.DeathProtectionComponent) itemStack2.get(murat.simv2.simulation.mirror.net.minecraft.component.DataComponentTypes.DEATH_PROTECTION);
            }
            if (itemStack != null) {
                this.setHealth(1.0F);
            }
            return deathProtectionComponent != null;
        }
    }

    protected void playHurtSound(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource damageSource) {
        this.playSound(this.getHurtSound(damageSource));
    }

    public void playSound(@Nullable
    murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent sound) {
        if (sound != null) {
            this.playSound(sound, this.getSoundVolume(), this.getSoundPitch());
        }
    }

    public void onDeath(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource damageSource) {
        if ((!this.isRemoved()) && (!this.dead)) {
            murat.simv2.simulation.mirror.net.minecraft.entity.Entity entity = damageSource.getAttacker();
            murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity livingEntity = this.getPrimeAdversary();
            if (this.isSleeping()) {
                this.wakeUp();
            }
            this.dead = true;
            if (this.getWorld() instanceof murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld serverWorld) {
                if ((entity == null) || entity.onKilledOther(serverWorld, this)) {
                    this.drop(serverWorld, damageSource);
                    this.onKilledBy(livingEntity);
                }
            }
        }
    }

    protected void onKilledBy(@Nullable
    murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity adversary) {
        if (this.getWorld() instanceof murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld serverWorld) {
            boolean var6 = false;
            if (adversary instanceof murat.simv2.simulation.mirror.net.minecraft.entity.boss.WitherEntity) {
                if (serverWorld.getGameRules().getBoolean(murat.simv2.simulation.mirror.net.minecraft.world.GameRules.DO_MOB_GRIEFING)) {
                    murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos = this.getBlockPos();
                    murat.simv2.simulation.mirror.net.minecraft.block.BlockState blockState = murat.simv2.simulation.mirror.net.minecraft.block.Blocks.WITHER_ROSE.getDefaultState();
                }
                if (!var6) {
                    murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity itemEntity = new murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity(this.getWorld(), this.getX(), this.getY(), this.getZ(), new murat.simv2.simulation.mirror.net.minecraft.item.ItemStack(murat.simv2.simulation.mirror.net.minecraft.item.Items.WITHER_ROSE));
                }
            }
        }
    }

    protected void drop(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld world, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource damageSource) {
        boolean bl = this.playerHitTimer > 0;
        if (this.shouldDropLoot() && world.getGameRules().getBoolean(murat.simv2.simulation.mirror.net.minecraft.world.GameRules.DO_MOB_LOOT)) {
            this.dropLoot(world, damageSource, bl);
        }
        this.dropExperience(world, damageSource.getAttacker());
    }

    protected void dropExperience(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld world, @Nullable
    murat.simv2.simulation.mirror.net.minecraft.entity.Entity attacker) {
        if ((!this.isExperienceDroppingDisabled()) && (this.shouldAlwaysDropExperience() || (((this.playerHitTimer > 0) && this.shouldDropExperience()) && world.getGameRules().getBoolean(murat.simv2.simulation.mirror.net.minecraft.world.GameRules.DO_MOB_LOOT)))) {
            murat.simv2.simulation.mirror.net.minecraft.entity.ExperienceOrbEntity.spawn(world, this.getPos(), this.getExperienceToDrop(world, attacker));
        }
    }

    protected float getAttackKnockbackAgainst(murat.simv2.simulation.mirror.net.minecraft.entity.Entity target, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource damageSource) {
        float f = ((float) (this.getAttributeValue(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributes.ATTACK_KNOCKBACK)));
        return this.getWorld() instanceof murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld serverWorld ? murat.simv2.simulation.mirror.net.minecraft.enchantment.EnchantmentHelper.modifyKnockback(serverWorld, this.getWeaponStack(), target, damageSource, f) : f;
    }

    protected void dropLoot(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld world, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource damageSource, boolean causedByPlayer) {
        Optional<murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<murat.simv2.simulation.mirror.net.minecraft.loot.LootTable>> optional = this.getLootTableKey();
        if (!optional.isEmpty()) {
            murat.simv2.simulation.mirror.net.minecraft.loot.LootTable lootTable = world.getServer().getReloadableRegistries().getLootTable(((murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<murat.simv2.simulation.mirror.net.minecraft.loot.LootTable>) (optional.get())));
            murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext.Builder builder = new murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext.Builder(world).add(murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContextParameters.THIS_ENTITY, this).add(murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContextParameters.ORIGIN, this.getPos()).add(murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContextParameters.DAMAGE_SOURCE, damageSource).addOptional(murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContextParameters.ATTACKING_ENTITY, damageSource.getAttacker()).addOptional(murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContextParameters.DIRECT_ATTACKING_ENTITY, damageSource.getSource());
            murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity playerEntity = this.getAttackingPlayer();
            murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext lootWorldContext = builder.build(murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContextTypes.ENTITY);
        }
    }

    public void takeKnockback(double strength, double x, double z) {
        strength *= 1.0 - this.getAttributeValue(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributes.KNOCKBACK_RESISTANCE);
        if (!(strength <= 0.0)) {
            murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = this.getVelocity();
            while (((x * x) + (z * z)) < 1.0E-5F) {
                x = (Math.random() - Math.random()) * 0.01;
                z = (Math.random() - Math.random()) * 0.01;
            } 
            murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d2 = new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d(x, 0.0, z).normalize().multiply(strength);
            this.setVelocity((vec3d.x / 2.0) - vec3d2.x, this.isOnGround() ? Math.min(0.4, (vec3d.y / 2.0) + strength) : vec3d.y, (vec3d.z / 2.0) - vec3d2.z);
        }
    }

    private murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent getFallSound(int distance) {
        return distance > 4 ? this.getFallSounds().big() : this.getFallSounds().small();
    }

    public boolean isExperienceDroppingDisabled() {
        return this.experienceDroppingDisabled;
    }

    public boolean isClimbing() {
        if (this.isSpectator()) {
            return false;
        } else {
            murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos = this.getBlockPos();
            murat.simv2.simulation.mirror.net.minecraft.block.BlockState blockState = this.getBlockStateAtPos();
            if (blockState.isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.BlockTags.CLIMBABLE)) {
                return true;
            } else if ((blockState.getBlock() instanceof murat.simv2.simulation.mirror.net.minecraft.block.TrapdoorBlock) && this.canEnterTrapdoor(blockPos, blockState)) {
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean canEnterTrapdoor(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos pos, murat.simv2.simulation.mirror.net.minecraft.block.BlockState state) {
        if (!((Boolean) (state.get(murat.simv2.simulation.mirror.net.minecraft.block.TrapdoorBlock.OPEN)))) {
            return false;
        } else {
            murat.simv2.simulation.mirror.net.minecraft.block.BlockState blockState = this.getWorld().getBlockState(pos.down());
            return blockState.isOf(murat.simv2.simulation.mirror.net.minecraft.block.Blocks.LADDER) && (blockState.get(murat.simv2.simulation.mirror.net.minecraft.block.LadderBlock.FACING) == state.get(murat.simv2.simulation.mirror.net.minecraft.block.TrapdoorBlock.FACING));
        }
    }

    public boolean isAlive() {
        return (!this.isRemoved()) && (this.getHealth() > 0.0F);
    }

    private double getUnsafeFallDistance(double fallDistance) {
        return (fallDistance + 1.0E-6) - this.getAttributeValue(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributes.SAFE_FALL_DISTANCE);
    }

    public int getArmor() {
        return murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.floor(this.getAttributeValue(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributes.ARMOR));
    }

    protected void damageEquipment(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource source, float amount, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot... slots) {
        if (!(amount <= 0.0F)) {
            int i = ((int) (Math.max(1.0F, amount / 4.0F)));
        }
    }

    protected float applyArmorToDamage(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource source, float amount) {
        if (!source.isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.DamageTypeTags.BYPASSES_ARMOR)) {
            this.damageArmor(source, amount);
            amount = murat.simv2.simulation.mirror.net.minecraft.entity.DamageUtil.getDamageLeft(this, amount, source, this.getArmor(), ((float) (this.getAttributeValue(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributes.ARMOR_TOUGHNESS))));
        }
        return amount;
    }

    protected float modifyAppliedDamage(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource source, float amount) {
        if (source.isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.DamageTypeTags.BYPASSES_EFFECTS)) {
            return amount;
        } else {
            if (this.hasStatusEffect(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffects.RESISTANCE) && (!source.isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.DamageTypeTags.BYPASSES_RESISTANCE))) {
                int i = (this.getStatusEffect(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffects.RESISTANCE).getAmplifier() + 1) * 5;
                int j = 25 - i;
                float f = amount * j;
                float g = amount;
                amount = Math.max(f / 25.0F, 0.0F);
                float h = g - amount;
            }
            if (amount <= 0.0F) {
                return 0.0F;
            } else if (source.isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.DamageTypeTags.BYPASSES_ENCHANTMENTS)) {
                return amount;
            } else {
                float k = 0.0F;
                if (k > 0.0F) {
                    amount = murat.simv2.simulation.mirror.net.minecraft.entity.DamageUtil.getInflictedDamage(amount, k);
                }
                return amount;
            }
        }
    }

    @Nullable
    public murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity getPrimeAdversary() {
        if (this.attackingPlayer != null) {
            return ((murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity) (this.attackingPlayer.resolve(this.getWorld(), murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity.class)));
        } else {
            return this.attackerReference != null ? ((murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity) (this.attackerReference.resolve(this.getWorld(), murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity.class))) : null;
        }
    }

    public float getMaxHealth() {
        return ((float) (this.getAttributeValue(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributes.MAX_HEALTH)));
    }

    public float getMaxAbsorption() {
        return ((float) (this.getAttributeValue(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributes.MAX_ABSORPTION)));
    }

    public double getAttributeValue(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttribute> attribute) {
        return this.getAttributes().getValue(attribute);
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getMainHandStack() {
        return this.getEquippedStack(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot.MAINHAND);
    }

    @NotNull
    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getWeaponStack() {
        return this.getMainHandStack();
    }

    public void setStackInHand(murat.simv2.simulation.mirror.net.minecraft.util.Hand hand, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack stack) {
        if (hand == murat.simv2.simulation.mirror.net.minecraft.util.Hand.MAIN_HAND) {
            this.equipStack(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot.MAINHAND, stack);
        } else {
            this.equipStack(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot.OFFHAND, stack);
        }
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getEquippedStack(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot slot) {
        return this.equipment.get(slot);
    }

    public void equipStack(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot slot, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack stack) {
        this.onEquipStack(slot, this.equipment.put(slot, stack), stack);
    }

    protected float getSoundVolume() {
        return 1.0F;
    }

    public float getSoundPitch() {
        return this.isBaby() ? ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F) + 1.5F : ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F) + 1.0F;
    }

    protected boolean isImmobile() {
        return this.isDead();
    }

    protected float getJumpVelocity() {
        return this.getJumpVelocity(1.0F);
    }

    protected float getJumpVelocity(float strength) {
        return ((((float) (this.getAttributeValue(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributes.JUMP_STRENGTH))) * strength) * this.getJumpVelocityMultiplier()) + this.getJumpBoostVelocityModifier();
    }

    public float getJumpBoostVelocityModifier() {
        return this.hasStatusEffect(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffects.JUMP_BOOST) ? 0.1F * (this.getStatusEffect(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffects.JUMP_BOOST).getAmplifier() + 1.0F) : 0.0F;
    }

    @VisibleForTesting
    public void jump() {
        float f = this.getJumpVelocity();
        if (!(f <= 1.0E-5F)) {
            murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = this.getVelocity();
            this.setVelocity(vec3d.x, Math.max(f, vec3d.y), vec3d.z);
            if (this.isSprinting()) {
                float g = this.getYaw() * ((float) (Math.PI / 180.0));
                this.addVelocityInternal(new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d((-murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.sin(g)) * 0.2, 0.0, murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.cos(g) * 0.2));
            }
        }
    }

    protected void knockDownwards() {
        this.setVelocity(this.getVelocity().add(0.0, -0.04F, 0.0));
    }

    protected void swimUpward(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey<murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid> fluid) {
        this.setVelocity(this.getVelocity().add(0.0, 0.04F, 0.0));
    }

    protected float getBaseWaterMovementSpeedMultiplier() {
        return 0.8F;
    }

    public boolean canWalkOnFluid(murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState state) {
        return false;
    }

    protected double getGravity() {
        return this.getAttributeValue(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributes.GRAVITY);
    }

    protected double getEffectiveGravity() {
        boolean bl = this.getVelocity().y <= 0.0;
        return bl && this.hasStatusEffect(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffects.SLOW_FALLING) ? Math.min(this.getFinalGravity(), 0.01) : this.getFinalGravity();
    }

    public void travel(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movementInput) {
        murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState fluidState = this.getWorld().getFluidState(this.getBlockPos());
        if (((this.isTouchingWater() || this.isInLava()) && this.shouldSwimInFluids()) && (!this.canWalkOnFluid(fluidState))) {
            this.travelInFluid(movementInput);
        } else if (this.isGliding()) {
            this.travelGliding(movementInput);
        } else {
            this.travelMidAir(movementInput);
        }
    }

    private void travelMidAir(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movementInput) {
        murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos = this.getVelocityAffectingPos();
        float f = (this.isOnGround()) ? this.getWorld().getBlockState(blockPos).getBlock().getSlipperiness() : 1.0F;
        float g = f * 0.91F;
        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = this.applyMovementInput(movementInput, f);
        double d = vec3d.y;
        murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance statusEffectInstance = this.getStatusEffect(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffects.LEVITATION);
        if (statusEffectInstance != null) {
            d += ((0.05 * (statusEffectInstance.getAmplifier() + 1)) - vec3d.y) * 0.2;
        } else if ((!this.getWorld().isClient) || this.getWorld().isChunkLoaded(blockPos)) {
            d -= this.getEffectiveGravity();
        }
        if (this.hasNoDrag()) {
            this.setVelocity(vec3d.x, d, vec3d.z);
        } else {
            float h = (this instanceof murat.simv2.simulation.mirror.net.minecraft.entity.Flutterer) ? g : 0.98F;
            this.setVelocity(vec3d.x * g, d * h, vec3d.z * g);
        }
    }

    private void travelInFluid(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movementInput) {
        boolean bl = this.getVelocity().y <= 0.0;
        double d = this.getY();
        double e = this.getEffectiveGravity();
        if (this.isTouchingWater()) {
            float f = (this.isSprinting()) ? 0.9F : this.getBaseWaterMovementSpeedMultiplier();
            float g = 0.02F;
            float h = ((float) (this.getAttributeValue(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributes.WATER_MOVEMENT_EFFICIENCY)));
            if (!this.isOnGround()) {
                h *= 0.5F;
            }
            if (h > 0.0F) {
                f += (0.54600006F - f) * h;
                g += (this.getMovementSpeed() - g) * h;
            }
            this.updateVelocity(g, movementInput);
            this.move(murat.simv2.simulation.mirror.net.minecraft.entity.MovementType.SELF, this.getVelocity());
            murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = this.getVelocity();
            if (this.horizontalCollision && this.isClimbing()) {
                vec3d = new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d(vec3d.x, 0.2, vec3d.z);
            }
            vec3d = vec3d.multiply(f, 0.8F, f);
            this.setVelocity(this.applyFluidMovingSpeed(e, bl, vec3d));
        } else {
            this.updateVelocity(0.02F, movementInput);
            this.move(murat.simv2.simulation.mirror.net.minecraft.entity.MovementType.SELF, this.getVelocity());
            if (this.getFluidHeight(murat.simv2.simulation.mirror.net.minecraft.registry.tag.FluidTags.LAVA) <= this.getSwimHeight()) {
                this.setVelocity(this.getVelocity().multiply(0.5, 0.8F, 0.5));
                murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d2 = this.applyFluidMovingSpeed(e, bl, this.getVelocity());
                this.setVelocity(vec3d2);
            } else {
                this.setVelocity(this.getVelocity().multiply(0.5));
            }
            if (e != 0.0) {
                this.setVelocity(this.getVelocity().add(0.0, (-e) / 4.0, 0.0));
            }
        }
        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d2 = this.getVelocity();
        if (this.horizontalCollision && this.doesNotCollide(vec3d2.x, ((vec3d2.y + 0.6F) - this.getY()) + d, vec3d2.z)) {
            this.setVelocity(vec3d2.x, 0.3F, vec3d2.z);
        }
    }

    private void travelGliding(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movementInput) {
        if (this.isClimbing()) {
            this.travelMidAir(movementInput);
        } else {
            murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = this.getVelocity();
            double d = vec3d.horizontalLength();
            this.setVelocity(this.calcGlidingVelocity(vec3d));
            this.move(murat.simv2.simulation.mirror.net.minecraft.entity.MovementType.SELF, this.getVelocity());
            if (!this.getWorld().isClient) {
                double e = this.getVelocity().horizontalLength();
                this.checkGlidingCollision(d, e);
            }
        }
    }

    private murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d calcGlidingVelocity(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d oldVelocity) {
        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = this.getRotationVector();
        float f = this.getPitch() * ((float) (Math.PI / 180.0));
        double d = Math.sqrt((vec3d.x * vec3d.x) + (vec3d.z * vec3d.z));
        double e = oldVelocity.horizontalLength();
        double g = this.getEffectiveGravity();
        double h = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.square(Math.cos(f));
        oldVelocity = oldVelocity.add(0.0, g * ((-1.0) + (h * 0.75)), 0.0);
        if ((oldVelocity.y < 0.0) && (d > 0.0)) {
            double i = (oldVelocity.y * (-0.1)) * h;
            oldVelocity = oldVelocity.add((vec3d.x * i) / d, i, (vec3d.z * i) / d);
        }
        if ((f < 0.0F) && (d > 0.0)) {
            double i = (e * (-murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.sin(f))) * 0.04;
            oldVelocity = oldVelocity.add(((-vec3d.x) * i) / d, i * 3.2, ((-vec3d.z) * i) / d);
        }
        if (d > 0.0) {
            oldVelocity = oldVelocity.add((((vec3d.x / d) * e) - oldVelocity.x) * 0.1, 0.0, (((vec3d.z / d) * e) - oldVelocity.z) * 0.1);
        }
        return oldVelocity.multiply(0.99F, 0.98F, 0.99F);
    }

    private void checkGlidingCollision(double oldSpeed, double newSpeed) {
        if (this.horizontalCollision) {
            double d = oldSpeed - newSpeed;
            float f = ((float) ((d * 10.0) - 3.0));
            if (f > 0.0F) {
                this.playSound(this.getFallSound(((int) (f))), 1.0F, 1.0F);
                this.serverDamage(this.getDamageSources().flyIntoWall(), f);
            }
        }
    }

    private void travelControlled(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity controllingPlayer, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movementInput) {
        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = this.getControlledMovementInput(controllingPlayer, movementInput);
        if (this.canMoveVoluntarily()) {
            this.setMovementSpeed(this.getSaddledSpeed(controllingPlayer));
            this.travel(vec3d);
        } else {
            this.setVelocity(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d.ZERO);
        }
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getControlledMovementInput(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity controllingPlayer, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movementInput) {
        return movementInput;
    }

    protected float getSaddledSpeed(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity controllingPlayer) {
        return this.getMovementSpeed();
    }

    public void updateLimbs(boolean flutter) {
        float f = ((float) (murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.magnitude(this.getX() - this.lastX, flutter ? this.getY() - this.lastY : 0.0, this.getZ() - this.lastZ)));
    }

    private murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d applyMovementInput(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movementInput, float slipperiness) {
        this.updateVelocity(this.getMovementSpeed(slipperiness), movementInput);
        this.setVelocity(this.applyClimbingSpeed(this.getVelocity()));
        this.move(murat.simv2.simulation.mirror.net.minecraft.entity.MovementType.SELF, this.getVelocity());
        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = this.getVelocity();
        if ((this.horizontalCollision || this.jumping) && (this.isClimbing() || (this.wasInPowderSnow && murat.simv2.simulation.mirror.net.minecraft.block.PowderSnowBlock.canWalkOnPowderSnow(this)))) {
            vec3d = new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d(vec3d.x, 0.2, vec3d.z);
        }
        return vec3d;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d applyFluidMovingSpeed(double gravity, boolean falling, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d motion) {
        if ((gravity != 0.0) && (!this.isSprinting())) {
            double d = 0.0;
            if ((falling && (Math.abs(motion.y - 0.005) >= 0.003)) && (Math.abs(motion.y - (gravity / 16.0)) < 0.003)) {
            } else {
                d = motion.y - (gravity / 16.0);
            }
            return new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d(motion.x, d, motion.z);
        } else {
            return motion;
        }
    }

    private murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d applyClimbingSpeed(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d motion) {
        if (this.isClimbing()) {
            this.onLanding();
            double d = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.clamp(motion.x, -0.15F, 0.15F);
            double e = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.clamp(motion.z, -0.15F, 0.15F);
            double g = Math.max(motion.y, -0.15F);
            motion = new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d(d, g, e);
        }
        return motion;
    }

    private float getMovementSpeed(float slipperiness) {
        return this.isOnGround() ? this.getMovementSpeed() * (0.21600002F / ((slipperiness * slipperiness) * slipperiness)) : this.getOffGroundSpeed();
    }

    public void tickMovement() {
        if (this.jumpingCooldown > 0) {
            this.jumpingCooldown--;
        }
        if (this.isInterpolating()) {
        } else if (!this.canMoveVoluntarily()) {
            this.setVelocity(this.getVelocity().multiply(0.98));
        }
        if (this.headTrackingIncrements > 0) {
            this.lerpHeadYaw(this.headTrackingIncrements, this.serverHeadYaw);
        }
        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = this.getVelocity();
        double d = vec3d.x;
        double e = vec3d.y;
        double f = vec3d.z;
        this.setVelocity(d, e, f);
        murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler profiler = murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profilers.get();
        this.tickMovementInput();
        if (this.isImmobile()) {
            this.jumping = false;
            this.sidewaysSpeed = 0.0F;
            this.forwardSpeed = 0.0F;
        }
        if (this.jumping && this.shouldSwimInFluids()) {
            double g = 0.0;
            if (this.isInLava()) {
                g = this.getFluidHeight(murat.simv2.simulation.mirror.net.minecraft.registry.tag.FluidTags.LAVA);
            } else {
                g = this.getFluidHeight(murat.simv2.simulation.mirror.net.minecraft.registry.tag.FluidTags.WATER);
            }
            boolean bl = this.isTouchingWater() && (g > 0.0);
            double h = this.getSwimHeight();
            if ((!bl) || (this.isOnGround() && (!(g > h)))) {
                if ((!this.isInLava()) || (this.isOnGround() && (!(g > h)))) {
                    if ((this.isOnGround() || (bl && (g <= h))) && (this.jumpingCooldown == 0)) {
                        this.jump();
                    }
                } else {
                    this.swimUpward(murat.simv2.simulation.mirror.net.minecraft.registry.tag.FluidTags.LAVA);
                }
            } else {
                this.swimUpward(murat.simv2.simulation.mirror.net.minecraft.registry.tag.FluidTags.WATER);
            }
        }
        if (this.isGliding()) {
            this.tickGliding();
        }
        murat.simv2.simulation.mirror.net.minecraft.util.math.Box box = this.getBoundingBox();
        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d2 = new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d(this.sidewaysSpeed, this.upwardSpeed, this.forwardSpeed);
        if (this.hasStatusEffect(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffects.SLOW_FALLING) || this.hasStatusEffect(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffects.LEVITATION)) {
            this.onLanding();
        }
        if ((this.getControllingPassenger() instanceof murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity playerEntity) && this.isAlive()) {
            this.travelControlled(playerEntity, vec3d2);
        } else if (this.canMoveVoluntarily()) {
            this.travel(vec3d2);
        }
        if ((!this.getWorld().isClient()) || this.isLogicalSideForUpdatingMovement()) {
            this.tickBlockCollision();
        }
        if (this.getWorld().isClient()) {
            this.updateLimbs(this instanceof murat.simv2.simulation.mirror.net.minecraft.entity.Flutterer);
        }
        if (this.getWorld() instanceof murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld serverWorld) {
            this.addPowderSnowSlowIfNeeded();
            if ((((this.age % 40) == 0) && this.isFrozen()) && this.canFreeze()) {
                this.damage(serverWorld, this.getDamageSources().freeze(), 1.0F);
            }
        }
        if (this.riptideTicks > 0) {
            this.tickRiptide(box, this.getBoundingBox());
        }
        this.tickCramming();
        if (((this.getWorld() instanceof murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld serverWorld) && this.hurtByWater()) && this.isTouchingWaterOrRain()) {
            this.damage(serverWorld, this.getDamageSources().drown(), 1.0F);
        }
    }

    protected void tickMovementInput() {
        this.sidewaysSpeed *= 0.98F;
        this.forwardSpeed *= 0.98F;
    }

    public boolean hurtByWater() {
        return false;
    }

    protected void tickGliding() {
        this.limitFallDistance();
    }

    protected void tickCramming() {
        List<murat.simv2.simulation.mirror.net.minecraft.entity.Entity> list = this.getWorld().getCrammedEntities(this, this.getBoundingBox());
        if (!list.isEmpty()) {
            if (this.getWorld() instanceof murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld serverWorld) {
                int i = serverWorld.getGameRules().getInt(murat.simv2.simulation.mirror.net.minecraft.world.GameRules.MAX_ENTITY_CRAMMING);
                if (((i > 0) && (list.size() > (i - 1))) && (this.random.nextInt(4) == 0)) {
                    int j = 0;
                    for (murat.simv2.simulation.mirror.net.minecraft.entity.Entity entity : list) {
                        if (!entity.hasVehicle()) {
                            j++;
                        }
                    }
                    if (j > (i - 1)) {
                        this.damage(serverWorld, this.getDamageSources().cramming(), 6.0F);
                    }
                }
            }
        }
    }

    protected void tickRiptide(murat.simv2.simulation.mirror.net.minecraft.util.math.Box a, murat.simv2.simulation.mirror.net.minecraft.util.math.Box b) {
        murat.simv2.simulation.mirror.net.minecraft.util.math.Box box = a.union(b);
        List<murat.simv2.simulation.mirror.net.minecraft.entity.Entity> list = this.getWorld().getOtherEntities(this, box);
        if (!list.isEmpty()) {
            for (murat.simv2.simulation.mirror.net.minecraft.entity.Entity entity : list) {
                if (entity instanceof murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity) {
                    this.attackLivingEntity(((murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity) (entity)));
                    this.setVelocity(this.getVelocity().multiply(-0.2));
                }
            }
        }
    }

    public boolean isUsingRiptide() {
        return (this.dataTracker.get(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity.LIVING_FLAGS) & 4) != 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.PositionInterpolator getInterpolator() {
        return this.interpolator;
    }

    public float getHeadYaw() {
        return this.headYaw;
    }

    public void setAbsorptionAmount(float absorptionAmount) {
        this.setAbsorptionAmountUnclamped(murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.clamp(absorptionAmount, 0.0F, this.getMaxAbsorption()));
    }

    @Nullable
    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getBlockingItem() {
        if (!this.isUsingItem()) {
            return null;
        } else {
            murat.simv2.simulation.mirror.net.minecraft.component.type.BlocksAttacksComponent blocksAttacksComponent = (murat.simv2.simulation.mirror.net.minecraft.component.type.BlocksAttacksComponent) this.activeItemStack.get(murat.simv2.simulation.mirror.net.minecraft.component.DataComponentTypes.BLOCKS_ATTACKS);
            if (blocksAttacksComponent != null) {
                int i = this.activeItemStack.getItem().getMaxUseTime(this.activeItemStack, this) - this.itemUseTimeLeft;
                if (i >= blocksAttacksComponent.getBlockDelayTicks()) {
                    return this.activeItemStack;
                }
            }
            return null;
        }
    }

    public boolean isHoldingOntoLadder() {
        return this.isSneaking();
    }

    public boolean isGliding() {
        return this.getFlag(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.GLIDING_FLAG_INDEX);
    }

    public boolean isInSwimmingPose() {
        return super.isInSwimmingPose() || ((!this.isGliding()) && this.isInPose(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose.GLIDING));
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions getDimensions(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose pose) {
        return pose == murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose.SLEEPING ? murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity.SLEEPING_DIMENSIONS : this.getBaseDimensions(pose).scaled(this.getScale());
    }

    public boolean isSleeping() {
        return this.getSleepingPosition().isPresent();
    }

    public void wakeUp() {
        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = this.getPos();
        this.setPosition(vec3d.x, vec3d.y, vec3d.z);
    }

    public boolean canFreeze() {
        if (this.isSpectator()) {
            return false;
        } else {
            for (murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot equipmentSlot : java.util.List.<murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot>of()) {
                if (this.getEquippedStack(equipmentSlot).isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.ItemTags.FREEZE_IMMUNE_WEARABLES)) {
                    return false;
                }
            }
            return super.canFreeze();
        }
    }

    public float getStepHeight() {
        float f = ((float) (this.getAttributeValue(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributes.STEP_HEIGHT)));
        return this.getControllingPassenger() instanceof murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity ? Math.max(f, 1.0F) : f;
    }

    protected void lerpHeadYaw(int headTrackingIncrements, double serverHeadYaw) {
        this.headYaw = ((float) (murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.lerpAngleDegrees(1.0 / headTrackingIncrements, ((double) (this.headYaw)), serverHeadYaw)));
    }

    public boolean isInvulnerableTo(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld world, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource source) {
        return this.isAlwaysInvulnerableTo(source) || murat.simv2.simulation.mirror.net.minecraft.enchantment.EnchantmentHelper.isInvulnerableTo(world, this, source);
    }

    public LivingEntity() {
    }

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static class FallSounds {
        public murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent big;
        public murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent small;

        public FallSounds(murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p0, murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p1) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent big() {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent small() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public FallSounds() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
