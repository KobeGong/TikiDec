package im.facechat.common.p044a;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.os.Handler;
import im.facechat.Rtc;
import im.facechat.common.p045b.C1639b;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: Foreground */
public class C1635a implements ActivityLifecycleCallbacks {
    public static final Class<?> f11207a = C1635a.class;
    private static C1635a f11208b;
    private volatile boolean f11209c = false;
    private volatile boolean f11210d = true;
    private Handler f11211e = new Handler();
    private List<C1634a> f11212f = new CopyOnWriteArrayList();
    private Runnable f11213g;

    /* compiled from: Foreground */
    class C16331 implements Runnable {
        final /* synthetic */ C1635a f11206a;

        C16331(C1635a c1635a) {
            this.f11206a = c1635a;
        }

        public void run() {
            if (this.f11206a.f11209c && this.f11206a.f11210d) {
                this.f11206a.f11209c = false;
                for (C1634a d : this.f11206a.f11212f) {
                    try {
                        d.mo4088d();
                    } catch (Throwable e) {
                        C1639b.m7917a(C1635a.f11207a, "Listener threw exception!", e);
                    }
                }
            }
        }
    }

    /* compiled from: Foreground */
    public interface C1634a {
        void mo4087c();

        void mo4088d();
    }

    public static C1635a m7889a(Application application) {
        if (f11208b == null) {
            synchronized (C1635a.class) {
                if (f11208b == null) {
                    f11208b = new C1635a();
                    application.registerActivityLifecycleCallbacks(f11208b);
                }
            }
        }
        return f11208b;
    }

    public static C1635a m7888a() {
        if (f11208b != null) {
            return f11208b;
        }
        throw new IllegalStateException("Foreground is not initialised - invoke at least once with parameterised init/get");
    }

    public void m7894a(C1634a c1634a) {
        this.f11212f.add(c1634a);
    }

    public void m7895b(C1634a c1634a) {
        this.f11212f.remove(c1634a);
    }

    public void onActivityResumed(Activity activity) {
        boolean z = false;
        this.f11210d = false;
        if (!this.f11209c) {
            z = true;
        }
        this.f11209c = true;
        if (this.f11213g != null) {
            this.f11211e.removeCallbacks(this.f11213g);
        }
        if (z) {
            for (C1634a c : this.f11212f) {
                try {
                    c.mo4087c();
                } catch (Throwable e) {
                    C1639b.m7917a(f11207a, "Listener threw exception!", e);
                }
            }
            Rtc.postCookies();
        }
    }

    public void onActivityPaused(Activity activity) {
        this.f11210d = true;
        if (this.f11213g != null) {
            this.f11211e.removeCallbacks(this.f11213g);
        }
        Handler handler = this.f11211e;
        Runnable c16331 = new C16331(this);
        this.f11213g = c16331;
        handler.postDelayed(c16331, 500);
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }
}
