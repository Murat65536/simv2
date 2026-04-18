package murat.simv2.simulation.mirror.net.minecraft.entity.decoration;

// Generated mirror stub for simulation closure.
public class ArmorStandEntity extends murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData ARMOR_STAND_FLAGS;
    public static java.lang.String ATTRIBUTES_NBT_KEY;
    public static float BABY_SCALE_FACTOR;
    public static int DEATH_TICKS;
    public static java.lang.Object DEFAULT_BODY_ROTATION;
    public static int DEFAULT_DISABLED_SLOTS;
    public static float DEFAULT_FRICTION;
    public static java.lang.Object DEFAULT_HEAD_ROTATION;
    public static java.lang.Object DEFAULT_LEFT_ARM_ROTATION;
    public static java.lang.Object DEFAULT_LEFT_LEG_ROTATION;
    public static int DEFAULT_MIN_FREEZE_DAMAGE_TICKS;
    public static int DEFAULT_PORTAL_COOLDOWN;
    public static java.lang.Object DEFAULT_RIGHT_ARM_ROTATION;
    public static java.lang.Object DEFAULT_RIGHT_LEG_ROTATION;
    public static int EQUIPMENT_SLOT_ID;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData FLAGS;
    public static int FREEZING_DAMAGE_INTERVAL;
    public static int GLIDING_FLAG_INDEX;
    public static int GLOWING_FLAG;
    public static int GLOWING_FLAG_INDEX;
    public static double GRAVITY;
    public static int HIDE_BASE_PLATE_FLAG;
    public static java.lang.String ID_KEY;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData LIVING_FLAGS;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions MARKER_DIMENSIONS;
    public static int MARKER_FLAG;
    public static int MAX_COMMAND_TAGS;
    public static int MAX_RIDING_COOLDOWN;
    public static float MIN_RISING_BUBBLE_COLUMN_SPEED;
    public static java.util.function.Predicate NOT_WEARING_GAZE_DISGUISE_PREDICATE;
    public static int OFF_HAND_ACTIVE_FLAG;
    public static int ON_FIRE_FLAG_INDEX;
    public static java.lang.String PASSENGERS_KEY;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData POSE;
    public static java.util.function.Predicate RIDEABLE_MINECART_PREDICATE;
    public static int SHOW_ARMS_FLAG;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions SLEEPING_DIMENSIONS;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions SMALL_DIMENSIONS;
    public static int SMALL_FLAG;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData TRACKER_BODY_ROTATION;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData TRACKER_HEAD_ROTATION;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData TRACKER_LEFT_ARM_ROTATION;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData TRACKER_LEFT_LEG_ROTATION;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData TRACKER_RIGHT_ARM_ROTATION;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData TRACKER_RIGHT_LEG_ROTATION;
    public static int USING_ITEM_FLAG;
    public static int USING_RIPTIDE_FLAG;
    public static java.lang.String UUID_KEY;
    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack activeItemStack;
    public int age;
    public murat.simv2.simulation.mirror.net.minecraft.entity.LazyEntityReference attackingPlayer;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos;
    public java.lang.Object bodyRotation;
    public float bodyYaw;
    public java.lang.Object brain;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos chunkPos;
    public java.util.Optional climbingPos;
    public boolean collidedSoftly;
    public murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker dataTracker;
    public boolean dead;
    public int deathTime;
    public int defaultMaxHealth;
    public int despawnCounter;
    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions dimensions;
    public int disabledSlots;
    public float distanceTraveled;
    public java.lang.Object elytraFlightController;
    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityEquipment equipment;
    public double fallDistance;
    public static int field_30072;
    public static int field_30074;
    public static double field_30075;
    public static int field_30443;
    public static boolean field_30445;
    public static int field_30446;
    public static double field_30447;
    public static double field_30448;
    public static double field_30449;
    public static double field_30450;
    public static int field_30451;
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
    public static boolean field_57644;
    public static boolean field_57646;
    public static boolean field_57647;
    public static boolean field_57648;
    public static boolean field_57649;
    public boolean firstUpdate;
    public it.unimi.dsi.fastutil.objects.Object2DoubleMap fluidHeight;
    public boolean forceUpdateSupportingBlockPos;
    public float forwardSpeed;
    public int glidingTicks;
    public boolean groundCollision;
    public float handSwingProgress;
    public int handSwingTicks;
    public boolean handSwinging;
    public java.lang.Object headRotation;
    public int headTrackingIncrements;
    public float headYaw;
    public boolean horizontalCollision;
    public int hurtTime;
    public boolean inPowderSnow;
    public murat.simv2.simulation.mirror.net.minecraft.entity.PositionInterpolator interpolator;
    public boolean intersectionChecked;
    public boolean invisible;
    public int itemUseTimeLeft;
    public boolean jumping;
    public int jumpingCooldown;
    public int lastAttackedTicks;
    public float lastBodyYaw;
    public float lastDamageTaken;
    public float lastHandSwingProgress;
    public float lastHeadYaw;
    public long lastHitTime;
    public float lastPitch;
    public double lastRenderX;
    public double lastRenderY;
    public double lastRenderZ;
    public double lastX;
    public double lastY;
    public float lastYaw;
    public double lastZ;
    public java.lang.Object leftArmRotation;
    public java.lang.Object leftLegRotation;
    public java.lang.Object limbAnimator;
    public int maxHurtTime;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movementMultiplier;
    public boolean noClip;
    public long pistonMovementTick;
    public int playerHitTimer;
    public java.lang.Object portalManager;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d pos;
    public murat.simv2.simulation.mirror.net.minecraft.util.Hand preferredHand;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random random;
    public int ridingCooldown;
    public java.lang.Object rightArmRotation;
    public java.lang.Object rightLegRotation;
    public float riptideAttackDamage;
    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack riptideStack;
    public int riptideTicks;
    public double serverHeadYaw;
    public float sidewaysSpeed;
    public float speed;
    public float standingEyeHeight;
    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState stateAtPos;
    public int stuckArrowTimer;
    public int stuckStingerTimer;
    public boolean submergedInWater;
    public java.util.Optional supportingBlockPos;
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

    public ArmorStandEntity(murat.simv2.simulation.mirror.net.minecraft.entity.EntityType p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1) {
    }

    public ArmorStandEntity(murat.simv2.simulation.mirror.net.minecraft.world.World p0, double p1, double p2, double p3) {
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

    public float applyMirror(java.lang.Object p0) {
        return 0.0F;
    }

    public void applyMovementEffects(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
    }

    public float applyRotation(java.lang.Object p0) {
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

    public void becomeAngry(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0) {
    }

    public void breakAndDropItem(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1) {
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

    public boolean canClip() {
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

    public boolean canExplosionDestroyBlock(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3, float p4) {
        return false;
    }

    public boolean canFreeze() {
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

    public boolean canWalkOnFluid(murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p0) {
        return false;
    }

    public static java.lang.Object castComponentValue(java.lang.Object p0, java.lang.Object p1) {
        return null;
    }

    public void changeLookDirection(double p0, double p1) {
    }

    public void checkDespawn() {
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

    public boolean copyComponentFrom(java.lang.Object p0, java.lang.Object p1) {
        return false;
    }

    public void copyComponentsFrom(java.lang.Object p0) {
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

    public static java.lang.Object createArmorStandAttributes() {
        return null;
    }

    public java.lang.Object createBrainProfile() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityEquipment createEquipment() {
        return null;
    }

    public static java.lang.Object createLivingAttributes() {
        return null;
    }

    public java.lang.Object createSpawnPacket(java.lang.Object p0) {
        return null;
    }

    public void damageArmor(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1) {
    }

    public void damageEquipment(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot[] p2) {
    }

    public void damageHelmet(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1) {
    }

    public boolean damage(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1, float p2) {
        return false;
    }

    public void defrost() {
    }

    public java.lang.Object deserializeBrain(com.mojang.serialization.Dynamic p0) {
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

    public void dropExperience(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
    }

    public void dropInventory(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity dropItem(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, boolean p1, boolean p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity dropItem(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, java.lang.Object p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity dropItem(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, java.lang.Object p1, int p2) {
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

    public void endCombat() {
    }

    public void enterCombat() {
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public void equipStack(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
    }

    public boolean equip(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2, murat.simv2.simulation.mirror.net.minecraft.util.Hand p3) {
        return false;
    }

    public void extinguishWithSound() {
    }

    public void extinguish() {
    }

    public void fall(double p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3) {
    }

    public boolean forEachGeneratedItem(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p1, java.util.function.Function p2, java.util.function.BiConsumer p3) {
        return false;
    }

    public boolean forEachGiftedItem(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p1, java.util.function.BiConsumer p2) {
        return false;
    }

    public void forEachShearedItem(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2, java.util.function.BiConsumer p3) {
    }

    public static java.lang.Object fromName(java.lang.String p0) {
        return null;
    }

    public static java.lang.Object fromProfile(com.mojang.authlib.GameProfile p0) {
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

    public int getArmor() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityAttachments getAttachments() {
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

    public java.lang.Object getAttributeInstance(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return null;
    }

    public double getAttributeValue(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
        return 0.0D;
    }

    public java.lang.Object getAttributes() {
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

    public java.lang.Object getBodyRotation() {
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

    public java.lang.Object getBrain() {
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

    public java.lang.Object getCommandSource(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
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

    public java.lang.Object getDamageSources() {
        return null;
    }

    public float getDamageTiltYaw() {
        return 0.0F;
    }

    public java.lang.Object getDamageTracker() {
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

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions getDimensions(boolean p0) {
        return null;
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

    public float getEffectiveExplosionResistance(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3, murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p4, float p5) {
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

    public java.lang.Object getHeadRotation() {
        return null;
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

    public java.lang.Object getHoverEvent() {
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

    public int getItemUseTimeLeft() {
        return 0;
    }

    public int getItemUseTime() {
        return 0;
    }

    public float getJumpBoostVelocityModifier() {
        return 0.0F;
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

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getLeashOffset() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getLeashOffset(float p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getLeashPos(float p0) {
        return null;
    }

    public java.lang.Object getLeftArmRotation() {
        return null;
    }

    public java.lang.Object getLeftLegRotation() {
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

    public java.lang.Object getMainArm() {
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

    public float getMaxHealth() {
        return 0.0F;
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

    public java.lang.Object getOrDefault(java.lang.Object p0, java.lang.Object p1) {
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

    public java.lang.Object getPistonBehavior() {
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

    public java.lang.Object getRegistryManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason getRemovalReason() {
        return null;
    }

    public static double getRenderDistanceMultiplier() {
        return 0.0D;
    }

    public java.lang.Object getRightArmRotation() {
        return null;
    }

    public java.lang.Object getRightLegRotation() {
        return null;
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

    public java.lang.Object getServer() {
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

    public murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot getSlotFromPosition(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
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

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getStackInArm(java.lang.Object p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getStackInHand(murat.simv2.simulation.mirror.net.minecraft.util.Hand p0) {
        return null;
    }

    public java.lang.Object getStackReference(int p0) {
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

    public float getTargetingMargin() {
        return 0.0F;
    }

    public int getTeamColorValue() {
        return 0;
    }

    public java.lang.Object getTrackedPosition() {
        return null;
    }

    public java.lang.Object getTyped(java.lang.Object p0) {
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

    public java.lang.Object get(java.lang.Object p0) {
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

    public java.lang.Object interactAt(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.Hand p2) {
        return null;
    }

    public java.lang.Object interact(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.Hand p1) {
        return null;
    }

    public boolean isAffectedBySplashPotions() {
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

    public boolean isImmuneToExplosion(java.lang.Object p0) {
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

    public boolean isLiving() {
        return false;
    }

    public boolean isLogicalSideForUpdatingMovement() {
        return false;
    }

    public boolean isMarker() {
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

    public boolean isSleeping() {
        return false;
    }

    public boolean isSlotDisabled(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
        return false;
    }

    public boolean isSmall() {
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

    public void jump() {
    }

    public void kill(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public void knockDownwards() {
    }

    public void knockback(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
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

    public void lookAt(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    public void markEffectsDirty() {
    }

    public float modifyAppliedDamage(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1) {
        return 0.0F;
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

    public void onBreak(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1) {
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

    public void onEquipStack(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2) {
    }

    public void onExplodedBy(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void onKilledBy(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
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

    public void onSpawnPacket(java.lang.Object p0) {
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

    public void onStruckByLightning(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, java.lang.Object p1) {
    }

    public void onSwimmingStart() {
    }

    public void onTrackedDataSet(murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData p0) {
    }

    public void playBlockFallSound() {
    }

    public void playBreakSound() {
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

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound poseToNbt() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d positionInPortal(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0, java.lang.Object p1) {
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

    public void readPoseNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
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

    public void sendPickup(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, int p1) {
    }

    public void serverDamage(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1) {
    }

    public void setAbsorptionAmountUnclamped(float p0) {
    }

    public void setAbsorptionAmount(float p0) {
    }

    public void setAir(int p0) {
    }

    public void setAngles(float p0, float p1) {
    }

    public boolean setApplicableComponent(java.lang.Object p0, java.lang.Object p1) {
        return false;
    }

    public void setAttacker(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity setAttackingPlayer(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0) {
        return null;
    }

    public void setAttacking(java.util.UUID p0, int p1) {
    }

    public void setAttacking(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, int p1) {
    }

    public byte setBitField(byte p0, int p1, boolean p2) {
        return (byte) 0;
    }

    public void setBodyRotation(java.lang.Object p0) {
    }

    public void setBodyYaw(float p0) {
    }

    public void setBoundingBox(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0) {
    }

    public void setChangeListener(java.lang.Object p0) {
    }

    public void setComponent(java.lang.Object p0, java.lang.Object p1) {
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

    public void setHeadRotation(java.lang.Object p0) {
    }

    public void setHeadYaw(float p0) {
    }

    public void setHealth(float p0) {
    }

    public void setHideBasePlate(boolean p0) {
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

    public void setLeftArmRotation(java.lang.Object p0) {
    }

    public void setLeftLegRotation(java.lang.Object p0) {
    }

    public void setLivingFlag(int p0, boolean p1) {
    }

    public void setMarker(boolean p0) {
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

    public void setPosition(double p0, double p1, double p2) {
    }

    public void setPosition(java.lang.Object p0, java.util.Set p1) {
    }

    public void setPosition(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
    }

    public void setPos(double p0, double p1, double p2) {
    }

    public void setRemoved(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p0) {
    }

    public static void setRenderDistanceMultiplier(double p0) {
    }

    public void setRightArmRotation(java.lang.Object p0) {
    }

    public void setRightLegRotation(java.lang.Object p0) {
    }

    public void setRotation(float p0, float p1) {
    }

    public void setShowArms(boolean p0) {
    }

    public void setSilent(boolean p0) {
    }

    public void setSleepingPosition(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public void setSmall(boolean p0) {
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

    public boolean shouldShowArms() {
        return false;
    }

    public boolean shouldShowBasePlate() {
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

    public void spawnBreakParticles() {
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

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity teleportTo(java.lang.Object p0) {
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

    public void tryUsePortal(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
    }

    public void turnHead(float p0) {
    }

    public void unsetRemoved() {
    }

    public void updateAttribute(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
    }

    public void updateEventHandler(java.util.function.BiConsumer p0) {
    }

    public void updateHealth(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1, float p2) {
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

    public void writeCustomDataToNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound writeNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
        return null;
    }

    public ArmorStandEntity() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
