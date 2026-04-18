package murat.simv2.simulation.mirror.net.minecraft.entity;

import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.objects.Object2DoubleArrayMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import murat.simv2.simulation.mirror.net.minecraft.block.BlockState;
import murat.simv2.simulation.mirror.net.minecraft.block.Blocks;
import murat.simv2.simulation.mirror.net.minecraft.block.FenceGateBlock;
import murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource;
import murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker;
import murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData;
import murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity;
import murat.simv2.simulation.mirror.net.minecraft.entity.vehicle.AbstractBoatEntity;
import murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid;
import murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState;
import murat.simv2.simulation.mirror.net.minecraft.particle.ParticleTypes;
import murat.simv2.simulation.mirror.net.minecraft.registry.tag.BlockTags;
import murat.simv2.simulation.mirror.net.minecraft.registry.tag.DamageTypeTags;
import murat.simv2.simulation.mirror.net.minecraft.registry.tag.EntityTypeTags;
import murat.simv2.simulation.mirror.net.minecraft.registry.tag.FluidTags;
import murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey;
import murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam;
import murat.simv2.simulation.mirror.net.minecraft.scoreboard.Team;
import murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld;
import murat.simv2.simulation.mirror.net.minecraft.sound.BlockSoundGroup;
import murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvents;
import murat.simv2.simulation.mirror.net.minecraft.text.Text;
import murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashException;
import murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport;
import murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection;
import murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult;
import murat.simv2.simulation.mirror.net.minecraft.util.hit.HitResult;
import murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Box;
import murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos;
import murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkSectionPos;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Direction;
import murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d;
import murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random;
import murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler;
import murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profilers;
import murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape;
import murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShapes;
import murat.simv2.simulation.mirror.net.minecraft.world.BlockView;
import murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext;
import murat.simv2.simulation.mirror.net.minecraft.world.World;
import murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder;
import murat.simv2.simulation.mirror.net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

// Mirrored from net.minecraft.entity.Entity
// Movement-relevant statements only (WALA backward slice + Spoon AST pruning)
// Generated - do not edit
public abstract class Entity {
    public static final ImmutableList<murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis> X_THEN_Z = ImmutableList.of(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis.Y, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis.X, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis.Z);

    public static final ImmutableList<murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis> Z_THEN_X = ImmutableList.of(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis.Y, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis.Z, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis.X);

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityType<?> type;

    @Nullable
    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity vehicle;

    public murat.simv2.simulation.mirror.net.minecraft.world.World world;

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d pos;

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos;

    public murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos chunkPos;

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d velocity = murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d.ZERO;

    public float yaw;

    public float pitch;

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box boundingBox = NULL_BOX;

    public boolean onGround;

    public boolean horizontalCollision;

    public boolean verticalCollision;

    public boolean groundCollision;

    public boolean collidedSoftly;

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movementMultiplier = murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d.ZERO;

    @Nullable
    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason removalReason;

    public float distanceTraveled;

    public double fallDistance;

    public float nextStepSoundDistance = 1.0F;

    public double lastRenderX;

    public double lastRenderY;

    public double lastRenderZ;

    public boolean noClip;

    public final murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random random = murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random.create();

    public int age;

    public int fireTicks = -this.getBurningDuration();

    public boolean touchingWater;

    public Object2DoubleMap<murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey<murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid>> fluidHeight = new Object2DoubleArrayMap<>(2);

    public final Set<murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey<murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid>> submergedFluidTag = new HashSet();

    public boolean firstUpdate = true;

    public murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker dataTracker;

    public static final murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<Byte> FLAGS = murat.simv2.simulation.mirror.net.minecraft.entity.Entity.FLAGS;

    public static final int ON_FIRE_FLAG_INDEX = 0;

    public static final int SPRINTING_FLAG_INDEX = 3;

    public static final int SWIMMING_FLAG_INDEX = 4;

    public static final murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<Integer> AIR = murat.simv2.simulation.mirror.net.minecraft.entity.Entity.AIR;

    public static final murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<Optional<murat.simv2.simulation.mirror.net.minecraft.text.Text>> CUSTOM_NAME = murat.simv2.simulation.mirror.net.minecraft.entity.Entity.CUSTOM_NAME;

    public static final murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<Boolean> NAME_VISIBLE = murat.simv2.simulation.mirror.net.minecraft.entity.Entity.NAME_VISIBLE;

    public static final murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<Boolean> SILENT = murat.simv2.simulation.mirror.net.minecraft.entity.Entity.SILENT;

    public static final murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<Boolean> NO_GRAVITY = murat.simv2.simulation.mirror.net.minecraft.entity.Entity.NO_GRAVITY;

    public static final murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose> POSE = murat.simv2.simulation.mirror.net.minecraft.entity.Entity.POSE;

    public static final murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<Integer> FROZEN_TICKS = murat.simv2.simulation.mirror.net.minecraft.entity.Entity.FROZEN_TICKS;

    public boolean invulnerable;

    public final double[] pistonMovementDelta = new double[]{ 0.0, 0.0, 0.0 };

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions dimensions;

    public float standingEyeHeight;

    public Optional<murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos> supportingBlockPos = Optional.empty();

    public boolean forceUpdateSupportingBlockPos = false;

    public float lastChimeIntensity;

    public int lastChimeAge;

    @Nullable
    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState stateAtPos = null;

    public final List<Object> currentlyCheckedCollisions = new ObjectArrayList<>();

    public final LongSet collidedBlockPositions = new LongOpenHashSet();

    public final murat.simv2.simulation.mirror.net.minecraft.entity.EntityCollisionHandler.Impl collisionHandler = new murat.simv2.simulation.mirror.net.minecraft.entity.EntityCollisionHandler.Impl();

