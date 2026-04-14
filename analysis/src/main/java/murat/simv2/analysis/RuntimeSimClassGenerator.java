package murat.simv2.analysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Generates a concrete runtime simulator class used by path prediction.
 * The class is generated from field analysis + sliced source structure so
 * hardcoded shim fields stay in sync with generation output.
 */
public final class RuntimeSimClassGenerator {
    private static final Pattern FIELD_DECL = Pattern.compile(
        "\\b(public|protected|private)\\s+(?:static\\s+)?(?:final\\s+)?[^\\n;()=]+\\s+([A-Za-z_][A-Za-z0-9_]*)\\s*(?:=|;)");

    private static final Pattern METHOD_DECL = Pattern.compile(
        "\\b(public|protected|private)\\s+(?:static\\s+)?[^\\n;=]+\\s+([A-Za-z_][A-Za-z0-9_]*)\\s*\\(([^)]*)\\)");

    private static final Pattern TRACKED_GET = Pattern.compile(
        "dataTracker\\.get\\(Sliced([A-Za-z0-9_]+)\\.([A-Z0-9_]+)\\)");

    private final Path outputDir;

    RuntimeSimClassGenerator(Path outputDir) {
        this.outputDir = outputDir;
    }

    void generate(List<FieldResult> fields) throws IOException {
        Path slicedDir = outputDir.resolve("java/murat/simv2/simulation/sliced");
        SlicedIntrospection sliced = inspectSlicedSources(slicedDir);
        Set<String> placeholders = computePlaceholderFields(fields, sliced);
        TrackerUsage trackerUsage = deriveTrackerUsage(sliced);

        Path javaDir = outputDir.resolve("java/murat/simv2/simulation");
        Files.createDirectories(javaDir);
        Path runtimeFile = javaDir.resolve("RuntimeSlicedClientPlayerEntity.java");
        Files.writeString(runtimeFile, generateRuntimeClassSource(placeholders, trackerUsage));
        System.out.println("Generated RuntimeSlicedClientPlayerEntity.java");
    }

    private SlicedIntrospection inspectSlicedSources(Path slicedDir) throws IOException {
        if (!Files.isDirectory(slicedDir)) {
            return new SlicedIntrospection(new LinkedHashSet<>(), new LinkedHashSet<>(),
                new LinkedHashSet<>(), new LinkedHashSet<>(), new LinkedHashSet<>());
        }

        Set<String> fieldNames = new LinkedHashSet<>();
        Set<String> finalFields = new LinkedHashSet<>();
        Set<String> getterMembers = new LinkedHashSet<>();
        Set<String> setterMembers = new LinkedHashSet<>();
        Set<String> trackedGetKeys = new LinkedHashSet<>();

        try (var paths = Files.list(slicedDir)) {
            for (Path file : (Iterable<Path>) paths.filter(path -> path.getFileName().toString().endsWith(".java"))::iterator) {
                String source = Files.readString(file);
                parseFields(source, fieldNames, finalFields);
                parseMethods(source, getterMembers, setterMembers);
                parseTrackedGets(source, trackedGetKeys);
            }
        }
        return new SlicedIntrospection(fieldNames, finalFields, getterMembers, setterMembers, trackedGetKeys);
    }

    private void parseTrackedGets(String source, Set<String> trackedGetKeys) {
        Matcher matcher = TRACKED_GET.matcher(source);
        while (matcher.find()) {
            trackedGetKeys.add("Sliced" + matcher.group(1) + "." + matcher.group(2));
        }
    }

    private void parseFields(String source, Set<String> fieldNames, Set<String> finalFields) {
        Matcher matcher = FIELD_DECL.matcher(source);
        while (matcher.find()) {
            String full = matcher.group();
            String name = matcher.group(2);
            fieldNames.add(name);
            if (full.contains(" final ")) {
                finalFields.add(name);
            }
        }
    }

    private void parseMethods(String source, Set<String> getterMembers, Set<String> setterMembers) {
        Matcher matcher = METHOD_DECL.matcher(source);
        while (matcher.find()) {
            String name = matcher.group(2);
            String params = matcher.group(3).trim();
            if ((name.startsWith("get") || name.startsWith("is") || name.startsWith("has")) && params.isEmpty()) {
                getterMembers.add(inferMemberName(name));
                continue;
            }
            if (name.startsWith("set") && countParams(params) == 1 && name.length() > 3) {
                setterMembers.add(Character.toLowerCase(name.charAt(3)) + name.substring(4));
            }
        }
    }

