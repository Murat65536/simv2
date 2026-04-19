package murat.simv2.simulation.mirror.net.minecraft.command.argument;

// Generated mirror stub for simulation closure.
public class EntityAnchorArgumentType extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static java.util.Collection<java.lang.String> EXAMPLES;
    public static com.mojang.brigadier.exceptions.DynamicCommandExceptionType INVALID_ANCHOR_EXCEPTION;

    public EntityAnchorArgumentType() {
    }

    public static murat.simv2.simulation.mirror.net.minecraft.command.argument.EntityAnchorArgumentType entityAnchor() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor getEntityAnchor(com.mojang.brigadier.context.CommandContext p0, java.lang.String p1) {
        return null;
    }

    public java.util.Collection getExamples() {
        return null;
    }

    public java.util.concurrent.CompletableFuture listSuggestions(com.mojang.brigadier.context.CommandContext p0, com.mojang.brigadier.suggestion.SuggestionsBuilder p1) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor parse(com.mojang.brigadier.StringReader p0) {
        return null;
    }

    public static class EntityAnchor {
        public static java.util.Map<java.lang.String, murat.simv2.simulation.mirror.net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor> ANCHORS;
        public static murat.simv2.simulation.mirror.net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor EYES;
        public static murat.simv2.simulation.mirror.net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor FEET;
        public static murat.simv2.simulation.mirror.net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor[] field_9850;
        public java.lang.String id;
        public java.util.function.BiFunction<murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d, murat.simv2.simulation.mirror.net.minecraft.entity.Entity, murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d> offset;

        public EntityAnchor(java.lang.String p0, int p1, java.lang.String p2, java.util.function.BiFunction p3) {
        }

        public static murat.simv2.simulation.mirror.net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor fromId(java.lang.String p0) {
            return null;
        }

        public int ordinal() {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d positionAt(murat.simv2.simulation.mirror.net.minecraft.entity.Entity p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.util.math.Vec3d positionAt(murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor valueOf(java.lang.String p0) {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor[] values() {
            return null;
        }

        public EntityAnchor() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
