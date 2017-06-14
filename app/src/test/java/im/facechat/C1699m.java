package im.facechat;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import im.facechat.common.p045b.C1639b;
import java.util.concurrent.Executor;

/* compiled from: LooperExecutor */
class C1699m extends Thread implements Executor {
    private static final Class<?> f11372a = C1699m.class;
    private final Object f11373b = new Object();
    private Handler f11374c = null;
    private boolean f11375d = false;
    private long f11376e;

    /* compiled from: LooperExecutor */
    class C16981 implements Runnable {
        final /* synthetic */ C1699m f11371a;

        C16981(C1699m c1699m) {
            this.f11371a = c1699m;
        }

        public void run() {
            Looper.myLooper().quit();
            C1639b.m7916a(C1699m.f11372a, "Looper thread finished.");
        }
    }

    C1699m() {
    }

    public void run() {
        Looper.prepare();
        synchronized (this.f11373b) {
            C1639b.m7916a(f11372a, "Looper thread started.");
            this.f11374c = new Handler();
            this.f11376e = Thread.currentThread().getId();
            this.f11373b.notify();
        }
        Looper.loop();
    }

    public synchronized void m8105a() {
        if (!this.f11375d) {
            C1639b.m7916a(f11372a, "requestStart");
            this.f11375d = true;
            this.f11374c = null;
            start();
            synchronized (this.f11373b) {
                while (this.f11374c == null) {
                    try {
                        this.f11373b.wait();
                    } catch (InterruptedException e) {
                        C1639b.m7921c(f11372a, "Can not start looper thread");
                        this.f11375d = false;
                    }
                }
            }
        }
    }

    public synchronized void m8106b() {
        if (this.f11375d) {
            this.f11375d = false;
            this.f11374c.post(new C16981(this));
        }
    }

    public synchronized void execute(@NonNull Runnable runnable) {
        if (!this.f11375d) {
            C1639b.m7919b(f11372a, "Running looper executor without calling requestStart()");
        } else if (Thread.currentThread().getId() == this.f11376e) {
            runnable.run();
        } else {
            this.f11374c.post(runnable);
        }
    }
}
