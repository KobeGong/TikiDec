package com.buddy.tiki.view;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import com.buddy.tiki.log.TikiLog;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class DotTailTextView {
    private TikiLog f2623a = TikiLog.getInstance(DotTailTextView.class.getSimpleName());
    private String f2624b;
    private Handler f2625c = new Handler();
    private int f2626d = -1;
    private String[] f2627e = new String[]{".", "..", "..."};
    private TimerTask f2628f;
    private Timer f2629g;
    private boolean f2630h = false;
    private WeakReference<AppCompatTextView> f2631i;

    class C04941 extends TimerTask {
        final /* synthetic */ DotTailTextView f2622a;

        C04941(DotTailTextView this$0) {
            this.f2622a = this$0;
        }

        public void run() {
            synchronized (this.f2622a) {
                this.f2622a.f2626d = (this.f2622a.f2626d + 1) % this.f2622a.f2627e.length;
                this.f2622a.f2625c.post(DotTailTextView$1$$Lambda$1.lambdaFactory$(this));
            }
        }

        /* synthetic */ void m1681a() {
            try {
                if (this.f2622a.f2631i != null && this.f2622a.f2631i.get() != null) {
                    ((AppCompatTextView) this.f2622a.f2631i.get()).setText(this.f2622a.f2624b);
                    if (this.f2622a.f2627e == null || this.f2622a.f2626d >= this.f2622a.f2627e.length || this.f2622a.f2626d < 0) {
                        ((AppCompatTextView) this.f2622a.f2631i.get()).setText(this.f2622a.f2624b);
                    } else {
                        ((AppCompatTextView) this.f2622a.f2631i.get()).setText(this.f2622a.f2624b + this.f2622a.f2627e[this.f2622a.f2626d]);
                    }
                }
            } catch (NullPointerException e) {
            }
        }
    }

    public DotTailTextView(@NonNull AppCompatTextView textView, boolean autoStart) {
        this.f2631i = new WeakReference(textView);
        if (autoStart) {
            start();
        }
    }

    public synchronized void start() {
        if (!(this.f2631i == null || this.f2631i.get() == null)) {
            this.f2624b = ((AppCompatTextView) this.f2631i.get()).getText().toString();
            this.f2623a.m261d("start:" + this.f2630h);
            if (!this.f2630h) {
                this.f2626d = -1;
                if (this.f2628f != null) {
                    this.f2628f.cancel();
                    this.f2628f = null;
                }
                this.f2628f = new C04941(this);
                this.f2629g = new Timer();
                this.f2629g.scheduleAtFixedRate(this.f2628f, 0, 1000);
                this.f2630h = true;
            }
        }
    }

    public synchronized void stop() {
        this.f2623a.m261d("stop:" + this.f2630h);
        if (this.f2630h) {
            this.f2629g.cancel();
            this.f2629g = null;
            this.f2628f = null;
            this.f2626d = -1;
            this.f2630h = false;
        }
    }

    public boolean isRunning() {
        return this.f2630h;
    }
}
