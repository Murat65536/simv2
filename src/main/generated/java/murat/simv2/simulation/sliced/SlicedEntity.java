package murat.simv2.simulation.sliced;

import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.objects.Object2DoubleArrayMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.entity.Entity.MoveEffect;
import net.minecraft.entity.Entity.QueuedCollisionCheck;
import net.minecraft.entity.Entity.RemovalReason;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.PositionInterpolator;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.profiler.Profilers;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import static net.minecraft.client.network.AbstractClientPlayerEntity.*;
import static net.minecraft.client.network.ClientPlayerEntity.*;
import static net.minecraft.entity.Entity.*;
import static net.minecraft.entity.LivingEntity.*;
import static net.minecraft.entity.player.PlayerEntity.*;

// Sliced from net.minecraft.entity.Entity
// Movement-relevant statements only (WALA backward slice + Spoon AST pruning)
// Generated — do not edit

public abstract class SlicedEntity {

    /** Real MC entity used as bridge for World and external API calls. */
    protected Entity entityBridge;

    private final EntityType<?> type;
    @Nullable
public Entity vehicle;
    private World world;
    public Vec3d pos;
    public BlockPos blockPos;
    private Vec3d velocity = Vec3d.ZERO;
    private float yaw;
    private float pitch;
    private Box boundingBox = NULL_BOX;
    private boolean onGround;
    public boolean horizontalCollision;
    public boolean verticalCollision;
    public boolean groundCollision;
    public Vec3d movementMultiplier = Vec3d.ZERO;
    @Nullable
private Entity.RemovalReason removalReason;
    public float distanceTraveled;
    public double fallDistance;
    private float nextStepSoundDistance = 1.0F;
    public double lastRenderX;
    public double lastRenderY;
    public double lastRenderZ;
    public boolean noClip;
    protected final Random random = Random.create();
    public boolean touchingWater;
    public Object2DoubleMap<TagKey<Fluid>> fluidHeight = new Object2DoubleArrayMap<>(2);
    private final Set<TagKey<Fluid>> submergedFluidTag = new HashSet();
    protected boolean firstUpdate = true;
    protected final DataTracker dataTracker;
    private boolean invulnerable;
    private final double[] pistonMovementDelta = new double[]{ 0.0, 0.0, 0.0 };
    public EntityDimensions dimensions;
    public float standingEyeHeight;
    public Optional<BlockPos> supportingBlockPos = Optional.empty();
    @Nullable
public BlockState stateAtPos = null;
    private final List<Entity.QueuedCollisionCheck> currentlyCheckedCollisions = new ObjectArrayList<>();

    public void addVelocityInternal(Vec3d velocity) {
        this.setVelocity(this.getVelocity().add(velocity));
    }

    public static Vec3d adjustMovementForCollisions(@Nullable
    Entity entity, Vec3d movement, Box entityBoundingBox, World world, List<VoxelShape> collisions) {
        List<VoxelShape> list = findCollisionsForMovement(entity, world, collisions, entityBoundingBox.stretch(movement));
        return Entity.adjustMovementForCollisions(movement, entityBoundingBox, list);
    }

    protected Vec3d adjustMovementForCollisions(Vec3d movement) {
        Box box = this.getBoundingBox();
        List<VoxelShape> list = this.getWorld().getEntityCollisions( (Entity) this.entityBridge, box.stretch(movement));
        Vec3d vec3d = (movement.lengthSquared() == 0.0) ? movement : Entity.adjustMovementForCollisions( (Entity) this.entityBridge, movement, box, this.getWorld(), list);
        boolean bl = movement.x != vec3d.x;
        boolean bl2 = movement.y != vec3d.y;
        boolean bl3 = movement.z != vec3d.z;
        boolean bl4 = bl2 && (movement.y < 0.0);
        if (((this.getStepHeight() > 0.0F) && (bl4 || this.isOnGround())) && (bl || bl3)) {
            Box box2 = (bl4) ? box.offset(0.0, vec3d.y, 0.0) : box;
            Box box3 = box2.stretch(movement.x, this.getStepHeight(), movement.z);
            if (!bl4) {
            }
            List<VoxelShape> list2 = findCollisionsForMovement( (Entity) this.entityBridge, this.world, list, box3);
            float f = ((float) (vec3d.y));
            float[] fs = collectStepHeights(box2, list2, this.getStepHeight(), f);
            for (float g : fs) {
                Vec3d vec3d2 = Entity.adjustMovementForCollisions(new Vec3d(movement.x, g, movement.z), box2, list2);
                if (vec3d2.horizontalLengthSquared() > vec3d.horizontalLengthSquared()) {
                    double d = box.minY - box2.minY;
                    return vec3d2.subtract(0.0, d, 0.0);
                }
            }
        }
        return vec3d;
    }

    protected static Vec3d adjustMovementForCollisions(Vec3d movement, Box entityBoundingBox, List<VoxelShape> collisions) {
        if (collisions.isEmpty()) {
            return movement;
        } else {
            Vec3d vec3d = Vec3d.ZERO;
            for (Direction.Axis axis : getAxisCheckOrder(movement)) {
                double d = movement.getComponentAlongAxis(axis);
                if (d != 0.0) {
                    double e = VoxelShapes.calculateMaxOffset(axis, entityBoundingBox.offset(vec3d), collisions, d);
                    vec3d = vec3d.withAxis(axis, e);
                }
            }
            return vec3d;
        }
    }

