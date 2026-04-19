package murat.simv2.simulation.mirror.net.minecraft.entity;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.doubles.DoubleDoubleImmutablePair;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
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
import murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributes;
import murat.simv2.simulation.mirror.net.minecraft.entity.boss.WitherEntity;
import murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource;
import murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageTypes;
import murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData;
import murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance;
import murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffects;
import murat.simv2.simulation.mirror.net.minecraft.entity.passive.WolfEntity;
import murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity;
import murat.simv2.simulation.mirror.net.minecraft.entity.projectile.PersistentProjectileEntity;
import murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity;
import murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState;
import murat.simv2.simulation.mirror.net.minecraft.item.ItemStack;
import murat.simv2.simulation.mirror.net.minecraft.item.Items;
import murat.simv2.simulation.mirror.net.minecraft.loot.LootTable;
import murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContextParameters;
import murat.simv2.simulation.mirror.net.minecraft.loot.context.LootContextTypes;
import murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext;
import murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext.Builder;
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

    public static final TrackedData LIVING_FLAGS = LivingEntity.LIVING_FLAGS;

    public static final TrackedData HEALTH = LivingEntity.HEALTH;

    public static final EntityDimensions SLEEPING_DIMENSIONS = EntityDimensions.fixed(0.2F, 0.2F).withEyeHeight(0.2F);

    public final Map<RegistryEntry, StatusEffectInstance> activeStatusEffects = Maps.<RegistryEntry, StatusEffectInstance>newHashMap();

    public boolean noDrag = false;

    public float headYaw;

    @Nullable
    public LazyEntityReference attackingPlayer;

    public int playerHitTimer;

    public boolean dead;

    public float lastDamageTaken;

    public boolean jumping;

    public float sidewaysSpeed;

    public float upwardSpeed;

    public float forwardSpeed;

    public PositionInterpolator interpolator = new PositionInterpolator(this);

    public double serverHeadYaw;

    public int headTrackingIncrements;

    @Nullable
    public LazyEntityReference attackerReference;

    public int jumpingCooldown;

    public ItemStack activeItemStack = ItemStack.EMPTY;

    public int itemUseTimeLeft;

    public int riptideTicks;

    public boolean experienceDroppingDisabled;

    public EntityEquipment equipment;

    public double lastX;

    public double lastY;

    public double lastZ;

    public int timeUntilRegen;

    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
        if (!this.isTouchingWater()) {
            this.checkWaterState();
        }
        if (((this.getWorld() instanceof ServerWorld serverWorld) && onGround) && (this.fallDistance > 0.0)) {
            double d = Math.max(0, MathHelper.floor(this.getUnsafeFallDistance(this.fallDistance)));
            if ((d > 0.0) && (!state.isAir())) {
                double e = this.getX();
                double f = this.getY();
                double g = this.getZ();
                BlockPos blockPos = this.getBlockPos();
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
        return MathHelper.lerp(((float) (this.getAttributeValue(EntityAttributes.MOVEMENT_EFFICIENCY))), super.getVelocityMultiplier(), 1.0F);
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

    public int getExperienceToDrop(ServerWorld world, @Nullable
    Entity attacker) {
        return EnchantmentHelper.getMobExperience(world, attacker, this, 0);
    }

    @Nullable
    public PlayerEntity getAttackingPlayer() {
        return ((PlayerEntity) (LazyEntityReference.resolve(this.attackingPlayer, this.getWorld(), PlayerEntity.class)));
    }

    public void setAttacking(PlayerEntity attackingPlayer, int playerHitTimer) {
        this.setAttacking(new LazyEntityReference(attackingPlayer), playerHitTimer);
    }

    public void setAttacking(UUID attackingPlayer, int playerHitTimer) {
        this.setAttacking(new LazyEntityReference(attackingPlayer), playerHitTimer);
    }

    private void setAttacking(LazyEntityReference attackingPlayer, int playerHitTimer) {
        this.attackingPlayer = attackingPlayer;
        this.playerHitTimer = playerHitTimer;
    }

    public void setAttacker(@Nullable
    LivingEntity attacker) {
        this.attackerReference = (attacker != null) ? new LazyEntityReference(attacker) : null;
    }

    public boolean hasNoDrag() {
        return this.noDrag;
    }

    public void onEquipStack(EquipmentSlot slot, ItemStack oldStack, ItemStack newStack) {
        if ((!this.getWorld().isClient()) && (!this.isSpectator())) {
            if ((!ItemStack.areItemsAndComponentsEqual(oldStack, newStack)) && (!this.firstUpdate)) {
                EquippableComponent equippableComponent = newStack.get(DataComponentTypes.EQUIPPABLE);
                if (((!this.isSilent()) && (equippableComponent != null)) && (slot == equippableComponent.slot())) {
                    this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), this.getEquipSound(slot, newStack, equippableComponent), this.getSoundCategory(), 1.0F, 1.0F, this.random.nextLong());
                }
            }
        }
    }

    public boolean hasStatusEffect(RegistryEntry effect) {
        return this.activeStatusEffects.containsKey(effect);
    }

    @Nullable
    public StatusEffectInstance getStatusEffect(RegistryEntry effect) {
        return ((StatusEffectInstance) (this.activeStatusEffects.get(effect)));
    }

    public float getHealth() {
        return this.dataTracker.get(LivingEntity.HEALTH);
    }

    public void setHealth(float health) {
        this.dataTracker.set(LivingEntity.HEALTH, MathHelper.clamp(health, 0.0F, this.getMaxHealth()));
    }

    public boolean isDead() {
        return this.getHealth() <= 0.0F;
    }

    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        if (this.isInvulnerableTo(world, source)) {
        } else if (this.isDead()) {
        } else if (source.isIn(DamageTypeTags.IS_FIRE) && this.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
        } else {
            if (this.isSleeping()) {
                this.wakeUp();
            }
            float g = this.getDamageBlockedAmount(world, source, amount);
            amount -= g;
            boolean bl = g > 0.0F;
            if (source.isIn(DamageTypeTags.IS_FREEZING) && this.getType().isIn(EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES)) {
                amount *= 5.0F;
            }
            if (source.isIn(DamageTypeTags.DAMAGES_HELMET) && (!this.getEquippedStack(EquipmentSlot.HEAD).isEmpty())) {
                this.damageHelmet(source, amount);
                amount *= 0.75F;
            }
            boolean bl2 = true;
            if ((this.timeUntilRegen > 10.0F) && (!source.isIn(DamageTypeTags.BYPASSES_COOLDOWN))) {
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
                BlocksAttacksComponent blocksAttacksComponent = this.getActiveItem().get(DataComponentTypes.BLOCKS_ATTACKS);
                if (!source.isIn(DamageTypeTags.NO_KNOCKBACK)) {
                    double d = 0.0;
                    double e = 0.0;
                    if (source.getSource() instanceof ProjectileEntity projectileEntity) {
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
        return false;
    }

    public float getDamageBlockedAmount(ServerWorld world, DamageSource source, float amount) {
        if (amount <= 0.0F) {
            return 0.0F;
        } else {
            ItemStack itemStack = this.getBlockingItem();
            if (itemStack == null) {
                return 0.0F;
            } else {
                BlocksAttacksComponent blocksAttacksComponent = itemStack.get(DataComponentTypes.BLOCKS_ATTACKS);
                if ((blocksAttacksComponent != null) && (!((Boolean) (blocksAttacksComponent.bypassedBy().map(tag -> source.isIn((murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey) tag)).orElse(false))))) {
                    if ((source.getSource() instanceof PersistentProjectileEntity persistentProjectileEntity) && (persistentProjectileEntity.getPierceLevel() > 0)) {
                        return 0.0F;
                    } else {
                        Vec3d vec3d = source.getPosition();
                        double d = 0.0;
                        if (vec3d != null) {
                            Vec3d vec3d2 = this.getRotationVector(0.0F, this.getHeadYaw());
                            Vec3d vec3d3 = vec3d.subtract(this.getPos());
                            vec3d3 = new Vec3d(vec3d3.x, 0.0, vec3d3.z).normalize();
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

    private void playThornsSound(DamageSource damageSource) {
        if (damageSource.isOf(DamageTypes.THORNS)) {
            SoundCategory soundCategory = (this instanceof PlayerEntity) ? SoundCategory.PLAYERS : SoundCategory.HOSTILE;
            this.getWorld().playSound(null, this.getPos().x, this.getPos().y, this.getPos().z, SoundEvents.ENCHANT_THORNS_HIT, soundCategory);
        }
    }

    protected void becomeAngry(DamageSource damageSource) {
        if (((damageSource.getAttacker() instanceof LivingEntity livingEntity) && (!damageSource.isIn(DamageTypeTags.NO_ANGER))) && ((!damageSource.isOf(DamageTypes.WIND_CHARGE)) || (!this.getType().isIn(EntityTypeTags.NO_ANGER_FROM_WIND_CHARGE)))) {
            this.setAttacker(livingEntity);
        }
    }

    @Nullable
    protected PlayerEntity setAttackingPlayer(DamageSource damageSource) {
        Entity entity = damageSource.getAttacker();
        if (entity instanceof PlayerEntity playerEntity) {
            this.setAttacking(playerEntity, 100);
        } else if ((entity instanceof WolfEntity wolfEntity) && wolfEntity.isTamed()) {
            if (wolfEntity.getOwnerReference() != null) {
                this.setAttacking(wolfEntity.getOwnerReference().getUuid(), 100);
            } else {
                this.attackingPlayer = null;
                this.playerHitTimer = 0;
            }
        }
        return ((PlayerEntity) (LazyEntityReference.resolve(this.attackingPlayer, this.getWorld(), PlayerEntity.class)));
    }

    private boolean tryUseDeathProtector(DamageSource source) {
        if (source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return false;
        } else {
            ItemStack itemStack = null;
            DeathProtectionComponent deathProtectionComponent = null;
            for (Hand hand : Hand.values()) {
                ItemStack itemStack2 = this.getStackInHand(hand);
                deathProtectionComponent = (murat.simv2.simulation.mirror.net.minecraft.component.type.DeathProtectionComponent) itemStack2.get(DataComponentTypes.DEATH_PROTECTION);
            }
            if (itemStack != null) {
                this.setHealth(1.0F);
            }
            return deathProtectionComponent != null;
        }
    }

    protected void playHurtSound(DamageSource damageSource) {
        this.playSound(this.getHurtSound(damageSource));
    }

    public void playSound(@Nullable
    SoundEvent sound) {
        if (sound != null) {
            this.playSound(sound, this.getSoundVolume(), this.getSoundPitch());
        }
    }

    public void onDeath(DamageSource damageSource) {
        if ((!this.isRemoved()) && (!this.dead)) {
            Entity entity = damageSource.getAttacker();
            LivingEntity livingEntity = this.getPrimeAdversary();
            if (this.isSleeping()) {
                this.wakeUp();
            }
            this.dead = true;
            if (this.getWorld() instanceof ServerWorld serverWorld) {
                if ((entity == null) || entity.onKilledOther(serverWorld, this)) {
                    this.drop(serverWorld, damageSource);
                    this.onKilledBy(livingEntity);
                }
            }
        }
    }

    protected void onKilledBy(@Nullable
    LivingEntity adversary) {
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            boolean var6 = false;
            if (adversary instanceof WitherEntity) {
                if (serverWorld.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                    BlockPos blockPos = this.getBlockPos();
                    BlockState blockState = Blocks.WITHER_ROSE.getDefaultState();
                }
                if (!var6) {
                    ItemEntity itemEntity = new ItemEntity(this.getWorld(), this.getX(), this.getY(), this.getZ(), new ItemStack(Items.WITHER_ROSE));
                }
            }
        }
    }

    protected void drop(ServerWorld world, DamageSource damageSource) {
        boolean bl = this.playerHitTimer > 0;
        if (this.shouldDropLoot() && world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
            this.dropLoot(world, damageSource, bl);
        }
        this.dropExperience(world, damageSource.getAttacker());
    }

    protected void dropExperience(ServerWorld world, @Nullable
    Entity attacker) {
        if ((!this.isExperienceDroppingDisabled()) && (this.shouldAlwaysDropExperience() || (((this.playerHitTimer > 0) && this.shouldDropExperience()) && world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)))) {
            ExperienceOrbEntity.spawn(world, this.getPos(), this.getExperienceToDrop(world, attacker));
        }
    }

    protected float getAttackKnockbackAgainst(Entity target, DamageSource damageSource) {
        float f = ((float) (this.getAttributeValue(EntityAttributes.ATTACK_KNOCKBACK)));
        return this.getWorld() instanceof ServerWorld serverWorld ? EnchantmentHelper.modifyKnockback(serverWorld, this.getWeaponStack(), target, damageSource, f) : f;
    }

    protected void dropLoot(ServerWorld world, DamageSource damageSource, boolean causedByPlayer) {
        Optional<RegistryKey> optional = this.getLootTableKey();
        if (!optional.isEmpty()) {
            LootTable lootTable = world.getServer().getReloadableRegistries().getLootTable(((RegistryKey) (optional.get())));
            Builder builder = new Builder(world).add(LootContextParameters.THIS_ENTITY, this).add(LootContextParameters.ORIGIN, this.getPos()).add(LootContextParameters.DAMAGE_SOURCE, damageSource).addOptional(LootContextParameters.ATTACKING_ENTITY, damageSource.getAttacker()).addOptional(LootContextParameters.DIRECT_ATTACKING_ENTITY, damageSource.getSource());
            PlayerEntity playerEntity = this.getAttackingPlayer();
            LootWorldContext lootWorldContext = builder.build(LootContextTypes.ENTITY);
        }
    }

    public void takeKnockback(double strength, double x, double z) {
        strength *= 1.0 - this.getAttributeValue(EntityAttributes.KNOCKBACK_RESISTANCE);
        if (!(strength <= 0.0)) {
            Vec3d vec3d = this.getVelocity();
            while (((x * x) + (z * z)) < 1.0E-5F) {
                x = (Math.random() - Math.random()) * 0.01;
                z = (Math.random() - Math.random()) * 0.01;
            } 
            Vec3d vec3d2 = new Vec3d(x, 0.0, z).normalize().multiply(strength);
            this.setVelocity((vec3d.x / 2.0) - vec3d2.x, this.isOnGround() ? Math.min(0.4, (vec3d.y / 2.0) + strength) : vec3d.y, (vec3d.z / 2.0) - vec3d2.z);
        }
    }

    private SoundEvent getFallSound(int distance) {
        return distance > 4 ? this.getFallSounds().big() : this.getFallSounds().small();
    }

    public boolean isExperienceDroppingDisabled() {
        return this.experienceDroppingDisabled;
    }

    public boolean isClimbing() {
        if (this.isSpectator()) {
            return false;
        } else {
            BlockPos blockPos = this.getBlockPos();
            BlockState blockState = this.getBlockStateAtPos();
            if (blockState.isIn(BlockTags.CLIMBABLE)) {
                return true;
            } else if ((blockState.getBlock() instanceof TrapdoorBlock) && this.canEnterTrapdoor(blockPos, blockState)) {
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean canEnterTrapdoor(BlockPos pos, BlockState state) {
        if (!((Boolean) (state.get(TrapdoorBlock.OPEN)))) {
            return false;
        } else {
            BlockState blockState = this.getWorld().getBlockState(pos.down());
            return blockState.isOf(Blocks.LADDER) && (blockState.get(LadderBlock.FACING) == state.get(TrapdoorBlock.FACING));
        }
    }

    public boolean isAlive() {
        return (!this.isRemoved()) && (this.getHealth() > 0.0F);
    }

    private double getUnsafeFallDistance(double fallDistance) {
        return (fallDistance + 1.0E-6) - this.getAttributeValue(EntityAttributes.SAFE_FALL_DISTANCE);
    }

    public int getArmor() {
        return MathHelper.floor(this.getAttributeValue(EntityAttributes.ARMOR));
    }

    protected void damageEquipment(DamageSource source, float amount, EquipmentSlot... slots) {
        if (!(amount <= 0.0F)) {
            int i = ((int) (Math.max(1.0F, amount / 4.0F)));
        }
    }

    protected float applyArmorToDamage(DamageSource source, float amount) {
        if (!source.isIn(DamageTypeTags.BYPASSES_ARMOR)) {
            this.damageArmor(source, amount);
            amount = DamageUtil.getDamageLeft(this, amount, source, this.getArmor(), ((float) (this.getAttributeValue(EntityAttributes.ARMOR_TOUGHNESS))));
        }
        return amount;
    }

    protected float modifyAppliedDamage(DamageSource source, float amount) {
        if (source.isIn(DamageTypeTags.BYPASSES_EFFECTS)) {
            return amount;
        } else {
            if (this.hasStatusEffect(StatusEffects.RESISTANCE) && (!source.isIn(DamageTypeTags.BYPASSES_RESISTANCE))) {
                int i = (this.getStatusEffect(StatusEffects.RESISTANCE).getAmplifier() + 1) * 5;
                int j = 25 - i;
                float f = amount * j;
                float g = amount;
                amount = Math.max(f / 25.0F, 0.0F);
                float h = g - amount;
            }
            if (amount <= 0.0F) {
                return 0.0F;
            } else if (source.isIn(DamageTypeTags.BYPASSES_ENCHANTMENTS)) {
                return amount;
            } else {
                float k = 0.0F;
                if (k > 0.0F) {
                    amount = DamageUtil.getInflictedDamage(amount, k);
                }
                return amount;
            }
        }
    }

    @Nullable
    public LivingEntity getPrimeAdversary() {
        if (this.attackingPlayer != null) {
            return ((LivingEntity) (this.attackingPlayer.resolve(this.getWorld(), PlayerEntity.class)));
        } else {
            return this.attackerReference != null ? ((LivingEntity) (this.attackerReference.resolve(this.getWorld(), LivingEntity.class))) : null;
        }
    }

    public float getMaxHealth() {
        return ((float) (this.getAttributeValue(EntityAttributes.MAX_HEALTH)));
    }

    public float getMaxAbsorption() {
        return ((float) (this.getAttributeValue(EntityAttributes.MAX_ABSORPTION)));
    }

    public double getAttributeValue(RegistryEntry attribute) {
        return this.getAttributes().getValue(attribute);
    }

    public ItemStack getMainHandStack() {
        return this.getEquippedStack(EquipmentSlot.MAINHAND);
    }

    @NotNull
    public ItemStack getWeaponStack() {
        return this.getMainHandStack();
    }

    public void setStackInHand(Hand hand, ItemStack stack) {
        if (hand == Hand.MAIN_HAND) {
            this.equipStack(EquipmentSlot.MAINHAND, stack);
        } else {
            this.equipStack(EquipmentSlot.OFFHAND, stack);
        }
    }

    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return this.equipment.get(slot);
    }

    public void equipStack(EquipmentSlot slot, ItemStack stack) {
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
        return ((((float) (this.getAttributeValue(EntityAttributes.JUMP_STRENGTH))) * strength) * this.getJumpVelocityMultiplier()) + this.getJumpBoostVelocityModifier();
    }

    public float getJumpBoostVelocityModifier() {
        return this.hasStatusEffect(StatusEffects.JUMP_BOOST) ? 0.1F * (this.getStatusEffect(StatusEffects.JUMP_BOOST).getAmplifier() + 1.0F) : 0.0F;
    }

    @VisibleForTesting
    public void jump() {
        float f = this.getJumpVelocity();
        if (!(f <= 1.0E-5F)) {
            Vec3d vec3d = this.getVelocity();
            this.setVelocity(vec3d.x, Math.max(f, vec3d.y), vec3d.z);
            if (this.isSprinting()) {
                float g = this.getYaw() * ((float) (Math.PI / 180.0));
                this.addVelocityInternal(new Vec3d((-MathHelper.sin(g)) * 0.2, 0.0, MathHelper.cos(g) * 0.2));
            }
        }
    }

    protected void knockDownwards() {
        this.setVelocity(this.getVelocity().add(0.0, -0.04F, 0.0));
    }

    protected void swimUpward(TagKey fluid) {
        this.setVelocity(this.getVelocity().add(0.0, 0.04F, 0.0));
    }

    protected float getBaseWaterMovementSpeedMultiplier() {
        return 0.8F;
    }

    public boolean canWalkOnFluid(FluidState state) {
        return false;
    }

    protected double getGravity() {
        return this.getAttributeValue(EntityAttributes.GRAVITY);
    }

    protected double getEffectiveGravity() {
        boolean bl = this.getVelocity().y <= 0.0;
        return bl && this.hasStatusEffect(StatusEffects.SLOW_FALLING) ? Math.min(this.getFinalGravity(), 0.01) : this.getFinalGravity();
    }

    public void travel(Vec3d movementInput) {
        FluidState fluidState = this.getWorld().getFluidState(this.getBlockPos());
        if (((this.isTouchingWater() || this.isInLava()) && this.shouldSwimInFluids()) && (!this.canWalkOnFluid(fluidState))) {
            this.travelInFluid(movementInput);
        } else if (this.isGliding()) {
            this.travelGliding(movementInput);
        } else {
            this.travelMidAir(movementInput);
        }
    }

    private void travelMidAir(Vec3d movementInput) {
        BlockPos blockPos = this.getVelocityAffectingPos();
        float f = (this.isOnGround()) ? this.getWorld().getBlockState(blockPos).getBlock().getSlipperiness() : 1.0F;
        float g = f * 0.91F;
        Vec3d vec3d = this.applyMovementInput(movementInput, f);
        double d = vec3d.y;
        StatusEffectInstance statusEffectInstance = this.getStatusEffect(StatusEffects.LEVITATION);
        if (statusEffectInstance != null) {
            d += ((0.05 * (statusEffectInstance.getAmplifier() + 1)) - vec3d.y) * 0.2;
        } else if ((!this.getWorld().isClient) || this.getWorld().isChunkLoaded(blockPos)) {
            d -= this.getEffectiveGravity();
        }
        if (this.hasNoDrag()) {
            this.setVelocity(vec3d.x, d, vec3d.z);
        } else {
            float h = (this instanceof Flutterer) ? g : 0.98F;
            this.setVelocity(vec3d.x * g, d * h, vec3d.z * g);
        }
    }

    private void travelInFluid(Vec3d movementInput) {
        boolean bl = this.getVelocity().y <= 0.0;
        double d = this.getY();
        double e = this.getEffectiveGravity();
        if (this.isTouchingWater()) {
            float f = (this.isSprinting()) ? 0.9F : this.getBaseWaterMovementSpeedMultiplier();
            float g = 0.02F;
            float h = ((float) (this.getAttributeValue(EntityAttributes.WATER_MOVEMENT_EFFICIENCY)));
            if (!this.isOnGround()) {
                h *= 0.5F;
            }
            if (h > 0.0F) {
                f += (0.54600006F - f) * h;
                g += (this.getMovementSpeed() - g) * h;
            }
            this.updateVelocity(g, movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            Vec3d vec3d = this.getVelocity();
            if (this.horizontalCollision && this.isClimbing()) {
                vec3d = new Vec3d(vec3d.x, 0.2, vec3d.z);
            }
            vec3d = vec3d.multiply(f, 0.8F, f);
            this.setVelocity(this.applyFluidMovingSpeed(e, bl, vec3d));
        } else {
            this.updateVelocity(0.02F, movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            if (this.getFluidHeight(FluidTags.LAVA) <= this.getSwimHeight()) {
                this.setVelocity(this.getVelocity().multiply(0.5, 0.8F, 0.5));
                Vec3d vec3d2 = this.applyFluidMovingSpeed(e, bl, this.getVelocity());
                this.setVelocity(vec3d2);
            } else {
                this.setVelocity(this.getVelocity().multiply(0.5));
            }
            if (e != 0.0) {
                this.setVelocity(this.getVelocity().add(0.0, (-e) / 4.0, 0.0));
            }
        }
        Vec3d vec3d2 = this.getVelocity();
        if (this.horizontalCollision && this.doesNotCollide(vec3d2.x, ((vec3d2.y + 0.6F) - this.getY()) + d, vec3d2.z)) {
            this.setVelocity(vec3d2.x, 0.3F, vec3d2.z);
        }
    }

    private void travelGliding(Vec3d movementInput) {
        if (this.isClimbing()) {
            this.travelMidAir(movementInput);
        } else {
            Vec3d vec3d = this.getVelocity();
            double d = vec3d.horizontalLength();
            this.setVelocity(this.calcGlidingVelocity(vec3d));
            this.move(MovementType.SELF, this.getVelocity());
            if (!this.getWorld().isClient) {
                double e = this.getVelocity().horizontalLength();
                this.checkGlidingCollision(d, e);
            }
        }
    }

    private Vec3d calcGlidingVelocity(Vec3d oldVelocity) {
        Vec3d vec3d = this.getRotationVector();
        float f = this.getPitch() * ((float) (Math.PI / 180.0));
        double d = Math.sqrt((vec3d.x * vec3d.x) + (vec3d.z * vec3d.z));
        double e = oldVelocity.horizontalLength();
        double g = this.getEffectiveGravity();
        double h = MathHelper.square(Math.cos(f));
        oldVelocity = oldVelocity.add(0.0, g * ((-1.0) + (h * 0.75)), 0.0);
        if ((oldVelocity.y < 0.0) && (d > 0.0)) {
            double i = (oldVelocity.y * (-0.1)) * h;
            oldVelocity = oldVelocity.add((vec3d.x * i) / d, i, (vec3d.z * i) / d);
        }
        if ((f < 0.0F) && (d > 0.0)) {
            double i = (e * (-MathHelper.sin(f))) * 0.04;
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

    private void travelControlled(PlayerEntity controllingPlayer, Vec3d movementInput) {
        Vec3d vec3d = this.getControlledMovementInput(controllingPlayer, movementInput);
        if (this.canMoveVoluntarily()) {
            this.setMovementSpeed(this.getSaddledSpeed(controllingPlayer));
            this.travel(vec3d);
        } else {
            this.setVelocity(Vec3d.ZERO);
        }
    }

    protected Vec3d getControlledMovementInput(PlayerEntity controllingPlayer, Vec3d movementInput) {
        return movementInput;
    }

    protected float getSaddledSpeed(PlayerEntity controllingPlayer) {
        return this.getMovementSpeed();
    }

    public void updateLimbs(boolean flutter) {
        float f = ((float) (MathHelper.magnitude(this.getX() - this.lastX, flutter ? this.getY() - this.lastY : 0.0, this.getZ() - this.lastZ)));
    }

    private Vec3d applyMovementInput(Vec3d movementInput, float slipperiness) {
        this.updateVelocity(this.getMovementSpeed(slipperiness), movementInput);
        this.setVelocity(this.applyClimbingSpeed(this.getVelocity()));
        this.move(MovementType.SELF, this.getVelocity());
        Vec3d vec3d = this.getVelocity();
        if ((this.horizontalCollision || this.jumping) && (this.isClimbing() || (this.wasInPowderSnow && PowderSnowBlock.canWalkOnPowderSnow(this)))) {
            vec3d = new Vec3d(vec3d.x, 0.2, vec3d.z);
        }
        return vec3d;
    }

    public Vec3d applyFluidMovingSpeed(double gravity, boolean falling, Vec3d motion) {
        if ((gravity != 0.0) && (!this.isSprinting())) {
            double d = 0.0;
            if ((falling && (Math.abs(motion.y - 0.005) >= 0.003)) && (Math.abs(motion.y - (gravity / 16.0)) < 0.003)) {
            } else {
                d = motion.y - (gravity / 16.0);
            }
            return new Vec3d(motion.x, d, motion.z);
        } else {
            return motion;
        }
    }

    private Vec3d applyClimbingSpeed(Vec3d motion) {
        if (this.isClimbing()) {
            this.onLanding();
            double d = MathHelper.clamp(motion.x, -0.15F, 0.15F);
            double e = MathHelper.clamp(motion.z, -0.15F, 0.15F);
            double g = Math.max(motion.y, -0.15F);
            motion = new Vec3d(d, g, e);
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
        Vec3d vec3d = this.getVelocity();
        double d = vec3d.x;
        double e = vec3d.y;
        double f = vec3d.z;
        this.setVelocity(d, e, f);
        Profiler profiler = Profilers.get();
        this.tickMovementInput();
        if (this.isImmobile()) {
            this.jumping = false;
            this.sidewaysSpeed = 0.0F;
            this.forwardSpeed = 0.0F;
        }
        if (this.jumping && this.shouldSwimInFluids()) {
            double g = 0.0;
            if (this.isInLava()) {
                g = this.getFluidHeight(FluidTags.LAVA);
            } else {
                g = this.getFluidHeight(FluidTags.WATER);
            }
            boolean bl = this.isTouchingWater() && (g > 0.0);
            double h = this.getSwimHeight();
            if ((!bl) || (this.isOnGround() && (!(g > h)))) {
                if ((!this.isInLava()) || (this.isOnGround() && (!(g > h)))) {
                    if ((this.isOnGround() || (bl && (g <= h))) && (this.jumpingCooldown == 0)) {
                        this.jump();
                    }
                } else {
                    this.swimUpward(FluidTags.LAVA);
                }
            } else {
                this.swimUpward(FluidTags.WATER);
            }
        }
        if (this.isGliding()) {
            this.tickGliding();
        }
        Box box = this.getBoundingBox();
        Vec3d vec3d2 = new Vec3d(this.sidewaysSpeed, this.upwardSpeed, this.forwardSpeed);
        if (this.hasStatusEffect(StatusEffects.SLOW_FALLING) || this.hasStatusEffect(StatusEffects.LEVITATION)) {
            this.onLanding();
        }
        if ((this.getControllingPassenger() instanceof PlayerEntity playerEntity) && this.isAlive()) {
            this.travelControlled(playerEntity, vec3d2);
        } else if (this.canMoveVoluntarily()) {
            this.travel(vec3d2);
        }
        if ((!this.getWorld().isClient()) || this.isLogicalSideForUpdatingMovement()) {
            this.tickBlockCollision();
        }
        if (this.getWorld().isClient()) {
            this.updateLimbs(this instanceof Flutterer);
        }
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            this.addPowderSnowSlowIfNeeded();
            if ((((this.age % 40) == 0) && this.isFrozen()) && this.canFreeze()) {
                this.damage(serverWorld, this.getDamageSources().freeze(), 1.0F);
            }
        }
        if (this.riptideTicks > 0) {
            this.tickRiptide(box, this.getBoundingBox());
        }
        this.tickCramming();
        if (((this.getWorld() instanceof ServerWorld serverWorld) && this.hurtByWater()) && this.isTouchingWaterOrRain()) {
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
        List<Entity> list = this.getWorld().getCrammedEntities(this, this.getBoundingBox());
        if (!list.isEmpty()) {
            if (this.getWorld() instanceof ServerWorld serverWorld) {
                int i = serverWorld.getGameRules().getInt(GameRules.MAX_ENTITY_CRAMMING);
                if (((i > 0) && (list.size() > (i - 1))) && (this.random.nextInt(4) == 0)) {
                    int j = 0;
                    for (Entity entity : list) {
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

    protected void tickRiptide(Box a, Box b) {
        Box box = a.union(b);
        List<Entity> list = this.getWorld().getOtherEntities(this, box);
        if (!list.isEmpty()) {
            for (Entity entity : list) {
                if (entity instanceof LivingEntity) {
                    this.attackLivingEntity(((LivingEntity) (entity)));
                    this.setVelocity(this.getVelocity().multiply(-0.2));
                }
            }
        }
    }

    public boolean isUsingRiptide() {
        return (this.dataTracker.get(LivingEntity.LIVING_FLAGS) & 4) != 0;
    }

    public PositionInterpolator getInterpolator() {
        return this.interpolator;
    }

    public float getHeadYaw() {
        return this.headYaw;
    }

    public void setAbsorptionAmount(float absorptionAmount) {
        this.setAbsorptionAmountUnclamped(MathHelper.clamp(absorptionAmount, 0.0F, this.getMaxAbsorption()));
    }

    @Nullable
    public ItemStack getBlockingItem() {
        if (!this.isUsingItem()) {
            return null;
        } else {
            BlocksAttacksComponent blocksAttacksComponent = this.activeItemStack.get(DataComponentTypes.BLOCKS_ATTACKS);
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
        return this.getFlag(Entity.GLIDING_FLAG_INDEX);
    }

    public boolean isInSwimmingPose() {
        return super.isInSwimmingPose() || ((!this.isGliding()) && this.isInPose(EntityPose.GLIDING));
    }

    public EntityDimensions getDimensions(EntityPose pose) {
        return pose == EntityPose.SLEEPING ? LivingEntity.SLEEPING_DIMENSIONS : this.getBaseDimensions(pose).scaled(this.getScale());
    }

    public boolean isSleeping() {
        return this.getSleepingPosition().isPresent();
    }

    public void wakeUp() {
        Vec3d vec3d = this.getPos();
        this.setPosition(vec3d.x, vec3d.y, vec3d.z);
    }

    public boolean canFreeze() {
        if (this.isSpectator()) {
            return false;
        } else {
            for (EquipmentSlot equipmentSlot : AttributeModifierSlot.ARMOR) {
                if (this.getEquippedStack(equipmentSlot).isIn(ItemTags.FREEZE_IMMUNE_WEARABLES)) {
                    return false;
                }
            }
            return super.canFreeze();
        }
    }

    public float getStepHeight() {
        float f = ((float) (this.getAttributeValue(EntityAttributes.STEP_HEIGHT)));
        return this.getControllingPassenger() instanceof PlayerEntity ? Math.max(f, 1.0F) : f;
    }

    protected void lerpHeadYaw(int headTrackingIncrements, double serverHeadYaw) {
        this.headYaw = ((float) (MathHelper.lerpAngleDegrees(1.0 / headTrackingIncrements, ((double) (this.headYaw)), serverHeadYaw)));
    }

    public boolean isInvulnerableTo(ServerWorld world, DamageSource source) {
        return this.isAlwaysInvulnerableTo(source) || EnchantmentHelper.isInvulnerableTo(world, this, source);
    }

    // BEGIN GENERATED PRIMARY CONTRACT STUBS
    public static java.lang.String ACTIVE_EFFECTS_NBT_KEY;
    public static java.lang.String ATTRIBUTES_NBT_KEY;
    public static float BABY_SCALE_FACTOR;
    public static int DEATH_TICKS;
    public static float DEFAULT_FRICTION;
    public static int DEFAULT_MIN_FREEZE_DAMAGE_TICKS;
    public static int DEFAULT_PORTAL_COOLDOWN;
    public static int EQUIPMENT_SLOT_ID;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<java.lang.Byte> FLAGS;
    public static int FREEZING_DAMAGE_INTERVAL;
    public static int GLOWING_FLAG;
    public static int GLOWING_FLAG_INDEX;
    public static java.lang.String ID_KEY;
    public static org.slf4j.Logger LOGGER;
    public static int MAX_COMMAND_TAGS;
    public static double MAX_ENTITY_VIEWING_DISTANCE;
    public static int MAX_RIDING_COOLDOWN;
    public static float MIN_RISING_BUBBLE_COLUMN_SPEED;
    public static java.util.function.Predicate<murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity> NOT_WEARING_GAZE_DISGUISE_PREDICATE;
    public static int OFF_HAND_ACTIVE_FLAG;
    public static int ON_FIRE_FLAG_INDEX;
    public static java.lang.String PASSENGERS_KEY;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose> POSE;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<java.util.List<murat.simv2.simulation.mirror.net.minecraft.particle.ParticleEffect>> POTION_SWIRLS;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<java.lang.Boolean> POTION_SWIRLS_AMBIENT;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier POWDER_SNOW_SPEED_MODIFIER_ID;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos>> SLEEPING_POSITION;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier SPRINTING_SPEED_BOOST;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier SPRINTING_SPEED_MODIFIER_ID;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<java.lang.Integer> STINGER_COUNT;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<java.lang.Integer> STUCK_ARROW_COUNT;
    public static int USING_ITEM_FLAG;
    public static int USING_RIPTIDE_FLAG;
    public static java.lang.String UUID_KEY;
    public float absorptionAmount;
    public int age;
    public murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity attacking;
    public murat.simv2.simulation.mirror.net.minecraft.entity.attribute.AttributeContainer attributes;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos;
    public float bodyYaw;
    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.brain.Brain<?> brain;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos chunkPos;
    public java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos> climbingPos;
    public boolean collidedSoftly;
    public murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageTracker damageTracker;
    public murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker dataTracker;
    public int deathTime;
    public int defaultMaxHealth;
    public int despawnCounter;
    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions dimensions;
    public float distanceTraveled;
    public boolean effectsChanged;
    public murat.simv2.simulation.mirror.net.minecraft.entity.mob.ElytraFlightController elytraFlightController;
    public double fallDistance;
    public static int field_30072;
    public static int field_30074;
    public static double field_30075;
    public static int field_30078;
    public static int field_30080;
    public static int field_30081;
    public static float field_44870;
    public static double field_44871;
    public static double field_44872;
    public static float field_44874;
    public static float field_47756;
    public static int field_48827;
    public static int field_49073;
    public static int field_49791;
    public static int field_49793;
    public static int field_55952;
    public static float field_56256;
    public boolean firstUpdate;
    public it.unimi.dsi.fastutil.objects.Object2DoubleMap<murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey<murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid>> fluidHeight;
    public boolean forceUpdateSupportingBlockPos;
    public int glidingTicks;
    public boolean groundCollision;
    public float handSwingProgress;
    public int handSwingTicks;
    public boolean handSwinging;
    public boolean horizontalCollision;
    public int hurtTime;
    public boolean inPowderSnow;
    public boolean intersectionChecked;
    public int lastAttackTime;
    public int lastAttackedTicks;
    public int lastAttackedTime;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos lastBlockPos;
    public float lastBodyYaw;
    public murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource lastDamageSource;
    public long lastDamageTime;
    public java.util.Map<murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack> lastEquipmentStacks;
    public float lastHandSwingProgress;
    public float lastHeadYaw;
    public float lastLeaningPitch;
    public float lastPitch;
    public double lastRenderX;
    public double lastRenderY;
    public double lastRenderZ;
    public float lastYaw;
    public float leaningPitch;
    public murat.simv2.simulation.mirror.net.minecraft.entity.LimbAnimator limbAnimator;
    public java.util.EnumMap<murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot, it.unimi.dsi.fastutil.objects.Reference2ObjectMap<java.lang.Object, java.util.Set<java.lang.Object>>> locationBasedEnchantmentEffects;
    public int maxHurtTime;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movementMultiplier;
    public float movementSpeed;
    public boolean noClip;
    public long pistonMovementTick;
    public murat.simv2.simulation.mirror.net.minecraft.world.dimension.PortalManager portalManager;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d pos;
    public murat.simv2.simulation.mirror.net.minecraft.util.Hand preferredHand;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random random;
    public int ridingCooldown;
    public float riptideAttackDamage;
    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack riptideStack;
    public float speed;
    public float standingEyeHeight;
    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState stateAtPos;
    public int stuckArrowTimer;
    public int stuckStingerTimer;
    public boolean submergedInWater;
    public java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos> supportingBlockPos;
    public boolean touchingWater;
    public java.util.UUID uuid;
    public java.lang.String uuidString;
    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity vehicle;
    public boolean velocityDirty;
    public boolean velocityModified;
    public boolean verticalCollision;

    public void addAirTravelEffects() {
    }

    public void addBubbleParticles() {
    }

    public boolean addCommandTag(java.lang.String p0) {
        return false;
    }

    public void addDeathParticles() {
    }

    public void addFlapEffects() {
    }

    public void addPassenger(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void addPortalChunkTicketAt(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public boolean addStatusEffect(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0) {
        return false;
    }

    public boolean addStatusEffect(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
        return false;
    }

    public void addVelocityInternal(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
    }

    public void addVelocity(double p0, double p1, double p2) {
    }

    public void addVelocity(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d adjustMovementForCollisions(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p2, murat.simv2.simulation.mirror.net.minecraft.world.World p3, java.util.List p4) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d adjustMovementForPiston(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d adjustMovementForSneaking(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.entity.MovementType p1) {
        return null;
    }

    public void animateDamage(float p0) {
    }

    public static void applyBubbleColumnEffects(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, boolean p1) {
    }

    public static void applyBubbleColumnSurfaceEffects(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
    }

    public void applyDamage(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1, float p2) {
    }

    public void applyGravity() {
    }

    public float applyMirror(murat.simv2.simulation.mirror.net.minecraft.util.BlockMirror p0) {
        return 0.0F;
    }

    public void applyMovementEffects(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
    }

    public float applyRotation(murat.simv2.simulation.mirror.net.minecraft.util.BlockRotation p0) {
        return 0.0F;
    }

    public boolean areItemsDifferent(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return false;
    }

    public void attackLivingEntity(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
    }

    public void attemptTickInVoid() {
    }

    public void baseTick() {
    }

    public boolean bypassesLandingEffects() {
        return false;
    }

    public boolean bypassesSteppingEffects() {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box calculateBoundingBox() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box calculateDefaultBoundingBox(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return null;
    }

    public void calculateDimensions() {
    }

    public float calculateNextStepSoundDistance() {
        return 0.0F;
    }

    public boolean canActVoluntarily() {
        return false;
    }

    public boolean canAddPassenger(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public boolean canAvoidTraps() {
        return false;
    }

    public boolean canBeHitByProjectile() {
        return false;
    }

    public boolean canBeSpectated(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
        return false;
    }

    public boolean canBreatheInWater() {
        return false;
    }

    public boolean canDispenserEquipSlot(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
        return false;
    }

    public boolean canEquipFromDispenser(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return false;
    }

    public boolean canEquip(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p1) {
        return false;
    }

    public boolean canExplosionDestroyBlock(murat.simv2.simulation.mirror.net.minecraft.world.explosion.Explosion p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3, float p4) {
        return false;
    }

    public static boolean canGlideWith(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p1) {
        return false;
    }

    public boolean canGlide() {
        return false;
    }

    public boolean canHaveStatusEffect(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0) {
        return false;
    }

    public boolean canHit() {
        return false;
    }

    public boolean canModifyAt(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public boolean canMoveVoluntarily() {
        return false;
    }

    public boolean canPickUpLoot() {
        return false;
    }

    public boolean canSee(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public boolean canSee(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.ShapeType p1, murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.FluidHandling p2, double p3) {
        return false;
    }

    public boolean canSprintAsVehicle() {
        return false;
    }

    public boolean canStartRiding(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public boolean canTakeDamage() {
        return false;
    }

    public boolean canTarget(murat.simv2.simulation.mirror.net.minecraft.entity.EntityType p0) {
        return false;
    }

    public boolean canTarget(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
        return false;
    }

    public boolean canTeleportBetween(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1) {
        return false;
    }

    public boolean canUsePortals(boolean p0) {
        return false;
    }

    public boolean canUseSlot(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
        return false;
    }

    public static java.lang.Object castComponentValue(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, java.lang.Object p1) {
        return null;
    }

    public void changeLookDirection(double p0, double p1) {
    }

    public void checkDespawn() {
    }

    public void checkHandStackSwap(java.util.Map p0) {
    }

    public void checkWaterState() {
    }

    public float clampScale(float p0) {
        return 0.0F;
    }

    public void clearActiveItem() {
    }

    public void clearPotionSwirls() {
    }

    public void clearSleepingPosition() {
    }

    public boolean clearStatusEffects() {
        return false;
    }

    public boolean clientDamage(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0) {
        return false;
    }

    public boolean collidesWithStateAtPos(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1) {
        return false;
    }

    public boolean collidesWith(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public int computeFallDamage(double p0, float p1) {
        return 0;
    }

    public void consumeItem() {
    }

    public static boolean containsOnlyAmbientEffects(java.util.Collection p0) {
        return false;
    }

    public boolean copyComponentFrom(murat.simv2.simulation.mirror.net.minecraft.component.ComponentsAccess p0, murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p1) {
        return false;
    }

    public void copyComponentsFrom(murat.simv2.simulation.mirror.net.minecraft.component.ComponentsAccess p0) {
    }

    public void copyComponentsFrom(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public void copyFrom(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void copyPositionAndRotation(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public boolean couldAcceptPassenger() {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.brain.Brain.Profile createBrainProfile() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityEquipment createEquipment() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity createItemEntity(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, boolean p1, boolean p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.entity.attribute.DefaultAttributeContainer.Builder createLivingAttributes() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet createSpawnPacket(murat.simv2.simulation.mirror.net.minecraft.server.network.EntityTrackerEntry p0) {
        return null;
    }

    public void damageArmor(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1) {
    }

    public void damageHelmet(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1) {
    }

    public void defrost() {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.brain.Brain deserializeBrain(com.mojang.serialization.Dynamic p0) {
        return null;
    }

    public void detach() {
    }

    public void disableExperienceDropping() {
    }

    public void discard() {
    }

    public void dismountVehicle() {
    }

    public float distanceTo(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return 0.0F;
    }

    public boolean doesNotCollide(double p0, double p1, double p2) {
        return false;
    }

    public boolean doesRenderOnFire() {
        return false;
    }

    public void dropEquipment(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1, boolean p2) {
    }

    public void dropInventory(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity dropItem(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, boolean p1, boolean p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity dropItem(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemConvertible p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity dropItem(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemConvertible p1, int p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity dropStack(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity dropStack(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1, float p2) {
        return null;
    }

    public void emitGameEvent(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
    }

    public void emitGameEvent(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
    }

    public void endCombat() {
    }

    public void enterCombat() {
    }

    public void extinguishWithSound() {
    }

    public void extinguish() {
    }

    public boolean forEachGeneratedItem(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p1, java.util.function.Function p2, java.util.function.BiConsumer p3) {
        return false;
    }

    public boolean forEachGiftedItem(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p1, java.util.function.BiConsumer p2) {
        return false;
    }

    public void forEachShearedItem(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2, java.util.function.BiConsumer p3) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreHolder fromName(java.lang.String p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreHolder fromProfile(com.mojang.authlib.GameProfile p0) {
        return null;
    }

    public float getAbsorptionAmount() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.Hand getActiveHand() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getActiveItem() {
        return null;
    }

    public java.util.Map getActiveStatusEffects() {
        return null;
    }

    public int getAir() {
        return 0;
    }

    public float getArmorVisibility() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityAttachments getAttachments() {
        return null;
    }

    public double getAttackDistanceScalingFactor(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity getAttacker() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity getAttacking() {
        return null;
    }

    public double getAttributeBaseValue(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeInstance getAttributeInstance(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.attribute.AttributeContainer getAttributes() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions getBaseDimensions(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getBlockPos() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState getBlockStateAtPos() {
        return null;
    }

    public int getBlockX() {
        return 0;
    }

    public int getBlockY() {
        return 0;
    }

    public int getBlockZ() {
        return 0;
    }

    public double getBodyX(double p0) {
        return 0.0D;
    }

    public float getBodyYaw() {
        return 0.0F;
    }

    public double getBodyY(double p0) {
        return 0.0D;
    }

    public double getBodyZ(double p0) {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box getBoundingBox() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box getBoundingBox(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.brain.Brain getBrain() {
        return null;
    }

    public float getBrightnessAtEyes() {
        return 0.0F;
    }

    public int getBurningDuration() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getCameraPosVec(float p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos getChunkPos() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getClientCameraPosVec(float p0) {
        return null;
    }

    public java.util.Optional getClimbingPos() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource getCommandSource(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
        return null;
    }

    public java.util.Set getCommandTags() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity getControllingPassenger() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getControllingVehicle() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getCustomName() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSources getDamageSources() {
        return null;
    }

    public float getDamageTiltYaw() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageTracker getDamageTracker() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker getDataTracker() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent getDeathSound() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getDefaultName() {
        return null;
    }

    public int getDefaultPortalCooldown() {
        return 0;
    }

    public int getDespawnCounter() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getDisplayName() {
        return null;
    }

    public float getEffectFadeFactor(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, float p1) {
        return 0.0F;
    }

    public float getEffectiveExplosionResistance(murat.simv2.simulation.mirror.net.minecraft.world.explosion.Explosion p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3, murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p4, float p5) {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.World getEntityWorld() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry getEquipSound(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1, murat.simv2.simulation.mirror.net.minecraft.component.type.EquippableComponent p2) {
        return null;
    }

    public static byte getEquipmentBreakStatus(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
        return (byte) 0;
    }

    public java.util.Map getEquipmentChanges() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot getEquipmentSlot(int p0) {
        return null;
    }

    public int getExperienceToDrop(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
        return 0;
    }

    public float getEyeHeight(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose p0) {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getEyePos() {
        return null;
    }

    public double getEyeY() {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction getFacing() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity.FallSounds getFallSounds() {
        return null;
    }

    public double getFinalGravity() {
        return 0.0D;
    }

    public int getFireTicks() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getFirstPassenger() {
        return null;
    }

    public boolean getFlag(int p0) {
        return false;
    }

    public double getFluidHeight(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0) {
        return 0.0D;
    }

    public float getFreezingScale() {
        return 0.0F;
    }

    public int getFrozenTicks() {
        return 0;
    }

    public int getGlidingTicks() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getHandPosOffset(murat.simv2.simulation.mirror.net.minecraft.item.Item p0) {
        return null;
    }

    public int getHandSwingDuration() {
        return 0;
    }

    public float getHandSwingProgress(float p0) {
        return 0.0F;
    }

    public float getHeight() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent getHighSpeedSplashSound() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box getHitbox() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction getHorizontalFacing() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent getHoverEvent() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent getHurtSound(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0) {
        return null;
    }

    public int getId() {
        return 0;
    }

    public int getItemUseTimeLeft() {
        return 0;
    }

    public int getItemUseTime() {
        return 0;
    }

    public float getJumpVelocityMultiplier() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState getLandingBlockState() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getLandingPos() {
        return null;
    }

    public int getLastAttackTime() {
        return 0;
    }

    public int getLastAttackedTime() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity getLastAttacker() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getLastRenderPos() {
        return null;
    }

    public float getLeaningPitch(float p0) {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getLeashOffset() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getLeashOffset(float p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getLeashPos(float p0) {
        return null;
    }

    public float getLerpedPitch(float p0) {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getLerpedPos(float p0) {
        return null;
    }

    public float getLerpedYaw(float p0) {
        return 0.0F;
    }

    public java.util.Map getLocationBasedEnchantmentEffects(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
        return null;
    }

    public java.util.Optional getLootTableKey() {
        return null;
    }

    public long getLootTableSeed() {
        return 0L;
    }

    public float getLuck() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.Arm getMainArm() {
        return null;
    }

    public int getMaxAir() {
        return 0;
    }

    public float getMaxRelativeHeadRotation() {
        return 0.0F;
    }

    public int getMinFreezeDamageTicks() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity.MoveEffect getMoveEffect() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction getMovementDirection() {
        return null;
    }

    public float getMovementSpeed() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getMovement() {
        return null;
    }

    public java.lang.String getNameForScoreboard() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getName() {
        return null;
    }

    public int getNextAirOnLand(int p0) {
        return 0;
    }

    public int getNextAirUnderwater(int p0) {
        return 0;
    }

    public float getOffGroundSpeed() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getOffHandStack() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getOppositeRotationVector(float p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getOppositeRotationVector(float p0, float p1) {
        return null;
    }

    public java.lang.Object getOrDefault(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, java.lang.Object p1) {
        return null;
    }

    public double getParticleX(double p0) {
        return 0.0D;
    }

    public double getParticleZ(double p0) {
        return 0.0D;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getPassengerAttachmentPos(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1, murat.simv2.simulation.mirror.net.minecraft.entity.EntityAttachments p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getPassengerAttachmentPos(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions p1, float p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getPassengerDismountOffset(double p0, double p1, float p2) {
        return null;
    }

    public java.util.List getPassengerList() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getPassengerRidingPos(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return null;
    }

    public java.lang.Iterable getPassengersDeep() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getPickBlockStack() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.piston.PistonBehavior getPistonBehavior() {
        return null;
    }

    public float getPitch() {
        return 0.0F;
    }

    public float getPitch(float p0) {
        return 0.0F;
    }

    public int getPlayerHitTimer() {
        return 0;
    }

    public int getPlayerPassengers() {
        return 0;
    }

    public int getPortalCooldown() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getPosWithYOffset(float p0) {
        return null;
    }

    public com.google.common.collect.ImmutableList getPoses() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose getPose() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getPos() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot getPreferredEquipmentSlot(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ProjectileDeflection getProjectileDeflection(murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getProjectileType(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return null;
    }

    public double getRandomBodyY() {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random getRandom() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource getRecentDamageSource() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager getRegistryManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason getRemovalReason() {
        return null;
    }

    public static double getRenderDistanceMultiplier() {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getRootVehicle() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f getRotationClient() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getRotationVecClient() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getRotationVector() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getRotationVector(float p0, float p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getRotationVec(float p0) {
        return null;
    }

    public int getSafeFallDistance() {
        return 0;
    }

    public int getSafeFallDistance(float p0) {
        return 0;
    }

    public java.lang.String getSavedEntityId() {
        return null;
    }

    public float getScaleFactor() {
        return 0.0F;
    }

    public float getScale() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.Team getScoreboardTeam() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.MinecraftServer getServer() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction getSleepingDirection() {
        return null;
    }

    public java.util.Optional getSleepingPosition() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot getSlotForHand(murat.simv2.simulation.mirror.net.minecraft.util.Hand p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory getSoundCategory() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent getSplashSound() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getStackInArm(murat.simv2.simulation.mirror.net.minecraft.util.Arm p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getStackInHand(murat.simv2.simulation.mirror.net.minecraft.util.Hand p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.inventory.StackReference getStackReference(int p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.inventory.StackReference getStackReference(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p1) {
        return null;
    }

    public float getStandingEyeHeight() {
        return 0.0F;
    }

    public java.util.Collection getStatusEffects() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getStepSoundPos(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState getSteppingBlockState() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getSteppingPos() {
        return null;
    }

    public int getStingerCount() {
        return 0;
    }

    public int getStuckArrowCount() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getStyledDisplayName() {
        return null;
    }

    public double getSwimHeight() {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent getSwimSound() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getSyncedPos() {
        return null;
    }

    public float getTargetingMargin() {
        return 0.0F;
    }

    public int getTeamColorValue() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.TrackedPosition getTrackedPosition() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.component.Component getTyped(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityType getType() {
        return null;
    }

    public java.lang.String getUuidAsString() {
        return null;
    }

    public java.util.UUID getUuid() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getVehicleAttachmentPos(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getVehicle() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getVelocityAffectingPos() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getVelocity() {
        return null;
    }

    public float getWeaponDisableBlockingForSeconds() {
        return 0.0F;
    }

    public float getWidth() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getWorldSpawnPos(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.World getWorld() {
        return null;
    }

    public double getX() {
        return 0.0D;
    }

    public float getYaw() {
        return 0.0F;
    }

    public float getYaw(float p0) {
        return 0.0F;
    }

    public double getY() {
        return 0.0D;
    }

    public double getZ() {
        return 0.0D;
    }

    public java.lang.Object get(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0) {
        return null;
    }

    public void giveOrDropStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public boolean handleAttack(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public void handleFallDamageForPassengers(double p0, float p1, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p2) {
    }

    public boolean handleFallDamage(double p0, float p1, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p2) {
        return false;
    }

    public void handleFall(double p0, double p1, double p2, boolean p3) {
    }

    public void handleStatus(byte p0) {
    }

    public boolean hasCollidedSoftly(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return false;
    }

    public boolean hasControllingPassenger() {
        return false;
    }

    public boolean hasCustomName() {
        return false;
    }

    public boolean hasInvertedHealingAndHarm() {
        return false;
    }

    public boolean hasLandedInFluid() {
        return false;
    }

    public boolean hasNoGravity() {
        return false;
    }

    public boolean hasPassengerDeep(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public boolean hasPassengers() {
        return false;
    }

    public boolean hasPassenger(java.util.function.Predicate p0) {
        return false;
    }

    public boolean hasPassenger(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public boolean hasPlayerRider() {
        return false;
    }

    public boolean hasPortalCooldown() {
        return false;
    }

    public boolean hasStackEquipped(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
        return false;
    }

    public boolean hasVehicle() {
        return false;
    }

    public void heal(float p0) {
    }

    public void igniteByLava() {
    }

    public void initDataTracker(murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.Builder p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult interactAt(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.Hand p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult interact(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.Hand p1) {
        return null;
    }

    public boolean isAffectedBySplashPotions() {
        return false;
    }

    public boolean isAlwaysInvulnerableTo(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0) {
        return false;
    }

    public boolean isArmorSlot(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
        return false;
    }

    public boolean isAttackable() {
        return false;
    }

    public boolean isBeingRainedOn() {
        return false;
    }

    public boolean isBlocking() {
        return false;
    }

    public boolean isCollidable() {
        return false;
    }

    public boolean isConnectedThroughVehicle(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public boolean isControlledByMainPlayer() {
        return false;
    }

    public boolean isControlledByPlayer() {
        return false;
    }

    public boolean isCrawling() {
        return false;
    }

    public boolean isCustomNameVisible() {
        return false;
    }

    public boolean isDescending() {
        return false;
    }

    public boolean isEntityLookingAtMe(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, double p1, boolean p2, boolean p3, double[] p4) {
        return false;
    }

    public boolean isFireImmune() {
        return false;
    }

    public boolean isFlappingWings() {
        return false;
    }

    public boolean isFrozen() {
        return false;
    }

    public boolean isGlowingLocal() {
        return false;
    }

    public boolean isGlowing() {
        return false;
    }

    public boolean isHolding(java.util.function.Predicate p0) {
        return false;
    }

    public boolean isHolding(murat.simv2.simulation.mirror.net.minecraft.item.Item p0) {
        return false;
    }

    public boolean isImmuneToExplosion(murat.simv2.simulation.mirror.net.minecraft.world.explosion.Explosion p0) {
        return false;
    }

    public boolean isInCreativeMode() {
        return false;
    }

    public boolean isInFluid() {
        return false;
    }

    public boolean isInLava() {
        return false;
    }

    public boolean isInPose(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose p0) {
        return false;
    }

    public boolean isInRange(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, double p1) {
        return false;
    }

    public boolean isInRange(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, double p1, double p2) {
        return false;
    }

    public boolean isInSameTeam(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public boolean isInSneakingPose() {
        return false;
    }

    public boolean isInsideWall() {
        return false;
    }

    public boolean isInterpolating() {
        return false;
    }

    public boolean isInvisibleTo(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
        return false;
    }

    public boolean isInvisible() {
        return false;
    }

    public boolean isInvulnerable() {
        return false;
    }

    public boolean isLiving() {
        return false;
    }

    public boolean isLogicalSideForUpdatingMovement() {
        return false;
    }

    public boolean isMobOrPlayer() {
        return false;
    }

    public boolean isOnFire() {
        return false;
    }

    public boolean isOnGround() {
        return false;
    }

    public boolean isOnRail() {
        return false;
    }

    public boolean isPartOfGame() {
        return false;
    }

    public boolean isPartOf(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public boolean isPlayer() {
        return false;
    }

    public boolean isPushable() {
        return false;
    }

    public boolean isPushedByFluids() {
        return false;
    }

    public boolean isRegionUnloaded() {
        return false;
    }

    public boolean isRemoved() {
        return false;
    }

    public boolean isSilent() {
        return false;
    }

    public boolean isSleepingInBed() {
        return false;
    }

    public boolean isSneaking() {
        return false;
    }

    public boolean isSneaky() {
        return false;
    }

    public boolean isSpectator() {
        return false;
    }

    public boolean isSprinting() {
        return false;
    }

    public boolean isSubmergedInWater() {
        return false;
    }

    public boolean isSubmergedIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0) {
        return false;
    }

    public boolean isSupportedBy(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean isSwimming() {
        return false;
    }

    public boolean isTeamPlayer(murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam p0) {
        return false;
    }

    public boolean isTeammate(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public boolean isTouchingWaterOrRain() {
        return false;
    }

    public boolean isTouchingWater() {
        return false;
    }

    public boolean isUsingItem() {
        return false;
    }

    public void kill(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public void knockback(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
    }

    public void lerpPosAndRotation(int p0, double p1, double p2, double p3, double p4, double p5) {
    }

    public float lerpYaw(float p0) {
        return 0.0F;
    }

    public void limitFallDistance() {
    }

    public void lookAt(murat.simv2.simulation.mirror.net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    public void markEffectsDirty() {
    }

    public static void method_18393(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
    }

    public void method_18404(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public java.lang.Boolean method_18405(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d method_20477(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public void method_24311(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0) {
    }

    public static boolean method_24519(murat.simv2.simulation.mirror.net.minecraft.item.Item p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return false;
    }

    public static void method_28305(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, murat.simv2.simulation.mirror.net.minecraft.nbt.NbtElement p1) {
    }

    public static boolean method_32323(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2) {
        return false;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d method_61418(double p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
        return null;
    }

    public static void method_61421(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.AttributeContainer p0, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p1, murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier p2) {
    }

    public void method_61423(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier p1) {
    }

    public static it.unimi.dsi.fastutil.objects.Reference2ObjectMap method_61427(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
        return null;
    }

    public boolean method_63629(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext method_64171(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext.Builder p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext method_64172(murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext.Builder p0) {
        return null;
    }

    public static void method_64448(java.util.function.BiConsumer p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2) {
    }

    public void method_64449(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
    }

    public static boolean method_64620(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
        return false;
    }

    public void method_66668(java.util.List p0, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.item.ItemStack method_66670(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
        return null;
    }

    public void method_67650(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public void method_68264(java.lang.String p0) {
    }

    public void method_68265(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movementInputToVelocity(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, float p1, float p2) {
        return null;
    }

    public void move(murat.simv2.simulation.mirror.net.minecraft.entity.MovementType p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    public boolean occludeVibrationSignals() {
        return false;
    }

    public void onAttacking(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void onBlockCollision(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
    }

    public void onBubbleColumnCollision(boolean p0) {
    }

    public void onBubbleColumnSurfaceCollision(boolean p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
    }

    public void onDamaged(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0) {
    }

    public void onDataTrackerUpdate(java.util.List p0) {
    }

    public void onDismounted(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void onEquipmentRemoved(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p1, murat.simv2.simulation.mirror.net.minecraft.entity.attribute.AttributeContainer p2) {
    }

    public void onExplodedBy(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public boolean onKilledOther(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1) {
        return false;
    }

    public void onLanding() {
    }

    public void onPassengerLookAround(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void onPlayerCollision(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
    }

    public void onRemoval(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p1) {
    }

    public void onRemoved() {
    }

    public void onRemove(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p0) {
    }

    public void onSpawnPacket(murat.simv2.simulation.mirror.net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket p0) {
    }

    public void onStartedTrackingBy(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
    }

    public void onStatusEffectApplied(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
    }

    public void onStatusEffectUpgraded(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p2) {
    }

    public void onStatusEffectsRemoved(java.util.Collection p0) {
    }

    public void onStoppedTrackingBy(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
    }

    public void onStruckByLightning(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LightningEntity p1) {
    }

    public void onSwimmingStart() {
    }

    public void onTrackedDataSet(murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData p0) {
    }

    public void playBlockFallSound() {
    }

    public void playCombinationStepSounds(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1) {
    }

    public void playEquipmentBreakEffects(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public void playExtinguishSound() {
    }

    public void playSecondaryStepSound(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
    }

    public void playSoundIfNotSilent(murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p0) {
    }

    public void playSound(murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p0, float p1, float p2) {
    }

    public void playStepSound(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1) {
    }

    public void playSwimSound() {
    }

    public void playSwimSound(float p0) {
    }

    public void popQueuedCollisionCheck() {
    }

    public void populateCrashReport(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d positionInPortal(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockLocating.Rectangle p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d positionInPortal(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return null;
    }

    public void pushAwayFrom(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void pushAway(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void pushOutOfBlocks(double p0, double p1, double p2) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.hit.HitResult raycast(double p0, float p1, boolean p2) {
        return null;
    }

    public void readCustomDataFromNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    public void readNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    public boolean recalculateDimensions(murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions p0) {
        return false;
    }

    public void refreshPositionAfterTeleport(double p0, double p1, double p2) {
    }

    public void refreshPositionAfterTeleport(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
    }

    public void refreshPositionAndAngles(double p0, double p1, double p2, float p3, float p4) {
    }

    public void refreshPositionAndAngles(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, float p1, float p2) {
    }

    public void refreshPositionAndAngles(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, float p1, float p2) {
    }

    public void refreshPosition() {
    }

    public void reinitDimensions() {
    }

    public void removeAllPassengers() {
    }

    public boolean removeCommandTag(java.lang.String p0) {
        return false;
    }

    public void removeFromDimension() {
    }

    public void removePassenger(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void removePowderSnowSlow() {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance removeStatusEffectInternal(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return null;
    }

    public boolean removeStatusEffect(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return false;
    }

    public void remove(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p0) {
    }

    public void requestTeleportAndDismount(double p0, double p1, double p2) {
    }

    public void requestTeleportOffset(double p0, double p1, double p2) {
    }

    public void requestTeleport(double p0, double p1, double p2) {
    }

    public void resetPortalCooldown() {
    }

    public void resetPosition() {
    }

    public void rotate(float p0, float p1) {
    }

    public boolean saveNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
        return false;
    }

    public boolean saveSelfNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
        return false;
    }

    public void scheduleVelocityUpdate() {
    }

    public void sendEffectToControllingPlayer(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0) {
    }

    public void sendEquipmentBreakStatus(murat.simv2.simulation.mirror.net.minecraft.item.Item p0, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p1) {
    }

    public void sendEquipmentChanges() {
    }

    public void sendEquipmentChanges(java.util.Map p0) {
    }

    public void sendPickup(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, int p1) {
    }

    public void serverDamage(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1) {
    }

    public void setAbsorptionAmountUnclamped(float p0) {
    }

    public void setAir(int p0) {
    }

    public void setAngles(float p0, float p1) {
    }

    public boolean setApplicableComponent(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, java.lang.Object p1) {
        return false;
    }

    public void setBodyYaw(float p0) {
    }

    public void setBoundingBox(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0) {
    }

    public void setChangeListener(murat.simv2.simulation.mirror.net.minecraft.world.entity.EntityChangeListener p0) {
    }

    public void setComponent(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, java.lang.Object p1) {
    }

    public void setCurrentHand(murat.simv2.simulation.mirror.net.minecraft.util.Hand p0) {
    }

    public void setCustomNameVisible(boolean p0) {
    }

    public void setCustomName(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public void setDespawnCounter(int p0) {
    }

    public void setFireTicks(int p0) {
    }

    public void setFlag(int p0, boolean p1) {
    }

    public void setFrozenTicks(int p0) {
    }

    public void setGlowing(boolean p0) {
    }

    public void setHeadYaw(float p0) {
    }

    public void setId(int p0) {
    }

    public void setInPowderSnow(boolean p0) {
    }

    public void setInvisible(boolean p0) {
    }

    public void setInvulnerable(boolean p0) {
    }

    public void setJumping(boolean p0) {
    }

    public void setLastPositionAndAngles(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, float p1, float p2) {
    }

    public void setLivingFlag(int p0, boolean p1) {
    }

    public void setMovementSpeed(float p0) {
    }

    public void setMovement(boolean p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2) {
    }

    public void setMovement(boolean p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    public void setNearbySongPlaying(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, boolean p1) {
    }

    public void setNoDrag(boolean p0) {
    }

    public void setNoGravity(boolean p0) {
    }

    public void setOnFireForTicks(int p0) {
    }

    public void setOnFireFor(float p0) {
    }

    public void setOnFireFromLava() {
    }

    public void setOnFire(boolean p0) {
    }

    public void setOnGround(boolean p0) {
    }

    public void setPitch(float p0) {
    }

    public void setPortalCooldown(int p0) {
    }

    public void setPose(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose p0) {
    }

    public void setPositionInBed(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public void setPosition(double p0, double p1, double p2) {
    }

    public void setPosition(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerPosition p0, java.util.Set p1) {
    }

    public void setPosition(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
    }

    public void setPos(double p0, double p1, double p2) {
    }

    public void setRemoved(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p0) {
    }

    public static void setRenderDistanceMultiplier(double p0) {
    }

    public void setRotation(float p0, float p1) {
    }

    public void setSilent(boolean p0) {
    }

    public void setSleepingPosition(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public void setSneaking(boolean p0) {
    }

    public void setSprinting(boolean p0) {
    }

    public void setStatusEffect(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
    }

    public void setStingerCount(int p0) {
    }

    public void setStuckArrowCount(int p0) {
    }

    public void setSwimming(boolean p0) {
    }

    public void setUuid(java.util.UUID p0) {
    }

    public void setVelocityClient(double p0, double p1, double p2) {
    }

    public void setVelocity(double p0, double p1, double p2) {
    }

    public void setVelocity(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
    }

    public void setWorld(murat.simv2.simulation.mirror.net.minecraft.world.World p0) {
    }

    public void setYaw(float p0) {
    }

    public boolean shouldAlwaysDropExperience() {
        return false;
    }

    public boolean shouldControlVehicles() {
        return false;
    }

    public boolean shouldDismountUnderwater() {
        return false;
    }

    public boolean shouldEscapePowderSnow() {
        return false;
    }

    public boolean shouldPlayBurnSoundInLava() {
        return false;
    }

    public boolean shouldRenderName() {
        return false;
    }

    public boolean shouldRender(double p0) {
        return false;
    }

    public boolean shouldRender(double p0, double p1, double p2) {
        return false;
    }

    public boolean shouldSave() {
        return false;
    }

    public boolean shouldSetPositionOnLoad() {
        return false;
    }

    public boolean shouldSpawnSprintingParticles() {
        return false;
    }

    public boolean shouldSwimInFluids() {
        return false;
    }

    public boolean shouldTickBlockCollision() {
        return false;
    }

    public boolean sidedDamage(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1) {
        return false;
    }

    public void sleep(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public void slowMovement(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    public static void spawnBubbleColumnParticles(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
    }

    public void spawnItemParticles(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, int p1) {
    }

    public void spawnSprintingParticles() {
    }

    public double squaredDistanceTo(double p0, double p1, double p2) {
        return 0.0D;
    }

    public double squaredDistanceTo(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return 0.0D;
    }

    public double squaredDistanceTo(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return 0.0D;
    }

    public boolean startRiding(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public boolean startRiding(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, boolean p1) {
        return false;
    }

    public void stopGliding() {
    }

    public void stopRiding() {
    }

    public void stopUsingItem() {
    }

    public java.util.stream.Stream streamPassengersAndSelf() {
        return null;
    }

    public java.util.stream.Stream streamSelfAndPassengers() {
        return null;
    }

    public void swapHandStacks() {
    }

    public void swingHand(murat.simv2.simulation.mirror.net.minecraft.util.Hand p0) {
    }

    public void swingHand(murat.simv2.simulation.mirror.net.minecraft.util.Hand p0, boolean p1) {
    }

    public void takeShieldHit(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity teleportTo(murat.simv2.simulation.mirror.net.minecraft.world.TeleportTarget p0) {
        return null;
    }

    public boolean teleport(double p0, double p1, double p2, boolean p3) {
        return false;
    }

    public boolean teleport(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, double p1, double p2, double p3, java.util.Set p4, float p5, float p6, boolean p7) {
        return false;
    }

    public void tickActiveItemStack() {
    }

    public void tickBlockCollision() {
    }

    public void tickBlockCollision(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    public void tickControlled(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    public void tickHandSwing() {
    }

    public void tickInVoid() {
    }

    public void tickItemStackUsage(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public void tickNewAi() {
    }

    public void tickPortalCooldown() {
    }

    public void tickPortalTeleportation() {
    }

    public void tickRiding() {
    }

    public void tickStatusEffects() {
    }

    public void tick() {
    }

    public void tiltScreen(double p0, double p1) {
    }

    public void triggerItemPickedUpByEntityCriteria(murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity p0) {
    }

    public boolean tryAttack(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
        return false;
    }

    public void tryUsePortal(murat.simv2.simulation.mirror.net.minecraft.block.Portal p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
    }

    public void turnHead(float p0) {
    }

    public void unsetRemoved() {
    }

    public void updateAttributes() {
    }

    public void updateAttribute(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
    }

    public void updateEventHandler(java.util.function.BiConsumer p0) {
    }

    public void updateGlowing() {
    }

    public void updateKilledAdvancementCriterion(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1) {
    }

    public void updateLastAngles() {
    }

    public void updateLastPosition() {
    }

    public void updateLeaningPitch() {
    }

    public boolean updateMovementInFluid(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0, double p1) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d updatePassengerForDismount(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
        return null;
    }

    public void updatePassengerPosition(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void updatePassengerPosition(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity.PositionUpdater p1) {
    }

    public void updatePositionAndAngles(double p0, double p1, double p2, float p3, float p4) {
    }

    public void updatePosition(double p0, double p1, double p2) {
    }

    public void updatePostDeath() {
    }

    public void updatePotionSwirls() {
    }

    public void updatePotionVisibility() {
    }

    public void updateSupportingBlockPos(boolean p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    public void updateSwimming() {
    }

    public void updateTrackedHeadRotation(float p0, int p1) {
    }

    public void updateTrackedPositionAndAngles(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, float p1, float p2) {
    }

    public void updateTrackedPosition(double p0, double p1, double p2) {
    }

    public void updateVelocity(float p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    public boolean updateWaterState() {
        return false;
    }

    public boolean wouldNotSuffocateInPose(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose p0) {
        return false;
    }

    public void writeCustomDataToNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound writeNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
        return null;
    }

    // END GENERATED PRIMARY CONTRACT STUBS

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