    public Entity(murat.simv2.simulation.mirror.net.minecraft.entity.EntityType<?> type, murat.simv2.simulation.mirror.net.minecraft.world.World world) {
        this.dimensions = type.getDimensions();
        this.pos = murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d.ZERO;
        this.blockPos = murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.ORIGIN;
        murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.Builder builder = new murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.Builder(this);
        builder.add(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.FLAGS, ((byte) (0)));
        builder.add(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.AIR, this.getMaxAir());
        builder.add(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.NAME_VISIBLE, false);
        builder.add(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.CUSTOM_NAME, Optional.empty());
        builder.add(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.SILENT, false);
        builder.add(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.NO_GRAVITY, false);
        builder.add(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.POSE, murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose.STANDING);
        builder.add(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.FROZEN_TICKS, 0);
        this.initDataTracker(builder);
        this.dataTracker = builder.build();
        this.setPosition(0.0, 0.0, 0.0);
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker getDataTracker() {
        return this.dataTracker;
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose getPose() {
        return this.dataTracker.get(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.POSE);
    }

    public boolean isInPose(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose pose) {
        return this.getPose() == pose;
    }

    public void setPosition(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d pos) {
        this.setPosition(pos.getX(), pos.getY(), pos.getZ());
    }

    public void setPosition(double x, double y, double z) {
        this.setPos(x, y, z);
        this.setBoundingBox(this.calculateBoundingBox());
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Box calculateBoundingBox() {
        return this.calculateDefaultBoundingBox(this.pos);
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Box calculateDefaultBoundingBox(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d pos) {
        return this.dimensions.getBoxAt(pos);
    }

    protected void refreshPosition() {
        this.setPosition(this.pos.x, this.pos.y, this.pos.z);
    }

    public void setFireTicks(int fireTicks) {
        this.fireTicks = fireTicks;
    }

    public void extinguish() {
        this.setFireTicks(0);
    }

    public boolean doesNotCollide(double offsetX, double offsetY, double offsetZ) {
        return this.doesNotCollide(this.getBoundingBox().offset(offsetX, offsetY, offsetZ));
    }

    private boolean doesNotCollide(murat.simv2.simulation.mirror.net.minecraft.util.math.Box box) {
        return this.getWorld().isSpaceEmpty(this, box) && (!this.getWorld().containsFluid(box));
    }

    public void setMovement(boolean onGround, boolean horizontalCollision, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movement) {
        this.onGround = onGround;
        this.horizontalCollision = horizontalCollision;
        this.updateSupportingBlockPos(onGround, movement);
    }

    protected void updateSupportingBlockPos(boolean onGround, @Nullable
    murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movement) {
        if (onGround) {
            murat.simv2.simulation.mirror.net.minecraft.util.math.Box box = this.getBoundingBox();
            murat.simv2.simulation.mirror.net.minecraft.util.math.Box box2 = new murat.simv2.simulation.mirror.net.minecraft.util.math.Box(box.minX, box.minY - 1.0E-6, box.minZ, box.maxX, box.minY, box.maxZ);
            Optional<murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos> optional = this.world.findSupportingBlockPos(this, box2);
            if (optional.isPresent() || this.forceUpdateSupportingBlockPos) {
            } else if (movement != null) {
                murat.simv2.simulation.mirror.net.minecraft.util.math.Box box3 = box2.offset(-movement.x, 0.0, -movement.z);
            }
        }
    }

    public boolean isOnGround() {
        return this.onGround;
    }

    public void move(murat.simv2.simulation.mirror.net.minecraft.entity.MovementType type, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movement) {
        if (this.noClip) {
            this.setPosition(this.getX() + movement.x, this.getY() + movement.y, this.getZ() + movement.z);
        } else {
            if (type == murat.simv2.simulation.mirror.net.minecraft.entity.MovementType.PISTON) {
                movement = this.adjustMovementForPiston(movement);
            }
            murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profiler profiler = murat.simv2.simulation.mirror.net.minecraft.util.profiler.Profilers.get();
            if (this.movementMultiplier.lengthSquared() > 1.0E-7) {
                movement = movement.multiply(this.movementMultiplier);
                this.setVelocity(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d.ZERO);
            }
            movement = this.adjustMovementForSneaking(movement, type);
            murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = this.adjustMovementForCollisions(movement);
            double d = vec3d.lengthSquared();
            if ((d > 1.0E-7) || ((movement.lengthSquared() - d) < 1.0E-7)) {
                if ((this.fallDistance != 0.0) && (d >= 1.0)) {
                    murat.simv2.simulation.mirror.net.minecraft.util.hit.BlockHitResult blockHitResult = this.getWorld().raycast(new murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext(this.getPos(), this.getPos().add(vec3d), murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.ShapeType.FALLDAMAGE_RESETTING, murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.FluidHandling.WATER, this));
                    if (blockHitResult.getType() != murat.simv2.simulation.mirror.net.minecraft.util.hit.HitResult.Type.MISS) {
                        this.onLanding();
                    }
                }
                murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d2 = this.getPos();
                List<murat.simv2.simulation.mirror.net.minecraft.entity.Entity.QueuedCollisionCheck> list = new ObjectArrayList<>();
                for (murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis axis : murat.simv2.simulation.mirror.net.minecraft.entity.Entity.getAxisCheckOrder(vec3d)) {
                    double e = vec3d.getComponentAlongAxis(axis);
                    if (e != 0.0) {
                        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d3 = vec3d2.offset(axis.getPositiveDirection(), e);
                        vec3d2 = vec3d3;
                    }
                }
                this.setPosition(vec3d2);
            }
            boolean bl = !murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.approximatelyEquals(movement.x, vec3d.x);
            boolean bl2 = !murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.approximatelyEquals(movement.z, vec3d.z);
            this.horizontalCollision = bl || bl2;
            if ((Math.abs(movement.y) > 0.0) || this.isLogicalSideForUpdatingMovement()) {
                this.verticalCollision = movement.y != vec3d.y;
                this.groundCollision = this.verticalCollision && (movement.y < 0.0);
                this.setMovement(this.groundCollision, this.horizontalCollision, vec3d);
            }
            if (this.horizontalCollision) {
                this.collidedSoftly = this.hasCollidedSoftly(vec3d);
            }
            murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos = this.getLandingPos();
            murat.simv2.simulation.mirror.net.minecraft.block.BlockState blockState = this.getWorld().getBlockState(blockPos);
            if (this.isLogicalSideForUpdatingMovement()) {
                this.fall(vec3d.y, this.isOnGround(), blockState, blockPos);
            }
            if (this.isRemoved()) {
            } else {
                if (this.horizontalCollision) {
                    murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d4 = this.getVelocity();
                    this.setVelocity(bl ? 0.0 : vec3d4.x, vec3d4.y, bl2 ? 0.0 : vec3d4.z);
                }
                if ((!this.getWorld().isClient()) || this.isLogicalSideForUpdatingMovement()) {
                    murat.simv2.simulation.mirror.net.minecraft.entity.Entity.MoveEffect moveEffect = this.getMoveEffect();
                    if (moveEffect.hasAny() && (!this.hasVehicle())) {
                        this.applyMoveEffect(moveEffect, vec3d, blockPos, blockState);
                    }
                }
                float f = this.getVelocityMultiplier();
                this.setVelocity(this.getVelocity().multiply(f, 1.0, f));
            }
        }
    }

    protected void applyMoveEffect(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.MoveEffect moveEffect, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movement, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos landingPos, murat.simv2.simulation.mirror.net.minecraft.block.BlockState landingState) {
        float g = ((float) (movement.length() * 0.6F));
        float h = ((float) (movement.horizontalLength() * 0.6F));
        murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos = this.getSteppingPos();
        murat.simv2.simulation.mirror.net.minecraft.block.BlockState blockState = this.getWorld().getBlockState(blockPos);
        boolean bl = this.canClimb(blockState);
        this.distanceTraveled += (bl) ? g : h;
        if ((this.distanceTraveled > this.nextStepSoundDistance) && (!blockState.isAir())) {
            boolean bl2 = blockPos.equals(landingPos);
            boolean bl3 = this.stepOnBlock(landingPos, landingState, moveEffect.playsSounds(), bl2, movement);
            if (!bl2) {
                bl3 |= this.stepOnBlock(blockPos, blockState, false, moveEffect.emitsGameEvents(), movement);
            }
            if (bl3) {
            } else if (this.isTouchingWater()) {
                if (moveEffect.playsSounds()) {
                    this.playSwimSound();
                }
            }
        }
    }

    protected void tickBlockCollision() {
        if (this.currentlyCheckedCollisions.isEmpty()) {
            this.currentlyCheckedCollisions.add(new murat.simv2.simulation.mirror.net.minecraft.entity.Entity.QueuedCollisionCheck(this.getLastRenderPos(), this.getPos()));
        } else if (((murat.simv2.simulation.mirror.net.minecraft.entity.Entity.QueuedCollisionCheck) (this.currentlyCheckedCollisions.getLast())).to.squaredDistanceTo(this.getPos()) > 9.9999994E-11F) {
            this.currentlyCheckedCollisions.add(new murat.simv2.simulation.mirror.net.minecraft.entity.Entity.QueuedCollisionCheck(((murat.simv2.simulation.mirror.net.minecraft.entity.Entity.QueuedCollisionCheck) (this.currentlyCheckedCollisions.getLast())).to, this.getPos()));
        }
        this.tickBlockCollisions(this.currentlyCheckedCollisions);
    }

    private void tickBlockCollisions(List<murat.simv2.simulation.mirror.net.minecraft.entity.Entity.QueuedCollisionCheck> checks) {
        if (this.shouldTickBlockCollision()) {
            if (this.isOnGround()) {
                murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos = this.getLandingPos();
                murat.simv2.simulation.mirror.net.minecraft.block.BlockState blockState = this.getWorld().getBlockState(blockPos);
            }
            boolean bl = this.isOnFire();
            boolean bl2 = this.shouldEscapePowderSnow();
            this.checkBlockCollision(checks, this.collisionHandler);
            if (this.isBeingRainedOn()) {
                this.extinguish();
            }
            if ((bl && (!this.isOnFire())) || (bl2 && (!this.shouldEscapePowderSnow()))) {
                this.playExtinguishSound();
            }
        }
    }

    protected boolean shouldTickBlockCollision() {
        return (!this.isRemoved()) && (!this.noClip);
    }

    private boolean canClimb(murat.simv2.simulation.mirror.net.minecraft.block.BlockState state) {
        return state.isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.BlockTags.CLIMBABLE) || state.isOf(murat.simv2.simulation.mirror.net.minecraft.block.Blocks.POWDER_SNOW);
    }

    private boolean stepOnBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos pos, murat.simv2.simulation.mirror.net.minecraft.block.BlockState state, boolean playSound, boolean emitEvent, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movement) {
        if (state.isAir()) {
            return false;
        } else {
            boolean bl = this.canClimb(state);
            if ((((this.isOnGround() || bl) || (this.isInSneakingPose() && (movement.y == 0.0))) || this.isOnRail()) && (!this.isSwimming())) {
                if (playSound) {
                    this.playStepSounds(pos, state);
                }
                if (emitEvent) {
                    this.getWorld().emitGameEvent(murat.simv2.simulation.mirror.net.minecraft.world.event.GameEvent.STEP, this.getPos(), murat.simv2.simulation.mirror.net.minecraft.world.event.GameEvent.Emitter.of(this, state));
                }
                return true;
            } else {
                return false;
            }
        }
    }

    protected void playExtinguishSound() {
        if (!this.world.isClient()) {
            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, this.getSoundCategory(), 0.7F, 1.6F + ((this.random.nextFloat() - this.random.nextFloat()) * 0.4F));
        }
    }

    @Deprecated
    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getLandingPos() {
        return this.getPosWithYOffset(0.2F);
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getVelocityAffectingPos() {
        return this.getPosWithYOffset(0.500001F);
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getSteppingPos() {
        return this.getPosWithYOffset(1.0E-5F);
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getPosWithYOffset(float offset) {
        if (this.supportingBlockPos.isPresent()) {
            murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos = ((murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos) (this.supportingBlockPos.get()));
            if (!(offset > 1.0E-5F)) {
                return blockPos;
            } else {
                murat.simv2.simulation.mirror.net.minecraft.block.BlockState blockState = this.getWorld().getBlockState(blockPos);
                return (((!(offset <= 0.5)) || (!blockState.isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.BlockTags.FENCES))) && (!blockState.isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.BlockTags.WALLS))) && (!(blockState.getBlock() instanceof murat.simv2.simulation.mirror.net.minecraft.block.FenceGateBlock)) ? blockPos.withY(murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.floor(this.pos.y - offset)) : blockPos;
            }
        } else {
            int i = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.floor(this.pos.x);
            int j = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.floor(this.pos.y - offset);
            int k = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.floor(this.pos.z);
            return new murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos(i, j, k);
        }
    }

    protected float getJumpVelocityMultiplier() {
        float f = this.getWorld().getBlockState(this.getBlockPos()).getBlock().getJumpVelocityMultiplier();
        float g = this.getWorld().getBlockState(this.getVelocityAffectingPos()).getBlock().getJumpVelocityMultiplier();
        return f == 1.0 ? g : f;
    }

    protected float getVelocityMultiplier() {
        murat.simv2.simulation.mirror.net.minecraft.block.BlockState blockState = this.getWorld().getBlockState(this.getBlockPos());
        float f = blockState.getBlock().getVelocityMultiplier();
        if ((!blockState.isOf(murat.simv2.simulation.mirror.net.minecraft.block.Blocks.WATER)) && (!blockState.isOf(murat.simv2.simulation.mirror.net.minecraft.block.Blocks.BUBBLE_COLUMN))) {
            return f == 1.0 ? this.getWorld().getBlockState(this.getVelocityAffectingPos()).getBlock().getVelocityMultiplier() : f;
        } else {
            return f;
        }
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d adjustMovementForPiston(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movement) {
        if (movement.lengthSquared() <= 1.0E-7) {
            return movement;
        } else {
            long l = this.getWorld().getTime();
            if (movement.x != 0.0) {
                double d = this.calculatePistonMovementFactor(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis.X, movement.x);
                return Math.abs(d) <= 1.0E-5F ? murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d.ZERO : new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d(d, 0.0, 0.0);
            } else if (movement.y != 0.0) {
                double d = this.calculatePistonMovementFactor(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis.Y, movement.y);
                return Math.abs(d) <= 1.0E-5F ? murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d.ZERO : new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d(0.0, d, 0.0);
            } else if (movement.z != 0.0) {
                double d = this.calculatePistonMovementFactor(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis.Z, movement.z);
                return Math.abs(d) <= 1.0E-5F ? murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d.ZERO : new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d(0.0, 0.0, d);
            } else {
                return murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d.ZERO;
            }
        }
    }

    private double calculatePistonMovementFactor(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis axis, double offsetFactor) {
        int i = axis.ordinal();
        double d = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.clamp(offsetFactor + this.pistonMovementDelta[i], -0.51, 0.51);
        offsetFactor = d - this.pistonMovementDelta[i];
        return offsetFactor;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d adjustMovementForCollisions(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movement) {
        murat.simv2.simulation.mirror.net.minecraft.util.math.Box box = this.getBoundingBox();
        List<murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape> list = this.getWorld().getEntityCollisions(this, box.stretch(movement));
        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = (movement.lengthSquared() == 0.0) ? movement : murat.simv2.simulation.mirror.net.minecraft.entity.Entity.adjustMovementForCollisions(this, movement, box, this.getWorld(), list);
        boolean bl = movement.x != vec3d.x;
        boolean bl2 = movement.y != vec3d.y;
        boolean bl3 = movement.z != vec3d.z;
        boolean bl4 = bl2 && (movement.y < 0.0);
        if (((this.getStepHeight() > 0.0F) && (bl4 || this.isOnGround())) && (bl || bl3)) {
            murat.simv2.simulation.mirror.net.minecraft.util.math.Box box2 = (bl4) ? box.offset(0.0, vec3d.y, 0.0) : box;
            murat.simv2.simulation.mirror.net.minecraft.util.math.Box box3 = box2.stretch(movement.x, this.getStepHeight(), movement.z);
            List<murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape> list2 = murat.simv2.simulation.mirror.net.minecraft.entity.Entity.findCollisionsForMovement(this, this.world, list, box3);
            float f = ((float) (vec3d.y));
            float[] fs = collectStepHeights(box2, list2, this.getStepHeight(), f);
            for (float g : fs) {
                murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d2 = murat.simv2.simulation.mirror.net.minecraft.entity.Entity.adjustMovementForCollisions(new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d(movement.x, g, movement.z), box2, list2);
                if (vec3d2.horizontalLengthSquared() > vec3d.horizontalLengthSquared()) {
                    double d = box.minY - box2.minY;
                    return vec3d2.subtract(0.0, d, 0.0);
                }
            }
        }
        return vec3d;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d adjustMovementForCollisions(@Nullable
    murat.simv2.simulation.mirror.net.minecraft.entity.Entity entity, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movement, murat.simv2.simulation.mirror.net.minecraft.util.math.Box entityBoundingBox, murat.simv2.simulation.mirror.net.minecraft.world.World world, List<murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape> collisions) {
        List<murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape> list = murat.simv2.simulation.mirror.net.minecraft.entity.Entity.findCollisionsForMovement(entity, world, collisions, entityBoundingBox.stretch(movement));
        return murat.simv2.simulation.mirror.net.minecraft.entity.Entity.adjustMovementForCollisions(movement, entityBoundingBox, list);
    }

    private static List<murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape> findCollisionsForMovement(@Nullable
    murat.simv2.simulation.mirror.net.minecraft.entity.Entity entity, murat.simv2.simulation.mirror.net.minecraft.world.World world, List<murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape> regularCollisions, murat.simv2.simulation.mirror.net.minecraft.util.math.Box movingEntityBoundingBox) {
        Builder<murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape> builder = ImmutableList.builderWithExpectedSize(regularCollisions.size() + 1);
        murat.simv2.simulation.mirror.net.minecraft.world.border.WorldBorder worldBorder = world.getWorldBorder();
        boolean bl = (entity != null) && worldBorder.canCollide(entity, movingEntityBoundingBox);
        return builder.build();
    }

    protected static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d adjustMovementForCollisions(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movement, murat.simv2.simulation.mirror.net.minecraft.util.math.Box entityBoundingBox, List<murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape> collisions) {
        if (collisions.isEmpty()) {
            return movement;
        } else {
            murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d.ZERO;
            for (murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis axis : murat.simv2.simulation.mirror.net.minecraft.entity.Entity.getAxisCheckOrder(movement)) {
                double d = movement.getComponentAlongAxis(axis);
                if (d != 0.0) {
                    double e = murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShapes.calculateMaxOffset(axis, entityBoundingBox.offset(vec3d), collisions, d);
                    vec3d = vec3d.withAxis(axis, e);
                }
            }
            return vec3d;
        }
    }

    private static Iterable<murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis> getAxisCheckOrder(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movement) {
        return Math.abs(movement.x) < Math.abs(movement.z) ? murat.simv2.simulation.mirror.net.minecraft.entity.Entity.Z_THEN_X : murat.simv2.simulation.mirror.net.minecraft.entity.Entity.X_THEN_Z;
    }

    private void checkBlockCollision(List<murat.simv2.simulation.mirror.net.minecraft.entity.Entity.QueuedCollisionCheck> queuedCollisionChecks, murat.simv2.simulation.mirror.net.minecraft.entity.EntityCollisionHandler.Impl collisionHandler) {
        if (this.shouldTickBlockCollision()) {
            LongSet longSet = this.collidedBlockPositions;
            for (murat.simv2.simulation.mirror.net.minecraft.entity.Entity.QueuedCollisionCheck queuedCollisionCheck : queuedCollisionChecks) {
                murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = queuedCollisionCheck.from();
                murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d2 = queuedCollisionCheck.to();
                murat.simv2.simulation.mirror.net.minecraft.util.math.Box box = this.calculateDefaultBoundingBox(vec3d2).contract(1.0E-5F);
                murat.simv2.simulation.mirror.net.minecraft.world.BlockView.collectCollisionsBetween(vec3d, vec3d2, box, (pos, version) -> {
                    if (this.isAlive()) {
                        murat.simv2.simulation.mirror.net.minecraft.block.BlockState blockState = this.getWorld().getBlockState(pos);
                        if (!blockState.isAir()) {
                            if (longSet.add(pos.asLong())) {
                                murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape voxelShape = blockState.getInsideCollisionShape(this.getWorld(), pos, this);
                                boolean bl = (voxelShape == murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShapes.fullCube()) || this.collides(vec3d, vec3d2, voxelShape.offset(new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d(pos)).getBoundingBoxes());
                                if (bl) {
                                    try {
                                        collisionHandler.updateIfNecessary(version);
                                        blockState.onEntityCollision(this.getWorld(), pos, this, collisionHandler);
                                        this.onBlockCollision(blockState);
                                    } catch (Throwable var14) {
                                        murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport crashReport = murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReport.create(var14, "Colliding entity with block");
                                        murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection crashReportSection = crashReport.addElement("Block being collided with");
                                        murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection.addBlockInfo(crashReportSection, this.getWorld(), pos, blockState);
                                        murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection crashReportSection2 = crashReport.addElement("murat.simv2.simulation.mirror.net.minecraft.entity.Entity being checked for collision");
                                        this.populateCrashReport(crashReportSection2);
                                        throw new murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashException(crashReport);
                                    }
                                }
                                boolean bl2 = this.collidesWithFluid(blockState.getFluidState(), pos, vec3d, vec3d2);
                                if (bl2) {
                                    collisionHandler.updateIfNecessary(version);
                                    blockState.getFluidState().onEntityCollision(this.getWorld(), pos, this, collisionHandler);
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    private void playStepSounds(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos pos, murat.simv2.simulation.mirror.net.minecraft.block.BlockState state) {
        this.playStepSound(pos, state);
        if (this.shouldPlayAmethystChimeSound(state)) {
            this.playAmethystChimeSound();
        }
    }

    protected void playSwimSound() {
        murat.simv2.simulation.mirror.net.minecraft.entity.Entity entity = ((murat.simv2.simulation.mirror.net.minecraft.entity.Entity) (Objects.requireNonNullElse(this.getControllingPassenger(), this)));
        float f = (entity == this) ? 0.35F : 0.4F;
        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = entity.getVelocity();
        float g = Math.min(1.0F, ((float) (Math.sqrt((((vec3d.x * vec3d.x) * 0.2F) + (vec3d.y * vec3d.y)) + ((vec3d.z * vec3d.z) * 0.2F)))) * f);
        this.playSwimSound(g);
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getStepSoundPos(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos pos) {
        murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos = pos.up();
        murat.simv2.simulation.mirror.net.minecraft.block.BlockState blockState = this.getWorld().getBlockState(blockPos);
        return (!blockState.isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.BlockTags.INSIDE_STEP_SOUND_BLOCKS)) && (!blockState.isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.BlockTags.COMBINATION_STEP_SOUND_BLOCKS)) ? pos : blockPos;
    }

    protected void playCombinationStepSounds(murat.simv2.simulation.mirror.net.minecraft.block.BlockState primaryState, murat.simv2.simulation.mirror.net.minecraft.block.BlockState secondaryState) {
        murat.simv2.simulation.mirror.net.minecraft.sound.BlockSoundGroup blockSoundGroup = primaryState.getSoundGroup();
        this.playSound(blockSoundGroup.getStepSound(), blockSoundGroup.getVolume() * 0.15F, blockSoundGroup.getPitch());
        this.playSecondaryStepSound(secondaryState);
    }

    protected void playSecondaryStepSound(murat.simv2.simulation.mirror.net.minecraft.block.BlockState state) {
        murat.simv2.simulation.mirror.net.minecraft.sound.BlockSoundGroup blockSoundGroup = state.getSoundGroup();
        this.playSound(blockSoundGroup.getStepSound(), blockSoundGroup.getVolume() * 0.05F, blockSoundGroup.getPitch() * 0.8F);
    }

    protected void playStepSound(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos pos, murat.simv2.simulation.mirror.net.minecraft.block.BlockState state) {
        murat.simv2.simulation.mirror.net.minecraft.sound.BlockSoundGroup blockSoundGroup = state.getSoundGroup();
        this.playSound(blockSoundGroup.getStepSound(), blockSoundGroup.getVolume() * 0.15F, blockSoundGroup.getPitch());
    }

    private boolean shouldPlayAmethystChimeSound(murat.simv2.simulation.mirror.net.minecraft.block.BlockState state) {
        return state.isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.BlockTags.CRYSTAL_SOUND_BLOCKS) && (this.age >= (this.lastChimeAge + 20));
    }

    private void playAmethystChimeSound() {
        this.lastChimeIntensity = this.lastChimeIntensity * ((float) (Math.pow(0.997, this.age - this.lastChimeAge)));
        this.lastChimeIntensity = Math.min(1.0F, this.lastChimeIntensity + 0.07F);
        float f = 0.5F + ((this.lastChimeIntensity * this.random.nextFloat()) * 1.2F);
        float g = 0.1F + (this.lastChimeIntensity * 1.2F);
        this.playSound(murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, g, f);
        this.lastChimeAge = this.age;
    }

    protected void playSwimSound(float volume) {
        this.playSound(this.getSwimSound(), volume, 1.0F + ((this.random.nextFloat() - this.random.nextFloat()) * 0.4F));
    }

    public boolean isSilent() {
        return this.dataTracker.get(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.SILENT);
    }

    public boolean hasNoGravity() {
        return this.dataTracker.get(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.NO_GRAVITY);
    }

    public double getFinalGravity() {
        return this.hasNoGravity() ? 0.0 : this.getGravity();
    }

    protected void fall(double heightDifference, boolean onGround, murat.simv2.simulation.mirror.net.minecraft.block.BlockState state, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos landedPosition) {
        if ((!this.isTouchingWater()) && (heightDifference < 0.0)) {
            this.fallDistance -= ((float) (heightDifference));
        }
        if (onGround) {
            this.onLanding();
        }
    }

    public boolean isFireImmune() {
        return this.getType().isFireImmune();
    }

    public boolean isTouchingWater() {
        return this.touchingWater;
    }

    boolean isBeingRainedOn() {
        murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos = this.getBlockPos();
        return this.getWorld().hasRain(blockPos) || this.getWorld().hasRain(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.ofFloored(blockPos.getX(), this.getBoundingBox().maxY, blockPos.getZ()));
    }

    public boolean isTouchingWaterOrRain() {
        return this.isTouchingWater() || this.isBeingRainedOn();
    }

    void checkWaterState() {
        if ((this.getVehicle() instanceof murat.simv2.simulation.mirror.net.minecraft.entity.vehicle.AbstractBoatEntity abstractBoatEntity) && (!abstractBoatEntity.isSubmergedInWater())) {
            this.touchingWater = false;
        } else if (this.updateMovementInFluid(murat.simv2.simulation.mirror.net.minecraft.registry.tag.FluidTags.WATER, 0.014)) {
            if ((!this.touchingWater) && (!this.firstUpdate)) {
                this.onSwimmingStart();
            }
            this.onLanding();
            this.touchingWater = true;
        } else {
            this.touchingWater = false;
        }
    }

    protected void onSwimmingStart() {
        murat.simv2.simulation.mirror.net.minecraft.entity.Entity entity = ((murat.simv2.simulation.mirror.net.minecraft.entity.Entity) (Objects.requireNonNullElse(this.getControllingPassenger(), this)));
        float f = (entity == this) ? 0.2F : 0.9F;
        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = entity.getVelocity();
        float g = Math.min(1.0F, ((float) (Math.sqrt((((vec3d.x * vec3d.x) * 0.2F) + (vec3d.y * vec3d.y)) + ((vec3d.z * vec3d.z) * 0.2F)))) * f);
        if (g < 0.25F) {
            this.playSound(this.getSplashSound(), g, 1.0F + ((this.random.nextFloat() - this.random.nextFloat()) * 0.4F));
        } else {
            this.playSound(this.getHighSpeedSplashSound(), g, 1.0F + ((this.random.nextFloat() - this.random.nextFloat()) * 0.4F));
        }
        float h = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.floor(this.getY());
        for (int i = 0; i < (1.0F + (this.dimensions.width() * 20.0F)); i++) {
            double d = ((this.random.nextDouble() * 2.0) - 1.0) * this.dimensions.width();
            double e = ((this.random.nextDouble() * 2.0) - 1.0) * this.dimensions.width();
            this.getWorld().addParticleClient(murat.simv2.simulation.mirror.net.minecraft.particle.ParticleTypes.BUBBLE, this.getX() + d, h + 1.0F, this.getZ() + e, vec3d.x, vec3d.y - (this.random.nextDouble() * 0.2F), vec3d.z);
        }
        for (int i = 0; i < (1.0F + (this.dimensions.width() * 20.0F)); i++) {
            double d = ((this.random.nextDouble() * 2.0) - 1.0) * this.dimensions.width();
            double e = ((this.random.nextDouble() * 2.0) - 1.0) * this.dimensions.width();
            this.getWorld().addParticleClient(murat.simv2.simulation.mirror.net.minecraft.particle.ParticleTypes.SPLASH, this.getX() + d, h + 1.0F, this.getZ() + e, vec3d.x, vec3d.y, vec3d.z);
        }
    }

    @Deprecated
    protected murat.simv2.simulation.mirror.net.minecraft.block.BlockState getLandingBlockState() {
        return this.getWorld().getBlockState(this.getLandingPos());
    }

    public boolean isSubmergedIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey<murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid> fluidTag) {
        return this.submergedFluidTag.contains(fluidTag);
    }

    public boolean isInLava() {
        return (!this.firstUpdate) && (this.fluidHeight.getDouble(murat.simv2.simulation.mirror.net.minecraft.registry.tag.FluidTags.LAVA) > 0.0);
    }

    public void updateVelocity(float speed, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movementInput) {
        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = murat.simv2.simulation.mirror.net.minecraft.entity.Entity.movementInputToVelocity(movementInput, speed, this.getYaw());
        this.setVelocity(this.getVelocity().add(vec3d));
    }

    protected static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movementInputToVelocity(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movementInput, float speed, float yaw) {
        double d = movementInput.lengthSquared();
        if (d < 1.0E-7) {
            return murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d.ZERO;
        } else {
            murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = (d > 1.0 ? movementInput.normalize() : movementInput).multiply(speed);
            float f = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.sin(yaw * ((float) (Math.PI / 180.0)));
            float g = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.cos(yaw * ((float) (Math.PI / 180.0)));
            return new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d((vec3d.x * g) - (vec3d.z * f), vec3d.y, (vec3d.z * g) + (vec3d.x * f));
        }
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getLastRenderPos() {
        return new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d(this.lastRenderX, this.lastRenderY, this.lastRenderZ);
    }

    public double squaredDistanceTo(murat.simv2.simulation.mirror.net.minecraft.entity.Entity entity) {
        return this.squaredDistanceTo(entity.getPos());
    }

    public double squaredDistanceTo(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vector) {
        double d = this.getX() - vector.x;
        double e = this.getY() - vector.y;
        double f = this.getZ() - vector.z;
        return ((d * d) + (e * e)) + (f * f);
    }

    @Deprecated
    public void serverDamage(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource source, float amount) {
        if (this.world instanceof murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld serverWorld) {
            this.damage(serverWorld, source, amount);
        }
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getRotationVector(float pitch, float yaw) {
        float f = pitch * ((float) (Math.PI / 180.0));
        float g = (-yaw) * ((float) (Math.PI / 180.0));
        float h = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.cos(g);
        float i = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.sin(g);
        float j = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.cos(f);
        float k = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.sin(f);
        return new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d(i * j, -k, h * j);
    }

    public boolean isInterpolating() {
        return (this.getInterpolator() != null) && this.getInterpolator().isInterpolating();
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getRotationVector() {
        return this.getRotationVector(this.getPitch(), this.getYaw());
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f getRotationClient() {
        return new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f(this.getPitch(), this.getYaw());
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getRotationVecClient() {
        return murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d.fromPolar(this.getRotationClient());
    }

    public boolean isOnFire() {
        boolean bl = (this.getWorld() != null) && this.getWorld().isClient;
        return (!this.isFireImmune()) && ((this.fireTicks > 0) || (bl && this.getFlag(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.ON_FIRE_FLAG_INDEX)));
    }

    public boolean hasVehicle() {
        return this.getVehicle() != null;
    }

    public boolean isSneaky() {
        return this.isSneaking();
    }

    public boolean isSprinting() {
        return this.getFlag(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.SPRINTING_FLAG_INDEX);
    }

    public boolean isSwimming() {
        return this.getFlag(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.SWIMMING_FLAG_INDEX);
    }

    public boolean isInSwimmingPose() {
        return this.isInPose(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose.SWIMMING);
    }

    public boolean isCrawling() {
        return this.isInSwimmingPose() && (!this.isTouchingWater());
    }

    public boolean isOnRail() {
        return false;
    }

    @Nullable
    public murat.simv2.simulation.mirror.net.minecraft.scoreboard.Team getScoreboardTeam() {
        return this.getWorld().getScoreboard().getScoreHolderTeam(this.getNameForScoreboard());
    }

    public boolean isTeammate(@Nullable
    murat.simv2.simulation.mirror.net.minecraft.entity.Entity other) {
        return other == null ? false : ((this == other) || this.isInSameTeam(other)) || other.isInSameTeam(this);
    }

    protected boolean isInSameTeam(murat.simv2.simulation.mirror.net.minecraft.entity.Entity other) {
        return this.isTeamPlayer(other.getScoreboardTeam());
    }

    public boolean isTeamPlayer(@Nullable
    murat.simv2.simulation.mirror.net.minecraft.scoreboard.AbstractTeam team) {
        return this.getScoreboardTeam() != null ? this.getScoreboardTeam().isEqual(team) : false;
    }

    protected boolean getFlag(int index) {
        return (this.dataTracker.get(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.FLAGS) & (1 << index)) != 0;
    }

    public int getMaxAir() {
        return 300;
    }

    public int getFrozenTicks() {
        return this.dataTracker.get(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.FROZEN_TICKS);
    }

    public boolean isFrozen() {
        return this.getFrozenTicks() >= this.getMinFreezeDamageTicks();
    }

    public int getMinFreezeDamageTicks() {
        return 140;
    }

    public void limitFallDistance() {
        if ((this.getVelocity().getY() > (-0.5)) && (this.fallDistance > 1.0)) {
            this.fallDistance = 1.0;
        }
    }

    public void onLanding() {
        this.fallDistance = 0.0;
    }

    protected boolean isAlwaysInvulnerableTo(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource damageSource) {
        return ((this.isRemoved() || ((this.invulnerable && (!damageSource.isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.DamageTypeTags.BYPASSES_INVULNERABILITY))) && (!damageSource.isSourceCreativePlayer()))) || (damageSource.isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.DamageTypeTags.IS_FIRE) && this.isFireImmune())) || (damageSource.isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.DamageTypeTags.IS_FALL) && this.getType().isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.EntityTypeTags.FALL_DAMAGE_IMMUNE));
    }

    public void onTrackedDataSet(murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<?> data) {
        if (murat.simv2.simulation.mirror.net.minecraft.entity.Entity.POSE.equals(data)) {
            this.calculateDimensions();
        }
    }

    public void calculateDimensions() {
        murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions entityDimensions = this.dimensions;
        murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose entityPose = this.getPose();
        murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions entityDimensions2 = this.getDimensions(entityPose);
        this.dimensions = entityDimensions2;
        this.refreshPosition();
        boolean bl = (entityDimensions2.width() <= 4.0F) && (entityDimensions2.height() <= 4.0F);
        if ((((((!this.world.isClient) && (!this.firstUpdate)) && (!this.noClip)) && bl) && ((entityDimensions2.width() > entityDimensions.width()) || (entityDimensions2.height() > entityDimensions.height()))) && (!(this instanceof murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity))) {
            this.recalculateDimensions(entityDimensions);
        }
    }

    public boolean recalculateDimensions(murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions previous) {
        murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions entityDimensions = this.getDimensions(this.getPose());
        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = this.getPos().add(0.0, previous.height() / 2.0, 0.0);
        double d = Math.max(0.0F, entityDimensions.width() - previous.width()) + 1.0E-6;
        double e = Math.max(0.0F, entityDimensions.height() - previous.height()) + 1.0E-6;
        murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape voxelShape = murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShapes.cuboid(murat.simv2.simulation.mirror.net.minecraft.util.math.Box.of(vec3d, d, e, d));
        Optional<murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d> optional = this.world.findClosestCollision(this, voxelShape, vec3d, entityDimensions.width(), entityDimensions.height(), entityDimensions.width());
        if (optional.isPresent()) {
            this.setPosition(((murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d) (optional.get())).add(0.0, (-entityDimensions.height()) / 2.0, 0.0));
        } else if ((entityDimensions.width() > previous.width()) && (entityDimensions.height() > previous.height())) {
            murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape voxelShape2 = murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShapes.cuboid(murat.simv2.simulation.mirror.net.minecraft.util.math.Box.of(vec3d, d, 1.0E-6, d));
            Optional<murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d> optional2 = this.world.findClosestCollision(this, voxelShape2, vec3d, entityDimensions.width(), previous.height(), entityDimensions.width());
            if (optional2.isPresent()) {
                this.setPosition(((murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d) (optional2.get())).add(0.0, ((-previous.height()) / 2.0) + 1.0E-6, 0.0));
            }
        }
    
        return false;}

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Box getBoundingBox() {
        return this.boundingBox;
    }

    public void setBoundingBox(murat.simv2.simulation.mirror.net.minecraft.util.math.Box boundingBox) {
        this.boundingBox = boundingBox;
    }

    public float getStandingEyeHeight() {
        return this.standingEyeHeight;
    }

    @Nullable
    public murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity getControllingPassenger() {
        return null;
    }

    public boolean isLogicalSideForUpdatingMovement() {
        return this.world.isClient() ? this.isControlledByMainPlayer() : !this.isControlledByPlayer();
    }

    @Nullable
    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity getVehicle() {
        return this.vehicle;
    }

    public boolean updateMovementInFluid(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey<murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid> tag, double speed) {
        if (this.isRegionUnloaded()) {
            return false;
        } else {
            murat.simv2.simulation.mirror.net.minecraft.util.math.Box box = this.getBoundingBox().contract(0.001);
            int i = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.floor(box.minX);
            int j = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.ceil(box.maxX);
            int k = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.floor(box.minY);
            int l = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.ceil(box.maxY);
            int m = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.floor(box.minZ);
            int n = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.ceil(box.maxZ);
            double d = 0.0;
            boolean bl = this.isPushedByFluids();
            boolean bl2 = false;
            murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d.ZERO;
            int o = 0;
            murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.Mutable mutable = new murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.Mutable();
            for (int p = i; p < j; p++) {
                for (int q = k; q < l; q++) {
                    for (int r = m; r < n; r++) {
                        murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState fluidState = this.getWorld().getFluidState(mutable);
                        if (fluidState.isIn(tag)) {
                            double e = q + fluidState.getHeight(this.getWorld(), mutable);
                            if (e >= box.minY) {
                                d = Math.max(e - box.minY, d);
                                if (bl) {
                                    murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d2 = fluidState.getVelocity(this.getWorld(), mutable);
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
                if (!(this instanceof murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity)) {
                    vec3d = vec3d.normalize();
                }
                murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d3 = this.getVelocity();
                vec3d = vec3d.multiply(speed);
                if (((Math.abs(vec3d3.x) < 0.003) && (Math.abs(vec3d3.z) < 0.003)) && (vec3d.length() < 0.0045000000000000005)) {
                    vec3d = vec3d.normalize().multiply(0.0045000000000000005);
                }
                this.setVelocity(this.getVelocity().add(vec3d));
            }
            return bl2;
        }
    }

    public boolean isRegionUnloaded() {
        murat.simv2.simulation.mirror.net.minecraft.util.math.Box box = this.getBoundingBox().expand(1.0);
        int i = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.floor(box.minX);
        int j = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.ceil(box.maxX);
        int k = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.floor(box.minZ);
        int l = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.ceil(box.maxZ);
        return !this.getWorld().isRegionLoaded(i, k, j, l);
    }

    public double getFluidHeight(murat.simv2.simulation.mirror.net.minecraft.registry.tag.TagKey<murat.simv2.simulation.mirror.net.minecraft.fluid.Fluid> fluid) {
        return this.fluidHeight.getDouble(fluid);
    }

    public double getSwimHeight() {
        return this.getStandingEyeHeight() < 0.4 ? 0.0 : 0.4;
    }

    public float getWidth() {
        return this.dimensions.width();
    }

    public float getHeight() {
        return this.dimensions.height();
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions getDimensions(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose pose) {
        return this.type.getDimensions();
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getPos() {
        return this.pos;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getBlockPos() {
        return this.blockPos;
    }

    public murat.simv2.simulation.mirror.net.minecraft.block.BlockState getBlockStateAtPos() {
        if (this.stateAtPos == null) {
            this.stateAtPos = this.getWorld().getBlockState(this.getBlockPos());
        }
        return this.stateAtPos;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getVelocity() {
        return this.velocity;
    }

    public void setVelocity(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d velocity) {
        this.velocity = velocity;
    }

    public void addVelocityInternal(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d velocity) {
        this.setVelocity(this.getVelocity().add(velocity));
    }

    public void setVelocity(double x, double y, double z) {
        this.setVelocity(new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d(x, y, z));
    }

    public double getX() {
        return this.pos.x;
    }

    public double getY() {
        return this.pos.y;
    }

    public double getBodyY(double heightScale) {
        return this.pos.y + (this.getHeight() * heightScale);
    }

    public double getZ() {
        return this.pos.z;
    }

    public void setPos(double x, double y, double z) {
        if (((this.pos.x != x) || (this.pos.y != y)) || (this.pos.z != z)) {
            this.pos = new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d(x, y, z);
            int i = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.floor(x);
            int j = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.floor(y);
            int k = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.floor(z);
            if (((i != this.blockPos.getX()) || (j != this.blockPos.getY())) || (k != this.blockPos.getZ())) {
                this.blockPos = new murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos(i, j, k);
                this.stateAtPos = null;
                if ((murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkSectionPos.getSectionCoord(i) != this.chunkPos.x) || (murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkSectionPos.getSectionCoord(k) != this.chunkPos.z)) {
                    this.chunkPos = new murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos(this.blockPos);
                }
            }
        }
    }

    public boolean canFreeze() {
        return !this.getType().isIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES);
    }

    public boolean shouldEscapePowderSnow() {
        return this.getFrozenTicks() > 0;
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public boolean isRemoved() {
        return this.removalReason != null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.world.World getWorld() {
        return this.world;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getMovement() {
        return (this.getControllingPassenger() instanceof murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity playerEntity) && this.isAlive() ? playerEntity.getMovement() : this.getVelocity();
    }

    // BEGIN GENERATED ENTITY COMPAT STUBS
    public boolean velocityModified;

    public Entity() {
    }

    public boolean isAttackable() {
        return false;
    }

    public boolean handleAttack(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
        return false;
    }

    public boolean sidedDamage(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0, float p1) {
        return false;
    }

    public boolean onKilledOther(
        murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0,
        murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p1
    ) {
        return false;
    }
    // END GENERATED ENTITY COMPAT STUBS

    // BEGIN GENERATED PRIMARY CONTRACT STUBS
    public static java.util.concurrent.atomic.AtomicInteger CURRENT_ID;
    public static java.lang.String CUSTOM_DATA_NBT_KEY;
    public static float DEFAULT_FRICTION;
    public static int DEFAULT_MIN_FREEZE_DAMAGE_TICKS;
    public static int DEFAULT_PORTAL_COOLDOWN;
    public static int FREEZING_DAMAGE_INTERVAL;
    public static int GLIDING_FLAG_INDEX;
    public static int GLOWING_FLAG_INDEX;
    public static java.lang.String ID_KEY;
    public static int INVISIBLE_FLAG_INDEX;
    public static int MAX_COMMAND_TAGS;
    public static int MAX_RIDING_COOLDOWN;
    public static float MIN_RISING_BUBBLE_COLUMN_SPEED;
    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Box NULL_BOX;
    public static java.lang.String PASSENGERS_KEY;
    public static int SNEAKING_FLAG_INDEX;
    public static double SPEED_IN_LAVA;
    public static double SPEED_IN_LAVA_IN_NETHER;
    public static double SPEED_IN_WATER;
    public static com.mojang.serialization.Codec TAG_LIST_CODEC;
    public static java.lang.String UUID_KEY;
    public java.lang.Object changeListener;
    public java.util.Set commandTags;
    public java.lang.Object customData;
    public static float field_44870;
    public static double field_44871;
    public static double field_44872;
    public static int field_49073;
    public static int field_49791;
    public boolean glowing;
    public boolean hasVisualFire;
    public int id;
    public boolean inPowderSnow;
    public boolean intersectionChecked;
    public float lastPitch;
    public double lastX;
    public double lastY;
    public float lastYaw;
    public double lastZ;
    public com.google.common.collect.ImmutableList passengerList;
    public long pistonMovementTick;
    public int portalCooldown;
    public java.lang.Object portalManager;
    public java.util.List queuedCollisionChecks;
    public static double renderDistanceMultiplier;
    public int ridingCooldown;
    public float speed;
    public boolean submergedInWater;
    public int timeUntilRegen;
    public java.lang.Object trackedPosition;
    public java.util.UUID uuid;
    public java.lang.String uuidString;
    public boolean velocityDirty;
    public boolean wasInPowderSnow;

    protected void addAirTravelEffects() {
    }

    protected boolean addCommandTag(java.lang.String p0) {
        return false;
    }

    protected void addFlapEffects() {
    }

    protected void addPassenger(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    protected void addPortalChunkTicketAt(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
    }

    protected void addVelocity(double p0, double p1, double p2) {
    }

    protected void addVelocity(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d adjustMovementForCollisions(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p2, murat.simv2.simulation.mirror.net.minecraft.world.World p3, java.util.List p4) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d adjustMovementForCollisions(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p1, java.util.List p2) {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d adjustMovementForSneaking(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.entity.MovementType p1) {
        return null;
    }

    protected void animateDamage(float p0) {
    }

    public static void applyBubbleColumnEffects(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, boolean p1) {
    }

    public static void applyBubbleColumnSurfaceEffects(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2) {
    }

    protected void applyGravity() {
    }

    protected float applyMirror(java.lang.Object p0) {
        return 0.0F;
    }

    protected float applyRotation(java.lang.Object p0) {
        return 0.0F;
    }

    protected void attemptTickInVoid() {
    }

    protected void baseTick() {
    }

    protected boolean bypassesLandingEffects() {
        return false;
    }

    protected boolean bypassesSteppingEffects() {
        return false;
    }

    protected float calculateNextStepSoundDistance() {
        return 0.0F;
    }

    protected boolean canActVoluntarily() {
        return false;
    }

    protected boolean canAddPassenger(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    protected boolean canAvoidTraps() {
        return false;
    }

    protected boolean canBeHitByProjectile() {
        return false;
    }

    protected boolean canBeSpectated(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
        return false;
    }

    protected boolean canExplosionDestroyBlock(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3, float p4) {
        return false;
    }

    protected boolean canHit() {
        return false;
    }

    protected boolean canModifyAt(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    protected boolean canMoveVoluntarily() {
        return false;
    }

    protected boolean canSprintAsVehicle() {
        return false;
    }

    protected boolean canStartRiding(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    protected boolean canTeleportBetween(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1) {
        return false;
    }

    protected boolean canUsePortals(boolean p0) {
        return false;
    }

    public static java.lang.Object castComponentValue(java.lang.Object p0, java.lang.Object p1) {
        return null;
    }

    protected void changeLookDirection(double p0, double p1) {
    }

    protected void checkBlockCollision(java.util.List p0, murat.simv2.simulation.mirror.net.minecraft.entity.EntityCollisionHandler.Impl p1) {
    }

    protected void checkDespawn() {
    }

    protected void checkWaterState() {
    }

    protected boolean clientDamage(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0) {
        return false;
    }

    public static float[] collectStepHeights(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0, java.util.List p1, float p2, float p3) {
        return null;
    }

    protected boolean collidesWithFluid(murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p3) {
        return false;
    }

    protected boolean collidesWithStateAtPos(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p1) {
        return false;
    }

    protected boolean collidesWith(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    protected boolean collides(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, java.util.List p2) {
        return false;
    }

    protected boolean copyComponentFrom(java.lang.Object p0, java.lang.Object p1) {
        return false;
    }

    protected void copyComponentsFrom(java.lang.Object p0) {
    }

    protected void copyComponentsFrom(murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p0) {
    }

    protected void copyFrom(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    protected void copyPositionAndRotation(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    protected boolean couldAcceptPassenger() {
        return false;
    }

    protected java.lang.Object createSpawnPacket(java.lang.Object p0) {
        return null;
    }

    protected boolean damage(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1, float p2) {
        return false;
    }

    protected void defrost() {
    }

    protected void detach() {
    }

    protected void discard() {
    }

    protected void dismountVehicle() {
    }

    protected float distanceTo(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return 0.0F;
    }

    protected boolean doesRenderOnFire() {
        return false;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity dropItem(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, java.lang.Object p1) {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity dropItem(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, java.lang.Object p1, int p2) {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity dropStack(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1) {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.entity.ItemEntity dropStack(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.item.ItemStack p1, float p2) {
        return null;
    }

    protected void emitGameEvent(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0) {
    }

    protected void emitGameEvent(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
    }

    protected boolean equals(java.lang.Object p0) {
        return false;
    }

    protected void extinguishWithSound() {
    }

    public static java.util.List findCollisionsForMovement(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.world.World p1, java.util.List p2, murat.simv2.simulation.mirror.net.minecraft.util.math.Box p3) {
        return null;
    }

    public static java.lang.Object fromName(java.lang.String p0) {
        return null;
    }

    public static java.lang.Object fromProfile(com.mojang.authlib.GameProfile p0) {
        return null;
    }

    protected int getAir() {
        return 0;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.entity.EntityAttachments getAttachments() {
        return null;
    }

    protected int getBlockX() {
        return 0;
    }

    protected int getBlockY() {
        return 0;
    }

    protected int getBlockZ() {
        return 0;
    }

    protected double getBodyX(double p0) {
        return 0.0D;
    }

    protected float getBodyYaw() {
        return 0.0F;
    }

    protected double getBodyZ(double p0) {
        return 0.0D;
    }

    protected float getBrightnessAtEyes() {
        return 0.0F;
    }

    protected int getBurningDuration() {
        return 0;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getCameraPosVec(float p0) {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos getChunkPos() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getClientCameraPosVec(float p0) {
        return null;
    }

    protected java.lang.Object getCommandSource(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
        return null;
    }

    protected java.util.Set getCommandTags() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.entity.Entity getControllingVehicle() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.text.Text getCustomName() {
        return null;
    }

    protected java.lang.Object getDamageSources() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.text.Text getDefaultName() {
        return null;
    }

    protected int getDefaultPortalCooldown() {
        return 0;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.text.Text getDisplayName() {
        return null;
    }

    protected float getEffectiveExplosionResistance(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.world.BlockView p1, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p2, murat.simv2.simulation.mirror.net.minecraft.block.BlockState p3, murat.simv2.simulation.mirror.net.minecraft.fluid.FluidState p4, float p5) {
        return 0.0F;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.world.World getEntityWorld() {
        return null;
    }

    protected float getEyeHeight(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose p0) {
        return 0.0F;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getEyePos() {
        return null;
    }

    protected double getEyeY() {
        return 0.0D;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Direction getFacing() {
        return null;
    }

    protected int getFireTicks() {
        return 0;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.entity.Entity getFirstPassenger() {
        return null;
    }

    protected float getFreezingScale() {
        return 0.0F;
    }

    protected double getGravity() {
        return 0.0D;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getHandPosOffset(murat.simv2.simulation.mirror.net.minecraft.item.Item p0) {
        return null;
    }

    protected float getHeadYaw() {
        return 0.0F;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent getHighSpeedSplashSound() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Direction getHorizontalFacing() {
        return null;
    }

    protected java.lang.Object getHoverEvent() {
        return null;
    }

    protected int getId() {
        return 0;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.entity.PositionInterpolator getInterpolator() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getLeashOffset() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getLeashOffset(float p0) {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getLeashPos(float p0) {
        return null;
    }

    protected float getLerpedPitch(float p0) {
        return 0.0F;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getLerpedPos(float p0) {
        return null;
    }

    protected float getLerpedYaw(float p0) {
        return 0.0F;
    }

    protected java.util.Optional getLootTableKey() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.entity.Entity.MoveEffect getMoveEffect() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Direction getMovementDirection() {
        return null;
    }

    protected java.lang.String getNameForScoreboard() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.text.Text getName() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getOppositeRotationVector(float p0) {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getOppositeRotationVector(float p0, float p1) {
        return null;
    }

    protected java.lang.Object getOrDefault(java.lang.Object p0, java.lang.Object p1) {
        return null;
    }

    protected double getParticleX(double p0) {
        return 0.0D;
    }

    protected double getParticleZ(double p0) {
        return 0.0D;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getPassengerAttachmentPos(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1, murat.simv2.simulation.mirror.net.minecraft.entity.EntityAttachments p2) {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getPassengerAttachmentPos(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions p1, float p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getPassengerDismountOffset(double p0, double p1, float p2) {
        return null;
    }

    protected java.util.List getPassengerList() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getPassengerRidingPos(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return null;
    }

    protected java.lang.Object getPassengerTeleportTarget(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
        return null;
    }

    protected java.lang.Iterable getPassengersDeep() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getPickBlockStack() {
        return null;
    }

    protected java.lang.Object getPistonBehavior() {
        return null;
    }

    protected float getPitch(float p0) {
        return 0.0F;
    }

    protected int getPlayerPassengers() {
        return 0;
    }

    protected int getPortalCooldown() {
        return 0;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.entity.ProjectileDeflection getProjectileDeflection(murat.simv2.simulation.mirror.net.minecraft.entity.projectile.ProjectileEntity p0) {
        return null;
    }

    protected double getRandomBodyY() {
        return 0.0D;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.random.Random getRandom() {
        return null;
    }

    protected java.lang.Object getRegistryManager() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason getRemovalReason() {
        return null;
    }

    public static double getRenderDistanceMultiplier() {
        return 0.0D;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.entity.Entity getRootVehicle() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getRotationVec(float p0) {
        return null;
    }

    protected int getSafeFallDistance() {
        return 0;
    }

    protected java.lang.String getSavedEntityId() {
        return null;
    }

    protected java.lang.Object getServer() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.sound.SoundCategory getSoundCategory() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent getSplashSound() {
        return null;
    }

    protected java.lang.Object getStackReference(int p0) {
        return null;
    }

    protected float getStepHeight() {
        return 0.0F;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.block.BlockState getSteppingBlockState() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.text.Text getStyledDisplayName() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent getSwimSound() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getSyncedPos() {
        return null;
    }

    protected float getTargetingMargin() {
        return 0.0F;
    }

    protected int getTeamColorValue() {
        return 0;
    }

    protected java.lang.Object getTrackedPosition() {
        return null;
    }

    protected java.lang.Object getTyped(java.lang.Object p0) {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.entity.EntityType getType() {
        return null;
    }

    protected java.lang.String getUuidAsString() {
        return null;
    }

    protected java.util.UUID getUuid() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getVehicleAttachmentPos(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.item.ItemStack getWeaponStack() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getWorldSpawnPos(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return null;
    }

    protected float getYaw(float p0) {
        return 0.0F;
    }

    protected java.lang.Object get(java.lang.Object p0) {
        return null;
    }

    protected boolean handleAttack(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    protected void handleFallDamageForPassengers(double p0, float p1, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p2) {
    }

    protected boolean handleFallDamage(double p0, float p1, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p2) {
        return false;
    }

    protected void handleFall(double p0, double p1, double p2, boolean p3) {
    }

    protected void handleStatus(byte p0) {
    }

    protected boolean hasCollidedSoftly(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
        return false;
    }

    protected boolean hasControllingPassenger() {
        return false;
    }

    protected boolean hasCustomName() {
        return false;
    }

    protected boolean hasPassengerDeep(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    protected boolean hasPassengers() {
        return false;
    }

    protected boolean hasPassenger(java.util.function.Predicate p0) {
        return false;
    }

    protected boolean hasPassenger(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    protected boolean hasPlayerRider() {
        return false;
    }

    protected boolean hasPortalCooldown() {
        return false;
    }

    protected int hashCode() {
        return 0;
    }

    protected void igniteByLava() {
    }

    protected void initDataTracker(murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.Builder p0) {
    }

    protected java.lang.Object interactAt(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.Hand p2) {
        return null;
    }

    protected java.lang.Object interact(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0, murat.simv2.simulation.mirror.net.minecraft.util.Hand p1) {
        return null;
    }

    protected boolean isAlive() {
        return false;
    }

    protected boolean isBeingRainedOn() {
        return false;
    }

    protected boolean isCollidable() {
        return false;
    }

    protected boolean isConnectedThroughVehicle(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    protected boolean isControlledByMainPlayer() {
        return false;
    }

    protected boolean isControlledByPlayer() {
        return false;
    }

    protected boolean isCustomNameVisible() {
        return false;
    }

    protected boolean isDescending() {
        return false;
    }

    protected boolean isFlappingWings() {
        return false;
    }

    protected boolean isGlowingLocal() {
        return false;
    }

    protected boolean isGlowing() {
        return false;
    }

    protected boolean isImmuneToExplosion(java.lang.Object p0) {
        return false;
    }

    protected boolean isInFluid() {
        return false;
    }

    protected boolean isInRange(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, double p1) {
        return false;
    }

    protected boolean isInRange(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, double p1, double p2) {
        return false;
    }

    protected boolean isInSneakingPose() {
        return false;
    }

    protected boolean isInsideWall() {
        return false;
    }

    protected boolean isInvisibleTo(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
        return false;
    }

    protected boolean isInvisible() {
        return false;
    }

    protected boolean isInvulnerable() {
        return false;
    }

    protected boolean isLiving() {
        return false;
    }

    protected boolean isPartOf(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    protected boolean isPlayer() {
        return false;
    }

    protected boolean isPushable() {
        return false;
    }

    protected boolean isPushedByFluids() {
        return false;
    }

    protected boolean isSneaking() {
        return false;
    }

    protected boolean isSpectator() {
        return false;
    }

    protected boolean isSubmergedInWater() {
        return false;
    }

    protected boolean isSupportedBy(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return false;
    }

    protected void kill(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0) {
    }

    protected void lerpPosAndRotation(int p0, double p1, double p2, double p3, double p4, double p5) {
    }

    protected float lerpYaw(float p0) {
        return 0.0F;
    }

    protected void lookAt(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    protected boolean method_30022(murat.simv2.simulation.mirror.net.minecraft.util.math.Box p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
        return false;
    }

    public static boolean method_31475(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
        return false;
    }

    protected java.util.Iterator method_31485() {
        return null;
    }

    public static void method_37216(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    public static boolean method_37217(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    public static void method_49789(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    protected murat.simv2.simulation.mirror.net.minecraft.block.BlockState method_51700(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0) {
        return null;
    }

    public static boolean method_54756(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    protected java.lang.String method_5689() {
        return null;
    }

    protected java.lang.String method_5761() {
        return null;
    }

    protected java.lang.String method_5766() {
        return null;
    }

    protected java.lang.Object method_5813(java.lang.Object p0) {
        return null;
    }

    protected java.lang.String method_5849() {
        return null;
    }

    protected void method_67632(it.unimi.dsi.fastutil.longs.LongSet p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p2, murat.simv2.simulation.mirror.net.minecraft.entity.EntityCollisionHandler.Impl p3, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p4, int p5) {
    }

    protected void method_67634(java.util.UUID p0) {
    }

    protected boolean occludeVibrationSignals() {
        return false;
    }

    protected void onBlockCollision(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0) {
    }

    protected void onBubbleColumnCollision(boolean p0) {
    }

    protected void onBubbleColumnSurfaceCollision(boolean p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
    }

    protected void onDamaged(murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p0) {
    }

    protected void onDataTrackerUpdate(java.util.List p0) {
    }

    protected void onExplodedBy(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    protected void onPassengerLookAround(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    protected void onPlayerCollision(murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity p0) {
    }

    protected void onRemoved() {
    }

    protected void onRemove(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p0) {
    }

    protected void onSpawnPacket(java.lang.Object p0) {
    }

    protected void onStartedTrackingBy(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
    }

    protected void onStoppedTrackingBy(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0) {
    }

    protected void onStruckByLightning(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, java.lang.Object p1) {
    }

    protected void playSoundIfNotSilent(murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p0) {
    }

    protected void playSound(murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent p0, float p1, float p2) {
    }

    protected void popQueuedCollisionCheck() {
    }

    protected void populateCrashReport(murat.simv2.simulation.mirror.net.minecraft.util.crash.CrashReportSection p0) {
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d positionInPortal(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis p0, java.lang.Object p1) {
        return null;
    }

    protected void pushAwayFrom(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    protected void pushOutOfBlocks(double p0, double p1, double p2) {
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.hit.HitResult raycast(double p0, float p1, boolean p2) {
        return null;
    }

    protected void readCustomDataFromNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    protected void readNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    protected void refreshPositionAfterTeleport(double p0, double p1, double p2) {
    }

    protected void refreshPositionAfterTeleport(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
    }

    protected void refreshPositionAndAngles(double p0, double p1, double p2, float p3, float p4) {
    }

    protected void refreshPositionAndAngles(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p0, float p1, float p2) {
    }

    protected void refreshPositionAndAngles(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, float p1, float p2) {
    }

    protected void reinitDimensions() {
    }

    protected void removeAllPassengers() {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.text.Text removeClickEvents(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
        return null;
    }

    protected boolean removeCommandTag(java.lang.String p0) {
        return false;
    }

    protected void removeFromDimension() {
    }

    protected void removePassenger(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    protected void remove(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p0) {
    }

    protected void requestTeleportAndDismount(double p0, double p1, double p2) {
    }

    protected void requestTeleportOffset(double p0, double p1, double p2) {
    }

    protected void requestTeleport(double p0, double p1, double p2) {
    }

    protected void resetPortalCooldown() {
    }

    protected void resetPosition() {
    }

    protected void rotate(float p0, float p1) {
    }

    protected boolean saveNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
        return false;
    }

    protected boolean saveSelfNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
        return false;
    }

    protected void scheduleVelocityUpdate() {
    }

    protected void sendTeleportPacket(java.lang.Object p0) {
    }

    protected void setAir(int p0) {
    }

    protected void setAngles(float p0, float p1) {
    }

    protected boolean setApplicableComponent(java.lang.Object p0, java.lang.Object p1) {
        return false;
    }

    protected void setBodyYaw(float p0) {
    }

    protected void setChangeListener(java.lang.Object p0) {
    }

    protected void setComponent(java.lang.Object p0, java.lang.Object p1) {
    }

    protected void setCustomNameVisible(boolean p0) {
    }

    protected void setCustomName(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
    }

    protected void setFlag(int p0, boolean p1) {
    }

    protected void setFrozenTicks(int p0) {
    }

    protected void setGlowing(boolean p0) {
    }

    protected void setHeadYaw(float p0) {
    }

    protected void setId(int p0) {
    }

    protected void setInPowderSnow(boolean p0) {
    }

    protected void setInvisible(boolean p0) {
    }

    protected void setInvulnerable(boolean p0) {
    }

    protected void setLastAngles(float p0, float p1) {
    }

    protected void setLastPositionAndAngles(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, float p1, float p2) {
    }

    protected void setLastPosition(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0) {
    }

    protected void setMovement(boolean p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    protected void setNoGravity(boolean p0) {
    }

    protected void setOnFireForTicks(int p0) {
    }

    protected void setOnFireFor(float p0) {
    }

    protected void setOnFireFromLava() {
    }

    protected void setOnFire(boolean p0) {
    }

    protected void setOnGround(boolean p0) {
    }

    protected void setPitch(float p0) {
    }

    protected void setPortalCooldown(int p0) {
    }

    protected void setPose(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose p0) {
    }

    protected void setPosition(java.lang.Object p0, java.util.Set p1) {
    }

    protected void setRemoved(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason p0) {
    }

    public static void setRenderDistanceMultiplier(double p0) {
    }

    protected void setRotation(float p0, float p1) {
    }

    protected void setSilent(boolean p0) {
    }

    protected void setSneaking(boolean p0) {
    }

    protected void setSprinting(boolean p0) {
    }

    protected void setSwimming(boolean p0) {
    }

    protected void setUuid(java.util.UUID p0) {
    }

    protected void setVelocityClient(double p0, double p1, double p2) {
    }

    protected void setWorld(murat.simv2.simulation.mirror.net.minecraft.world.World p0) {
    }

    protected void setYaw(float p0) {
    }

    protected boolean shouldControlVehicles() {
        return false;
    }

    protected boolean shouldDismountUnderwater() {
        return false;
    }

    protected boolean shouldPlayBurnSoundInLava() {
        return false;
    }

    protected boolean shouldRenderName() {
        return false;
    }

    protected boolean shouldRender(double p0) {
        return false;
    }

    protected boolean shouldRender(double p0, double p1, double p2) {
        return false;
    }

    protected boolean shouldSave() {
        return false;
    }

    protected boolean shouldSetPositionOnLoad() {
        return false;
    }

    protected boolean shouldSpawnSprintingParticles() {
        return false;
    }

    protected void slowMovement(murat.simv2.simulation.mirror.net.minecraft.block.BlockState p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    public static void spawnBubbleColumnParticles(murat.simv2.simulation.mirror.net.minecraft.world.World p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
    }

    protected void spawnSprintingParticles() {
    }

    protected double squaredDistanceTo(double p0, double p1, double p2) {
        return 0.0D;
    }

    protected boolean startRiding(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
        return false;
    }

    protected boolean startRiding(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, boolean p1) {
        return false;
    }

    protected void stopRiding() {
    }

    protected java.util.stream.Stream streamIntoPassengers() {
        return null;
    }

    protected java.util.stream.Stream streamPassengersAndSelf() {
        return null;
    }

    protected java.util.stream.Stream streamSelfAndPassengers() {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.entity.Entity teleportCrossDimension(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, java.lang.Object p1) {
        return null;
    }

    protected void teleportPassengers() {
    }

    protected murat.simv2.simulation.mirror.net.minecraft.entity.Entity teleportSameDimension(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, java.lang.Object p1) {
        return null;
    }

    protected murat.simv2.simulation.mirror.net.minecraft.entity.Entity teleportTo(java.lang.Object p0) {
        return null;
    }

    protected boolean teleport(murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld p0, double p1, double p2, double p3, java.util.Set p4, float p5, float p6, boolean p7) {
        return false;
    }

    protected void tickBlockCollisions(java.util.List p0) {
    }

    protected void tickBlockCollision(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
    }

    protected void tickInVoid() {
    }

    protected void tickPortalCooldown() {
    }

    protected void tickPortalTeleportation() {
    }

    protected void tickRiding() {
    }

    protected void tick() {
    }

    protected java.lang.String toString() {
        return null;
    }

    protected void tryUsePortal(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos p1) {
    }

    protected void unsetRemoved() {
    }

    protected void updateEventHandler(java.util.function.BiConsumer p0) {
    }

    protected void updateKilledAdvancementCriterion(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource p1) {
    }

    protected void updateLastAngles() {
    }

    protected void updateLastPosition() {
    }

    protected murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d updatePassengerForDismount(murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity p0) {
        return null;
    }

    protected void updatePassengerPosition(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
    }

    protected void updatePassengerPosition(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity.PositionUpdater p1) {
    }

    protected void updatePositionAndAngles(double p0, double p1, double p2, float p3, float p4) {
    }

    protected void updatePosition(double p0, double p1, double p2) {
    }

    protected void updateSubmergedInWaterState() {
    }

    protected void updateSwimming() {
    }

    protected void updateTrackedHeadRotation(float p0, int p1) {
    }

    protected void updateTrackedPositionAndAngles(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, float p1, float p2) {
    }

    protected void updateTrackedPosition(double p0, double p1, double p2) {
    }

    protected boolean updateWaterState() {
        return false;
    }

    protected void writeCustomDataToNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
    }

    protected murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound writeNbt(murat.simv2.simulation.mirror.net.minecraft.nbt.NbtCompound p0) {
        return null;
    }

    // END GENERATED PRIMARY CONTRACT STUBS

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static class MoveEffect {
        public static murat.simv2.simulation.mirror.net.minecraft.entity.Entity.MoveEffect ALL;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.Entity.MoveEffect EVENTS;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.Entity.MoveEffect NONE;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.Entity.MoveEffect SOUNDS;
        public boolean events;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.Entity.MoveEffect[] field_28636;
        public boolean sounds;

        public MoveEffect(java.lang.String p0, int p1, boolean p2, boolean p3) {
        }

        public boolean emitsGameEvents() {
            return false;
        }

        public boolean hasAny() {
            return false;
        }

        public int ordinal() {
            return 0;
        }

        public boolean playsSounds() {
            return false;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.Entity.MoveEffect valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.Entity.MoveEffect[] values() {
            return null;
        }

        public MoveEffect() {
        }

    }

    public static interface PositionUpdater {
        public void accept(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0, double p1, double p2, double p3);

    }

    public static class QueuedCollisionCheck {
        public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d from;
        public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d to;

        public QueuedCollisionCheck(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p0, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d p1) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d from() {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public java.lang.String toString() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d to() {
            return null;
        }

        public QueuedCollisionCheck() {
        }

    }

    public static class RemovalReason {
        public static murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason CHANGED_DIMENSION;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason DISCARDED;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason KILLED;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason UNLOADED_TO_CHUNK;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason UNLOADED_WITH_PLAYER;
        public boolean destroy;
        public static murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason[] field_27005;
        public boolean save;

        public RemovalReason(java.lang.String p0, int p1, boolean p2, boolean p3) {
        }

        public int ordinal() {
            return 0;
        }

        public boolean shouldDestroy() {
            return false;
        }

        public boolean shouldSave() {
            return false;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.entity.Entity.RemovalReason[] values() {
            return null;
        }

        public RemovalReason() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
