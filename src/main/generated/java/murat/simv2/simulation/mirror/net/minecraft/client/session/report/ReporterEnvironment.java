package murat.simv2.simulation.mirror.net.minecraft.client.session.report;

// Generated mirror stub for simulation closure.
public class ReporterEnvironment {

    // BEGIN GENERATED MIRROR NESTED STUBS
    public java.lang.String clientVersion;
    public murat.simv2.simulation.mirror.net.minecraft.client.session.report.ReporterEnvironment.Server server;

    public ReporterEnvironment(java.lang.String p0, murat.simv2.simulation.mirror.net.minecraft.client.session.report.ReporterEnvironment.Server p1) {
    }

    public java.lang.String clientVersion() {
        return null;
    }

    public boolean equals(java.lang.Object p0) {
        return false;
    }

    public static java.lang.String getVersion() {
        return null;
    }

    public int hashCode() {
        return 0;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.client.session.report.ReporterEnvironment ofIntegratedServer() {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.client.session.report.ReporterEnvironment ofRealm(java.lang.Object p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.client.session.report.ReporterEnvironment ofServer(murat.simv2.simulation.mirror.net.minecraft.client.session.report.ReporterEnvironment.Server p0) {
        return null;
    }

    public static murat.simv2.simulation.mirror.net.minecraft.client.session.report.ReporterEnvironment ofThirdPartyServer(java.lang.String p0) {
        return null;
    }

    public murat.simv2.simulation.mirror.net.minecraft.client.session.report.ReporterEnvironment.Server server() {
        return null;
    }

    public com.mojang.authlib.yggdrasil.request.AbuseReportRequest.ClientInfo toClientInfo() {
        return null;
    }

    public com.mojang.authlib.yggdrasil.request.AbuseReportRequest.RealmInfo toRealmInfo() {
        return null;
    }

    public java.lang.String toString() {
        return null;
    }

    public com.mojang.authlib.yggdrasil.request.AbuseReportRequest.ThirdPartyServerInfo toThirdPartyServerInfo() {
        return null;
    }

    public ReporterEnvironment() {
    }

    public static interface Server {
        public static class Realm implements murat.simv2.simulation.mirror.net.minecraft.client.session.report.ReporterEnvironment.Server {
            public long realmId;
            public int slotId;

            public Realm(java.lang.Object p0) {
            }

            public Realm(long p0, int p1) {
            }

            public boolean equals(java.lang.Object p0) {
                return false;
            }

            public int hashCode() {
                return 0;
            }

            public long realmId() {
                return 0L;
            }

            public int slotId() {
                return 0;
            }

            public java.lang.String toString() {
                return null;
            }

            public Realm() {
            }

        }

        public static class ThirdParty implements murat.simv2.simulation.mirror.net.minecraft.client.session.report.ReporterEnvironment.Server {
            public java.lang.String ip;

            public ThirdParty(java.lang.String p0) {
            }

            public boolean equals(java.lang.Object p0) {
                return false;
            }

            public int hashCode() {
                return 0;
            }

            public java.lang.String ip() {
                return null;
            }

            public java.lang.String toString() {
                return null;
            }

            public ThirdParty() {
            }

        }

    }

    // END GENERATED MIRROR NESTED STUBS
}
