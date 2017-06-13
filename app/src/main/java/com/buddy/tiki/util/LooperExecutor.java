package com.buddy.tiki.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.buddy.tiki.log.TikiLog;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;

public class LooperExecutor extends Thread implements Executor {
    private final Object f2412a = new Object();
    private final List<Runnable> f2413b = new LinkedList();
    private Handler f2414c = null;
    private boolean f2415d = false;
    private long f2416e;
    private TikiLog f2417f = TikiLog.getInstance("LooperExecutor");

    public void run() {
        Looper.prepare();
        synchronized (this.f2412a) {
            this.f2417f.m261d("Looper thread started.");
            this.f2414c = new Handler();
            this.f2416e = Thread.currentThread().getId();
            this.f2412a.notify();
        }
        Looper.loop();
    }

    public synchronized void requestStart() {
        if (!this.f2415d) {
            this.f2415d = true;
            this.f2414c = null;
            start();
            synchronized (this.f2412a) {
                while (this.f2414c == null) {
                    try {
                        this.f2412a.wait();
                    } catch (InterruptedException e) {
                        this.f2417f.m263e("Can not start looper thread");
                        this.f2415d = false;
                    }
                }
            }
        }
    }

    public synchronized void requestStop() {
        if (this.f2415d) {
            this.f2415d = false;
            this.f2414c.post(LooperExecutor$$Lambda$1.lambdaFactory$(this));
        }
    }

    /* synthetic */ void m1552a() {
        Looper.myLooper().quit();
        this.f2417f.m261d("Looper thread finished.");
    }

    public boolean checkOnLooperThread() {
        return Thread.currentThread().getId() == this.f2416e;
    }

    public synchronized void scheduleAtFixedRate(final Runnable command, final long periodMillis) {
        if (this.f2415d) {
            Runnable runnable = new Runnable(this) {
                final /* synthetic */ LooperExecutor f2411c;

                public void run() {
                    if (this.f2411c.f2415d) {
                        command.run();
                        if (!this.f2411c.f2414c.postDelayed(this, periodMillis)) {
                            this.f2411c.f2417f.m263e("Failed to post a delayed runnable in the chain.");
                        }
                    }
                }
            };
            this.f2413b.add(runnable);
            if (!this.f2414c.postDelayed(runnable, periodMillis)) {
                this.f2417f.m263e("Failed to post a delayed runnable.");
            }
        } else {
            this.f2417f.m269w("Trying to schedule task for non running executor");
        }
    }

    public synchronized void cancelScheduledTasks() {
        if (this.f2415d) {
            for (Runnable r : this.f2413b) {
                this.f2414c.removeCallbacks(r);
            }
            this.f2413b.clear();
        } else {
            this.f2417f.m269w("Trying to cancel schedule tasks for non running executor");
        }
    }

    public synchronized void execute(@NonNull Runnable runnable) {
        execute(runnable, 0);
    }

    public synchronized void execute(@NonNull Runnable runnable, int delay) {
        if (delay < 0) {
            delay = 0;
        }
        if (!this.f2415d) {
            this.f2417f.m269w("Running looper executor without calling requestStart()");
        } else if (Thread.currentThread().getId() == this.f2416e) {
            runnable.run();
        } else {
            this.f2414c.postDelayed(runnable, (long) delay);
        }
    }
}
