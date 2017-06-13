package com.buddy.tiki.view.loading;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import com.buddy.tiki.util.DisplayUtil;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacpp.postproc;

public class LoadingView extends FrameLayout {
    protected int f2986a;
    protected int f2987b;
    private final float f2988c = 1.2f;
    private MyCircleImageView f2989d;
    private MaterialProgressDrawable f2990e;
    private int f2991f;
    private int f2992g;
    private int f2993h;
    private float f2994i = -1.0f;
    private final Animation f2995j = new C05031(this);
    private int f2996k;
    private Animation f2997l;
    private Animation f2998m;
    private DecelerateInterpolator f2999n;
    private boolean f3000o = false;
    private AnimationListener f3001p = new C05042(this);

    class C05031 extends Animation {
        final /* synthetic */ LoadingView f2982a;

        C05031(LoadingView this$0) {
            this.f2982a = this$0;
        }

        public void applyTransformation(float interpolatedTime, Transformation t) {
            this.f2982a.setTargetOffsetTopAndBottom((this.f2982a.f2987b + ((int) (((float) (((int) (((float) this.f2982a.f2986a) + this.f2982a.f2994i)) - this.f2982a.f2987b)) * interpolatedTime))) - this.f2982a.f2989d.getTop());
        }
    }

    class C05042 implements AnimationListener {
        final /* synthetic */ LoadingView f2983a;

        C05042(LoadingView this$0) {
            this.f2983a = this$0;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            if (this.f2983a.f3000o) {
                this.f2983a.f2990e.start();
            } else {
                this.f2983a.f2990e.stop();
                this.f2983a.f2989d.setVisibility(8);
                ViewCompat.setScaleX(this.f2983a.f2989d, 0.0f);
                ViewCompat.setScaleY(this.f2983a.f2989d, 0.0f);
            }
            this.f2983a.f2993h = this.f2983a.f2989d.getTop();
        }
    }

    class C05053 extends Animation {
        final /* synthetic */ LoadingView f2984a;

        C05053(LoadingView this$0) {
            this.f2984a = this$0;
        }

        public void applyTransformation(float interpolatedTime, Transformation t) {
            this.f2984a.setAnimationProgress(interpolatedTime);
        }
    }

    class C05064 extends Animation {
        final /* synthetic */ LoadingView f2985a;

        C05064(LoadingView this$0) {
            this.f2985a = this$0;
        }

        public void applyTransformation(float interpolatedTime, Transformation t) {
            this.f2985a.setAnimationProgress(1.0f - interpolatedTime);
        }
    }

    public LoadingView(Context context) {
        super(context);
        m1876a(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        m1876a(context);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        m1876a(context);
    }

    @TargetApi(21)
    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        m1876a(context);
    }

    private void m1876a(Context context) {
        if (!isInEditMode()) {
            m1874a();
            int density = (int) (45.0f * DisplayUtil.getDensity());
            this.f2991f = density;
            this.f2992g = density;
            this.f2994i = 64.0f * DisplayUtil.getDensity();
            this.f2996k = getResources().getInteger(17694721);
            this.f2999n = new DecelerateInterpolator(2.0f);
            setVisibility(0);
        }
    }

    private void m1874a() {
        this.f2989d = new MyCircleImageView(getContext(), -1, 22.0f);
        this.f2990e = new MaterialProgressDrawable(getContext(), this);
        this.f2990e.setBackgroundColor(-1);
        this.f2989d.setImageDrawable(this.f2990e);
        this.f2989d.setVisibility(0);
        this.f2990e.setAlpha(avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK);
        this.f2990e.setColorSchemeColors(-4046);
        addView(this.f2989d);
    }

    public void setScale(float scale) {
        this.f2990e.showArrow(true);
        float targetScale = scale;
        this.f2990e.setArrowScale(Math.min(1.0f, targetScale));
        this.f2990e.setProgressRotation(targetScale);
        this.f2990e.setStartEndTrim(0.0f, Math.min(0.8f, 0.8f * targetScale));
    }

    public void onRefresh(float scale) {
        this.f3000o = true;
        m1877a(null);
        m1875a(this.f2993h, this.f3001p);
    }

    public void stopRefresh() {
        this.f3000o = false;
        m1882b(this.f3001p);
    }

    public void startAnimation() {
        this.f2990e.start();
    }

    public void stopAnimation() {
        this.f2990e.stop();
    }

    private void m1877a(AnimationListener listener) {
        this.f2989d.setVisibility(0);
        this.f2997l = new C05053(this);
        if (listener != null) {
            this.f2997l.setAnimationListener(listener);
        }
        this.f2997l.setDuration((long) this.f2996k);
        this.f2989d.clearAnimation();
        this.f2989d.startAnimation(this.f2997l);
    }

    private void m1882b(AnimationListener listener) {
        this.f2998m = new C05064(this);
        this.f2998m.setDuration(150);
        this.f2989d.clearAnimation();
        this.f2989d.startAnimation(this.f2998m);
    }

    private void m1875a(int from, AnimationListener listener) {
        this.f2987b = from;
        this.f2995j.reset();
        this.f2995j.setDuration(200);
        this.f2995j.setInterpolator(this.f2999n);
        if (listener != null) {
            this.f2995j.setAnimationListener(listener);
        }
        this.f2989d.clearAnimation();
        this.f2989d.startAnimation(this.f2995j);
    }

    public void setVisibility(int visibility) {
        if (visibility == 8 || visibility == 4) {
            this.f2990e.stop();
        } else {
            this.f2990e.start();
            this.f2990e.showArrow(true);
        }
        super.setVisibility(visibility);
    }

    private void setAnimationProgress(float progress) {
        ViewCompat.setScaleX(this.f2989d, progress);
        ViewCompat.setScaleY(this.f2989d, progress);
    }

    private void setTargetOffsetTopAndBottom(int offset) {
        this.f2989d.bringToFront();
        this.f2989d.offsetTopAndBottom(offset);
        this.f2993h = this.f2989d.getTop();
        invalidate();
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int width = getMeasuredWidth();
        int circleWidth = this.f2989d.getMeasuredWidth();
        this.f2989d.layout((width / 2) - (circleWidth / 2), 0, (width / 2) + (circleWidth / 2), this.f2989d.getMeasuredHeight());
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.f2989d.measure(MeasureSpec.makeMeasureSpec(this.f2991f, postproc.PP_CPU_CAPS_3DNOW), MeasureSpec.makeMeasureSpec(this.f2992g, postproc.PP_CPU_CAPS_3DNOW));
    }
}
