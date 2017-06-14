package im.facechat;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.v4.view.PointerIconCompat;
import android.support.v7.recyclerview.BuildConfig;
import android.text.TextUtils;
import com.igexin.download.Downloads;
import im.facechat.common.a.a;
import im.facechat.common.a.b;
import im.facechat.common.c.f;
import im.facechat.common.protocol.FacechatCapturer;
import im.facechat.t.c;
import im.facechat.view.FCSurfaceView;
import java.util.concurrent.atomic.AtomicBoolean;
import org.bytedeco.javacpp.swscale;
import org.webrtc.EglBase;
import org.webrtc.GlRectDrawer;
import org.webrtc.PeerConnection.IceServer;
import org.webrtc.RendererCommon.GlDrawer;
import org.webrtc.VideoRenderer.Callbacks;

public final class Rtc {
    private static final Class<?> f9524a = Rtc.class;
    private static AtomicBoolean f9525b = new AtomicBoolean(false);
    private static AtomicBoolean f9526c = new AtomicBoolean(false);
    private static h f9527d;
    private static o f9528e;
    private static C1178b f9529f;
    private static EglBase f9530g;
    private static c f9531h;
    private static Callbacks f9532i;
    private static Callbacks f9533j;
    private static FCRoomEvent f9534k;
    private static b f9535l;

    public interface FCRoomEvent {
        @Deprecated
        void onError(int i, String str);

        void onJoinRoom(String str, String str2);

        void onLeaveRoom(String str, String str2);

        void onRoomMessage(String str, String str2);

        void onRoomSession(String str, String str2);

        void onStateChange(int i, @Nullable String str);

        void onSystemMessage(String str, String str2);

        void onWebSocketState(int i);
    }

    private Rtc() {
    }

    public static void preInitialize(@NonNull Application application) {
        im.facechat.common.c.c.a().a(application.getApplicationContext());
        a.a(application);
    }

    public static void initialize(@NonNull Application application, String str, boolean z) {
        m6075a(application, str, z, true);
    }

    public static void enableLog(boolean z) {
        im.facechat.common.b.a.a().a(z ? 3 : 7);
    }

    private static void m6075a(@NonNull Application application, String str, boolean z, boolean z2) {
        if (z2) {
            postCookies();
        }
        if (!f9525b.get()) {
            if (TextUtils.isEmpty(str)) {
                if (f9534k != null) {
                    f9534k.onError(403, "\u65e0\u6548\u5230appId");
                }
                FacechatBroadcastManager.a("im.facechat.ACTION_FAIL", 403, "\u65e0\u6548\u5230appId");
                return;
            }
            q.f(str);
            b.a(application);
            Context applicationContext = application.getApplicationContext();
            f9535l = new b(null);
            f9535l.i();
            m6077a(applicationContext, str, z);
        }
    }

    private static void m6076a(@NonNull Application application, boolean z) {
        if (!f9525b.get()) {
            try {
                ApplicationInfo applicationInfo = application.getApplicationContext().getPackageManager().getApplicationInfo(application.getApplicationContext().getPackageName(), swscale.SWS_GAUSS);
                m6075a(application, applicationInfo.metaData.getString("FACECHAT_APP_ID"), applicationInfo.metaData.getBoolean("FACECHAT_WEBSOCKER_ENABLE", false), z);
            } catch (Throwable e) {
                FacechatBroadcastManager.a("im.facechat.ACTION_FAIL", 403, "\u65e0\u6548\u5230appId");
                im.facechat.common.b.b.a(f9524a, " not found", e);
            }
        }
    }

    public static void initialize(@NonNull Application application) {
        m6076a(application, true);
    }

    public static boolean hasBeenInitialized() {
        return f9525b.get();
    }

    public static void registerRoomEvent(@NonNull FCRoomEvent fCRoomEvent) {
        f9534k = fCRoomEvent;
    }

