package com.buddy.tiki.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
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
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import java.util.ArrayList;
import org.bytedeco.javacpp.avutil;

public class MaterialProgressDrawable extends Drawable implements Animatable {
    private static final Interpolator f476b = new LinearInterpolator();
    private static final Interpolator f477c = new EndCurveInterpolator();
    private static final Interpolator f478d = new StartCurveInterpolator();
    private static final Interpolator f479e = new AccelerateDecelerateInterpolator();
    boolean f480a;
    private final int[] f481f = new int[]{Color.parseColor("#FFF532")};
    private final ArrayList<Animation> f482g = new ArrayList();
    private final Ring f483h;
    private final Callback f484i = new C03771(this);
    private float f485j;
    private Resources f486k;
    private View f487l;
    private Animation f488m;
    private float f489n;
    private double f490o;
    private double f491p;

    class C03771 implements Callback {
        final /* synthetic */ MaterialProgressDrawable f448a;

        C03771(MaterialProgressDrawable this$0) {
            this.f448a = this$0;
        }

        public void invalidateDrawable(Drawable d) {
            this.f448a.invalidateSelf();
        }

        public void scheduleDrawable(Drawable d, Runnable what, long when) {
            this.f448a.scheduleSelf(what, when);
        }

        public void unscheduleDrawable(Drawable d, Runnable what) {
            this.f448a.unscheduleSelf(what);
        }
    }

    private static class EndCurveInterpolator extends AccelerateDecelerateInterpolator {
        private EndCurveInterpolator() {
        }

        public float getInterpolation(float input) {
            return super.getInterpolation(Math.max(0.0f, (input - 0.5f) * 2.0f));
        }
    }

    private static class Ring {
        private final RectF f453a = new RectF();
        private final Paint f454b = new Paint();
        private final Paint f455c = new Paint();
        private final Callback f456d;
        private final Paint f457e = new Paint();
        private float f458f = 0.0f;
        private float f459g = 0.0f;
        private float f460h = 0.0f;
        private float f461i = 5.0f;
        private float f462j = 2.5f;
        private int[] f463k;
        private int f464l;
        private float f465m;
        private float f466n;
        private float f467o;
        private boolean f468p;
        private Path f469q;
        private float f470r;
        private double f471s;
        private int f472t;
        private int f473u;
        private int f474v;
        private int f475w;

        public Ring(Callback callback) {
            this.f456d = callback;
            this.f454b.setStrokeCap(Cap.SQUARE);
            this.f454b.setAntiAlias(true);
            this.f454b.setStyle(Style.STROKE);
            this.f455c.setStyle(Style.FILL);
            this.f455c.setAntiAlias(true);
        }

        public void setBackgroundColor(int color) {
            this.f475w = color;
        }

        public void setArrowDimensions(float width, float height) {
            this.f472t = (int) width;
            this.f473u = (int) height;
        }

        public void draw(Canvas c, Rect bounds) {
            RectF arcBounds = this.f453a;
            arcBounds.set(bounds);
            arcBounds.inset(this.f462j, this.f462j);
            float startAngle = (this.f458f + this.f460h) * 360.0f;
            float sweepAngle = ((this.f459g + this.f460h) * 360.0f) - startAngle;
            this.f454b.setColor(this.f463k[this.f464l]);
            c.drawArc(arcBounds, startAngle, sweepAngle, false, this.f454b);
            m99a(c, startAngle, sweepAngle, bounds);
            if (this.f474v < avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK) {
                this.f457e.setColor(this.f475w);
                this.f457e.setAlpha(255 - this.f474v);
                c.drawCircle(bounds.exactCenterX() * 1.0f, bounds.exactCenterY() * 1.0f, ((float) bounds.width()) / 2.0f, this.f457e);
            }
        }

        private void m99a(Canvas c, float startAngle, float sweepAngle, Rect bounds) {
            if (this.f468p) {
                if (this.f469q == null) {
                    this.f469q = new Path();
                    this.f469q.setFillType(FillType.EVEN_ODD);
                } else {
                    this.f469q.reset();
                }
                float inset = ((float) (((int) this.f462j) / 2)) * this.f470r;
                float x = (float) ((this.f471s * Math.cos(0.0d)) + ((double) bounds.exactCenterX()));
                float y = (float) ((this.f471s * Math.sin(0.0d)) + ((double) bounds.exactCenterY()));
                this.f469q.moveTo(0.0f, 0.0f);
                this.f469q.lineTo(((float) this.f472t) * this.f470r, 0.0f);
                this.f469q.lineTo((((float) this.f472t) * this.f470r) / 2.0f, ((float) this.f473u) * this.f470r);
                this.f469q.offset(x - inset, y);
                this.f469q.close();
                this.f455c.setColor(this.f463k[this.f464l]);
                c.rotate((startAngle + sweepAngle) - 5.0f, bounds.exactCenterX(), bounds.exactCenterY());
                c.drawPath(this.f469q, this.f455c);
            }
        }

