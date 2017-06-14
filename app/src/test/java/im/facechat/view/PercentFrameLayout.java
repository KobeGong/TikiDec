package im.facechat.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import io.netty.handler.codec.http2.Http2CodecUtil;
import io.netty.util.internal.shaded.org.jctools.util.Pow2;

public class PercentFrameLayout extends ViewGroup {
    private int f11552a = 0;
    private int f11553b = 0;
    private int f11554c = 100;
    private int f11555d = 100;

    public PercentFrameLayout(Context context) {
        super(context);
    }

    public PercentFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PercentFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setPosition(int i, int i2, int i3, int i4) {
        this.f11552a = i;
        this.f11553b = i2;
        this.f11554c = i3;
        this.f11555d = i4;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    protected void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(Http2CodecUtil.MAX_INITIAL_WINDOW_SIZE, i);
        int defaultSize2 = getDefaultSize(Http2CodecUtil.MAX_INITIAL_WINDOW_SIZE, i2);
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(defaultSize, Pow2.MAX_POW2), MeasureSpec.makeMeasureSpec(defaultSize2, Pow2.MAX_POW2));
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec((defaultSize * this.f11554c) / 100, Integer.MIN_VALUE);
        defaultSize2 = MeasureSpec.makeMeasureSpec((this.f11555d * defaultSize2) / 100, Integer.MIN_VALUE);
        for (defaultSize = 0; defaultSize < getChildCount(); defaultSize++) {
            View childAt = getChildAt(defaultSize);
            if (childAt.getVisibility() != 8) {
                childAt.measure(makeMeasureSpec, defaultSize2);
            }
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int i7 = (this.f11554c * i5) / 100;
        int i8 = (this.f11555d * i6) / 100;
        int i9 = i + ((i5 * this.f11552a) / 100);
        i6 = i2 + ((this.f11553b * i6) / 100);
        for (i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i10 = ((i7 - measuredWidth) / 2) + i9;
                int i11 = ((i8 - measuredHeight) / 2) + i6;
                childAt.layout(i10, i11, measuredWidth + i10, measuredHeight + i11);
            }
        }
    }
}
