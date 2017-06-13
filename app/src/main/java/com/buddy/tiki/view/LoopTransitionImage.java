package com.buddy.tiki.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import java.util.ArrayList;
import java.util.List;

public class LoopTransitionImage extends AppCompatImageView {
    private List<Integer> f2703a;
    private int f2704b;
    private long f2705c;
    private long f2706d;
    private int f2707e;
    private Paint f2708f;
    private PorterDuffXfermode f2709g;
    private PorterDuffXfermode f2710h;
    private Rect f2711i;

    public LoopTransitionImage(Context context) {
        this(context, null);
    }

    public LoopTransitionImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoopTransitionImage(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        this.f2703a = new ArrayList();
        this.f2705c = SystemClock.uptimeMillis();
        this.f2708f = new Paint(1);
        this.f2709g = new PorterDuffXfermode(Mode.SRC_IN);
        this.f2710h = new PorterDuffXfermode(Mode.SRC_ATOP);
        this.f2711i = new Rect();
        if (VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
    }

    public synchronized void setColors(List<Integer> colors) {
        if (colors != null) {
            if (colors.size() != 0) {
                this.f2703a.clear();
                this.f2703a.addAll(colors);
            }
        }
    }

    public synchronized void clearColors() {
        this.f2703a.clear();
    }

    public synchronized int[] getColors() {
        int[] iArr;
        if (this.f2703a == null || this.f2703a.size() == 0) {
            iArr = null;
        } else {
            iArr = new int[this.f2703a.size()];
            for (int i = 0; i < this.f2703a.size(); i++) {
                iArr[i] = ((Integer) this.f2703a.get(i)).intValue();
            }
        }
        return iArr;
    }

    public synchronized void setColors(int[] colors) {
        if (colors != null) {
            if (colors.length != 0) {
                this.f2703a.clear();
                for (int i : colors) {
                    this.f2703a.add(Integer.valueOf(i));
                }
            }
        }
    }

    public void setInterval(int interval) {
        this.f2704b = interval;
    }

    private void m1719a() {
        if (this.f2704b > 0 && this.f2703a.size() > 0) {
            this.f2706d = SystemClock.uptimeMillis();
            long interval = this.f2706d - this.f2705c;
            int startIndex = (int) (interval / ((long) this.f2704b));
            int nextIndex = startIndex + 1;
            nextIndex %= this.f2703a.size();
            int startColor = ((Integer) this.f2703a.get(startIndex % this.f2703a.size())).intValue();
            int nextColor = ((Integer) this.f2703a.get(nextIndex)).intValue();
            float factor = ((float) (interval % ((long) this.f2704b))) / ((float) this.f2704b);
            this.f2707e = Color.argb((int) ((((float) Color.alpha(startColor)) * (1.0f - factor)) + (((float) Color.alpha(nextColor)) * factor)), (int) ((((float) Color.red(startColor)) * (1.0f - factor)) + (((float) Color.red(nextColor)) * factor)), (int) ((((float) Color.green(startColor)) * (1.0f - factor)) + (((float) Color.green(nextColor)) * factor)), (int) ((((float) Color.blue(startColor)) * (1.0f - factor)) + (((float) Color.blue(nextColor)) * factor)));
            invalidate();
        }
    }

    private synchronized void m1720a(Canvas canvas) {
        if (canvas != null) {
            if (this.f2703a.size() > 0) {
                m1719a();
                this.f2708f.setColor(this.f2707e);
                this.f2708f.setXfermode(this.f2709g);
                this.f2711i.set(0, 0, canvas.getWidth(), canvas.getHeight());
                canvas.drawRect(this.f2711i, this.f2708f);
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        m1720a(canvas);
    }
}
