package com.buddy.tiki.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import com.facebook.drawee.view.SimpleDraweeView;

public abstract class ShapedView extends SimpleDraweeView {
    private PorterDuffXfermode f2818a;
    private Paint f2819b;
    private Bitmap f2820c;
    private Rect f2821d;
    private Rect f2822e;

    protected abstract int getShapeId();

    public ShapedView(Context context) {
        this(context, null);
    }

    public ShapedView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShapedView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        this.f2818a = new PorterDuffXfermode(Mode.DST_IN);
        this.f2819b = new Paint(1);
        this.f2821d = new Rect();
        this.f2822e = new Rect();
        Options options = new Options();
        options.inPreferredConfig = Config.ARGB_8888;
        this.f2820c = BitmapFactory.decodeResource(getResources(), getShapeId(), options);
        this.f2819b.setXfermode(this.f2818a);
        setLayerType(2, null);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f2821d.set(0, 0, this.f2820c.getWidth(), this.f2820c.getHeight());
        this.f2822e.set(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawBitmap(this.f2820c, this.f2821d, this.f2822e, this.f2819b);
    }
}
