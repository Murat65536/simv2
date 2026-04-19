package murat.simv2.simulation.mirror.net.minecraft.entity.player;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis;
import java.util.Optional;
import murat.simv2.simulation.mirror.net.minecraft.block.BlockState;
import murat.simv2.simulation.mirror.net.minecraft.entity.Entity;
import murat.simv2.simulation.mirror.net.minecraft.entity.Entity.MoveEffect;
import murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose;
import murat.simv2.simulation.mirror.net.minecraft.entity.EntityType;
import murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot;
import murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity;
import murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity.FallSounds;
import murat.simv2.simulation.mirror.net.minecraft.entity.MovementType;
import murat.simv2.simulation.mirror.net.minecraft.entity.ProjectileDeflection;
import murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributes;
import murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonPart;
import murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource;
import murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker;
import murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData;
import murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedDataHandlerRegistry;
import murat.simv2.simulation.mirror.net.minecraft.entity.decoration.ArmorStandEntity;
import murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffects;
import murat.simv2.simulation.mirror.net.minecraft.entity.passive.ParrotEntity;
import murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity;
import murat.simv2.simulation.mirror.net.minecraft.item.ItemStack;
import murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound;
import murat.simv2.simulation.mirror.net.minecraft.particle.ParticleTypes;
import murat.simv2.simulation.mirror.net.minecraft.registry.tag.BlockTags;
import murat.simv2.simulation.mirror.net.minecraft.registry.tag.DamageTypeTags;
import murat.simv2.simulation.mirror.net.minecraft.registry.tag.EntityTypeTags;
import murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity;
import murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld;
import murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent;
import murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvents;
import murat.simv2.simulation.mirror.net.minecraft.util.Hand;
import murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Box;
import murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d;
import murat.simv2.simulation.mirror.net.minecraft.world.Difficulty;
import murat.simv2.simulation.mirror.net.minecraft.world.GameMode;
import murat.simv2.simulation.mirror.net.minecraft.world.GameRules;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
// Mirrored from net.minecraft.entity.player.PlayerEntity
// Movement-relevant statements only (WALA backward slice + Spoon AST pruning)
// Generated - do not edit
public abstract class PlayerEntity extends LivingEntity {
    public static final TrackedData ABSORPTION_AMOUNT = PlayerEntity.ABSORPTION_AMOUNT;

