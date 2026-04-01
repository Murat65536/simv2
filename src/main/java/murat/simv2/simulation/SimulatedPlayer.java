package murat.simv2.simulation;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.recipebook.ClientRecipeBook;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.StatHandler;
import net.minecraft.util.PlayerInput;
import net.minecraft.util.math.Vec3d;

public class SimulatedPlayer extends ClientPlayerEntity {

    private final SimulatedInput simulatedInput;

    public SimulatedPlayer(MinecraftClient client, ClientWorld world, ClientPlayNetworkHandler networkHandler) {
        super(client, world, networkHandler, new StatHandler(), new ClientRecipeBook(), false, false);
        this.simulatedInput = new SimulatedInput();
        this.input = this.simulatedInput;
    }

    // --- Side-effect suppression ---

    @Override
    public boolean checkGliding() {
        super.checkGliding();
        return false;
    }

    @Override
    public void sendAbilitiesUpdate() {
    }

    @Override
    public void playSound(SoundEvent sound, float volume, float pitch) {
    }

    @Override
    public void playSoundToPlayer(SoundEvent event, SoundCategory category, float volume, float pitch) {
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public void pushAwayFrom(Entity entity) {
    }

    @Override
    protected void tickCramming() {
    }

    @Override
    public void pushAway(Entity entity) {
    }

    // --- Simulation ---

    public void simulateTick() {
        this.lastX = this.getX();
        this.lastY = this.getY();
        this.lastZ = this.getZ();
        this.lastRenderX = this.getX();
        this.lastRenderY = this.getY();
        this.lastRenderZ = this.getZ();
        this.lastYaw = this.getYaw();
        this.lastPitch = this.getPitch();
        this.lastBodyYaw = this.bodyYaw;
        this.lastHeadYaw = this.headYaw;

        this.noClip = this.isSpectator();
        if (this.isSpectator() || this.hasVehicle()) {
            this.setOnGround(false);
        }

        this.baseTick();
        this.tickMovement();
    }

    // --- State sync ---

    public void syncFrom(ClientPlayerEntity real) {
        // Delegate to WALA-generated sync (discovered via 0-CFA + Mod/Ref analysis)
        GeneratedSync.sync(this, real);

        // Movement modes (DataTracker flags, not discoverable by field analysis)
        this.setSprinting(real.isSprinting());
        this.setSneaking(real.isSneaking());
        this.setSwimming(real.isSwimming());
    }

    // --- Input control ---

    public void setInput(PlayerInput playerInput) {
        this.simulatedInput.set(playerInput);
    }

    public SimulatedInput getSimulatedInput() {
        return this.simulatedInput;
    }
}
