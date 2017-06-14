package im.facechat.view;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Point;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View.MeasureSpec;
import im.facechat.common.p045b.C1639b;
import im.facechat.common.protocol.FacechatCapturer.TextureCallback;
import io.netty.handler.codec.http2.Http2CodecUtil;
import io.netty.util.internal.shaded.org.jctools.util.Pow2;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
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
import tv.danmaku.ijk.media.player.BuildConfig;

public class FCSurfaceView extends SurfaceView implements Callback, TextureCallback, Callbacks {
    private long f11519A;
    private long f11520B;
    private ConcurrentLinkedQueue<String> f11521C = new ConcurrentLinkedQueue();
    private CaptureCallBack f11522D;
    private boolean f11523E = false;
    private RgbFrame f11524F;
    private final Runnable f11525G = new C17332(this);
    Runnable f11526a;
    private final Object f11527b = new Object();
    private final YuvUploader f11528c = new YuvUploader();
    private final Object f11529d = new Object();
    private final Object f11530e = new Object();
    private final Point f11531f = new Point();
    private final Point f11532g = new Point();
    private final Object f11533h = new Object();
    private HandlerThread f11534i;
    private Handler f11535j;
    private EglBase f11536k;
    private final Runnable f11537l = new C17321(this);
    private GlDrawer f11538m;
    private int[] f11539n = null;
    private I420Frame f11540o;
    private Point f11541p = new Point();
    private boolean f11542q;
    private int f11543r = 1280;
    private int f11544s = 720;
    private int f11545t = 90;
    private ScalingType f11546u = ScalingType.SCALE_ASPECT_BALANCED;
    private boolean f11547v;
    private RendererEvents f11548w;
    private int f11549x;
    private int f11550y;
    private int f11551z;

    class C17321 implements Runnable {
        final /* synthetic */ FCSurfaceView f11512a;

        C17321(FCSurfaceView fCSurfaceView) {
            this.f11512a = fCSurfaceView;
        }

        public void run() {
            this.f11512a.m8229a();
        }
    }

    class C17332 implements Runnable {
        final /* synthetic */ FCSurfaceView f11513a;

        C17332(FCSurfaceView fCSurfaceView) {
            this.f11513a = fCSurfaceView;
        }

        public void run() {
            this.f11513a.m8237c();
        }
    }

    class C17343 implements Runnable {
        final /* synthetic */ FCSurfaceView f11514a;

        C17343(FCSurfaceView fCSurfaceView) {
            this.f11514a = fCSurfaceView;
        }

        public void run() {
            synchronized (this.f11514a.f11530e) {
                if (!(this.f11514a.f11536k == null || !this.f11514a.f11542q || this.f11514a.f11536k.hasSurface())) {
                    this.f11514a.f11536k.createSurface(this.f11514a.getHolder().getSurface());
                    this.f11514a.f11536k.makeCurrent();
                    GLES20.glPixelStorei(3317, 1);
                }
            }
        }
    }

    class C17365 implements Runnable {
        final /* synthetic */ FCSurfaceView f11517a;

        C17365(FCSurfaceView fCSurfaceView) {
            this.f11517a = fCSurfaceView;
        }

        public void run() {
            if (this.f11517a.f11536k != null) {
                this.f11517a.f11536k.detachCurrent();
                this.f11517a.f11536k.releaseSurface();
            }
        }
    }

    class C17376 implements Runnable {
        final /* synthetic */ FCSurfaceView f11518a;

        C17376(FCSurfaceView fCSurfaceView) {
            this.f11518a = fCSurfaceView;
        }

        public void run() {
            this.f11518a.requestLayout();
        }
    }

    public interface CaptureCallBack {
        void captureBmp(Bitmap bitmap);
    }

