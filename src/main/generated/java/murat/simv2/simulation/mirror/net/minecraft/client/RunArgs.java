package murat.simv2.simulation.mirror.net.minecraft.client;

// Generated mirror stub for simulation closure.
public class RunArgs extends java.lang.Object {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public murat.simv2.simulation.mirror.net.minecraft.client.RunArgs.Directories directories;
    public murat.simv2.simulation.mirror.net.minecraft.client.RunArgs.Game game;
    public murat.simv2.simulation.mirror.net.minecraft.client.RunArgs.Network network;
    public murat.simv2.simulation.mirror.net.minecraft.client.RunArgs.QuickPlay quickPlay;
    public java.lang.Object windowSettings;

    public RunArgs(murat.simv2.simulation.mirror.net.minecraft.client.RunArgs.Network p0, java.lang.Object p1, murat.simv2.simulation.mirror.net.minecraft.client.RunArgs.Directories p2, murat.simv2.simulation.mirror.net.minecraft.client.RunArgs.Game p3, murat.simv2.simulation.mirror.net.minecraft.client.RunArgs.QuickPlay p4) {
    }

    public RunArgs() {
    }

    public static class Directories extends java.lang.Object {
        public java.io.File assetDir;
        public java.lang.String assetIndex;
        public java.io.File resourcePackDir;
        public java.io.File runDir;

        public Directories(java.io.File p0, java.io.File p1, java.io.File p2, java.lang.String p3) {
        }

        public java.nio.file.Path getAssetDir() {
            return null;
        }

        public Directories() {
        }

    }

    public static class Game extends java.lang.Object {
        public boolean demo;
        public boolean multiplayerDisabled;
        public boolean onlineChatDisabled;
        public boolean renderDebugLabels;
        public boolean tracyEnabled;
        public java.lang.String version;
        public java.lang.String versionType;

        public Game(boolean p0, java.lang.String p1, java.lang.String p2, boolean p3, boolean p4, boolean p5, boolean p6) {
        }

        public Game() {
        }

    }

    public static class Network extends java.lang.Object {
        public java.net.Proxy netProxy;
        public com.mojang.authlib.properties.PropertyMap profileProperties;
        public murat.simv2.simulation.mirror.net.minecraft.client.session.Session session;
        public com.mojang.authlib.properties.PropertyMap userProperties;

        public Network(murat.simv2.simulation.mirror.net.minecraft.client.session.Session p0, com.mojang.authlib.properties.PropertyMap p1, com.mojang.authlib.properties.PropertyMap p2, java.net.Proxy p3) {
        }

        public Network() {
        }

    }

    public static class QuickPlay {
        public java.lang.String multiplayer;
        public java.lang.String path;
        public java.lang.String realms;
        public java.lang.String singleplayer;

        public QuickPlay(java.lang.String p0, java.lang.String p1, java.lang.String p2, java.lang.String p3) {
        }

        public boolean equals(java.lang.Object p0) {
            return false;
        }

        public int hashCode() {
            return 0;
        }

        public boolean isEnabled() {
            return false;
        }

        public java.lang.String multiplayer() {
            return null;
        }

        public java.lang.String path() {
            return null;
        }

        public java.lang.String realms() {
            return null;
        }

        public java.lang.String singleplayer() {
            return null;
        }

        public java.lang.String toString() {
            return null;
        }

        public QuickPlay() {
        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
