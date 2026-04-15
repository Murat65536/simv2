package murat.simv2.simulation.mirror.net.minecraft.entity;

import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.floats.FloatArraySet;
import it.unimi.dsi.fastutil.floats.FloatArrays;
import it.unimi.dsi.fastutil.floats.FloatSet;
import it.unimi.dsi.fastutil.objects.Object2DoubleArrayMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker;
import murat.simv2.simulation.mirror.net.minecraft.entity.data.TrackedData;
import murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerEntity;
import murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Direction;
import murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d;
import murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShapes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.PositionInterpolator;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
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
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.profiler.Profilers;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

// Mirrored from net.minecraft.entity.Entity
// Movement-relevant statements only (WALA backward slice + Spoon AST pruning)
// Generated - do not edit
public abstract class Entity {
    public static final ImmutableList<net.minecraft.util.math.Direction.Axis> X_THEN_Z = ImmutableList.of(net.minecraft.util.math.Direction.Axis.Y, net.minecraft.util.math.Direction.Axis.X, net.minecraft.util.math.Direction.Axis.Z);

    public static final ImmutableList<net.minecraft.util.math.Direction.Axis> Z_THEN_X = ImmutableList.of(net.minecraft.util.math.Direction.Axis.Y, net.minecraft.util.math.Direction.Axis.Z, net.minecraft.util.math.Direction.Axis.X);

