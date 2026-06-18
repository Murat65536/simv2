package murat.simv2.sim;

/**
 * Raw player intent for one tick — the movement keys/buttons as a human (or a rollout policy) would
 * press them, BEFORE Minecraft's input factors. {@link MovementSim#step(SimPlayerState, PlayerInput,
 * WorldSnapshot)} converts this to the travel-space movement vector exactly as the client does
 * (button → Vec2f, the 0.98 / using-item / sneak slowdowns, diagonal normalization), and drives
 * sprint start/stop, jump and the sneak-edge ledge clamp from it.
 *
 * <p>{@code forward/back} and {@code left/right} are independent presses (both pressed cancels, as
 * vanilla does). {@code sprint} is the sprint key held (double-tap-to-sprint is not modeled).
 */
public record PlayerInput(boolean forward, boolean back, boolean left, boolean right,
                          boolean jump, boolean sneak, boolean sprint, boolean usingItem) {

    public static final PlayerInput NONE =
        new PlayerInput(false, false, false, false, false, false, false, false);

    /** Walk straight forward. (Named {@code walkForward} to avoid clashing with the {@code forward()} accessor.) */
    public static PlayerInput walkForward() {
        return new PlayerInput(true, false, false, false, false, false, false, false);
    }

    /** Sprint straight forward (sprint key + forward). */
    public static PlayerInput sprintForward() {
        return new PlayerInput(true, false, false, false, false, false, true, false);
    }

    /** Walk forward holding jump. */
    public static PlayerInput forwardJumping() {
        return new PlayerInput(true, false, false, false, true, false, false, false);
    }

    /** Sneak forward (ledge-safe walk). */
    public static PlayerInput sneakForward() {
        return new PlayerInput(true, false, false, false, false, true, false, false);
    }

    /** Just hold jump (e.g. bunny-hop in place). */
    public static PlayerInput jumping() {
        return new PlayerInput(false, false, false, false, true, false, false, false);
    }
}
