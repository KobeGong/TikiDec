package com.buddy.tiki.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.util.DisplayUtil;

public class CircleProgressView extends View {
    private Paint f2581a;
    private int f2582b;
    private int f2583c;
    private float f2584d;
    private float f2585e;
    private int f2586f;
    private int f2587g;
    private int f2588h;
    private int f2589i;
    private int f2590j;
    private boolean f2591k;
    private RectF f2592l;
    private RectF f2593m;
    private int f2594n;
    private int f2595o;
    private int f2596p;

    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.f2591k = false;
        this.f2594n = 0;
        this.f2595o = 0;
        this.f2596p = DisplayUtil.dip2px(0.5f);
        this.f2581a = new Paint();
        this.f2581a.setStrokeJoin(Join.ROUND);
        this.f2581a.setStrokeCap(Cap.ROUND);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, C0376R.styleable.RoundProgressBar);
        this.f2582b = mTypedArray.getColor(0, getResources().getColor(C0376R.color.white));
        this.f2583c = mTypedArray.getColor(1, getResources().getColor(C0376R.color.red));
        this.f2584d = mTypedArray.getDimension(2, (float) DisplayUtil.dip2px(4.0f));
        this.f2586f = mTypedArray.getInteger(3, 600);
        this.f2585e = (float) DisplayUtil.dip2px(22.0f);
        this.f2588h = DisplayUtil.dip2px(6.0f);
        this.f2589i = DisplayUtil.dip2px(44.0f);
        this.f2590j = DisplayUtil.dip2px(16.0f);
        this.f2592l = new RectF((float) this.f2590j, (float) this.f2590j, (float) this.f2589i, (float) this.f2589i);
        this.f2593m = new RectF();
        this.f2595o = (int) Math.abs(this.f2585e - ((float) DisplayUtil.dip2px(20.0f)));
        mTypedArray.recycle();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centre = getWidth() / 2;
        int radius = (int) (((float) centre) - (this.f2584d / 2.0f));
        this.f2581a.setColor(this.f2582b);
        this.f2581a.setStyle(Style.STROKE);
        this.f2581a.setStrokeWidth(this.f2584d);
        this.f2581a.setAntiAlias(true);
        canvas.drawCircle((float) centre, (float) centre, (float) radius, this.f2581a);
        this.f2581a.setColor(this.f2583c);
        this.f2581a.setStyle(Style.FILL);
        this.f2581a.setAntiAlias(true);
        canvas.drawRoundRect(this.f2592l, (float) this.f2588h, (float) this.f2588h, this.f2581a);
        if (this.f2591k) {
            this.f2581a.setStrokeWidth(this.f2584d);
            this.f2581a.setColor(this.f2583c);
            this.f2593m.set((float) (centre - radius), (float) (centre - radius), (float) (centre + radius), (float) (centre + radius));
            this.f2581a.setStyle(Style.STROKE);
            canvas.drawArc(this.f2593m, 270.0f, (float) ((this.f2587g * 360) / this.f2586f), false, this.f2581a);
        }
    }

    public boolean isRecording() {
        return this.f2591k;
    }

    public void setRecording(boolean flag) {
        this.f2591k = flag;
        if (!this.f2591k) {
            this.f2594n = 0;
        }
    }

    public synchronized int getMax() {
        return this.f2586f;
    }

    public synchronized void setMax(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.f2586f = max;
    }

    public synchronized int getProgress() {
        return this.f2587g;
    }

    public synchronized void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (progress > this.f2586f) {
            progress = this.f2586f;
        }
        if (progress <= this.f2586f) {
            this.f2587g = progress;
            postInvalidate();
        }
    }

    public int getCricleColor() {
        return this.f2582b;
    }

    public void setCricleColor(int cricleColor) {
        this.f2582b = cricleColor;
    }

    public int getCricleProgressColor() {
        return this.f2583c;
    }

    public void setCricleProgressColor(int cricleProgressColor) {
        this.f2583c = cricleProgressColor;
    }

    public float getRoundWidth() {
        return this.f2584d;
    }

    public void setRoundWidth(float roundWidth) {
        this.f2584d = roundWidth;
    }
}
