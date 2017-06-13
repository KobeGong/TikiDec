package com.buddy.tiki.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import com.buddy.tiki.log.TikiLog;
import com.facebook.drawee.view.SimpleDraweeView;

public class PinchToZoomDraweeView extends SimpleDraweeView {
    private static final TikiLog f2774c = TikiLog.getInstance(PinchToZoomDraweeView.class.getSimpleName());
    float f2775a;
    float f2776b;
    private final ScaleGestureDetector f2777d;
    private final OnScaleGestureListener f2778e;
    private final GestureDetector f2779f;
    private final Matrix f2780g;
    private float f2781h;
    @Nullable
    private OnZoomChangeListener f2782i;
    private RectF f2783j;
    private RectF f2784k;
    private RectF f2785l;
    private boolean f2786m;

    class C04951 extends SimpleOnScaleGestureListener {
        final /* synthetic */ PinchToZoomDraweeView f2769a;
        private boolean f2770b = false;

        C04951(PinchToZoomDraweeView this$0) {
            this.f2769a = this$0;
        }

        public boolean onScaleBegin(ScaleGestureDetector detector) {
            this.f2769a.f2775a = detector.getFocusX();
            this.f2769a.f2776b = detector.getFocusY();
            return true;
        }

        public boolean onScale(ScaleGestureDetector detector) {
            float factor = detector.getScaleFactor();
            Matrix transformationMatrix = new Matrix();
            float focusX = detector.getFocusX();
            float focusY = detector.getFocusY();
            transformationMatrix.postTranslate(-focusX, -focusY);
            transformationMatrix.postScale(factor, factor);
            float focusShiftX = focusX - this.f2769a.f2775a;
            float focusShiftY = focusY - this.f2769a.f2776b;
            transformationMatrix.postTranslate(focusX + focusShiftX, focusY + focusShiftY);
            if (this.f2769a.f2786m) {
                this.f2769a.f2784k.set(this.f2769a.f2783j);
                transformationMatrix.mapRect(this.f2769a.f2784k);
                if (this.f2769a.f2784k.left > this.f2769a.f2785l.left) {
                    factor = (((focusX - this.f2769a.f2784k.left) + (this.f2769a.f2784k.left - this.f2769a.f2785l.left)) * factor) / (focusX - this.f2769a.f2784k.left);
                }
                if (this.f2769a.f2784k.right < this.f2769a.f2785l.right) {
                    float factorRight = (((this.f2769a.f2784k.right - focusX) + (this.f2769a.f2785l.right - this.f2769a.f2784k.right)) * factor) / (this.f2769a.f2784k.right - focusX);
                    if (factorRight > factor) {
                        factor = factorRight;
                    }
                }
                if (this.f2769a.f2784k.top > this.f2769a.f2785l.top) {
                    float factorTop = (((focusY - this.f2769a.f2784k.top) + (this.f2769a.f2784k.top - this.f2769a.f2785l.top)) * factor) / (focusY - this.f2769a.f2784k.top);
                    if (factorTop > factor) {
                        factor = factorTop;
                    }
                }
                if (this.f2769a.f2784k.bottom < this.f2769a.f2785l.bottom) {
                    float factorBottom = (((this.f2769a.f2784k.bottom - focusY) + (this.f2769a.f2785l.bottom - this.f2769a.f2784k.bottom)) * factor) / (this.f2769a.f2784k.bottom - focusY);
                    if (factorBottom > factor) {
                        factor = factorBottom;
                    }
                }
                transformationMatrix.reset();
                transformationMatrix.postTranslate(-focusX, -focusY);
                transformationMatrix.postScale(factor, factor);
                transformationMatrix.postTranslate(focusX + focusShiftX, focusY + focusShiftY);
            }
            transformationMatrix.mapRect(this.f2769a.f2783j);
            this.f2769a.f2780g.postConcat(transformationMatrix);
            this.f2769a.f2775a = focusX;
            this.f2769a.f2776b = focusY;
            this.f2769a.invalidate();
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector detector) {
            super.onScaleEnd(detector);
        }
    }

