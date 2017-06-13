package com.buddy.tiki.view.loading;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

/* compiled from: LoadingView */
class MyCircleImageView extends ImageView {
    private AnimationListener f3050a;
    private int f3051b;

    /* compiled from: LoadingView */
    private class OvalShadow extends OvalShape {
        final /* synthetic */ MyCircleImageView f3046a;
        private RadialGradient f3047b;
        private Paint f3048c = new Paint();
        private int f3049d;

        public OvalShadow(MyCircleImageView myCircleImageView, int shadowRadius, int circleDiameter) {
            this.f3046a = myCircleImageView;
            myCircleImageView.f3051b = shadowRadius;
            this.f3049d = circleDiameter;
            this.f3047b = new RadialGradient(((float) this.f3049d) / 2.0f, ((float) this.f3049d) / 2.0f, ((float) myCircleImageView.f3051b) * 1.0f, new int[]{1023410176, 0}, null, TileMode.CLAMP);
            this.f3048c.setShader(this.f3047b);
        }

        public void draw(Canvas canvas, Paint paint) {
            int viewWidth = this.f3046a.getWidth();
            int viewHeight = this.f3046a.getHeight();
            canvas.drawCircle(((float) viewWidth) / 2.0f, ((float) viewHeight) / 2.0f, (((float) this.f3049d) / 2.0f) + ((float) this.f3046a.f3051b), this.f3048c);
            canvas.drawCircle((float) (viewWidth / 2), (float) (viewHeight / 2), (float) (this.f3049d / 2), paint);
        }
    }

    public MyCircleImageView(Context context, int color, float radius) {
        super(context);
        if (!isInEditMode()) {
            ShapeDrawable circle;
            float density = getContext().getResources().getDisplayMetrics().density;
            int diameter = (int) ((radius * density) * 2.0f);
            int shadowYOffset = (int) (1.75f * density);
            int shadowXOffset = (int) (0.0f * density);
            this.f3051b = (int) (3.5f * density);
            if (m1898a()) {
                circle = new ShapeDrawable(new OvalShape());
                ViewCompat.setElevation(this, 4.0f * density);
            } else {
                circle = new ShapeDrawable(new OvalShadow(this, this.f3051b, diameter));
                ViewCompat.setLayerType(this, 1, circle.getPaint());
                circle.getPaint().setShadowLayer((float) this.f3051b, (float) shadowXOffset, (float) shadowYOffset, 503316480);
                int padding = this.f3051b;
                setPadding(padding, padding, padding, padding);
            }
            circle.getPaint().setColor(color);
            setBackgroundDrawable(circle);
        }
    }

    private boolean m1898a() {
        return VERSION.SDK_INT >= 21;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!m1898a()) {
            setMeasuredDimension(getMeasuredWidth() + (this.f3051b * 2), getMeasuredHeight() + (this.f3051b * 2));
        }
    }

    public void setAnimationListener(AnimationListener listener) {
        this.f3050a = listener;
    }

    public void onAnimationStart() {
        super.onAnimationStart();
        if (this.f3050a != null) {
            this.f3050a.onAnimationStart(getAnimation());
        }
    }

    public void onAnimationEnd() {
        super.onAnimationEnd();
        if (this.f3050a != null) {
            this.f3050a.onAnimationEnd(getAnimation());
        }
    }

    public void setBackgroundColorRes(int colorRes) {
        setBackgroundColor(getContext().getResources().getColor(colorRes));
    }

    public void setBackgroundColor(int color) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(color);
        }
    }
}
