package murat.simv2.simulation.mirror.net.minecraft.client.network;

import java.util.Iterator;
import java.util.stream.StreamSupport;
import murat.simv2.simulation.mirror.net.minecraft.block.BlockState;
import murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext;
import murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient;
import murat.simv2.simulation.mirror.net.minecraft.client.input.Input;
import murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose;
import murat.simv2.simulation.mirror.net.minecraft.entity.JumpingMount;
import murat.simv2.simulation.mirror.net.minecraft.entity.MovementType;
import murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributes;
import murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffects;
import murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerAbilities;
import murat.simv2.simulation.mirror.net.minecraft.registry.tag.FluidTags;
import murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent;
import murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Box;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Direction;
import murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f;
import murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d;
import murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape;

// Mirrored from net.minecraft.client.network.ClientPlayerEntity
// Movement-relevant statements only (WALA backward slice + Spoon AST pruning)
// Generated - do not edit
public abstract class ClientPlayerEntity extends AbstractClientPlayerEntity {
    public float distanceMoved;

    public boolean inSneakingPose;

    public murat.simv2.simulation.mirror.net.minecraft.client.input.Input input = new murat.simv2.simulation.mirror.net.minecraft.client.input.Input();

    public murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient client;

    public float renderPitch;

    public boolean usingItem;

    public boolean autoJumpEnabled = true;

    public int ticksToNextAutoJump;

    public int abilityResyncCountdown;

    public boolean isMainPlayer() {
        return true;
    }

    public boolean isHoldingOntoLadder() {
        return (!this.getAbilities().flying) && super.isHoldingOntoLadder();
    }

    private void pushOutOfBlocks(double x, double z) {
        murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos = murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.ofFloored(x, this.getY(), z);
        if (this.wouldCollideAt(blockPos)) {
            double d = x - blockPos.getX();
            double e = z - blockPos.getZ();
            murat.simv2.simulation.mirror.net.minecraft.util.math.Direction direction = null;
            double f = Double.MAX_VALUE;
            murat.simv2.simulation.mirror.net.minecraft.util.math.Direction[] directions = new murat.simv2.simulation.mirror.net.minecraft.util.math.Direction[]{ murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.WEST, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.EAST, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.NORTH, murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.SOUTH };
            for (murat.simv2.simulation.mirror.net.minecraft.util.math.Direction direction2 : directions) {
                double g = direction2.getAxis().choose(d, 0.0, e);
                double h = (direction2.getDirection() == murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.AxisDirection.POSITIVE) ? 1.0 - g : g;
            }
            if (direction != null) {
                murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = this.getVelocity();
                if (direction.getAxis() == murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis.X) {
                    this.setVelocity(0.1 * direction.getOffsetX(), vec3d.y, vec3d.z);
                } else {
                    this.setVelocity(vec3d.x, vec3d.y, 0.1 * direction.getOffsetZ());
                }
            }
        }
    }

    private boolean wouldCollideAt(murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos pos) {
        murat.simv2.simulation.mirror.net.minecraft.util.math.Box box = this.getBoundingBox();
        murat.simv2.simulation.mirror.net.minecraft.util.math.Box box2 = new murat.simv2.simulation.mirror.net.minecraft.util.math.Box(pos.getX(), box.minY, pos.getZ(), pos.getX() + 1.0, box.maxY, pos.getZ() + 1.0).contract(1.0E-7);
        return this.getWorld().canCollide(this, box2);
    }

    public void playSound(murat.simv2.simulation.mirror.net.minecraft.sound.SoundEvent sound, float volume, float pitch) {
        this.getWorld().playSoundClient(this.getX(), this.getY(), this.getZ(), sound, this.getSoundCategory(), volume, pitch, false);
    }

