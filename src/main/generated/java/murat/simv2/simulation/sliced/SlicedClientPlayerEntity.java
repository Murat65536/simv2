package murat.simv2.simulation.sliced;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.StreamSupport;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.JumpingMount;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.math.Direction.AxisDirection;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import org.jetbrains.annotations.Nullable;
import static net.minecraft.client.network.AbstractClientPlayerEntity.*;
import static net.minecraft.client.network.ClientPlayerEntity.*;
import static net.minecraft.entity.Entity.*;
import static net.minecraft.entity.LivingEntity.*;
import static net.minecraft.entity.player.PlayerEntity.*;

// Sliced from net.minecraft.client.network.ClientPlayerEntity
// Movement-relevant statements only (WALA backward slice + Spoon AST pruning)
// Generated — do not edit

public abstract class SlicedClientPlayerEntity extends SlicedAbstractClientPlayerEntity {

    public boolean inSneakingPose;
    public Input input = new Input();
    protected MinecraftClient client;
    public boolean usingItem;
    public boolean autoJumpEnabled = true;
    public int ticksToNextAutoJump;
    public int abilityResyncCountdown;
    public boolean jumping;
    public float sidewaysSpeed;
    public float forwardSpeed;
    @Nullable
public Entity vehicle;
    private Vec3d velocity = Vec3d.ZERO;
    public boolean noClip;
    private final Set<TagKey<Fluid>> submergedFluidTag = new HashSet();

    protected void autoJump(float dx, float dz) {
        if (this.shouldAutoJump()) {
            Vec3d vec3d = this.getPos();
            Vec3d vec3d2 = vec3d.add(dx, 0.0, dz);
            Vec3d vec3d3 = new Vec3d(dx, 0.0, dz);
            float f = this.getMovementSpeed();
            float g = ((float) (vec3d3.lengthSquared()));
            if (g <= 0.001F) {
                Vec2f vec2f = this.input.getMovementInput();
                float h = f * vec2f.x;
                float i = f * vec2f.y;
                float j = MathHelper.sin(this.getYaw() * ((float) (Math.PI / 180.0)));
                float k = MathHelper.cos(this.getYaw() * ((float) (Math.PI / 180.0)));
                vec3d3 = new Vec3d((h * k) - (i * j), vec3d3.y, (i * k) + (h * j));
                g = ((float) (vec3d3.lengthSquared()));
            }
            float l = MathHelper.inverseSqrt(g);
            Vec3d vec3d4 = vec3d3.multiply(l);
            Vec3d vec3d5 = this.getRotationVecClient();
            float j = ((float) ((vec3d5.x * vec3d4.x) + (vec3d5.z * vec3d4.z)));
            if (!(j < (-0.15F))) {
                ShapeContext shapeContext = ShapeContext.of( (ClientPlayerEntity) this.entityBridge);
                BlockPos blockPos = BlockPos.ofFloored(this.getX(), this.getBoundingBox().maxY, this.getZ());
                BlockState blockState = this.getWorld().getBlockState(blockPos);
                if (blockState.getCollisionShape(this.getWorld(), blockPos, shapeContext).isEmpty()) {
                    BlockState blockState2 = this.getWorld().getBlockState(blockPos);
                    if (blockState2.getCollisionShape(this.getWorld(), blockPos, shapeContext).isEmpty()) {
                        float n = 1.2F;
                        float o = Math.max(f * 7.0F, 1.0F / l);
                        Vec3d vec3d7 = vec3d2.add(vec3d4.multiply(o));
                        float p = this.getWidth();
                        float q = this.getHeight();
                        Box box = new Box(vec3d, vec3d7.add(0.0, q, 0.0)).expand(p, 0.0, p);
                        Vec3d vec3d6 = vec3d.add(0.0, 0.51F, 0.0);
                        vec3d7 = vec3d7.add(0.0, 0.51F, 0.0);
                        Vec3d vec3d8 = vec3d4.crossProduct(new Vec3d(0.0, 1.0, 0.0));
                        Vec3d vec3d9 = vec3d8.multiply(p * 0.5F);
                        Vec3d vec3d10 = vec3d6.subtract(vec3d9);
                        Vec3d vec3d11 = vec3d7.subtract(vec3d9);
                        Vec3d vec3d12 = vec3d6.add(vec3d9);
                        Vec3d vec3d13 = vec3d7.add(vec3d9);
                        Iterable<VoxelShape> iterable = this.getWorld().getCollisions( (ClientPlayerEntity) this.entityBridge, box);
                        Iterator<Box> iterator = StreamSupport.stream(iterable.spliterator(), false).flatMap(shape -> shape.getBoundingBoxes().stream()).iterator();
                        float r = Float.MIN_VALUE;
                    }
                }
            }
        }
    }

