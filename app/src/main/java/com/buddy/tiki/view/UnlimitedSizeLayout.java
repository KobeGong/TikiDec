package com.buddy.tiki.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import com.buddy.tiki.C0376R;
import org.bytedeco.javacpp.postproc;
import org.bytedeco.javacpp.swscale;

public class UnlimitedSizeLayout extends ViewGroup {

    public static class LayoutParams extends MarginLayoutParams {
        public int f2892a;
        public int f2893b;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, C0376R.styleable.UnlimitedSizeLayout);
            this.f2892a = a.getDimensionPixelOffset(0, 0);
            this.f2893b = a.getDimensionPixelOffset(1, 0);
            a.recycle();
        }

        public LayoutParams(int width, int height, int x, int y) {
            super(width, height);
            this.f2892a = x;
            this.f2893b = y;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
        }
    }

    public UnlimitedSizeLayout(Context context) {
        this(context, null);
    }

    public UnlimitedSizeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnlimitedSizeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        int width = 0;
        int height = 0;
        int childState = 0;
        int newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(swscale.SWS_PRINT_INFO, postproc.PP_CPU_CAPS_MMX);
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(swscale.SWS_PRINT_INFO, postproc.PP_CPU_CAPS_MMX);
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (!(child == null || child.getVisibility() == 8)) {
                measureChildWithMargins(child, newWidthMeasureSpec, 0, newHeightMeasureSpec, 0);
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                width = Math.max(width, ((((lp.f2892a + child.getMeasuredWidth()) + lp.leftMargin) + lp.rightMargin) + getPaddingLeft()) + getPaddingRight());
                height = Math.max(height, ((((lp.f2893b + child.getMeasuredHeight()) + lp.topMargin) + lp.bottomMargin) + getPaddingTop()) + getPaddingBottom());
                childState = combineMeasuredStates(childState, child.getMeasuredState());
            }
        }
        setMeasuredDimension(resolveSizeAndState(Math.max(width, getSuggestedMinimumWidth()), widthMeasureSpec, childState), resolveSizeAndState(Math.max(height, getSuggestedMinimumHeight()), heightMeasureSpec, childState));
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int leftPos = getPaddingLeft();
        int rightPos = (r - l) - getPaddingRight();
        int parentTop = getPaddingTop();
        int parantBottom = (b - t) - getPaddingBottom();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                child.layout((lp.f2892a + lp.leftMargin) + leftPos, (lp.f2893b + lp.topMargin) + parentTop, ((lp.f2892a + lp.leftMargin) + child.getMeasuredWidth()) + leftPos, ((lp.f2893b + lp.topMargin) + child.getMeasuredHeight()) + parentTop);
            }
        }
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1, 0, 0);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }
}
