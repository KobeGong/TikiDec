package com.buddy.tiki.view;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTextView {
    private Handler f2888a = new Handler();
    private long f2889b;
    private Timer f2890c;
    private WeakReference<AppCompatTextView> f2891d;

    class C04991 extends TimerTask {
        final /* synthetic */ TimerTextView f2887a;

        C04991(TimerTextView this$0) {
            this.f2887a = this$0;
        }

        public void run() {
            this.f2887a.f2888a.post(TimerTextView$1$$Lambda$1.lambdaFactory$(this));
        }

        /* synthetic */ void m1799a() {
            try {
                if (!(this.f2887a.f2891d == null || this.f2887a.f2891d.get() == null)) {
                    ((AppCompatTextView) this.f2887a.f2891d.get()).setText(String.format(Locale.getDefault(), "%02d:%02d", new Object[]{Integer.valueOf(this.f2887a.m1800a(this.f2887a.f2889b)), Integer.valueOf(this.f2887a.m1803b(this.f2887a.f2889b))}));
                }
                this.f2887a.f2889b = 1 + this.f2887a.f2889b;
            } catch (NullPointerException e) {
            }
        }
    }

    public TimerTextView(@NonNull AppCompatTextView textView) {
        this.f2891d = new WeakReference(textView);
    }

    public synchronized void start() {
        if (this.f2890c != null) {
            this.f2890c.cancel();
        }
        this.f2890c = new Timer("TimerTextView", true);
        this.f2890c.scheduleAtFixedRate(new C04991(this), 0, 1000);
    }

    public synchronized void stop() {
        if (this.f2890c != null) {
            this.f2890c.cancel();
        }
    }

    public synchronized long getElapseTimeInMills() {
        return this.f2889b * 1000;
    }

    private int m1800a(long time) {
        return (int) ((time / 60) % 60);
    }

    private int m1803b(long time) {
        return (int) (time % 60);
    }
}
