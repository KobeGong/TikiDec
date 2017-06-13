package com.buddy.tiki.view.match;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.buddy.tiki.C0376R;

public class LoadingView1 extends RelativeLayout {
    private ImageView f3063a;
    private ImageView f3064b;
    private ImageView f3065c;
    private Animation f3066d;
    private Animation f3067e;
    private Animation f3068f;
    private Handler f3069g;
    private boolean f3070h;

    class C05131 implements AnimationListener {
        final /* synthetic */ LoadingView1 f3058a;

        class C05121 implements Runnable {
            final /* synthetic */ C05131 f3057a;

            C05121(C05131 this$1) {
                this.f3057a = this$1;
            }

            public void run() {
                this.f3057a.f3058a.f3063a.startAnimation(this.f3057a.f3058a.f3066d);
            }
        }

        C05131(LoadingView1 this$0) {
            this.f3058a = this$0;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            if (this.f3058a.f3070h) {
                this.f3058a.f3069g.post(new C05121(this));
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    class C05152 implements AnimationListener {
        final /* synthetic */ LoadingView1 f3060a;

        class C05141 implements Runnable {
            final /* synthetic */ C05152 f3059a;

            C05141(C05152 this$1) {
                this.f3059a = this$1;
            }

            public void run() {
                this.f3059a.f3060a.f3064b.startAnimation(this.f3059a.f3060a.f3067e);
            }
        }

        C05152(LoadingView1 this$0) {
            this.f3060a = this$0;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            if (this.f3060a.f3070h) {
                this.f3060a.f3069g.post(new C05141(this));
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    class C05173 implements AnimationListener {
        final /* synthetic */ LoadingView1 f3062a;

        class C05161 implements Runnable {
            final /* synthetic */ C05173 f3061a;

            C05161(C05173 this$1) {
                this.f3061a = this$1;
            }

            public void run() {
                this.f3061a.f3062a.f3065c.startAnimation(this.f3061a.f3062a.f3068f);
            }
        }

        C05173(LoadingView1 this$0) {
            this.f3062a = this$0;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            if (this.f3062a.f3070h) {
                this.f3062a.f3069g.post(new C05161(this));
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    public LoadingView1(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        this.f3069g = new Handler();
        this.f3070h = true;
        inflate(context, C0376R.layout.view_loading_1, this);
        m1902a();
        m1905b();
        startAnimation();
    }

    public LoadingView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView1(Context context) {
        this(context, null);
    }

    private void m1902a() {
        this.f3063a = (ImageView) findViewById(C0376R.id.white_circle1);
        this.f3064b = (ImageView) findViewById(C0376R.id.white_circle2);
        this.f3065c = (ImageView) findViewById(C0376R.id.white_circle3);
    }

    private void m1905b() {
        this.f3066d = AnimationUtils.loadAnimation(getContext(), C0376R.anim.loading_view_1_circle_1);
        this.f3066d.setAnimationListener(new C05131(this));
        this.f3067e = AnimationUtils.loadAnimation(getContext(), C0376R.anim.loading_view_1_circle_1);
        this.f3067e.setAnimationListener(new C05152(this));
        this.f3068f = AnimationUtils.loadAnimation(getContext(), C0376R.anim.loading_view_1_circle_1);
        this.f3068f.setAnimationListener(new C05173(this));
    }

    public void startAnimation() {
        this.f3070h = true;
        this.f3063a.startAnimation(this.f3066d);
        this.f3064b.startAnimation(this.f3067e);
        this.f3065c.startAnimation(this.f3068f);
    }

    public void stopAnimation() {
        this.f3070h = false;
    }
}
