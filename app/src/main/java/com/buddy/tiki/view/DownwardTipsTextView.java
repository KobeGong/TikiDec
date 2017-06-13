package com.buddy.tiki.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import com.buddy.tiki.util.DisplayUtil;
import org.bytedeco.javacpp.avcodec;

public class DownwardTipsTextView extends AppCompatTextView {
    private Paint f2642a;
    private Path f2643b;
    private RectF f2644c;
    private float f2645d;

    public DownwardTipsTextView(Context context) {
        this(context, null);
    }

    public DownwardTipsTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DownwardTipsTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        m1693a();
    }

    private void m1693a() {
        this.f2642a = new Paint();
        this.f2643b = new Path();
        this.f2644c = new RectF();
        if (!isInEditMode()) {
            setGravity(17);
            setPaddingRelative(DisplayUtil.dip2px(12.0f), DisplayUtil.dip2px(6.0f), DisplayUtil.dip2px(12.0f), DisplayUtil.dip2px(14.0f));
            this.f2645d = (float) DisplayUtil.dip2px(4.0f);
        }
    }

    protected void onDraw(Canvas canvas) {
        float middleWidth = ((float) getWidth()) / 2.0f;
        this.f2644c.set((float) DisplayUtil.dip2px(6.0f), 0.0f, (float) (getWidth() - DisplayUtil.dip2px(6.0f)), (float) (getHeight() - DisplayUtil.dip2px(8.0f)));
        this.f2642a.reset();
        this.f2642a.setAntiAlias(true);
        this.f2642a.setStyle(Style.FILL);
        this.f2642a.setARGB(avcodec.AV_CODEC_ID_EXR, 0, 0, 0);
        canvas.drawRoundRect(this.f2644c, this.f2645d, this.f2645d, this.f2642a);
        this.f2643b.reset();
        this.f2643b.moveTo(middleWidth - (this.f2645d * 2.0f), ((float) getHeight()) - (this.f2645d * 2.0f));
        this.f2643b.lineTo(middleWidth, (float) getHeight());
        this.f2643b.lineTo((this.f2645d * 2.0f) + middleWidth, ((float) getHeight()) - (this.f2645d * 2.0f));
        this.f2643b.close();
        canvas.drawPath(this.f2643b, this.f2642a);
        super.onDraw(canvas);
    }
}
