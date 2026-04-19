package murat.simv2.simulation.mirror.net.minecraft.network.message;

// Generated mirror stub for simulation closure.
public class MessageType {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType> CHAT;
    public static java.lang.Object CHAT_TEXT_DECORATION;
    public static com.mojang.serialization.Codec<murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType> CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType> EMOTE_COMMAND;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType>> ENTRY_PACKET_CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType> MSG_COMMAND_INCOMING;
    public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType> MSG_COMMAND_OUTGOING;
    public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType> PACKET_CODEC;
    public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType> SAY_COMMAND;
    public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType> TEAM_MSG_COMMAND_INCOMING;
    public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey<murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType> TEAM_MSG_COMMAND_OUTGOING;
    public java.lang.Object chat;
    public java.lang.Object narration;

    public MessageType(java.lang.Object p0, java.lang.Object p1) {
    }

    public static void bootstrap(murat.simv2.simulation.mirror.net.minecraft.registry.Registerable p0) {
    }

    public java.lang.Object chat() {
        return null;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public java.lang.Object narration() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType.Parameters params(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, murat.simv2.simulation.mirror.net.minecraft.entity.Entity p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType.Parameters params(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, murat.simv2.simulation.mirror.net.minecraft.registry.DynamicRegistryManager p1, murat.simv2.simulation.mirror.net.minecraft.text.Text p2) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType.Parameters params(murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey p0, murat.simv2.simulation.mirror.net.minecraft.server.command.ServerCommandSource p1) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.registry.RegistryKey register(java.lang.String p0) {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public MessageType() {
    }

    public static class Parameters {
        public static murat.simv2.simulation.mirror.net.minecraft.network.codec.PacketCodec<java.lang.Object, murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType.Parameters> CODEC;
        public murat.simv2.simulation.mirror.net.minecraft.text.Text name;
        public java.util.Optional<murat.simv2.simulation.mirror.net.minecraft.text.Text> targetName;
        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry<murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType> type;

        public Parameters(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.text.Text p1) {
        }

        public Parameters(murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry p0, murat.simv2.simulation.mirror.net.minecraft.text.Text p1, java.util.Optional p2) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.text.Text applyChatDecoration(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.text.Text applyNarrationDecoration(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public murat.simv2.simulation.mirror.net.minecraft.text.Text name() {
            return null;
        }

        public java.util.Optional targetName() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.registry.entry.RegistryEntry type() {
            return null;
        }

        public murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType.Parameters withTargetName(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
            return null;
        }

        public Parameters() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