    public class GestureListener implements OnDoubleTapListener, OnGestureListener {
        final /* synthetic */ PinchToZoomDraweeView f2771a;
        private int f2772b = 20;
        private int f2773c = 10;

        public GestureListener(PinchToZoomDraweeView this$0) {
            this.f2771a = this$0;
        }

        public boolean onDown(MotionEvent e) {
            return true;
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            float tx = -distanceX;
            float ty = -distanceY;
            if (this.f2771a.f2786m) {
                if (this.f2771a.f2783j.left + tx > this.f2771a.f2785l.left) {
                    tx = this.f2771a.f2785l.left - this.f2771a.f2783j.left;
                }
                if (this.f2771a.f2783j.right + tx < this.f2771a.f2785l.right) {
                    tx = this.f2771a.f2785l.right - this.f2771a.f2783j.right;
                }
                if (this.f2771a.f2783j.top + ty > this.f2771a.f2785l.top) {
                    ty = this.f2771a.f2785l.top - this.f2771a.f2783j.top;
                }
                if (this.f2771a.f2783j.bottom + ty < this.f2771a.f2785l.bottom) {
                    ty = this.f2771a.f2785l.bottom - this.f2771a.f2783j.bottom;
                }
            }
            this.f2771a.f2783j.set(this.f2771a.f2783j.left + tx, this.f2771a.f2783j.top + ty, this.f2771a.f2783j.right + tx, this.f2771a.f2783j.bottom + ty);
            this.f2771a.f2780g.postTranslate(tx, ty);
            this.f2771a.invalidate();
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
        }

        public boolean onDoubleTap(MotionEvent e) {
            return false;
        }

        public boolean onDoubleTapEvent(MotionEvent e) {
            return false;
        }

        public void onShowPress(MotionEvent e) {
        }

        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        public void onLongPress(MotionEvent e) {
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if ((e1.getX() - e2.getX() <= ((float) this.f2772b) || Math.abs(velocityX) <= ((float) this.f2773c)) && e2.getX() - e1.getX() > ((float) this.f2772b) && Math.abs(velocityX) > ((float) this.f2773c)) {
            }
            return false;
        }
    }

    public interface OnZoomChangeListener {
        void onZoomChange(float f);
    }

    public PinchToZoomDraweeView(Context context) {
        this(context, null);
    }

    public PinchToZoomDraweeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PinchToZoomDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.f2783j = new RectF();
        this.f2784k = new RectF();
        this.f2785l = new RectF();
        this.f2786m = false;
        this.f2778e = new C04951(this);
        this.f2777d = new ScaleGestureDetector(getContext(), this.f2778e);
        this.f2780g = new Matrix();
        this.f2779f = new GestureDetector(getContext(), new GestureListener(this));
    }

    public void setOnZoomChangeListener(OnZoomChangeListener listener) {
        this.f2782i = listener;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setOnZoomChangeListener(null);
    }

    protected void onDraw(@NonNull Canvas canvas) {
        int saveCount = canvas.save();
        canvas.concat(this.f2780g);
        super.onDraw(canvas);
        canvas.restoreToCount(saveCount);
    }

    public boolean onTouchEvent(MotionEvent event) {
        this.f2777d.onTouchEvent(event);
        this.f2779f.onTouchEvent(event);
        super.onTouchEvent(event);
        return true;
    }

    public void resetZoom() {
        reset();
    }

    public void reset() {
        this.f2780g.reset();
        this.f2781h = 1.0f;
        invalidate();
    }

    public void setConstraints(RectF originRect, RectF mustContainedRect) {
        if (originRect.contains(mustContainedRect)) {
            this.f2783j.set(originRect);
            this.f2785l.set(mustContainedRect);
            this.f2786m = true;
        }
    }

    public void removeConstraints() {
        this.f2786m = false;
    }

    public RectF getWholeRect() {
        return new RectF(this.f2783j);
    }

    public RectF getIntersectRect() {
        RectF resultRect = new RectF(this.f2785l);
        return resultRect.intersect(this.f2783j) ? resultRect : null;
    }
}
