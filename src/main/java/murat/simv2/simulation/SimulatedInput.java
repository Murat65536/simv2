package murat.simv2.simulation;

import net.minecraft.client.input.Input;
import net.minecraft.util.PlayerInput;
import net.minecraft.util.math.Vec2f;

public class SimulatedInput extends Input {

    @Override
    public void tick() {
        float forward = 0.0f;
        if (this.playerInput.forward()) forward += 1.0f;
        if (this.playerInput.backward()) forward -= 1.0f;

        float sideways = 0.0f;
        if (this.playerInput.left()) sideways += 1.0f;
        if (this.playerInput.right()) sideways -= 1.0f;

        this.movementVector = new Vec2f(sideways, forward).normalize();
    }

    public void set(PlayerInput input) {
        this.playerInput = input;
    }
}
