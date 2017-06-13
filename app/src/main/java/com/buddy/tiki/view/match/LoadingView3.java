package com.buddy.tiki.view.match;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PointerIconCompat;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.buddy.tiki.C0376R;

public class LoadingView3 extends RelativeLayout {
    private ImageView f3080a;
    private ImageView f3081b;
    private ImageView f3082c;
    private AnimationSet f3083d;
    private AnimationSet f3084e;
    private AnimationSet f3085f;
    private Handler f3086g;
    private int f3087h;
    private int f3088i;
    private boolean f3089j;
    private Runnable f3090k;
    private Runnable f3091l;
    private Runnable f3092m;

    class C05181 implements Runnable {
        final /* synthetic */ LoadingView3 f3075a;

        C05181(LoadingView3 this$0) {
            this.f3075a = this$0;
        }

        public void run() {
            this.f3075a.f3080a.startAnimation(this.f3075a.f3083d);
        }
    }

    class C05192 implements Runnable {
        final /* synthetic */ LoadingView3 f3076a;

        C05192(LoadingView3 this$0) {
            this.f3076a = this$0;
        }

        public void run() {
            this.f3076a.f3081b.startAnimation(this.f3076a.f3084e);
        }
    }

    class C05203 implements Runnable {
        final /* synthetic */ LoadingView3 f3077a;

        C05203(LoadingView3 this$0) {
            this.f3077a = this$0;
        }

        public void run() {
            this.f3077a.f3082c.startAnimation(this.f3077a.f3085f);
        }
    }

    class C05224 implements AnimationListener {
        final /* synthetic */ LoadingView3 f3079a;

        class C05211 implements Runnable {
            final /* synthetic */ C05224 f3078a;

            C05211(C05224 this$1) {
                this.f3078a = this$1;
            }

            public void run() {
                this.f3078a.f3079a.startAnimation();
            }
        }

