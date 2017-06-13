package com.buddy.tiki.view.match;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.buddy.tiki.C0376R;

public class LoadingView2 extends RelativeLayout {
    private ImageView f3071a;
    private ImageView f3072b;
    private TranslateAnimation f3073c;
    private TranslateAnimation f3074d;

    public LoadingView2(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        inflate(context, C0376R.layout.view_loading_2, this);
        m1912a();
        m1913b();
        startAnimation();
    }

    public LoadingView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView2(Context context) {
        this(context, null);
    }

    private void m1912a() {
        this.f3071a = (ImageView) findViewById(C0376R.id.white_circle1);
        this.f3072b = (ImageView) findViewById(C0376R.id.white_circle2);
    }

    private void m1913b() {
        this.f3073c = new TranslateAnimation(1, 0.0f, 1, 2.0f, 1, 0.0f, 1, 0.0f);
        this.f3073c.setDuration(1000);
        this.f3073c.setRepeatMode(2);
        this.f3073c.setRepeatCount(-1);
        this.f3074d = new TranslateAnimation(1, 0.0f, 1, -2.0f, 1, 0.0f, 1, 0.0f);
        this.f3074d.setDuration(1000);
        this.f3074d.setRepeatMode(2);
        this.f3074d.setRepeatCount(-1);
    }

    public void startAnimation() {
        this.f3071a.startAnimation(this.f3073c);
        this.f3072b.startAnimation(this.f3074d);
    }

    public void stopAnimation() {
        if (this.f3073c != null) {
            this.f3073c.cancel();
        }
        if (this.f3074d != null) {
            this.f3074d.cancel();
        }
    }
}