    public static final Box NULL_BOX = new Box(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

    public EntityType<?> type;

    @Nullable
    public net.minecraft.entity.Entity vehicle;

    public World world;

    public net.minecraft.util.math.Vec3d pos;

    public net.minecraft.util.math.BlockPos blockPos;

    public net.minecraft.util.math.Vec3d velocity = net.minecraft.util.math.Vec3d.ZERO;

    public float yaw;

    public float pitch;

    public Box boundingBox = net.minecraft.entity.Entity.NULL_BOX;

    public boolean onGround;

    public boolean horizontalCollision;

    public boolean verticalCollision;

    public boolean groundCollision;

    public net.minecraft.util.math.Vec3d movementMultiplier = net.minecraft.util.math.Vec3d.ZERO;

    @Nullable
    public net.minecraft.entity.Entity.RemovalReason removalReason;

    public float distanceTraveled;

    public double fallDistance;

    public float nextStepSoundDistance = 1.0F;

    public double lastRenderX;

    public double lastRenderY;

    public double lastRenderZ;

    public boolean noClip;

    public final Random random = Random.create();

    public boolean touchingWater;

    public Object2DoubleMap<TagKey<Fluid>> fluidHeight = new Object2DoubleArrayMap<>(2);

    public final Set<TagKey<Fluid>> submergedFluidTag = new HashSet();

    public boolean firstUpdate = true;

    public net.minecraft.entity.data.DataTracker dataTracker;

    public static final net.minecraft.entity.data.TrackedData<Byte> FLAGS = net.minecraft.entity.Entity.FLAGS;

    public static final int SNEAKING_FLAG_INDEX = 1;

    public static final int SPRINTING_FLAG_INDEX = 3;

    public static final int SWIMMING_FLAG_INDEX = 4;

    public static final net.minecraft.entity.data.TrackedData<Integer> AIR = net.minecraft.entity.Entity.AIR;

    public static final net.minecraft.entity.data.TrackedData<Optional<Text>> CUSTOM_NAME = net.minecraft.entity.Entity.CUSTOM_NAME;

    public static final net.minecraft.entity.data.TrackedData<Boolean> NAME_VISIBLE = net.minecraft.entity.Entity.NAME_VISIBLE;

    public static final net.minecraft.entity.data.TrackedData<Boolean> SILENT = net.minecraft.entity.Entity.SILENT;

    public static final net.minecraft.entity.data.TrackedData<Boolean> NO_GRAVITY = net.minecraft.entity.Entity.NO_GRAVITY;

    public static final net.minecraft.entity.data.TrackedData<EntityPose> POSE = net.minecraft.entity.Entity.POSE;

    public static final net.minecraft.entity.data.TrackedData<Integer> FROZEN_TICKS = net.minecraft.entity.Entity.FROZEN_TICKS;

    public boolean invulnerable;

    public UUID uuid = net.minecraft.util.math.MathHelper.randomUuid(this.random);

    public final double[] pistonMovementDelta = new double[]{ 0.0, 0.0, 0.0 };

    public net.minecraft.entity.EntityDimensions dimensions;

    public float standingEyeHeight;

    public Optional<net.minecraft.util.math.BlockPos> supportingBlockPos = Optional.empty();

    @Nullable
    public BlockState stateAtPos = null;

    public final List<Object> currentlyCheckedCollisions = new ObjectArrayList<>();

    public Entity(EntityType<?> type, World world) {
        this.entityBridge = claimEntityBridgeBootstrap();
        this.dimensions = type.getDimensions();
        this.pos = net.minecraft.util.math.Vec3d.ZERO;
        net.minecraft.entity.data.DataTracker.Builder builder = new net.minecraft.entity.data.DataTracker.Builder(((net.minecraft.entity.Entity) (this.entityBridge)));
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

    public boolean isSpectator() {
        return false;
    }

    public EntityType<?> getType() {
        return this.type;
    }

    protected abstract void initDataTracker(net.minecraft.entity.data.DataTracker.Builder builder);

    public net.minecraft.entity.data.DataTracker getDataTracker() {
        return this.dataTracker;
    }

    public EntityPose getPose() {
        return this.dataTracker.get(Entity.POSE);
    }

    public boolean isInPose(EntityPose pose) {
        return this.getPose() == pose;
    }

    public void setPosition(net.minecraft.util.math.Vec3d pos) {
        this.setPosition(pos.getX(), pos.getY(), pos.getZ());
    }

    public void setPosition(double x, double y, double z) {
        this.setPos(x, y, z);
        this.setBoundingBox(this.calculateBoundingBox());
    }

    protected Box calculateBoundingBox() {
        return this.calculateDefaultBoundingBox(this.pos);
    }

    protected Box calculateDefaultBoundingBox(net.minecraft.util.math.Vec3d pos) {
        return this.dimensions.getBoxAt(pos);
    }

    protected void refreshPosition() {
        this.setPosition(this.pos.x, this.pos.y, this.pos.z);
    }

    public boolean doesNotCollide(double offsetX, double offsetY, double offsetZ) {
        return this.doesNotCollide(this.getBoundingBox().offset(offsetX, offsetY, offsetZ));
    }

    private boolean doesNotCollide(Box box) {
        return this.getWorld().isSpaceEmpty(((net.minecraft.entity.Entity) (this.entityBridge)), box) && (!this.getWorld().containsFluid(box));
    }

    public void setMovement(boolean onGround, boolean horizontalCollision, net.minecraft.util.math.Vec3d movement) {
        this.onGround = onGround;
        this.horizontalCollision = horizontalCollision;
    }

    public boolean isOnGround() {
        return this.onGround;
    }

    public void move(net.minecraft.entity.MovementType type, net.minecraft.util.math.Vec3d movement) {
        if (this.noClip) {
            this.setPosition(this.getX() + movement.x, this.getY() + movement.y, this.getZ() + movement.z);
        } else {
            if (type == net.minecraft.entity.MovementType.PISTON) {
                movement = this.adjustMovementForPiston(movement);
            }
            Profiler profiler = Profilers.get();
            if (this.movementMultiplier.lengthSquared() > 1.0E-7) {
                movement = movement.multiply(this.movementMultiplier);
                this.setVelocity(net.minecraft.util.math.Vec3d.ZERO);
            }
            movement = this.adjustMovementForSneaking(movement, type);
            net.minecraft.util.math.Vec3d vec3d = this.adjustMovementForCollisions(movement);
            double d = vec3d.lengthSquared();
            if ((d > 1.0E-7) || ((movement.lengthSquared() - d) < 1.0E-7)) {
                if ((this.fallDistance != 0.0) && (d >= 1.0)) {
                    BlockHitResult blockHitResult = this.getWorld().raycast(new RaycastContext(this.getPos(), this.getPos().add(vec3d), RaycastContext.ShapeType.FALLDAMAGE_RESETTING, RaycastContext.FluidHandling.WATER, ((net.minecraft.entity.Entity) (this.entityBridge))));
                    if (blockHitResult.getType() != HitResult.Type.MISS) {
                        this.onLanding();
                    }
                }
                net.minecraft.util.math.Vec3d vec3d2 = this.getPos();
                List<net.minecraft.entity.Entity.QueuedCollisionCheck> list = new ObjectArrayList<>();
                for (net.minecraft.util.math.Direction.Axis axis : Entity.getAxisCheckOrder(vec3d)) {
                    double e = vec3d.getComponentAlongAxis(axis);
                    if (e != 0.0) {
                        net.minecraft.util.math.Vec3d vec3d3 = vec3d2.offset(axis.getPositiveDirection(), e);
                        vec3d2 = vec3d3;
                    }
                }
                this.setPosition(vec3d2);
            }
            boolean bl = !net.minecraft.util.math.MathHelper.approximatelyEquals(movement.x, vec3d.x);
            boolean bl2 = !net.minecraft.util.math.MathHelper.approximatelyEquals(movement.z, vec3d.z);
            this.horizontalCollision = bl || bl2;
            if ((Math.abs(movement.y) > 0.0) || this.isLogicalSideForUpdatingMovement()) {
                this.verticalCollision = movement.y != vec3d.y;
                this.groundCollision = this.verticalCollision && (movement.y < 0.0);
                this.setMovement(this.groundCollision, this.horizontalCollision, vec3d);
            }
            net.minecraft.util.math.BlockPos blockPos = this.getLandingPos();
            BlockState blockState = this.getWorld().getBlockState(blockPos);
            if (this.isLogicalSideForUpdatingMovement()) {
                this.fall(vec3d.y, this.isOnGround(), blockState, blockPos);
            }
            if (this.isRemoved()) {
            } else {
                if (this.horizontalCollision) {
                    net.minecraft.util.math.Vec3d vec3d4 = this.getVelocity();
                    this.setVelocity(bl ? 0.0 : vec3d4.x, vec3d4.y, bl2 ? 0.0 : vec3d4.z);
                }
                if ((!this.getWorld().isClient()) || this.isLogicalSideForUpdatingMovement()) {
                    net.minecraft.entity.Entity.MoveEffect moveEffect = this.getMoveEffect();
                    if (moveEffect.hasAny() && (!this.hasVehicle())) {
                        this.applyMoveEffect(moveEffect, vec3d, blockPos, blockState);
                    }
                }
                float f = this.getVelocityMultiplier();
                this.setVelocity(this.getVelocity().multiply(f, 1.0, f));
            }
        }
    }

    protected void applyMoveEffect(net.minecraft.entity.Entity.MoveEffect moveEffect, net.minecraft.util.math.Vec3d movement, net.minecraft.util.math.BlockPos landingPos, BlockState landingState) {
        float g = ((float) (movement.length() * 0.6F));
        float h = ((float) (movement.horizontalLength() * 0.6F));
        net.minecraft.util.math.BlockPos blockPos = this.getSteppingPos();
        BlockState blockState = this.getWorld().getBlockState(blockPos);
        boolean bl = this.canClimb(blockState);
        this.distanceTraveled += (bl) ? g : h;
        if ((this.distanceTraveled > this.nextStepSoundDistance) && (!blockState.isAir())) {
            boolean bl2 = blockPos.equals(landingPos);
            boolean bl3 = this.stepOnBlock(landingPos, landingState, moveEffect.playsSounds(), bl2, movement);
            if (!bl2) {
                bl3 |= this.stepOnBlock(blockPos, blockState, false, moveEffect.emitsGameEvents(), movement);
            }
        }
    }

    protected void tickBlockCollision() {
        if (this.currentlyCheckedCollisions.isEmpty()) {
            this.currentlyCheckedCollisions.add(new net.minecraft.entity.Entity.QueuedCollisionCheck(this.getLastRenderPos(), this.getPos()));
        }
    }

    private boolean canClimb(BlockState state) {
        return state.isIn(BlockTags.CLIMBABLE) || state.isOf(Blocks.POWDER_SNOW);
    }

    private boolean stepOnBlock(net.minecraft.util.math.BlockPos pos, BlockState state, boolean playSound, boolean emitEvent, net.minecraft.util.math.Vec3d movement) {
        if (state.isAir()) {
        } else {
            boolean bl = this.canClimb(state);
        }
        return false;
    }

    @Deprecated
    public net.minecraft.util.math.BlockPos getLandingPos() {
        return this.getPosWithYOffset(0.2F);
    }

    public net.minecraft.util.math.BlockPos getVelocityAffectingPos() {
        return this.getPosWithYOffset(0.500001F);
    }

    public net.minecraft.util.math.BlockPos getSteppingPos() {
        return this.getPosWithYOffset(1.0E-5F);
    }

    protected net.minecraft.util.math.BlockPos getPosWithYOffset(float offset) {
        if (this.supportingBlockPos.isPresent()) {
            net.minecraft.util.math.BlockPos blockPos = ((net.minecraft.util.math.BlockPos) (this.supportingBlockPos.get()));
            if (!(offset > 1.0E-5F)) {
                return blockPos;
            } else {
                BlockState blockState = this.getWorld().getBlockState(blockPos);
                return (((!(offset <= 0.5)) || (!blockState.isIn(BlockTags.FENCES))) && (!blockState.isIn(BlockTags.WALLS))) && (!(blockState.getBlock() instanceof FenceGateBlock)) ? blockPos.withY(net.minecraft.util.math.MathHelper.floor(this.pos.y - offset)) : blockPos;
            }
        } else {
            int i = net.minecraft.util.math.MathHelper.floor(this.pos.x);
            int j = net.minecraft.util.math.MathHelper.floor(this.pos.y - offset);
            int k = net.minecraft.util.math.MathHelper.floor(this.pos.z);
            return new net.minecraft.util.math.BlockPos(i, j, k);
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
        if ((!blockState.isOf(Blocks.WATER)) && (!blockState.isOf(Blocks.BUBBLE_COLUMN))) {
            return f == 1.0 ? this.getWorld().getBlockState(this.getVelocityAffectingPos()).getBlock().getVelocityMultiplier() : f;
        } else {
            return f;
        }
    }

    protected net.minecraft.util.math.Vec3d adjustMovementForSneaking(net.minecraft.util.math.Vec3d movement, net.minecraft.entity.MovementType type) {
        return movement;
    }

    protected net.minecraft.util.math.Vec3d adjustMovementForPiston(net.minecraft.util.math.Vec3d movement) {
        if (movement.lengthSquared() <= 1.0E-7) {
            return movement;
        } else {
            long l = this.getWorld().getTime();
            if (movement.x != 0.0) {
                double d = this.calculatePistonMovementFactor(net.minecraft.util.math.Direction.Axis.X, movement.x);
                return Math.abs(d) <= 1.0E-5F ? net.minecraft.util.math.Vec3d.ZERO : new net.minecraft.util.math.Vec3d(d, 0.0, 0.0);
            } else if (movement.y != 0.0) {
                double d = this.calculatePistonMovementFactor(net.minecraft.util.math.Direction.Axis.Y, movement.y);
                return Math.abs(d) <= 1.0E-5F ? net.minecraft.util.math.Vec3d.ZERO : new net.minecraft.util.math.Vec3d(0.0, d, 0.0);
            } else if (movement.z != 0.0) {
                double d = this.calculatePistonMovementFactor(net.minecraft.util.math.Direction.Axis.Z, movement.z);
                return Math.abs(d) <= 1.0E-5F ? net.minecraft.util.math.Vec3d.ZERO : new net.minecraft.util.math.Vec3d(0.0, 0.0, d);
            } else {
                return net.minecraft.util.math.Vec3d.ZERO;
            }
        }
    }

    private double calculatePistonMovementFactor(net.minecraft.util.math.Direction.Axis axis, double offsetFactor) {
        int i = axis.ordinal();
        double d = net.minecraft.util.math.MathHelper.clamp(offsetFactor + this.pistonMovementDelta[i], -0.51, 0.51);
        offsetFactor = d - this.pistonMovementDelta[i];
        return offsetFactor;
    }

    protected net.minecraft.util.math.Vec3d adjustMovementForCollisions(net.minecraft.util.math.Vec3d movement) {
        Box box = this.getBoundingBox();
        List<VoxelShape> list = this.getWorld().getEntityCollisions(((net.minecraft.entity.Entity) (this.entityBridge)), box.stretch(movement));
        net.minecraft.util.math.Vec3d vec3d = (movement.lengthSquared() == 0.0) ? movement : Entity.adjustMovementForCollisions(((net.minecraft.entity.Entity) (this.entityBridge)), movement, box, this.getWorld(), list);
        boolean bl = movement.x != vec3d.x;
        boolean bl2 = movement.y != vec3d.y;
        boolean bl3 = movement.z != vec3d.z;
        boolean bl4 = bl2 && (movement.y < 0.0);
        if (((this.getStepHeight() > 0.0F) && (bl4 || this.isOnGround())) && (bl || bl3)) {
            Box box2 = (bl4) ? box.offset(0.0, vec3d.y, 0.0) : box;
            Box box3 = box2.stretch(movement.x, this.getStepHeight(), movement.z);
            List<VoxelShape> list2 = Entity.findCollisionsForMovement(((net.minecraft.entity.Entity) (this.entityBridge)), this.world, list, box3);
            float f = ((float) (vec3d.y));
            float[] fs = Entity.collectStepHeights(box2, list2, this.getStepHeight(), f);
            for (float g : fs) {
                net.minecraft.util.math.Vec3d vec3d2 = Entity.adjustMovementForCollisions(new net.minecraft.util.math.Vec3d(movement.x, g, movement.z), box2, list2);
                if (vec3d2.horizontalLengthSquared() > vec3d.horizontalLengthSquared()) {
                    double d = box.minY - box2.minY;
                    return vec3d2.subtract(0.0, d, 0.0);
                }
            }
        }
        return vec3d;
    }

    private static float[] collectStepHeights(Box collisionBox, List<VoxelShape> collisions, float f, float stepHeight) {
        FloatSet floatSet = new FloatArraySet(4);
        for (VoxelShape voxelShape : collisions) {
            for (double d : voxelShape.getPointPositions(net.minecraft.util.math.Direction.Axis.Y)) {
                float g = ((float) (d - collisionBox.minY));
                if ((!(g < 0.0F)) && (g != stepHeight)) {
                    if (g > f) {
                        break;
                    }
                    floatSet.add(g);
                }
            }
        }
        float[] fs = floatSet.toFloatArray();
        FloatArrays.unstableSort(fs);
        return fs;
    }

    public static net.minecraft.util.math.Vec3d adjustMovementForCollisions(@Nullable
    net.minecraft.entity.Entity entity, net.minecraft.util.math.Vec3d movement, Box entityBoundingBox, World world, List<VoxelShape> collisions) {
        List<VoxelShape> list = Entity.findCollisionsForMovement(entity, world, collisions, entityBoundingBox.stretch(movement));
        return Entity.adjustMovementForCollisions(movement, entityBoundingBox, list);
    }

    private static List<VoxelShape> findCollisionsForMovement(@Nullable
    net.minecraft.entity.Entity entity, World world, List<VoxelShape> regularCollisions, Box movingEntityBoundingBox) {
        Builder<VoxelShape> builder = ImmutableList.builderWithExpectedSize(regularCollisions.size() + 1);
        WorldBorder worldBorder = world.getWorldBorder();
        boolean bl = (entity != null) && worldBorder.canCollide(entity, movingEntityBoundingBox);
        return builder.build();
    }

    protected static net.minecraft.util.math.Vec3d adjustMovementForCollisions(net.minecraft.util.math.Vec3d movement, Box entityBoundingBox, List<VoxelShape> collisions) {
        if (collisions.isEmpty()) {
            return movement;
        } else {
            net.minecraft.util.math.Vec3d vec3d = net.minecraft.util.math.Vec3d.ZERO;
            for (net.minecraft.util.math.Direction.Axis axis : Entity.getAxisCheckOrder(movement)) {
                double d = movement.getComponentAlongAxis(axis);
                if (d != 0.0) {
                    double e = net.minecraft.util.shape.VoxelShapes.calculateMaxOffset(axis, entityBoundingBox.offset(vec3d), collisions, d);
                    vec3d = vec3d.withAxis(axis, e);
                }
            }
            return vec3d;
        }
    }

    private static Iterable<net.minecraft.util.math.Direction.Axis> getAxisCheckOrder(net.minecraft.util.math.Vec3d movement) {
        return Math.abs(movement.x) < Math.abs(movement.z) ? Entity.Z_THEN_X : Entity.X_THEN_Z;
    }

    protected SoundEvent getSplashSound() {
        return SoundEvents.ENTITY_GENERIC_SPLASH;
    }

    protected SoundEvent getHighSpeedSplashSound() {
        return SoundEvents.ENTITY_GENERIC_SPLASH;
    }

    public void emitGameEvent(RegistryEntry<GameEvent> event, @Nullable
    net.minecraft.entity.Entity entity) {
        this.getWorld().emitGameEvent(entity, event, this.pos);
    }

    public void emitGameEvent(RegistryEntry<GameEvent> event) {
        ((net.minecraft.entity.Entity) (this.entityBridge)).emitGameEvent(event, ((net.minecraft.entity.Entity) (this.entityBridge)));
    }

    public void playSound(SoundEvent sound, float volume, float pitch) {
        if (!this.isSilent()) {
            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), sound, this.getSoundCategory(), volume, pitch);
        }
    }

    public boolean isSilent() {
        return this.dataTracker.get(Entity.SILENT);
    }

    public boolean hasNoGravity() {
        return this.dataTracker.get(Entity.NO_GRAVITY);
    }

    protected double getGravity() {
        return 0.0;
    }

    public double getFinalGravity() {
        return this.hasNoGravity() ? 0.0 : this.getGravity();
    }

    protected net.minecraft.entity.Entity.MoveEffect getMoveEffect() {
        return net.minecraft.entity.Entity.MoveEffect.ALL;
    }

    protected void fall(double heightDifference, boolean onGround, BlockState state, net.minecraft.util.math.BlockPos landedPosition) {
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
        net.minecraft.util.math.BlockPos blockPos = this.getBlockPos();
        return this.getWorld().hasRain(blockPos) || this.getWorld().hasRain(net.minecraft.util.math.BlockPos.ofFloored(blockPos.getX(), this.getBoundingBox().maxY, blockPos.getZ()));
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
        net.minecraft.entity.Entity entity = ((net.minecraft.entity.Entity) (Objects.requireNonNullElse(this.getControllingPassenger(), ((net.minecraft.entity.Entity) (this.entityBridge)))));
        float f = (entity == this.entityBridge) ? 0.2F : 0.9F;
        net.minecraft.util.math.Vec3d vec3d = entity.getVelocity();
        float g = Math.min(1.0F, ((float) (Math.sqrt((((vec3d.x * vec3d.x) * 0.2F) + (vec3d.y * vec3d.y)) + ((vec3d.z * vec3d.z) * 0.2F)))) * f);
        if (g < 0.25F) {
            this.playSound(this.getSplashSound(), g, 1.0F + ((this.random.nextFloat() - this.random.nextFloat()) * 0.4F));
        } else {
            this.playSound(this.getHighSpeedSplashSound(), g, 1.0F + ((this.random.nextFloat() - this.random.nextFloat()) * 0.4F));
        }
        float h = net.minecraft.util.math.MathHelper.floor(this.getY());
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

    public boolean isSubmergedIn(TagKey<Fluid> fluidTag) {
        return this.submergedFluidTag.contains(fluidTag);
    }

    public boolean isInLava() {
        return (!this.firstUpdate) && (this.fluidHeight.getDouble(FluidTags.LAVA) > 0.0);
    }

    public void updateVelocity(float speed, net.minecraft.util.math.Vec3d movementInput) {
        net.minecraft.util.math.Vec3d vec3d = Entity.movementInputToVelocity(movementInput, speed, this.getYaw());
        this.setVelocity(this.getVelocity().add(vec3d));
    }

    protected static net.minecraft.util.math.Vec3d movementInputToVelocity(net.minecraft.util.math.Vec3d movementInput, float speed, float yaw) {
        double d = movementInput.lengthSquared();
        if (d < 1.0E-7) {
            return net.minecraft.util.math.Vec3d.ZERO;
        } else {
            net.minecraft.util.math.Vec3d vec3d = (d > 1.0 ? movementInput.normalize() : movementInput).multiply(speed);
            float f = net.minecraft.util.math.MathHelper.sin(yaw * ((float) (Math.PI / 180.0)));
            float g = net.minecraft.util.math.MathHelper.cos(yaw * ((float) (Math.PI / 180.0)));
            return new net.minecraft.util.math.Vec3d((vec3d.x * g) - (vec3d.z * f), vec3d.y, (vec3d.z * g) + (vec3d.x * f));
        }
    }

    public net.minecraft.util.math.Vec3d getLastRenderPos() {
        return new net.minecraft.util.math.Vec3d(this.lastRenderX, this.lastRenderY, this.lastRenderZ);
    }

    @Deprecated
    public void serverDamage(DamageSource source, float amount) {
        if (this.world instanceof ServerWorld serverWorld) {
            this.damage(serverWorld, source, amount);
        }
    }

    public abstract boolean damage(ServerWorld world, DamageSource source, float amount);

    public net.minecraft.util.math.Vec3d getRotationVector(float pitch, float yaw) {
        float f = pitch * ((float) (Math.PI / 180.0));
        float g = (-yaw) * ((float) (Math.PI / 180.0));
        float h = net.minecraft.util.math.MathHelper.cos(g);
        float i = net.minecraft.util.math.MathHelper.sin(g);
        float j = net.minecraft.util.math.MathHelper.cos(f);
        float k = net.minecraft.util.math.MathHelper.sin(f);
        return new net.minecraft.util.math.Vec3d(i * j, -k, h * j);
    }

    public boolean isInterpolating() {
        return (this.getInterpolator() != null) && this.getInterpolator().isInterpolating();
    }

    @Nullable
    public PositionInterpolator getInterpolator() {
        return null;
    }

    public net.minecraft.util.math.Vec3d getRotationVector() {
        return this.getRotationVector(this.getPitch(), this.getYaw());
    }

    public net.minecraft.util.math.Vec2f getRotationClient() {
        return new net.minecraft.util.math.Vec2f(this.getPitch(), this.getYaw());
    }

    public net.minecraft.util.math.Vec3d getRotationVecClient() {
        return net.minecraft.util.math.Vec3d.fromPolar(this.getRotationClient());
    }

    public boolean hasVehicle() {
        return this.getVehicle() != null;
    }

    public boolean isSneaking() {
        return this.getFlag(Entity.SNEAKING_FLAG_INDEX);
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

    public UUID getUuid() {
        return this.uuid;
    }

    public boolean isPushedByFluids() {
        return true;
    }

    public void onTrackedDataSet(net.minecraft.entity.data.TrackedData<?> data) {
        if (Entity.POSE.equals(data)) {
            this.calculateDimensions();
        }
    }

    public void calculateDimensions() {
        net.minecraft.entity.EntityDimensions entityDimensions = this.dimensions;
        EntityPose entityPose = this.getPose();
        net.minecraft.entity.EntityDimensions entityDimensions2 = this.getDimensions(entityPose);
        this.dimensions = entityDimensions2;
        this.refreshPosition();
        boolean bl = (entityDimensions2.width() <= 4.0F) && (entityDimensions2.height() <= 4.0F);
        if ((((((!this.world.isClient) && (!this.firstUpdate)) && (!this.noClip)) && bl) && ((entityDimensions2.width() > entityDimensions.width()) || (entityDimensions2.height() > entityDimensions.height()))) && (!(this instanceof PlayerEntity))) {
            this.recalculateDimensions(entityDimensions);
        }
    }

    public void recalculateDimensions(net.minecraft.entity.EntityDimensions previous) {
        net.minecraft.entity.EntityDimensions entityDimensions = this.getDimensions(this.getPose());
        net.minecraft.util.math.Vec3d vec3d = this.getPos().add(0.0, previous.height() / 2.0, 0.0);
        double d = Math.max(0.0F, entityDimensions.width() - previous.width()) + 1.0E-6;
        double e = Math.max(0.0F, entityDimensions.height() - previous.height()) + 1.0E-6;
        VoxelShape voxelShape = net.minecraft.util.shape.VoxelShapes.cuboid(Box.of(vec3d, d, e, d));
        Optional<net.minecraft.util.math.Vec3d> optional = this.world.findClosestCollision(((net.minecraft.entity.Entity) (this.entityBridge)), voxelShape, vec3d, entityDimensions.width(), entityDimensions.height(), entityDimensions.width());
        if (optional.isPresent()) {
            this.setPosition(((net.minecraft.util.math.Vec3d) (optional.get())).add(0.0, (-entityDimensions.height()) / 2.0, 0.0));
        } else if ((entityDimensions.width() > previous.width()) && (entityDimensions.height() > previous.height())) {
            VoxelShape voxelShape2 = net.minecraft.util.shape.VoxelShapes.cuboid(Box.of(vec3d, d, 1.0E-6, d));
            Optional<net.minecraft.util.math.Vec3d> optional2 = this.world.findClosestCollision(((net.minecraft.entity.Entity) (this.entityBridge)), voxelShape2, vec3d, entityDimensions.width(), previous.height(), entityDimensions.width());
            if (optional2.isPresent()) {
                this.setPosition(((net.minecraft.util.math.Vec3d) (optional2.get())).add(0.0, ((-previous.height()) / 2.0) + 1.0E-6, 0.0));
            }
        }
    }

    public Box getBoundingBox() {
        return this.boundingBox;
    }

    public void setBoundingBox(Box boundingBox) {
        this.boundingBox = boundingBox;
    }

    public float getStandingEyeHeight() {
        return this.standingEyeHeight;
    }

    @Nullable
    public net.minecraft.entity.LivingEntity getControllingPassenger() {
        return null;
    }

    public boolean isLogicalSideForUpdatingMovement() {
        return this.world.isClient() ? this.isControlledByMainPlayer() : !this.isControlledByPlayer();
    }

    protected boolean isControlledByMainPlayer() {
        net.minecraft.entity.LivingEntity livingEntity = this.getControllingPassenger();
        return (livingEntity != null) && livingEntity.isControlledByMainPlayer();
    }

    public boolean isControlledByPlayer() {
        net.minecraft.entity.LivingEntity livingEntity = this.getControllingPassenger();
        return (livingEntity != null) && livingEntity.isControlledByPlayer();
    }

    public boolean canMoveVoluntarily() {
        return this.isLogicalSideForUpdatingMovement();
    }

    @Nullable
    public net.minecraft.entity.Entity getVehicle() {
        return this.vehicle;
    }

    @Nullable
    public net.minecraft.entity.Entity getControllingVehicle() {
        return (this.vehicle != null) && (this.vehicle.getControllingPassenger() == this.entityBridge) ? this.vehicle : null;
    }

    public SoundCategory getSoundCategory() {
        return SoundCategory.NEUTRAL;
    }

    public boolean updateMovementInFluid(TagKey<Fluid> tag, double speed) {
        if (this.isRegionUnloaded()) {
            return false;
        } else {
            Box box = this.getBoundingBox().contract(0.001);
            int i = net.minecraft.util.math.MathHelper.floor(box.minX);
            int j = net.minecraft.util.math.MathHelper.ceil(box.maxX);
            int k = net.minecraft.util.math.MathHelper.floor(box.minY);
            int l = net.minecraft.util.math.MathHelper.ceil(box.maxY);
            int m = net.minecraft.util.math.MathHelper.floor(box.minZ);
            int n = net.minecraft.util.math.MathHelper.ceil(box.maxZ);
            double d = 0.0;
            boolean bl = this.isPushedByFluids();
            boolean bl2 = false;
            net.minecraft.util.math.Vec3d vec3d = net.minecraft.util.math.Vec3d.ZERO;
            int o = 0;
            net.minecraft.util.math.BlockPos.Mutable mutable = new net.minecraft.util.math.BlockPos.Mutable();
            for (int p = i; p < j; p++) {
                for (int q = k; q < l; q++) {
                    for (int r = m; r < n; r++) {
                        FluidState fluidState = this.getWorld().getFluidState(mutable);
                        if (fluidState.isIn(tag)) {
                            double e = q + fluidState.getHeight(this.getWorld(), mutable);
                            if (e >= box.minY) {
                                d = Math.max(e - box.minY, d);
                                if (bl) {
                                    net.minecraft.util.math.Vec3d vec3d2 = fluidState.getVelocity(this.getWorld(), mutable);
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
                net.minecraft.util.math.Vec3d vec3d3 = this.getVelocity();
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
        Box box = this.getBoundingBox().expand(1.0);
        int i = net.minecraft.util.math.MathHelper.floor(box.minX);
        int j = net.minecraft.util.math.MathHelper.ceil(box.maxX);
        int k = net.minecraft.util.math.MathHelper.floor(box.minZ);
        int l = net.minecraft.util.math.MathHelper.ceil(box.maxZ);
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

    public net.minecraft.entity.EntityDimensions getDimensions(EntityPose pose) {
        return this.type.getDimensions();
    }

    public net.minecraft.util.math.Vec3d getPos() {
        return this.pos;
    }

    public net.minecraft.util.math.BlockPos getBlockPos() {
        return this.blockPos;
    }

    public BlockState getBlockStateAtPos() {
        if (this.stateAtPos == null) {
            this.stateAtPos = this.getWorld().getBlockState(this.getBlockPos());
        }
        return this.stateAtPos;
    }

    public net.minecraft.util.math.Vec3d getVelocity() {
        return this.velocity;
    }

    public void setVelocity(net.minecraft.util.math.Vec3d velocity) {
        this.velocity = velocity;
    }

    public void addVelocityInternal(net.minecraft.util.math.Vec3d velocity) {
        this.setVelocity(this.getVelocity().add(velocity));
    }

    public void setVelocity(double x, double y, double z) {
        this.setVelocity(new net.minecraft.util.math.Vec3d(x, y, z));
    }

    public double getX() {
        return this.pos.x;
    }

    public double getY() {
        return this.pos.y;
    }

    public double getZ() {
        return this.pos.z;
    }

    public void setPos(double x, double y, double z) {
        if (((this.pos.x != x) || (this.pos.y != y)) || (this.pos.z != z)) {
            this.pos = new net.minecraft.util.math.Vec3d(x, y, z);
            int i = net.minecraft.util.math.MathHelper.floor(x);
            int j = net.minecraft.util.math.MathHelper.floor(y);
            int k = net.minecraft.util.math.MathHelper.floor(z);
            if (((i != this.blockPos.getX()) || (j != this.blockPos.getY())) || (k != this.blockPos.getZ())) {
                this.blockPos = new net.minecraft.util.math.BlockPos(i, j, k);
                this.stateAtPos = null;
            }
        }
    }

    public boolean canFreeze() {
        return !this.getType().isIn(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES);
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public float getStepHeight() {
        return 0.0F;
    }

    public boolean isRemoved() {
        return this.removalReason != null;
    }

    public World getWorld() {
        return this.world;
    }

    public DamageSources getDamageSources() {
        return this.getWorld().getDamageSources();
    }

    protected net.minecraft.entity.Entity entityBridge;

    private static final ThreadLocal ENTITY_BRIDGE_BOOTSTRAP = new ThreadLocal<>();

    public static void pushEntityBridgeBootstrap(net.minecraft.entity.Entity entity) {
        ENTITY_BRIDGE_BOOTSTRAP.set(entity);
    }

    private static net.minecraft.entity.Entity claimEntityBridgeBootstrap() {
        net.minecraft.entity.Entity entity = (net.minecraft.entity.Entity) ENTITY_BRIDGE_BOOTSTRAP.get();
        ENTITY_BRIDGE_BOOTSTRAP.remove();
        if (entity == null) throw new IllegalStateException("Missing entity bridge bootstrap for mirror construction");
        return entity;
    }
}

