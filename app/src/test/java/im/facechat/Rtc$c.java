package im.facechat;

import im.facechat.C1677t.C1630b;
import im.facechat.C1677t.C1731c;
import im.facechat.common.p045b.C1639b;
import java.util.concurrent.atomic.AtomicInteger;
import org.webrtc.IceCandidate;
import org.webrtc.SessionDescription;

class Rtc$c implements C1630b {
    AtomicInteger f11184a;

    private Rtc$c() {
        this.a = new AtomicInteger(0);
    }

    public void mo4047a(String str, String str2) {
        if (Rtc.b() != null) {
            Rtc.b().onLeaveRoom(str, str2);
        }
        C1639b.m7921c(Rtc.a(), "class ReceivingSignEvent :[onLeaveRoom] send leave");
        if (Rtc.d() != null) {
            Rtc.d().m7820d(1004);
        }
    }

    public void mo4050b(String str, String str2) {
        if (Rtc.b() != null) {
            Rtc.b().onJoinRoom(str, str2);
        }
    }

    public void mo4045a(int i, String str) {
        if (Rtc.b() != null) {
            Rtc.b().onError(i, str);
        }
        FacechatBroadcastManager.m7738a(FacechatBroadcastManager.ACTION_FAIL, i, str);
    }

    public void mo4049a(boolean z, SessionDescription sessionDescription) {
        if (Rtc.g() != null) {
            Rtc.g().m8191a(sessionDescription);
            if (!z) {
                Rtc.g().m8199d();
            }
        }
    }

    public void mo4048a(IceCandidate iceCandidate) {
        if (Rtc.g() != null) {
            Rtc.g().m8190a(iceCandidate);
        }
    }

    public void mo4051c(String str, String str2) {
        if (Rtc.b() != null) {
            Rtc.b().onRoomMessage(str, str2);
        }
    }

    public void mo4046a(C1731c c1731c) {
        Rtc.a(c1731c);
        if (Rtc.g() != null) {
            C1639b.m7921c(Rtc.a(), c1731c.toString());
            Rtc.g().m8185a(c1731c.f11511d.f11506b);
            Rtc.g().m8193a(Rtc.h(), c1731c.f11511d.f11505a);
            if (c1731c.f11508a) {
                Rtc.g().m8198c();
            }
        }
        if (Rtc.b() != null) {
            Rtc.b().onRoomSession(c1731c.f11510c, c1731c.f11509b);
        }
    }

    public void mo4044a(int i) {
        C1639b.m7916a(Rtc.a(), "class ReceivingSignEvent :[onWebSocketState] " + i + " count=" + this.a.get());
        int i2 = this.a.get();
        if (i2 > 3) {
            this.a.set(i2 % 3);
        }
        if (i == -1) {
            this.a.incrementAndGet();
        } else if (i != 1) {
            this.a.set(0);
        }
        if (Rtc.b() == null) {
            return;
        }
        if (i != -1 || this.a.compareAndSet(3, 0)) {
            Rtc.b().onWebSocketState(i);
        }
    }

    public void mo4052d(String str, String str2) {
        C1639b.m7916a(Rtc.a(), "class ReceivingSignEvent :[onSystemMessage] event is null = " + (Rtc.b() == null));
        if (Rtc.b() != null) {
            Rtc.b().onSystemMessage(str, str2);
        }
    }
}
