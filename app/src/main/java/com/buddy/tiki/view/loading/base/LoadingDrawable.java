package com.buddy.tiki.view.loading.base;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;

public class LoadingDrawable extends Drawable implements Animatable {
    private final LoadingRenderer f3054a;
    private final Callback f3055b = new C05101(this);

    class C05101 implements Callback {
        final /* synthetic */ LoadingDrawable f3053a;

        C05101(LoadingDrawable this$0) {
            this.f3053a = this$0;
        }

        public void invalidateDrawable(Drawable d) {
            this.f3053a.invalidateSelf();
        }

        public void scheduleDrawable(Drawable d, Runnable what, long when) {
            this.f3053a.scheduleSelf(what, when);
        }

        public void unscheduleDrawable(Drawable d, Runnable what) {
            this.f3053a.unscheduleSelf(what);
        }
    }

    public LoadingDrawable(LoadingRenderer loadingRender) {
        this.f3054a = loadingRender;
        this.f3054a.m1829a(this.f3055b);
    }

    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.f3054a.m1828a(bounds);
    }

    public void draw(Canvas canvas) {
        if (!getBounds().isEmpty()) {
            this.f3054a.m1825a(canvas);
        }
    }

    public void setAlpha(int alpha) {
        this.f3054a.mo2237a(alpha);
    }

    public void setColorFilter(ColorFilter cf) {
        this.f3054a.mo2239a(cf);
    }

    public int getOpacity() {
        return -3;
    }

    public void start() {
        this.f3054a.m1830b();
    }

    public void stop() {
        this.f3054a.m1831c();
    }

    public boolean isRunning() {
        return this.f3054a.m1832d();
    }

    public int getIntrinsicHeight() {
        return (int) this.f3054a.f2930d;
    }

    public int getIntrinsicWidth() {
        return (int) this.f3054a.f2929c;
    }
}