    private String inferMemberName(String methodName) {
        if ((methodName.startsWith("get") || methodName.startsWith("has")) && methodName.length() > 3) {
            return Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
        }
        if (methodName.startsWith("is") && methodName.length() > 2) {
            return Character.toLowerCase(methodName.charAt(2)) + methodName.substring(3);
        }
        return methodName;
    }

    private int countParams(String params) {
        if (params == null || params.isBlank()) {
            return 0;
        }
        int count = 1;
        for (int i = 0; i < params.length(); i++) {
            if (params.charAt(i) == ',') {
                count++;
            }
        }
        return count;
    }

    private Set<String> computePlaceholderFields(List<FieldResult> fields, SlicedIntrospection sliced) {
        Set<String> placeholders = new LinkedHashSet<>();
        for (FieldResult field : fields) {
            String name = field.fieldName();
            if (AnalysisConfig.EXCLUDED_FIELDS.contains(name) || !isJavaIdentifier(name)) {
                continue;
            }

            boolean hasField = sliced.fieldNames().contains(name);
            boolean hasGetter = sliced.getterMembers().contains(name);
            boolean hasSetter = sliced.setterMembers().contains(name);
            boolean readable = hasField || hasGetter;
            boolean writable = hasSetter || (hasField && !sliced.finalFields().contains(name));

            if (!readable || !writable) {
                placeholders.add(name);
            }
        }
        return placeholders;
    }

    private TrackerUsage deriveTrackerUsage(SlicedIntrospection sliced) {
        Set<String> keys = sliced.trackedGetKeys();
        boolean livingFlags = keys.contains("SlicedLivingEntity.LIVING_FLAGS");
        boolean sleepingPosition = keys.contains("SlicedLivingEntity.SLEEPING_POSITION");
        boolean health = keys.contains("SlicedLivingEntity.HEALTH");
        boolean absorption = keys.contains("SlicedPlayerEntity.ABSORPTION_AMOUNT");
        return new TrackerUsage(livingFlags, sleepingPosition, health, absorption);
    }

