package murat.simv2.simulation.mirror.net.minecraft.entity.projectile;

// Generated mirror stub for simulation closure.
public class PersistentProjectileEntity extends murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static int CRITICAL_FLAG;
    public static boolean DEFAULT_CRITICAL;
    public static float DEFAULT_DRAG;
    public static float DEFAULT_FRICTION;
    public static boolean DEFAULT_IN_GROUND;
    public static short DEFAULT_LIFE;
    public static int DEFAULT_MIN_FREEZE_DAMAGE_TICKS;
    public static byte DEFAULT_PIERCE_LEVEL;
    public static int DEFAULT_PORTAL_COOLDOWN;
    public static byte DEFAULT_SHAKE;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData FLAGS;
    public static int FREEZING_DAMAGE_INTERVAL;
    public static int GLIDING_FLAG_INDEX;
    public static int GLOWING_FLAG_INDEX;
    public static java.lang.String ID_KEY;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData IN_GROUND;
    public static int MAX_COMMAND_TAGS;
    public static int MAX_RIDING_COOLDOWN;
    public static float MIN_RISING_BUBBLE_COLUMN_SPEED;
    public static int NO_CLIP_FLAG;
    public static int ON_FIRE_FLAG_INDEX;
    public static java.lang.String PASSENGERS_KEY;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData PIERCE_LEVEL;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData POSE;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData PROJECTILE_FLAGS;
    public static java.lang.String UUID_KEY;
    public int age;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos chunkPos;
    public boolean collidedSoftly;
    public double damage;
    public murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker dataTracker;
    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions dimensions;
    public float distanceTraveled;
    public double fallDistance;
    public static double field_30657;
    public static float field_44870;
    public static double field_44871;
    public static double field_44872;
    public static int field_49073;
    public static int field_49791;
    public static int field_54968;
    public static float field_55017;
    public boolean firstUpdate;
    public it.unimi.dsi.fastutil.objects.Object2DoubleMap fluidHeight;
    public boolean forceUpdateSupportingBlockPos;
    public boolean groundCollision;
    public boolean horizontalCollision;
    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState inBlockState;
    public int inGroundTime;
    public boolean inPowderSnow;
    public boolean intersectionChecked;
    public float lastPitch;
    public double lastRenderX;
    public double lastRenderY;
    public double lastRenderZ;
    public double lastX;
    public double lastY;
    public float lastYaw;
    public double lastZ;
    public int life;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movementMultiplier;
    public boolean noClip;
    public murat.simv2.simulation.mirror.net.minecraft.entity.projectile.PersistentProjectileEntity.PickupPermission pickupType;
    public it.unimi.dsi.fastutil.ints.IntOpenHashSet piercedEntities;
    public java.util.List piercingKilledEntities;
    public long pistonMovementTick;
    public java.lang.Object portalManager;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d pos;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random random;
    public int ridingCooldown;
    public int shake;
    public murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent sound;
    public float speed;
    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack stack;
    public float standingEyeHeight;
    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState stateAtPos;
    public boolean submergedInWater;
    public java.util.Optional supportingBlockPos;
    public int timeUntilRegen;
    public boolean touchingWater;
    public java.util.UUID uuid;
    public java.lang.String uuidString;
    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity vehicle;
    public boolean velocityDirty;
    public boolean velocityModified;
    public boolean verticalCollision;
    public boolean wasInPowderSnow;
    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack weapon;

    public PersistentProjectileEntity(murat.simv2.simulation.mirror.net.minecraft.entity.EntityType p0, double p1, double p2, double p3, murat.simv2.simulation.mirror.net.minecraft.world.World p4, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p5, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p6) {
    }

    public PersistentProjectileEntity(murat.simv2.simulation.mirror.net.minecraft.entity.EntityType p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1, murat.simv2.simulation.mirror.net.minecraft.world.World p2, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p3, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p4) {
    }

    public PersistentProjectileEntity(murat.simv2.simulation.mirror.net.minecraft.entity.EntityType p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1) {
    }

    public void addAirTravelEffects() {
    }

    public boolean addCommandTag(java.lang.String p0) {
        return false;
    }

    public void addFlapEffects() {
    }

    public void addPassenger(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void addPortalChunkTicketAt(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
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

    public void age() {
    }

    public void animateDamage(float p0) {
    }

    public static void applyBubbleColumnEffects(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, boolean p1) {
    }

    public static void applyBubbleColumnSurfaceEffects(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
    }

    public void applyCollision(murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult p0) {
    }

    public void applyDamageModifier(float p0) {
    }

    public void applyDrag(float p0) {
    }

    public void applyGravity() {
    }

    public float applyMirror(java.lang.Object p0) {
        return 0.0F;
    }

    public float applyRotation(java.lang.Object p0) {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack asItemStack() {
        return null;
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

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d calculateVelocity(double p0, double p1, double p2, float p3, float p4) {
        return null;
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

    public boolean canBreakBlocks(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
        return false;
    }

    public boolean canExplosionDestroyBlock(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3, float p4) {
        return false;
    }

    public boolean canFreeze() {
        return false;
    }

    public boolean canHit() {
        return false;
    }

    public boolean canHit(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public boolean canModifyAt(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public boolean canMoveVoluntarily() {
        return false;
    }

    public boolean canSprintAsVehicle() {
        return false;
    }

    public boolean canStartRiding(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public boolean canTeleportBetween(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1) {
        return false;
    }

    public boolean canUsePortals(boolean p0) {
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

    public void clearPiercingStatus() {
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

    public java.lang.Object createSpawnPacket(java.lang.Object p0) {
        return null;
    }

    public boolean damage(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1, float p2) {
        return false;
    }

    public boolean deflectsAgainstWorldBorder() {
        return false;
    }

    public boolean deflect(murat.simv2.simulation.mirror.net.minecraft.entity.ProjectileDeflection p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p2, boolean p3) {
        return false;
    }

    public void defrost() {
    }

    public void detach() {
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

    public murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity dropItem(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, java.lang.Object p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity dropItem(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, java.lang.Object p1, int p2) {
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

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public void extinguishWithSound() {
    }

    public void extinguish() {
    }

    public void fall() {
    }

    public void fall(double p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3) {
    }

    public static java.lang.Object fromName(java.lang.String p0) {
        return null;
    }

    public static java.lang.Object fromProfile(com.mojang.authlib.GameProfile p0) {
        return null;
    }

    public int getAir() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityAttachments getAttachments() {
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

    public java.lang.Object getCommandSource(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
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

    public java.lang.Object getDamageSources() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker getDataTracker() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getDefaultItemStack() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getDefaultName() {
        return null;
    }

    public int getDefaultPortalCooldown() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions getDimensions(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getDisplayName() {
        return null;
    }

    public float getDragInWater() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getEffectCause() {
        return null;
    }

    public float getEffectiveExplosionResistance(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3, murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p4, float p5) {
        return 0.0F;
    }

    public java.lang.Object getEntityCollision(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.World getEntityWorld() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getEntity(java.util.UUID p0) {
        return null;
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

    public double getGravity() {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getHandPosOffset(murat.simv2.simulation.mirror.net.minecraft.item.Item p0) {
        return null;
    }

    public float getHeadYaw() {
        return 0.0F;
    }

    public float getHeight() {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent getHighSpeedSplashSound() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent getHitSound() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction getHorizontalFacing() {
        return null;
    }

    public java.lang.Object getHoverEvent() {
        return null;
    }

    public int getId() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.PositionInterpolator getInterpolator() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getItemStack() {
        return null;
    }

    public float getJumpVelocityMultiplier() {
        return 0.0F;
    }

    public it.unimi.dsi.fastutil.doubles.DoubleDoubleImmutablePair getKnockback(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState getLandingBlockState() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getLandingPos() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getLastRenderPos() {
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

    public java.util.Optional getLootTableKey() {
        return null;
    }

    public int getMaxAir() {
        return 0;
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

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getMovement() {
        return null;
    }

    public java.lang.String getNameForScoreboard() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.Text getName() {
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

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getOwner() {
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

    public byte getPierceLevel() {
        return (byte) 0;
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

    public int getPlayerPassengers() {
        return 0;
    }

    public int getPortalCooldown() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getPosWithYOffset(float p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose getPose() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getPos() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ProjectileDeflection getProjectileDeflection(murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity p0) {
        return null;
    }

    public double getRandomBodyY() {
        return 0.0D;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random getRandom() {
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

    public java.lang.String getSavedEntityId() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.Team getScoreboardTeam() {
        return null;
    }

    public java.lang.Object getServer() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory getSoundCategory() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent getSound() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent getSplashSound() {
        return null;
    }

    public java.lang.Object getStackReference(int p0) {
        return null;
    }

    public float getStandingEyeHeight() {
        return 0.0F;
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

    public boolean hasVehicle() {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.ProjectileDeflection hitOrDeflect(murat.simv2.simulation.mirror.net.minecraft.util.hit.HitResult p0) {
        return null;
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

    public boolean isAlive() {
        return false;
    }

    public boolean isAlwaysInvulnerableTo(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0) {
        return false;
    }

    public boolean isAttackable() {
        return false;
    }

    public boolean isBeingRainedOn() {
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

    public boolean isCritical() {
        return false;
    }

    public boolean isCustomNameVisible() {
        return false;
    }

    public boolean isDescending() {
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

    public boolean isImmuneToExplosion(java.lang.Object p0) {
        return false;
    }

    public boolean isInFluid() {
        return false;
    }

    public boolean isInGround() {
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

    public boolean isInvulnerable() {
        return false;
    }

    public boolean isLiving() {
        return false;
    }

    public boolean isLogicalSideForUpdatingMovement() {
        return false;
    }

    public boolean isNoClip() {
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

    public boolean isOwner(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
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

    public void kill(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public void knockback(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1) {
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

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movementInputToVelocity(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, float p1, float p2) {
        return null;
    }

    public void move(murat.simv2.simulation.mirror.net.minecraft.entity.MovementType p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    public boolean occludeVibrationSignals() {
        return false;
    }

    public void onBlockCollision(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
    }

    public void onBlockHitEnchantmentEffects(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2) {
    }

    public void onBlockHit(murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult p0) {
    }

    public void onBroken(murat.simv2.simulation.mirror.net.minecraft.item.Item p0) {
    }

    public void onBubbleColumnCollision(boolean p0) {
    }

    public void onBubbleColumnSurfaceCollision(boolean p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
    }

    public void onCollision(murat.simv2.simulation.mirror.net.minecraft.util.hit.HitResult p0) {
    }

    public void onDamaged(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0) {
    }

    public void onDataTrackerUpdate(java.util.List p0) {
    }

    public void onDeflected(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, boolean p1) {
    }

    public void onEntityHit(java.lang.Object p0) {
    }

    public void onExplodedBy(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void onHit(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
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

    public void onRemoved() {
    }

    public void onRemove(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p0) {
    }

    public void onSpawnPacket(java.lang.Object p0) {
    }

    public void onStartedTrackingBy(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
    }

    public void onStoppedTrackingBy(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
    }

    public void onStruckByLightning(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, java.lang.Object p1) {
    }

    public void onSwimmingStart() {
    }

    public void onTrackedDataSet(murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData p0) {
    }

    public void playCombinationStepSounds(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1) {
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

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d positionInPortal(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0, java.lang.Object p1) {
        return null;
    }

    public void pushAwayFrom(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
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

    public void serverDamage(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1) {
    }

    public void setAir(int p0) {
    }

    public void setAngles(float p0, float p1) {
    }

    public boolean setApplicableComponent(java.lang.Object p0, java.lang.Object p1) {
        return false;
    }

    public void setBodyYaw(float p0) {
    }

    public void setBoundingBox(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0) {
    }

    public void setChangeListener(java.lang.Object p0) {
    }

    public void setComponent(java.lang.Object p0, java.lang.Object p1) {
    }

    public void setCritical(boolean p0) {
    }

    public void setCustomNameVisible(boolean p0) {
    }

    public void setCustomName(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public void setDamage(double p0) {
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

    public void setInGround(boolean p0) {
    }

    public void setInPowderSnow(boolean p0) {
    }

    public void setInvisible(boolean p0) {
    }

    public void setInvulnerable(boolean p0) {
    }

    public void setLastPositionAndAngles(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, float p1, float p2) {
    }

    public void setMovement(boolean p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2) {
    }

    public void setMovement(boolean p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    public void setNoClip(boolean p0) {
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

    public void setOwner(java.util.UUID p0) {
    }

    public void setOwner(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void setPierceLevel(byte p0) {
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

    public void setProjectileFlag(int p0, boolean p1) {
    }

    public void setRemoved(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p0) {
    }

    public static void setRenderDistanceMultiplier(double p0) {
    }

    public void setRotation(float p0, float p1) {
    }

    public void setSilent(boolean p0) {
    }

    public void setSneaking(boolean p0) {
    }

    public void setSound(murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p0) {
    }

    public void setSprinting(boolean p0) {
    }

    public void setStack(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    public void setSwimming(boolean p0) {
    }

    public void setUuid(java.util.UUID p0) {
    }

    public void setVelocityClient(double p0, double p1, double p2) {
    }

    public void setVelocity(double p0, double p1, double p2) {
    }

    public void setVelocity(double p0, double p1, double p2, float p3, float p4) {
    }

    public void setVelocity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, float p1, float p2, float p3, float p4, float p5) {
    }

    public void setVelocity(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
    }

    public void setWorld(murat.simv2.simulation.mirror.net.minecraft.world.World p0) {
    }

    public void setYaw(float p0) {
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

    public boolean shouldFall() {
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

    public boolean shouldTickBlockCollision() {
        return false;
    }

    public boolean sidedDamage(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1) {
        return false;
    }

    public void slowMovement(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    public static void spawnBubbleColumnParticles(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
    }

    public void spawnBubbleParticles(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
    }

    public void spawnSprintingParticles() {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity spawnWithVelocity(murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2, double p3, double p4, double p5, float p6, float p7) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity spawnWithVelocity(murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity.ProjectileCreator p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p3, double p4, double p5, double p6, float p7, float p8) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity spawnWithVelocity(murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity.ProjectileCreator p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p3, float p4, float p5, float p6) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity spawn(murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity spawn(murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2, java.util.function.Consumer p3) {
        return null;
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

    public void stopRiding() {
    }

    public java.util.stream.Stream streamPassengersAndSelf() {
        return null;
    }

    public java.util.stream.Stream streamSelfAndPassengers() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity teleportTo(java.lang.Object p0) {
        return null;
    }

    public boolean teleport(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, double p1, double p2, double p3, java.util.Set p4, float p5, float p6, boolean p7) {
        return false;
    }

    public void tickBlockCollision() {
    }

    public void tickBlockCollision(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    public void tickInVoid() {
    }

    public void tickPortalCooldown() {
    }

    public void tickPortalTeleportation() {
    }

    public void tickRiding() {
    }

    public void tick() {
    }

    public java.lang.String toString() {
        return null;
    }

    public void triggerProjectileSpawned(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
    }

    public boolean tryPickup(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
        return false;
    }

    public void tryUsePortal(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
    }

    public void unsetRemoved() {
    }

    public void updateEventHandler(java.util.function.BiConsumer p0) {
    }

    public void updateKilledAdvancementCriterion(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1) {
    }

    public void updateLastAngles() {
    }

    public void updateLastPosition() {
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

    public void updateRotation() {
    }

    public static float updateRotation(float p0, float p1) {
        return 0.0F;
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

    public void writeCustomDataToNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound writeNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
        return null;
    }

    public PersistentProjectileEntity() {
    }

    public static class PickupPermission {
        public static murat.simv2.simulation.mirror.net.minecraft.entity.projectile.PersistentProjectileEntity.PickupPermission ALLOWED;
        public static com.mojang.serialization.Codec CODEC;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.projectile.PersistentProjectileEntity.PickupPermission CREATIVE_ONLY;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.projectile.PersistentProjectileEntity.PickupPermission DISALLOWED;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.projectile.PersistentProjectileEntity.PickupPermission[] field_7591;

        public PickupPermission(java.lang.String p0, int p1) {
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.projectile.PersistentProjectileEntity.PickupPermission fromOrdinal(int p0) {
            return null;
        }

        public int ordinal() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.projectile.PersistentProjectileEntity.PickupPermission valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.projectile.PersistentProjectileEntity.PickupPermission[] values() {
            return null;
        }

        public PickupPermission() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
