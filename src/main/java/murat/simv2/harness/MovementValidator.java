package murat.simv2.harness;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import murat.simv2.PathRenderer;
import murat.simv2.SimV2;
import murat.simv2.sim.MovementSim;
import murat.simv2.sim.SimPlayerState;
import murat.simv2.sim.Vec3;
import murat.simv2.sim.WorldSnapshot;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;

/**
 * Travel-isolated validation: {@link #beginTravel} captures the player's state at the entry of
 * {@code LivingEntity.travel}, predicts the post-travel position/velocity with the standalone
 * {@link MovementSim}, and {@link #endTravel} (at travel RETURN, same tick) compares against the
 * real result. Comparing within the single travel call removes any post-travel processing
 * (pushOutOfBlocks, entity collision, server correction) from the measurement, so a non-zero
 * error is unambiguously inside the physics we ported.
 */
public final class MovementValidator {
    private MovementValidator() {
    }

    private static final int RENDER_TRAIL = 60;
    private static final int LOG_EVERY = 40;
    private static final double DETAIL_THRESHOLD = 1.0E-3;

    private static boolean announced;

    private static boolean active;
    private static Vec3 startPos;
    private static Vec3 predPos;
    private static Vec3 predVel;
    private static Vec3 inVel;
    private static Vec3 inInput;
    private static float inYaw;
    private static boolean inOnGround;
    private static boolean inSprint;

    private static long compared;
    private static long detailed;
    private static double errSum;
    private static double errMax;

    private static final Deque<Vec3d> actualTrail = new ArrayDeque<>();

    public static void beginTravel(ClientPlayerEntity player, Vec3d movementInput) {
        if (!announced) {
            announced = true;
            SimV2.LOGGER.info("[movement-sim] travel hooks live (HEAD+RETURN)");
        }
        active = MovementSim.supports(
            player.isTouchingWater(), player.isInLava(), player.isGliding(),
            player.isClimbing(), player.hasVehicle(), player.getAbilities().flying);
        if (!active) {
            return;
        }
        SimPlayerState s = PlayerStateCapture.capturePlayer(player);
        startPos = s.pos;
        inVel = s.velocity;
        inInput = Captures.toVec3(movementInput);
        inYaw = s.yaw;
        inOnGround = s.onGround;
        inSprint = s.sprinting;

        WorldSnapshot snapshot = Captures.captureWorld(player.getWorld(), s);
        MovementSim.travelMidAir(s, inInput, snapshot);
        predPos = s.pos;
        predVel = s.velocity;
    }

    public static void endTravel(ClientPlayerEntity player) {
        if (active) {
            Vec3 actualPos = Captures.toVec3(player.getPos());
            Vec3 actualVel = Captures.toVec3(player.getVelocity());

            double pex = Math.abs(predPos.x() - actualPos.x());
            double pey = Math.abs(predPos.y() - actualPos.y());
            double pez = Math.abs(predPos.z() - actualPos.z());
            double posErr = Math.sqrt(pex * pex + pey * pey + pez * pez);
            errSum += posErr;
            errMax = Math.max(errMax, posErr);
            compared++;

            if (posErr > DETAIL_THRESHOLD && detailed < 60) {
                detailed++;
                SimV2.LOGGER.info(String.format(
                    "[movement-sim] DETAIL posErr=%.6f (x=%.6f y=%.6f z=%.6f)"
                        + " velErr=(x=%.6f y=%.6f z=%.6f)"
                        + " | inVel=(%.5f,%.5f,%.5f) input=(%.3f,%.3f) yaw=%.1f onGround=%b sprint=%b",
                    posErr, pex, pey, pez,
                    Math.abs(predVel.x() - actualVel.x()),
                    Math.abs(predVel.y() - actualVel.y()),
                    Math.abs(predVel.z() - actualVel.z()),
                    inVel.x(), inVel.y(), inVel.z(),
                    inInput.x(), inInput.z(), inYaw, inOnGround, inSprint));
            } else if (compared <= 5 || compared % LOG_EVERY == 0) {
                SimV2.LOGGER.info(String.format(
                    "[movement-sim] tick=%d posErr=%.6f avgErr=%.6f maxErr=%.6f (detailed=%d)",
                    compared, posErr, errSum / compared, errMax, detailed));
            }
        }
        active = false;
        updateTrail(player.getPos());
    }

    private static void updateTrail(Vec3d pos) {
        actualTrail.addLast(pos);
        while (actualTrail.size() > RENDER_TRAIL) {
            actualTrail.removeFirst();
        }
        PathRenderer.setPath(new ArrayList<>(actualTrail));
    }
}
