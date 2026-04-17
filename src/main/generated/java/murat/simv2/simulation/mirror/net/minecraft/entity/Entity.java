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
import murat.simv2.simulation.mirror.net.minecraft.block.Blocks;
import murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker;
import murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData;
import murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity;
import murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Box;
import murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkPos;
import murat.simv2.simulation.mirror.net.minecraft.util.math.ChunkSectionPos;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Direction;
import murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d;
import murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShapes;
import murat.simv2.simulation.mirror.net.minecraft.world.BlockView;
import murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.scoreboard.AbstractTeam;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.crash.CrashReportSection;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.profiler.Profilers;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

// Mirrored from net.minecraft.entity.Entity
// Movement-relevant statements only (WALA backward slice + Spoon AST pruning)
// Generated - do not edit
public abstract class Entity {
    public static final ImmutableList<murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis> X_THEN_Z = ImmutableList.of(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis.Y, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis.X, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis.Z);

    public static final ImmutableList<murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis> Z_THEN_X = ImmutableList.of(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis.Y, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis.Z, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis.X);

    public EntityType<?> type;

    @Nullable
    public murat.simv2.simulation.mirror.net.minecraft.entity.Entity vehicle;

    public World world;

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

    public final Random random = Random.create();

    public int age;

    public int fireTicks = -this.getBurningDuration();

    public boolean touchingWater;

    public Object2DoubleMap<TagKey<Fluid>> fluidHeight = new Object2DoubleArrayMap<>(2);

    public final Set<TagKey<Fluid>> submergedFluidTag = new HashSet();

    public boolean firstUpdate = true;

    public murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker dataTracker;

    public static final murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<Byte> FLAGS = murat.simv2.simulation.mirror.net.minecraft.entity.Entity.FLAGS;

    public static final int ON_FIRE_FLAG_INDEX = 0;

    public static final int SPRINTING_FLAG_INDEX = 3;

    public static final int SWIMMING_FLAG_INDEX = 4;

    public static final murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<Integer> AIR = murat.simv2.simulation.mirror.net.minecraft.entity.Entity.AIR;

    public static final murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<Optional<Text>> CUSTOM_NAME = murat.simv2.simulation.mirror.net.minecraft.entity.Entity.CUSTOM_NAME;

    public static final murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<Boolean> NAME_VISIBLE = murat.simv2.simulation.mirror.net.minecraft.entity.Entity.NAME_VISIBLE;

    public static final murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<Boolean> SILENT = murat.simv2.simulation.mirror.net.minecraft.entity.Entity.SILENT;

    public static final murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<Boolean> NO_GRAVITY = murat.simv2.simulation.mirror.net.minecraft.entity.Entity.NO_GRAVITY;

    public static final murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<EntityPose> POSE = murat.simv2.simulation.mirror.net.minecraft.entity.Entity.POSE;

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
    public BlockState stateAtPos = null;

    public final List<Object> currentlyCheckedCollisions = new ObjectArrayList<>();

    public final LongSet collidedBlockPositions = new LongOpenHashSet();

    public final murat.simv2.simulation.mirror.net.minecraft.entity.EntityCollisionHandler.Impl collisionHandler = new murat.simv2.simulation.mirror.net.minecraft.entity.EntityCollisionHandler.Impl();

    public Entity(EntityType<?> type, World world) {
        this.dimensions = type.getDimensions();
        this.pos = murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d.ZERO;
        this.blockPos = murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.ORIGIN;
        murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.Builder builder = new murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.Builder(this);
        builder.add(Entity.FLAGS, ((byte) (0)));
        builder.add(Entity.AIR, this.getMaxAir());
        builder.add(Entity.NAME_VISIBLE, false);
        builder.add(Entity.CUSTOM_NAME, Optional.empty());
        builder.add(Entity.SILENT, false);
        builder.add(Entity.NO_GRAVITY, false);
        builder.add(Entity.POSE, EntityPose.STANDING);
        builder.add(Entity.FROZEN_TICKS, 0);
        this.initDataTracker(builder);
        this.dataTracker = builder.build();
        this.setPosition(0.0, 0.0, 0.0);
    }

    public murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker getDataTracker() {
        return this.dataTracker;
    }

    public EntityPose getPose() {
        return this.dataTracker.get(Entity.POSE);
    }

