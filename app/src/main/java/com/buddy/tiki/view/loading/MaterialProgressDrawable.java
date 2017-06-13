package com.buddy.tiki.view.loading;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import java.util.ArrayList;
import org.bytedeco.javacpp.avutil;

/* compiled from: LoadingView */
class MaterialProgressDrawable extends Drawable implements Animatable {
    private static final Interpolator f3030b = new LinearInterpolator();
    private static final Interpolator f3031c = new EndCurveInterpolator();
    private static final Interpolator f3032d = new StartCurveInterpolator();
    private static final Interpolator f3033e = new AccelerateDecelerateInterpolator();
    boolean f3034a;
    private final int[] f3035f = new int[]{ViewCompat.MEASURED_STATE_MASK};
    private final ArrayList<Animation> f3036g = new ArrayList();
    private final Ring f3037h;
    private final Callback f3038i = new C05071(this);
    private float f3039j;
    private Resources f3040k;
    private View f3041l;
    private Animation f3042m;
    private float f3043n;
    private double f3044o;
    private double f3045p;

    /* compiled from: LoadingView */
    class C05071 implements Callback {
        final /* synthetic */ MaterialProgressDrawable f3002a;

        C05071(MaterialProgressDrawable this$0) {
            this.f3002a = this$0;
        }

        public void invalidateDrawable(Drawable d) {
            this.f3002a.invalidateSelf();
        }

        public void scheduleDrawable(Drawable d, Runnable what, long when) {
            this.f3002a.scheduleSelf(what, when);
        }

        public void unscheduleDrawable(Drawable d, Runnable what) {
            this.f3002a.unscheduleSelf(what);
        }
    }

    /* compiled from: LoadingView */
    private static class EndCurveInterpolator extends AccelerateDecelerateInterpolator {
        private EndCurveInterpolator() {
        }

        public float getInterpolation(float input) {
            return super.getInterpolation(Math.max(0.0f, (input - 0.5f) * 2.0f));
        }
    }

    /* compiled from: LoadingView */
    private static class Ring {
        private final RectF f3007a = new RectF();
        private final Paint f3008b = new Paint();
        private final Paint f3009c = new Paint();
        private final Callback f3010d;
        private final Paint f3011e = new Paint();
        private float f3012f = 0.0f;
        private float f3013g = 0.0f;
        private float f3014h = 0.0f;
        private float f3015i = 5.0f;
        private float f3016j = 2.5f;
        private int[] f3017k;
        private int f3018l;
        private float f3019m;
        private float f3020n;
        private float f3021o;
        private boolean f3022p;
        private Path f3023q;
        private float f3024r;
        private double f3025s;
        private int f3026t;
        private int f3027u;
        private int f3028v;
        private int f3029w;

        public Ring(Callback callback) {
            this.f3010d = callback;
            this.f3008b.setStrokeCap(Cap.SQUARE);
            this.f3008b.setAntiAlias(true);
            this.f3008b.setStyle(Style.STROKE);
            this.f3009c.setStyle(Style.FILL);
            this.f3009c.setAntiAlias(true);
        }

        public void setBackgroundColor(int color) {
            this.f3029w = color;
        }

        public void setArrowDimensions(float width, float height) {
            this.f3026t = (int) width;
            this.f3027u = (int) height;
        }

        public void draw(Canvas c, Rect bounds) {
            RectF arcBounds = this.f3007a;
            arcBounds.set(bounds);
            arcBounds.inset(this.f3016j, this.f3016j);
            float startAngle = (this.f3012f + this.f3014h) * 360.0f;
            float sweepAngle = ((this.f3013g + this.f3014h) * 360.0f) - startAngle;
            this.f3008b.setColor(this.f3017k[this.f3018l]);
            c.drawArc(arcBounds, startAngle, sweepAngle, false, this.f3008b);
            m1886a(c, startAngle, sweepAngle, bounds);
            if (this.f3028v < avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK) {
                this.f3011e.setColor(this.f3029w);
                this.f3011e.setAlpha(255 - this.f3028v);
                c.drawCircle(bounds.exactCenterX() * 1.0f, bounds.exactCenterY() * 1.0f, ((float) bounds.width()) / 2.0f, this.f3011e);
            }
        }

        private void m1886a(Canvas c, float startAngle, float sweepAngle, Rect bounds) {
            if (this.f3022p) {
                if (this.f3023q == null) {
                    this.f3023q = new Path();
                    this.f3023q.setFillType(FillType.EVEN_ODD);
                } else {
                    this.f3023q.reset();
                }
                float inset = ((float) (((int) this.f3016j) / 2)) * this.f3024r;
                float x = (float) ((this.f3025s * Math.cos(0.0d)) + ((double) bounds.exactCenterX()));
                float y = (float) ((this.f3025s * Math.sin(0.0d)) + ((double) bounds.exactCenterY()));
                this.f3023q.moveTo(0.0f, 0.0f);
                this.f3023q.lineTo(((float) this.f3026t) * this.f3024r, 0.0f);
                this.f3023q.lineTo((((float) this.f3026t) * this.f3024r) / 2.0f, ((float) this.f3027u) * this.f3024r);
                this.f3023q.offset(x - inset, y);
                this.f3023q.close();
                this.f3009c.setColor(this.f3017k[this.f3018l]);
                c.rotate((startAngle + sweepAngle) - 5.0f, bounds.exactCenterX(), bounds.exactCenterY());
                c.drawPath(this.f3023q, this.f3009c);
            }
        }

