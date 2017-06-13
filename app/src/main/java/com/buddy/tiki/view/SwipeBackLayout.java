package com.buddy.tiki.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.helper.ViewDragHelper;
import com.buddy.tiki.helper.ViewDragHelper.Callback;
import java.util.ArrayList;
import java.util.List;

public class SwipeBackLayout extends FrameLayout {
    private static final int[] f2864a = new int[]{1, 2, 8, 11};
    private int f2865b;
    private float f2866c;
    private Activity f2867d;
    private boolean f2868e;
    private View f2869f;
    private ViewDragHelper f2870g;
    private float f2871h;
    private int f2872i;
    private int f2873j;
    private List<SwipeListener> f2874k;
    private Drawable f2875l;
    private Drawable f2876m;
    private Drawable f2877n;
    private float f2878o;
    private int f2879p;
    private boolean f2880q;
    private Rect f2881r;
    private int f2882s;

    public interface SwipeListener {
        void onEdgeTouch(int i);

        void onScrollOverThreshold();

        void onScrollStateChange(int i, float f);
    }

    private class ViewDragCallback extends Callback {
        final /* synthetic */ SwipeBackLayout f2862a;
        private boolean f2863b;

        private ViewDragCallback(SwipeBackLayout swipeBackLayout) {
            this.f2862a = swipeBackLayout;
        }

        public boolean tryCaptureView(View view, int i) {
            boolean ret = this.f2862a.f2870g.isEdgeTouched(this.f2862a.f2865b, i);
            if (ret) {
                if (this.f2862a.f2870g.isEdgeTouched(1, i)) {
                    this.f2862a.f2882s = 1;
                } else if (this.f2862a.f2870g.isEdgeTouched(2, i)) {
                    this.f2862a.f2882s = 2;
                } else if (this.f2862a.f2870g.isEdgeTouched(8, i)) {
                    this.f2862a.f2882s = 8;
                }
                if (!(this.f2862a.f2874k == null || this.f2862a.f2874k.isEmpty())) {
                    for (SwipeListener listener : this.f2862a.f2874k) {
                        listener.onEdgeTouch(this.f2862a.f2882s);
                    }
                }
                this.f2863b = true;
            }
            boolean directionCheck = false;
            if (this.f2862a.f2865b == 1 || this.f2862a.f2865b == 2) {
                directionCheck = !this.f2862a.f2870g.checkTouchSlop(2, i);
            } else if (this.f2862a.f2865b == 8) {
                directionCheck = !this.f2862a.f2870g.checkTouchSlop(1, i);
            } else if (this.f2862a.f2865b == 11) {
                directionCheck = true;
            }
            return ret & directionCheck;
        }

        public int getViewHorizontalDragRange(View child) {
            return this.f2862a.f2865b & 3;
        }