    public static void unregisterRoomEvent(@NonNull FCRoomEvent fCRoomEvent) {
        f9534k = null;
    }

    @Nullable
    public static String getToken() throws NullPointerException {
        return j.a().b().a().c();
    }

    public static void retryConnecting(Application application) {
        if (!f9525b.get()) {
            m6076a(application, false);
        } else if (f9527d != null) {
            f9527d.a();
        }
    }

    private static void m6077a(@NonNull Context context, @NonNull String str, boolean z) {
        FCBaseParams a = p.a(context, str);
        Object a2 = f.a(context);
        if (TextUtils.isEmpty(a2)) {
            if (f9534k != null) {
                f9534k.onError(PointerIconCompat.TYPE_CONTEXT_MENU, "\u65e0\u6548\u7684apk\u5305\u4fe1\u606f");
            }
            FacechatBroadcastManager.a("im.facechat.ACTION_FAIL", PointerIconCompat.TYPE_CONTEXT_MENU, "\u65e0\u6548\u7684apk\u5305\u4fe1\u606f");
            return;
        }
        a.setBi(context.getPackageName());
        a.setBmd5(a2);
        l.a(context, new 2(context, a, new 1(context, a, z)));
    }

    public static void setApnsToken(@NonNull Context context, int i, @NonNull String str, @Nullable SimpleActionCallback simpleActionCallback) throws NullPointerException {
        if (f9525b.get()) {
            i b = j.a().b();
            n.a().a(context, b.b().appId, b.a().c(), i, str, new 3(simpleActionCallback));
            return;
        }
        FacechatBroadcastManager.a("im.facechat.ACTION_FAIL", Downloads.STATUS_BAD_REQUEST, "sdk\u521d\u59cb\u5316\u672a\u6210\u529f\uff0c\u8bf7\u91cd\u8bd5");
        if (f9534k != null) {
            f9534k.onError(Downloads.STATUS_BAD_REQUEST, "sdk\u521d\u59cb\u5316\u672a\u6210\u529f\uff0c\u8bf7\u91cd\u8bd5");
        }
    }

    public static void postCookies() {
        n.a().a(q.e(), q.a(), q.b());
    }

    @RequiresPermission(allOf = {"android.permission.CAMERA", "android.permission.RECORD_AUDIO"})
    public static <F extends FacechatCapturer, G extends GlDrawer> void openCamera(@NonNull Context context, FCSurfaceView fCSurfaceView, FCSurfaceView fCSurfaceView2, OnConstructCapturer<F> onConstructCapturer, @Nullable OnConstructGlDrawer<G> onConstructGlDrawer) {
        if (f9526c.compareAndSet(false, true)) {
            f9530g = EglBase.create(null, EglBase.CONFIG_PLAIN);
            GlDrawer glRectDrawer = new GlRectDrawer();
            GlDrawer glRectDrawer2 = onConstructGlDrawer == null ? new GlRectDrawer() : onConstructGlDrawer.newInstance();
            fCSurfaceView.init(f9530g.getEglBaseContext(), null, EglBase.CONFIG_PLAIN, glRectDrawer);
            fCSurfaceView.setMirror(true);
            fCSurfaceView2.init(f9530g.getEglBaseContext(), null, EglBase.CONFIG_PLAIN, glRectDrawer2);
            f9532i = fCSurfaceView2;
            f9533j = fCSurfaceView;
            f9528e = o.a();
            f9528e.a(onConstructCapturer);
            f9528e.a(context, f.a(context), a.f());
            f9528e.a(f9530g.getEglBaseContext(), fCSurfaceView, new IceServer(BuildConfig.VERSION_NAME));
        }
        if (f9529f == null) {
            f9529f = C1178b.m6089a(context, new 4());
            f9529f.m6103a();
        }
    }

    @RequiresPermission("android.permission.CAMERA")
    public static void switchCamera(@Nullable SimpleActionCallback simpleActionCallback) {
        if (f9526c.get()) {
            f9528e.a(new 5(simpleActionCallback));
        }
    }