    private boolean isJavaIdentifier(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }
        if (!Character.isJavaIdentifierStart(name.charAt(0))) {
            return false;
        }
        for (int i = 1; i < name.length(); i++) {
            if (!Character.isJavaIdentifierPart(name.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private String generateRuntimeClassSource(Set<String> placeholders, TrackerUsage trackerUsage) {
        StringBuilder sb = new StringBuilder();
        sb.append("package murat.simv2.simulation;\n\n");
        sb.append("import java.lang.reflect.Field;\n");
        sb.append("import java.lang.reflect.Modifier;\n");
        sb.append("import java.util.Collection;\n");
        sb.append("import java.util.Map;\n");
        sb.append("import java.util.Optional;\n");
        sb.append("import murat.simv2.simulation.sliced.SlicedClientPlayerEntity;\n");
        sb.append("import murat.simv2.simulation.sliced.SlicedEntity;\n");
        sb.append("import murat.simv2.simulation.sliced.SlicedLivingEntity;\n");
        sb.append("import net.minecraft.client.MinecraftClient;\n");
        sb.append("import net.minecraft.client.network.ClientPlayNetworkHandler;\n");
        sb.append("import net.minecraft.client.network.ClientPlayerEntity;\n");
        sb.append("import net.minecraft.client.recipebook.ClientRecipeBook;\n");
        sb.append("import net.minecraft.client.world.ClientWorld;\n");
        sb.append("import net.minecraft.entity.damage.DamageSource;\n");
        sb.append("import net.minecraft.entity.data.DataTracker;\n");
        sb.append("import net.minecraft.entity.data.TrackedData;\n");
        sb.append("import net.minecraft.registry.entry.RegistryEntry;\n");
        sb.append("import net.minecraft.server.world.ServerWorld;\n");
        sb.append("import net.minecraft.stat.StatHandler;\n");
        sb.append("import net.minecraft.util.math.BlockPos;\n");
        sb.append("import net.minecraft.util.math.Vec3d;\n");
        sb.append("import net.minecraft.world.GameMode;\n");
        sb.append("import net.minecraft.world.event.GameEvent;\n\n");
        sb.append("// Generated by movement analysis — do not edit\n");
        sb.append("public final class RuntimeSlicedClientPlayerEntity extends SlicedClientPlayerEntity {\n");
        sb.append("    private final MinecraftClient minecraftClient;\n");
        sb.append("    private ClientPlayerEntity boundRealPlayer;\n");
        if (trackerUsage.livingFlags()) {
            sb.append("    private byte livingFlagsMirror;\n");
        }
        if (trackerUsage.sleepingPosition()) {
            sb.append("    private Optional<BlockPos> sleepingPositionMirror = Optional.empty();\n");
        }
        if (trackerUsage.health()) {
            sb.append("    private float healthMirror = 20.0F;\n");
        }
        if (trackerUsage.absorptionAmount()) {
            sb.append("    private float absorptionAmountMirror;\n");
        }
        sb.append('\n');

        for (String name : placeholders) {
            sb.append("    public Object ").append(name).append(";\n");
        }
        if (!placeholders.isEmpty()) {
            sb.append('\n');
        }

        sb.append("    public RuntimeSlicedClientPlayerEntity(MinecraftClient client, ClientPlayerEntity realPlayer) {\n");
        sb.append("        super(\n");
        sb.append("            prepareConstruction(client, realPlayer),\n");
        sb.append("            requireWorld(realPlayer),\n");
        sb.append("            requireNetworkHandler(client),\n");
        sb.append("            requireStatHandler(realPlayer),\n");
        sb.append("            requireRecipeBook(realPlayer),\n");
        sb.append("            realPlayer.isSneaking(),\n");
        sb.append("            realPlayer.isSprinting()\n");
        sb.append("        );\n");
        sb.append("        this.minecraftClient = client;\n");
        sb.append("        this.bindContext(realPlayer);\n");
        sb.append("        this.copyTrackedState(realPlayer);\n");
        sb.append("    }\n\n");

        sb.append("    public void syncFrom(ClientPlayerEntity realPlayer) {\n");
        sb.append("        this.bindContext(realPlayer);\n");
        sb.append("        GeneratedSync.sync(realPlayer, this);\n");
        sb.append("        this.applyShadowedState();\n");
        sb.append("        this.bindContext(realPlayer);\n");
        sb.append("        this.copyTrackedState(realPlayer);\n");
        sb.append("    }\n\n");

        sb.append("    public boolean isBoundTo(ClientPlayerEntity realPlayer) {\n");
        sb.append("        return this.boundRealPlayer == realPlayer;\n");
        sb.append("    }\n\n");

        sb.append("    @Override\n");
        sb.append("    protected void initDataTracker(DataTracker.Builder builder) {\n");
        sb.append("        if (!(this.entityBridge instanceof ClientPlayerEntity bridgePlayer)) {\n");
        sb.append("            throw new IllegalStateException(\"Entity bridge is not a ClientPlayerEntity during tracker init\");\n");
        sb.append("        }\n");
        sb.append("        for (DataTracker.Entry<?> entry : bridgePlayer.getDataTracker().entries) {\n");
        sb.append("            if (entry == null) {\n");
        sb.append("                continue;\n");
        sb.append("            }\n");
        sb.append("            TrackedData<?> key = entry.getData();\n");
        sb.append("            if (isBaseEntityTrackerKey(key)) {\n");
        sb.append("                continue;\n");
        sb.append("            }\n");
        sb.append("            addTrackerEntry(builder, key, entry.get());\n");
        sb.append("        }\n");
        sb.append("    }\n\n");

        sb.append("    @SuppressWarnings({ \"rawtypes\", \"unchecked\" })\n");
        sb.append("    private static void addTrackerEntry(DataTracker.Builder builder, TrackedData<?> key, Object value) {\n");
        sb.append("        builder.add((TrackedData) key, value);\n");
        sb.append("    }\n\n");

        sb.append("    private static boolean isBaseEntityTrackerKey(TrackedData<?> key) {\n");
        sb.append("        return key == SlicedEntity.FLAGS\n");
        sb.append("            || key == SlicedEntity.AIR\n");
        sb.append("            || key == SlicedEntity.CUSTOM_NAME\n");
        sb.append("            || key == SlicedEntity.NAME_VISIBLE\n");
        sb.append("            || key == SlicedEntity.SILENT\n");
        sb.append("            || key == SlicedEntity.NO_GRAVITY\n");
        sb.append("            || key == SlicedEntity.POSE\n");
        sb.append("            || key == SlicedEntity.FROZEN_TICKS;\n");
        sb.append("    }\n\n");

        sb.append("    @Override\n");
        sb.append("    public boolean damage(ServerWorld world, DamageSource source, float amount) {\n");
        sb.append("        return false;\n");
        sb.append("    }\n\n");

        sb.append("    @Override\n");
        sb.append("    public void emitGameEvent(RegistryEntry<GameEvent> event) {\n");
        sb.append("    }\n\n");

        sb.append("    @Override\n");
        sb.append("    public GameMode getGameMode() {\n");
        sb.append("        if (this.minecraftClient.interactionManager != null && this.minecraftClient.interactionManager.getCurrentGameMode() != null) {\n");
        sb.append("            return this.minecraftClient.interactionManager.getCurrentGameMode();\n");
        sb.append("        }\n");
        sb.append("        if (this.boundRealPlayer != null && this.boundRealPlayer.isSpectator()) {\n");
        sb.append("            return GameMode.SPECTATOR;\n");
        sb.append("        }\n");
        sb.append("        return GameMode.SURVIVAL;\n");
        sb.append("    }\n\n");

        sb.append("    @Override\n");
        sb.append("    public void setVelocity(double x, double y, double z) {\n");
        sb.append("        this.velocity = new Vec3d(x, y, z);\n");
        sb.append("    }\n\n");

        if (trackerUsage.livingFlags()) {
            sb.append("    @Override\n");
            sb.append("    public boolean isUsingRiptide() {\n");
            sb.append("        return (this.livingFlagsMirror & 4) != 0;\n");
            sb.append("    }\n\n");
        }

        if (trackerUsage.sleepingPosition()) {
            sb.append("    @Override\n");
            sb.append("    public Optional<BlockPos> getSleepingPosition() {\n");
            sb.append("        return this.sleepingPositionMirror;\n");
            sb.append("    }\n\n");

            sb.append("    @Override\n");
            sb.append("    public boolean isSleeping() {\n");
            sb.append("        return this.sleepingPositionMirror.isPresent();\n");
            sb.append("    }\n\n");
        }

        if (trackerUsage.health()) {
            sb.append("    @Override\n");
            sb.append("    public float getHealth() {\n");
            sb.append("        return this.healthMirror;\n");
            sb.append("    }\n\n");

            sb.append("    @Override\n");
            sb.append("    public void setHealth(float health) {\n");
            sb.append("        this.healthMirror = health;\n");
            sb.append("    }\n\n");
        }

        if (trackerUsage.absorptionAmount()) {
            sb.append("    @Override\n");
            sb.append("    protected void setAbsorptionAmountUnclamped(float absorptionAmount) {\n");
            sb.append("        this.absorptionAmountMirror = absorptionAmount;\n");
            sb.append("    }\n\n");

            sb.append("    @Override\n");
            sb.append("    public float getAbsorptionAmount() {\n");
            sb.append("        return this.absorptionAmountMirror;\n");
            sb.append("    }\n\n");
        }

        sb.append("    private void bindContext(ClientPlayerEntity realPlayer) {\n");
        sb.append("        this.boundRealPlayer = realPlayer;\n");
        sb.append("        this.entityBridge = realPlayer;\n");
        sb.append("        this.client = this.minecraftClient;\n");
        sb.append("        this.world = realPlayer.getWorld();\n");
        sb.append("        this.type = realPlayer.getType();\n");
        sb.append("        this.vehicle = realPlayer.getVehicle();\n");
        sb.append("        this.blockPos = realPlayer.getBlockPos();\n");
        sb.append("    }\n\n");

        sb.append("    private void copyTrackedState(ClientPlayerEntity realPlayer) {\n");
        sb.append("        this.dataTracker.set(SlicedEntity.FLAGS, encodeEntityFlags(realPlayer));\n");
        sb.append("        this.dataTracker.set(SlicedEntity.POSE, realPlayer.getPose());\n");
        sb.append("        this.dataTracker.set(SlicedEntity.FROZEN_TICKS, realPlayer.getFrozenTicks());\n");
        if (trackerUsage.livingFlags()) {
            sb.append("        this.livingFlagsMirror = encodeLivingFlags(realPlayer);\n");
        }
        if (trackerUsage.health()) {
            sb.append("        this.healthMirror = realPlayer.getHealth();\n");
        }
        if (trackerUsage.sleepingPosition()) {
            sb.append("        this.sleepingPositionMirror = realPlayer.getSleepingPosition();\n");
        }
        if (trackerUsage.absorptionAmount()) {
            sb.append("        this.absorptionAmountMirror = realPlayer.getAbsorptionAmount();\n");
        }
        sb.append("    }\n\n");

        sb.append("    private void applyShadowedState() {\n");
        for (String name : placeholders) {
            sb.append("        applyShadowField(\"").append(name).append("\", this.").append(name).append(");\n");
        }
        sb.append("    }\n\n");

        sb.append("    @SuppressWarnings({ \"rawtypes\", \"unchecked\" })\n");
        sb.append("    private void applyShadowField(String fieldName, Object shadowValue) {\n");
        sb.append("        if (shadowValue == null) {\n");
        sb.append("            return;\n");
        sb.append("        }\n");
        sb.append("        try {\n");
        sb.append("            Field targetField = findField(getClass().getSuperclass(), fieldName);\n");
        sb.append("            if (targetField == null) {\n");
        sb.append("                return;\n");
        sb.append("            }\n");
        sb.append("            targetField.setAccessible(true);\n");
        sb.append("            Object targetValue = targetField.get(this);\n");
        sb.append("            if (targetValue instanceof Map<?, ?> targetMap && shadowValue instanceof Map<?, ?> shadowMap) {\n");
        sb.append("                ((Map) targetMap).clear();\n");
        sb.append("                ((Map) targetMap).putAll((Map) shadowMap);\n");
        sb.append("                return;\n");
        sb.append("            }\n");
        sb.append("            if (targetValue instanceof Collection<?> targetCollection && shadowValue instanceof Collection<?> shadowCollection) {\n");
        sb.append("                ((Collection) targetCollection).clear();\n");
        sb.append("                ((Collection) targetCollection).addAll((Collection) shadowCollection);\n");
        sb.append("                return;\n");
        sb.append("            }\n");
        sb.append("            if (targetValue != null && targetValue.getClass().isArray() && shadowValue.getClass().isArray()) {\n");
        sb.append("                int copyLength = Math.min(java.lang.reflect.Array.getLength(targetValue), java.lang.reflect.Array.getLength(shadowValue));\n");
        sb.append("                System.arraycopy(shadowValue, 0, targetValue, 0, copyLength);\n");
        sb.append("                return;\n");
        sb.append("            }\n");
        sb.append("            if (!Modifier.isFinal(targetField.getModifiers())) {\n");
        sb.append("                targetField.set(this, shadowValue);\n");
        sb.append("            }\n");
        sb.append("        } catch (ReflectiveOperationException ex) {\n");
        sb.append("            throw new IllegalStateException(\"Failed to apply shadow field '\" + fieldName + \"'\", ex);\n");
        sb.append("        }\n");
        sb.append("    }\n\n");

        sb.append("    private static Field findField(Class<?> type, String fieldName) {\n");
        sb.append("        Class<?> current = type;\n");
        sb.append("        while (current != null && current != Object.class) {\n");
        sb.append("            try {\n");
        sb.append("                return current.getDeclaredField(fieldName);\n");
        sb.append("            } catch (NoSuchFieldException ignored) {\n");
        sb.append("                current = current.getSuperclass();\n");
        sb.append("            }\n");
        sb.append("        }\n");
        sb.append("        return null;\n");
        sb.append("    }\n\n");

        sb.append("    private static byte encodeEntityFlags(ClientPlayerEntity realPlayer) {\n");
        sb.append("        byte flags = 0;\n");
        sb.append("        if (realPlayer.isSneaking()) {\n");
        sb.append("            flags |= (byte) (1 << SlicedEntity.SNEAKING_FLAG_INDEX);\n");
        sb.append("        }\n");
        sb.append("        if (realPlayer.isSprinting()) {\n");
        sb.append("            flags |= (byte) (1 << SlicedEntity.SPRINTING_FLAG_INDEX);\n");
        sb.append("        }\n");
        sb.append("        if (realPlayer.isSwimming()) {\n");
        sb.append("            flags |= (byte) (1 << SlicedEntity.SWIMMING_FLAG_INDEX);\n");
        sb.append("        }\n");
        sb.append("        if (realPlayer.isGliding()) {\n");
        sb.append("            flags |= (byte) (1 << SlicedLivingEntity.GLIDING_FLAG_INDEX);\n");
        sb.append("        }\n");
        sb.append("        return flags;\n");
        sb.append("    }\n\n");

        if (trackerUsage.livingFlags()) {
            sb.append("    private static byte encodeLivingFlags(ClientPlayerEntity realPlayer) {\n");
            sb.append("        byte flags = 0;\n");
            sb.append("        if (realPlayer.isUsingItem()) {\n");
            sb.append("            flags |= 1;\n");
            sb.append("        }\n");
            sb.append("        if (realPlayer.isUsingRiptide()) {\n");
            sb.append("            flags |= 4;\n");
            sb.append("        }\n");
            sb.append("        return flags;\n");
            sb.append("    }\n\n");
        }

        sb.append("    private static ClientWorld requireWorld(ClientPlayerEntity realPlayer) {\n");
        sb.append("        if (realPlayer.getWorld() instanceof ClientWorld clientWorld) {\n");
        sb.append("            return clientWorld;\n");
        sb.append("        }\n");
        sb.append("        throw new IllegalStateException(\"Client player world is not a ClientWorld\");\n");
        sb.append("    }\n\n");

        sb.append("    private static MinecraftClient prepareConstruction(MinecraftClient client, ClientPlayerEntity realPlayer) {\n");
        sb.append("        SlicedEntity.pushEntityBridgeBootstrap(realPlayer);\n");
        sb.append("        return client;\n");
        sb.append("    }\n\n");

        sb.append("    private static ClientPlayNetworkHandler requireNetworkHandler(MinecraftClient client) {\n");
        sb.append("        ClientPlayNetworkHandler handler = client.getNetworkHandler();\n");
        sb.append("        if (handler == null) {\n");
        sb.append("            throw new IllegalStateException(\"Network handler is not available\");\n");
        sb.append("        }\n");
        sb.append("        return handler;\n");
        sb.append("    }\n\n");

        sb.append("    private static StatHandler requireStatHandler(ClientPlayerEntity realPlayer) {\n");
        sb.append("        StatHandler statHandler = realPlayer.getStatHandler();\n");
        sb.append("        if (statHandler == null) {\n");
        sb.append("            throw new IllegalStateException(\"StatHandler is not available\");\n");
        sb.append("        }\n");
        sb.append("        return statHandler;\n");
        sb.append("    }\n\n");

        sb.append("    private static ClientRecipeBook requireRecipeBook(ClientPlayerEntity realPlayer) {\n");
        sb.append("        Object recipeBook = realPlayer.getRecipeBook();\n");
        sb.append("        if (recipeBook instanceof ClientRecipeBook clientRecipeBook) {\n");
        sb.append("            return clientRecipeBook;\n");
        sb.append("        }\n");
        sb.append("        throw new IllegalStateException(\"RecipeBook is not a ClientRecipeBook\");\n");
        sb.append("    }\n");
        sb.append("}\n");

        return sb.toString();
    }

    private record SlicedIntrospection(Set<String> fieldNames,
                                       Set<String> finalFields,
                                       Set<String> getterMembers,
                                       Set<String> setterMembers,
                                       Set<String> trackedGetKeys) {}

    private record TrackerUsage(boolean livingFlags,
                                boolean sleepingPosition,
                                boolean health,
                                boolean absorptionAmount) {}
}
