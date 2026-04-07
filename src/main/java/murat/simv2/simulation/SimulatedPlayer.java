package murat.simv2.simulation;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.objects.Object2DoubleArrayMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.recipebook.ClientRecipeBook;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.EntityEquipment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LimbAnimator;
import net.minecraft.entity.TrackedPosition;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.DefaultAttributeRegistry;
import net.minecraft.entity.damage.DamageTracker;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.mob.ElytraFlightController;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.StatHandler;
import net.minecraft.util.Cooldown;
import net.minecraft.util.PlayerInput;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.entity.EntityChangeListener;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulatedPlayer extends ClientPlayerEntity {

    private SimulatedInput simulatedInput;

    /**
     * Dummy constructor — only exists so javac sees a valid super() call.
     * Never actually invoked; Objenesis bypasses it via Unsafe.allocateInstance.
     */
    @SuppressWarnings("unused")
    private SimulatedPlayer() {
        super(
            MinecraftClient.getInstance(),
            MinecraftClient.getInstance().world,
            MinecraftClient.getInstance().player.networkHandler,
            new StatHandler(), new ClientRecipeBook(), false, false
        );
        throw new AssertionError("SimulatedPlayer must be created via SimulatedPlayerFactory");
    }

    /**
     * Initialise every field that the Entity → LivingEntity → PlayerEntity →
     * ClientPlayerEntity constructor chain would have set.
     * Called once, right after Objenesis allocates the blank instance.
     */
    @SuppressWarnings("unchecked")
    void bootstrap(MinecraftClient client, ClientWorld world, ClientPlayNetworkHandler networkHandler) {
        // ── Entity fields ──────────────────────────────────────────────
        this.type = EntityType.PLAYER;
        this.id = Entity.CURRENT_ID.incrementAndGet();
        this.uuid = UUID.randomUUID();
        this.uuidString = this.uuid.toString();
        this.world = world;
        this.random = Random.create();
        this.pos = Vec3d.ZERO;
        this.blockPos = BlockPos.ORIGIN;
        this.chunkPos = new ChunkPos(BlockPos.ORIGIN);
        this.setVelocity(Vec3d.ZERO);
        this.dimensions = EntityType.PLAYER.getDimensions();
        this.standingEyeHeight = this.dimensions.eyeHeight();
        this.setBoundingBox(this.dimensions.getBoxAt(Vec3d.ZERO));
        this.fluidHeight = new Object2DoubleArrayMap<>(2);
        this.submergedFluidTag = new HashSet<>();
        this.movementMultiplier = Vec3d.ZERO;
        this.supportingBlockPos = Optional.empty();
        this.passengerList = ImmutableList.of();
        this.firstUpdate = true;
        this.trackedPosition = new TrackedPosition();
        this.commandTags = new HashSet<>();
        this.pistonMovementDelta = new double[3];
        this.queuedCollisionChecks = List.of(new ArrayList<>(), new ArrayList<>());
        this.currentlyCheckedCollisions = new ArrayList<>();
        this.collidedBlockPositions = new LongOpenHashSet();
        this.collisionHandler = new EntityCollisionHandler.Impl();
        this.changeListener = EntityChangeListener.NONE;

        // DataTracker — register all tracked data entries from the full hierarchy
        DataTracker.Builder builder = new DataTracker.Builder(this);
        this.initDataTracker(builder);
        this.dataTracker = builder.build();

        // ── LivingEntity fields ────────────────────────────────────────
        this.attributes = new AttributeContainer(DefaultAttributeRegistry.get(EntityType.PLAYER));
        this.damageTracker = new DamageTracker(this);
        this.activeStatusEffects = new HashMap<>();
        this.lastEquipmentStacks = new HashMap<>();
        this.limbAnimator = new LimbAnimator();
        this.defaultMaxHealth = (int) this.getMaxHealth();
        this.elytraFlightController = new ElytraFlightController(this);
        this.locationBasedEnchantmentEffects = new EnumMap<>(net.minecraft.entity.EquipmentSlot.class);
        this.equipment = new EntityEquipment();
        this.activeItemStack = ItemStack.EMPTY;

        // ── PlayerEntity fields ────────────────────────────────────────
        this.abilities = new PlayerAbilities();
        this.gameProfile = new GameProfile(this.uuid, "SimulatedPlayer");
        this.inventory = new PlayerInventory(this, this.equipment);
        this.enderChestInventory = new EnderChestInventory();
        this.hungerManager = new HungerManager();
        this.playerScreenHandler = new PlayerScreenHandler(this.inventory, true, this);
        this.itemCooldownManager = new ItemCooldownManager();
        this.currentScreenHandler = this.playerScreenHandler;

        // ── ClientPlayerEntity fields ──────────────────────────────────
        this.client = client;
        this.networkHandler = networkHandler;
        this.statHandler = new StatHandler();
        this.recipeBook = new ClientRecipeBook();
        this.itemDropCooldown = new Cooldown(20, 5);
        this.tickables = new ArrayList<>();

        // ── Simulated input ────────────────────────────────────────────
        this.simulatedInput = new SimulatedInput();
        this.input = this.simulatedInput;

        // Initial health
        this.setHealth(this.getMaxHealth());
    }

    // --- Side-effect suppression ---

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

    @Override
    public boolean shouldSpawnSprintingParticles() {
        return false;
    }

    @Override
    protected void onSwimmingStart() {
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
        this.tickStatusEffects();
        this.tickMovement();
    }

    // --- State sync ---

    public void syncFrom(ClientPlayerEntity real) {
        GeneratedSync.sync(this, real);
    }

    // --- Input control ---

    public void setInput(PlayerInput playerInput) {
        this.simulatedInput.set(playerInput);
    }

    public SimulatedInput getSimulatedInput() {
        return this.simulatedInput;
    }
}