    protected Vec3d adjustMovementForPiston(Vec3d movement) {
        if (movement.lengthSquared() <= 1.0E-7) {
            return movement;
        } else {
            long l = this.getWorld().getTime();
            if (movement.x != 0.0) {
                double d = this.calculatePistonMovementFactor(Axis.X, movement.x);
                return Math.abs(d) <= 1.0E-5F ? Vec3d.ZERO : new Vec3d(d, 0.0, 0.0);
            } else if (movement.y != 0.0) {
                double d = this.calculatePistonMovementFactor(Axis.Y, movement.y);
                return Math.abs(d) <= 1.0E-5F ? Vec3d.ZERO : new Vec3d(0.0, d, 0.0);
            } else if (movement.z != 0.0) {
                double d = this.calculatePistonMovementFactor(Axis.Z, movement.z);
                return Math.abs(d) <= 1.0E-5F ? Vec3d.ZERO : new Vec3d(0.0, 0.0, d);
            } else {
                return Vec3d.ZERO;
            }
        }
    }

    protected void applyMoveEffect(Entity.MoveEffect moveEffect, Vec3d movement, BlockPos landingPos, BlockState landingState) {
        float g = ((float) (movement.length() * 0.6F));
        float h = ((float) (movement.horizontalLength() * 0.6F));
        BlockPos blockPos = this.getSteppingPos();
        BlockState blockState = this.getWorld().getBlockState(blockPos);
        boolean bl = this.canClimb(blockState);
        this.distanceTraveled += (bl) ? g : h;
        if ((this.distanceTraveled > this.nextStepSoundDistance) && (!blockState.isAir())) {
            boolean bl2 = blockPos.equals(landingPos);
            boolean bl3 = this.stepOnBlock(landingPos, landingState, moveEffect.playsSounds(), bl2, movement);
            if (!bl2) {
                bl3 |= this.stepOnBlock(blockPos, blockState, false, moveEffect.emitsGameEvents(), movement);
            }
        } else {
        }
    }

    protected Box calculateBoundingBox() {
        return this.calculateDefaultBoundingBox(this.pos);
    }

    protected Box calculateDefaultBoundingBox(Vec3d pos) {
        return this.dimensions.getBoxAt(pos);
    }

    /**
     * Calculates and sets the dimension (bounding box) of the entity and refreshes
     * its position.
     */
    public void calculateDimensions() {
        EntityDimensions entityDimensions = this.dimensions;
        EntityPose entityPose = this.getPose();
        EntityDimensions entityDimensions2 = this.getDimensions(entityPose);
        this.dimensions = entityDimensions2;
        this.refreshPosition();
        boolean bl = (entityDimensions2.width() <= 4.0F) && (entityDimensions2.height() <= 4.0F);
        if ((((((!this.world.isClient) && (!this.firstUpdate)) && (!this.noClip)) && bl) && ((entityDimensions2.width() > entityDimensions.width()) || (entityDimensions2.height() > entityDimensions.height()))) && (!(this instanceof SlicedPlayerEntity))) {
            this.recalculateDimensions(entityDimensions);
        }
    }

    private double calculatePistonMovementFactor(Direction.Axis axis, double offsetFactor) {
        int i = axis.ordinal();
        double d = MathHelper.clamp(offsetFactor + this.pistonMovementDelta[i], -0.51, 0.51);
        offsetFactor = d - this.pistonMovementDelta[i];
        return offsetFactor;
    }

    private boolean canClimb(BlockState state) {
        return state.isIn(BlockTags.CLIMBABLE) || state.isOf(Blocks.POWDER_SNOW);
    }

    /**
     * {@return whether the entity can freeze}
     *
     * @implNote Entities cannot be frozen if they are in the {@link net.minecraft.registry.tag.EntityTypeTags#FREEZE_IMMUNE_ENTITY_TYPES} tag. In addition to this, {@link LivingEntity} cannot be frozen if they are spectator or if they wear an
    item inside {@link net.minecraft.registry.tag.ItemTags#FREEZE_IMMUNE_WEARABLES} tag.
     */
    public boolean canFreeze() {
        return !this.getType().isIn(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES);
    }

    void checkWaterState() {
        if ((this.getVehicle() instanceof AbstractBoatEntity abstractBoatEntity) && (!abstractBoatEntity.isSubmergedInWater())) {
            this.touchingWater = false;
        } else if (this.updateMovementInFluid(FluidTags.WATER, 0.014)) {
            if ((!this.touchingWater) && (!this.firstUpdate)) {
                this.onSwimmingStart();
            }
            this.onLanding();
            this.touchingWater = true;
        } else {
            this.touchingWater = false;
        }
    }

    /**
     * {@return whether the bounding box with the given offsets do not collide with
     * blocks or fluids}
     */
    public boolean doesNotCollide(double offsetX, double offsetY, double offsetZ) {
        return this.doesNotCollide(this.getBoundingBox().offset(offsetX, offsetY, offsetZ));
    }

    private boolean doesNotCollide(Box box) {
        return this.getWorld().isSpaceEmpty( (Entity) this.entityBridge, box) && (!this.getWorld().containsFluid(box));
    }

