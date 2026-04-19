package murat.simv2.simulation.mirror.net.minecraft.network.message;

// Generated mirror stub for simulation closure.
public interface SentMessage {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public murat.simv2.simulation.mirror.net.minecraft.text.Text content();

    public static murat.simv2.simulation.mirror.net.minecraft.network.message.SentMessage of(java.lang.Object p0) {
        return null;
    }

    public void send(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType.Parameters p2);

    public static class Chat implements murat.simv2.simulation.mirror.net.minecraft.network.message.SentMessage {
        public java.lang.Object message;

        public Chat(java.lang.Object p0) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.text.Text content() {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public java.lang.Object message() {
            return null;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.network.message.SentMessage of(java.lang.Object p0) {
            return null;
        }

        public void send(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType.Parameters p2) {
        }

        public java.lang.String toString() {
            return null;
        }

        public Chat() {
        }

    }

    public static class Profileless implements murat.simv2.simulation.mirror.net.minecraft.network.message.SentMessage {
        public murat.simv2.simulation.mirror.net.minecraft.text.Text content;

        public Profileless(murat.simv2.simulation.mirror.net.minecraft.text.Text p0) {
        }

        public murat.simv2.simulation.mirror.net.minecraft.text.Text content() {
            return null;
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public static murat.simv2.simulation.mirror.net.minecraft.network.message.SentMessage of(java.lang.Object p0) {
            return null;
        }

        public void send(murat.simv2.simulation.mirror.net.minecraft.server.network.ServerPlayerEntity p0, boolean p1, murat.simv2.simulation.mirror.net.minecraft.network.message.MessageType.Parameters p2) {
        }

        public java.lang.String toString() {
            return null;
        }

        public Profileless() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