        C05224(LoadingView3 this$0) {
            this.f3079a = this$0;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            if (this.f3079a.f3089j) {
                this.f3079a.f3086g.post(new C05211(this));
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    public LoadingView3(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        this.f3086g = new Handler();
        this.f3087h = PointerIconCompat.TYPE_DEFAULT;
        this.f3088i = 500;
        this.f3089j = true;
        this.f3090k = new C05181(this);
        this.f3091l = new C05192(this);
        this.f3092m = new C05203(this);
        inflate(context, C0376R.layout.view_loading_3, this);
        m1915a();
        m1917b();
        startAnimation();
    }

    public LoadingView3(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView3(Context context) {
        this(context, null);
    }

    private void m1915a() {
        this.f3080a = (ImageView) findViewById(C0376R.id.white_circle1);
        this.f3081b = (ImageView) findViewById(C0376R.id.white_circle2);
        this.f3082c = (ImageView) findViewById(C0376R.id.white_circle3);
    }

    private void m1917b() {
        this.f3083d = new AnimationSet(false);
        ScaleAnimation scaleAnimation1 = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation1.setDuration((long) ((int) ((((float) this.f3087h) * 5.0f) / 14.0f)));
        scaleAnimation1.setStartOffset(0);
        this.f3083d.addAnimation(scaleAnimation1);
        ScaleAnimation scaleAnimation11 = new ScaleAnimation(1.0f, 0.75f, 1.0f, 0.75f, 1, 0.5f, 1, 0.5f);
        scaleAnimation11.setDuration((long) ((int) ((((float) this.f3087h) * 9.0f) / 14.0f)));
        scaleAnimation11.setStartOffset((long) ((int) ((((float) this.f3087h) * 5.0f) / 14.0f)));
        this.f3083d.addAnimation(scaleAnimation11);
        ScaleAnimation scaleAnimation12 = new ScaleAnimation(1.0f, 1.3333334f, 1.0f, 1.3333334f, 1, 0.5f, 1, 0.5f);
        scaleAnimation12.setDuration((long) ((int) ((((float) this.f3087h) * 9.0f) / 14.0f)));
        scaleAnimation12.setStartOffset((long) this.f3087h);
        this.f3083d.addAnimation(scaleAnimation12);
        ScaleAnimation scaleAnimation13 = new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f, 1, 0.5f, 1, 0.5f);
        scaleAnimation13.setDuration((long) ((int) ((((float) this.f3087h) * 5.0f) / 14.0f)));
        scaleAnimation13.setStartOffset((long) ((int) ((((float) this.f3087h) * 23.0f) / 14.0f)));
        this.f3083d.addAnimation(scaleAnimation13);
        TranslateAnimation translateAnimation1 = new TranslateAnimation(1, 0.0f, 1, 3.5f, 1, 0.0f, 1, 0.0f);
        translateAnimation1.setDuration((long) this.f3087h);
        translateAnimation1.setRepeatMode(2);
        translateAnimation1.setRepeatCount(1);
        translateAnimation1.setStartOffset(0);
        this.f3083d.addAnimation(translateAnimation1);
        Animation alphaAnimation = new AlphaAnimation(0.5f, 1.0f);
        alphaAnimation.setDuration((long) this.f3087h);
        alphaAnimation.setRepeatMode(2);
        alphaAnimation.setRepeatCount(1);
        this.f3083d.addAnimation(alphaAnimation);
        this.f3083d.setRepeatMode(2);
        this.f3083d.setRepeatCount(-1);
        this.f3084e = new AnimationSet(false);
        ScaleAnimation scaleAnimation2 = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation2.setDuration((long) this.f3088i);
        scaleAnimation2.setStartOffset(0);
        this.f3084e.addAnimation(scaleAnimation2);
        ScaleAnimation scaleAnimation21 = new ScaleAnimation(1.0f, 0.75f, 1.0f, 0.75f, 1, 0.5f, 1, 0.5f);
        scaleAnimation21.setDuration((long) this.f3088i);
        scaleAnimation21.setStartOffset((long) this.f3088i);
        this.f3084e.addAnimation(scaleAnimation21);
        ScaleAnimation scaleAnimation22 = new ScaleAnimation(1.0f, 1.3333334f, 1.0f, 1.3333334f, 1, 0.5f, 1, 0.5f);
        scaleAnimation22.setDuration((long) this.f3088i);
        scaleAnimation22.setStartOffset((long) (this.f3088i * 2));
        this.f3084e.addAnimation(scaleAnimation22);
        ScaleAnimation scaleAnimation23 = new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f, 1, 0.5f, 1, 0.5f);
        scaleAnimation23.setDuration((long) this.f3088i);
        scaleAnimation23.setStartOffset((long) (this.f3088i * 3));
        this.f3084e.addAnimation(scaleAnimation23);
        alphaAnimation = new AlphaAnimation(0.5f, 1.0f);
        alphaAnimation.setDuration((long) this.f3087h);
        alphaAnimation.setRepeatMode(2);
        alphaAnimation.setRepeatCount(1);
        this.f3084e.addAnimation(alphaAnimation);
        this.f3084e.setRepeatMode(2);
        this.f3084e.setRepeatCount(-1);
        this.f3085f = new AnimationSet(false);
        this.f3085f.addAnimation(scaleAnimation1);
        this.f3085f.addAnimation(scaleAnimation11);
        this.f3085f.addAnimation(scaleAnimation12);
        this.f3085f.addAnimation(scaleAnimation13);
        TranslateAnimation translateAnimation3 = new TranslateAnimation(1, 0.0f, 1, -3.5f, 1, 0.0f, 1, 0.0f);
        this.f3085f.addAnimation(alphaAnimation);
        translateAnimation3.setDuration((long) this.f3087h);
        translateAnimation3.setRepeatMode(2);
        translateAnimation3.setRepeatCount(1);
        translateAnimation3.setFillAfter(true);
        this.f3085f.addAnimation(translateAnimation3);
        this.f3085f.setRepeatMode(2);
        this.f3085f.setRepeatCount(-1);
        this.f3085f.setAnimationListener(new C05224(this));
    }

    public void startAnimation() {
        this.f3089j = true;
        this.f3080a.startAnimation(this.f3083d);
        this.f3081b.startAnimation(this.f3084e);
        this.f3082c.startAnimation(this.f3085f);
    }

    public void stopAnimation() {
        this.f3089j = false;
    }
}