        public int getViewVerticalDragRange(View child) {
            return this.f2862a.f2865b & 8;
        }

        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            if ((this.f2862a.f2882s & 1) != 0) {
                this.f2862a.f2871h = Math.abs(((float) left) / ((float) (this.f2862a.f2869f.getWidth() + this.f2862a.f2875l.getIntrinsicWidth())));
            } else if ((this.f2862a.f2882s & 2) != 0) {
                this.f2862a.f2871h = Math.abs(((float) left) / ((float) (this.f2862a.f2869f.getWidth() + this.f2862a.f2876m.getIntrinsicWidth())));
            } else if ((this.f2862a.f2882s & 8) != 0) {
                this.f2862a.f2871h = Math.abs(((float) top) / ((float) (this.f2862a.f2869f.getHeight() + this.f2862a.f2877n.getIntrinsicHeight())));
            }
            this.f2862a.f2872i = left;
            this.f2862a.f2873j = top;
            this.f2862a.invalidate();
            if (this.f2862a.f2871h < this.f2862a.f2866c && !this.f2863b) {
                this.f2863b = true;
            }
            if (this.f2862a.f2874k != null && !this.f2862a.f2874k.isEmpty() && this.f2862a.f2870g.getViewDragState() == 1 && this.f2862a.f2871h >= this.f2862a.f2866c && this.f2863b) {
                this.f2863b = false;
                for (SwipeListener listener : this.f2862a.f2874k) {
                    listener.onScrollOverThreshold();
                }
            }
            if (this.f2862a.f2871h >= 1.0f && !this.f2862a.f2867d.isFinishing()) {
                this.f2862a.f2867d.finish();
                this.f2862a.f2867d.overridePendingTransition(0, 0);
            }
        }

        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            int childWidth = releasedChild.getWidth();
            int childHeight = releasedChild.getHeight();
            int left = 0;
            int top = 0;
            if ((this.f2862a.f2882s & 1) != 0) {
                left = (xvel > 0.0f || (xvel == 0.0f && this.f2862a.f2871h > this.f2862a.f2866c)) ? (this.f2862a.f2875l.getIntrinsicWidth() + childWidth) + 10 : 0;
            } else if ((this.f2862a.f2882s & 2) != 0) {
                left = (xvel < 0.0f || (xvel == 0.0f && this.f2862a.f2871h > this.f2862a.f2866c)) ? -((this.f2862a.f2875l.getIntrinsicWidth() + childWidth) + 10) : 0;
            } else if ((this.f2862a.f2882s & 8) != 0) {
                top = (yvel < 0.0f || (yvel == 0.0f && this.f2862a.f2871h > this.f2862a.f2866c)) ? -((this.f2862a.f2877n.getIntrinsicHeight() + childHeight) + 10) : 0;
            }
            this.f2862a.f2870g.settleCapturedViewAt(left, top);
            this.f2862a.invalidate();
        }

        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if ((this.f2862a.f2882s & 1) != 0) {
                return Math.min(child.getWidth(), Math.max(left, 0));
            }
            if ((this.f2862a.f2882s & 2) != 0) {
                return Math.min(0, Math.max(left, -child.getWidth()));
            }
            return 0;
        }

        public int clampViewPositionVertical(View child, int top, int dy) {
            if ((this.f2862a.f2882s & 8) != 0) {
                return Math.min(0, Math.max(top, -child.getHeight()));
            }
            return 0;
        }

        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
            if (this.f2862a.f2874k != null && !this.f2862a.f2874k.isEmpty()) {
                for (SwipeListener listener : this.f2862a.f2874k) {
                    listener.onScrollStateChange(state, this.f2862a.f2871h);
                }
            }
        }
    }

    public SwipeBackLayout(Context context) {
        this(context, null);
    }

    public SwipeBackLayout(Context context, AttributeSet attrs) {
        this(context, attrs, C0376R.attr.SwipeBackLayoutStyle);
    }

    public SwipeBackLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        this.f2866c = 0.3f;
        this.f2868e = true;
        this.f2879p = -1728053248;
        this.f2881r = new Rect();
        this.f2870g = ViewDragHelper.create(this, new ViewDragCallback());
        TypedArray a = context.obtainStyledAttributes(attrs, C0376R.styleable.SwipeBackLayout, defStyle, C0376R.style.SwipeBackLayout);
        int edgeSize = a.getDimensionPixelSize(0, -1);
        if (edgeSize > 0) {
            setEdgeSize(edgeSize);
        }
        setEdgeTrackingEnabled(f2864a[a.getInt(1, 0)]);
        int shadowLeft = a.getResourceId(2, C0376R.mipmap.shadow_left);
        int shadowRight = a.getResourceId(3, C0376R.mipmap.shadow_right);
        int shadowBottom = a.getResourceId(4, C0376R.mipmap.shadow_bottom);
        setShadow(shadowLeft, 1);
        setShadow(shadowRight, 2);
        setShadow(shadowBottom, 8);
        a.recycle();
        float minVel = 400.0f * getResources().getDisplayMetrics().density;
        this.f2870g.setMinVelocity(minVel);
        this.f2870g.setMaxVelocity(2.0f * minVel);
    }

    public void setSensitivity(Context context, float sensitivity) {
        this.f2870g.setSensitivity(context, sensitivity);
    }

    private void setContentView(View view) {
        this.f2869f = view;
    }

    public void setEnableGesture(boolean enable) {
        this.f2868e = enable;
    }

    public void setEdgeTrackingEnabled(int edgeFlags) {
        this.f2865b = edgeFlags;
        this.f2870g.setEdgeTrackingEnabled(this.f2865b);
    }

    public void setScrimColor(int color) {
        this.f2879p = color;
        invalidate();
    }

    public void setEdgeSize(int size) {
        this.f2870g.setEdgeSize(size);
    }

    @Deprecated
    public void setSwipeListener(SwipeListener listener) {
        addSwipeListener(listener);
    }

    public void addSwipeListener(SwipeListener listener) {
        if (this.f2874k == null) {
            this.f2874k = new ArrayList();
        }
        this.f2874k.add(listener);
    }

    public void removeSwipeListener(SwipeListener listener) {
        if (this.f2874k != null) {
            this.f2874k.remove(listener);
        }
    }

    public void setScrollThresHold(float threshold) {
        if (threshold >= 1.0f || threshold <= 0.0f) {
            throw new IllegalArgumentException("Threshold value should be between 0 and 1.0");
        }
        this.f2866c = threshold;
    }

    public void setShadow(Drawable shadow, int edgeFlag) {
        if ((edgeFlag & 1) != 0) {
            this.f2875l = shadow;
        } else if ((edgeFlag & 2) != 0) {
            this.f2876m = shadow;
        } else if ((edgeFlag & 8) != 0) {
            this.f2877n = shadow;
        }
        invalidate();
    }

    public void setShadow(int resId, int edgeFlag) {
        setShadow(getResources().getDrawable(resId), edgeFlag);
    }

    public void scrollToFinishActivity() {
        int childWidth = this.f2869f.getWidth();
        int childHeight = this.f2869f.getHeight();
        int left = 0;
        int top = 0;
        if ((this.f2865b & 1) != 0) {
            left = (this.f2875l.getIntrinsicWidth() + childWidth) + 10;
            this.f2882s = 1;
        } else if ((this.f2865b & 2) != 0) {
            left = ((-childWidth) - this.f2876m.getIntrinsicWidth()) - 10;
            this.f2882s = 2;
        } else if ((this.f2865b & 8) != 0) {
            top = ((-childHeight) - this.f2877n.getIntrinsicHeight()) - 10;
            this.f2882s = 8;
        }
        this.f2870g.smoothSlideViewTo(this.f2869f, left, top);
        invalidate();
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean z = false;
        if (this.f2868e) {
            try {
                z = this.f2870g.shouldInterceptTouchEvent(event);
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        return z;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!this.f2868e) {
            return false;
        }
        this.f2870g.processTouchEvent(event);
        return true;
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        this.f2880q = true;
        if (this.f2869f != null) {
            this.f2869f.layout(this.f2872i, this.f2873j, this.f2872i + this.f2869f.getMeasuredWidth(), this.f2873j + this.f2869f.getMeasuredHeight());
        }
        this.f2880q = false;
    }

    public void requestLayout() {
        if (!this.f2880q) {
            super.requestLayout();
        }
    }

    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        boolean drawContent = child == this.f2869f;
        boolean ret = super.drawChild(canvas, child, drawingTime);
        if (this.f2878o > 0.0f && drawContent && this.f2870g.getViewDragState() != 0) {
            m1788b(canvas, child);
            m1785a(canvas, child);
        }
        return ret;
    }

    private void m1785a(Canvas canvas, View child) {
        int color = (((int) (((float) ((this.f2879p & ViewCompat.MEASURED_STATE_MASK) >>> 24)) * this.f2878o)) << 24) | (this.f2879p & ViewCompat.MEASURED_SIZE_MASK);
        if ((this.f2882s & 1) != 0) {
            canvas.clipRect(0, 0, child.getLeft(), getHeight());
        } else if ((this.f2882s & 2) != 0) {
            canvas.clipRect(child.getRight(), 0, getRight(), getHeight());
        } else if ((this.f2882s & 8) != 0) {
            canvas.clipRect(child.getLeft(), child.getBottom(), getRight(), getHeight());
        }
        canvas.drawColor(color);
    }

    private void m1788b(Canvas canvas, View child) {
        Rect childRect = this.f2881r;
        child.getHitRect(childRect);
        if ((this.f2865b & 1) != 0) {
            this.f2875l.setBounds(childRect.left - this.f2875l.getIntrinsicWidth(), childRect.top, childRect.left, childRect.bottom);
            this.f2875l.setAlpha((int) (this.f2878o * 255.0f));
            this.f2875l.draw(canvas);
        }
        if ((this.f2865b & 2) != 0) {
            this.f2876m.setBounds(childRect.right, childRect.top, childRect.right + this.f2876m.getIntrinsicWidth(), childRect.bottom);
            this.f2876m.setAlpha((int) (this.f2878o * 255.0f));
            this.f2876m.draw(canvas);
        }
        if ((this.f2865b & 8) != 0) {
            this.f2877n.setBounds(childRect.left, childRect.bottom, childRect.right, childRect.bottom + this.f2877n.getIntrinsicHeight());
            this.f2877n.setAlpha((int) (this.f2878o * 255.0f));
            this.f2877n.draw(canvas);
        }
    }

    public void attachToActivity(Activity activity) {
        this.f2867d = activity;
        TypedArray a = activity.getTheme().obtainStyledAttributes(new int[]{16842836});
        int background = a.getResourceId(0, 0);
        a.recycle();
        ViewGroup decor = (ViewGroup) activity.getWindow().getDecorView();
        ViewGroup decorChild = (ViewGroup) decor.getChildAt(0);
        decorChild.setBackgroundResource(background);
        decor.removeView(decorChild);
        addView(decorChild);
        setContentView(decorChild);
        decor.addView(this);
    }

    public void computeScroll() {
        this.f2878o = 1.0f - this.f2871h;
        if (this.f2870g.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
