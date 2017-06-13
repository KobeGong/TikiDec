package com.buddy.tiki.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.buddy.tiki.C0376R;

public class DoubleLayerImageView extends FrameLayout {
    protected ImageView f2634a;
    protected ImageView f2635b;
    protected boolean f2636c;
    @DrawableRes
    private int f2637d;
    @DrawableRes
    private int f2638e;
    @DrawableRes
    private int f2639f;
    private int f2640g;
    private int f2641h;

    public DoubleLayerImageView(Context context) {
        this(context, null);
    }

    public DoubleLayerImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DoubleLayerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, C0376R.styleable.DoubleLayerImageView, defStyleAttr, 0);
        this.f2637d = array.getResourceId(2, -1);
        this.f2638e = array.getResourceId(1, -1);
        this.f2639f = array.getResourceId(0, -1);
        array.recycle();
        m1688a(context);
    }

    private void m1688a(Context context) {
        this.f2636c = true;
        inflate(context, C0376R.layout.widget_double_layer_imageview, this);
        this.f2634a = (ImageView) findViewById(C0376R.id.under_layer);
        this.f2635b = (ImageView) findViewById(C0376R.id.top_layer);
        this.f2634a.setImageResource(this.f2639f);
        this.f2635b.setImageResource(this.f2637d);
    }

    public void translationView(float translationX, @FloatRange(from = 0.0d, to = 1.0d) float translationPercent) {
        setTranslationX(translationX);
        setScaleX(1.0f - (0.25f * translationPercent));
        setScaleY(1.0f - (0.25f * translationPercent));
        if (this.f2636c) {
            if (translationPercent == 1.0f) {
                this.f2634a.setAlpha(1.0f);
            } else {
                this.f2634a.setAlpha(0.0f);
            }
            m1691a(this.f2635b, this.f2637d, translationPercent);
        }
    }

    protected void m1691a(ImageView imageView, @DrawableRes int drawableId, @FloatRange(from = 0.0d, to = 1.0d) float percent) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), drawableId);
        ConstantState state = drawable.getConstantState();
        Drawable wrapDrawable = DrawableCompat.wrap(state == null ? drawable : state.newDrawable());
        wrapDrawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        DrawableCompat.setTint(wrapDrawable, m1690a(-1, ContextCompat.getColor(getContext(), C0376R.color.bottomNaviTintColor), percent));
        imageView.setImageDrawable(wrapDrawable);
    }

    @ColorInt
    protected int m1690a(@ColorInt int startColor, @ColorInt int endColor, @FloatRange(from = 0.0d, to = 1.0d) float percent) {
        int startRed = Color.red(startColor);
        int startGreen = Color.green(startColor);
        int startBlue = Color.blue(startColor);
        return Color.rgb(((int) (((float) (Color.red(endColor) - startRed)) * percent)) + startColor, ((int) (((float) (Color.green(endColor) - startGreen)) * percent)) + startGreen, ((int) (((float) (Color.blue(endColor) - startBlue)) * percent)) + startBlue);
    }

    public void setApplyNum(int applyNum) {
        this.f2641h = applyNum;
        m1689b(this.f2641h + this.f2640g <= 0);
    }

    public void setUnreadNum(int unreadNum) {
        this.f2640g = unreadNum;
        m1689b(this.f2641h + this.f2640g <= 0);
    }

    private void m1689b(boolean show) {
        post(DoubleLayerImageView$$Lambda$1.lambdaFactory$(this, show));
    }

    /* synthetic */ void m1692a(boolean show) {
        this.f2636c = show;
        if (this.f2636c) {
            this.f2635b.setImageResource(this.f2637d);
            this.f2634a.setImageResource(this.f2639f);
            if (getScaleX() == 0.75f) {
                this.f2634a.setAlpha(1.0f);
                m1691a(this.f2635b, this.f2637d, 1.0f);
                return;
            }
            return;
        }
        this.f2634a.setAlpha(0.0f);
        this.f2635b.setImageResource(this.f2638e);
        this.f2634a.setImageDrawable(null);
    }
}
