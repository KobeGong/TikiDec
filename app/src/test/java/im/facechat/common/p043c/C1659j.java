package im.facechat.common.p043c;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;

/* compiled from: ThreadHolder */
public class C1659j {
    private final Handler f11269a;

    /* compiled from: ThreadHolder */
    private static class C1658a {
        private static final C1659j f11268a = new C1659j();
    }

    private C1659j() {
        HandlerThread handlerThread = new HandlerThread("FacechatThreadHolder");
        handlerThread.start();
        this.f11269a = new Handler(handlerThread.getLooper());
    }

    public static C1659j m7994a() {
        return C1658a.f11268a;
    }

    public void m7995a(@NonNull Runnable runnable) {
        this.f11269a.post(runnable);
    }
}
