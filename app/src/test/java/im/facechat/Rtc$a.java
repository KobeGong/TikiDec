package im.facechat;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import im.facechat.C1725o.C1619b;
import im.facechat.common.p043c.C1643b;
import im.facechat.common.p043c.C1643b.C1642b;
import im.facechat.common.p045b.C1639b;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.webrtc.IceCandidate;
import org.webrtc.SessionDescription;
import org.webrtc.StatsReport;

class Rtc$a implements C1619b {
    private final Handler f11162a;
    private Timer f11163b;
    private volatile C1642b f11164c;
    private boolean f11165d;

    class C16171 extends TimerTask {
        final /* synthetic */ Rtc$a f11160a;

        C16171(Rtc$a rtc$a) {
            this.f11160a = rtc$a;
        }

        public void run() {
            if (Rtc.c() != null && this.f11160a.c != null) {
                Rtc.c().m8049c();
            }
        }
    }

    private static class C1618a {
        private static final Rtc$a f11161a = new Rtc$a(null);
    }

    private Rtc$a() {
        this.c = null;
        this.d = false;
        HandlerThread handlerThread = new HandlerThread("FCPeerConnectionEvent");
        handlerThread.start();
        this.a = new Handler(handlerThread.getLooper());
    }

    public static Rtc$a m7765f() {
        return C1618a.f11161a;
    }

    public void m7778g() {
        this.a.getLooper().quitSafely();
    }

    public void mo4033a(SessionDescription sessionDescription) {
        if (Rtc.i() != null && Rtc.c() != null) {
            if (Rtc.i().f11508a) {
                Rtc.c().m8046a(sessionDescription);
            } else {
                Rtc.c().m8048b(sessionDescription);
            }
        }
    }

    public void mo4032a(IceCandidate iceCandidate) {
        if (Rtc.c() != null) {
            Rtc.c().m8045a(iceCandidate);
        }
    }

    public void mo4030a() {
        C1639b.m7916a(Rtc.a(), "class FCPeerConnectionEvent :[onIceConnected] icConnected is " + this.d);
        if (!this.d) {
            this.d = true;
            m7767j();
            if (Rtc.d() != null) {
                Rtc.d().m7820d(1003);
            }
        }
    }

    public void mo4036b() {
        if (Rtc.d() != null) {
            Rtc.d().m7820d(1006);
        }
    }

    public void mo4037c() {
    }

    public void mo4038d() {
        this.d = false;
        m7766i();
        C1639b.m7921c(Rtc.a(), "class FCPeerConnectionEvent :[onIceDisconnected] send leave");
        if (Rtc.d() != null) {
            Rtc.d().m7820d(1004);
        }
    }

    public void mo4039e() {
        m7766i();
        C1639b.m7921c(Rtc.a(), "class FCPeerConnectionEvent :[onPeerConnectionClosed] send leave");
        if (Rtc.d() != null) {
            Rtc.d().m7820d(1004);
        }
    }

    public void mo4035a(StatsReport[] statsReportArr) {
        C1642b a = C1643b.m7938a(statsReportArr);
        if (a != null) {
            this.c = C1643b.m7937a(this.c, a);
        }
    }

    public void mo4031a(String str, boolean z) {
        C1639b.m7921c(Rtc.a(), "class FCPeerConnectionEvent :[onPeerConnectionError] " + str);
        if (Rtc.b() != null) {
            Rtc.b().onError(405, str);
        }
        FacechatBroadcastManager.m7738a(FacechatBroadcastManager.ACTION_FAIL, 405, str);
        if (z && Rtc.d() != null) {
            Rtc.d().m7820d(1004);
        }
    }

    public void mo4034a(IceCandidate[] iceCandidateArr) {
    }

    @Nullable
    public Map<String, Object> m7779h() {
        return this.c == null ? null : this.c.m7934a();
    }

    private void m7766i() {
        C1639b.m7921c(Rtc.a(), "class FCPeerConnectionEvent :[cancelTimer]");
        if (this.b != null) {
            this.b.purge();
            this.b.cancel();
            this.b = null;
        }
    }

    private void m7767j() {
        m7766i();
        this.b = new Timer(true);
        this.b.schedule(new C16171(this), 15000, 15000);
    }
}
