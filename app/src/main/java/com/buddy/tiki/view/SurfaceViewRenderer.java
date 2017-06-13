package com.buddy.tiki.view;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Point;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.recyclerview.BuildConfig;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View.MeasureSpec;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.postproc;
import org.bytedeco.javacpp.swscale;
import org.webrtc.EglBase;
import org.webrtc.GlRectDrawer;
import org.webrtc.Logging;
import org.webrtc.RendererCommon;
import org.webrtc.RendererCommon.GlDrawer;
import org.webrtc.RendererCommon.RendererEvents;
import org.webrtc.RendererCommon.ScalingType;
import org.webrtc.RendererCommon.YuvUploader;
import org.webrtc.VideoRenderer;
import org.webrtc.VideoRenderer.Callbacks;
import org.webrtc.VideoRenderer.I420Frame;

public class SurfaceViewRenderer extends SurfaceView implements Callback, Callbacks {
    private long f2832A;
    private ConcurrentLinkedQueue<String> f2833B;
    private CaptureCallBack f2834C;
    private final Runnable f2835D;
    private final Object f2836a;
    private final YuvUploader f2837b;
    private final Object f2838c;
    private final Object f2839d;
    private final Point f2840e;
    private final Point f2841f;
    private final Object f2842g;
    private HandlerThread f2843h;
    private Handler f2844i;
    private EglBase f2845j;
    private final Runnable f2846k;
    private GlDrawer f2847l;
    private int[] f2848m;
    private I420Frame f2849n;
    private Point f2850o;
    private boolean f2851p;
    private int f2852q;
    private int f2853r;
    private int f2854s;
    private ScalingType f2855t;
    private boolean f2856u;
    private RendererEvents f2857v;
    private int f2858w;
    private int f2859x;
    private int f2860y;
    private long f2861z;

    public interface CaptureCallBack {
        void captureBmp(Bitmap bitmap);
    }

    public SurfaceViewRenderer(Context context) {
        super(context);
        this.f2836a = new Object();
        this.f2837b = new YuvUploader();
        this.f2838c = new Object();
        this.f2839d = new Object();
        this.f2840e = new Point();
        this.f2841f = new Point();
        this.f2842g = new Object();
        this.f2846k = SurfaceViewRenderer$$Lambda$1.lambdaFactory$(this);
        this.f2848m = null;
        this.f2850o = new Point();
        this.f2855t = ScalingType.SCALE_ASPECT_BALANCED;
        this.f2833B = new ConcurrentLinkedQueue();
        this.f2835D = SurfaceViewRenderer$$Lambda$2.lambdaFactory$(this);
        getHolder().addCallback(this);
    }

