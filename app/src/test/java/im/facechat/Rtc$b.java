package im.facechat;

import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.sina.weibo.sdk.utils.AidTask;
import im.facechat.common.p043c.C1621h;
import im.facechat.common.p043c.C1629i;
import im.facechat.common.p045b.C1639b;
import im.facechat.view.FCSurfaceView;
import java.util.Timer;
import java.util.TimerTask;
import tv.danmaku.ijk.media.player.BuildConfig;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

protected final class Rtc$b extends C1629i {
    private Rtc$d f11176a;
    private Timer f11177b;
    private C1625c f11178c;
    private C1626d f11179d;
    private C1628e f11180e;
    private C1624b f11181f;
    private C1622a f11182g;
    private volatile int f11183h;

    class C1622a extends C1621h {
        final /* synthetic */ Rtc$b f11166a;

        C1622a(Rtc$b rtc$b) {
            this.f11166a = rtc$b;
        }

        public boolean mo4042a(Message message) {
            switch (message.what) {
                case AidTask.WHAT_LOAD_AID_ERR /*1002*/:
                    this.f11166a.m7835a(new Rtc$d(BuildConfig.VERSION_NAME, "timeout"), true);
                    this.f11166a.m7805a(this.f11166a.d);
                    break;
                case 1004:
                    C1639b.m7916a(Rtc.a(), "i want to leave " + message.obj);
                    this.f11166a.a = (Rtc$d) message.obj;
                    this.f11166a.m7835a(this.f11166a.a, true);
                    this.f11166a.m7805a(this.f11166a.d);
                    break;
                case 1005:
                    this.f11166a.m7835a(null, true);
                    this.f11166a.m7805a(this.f11166a.c);
                    this.f11166a.m7847k();
                    break;
                case 1006:
                    if (Rtc.b() != null) {
                        Rtc.b().onStateChange(14, null);
                        break;
                    }
                    break;
                default:
                    C1639b.m7919b(Rtc.a(), "class ConnectedState :[processMessage] " + message.what);
                    break;
            }
            return true;
        }

        public void mo4041a() {
            this.f11166a.m7846j();
            this.f11166a.h = 12;
            if (Rtc.b() != null) {
                Rtc.b().onStateChange(this.f11166a.h, this.f11166a.a == null ? null : this.f11166a.a.a);
            }
            C1639b.m7916a(Rtc.a(), "class ConnectedState :[enter]");
        }
    }

    class C1624b extends C1621h {
        final /* synthetic */ Rtc$b f11168a;

        class C16231 extends TimerTask {
            final /* synthetic */ C1624b f11167a;

            C16231(C1624b c1624b) {
                this.f11167a = c1624b;
            }

            public void run() {
                C1639b.m7919b(Rtc.a(), "class ConnectingState :[run] send leave");
                this.f11167a.f11168a.m7820d((int) AidTask.WHAT_LOAD_AID_ERR);
            }
        }

        C1624b(Rtc$b rtc$b) {
            this.f11168a = rtc$b;
        }

        public boolean mo4042a(Message message) {
            switch (message.what) {
                case AidTask.WHAT_LOAD_AID_ERR /*1002*/:
                    this.f11168a.m7835a(new Rtc$d(BuildConfig.VERSION_NAME, "timeout"), false);
                    this.f11168a.m7805a(this.f11168a.d);
                    break;
                case 1003:
                    this.f11168a.m7805a(this.f11168a.g);
                    break;
                case 1004:
                    this.f11168a.m7835a(null, false);
                    this.f11168a.m7805a(this.f11168a.d);
                    break;
                case 1005:
                    this.f11168a.m7835a(null, false);
                    this.f11168a.m7805a(this.f11168a.c);
                    this.f11168a.m7847k();
                    break;
                default:
                    C1639b.m7921c(Rtc.a(), "class ConnectingState :[processMessage]" + message.what);
                    break;
            }
            return true;
        }

        public void mo4041a() {
            this.f11168a.m7836a(new C16231(this), 12500);
            this.f11168a.h = 11;
            if (Rtc.b() != null) {
                Rtc.b().onStateChange(this.f11168a.h, this.f11168a.a == null ? null : this.f11168a.a.a);
            }
            C1639b.m7916a(Rtc.a(), "class ConnectingState :[enter]");
        }

        public void mo4043b() {
            this.f11168a.m7846j();
        }
    }

    class C1625c extends C1621h {
        final /* synthetic */ Rtc$b f11169a;

        C1625c(Rtc$b rtc$b) {
            this.f11169a = rtc$b;
        }

        public boolean mo4042a(Message message) {
            switch (message.what) {
                case IjkMediaMeta.FF_PROFILE_H264_HIGH /*100*/:
                    this.f11169a.m7805a(this.f11169a.d);
                    break;
            }
            return true;
        }

        public void mo4041a() {
            this.f11169a.h = 0;
            if (Rtc.b() != null) {
                Rtc.b().onStateChange(this.f11169a.h, null);
            }
            this.f11169a.a = null;
            C1639b.m7921c(Rtc.a(), "class DefaultState :[enter]");
        }
    }

    class C1626d extends C1621h {
        final /* synthetic */ Rtc$b f11170a;

        C1626d(Rtc$b rtc$b) {
            this.f11170a = rtc$b;
        }

