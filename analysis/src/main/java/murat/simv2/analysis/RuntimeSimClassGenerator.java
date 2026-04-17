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
 * The class is generated from field analysis + mirrored source structure so
 * hardcoded shim fields stay in sync with generation output.
 */
public final class RuntimeSimClassGenerator {
    private static final Pattern FIELD_DECL = Pattern.compile(
        "\\b(public|protected|private)\\s+(?:static\\s+)?(?:final\\s+)?[^\\n;()=]+\\s+([A-Za-z_][A-Za-z0-9_]*)\\s*(?:=|;)");

    private static final Pattern METHOD_DECL = Pattern.compile(
        "\\b(public|protected|private)\\s+(?:static\\s+)?[^\\n;=]+\\s+([A-Za-z_][A-Za-z0-9_]*)\\s*\\(([^)]*)\\)");

    private static final Pattern TRACKED_GET = Pattern.compile(
        "dataTracker\\.get\\((?:[A-Za-z0-9_$.]+\\.)?([A-Za-z0-9_$]+)\\.([A-Z0-9_]+)\\)");

    private final Path outputDir;

    RuntimeSimClassGenerator(Path outputDir) {
        this.outputDir = outputDir;
    }

    void generate(List<FieldResult> fields) throws IOException {
        Path mirrorDir = outputDir.resolve("java/murat/simv2/simulation/mirror");
        MirrorIntrospection mirror = inspectMirrorSources(mirrorDir);
        Set<String> placeholders = computePlaceholderFields(fields, mirror);
        TrackerUsage trackerUsage = deriveTrackerUsage(mirror);

        Path javaDir = outputDir.resolve("java/murat/simv2/simulation");
        Files.createDirectories(javaDir);
        Files.deleteIfExists(javaDir.resolve("RuntimeSlicedClientPlayerEntity.java"));
        Path runtimeFile = javaDir.resolve("RuntimeMirroredClientPlayerEntity.java");
        Files.writeString(runtimeFile, generateRuntimeClassSource(placeholders, trackerUsage));
        System.out.println("Generated RuntimeMirroredClientPlayerEntity.java");
    }

    private MirrorIntrospection inspectMirrorSources(Path mirrorDir) throws IOException {
        if (!Files.isDirectory(mirrorDir)) {
            return new MirrorIntrospection(new LinkedHashSet<>(), new LinkedHashSet<>(),
                new LinkedHashSet<>(), new LinkedHashSet<>(), new LinkedHashSet<>());
        }

        Set<String> fieldNames = new LinkedHashSet<>();
        Set<String> finalFields = new LinkedHashSet<>();
        Set<String> getterMembers = new LinkedHashSet<>();
        Set<String> setterMembers = new LinkedHashSet<>();
        Set<String> trackedGetKeys = new LinkedHashSet<>();

        try (var paths = Files.walk(mirrorDir)) {
            for (Path file : (Iterable<Path>) paths
                .filter(Files::isRegularFile)
                .filter(path -> path.getFileName().toString().endsWith(".java"))::iterator) {
                String source = Files.readString(file);
                parseFields(source, fieldNames, finalFields);
                parseMethods(source, getterMembers, setterMembers);
                parseTrackedGets(source, trackedGetKeys);
            }
        }
        return new MirrorIntrospection(fieldNames, finalFields, getterMembers, setterMembers, trackedGetKeys);
    }

