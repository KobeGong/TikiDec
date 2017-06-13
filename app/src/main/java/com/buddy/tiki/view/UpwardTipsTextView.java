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

public class UpwardTipsTextView extends AppCompatTextView {
    private Paint f2894a;
    private Path f2895b;
    private RectF f2896c;

    public UpwardTipsTextView(Context context) {
        super(context);
        m1808a();
    }

    public UpwardTipsTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        m1808a();
    }

    public UpwardTipsTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        m1808a();
    }

    private void m1808a() {
        this.f2894a = new Paint();
        this.f2895b = new Path();
        this.f2896c = new RectF();
    }

    protected void onDraw(Canvas canvas) {
        this.f2896c.set(0.0f, (float) DisplayUtil.dip2px(8.0f), (float) getWidth(), (float) getHeight());
        this.f2894a.reset();
        this.f2894a.setAntiAlias(true);
        this.f2894a.setStyle(Style.FILL);
        this.f2894a.setARGB(avcodec.AV_CODEC_ID_EXR, 0, 0, 0);
        canvas.drawRoundRect(this.f2896c, (float) DisplayUtil.dip2px(4.0f), (float) DisplayUtil.dip2px(4.0f), this.f2894a);
        this.f2895b.reset();
        this.f2895b.moveTo((float) DisplayUtil.dip2px(10.0f), (float) DisplayUtil.dip2px(8.0f));
        this.f2895b.lineTo((float) DisplayUtil.dip2px(16.0f), 0.0f);
        this.f2895b.lineTo((float) DisplayUtil.dip2px(22.0f), (float) DisplayUtil.dip2px(8.0f));
        this.f2895b.close();
        canvas.drawPath(this.f2895b, this.f2894a);
        super.onDraw(canvas);
    }
}