        public void setColors(@NonNull int[] colors) {
            this.f3017k = colors;
            setColorIndex(0);
        }

        public void setColorIndex(int index) {
            this.f3018l = index;
        }

        public void goToNextColor() {
            this.f3018l = (this.f3018l + 1) % this.f3017k.length;
        }

        public void setColorFilter(ColorFilter filter) {
            this.f3008b.setColorFilter(filter);
            m1885a();
        }

        public int getAlpha() {
            return this.f3028v;
        }

        public void setAlpha(int alpha) {
            this.f3028v = alpha;
        }

        public float getStrokeWidth() {
            return this.f3015i;
        }

        public void setStrokeWidth(float strokeWidth) {
            this.f3015i = strokeWidth;
            this.f3008b.setStrokeWidth(strokeWidth);
            m1885a();
        }

        public float getStartTrim() {
            return this.f3012f;
        }

        public void setStartTrim(float startTrim) {
            this.f3012f = startTrim;
            m1885a();
        }

        public float getStartingStartTrim() {
            return this.f3019m;
        }

        public float getStartingEndTrim() {
            return this.f3020n;
        }

        public float getEndTrim() {
            return this.f3013g;
        }

        public void setEndTrim(float endTrim) {
            this.f3013g = endTrim;
            m1885a();
        }

        public float getRotation() {
            return this.f3014h;
        }

        public void setRotation(float rotation) {
            this.f3014h = rotation;
            m1885a();
        }

        public void setInsets(int width, int height) {
            float insets;
            float minEdge = (float) Math.min(width, height);
            if (this.f3025s <= 0.0d || minEdge < 0.0f) {
                insets = (float) Math.ceil((double) (this.f3015i / 2.0f));
            } else {
                insets = (float) (((double) (minEdge / 2.0f)) - this.f3025s);
            }
            this.f3016j = insets;
        }

        public float getInsets() {
            return this.f3016j;
        }

        public double getCenterRadius() {
            return this.f3025s;
        }

        public void setCenterRadius(double centerRadius) {
            this.f3025s = centerRadius;
        }

        public void setShowArrow(boolean show) {
            if (this.f3022p != show) {
                this.f3022p = show;
                m1885a();
            }
        }

        public void setArrowScale(float scale) {
            if (scale != this.f3024r) {
                this.f3024r = scale;
                m1885a();
            }
        }

        public float getStartingRotation() {
            return this.f3021o;
        }

        public void storeOriginals() {
            this.f3019m = this.f3012f;
            this.f3020n = this.f3013g;
            this.f3021o = this.f3014h;
        }

        public void resetOriginals() {
            this.f3019m = 0.0f;
            this.f3020n = 0.0f;
            this.f3021o = 0.0f;
            setStartTrim(0.0f);
            setEndTrim(0.0f);
            setRotation(0.0f);
        }

        private void m1885a() {
            this.f3010d.invalidateDrawable(null);
        }
    }

    /* compiled from: LoadingView */
    private static class StartCurveInterpolator extends AccelerateDecelerateInterpolator {
        private StartCurveInterpolator() {
        }

        public float getInterpolation(float input) {
            return super.getInterpolation(Math.min(1.0f, 2.0f * input));
        }
    }

    public MaterialProgressDrawable(Context context, View parent) {
        this.f3041l = parent;
        this.f3040k = context.getResources();
        this.f3037h = new Ring(this.f3038i);
        this.f3037h.setColors(this.f3035f);
        updateSizes(1);
        m1894c();
    }

    private void m1890a(double progressCircleWidth, double progressCircleHeight, double centerRadius, double strokeWidth, float arrowWidth, float arrowHeight) {
        Ring ring = this.f3037h;
        float screenDensity = this.f3040k.getDisplayMetrics().density;
        this.f3044o = ((double) screenDensity) * progressCircleWidth;
        this.f3045p = ((double) screenDensity) * progressCircleHeight;
        ring.setStrokeWidth(((float) strokeWidth) * screenDensity);
        ring.setCenterRadius(((double) screenDensity) * centerRadius);
        ring.setColorIndex(0);
        ring.setArrowDimensions(arrowWidth * screenDensity, arrowHeight * screenDensity);
        ring.setInsets((int) this.f3044o, (int) this.f3045p);
    }

    public void updateSizes(int size) {
        if (size == 0) {
            m1890a(56.0d, 56.0d, 12.5d, 3.0d, 12.0f, 6.0f);
        } else {
            m1890a(40.0d, 40.0d, 8.75d, 2.5d, 10.0f, 5.0f);
        }
    }

