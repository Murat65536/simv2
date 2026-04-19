package murat.simv2.simulation.mirror.net.minecraft.server.network;

// Generated mirror stub for simulation closure.
public class ServerPlayerEntity extends murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.lang.String ATTRIBUTES_NBT_KEY;
    public static float BABY_SCALE_FACTOR;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier CREATIVE_BLOCK_INTERACTION_RANGE_MODIFIER;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributeModifier CREATIVE_ENTITY_INTERACTION_RANGE_MODIFIER;
    public static int DEATH_TICKS;
    public static float DEFAULT_EYE_HEIGHT;
    public static float DEFAULT_FRICTION;
    public static murat.simv2.simulation.mirror.net.minecraft.util.Arm DEFAULT_MAIN_ARM;
    public static int DEFAULT_MIN_FREEZE_DAMAGE_TICKS;
    public static int DEFAULT_PORTAL_COOLDOWN;
    public static boolean DEFAULT_SEEN_CREDITS;
    public static boolean DEFAULT_SPAWN_EXTRA_PARTICLES_ON_FALL;
    public static java.lang.String ENDER_PEARLS_DIMENSION_KEY;
    public static java.lang.String ENDER_PEARLS_KEY;
    public static int EQUIPMENT_SLOT_ID;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<java.lang.Byte> FLAGS;
    public static int FREEZING_DAMAGE_INTERVAL;
    public static int GLIDING_FLAG_INDEX;
    public static int GLOWING_FLAG;
    public static int GLOWING_FLAG_INDEX;
    public static double GRAVITY;
    public static java.lang.String ID_KEY;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound> LEFT_SHOULDER_ENTITY;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<java.lang.Byte> LIVING_FLAGS;
    public static org.slf4j.Logger LOGGER;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<java.lang.Byte> MAIN_ARM;
    public static int MAX_COMMAND_TAGS;
    public static int MAX_RIDING_COOLDOWN;
    public static float MIN_RISING_BUBBLE_COLUMN_SPEED;
    public static java.util.function.Predicate<murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity> NOT_WEARING_GAZE_DISGUISE_PREDICATE;
    public static int OFF_HAND_ACTIVE_FLAG;
    public static int ON_FIRE_FLAG_INDEX;
    public static java.lang.String PASSENGERS_KEY;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<java.lang.Byte> PLAYER_MODEL_PARTS;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose> POSE;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound> RIGHT_SHOULDER_ENTITY;
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text SET_SPAWN_TEXT;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions SLEEPING_DIMENSIONS;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions STANDING_DIMENSIONS;
    public static int USING_ITEM_FLAG;
    public static int USING_RIPTIDE_FLAG;
    public static java.lang.String UUID_KEY;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d VEHICLE_ATTACHMENT_POS;
    public int abilityResyncCountdown;
    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack activeItemStack;
    public murat.simv2.simulation.mirror.net.minecraft.advancement.PlayerAdvancementTracker advancementTracker;
    public int age;
    public boolean allowServerListing;
    public murat.simv2.simulation.mirror.net.minecraft.entity.LazyEntityReference<murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity> attackingPlayer;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos;
    public float bodyYaw;
    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.brain.Brain<?> brain;
    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity cameraEntity;
    public double capeX;
    public double capeY;
    public double capeZ;
    public murat.simv2.simulation.mirror.net.minecraft.server.network.ChunkFilter chunkFilter;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos chunkPos;
    public boolean clientChatColorsEnabled;
    public murat.simv2.simulation.mirror.net.minecraft.network.message.ChatVisibility clientChatVisibility;
    public java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos> climbingPos;
    public boolean collidedSoftly;
    public murat.simv2.simulation.mirror.net.minecraft.server.command.CommandOutput commandOutput;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d currentExplosionImpactPos;
    public murat.simv2.simulation.mirror.net.minecraft.screen.ScreenHandler currentScreenHandler;
    public float damageTiltYaw;
    public murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker dataTracker;
    public boolean dead;
    public int deathTime;
    public int defaultMaxHealth;
    public int despawnCounter;
    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions dimensions;
    public boolean disconnected;
    public float distanceTraveled;
    public murat.simv2.simulation.mirror.net.minecraft.entity.mob.ElytraFlightController elytraFlightController;
    public int enchantingTableSeed;
    public murat.simv2.simulation.mirror.net.minecraft.inventory.EnderChestInventory enderChestInventory;
    public java.util.Set<murat.simv2.simulation.mirror.net.minecraft.entity.projectile.thrown.EnderPearlEntity> enderPearls;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d enteredNetherPos;
    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityEquipment equipment;
    public int experienceLevel;
    public int experiencePickUpDelay;
    public float experienceProgress;
    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity explodedBy;
    public double fallDistance;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d fallStartPos;
    public static int field_29769;
    public static int field_29770;
    public static int field_30072;
    public static int field_30074;
    public static double field_30075;
    public static int field_30644;
    public static int field_30645;
    public static int field_30646;
    public static int field_30647;
    public static float field_30648;
    public static float field_30649;
    public static float field_30650;
    public static float field_44870;
    public static double field_44871;
    public static double field_44872;
    public static float field_44874;
    public static int field_46175;
    public static int field_46928;
    public static float field_47756;
    public static float field_47819;
    public static float field_47820;
    public static int field_48827;
    public static int field_49073;
    public static int field_49734;
    public static int field_49735;
    public java.lang.Object field_49777;
    public static int field_49791;
    public static double field_54046;
    public static double field_54047;
    public static int field_54207;
    public static int field_55202;
    public static int field_55952;
    public static float field_56256;
    public float field_7509;
    public boolean filterText;
    public boolean firstUpdate;
    public murat.simv2.simulation.mirror.net.minecraft.entity.projectile.FishingBobberEntity fishHook;
    public it.unimi.dsi.fastutil.objects.Object2DoubleMap<murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey<murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid>> fluidHeight;
    public boolean forceUpdateSupportingBlockPos;
    public float forwardSpeed;
    public int glidingTicks;
    public boolean groundCollision;
    public float handSwingProgress;
    public int handSwingTicks;
    public boolean handSwinging;
    public int headTrackingIncrements;
    public float headYaw;
    public boolean horizontalCollision;
    public murat.simv2.simulation.mirror.net.minecraft.entity.player.HungerManager hungerManager;
    public int hurtTime;
    public boolean inPowderSnow;
    public boolean inTeleportationState;
    public murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerInteractionManager interactionManager;
    public murat.simv2.simulation.mirror.net.minecraft.entity.PositionInterpolator interpolator;
    public boolean intersectionChecked;
    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerInventory inventory;
    public boolean isSubmergedInWater;
    public int itemUseTimeLeft;
    public boolean jumping;
    public int jumpingCooldown;
    public java.lang.String language;
    public long lastActionTime;
    public int lastAirScore;
    public int lastArmorScore;
    public int lastAttackedTicks;
    public float lastBodyYaw;
    public double lastCapeX;
    public double lastCapeY;
    public double lastCapeZ;
    public float lastDamageTaken;
    public int lastExperienceScore;
    public int lastFoodScore;
    public float lastHandSwingProgress;
    public float lastHeadYaw;
    public float lastHealthScore;
    public int lastLevelScore;
    public float lastPitch;
    public double lastRenderX;
    public double lastRenderY;
    public double lastRenderZ;
    public float lastStrideDistance;
    public double lastX;
    public double lastY;
    public float lastYaw;
    public double lastZ;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d levitationStartPos;
    public int levitationStartTick;
    public murat.simv2.simulation.mirror.net.minecraft.entity.LimbAnimator limbAnimator;
    public int maxHurtTime;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movement;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movementMultiplier;
    public murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayNetworkHandler networkHandler;
    public boolean noClip;
    public boolean notInAnyWorld;
    public murat.simv2.simulation.mirror.net.minecraft.particle.ParticlesMode particlesMode;
    public long pistonMovementTick;
    public int playerHitTimer;
    public murat.simv2.simulation.mirror.net.minecraft.util.PlayerInput playerInput;
    public murat.simv2.simulation.mirror.net.minecraft.screen.PlayerScreenHandler playerScreenHandler;
    public murat.simv2.simulation.mirror.net.minecraft.world.dimension.PortalManager portalManager;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d pos;
    public murat.simv2.simulation.mirror.net.minecraft.util.Hand preferredHand;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random random;
    public murat.simv2.simulation.mirror.net.minecraft.server.network.ServerRecipeBook recipeBook;
    public int remainingLoadTicks;
    public murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity.Respawn respawn;
    public int ridingCooldown;
    public float riptideAttackDamage;
    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack riptideStack;
    public int riptideTicks;
    public murat.simv2.simulation.mirror.net.minecraft.screen.ScreenHandlerListener screenHandlerListener;
    public murat.simv2.simulation.mirror.net.minecraft.screen.ScreenHandlerSyncHandler screenHandlerSyncHandler;
    public int screenHandlerSyncId;
    public murat.simv2.simulation.mirror.net.minecraft.block.entity.SculkShriekerWarningManager sculkShriekerWarningManager;
    public boolean seenCredits;
    public murat.simv2.simulation.mirror.net.minecraft.server.MinecraftServer server;
    public double serverHeadYaw;
    public murat.simv2.simulation.mirror.net.minecraft.network.encryption.PublicPlayerSession session;
    public float sidewaysSpeed;
    public boolean spawnExtraParticlesOnFall;
    public float speed;
    public float standingEyeHeight;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos startRaidPos;
    public murat.simv2.simulation.mirror.net.minecraft.stat.ServerStatHandler statHandler;
    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState stateAtPos;
    public float strideDistance;
    public int stuckArrowTimer;
    public int stuckStingerTimer;
    public boolean submergedInWater;
    public java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos> supportingBlockPos;
    public int syncedExperience;
    public int syncedFoodLevel;
    public float syncedHealth;
    public boolean syncedSaturationIsZero;
    public murat.simv2.simulation.mirror.net.minecraft.server.filter.TextStream textStream;
    public int timeUntilRegen;
    public int totalExperience;
    public boolean touchingWater;
    public float upwardSpeed;
    public java.util.UUID uuid;
    public java.lang.String uuidString;
    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity vehicle;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vehicleInLavaRidingPos;
    public boolean velocityDirty;
    public boolean velocityModified;
    public boolean verticalCollision;
    public int viewDistance;
    public boolean wasInPowderSnow;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkSectionPos watchedSection;

    public ServerPlayerEntity(murat.simv2.simulation.mirror.net.minecraft.server.MinecraftServer p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, com.mojang.authlib.GameProfile p2, murat.simv2.simulation.mirror.net.minecraft.network.packet.c2s.common.SyncedClientOptions p3) {
    }

    public boolean acceptsChatMessage() {
        return false;
    }

    public boolean acceptsMessage(boolean p0) {
        return false;
    }

    public void addAirTravelEffects() {
    }

    public boolean addCommandTag(java.lang.String p0) {
        return false;
    }

    public void addCritParticles(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void addDeathParticles() {
    }

    public void addEnchantedHitParticles(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public static long addEnderPearlTicket(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos p1) {
        return 0L;
    }

    public void addEnderPearl(murat.simv2.simulation.mirror.net.minecraft.entity.projectile.thrown.EnderPearlEntity p0) {
    }

    public void addExhaustion(float p0) {
    }

    public void addExperienceLevels(int p0) {
    }

    public void addExperience(int p0) {
    }

    public void addFlapEffects() {
    }

    public void addPassenger(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void addPortalChunkTicketAt(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public void addPowderSnowSlowIfNeeded() {
    }

    public void addScore(int p0) {
    }

    public boolean addShoulderEntity(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
        return false;
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

    public boolean allowsServerListing() {
        return false;
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

    public void applyEnchantmentCosts(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, int p1) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d applyFluidMovingSpeed(double p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2) {
        return null;
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

    public boolean areClientChatColorsEnabled() {
        return false;
    }

    public boolean areItemsDifferent(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return false;
    }

    public java.lang.String asString() {
        return null;
    }

    public void attackLivingEntity(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
    }

    public void attack(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void attemptTickInVoid() {
    }

    public void baseTick() {
    }

    public void becomeAngry(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0) {
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

    public int calculateSpawnOffsetMultiplier(int p0) {
        return 0;
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

    public boolean canChangeIntoPose(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose p0) {
        return false;
    }

    public boolean canConsume(boolean p0) {
        return false;
    }

    public boolean canDispenserEquipSlot(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0) {
        return false;
    }

    public boolean canDropItems() {
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

    public boolean canFoodHeal() {
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

    public boolean canHarvest(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return false;
    }

    public boolean canHaveStatusEffect(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0) {
        return false;
    }

    public boolean canHit() {
        return false;
    }

    public boolean canInteractWithBlockAt(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, double p1) {
        return false;
    }

    public boolean canInteractWithEntityIn(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0, double p1) {
        return false;
    }

    public boolean canInteractWithEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, double p1) {
        return false;
    }

    public boolean canModifyAt(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public boolean canModifyBlocks() {
        return false;
    }

    public boolean canMoveVoluntarily() {
        return false;
    }

    public boolean canPickUpLoot() {
        return false;
    }

    public boolean canPlaceOn(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2) {
        return false;
    }

    public boolean canResetTimeBySleeping() {
        return false;
    }

    public boolean canSee(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public boolean canSee(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.ShapeType p1, murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.FluidHandling p2, double p3) {
        return false;
    }

    public boolean canSpawnIn(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1) {
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

    public static java.lang.Object castComponentValue(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, java.lang.Object p1) {
        return null;
    }

    public boolean changeGameMode(murat.simv2.simulation.mirror.net.minecraft.world.GameMode p0) {
        return false;
    }

    public void changeLookDirection(double p0, double p1) {
    }

    public void checkDespawn() {
    }

    public boolean checkGliding() {
        return false;
    }

    public void checkWaterState() {
    }

    public float clampScale(float p0) {
        return 0.0F;
    }

    public void clearActiveItem() {
    }

    public void clearCurrentExplosion() {
    }

    public void clearPotionSwirls() {
    }

    public void clearSleepingPosition() {
    }

    public void clearStartRaidPos() {
    }

    public boolean clearStatusEffects() {
        return false;
    }

    public boolean clientDamage(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0) {
        return false;
    }

    public boolean clipAtLedge() {
        return false;
    }

    public void closeHandledScreen() {
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

    public void copyFrom(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0, boolean p1) {
    }

    public void copyPositionAndRotation(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public boolean couldAcceptPassenger() {
        return false;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.brain.Brain.Profile createBrainProfile() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.network.packet.s2c.play.CommonPlayerSpawnInfo createCommonPlayerSpawnInfo(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.ItemCooldownManager createCooldownManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityEquipment createEquipment() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.entity.attribute.DefaultAttributeContainer.Builder createLivingAttributes() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.entity.attribute.DefaultAttributeContainer.Builder createPlayerAttributes() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet createSpawnPacket(murat.simv2.simulation.mirror.net.minecraft.server.network.EntityTrackerEntry p0) {
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

    public murat.simv2.simulation.mirror.net.minecraft.entity.ai.brain.Brain deserializeBrain(com.mojang.serialization.Dynamic p0) {
        return null;
    }

    public void detachForDimensionChange() {
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

    public boolean doesNotSuffocate(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean doesRenderOnFire() {
        return false;
    }

    public void dropCreativeStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public void dropEquipment(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1, boolean p2) {
    }

    public void dropExperience(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
    }

    public void dropInventory(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity dropItem(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, boolean p1) {
        return null;
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

    public boolean dropSelectedItem(boolean p0) {
        return false;
    }

    public void dropShoulderEntities() {
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

    public void extinguishWithSound() {
    }

    public void extinguish() {
    }

    public void fall(double p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3) {
    }

    public static java.util.Optional findRespawnPosition(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity.Respawn p1, boolean p2) {
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

    public void forgiveMobAnger() {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreHolder fromName(java.lang.String p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreHolder fromProfile(com.mojang.authlib.GameProfile p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.world.GameMode gameModeFromNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, java.lang.String p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerAbilities getAbilities() {
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

    public murat.simv2.simulation.mirror.net.minecraft.advancement.PlayerAdvancementTracker getAdvancementTracker() {
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

    public float getAttackCooldownProgressPerTick() {
        return 0.0F;
    }

    public float getAttackCooldownProgress(float p0) {
        return 0.0F;
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

    public float getBlockBreakingSpeed(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
        return 0.0F;
    }

    public double getBlockInteractionRange() {
        return 0.0D;
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

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getCameraEntity() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getCameraPosVec(float p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.network.ChunkFilter getChunkFilter() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos getChunkPos() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getClientCameraPosVec(float p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.network.message.ChatVisibility getClientChatVisibility() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.network.packet.c2s.common.SyncedClientOptions getClientOptions() {
        return null;
    }

    public java.util.Optional getClimbingPos() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.command.CommandOutput getCommandOutput() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource getCommandSource() {
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

    public float getDamageAgainst(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, float p1, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p2) {
        return 0.0F;
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

    public int getEnchantingTableSeed() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.inventory.EnderChestInventory getEnderChestInventory() {
        return null;
    }

    public java.util.Set getEnderPearls() {
        return null;
    }

    public double getEntityInteractionRange() {
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

    public murat.simv2.simulation.mirror.net.minecraft.world.GameMode getGameMode() {
        return null;
    }

    public com.mojang.authlib.GameProfile getGameProfile() {
        return null;
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

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.HungerManager getHungerManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent getHurtSound(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0) {
        return null;
    }

    public int getId() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getInputVelocityForMinecart() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.PositionInterpolator getInterpolator() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerInventory getInventory() {
        return null;
    }

    public java.lang.String getIp() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.player.ItemCooldownManager getItemCooldownManager() {
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

    public long getLastActionTime() {
        return 0L;
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

    public java.util.Optional getLastDeathPos() {
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

    public int getNextLevelExperience() {
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

    public int getPermissionLevel() {
        return 0;
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

    public murat.simv2.simulation.mirror.net.minecraft.util.PlayerInput getPlayerInput() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getPlayerListName() {
        return null;
    }

    public int getPlayerListOrder() {
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

    public murat.simv2.simulation.mirror.net.minecraft.server.network.ServerRecipeBook getRecipeBook() {
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

    public murat.simv2.simulation.mirror.net.minecraft.world.TeleportTarget getRespawnTarget(boolean p0, murat.simv2.simulation.mirror.net.minecraft.world.TeleportTarget.PostDimensionTransition p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity.Respawn getRespawn() {
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

    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.Scoreboard getScoreboard() {
        return null;
    }

    public int getScore() {
        return 0;
    }

    public java.util.Optional getSculkShriekerWarningManager() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.GameMode getServerGameMode(murat.simv2.simulation.mirror.net.minecraft.world.GameMode p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld getServerWorld() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.MinecraftServer getServer() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.network.encryption.PublicPlayerSession getSession() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound getShoulderEntityLeft() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound getShoulderEntityRight() {
        return null;
    }

    public int getSleepTimer() {
        return 0;
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

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getStartRaidPos() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.stat.ServerStatHandler getStatHandler() {
        return null;
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

    public murat.simv2.simulation.mirror.net.minecraft.server.filter.TextStream getTextStream() {
        return null;
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

    public int getViewDistance() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkSectionPos getWatchedSection() {
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

    public boolean giveItemStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return false;
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

    public long handleThrownEnderPearl(murat.simv2.simulation.mirror.net.minecraft.entity.projectile.thrown.EnderPearlEntity p0) {
        return 0L;
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

    public boolean hasPermissionLevel(int p0) {
        return false;
    }

    public boolean hasPlayerRider() {
        return false;
    }

    public boolean hasPortalCooldown() {
        return false;
    }

    public boolean hasReducedDebugInfo() {
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

    public void increaseRidingMotionStats(double p0, double p1, double p2) {
    }

    public void increaseStat(murat.simv2.simulation.mirror.net.minecraft.stat.Stat p0, int p1) {
    }

    public void increaseStat(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0, int p1) {
    }

    public void increaseTravelMotionStats(double p0, double p1, double p2) {
    }

    public void incrementScreenHandlerSyncId() {
    }

    public void incrementStat(murat.simv2.simulation.mirror.net.minecraft.stat.Stat p0) {
    }

    public void incrementStat(murat.simv2.simulation.mirror.net.minecraft.util.Identifier p0) {
    }

    public void initDataTracker(murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.Builder p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult interactAt(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.Hand p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult interact(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.Hand p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult interact(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.Hand p1) {
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

    public boolean isBedObstructed(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1) {
        return false;
    }

    public boolean isBedWithinRange(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    public boolean isBedWithinRange(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction p1) {
        return false;
    }

    public boolean isBeingRainedOn() {
        return false;
    }

    public boolean isBlockBreakingRestricted(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.world.GameMode p2) {
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

    public boolean isCreativeLevelTwoOp() {
        return false;
    }

    public boolean isCreative() {
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

    public boolean isDisconnected() {
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

    public boolean isInTeleportationState() {
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

    public boolean isLoaded() {
        return false;
    }

    public boolean isLogicalSideForUpdatingMovement() {
        return false;
    }

    public boolean isMainPlayer() {
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

    public boolean isPartVisible(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerModelPart p0) {
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

    public boolean isPvpEnabled() {
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

    public boolean isUsingSpyglass() {
        return false;
    }

    public static boolean isZero(double p0, double p1, double p2) {
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

    public int lockRecipes(java.util.Collection p0) {
        return 0;
    }

    public void lookAtEntity(murat.simv2.simulation.mirror.net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1, murat.simv2.simulation.mirror.net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor p2) {
    }

    public void lookAt(murat.simv2.simulation.mirror.net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    public void markEffectsDirty() {
    }

    public void markHealthDirty() {
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

    public void onDisconnect() {
    }

    public void onEquipStack(murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2) {
    }

    public void onExplodedBy(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void onHandledScreenClosed() {
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

    public void onPickupSlotClick(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1, murat.simv2.simulation.mirror.net.minecraft.util.ClickType p2) {
    }

    public void onPlayerCollision(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
    }

    public void onRecipeCrafted(murat.simv2.simulation.mirror.net.minecraft.recipe.RecipeEntry p0, java.util.List p1) {
    }

    public void onRemoval(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p1) {
    }

    public void onRemoved() {
    }

    public void onRemove(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p0) {
    }

    public void onScreenHandlerOpened(murat.simv2.simulation.mirror.net.minecraft.screen.ScreenHandler p0) {
    }

    public void onSpawnPacket(murat.simv2.simulation.mirror.net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket p0) {
    }

    public void onSpawn() {
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

    public void onTeleportationDone() {
    }

    public void onTrackedDataSet(murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData p0) {
    }

    public void openCommandBlockMinecartScreen(murat.simv2.simulation.mirror.net.minecraft.world.CommandBlockExecutor p0) {
    }

    public void openCommandBlockScreen(murat.simv2.simulation.mirror.net.minecraft.block.entity.CommandBlockBlockEntity p0) {
    }

    public void openEditSignScreen(murat.simv2.simulation.mirror.net.minecraft.block.entity.SignBlockEntity p0, boolean p1) {
    }

    public java.util.OptionalInt openHandledScreen(murat.simv2.simulation.mirror.net.minecraft.screen.NamedScreenHandlerFactory p0) {
        return null;
    }

    public void openHorseInventory(murat.simv2.simulation.mirror.net.minecraft.entity.passive.AbstractHorseEntity p0, murat.simv2.simulation.mirror.net.minecraft.inventory.Inventory p1) {
    }

    public void openJigsawScreen(murat.simv2.simulation.mirror.net.minecraft.block.entity.JigsawBlockEntity p0) {
    }

    public void openStructureBlockScreen(murat.simv2.simulation.mirror.net.minecraft.block.entity.StructureBlockBlockEntity p0) {
    }

    public void openTestBlockScreen(murat.simv2.simulation.mirror.net.minecraft.block.entity.TestBlockEntity p0) {
    }

    public void openTestInstanceBlockScreen(murat.simv2.simulation.mirror.net.minecraft.block.entity.TestInstanceBlockEntity p0) {
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

    public void playSoundToPlayer(murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p0, murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory p1, float p2, float p3) {
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

    public void playerTick() {
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

    public void readEnderPearls(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    public void readEnderPearl(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    public void readGameModeNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    public void readNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    public void readRootVehicle(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
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

    public void removeEnderPearl(murat.simv2.simulation.mirror.net.minecraft.entity.projectile.thrown.EnderPearlEntity p0) {
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

    public void requestRespawn() {
    }

    public void requestTeleportAndDismount(double p0, double p1, double p2) {
    }

    public void requestTeleportOffset(double p0, double p1, double p2) {
    }

    public void requestTeleport(double p0, double p1, double p2) {
    }

    public void resetLastAttackedTicks() {
    }

    public void resetPortalCooldown() {
    }

    public void resetPosition() {
    }

    public void resetStat(murat.simv2.simulation.mirror.net.minecraft.stat.Stat p0) {
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

    public void sendAbilitiesUpdate() {
    }

    public void sendChatMessage(murat.simv2.simulation.mirror.net.minecraft.network.message.SentMessage p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType.Parameters p2) {
    }

    public void sendEffectToControllingPlayer(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffectInstance p0) {
    }

    public void sendEquipmentBreakStatus(murat.simv2.simulation.mirror.net.minecraft.item.Item p0, murat.simv2.simulation.mirror.net.minecraft.entity.EquipmentSlot p1) {
    }

    public void sendMapPacket(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public void sendMessageToClient(murat.simv2.simulation.mirror.net.minecraft.text.Text p0, boolean p1) {
    }

    public void sendMessage(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public void sendMessage(murat.simv2.simulation.mirror.net.minecraft.text.Text p0, boolean p1) {
    }

    public void sendPickup(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, int p1) {
    }

    public void sendServerMetadata(murat.simv2.simulation.mirror.net.minecraft.server.ServerMetadata p0) {
    }

    public void sendTradeOffers(int p0, murat.simv2.simulation.mirror.net.minecraft.village.TradeOfferList p1, int p2, int p3, boolean p4, boolean p5) {
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

    public boolean setApplicableComponent(murat.simv2.simulation.mirror.net.minecraft.component.ComponentType p0, java.lang.Object p1) {
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

    public void setBodyYaw(float p0) {
    }

    public void setBoundingBox(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0) {
    }

    public void setCameraEntity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void setChangeListener(murat.simv2.simulation.mirror.net.minecraft.world.entity.EntityChangeListener p0) {
    }

    public void setChunkFilter(murat.simv2.simulation.mirror.net.minecraft.server.network.ChunkFilter p0) {
    }

    public void setClientOptions(murat.simv2.simulation.mirror.net.minecraft.network.packet.c2s.common.SyncedClientOptions p0) {
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

    public void setExperienceLevel(int p0) {
    }

    public void setExperiencePoints(int p0) {
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

    public void setHealth(float p0) {
    }

    public void setId(int p0) {
    }

    public void setIgnoreFallDamageFromCurrentExplosion(boolean p0) {
    }

    public void setInPowderSnow(boolean p0) {
    }

    public void setInvisible(boolean p0) {
    }

    public void setInvulnerable(boolean p0) {
    }

    public void setJumping(boolean p0) {
    }

    public void setLastDeathPos(java.util.Optional p0) {
    }

    public void setLastPositionAndAngles(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, float p1, float p2) {
    }

    public void setLivingFlag(int p0, boolean p1) {
    }

    public void setLoaded(boolean p0) {
    }

    public void setMainArm(murat.simv2.simulation.mirror.net.minecraft.util.Arm p0) {
    }

    public void setMovementSpeed(float p0) {
    }

    public void setMovement(boolean p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2) {
    }

    public void setMovement(boolean p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    public void setMovement(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
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

    public void setPlayerInput(murat.simv2.simulation.mirror.net.minecraft.util.PlayerInput p0) {
    }

    public void setPortalCooldown(int p0) {
    }

    public void setPose(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose p0) {
    }

    public void setPosition(double p0, double p1, double p2) {
    }

    public void setPosition(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerPosition p0, java.util.Set p1) {
    }

    public void setPosition(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
    }

    public void setPos(double p0, double p1, double p2) {
    }

    public void setReducedDebugInfo(boolean p0) {
    }

    public void setRemoved(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p0) {
    }

    public static void setRenderDistanceMultiplier(double p0) {
    }

    public void setRotation(float p0, float p1) {
    }

    public void setScore(int p0) {
    }

    public void setServerWorld(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public void setSession(murat.simv2.simulation.mirror.net.minecraft.network.encryption.PublicPlayerSession p0) {
    }

    public void setShoulderEntityLeft(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    public void setShoulderEntityRight(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    public void setSilent(boolean p0) {
    }

    public void setSleepingPosition(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    public void setSneaking(boolean p0) {
    }

    public void setSpawnExtraParticlesOnFall(boolean p0) {
    }

    public void setSpawnPointFrom(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
    }

    public void setSpawnPoint(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity.Respawn p0, boolean p1) {
    }

    public void setSprinting(boolean p0) {
    }

    public void setStackInHand(murat.simv2.simulation.mirror.net.minecraft.util.Hand p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
    }

    public void setStartRaidPos(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
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

    public void setWatchedSection(murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkSectionPos p0) {
    }

    public void setWorld(murat.simv2.simulation.mirror.net.minecraft.world.World p0) {
    }

    public void setYaw(float p0) {
    }

    public boolean shouldAlwaysDropExperience() {
        return false;
    }

    public boolean shouldCancelInteraction() {
        return false;
    }

    public boolean shouldCloseHandledScreenOnRespawn() {
        return false;
    }

    public boolean shouldControlVehicles() {
        return false;
    }

    public boolean shouldDamagePlayer(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
        return false;
    }

    public boolean shouldDismountUnderwater() {
        return false;
    }

    public boolean shouldDismount() {
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

    public boolean shouldFilterMessagesSentTo(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
        return false;
    }

    public boolean shouldFilterText() {
        return false;
    }

    public boolean shouldIgnoreFallDamageFromCurrentExplosion() {
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

    public boolean shouldRotateWithMinecart() {
        return false;
    }

    public boolean shouldSave() {
        return false;
    }

    public boolean shouldSetPositionOnLoad() {
        return false;
    }

    public boolean shouldSkipBlockDrops() {
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

    public void spawnSweepAttackParticles() {
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

    public void startGliding() {
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

    public void tickFallStartPos() {
    }

    public void tickGliding() {
    }

    public void tickHandSwing() {
    }

    public void tickHunger() {
    }

    public void tickInVoid() {
    }

    public void tickItemStackUsage(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public void tickLoaded() {
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

    public void tickVehicleInLavaRiding() {
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

    public void tryClearCurrentExplosion() {
    }

    public com.mojang.datafixers.util.Either trySleep(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public void tryUsePortal(murat.simv2.simulation.mirror.net.minecraft.block.Portal p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
    }

    public void turnHead(float p0) {
    }

    public int unlockRecipes(java.util.Collection p0) {
        return 0;
    }

    public void unlockRecipes(java.util.List p0) {
    }

    public void unsetRemoved() {
    }

    public void updateAttribute(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
    }

    public void updateCreativeInteractionRangeModifiers() {
    }

    public void updateEventHandler(java.util.function.BiConsumer p0) {
    }

    public void updateKilledAdvancementCriterion(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1) {
    }

    public void updateLastActionTime() {
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

    public void updatePose() {
    }

    public void updatePositionAndAngles(double p0, double p1, double p2, float p3, float p4) {
    }

    public void updatePosition(double p0, double p1, double p2) {
    }

    public void updatePostDeath() {
    }

    public void updatePotionVisibility() {
    }

    public void updateScoreboardScore(murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreHolder p0, murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreHolder p1, murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion[] p2) {
    }

    public void updateScores(murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreboardCriterion p0, int p1) {
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

    public boolean updateWaterSubmersionState() {
        return false;
    }

    public void useBook(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0, murat.simv2.simulation.mirror.net.minecraft.util.Hand p1) {
    }

    public void useRiptide(int p0, float p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2) {
    }

    public void vanishCursedItems() {
    }

    public void wakeUp() {
    }

    public void wakeUp(boolean p0, boolean p1) {
    }

    public void worldChanged(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public boolean wouldNotSuffocateInPose(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose p0) {
        return false;
    }

    public void writeCustomDataToNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    public void writeEnderPearls(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    public void writeGameModeNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound writeNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
        return null;
    }

    public void writeRootVehicle(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    public ServerPlayerEntity() {
    }

    public static class Respawn {
        public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity.Respawn> CODEC;
        public float angle;
        public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<murat.simv2.simulation.mirror.net.minecraft.world.World> dimension;
        public boolean forced;
        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos pos;

        public Respawn(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, float p2, boolean p3) {
        }

        public float angle() {
            return 0.0F;
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey dimension() {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public boolean forced() {
            return false;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey getDimension(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity.Respawn p0) {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public boolean posEquals(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity.Respawn p0) {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos pos() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public Respawn() {
        }

    }

    public static class RespawnPos {
        public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d pos;
        public float yaw;

        public RespawnPos(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, float p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity.RespawnPos fromCurrentPos(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
            return null;
        }

        public static float getYaw(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
            return 0.0F;
        }

        public int hashCode() {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d pos() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public float yaw() {
            return 0.0F;
        }

        public RespawnPos() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
