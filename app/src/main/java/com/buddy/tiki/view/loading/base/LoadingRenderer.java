package com.buddy.tiki.view.loading.base;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable.Callback;
import android.view.animation.LinearInterpolator;
import com.buddy.tiki.util.DisplayUtil;

public abstract class LoadingRenderer {
    protected final Rect f2927a = new Rect();
    protected long f2928b;
    protected float f2929c;
    protected float f2930d;
    private Callback f2931e;
    private final AnimatorUpdateListener f2932f = new C05111(this);
    private ValueAnimator f2933g;

    class C05111 implements AnimatorUpdateListener {
        final /* synthetic */ LoadingRenderer f3056a;

        C05111(LoadingRenderer this$0) {
            this.f3056a = this$0;
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            this.f3056a.mo2236a(((Float) animation.getAnimatedValue()).floatValue());
            this.f3056a.m1821f();
        }
    }

    protected abstract void mo2235a();

    protected abstract void mo2236a(float f);

    protected abstract void mo2237a(int i);

    protected abstract void mo2239a(ColorFilter colorFilter);

    public LoadingRenderer(Context context) {
        m1818a(context);
        m1820e();
    }

    @Deprecated
    protected void mo2238a(Canvas canvas, Rect bounds) {
    }

    protected void m1825a(Canvas canvas) {
        mo2238a(canvas, this.f2927a);
    }

    void m1830b() {
        mo2235a();
        this.f2933g.addUpdateListener(this.f2932f);
        this.f2933g.setRepeatCount(-1);
        this.f2933g.setDuration(this.f2928b);
        this.f2933g.start();
    }

    void m1831c() {
        this.f2933g.removeUpdateListener(this.f2932f);
        this.f2933g.setRepeatCount(0);
        this.f2933g.setDuration(0);
        this.f2933g.end();
    }

    boolean m1832d() {
        return this.f2933g.isRunning();
    }

    void m1829a(Callback callback) {
        this.f2931e = callback;
    }

    void m1828a(Rect bounds) {
        this.f2927a.set(bounds);
    }

    private void m1818a(Context context) {
        this.f2929c = (float) DisplayUtil.dip2px(56.0f);
        this.f2930d = (float) DisplayUtil.dip2px(56.0f);
        this.f2928b = 1333;
    }

    private void m1820e() {
        this.f2933g = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.f2933g.setRepeatCount(-1);
        this.f2933g.setRepeatMode(1);
        this.f2933g.setDuration(this.f2928b);
        this.f2933g.setInterpolator(new LinearInterpolator());
        this.f2933g.addUpdateListener(this.f2932f);
    }

    private void m1821f() {
        this.f2931e.invalidateDrawable(null);
    }
}
