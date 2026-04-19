package murat.simv2.simulation.mirror.net.minecraft.entity.vehicle;

// Generated mirror stub for simulation closure.
public class ChestRaftEntity {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<java.lang.Integer> DAMAGE_WOBBLE_SIDE;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<java.lang.Float> DAMAGE_WOBBLE_STRENGTH;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<java.lang.Integer> DAMAGE_WOBBLE_TICKS;
    public static float DEFAULT_FRICTION;
    public static int DEFAULT_MIN_FREEZE_DAMAGE_TICKS;
    public static int DEFAULT_PORTAL_COOLDOWN;
    public static double EMIT_SOUND_EVENT_PADDLE_ROTATION;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<java.lang.Byte> FLAGS;
    public static int FREEZING_DAMAGE_INTERVAL;
    public static int GLIDING_FLAG_INDEX;
    public static int GLOWING_FLAG_INDEX;
    public static java.lang.String ID_KEY;
    public static int MAX_COMMAND_TAGS;
    public static int MAX_RIDING_COOLDOWN;
    public static float MIN_RISING_BUBBLE_COLUMN_SPEED;
    public static int ON_FIRE_FLAG_INDEX;
    public static java.lang.String PASSENGERS_KEY;
    public static murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose> POSE;
    public static java.lang.String UUID_KEY;
    public int age;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos chunkPos;
    public boolean collidedSoftly;
    public murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker dataTracker;
    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions dimensions;
    public float distanceTraveled;
    public double fallDistance;
    public static float field_44870;
    public static double field_44871;
    public static double field_44872;
    public static int field_49073;
    public static int field_49791;
    public static int field_54427;
    public static int field_54445;
    public static int field_54447;
    public boolean firstUpdate;
    public it.unimi.dsi.fastutil.objects.Object2DoubleMap<murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey<murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid>> fluidHeight;
    public boolean forceUpdateSupportingBlockPos;
    public boolean groundCollision;
    public boolean horizontalCollision;
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
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movementMultiplier;
    public boolean noClip;
    public long pistonMovementTick;
    public murat.simv2.simulation.mirror.net.minecraft.world.dimension.PortalManager portalManager;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d pos;
    public murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random random;
    public int ridingCooldown;
    public float speed;
    public float standingEyeHeight;
    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState stateAtPos;
    public boolean submergedInWater;
    public java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos> supportingBlockPos;
    public int timeUntilRegen;
    public boolean touchingWater;
    public java.util.UUID uuid;
    public java.lang.String uuidString;
    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity vehicle;
    public boolean velocityDirty;
    public boolean velocityModified;
    public boolean verticalCollision;
    public boolean wasInPowderSnow;

