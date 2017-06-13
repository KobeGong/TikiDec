package com.buddy.tiki.view;

import android.content.Context;
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

public class VideoUploadProgressView extends View {
    private Paint f2913a;
    private int f2914b;
    private int f2915c;
    private float f2916d;
    private int f2917e;
    private int f2918f;

    public VideoUploadProgressView(Context context) {
        this(context, null);
    }

    public VideoUploadProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoUploadProgressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.f2913a = new Paint();
        this.f2913a.setStrokeJoin(Join.ROUND);
        this.f2913a.setStrokeCap(Cap.ROUND);
        this.f2914b = getResources().getColor(C0376R.color.white_alpha);
        this.f2915c = getResources().getColor(C0376R.color.white);
        this.f2916d = (float) DisplayUtil.dip2px(3.0f);
        this.f2917e = 100;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centre = getWidth() / 2;
        int radius = (int) (((float) centre) - (this.f2916d / 2.0f));
        this.f2913a.setColor(this.f2914b);
        this.f2913a.setStyle(Style.STROKE);
        this.f2913a.setStrokeWidth(this.f2916d);
        this.f2913a.setAntiAlias(true);
        canvas.drawCircle((float) centre, (float) centre, (float) radius, this.f2913a);
        this.f2913a.setStrokeWidth(this.f2916d);
        this.f2913a.setColor(this.f2915c);
        RectF oval = new RectF((float) (centre - radius), (float) (centre - radius), (float) (centre + radius), (float) (centre + radius));
        this.f2913a.setStyle(Style.STROKE);
        canvas.drawArc(oval, 270.0f, (float) ((this.f2918f * 360) / this.f2917e), false, this.f2913a);
    }

    public synchronized int getMax() {
        return this.f2917e;
    }

    public synchronized void setMax(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.f2917e = max;
    }

    public synchronized int getProgress() {
        return this.f2918f;
    }

    public synchronized void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (progress > this.f2917e) {
            progress = this.f2917e;
        }
        if (progress <= this.f2917e) {
            this.f2918f = progress;
            postInvalidate();
        }
    }
}