    public SurfaceViewRenderer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.f2836a = new Object();
        this.f2837b = new YuvUploader();
        this.f2838c = new Object();
        this.f2839d = new Object();
        this.f2840e = new Point();
        this.f2841f = new Point();
        this.f2842g = new Object();
        this.f2846k = SurfaceViewRenderer$$Lambda$3.lambdaFactory$(this);
        this.f2848m = null;
        this.f2850o = new Point();
        this.f2855t = ScalingType.SCALE_ASPECT_BALANCED;
        this.f2833B = new ConcurrentLinkedQueue();
        this.f2835D = SurfaceViewRenderer$$Lambda$4.lambdaFactory$(this);
        getHolder().addCallback(this);
    }

    public void init(EglBase.Context sharedContext, RendererEvents rendererEvents) {
        init(sharedContext, rendererEvents, EglBase.CONFIG_PLAIN, new GlRectDrawer());
    }

    public void init(EglBase.Context sharedContext, RendererEvents rendererEvents, int[] configAttributes, GlDrawer drawer) {
        synchronized (this.f2836a) {
            if (this.f2844i != null) {
                throw new IllegalStateException(getResourceName() + "Already initialized");
            }
            Logging.d("SurfaceViewRenderer", getResourceName() + "Initializing.");
            this.f2857v = rendererEvents;
            this.f2847l = drawer;
            this.f2843h = new HandlerThread("SurfaceViewRenderer");
            this.f2843h.start();
            this.f2845j = EglBase.create(sharedContext, configAttributes);
            this.f2844i = new Handler(this.f2843h.getLooper());
        }
        tryCreateEglSurface();
    }

    public void tryCreateEglSurface() {
        m1768a(SurfaceViewRenderer$$Lambda$5.lambdaFactory$(this));
    }

    /* synthetic */ void m1781b() {
        synchronized (this.f2839d) {
            if (!(this.f2845j == null || !this.f2851p || this.f2845j.hasSurface())) {
                this.f2845j.createSurface(getHolder().getSurface());
                this.f2845j.makeCurrent();
                GLES20.glPixelStorei(3317, 1);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void release() {
        /*
        r5 = this;
        r4 = 0;
        r0 = new java.util.concurrent.CountDownLatch;
        r1 = 1;
        r0.<init>(r1);
        r2 = r5.f2836a;
        monitor-enter(r2);
        r1 = r5.f2844i;	 Catch:{ all -> 0x0050 }
        if (r1 != 0) goto L_0x0010;
    L_0x000e:
        monitor-exit(r2);	 Catch:{ all -> 0x0050 }
    L_0x000f:
        return;
    L_0x0010:
        r1 = r5.f2844i;	 Catch:{ all -> 0x0050 }
        r3 = com.buddy.tiki.view.SurfaceViewRenderer$$Lambda$6.lambdaFactory$(r5, r0);	 Catch:{ all -> 0x0050 }
        r1.postAtFrontOfQueue(r3);	 Catch:{ all -> 0x0050 }
        r1 = 0;
        r5.f2844i = r1;	 Catch:{ all -> 0x0050 }
        monitor-exit(r2);	 Catch:{ all -> 0x0050 }
        org.webrtc.ThreadUtils.awaitUninterruptibly(r0);
        r1 = r5.f2843h;
        r1.quit();
        r2 = r5.f2838c;
        monitor-enter(r2);
        r1 = r5.f2849n;	 Catch:{ all -> 0x0053 }
        if (r1 == 0) goto L_0x0034;
    L_0x002c:
        r1 = r5.f2849n;	 Catch:{ all -> 0x0053 }
        org.webrtc.VideoRenderer.renderFrameDone(r1);	 Catch:{ all -> 0x0053 }
        r1 = 0;
        r5.f2849n = r1;	 Catch:{ all -> 0x0053 }
    L_0x0034:
        monitor-exit(r2);	 Catch:{ all -> 0x0053 }
        r1 = r5.f2843h;
        org.webrtc.ThreadUtils.joinUninterruptibly(r1);
        r5.f2843h = r4;
        r2 = r5.f2839d;
        monitor-enter(r2);
        r1 = 0;
        r5.f2852q = r1;	 Catch:{ all -> 0x0056 }
        r1 = 0;
        r5.f2853r = r1;	 Catch:{ all -> 0x0056 }
        r1 = 0;
        r5.f2854s = r1;	 Catch:{ all -> 0x0056 }
        r1 = 0;
        r5.f2857v = r1;	 Catch:{ all -> 0x0056 }
        monitor-exit(r2);	 Catch:{ all -> 0x0056 }
        r5.resetStatistics();
        goto L_0x000f;
    L_0x0050:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0050 }
        throw r1;
    L_0x0053:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0053 }
        throw r1;
    L_0x0056:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0056 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.buddy.tiki.view.SurfaceViewRenderer.release():void");
    }

    /* synthetic */ void m1780a(CountDownLatch eglCleanupBarrier) {
        this.f2847l.release();
        this.f2847l = null;
        if (this.f2848m != null) {
            GLES20.glDeleteTextures(3, this.f2848m, 0);
            this.f2848m = null;
        }
        m1771c();
        this.f2845j.release();
        this.f2845j = null;
        eglCleanupBarrier.countDown();
    }

    public void resetStatistics() {
        synchronized (this.f2842g) {
            this.f2858w = 0;
            this.f2859x = 0;
            this.f2860y = 0;
            this.f2861z = 0;
            this.f2832A = 0;
        }
    }

    public void setMirror(boolean mirror) {
        synchronized (this.f2839d) {
            this.f2856u = mirror;
        }
    }

    public void setScalingType(ScalingType scalingType) {
        synchronized (this.f2839d) {
            this.f2855t = scalingType;
        }
    }

    public void renderFrame(I420Frame frame) {
        synchronized (this.f2842g) {
            this.f2858w++;
        }
        synchronized (this.f2836a) {
            if (this.f2844i == null) {
                VideoRenderer.renderFrameDone(frame);
                return;
            }
            synchronized (this.f2838c) {
                if (this.f2849n != null) {
                    synchronized (this.f2842g) {
                        this.f2859x++;
                    }
                    VideoRenderer.renderFrameDone(this.f2849n);
                }
                this.f2849n = frame;
                this.f2844i.post(this.f2835D);
            }
        }
    }

    private Point m1766a(int widthSpec, int heightSpec) {
        Point size;
        synchronized (this.f2839d) {
            int maxWidth = getDefaultSize(ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, widthSpec);
            int maxHeight = getDefaultSize(ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, heightSpec);
            size = RendererCommon.getDisplaySize(this.f2855t, m1776f(), maxWidth, maxHeight);
            if (MeasureSpec.getMode(widthSpec) == postproc.PP_CPU_CAPS_3DNOW) {
                size.x = maxWidth;
            }
            if (MeasureSpec.getMode(heightSpec) == postproc.PP_CPU_CAPS_3DNOW) {
                size.y = maxHeight;
            }
        }
        return size;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onMeasure(int r5, int r6) {
        /*
        r4 = this;
        r2 = r4.f2839d;
        monitor-enter(r2);
        r1 = r4.f2852q;	 Catch:{ all -> 0x004e }
        if (r1 == 0) goto L_0x000b;
    L_0x0007:
        r1 = r4.f2853r;	 Catch:{ all -> 0x004e }
        if (r1 != 0) goto L_0x0010;
    L_0x000b:
        super.onMeasure(r5, r6);	 Catch:{ all -> 0x004e }
        monitor-exit(r2);	 Catch:{ all -> 0x004e }
    L_0x000f:
        return;
    L_0x0010:
        r1 = r4.m1766a(r5, r6);	 Catch:{ all -> 0x004e }
        r4.f2850o = r1;	 Catch:{ all -> 0x004e }
        r1 = r4.f2850o;	 Catch:{ all -> 0x004e }
        r1 = r1.x;	 Catch:{ all -> 0x004e }
        r3 = r4.getMeasuredWidth();	 Catch:{ all -> 0x004e }
        if (r1 != r3) goto L_0x002a;
    L_0x0020:
        r1 = r4.f2850o;	 Catch:{ all -> 0x004e }
        r1 = r1.y;	 Catch:{ all -> 0x004e }
        r3 = r4.getMeasuredHeight();	 Catch:{ all -> 0x004e }
        if (r1 == r3) goto L_0x004c;
    L_0x002a:
        r0 = 1;
    L_0x002b:
        r1 = r4.f2850o;	 Catch:{ all -> 0x004e }
        r1 = r1.x;	 Catch:{ all -> 0x004e }
        r3 = r4.f2850o;	 Catch:{ all -> 0x004e }
        r3 = r3.y;	 Catch:{ all -> 0x004e }
        r4.setMeasuredDimension(r1, r3);	 Catch:{ all -> 0x004e }
        monitor-exit(r2);	 Catch:{ all -> 0x004e }
        if (r0 == 0) goto L_0x000f;
    L_0x0039:
        r2 = r4.f2836a;
        monitor-enter(r2);
        r1 = r4.f2844i;	 Catch:{ all -> 0x0049 }
        if (r1 == 0) goto L_0x0047;
    L_0x0040:
        r1 = r4.f2844i;	 Catch:{ all -> 0x0049 }
        r3 = r4.f2846k;	 Catch:{ all -> 0x0049 }
        r1.postAtFrontOfQueue(r3);	 Catch:{ all -> 0x0049 }
    L_0x0047:
        monitor-exit(r2);	 Catch:{ all -> 0x0049 }
        goto L_0x000f;
    L_0x0049:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0049 }
        throw r1;
    L_0x004c:
        r0 = 0;
        goto L_0x002b;
    L_0x004e:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x004e }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.buddy.tiki.view.SurfaceViewRenderer.onMeasure(int, int):void");
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        synchronized (this.f2839d) {
            this.f2840e.x = right - left;
            this.f2840e.y = bottom - top;
        }
        m1768a(this.f2835D);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        synchronized (this.f2839d) {
            this.f2851p = true;
        }
        tryCreateEglSurface();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        synchronized (this.f2839d) {
            this.f2851p = false;
            this.f2841f.x = 0;
            this.f2841f.y = 0;
        }
        m1768a(SurfaceViewRenderer$$Lambda$7.lambdaFactory$(this));
    }

    /* synthetic */ void m1779a() {
        if (this.f2845j != null) {
            this.f2845j.detachCurrent();
            this.f2845j.releaseSurface();
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        synchronized (this.f2839d) {
            this.f2841f.x = width;
            this.f2841f.y = height;
        }
        m1768a(this.f2835D);
    }

    private void m1768a(Runnable runnable) {
        synchronized (this.f2836a) {
            if (this.f2844i != null) {
                this.f2844i.post(runnable);
            }
        }
    }

    private String getResourceName() {
        try {
            return getResources().getResourceEntryName(getId()) + ": ";
        } catch (NotFoundException e) {
            return BuildConfig.VERSION_NAME;
        }
    }

    private void m1771c() {
        if (Thread.currentThread() != this.f2843h) {
            throw new IllegalStateException(getResourceName() + "Wrong thread.");
        } else if (this.f2845j != null && this.f2845j.hasSurface()) {
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            GLES20.glClear(swscale.SWS_FULL_CHR_H_INP);
            this.f2845j.swapBuffers();
        }
    }

    private boolean m1774d() {
        if (Thread.currentThread() != this.f2843h) {
            throw new IllegalStateException(getResourceName() + "Wrong thread.");
        }
        boolean z;
        synchronized (this.f2839d) {
            z = this.f2840e.equals(this.f2850o) && this.f2841f.equals(this.f2840e);
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1775e() {
        /*
        r20 = this;
        r4 = java.lang.Thread.currentThread();
        r0 = r20;
        r5 = r0.f2843h;
        if (r4 == r5) goto L_0x0027;
    L_0x000a:
        r4 = new java.lang.IllegalStateException;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = r20.getResourceName();
        r5 = r5.append(r6);
        r6 = "Wrong thread.";
        r5 = r5.append(r6);
        r5 = r5.toString();
        r4.<init>(r5);
        throw r4;
    L_0x0027:
        r0 = r20;
        r5 = r0.f2838c;
        monitor-enter(r5);
        r0 = r20;
        r4 = r0.f2849n;	 Catch:{ all -> 0x0057 }
        if (r4 != 0) goto L_0x0034;
    L_0x0032:
        monitor-exit(r5);	 Catch:{ all -> 0x0057 }
    L_0x0033:
        return;
    L_0x0034:
        r0 = r20;
        r13 = r0.f2849n;	 Catch:{ all -> 0x0057 }
        r4 = 0;
        r0 = r20;
        r0.f2849n = r4;	 Catch:{ all -> 0x0057 }
        monitor-exit(r5);	 Catch:{ all -> 0x0057 }
        r0 = r20;
        r0.m1769a(r13);
        r0 = r20;
        r4 = r0.f2845j;
        if (r4 == 0) goto L_0x0053;
    L_0x0049:
        r0 = r20;
        r4 = r0.f2845j;
        r4 = r4.hasSurface();
        if (r4 != 0) goto L_0x005a;
    L_0x0053:
        org.webrtc.VideoRenderer.renderFrameDone(r13);
        goto L_0x0033;
    L_0x0057:
        r4 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0057 }
        throw r4;
    L_0x005a:
        r4 = r20.m1774d();
        if (r4 != 0) goto L_0x0067;
    L_0x0060:
        r20.m1771c();
        org.webrtc.VideoRenderer.renderFrameDone(r13);
        goto L_0x0033;
    L_0x0067:
        r0 = r20;
        r5 = r0.f2839d;
        monitor-enter(r5);
        r0 = r20;
        r4 = r0.f2845j;	 Catch:{ all -> 0x00ed }
        r4 = r4.surfaceWidth();	 Catch:{ all -> 0x00ed }
        r0 = r20;
        r6 = r0.f2841f;	 Catch:{ all -> 0x00ed }
        r6 = r6.x;	 Catch:{ all -> 0x00ed }
        if (r4 != r6) goto L_0x008c;
    L_0x007c:
        r0 = r20;
        r4 = r0.f2845j;	 Catch:{ all -> 0x00ed }
        r4 = r4.surfaceHeight();	 Catch:{ all -> 0x00ed }
        r0 = r20;
        r6 = r0.f2841f;	 Catch:{ all -> 0x00ed }
        r6 = r6.y;	 Catch:{ all -> 0x00ed }
        if (r4 == r6) goto L_0x008f;
    L_0x008c:
        r20.m1771c();	 Catch:{ all -> 0x00ed }
    L_0x008f:
        monitor-exit(r5);	 Catch:{ all -> 0x00ed }
        r18 = java.lang.System.nanoTime();
        r0 = r20;
        r5 = r0.f2839d;
        monitor-enter(r5);
        r4 = r13.samplingMatrix;	 Catch:{ all -> 0x00f0 }
        r6 = r13.rotationDegree;	 Catch:{ all -> 0x00f0 }
        r6 = (float) r6;	 Catch:{ all -> 0x00f0 }
        r16 = org.webrtc.RendererCommon.rotateTextureMatrix(r4, r6);	 Catch:{ all -> 0x00f0 }
        r0 = r20;
        r4 = r0.f2856u;	 Catch:{ all -> 0x00f0 }
        r6 = r20.m1776f();	 Catch:{ all -> 0x00f0 }
        r0 = r20;
        r7 = r0.f2840e;	 Catch:{ all -> 0x00f0 }
        r7 = r7.x;	 Catch:{ all -> 0x00f0 }
        r7 = (float) r7;	 Catch:{ all -> 0x00f0 }
        r0 = r20;
        r8 = r0.f2840e;	 Catch:{ all -> 0x00f0 }
        r8 = r8.y;	 Catch:{ all -> 0x00f0 }
        r8 = (float) r8;	 Catch:{ all -> 0x00f0 }
        r7 = r7 / r8;
        r15 = org.webrtc.RendererCommon.getLayoutMatrix(r4, r6, r7);	 Catch:{ all -> 0x00f0 }
        r0 = r16;
        r17 = org.webrtc.RendererCommon.multiplyMatrices(r0, r15);	 Catch:{ all -> 0x00f0 }
        monitor-exit(r5);	 Catch:{ all -> 0x00f0 }
        r4 = 16384; // 0x4000 float:2.2959E-41 double:8.0948E-320;
        android.opengl.GLES20.glClear(r4);
        r4 = r13.yuvFrame;
        if (r4 == 0) goto L_0x01a5;
    L_0x00cd:
        r0 = r20;
        r4 = r0.f2848m;
        if (r4 != 0) goto L_0x00f3;
    L_0x00d3:
        r4 = 3;
        r4 = new int[r4];
        r0 = r20;
        r0.f2848m = r4;
        r14 = 0;
    L_0x00db:
        r4 = 3;
        if (r14 >= r4) goto L_0x00f3;
    L_0x00de:
        r0 = r20;
        r4 = r0.f2848m;
        r5 = 3553; // 0xde1 float:4.979E-42 double:1.7554E-320;
        r5 = org.webrtc.GlUtil.generateTexture(r5);
        r4[r14] = r5;
        r14 = r14 + 1;
        goto L_0x00db;
    L_0x00ed:
        r4 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x00ed }
        throw r4;
    L_0x00f0:
        r4 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x00f0 }
        throw r4;
    L_0x00f3:
        r0 = r20;
        r4 = r0.f2837b;
        r0 = r20;
        r5 = r0.f2848m;
        r6 = r13.width;
        r7 = r13.height;
        r8 = r13.yuvStrides;
        r9 = r13.yuvPlanes;
        r4.uploadYuvData(r5, r6, r7, r8, r9);
        r0 = r20;
        r4 = r0.f2847l;
        r0 = r20;
        r5 = r0.f2848m;
        r7 = r13.rotatedWidth();
        r8 = r13.rotatedHeight();
        r9 = 0;
        r10 = 0;
        r0 = r20;
        r6 = r0.f2841f;
        r11 = r6.x;
        r0 = r20;
        r6 = r0.f2841f;
        r12 = r6.y;
        r6 = r17;
        r4.drawYuv(r5, r6, r7, r8, r9, r10, r11, r12);
    L_0x0129:
        r0 = r20;
        r4 = r0.f2845j;
        r4.swapBuffers();
        org.webrtc.VideoRenderer.renderFrameDone(r13);
        r0 = r20;
        r5 = r0.f2842g;
        monitor-enter(r5);
        r0 = r20;
        r4 = r0.f2860y;	 Catch:{ all -> 0x01cb }
        if (r4 != 0) goto L_0x0157;
    L_0x013e:
        r0 = r18;
        r2 = r20;
        r2.f2861z = r0;	 Catch:{ all -> 0x01cb }
        r0 = r20;
        r6 = r0.f2839d;	 Catch:{ all -> 0x01cb }
        monitor-enter(r6);	 Catch:{ all -> 0x01cb }
        r0 = r20;
        r4 = r0.f2857v;	 Catch:{ all -> 0x01c8 }
        if (r4 == 0) goto L_0x0156;
    L_0x014f:
        r0 = r20;
        r4 = r0.f2857v;	 Catch:{ all -> 0x01c8 }
        r4.onFirstFrameRendered();	 Catch:{ all -> 0x01c8 }
    L_0x0156:
        monitor-exit(r6);	 Catch:{ all -> 0x01c8 }
    L_0x0157:
        r0 = r20;
        r4 = r0.f2860y;	 Catch:{ all -> 0x01cb }
        r4 = r4 + 1;
        r0 = r20;
        r0.f2860y = r4;	 Catch:{ all -> 0x01cb }
        r0 = r20;
        r6 = r0.f2832A;	 Catch:{ all -> 0x01cb }
        r8 = java.lang.System.nanoTime();	 Catch:{ all -> 0x01cb }
        r8 = r8 - r18;
        r6 = r6 + r8;
        r0 = r20;
        r0.f2832A = r6;	 Catch:{ all -> 0x01cb }
        r0 = r20;
        r4 = r0.f2860y;	 Catch:{ all -> 0x01cb }
        r4 = r4 % 300;
        if (r4 != 0) goto L_0x017b;
    L_0x0178:
        r20.m1777g();	 Catch:{ all -> 0x01cb }
    L_0x017b:
        monitor-exit(r5);	 Catch:{ all -> 0x01cb }
        r0 = r20;
        r4 = r0.f2833B;
        r4 = r4.isEmpty();
        if (r4 != 0) goto L_0x0033;
    L_0x0186:
        r0 = r20;
        r4 = r0.f2833B;
        r4.poll();
        r0 = r20;
        r4 = r0.f2834C;
        if (r4 == 0) goto L_0x0033;
    L_0x0193:
        r0 = r20;
        r4 = r0.f2834C;
        r5 = r20.m1778h();
        r4.captureBmp(r5);
        r4 = 0;
        r0 = r20;
        r0.f2834C = r4;
        goto L_0x0033;
    L_0x01a5:
        r0 = r20;
        r4 = r0.f2847l;
        r5 = r13.textureId;
        r7 = r13.rotatedWidth();
        r8 = r13.rotatedHeight();
        r9 = 0;
        r10 = 0;
        r0 = r20;
        r6 = r0.f2841f;
        r11 = r6.x;
        r0 = r20;
        r6 = r0.f2841f;
        r12 = r6.y;
        r6 = r17;
        r4.drawOes(r5, r6, r7, r8, r9, r10, r11, r12);
        goto L_0x0129;
    L_0x01c8:
        r4 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x01c8 }
        throw r4;	 Catch:{ all -> 0x01cb }
    L_0x01cb:
        r4 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x01cb }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.buddy.tiki.view.SurfaceViewRenderer.e():void");
    }

    private float m1776f() {
        float f;
        synchronized (this.f2839d) {
            if (this.f2852q == 0 || this.f2853r == 0) {
                f = 0.0f;
            } else {
                f = this.f2854s % avcodec.AV_CODEC_ID_VP7 == 0 ? ((float) this.f2852q) / ((float) this.f2853r) : ((float) this.f2853r) / ((float) this.f2852q);
            }
        }
        return f;
    }

    private void m1769a(I420Frame frame) {
        synchronized (this.f2839d) {
            if (!(this.f2852q == frame.width && this.f2853r == frame.height && this.f2854s == frame.rotationDegree)) {
                if (this.f2857v != null) {
                    this.f2857v.onFrameResolutionChanged(frame.width, frame.height, frame.rotationDegree);
                }
                this.f2852q = frame.width;
                this.f2853r = frame.height;
                this.f2854s = frame.rotationDegree;
                post(SurfaceViewRenderer$$Lambda$8.lambdaFactory$(this));
            }
        }
    }

    private void m1777g() {
    }

    public void capture(CaptureCallBack callback) {
        this.f2833B.add(UUID.randomUUID().toString());
        this.f2834C = callback;
    }

    private Bitmap m1778h() {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int screenshotSize = width * height;
        ByteBuffer bb = ByteBuffer.allocateDirect(screenshotSize * 4);
        bb.order(ByteOrder.nativeOrder());
        GLES20.glReadPixels(0, 0, width, height, 6408, 5121, bb);
        int[] pixelBuffer = new int[screenshotSize];
        bb.asIntBuffer().get(pixelBuffer);
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.RGB_565);
        bitmap.setPixels(pixelBuffer, screenshotSize - width, -width, 0, 0, width, height);
        short[] sBuffer = new short[screenshotSize];
        Buffer sb = ShortBuffer.wrap(sBuffer);
        bitmap.copyPixelsToBuffer(sb);
        for (int i = 0; i < screenshotSize; i++) {
            short v = sBuffer[i];
            sBuffer[i] = (short) ((((v & 31) << 11) | (v & 2016)) | ((63488 & v) >> 11));
        }
        sb.rewind();
        bitmap.copyPixelsFromBuffer(sb);
        return bitmap;
    }
}
