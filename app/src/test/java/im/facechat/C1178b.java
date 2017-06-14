package im.facechat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.preference.PreferenceManager;
import im.facechat.b.1;
import im.facechat.b.2;
import im.facechat.b.3;
import im.facechat.b.a;
import im.facechat.common.b.b;
import io.netty.handler.codec.rtsp.RtspHeaders.Values;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* compiled from: AppRTCAudioManager */
class C1178b {
    private static final Class<?> f9540a = C1178b.class;
    private final WeakReference<Context> f9541b;
    private final Runnable f9542c;
    private final a f9543d;
    private final String f9544e;
    private final Set<a> f9545f = new HashSet();
    private boolean f9546g = false;
    private AudioManager f9547h;
    private int f9548i = -2;
    private boolean f9549j = false;
    private boolean f9550k = false;
    private c f9551l = null;
    private a f9552m;
    private BroadcastReceiver f9553n;

    private C1178b(Context context, Runnable runnable) {
        this.f9541b = new WeakReference(context);
        this.f9542c = runnable;
        this.f9547h = (AudioManager) context.getSystemService("audio");
        PreferenceManager.getDefaultSharedPreferences(context);
        this.f9544e = "false";
        if (this.f9544e.equals("false")) {
            this.f9543d = a.c;
        } else {
            this.f9543d = a.a;
        }
        this.f9551l = c.a(context, new 1(this));
        d.a(f9540a);
    }

    public static C1178b m6089a(Context context, Runnable runnable) {
        return new C1178b(context, runnable);
    }

    private void m6097d() {
        if (!this.f9544e.equals("auto") || this.f9545f.size() != 2 || !this.f9545f.contains(a.c) || !this.f9545f.contains(a.a)) {
            return;
        }
        if (this.f9551l.c()) {
            m6104a(a.c);
        } else {
            m6104a(a.a);
        }
    }

    public void m6103a() {
        b.a(f9540a, "init");
        if (!this.f9546g) {
            this.f9548i = this.f9547h.getMode();
            this.f9549j = this.f9547h.isSpeakerphoneOn();
            this.f9550k = this.f9547h.isMicrophoneMute();
            this.f9547h.requestAudioFocus(null, 0, 2);
            this.f9547h.setMode(3);
            m6094b(false);
            m6096c(m6101h());
            m6098e();
            this.f9546g = true;
        }
    }

    public void m6105b() {
        b.a(f9540a, Values.CLOSE);
        if (this.f9546g) {
            m6099f();
            m6092a(this.f9549j);
            m6094b(this.f9550k);
            this.f9547h.setMode(this.f9548i);
            this.f9547h.abandonAudioFocus(null);
            if (this.f9551l != null) {
                this.f9551l.b();
                this.f9551l = null;
            }
            this.f9546g = false;
        }
    }

    public void m6104a(a aVar) {
        b.a(f9540a, "setAudioDevice(device=" + aVar + ")");
        switch (3.a[aVar.ordinal()]) {
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                m6092a(true);
                this.f9552m = a.a;
                break;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                m6092a(false);
                this.f9552m = a.c;
                break;
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                m6092a(false);
                this.f9552m = a.b;
                break;
            default:
                b.c(f9540a, "Invalid audio device selection");
                break;
        }
        m6102i();
    }

    private void m6098e() {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.HEADSET_PLUG");
        this.f9553n = new 2(this);
        if (this.f9541b != null && this.f9541b.get() != null) {
            ((Context) this.f9541b.get()).registerReceiver(this.f9553n, intentFilter);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m6099f() {
        /*
        r4 = this;
        r3 = 0;
        r0 = r4.f9541b;	 Catch:{ IllegalArgumentException -> 0x001d }
        if (r0 == 0) goto L_0x001a;
    L_0x0005:
        r0 = r4.f9541b;	 Catch:{ IllegalArgumentException -> 0x001d }
        r0 = r0.get();	 Catch:{ IllegalArgumentException -> 0x001d }
        if (r0 == 0) goto L_0x001a;
    L_0x000d:
        r0 = r4.f9541b;	 Catch:{ IllegalArgumentException -> 0x001d }
        r0 = r0.get();	 Catch:{ IllegalArgumentException -> 0x001d }
        r0 = (android.content.Context) r0;	 Catch:{ IllegalArgumentException -> 0x001d }
        r1 = r4.f9553n;	 Catch:{ IllegalArgumentException -> 0x001d }
        r0.unregisterReceiver(r1);	 Catch:{ IllegalArgumentException -> 0x001d }
    L_0x001a:
        r4.f9553n = r3;
    L_0x001c:
        return;
    L_0x001d:
        r0 = move-exception;
        r1 = f9540a;	 Catch:{ all -> 0x0028 }
        r2 = "already unregister ";
        im.facechat.common.b.b.a(r1, r2, r0);	 Catch:{ all -> 0x0028 }
        r4.f9553n = r3;
        goto L_0x001c;
    L_0x0028:
        r0 = move-exception;
        r4.f9553n = r3;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: im.facechat.b.f():void");
    }

    private void m6092a(boolean z) {
        if (this.f9547h.isSpeakerphoneOn() != z) {
            this.f9547h.setSpeakerphoneOn(z);
        }
    }

    private void m6094b(boolean z) {
        if (this.f9547h.isMicrophoneMute() != z) {
            this.f9547h.setMicrophoneMute(z);
        }
    }

    private boolean m6100g() {
        return (this.f9541b == null || this.f9541b.get() == null || !((Context) this.f9541b.get()).getPackageManager().hasSystemFeature("android.hardware.telephony")) ? false : true;
    }

    @Deprecated
    private boolean m6101h() {
        return this.f9547h.isWiredHeadsetOn();
    }

    private void m6096c(boolean z) {
        this.f9545f.clear();
        if (z) {
            this.f9545f.add(a.b);
        } else {
            this.f9545f.add(a.a);
            if (m6100g()) {
                this.f9545f.add(a.c);
            }
        }
        b.a(f9540a, "audioDevices: " + this.f9545f);
        if (z) {
            m6104a(a.b);
        } else {
            m6104a(this.f9543d);
        }
    }

    private void m6102i() {
        boolean z = true;
        b.a(f9540a, "onAudioManagerChangedState: devices=" + this.f9545f + ", selected=" + this.f9552m);
        if (this.f9545f.size() == 2) {
            if (!(this.f9545f.contains(a.c) && this.f9545f.contains(a.a))) {
                z = false;
            }
            d.a(z);
            this.f9551l.a();
        } else if (this.f9545f.size() == 1) {
            this.f9551l.b();
        } else {
            b.c(f9540a, "Invalid device list");
        }
        if (this.f9542c != null) {
            this.f9542c.run();
        }
    }
}