    /**
     * Called when the entity falls. Flying mobs should override this to do nothing.
     *
     * @implNote If on ground, this calls {@link net.minecraft.block.Block#onLandedUpon}, which can add or
    reduce fall damage, emits {@link net.minecraft.world.event.GameEvent#HIT_GROUND}, then calls {@link #onLanding}.
    Otherwise, if {@code heightDifference} is negative, it subtracts that value from
    {@link #fallDistance}.
     */
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
        if ((!this.isTouchingWater()) && (heightDifference < 0.0)) {
            this.fallDistance -= ((float) (heightDifference));
        }
        if (onGround) {
            this.onLanding();
        }
    }

    private static List<VoxelShape> findCollisionsForMovement(@Nullable
    Entity entity, World world, List<VoxelShape> regularCollisions, Box movingEntityBoundingBox) {
        Builder<VoxelShape> builder = ImmutableList.builderWithExpectedSize(regularCollisions.size() + 1);
        WorldBorder worldBorder = world.getWorldBorder();
        boolean bl = (entity != null) && worldBorder.canCollide(entity, movingEntityBoundingBox);
        return builder.build();
    }

    public BlockPos getBlockPos() {
        return this.blockPos;
    }

    /**
     * {@return the block state at the entity's position}
     *
     * <p>The result is cached.
     *
     * @see #getBlockPos
     * @see #getLandingBlockState
     * @see #getSteppingBlockState
     */
    public BlockState getBlockStateAtPos() {
        if (this.stateAtPos == null) {
            this.stateAtPos = this.getWorld().getBlockState(this.getBlockPos());
        }
        return this.stateAtPos;
    }

    public Box getBoundingBox() {
        return this.boundingBox;
    }

    /**
     * {@return the passenger in control of this entity, or {@code null} if there is none}
     *
     * <p>Rideable entities should override this to return the entity. This is
     * usually {@code #getFirstPassenger}.
     *
     * @see #hasControllingPassenger
     * @see #getPassengerList
     * @see #getFirstPassenger
     */
    @Nullable
    public LivingEntity getControllingPassenger() {
        return null;
    }

    public DataTracker getDataTracker() {
        return this.dataTracker;
    }

    /**
     * {@return the dimensions of the entity with the given {@code pose}}
     *
     * @see #getWidth
     * @see #getHeight
     */
    public EntityDimensions getDimensions(EntityPose pose) {
        return this.type.getDimensions();
    }

    public double getFinalGravity() {
        return this.hasNoGravity() ? 0.0 : this.getGravity();
    }

    /**
     * {@return the entity flag with index {@code flag}}
     *
     * <p>Entity flag is used to track whether the entity is sneaking, sprinting, invisible,
     * etc.
     */
    protected boolean getFlag(int index) {
        return (this.dataTracker.get(FLAGS) & (1 << index)) != 0;
    }

    /**
     * {@return the height of the fluid in {@code fluid} tag}
     */
    public double getFluidHeight(TagKey<Fluid> fluid) {
        return this.fluidHeight.getDouble(fluid);
    }

    /**
     * {@return how long the entity is freezing, in ticks}
     *
     * <p>If this is equal to or above {@link #getMinFreezeDamageTicks}, the entity
     * receives freezing damage.
     *
     * @see #setFrozenTicks
     * @see #getFreezingScale
     * @see #isFrozen
     * @see #getMinFreezeDamageTicks
     */
    public int getFrozenTicks() {
        return this.dataTracker.get(FROZEN_TICKS);
    }

    /**
     * {@return the height of the entity's current dimension}
     */
    public float getHeight() {
        return this.dimensions.height();
    }

    protected float getJumpVelocityMultiplier() {
        float f = this.getWorld().getBlockState(this.getBlockPos()).getBlock().getJumpVelocityMultiplier();
        float g = this.getWorld().getBlockState(this.getVelocityAffectingPos()).getBlock().getJumpVelocityMultiplier();
        return f == 1.0 ? g : f;
    }

    /**
     * {@return the landing position}
     *
     * @implNote Landing position is the entity's position, with {@code 0.2} subtracted
    from the Y coordinate. This means that, for example, if a player is on a carpet on
    a soul soil, the soul soil's position would be returned.
     * @see #getSteppingPos()
     * @see #getLandingBlockState()
     */
    @Deprecated
    public BlockPos getLandingPos() {
        return this.getPosWithYOffset(0.2F);
    }

    public Vec3d getLastRenderPos() {
        return new Vec3d(this.lastRenderX, this.lastRenderY, this.lastRenderZ);
    }

    /**
     * {@return the maximum amount of air the entity can hold, in ticks}
     *
     * <p>Most entities have the max air of 300 ticks, or 15 seconds.
     * {@link net.minecraft.entity.passive.DolphinEntity} has 4800 ticks or 4
     * minutes; {@link net.minecraft.entity.passive.AxolotlEntity} has 6000 ticks
     * or 5 minutes. Note that this does not include enchantments.
     *
     * @see #getAir
     * @see #setAir
     */
    public int getMaxAir() {
        return 300;
    }

    /**
     * {@return how long it takes for the entity to be completely frozen and receive
     * freezing damage, in ticks}
     *
     * @see #getFrozenTicks
     * @see #setFrozenTicks
     * @see #getFreezingScale
     * @see #isFrozen
     */
    public int getMinFreezeDamageTicks() {
        return 140;
    }

    public float getPitch() {
        return this.pitch;
    }

    /**
     * {@return the exact position of the entity}
     *
     * @see #getSyncedPos
     * @see #getBlockPos
     * @see #getChunkPos
     */
    public Vec3d getPos() {
        return this.pos;
    }

    protected BlockPos getPosWithYOffset(float offset) {
        if (this.supportingBlockPos.isPresent()) {
            BlockPos blockPos = ((BlockPos) (this.supportingBlockPos.get()));
            if (!(offset > 1.0E-5F)) {
                return blockPos;
            } else {
                BlockState blockState = this.getWorld().getBlockState(blockPos);
                return (((!(offset <= 0.5)) || (!blockState.isIn(BlockTags.FENCES))) && (!blockState.isIn(BlockTags.WALLS))) && (!(blockState.getBlock() instanceof FenceGateBlock)) ? blockPos.withY(MathHelper.floor(this.pos.y - offset)) : blockPos;
            }
        } else {
            int i = MathHelper.floor(this.pos.x);
            int j = MathHelper.floor(this.pos.y - offset);
            int k = MathHelper.floor(this.pos.z);
            return new BlockPos(i, j, k);
        }
    }

    public EntityPose getPose() {
        return this.dataTracker.get(POSE);
    }

    public Vec2f getRotationClient() {
        return new Vec2f(this.getPitch(), this.getYaw());
    }

    public Vec3d getRotationVecClient() {
        return Vec3d.fromPolar(this.getRotationClient());
    }

    public Vec3d getRotationVector() {
        return this.getRotationVector(this.getPitch(), this.getYaw());
    }

    public Vec3d getRotationVector(float pitch, float yaw) {
        float f = pitch * ((float) (Math.PI / 180.0));
        float g = (-yaw) * ((float) (Math.PI / 180.0));
        float h = MathHelper.cos(g);
        float i = MathHelper.sin(g);
        float j = MathHelper.cos(f);
        float k = MathHelper.sin(f);
        return new Vec3d(i * j, -k, h * j);
    }

    /**
     * {@return the standing eye height}
     *
     * <p>This is used for calculating the leash offset.
     *
     * @see #getLeashOffset
     */
    public float getStandingEyeHeight() {
        return this.standingEyeHeight;
    }

    /**
     * {@return the stepping position}
     *
     * @implNote Stepping position is the entity's position, with {@code 1e-05} subtracted
    from the Y coordinate. This means that, for example, if a player is on a carpet on
    a soul soil, the carpet's position would be returned.
     * @see #getLandingPos()
     * @see #getSteppingBlockState()
     */
    public BlockPos getSteppingPos() {
        return this.getPosWithYOffset(1.0E-5F);
    }

    /**
     * {@return the minimum submerged height of this entity in fluid so that it
     * would be affected by fluid physics}
     *
     * @apiNote This is also used by living entities for checking whether to
    start swimming.
     * @implNote This implementation returns {@code 0.4} if its
    {@linkplain #getStandingEyeHeight standing eye height} is larger than
    {@code 0.4}; otherwise it returns {@code 0.0} for shorter entities.
    The swim height of 0 allows short entities like baby animals
    to start swimming to avoid suffocation.
     */
    public double getSwimHeight() {
        return this.getStandingEyeHeight() < 0.4 ? 0.0 : 0.4;
    }

    /**
     * {@return the entity this entity rides, or {@code null} if there is none}
     *
     * @see #getRootVehicle
     * @see #getControllingVehicle
     */
    @Nullable
    public Entity getVehicle() {
        return this.vehicle;
    }

    public Vec3d getVelocity() {
        return this.velocity;
    }

    public BlockPos getVelocityAffectingPos() {
        return this.getPosWithYOffset(0.500001F);
    }

    protected float getVelocityMultiplier() {
        BlockState blockState = this.getWorld().getBlockState(this.getBlockPos());
        float f = blockState.getBlock().getVelocityMultiplier();
        if ((!blockState.isOf(Blocks.WATER)) && (!blockState.isOf(Blocks.BUBBLE_COLUMN))) {
            return f == 1.0 ? this.getWorld().getBlockState(this.getVelocityAffectingPos()).getBlock().getVelocityMultiplier() : f;
        } else {
            return f;
        }
    }

    /**
     * {@return the width of the entity's current dimension}
     */
    public float getWidth() {
        return this.dimensions.width();
    }

    public World getWorld() {
        return this.world;
    }

    public double getX() {
        return this.pos.x;
    }

    public double getY() {
        return this.pos.y;
    }

    public float getYaw() {
        return this.yaw;
    }

    public double getZ() {
        return this.pos.z;
    }

    /**
     * {@return whether the entity has no gravity}
     *
     * <p>Entities using {@link net.minecraft.entity.ai.control.FlightMoveControl} has
     * no gravity. This is saved under the {@code NoGravity} NBT key.
     */
    public boolean hasNoGravity() {
        return this.dataTracker.get(NO_GRAVITY);
    }

    /**
     * {@return whether this entity is riding an entity}
     *
     * <p>This is the opposite of {@link #hasPassengers}.
     *
     * @see #startRiding(Entity)
     * @see #startRiding(Entity, boolean)
     * @see #stopRiding
     * @see #hasPassengers
     */
    public boolean hasVehicle() {
        return this.getVehicle() != null;
    }

    protected boolean isAlwaysInvulnerableTo(DamageSource damageSource) {
        return ((this.isRemoved() || ((this.invulnerable && (!damageSource.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY))) && (!damageSource.isSourceCreativePlayer()))) || (damageSource.isIn(DamageTypeTags.IS_FIRE) && this.isFireImmune())) || (damageSource.isIn(DamageTypeTags.IS_FALL) && this.getType().isIn(EntityTypeTags.FALL_DAMAGE_IMMUNE));
    }

    /**
     * {@return whether it is raining at the entity's position}
     */
    boolean isBeingRainedOn() {
        BlockPos blockPos = this.getBlockPos();
        return this.getWorld().hasRain(blockPos) || this.getWorld().hasRain(BlockPos.ofFloored(blockPos.getX(), this.getBoundingBox().maxY, blockPos.getZ()));
    }

    /**
     * {@return whether the entity is immune to {@linkplain
     * net.minecraft.registry.tag.DamageTypeTags#IS_FIRE fire damage}}
     *
     * @see EntityType.Builder#makeFireImmune
     */
    public boolean isFireImmune() {
        return this.getType().isFireImmune();
    }

    /**
     * {@return whether the entity is frozen}
     *
     * <p>Frozen entities take freezing damage. Entity becomes frozen {@link #getMinFreezeDamageTicks} ticks after starting to freeze.
     *
     * @see #getFrozenTicks
     * @see #setFrozenTicks
     * @see #getFreezingScale
     * @see #getMinFreezeDamageTicks
     */
    public boolean isFrozen() {
        return this.getFrozenTicks() >= this.getMinFreezeDamageTicks();
    }

    /**
     * {@return whether the entity is in lava}
     */
    public boolean isInLava() {
        return (!this.firstUpdate) && (this.fluidHeight.getDouble(FluidTags.LAVA) > 0.0);
    }

    public boolean isInterpolating() {
        return (this.getInterpolator() != null) && this.getInterpolator().isInterpolating();
    }

    public boolean isLogicalSideForUpdatingMovement() {
        return this.world.isClient() ? this.isControlledByMainPlayer() : !this.isControlledByPlayer();
    }

    /**
     * {@return whether the entity is on the ground}
     */
    public boolean isOnGround() {
        return this.onGround;
    }

    public boolean isOnRail() {
        return false;
    }

    /**
     * {@return whether any part of this entity's bounding box is in an unloaded
     * region of the world the entity is in}
     *
     * @implNote This implementation expands this entity's bounding box by 1 in
    each axis and checks whether the expanded box's smallest enclosing
    axis-aligned integer box is fully loaded in the world.
     */
    public boolean isRegionUnloaded() {
        Box box = this.getBoundingBox().expand(1.0);
        int i = MathHelper.floor(box.minX);
        int j = MathHelper.ceil(box.maxX);
        int k = MathHelper.floor(box.minZ);
        int l = MathHelper.ceil(box.maxZ);
        return !this.getWorld().isRegionLoaded(i, k, j, l);
    }

    public boolean isRemoved() {
        return this.removalReason != null;
    }

    public boolean isSneaky() {
        return this.isSneaking();
    }

    /**
     * {@return whether the entity is sprinting}
     *
     * <p>Swimming is also considered as sprinting.
     *
     * #setSprinting
     */
    public boolean isSprinting() {
        return this.getFlag(SPRINTING_FLAG_INDEX);
    }

    /**
     * {@return whether the entity is submerged in a fluid in {@code fluidTag}}
     */
    public boolean isSubmergedIn(TagKey<Fluid> fluidTag) {
        return this.submergedFluidTag.contains(fluidTag);
    }

    /**
     * {@return whether the entity is swimming}
     *
     * <p>An entity is swimming if it is touching water, not riding any entities, and is
     * sprinting. Note that to start swimming, the entity must first be submerged in
     * water.
     *
     * @see #setSwimming
     */
    public boolean isSwimming() {
        return this.getFlag(SWIMMING_FLAG_INDEX);
    }

    /**
     * Returns whether this entity's hitbox is touching water fluid.
     */
    public boolean isTouchingWater() {
        return this.touchingWater;
    }

    /**
     * {@return whether this entity is touching water or is being rained on (but does not check
     * for a bubble column)}
     *
     * @see net.minecraft.entity.Entity#isTouchingWater()
     * @see net.minecraft.entity.Entity#isBeingRainedOn()
     * @see net.minecraft.entity.Entity#isInFluid()
     */
    public boolean isTouchingWaterOrRain() {
        return this.isTouchingWater() || this.isBeingRainedOn();
    }

    /**
     * Called when this entity is fall flying or on a lead.
     *
     * <p>Limits this entity's {@code fallDistance} if its downward velocity isn't fast enough
     * in order to prevent unwarranted fall damage.
     */
    public void limitFallDistance() {
        if ((this.getVelocity().getY() > (-0.5)) && (this.fallDistance > 1.0)) {
            this.fallDistance = 1.0;
        }
    }

    public void move(MovementType type, Vec3d movement) {
        if (this.noClip) {
            this.setPosition(this.getX() + movement.x, this.getY() + movement.y, this.getZ() + movement.z);
        } else {
            if (type == MovementType.PISTON) {
                movement = this.adjustMovementForPiston(movement);
                if (movement.equals(Vec3d.ZERO)) {
                }
            }
            Profiler profiler = Profilers.get();
            if (this.movementMultiplier.lengthSquared() > 1.0E-7) {
                movement = movement.multiply(this.movementMultiplier);
                this.setVelocity(Vec3d.ZERO);
            }
            movement = this.adjustMovementForSneaking(movement, type);
            Vec3d vec3d = this.adjustMovementForCollisions(movement);
            double d = vec3d.lengthSquared();
            if ((d > 1.0E-7) || ((movement.lengthSquared() - d) < 1.0E-7)) {
                if ((this.fallDistance != 0.0) && (d >= 1.0)) {
                    BlockHitResult blockHitResult = this.getWorld().raycast(new RaycastContext(this.getPos(), this.getPos().add(vec3d), ShapeType.FALLDAMAGE_RESETTING, FluidHandling.WATER, (Entity) this.entityBridge));
                    if (blockHitResult.getType() != Type.MISS) {
                        this.onLanding();
                    }
                }
                Vec3d vec3d2 = this.getPos();
                List<Entity.QueuedCollisionCheck> list = new ObjectArrayList<>();
                for (Direction.Axis axis : getAxisCheckOrder(vec3d)) {
                    double e = vec3d.getComponentAlongAxis(axis);
                    if (e != 0.0) {
                        Vec3d vec3d3 = vec3d2.offset(axis.getPositiveDirection(), e);
                    }
                }
                this.setPosition(vec3d2);
            }
            boolean bl = !MathHelper.approximatelyEquals(movement.x, vec3d.x);
            boolean bl2 = !MathHelper.approximatelyEquals(movement.z, vec3d.z);
            this.horizontalCollision = bl || bl2;
            if ((Math.abs(movement.y) > 0.0) || this.isLogicalSideForUpdatingMovement()) {
                this.verticalCollision = movement.y != vec3d.y;
                this.groundCollision = this.verticalCollision && (movement.y < 0.0);
                this.setMovement(this.groundCollision, this.horizontalCollision, vec3d);
            }
            BlockPos blockPos = this.getLandingPos();
            BlockState blockState = this.getWorld().getBlockState(blockPos);
            if (this.isLogicalSideForUpdatingMovement()) {
                this.fall(vec3d.y, this.isOnGround(), blockState, blockPos);
            }
            if (this.isRemoved()) {
            } else {
                if (this.horizontalCollision) {
                    Vec3d vec3d4 = this.getVelocity();
                    this.setVelocity(bl ? 0.0 : vec3d4.x, vec3d4.y, bl2 ? 0.0 : vec3d4.z);
                }
                if ((!this.getWorld().isClient()) || this.isLogicalSideForUpdatingMovement()) {
                    Entity.MoveEffect moveEffect = this.getMoveEffect();
                    if (moveEffect.hasAny() && (!this.hasVehicle())) {
                        this.applyMoveEffect(moveEffect, vec3d, blockPos, blockState);
                    }
                }
                float f = this.getVelocityMultiplier();
                this.setVelocity(this.getVelocity().multiply(f, 1.0, f));
            }
        }
    }

    /**
     * {@return a vector with the horizontal direction being {@code yaw} degrees and the
     * absolute value being {@code movementInput} normalized and multiplied by {@code speed}}
     */
    protected static Vec3d movementInputToVelocity(Vec3d movementInput, float speed, float yaw) {
        double d = movementInput.lengthSquared();
        if (d < 1.0E-7) {
            return Vec3d.ZERO;
        } else {
            Vec3d vec3d = (d > 1.0 ? movementInput.normalize() : movementInput).multiply(speed);
            float f = MathHelper.sin(yaw * ((float) (Math.PI / 180.0)));
            float g = MathHelper.cos(yaw * ((float) (Math.PI / 180.0)));
            return new Vec3d((vec3d.x * g) - (vec3d.z * f), vec3d.y, (vec3d.z * g) + (vec3d.x * f));
        }
    }

    /**
     * Called when the entity lands on a block.
     */
    public void onLanding() {
        this.fallDistance = 0.0;
    }

    public void onTrackedDataSet(TrackedData<?> data) {
        if (POSE.equals(data)) {
            this.calculateDimensions();
        }
    }

    public boolean recalculateDimensions(EntityDimensions previous) {
        EntityDimensions entityDimensions = this.getDimensions(this.getPose());
        Vec3d vec3d = this.getPos().add(0.0, previous.height() / 2.0, 0.0);
        double d = Math.max(0.0F, entityDimensions.width() - previous.width()) + 1.0E-6;
        double e = Math.max(0.0F, entityDimensions.height() - previous.height()) + 1.0E-6;
        VoxelShape voxelShape = VoxelShapes.cuboid(Box.of(vec3d, d, e, d));
        Optional<Vec3d> optional = this.world.findClosestCollision( (Entity) this.entityBridge, voxelShape, vec3d, entityDimensions.width(), entityDimensions.height(), entityDimensions.width());
        if (optional.isPresent()) {
            this.setPosition(((Vec3d) (optional.get())).add(0.0, (-entityDimensions.height()) / 2.0, 0.0));
        } else if ((entityDimensions.width() > previous.width()) && (entityDimensions.height() > previous.height())) {
            VoxelShape voxelShape2 = VoxelShapes.cuboid(Box.of(vec3d, d, 1.0E-6, d));
            Optional<Vec3d> optional2 = this.world.findClosestCollision( (Entity) this.entityBridge, voxelShape2, vec3d, entityDimensions.width(), previous.height(), entityDimensions.width());
            if (optional2.isPresent()) {
                this.setPosition(((Vec3d) (optional2.get())).add(0.0, ((-previous.height()) / 2.0) + 1.0E-6, 0.0));
            }
        }
    }

    protected void refreshPosition() {
        this.setPosition(this.pos.x, this.pos.y, this.pos.z);
    }

    @Deprecated
    public void serverDamage(DamageSource source, float amount) {
        if (this.world instanceof ServerWorld serverWorld) {
            this.damage(serverWorld, source, amount);
        }
    }

    public void setBoundingBox(Box boundingBox) {
        this.boundingBox = boundingBox;
    }

    public void setMovement(boolean onGround, boolean horizontalCollision, Vec3d movement) {
        this.onGround = onGround;
        this.horizontalCollision = horizontalCollision;
    }

    /**
     * Sets the position of this entity.
     *
     * <p>This should be used when overriding {@link #tick} to change the
     * entity's position; in other cases, use {@link #setPosition(double, double, double)}
     * or {@link #refreshPositionAndAngles(double, double, double, float, float)}.
     *
     * @see #setPosition(double, double, double)
     * @see #refreshPositionAndAngles(double, double, double, float, float)
     */
    public void setPos(double x, double y, double z) {
        if (((this.pos.x != x) || (this.pos.y != y)) || (this.pos.z != z)) {
            this.pos = new Vec3d(x, y, z);
            int i = MathHelper.floor(x);
            int j = MathHelper.floor(y);
            int k = MathHelper.floor(z);
            if (((i != this.blockPos.getX()) || (j != this.blockPos.getY())) || (k != this.blockPos.getZ())) {
                this.blockPos = new BlockPos(i, j, k);
                this.stateAtPos = null;
            }
        }
    }

    /**
     * Sets the position and refreshes the bounding box.
     *
     * <p>This should be called after creating an instance of non-living entities.
     * For living entities, {@link #refreshPositionAndAngles} should be used instead.
     *
     * @see #refreshPositionAndAngles
     * @see #teleportTo
     */
    public void setPosition(double x, double y, double z) {
        this.setPos(x, y, z);
        this.setBoundingBox(this.calculateBoundingBox());
    }

    /**
     * Sets the position and refreshes the bounding box.
     *
     * <p>This should be called after creating an instance of non-living entities.
     * For living entities, {@link #refreshPositionAndAngles} should be used instead.
     *
     * @see #refreshPositionAndAngles
     * @see #teleportTo
     */
    public void setPosition(Vec3d pos) {
        this.setPosition(pos.getX(), pos.getY(), pos.getZ());
    }

    public void setVelocity(double x, double y, double z) {
        this.setVelocity(new Vec3d(x, y, z));
    }

    public void setVelocity(Vec3d velocity) {
        this.velocity = velocity;
    }

    private boolean stepOnBlock(BlockPos pos, BlockState state, boolean playSound, boolean emitEvent, Vec3d movement) {
        if (state.isAir()) {
        } else {
            boolean bl = this.canClimb(state);
            if ((((this.isOnGround() || bl) || (this.isInSneakingPose() && (movement.y == 0.0))) || this.isOnRail()) && (!this.isSwimming())) {
            } else {
            }
        }
    }

    protected void tickBlockCollision() {
        if (this.currentlyCheckedCollisions.isEmpty()) {
            this.currentlyCheckedCollisions.add(new Entity.QueuedCollisionCheck(this.getLastRenderPos(), this.getPos()));
        } else {
        }
    }

    public boolean updateMovementInFluid(TagKey<Fluid> tag, double speed) {
        if (this.isRegionUnloaded()) {
            return false;
        } else {
            Box box = this.getBoundingBox().contract(0.001);
            int i = MathHelper.floor(box.minX);
            int j = MathHelper.ceil(box.maxX);
            int k = MathHelper.floor(box.minY);
            int l = MathHelper.ceil(box.maxY);
            int m = MathHelper.floor(box.minZ);
            int n = MathHelper.ceil(box.maxZ);
            double d = 0.0;
            boolean bl = this.isPushedByFluids();
            boolean bl2 = false;
            Vec3d vec3d = Vec3d.ZERO;
            int o = 0;
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            for (int p = i; p < j; p++) {
                for (int q = k; q < l; q++) {
                    for (int r = m; r < n; r++) {
                        FluidState fluidState = this.getWorld().getFluidState(mutable);
                        if (fluidState.isIn(tag)) {
                            double e = q + fluidState.getHeight(this.getWorld(), mutable);
                            if (e >= box.minY) {
                                d = Math.max(e - box.minY, d);
                                if (bl) {
                                    Vec3d vec3d2 = fluidState.getVelocity(this.getWorld(), mutable);
                                    if (d < 0.4) {
                                    }
                                    vec3d = vec3d.add(vec3d2);
                                    o++;
                                }
                            }
                        }
                    }
                }
            }
            if (vec3d.length() > 0.0) {
                if (o > 0) {
                    vec3d = vec3d.multiply(1.0 / o);
                }
                if (!(this instanceof SlicedPlayerEntity)) {
                    vec3d = vec3d.normalize();
                }
                Vec3d vec3d3 = this.getVelocity();
                vec3d = vec3d.multiply(speed);
                if (((Math.abs(vec3d3.x) < 0.003) && (Math.abs(vec3d3.z) < 0.003)) && (vec3d.length() < 0.0045000000000000005)) {
                    vec3d = vec3d.normalize().multiply(0.0045000000000000005);
                }
                this.setVelocity(this.getVelocity().add(vec3d));
            }
            return bl2;
        }
    }

    /**
     * Updates the entity's velocity to add a vector in the direction of the entity's yaw
     * whose absolute value is {@code movementInput} normalized and multiplied by {@code speed}.
     *
     * <p>This is usually called inside overridden {@link LivingEntity#travel} if the entity is
     * touching water; see {@link net.minecraft.entity.passive.FishEntity} for an example.
     */
    public void updateVelocity(float speed, Vec3d movementInput) {
        Vec3d vec3d = movementInputToVelocity(movementInput, speed, this.getYaw());
        this.setVelocity(this.getVelocity().add(vec3d));
    }

    public float getStepHeight() {
        return 0.0F;
    }

    public EntityType<?> getType() {
        return this.type;
    }

    protected void onSwimmingStart() {
        Entity entity = ((Entity) (Objects.requireNonNullElse(this.getControllingPassenger(), (Entity) this.entityBridge)));
        float f = (entity == this.entityBridge) ? 0.2F : 0.9F;
        Vec3d vec3d = entity.getVelocity();
        float g = Math.min(1.0F, ((float) (Math.sqrt((((vec3d.x * vec3d.x) * 0.2F) + (vec3d.y * vec3d.y)) + ((vec3d.z * vec3d.z) * 0.2F)))) * f);
        if (g < 0.25F) {
            this.playSound(this.getSplashSound(), g, 1.0F + ((this.random.nextFloat() - this.random.nextFloat()) * 0.4F));
        } else {
            this.playSound(this.getHighSpeedSplashSound(), g, 1.0F + ((this.random.nextFloat() - this.random.nextFloat()) * 0.4F));
        }
        float h = MathHelper.floor(this.getY());
        for (int i = 0; i < (1.0F + (this.dimensions.width() * 20.0F)); i++) {
            double d = ((this.random.nextDouble() * 2.0) - 1.0) * this.dimensions.width();
            double e = ((this.random.nextDouble() * 2.0) - 1.0) * this.dimensions.width();
            this.getWorld().addParticleClient(ParticleTypes.BUBBLE, this.getX() + d, h + 1.0F, this.getZ() + e, vec3d.x, vec3d.y - (this.random.nextDouble() * 0.2F), vec3d.z);
        }
        for (int i = 0; i < (1.0F + (this.dimensions.width() * 20.0F)); i++) {
            double d = ((this.random.nextDouble() * 2.0) - 1.0) * this.dimensions.width();
            double e = ((this.random.nextDouble() * 2.0) - 1.0) * this.dimensions.width();
            this.getWorld().addParticleClient(ParticleTypes.SPLASH, this.getX() + d, h + 1.0F, this.getZ() + e, vec3d.x, vec3d.y, vec3d.z);
        }
        this.emitGameEvent(GameEvent.SPLASH);
    }

    protected double getGravity() {
        return 0.0;
    }

    @Nullable
    public PositionInterpolator getInterpolator() {
        return null;
    }

    protected boolean isControlledByMainPlayer() {
        LivingEntity livingEntity = this.getControllingPassenger();
        return (livingEntity != null) && livingEntity.isControlledByMainPlayer();
    }

    public boolean isControlledByPlayer() {
        LivingEntity livingEntity = this.getControllingPassenger();
        return (livingEntity != null) && livingEntity.isControlledByPlayer();
    }

    /**
     * {@return whether the entity is sneaking}
     *
     * <p>This only returns {@code true} if the entity is a player and that player
     * is pressing the Sneak key. See also {@link #isInSneakingPose}.
     *
     * @see #setSneaking
     * @see #isInSneakingPose
     */
    public boolean isSneaking() {
        return this.getFlag(SNEAKING_FLAG_INDEX);
    }

    protected Vec3d adjustMovementForSneaking(Vec3d movement, MovementType type) {
        return movement;
    }

    /**
     * Returns the possible effect(s) of an entity moving.
     *
     * @implNote If an entity does not emit game events or play move sounds, this
    method should be overridden as returning a value other than
    {@linkplain Entity.MoveEffect#ALL ALL} allows skipping some movement logic
    and boost ticking performance.
     */
    protected Entity.MoveEffect getMoveEffect() {
        return Entity.MoveEffect.ALL;
    }

    /**
     * Applies a damage to this entity. The exact implementation differs between subclasses.
     *
     * <p>{@link net.minecraft.entity.LivingEntity} has health value, and damaging the entity decreases it. This
     * also handles shields, extra damage to helmets for falling blocks, setting the attacker,
     * playing hurt sound, etc.
     *
     * <p>Some entities like {@link net.minecraft.entity.ItemEntity} also have health value, which the overridden
     * method decrements. There also exist several entities, like {@link net.minecraft.entity.decoration.EndCrystalEntity}, where any damage discards the entity
     * (perhaps with an explosion).
     *
     * <p>If this is overridden, it must check the result of {@link net.minecraft.entity.LivingEntity#isInvulnerableTo} and
     * return early.
     *
     * @return whether the entity was actually damaged
     * @see #isAlwaysInvulnerableTo
     * @see net.minecraft.entity.LivingEntity#isInvulnerableTo
     * @see net.minecraft.entity.LivingEntity#modifyAppliedDamage
     */
    public abstract boolean damage(ServerWorld world, DamageSource source, float amount);

    /**
     * {@return whether the entity is in a crouching pose}
     *
     * <p>Compared to {@link #isSneaking()}, it only makes the entity appear
     * crouching and does not bring other effects of sneaking, such as no less
     * obvious name label rendering, no dismounting while riding, etc.
     *
     * <p>This is used by vanilla for non-player entities to crouch, such as
     * for foxes and cats. This is also used when the entity is a player and
     * the player would otherwise collide with blocks (for example, when the
     * player is in a 1.5 blocks tall tunnel).
     */
    public boolean isInSneakingPose() {
        return this.isInPose(EntityPose.CROUCHING);
    }

    /**
     * {@return whether the entity is pushed by fluids}
     *
     * @apiNote Aquatic mobs should override this to return {@code false}.
    Players are not pushed by fluids if they can fly (e.g. because of game mode).
     */
    public boolean isPushedByFluids() {
        return true;
    }

    /**
     * Plays {@code sound} at this entity's position with the entity's {@linkplain #getSoundCategory sound category} if the entity is {@linkplain #isSilent not silent}.
     */
    public void playSound(SoundEvent sound, float volume, float pitch) {
        if (!this.isSilent()) {
            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), sound, this.getSoundCategory(), volume, pitch);
        }
    }

    protected SoundEvent getSplashSound() {
        return SoundEvents.ENTITY_GENERIC_SPLASH;
    }

    protected SoundEvent getHighSpeedSplashSound() {
        return SoundEvents.ENTITY_GENERIC_SPLASH;
    }

    /**
     * Emits a game event originating from this entity at this entity's position.
     *
     * @see #emitGameEvent(RegistryEntry, Entity)
     */
    public void emitGameEvent(RegistryEntry<GameEvent> event) {
        this.emitGameEvent(event, (Entity) this.entityBridge);
    }

    public boolean isInPose(EntityPose pose) {
        return this.getPose() == pose;
    }

    /**
     * {@return whether the entity is silent}
     *
     * <p>Silent entities should not make sounds. {@link #playSound} checks this method by
     * default, but if a sound is played manually, this has to be checked too.
     *
     * <p>This is saved under the {@code Silent} NBT key.
     */
    public boolean isSilent() {
        return this.dataTracker.get(SILENT);
    }

    /**
     * {@return the sound category for sounds from this entity}
     *
     * <p>This is used by {@link #playSound(SoundEvent, float, float)} and defaults to
     * {@link SoundCategory#NEUTRAL}. Hostile entities should override this to
     * return {@link SoundCategory#HOSTILE}.
     *
     * @see #playSound(SoundEvent, float, float)
     */
    public SoundCategory getSoundCategory() {
        return SoundCategory.NEUTRAL;
    }

    /**
     * Emits a game event originating from another entity at this entity's position.
     *
     * <p>A common example is a game event called in {@link #interact}, where the player
     * interacting with the entity is the emitter of the event.
     *
     * @see #emitGameEvent(RegistryEntry)
     * @param entity
     * 		the entity that emitted the game event, or {@code null} if there is none
     */
    public void emitGameEvent(RegistryEntry<GameEvent> event, @Nullable
    Entity entity) {
        this.getWorld().emitGameEvent(entity, event, this.pos);
    }
}
