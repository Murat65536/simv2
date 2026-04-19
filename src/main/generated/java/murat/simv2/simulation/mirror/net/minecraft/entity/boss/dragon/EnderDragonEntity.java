package murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon;

// Generated mirror stub for simulation closure.
public class EnderDragonEntity extends murat.simv2.simulation.mirror.net.minecraft.entity.mob.MobEntity implements murat.simv2.simulation.mirror.net.minecraft.entity.mob.Monster {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.lang.String ATTRIBUTES_NBT_KEY;
    public static float BABY_SCALE_FACTOR;
    public static float BASE_ENCHANTED_ARMOR_CHANCE;
    public static float BASE_ENCHANTED_MAIN_HAND_EQUIPMENT_CHANCE;
    public static float BASE_SPAWN_EQUIPMENT_CHANCE;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.ai.TargetPredicate CLOSE_PLAYER_PREDICATE;
    public static int DEATH_TICKS;
    public static float DEFAULT_CAN_PICKUP_LOOT_CHANCE;
    public static float DEFAULT_FRICTION;
    public static int DEFAULT_MIN_FREEZE_DAMAGE_TICKS;
    public static int DEFAULT_PORTAL_COOLDOWN;
    public static int DEFAULT_TICKS_SINCE_DEATH;
    public static java.lang.String DRAGON_DEATH_TIME_KEY;
    public static java.lang.String DRAGON_PHASE_KEY;
    public static int EQUIPMENT_SLOT_ID;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<java.lang.Byte> FLAGS;
    public static int FREEZING_DAMAGE_INTERVAL;
    public static int GLIDING_FLAG_INDEX;
    public static int GLOWING_FLAG;
    public static int GLOWING_FLAG_INDEX;
    public static double GRAVITY;
    public static java.lang.String ID_KEY;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<java.lang.Byte> LIVING_FLAGS;
    public static org.slf4j.Logger LOGGER;
    public static int MAX_COMMAND_TAGS;
    public static int MAX_HEALTH;
    public static int MAX_RIDING_COOLDOWN;
    public static int MINIMUM_DROPPED_EXPERIENCE_PER_EQUIPMENT;
    public static float MIN_RISING_BUBBLE_COLUMN_SPEED;
    public static java.util.function.Predicate<murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity> NOT_WEARING_GAZE_DISGUISE_PREDICATE;
    public static int OFF_HAND_ACTIVE_FLAG;
    public static int ON_FIRE_FLAG_INDEX;
    public static java.lang.String PASSENGERS_KEY;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<java.lang.Integer> PHASE_TYPE;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose> POSE;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Identifier RANDOM_SPAWN_BONUS_MODIFIER_ID;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions SLEEPING_DIMENSIONS;
    public static float TAKEOFF_THRESHOLD;
    public static int USING_ITEM_FLAG;
    public static int USING_RIPTIDE_FLAG;
    public static java.lang.String UUID_KEY;
    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack activeItemStack;
    public int age;
    public int ambientSoundChance;
    public murat.simv2.simulation.mirror.net.minecraft.entity.LazyEntityReference<murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity> attackingPlayer;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos;
    public murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonPart body;
    public float bodyYaw;
    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.brain.Brain<?> brain;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos chunkPos;
    public java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos> climbingPos;
    public boolean collidedSoftly;
    public java.lang.Object connectedCrystal;
    public float damageDuringSitting;
    public murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker dataTracker;
    public boolean dead;
    public int deathTime;
    public int defaultMaxHealth;
    public int despawnCounter;
    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions dimensions;
    public float distanceTraveled;
    public murat.simv2.simulation.mirror.net.minecraft.entity.mob.ElytraFlightController elytraFlightController;
    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityEquipment equipment;
    public int experiencePoints;
    public double fallDistance;
    public static int field_30072;
    public static int field_30074;
    public static double field_30075;
    public static int field_30429;
    public static int field_35039;
    public static float field_44870;
    public static double field_44871;
    public static double field_44872;
    public static float field_44874;
    public static float field_47756;
    public static int field_48827;
    public static int field_49073;
    public static int field_49791;
    public static int field_55952;
    public static float field_56256;
    public murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonFight fight;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos fightOrigin;
    public boolean firstUpdate;
    public it.unimi.dsi.fastutil.objects.Object2DoubleMap<murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey<murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid>> fluidHeight;
    public boolean forceUpdateSupportingBlockPos;
    public float forwardSpeed;
    public java.lang.Object frameTracker;
    public int glidingTicks;
    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.goal.GoalSelector goalSelector;
    public boolean groundCollision;
    public float handSwingProgress;
    public int handSwingTicks;
    public boolean handSwinging;
    public murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonPart head;
    public int headTrackingIncrements;
    public float headYaw;
    public boolean horizontalCollision;
    public int hurtTime;
    public boolean inPowderSnow;
    public murat.simv2.simulation.mirror.net.minecraft.entity.PositionInterpolator interpolator;
    public boolean intersectionChecked;
    public int itemUseTimeLeft;
    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.control.JumpControl jumpControl;
    public boolean jumping;
    public int jumpingCooldown;
    public int lastAttackedTicks;
    public float lastBodyYaw;
    public float lastDamageTaken;
    public float lastHandSwingProgress;
    public float lastHeadYaw;
    public float lastPitch;
    public double lastRenderX;
    public double lastRenderY;
    public double lastRenderZ;
    public float lastWingPosition;
    public double lastX;
    public double lastY;
    public float lastYaw;
    public double lastZ;
    public murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonPart leftWing;
    public murat.simv2.simulation.mirror.net.minecraft.entity.LimbAnimator limbAnimator;
    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.control.LookControl lookControl;
    public int maxHurtTime;
    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.control.MoveControl moveControl;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movementMultiplier;
    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.pathing.EntityNavigation navigation;
    public murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonPart neck;
    public boolean noClip;
    public murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonPart[] parts;
    public java.lang.Object pathHeap;
    public int[] pathNodeConnections;
    public java.lang.Object[] pathNodes;
    public java.lang.Object phaseManager;
    public long pistonMovementTick;
    public int playerHitTimer;
    public murat.simv2.simulation.mirror.net.minecraft.world.dimension.PortalManager portalManager;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d pos;
    public murat.simv2.simulation.mirror.net.minecraft.util.Hand preferredHand;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random random;
    public int ridingCooldown;
    public murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonPart rightWing;
    public float riptideAttackDamage;
    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack riptideStack;
    public int riptideTicks;
    public double serverHeadYaw;
    public float sidewaysSpeed;
    public boolean slowedDownByBlock;
    public float speed;
    public float standingEyeHeight;
    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState stateAtPos;
    public int stuckArrowTimer;
    public int stuckStingerTimer;
    public boolean submergedInWater;
    public java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos> supportingBlockPos;
    public murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonPart tail1;
    public murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonPart tail2;
    public murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonPart tail3;
    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.goal.GoalSelector targetSelector;
    public int ticksSinceDeath;
    public int ticksUntilNextGrowl;
    public int timeUntilRegen;
    public boolean touchingWater;
    public float upwardSpeed;
    public java.util.UUID uuid;
    public java.lang.String uuidString;
    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity vehicle;
    public boolean velocityDirty;
    public boolean velocityModified;
    public boolean verticalCollision;
    public boolean wasInPowderSnow;
    public float wingPosition;
    public float yawAcceleration;

