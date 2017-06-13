package com.buddy.tiki.view.match;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.buddy.tiki.C0376R;

public class LoadingView4 extends RelativeLayout {
    private ImageView f3094a;
    private ImageView f3095b;
    private ImageView f3096c;
    private AnimationSet f3097d;

    class C05231 implements Runnable {
        final /* synthetic */ LoadingView4 f3093a;

        C05231(LoadingView4 this$0) {
            this.f3093a = this$0;
        }

        public void run() {
            this.f3093a.stopAnimation();
        }
    }

    public LoadingView4(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        inflate(context, C0376R.layout.view_loading_4, this);
        m1924a();
        m1925b();
        startAnimation();
        new Handler().postDelayed(new C05231(this), 5000);
    }

    public LoadingView4(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView4(Context context) {
        this(context, null);
    }

    private void m1924a() {
        this.f3094a = (ImageView) findViewById(C0376R.id.white_circle1);
        this.f3095b = (ImageView) findViewById(C0376R.id.white_circle2);
        this.f3096c = (ImageView) findViewById(C0376R.id.white_circle3);
    }

    private void m1925b() {
        this.f3097d = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.33333334f, 1.0f, 0.33333334f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setRepeatMode(2);
        scaleAnimation.setRepeatCount(-1);
        this.f3097d.addAnimation(scaleAnimation);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatMode(2);
        rotateAnimation.setRepeatCount(-1);
        this.f3097d.addAnimation(rotateAnimation);
        this.f3097d.setDuration(1000);
        this.f3097d.setRepeatMode(2);
        this.f3097d.setRepeatCount(-1);
    }

    public void startAnimation() {
        startAnimation(this.f3097d);
    }

    public void stopAnimation() {
    }
}
