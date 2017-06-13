package com.buddy.tiki.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.v4.view.PointerIconCompat;
import org.bytedeco.javacpp.avutil;

public class CountDownBorderDrawable extends Drawable {
    protected final Paint f414a = new Paint(1);
    protected ColorFilter f415b;
    protected int f416c = Color.parseColor("#7c000000");
    protected int f417d = Color.parseColor("#fffad200");
    protected int f418e = Color.parseColor("#ff3c32");
    protected long f419f = 100;
    protected long f420g = 20000;
    protected long f421h = 0;
    protected int f422i = 9;
    private int f423j;
    private boolean f424k = false;
    private boolean f425l = false;
    private long f426m;
    private long f427n;
    private CountDownListener f428o;
    private int f429p = 0;
    private long f430q = 500;

    public interface CountDownListener {
        void onTimeLeft(int i);

        void onTimeUp();
    }

    public static class SimpleCountDownListener implements CountDownListener {
        public void onTimeLeft(int second) {
        }

        public void onTimeUp() {
        }
    }

    public void setAlpha(int alpha) {
        if (alpha != this.f423j) {
            this.f423j = alpha;
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (colorFilter != this.f415b) {
            this.f415b = colorFilter;
            invalidateSelf();
        }
    }

    public int getOpacity() {
        return this.f423j == avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK ? -1 : -3;
    }

    public void setStrokeWidth(int width) {
        this.f422i = width;
        invalidateSelf();
    }

    public void setProgress(int progress, int total) {
        this.f419f = (long) (total * PointerIconCompat.TYPE_DEFAULT);
        this.f421h = (long) (progress * PointerIconCompat.TYPE_DEFAULT);
        this.f425l = true;
        invalidateSelf();
    }

    public void setProgressColor(int color) {
        this.f416c = color;
        invalidateSelf();
    }

    public void setRemainderColor(int color) {
        this.f417d = color;
        invalidateSelf();
    }

    public void draw(Canvas canvas) {
        if (this.f419f > 0 && this.f422i != 0) {
            Rect bounds = getBounds();
            if (this.f425l || this.f424k) {
                if (!this.f425l) {
                    m95b();
                }
                float width = (float) (bounds.right - bounds.left);
                float height = (float) (bounds.bottom - bounds.top);
                float progress = ((float) this.f421h) / ((float) this.f419f);
                if (progress > 1.0f) {
                    progress = 1.0f;
                }
                if (progress < 0.0f) {
                    progress = 0.0f;
                }
                float totalLenght = (width + height) * 2.0f;
                if (progress <= height / totalLenght) {
                    this.f414a.setColor(this.f416c);
                    this.f414a.setStrokeWidth((float) this.f422i);
                    canvas.drawLine((float) bounds.left, (float) bounds.top, (float) bounds.left, progress * totalLenght, this.f414a);
                    m94a();
                    canvas.drawLine((float) bounds.left, progress * totalLenght, (float) bounds.left, (float) bounds.bottom, this.f414a);
                    canvas.drawLine((float) bounds.left, (float) bounds.bottom, (float) bounds.right, (float) bounds.bottom, this.f414a);
                    canvas.drawLine((float) bounds.right, (float) bounds.bottom, (float) bounds.right, (float) bounds.top, this.f414a);
                    canvas.drawLine((float) bounds.right, (float) bounds.top, (float) bounds.left, (float) bounds.top, this.f414a);
                } else if (progress <= 0.5f) {
                    this.f414a.setColor(this.f416c);
                    this.f414a.setStrokeWidth((float) this.f422i);
                    canvas.drawLine((float) bounds.left, (float) bounds.top, (float) bounds.left, (float) bounds.bottom, this.f414a);
                    canvas.drawLine((float) bounds.left, (float) bounds.bottom, (((float) bounds.left) + (progress * totalLenght)) - height, (float) bounds.bottom, this.f414a);
                    m94a();
                    canvas.drawLine((((float) bounds.left) + (progress * totalLenght)) - height, (float) bounds.bottom, (float) bounds.right, (float) bounds.bottom, this.f414a);
                    canvas.drawLine((float) bounds.right, (float) bounds.bottom, (float) bounds.right, (float) bounds.top, this.f414a);
                    canvas.drawLine((float) bounds.right, (float) bounds.top, (float) bounds.left, (float) bounds.top, this.f414a);
                } else if (progress <= ((height * 2.0f) + width) / totalLenght) {
                    this.f414a.setColor(this.f416c);
                    this.f414a.setStrokeWidth((float) this.f422i);
                    canvas.drawLine((float) bounds.left, (float) bounds.top, (float) bounds.left, (float) bounds.bottom, this.f414a);
                    canvas.drawLine((float) bounds.left, (float) bounds.bottom, (float) bounds.right, (float) bounds.bottom, this.f414a);
                    canvas.drawLine((float) bounds.right, (float) bounds.bottom, (float) bounds.right, ((float) bounds.bottom) - (((progress * totalLenght) - width) - height), this.f414a);
                    m94a();
                    canvas.drawLine((float) bounds.right, ((float) bounds.bottom) - (((progress * totalLenght) - width) - height), (float) bounds.right, (float) bounds.top, this.f414a);
                    canvas.drawLine((float) bounds.right, (float) bounds.top, (float) bounds.left, (float) bounds.top, this.f414a);
                } else {
                    this.f414a.setColor(this.f416c);
                    this.f414a.setStrokeWidth((float) this.f422i);
                    canvas.drawLine((float) bounds.left, (float) bounds.top, (float) bounds.left, (float) bounds.bottom, this.f414a);
                    canvas.drawLine((float) bounds.left, (float) bounds.bottom, (float) bounds.right, (float) bounds.bottom, this.f414a);
                    canvas.drawLine((float) bounds.right, (float) bounds.bottom, (float) bounds.right, (float) bounds.top, this.f414a);
                    canvas.drawLine((float) bounds.right, (float) bounds.top, ((float) bounds.right) - (((progress * totalLenght) - width) - (height * 2.0f)), (float) bounds.top, this.f414a);
                    m94a();
                    canvas.drawLine(((float) bounds.right) - (((progress * totalLenght) - width) - (height * 2.0f)), (float) bounds.top, (float) bounds.left, (float) bounds.top, this.f414a);
                }
                this.f425l = false;
                return;
            }
            this.f414a.setColor(this.f416c);
            this.f414a.setStrokeWidth((float) this.f422i);
            canvas.drawLine((float) bounds.left, (float) bounds.top, (float) bounds.left, (float) (bounds.bottom - (this.f422i / 2)), this.f414a);
            canvas.drawLine((float) bounds.left, (float) bounds.bottom, (float) (bounds.right - (this.f422i / 2)), (float) bounds.bottom, this.f414a);
            canvas.drawLine((float) bounds.right, (float) bounds.bottom, (float) bounds.right, (float) (bounds.top + (this.f422i / 2)), this.f414a);
            canvas.drawLine((float) bounds.right, (float) bounds.top, (float) (bounds.left + (this.f422i / 2)), (float) bounds.top, this.f414a);
        }
    }

    private void m94a() {
        if (this.f419f - this.f421h > this.f420g) {
            this.f414a.setColor(this.f417d);
        } else if ((this.f421h / this.f430q) % 2 == 0) {
            this.f414a.setColor(this.f416c);
        } else {
            this.f414a.setColor(this.f418e);
        }
    }

    private void m95b() {
        this.f427n = SystemClock.uptimeMillis();
        this.f421h = this.f427n - this.f426m;
        if (this.f421h < this.f419f) {
            int newProgressSecond = (int) (this.f421h / 1000);
            if (newProgressSecond > this.f429p) {
                this.f429p = newProgressSecond;
                if (this.f428o != null) {
                    this.f428o.onTimeLeft((int) ((this.f419f / 1000) - ((long) this.f429p)));
                }
            }
            invalidateSelf();
        } else if (this.f428o != null) {
            this.f428o.onTimeUp();
        }
    }

    public void start(int totalSecond) {
        this.f424k = true;
        this.f426m = SystemClock.uptimeMillis();
        this.f419f = (long) (totalSecond * PointerIconCompat.TYPE_DEFAULT);
        this.f421h = 0;
        this.f429p = 0;
    }

    public void stop() {
        this.f424k = false;
        this.f421h = 0;
        invalidateSelf();
    }

    public void setCountDownListener(CountDownListener countDownListener) {
        this.f428o = countDownListener;
    }

    public void increate(int increaseTime) {
        this.f419f += (long) (increaseTime * PointerIconCompat.TYPE_DEFAULT);
    }
}
