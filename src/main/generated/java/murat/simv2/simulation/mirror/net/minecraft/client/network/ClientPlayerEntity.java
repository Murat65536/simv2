package murat.simv2.simulation.mirror.net.minecraft.client.network;

import java.util.Iterator;
import java.util.stream.StreamSupport;
import murat.simv2.simulation.mirror.net.minecraft.entity.MovementType;
import murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Direction;
import murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.recipebook.ClientRecipeBook;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.JumpingMount;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.stat.StatHandler;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import org.jetbrains.annotations.Nullable;

// Mirrored from net.minecraft.client.network.ClientPlayerEntity
// Movement-relevant statements only (WALA backward slice + Spoon AST pruning)
// Generated - do not edit
public abstract class ClientPlayerEntity extends AbstractClientPlayerEntity {
    public boolean inSneakingPose;

    public Input input = new Input();

    public MinecraftClient client;

    public boolean usingItem;

    public boolean autoJumpEnabled = true;

    public int ticksToNextAutoJump;

    public ClientPlayerEntity(MinecraftClient client, ClientWorld world, ClientPlayNetworkHandler networkHandler, StatHandler stats, ClientRecipeBook recipeBook, boolean lastSneaking, boolean lastSprinting) {
        super(world, networkHandler.getProfile());
    }

    public int abilityResyncCountdown;

    public boolean isMainPlayer() {
        return true;
    }

    public boolean isHoldingOntoLadder() {
        return (!this.getAbilities().flying) && super.isHoldingOntoLadder();
    }

    private void pushOutOfBlocks(double x, double z) {
        net.minecraft.util.math.BlockPos blockPos = net.minecraft.util.math.BlockPos.ofFloored(x, this.getY(), z);
        if (this.wouldCollideAt(blockPos)) {
            double d = x - blockPos.getX();
            double e = z - blockPos.getZ();
            net.minecraft.util.math.Direction direction = null;
            double f = Double.MAX_VALUE;
            net.minecraft.util.math.Direction[] directions = new net.minecraft.util.math.Direction[]{ net.minecraft.util.math.Direction.WEST, net.minecraft.util.math.Direction.EAST, net.minecraft.util.math.Direction.NORTH, net.minecraft.util.math.Direction.SOUTH };
            for (net.minecraft.util.math.Direction direction2 : directions) {
                double g = direction2.getAxis().choose(d, 0.0, e);
                double h = (direction2.getDirection() == net.minecraft.util.math.Direction.AxisDirection.POSITIVE) ? 1.0 - g : g;
            }
            if (direction != null) {
                net.minecraft.util.math.Vec3d vec3d = this.getVelocity();
                if (direction.getAxis() == net.minecraft.util.math.Direction.Axis.X) {
                    this.setVelocity(0.1 * direction.getOffsetX(), vec3d.y, vec3d.z);
                } else {
                    this.setVelocity(vec3d.x, vec3d.y, 0.1 * direction.getOffsetZ());
                }
            }
        }
    }

    private boolean wouldCollideAt(net.minecraft.util.math.BlockPos pos) {
        Box box = this.getBoundingBox();
        Box box2 = new Box(pos.getX(), box.minY, pos.getZ(), pos.getX() + 1.0, box.maxY, pos.getZ() + 1.0).contract(1.0E-7);
        return this.getWorld().canCollide(((net.minecraft.client.network.ClientPlayerEntity) (this.entityBridge)), box2);
    }

    public boolean isUsingItem() {
        return this.usingItem;
    }

    @Nullable
    public JumpingMount getJumpingMount() {
        return (this.getControllingVehicle() instanceof JumpingMount jumpingMount) && jumpingMount.canJump() ? jumpingMount : null;
    }

    public boolean isSneaking() {
        return this.input.playerInput.sneak();
    }

    public boolean isInSneakingPose() {
        return this.inSneakingPose;
    }

    public boolean shouldSlowDown() {
        return this.isInSneakingPose() || this.isCrawling();
    }

    public void tickMovementInput() {
        if (this.isCamera()) {
            net.minecraft.util.math.Vec2f vec2f = this.applyMovementSpeedFactors(this.input.getMovementInput());
            this.sidewaysSpeed = vec2f.x;
            this.forwardSpeed = vec2f.y;
            this.jumping = this.input.playerInput.jump();
        } else {
            super.tickMovementInput();
        }
    }

    private net.minecraft.util.math.Vec2f applyMovementSpeedFactors(net.minecraft.util.math.Vec2f input) {
        if (input.lengthSquared() == 0.0F) {
            return input;
        } else {
            net.minecraft.util.math.Vec2f vec2f = input.multiply(0.98F);
            if (this.isUsingItem() && (!this.hasVehicle())) {
                vec2f = vec2f.multiply(0.2F);
            }
            if (this.shouldSlowDown()) {
                float f = ((float) (this.getAttributeValue(EntityAttributes.SNEAKING_SPEED)));
                vec2f = vec2f.multiply(f);
            }
            return ClientPlayerEntity.applyDirectionalMovementSpeedFactors(vec2f);
        }
    }

    private static net.minecraft.util.math.Vec2f applyDirectionalMovementSpeedFactors(net.minecraft.util.math.Vec2f vec) {
        float f = vec.length();
        if (f <= 0.0F) {
            return vec;
        } else {
            net.minecraft.util.math.Vec2f vec2f = vec.multiply(1.0F / f);
            float g = ClientPlayerEntity.getDirectionalMovementSpeedMultiplier(vec2f);
            float h = Math.min(f * g, 1.0F);
            return vec2f.multiply(h);
        }
    }