    public boolean isInPose(EntityPose pose) {
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
            Profiler profiler = Profilers.get();
            if (this.movementMultiplier.lengthSquared() > 1.0E-7) {
                movement = movement.multiply(this.movementMultiplier);
                this.setVelocity(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d.ZERO);
            }
            movement = this.adjustMovementForSneaking(movement, type);
            murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = this.adjustMovementForCollisions(movement);
            double d = vec3d.lengthSquared();
            if ((d > 1.0E-7) || ((movement.lengthSquared() - d) < 1.0E-7)) {
                if ((this.fallDistance != 0.0) && (d >= 1.0)) {
                    BlockHitResult blockHitResult = this.getWorld().raycast(new murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext(this.getPos(), this.getPos().add(vec3d), murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.ShapeType.FALLDAMAGE_RESETTING, murat.simv2.simulation.mirror.net.minecraft.world.RaycastContext.FluidHandling.WATER, this));
                    if (blockHitResult.getType() != HitResult.Type.MISS) {
                        this.onLanding();
                    }
                }
                murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d2 = this.getPos();
                List<murat.simv2.simulation.mirror.net.minecraft.entity.Entity.QueuedCollisionCheck> list = new ObjectArrayList<>();
                for (murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis axis : Entity.getAxisCheckOrder(vec3d)) {
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
            BlockState blockState = this.getWorld().getBlockState(blockPos);
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

    protected void applyMoveEffect(murat.simv2.simulation.mirror.net.minecraft.entity.Entity.MoveEffect moveEffect, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movement, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos landingPos, BlockState landingState) {
        float g = ((float) (movement.length() * 0.6F));
        float h = ((float) (movement.horizontalLength() * 0.6F));
        murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos = this.getSteppingPos();
        BlockState blockState = this.getWorld().getBlockState(blockPos);
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
                BlockState blockState = this.getWorld().getBlockState(blockPos);
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

    private boolean canClimb(BlockState state) {
        return state.isIn(BlockTags.CLIMBABLE) || state.isOf(murat.simv2.simulation.mirror.net.minecraft.block.Blocks.POWDER_SNOW);
    }

    private boolean stepOnBlock(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos pos, BlockState state, boolean playSound, boolean emitEvent, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movement) {
        if (state.isAir()) {
            return false;
        } else {
            boolean bl = this.canClimb(state);
            if ((((this.isOnGround() || bl) || (this.isInSneakingPose() && (movement.y == 0.0))) || this.isOnRail()) && (!this.isSwimming())) {
                if (playSound) {
                    this.playStepSounds(pos, state);
                }
                if (emitEvent) {
                    this.getWorld().emitGameEvent(GameEvent.STEP, this.getPos(), GameEvent.Emitter.of(this, state));
                }
                return true;
            } else {
                return false;
            }
        }
    }

    protected void playExtinguishSound() {
        if (!this.world.isClient()) {
            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, this.getSoundCategory(), 0.7F, 1.6F + ((this.random.nextFloat() - this.random.nextFloat()) * 0.4F));
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
                BlockState blockState = this.getWorld().getBlockState(blockPos);
                return (((!(offset <= 0.5)) || (!blockState.isIn(BlockTags.FENCES))) && (!blockState.isIn(BlockTags.WALLS))) && (!(blockState.getBlock() instanceof FenceGateBlock)) ? blockPos.withY(murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.floor(this.pos.y - offset)) : blockPos;
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
        BlockState blockState = this.getWorld().getBlockState(this.getBlockPos());
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
        List<VoxelShape> list = this.getWorld().getEntityCollisions(this, box.stretch(movement));
        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = (movement.lengthSquared() == 0.0) ? movement : Entity.adjustMovementForCollisions(this, movement, box, this.getWorld(), list);
        boolean bl = movement.x != vec3d.x;
        boolean bl2 = movement.y != vec3d.y;
        boolean bl3 = movement.z != vec3d.z;
        boolean bl4 = bl2 && (movement.y < 0.0);
        if (((this.getStepHeight() > 0.0F) && (bl4 || this.isOnGround())) && (bl || bl3)) {
            murat.simv2.simulation.mirror.net.minecraft.util.math.Box box2 = (bl4) ? box.offset(0.0, vec3d.y, 0.0) : box;
            murat.simv2.simulation.mirror.net.minecraft.util.math.Box box3 = box2.stretch(movement.x, this.getStepHeight(), movement.z);
            List<VoxelShape> list2 = Entity.findCollisionsForMovement(this, this.world, list, box3);
            float f = ((float) (vec3d.y));
            float[] fs = collectStepHeights(box2, list2, this.getStepHeight(), f);
            for (float g : fs) {
                murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d2 = Entity.adjustMovementForCollisions(new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d(movement.x, g, movement.z), box2, list2);
                if (vec3d2.horizontalLengthSquared() > vec3d.horizontalLengthSquared()) {
                    double d = box.minY - box2.minY;
                    return vec3d2.subtract(0.0, d, 0.0);
                }
            }
        }
        return vec3d;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d adjustMovementForCollisions(@Nullable
    murat.simv2.simulation.mirror.net.minecraft.entity.Entity entity, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movement, murat.simv2.simulation.mirror.net.minecraft.util.math.Box entityBoundingBox, World world, List<VoxelShape> collisions) {
        List<VoxelShape> list = Entity.findCollisionsForMovement(entity, world, collisions, entityBoundingBox.stretch(movement));
        return Entity.adjustMovementForCollisions(movement, entityBoundingBox, list);
    }

    private static List<VoxelShape> findCollisionsForMovement(@Nullable
    murat.simv2.simulation.mirror.net.minecraft.entity.Entity entity, World world, List<VoxelShape> regularCollisions, murat.simv2.simulation.mirror.net.minecraft.util.math.Box movingEntityBoundingBox) {
        Builder<VoxelShape> builder = ImmutableList.builderWithExpectedSize(regularCollisions.size() + 1);
        WorldBorder worldBorder = world.getWorldBorder();
        boolean bl = (entity != null) && worldBorder.canCollide(entity, movingEntityBoundingBox);
        return builder.build();
    }

    protected static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d adjustMovementForCollisions(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movement, murat.simv2.simulation.mirror.net.minecraft.util.math.Box entityBoundingBox, List<VoxelShape> collisions) {
        if (collisions.isEmpty()) {
            return movement;
        } else {
            murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d.ZERO;
            for (murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis axis : Entity.getAxisCheckOrder(movement)) {
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
        return Math.abs(movement.x) < Math.abs(movement.z) ? Entity.Z_THEN_X : Entity.X_THEN_Z;
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
                        BlockState blockState = this.getWorld().getBlockState(pos);
                        if (!blockState.isAir()) {
                            if (longSet.add(pos.asLong())) {
                                VoxelShape voxelShape = blockState.getInsideCollisionShape(this.getWorld(), pos, this);
                                boolean bl = (voxelShape == murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShapes.fullCube()) || this.collides(vec3d, vec3d2, voxelShape.offset(new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d(pos)).getBoundingBoxes());
                                if (bl) {
                                    try {
                                        collisionHandler.updateIfNecessary(version);
                                        blockState.onEntityCollision(this.getWorld(), pos, this, collisionHandler);
                                        this.onBlockCollision(blockState);
                                    } catch (Throwable var14) {
                                        CrashReport crashReport = CrashReport.create(var14, "Colliding entity with block");
                                        CrashReportSection crashReportSection = crashReport.addElement("Block being collided with");
                                        CrashReportSection.addBlockInfo(crashReportSection, this.getWorld(), pos, blockState);
                                        CrashReportSection crashReportSection2 = crashReport.addElement("murat.simv2.simulation.mirror.net.minecraft.entity.Entity being checked for collision");
                                        this.populateCrashReport(crashReportSection2);
                                        throw new CrashException(crashReport);
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

    private void playStepSounds(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos pos, BlockState state) {
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
        BlockState blockState = this.getWorld().getBlockState(blockPos);
        return (!blockState.isIn(BlockTags.INSIDE_STEP_SOUND_BLOCKS)) && (!blockState.isIn(BlockTags.COMBINATION_STEP_SOUND_BLOCKS)) ? pos : blockPos;
    }

    protected void playCombinationStepSounds(BlockState primaryState, BlockState secondaryState) {
        BlockSoundGroup blockSoundGroup = primaryState.getSoundGroup();
        this.playSound(blockSoundGroup.getStepSound(), blockSoundGroup.getVolume() * 0.15F, blockSoundGroup.getPitch());
        this.playSecondaryStepSound(secondaryState);
    }

    protected void playSecondaryStepSound(BlockState state) {
        BlockSoundGroup blockSoundGroup = state.getSoundGroup();
        this.playSound(blockSoundGroup.getStepSound(), blockSoundGroup.getVolume() * 0.05F, blockSoundGroup.getPitch() * 0.8F);
    }

    protected void playStepSound(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos pos, BlockState state) {
        BlockSoundGroup blockSoundGroup = state.getSoundGroup();
        this.playSound(blockSoundGroup.getStepSound(), blockSoundGroup.getVolume() * 0.15F, blockSoundGroup.getPitch());
    }

    private boolean shouldPlayAmethystChimeSound(BlockState state) {
        return state.isIn(BlockTags.CRYSTAL_SOUND_BLOCKS) && (this.age >= (this.lastChimeAge + 20));
    }

    private void playAmethystChimeSound() {
        this.lastChimeIntensity = this.lastChimeIntensity * ((float) (Math.pow(0.997, this.age - this.lastChimeAge)));
        this.lastChimeIntensity = Math.min(1.0F, this.lastChimeIntensity + 0.07F);
        float f = 0.5F + ((this.lastChimeIntensity * this.random.nextFloat()) * 1.2F);
        float g = 0.1F + (this.lastChimeIntensity * 1.2F);
        this.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, g, f);
        this.lastChimeAge = this.age;
    }

    protected void playSwimSound(float volume) {
        this.playSound(this.getSwimSound(), volume, 1.0F + ((this.random.nextFloat() - this.random.nextFloat()) * 0.4F));
    }

    public boolean isSilent() {
        return this.dataTracker.get(Entity.SILENT);
    }

    public boolean hasNoGravity() {
        return this.dataTracker.get(Entity.NO_GRAVITY);
    }

    public double getFinalGravity() {
        return this.hasNoGravity() ? 0.0 : this.getGravity();
    }

    protected void fall(double heightDifference, boolean onGround, BlockState state, murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos landedPosition) {
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
            this.getWorld().addParticleClient(ParticleTypes.BUBBLE, this.getX() + d, h + 1.0F, this.getZ() + e, vec3d.x, vec3d.y - (this.random.nextDouble() * 0.2F), vec3d.z);
        }
        for (int i = 0; i < (1.0F + (this.dimensions.width() * 20.0F)); i++) {
            double d = ((this.random.nextDouble() * 2.0) - 1.0) * this.dimensions.width();
            double e = ((this.random.nextDouble() * 2.0) - 1.0) * this.dimensions.width();
            this.getWorld().addParticleClient(ParticleTypes.SPLASH, this.getX() + d, h + 1.0F, this.getZ() + e, vec3d.x, vec3d.y, vec3d.z);
        }
    }

    @Deprecated
    protected BlockState getLandingBlockState() {
        return this.getWorld().getBlockState(this.getLandingPos());
    }

    public boolean isSubmergedIn(TagKey<Fluid> fluidTag) {
        return this.submergedFluidTag.contains(fluidTag);
    }

    public boolean isInLava() {
        return (!this.firstUpdate) && (this.fluidHeight.getDouble(FluidTags.LAVA) > 0.0);
    }

    public void updateVelocity(float speed, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movementInput) {
        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = Entity.movementInputToVelocity(movementInput, speed, this.getYaw());
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
    public void serverDamage(DamageSource source, float amount) {
        if (this.world instanceof ServerWorld serverWorld) {
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
        return (!this.isFireImmune()) && ((this.fireTicks > 0) || (bl && this.getFlag(Entity.ON_FIRE_FLAG_INDEX)));
    }

    public boolean hasVehicle() {
        return this.getVehicle() != null;
    }

    public boolean isSneaky() {
        return this.isSneaking();
    }

    public boolean isSprinting() {
        return this.getFlag(Entity.SPRINTING_FLAG_INDEX);
    }

    public boolean isSwimming() {
        return this.getFlag(Entity.SWIMMING_FLAG_INDEX);
    }

    public boolean isInSwimmingPose() {
        return this.isInPose(EntityPose.SWIMMING);
    }

    public boolean isCrawling() {
        return this.isInSwimmingPose() && (!this.isTouchingWater());
    }

    public boolean isOnRail() {
        return false;
    }

    @Nullable
    public Team getScoreboardTeam() {
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
    AbstractTeam team) {
        return this.getScoreboardTeam() != null ? this.getScoreboardTeam().isEqual(team) : false;
    }

    protected boolean getFlag(int index) {
        return (this.dataTracker.get(Entity.FLAGS) & (1 << index)) != 0;
    }

    public int getMaxAir() {
        return 300;
    }

    public int getFrozenTicks() {
        return this.dataTracker.get(Entity.FROZEN_TICKS);
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

    protected boolean isAlwaysInvulnerableTo(DamageSource damageSource) {
        return ((this.isRemoved() || ((this.invulnerable && (!damageSource.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY))) && (!damageSource.isSourceCreativePlayer()))) || (damageSource.isIn(DamageTypeTags.IS_FIRE) && this.isFireImmune())) || (damageSource.isIn(DamageTypeTags.IS_FALL) && this.getType().isIn(EntityTypeTags.FALL_DAMAGE_IMMUNE));
    }

    public void onTrackedDataSet(murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData<?> data) {
        if (Entity.POSE.equals(data)) {
            this.calculateDimensions();
        }
    }

    public void calculateDimensions() {
        murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions entityDimensions = this.dimensions;
        EntityPose entityPose = this.getPose();
        murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions entityDimensions2 = this.getDimensions(entityPose);
        this.dimensions = entityDimensions2;
        this.refreshPosition();
        boolean bl = (entityDimensions2.width() <= 4.0F) && (entityDimensions2.height() <= 4.0F);
        if ((((((!this.world.isClient) && (!this.firstUpdate)) && (!this.noClip)) && bl) && ((entityDimensions2.width() > entityDimensions.width()) || (entityDimensions2.height() > entityDimensions.height()))) && (!(this instanceof PlayerEntity))) {
            this.recalculateDimensions(entityDimensions);
        }
    }

    public boolean recalculateDimensions(murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions previous) {
        murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions entityDimensions = this.getDimensions(this.getPose());
        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = this.getPos().add(0.0, previous.height() / 2.0, 0.0);
        double d = Math.max(0.0F, entityDimensions.width() - previous.width()) + 1.0E-6;
        double e = Math.max(0.0F, entityDimensions.height() - previous.height()) + 1.0E-6;
        VoxelShape voxelShape = murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShapes.cuboid(murat.simv2.simulation.mirror.net.minecraft.util.math.Box.of(vec3d, d, e, d));
        Optional<murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d> optional = this.world.findClosestCollision(this, voxelShape, vec3d, entityDimensions.width(), entityDimensions.height(), entityDimensions.width());
        if (optional.isPresent()) {
            this.setPosition(((murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d) (optional.get())).add(0.0, (-entityDimensions.height()) / 2.0, 0.0));
        } else if ((entityDimensions.width() > previous.width()) && (entityDimensions.height() > previous.height())) {
            VoxelShape voxelShape2 = murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShapes.cuboid(murat.simv2.simulation.mirror.net.minecraft.util.math.Box.of(vec3d, d, 1.0E-6, d));
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

    public boolean updateMovementInFluid(TagKey<Fluid> tag, double speed) {
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
                        FluidState fluidState = this.getWorld().getFluidState(mutable);
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
                if (!(this instanceof PlayerEntity)) {
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

    public double getFluidHeight(TagKey<Fluid> fluid) {
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

    public murat.simv2.simulation.mirror.net.minecraft.entity.EntityDimensions getDimensions(EntityPose pose) {
        return this.type.getDimensions();
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getPos() {
        return this.pos;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos getBlockPos() {
        return this.blockPos;
    }

    public BlockState getBlockStateAtPos() {
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
        return !this.getType().isIn(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES);
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

    public World getWorld() {
        return this.world;
    }

    public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d getMovement() {
        return (this.getControllingPassenger() instanceof murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity playerEntity) && this.isAlive() ? playerEntity.getMovement() : this.getVelocity();
    }

    public Entity() {
    }

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static class MoveEffect {
        public MoveEffect(java.lang.String p0, int p1, boolean p2, boolean p3) {
        }

        public boolean emitsGameEvents() {
            return false;
        }

        public boolean hasAny() {
            return false;
        }

        public boolean playsSounds() {
            return false;
        }

        public MoveEffect() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
