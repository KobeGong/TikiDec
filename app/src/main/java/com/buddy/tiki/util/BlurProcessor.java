package com.buddy.tiki.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.facebook.imagepipeline.request.BasePostprocessor;

public class BlurProcessor extends BasePostprocessor {
    private final int f2320b = 30;
    private final int f2321c = 40;
    private Context f2322d;

    public BlurProcessor(Context context) {
        this.f2322d = context;
    }

    public String getName() {
        return "videoMessageBlurProcessor";
    }

    public void process(Bitmap bitmap) {
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 30, 40, false);
        if (scaledBitmap != null) {
            Bitmap result = BitmapUtil.fastblur(this.f2322d, scaledBitmap, 20);
            if (result != null) {
                Canvas copyCanvas = new Canvas(bitmap);
                Rect srcRect = new Rect(0, 0, result.getWidth(), result.getHeight());
                Rect dstRect = new Rect(0, 0, copyCanvas.getWidth(), copyCanvas.getHeight());
                Paint paint = new Paint();
                paint.setFlags(2);
                copyCanvas.drawBitmap(result, srcRect, dstRect, paint);
                BitmapUtil.recycle(result);
            }
            BitmapUtil.recycle(scaledBitmap);
        }
    }
}