    private void parseTrackedGets(String source, Set<String> trackedGetKeys) {
        Matcher matcher = TRACKED_GET.matcher(source);
        while (matcher.find()) {
            trackedGetKeys.add(matcher.group(1) + "." + matcher.group(2));
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

    private Set<String> computePlaceholderFields(List<FieldResult> fields, MirrorIntrospection mirror) {
        Set<String> placeholders = new LinkedHashSet<>();
        for (FieldResult field : fields) {
            String name = field.fieldName();
            if (AnalysisConfig.EXCLUDED_FIELDS.contains(name) || !isJavaIdentifier(name)) {
                continue;
            }
            placeholders.add(name);
        }
        return placeholders;
    }

    private TrackerUsage deriveTrackerUsage(MirrorIntrospection mirror) {
        Set<String> keys = mirror.trackedGetKeys();
        boolean livingFlags = keys.contains("LivingEntity.LIVING_FLAGS");
        boolean sleepingPosition = keys.contains("LivingEntity.SLEEPING_POSITION");
        boolean health = keys.contains("LivingEntity.HEALTH");
        boolean absorption = keys.contains("PlayerEntity.ABSORPTION_AMOUNT");
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
        sb.append("import murat.simv2.simulation.mirror.net.minecraft.entity.Entity;\n");
        sb.append("import murat.simv2.simulation.mirror.net.minecraft.entity.EntityPose;\n");
        sb.append("import murat.simv2.simulation.mirror.net.minecraft.entity.LivingEntity;\n");
        sb.append("import murat.simv2.simulation.mirror.net.minecraft.entity.damage.DamageSource;\n");
        sb.append("import murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry;\n");
        sb.append("import murat.simv2.simulation.mirror.net.minecraft.server.world.ServerWorld;\n");
        sb.append("import murat.simv2.simulation.mirror.net.minecraft.util.math.BlockPos;\n");
        sb.append("import murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d;\n");
        sb.append("import murat.simv2.simulation.mirror.net.minecraft.world.GameMode;\n");
        sb.append("import murat.simv2.simulation.mirror.net.minecraft.world.event.GameEvent;\n");
        sb.append("import net.minecraft.client.MinecraftClient;\n");
        sb.append("import net.minecraft.client.network.ClientPlayerEntity;\n");
        sb.append("\n");
        sb.append("// Generated by movement analysis — do not edit\n");
        sb.append("public final class RuntimeMirroredClientPlayerEntity extends murat.simv2.simulation.mirror.net.minecraft.client.network.ClientPlayerEntity {\n");
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

        sb.append("    public RuntimeMirroredClientPlayerEntity(MinecraftClient client, ClientPlayerEntity realPlayer) {\n");
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
        sb.append("    public boolean damage(ServerWorld world, DamageSource source, float amount) {\n");
        sb.append("        return false;\n");
        sb.append("    }\n\n");

        sb.append("    @Override\n");
        sb.append("    public void emitGameEvent(RegistryEntry<GameEvent> event) {\n");
        sb.append("    }\n\n");

        sb.append("    @Override\n");
        sb.append("    public GameMode getGameMode() {\n");
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

        sb.append("    @Override\n");
        sb.append("    protected void initDataTracker(murat.simv2.simulation.mirror.net.minecraft.entity.data.DataTracker.Builder builder) {\n");
        sb.append("    }\n\n");

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
        sb.append("        this.client = null;\n");
        sb.append("        this.world = null;\n");
        sb.append("        this.type = null;\n");
        sb.append("        this.vehicle = null;\n");
        sb.append("        this.blockPos = toMirrorBlockPos(realPlayer.getBlockPos());\n");
        sb.append("    }\n\n");

        sb.append("    private void copyTrackedState(ClientPlayerEntity realPlayer) {\n");
        sb.append("        this.getDataTracker().set(Entity.FLAGS, encodeEntityFlags(realPlayer));\n");
        sb.append("        this.getDataTracker().set(Entity.POSE, toMirrorEntityPose(realPlayer.getPose()));\n");
        sb.append("        this.getDataTracker().set(Entity.FROZEN_TICKS, realPlayer.getFrozenTicks());\n");
        if (trackerUsage.livingFlags()) {
            sb.append("        this.livingFlagsMirror = encodeLivingFlags(realPlayer);\n");
        }
        if (trackerUsage.health()) {
            sb.append("        this.healthMirror = realPlayer.getHealth();\n");
        }
        if (trackerUsage.sleepingPosition()) {
            sb.append("        this.sleepingPositionMirror = realPlayer.getSleepingPosition().map(RuntimeMirroredClientPlayerEntity::toMirrorBlockPos);\n");
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
        sb.append("            if (!isAssignableValue(targetField.getType(), shadowValue)) {\n");
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

        sb.append("    private static boolean isAssignableValue(Class<?> targetType, Object value) {\n");
        sb.append("        if (value == null) {\n");
        sb.append("            return !targetType.isPrimitive();\n");
        sb.append("        }\n");
        sb.append("        if (!targetType.isPrimitive()) {\n");
        sb.append("            return targetType.isInstance(value);\n");
        sb.append("        }\n");
        sb.append("        return (targetType == boolean.class && value instanceof Boolean)\n");
        sb.append("            || (targetType == byte.class && value instanceof Byte)\n");
        sb.append("            || (targetType == short.class && value instanceof Short)\n");
        sb.append("            || (targetType == int.class && value instanceof Integer)\n");
        sb.append("            || (targetType == long.class && value instanceof Long)\n");
        sb.append("            || (targetType == float.class && value instanceof Float)\n");
        sb.append("            || (targetType == double.class && value instanceof Double)\n");
        sb.append("            || (targetType == char.class && value instanceof Character);\n");
        sb.append("    }\n\n");

        sb.append("    private static byte encodeEntityFlags(ClientPlayerEntity realPlayer) {\n");
        sb.append("        byte flags = 0;\n");
        sb.append("        if (realPlayer.isSneaking()) {\n");
        sb.append("            flags |= (byte) (1 << Entity.SNEAKING_FLAG_INDEX);\n");
        sb.append("        }\n");
        sb.append("        if (realPlayer.isSprinting()) {\n");
        sb.append("            flags |= (byte) (1 << Entity.SPRINTING_FLAG_INDEX);\n");
        sb.append("        }\n");
        sb.append("        if (realPlayer.isSwimming()) {\n");
        sb.append("            flags |= (byte) (1 << Entity.SWIMMING_FLAG_INDEX);\n");
        sb.append("        }\n");
        sb.append("        if (realPlayer.isGliding()) {\n");
        sb.append("            flags |= (byte) (1 << LivingEntity.GLIDING_FLAG_INDEX);\n");
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

        sb.append("    private static murat.simv2.simulation.mirror.net.minecraft.client.world.ClientWorld requireWorld(ClientPlayerEntity realPlayer) {\n");
        sb.append("        return new murat.simv2.simulation.mirror.net.minecraft.client.world.ClientWorld();\n");
        sb.append("    }\n\n");

        sb.append("    private static murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient prepareConstruction(MinecraftClient client, ClientPlayerEntity realPlayer) {\n");
        sb.append("        return new murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient();\n");
        sb.append("    }\n\n");

        sb.append("    private static murat.simv2.simulation.mirror.net.minecraft.client.network.ClientPlayNetworkHandler requireNetworkHandler(MinecraftClient client) {\n");
        sb.append("        return new murat.simv2.simulation.mirror.net.minecraft.client.network.ClientPlayNetworkHandler();\n");
        sb.append("    }\n\n");

        sb.append("    private static murat.simv2.simulation.mirror.net.minecraft.stat.StatHandler requireStatHandler(ClientPlayerEntity realPlayer) {\n");
        sb.append("        return new murat.simv2.simulation.mirror.net.minecraft.stat.StatHandler();\n");
        sb.append("    }\n\n");

        sb.append("    private static murat.simv2.simulation.mirror.net.minecraft.client.recipebook.ClientRecipeBook requireRecipeBook(ClientPlayerEntity realPlayer) {\n");
        sb.append("        return new murat.simv2.simulation.mirror.net.minecraft.client.recipebook.ClientRecipeBook();\n");
        sb.append("    }\n\n");

        sb.append("    private static BlockPos toMirrorBlockPos(net.minecraft.util.math.BlockPos source) {\n");
        sb.append("        if (source == null) {\n");
        sb.append("            return null;\n");
        sb.append("        }\n");
        sb.append("        return new BlockPos(source.getX(), source.getY(), source.getZ());\n");
        sb.append("    }\n\n");

        sb.append("    private static EntityPose toMirrorEntityPose(net.minecraft.entity.EntityPose source) {\n");
        sb.append("        if (source == null) {\n");
        sb.append("            return null;\n");
        sb.append("        }\n");
        sb.append("        return EntityPose.valueOf(source.name());\n");
        sb.append("    }\n");
        sb.append("}\n");

        String rendered = sb.toString();
        return rendered.replace("    @Override\n", "");
    }

    private record MirrorIntrospection(Set<String> fieldNames,
                                       Set<String> finalFields,
                                       Set<String> getterMembers,
                                       Set<String> setterMembers,
                                       Set<String> trackedGetKeys) {}

    private record TrackerUsage(boolean livingFlags,
                                boolean sleepingPosition,
                                boolean health,
                                boolean absorptionAmount) {}
}
