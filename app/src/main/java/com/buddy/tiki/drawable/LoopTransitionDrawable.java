package com.buddy.tiki.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;
import org.bytedeco.javacpp.avutil;

public class LoopTransitionDrawable extends Drawable {
    private int f431a;
    private ColorFilter f432b;
    private List<Integer> f433c = new ArrayList();
    private int f434d;
    private long f435e = SystemClock.uptimeMillis();
    private long f436f;
    private int f437g;
    private Paint f438h = new Paint(1);
    private long f439i = 0;
    private PorterDuffXfermode f440j = new PorterDuffXfermode(Mode.CLEAR);
    private PorterDuffXfermode f441k = new PorterDuffXfermode(Mode.SRC_IN);
    private PorterDuffXfermode f442l = new PorterDuffXfermode(Mode.DST_IN);
    private boolean f443m = false;
    private boolean f444n = false;
    private Bitmap f445o;
    private Rect f446p;
    private Rect f447q = new Rect();

    public LoopTransitionDrawable(long base) {
        this.f439i = base;
    }

    public void setAlpha(int alpha) {
        if (alpha != this.f431a) {
            this.f431a = alpha;
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (colorFilter != this.f432b) {
            this.f432b = colorFilter;
            invalidateSelf();
        }
    }

    public int getOpacity() {
        return this.f431a == avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK ? -1 : -3;
    }

    public void setInterval(int interval) {
        this.f434d = interval;
    }

    public void setColors(List<Integer> colors) {
        if (colors != null && colors.size() != 0) {
            this.f433c.clear();
            this.f433c.addAll(colors);
        }
    }

    public int[] getColors() {
        if (this.f433c == null || this.f433c.size() == 0) {
            return null;
        }
        int[] result = new int[this.f433c.size()];
        for (int i = 0; i < this.f433c.size(); i++) {
            result[i] = ((Integer) this.f433c.get(i)).intValue();
        }
        return result;
    }

    public void setColors(int[] colors) {
        if (colors != null && colors.length != 0) {
            this.f433c.clear();
            for (int i : colors) {
                this.f433c.add(Integer.valueOf(i));
            }
        }
    }

    private void m96a() {
        if (this.f434d > 0 && this.f433c.size() > 0) {
            this.f436f = SystemClock.uptimeMillis();
            long interval = (this.f436f - this.f435e) + this.f439i;
            int startIndex = (int) (interval / ((long) this.f434d));
            int nextIndex = startIndex + 1;
            nextIndex %= this.f433c.size();
            int startColor = ((Integer) this.f433c.get(startIndex % this.f433c.size())).intValue();
            int nextColor = ((Integer) this.f433c.get(nextIndex)).intValue();
            float factor = ((float) (interval % ((long) this.f434d))) / ((float) this.f434d);
            this.f437g = Color.argb((int) ((((float) Color.alpha(startColor)) * (1.0f - factor)) + (((float) Color.alpha(nextColor)) * factor)), (int) ((((float) Color.red(startColor)) * (1.0f - factor)) + (((float) Color.red(nextColor)) * factor)), (int) ((((float) Color.green(startColor)) * (1.0f - factor)) + (((float) Color.green(nextColor)) * factor)), (int) ((((float) Color.blue(startColor)) * (1.0f - factor)) + (((float) Color.blue(nextColor)) * factor)));
            invalidateSelf();
        }
    }

    public void draw(Canvas canvas) {
        m96a();
        m97a(canvas.getWidth(), canvas.getHeight());
        this.f438h.setColor(this.f437g);
        Xfermode xfermode = this.f438h.getXfermode();
        if (this.f443m) {
            if (this.f445o != null && !this.f445o.isRecycled()) {
                canvas.drawBitmap(this.f445o, this.f446p, this.f447q, this.f438h);
            }
        } else if (this.f444n) {
            if (!(this.f445o == null || this.f445o.isRecycled())) {
                canvas.drawBitmap(this.f445o, this.f446p, this.f447q, this.f438h);
            }
            this.f438h.setXfermode(this.f441k);
            canvas.drawRect(this.f447q, this.f438h);
            this.f438h.setXfermode(xfermode);
        } else {
            canvas.drawRect(this.f447q, this.f438h);
            if (this.f445o != null && !this.f445o.isRecycled()) {
                this.f438h.setXfermode(this.f442l);
                canvas.drawBitmap(this.f445o, this.f446p, this.f447q, this.f438h);
                this.f438h.setXfermode(xfermode);
            }
        }
    }

    public void setShapeOnly(boolean shapeOnly) {
        this.f443m = shapeOnly;
    }

    public void setReverseDrawOrder(boolean reverseDrawOrder) {
        this.f444n = reverseDrawOrder;
    }

    private void m97a(int canvasWidth, int canvasHeight) {
        if (canvasWidth <= 0 || canvasHeight <= 0 || this.f446p == null || this.f446p.width() <= 0 || this.f446p.height() <= 0) {
            this.f447q.set(0, 0, canvasWidth, canvasHeight);
            return;
        }
        int newWidth = (this.f446p.width() * canvasHeight) / this.f446p.height();
        int newHeight = canvasHeight;
        this.f447q.set((canvasWidth - newWidth) / 2, 0, ((canvasWidth - newWidth) / 2) + newWidth, canvasHeight);
    }

    public void setShape(Bitmap shape) {
        if (shape != null) {
            this.f445o = shape.copy(shape.getConfig(), false);
            this.f446p = new Rect(0, 0, this.f445o.getWidth(), this.f445o.getHeight());
            return;
        }
        this.f445o = null;
        this.f446p = null;
    }
}