    public void showArrow(boolean show) {
        this.f3037h.setShowArrow(show);
    }

    public void setArrowScale(float scale) {
        this.f3037h.setArrowScale(scale);
    }

    public void setStartEndTrim(float startAngle, float endAngle) {
        this.f3037h.setStartTrim(startAngle);
        this.f3037h.setEndTrim(endAngle);
    }

    public void setProgressRotation(float rotation) {
        this.f3037h.setRotation(rotation);
    }

    public void setBackgroundColor(int color) {
        this.f3037h.setBackgroundColor(color);
    }

    public void setColorSchemeColors(int... colors) {
        this.f3037h.setColors(colors);
        this.f3037h.setColorIndex(0);
    }

    public int getIntrinsicHeight() {
        return (int) this.f3045p;
    }

    public int getIntrinsicWidth() {
        return (int) this.f3044o;
    }

    public void draw(Canvas c) {
        Rect bounds = getBounds();
        int saveCount = c.save();
        c.rotate(this.f3039j, bounds.exactCenterX(), bounds.exactCenterY());
        this.f3037h.draw(c, bounds);
        c.restoreToCount(saveCount);
    }

    public int getAlpha() {
        return this.f3037h.getAlpha();
    }

    public void setAlpha(int alpha) {
        this.f3037h.setAlpha(alpha);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f3037h.setColorFilter(colorFilter);
    }

    void m1895a(float rotation) {
        this.f3039j = rotation;
        invalidateSelf();
    }

    public int getOpacity() {
        return -3;
    }

    public boolean isRunning() {
        ArrayList<Animation> animators = this.f3036g;
        int N = animators.size();
        for (int i = 0; i < N; i++) {
            Animation animator = (Animation) animators.get(i);
            if (animator.hasStarted() && !animator.hasEnded()) {
                return true;
            }
        }
        return false;
    }

    public void start() {
        this.f3042m.reset();
        this.f3037h.storeOriginals();
        if (this.f3037h.getEndTrim() != this.f3037h.getStartTrim()) {
            this.f3034a = true;
            this.f3042m.setDuration(666);
            this.f3041l.startAnimation(this.f3042m);
            return;
        }
        this.f3037h.setColorIndex(0);
        this.f3037h.resetOriginals();
        this.f3042m.setDuration(1333);
        this.f3041l.startAnimation(this.f3042m);
    }

    public void stop() {
        this.f3041l.clearAnimation();
        m1895a(0.0f);
        this.f3037h.setShowArrow(false);
        this.f3037h.setColorIndex(0);
        this.f3037h.resetOriginals();
    }

    private void m1891a(float interpolatedTime, Ring ring) {
        float targetRotation = (float) (Math.floor((double) (ring.getStartingRotation() / 0.8f)) + 1.0d);
        ring.setStartTrim(ring.getStartingStartTrim() + ((ring.getStartingEndTrim() - ring.getStartingStartTrim()) * interpolatedTime));
        ring.setRotation(ring.getStartingRotation() + ((targetRotation - ring.getStartingRotation()) * interpolatedTime));
    }

    private void m1894c() {
        final Ring ring = this.f3037h;
        Animation animation = new Animation(this) {
            final /* synthetic */ MaterialProgressDrawable f3004b;

            public void applyTransformation(float interpolatedTime, Transformation t) {
                if (this.f3004b.f3034a) {
                    this.f3004b.m1891a(interpolatedTime, ring);
                    return;
                }
                float minProgressArc = (float) Math.toRadians(((double) ring.getStrokeWidth()) / (6.283185307179586d * ring.getCenterRadius()));
                float startingEndTrim = ring.getStartingEndTrim();
                float startingTrim = ring.getStartingStartTrim();
                float startingRotation = ring.getStartingRotation();
                ring.setEndTrim(startingEndTrim + (MaterialProgressDrawable.f3032d.getInterpolation(interpolatedTime) * (0.8f - minProgressArc)));
                ring.setStartTrim(startingTrim + (0.8f * MaterialProgressDrawable.f3031c.getInterpolation(interpolatedTime)));
                ring.setRotation(startingRotation + (0.25f * interpolatedTime));
                this.f3004b.m1895a((144.0f * interpolatedTime) + (720.0f * (this.f3004b.f3043n / 5.0f)));
            }
        };
        animation.setRepeatCount(-1);
        animation.setRepeatMode(1);
        animation.setInterpolator(f3030b);
        animation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ MaterialProgressDrawable f3006b;

            public void onAnimationStart(Animation animation) {
                this.f3006b.f3043n = 0.0f;
            }

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
                ring.storeOriginals();
                ring.goToNextColor();
                ring.setStartTrim(ring.getEndTrim());
                if (this.f3006b.f3034a) {
                    this.f3006b.f3034a = false;
                    animation.setDuration(1333);
                    ring.setShowArrow(false);
                    return;
                }
                this.f3006b.f3043n = (this.f3006b.f3043n + 1.0f) % 5.0f;
            }
        });
        this.f3042m = animation;
    }
}
