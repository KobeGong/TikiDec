package im.facechat;

import org.webrtc.IceCandidate;
import org.webrtc.PeerConnection.IceServer;
import org.webrtc.SessionDescription;

/* compiled from: RtcEvent */
interface C1677t {

    /* compiled from: RtcEvent */
    public interface C1630b {
        void mo4044a(int i);

        void mo4045a(int i, String str);

        void mo4046a(C1731c c1731c);

        void mo4047a(String str, String str2);

        void mo4048a(IceCandidate iceCandidate);

        void mo4049a(boolean z, SessionDescription sessionDescription);

        void mo4050b(String str, String str2);

        void mo4051c(String str, String str2);

        void mo4052d(String str, String str2);
    }

    /* compiled from: RtcEvent */
    public static class C1730a {
        public final IceServer f11505a;
        public final int f11506b;
        public final long f11507c;

        public C1730a(String str, String str2, String str3, int i, long j) {
            this.f11505a = new IceServer(str, str2, str3);
            this.f11506b = i;
            this.f11507c = j;
        }

        public String toString() {
            return "iceServer " + this.f11505a + " quality " + this.f11506b + " noIces " + this.f11507c;
        }
    }

    /* compiled from: RtcEvent */
    public static class C1731c {
        public final boolean f11508a;
        public final String f11509b;
        public final String f11510c;
        public final C1730a f11511d;

        public C1731c(boolean z, String str, String str2, C1730a c1730a) {
            this.f11508a = z;
            this.f11509b = str;
            this.f11510c = str2;
            this.f11511d = c1730a;
        }

        public String toString() {
            return "initiator " + this.f11508a + " session " + this.f11509b + " roomId " + this.f11510c + " parameters " + this.f11511d;
        }
    }
}
