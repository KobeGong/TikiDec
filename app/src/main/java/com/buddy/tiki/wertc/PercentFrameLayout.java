package com.buddy.tiki.wertc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import org.bytedeco.javacpp.postproc;

public class PercentFrameLayout extends ViewGroup {
    private int f2576a = 0;
    private int f2577b = 0;
    private int f2578c = 100;
    private int f2579d = 100;

    public PercentFrameLayout(Context context) {
        super(context);
    }

    public PercentFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPosition(int xPercent, int yPercent, int widthPercent, int heightPercent) {
        this.f2576a = xPercent;
        this.f2577b = yPercent;
        this.f2578c = widthPercent;
        this.f2579d = heightPercent;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, widthMeasureSpec);
        int height = getDefaultSize(ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(width, postproc.PP_CPU_CAPS_3DNOW), MeasureSpec.makeMeasureSpec(height, postproc.PP_CPU_CAPS_3DNOW));
        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec((this.f2578c * width) / 100, postproc.PP_CPU_CAPS_MMX);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec((this.f2579d * height) / 100, postproc.PP_CPU_CAPS_MMX);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
            }
        }
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int width = right - left;
        int height = bottom - top;
        int subWidth = (this.f2578c * width) / 100;
        int subHeight = (this.f2579d * height) / 100;
        int subLeft = left + ((this.f2576a * width) / 100);
        int subTop = top + ((this.f2577b * height) / 100);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                int childLeft = subLeft + ((subWidth - childWidth) / 2);
                int childTop = subTop + ((subHeight - childHeight) / 2);
                child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
            }
        }
    }
}
