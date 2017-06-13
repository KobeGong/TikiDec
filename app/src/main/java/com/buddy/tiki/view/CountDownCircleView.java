package com.buddy.tiki.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.model.constant.MsgDataType;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacpp.postproc;

public class CountDownCircleView extends View {
    private Paint f2597a;
    private float f2598b;
    private float f2599c;
    private float f2600d;
    private float f2601e;
    private RectF f2602f;
    private int[] f2603g;
    private long f2604h;

    public CountDownCircleView(Context context) {
        this(context, null);
    }

    public CountDownCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.f2597a = new Paint();
        this.f2602f = new RectF();
        this.f2603g = new int[]{Color.rgb(avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK, MsgDataType.CALL_CLOSE_MSG, 50), Color.rgb(avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK, 67, 0)};
        TypedArray array = context.obtainStyledAttributes(attrs, C0376R.styleable.CountDownCircleView, defStyleAttr, 0);
        this.f2601e = array.getDimension(0, 5.0f);
        array.recycle();
    }

    private void m1673a() {
        this.f2598b = (float) getWidth();
        this.f2599c = (float) getHeight();
        this.f2600d = Math.min(this.f2598b, this.f2599c) / 2.0f;
    }

    private void m1674b() {
        this.f2597a.reset();
        this.f2597a.setAntiAlias(true);
    }

    private int m1672a(int origin) {
        int specMode = MeasureSpec.getMode(origin);
        int specSize = MeasureSpec.getSize(origin);
        if (specMode == postproc.PP_CPU_CAPS_3DNOW) {
            return specSize;
        }
        if (specMode == postproc.PP_CPU_CAPS_MMX) {
            return Math.min(24, specSize);
        }
        return 24;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(m1672a(widthMeasureSpec), m1672a(heightMeasureSpec));
    }

    protected void onDraw(Canvas canvas) {
        this.f2602f.setEmpty();
        m1674b();
        m1673a();
        this.f2597a.setColor(Color.parseColor("#77323232"));
        this.f2597a.setStrokeWidth(this.f2601e);
        this.f2597a.setStyle(Style.STROKE);
        canvas.drawCircle(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f, Math.min((((float) getWidth()) / 2.0f) - (this.f2601e / 2.0f), (((float) getHeight()) / 2.0f) - (this.f2601e / 2.0f)), this.f2597a);
        this.f2602f.set(this.f2601e / 2.0f, this.f2601e / 2.0f, ((float) getWidth()) - (this.f2601e / 2.0f), ((float) getHeight()) - (this.f2601e / 2.0f));
        this.f2597a.setStyle(Style.STROKE);
        this.f2597a.setStrokeWidth(this.f2601e);
        float percent = m1675c();
        if (percent <= 0.25f) {
            this.f2597a.setColor(this.f2603g[1]);
        } else {
            this.f2597a.setColor(this.f2603g[0]);
        }
        canvas.drawArc(this.f2602f, 270.0f - (percent * 360.0f), percent * 360.0f, false, this.f2597a);
    }

    private float m1675c() {
        return (((float) this.f2604h) * 1.0f) / 8.64E7f;
    }

    public void setColors(int... colors) {
        this.f2603g = colors;
    }

    public void setProgress(int percent) {
        if (percent >= 0 && percent <= 100) {
            this.f2604h = (86400000 * ((long) percent)) / 100;
            if (Looper.myLooper() != Looper.getMainLooper()) {
                postInvalidate();
            } else {
                invalidate();
            }
        }
    }

    public void setLeftTime(long leftTime) {
        this.f2604h = leftTime;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            postInvalidate();
        } else {
            invalidate();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
