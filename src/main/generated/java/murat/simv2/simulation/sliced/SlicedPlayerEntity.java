package murat.simv2.simulation.sliced;

import java.util.Optional;
import net.minecraft.entity.Entity.MoveEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ProjectileDeflection;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stat;
import net.minecraft.util.Arm;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameMode;
import net.minecraft.world.GameRules;
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

    private final PlayerAbilities abilities = new PlayerAbilities();
    public int experienceLevel = 0;
    protected float damageTiltYaw;
    protected int lastAttackedTicks;
    public boolean jumping;
    protected float riptideAttackDamage;
    public double fallDistance;

    protected Vec3d adjustMovementForSneaking(Vec3d movement, MovementType type) {
        float f = this.getStepHeight();
        if (((((!this.abilities.flying) && (!(movement.y > 0.0))) && ((type == MovementType.SELF) || (type == MovementType.PLAYER))) && this.clipAtLedge()) && this.method_30263(f)) {
            double d = movement.x;
            double e = movement.z;
            double h = Math.signum(d) * 0.05;
            double i;
            for (i = Math.signum(e) * 0.05; (d != 0.0) && this.isSpaceAroundPlayerEmpty(d, 0.0, f); d -= h) {
                if (Math.abs(d) <= 0.05) {
                }
            }
            while ((e != 0.0) && this.isSpaceAroundPlayerEmpty(0.0, e, f)) {
                if (Math.abs(e) <= 0.05) {
                }
                e -= i;
            } 
            while (((d != 0.0) && (e != 0.0)) && this.isSpaceAroundPlayerEmpty(d, e, f)) {
                if (Math.abs(d) <= 0.05) {
                } else {
                    d -= h;
                }
                if (Math.abs(e) <= 0.05) {
                } else {
                    e -= i;
                }
            } 
            return new Vec3d(d, movement.y, e);
        } else {
            return movement;
        }
    }

    protected void applyDamage(ServerWorld world, DamageSource source, float amount) {
        if (!this.isInvulnerableTo(world, source)) {
            amount = this.applyArmorToDamage(source, amount);
            amount = this.modifyAppliedDamage(source, amount);
            float var8 = Math.max(amount - this.getAbsorptionAmount(), 0.0F);
            this.setAbsorptionAmount(this.getAbsorptionAmount() - (amount - var8));
            float g = amount - var8;
            if (var8 != 0.0F) {
                this.setHealth(this.getHealth() - var8);
            }
        }
    }

    public void attack(Entity target) {
        if (target.isAttackable()) {
            if (!target.handleAttack( (PlayerEntity) this.entityBridge)) {
                float f = (this.isUsingRiptide()) ? this.riptideAttackDamage : ((float) (this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE)));
                ItemStack itemStack = this.getWeaponStack();
                DamageSource damageSource = ((DamageSource) (Optional.ofNullable(itemStack.getItem().getDamageSource( (PlayerEntity) this.entityBridge)).orElse(this.getDamageSources().playerAttack( (PlayerEntity) this.entityBridge))));
                float g = this.getDamageAgainst(target, f, damageSource) - f;
                float h = this.getAttackCooldownProgress(0.5F);
                f *= 0.2F + ((h * h) * 0.8F);
                g *= h;
                if ((target.getType().isIn(EntityTypeTags.REDIRECTABLE_PROJECTILE) && (target instanceof ProjectileEntity projectileEntity)) && projectileEntity.deflect(ProjectileDeflection.REDIRECTED, (PlayerEntity) this.entityBridge, (PlayerEntity) this.entityBridge, true)) {
                } else if ((f > 0.0F) || (g > 0.0F)) {
                    boolean bl = h > 0.9F;
                    boolean bl2;
                    if (this.isSprinting() && bl) {
                    } else {
                    }
                    boolean bl3 = (((((((bl && (this.fallDistance > 0.0)) && (!this.isOnGround())) && (!this.isClimbing())) && (!this.isTouchingWater())) && (!this.hasStatusEffect(StatusEffects.BLINDNESS))) && (!this.hasVehicle())) && (target instanceof LivingEntity)) && (!this.isSprinting());
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
                    } else {
                    }
                }
            }
        }
    }

    protected void attackLivingEntity(LivingEntity target) {
        this.attack(target);
    }

    protected boolean canChangeIntoPose(EntityPose pose) {
        return this.getWorld().isSpaceEmpty( (PlayerEntity) this.entityBridge, this.getDimensions(pose).getBoxAt(this.getPos()).contract(1.0E-7));
    }

    public boolean canMoveVoluntarily() {
        return (!this.getWorld().isClient) || this.isMainPlayer();
    }

    protected boolean clipAtLedge() {
        return this.isSneaking();
    }

    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        if (this.isInvulnerableTo(world, source)) {
        } else if (this.abilities.invulnerable && (!source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY))) {
        } else if (this.isDead()) {
        } else {
            if (source.isScaledWithDifficulty()) {
                if (world.getDifficulty() == Difficulty.PEACEFUL) {
                }
                if (world.getDifficulty() == Difficulty.EASY) {
                    amount = Math.min((amount / 2.0F) + 1.0F, amount);
                }
                if (world.getDifficulty() == Difficulty.HARD) {
                    amount = (amount * 3.0F) / 2.0F;
                }
            }
            return amount == 0.0F ? false : this.damage(world, source, amount);
        }
    }

    public float getAbsorptionAmount() {
        return this.getDataTracker().get(ABSORPTION_AMOUNT);
    }

    public float getAttackCooldownProgress(float baseTime) {
        return MathHelper.clamp((this.lastAttackedTicks + baseTime) / this.getAttackCooldownProgressPerTick(), 0.0F, 1.0F);
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

    protected int getExperienceToDrop(ServerWorld world) {
        return (!world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY)) && (!this.isSpectator()) ? Math.min(this.experienceLevel * 7, 100) : 0;
    }

    protected Entity.MoveEffect getMoveEffect() {
        return this.abilities.flying || (this.isOnGround() && this.isSneaky()) ? Entity.MoveEffect.NONE : Entity.MoveEffect.ALL;
    }

    public float getMovementSpeed() {
        return ((float) (this.getAttributeValue(EntityAttributes.MOVEMENT_SPEED)));
    }

    protected float getOffGroundSpeed() {
        if (this.abilities.flying && (!this.hasVehicle())) {
            return this.isSprinting() ? this.abilities.getFlySpeed() * 2.0F : this.abilities.getFlySpeed();
        } else {
            return this.isSprinting() ? 0.025999999F : 0.02F;
        }
    }

    protected float getVelocityMultiplier() {
        return (!this.abilities.flying) && (!this.isGliding()) ? this.getVelocityMultiplier() : 1.0F;
    }

    public boolean isClimbing() {
        return this.abilities.flying ? false : this.isClimbing();
    }

    protected boolean isControlledByMainPlayer() {
        return this.isMainPlayer();
    }

    public boolean isControlledByPlayer() {
        return true;
    }

    protected boolean isImmobile() {
        return this.isImmobile() || this.isSleeping();
    }

    public boolean isInvulnerableTo(ServerWorld world, DamageSource source) {
        if (this.isInvulnerableTo(world, source)) {
            return true;
        } else if (source.isIn(DamageTypeTags.IS_DROWNING)) {
            return !world.getGameRules().getBoolean(GameRules.DROWNING_DAMAGE);
        } else if (source.isIn(DamageTypeTags.IS_FALL)) {
            return !world.getGameRules().getBoolean(GameRules.FALL_DAMAGE);
        } else if (source.isIn(DamageTypeTags.IS_FIRE)) {
            return !world.getGameRules().getBoolean(GameRules.FIRE_DAMAGE);
        } else {
            return source.isIn(DamageTypeTags.IS_FREEZING) ? !world.getGameRules().getBoolean(GameRules.FREEZE_DAMAGE) : false;
        }
    }

    public boolean isPushedByFluids() {
        return !this.abilities.flying;
    }

    private boolean isSpaceAroundPlayerEmpty(double offsetX, double offsetZ, double d) {
        Box box = this.getBoundingBox();
        return this.getWorld().isSpaceEmpty( (PlayerEntity) this.entityBridge, new Box((box.minX + 1.0E-7) + offsetX, (box.minY - d) - 1.0E-7, (box.minZ + 1.0E-7) + offsetZ, (box.maxX - 1.0E-7) + offsetX, box.minY, (box.maxZ - 1.0E-7) + offsetZ));
    }

    public boolean isSpectator() {
        return this.getGameMode() == GameMode.SPECTATOR;
    }

    public boolean isSwimming() {
        return ((!this.abilities.flying) && (!this.isSpectator())) && this.isSwimming();
    }

    private boolean method_30263(float f) {
        return this.isOnGround() || ((this.fallDistance < f) && (!this.isSpaceAroundPlayerEmpty(0.0, 0.0, f - this.fallDistance)));
    }

    public void onDeath(DamageSource damageSource) {
        this.onDeath(damageSource);
        this.refreshPosition();
        if ((!this.isSpectator()) && (this.getWorld() instanceof ServerWorld serverWorld)) {
            this.drop(serverWorld, damageSource);
        }
        if (damageSource != null) {
            this.setVelocity((-MathHelper.cos((this.getDamageTiltYaw() + this.getYaw()) * ((float) (Math.PI / 180.0)))) * 0.1F, 0.1F, (-MathHelper.sin((this.getDamageTiltYaw() + this.getYaw()) * ((float) (Math.PI / 180.0)))) * 0.1F);
        } else {
            this.setVelocity(0.0, 0.1, 0.0);
        }
    }

    protected void onSwimmingStart() {
        if (!this.isSpectator()) {
        }
    }

    protected void setAbsorptionAmountUnclamped(float absorptionAmount) {
        this.getDataTracker().set(ABSORPTION_AMOUNT, absorptionAmount);
    }

    protected boolean shouldAlwaysDropExperience() {
        return true;
    }

    public boolean shouldSwimInFluids() {
        return !this.abilities.flying;
    }

    public void tickMovement() {
        if (this.abilities.flying && (!this.hasVehicle())) {
            this.onLanding();
        }
        this.tickMovement();
        float f;
    }

    public void travel(Vec3d movementInput) {
        if (this.hasVehicle()) {
            this.travel(movementInput);
        } else {
            if (this.isSwimming()) {
                double d = this.getRotationVector().y;
                double e = (d < (-0.2)) ? 0.085 : 0.06;
                if (((d <= 0.0) || this.jumping) || (!this.getWorld().getFluidState(BlockPos.ofFloored(this.getX(), (this.getY() + 1.0) - 0.1, this.getZ())).isEmpty())) {
                    Vec3d vec3d = this.getVelocity();
                    this.setVelocity(vec3d.add(0.0, (d - vec3d.y) * e, 0.0));
                }
            }
            if (this.getAbilities().flying) {
                double d = this.getVelocity().y;
                this.travel(movementInput);
                this.setVelocity(this.getVelocity().withAxis(Axis.Y, d * 0.6));
            } else {
                this.travel(movementInput);
            }
        }
    }

    public void wakeUp() {
        this.wakeUp(true, true);
    }

    /**
     * Wakes this player up.
     *
     * @param updateSleepingPlayers
     * 		if {@code true} and called on the logical server, sends sleeping status updates to all players
     * @param skipSleepTimer
     * 		if {@code true}, the {@linkplain #sleepTimer sleep timer} will be set straight to 0 instead of 100
     */
    public void wakeUp(boolean skipSleepTimer, boolean updateSleepingPlayers) {
        this.wakeUp();
    }

    public boolean isMainPlayer() {
        return false;
    }

    @Nullable
    public abstract GameMode getGameMode();

    public PlayerAbilities getAbilities() {
        return this.abilities;
    }
}