    public boolean isUsingItem() {
        return this.usingItem;
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
            murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f vec2f = this.applyMovementSpeedFactors(this.input.getMovementInput());
            this.sidewaysSpeed = vec2f.x;
            this.forwardSpeed = vec2f.y;
            this.jumping = this.input.playerInput.jump();
            this.renderPitch = this.renderPitch + ((this.getPitch() - this.renderPitch) * 0.5F);
        } else {
            super.tickMovementInput();
        }
    }

    private murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f applyMovementSpeedFactors(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f input) {
        if (input.lengthSquared() == 0.0F) {
        } else {
            murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f vec2f = input.multiply(0.98F);
            if (this.shouldSlowDown()) {
                float f = ((float) (this.getAttributeValue(murat.simv2.simulation.mirror.net.minecraft.entity.attribute.EntityAttributes.SNEAKING_SPEED)));
            }
            return ClientPlayerEntity.applyDirectionalMovementSpeedFactors(vec2f);
        }
        return null;
    }

    private static murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f applyDirectionalMovementSpeedFactors(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f vec) {
        float f = vec.length();
        if (f <= 0.0F) {
        } else {
            murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f vec2f = vec.multiply(1.0F / f);
            float g = ClientPlayerEntity.getDirectionalMovementSpeedMultiplier(vec2f);
            float h = Math.min(f * g, 1.0F);
        }
        return null;
    }

    private static float getDirectionalMovementSpeedMultiplier(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f vec) {
        float f = Math.abs(vec.x);
        float g = Math.abs(vec.y);
        float h = (g > f) ? f / g : g / f;
        return murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.sqrt(1.0F + murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.square(h));
    }

    protected boolean isCamera() {
        return this.client.getCameraEntity() == this;
    }

    public void tickMovement() {
        boolean bl = this.input.playerInput.jump();
        boolean bl2 = this.input.playerInput.sneak();
        boolean bl3 = this.input.hasForwardMovement();
        murat.simv2.simulation.mirror.net.minecraft.entity.player.PlayerAbilities playerAbilities = this.getAbilities();
        this.inSneakingPose = ((((!playerAbilities.flying) && (!this.isSwimming())) && (!this.hasVehicle())) && this.canChangeIntoPose(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose.CROUCHING)) && (this.isSneaking() || ((!this.isSleeping()) && (!this.canChangeIntoPose(murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose.STANDING))));
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
        if (this.isSubmergedIn(murat.simv2.simulation.mirror.net.minecraft.registry.tag.FluidTags.WATER)) {
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
        murat.simv2.simulation.mirror.net.minecraft.entity.JumpingMount jumpingMount = this.getJumpingMount();
        super.tickMovement();
    }

    public void move(murat.simv2.simulation.mirror.net.minecraft.entity.MovementType type, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d movement) {
        double d = this.getX();
        double e = this.getZ();
        super.move(type, movement);
        float f = ((float) (this.getX() - d));
        float g = ((float) (this.getZ() - e));
        this.autoJump(f, g);
        this.distanceMoved = this.distanceMoved + (murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.hypot(f, g) * 0.6F);
    }

    public boolean isAutoJumpEnabled() {
        return this.autoJumpEnabled;
    }

    protected void autoJump(float dx, float dz) {
        if (this.shouldAutoJump()) {
            murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d = this.getPos();
            murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d2 = vec3d.add(dx, 0.0, dz);
            murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d3 = new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d(dx, 0.0, dz);
            float f = this.getMovementSpeed();
            float g = ((float) (vec3d3.lengthSquared()));
            if (g <= 0.001F) {
                murat.simv2.simulation.mirror.net.minecraft.util.math.Vec2f vec2f = this.input.getMovementInput();
                float h = f * vec2f.x;
                float i = f * vec2f.y;
                float j = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.sin(this.getYaw() * ((float) (Math.PI / 180.0)));
                float k = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.cos(this.getYaw() * ((float) (Math.PI / 180.0)));
                vec3d3 = new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d((h * k) - (i * j), vec3d3.y, (i * k) + (h * j));
                g = ((float) (vec3d3.lengthSquared()));
            }
            float l = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.inverseSqrt(g);
            murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d4 = vec3d3.multiply(l);
            murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d5 = this.getRotationVecClient();
            float j = ((float) ((vec3d5.x * vec3d4.x) + (vec3d5.z * vec3d4.z)));
            if (!(j < (-0.15F))) {
                murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext shapeContext = murat.simv2.simulation.mirror.net.minecraft.block.ShapeContext.of(this);
                murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos = murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.ofFloored(this.getX(), this.getBoundingBox().maxY, this.getZ());
                murat.simv2.simulation.mirror.net.minecraft.block.BlockState blockState = this.getWorld().getBlockState(blockPos);
                if (blockState.getCollisionShape(this.getWorld(), blockPos, shapeContext).isEmpty()) {
                    blockPos = blockPos.up();
                    murat.simv2.simulation.mirror.net.minecraft.block.BlockState blockState2 = this.getWorld().getBlockState(blockPos);
                    if (blockState2.getCollisionShape(this.getWorld(), blockPos, shapeContext).isEmpty()) {
                        float n = 1.2F;
                        if (this.hasStatusEffect(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffects.JUMP_BOOST)) {
                            n += (this.getStatusEffect(murat.simv2.simulation.mirror.net.minecraft.entity.effect.StatusEffects.JUMP_BOOST).getAmplifier() + 1) * 0.75F;
                        }
                        float o = Math.max(f * 7.0F, 1.0F / l);
                        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d7 = vec3d2.add(vec3d4.multiply(o));
                        float p = this.getWidth();
                        float q = this.getHeight();
                        murat.simv2.simulation.mirror.net.minecraft.util.math.Box box = new murat.simv2.simulation.mirror.net.minecraft.util.math.Box(vec3d, vec3d7.add(0.0, q, 0.0)).expand(p, 0.0, p);
                        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d6 = vec3d.add(0.0, 0.51F, 0.0);
                        vec3d7 = vec3d7.add(0.0, 0.51F, 0.0);
                        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d8 = vec3d4.crossProduct(new murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d(0.0, 1.0, 0.0));
                        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d9 = vec3d8.multiply(p * 0.5F);
                        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d10 = vec3d6.subtract(vec3d9);
                        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d11 = vec3d7.subtract(vec3d9);
                        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d12 = vec3d6.add(vec3d9);
                        murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d13 = vec3d7.add(vec3d9);
                        Iterable<murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape> iterable = this.getWorld().getCollisions(this, box);
                        Iterator<murat.simv2.simulation.mirror.net.minecraft.util.math.Box> iterator = StreamSupport.stream(iterable.spliterator(), false).flatMap(shape -> shape.getBoundingBoxes().stream()).iterator();
                        float r = Float.MIN_VALUE;
                        while (iterator.hasNext()) {
                            murat.simv2.simulation.mirror.net.minecraft.util.math.Box box2 = ((murat.simv2.simulation.mirror.net.minecraft.util.math.Box) (iterator.next()));
                            if (box2.intersects(vec3d10, vec3d11) || box2.intersects(vec3d12, vec3d13)) {
                                r = ((float) (box2.maxY));
                                murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d vec3d14 = box2.getCenter();
                                murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos2 = murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos.ofFloored(vec3d14);
                                for (int s = 1; s < n; s++) {
                                    murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos blockPos3 = blockPos2.up(s);
                                    murat.simv2.simulation.mirror.net.minecraft.block.BlockState blockState3 = this.getWorld().getBlockState(blockPos3);
                                    murat.simv2.simulation.mirror.net.minecraft.util.shape.VoxelShape voxelShape = null;
                                    if (!(voxelShape = blockState3.getCollisionShape(this.getWorld(), blockPos3, shapeContext)).isEmpty()) {
                                        r = ((float) (voxelShape.getMax(murat.simv2.simulation.mirror.net.minecraft.util.math.Direction.Axis.Y))) + blockPos3.getY();
                                    }
                                    if (s > 1) {
                                        blockPos = blockPos.up();
                                        murat.simv2.simulation.mirror.net.minecraft.block.BlockState blockState4 = this.getWorld().getBlockState(blockPos);
                                    }
                                }
                            }
                        } 
                        if (r != Float.MIN_VALUE) {
                            float t = ((float) (r - this.getY()));
                        }
                    }
                }
            }
        }
    }

    protected void hasCollidedSoftly(murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d adjustedMovement) {
        float f = this.getYaw() * ((float) (Math.PI / 180.0));
        double d = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.sin(f);
        double e = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.cos(f);
        double g = (this.sidewaysSpeed * e) - (this.forwardSpeed * d);
        double h = (this.forwardSpeed * e) + (this.sidewaysSpeed * d);
        double i = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.square(g) + murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.square(h);
        double j = murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.square(adjustedMovement.x) + murat.simv2.simulation.mirror.net.minecraft.util.math.MathHelper.square(adjustedMovement.z);
        if ((!(i < 1.0E-5F)) && (!(j < 1.0E-5F))) {
            double k = (g * adjustedMovement.x) + (h * adjustedMovement.z);
            double l = Math.acos(k / Math.sqrt(i * j));
        }
    }

    private boolean shouldAutoJump() {
        return (((((this.isAutoJumpEnabled() && (this.ticksToNextAutoJump <= 0)) && this.isOnGround()) && (!this.clipAtLedge())) && (!this.hasVehicle())) && this.hasMovementInput()) && (this.getJumpVelocityMultiplier() >= 1.0);
    }

    private boolean hasMovementInput() {
        return this.input.getMovementInput().lengthSquared() > 0.0F;
    }

    public ClientPlayerEntity() {
    }

}