    private boolean hasMovementInput() {
        return this.input.getMovementInput().lengthSquared() > 0.0F;
    }

    public boolean isAutoJumpEnabled() {
        return this.autoJumpEnabled;
    }

    protected boolean isCamera() {
        return this.client.getCameraEntity() == this.entityBridge;
    }

    public boolean isHoldingOntoLadder() {
        return (!this.getAbilities().flying) && this.isHoldingOntoLadder();
    }

    public boolean isInSneakingPose() {
        return this.inSneakingPose;
    }

    public boolean isMainPlayer() {
        return true;
    }

    public boolean isSneaking() {
        return this.input.playerInput.sneak();
    }

    public boolean isUsingItem() {
        return this.usingItem;
    }

    public void move(MovementType type, Vec3d movement) {
        double d = this.getX();
        double e = this.getZ();
        this.move(type, movement);
        float f = ((float) (this.getX() - d));
        float g = ((float) (this.getZ() - e));
        this.autoJump(f, g);
    }

    private void pushOutOfBlocks(double x, double z) {
        BlockPos blockPos = BlockPos.ofFloored(x, this.getY(), z);
        if (this.wouldCollideAt(blockPos)) {
            double d = x - blockPos.getX();
            double e = z - blockPos.getZ();
            Direction direction = null;
            double f = Double.MAX_VALUE;
            Direction[] directions = new Direction[]{ Direction.WEST, Direction.EAST, Direction.NORTH, Direction.SOUTH };
            for (Direction direction2 : directions) {
                double g = direction2.getAxis().choose(d, 0.0, e);
                double h = (direction2.getDirection() == AxisDirection.POSITIVE) ? 1.0 - g : g;
            }
            if (direction != null) {
                Vec3d vec3d = this.getVelocity();
                if (direction.getAxis() == Axis.X) {
                    this.setVelocity(0.1 * direction.getOffsetX(), vec3d.y, vec3d.z);
                } else {
                    this.setVelocity(vec3d.x, vec3d.y, 0.1 * direction.getOffsetZ());
                }
            }
        }
    }

    private boolean shouldAutoJump() {
        return (((((this.isAutoJumpEnabled() && (this.ticksToNextAutoJump <= 0)) && this.isOnGround()) && (!this.clipAtLedge())) && (!this.hasVehicle())) && this.hasMovementInput()) && (this.getJumpVelocityMultiplier() >= 1.0);
    }

    public void tickMovement() {
        boolean bl = this.input.playerInput.jump();
        boolean bl2 = this.input.playerInput.sneak();
        boolean bl3 = this.input.hasForwardMovement();
        PlayerAbilities playerAbilities = this.getAbilities();
        this.inSneakingPose = ((((!playerAbilities.flying) && (!this.isSwimming())) && (!this.hasVehicle())) && this.canChangeIntoPose(EntityPose.CROUCHING)) && (this.isSneaking() || ((!this.isSleeping()) && (!this.canChangeIntoPose(EntityPose.STANDING))));
        boolean bl4 = false;
        if (this.ticksToNextAutoJump > 0) {
            this.ticksToNextAutoJump--;
        }
        if (!this.noClip) {
            this.pushOutOfBlocks(this.getX() - (this.getWidth() * 0.35), this.getZ() + (this.getWidth() * 0.35));
            this.pushOutOfBlocks(this.getX() - (this.getWidth() * 0.35), this.getZ() - (this.getWidth() * 0.35));
            this.pushOutOfBlocks(this.getX() + (this.getWidth() * 0.35), this.getZ() - (this.getWidth() * 0.35));
            this.pushOutOfBlocks(this.getX() + (this.getWidth() * 0.35), this.getZ() + (this.getWidth() * 0.35));
        }
        boolean bl5 = false;
        if (playerAbilities.allowFlying) {
            if (this.client.interactionManager.isFlyingLocked()) {
            } else if (((!bl) && this.input.playerInput.jump()) && (!bl4)) {
                if (this.abilityResyncCountdown == 0) {
                } else if (!this.isSwimming()) {
                    if (playerAbilities.flying && this.isOnGround()) {
                        this.jump();
                    }
                }
            }
        }
        if ((this.isTouchingWater() && this.input.playerInput.sneak()) && this.shouldSwimInFluids()) {
            this.knockDownwards();
        }
        if (this.isSubmergedIn(FluidTags.WATER)) {
            int i = (this.isSpectator()) ? 10 : 1;
        }
        if (playerAbilities.flying && this.isCamera()) {
            int i = 0;
            if (this.input.playerInput.sneak()) {
                i--;
            }
            if (this.input.playerInput.jump()) {
                i++;
            }
            if (i != 0) {
                this.setVelocity(this.getVelocity().add(0.0, (i * playerAbilities.getFlySpeed()) * 3.0F, 0.0));
            }
        }
        JumpingMount jumpingMount = this.getJumpingMount();
        this.tickMovement();
    }

