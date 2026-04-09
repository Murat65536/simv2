package murat.simv2.simulation.sliced;

import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.math.Direction.AxisDirection;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
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
  public boolean autoJumpEnabled = true;
  public int ticksToNextAutoJump;

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
        ShapeContext shapeContext = ShapeContext.of((ClientPlayerEntity) this.entityBridge);
        BlockPos blockPos =
            BlockPos.ofFloored(this.getX(), this.getBoundingBox().maxY, this.getZ());
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
            Iterable<VoxelShape> iterable =
                this.getWorld().getCollisions((ClientPlayerEntity) this.entityBridge, box);
            Iterator<Box> iterator =
                StreamSupport.stream(iterable.spliterator(), false)
                    .flatMap(shape -> shape.getBoundingBoxes().stream())
                    .iterator();
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

  public boolean isInSneakingPose() {
    return this.inSneakingPose;
  }

  public boolean isMainPlayer() {
    return true;
  }

  private void pushOutOfBlocks(double x, double z) {
    BlockPos blockPos = BlockPos.ofFloored(x, this.getY(), z);
    if (this.wouldCollideAt(blockPos)) {
      double d = x - blockPos.getX();
      double e = z - blockPos.getZ();
      Direction direction = null;
      double f = Double.MAX_VALUE;
      Direction[] directions =
          new Direction[] {Direction.WEST, Direction.EAST, Direction.NORTH, Direction.SOUTH};
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
    return (((((this.isAutoJumpEnabled() && (this.ticksToNextAutoJump <= 0)) && this.isOnGround())
                    && (!this.clipAtLedge()))
                && (!this.hasVehicle()))
            && this.hasMovementInput())
        && (this.getJumpVelocityMultiplier() >= 1.0);
  }

  private boolean wouldCollideAt(BlockPos pos) {
    Box box = this.getBoundingBox();
    Box box2 =
        new Box(pos.getX(), box.minY, pos.getZ(), pos.getX() + 1.0, box.maxY, pos.getZ() + 1.0)
            .contract(1.0E-7);
    return this.getWorld().canCollide((ClientPlayerEntity) this.entityBridge, box2);
  }

  public void setVelocity(double x, double y, double z) {
    this.setVelocity(new Vec3d(x, y, z));
  }
}