    public static final TrackedData LEFT_SHOULDER_ENTITY = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.NBT_COMPOUND);

    public static final TrackedData RIGHT_SHOULDER_ENTITY = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.NBT_COMPOUND);

    public final PlayerAbilities abilities = new PlayerAbilities();

    public int experienceLevel = 0;

    public float damageTiltYaw;

    public int lastAttackedTicks;

    public float riptideAttackDamage;

    @Nullable
    public ItemStack riptideStack;

    public boolean velocityModified;

    protected boolean clipAtLedge() {
        return this.isSneaking();
    }

    protected boolean canChangeIntoPose(EntityPose pose) {
        return this.getWorld().isSpaceEmpty(this, this.getDimensions(pose).getBoxAt(this.getPos()).contract(1.0E-7));
    }

    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_PLAYER_SWIM;
    }

    protected SoundEvent getSplashSound() {
        return SoundEvents.ENTITY_PLAYER_SPLASH;
    }

    protected SoundEvent getHighSpeedSplashSound() {
        return SoundEvents.ENTITY_PLAYER_SPLASH_HIGH_SPEED;
    }

    public void tickMovement() {
        if (this.abilities.flying && (!this.hasVehicle())) {
            this.onLanding();
        }
        super.tickMovement();
        this.setMovementSpeed(((float) (this.getAttributeValue(EntityAttributes.MOVEMENT_SPEED))));
        float f = 0.0F;
        if ((this.isOnGround() && (!this.isDead())) && (!this.isSwimming())) {
            f = Math.min(0.1F, ((float) (this.getVelocity().horizontalLength())));
        }
        this.updateShoulderEntity(this.getShoulderEntityLeft());
        this.updateShoulderEntity(this.getShoulderEntityRight());
    }

    private void updateShoulderEntity(NbtCompound entityNbt) {
        if ((!entityNbt.isEmpty()) && (!entityNbt.getBoolean("Silent", false))) {
            if (this.getWorld().random.nextInt(200) == 0) {
                EntityType entityType = ((EntityType) (entityNbt.get("id", EntityType.CODEC).orElse(null)));
                if ((entityType == EntityType.PARROT) && (!ParrotEntity.imitateNearbyMob(this.getWorld(), this))) {
                    this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), ParrotEntity.getRandomSound(this.getWorld(), this.getWorld().random), this.getSoundCategory(), 1.0F, ParrotEntity.getSoundPitch(this.getWorld().random));
                }
            }
        }
    }

    @NotNull
    public ItemStack getWeaponStack() {
        return this.isUsingRiptide() && (this.riptideStack != null) ? this.riptideStack : super.getWeaponStack();
    }

    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
        this.refreshPosition();
        if ((!this.isSpectator()) && (this.getWorld() instanceof ServerWorld serverWorld)) {
            this.drop(serverWorld, damageSource);
        }
        if (damageSource != null) {
            this.setVelocity((-MathHelper.cos((this.getDamageTiltYaw() + this.getYaw()) * ((float) (Math.PI / 180.0)))) * 0.1F, 0.1F, (-MathHelper.sin((this.getDamageTiltYaw() + this.getYaw()) * ((float) (Math.PI / 180.0)))) * 0.1F);
        } else {
            this.setVelocity(0.0, 0.1, 0.0);
        }
        this.extinguish();
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return source.getType().effects().getSound();
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PLAYER_DEATH;
    }

    public boolean isInvulnerableTo(ServerWorld world, DamageSource source) {
        if (super.isInvulnerableTo(world, source)) {
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

    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        if (this.isInvulnerableTo(world, source)) {
        } else if (this.abilities.invulnerable && (!source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY))) {
        } else if (this.isDead()) {
        } else {
            if (source.isScaledWithDifficulty()) {
                if (world.getDifficulty() == Difficulty.EASY) {
                    amount = Math.min((amount / 2.0F) + 1.0F, amount);
                }
                if (world.getDifficulty() == Difficulty.HARD) {
                    amount = (amount * 3.0F) / 2.0F;
                }
            }
            return amount == 0.0F ? false : super.damage(world, source, amount);
        }
        return false;
    }

    protected void damageArmor(DamageSource source, float amount) {
        this.damageEquipment(source, amount, new EquipmentSlot[]{ EquipmentSlot.FEET, EquipmentSlot.LEGS, EquipmentSlot.CHEST, EquipmentSlot.HEAD });
    }

    protected void damageHelmet(DamageSource source, float amount) {
        this.damageEquipment(source, amount, new EquipmentSlot[]{ EquipmentSlot.HEAD });
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

    protected boolean isImmobile() {
        return super.isImmobile() || this.isSleeping();
    }

    public boolean shouldSwimInFluids() {
        return !this.abilities.flying;
    }

    protected Vec3d adjustMovementForSneaking(Vec3d movement, MovementType type) {
        float f = this.getStepHeight();
        if (((((!this.abilities.flying) && (!(movement.y > 0.0))) && ((type == MovementType.SELF) || (type == MovementType.PLAYER))) && this.clipAtLedge()) && this.method_30263(f)) {
            double d = movement.x;
            double e = movement.z;
            double h = Math.signum(d) * 0.05;
            double i = 0.0;
            for (i = Math.signum(e) * 0.05; (d != 0.0) && this.isSpaceAroundPlayerEmpty(d, 0.0, f); d -= h) {
            }
            while ((e != 0.0) && this.isSpaceAroundPlayerEmpty(0.0, e, f)) {
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

    private boolean method_30263(float f) {
        return this.isOnGround() || ((this.fallDistance < f) && (!this.isSpaceAroundPlayerEmpty(0.0, 0.0, f - this.fallDistance)));
    }

    private boolean isSpaceAroundPlayerEmpty(double offsetX, double offsetZ, double d) {
        Box box = this.getBoundingBox();
        return this.getWorld().isSpaceEmpty(this, new Box((box.minX + 1.0E-7) + offsetX, (box.minY - d) - 1.0E-7, (box.minZ + 1.0E-7) + offsetZ, (box.maxX - 1.0E-7) + offsetX, box.minY, (box.maxZ - 1.0E-7) + offsetZ));
    }

    public void attack(Entity target) {
        if (target.isAttackable()) {
            if (!target.handleAttack(this)) {
                float f = (this.isUsingRiptide()) ? this.riptideAttackDamage : ((float) (this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE)));
                ItemStack itemStack = this.getWeaponStack();
                DamageSource damageSource = ((DamageSource) (Optional.ofNullable(itemStack.getItem().getDamageSource(this)).orElse(this.getDamageSources().playerAttack(this))));
                float g = this.getDamageAgainst(target, f, damageSource) - f;
                float h = this.getAttackCooldownProgress(0.5F);
                f *= 0.2F + ((h * h) * 0.8F);
                g *= h;
                if ((target.getType().isIn(EntityTypeTags.REDIRECTABLE_PROJECTILE) && (target instanceof ProjectileEntity projectileEntity)) && projectileEntity.deflect(ProjectileDeflection.REDIRECTED, this, this, true)) {
                    this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, this.getSoundCategory());
                } else if ((f > 0.0F) || (g > 0.0F)) {
                    boolean bl = h > 0.9F;
                    boolean bl2 = false;
                    if (this.isSprinting() && bl) {
                        this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, this.getSoundCategory(), 1.0F, 1.0F);
                    }
                    boolean bl3 = (((((((bl && (this.fallDistance > 0.0)) && (!this.isOnGround())) && (!this.isClimbing())) && (!this.isTouchingWater())) && (!this.hasStatusEffect(StatusEffects.BLINDNESS))) && (!this.hasVehicle())) && (target instanceof LivingEntity)) && (!this.isSprinting());
                    float i = f + g;
                    boolean bl4 = false;
                    if (((bl && (!bl3)) && (!bl2)) && this.isOnGround()) {
                        double d = this.getMovement().horizontalLengthSquared();
                        double e = this.getMovementSpeed() * 2.5;
                    }
                    float j = 0.0F;
                    Vec3d vec3d = target.getVelocity();
                    boolean bl5 = target.sidedDamage(damageSource, i);
                    if (bl5) {
                        float k = this.getAttackKnockbackAgainst(target, damageSource) + (bl2 ? 1.0F : 0.0F);
                        if (k > 0.0F) {
                            if (target instanceof LivingEntity livingEntity2) {
                                livingEntity2.takeKnockback(k * 0.5F, MathHelper.sin(this.getYaw() * ((float) (Math.PI / 180.0))), -MathHelper.cos(this.getYaw() * ((float) (Math.PI / 180.0))));
                            } else {
                                target.addVelocity(((-MathHelper.sin(this.getYaw() * ((float) (Math.PI / 180.0)))) * k) * 0.5F, 0.1, (MathHelper.cos(this.getYaw() * ((float) (Math.PI / 180.0))) * k) * 0.5F);
                            }
                            this.setVelocity(this.getVelocity().multiply(0.6, 1.0, 0.6));
                        }
                        if (bl4) {
                            float l = 1.0F + (((float) (this.getAttributeValue(EntityAttributes.SWEEPING_DAMAGE_RATIO))) * f);
                            for (LivingEntity livingEntity3 : this.getWorld().getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox().expand(1.0, 0.25, 1.0))) {
                                if (((((livingEntity3 != this) && (livingEntity3 != target)) && (!this.isTeammate(livingEntity3))) && (!((livingEntity3 instanceof ArmorStandEntity armorStandEntity) && armorStandEntity.isMarker()))) && (this.squaredDistanceTo(livingEntity3) < 9.0)) {
                                    float m = this.getDamageAgainst(livingEntity3, l, damageSource) * h;
                                    if ((this.getWorld() instanceof ServerWorld serverWorld) && livingEntity3.damage(serverWorld, damageSource, m)) {
                                        livingEntity3.takeKnockback(0.4F, MathHelper.sin(this.getYaw() * ((float) (Math.PI / 180.0))), -MathHelper.cos(this.getYaw() * ((float) (Math.PI / 180.0))));
                                    }
                                }
                            }
                            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, this.getSoundCategory(), 1.0F, 1.0F);
                            this.spawnSweepAttackParticles();
                        }
                        if ((target instanceof ServerPlayerEntity) && target.velocityModified) {
                            target.setVelocity(vec3d);
                        }
                        if (bl3) {
                            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, this.getSoundCategory(), 1.0F, 1.0F);
                        }
                        if ((!bl3) && (!bl4)) {
                            if (bl) {
                                this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, this.getSoundCategory(), 1.0F, 1.0F);
                            } else {
                                this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, this.getSoundCategory(), 1.0F, 1.0F);
                            }
                        }
                        Entity entity = target;
                        if (target instanceof EnderDragonPart) {
                            entity = ((EnderDragonPart) (target)).owner;
                        }
                        boolean bl6 = false;
                        if (((!this.getWorld().isClient) && (!itemStack.isEmpty())) && (entity instanceof LivingEntity)) {
                            if (itemStack.isEmpty()) {
                                if (itemStack == this.getMainHandStack()) {
                                    this.setStackInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
                                } else {
                                    this.setStackInHand(Hand.OFF_HAND, ItemStack.EMPTY);
                                }
                            }
                        }
                    } else {
                        this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, this.getSoundCategory(), 1.0F, 1.0F);
                    }
                }
            }
        }
    }

    protected float getDamageAgainst(Entity target, float baseDamage, DamageSource damageSource) {
        return baseDamage;
    }

    protected void attackLivingEntity(LivingEntity target) {
        this.attack(target);
    }

    public void spawnSweepAttackParticles() {
        double d = -MathHelper.sin(this.getYaw() * ((float) (Math.PI / 180.0)));
        double e = MathHelper.cos(this.getYaw() * ((float) (Math.PI / 180.0)));
        if (this.getWorld() instanceof ServerWorld) {
            ((ServerWorld) (this.getWorld())).spawnParticles(ParticleTypes.SWEEP_ATTACK, this.getX() + d, this.getBodyY(0.5), this.getZ() + e, 0, d, 0.0, e, 0.0);
        }
    }

    public boolean isControlledByPlayer() {
        return true;
    }

    protected boolean isControlledByMainPlayer() {
        return this.isMainPlayer();
    }

    public boolean canMoveVoluntarily() {
        return (!this.getWorld().isClient) || this.isMainPlayer();
    }

    public void wakeUp(boolean skipSleepTimer, boolean updateSleepingPlayers) {
        super.wakeUp();
    }

    public void wakeUp() {
        this.wakeUp(true, true);
    }

    public void travel(Vec3d movementInput) {
        if (this.hasVehicle()) {
            super.travel(movementInput);
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
                super.travel(movementInput);
                this.setVelocity(this.getVelocity().withAxis(Axis.Y, d * 0.6));
            } else {
                super.travel(movementInput);
            }
        }
    }

    public float getMovementSpeed() {
        return ((float) (this.getAttributeValue(EntityAttributes.MOVEMENT_SPEED)));
    }

    protected void onSwimmingStart() {
        if (!this.isSpectator()) {
            super.onSwimmingStart();
        }
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        if (this.isTouchingWater()) {
            this.playSwimSound();
            this.playSecondaryStepSound(state);
        } else {
            BlockPos blockPos = this.getStepSoundPos(pos);
            if (!pos.equals(blockPos)) {
                BlockState blockState = this.getWorld().getBlockState(blockPos);
                if (blockState.isIn(BlockTags.COMBINATION_STEP_SOUND_BLOCKS)) {
                    this.playCombinationStepSounds(blockState, state);
                } else {
                    super.playStepSound(blockPos, blockState);
                }
            } else {
                super.playStepSound(pos, state);
            }
        }
    }

    public FallSounds getFallSounds() {
        return new FallSounds(SoundEvents.ENTITY_PLAYER_SMALL_FALL, SoundEvents.ENTITY_PLAYER_BIG_FALL);
    }

    protected int getExperienceToDrop(ServerWorld world) {
        return (!world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY)) && (!this.isSpectator()) ? Math.min(this.experienceLevel * 7, 100) : 0;
    }

    protected boolean shouldAlwaysDropExperience() {
        return true;
    }

    protected MoveEffect getMoveEffect() {
        return this.abilities.flying || (this.isOnGround() && this.isSneaky()) ? MoveEffect.NONE : MoveEffect.ALL;
    }

    public boolean isSpectator() {
        return this.getGameMode() == GameMode.SPECTATOR;
    }

    public boolean isSwimming() {
        return ((!this.abilities.flying) && (!this.isSpectator())) && super.isSwimming();
    }

    public boolean isPushedByFluids() {
        return !this.abilities.flying;
    }

    protected void setAbsorptionAmountUnclamped(float absorptionAmount) {
        this.getDataTracker().set(PlayerEntity.ABSORPTION_AMOUNT, absorptionAmount);
    }

    public float getAbsorptionAmount() {
        return this.getDataTracker().get(PlayerEntity.ABSORPTION_AMOUNT);
    }

    public void setFireTicks(int fireTicks) {
        super.setFireTicks(this.abilities.invulnerable ? Math.min(fireTicks, 1) : fireTicks);
    }

    public NbtCompound getShoulderEntityLeft() {
        return this.dataTracker.get(PlayerEntity.LEFT_SHOULDER_ENTITY);
    }

    public NbtCompound getShoulderEntityRight() {
        return this.dataTracker.get(PlayerEntity.RIGHT_SHOULDER_ENTITY);
    }

    public float getAttackCooldownProgressPerTick() {
        return ((float) ((1.0 / this.getAttributeValue(EntityAttributes.ATTACK_SPEED)) * 20.0));
    }

    public float getAttackCooldownProgress(float baseTime) {
        return MathHelper.clamp((this.lastAttackedTicks + baseTime) / this.getAttackCooldownProgressPerTick(), 0.0F, 1.0F);
    }

    protected float getVelocityMultiplier() {
        return (!this.abilities.flying) && (!this.isGliding()) ? super.getVelocityMultiplier() : 1.0F;
    }

    public float getDamageTiltYaw() {
        return this.damageTiltYaw;
    }

    protected float getOffGroundSpeed() {
        if (this.abilities.flying && (!this.hasVehicle())) {
            return this.isSprinting() ? this.abilities.getFlySpeed() * 2.0F : this.abilities.getFlySpeed();
        } else {
            return this.isSprinting() ? 0.025999999F : 0.02F;
        }
    }

    public boolean isClimbing() {
        return this.abilities.flying ? false : super.isClimbing();
    }

    public PlayerEntity() {
    }

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static class SleepFailureReason {
        public static murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity.SleepFailureReason NOT_POSSIBLE_HERE;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity.SleepFailureReason NOT_POSSIBLE_NOW;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity.SleepFailureReason NOT_SAFE;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity.SleepFailureReason OBSTRUCTED;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity.SleepFailureReason OTHER_PROBLEM;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity.SleepFailureReason TOO_FAR_AWAY;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity.SleepFailureReason[] field_7526;
        public murat.simv2.simulation.mirror.net.minecraft.text.Text message;

        public SleepFailureReason(java.lang.String p0, int p1) {
        }

        public SleepFailureReason(java.lang.String p0, int p1, murat.simv2.simulation.mirror.net.minecraft.text.Text p2) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.text.Text getMessage() {
            return null;
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity.SleepFailureReason valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity.SleepFailureReason[] values() {
            return null;
        }

        public SleepFailureReason() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