    private static void m6088k() {
    }

    @CheckResult
    public static boolean joinRoom(String str, String str2) {
        if (f9527d == null) {
            if (f9534k != null) {
                f9534k.onError(Downloads.STATUS_BAD_REQUEST, "socket\u672a\u8fde\u63a5");
            }
            FacechatBroadcastManager.a("im.facechat.ACTION_FAIL", Downloads.STATUS_BAD_REQUEST, "socket\u672a\u8fde\u63a5");
            return false;
        }
        if (f9535l != null) {
            f9535l.b(PointerIconCompat.TYPE_DEFAULT, new d(str, str2));
        }
        return true;
    }

    @CheckResult
    public static boolean leaveRoom(String str, String str2) {
        if (f9527d == null) {
            if (f9534k != null) {
                f9534k.onError(Downloads.STATUS_BAD_REQUEST, "socket\u672a\u8fde\u63a5");
            }
            FacechatBroadcastManager.a("im.facechat.ACTION_FAIL", Downloads.STATUS_BAD_REQUEST, "socket\u672a\u8fde\u63a5");
            return false;
        }
        if (f9528e != null) {
            f9528e.b(f9532i, null);
        }
        im.facechat.common.b.b.c(f9524a, "class Rtc :[leaveRoom] send leave");
        if (f9535l != null) {
            f9535l.b(PointerIconCompat.TYPE_WAIT, new d(str, str2));
        }
        return true;
    }

    public static void shutdown() {
        if (f9535l != null) {
            f9535l.d(1005);
            f9535l = null;
        }
        if (f9528e != null) {
            f9528e.b();
            f9528e = null;
        }
        f9526c.set(false);
        if (f9529f != null) {
            f9529f.m6105b();
            f9529f = null;
        }
        if (f9530g != null) {
            f9530g.release();
            f9530g = null;
        }
    }

    @CheckResult
    public static boolean sendRoomMessage(String str) {
        if (f9527d == null) {
            if (f9534k != null) {
                f9534k.onError(Downloads.STATUS_BAD_REQUEST, "socket\u672a\u8fde\u63a5");
            }
            FacechatBroadcastManager.a("im.facechat.ACTION_FAIL", Downloads.STATUS_BAD_REQUEST, "socket\u672a\u8fde\u63a5");
            return false;
        }
        f9527d.a(str);
        return true;
    }

    @RequiresPermission("android.permission.CAMERA")
    public static <G extends GlDrawer> void onResume(@NonNull FCSurfaceView fCSurfaceView, @NonNull FCSurfaceView fCSurfaceView2, @Nullable OnConstructGlDrawer<G> onConstructGlDrawer) {
        if (f9528e != null) {
            if (!(f9532i == null || f9532i == fCSurfaceView || f9530g == null)) {
                ((FCSurfaceView) f9532i).release();
                fCSurfaceView.init(f9530g.getEglBaseContext(), null, EglBase.CONFIG_PLAIN, onConstructGlDrawer == null ? new GlRectDrawer() : onConstructGlDrawer.newInstance());
                f9532i = fCSurfaceView;
            }
            if (!(f9533j == null || f9533j == fCSurfaceView2 || f9530g == null)) {
                ((FCSurfaceView) f9533j).release();
                fCSurfaceView2.init(f9530g.getEglBaseContext(), null);
                f9533j = fCSurfaceView2;
            }
            f9528e.a(fCSurfaceView, fCSurfaceView2);
        }
    }

    @RequiresPermission("android.permission.CAMERA")
    public static void onPause() {
        im.facechat.common.b.b.c(f9524a, "class Rtc :[onPause] peerconnection null is " + (f9528e == null));
        if (f9528e != null) {
            f9528e.e();
        }
    }

    public static int getCurrentState() {
        if (f9535l != null) {
            return f9535l.a();
        }
        return 0;
    }
}