        public boolean mo4042a(Message message) {
            switch (message.what) {
                case IjkMediaCodecInfo.RANK_MAX /*1000*/:
                    this.f11170a.a = (Rtc$d) message.obj;
                    this.f11170a.m7834a(this.f11170a.a);
                    this.f11170a.m7805a(this.f11170a.e);
                    break;
                case 1005:
                    this.f11170a.m7805a(this.f11170a.c);
                    this.f11170a.m7847k();
                    break;
                default:
                    C1639b.m7921c(Rtc.a(), "class StandbyState :[processMessage]" + message.what);
                    break;
            }
            return true;
        }

        public void mo4041a() {
            C1639b.m7916a(Rtc.a(), "class StandbyState :[enter] " + this.f11170a.h);
            if (this.f11170a.h == 0) {
                this.f11170a.h = 1;
                if (Rtc.b() != null) {
                    Rtc.b().onStateChange(1, null);
                }
            } else if (!(this.f11170a.h == 1 || this.f11170a.h == 13)) {
                if (Rtc.b() != null) {
                    Rtc.b().onStateChange(13, this.f11170a.a == null ? null : this.f11170a.a.a);
                }
                this.f11170a.h = 13;
            }
            this.f11170a.a = null;
        }
    }

    class C1628e extends C1621h {
        final /* synthetic */ Rtc$b f11172a;

        class C16271 extends TimerTask {
            final /* synthetic */ C1628e f11171a;

            C16271(C1628e c1628e) {
                this.f11171a = c1628e;
            }

            public void run() {
                C1639b.m7919b(Rtc.a(), "class WaitingJoinState :[run] send leave");
                this.f11171a.f11172a.m7820d((int) AidTask.WHAT_LOAD_AID_ERR);
            }
        }

        C1628e(Rtc$b rtc$b) {
            this.f11172a = rtc$b;
        }

        public boolean mo4042a(Message message) {
            switch (message.what) {
                case AidTask.WHAT_LOAD_AID_SUC /*1001*/:
                    this.f11172a.m7805a(this.f11172a.f);
                    break;
                case AidTask.WHAT_LOAD_AID_ERR /*1002*/:
                    this.f11172a.m7835a(new Rtc$d(BuildConfig.VERSION_NAME, "timeout"), false);
                    this.f11172a.m7805a(this.f11172a.d);
                    break;
                case 1004:
                    this.f11172a.m7835a(null, false);
                    this.f11172a.m7805a(this.f11172a.d);
                    break;
                case 1005:
                    this.f11172a.m7835a(null, false);
                    this.f11172a.m7805a(this.f11172a.c);
                    this.f11172a.m7847k();
                    break;
                default:
                    C1639b.m7921c(Rtc.a(), "class WaitingJoinState :[processMessage]" + message.what);
                    break;
            }
            return true;
        }

        public void mo4041a() {
            this.f11172a.m7836a(new C16271(this), 8000);
            this.f11172a.h = 10;
            if (Rtc.b() != null) {
                Rtc.b().onStateChange(this.f11172a.h, this.f11172a.a == null ? null : this.f11172a.a.a);
            }
            C1639b.m7916a(Rtc.a(), "class WaitingJoinState :[enter]");
        }

        public void mo4043b() {
            this.f11172a.m7846j();
        }
    }

    private Rtc$b() {
        super("FacechatStateMachine");
        this.c = new C1625c(this);
        this.d = new C1626d(this);
        this.e = new C1628e(this);
        this.f = new C1624b(this);
        this.g = new C1622a(this);
        m7806a(this.c);
        m7807a(this.d, this.c);
        m7807a(this.e, this.d);
        m7807a(this.f, this.e);
        m7807a(this.g, this.f);
        m7814b(this.c);
        m7828i();
    }

    public int m7848a() {
        return this.h;
    }

    private void m7846j() {
        if (this.b != null) {
            this.b.purge();
            this.b.cancel();
            this.b = null;
        }
    }

    private void m7836a(@NonNull TimerTask timerTask, long j) {
        m7846j();
        this.b = new Timer();
        this.b.schedule(timerTask, j);
    }

    private void m7835a(@Nullable Rtc$d rtc$d, boolean z) {
        Rtc$a.m7765f().m7766i();
        Rtc$a.m7765f().d = false;
        if (Rtc.c() != null) {
            Rtc.c().m8043a(rtc$d == null ? BuildConfig.VERSION_NAME : rtc$d.a, Rtc$a.m7765f().m7779h(), rtc$d == null ? BuildConfig.VERSION_NAME : rtc$d.b, z);
            Rtc$a.m7765f().c = null;
        }
    }

    private void m7834a(Rtc$d rtc$d) {
        if (Rtc.c() != null) {
            Rtc.c().m8042a(rtc$d.a, rtc$d.b);
        }
    }

    private void m7847k() {
        Rtc$a.m7765f().m7778g();
        if (Rtc.c() != null) {
            Rtc.c().m8047b();
            Rtc.a(null);
        }
        Rtc.a(null);
        if (Rtc.h() != null) {
            ((FCSurfaceView) Rtc.h()).release();
            Rtc.a(null);
        }
        if (Rtc.j() != null) {
            ((FCSurfaceView) Rtc.j()).release();
            Rtc.b(null);
        }
        Rtc.a(null);
        Rtc.e().set(false);
        m7827h();
    }
}