    public FCSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    public FCSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        getHolder().addCallback(this);
    }

    public void init(EglBase.Context context, RendererEvents rendererEvents) {
        init(context, rendererEvents, EglBase.CONFIG_PLAIN, new GlRectDrawer());
    }

    public void init(EglBase.Context context, RendererEvents rendererEvents, int[] iArr, GlDrawer glDrawer) {
        synchronized (this.f11527b) {
            if (this.f11535j != null) {
                throw new IllegalStateException(getResourceName() + "Already initialized");
            }
            Logging.m9015d("FCSurfaceView", getResourceName() + "Initializing.");
            this.f11548w = rendererEvents;
            this.f11538m = glDrawer;
            this.f11534i = new HandlerThread("FCSurfaceView");
            this.f11534i.start();
            this.f11536k = EglBase.create(context, iArr);
            this.f11535j = new Handler(this.f11534i.getLooper());
        }
        tryCreateEglSurface();
    }

    public void tryCreateEglSurface() {
        m8231a(new C17343(this));
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
        r1 = r5.f11527b;
        monitor-enter(r1);
        r2 = r5.f11535j;	 Catch:{ all -> 0x006d }
        if (r2 != 0) goto L_0x002c;
    L_0x000e:
        r0 = "FCSurfaceView";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x006d }
        r2.<init>();	 Catch:{ all -> 0x006d }
        r3 = r5.getResourceName();	 Catch:{ all -> 0x006d }
        r2 = r2.append(r3);	 Catch:{ all -> 0x006d }
        r3 = "Already released";
        r2 = r2.append(r3);	 Catch:{ all -> 0x006d }
        r2 = r2.toString();	 Catch:{ all -> 0x006d }
        im.facechat.common.p045b.C1639b.m7918a(r0, r2);	 Catch:{ all -> 0x006d }
        monitor-exit(r1);	 Catch:{ all -> 0x006d }
    L_0x002b:
        return;
    L_0x002c:
        r2 = r5.f11535j;	 Catch:{ all -> 0x006d }
        r3 = new im.facechat.view.FCSurfaceView$4;	 Catch:{ all -> 0x006d }
        r3.<init>(r5, r0);	 Catch:{ all -> 0x006d }
        r2.postAtFrontOfQueue(r3);	 Catch:{ all -> 0x006d }
        r2 = 0;
        r5.f11535j = r2;	 Catch:{ all -> 0x006d }
        monitor-exit(r1);	 Catch:{ all -> 0x006d }
        org.webrtc.ThreadUtils.awaitUninterruptibly(r0);
        r0 = r5.f11534i;
        r0.quit();
        r1 = r5.f11529d;
        monitor-enter(r1);
        r0 = r5.f11540o;	 Catch:{ all -> 0x0070 }
        if (r0 == 0) goto L_0x0051;
    L_0x0049:
        r0 = r5.f11540o;	 Catch:{ all -> 0x0070 }
        org.webrtc.VideoRenderer.renderFrameDone(r0);	 Catch:{ all -> 0x0070 }
        r0 = 0;
        r5.f11540o = r0;	 Catch:{ all -> 0x0070 }
    L_0x0051:
        monitor-exit(r1);	 Catch:{ all -> 0x0070 }
        r0 = r5.f11534i;
        org.webrtc.ThreadUtils.joinUninterruptibly(r0);
        r5.f11534i = r4;
        r1 = r5.f11530e;
        monitor-enter(r1);
        r0 = 0;
        r5.f11543r = r0;	 Catch:{ all -> 0x0073 }
        r0 = 0;
        r5.f11544s = r0;	 Catch:{ all -> 0x0073 }
        r0 = 0;
        r5.f11545t = r0;	 Catch:{ all -> 0x0073 }
        r0 = 0;
        r5.f11548w = r0;	 Catch:{ all -> 0x0073 }
        monitor-exit(r1);	 Catch:{ all -> 0x0073 }
        r5.resetStatistics();
        goto L_0x002b;
    L_0x006d:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x006d }
        throw r0;
    L_0x0070:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0070 }
        throw r0;
    L_0x0073:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0073 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: im.facechat.view.FCSurfaceView.release():void");
    }

    public void resetStatistics() {
        synchronized (this.f11533h) {
            this.f11549x = 0;
            this.f11550y = 0;
            this.f11551z = 0;
            this.f11519A = 0;
            this.f11520B = 0;
        }
    }

    public boolean getMirror() {
        boolean z;
        synchronized (this.f11530e) {
            z = this.f11547v;
        }
        return z;
    }

    public void setMirror(boolean z) {
        synchronized (this.f11530e) {
            this.f11547v = z;
        }
    }

    public void toggleMirror() {
        synchronized (this.f11530e) {
            this.f11547v = !this.f11547v;
        }
    }

    public void setScalingType(ScalingType scalingType) {
        synchronized (this.f11530e) {
            this.f11546u = scalingType;
        }
    }

    public void renderFrame(I420Frame i420Frame) {
        synchronized (this.f11533h) {
            this.f11549x++;
        }
        synchronized (this.f11527b) {
            if (this.f11535j == null) {
                C1639b.m7918a("FCSurfaceView", getResourceName() + "Dropping frame - Not initialized or already released.");
                VideoRenderer.renderFrameDone(i420Frame);
                return;
            }
            synchronized (this.f11529d) {
                if (this.f11540o != null) {
                    synchronized (this.f11533h) {
                        this.f11550y++;
                    }
                    VideoRenderer.renderFrameDone(this.f11540o);
                }
                this.f11540o = null;
                if (this.f11523E) {
                    VideoRenderer.renderFrameDone(i420Frame);
                } else {
                    this.f11540o = i420Frame;
                    this.f11535j.post(this.f11525G);
                }
            }
        }
    }

    private Point m8226a(int i, int i2) {
        Point displaySize;
        synchronized (this.f11530e) {
            int defaultSize = getDefaultSize(Http2CodecUtil.MAX_INITIAL_WINDOW_SIZE, i);
            int defaultSize2 = getDefaultSize(Http2CodecUtil.MAX_INITIAL_WINDOW_SIZE, i2);
            displaySize = RendererCommon.getDisplaySize(this.f11546u, m8238d(), defaultSize, defaultSize2);
            if (MeasureSpec.getMode(i) == Pow2.MAX_POW2) {
                displaySize.x = defaultSize;
            }
            if (MeasureSpec.getMode(i2) == Pow2.MAX_POW2) {
                displaySize.y = defaultSize2;
            }
        }
        return displaySize;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onMeasure(int r5, int r6) {
        /*
        r4 = this;
        r1 = r4.f11530e;
        monitor-enter(r1);
        r0 = r4.f11543r;	 Catch:{ all -> 0x004e }
        if (r0 == 0) goto L_0x000b;
    L_0x0007:
        r0 = r4.f11544s;	 Catch:{ all -> 0x004e }
        if (r0 != 0) goto L_0x0010;
    L_0x000b:
        super.onMeasure(r5, r6);	 Catch:{ all -> 0x004e }
        monitor-exit(r1);	 Catch:{ all -> 0x004e }
    L_0x000f:
        return;
    L_0x0010:
        r0 = r4.m8226a(r5, r6);	 Catch:{ all -> 0x004e }
        r4.f11541p = r0;	 Catch:{ all -> 0x004e }
        r0 = r4.f11541p;	 Catch:{ all -> 0x004e }
        r0 = r0.x;	 Catch:{ all -> 0x004e }
        r2 = r4.getMeasuredWidth();	 Catch:{ all -> 0x004e }
        if (r0 != r2) goto L_0x002a;
    L_0x0020:
        r0 = r4.f11541p;	 Catch:{ all -> 0x004e }
        r0 = r0.y;	 Catch:{ all -> 0x004e }
        r2 = r4.getMeasuredHeight();	 Catch:{ all -> 0x004e }
        if (r0 == r2) goto L_0x004c;
    L_0x002a:
        r0 = 1;
    L_0x002b:
        r2 = r4.f11541p;	 Catch:{ all -> 0x004e }
        r2 = r2.x;	 Catch:{ all -> 0x004e }
        r3 = r4.f11541p;	 Catch:{ all -> 0x004e }
        r3 = r3.y;	 Catch:{ all -> 0x004e }
        r4.setMeasuredDimension(r2, r3);	 Catch:{ all -> 0x004e }
        monitor-exit(r1);	 Catch:{ all -> 0x004e }
        if (r0 == 0) goto L_0x000f;
    L_0x0039:
        r1 = r4.f11527b;
        monitor-enter(r1);
        r0 = r4.f11535j;	 Catch:{ all -> 0x0049 }
        if (r0 == 0) goto L_0x0047;
    L_0x0040:
        r0 = r4.f11535j;	 Catch:{ all -> 0x0049 }
        r2 = r4.f11537l;	 Catch:{ all -> 0x0049 }
        r0.postAtFrontOfQueue(r2);	 Catch:{ all -> 0x0049 }
    L_0x0047:
        monitor-exit(r1);	 Catch:{ all -> 0x0049 }
        goto L_0x000f;
    L_0x0049:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0049 }
        throw r0;
    L_0x004c:
        r0 = 0;
        goto L_0x002b;
    L_0x004e:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x004e }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: im.facechat.view.FCSurfaceView.onMeasure(int, int):void");
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        synchronized (this.f11530e) {
            this.f11531f.x = i3 - i;
            this.f11531f.y = i4 - i2;
        }
        m8231a(this.f11525G);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        C1639b.m7918a("FCSurfaceView", getResourceName() + "Surface created.");
        synchronized (this.f11530e) {
            this.f11542q = true;
        }
        tryCreateEglSurface();
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        C1639b.m7918a("FCSurfaceView", getResourceName() + "Surface destroyed.");
        synchronized (this.f11530e) {
            this.f11542q = false;
            this.f11532g.x = 0;
            this.f11532g.y = 0;
        }
        m8231a(new C17365(this));
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        C1639b.m7918a("FCSurfaceView", getResourceName() + "Surface changed: " + i2 + "x" + i3);
        synchronized (this.f11530e) {
            this.f11532g.x = i2;
            this.f11532g.y = i3;
        }
        m8231a(this.f11525G);
    }

    private void m8231a(Runnable runnable) {
        synchronized (this.f11527b) {
            if (this.f11535j != null) {
                this.f11535j.post(runnable);
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

    private void m8229a() {
        if (Thread.currentThread() != this.f11534i) {
            throw new IllegalStateException(getResourceName() + "Wrong thread.");
        } else if (this.f11536k != null && this.f11536k.hasSurface()) {
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            GLES20.glClear(Http2CodecUtil.MAX_FRAME_SIZE_LOWER_BOUND);
            this.f11536k.swapBuffers();
        }
    }

    private boolean m8235b() {
        if (Thread.currentThread() != this.f11534i) {
            throw new IllegalStateException(getResourceName() + "Wrong thread.");
        }
        boolean z;
        synchronized (this.f11530e) {
            z = this.f11531f.equals(this.f11541p) && this.f11532g.equals(this.f11531f);
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m8237c() {
        /*
        r14 = this;
        r8 = 16384; // 0x4000 float:2.2959E-41 double:8.0948E-320;
        r7 = 3;
        r11 = 0;
        r9 = 0;
        r0 = java.lang.Thread.currentThread();
        r1 = r14.f11534i;
        if (r0 == r1) goto L_0x002a;
    L_0x000d:
        r0 = new java.lang.IllegalStateException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = r14.getResourceName();
        r1 = r1.append(r2);
        r2 = "Wrong thread.";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x002a:
        r1 = r14.f11529d;
        monitor-enter(r1);
        r10 = r14.f11540o;	 Catch:{ all -> 0x0070 }
        r0 = 0;
        r14.f11540o = r0;	 Catch:{ all -> 0x0070 }
        r0 = r14.f11523E;	 Catch:{ all -> 0x0070 }
        r2 = r14.f11524F;	 Catch:{ all -> 0x0070 }
        r3 = 0;
        r14.f11524F = r3;	 Catch:{ all -> 0x0070 }
        monitor-exit(r1);	 Catch:{ all -> 0x0070 }
        android.os.SystemClock.elapsedRealtimeNanos();
        if (r0 != 0) goto L_0x01ae;
    L_0x003f:
        if (r10 == 0) goto L_0x01ae;
    L_0x0041:
        r14.m8232a(r10);
        r0 = r14.f11536k;
        if (r0 == 0) goto L_0x0050;
    L_0x0048:
        r0 = r14.f11536k;
        r0 = r0.hasSurface();
        if (r0 != 0) goto L_0x0073;
    L_0x0050:
        r0 = "FCSurfaceView";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = r14.getResourceName();
        r1 = r1.append(r2);
        r2 = "No surface to draw on";
        r1 = r1.append(r2);
        r1 = r1.toString();
        im.facechat.common.p045b.C1639b.m7918a(r0, r1);
        org.webrtc.VideoRenderer.renderFrameDone(r10);
    L_0x006f:
        return;
    L_0x0070:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0070 }
        throw r0;
    L_0x0073:
        r0 = r14.m8235b();
        if (r0 != 0) goto L_0x0080;
    L_0x0079:
        r14.m8229a();
        org.webrtc.VideoRenderer.renderFrameDone(r10);
        goto L_0x006f;
    L_0x0080:
        r1 = r14.f11530e;
        monitor-enter(r1);
        r0 = r14.f11536k;	 Catch:{ all -> 0x00e8 }
        r0 = r0.surfaceWidth();	 Catch:{ all -> 0x00e8 }
        r2 = r14.f11532g;	 Catch:{ all -> 0x00e8 }
        r2 = r2.x;	 Catch:{ all -> 0x00e8 }
        if (r0 != r2) goto L_0x009b;
    L_0x008f:
        r0 = r14.f11536k;	 Catch:{ all -> 0x00e8 }
        r0 = r0.surfaceHeight();	 Catch:{ all -> 0x00e8 }
        r2 = r14.f11532g;	 Catch:{ all -> 0x00e8 }
        r2 = r2.y;	 Catch:{ all -> 0x00e8 }
        if (r0 == r2) goto L_0x009e;
    L_0x009b:
        r14.m8229a();	 Catch:{ all -> 0x00e8 }
    L_0x009e:
        monitor-exit(r1);	 Catch:{ all -> 0x00e8 }
        r12 = java.lang.System.nanoTime();
        r1 = r14.f11530e;
        monitor-enter(r1);
        r0 = r10.samplingMatrix;	 Catch:{ all -> 0x00eb }
        r2 = r10.rotationDegree;	 Catch:{ all -> 0x00eb }
        r2 = (float) r2;	 Catch:{ all -> 0x00eb }
        r0 = org.webrtc.RendererCommon.rotateTextureMatrix(r0, r2);	 Catch:{ all -> 0x00eb }
        r2 = r14.f11547v;	 Catch:{ all -> 0x00eb }
        r3 = r14.m8238d();	 Catch:{ all -> 0x00eb }
        r4 = r14.f11531f;	 Catch:{ all -> 0x00eb }
        r4 = r4.x;	 Catch:{ all -> 0x00eb }
        r4 = (float) r4;	 Catch:{ all -> 0x00eb }
        r5 = r14.f11531f;	 Catch:{ all -> 0x00eb }
        r5 = r5.y;	 Catch:{ all -> 0x00eb }
        r5 = (float) r5;	 Catch:{ all -> 0x00eb }
        r4 = r4 / r5;
        r2 = org.webrtc.RendererCommon.getLayoutMatrix(r2, r3, r4);	 Catch:{ all -> 0x00eb }
        r6 = org.webrtc.RendererCommon.multiplyMatrices(r0, r2);	 Catch:{ all -> 0x00eb }
        monitor-exit(r1);	 Catch:{ all -> 0x00eb }
        android.opengl.GLES20.glClear(r8);
        r0 = r10.yuvFrame;
        if (r0 == 0) goto L_0x018c;
    L_0x00d0:
        r0 = r14.f11539n;
        if (r0 != 0) goto L_0x00ee;
    L_0x00d4:
        r0 = new int[r7];
        r14.f11539n = r0;
        r0 = r9;
    L_0x00d9:
        if (r0 >= r7) goto L_0x00ee;
    L_0x00db:
        r1 = r14.f11539n;
        r2 = 3553; // 0xde1 float:4.979E-42 double:1.7554E-320;
        r2 = org.webrtc.GlUtil.generateTexture(r2);
        r1[r0] = r2;
        r0 = r0 + 1;
        goto L_0x00d9;
    L_0x00e8:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x00e8 }
        throw r0;
    L_0x00eb:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x00eb }
        throw r0;
    L_0x00ee:
        r0 = r14.f11528c;
        r1 = r14.f11539n;
        r2 = r10.width;
        r3 = r10.height;
        r4 = r10.yuvStrides;
        r5 = r10.yuvPlanes;
        r0.uploadYuvData(r1, r2, r3, r4, r5);
        r0 = r14.f11538m;
        r1 = r14.f11539n;
        r3 = r10.rotatedWidth();
        r4 = r10.rotatedHeight();
        r2 = r14.f11532g;
        r7 = r2.x;
        r2 = r14.f11532g;
        r8 = r2.y;
        r2 = r6;
        r5 = r9;
        r6 = r9;
        r0.drawYuv(r1, r2, r3, r4, r5, r6, r7, r8);
    L_0x0117:
        r0 = r14.f11536k;
        r0.swapBuffers();
        org.webrtc.VideoRenderer.renderFrameDone(r10);
        r1 = r14.f11533h;
        monitor-enter(r1);
        r0 = r14.f11551z;	 Catch:{ all -> 0x01ab }
        if (r0 != 0) goto L_0x0151;
    L_0x0126:
        r14.f11519A = r12;	 Catch:{ all -> 0x01ab }
        r2 = r14.f11530e;	 Catch:{ all -> 0x01ab }
        monitor-enter(r2);	 Catch:{ all -> 0x01ab }
        r0 = "FCSurfaceView";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01a8 }
        r3.<init>();	 Catch:{ all -> 0x01a8 }
        r4 = r14.getResourceName();	 Catch:{ all -> 0x01a8 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x01a8 }
        r4 = "Reporting first rendered frame.";
        r3 = r3.append(r4);	 Catch:{ all -> 0x01a8 }
        r3 = r3.toString();	 Catch:{ all -> 0x01a8 }
        im.facechat.common.p045b.C1639b.m7918a(r0, r3);	 Catch:{ all -> 0x01a8 }
        r0 = r14.f11548w;	 Catch:{ all -> 0x01a8 }
        if (r0 == 0) goto L_0x0150;
    L_0x014b:
        r0 = r14.f11548w;	 Catch:{ all -> 0x01a8 }
        r0.onFirstFrameRendered();	 Catch:{ all -> 0x01a8 }
    L_0x0150:
        monitor-exit(r2);	 Catch:{ all -> 0x01a8 }
    L_0x0151:
        r0 = r14.f11551z;	 Catch:{ all -> 0x01ab }
        r0 = r0 + 1;
        r14.f11551z = r0;	 Catch:{ all -> 0x01ab }
        r2 = r14.f11520B;	 Catch:{ all -> 0x01ab }
        r4 = java.lang.System.nanoTime();	 Catch:{ all -> 0x01ab }
        r4 = r4 - r12;
        r2 = r2 + r4;
        r14.f11520B = r2;	 Catch:{ all -> 0x01ab }
        r0 = r14.f11551z;	 Catch:{ all -> 0x01ab }
        r0 = r0 % 300;
        if (r0 != 0) goto L_0x016a;
    L_0x0167:
        r14.m8240e();	 Catch:{ all -> 0x01ab }
    L_0x016a:
        monitor-exit(r1);	 Catch:{ all -> 0x01ab }
        r0 = r14.f11521C;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x0187;
    L_0x0173:
        r0 = r14.f11521C;
        r0.poll();
        r0 = r14.f11522D;
        if (r0 == 0) goto L_0x0187;
    L_0x017c:
        r0 = r14.f11522D;
        r1 = r14.m8242f();
        r0.captureBmp(r1);
        r14.f11522D = r11;
    L_0x0187:
        android.os.SystemClock.elapsedRealtimeNanos();
        goto L_0x006f;
    L_0x018c:
        r0 = r14.f11538m;
        r1 = r10.textureId;
        r3 = r10.rotatedWidth();
        r4 = r10.rotatedHeight();
        r2 = r14.f11532g;
        r7 = r2.x;
        r2 = r14.f11532g;
        r8 = r2.y;
        r2 = r6;
        r5 = r9;
        r6 = r9;
        r0.drawOes(r1, r2, r3, r4, r5, r6, r7, r8);
        goto L_0x0117;
    L_0x01a8:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x01a8 }
        throw r0;	 Catch:{ all -> 0x01ab }
    L_0x01ab:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x01ab }
        throw r0;
    L_0x01ae:
        if (r0 == 0) goto L_0x0187;
    L_0x01b0:
        if (r2 == 0) goto L_0x0187;
    L_0x01b2:
        r1 = r14.f11530e;
        monitor-enter(r1);
        r0 = r14.f11536k;	 Catch:{ all -> 0x01d7 }
        r0 = r0.surfaceWidth();	 Catch:{ all -> 0x01d7 }
        r3 = r14.f11532g;	 Catch:{ all -> 0x01d7 }
        r3 = r3.x;	 Catch:{ all -> 0x01d7 }
        if (r0 != r3) goto L_0x01cd;
    L_0x01c1:
        r0 = r14.f11536k;	 Catch:{ all -> 0x01d7 }
        r0 = r0.surfaceHeight();	 Catch:{ all -> 0x01d7 }
        r3 = r14.f11532g;	 Catch:{ all -> 0x01d7 }
        r3 = r3.y;	 Catch:{ all -> 0x01d7 }
        if (r0 == r3) goto L_0x01d0;
    L_0x01cd:
        r14.m8229a();	 Catch:{ all -> 0x01d7 }
    L_0x01d0:
        r0 = r14.f11542q;	 Catch:{ all -> 0x01d7 }
        if (r0 != 0) goto L_0x01da;
    L_0x01d4:
        monitor-exit(r1);	 Catch:{ all -> 0x01d7 }
        goto L_0x006f;
    L_0x01d7:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x01d7 }
        throw r0;
    L_0x01da:
        monitor-exit(r1);	 Catch:{ all -> 0x01d7 }
        r0 = r2.getRgbTextureId();
        if (r0 < 0) goto L_0x006f;
    L_0x01e1:
        r0 = r2.getTexMatrix();
        if (r0 != 0) goto L_0x01f0;
    L_0x01e7:
        r0 = "FCSurfaceView";
        r1 = "rgbFrame.getTexMatrix is null";
        im.facechat.common.p045b.C1639b.m7920b(r0, r1);
        goto L_0x006f;
    L_0x01f0:
        java.lang.System.nanoTime();
        android.opengl.GLES20.glClear(r8);
        r0 = r14.f11538m;
        r1 = r2.getRgbTextureId();
        r2 = r2.getTexMatrix();
        r3 = 720; // 0x2d0 float:1.009E-42 double:3.557E-321;
        r4 = 1280; // 0x500 float:1.794E-42 double:6.324E-321;
        r5 = r14.f11532g;
        r7 = r5.x;
        r5 = r14.f11532g;
        r8 = r5.y;
        r5 = r9;
        r6 = r9;
        r0.drawRgb(r1, r2, r3, r4, r5, r6, r7, r8);
        r0 = r14.f11536k;
        r0.swapBuffers();
        r0 = r14.f11526a;
        if (r0 == 0) goto L_0x021f;
    L_0x021a:
        r0 = r14.f11526a;
        r0.run();
    L_0x021f:
        r0 = r14.f11521C;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x0187;
    L_0x0227:
        r0 = r14.f11521C;
        r0.poll();
        r0 = r14.f11522D;
        if (r0 == 0) goto L_0x0187;
    L_0x0230:
        r0 = r14.f11522D;
        r1 = r14.m8242f();
        r0.captureBmp(r1);
        r14.f11522D = r11;
        goto L_0x0187;
        */
        throw new UnsupportedOperationException("Method not decompiled: im.facechat.view.FCSurfaceView.c():void");
    }

    private float m8238d() {
        float f;
        synchronized (this.f11530e) {
            if (this.f11543r == 0 || this.f11544s == 0) {
                f = 0.0f;
            } else {
                f = this.f11545t % 180 == 0 ? ((float) this.f11543r) / ((float) this.f11544s) : ((float) this.f11544s) / ((float) this.f11543r);
            }
        }
        return f;
    }

    private void m8232a(I420Frame i420Frame) {
        synchronized (this.f11530e) {
            if (!(this.f11543r == i420Frame.width && this.f11544s == i420Frame.height && this.f11545t == i420Frame.rotationDegree)) {
                C1639b.m7918a("FCSurfaceView", getResourceName() + "Reporting frame resolution changed to " + i420Frame.width + "x" + i420Frame.height + " with rotation " + i420Frame.rotationDegree);
                if (this.f11548w != null) {
                    this.f11548w.onFrameResolutionChanged(i420Frame.width, i420Frame.height, i420Frame.rotationDegree);
                }
                this.f11543r = i420Frame.width;
                this.f11544s = i420Frame.height;
                this.f11545t = i420Frame.rotationDegree;
                post(new C17376(this));
            }
        }
    }

    private void m8240e() {
    }

    public void capture(CaptureCallBack captureCallBack) {
        this.f11521C.add(UUID.randomUUID().toString());
        this.f11522D = captureCallBack;
    }

    private Bitmap m8242f() {
        int i = 0;
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int i2 = measuredWidth * measuredHeight;
        Buffer allocateDirect = ByteBuffer.allocateDirect(i2 * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        GLES20.glReadPixels(0, 0, measuredWidth, measuredHeight, 6408, 5121, allocateDirect);
        int[] iArr = new int[i2];
        allocateDirect.asIntBuffer().get(iArr);
        Bitmap createBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Config.RGB_565);
        createBitmap.setPixels(iArr, i2 - measuredWidth, -measuredWidth, 0, 0, measuredWidth, measuredHeight);
        short[] sArr = new short[i2];
        Buffer wrap = ShortBuffer.wrap(sArr);
        createBitmap.copyPixelsToBuffer(wrap);
        while (i < i2) {
            short s = sArr[i];
            sArr[i] = (short) (((s & 63488) >> 11) | (((s & 31) << 11) | (s & 2016)));
            i++;
        }
        wrap.rewind();
        createBitmap.copyPixelsFromBuffer(wrap);
        return createBitmap;
    }

    public void setDrawRgb(boolean z) {
        synchronized (this.f11529d) {
            this.f11523E = z;
            this.f11540o = null;
        }
    }

    public void onTextureAvailable(int i, int i2, float[] fArr, int i3, long j, Runnable runnable) {
        if (this.f11523E) {
            synchronized (this.f11529d) {
                if (this.f11523E) {
                    this.f11524F = new RgbFrame(i, fArr, i3, j);
                    this.f11540o = null;
                    this.f11526a = runnable;
                }
            }
            m8231a(this.f11525G);
        }
    }
}