    private static float getDirectionalMovementSpeedMultiplier(net.minecraft.util.math.Vec2f vec) {
        float f = Math.abs(vec.x);
        float g = Math.abs(vec.y);
        float h = (g > f) ? f / g : g / f;
        return net.minecraft.util.math.MathHelper.sqrt(1.0F + net.minecraft.util.math.MathHelper.square(h));
    }

    protected boolean isCamera() {
        return this.client.getCameraEntity() == this.entityBridge;
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
        super.tickMovement();
    }

    public void move(net.minecraft.entity.MovementType type, net.minecraft.util.math.Vec3d movement) {
        double d = this.getX();
        double e = this.getZ();
        super.move(type, movement);
        float f = ((float) (this.getX() - d));
        float g = ((float) (this.getZ() - e));
        this.autoJump(f, g);
    }

    public boolean isAutoJumpEnabled() {
        return this.autoJumpEnabled;
    }

    protected void autoJump(float dx, float dz) {
        if (this.shouldAutoJump()) {
            net.minecraft.util.math.Vec3d vec3d = this.getPos();
            net.minecraft.util.math.Vec3d vec3d2 = vec3d.add(dx, 0.0, dz);
            net.minecraft.util.math.Vec3d vec3d3 = new net.minecraft.util.math.Vec3d(dx, 0.0, dz);
            float f = this.getMovementSpeed();
            float g = ((float) (vec3d3.lengthSquared()));
            if (g <= 0.001F) {
                net.minecraft.util.math.Vec2f vec2f = this.input.getMovementInput();
                float h = f * vec2f.x;
                float i = f * vec2f.y;
                float j = net.minecraft.util.math.MathHelper.sin(this.getYaw() * ((float) (Math.PI / 180.0)));
                float k = net.minecraft.util.math.MathHelper.cos(this.getYaw() * ((float) (Math.PI / 180.0)));
                vec3d3 = new net.minecraft.util.math.Vec3d((h * k) - (i * j), vec3d3.y, (i * k) + (h * j));
                g = ((float) (vec3d3.lengthSquared()));
            }
            float l = net.minecraft.util.math.MathHelper.inverseSqrt(g);
            net.minecraft.util.math.Vec3d vec3d4 = vec3d3.multiply(l);
            net.minecraft.util.math.Vec3d vec3d5 = this.getRotationVecClient();
            float j = ((float) ((vec3d5.x * vec3d4.x) + (vec3d5.z * vec3d4.z)));
            if (!(j < (-0.15F))) {
                ShapeContext shapeContext = ShapeContext.of(((net.minecraft.client.network.ClientPlayerEntity) (this.entityBridge)));
                net.minecraft.util.math.BlockPos blockPos = net.minecraft.util.math.BlockPos.ofFloored(this.getX(), this.getBoundingBox().maxY, this.getZ());
                BlockState blockState = this.getWorld().getBlockState(blockPos);
                if (blockState.getCollisionShape(this.getWorld(), blockPos, shapeContext).isEmpty()) {
                    BlockState blockState2 = this.getWorld().getBlockState(blockPos);
                    if (blockState2.getCollisionShape(this.getWorld(), blockPos, shapeContext).isEmpty()) {
                        float n = 1.2F;
                        float o = Math.max(f * 7.0F, 1.0F / l);
                        net.minecraft.util.math.Vec3d vec3d7 = vec3d2.add(vec3d4.multiply(o));
                        float p = this.getWidth();
                        float q = this.getHeight();
                        Box box = new Box(vec3d, vec3d7.add(0.0, q, 0.0)).expand(p, 0.0, p);
                        net.minecraft.util.math.Vec3d vec3d6 = vec3d.add(0.0, 0.51F, 0.0);
                        vec3d7 = vec3d7.add(0.0, 0.51F, 0.0);
                        net.minecraft.util.math.Vec3d vec3d8 = vec3d4.crossProduct(new net.minecraft.util.math.Vec3d(0.0, 1.0, 0.0));
                        net.minecraft.util.math.Vec3d vec3d9 = vec3d8.multiply(p * 0.5F);
                        net.minecraft.util.math.Vec3d vec3d10 = vec3d6.subtract(vec3d9);
                        net.minecraft.util.math.Vec3d vec3d11 = vec3d7.subtract(vec3d9);
                        net.minecraft.util.math.Vec3d vec3d12 = vec3d6.add(vec3d9);
                        net.minecraft.util.math.Vec3d vec3d13 = vec3d7.add(vec3d9);
                        Iterable<VoxelShape> iterable = this.getWorld().getCollisions(((net.minecraft.client.network.ClientPlayerEntity) (this.entityBridge)), box);
                        Iterator<Box> iterator = StreamSupport.stream(iterable.spliterator(), false).flatMap(shape -> shape.getBoundingBoxes().stream()).iterator();
                        float r = Float.MIN_VALUE;
                    }
                }
            }
        }
    }

    private boolean shouldAutoJump() {
        return (((((this.isAutoJumpEnabled() && (this.ticksToNextAutoJump <= 0)) && this.isOnGround()) && (!this.clipAtLedge())) && (!this.hasVehicle())) && this.hasMovementInput()) && (this.getJumpVelocityMultiplier() >= 1.0);
    }

    private boolean hasMovementInput() {
        return this.input.getMovementInput().lengthSquared() > 0.0F;
    }
}
