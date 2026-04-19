package murat.simv2.simulation.mirror.net.minecraft.client.network.message;

// Generated mirror stub for simulation closure.
public class MessageHandler extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public static murat.simv2.simulation.mirror.net.minecraft.text.Text VALIDATION_ERROR_TEXT;
    public long chatDelay;
    public murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient client;
    public java.util.Deque<murat.simv2.simulation.mirror.net.minecraft.client.network.message.MessageHandler.ProcessableMessage> delayedMessages;
    public long lastProcessTime;

    public MessageHandler(murat.simv2.simulation.mirror.net.minecraft.client.MinecraftClient p0) {
    }

    public void addToChatLog(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType.Parameters p1, com.mojang.authlib.GameProfile p2, java.lang.Object p3) {
    }

    public void addToChatLog(murat.simv2.simulation.mirror.net.minecraft.text.Text p0, java.time.Instant p1) {
    }

    public java.util.UUID extractSender(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
        return null;
    }

    public java.lang.Object getStatus(java.lang.Object p0, murat.simv2.simulation.mirror.net.minecraft.text.Text p1, java.time.Instant p2) {
        return null;
    }

    public long getUnprocessedMessageCount() {
        return 0L;
    }

    public boolean isAlwaysTrusted(java.util.UUID p0) {
        return false;
    }

    public void narrate(murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType.Parameters p0, murat.simv2.simulation.mirror.net.minecraft.text.Text p1) {
    }

    public void onChatMessage(java.lang.Object p0, com.mojang.authlib.GameProfile p1, murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType.Parameters p2) {
    }

    public void onGameMessage(murat.simv2.simulation.mirror.net.minecraft.text.Text p0, boolean p1) {
    }

    public void onProfilelessMessage(murat.simv2.simulation.mirror.net.minecraft.text.Text p0, murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType.Parameters p1) {
    }

    public void onUnverifiedMessage(java.util.UUID p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType.Parameters p2) {
    }

    public void processAll() {
    }

    public boolean processChatMessageInternal(murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType.Parameters p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.text.Text p2, com.mojang.authlib.GameProfile p3, boolean p4, java.time.Instant p5) {
        return false;
    }

    public void processDelayedMessages() {
    }

    public void process() {
    }

    public void process(java.lang.Object p0, java.util.function.BooleanSupplier p1) {
    }

    public boolean removeDelayedMessage(java.lang.Object p0) {
        return false;
    }

    public void setChatDelay(double p0) {
    }

    public boolean shouldDelay() {
        return false;
    }

    public MessageHandler() {
    }

    public static class ProcessableMessage {
        public java.util.function.BooleanSupplier handler;
        public java.lang.Object signature;

        public ProcessableMessage(java.lang.Object p0, java.util.function.BooleanSupplier p1) {
        }

        public boolean accept() {
            return false;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public java.util.function.BooleanSupplier handler() {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public java.lang.Object signature() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public ProcessableMessage() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