        public void setColors(@NonNull int[] colors) {
            this.f463k = colors;
            setColorIndex(0);
        }

        public void setColorIndex(int index) {
            this.f464l = index;
        }

        public void goToNextColor() {
            this.f464l = (this.f464l + 1) % this.f463k.length;
        }

        public void setColorFilter(ColorFilter filter) {
            this.f454b.setColorFilter(filter);
            m98a();
        }

        public int getAlpha() {
            return this.f474v;
        }

        public void setAlpha(int alpha) {
            this.f474v = alpha;
        }

        public float getStrokeWidth() {
            return this.f461i;
        }

        public void setStrokeWidth(float strokeWidth) {
            this.f461i = strokeWidth;
            this.f454b.setStrokeWidth(strokeWidth);
            m98a();
        }

        public float getStartTrim() {
            return this.f458f;
        }

        public void setStartTrim(float startTrim) {
            this.f458f = startTrim;
            m98a();
        }

        public float getStartingStartTrim() {
            return this.f465m;
        }

        public float getStartingEndTrim() {
            return this.f466n;
        }

        public float getEndTrim() {
            return this.f459g;
        }

        public void setEndTrim(float endTrim) {
            this.f459g = endTrim;
            m98a();
        }

        public float getRotation() {
            return this.f460h;
        }

        public void setRotation(float rotation) {
            this.f460h = rotation;
            m98a();
        }

        public void setInsets(int width, int height) {
            float insets;
            float minEdge = (float) Math.min(width, height);
            if (this.f471s <= 0.0d || minEdge < 0.0f) {
                insets = (float) Math.ceil((double) (this.f461i / 2.0f));
            } else {
                insets = (float) (((double) (minEdge / 2.0f)) - this.f471s);
            }
            this.f462j = insets;
        }

        public float getInsets() {
            return this.f462j;
        }

        public double getCenterRadius() {
            return this.f471s;
        }

        public void setCenterRadius(double centerRadius) {
            this.f471s = centerRadius;
        }

        public void setShowArrow(boolean show) {
            if (this.f468p != show) {
                this.f468p = show;
                m98a();
            }
        }

        public void setArrowScale(float scale) {
            if (scale != this.f470r) {
                this.f470r = scale;
                m98a();
            }
        }

        public float getStartingRotation() {
            return this.f467o;
        }

        public void storeOriginals() {
            this.f465m = this.f458f;
            this.f466n = this.f459g;
            this.f467o = this.f460h;
        }

        public void resetOriginals() {
            this.f465m = 0.0f;
            this.f466n = 0.0f;
            this.f467o = 0.0f;
            setStartTrim(0.0f);
            setEndTrim(0.0f);
            setRotation(0.0f);
        }

        private void m98a() {
            this.f456d.invalidateDrawable(null);
        }
    }

    private static class StartCurveInterpolator extends AccelerateDecelerateInterpolator {
        private StartCurveInterpolator() {
        }

        public float getInterpolation(float input) {
            return super.getInterpolation(Math.min(1.0f, 2.0f * input));
        }
    }

    public MaterialProgressDrawable(Context context, View parent) {
        this.f487l = parent;
        this.f486k = context.getResources();
        this.f483h = new Ring(this.f484i);
        this.f483h.setColors(this.f481f);
        updateSizes(1);
        m107c();
    }

    private void m103a(double progressCircleWidth, double progressCircleHeight, double centerRadius, double strokeWidth, float arrowWidth, float arrowHeight) {
        Ring ring = this.f483h;
        float screenDensity = this.f486k.getDisplayMetrics().density;
        this.f490o = ((double) screenDensity) * progressCircleWidth;
        this.f491p = ((double) screenDensity) * progressCircleHeight;
        ring.setStrokeWidth(((float) strokeWidth) * screenDensity);
        ring.setCenterRadius(((double) screenDensity) * centerRadius);
        ring.setColorIndex(0);
        ring.setArrowDimensions(arrowWidth * screenDensity, arrowHeight * screenDensity);
        ring.setInsets((int) this.f490o, (int) this.f491p);
    }

    public void updateSizes(int size) {
        if (size == 0) {
            m103a(56.0d, 56.0d, 12.5d, 3.0d, 12.0f, 6.0f);
        } else {
            m103a(40.0d, 40.0d, 1.75d, 2.5d, 10.0f, 5.0f);
        }
    }