    public void tickMovementInput() {
        if (this.isCamera()) {
            Vec2f vec2f = this.applyMovementSpeedFactors(this.input.getMovementInput());
            this.sidewaysSpeed = vec2f.x;
            this.forwardSpeed = vec2f.y;
            this.jumping = this.input.playerInput.jump();
        } else {
            this.tickMovementInput();
        }
    }

    private boolean wouldCollideAt(BlockPos pos) {
        Box box = this.getBoundingBox();
        Box box2 = new Box(pos.getX(), box.minY, pos.getZ(), pos.getX() + 1.0, box.maxY, pos.getZ() + 1.0).contract(1.0E-7);
        return this.getWorld().canCollide( (ClientPlayerEntity) this.entityBridge, box2);
    }

    public void setVelocity(double x, double y, double z) {
        this.setVelocity(new Vec3d(x, y, z));
    }

    protected boolean canChangeIntoPose(EntityPose pose) {
        return this.getWorld().isSpaceEmpty( (ClientPlayerEntity) this.entityBridge, this.getDimensions(pose).getBoxAt(this.getPos()).contract(1.0E-7));
    }

    public boolean isSubmergedIn(TagKey<Fluid> fluidTag) {
        return this.submergedFluidTag.contains(fluidTag);
    }

    public void setVelocity(Vec3d velocity) {
        this.velocity = velocity;
    }

    @Nullable
    public JumpingMount getJumpingMount() {
        return (this.getControllingVehicle() instanceof JumpingMount jumpingMount) && jumpingMount.canJump() ? jumpingMount : null;
    }

    private Vec2f applyMovementSpeedFactors(Vec2f input) {
        if (input.lengthSquared() == 0.0F) {
            return input;
        } else {
            Vec2f vec2f = input.multiply(0.98F);
            if (this.isUsingItem() && (!this.hasVehicle())) {
                vec2f = vec2f.multiply(0.2F);
            }
            if (this.shouldSlowDown()) {
                float f = ((float) (this.getAttributeValue(EntityAttributes.SNEAKING_SPEED)));
                vec2f = vec2f.multiply(f);
            }
            return applyDirectionalMovementSpeedFactors(vec2f);
        }
    }

    @Nullable
    public Entity getControllingVehicle() {
        return (this.vehicle != null) && (this.vehicle.getControllingPassenger() == this.entityBridge) ? this.vehicle : null;
    }

    public boolean shouldSlowDown() {
        return this.isInSneakingPose() || this.isCrawling();
    }

    public double getAttributeValue(RegistryEntry<EntityAttribute> attribute) {
        return this.getAttributes().getValue(attribute);
    }

    private static Vec2f applyDirectionalMovementSpeedFactors(Vec2f vec) {
        float f = vec.length();
        if (f <= 0.0F) {
            return vec;
        } else {
            Vec2f vec2f = vec.multiply(1.0F / f);
            float g = getDirectionalMovementSpeedMultiplier(vec2f);
            float h = Math.min(f * g, 1.0F);
            return vec2f.multiply(h);
        }
    }

    public boolean isCrawling() {
        return this.isInSwimmingPose() && (!this.isTouchingWater());
    }

    private static float getDirectionalMovementSpeedMultiplier(Vec2f vec) {
        float f = Math.abs(vec.x);
        float g = Math.abs(vec.y);
        float h = (g > f) ? f / g : g / f;
        return MathHelper.sqrt(1.0F + MathHelper.square(h));
    }

    public boolean isInSwimmingPose() {
        return this.isInSwimmingPose() || ((!this.isGliding()) && this.isInPose(EntityPose.GLIDING));
    }

    public boolean isInPose(EntityPose pose) {
        return this.getPose() == pose;
    }
}