    public EnderDragonEntity(murat.simv2.simulation.mirror.net.minecraft.entity.EntityType p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1) {
    }

    public void addAirTravelEffects() {
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

    public void addPowderSnowSlowIfNeeded() {
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

    public float applyArmorToDamage(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1) {
        return 0.0F;
    }

    public static void applyBubbleColumnEffects(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, boolean p1) {
    }

    public static void applyBubbleColumnSurfaceEffects(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
    }

    public void applyDamage(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1, float p2) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d applyFluidMovingSpeed(double p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2) {
        return null;
    }

    public void applyGravity() {
    }

    public void applyLeashElasticity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, float p1) {
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

    public void attachLeash(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, boolean p1) {
    }

    public void attackLivingEntity(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
    }

    public void attemptTickInVoid() {
    }

    public void baseTick() {
    }

    public void becomeAngry(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0) {
    }

    public boolean beforeLeashTick(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, float p1) {
        return false;
    }

    public void breakLongLeash() {
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

    public boolean canBeLeashed() {
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

    public boolean canFreeze() {
        return false;
    }

    public boolean canGather(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
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

    public boolean canImmediatelyDespawn(double p0) {
        return false;
    }

    public boolean canLeashAttachTo() {
        return false;
    }

    public static boolean canMobSpawn(murat.simv2.simulation.mirror.net.minecraft.entity.EntityType p0, murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p1, murat.simv2.simulation.mirror.net.minecraft.entity.SpawnReason p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p4) {
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

    public boolean canPickupItem(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return false;
    }

    public boolean canSee(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public boolean canSee(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.ShapeType p1, murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.FluidHandling p2, double p3) {
        return false;
    }

    public boolean canSpawn(murat.simv2.simulation.mirror.net.minecraft.world.WorldAccess p0, murat.simv2.simulation.mirror.net.minecraft.entity.SpawnReason p1) {
        return false;
    }

    public boolean canSpawn(murat.simv2.simulation.mirror.net.minecraft.world.WorldView p0) {
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

    public boolean canUseRangedWeapon(murat.simv2.simulation.mirror.net.minecraft.item.RangedWeaponItem p0) {
        return false;
    }

    public boolean canUseSlot(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
        return false;
    }

    public boolean canWalkOnFluid(murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p0) {
        return false;
    }

    public boolean cannotDespawn() {
        return false;
    }

    public static java.lang.Object castComponentValue(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, java.lang.Object p1) {
        return null;
    }

    public void changeLookDirection(double p0, double p1) {
    }

    public void checkDespawn() {
    }

    public void checkWaterState() {
    }

    public void clampHeadYaw() {
    }

    public float clampScale(float p0) {
        return 0.0F;
    }

    public void clearActiveItem() {
    }

    public void clearGoalsAndTasks() {
    }

    public void clearGoals(java.util.function.Predicate p0) {
    }

    public void clearPositionTarget() {
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

    public murat.simv2.simulation.mirror.net.minecraft.entity.mob.MobEntity convertTo(murat.simv2.simulation.mirror.net.minecraft.entity.EntityType p0, murat.simv2.simulation.mirror.net.minecraft.entity.conversion.EntityConversionContext p1, murat.simv2.simulation.mirror.net.minecraft.entity.SpawnReason p2, murat.simv2.simulation.mirror.net.minecraft.entity.conversion.EntityConversionContext.Finalizer p3) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.mob.MobEntity convertTo(murat.simv2.simulation.mirror.net.minecraft.entity.EntityType p0, murat.simv2.simulation.mirror.net.minecraft.entity.conversion.EntityConversionContext p1, murat.simv2.simulation.mirror.net.minecraft.entity.conversion.EntityConversionContext.Finalizer p2) {
        return null;
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

    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.control.BodyControl createBodyControl() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.brain.Brain.Profile createBrainProfile() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.entity.attribute.DefaultAttributeContainer.Builder createEnderDragonAttributes() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.inventory.Inventory createEquipmentInventory(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityEquipment createEquipment() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.entity.attribute.DefaultAttributeContainer.Builder createLivingAttributes() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.entity.attribute.DefaultAttributeContainer.Builder createMobAttributes() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.pathing.EntityNavigation createNavigation(murat.simv2.simulation.mirror.net.minecraft.world.World p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet createSpawnPacket(murat.simv2.simulation.mirror.net.minecraft.server.network.EntityTrackerEntry p0) {
        return null;
    }

    public void crystalDestroyed(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p3) {
    }

    public void damageArmor(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1) {
    }

    public void damageEquipment(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot[] p2) {
    }

    public void damageHelmet(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1) {
    }

    public void damageLivingEntities(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, java.util.List p1) {
    }

    public boolean damagePart(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonPart p1, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p2, float p3) {
        return false;
    }

    public boolean damage(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1, float p2) {
        return false;
    }

    public void defrost() {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.brain.Brain deserializeBrain(com.mojang.serialization.Dynamic p0) {
        return null;
    }

    public boolean destroyBlocks(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1) {
        return false;
    }

    public void detachLeashWithoutDrop() {
    }

    public void detachLeash() {
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

    public void dropAllForeignEquipment(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public void dropEquipment(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1, boolean p2) {
    }

    public void dropExperience(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
    }

    public java.util.Set dropForeignEquipment(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, java.util.function.Predicate p1) {
        return null;
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

    public void dropLoot(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1, boolean p2) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity dropStack(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity dropStack(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1, float p2) {
        return null;
    }

    public void drop(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1) {
    }

    public void emitGameEvent(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
    }

    public void emitGameEvent(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
    }

    public void enchantEquipment(murat.simv2.simulation.mirror.net.minecraft.world.ServerWorldAccess p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p2, murat.simv2.simulation.mirror.net.minecraft.world.LocalDifficulty p3) {
    }

    public void enchantMainHandItem(murat.simv2.simulation.mirror.net.minecraft.world.ServerWorldAccess p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1, murat.simv2.simulation.mirror.net.minecraft.world.LocalDifficulty p2) {
    }

    public void endCombat() {
    }

    public void enterCombat() {
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public void equipBodyArmor(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public void equipLootStack(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
    }

    public void equipStack(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
    }

    public void extinguishWithSound() {
    }

    public void extinguish() {
    }

    public void fall(double p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3) {
    }

    public java.lang.Object findPath(int p0, int p1, java.lang.Object p2) {
        return null;
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

    public murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent getAmbientSound() {
        return null;
    }

    public float getArmorVisibility() {
        return 0.0F;
    }

    public int getArmor() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityAttachments getAttachments() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box getAttackBox() {
        return null;
    }

    public double getAttackDistanceScalingFactor(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return 0.0D;
    }

    public float getAttackKnockbackAgainst(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1) {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity getAttacker() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity getAttackingPlayer() {
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

    public double getAttributeValue(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.attribute.AttributeContainer getAttributes() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions getBaseDimensions(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose p0) {
        return null;
    }

    public float getBaseWaterMovementSpeedMultiplier() {
        return 0.0F;
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

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getBlockingItem() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getBodyArmor() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonPart[] getBodyParts() {
        return null;
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

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getControlledMovementInput(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
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

    public float getDamageBlockedAmount(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1, float p2) {
        return 0.0F;
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

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions getDimensions(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose p0) {
        return null;
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

    public double getEffectiveGravity() {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.World getEntityWorld() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry getEquipSound(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1, murat.simv2.simulation.mirror.net.minecraft.component.type.EquippableComponent p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentDropChances getEquipmentDropChances() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.item.Item getEquipmentForSlot(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0, int p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getEquippedStack(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
        return null;
    }

    public int getExperienceToDrop(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
        return 0;
    }

    public int getExperienceToDrop(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
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

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getFightOrigin() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonFight getFight() {
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

    public double getGravity() {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getHandPosOffset(murat.simv2.simulation.mirror.net.minecraft.item.Item p0) {
        return null;
    }

    public float getHandSwingProgress(float p0) {
        return 0.0F;
    }

    public float getHeadVerticalMovement() {
        return 0.0F;
    }

    public float getHeadYaw() {
        return 0.0F;
    }

    public float getHealth() {
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

    public murat.simv2.simulation.mirror.net.minecraft.entity.PositionInterpolator getInterpolator() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3i getItemPickUpRangeExpander() {
        return null;
    }

    public int getItemUseTimeLeft() {
        return 0;
    }

    public int getItemUseTime() {
        return 0;
    }

    public float getJumpBoostVelocityModifier() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.control.JumpControl getJumpControl() {
        return null;
    }

    public float getJumpVelocityMultiplier() {
        return 0.0F;
    }

    public float getJumpVelocity() {
        return 0.0F;
    }

    public float getJumpVelocity(float p0) {
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

    public murat.simv2.simulation.mirror.net.minecraft.entity.Leashable.LeashData getLeashData() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getLeashHolder() {
        return null;
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

    public int getLimitPerChunk() {
        return 0;
    }

    public java.util.Map getLocationBasedEnchantmentEffects(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.control.LookControl getLookControl() {
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

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getMainHandStack() {
        return null;
    }

    public float getMaxAbsorption() {
        return 0.0F;
    }

    public int getMaxAir() {
        return 0;
    }

    public int getMaxHeadRotation() {
        return 0;
    }

    public float getMaxHealth() {
        return 0.0F;
    }

    public int getMaxLookPitchChange() {
        return 0;
    }

    public int getMaxLookYawChange() {
        return 0;
    }

    public float getMaxRelativeHeadRotation() {
        return 0.0F;
    }

    public int getMinAmbientSoundDelay() {
        return 0;
    }

    public int getMinFreezeDamageTicks() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.control.MoveControl getMoveControl() {
        return null;
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

    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.pathing.EntityNavigation getNavigation() {
        return null;
    }

    public int getNearestPathNodeIndex() {
        return 0;
    }

    public int getNearestPathNodeIndex(double p0, double p1, double p2) {
        return 0;
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

    public java.lang.Object getPathOfAllPredecessors(java.lang.Object p0, java.lang.Object p1) {
        return null;
    }

    public float getPathfindingPenalty(murat.simv2.simulation.mirror.net.minecraft.entity.ai.pathing.PathNodeType p0) {
        return 0.0F;
    }

    public java.lang.Object getPhaseManager() {
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

    public float getPositionTargetRange() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getPositionTarget() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getPos() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot getPreferredEquipmentSlot(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey getPreferredWeapons() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity getPrimeAdversary() {
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

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getRotationVectorFromPhase(float p0) {
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

    public float getSaddledSpeed(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
        return 0.0F;
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

    public murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot getSlotForStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, java.util.List p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory getSoundCategory() {
        return null;
    }

    public float getSoundPitch() {
        return 0.0F;
    }

    public float getSoundVolume() {
        return 0.0F;
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

    public float getStandingEyeHeight() {
        return 0.0F;
    }

    public java.util.Collection getStatusEffects() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance getStatusEffect(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return null;
    }

    public float getStepHeight() {
        return 0.0F;
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

    public murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity getTargetInBrain() {
        return null;
    }

    public float getTargetingMargin() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity getTarget() {
        return null;
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

    public float getVelocityMultiplier() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getVelocity() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.mob.MobVisibilityCache getVisibilityCache() {
        return null;
    }

    public float getWeaponDisableBlockingForSeconds() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getWeaponStack() {
        return null;
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

    public boolean hasNoDrag() {
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

    public boolean hasPositionTarget() {
        return false;
    }

    public boolean hasSaddleEquipped() {
        return false;
    }

    public boolean hasStackEquipped(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
        return false;
    }

    public boolean hasStatusEffect(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return false;
    }

    public boolean hasVehicle() {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public void heal(float p0) {
    }

    public boolean hurtByWater() {
        return false;
    }

    public void igniteByLava() {
    }

    public void initDataTracker(murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.Builder p0) {
    }

    public void initEquipment(murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p0, murat.simv2.simulation.mirror.net.minecraft.world.LocalDifficulty p1) {
    }

    public void initGoals() {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityData initialize(murat.simv2.simulation.mirror.net.minecraft.world.ServerWorldAccess p0, murat.simv2.simulation.mirror.net.minecraft.world.LocalDifficulty p1, murat.simv2.simulation.mirror.net.minecraft.entity.SpawnReason p2, murat.simv2.simulation.mirror.net.minecraft.entity.EntityData p3) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult interactAt(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.Hand p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult interactMob(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.Hand p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult interact(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.Hand p1) {
        return null;
    }

    public boolean isAffectedByDaylight() {
        return false;
    }

    public boolean isAffectedBySplashPotions() {
        return false;
    }

    public boolean isAiDisabled() {
        return false;
    }

    public boolean isAlive() {
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

    public boolean isAttacking() {
        return false;
    }

    public boolean isBaby() {
        return false;
    }

    public boolean isBeingRainedOn() {
        return false;
    }

    public boolean isBlocking() {
        return false;
    }

    public boolean isClimbing() {
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

    public boolean isDead() {
        return false;
    }

    public boolean isDescending() {
        return false;
    }

    public boolean isDisallowedInPeaceful() {
        return false;
    }

    public boolean isEntityLookingAtMe(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, double p1, boolean p2, boolean p3, double[] p4) {
        return false;
    }

    public boolean isExperienceDroppingDisabled() {
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

    public boolean isGliding() {
        return false;
    }

    public boolean isGlowingLocal() {
        return false;
    }

    public boolean isGlowing() {
        return false;
    }

    public boolean isHoldingOntoLadder() {
        return false;
    }

    public boolean isHolding(java.util.function.Predicate p0) {
        return false;
    }

    public boolean isHolding(murat.simv2.simulation.mirror.net.minecraft.item.Item p0) {
        return false;
    }

    public boolean isImmobile() {
        return false;
    }

    public boolean isImmuneToExplosion(murat.simv2.simulation.mirror.net.minecraft.world.explosion.Explosion p0) {
        return false;
    }

    public boolean isInAttackRange(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
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

    public boolean isInSwimmingPose() {
        return false;
    }

    public boolean isInWalkTargetRange() {
        return false;
    }

    public boolean isInWalkTargetRange(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
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

    public boolean isInvulnerableTo(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1) {
        return false;
    }

    public boolean isInvulnerable() {
        return false;
    }

    public boolean isLeashed() {
        return false;
    }

    public boolean isLeftHanded() {
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

    public boolean isPersistent() {
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

    public boolean isSleeping() {
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

    public boolean isUsingRiptide() {
        return false;
    }

    public boolean isWearingBodyArmor() {
        return false;
    }

    public void jump() {
    }

    public void kill(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public void knockDownwards() {
    }

    public void knockback(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
    }

    public void launchLivingEntities(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, java.util.List p1) {
    }

    public void lerpHeadYaw(int p0, double p1) {
    }

    public void lerpPosAndRotation(int p0, double p1, double p2, double p3, double p4, double p5) {
    }

    public float lerpYaw(float p0) {
        return 0.0F;
    }

    public void limitFallDistance() {
    }

    public void lookAtEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, float p1, float p2) {
    }

    public void lookAt(murat.simv2.simulation.mirror.net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    public void loot(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity p1) {
    }

    public void markEffectsDirty() {
    }

    public boolean mightBeLeashed() {
        return false;
    }

    public void mobTick(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public float modifyAppliedDamage(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1) {
        return 0.0F;
    }

    public void movePart(murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonPart p0, double p1, double p2, double p3) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movementInputToVelocity(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, float p1, float p2) {
        return null;
    }

    public boolean movesIndependently() {
        return false;
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

    public void onDeath(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0) {
    }

    public void onEatingGrass() {
    }

    public void onEquipStack(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2) {
    }

    public void onExplodedBy(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void onFinishPathfinding() {
    }

    public void onKilledBy(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
    }

    public boolean onKilledOther(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1) {
        return false;
    }

    public void onLanding() {
    }

    public void onLeashRemoved() {
    }

    public void onPassengerLookAround(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void onPlayerCollision(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
    }

    public void onPlayerSpawnedChild(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.mob.MobEntity p1) {
    }

    public void onRemoval(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p1) {
    }

    public void onRemoved() {
    }

    public void onRemove(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p0) {
    }

    public void onShortLeashTick(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void onSpawnPacket(murat.simv2.simulation.mirror.net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket p0) {
    }

    public void onStartPathfinding() {
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

    public void parentDamage(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1, float p2) {
    }

    public void playAmbientSound() {
    }

    public void playAttackSound() {
    }

    public void playBlockFallSound() {
    }

    public void playCombinationStepSounds(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1) {
    }

    public void playExtinguishSound() {
    }

    public void playHurtSound(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0) {
    }

    public void playSecondaryStepSound(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
    }

    public void playSoundIfNotSilent(murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p0) {
    }

    public void playSound(murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p0) {
    }

    public void playSound(murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p0, float p1, float p2) {
    }

    public void playSpawnEffects() {
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

    public boolean prefersNewDamageableItem(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return false;
    }

    public boolean prefersNewEquipment(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p2) {
        return false;
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

    public void readLeashDataFromNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
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

    public void sendAiDebugData() {
    }

    public void sendEffectToControllingPlayer(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0) {
    }

    public void sendEquipmentBreakStatus(murat.simv2.simulation.mirror.net.minecraft.item.Item p0, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p1) {
    }

    public void sendPickup(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, int p1) {
    }

    public void serverDamage(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1) {
    }

    public void setAbsorptionAmountUnclamped(float p0) {
    }

    public void setAbsorptionAmount(float p0) {
    }

    public void setAiDisabled(boolean p0) {
    }

    public void setAir(int p0) {
    }

    public void setAngles(float p0, float p1) {
    }

    public boolean setApplicableComponent(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, java.lang.Object p1) {
        return false;
    }

    public void setAttacker(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity setAttackingPlayer(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0) {
        return null;
    }

    public void setAttacking(boolean p0) {
    }

    public void setAttacking(java.util.UUID p0, int p1) {
    }

    public void setAttacking(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, int p1) {
    }

    public void setBaby(boolean p0) {
    }

    public void setBodyYaw(float p0) {
    }

    public void setBoundingBox(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0) {
    }

    public void setCanPickUpLoot(boolean p0) {
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

    public void setDropGuaranteed(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
    }

    public void setEquipmentDropChance(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0, float p1) {
    }

    public void setEquipmentFromTable(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentTable p0) {
    }

    public void setEquipmentFromTable(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentTable p0, murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext p1) {
    }

    public void setEquipmentFromTable(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, java.util.Map p1) {
    }

    public void setEquipmentFromTable(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext p1, java.util.Map p2) {
    }

    public void setEquipmentFromTable(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, murat.simv2.simulation.mirror.net.minecraft.loot.context.LootWorldContext p1, long p2, java.util.Map p3) {
    }

    public void setFightOrigin(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public void setFight(murat.simv2.simulation.mirror.net.minecraft.entity.boss.dragon.EnderDragonFight p0) {
    }

    public void setFireTicks(int p0) {
    }

    public void setFlag(int p0, boolean p1) {
    }

    public void setForwardSpeed(float p0) {
    }

    public void setFrozenTicks(int p0) {
    }

    public void setGlowing(boolean p0) {
    }

    public void setHeadYaw(float p0) {
    }

    public void setHealth(float p0) {
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

    public void setLeashData(murat.simv2.simulation.mirror.net.minecraft.entity.Leashable.LeashData p0) {
    }

    public void setLeftHanded(boolean p0) {
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

    public void setPathfindingPenalty(murat.simv2.simulation.mirror.net.minecraft.entity.ai.pathing.PathNodeType p0, float p1) {
    }

    public void setPersistent() {
    }

    public void setPitch(float p0) {
    }

    public void setPortalCooldown(int p0) {
    }

    public void setPose(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose p0) {
    }

    public void setPositionTarget(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, int p1) {
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

    public void setSidewaysSpeed(float p0) {
    }

    public void setSilent(boolean p0) {
    }

    public void setSleepingPosition(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public void setSneaking(boolean p0) {
    }

    public void setSprinting(boolean p0) {
    }

    public void setStackInHand(murat.simv2.simulation.mirror.net.minecraft.util.Hand p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
    }

    public void setStatusEffect(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
    }

    public void setStingerCount(int p0) {
    }

    public void setStuckArrowCount(int p0) {
    }

    public void setSwimming(boolean p0) {
    }

    public void setTarget(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
    }

    public void setUnresolvedLeashHolderId(int p0) {
    }

    public void setUpwardSpeed(float p0) {
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

    public boolean shouldDropExperience() {
        return false;
    }

    public boolean shouldDropLoot() {
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

    public boolean spawnsTooManyForEachTry(int p0) {
        return false;
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

    public void stopMovement() {
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

    public void swimUpward(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey p0) {
    }

    public void swingHand(murat.simv2.simulation.mirror.net.minecraft.util.Hand p0) {
    }

    public void swingHand(murat.simv2.simulation.mirror.net.minecraft.util.Hand p0, boolean p1) {
    }

    public void takeKnockback(double p0, double p1, double p2) {
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

    public void tickBlockCollision() {
    }

    public void tickBlockCollision(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    public void tickControlled(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    public void tickCramming() {
    }

    public void tickGliding() {
    }

    public void tickHandSwing() {
    }

    public void tickInVoid() {
    }

    public void tickItemStackUsage(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public static void tickLeash(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
    }

    public void tickMovementInput() {
    }

    public void tickMovement() {
    }

    public void tickNewAi() {
    }

    public void tickPortalCooldown() {
    }

    public void tickPortalTeleportation() {
    }

    public void tickRiding() {
    }

    public void tickRiptide(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1) {
    }

    public void tickStatusEffects() {
    }

    public void tickWithEndCrystals() {
    }

    public void tick() {
    }

    public void tiltScreen(double p0, double p1) {
    }

    public java.lang.String toString() {
        return null;
    }

    public void travel(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
    }

    public void triggerItemPickedUpByEntityCriteria(murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity p0) {
    }

    public boolean tryAttack(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack tryEquip(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return null;
    }

    public void tryUsePortal(murat.simv2.simulation.mirror.net.minecraft.block.Portal p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
    }

    public void turnHead(float p0) {
    }

    public void unsetRemoved() {
    }

    public void updateAttribute(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
    }

    public void updateEnchantments(murat.simv2.simulation.mirror.net.minecraft.world.ServerWorldAccess p0, murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random p1, murat.simv2.simulation.mirror.net.minecraft.world.LocalDifficulty p2) {
    }

    public void updateEventHandler(java.util.function.BiConsumer p0) {
    }

    public void updateGoalControls() {
    }

    public void updateKilledAdvancementCriterion(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1) {
    }

    public void updateLastAngles() {
    }

    public void updateLastPosition() {
    }

    public void updateLimbs(boolean p0) {
    }

    public void updateLimbs(float p0) {
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

    public void wakeUp() {
    }

    public boolean wouldNotSuffocateInPose(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose p0) {
        return false;
    }

    public float wrapYawChange(double p0) {
        return 0.0F;
    }

    public void writeCustomDataToNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    public void writeLeashDataToNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, murat.simv2.simulation.mirror.net.minecraft.entity.Leashable.LeashData p1) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound writeNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
        return null;
    }

    public EnderDragonEntity() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