    public void showArrow(boolean show) {
        this.f483h.setShowArrow(show);
    }

    public void setArrowScale(float scale) {
        this.f483h.setArrowScale(scale);
    }

    public void setStartEndTrim(float startAngle, float endAngle) {
        this.f483h.setStartTrim(startAngle);
        this.f483h.setEndTrim(endAngle);
    }

    public void setProgressRotation(float rotation) {
        this.f483h.setRotation(rotation);
    }

    public void setBackgroundColor(int color) {
        this.f483h.setBackgroundColor(color);
    }

    public void setColorSchemeColors(int... colors) {
        this.f483h.setColors(colors);
        this.f483h.setColorIndex(0);
    }

    public int getIntrinsicHeight() {
        return (int) this.f491p;
    }

    public int getIntrinsicWidth() {
        return (int) this.f490o;
    }

    public void draw(Canvas c) {
        Rect bounds = getBounds();
        int saveCount = c.save();
        c.rotate(this.f485j, bounds.exactCenterX(), bounds.exactCenterY());
        this.f483h.draw(c, bounds);
        c.restoreToCount(saveCount);
    }

    public int getAlpha() {
        return this.f483h.getAlpha();
    }

    public void setAlpha(int alpha) {
        this.f483h.setAlpha(alpha);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f483h.setColorFilter(colorFilter);
    }

    void m108a(float rotation) {
        this.f485j = rotation;
        invalidateSelf();
    }

    public int getOpacity() {
        return -3;
    }

    public boolean isRunning() {
        ArrayList<Animation> animators = this.f482g;
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
        this.f488m.reset();
        this.f483h.storeOriginals();
        if (this.f483h.getEndTrim() != this.f483h.getStartTrim()) {
            this.f480a = true;
            this.f488m.setDuration(666);
            this.f487l.startAnimation(this.f488m);
            return;
        }
        this.f483h.setColorIndex(0);
        this.f483h.resetOriginals();
        this.f488m.setDuration(1333);
        this.f487l.startAnimation(this.f488m);
    }

    public void stop() {
        this.f487l.clearAnimation();
        m108a(0.0f);
        this.f483h.setShowArrow(false);
        this.f483h.setColorIndex(0);
        this.f483h.resetOriginals();
    }

    private void m104a(float interpolatedTime, Ring ring) {
        float targetRotation = (float) (Math.floor((double) (ring.getStartingRotation() / 0.8f)) + 1.0d);
        ring.setStartTrim(ring.getStartingStartTrim() + ((ring.getStartingEndTrim() - ring.getStartingStartTrim()) * interpolatedTime));
        ring.setRotation(ring.getStartingRotation() + ((targetRotation - ring.getStartingRotation()) * interpolatedTime));
    }

    private void m107c() {
        final Ring ring = this.f483h;
        Animation animation = new Animation(this) {
            final /* synthetic */ MaterialProgressDrawable f450b;

            public void applyTransformation(float interpolatedTime, Transformation t) {
                if (this.f450b.f480a) {
                    this.f450b.m104a(interpolatedTime, ring);
                    return;
                }
                float minProgressArc = (float) Math.toRadians(((double) ring.getStrokeWidth()) / (6.283185307179586d * ring.getCenterRadius()));
                float startingEndTrim = ring.getStartingEndTrim();
                float startingTrim = ring.getStartingStartTrim();
                float startingRotation = ring.getStartingRotation();
                ring.setEndTrim(startingEndTrim + (MaterialProgressDrawable.f478d.getInterpolation(interpolatedTime) * (0.8f - minProgressArc)));
                ring.setStartTrim(startingTrim + (0.8f * MaterialProgressDrawable.f477c.getInterpolation(interpolatedTime)));
                ring.setRotation(startingRotation + (0.25f * interpolatedTime));
                this.f450b.m108a((144.0f * interpolatedTime) + (720.0f * (this.f450b.f489n / 5.0f)));
            }
        };
        animation.setRepeatCount(-1);
        animation.setRepeatMode(1);
        animation.setInterpolator(f476b);
        animation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ MaterialProgressDrawable f452b;

            public void onAnimationStart(Animation animation) {
                this.f452b.f489n = 0.0f;
            }

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
                ring.storeOriginals();
                ring.goToNextColor();
                ring.setStartTrim(ring.getEndTrim());
                if (this.f452b.f480a) {
                    this.f452b.f480a = false;
                    animation.setDuration(1333);
                    ring.setShowArrow(false);
                    return;
                }
                this.f452b.f489n = (this.f452b.f489n + 1.0f) % 5.0f;
            }
        });
        this.f488m = animation;
    }
}
