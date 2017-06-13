package com.buddy.tiki.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import com.buddy.tiki.C0376R;

public class RoundTextView extends AppCompatTextView {
    private float f2791a;
    private int f2792b;
    private int f2793c;
    private int f2794d;
    private RectF f2795e;
    private Paint f2796f;
    private boolean f2797g;

    public RoundTextView(Context context) {
        this(context, null);
    }

    public RoundTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.f2793c = 0;
        this.f2794d = 0;
        this.f2797g = true;
        if (isInEditMode()) {
            this.f2791a = -1.0f;
            this.f2792b = Color.parseColor("#33ffffff");
            this.f2794d = 0;
            this.f2793c = 0;
            this.f2797g = false;
        } else {
            TypedArray a = context.obtainStyledAttributes(attrs, C0376R.styleable.RoundTextView, defStyle, 0);
            this.f2791a = (float) a.getDimensionPixelSize(0, -1);
            this.f2792b = a.getColor(1, 0);
            this.f2794d = a.getDimensionPixelSize(2, 0);
            this.f2793c = a.getColor(3, 0);
            this.f2797g = a.getBoolean(4, false);
            a.recycle();
        }
        this.f2796f = new Paint();
        this.f2795e = new RectF();
    }

    protected void onDraw(Canvas canvas) {
        RectF rectF;
        float f = 0.0f;
        int w = getWidth();
        int h = getHeight();
        this.f2795e.setEmpty();
        if (this.f2797g) {
            float f2;
            this.f2795e.left = (float) getPaddingLeft();
            this.f2795e.right = (((float) w) - this.f2795e.left) - this.f2795e.right;
            RectF rectF2 = this.f2795e;
            if (this.f2795e.right > 0.0f) {
                f2 = this.f2795e.right;
            } else {
                f2 = 0.0f;
            }
            rectF2.right = f2;
            this.f2795e.top = (float) getPaddingTop();
            this.f2795e.bottom = (((float) h) - this.f2795e.top) - this.f2795e.bottom;
            rectF = this.f2795e;
            if (this.f2795e.bottom > 0.0f) {
                f = this.f2795e.bottom;
            }
            rectF.bottom = f;
        } else {
            this.f2795e.left = 0.0f;
            this.f2795e.top = 0.0f;
            this.f2795e.right = (float) w;
            this.f2795e.bottom = (float) h;
        }
        if (this.f2794d > 0) {
            this.f2796f.reset();
            this.f2796f.setAntiAlias(true);
            this.f2796f.setStyle(Style.STROKE);
            this.f2796f.setColor(this.f2793c);
            this.f2796f.setStrokeWidth((float) this.f2794d);
            canvas.drawRoundRect(this.f2795e, this.f2791a, this.f2791a, this.f2796f);
        }
        this.f2796f.reset();
        this.f2796f.setAntiAlias(true);
        this.f2796f.setStyle(Style.FILL);
        this.f2796f.setColor(this.f2792b);
        this.f2795e.bottom -= (float) this.f2794d;
        this.f2795e.top += (float) this.f2794d;
        rectF = this.f2795e;
        rectF.left += (float) this.f2794d;
        rectF = this.f2795e;
        rectF.right -= (float) this.f2794d;
        if (this.f2791a != -1.0f) {
            canvas.drawRoundRect(this.f2795e, this.f2791a - ((float) this.f2794d), this.f2791a - ((float) this.f2794d), this.f2796f);
        } else {
            canvas.drawRoundRect(this.f2795e, (float) ((h / 2) - this.f2794d), (float) ((h / 2) - this.f2794d), this.f2796f);
        }
        this.f2796f.reset();
        this.f2796f.setTextSize(getTextSize());
        this.f2796f.setColor(getCurrentTextColor());
        super.onDraw(canvas);
    }

    public void setBorderWidth(int mBorderWidth) {
        this.f2794d = mBorderWidth;
        invalidate();
        forceLayout();
    }

    public void setBorderColor(int mBorderColor) {
        this.f2793c = mBorderColor;
        invalidate();
        forceLayout();
    }

    public void setTextBg(@ColorInt int mTextBg) {
        this.f2792b = mTextBg;
        invalidate();
        forceLayout();
    }

    public void setCornerRadius(float mCornerRadius) {
        this.f2791a = mCornerRadius;
        invalidate();
        forceLayout();
    }

    public void setTransparentPadding(boolean isTransparent) {
        this.f2797g = isTransparent;
    }
}