    public ChestRaftEntity(murat.simv2.simulation.mirror.net.minecraft.entity.EntityType p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, java.util.function.Supplier p2) {
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

    public void animateDamage(float p0) {
    }

    public static void applyBubbleColumnEffects(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, boolean p1) {
    }

    public static void applyBubbleColumnSurfaceEffects(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
    }

    public void applyGravity() {
    }

    public void applyLeashElasticity(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, float p1) {
    }

    public float applyMirror(murat.simv2.simulation.mirror.net.minecraft.util.BlockMirror p0) {
        return 0.0F;
    }

    public float applyRotation(murat.simv2.simulation.mirror.net.minecraft.util.BlockRotation p0) {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.Item asItem() {
        return null;
    }

    public void attachLeash(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, boolean p1) {
    }

    public void attemptTickInVoid() {
    }

    public void baseTick() {
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

    public static boolean canCollide(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
        return false;
    }

    public boolean canExplosionDestroyBlock(murat.simv2.simulation.mirror.net.minecraft.world.explosion.Explosion p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3, float p4) {
        return false;
    }

    public boolean canFreeze() {
        return false;
    }

    public boolean canHit() {
        return false;
    }

    public boolean canLeashAttachTo() {
        return false;
    }

    public boolean canModifyAt(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public boolean canMoveVoluntarily() {
        return false;
    }

    public boolean canPlayerAccess(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
        return false;
    }

    public static boolean canPlayerUse(murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1) {
        return false;
    }

    public static boolean canPlayerUse(murat.simv2.simulation.mirror.net.minecraft.block.entity.BlockEntity p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p1, float p2) {
        return false;
    }

    public boolean canPlayerUse(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
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

    public boolean canTransferTo(murat.simv2.simulation.mirror.net.minecraft.inventory.Inventory p0, int p1, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p2) {
        return false;
    }

    public boolean canUsePortals(boolean p0) {
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

    public void clampPassengerYaw(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void clearInventory() {
    }

    public void clear() {
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

    public boolean containsAny(java.util.Set p0) {
        return false;
    }

    public boolean containsAny(java.util.function.Predicate p0) {
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

    public int count(murat.simv2.simulation.mirror.net.minecraft.item.Item p0) {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.screen.ScreenHandler createMenu(int p0, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerInventory p1, murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.network.packet.Packet createSpawnPacket(murat.simv2.simulation.mirror.net.minecraft.server.network.EntityTrackerEntry p0) {
        return null;
    }

    public boolean damage(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1, float p2) {
        return false;
    }

    public void defrost() {
    }

    public void detachLeashWithoutDrop() {
    }

    public void detachLeash() {
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

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public void extinguishWithSound() {
    }

    public void extinguish() {
    }

    public void fall(double p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p2, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p3) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreHolder fromName(java.lang.String p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.scoreboard.ScoreHolder fromProfile(com.mojang.authlib.GameProfile p0) {
        return null;
    }

    public void generateInventoryLoot(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
    }

    public void generateLoot(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
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

    public int getDamageWobbleSide() {
        return 0;
    }

    public float getDamageWobbleStrength() {
        return 0.0F;
    }

    public int getDamageWobbleTicks() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker getDataTracker() {
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

    public float getEffectiveExplosionResistance(murat.simv2.simulation.mirror.net.minecraft.world.explosion.Explosion p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3, murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p4, float p5) {
        return 0.0F;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.World getEntityWorld() {
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

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Direction getHorizontalFacing() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.text.HoverEvent getHoverEvent() {
        return null;
    }

    public int getId() {
        return 0;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.PositionInterpolator getInterpolator() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.inventory.StackReference getInventoryStackReference(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getInventoryStack(int p0) {
        return null;
    }

    public java.lang.Object getInventory() {
        return null;
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

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getLastRenderPos() {
        return null;
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

    public java.util.Optional getLootTableKey() {
        return null;
    }

    public long getLootTableSeed() {
        return 0L;
    }

    public murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey getLootTable() {
        return null;
    }

    public int getMaxAir() {
        return 0;
    }

    public int getMaxCountPerStack() {
        return 0;
    }

    public int getMaxCount(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
        return 0;
    }

    public int getMaxPassengers() {
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

    public float getNearbySlipperiness() {
        return 0.0F;
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

    public murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent getPaddleSound() {
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

    public double getPassengerAttachmentY(murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions p0) {
        return 0.0D;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getPassengerDismountOffset(double p0, double p1, float p2) {
        return null;
    }

    public float getPassengerHorizontalOffset() {
        return 0.0F;
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

    public java.lang.String getSavedEntityId() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.Team getScoreboardTeam() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.server.MinecraftServer getServer() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory getSoundCategory() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent getSplashSound() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.inventory.StackReference getStackReference(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getStack(int p0) {
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

    public float getWaterHeightBelow() {
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

    public void igniteByLava() {
    }

    public void initDataTracker(murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.Builder p0) {
    }

    public void initPosition(double p0, double p1, double p2) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult interactAt(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.Hand p2) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult interact(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.Hand p1) {
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

    public boolean isCustomNameVisible() {
        return false;
    }

    public boolean isDescending() {
        return false;
    }

    public boolean isEmpty() {
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

    public boolean isImmuneToExplosion(murat.simv2.simulation.mirror.net.minecraft.world.explosion.Explosion p0) {
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

    public boolean isInventoryEmpty() {
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

    public boolean isLeashed() {
        return false;
    }

    public boolean isLiving() {
        return false;
    }

    public boolean isLogicalSideForUpdatingMovement() {
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

    public boolean isPaddleMoving(int p0) {
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

    public boolean isSmallerThanBoat(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
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

    public boolean isValid(int p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return false;
    }

    public java.util.Iterator iterator() {
        return null;
    }

    public void killAndDropItem(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.item.Item p1) {
    }

    public void killAndDropSelf(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1) {
    }

    public void kill(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    public float lerpBubbleWobble(float p0) {
        return 0.0F;
    }

    public float lerpPaddlePhase(int p0, float p1) {
        return 0.0F;
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

    public void markDirty() {
    }

    public boolean mightBeLeashed() {
        return false;
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

    public void onBroken(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p1, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p2) {
    }

    public void onBubbleColumnCollision(boolean p0) {
    }

    public void onBubbleColumnSurfaceCollision(boolean p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
    }

    public void onClose(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
    }

    public void onDamaged(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0) {
    }

    public void onDataTrackerUpdate(java.util.List p0) {
    }

    public void onExplodedBy(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public boolean onKilledOther(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1) {
        return false;
    }

    public void onLanding() {
    }

    public void onLeashRemoved() {
    }

    public void onOpen(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
    }

    public void onPassengerLookAround(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void onPlayerCollision(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
    }

    public void onRemoved() {
    }

    public void onRemove(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p0) {
    }

    public void onShortLeashTick(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public void onSpawnPacket(murat.simv2.simulation.mirror.net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket p0) {
    }

    public void onStartedTrackingBy(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
    }

    public void onStoppedTrackingBy(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
    }

    public void onStruckByLightning(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.LightningEntity p1) {
    }

    public void onSwimmingStart() {
    }

    public void onTrackedDataSet(murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData p0) {
    }

    public void openInventory(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.ActionResult open(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
        return null;
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

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d positionInPortal(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockLocating.Rectangle p1) {
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

    public void readInventoryFromNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p1) {
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

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack removeInventoryStack(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack removeInventoryStack(int p0, int p1) {
        return null;
    }

    public void removePassenger(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack removeStack(int p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.item.ItemStack removeStack(int p0, int p1) {
        return null;
    }

    public void remove(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p0) {
    }

    public void requestTeleportAndDismount(double p0, double p1, double p2) {
    }

    public void requestTeleportOffset(double p0, double p1, double p2) {
    }

    public void requestTeleport(double p0, double p1, double p2) {
    }

    public void resetInventory() {
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

    public void setCustomNameVisible(boolean p0) {
    }

    public void setCustomName(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    public void setDamageWobbleSide(int p0) {
    }

    public void setDamageWobbleStrength(float p0) {
    }

    public void setDamageWobbleTicks(int p0) {
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

    public void setInputs(boolean p0, boolean p1, boolean p2, boolean p3) {
    }

    public void setInventoryStack(int p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
    }

    public void setInvisible(boolean p0) {
    }

    public void setInvulnerable(boolean p0) {
    }

    public void setLastPositionAndAngles(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, float p1, float p2) {
    }

    public void setLeashData(murat.simv2.simulation.mirror.net.minecraft.entity.Leashable.LeashData p0) {
    }

    public void setLootTableSeed(long p0) {
    }

    public void setLootTable(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0) {
    }

    public void setMovement(boolean p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2) {
    }

    public void setMovement(boolean p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
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

    public void setPaddlesMoving(boolean p0, boolean p1) {
    }

    public void setPitch(float p0) {
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

    public void setSprinting(boolean p0) {
    }

    public void setStack(int p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
    }

    public void setSwimming(boolean p0) {
    }

    public void setUnresolvedLeashHolderId(int p0) {
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

    public boolean shouldAlwaysKill(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0) {
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

    public boolean shouldTickBlockCollision() {
        return false;
    }

    public boolean sidedDamage(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1) {
        return false;
    }

    public int size() {
        return 0;
    }

    public void slowMovement(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    public static void spawnBubbleColumnParticles(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
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

    public void stopRiding() {
    }

    public java.util.stream.Stream streamPassengersAndSelf() {
        return null;
    }

    public java.util.stream.Stream streamSelfAndPassengers() {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity teleportTo(murat.simv2.simulation.mirror.net.minecraft.world.TeleportTarget p0) {
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

    public static void tickLeash(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
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

    public void tryUsePortal(murat.simv2.simulation.mirror.net.minecraft.block.Portal p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
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

    public void writeInventoryToNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, murat.simv2.simulation.mirror.net.minecraft.registry.RegistryWrapper.WrapperLookup p1) {
    }

    public void writeLeashDataToNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0, murat.simv2.simulation.mirror.net.minecraft.entity.Leashable.LeashData p1) {
    }

    public murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound writeNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
        return null;
    }

    public ChestRaftEntity() {
    }

    // END GENERATED MIRROR NESTED STUBS
}
