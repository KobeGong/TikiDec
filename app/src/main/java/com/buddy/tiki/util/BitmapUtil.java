package com.buddy.tiki.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import com.buddy.tiki.log.TikiLog;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacpp.swscale;

public class BitmapUtil {
    private static final TikiLog f2318a = TikiLog.getInstance("BitmapUtil");

    public static boolean saved2Local(Bitmap bitmap, String fileName) {
        boolean z;
        FileNotFoundException e;
        Throwable th;
        BufferedOutputStream os = null;
        try {
            BufferedOutputStream os2 = new BufferedOutputStream(new FileOutputStream(fileName));
            try {
                bitmap.compress(CompressFormat.PNG, 75, os2);
                z = true;
                if (os2 != null) {
                    try {
                        os2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                bitmap.recycle();
                f2318a.m263e("finish");
                os = os2;
            } catch (FileNotFoundException e3) {
                e = e3;
                os = os2;
                try {
                    e.printStackTrace();
                    z = false;
                    if (os != null) {
                        try {
                            os.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    bitmap.recycle();
                    f2318a.m263e("finish");
                    return z;
                } catch (Throwable th2) {
                    th = th2;
                    if (os != null) {
                        try {
                            os.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                    bitmap.recycle();
                    f2318a.m263e("finish");
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                os = os2;
                if (os != null) {
                    os.close();
                }
                bitmap.recycle();
                f2318a.m263e("finish");
                throw th;
            }
        } catch (FileNotFoundException e4) {
            e = e4;
            e.printStackTrace();
            z = false;
            if (os != null) {
                os.close();
            }
            bitmap.recycle();
            f2318a.m263e("finish");
            return z;
        }
        return z;
    }

    @SuppressLint({"NewApi"})
    public static Bitmap fastblur(Context context, Bitmap sentBitmap, int radius) {
        if (VERSION.SDK_INT > 16) {
            long start = System.currentTimeMillis();
            Bitmap bitmap = Bitmap.createBitmap(sentBitmap.getWidth(), sentBitmap.getHeight(), Config.ARGB_8888);
            RenderScript rs = RenderScript.create(context);
            ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            Allocation input = Allocation.createFromBitmap(rs, sentBitmap);
            Allocation output = Allocation.createFromBitmap(rs, bitmap);
            script.setRadius((float) radius);
            script.setInput(input);
            script.forEach(output);
            output.copyTo(bitmap);
            input.destroy();
            output.destroy();
            script.destroy();
            rs.destroy();
            return bitmap;
        }
        bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
        if (radius < 1) {
            return null;
        }
        int i;
        int y;
        int x;
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        int[] pix = new int[(w * h)];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);
        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = (radius + radius) + 1;
        int[] r = new int[wh];
        int[] g = new int[wh];
        int[] b = new int[wh];
        int[] vmin = new int[Math.max(w, h)];
        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int[] dv = new int[(divsum * swscale.SWS_SINC)];
        for (i = 0; i < divsum * swscale.SWS_SINC; i++) {
            dv[i] = i / divsum;
        }
        int yi = 0;
        int yw = 0;
        int[][] stack = (int[][]) Array.newInstance(Integer.TYPE, new int[]{div, 3});
        int r1 = radius + 1;
        for (y = 0; y < h; y++) {
            int bsum = 0;
            int gsum = 0;
            int rsum = 0;
            int boutsum = 0;
            int goutsum = 0;
            int routsum = 0;
            int binsum = 0;
            int ginsum = 0;
            int rinsum = 0;
            for (i = -radius; i <= radius; i++) {
                int p = pix[Math.min(wm, Math.max(i, 0)) + yi];
                int[] sir = stack[i + radius];
                sir[0] = (16711680 & p) >> 16;
                sir[1] = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & p) >> 8;
                sir[2] = p & avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK;
                int rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            int stackpointer = radius;
            for (x = 0; x < w; x++) {
                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];
                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;
                sir = stack[((stackpointer - radius) + div) % div];
                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];
                if (y == 0) {
                    vmin[x] = Math.min((x + radius) + 1, wm);
                }
                p = pix[vmin[x] + yw];
                sir[0] = (16711680 & p) >> 16;
                sir[1] = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & p) >> 8;
                sir[2] = p & avutil.AV_CH_LAYOUT_7POINT1_WIDE_BACK;
                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];
                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;
                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer % div];
                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];
                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];
                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            bsum = 0;
            gsum = 0;
            rsum = 0;
            boutsum = 0;
            goutsum = 0;
            routsum = 0;
            binsum = 0;
            ginsum = 0;
            rinsum = 0;
            int yp = (-radius) * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;
                sir = stack[i + radius];
                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];
                rbs = r1 - Math.abs(i);
                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                pix[yi] = (((ViewCompat.MEASURED_STATE_MASK & pix[yi]) | (dv[rsum] << 16)) | (dv[gsum] << 8)) | dv[bsum];
                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;
                sir = stack[((stackpointer - radius) + div) % div];
                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];
                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];
                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];
                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];
                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;
                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];
                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];
                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];
                yi += w;
            }
        }
        bitmap.setPixels(pix, 0, w, 0, 0, w, h);
        return bitmap;
    }

    public static void recycle(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    public static byte[] rotateNV21Degree90(byte[] data, int imageWidth, int imageHeight) {
        int x;
        byte[] yuv = new byte[(((imageWidth * imageHeight) * 3) / 2)];
        int i = 0;
        for (x = 0; x < imageWidth; x++) {
            int y;
            for (y = imageHeight - 1; y >= 0; y--) {
                yuv[i] = data[(y * imageWidth) + x];
                i++;
            }
        }
        i = (((imageWidth * imageHeight) * 3) / 2) - 1;
        for (x = imageWidth - 1; x > 0; x -= 2) {
            for (y = 0; y < imageHeight / 2; y++) {
                yuv[i] = data[((imageWidth * imageHeight) + (y * imageWidth)) + x];
                i--;
                yuv[i] = data[((imageWidth * imageHeight) + (y * imageWidth)) + (x - 1)];
                i--;
            }
        }
        return yuv;
    }

    public static byte[] rotateNV21Degree90AndHorizontalFlip(byte[] data, int imageWidth, int imageHeight) {
        int x;
        byte[] yuv = new byte[(((imageWidth * imageHeight) * 3) / 2)];
        int i = 0;
        for (x = 0; x < imageWidth; x++) {
            int y;
            for (y = 0; y < imageHeight; y++) {
                yuv[i] = data[(y * imageWidth) + x];
                i++;
            }
        }
        i = (((imageWidth * imageHeight) * 3) / 2) - 1;
        for (x = imageWidth - 1; x > 0; x -= 2) {
            for (y = (imageHeight / 2) - 1; y >= 0; y--) {
                yuv[i] = data[((imageWidth * imageHeight) + (y * imageWidth)) + x];
                i--;
                yuv[i] = data[((imageWidth * imageHeight) + (y * imageWidth)) + (x - 1)];
                i--;
            }
        }
        return yuv;
    }

    public static byte[] rotateNV21Degree270(byte[] data, int imageWidth, int imageHeight) {
        int x;
        int y;
        byte[] yuv = new byte[(((imageWidth * imageHeight) * 3) / 2)];
        int i = 0;
        for (x = imageWidth - 1; x >= 0; x--) {
            for (y = 0; y < imageHeight; y++) {
                yuv[i] = data[(y * imageWidth) + x];
                i++;
            }
        }
        i = (imageWidth * imageHeight) - 1;
        for (x = imageWidth - 1; x > 0; x -= 2) {
            for (y = 0; y < imageHeight / 2; y++) {
                yuv[i] = data[((imageWidth * imageHeight) + (y * imageWidth)) + x];
                i++;
                yuv[i] = data[((imageWidth * imageHeight) + (y * imageWidth)) + (x - 1)];
                i++;
            }
        }
        return yuv;
    }

    public static byte[] rotateNV21Degree270AndHorizontalFlip(byte[] data, int imageWidth, int imageHeight) {
        int x;
        byte[] yuv = new byte[(((imageWidth * imageHeight) * 3) / 2)];
        int i = 0;
        for (x = imageWidth - 1; x >= 0; x--) {
            int y;
            for (y = imageHeight - 1; y >= 0; y--) {
                yuv[i] = data[(y * imageWidth) + x];
                i++;
            }
        }
        i = (imageWidth * imageHeight) - 1;
        for (x = imageWidth - 1; x > 0; x -= 2) {
            for (y = (imageHeight / 2) - 1; y >= 0; y--) {
                yuv[i] = data[((imageWidth * imageHeight) + (y * imageWidth)) + x];
                i++;
                yuv[i] = data[((imageWidth * imageHeight) + (y * imageWidth)) + (x - 1)];
                i++;
            }
        }
        return yuv;
    }
}
