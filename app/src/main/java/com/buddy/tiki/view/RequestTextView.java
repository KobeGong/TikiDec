package com.buddy.tiki.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.util.DisplayUtil;
import org.bytedeco.javacpp.avutil;

public class RequestTextView extends AppCompatTextView {
    private static final TikiLog f2787a = TikiLog.getInstance("RequestTextView");
    private Paint f2788b;
    private Path f2789c;
    private RectF f2790d;

    public RequestTextView(Context context) {
        super(context);
        m1749a();
    }

    public RequestTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        m1749a();
    }

    public RequestTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        m1749a();
    }

    private void m1749a() {
        this.f2788b = new Paint();
        this.f2789c = new Path();
        this.f2790d = new RectF();
    }

    protected void onDraw(Canvas canvas) {
        this.f2790d.set(0.0f, (float) DisplayUtil.dip2px(6.0f), (float) (((getWidth() + getPaddingStart()) + getPaddingEnd()) - DisplayUtil.dip2px(12.0f)), (float) (((getHeight() + getPaddingTop()) + getPaddingBottom()) - DisplayUtil.dip2px(18.0f)));
        this.f2788b.reset();
        this.f2788b.setAntiAlias(true);
        this.f2788b.setStyle(Style.FILL);
        this.f2788b.setARGB(204, avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK, 60, 60);
        canvas.drawRoundRect(this.f2790d, (float) DisplayUtil.dip2px(6.0f), (float) DisplayUtil.dip2px(6.0f), this.f2788b);
        this.f2789c.reset();
        this.f2789c.moveTo((float) DisplayUtil.dip2px(10.0f), (float) DisplayUtil.dip2px(6.0f));
        this.f2789c.lineTo((float) DisplayUtil.dip2px(16.0f), 0.0f);
        this.f2789c.lineTo((float) DisplayUtil.dip2px(22.0f), (float) DisplayUtil.dip2px(6.0f));
        this.f2789c.close();
        canvas.drawPath(this.f2789c, this.f2788b);
        super.onDraw(canvas);
    }
}
