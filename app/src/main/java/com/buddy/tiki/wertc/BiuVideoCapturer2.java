package com.buddy.tiki.wertc;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.WindowManager;
import com.buddy.tiki.event.CallEvent.PreviewSizeChangeEvent;
import com.buddy.tiki.log.TikiLog;
import com.buddy.tiki.util.FUUtil;
import im.facechat.common.protocol.FacechatCapturer;
import im.facechat.common.protocol.FacechatCapturer.TextureCallback;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.bytedeco.javacpp.swscale;
import org.greenrobot.eventbus.EventBus;
import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.CameraEnumerationAndroid.CaptureFormat;
import org.webrtc.CameraEnumerationAndroid.CaptureFormat.FramerateRange;
import org.webrtc.CameraVideoCapturer.CameraEventsHandler;
import org.webrtc.CameraVideoCapturer.CameraStatistics;
import org.webrtc.CameraVideoCapturer.CameraSwitchHandler;
import org.webrtc.Logging;
import org.webrtc.RendererCommon;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoCapturer.CapturerObserver;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class BiuVideoCapturer2 extends FacechatCapturer {
    private static final TikiLog f3527f = TikiLog.getInstance("BiuVideoCapturer2");
    private volatile boolean f3528A;
    private CapturerObserver f3529B = null;
    private boolean f3530C;
    private SurfaceTextureHelper f3531D;
    private int f3532E;
    private long f3533F = -1;
    private PreviewCallback f3534G;
    private boolean f3535H = false;
    private boolean f3536I = false;
    private boolean f3537J = true;
    private Runnable f3538K;
    private int f3539L = 0;
    private TextureCallback f3540M;
    float[] f3541a = new float[]{0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f};
    float[] f3542b = new float[]{0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f};
    long f3543c;
    long f3544d;
    double f3545e = 0.0d;
    private final Object f3546g = new Object();
    private final Object f3547h = new Object();
    private final Object f3548i = new Object();
    private final CameraEventsHandler f3549j;
    private final Set<byte[]> f3550k = new HashSet();
    private final boolean f3551l;
    private final ErrorCallback f3552m = new C05571(this);
    private final AtomicBoolean f3553n = new AtomicBoolean();
    private int f3554o = 640;
    private int f3555p = 480;
    private Camera f3556q;
    private Handler f3557r;
    private Context f3558s;
    private int f3559t;
    private CameraInfo f3560u;
    private CameraStatistics f3561v;
    private int f3562w;
    private int f3563x;
    private int f3564y;
    private CaptureFormat f3565z;

    public interface PreviewCallback {
        void onPreviewFrame(byte[] bArr, int i, int i2, int i3, long j);
    }

    class C05571 implements ErrorCallback {
        final /* synthetic */ BiuVideoCapturer2 f3526a;

        C05571(BiuVideoCapturer2 this$0) {
            this.f3526a = this$0;
        }

        public void onError(int error, Camera camera) {
            String errorMessage;
            if (error == 100) {
                errorMessage = "Camera server died!";
            } else {
                errorMessage = "Camera error: " + error;
            }
            BiuVideoCapturer2.f3527f.m263e(errorMessage);
            if (this.f3526a.f3549j != null) {
                this.f3526a.f3549j.onCameraError(errorMessage);
            }
        }
    }

    public BiuVideoCapturer2(String cameraName, CameraEventsHandler eventsHandler, boolean captureToTexture) {
        super(cameraName, eventsHandler, captureToTexture);
        if (Camera.getNumberOfCameras() == 0) {
            throw new RuntimeException("No cameras available");
        }
        if (TextUtils.isEmpty(cameraName)) {
            this.f3559t = 0;
        } else {
            this.f3559t = m2170a(cameraName);
        }
        this.f3549j = eventsHandler;
        this.f3551l = false;
        f3527f.m261d("BiuVideoCapturer2 isCapturingToTexture : " + this.f3551l);
    }

    public static BiuVideoCapturer2 create(String name, CameraEventsHandler eventsHandler) {
        return create(name, eventsHandler, false);
    }

    @Deprecated
    public static BiuVideoCapturer2 create(String name, CameraEventsHandler eventsHandler, boolean captureToTexture) {
        try {
            return new BiuVideoCapturer2(name, eventsHandler, captureToTexture);
        } catch (RuntimeException var4) {
            f3527f.m264e("Couldn't create camera.", var4);
            return null;
        }
    }

    private static int m2170a(String deviceName) {
        f3527f.m261d("getCameraIndex: " + deviceName);
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            if (deviceName.equals(CameraEnumerationAndroid.getDeviceName(i))) {
                return i;
            }
        }
        throw new IllegalArgumentException("No such camera: " + deviceName);
    }

    public void printStackTrace() {
        Thread cameraThread = null;
        Object cameraStackTraces = this.f3546g;
        synchronized (this.f3546g) {
            if (this.f3557r != null) {
                cameraThread = this.f3557r.getLooper().getThread();
            }
        }
        if (cameraThread != null) {
            StackTraceElement[] var8 = cameraThread.getStackTrace();
            if (var8.length > 0) {
                f3527f.m261d("BiuVideoCapturer2 stacks trace:");
                StackTraceElement[] var3 = var8;
                int var4 = var8.length;
                for (int var5 = 0; var5 < var4; var5++) {
                    f3527f.m261d(var3[var5].toString());
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void switchCamera(org.webrtc.CameraVideoCapturer.CameraSwitchHandler r6) {
        /*
        r5 = this;
        r2 = android.hardware.Camera.getNumberOfCameras();
        r3 = 2;
        if (r2 >= r3) goto L_0x000f;
    L_0x0007:
        if (r6 == 0) goto L_0x000e;
    L_0x0009:
        r2 = "No camera to switch to.";
        r6.onCameraSwitchError(r2);
    L_0x000e:
        return;
    L_0x000f:
        r0 = r5.f3548i;
        r3 = r5.f3548i;
        monitor-enter(r3);
        r2 = r5.f3528A;	 Catch:{ all -> 0x0028 }
        if (r2 == 0) goto L_0x002b;
    L_0x0018:
        r2 = "BiuVideoCapturer2";
        r4 = "Ignoring camera switch request.";
        org.webrtc.Logging.w(r2, r4);	 Catch:{ all -> 0x0028 }
        if (r6 == 0) goto L_0x0026;
    L_0x0021:
        r2 = "Pending camera switch already in progress.";
        r6.onCameraSwitchError(r2);	 Catch:{ all -> 0x0028 }
    L_0x0026:
        monitor-exit(r3);	 Catch:{ all -> 0x0028 }
        goto L_0x000e;
    L_0x0028:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0028 }
        throw r2;
    L_0x002b:
        r2 = 1;
        r5.f3528A = r2;	 Catch:{ all -> 0x0028 }
        monitor-exit(r3);	 Catch:{ all -> 0x0028 }
        r2 = com.buddy.tiki.wertc.BiuVideoCapturer2$$Lambda$1.lambdaFactory$(r5, r6);
        r1 = r5.m2174a(r2);
        if (r1 != 0) goto L_0x000e;
    L_0x0039:
        if (r6 == 0) goto L_0x000e;
    L_0x003b:
        r2 = "Camera is stopped.";
        r6.onCameraSwitchError(r2);
        goto L_0x000e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.buddy.tiki.wertc.BiuVideoCapturer2.switchCamera(org.webrtc.CameraVideoCapturer$CameraSwitchHandler):void");
    }

    /* synthetic */ void m2187a(CameraSwitchHandler switchEventsHandler) {
        boolean z = true;
        m2178e();
        synchronized (this.f3548i) {
            this.f3528A = false;
        }
        if (switchEventsHandler != null) {
            if (this.f3560u.facing != 1) {
                z = false;
            }
            switchEventsHandler.onCameraSwitchDone(z);
        }
    }

    /* synthetic */ void m2191d(int width, int height, int framerate) {
        m2183g(width, height, framerate);
    }

    public void onOutputFormatRequest(int width, int height, int framerate) {
        m2174a(BiuVideoCapturer2$$Lambda$2.lambdaFactory$(this, width, height, framerate));
    }

    public void changeCaptureFormat(int width, int height, int framerate) {
        m2174a(BiuVideoCapturer2$$Lambda$3.lambdaFactory$(this, width, height, framerate));
    }

    /* synthetic */ void m2190c(int width, int height, int framerate) {
        f3527f.m261d("onOutputFormatRequestOnCameraThread: " + width + "x" + height + "@" + framerate);
        if (this.f3529B != null) {
            this.f3529B.onOutputFormatRequest(width, height, framerate);
        }
    }

    int m2184a() {
        int i;
        Object var1 = this.f3547h;
        synchronized (this.f3547h) {
            i = this.f3559t;
        }
        return i;
    }

    public List<CaptureFormat> getSupportedFormats() {
        return Camera1Enumerator.m2193a(m2184a());
    }

    public boolean isCapturingToTexture() {
        return this.f3551l;
    }

    private void m2176c() {
        if (Thread.currentThread() != this.f3557r.getLooper().getThread()) {
            throw new IllegalStateException("Wrong thread");
        }
    }

    private boolean m2174a(Runnable runnable) {
        return m2173a(0, runnable);
    }

    private boolean m2173a(int delayMs, Runnable runnable) {
        return this.f3557r != null && this.f3553n.get() && this.f3557r.postAtTime(runnable, this, SystemClock.uptimeMillis() + ((long) delayMs));
    }

    public void dispose() {
        f3527f.m261d("dispose");
    }

    private boolean m2177d() {
        return (this.f3558s == null || this.f3529B == null) ? false : true;
    }

    public void initialize(SurfaceTextureHelper surfaceTextureHelper, Context applicationContext, CapturerObserver frameObserver) {
        Logging.d("VideoCapturerAndroid", "initialize");
        if (applicationContext == null) {
            throw new IllegalArgumentException("applicationContext not set.");
        } else if (frameObserver == null) {
            throw new IllegalArgumentException("frameObserver not set.");
        } else if (m2177d()) {
            throw new IllegalStateException("Already initialized");
        } else {
            this.f3558s = applicationContext;
            this.f3529B = frameObserver;
            this.f3531D = surfaceTextureHelper;
            this.f3557r = surfaceTextureHelper == null ? null : surfaceTextureHelper.getHandler();
        }
    }

    public void startCapture(int width, int height, int framerate) {
        f3527f.m261d("startCapture requested: " + width + "x" + height + "@" + framerate);
        if (!m2177d()) {
            throw new IllegalStateException("startCapture called in uninitialized state");
        } else if (this.f3531D == null) {
            this.f3529B.onCapturerStarted(false);
            if (this.f3549j != null) {
                this.f3549j.onCameraError("No SurfaceTexture created.");
            }
        } else if (this.f3553n.getAndSet(true)) {
            Logging.e("VideoCapturerAndroid", "Camera has already been started.");
        } else if (!m2174a(BiuVideoCapturer2$$Lambda$4.lambdaFactory$(this, width, height, framerate))) {
            this.f3529B.onCapturerStarted(false);
            if (this.f3549j != null) {
                this.f3549j.onCameraError("Could not post task to camera thread.");
            }
            this.f3553n.set(false);
        }
    }

    /* synthetic */ void m2189b(int width, int height, int framerate) {
        this.f3532E = 0;
        m2179e(width, height, framerate);
    }

    private void m2179e(int width, int height, int framerate) {
        m2176c();
        if (!this.f3553n.get()) {
            Logging.e("VideoCapturerAndroid", "startCaptureOnCameraThread: Camera is stopped");
        } else if (this.f3556q != null) {
            f3527f.m263e("startCaptureOnCameraThread: Camera has already been started.");
        } else {
            this.f3530C = false;
            try {
                Object e = this.f3547h;
                synchronized (this.f3547h) {
                    f3527f.m261d("Opening camera " + this.f3559t);
                    if (this.f3549j != null) {
                        this.f3549j.onCameraOpening(this.f3559t);
                    }
                    this.f3556q = Camera.open(this.f3559t);
                    this.f3560u = new CameraInfo();
                    Camera.getCameraInfo(this.f3559t, this.f3560u);
                }
                try {
                    this.f3556q.setPreviewTexture(this.f3531D.getSurfaceTexture());
                    f3527f.m261d("Camera orientation: " + this.f3560u.orientation + " .Device orientation: " + m2180f());
                    this.f3556q.setErrorCallback(this.f3552m);
                    m2181f(width, height, framerate);
                    this.f3529B.onCapturerStarted(true);
                    if (this.f3551l) {
                        this.f3531D.startListening(this);
                    }
                    this.f3561v = new CameraStatistics(this.f3531D, this.f3549j);
                } catch (IOException var9) {
                    f3527f.m264e("setPreviewTexture failed", (Throwable) null);
                    throw new RuntimeException(var9);
                } catch (RuntimeException var12) {
                    f3527f.m264e("startCapture failed", var12);
                    m2172a(true);
                    this.f3529B.onCapturerStarted(false);
                    if (this.f3549j != null) {
                        this.f3549j.onCameraError("Camera can not be started.");
                    }
                }
            } catch (RuntimeException var11) {
                this.f3532E++;
                if (this.f3532E < 3) {
                    f3527f.m264e("Camera.open failed, retrying", var11);
                    m2173a(500, BiuVideoCapturer2$$Lambda$5.lambdaFactory$(this, width, height, framerate));
                    return;
                }
                throw var11;
            }
        }
    }

    /* synthetic */ void m2185a(int width, int height, int framerate) {
        m2179e(width, height, framerate);
    }

    private void m2181f(int width, int height, int framerate) {
        m2176c();
        f3527f.m261d("startPreviewOnCameraThread requested: " + width + "x" + height + "@" + framerate);
        if (this.f3553n.get() && this.f3556q != null) {
            FramerateRange bestFpsRange;
            int i;
            this.f3562w = width;
            this.f3563x = height;
            this.f3564y = framerate;
            Parameters parameters = this.f3556q.getParameters();
            List<FramerateRange> supportedFramerates = Camera1Enumerator.m2196b(parameters.getSupportedPreviewFpsRange());
            f3527f.m261d("Available fps ranges: " + supportedFramerates);
            if (supportedFramerates.isEmpty()) {
                Logging.w("BiuVideoCapturer2", "No supported preview fps range");
                bestFpsRange = new FramerateRange(15, 30);
            } else {
                bestFpsRange = CameraEnumerationAndroid.getClosestSupportedFramerateRange(supportedFramerates, framerate);
            }
            if (bestFpsRange.min <= 0 || bestFpsRange.max <= 0) {
                bestFpsRange = CameraHelper.getClosestSupportedFramerateRange(supportedFramerates, framerate);
            }
            List previewSizeList = parameters.getSupportedPreviewSizes();
            StringBuilder sb = new StringBuilder();
            sb.append("Available preview size: ");
            for (i = 0; i < previewSizeList.size(); i++) {
                sb.append(" [" + ((Size) previewSizeList.get(i)).width + "," + ((Size) previewSizeList.get(i)).height + "]");
            }
            f3527f.m261d(sb.toString());
            org.webrtc.Size previewSize = CameraEnumerationAndroid.getClosestSupportedSize(Camera1Enumerator.m2194a(previewSizeList), width, height);
            CaptureFormat captureFormat = new CaptureFormat(previewSize.width, previewSize.height, bestFpsRange);
            if (!captureFormat.equals(this.f3565z)) {
                f3527f.m261d("isVideoStabilizationSupported: " + parameters.isVideoStabilizationSupported());
                if (parameters.isVideoStabilizationSupported()) {
                    parameters.setVideoStabilization(true);
                }
                if (captureFormat.framerate.max > 0) {
                    f3527f.m261d("setPreviewFpsRange: min:" + captureFormat.framerate.min + " max:" + captureFormat.framerate.max);
                    parameters.setPreviewFpsRange(captureFormat.framerate.min, captureFormat.framerate.max);
                }
                if (!this.f3551l) {
                    captureFormat.getClass();
                    parameters.setPreviewFormat(17);
                }
                List pictureFormats = parameters.getSupportedPictureFormats();
                if (pictureFormats != null && pictureFormats.contains(Integer.valueOf(swscale.SWS_SINC))) {
                    parameters.setPictureFormat(swscale.SWS_SINC);
                }
                f3527f.m261d("preview size:w:" + captureFormat.width + " h:" + captureFormat.height);
                parameters.setPreviewSize(captureFormat.width, captureFormat.height);
                EventBus.getDefault().post(new PreviewSizeChangeEvent(captureFormat.width, captureFormat.height));
                org.webrtc.Size pictureSize = CameraEnumerationAndroid.getClosestSupportedSize(Camera1Enumerator.m2194a(parameters.getSupportedPictureSizes()), width, height);
                parameters.setPictureSize(pictureSize.width, pictureSize.height);
                if (this.f3565z != null) {
                    this.f3556q.stopPreview();
                    this.f3556q.setPreviewCallbackWithBuffer(null);
                }
                this.f3554o = captureFormat.width;
                this.f3555p = captureFormat.height;
                m2183g(this.f3554o, this.f3555p, bestFpsRange.max);
                f3527f.m261d("Start capturing: " + captureFormat);
                this.f3565z = captureFormat;
                List sceneModes = parameters.getSupportedSceneModes();
                if (sceneModes != null && sceneModes.contains("portrait")) {
                    f3527f.m261d("setSceneMode(Camera.Parameters.SCENE_MODE_PORTRAIT)");
                    parameters.setSceneMode("portrait");
                }
                List flashModes = parameters.getSupportedFlashModes();
                if (flashModes != null && flashModes.contains("off")) {
                    f3527f.m261d("setFlashMode(Camera.Parameters.FLASH_MODE_OFF)");
                    parameters.setFlashMode("off");
                }
                List focusModes = parameters.getSupportedFocusModes();
                if (focusModes != null && focusModes.contains("continuous-picture")) {
                    f3527f.m261d("setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)");
                    parameters.setFocusMode("continuous-picture");
                }
                f3527f.m261d("flatten:" + parameters.flatten());
                try {
                    this.f3556q.setParameters(parameters);
                } catch (Exception e) {
                    f3527f.m261d("setParameters failed");
                    if (supportedFramerates == null || supportedFramerates.isEmpty()) {
                        parameters.setPreviewFpsRange(7000, 30000);
                    } else {
                        FramerateRange framerateRange = (FramerateRange) supportedFramerates.get(0);
                        if (framerateRange != null) {
                            parameters.setPreviewFpsRange(framerateRange.min, framerateRange.max);
                        }
                    }
                    try {
                        this.f3556q.setParameters(parameters);
                    } catch (Exception e2) {
                    }
                }
                try {
                    this.f3556q.setDisplayOrientation(0);
                    if (!this.f3551l) {
                        this.f3550k.clear();
                        int frameSize = captureFormat.frameSize();
                        for (i = 0; i < 3; i++) {
                            ByteBuffer buffer = ByteBuffer.allocateDirect(frameSize);
                            this.f3550k.add(buffer.array());
                            this.f3556q.addCallbackBuffer(buffer.array());
                        }
                        this.f3556q.setPreviewCallbackWithBuffer(this);
                    }
                    this.f3556q.startPreview();
                    FUUtil.getInstance().unloadAll();
                    FUUtil.getInstance().release();
                    FUUtil.getInstance().init();
                    if (this.f3538K != null) {
                        f3527f.m261d("capturer onStarted");
                        this.f3538K.run();
                    }
                } catch (Exception e3) {
                    f3527f.m264e("setDisplayOrientation failed:", e3);
                }
            }
        }
    }

    public void stopCapture() throws InterruptedException {
        f3527f.m261d("stopCapture");
        CountDownLatch barrier = new CountDownLatch(1);
        if (m2174a(BiuVideoCapturer2$$Lambda$6.lambdaFactory$(this, barrier))) {
            if (!barrier.await(7000, TimeUnit.MILLISECONDS)) {
                f3527f.m263e("Camera stop timeout");
                printStackTrace();
                if (this.f3549j != null) {
                    this.f3549j.onCameraError("Camera stop timeout");
                }
            }
            this.f3529B.onCapturerStopped();
            f3527f.m261d("stopCapture done");
            return;
        }
        f3527f.m263e("Calling stopCapture() for already stopped camera.");
    }

    /* synthetic */ void m2186a(CountDownLatch barrier) {
        m2172a(true);
        barrier.countDown();
    }

    private void m2172a(boolean stopHandler) {
        m2176c();
        f3527f.m261d("stopCaptureOnCameraThread");
        if (this.f3531D != null) {
            this.f3531D.stopListening();
        }
        FUUtil.getInstance().unloadAll();
        FUUtil.getInstance().deivceLost();
        if (stopHandler) {
            this.f3553n.set(false);
            this.f3557r.removeCallbacksAndMessages(this);
        }
        if (this.f3561v != null) {
            this.f3561v.release();
            this.f3561v = null;
        }
        f3527f.m261d("Stop preview.");
        if (this.f3556q != null) {
            try {
                this.f3556q.stopPreview();
                this.f3556q.setPreviewCallbackWithBuffer(null);
            } catch (RuntimeException e) {
                f3527f.m262d("Stop preview.", e);
            }
        }
        this.f3550k.clear();
        this.f3565z = null;
        f3527f.m261d("Release camera.");
        if (this.f3556q != null) {
            this.f3556q.release();
            this.f3556q = null;
        }
        if (this.f3549j != null) {
            this.f3549j.onCameraClosed();
        }
        f3527f.m261d("stopCaptureOnCameraThread done");
    }

    private void m2178e() {
        m2176c();
        if (this.f3553n.get()) {
            f3527f.m261d("switchCameraOnCameraThread");
            m2172a(false);
            Object var1 = this.f3547h;
            synchronized (this.f3547h) {
                this.f3559t = (this.f3559t + 1) % Camera.getNumberOfCameras();
            }
            FUUtil.getInstance().deivceLost();
            FUUtil.getInstance().cameraChange();
            m2179e(this.f3562w, this.f3563x, this.f3564y);
            f3527f.m261d("switchCameraOnCameraThread done");
            return;
        }
        Logging.e("VideoCapturerAndroid", "switchCameraOnCameraThread: Camera is stopped");
    }

    private void m2183g(int width, int height, int framerate) {
        m2176c();
        if (this.f3556q == null) {
            f3527f.m263e("Calling onOutputFormatRequest() on stopped camera.");
            return;
        }
        f3527f.m261d("onOutputFormatRequestOnCameraThread: " + width + "x" + height + "@" + framerate);
        this.f3529B.onOutputFormatRequest(width, height, framerate);
    }

    private int m2180f() {
        switch (((WindowManager) this.f3558s.getSystemService("window")).getDefaultDisplay().getRotation()) {
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                break;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                break;
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                break;
            default:
                break;
        }
        return 0;
    }

    private int m2182g() {
        int rotation = m2180f();
        if (this.f3560u.facing == 0) {
            rotation = 360 - rotation;
        }
        return (this.f3560u.orientation + rotation) % 360;
    }

    public void onPreviewFrame(byte[] data, Camera callbackCamera) {
        m2176c();
        if (!this.f3553n.get()) {
            Logging.e("VideoCapturerAndroid", "onPreviewFrame: Camera is stopped");
        } else if (!this.f3550k.contains(data)) {
        } else {
            if (this.f3556q != callbackCamera) {
                throw new RuntimeException("Unexpected camera in callback!");
            }
            long captureTimeNs = TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
            if (!(this.f3549j == null || this.f3530C)) {
                this.f3549j.onFirstFrameAvailable();
                this.f3530C = true;
            }
            byte[] fuCopy = new byte[data.length];
            System.arraycopy(data, 0, fuCopy, 0, fuCopy.length);
            this.f3556q.addCallbackBuffer(data);
            if (this.f3537J && FUUtil.getInstance().isSupportFaceUnity()) {
                this.f3543c = SystemClock.elapsedRealtimeNanos();
                int textureId = FUUtil.getInstance().applyFaceUnity(fuCopy, this.f3554o, this.f3555p);
                this.f3544d = SystemClock.elapsedRealtimeNanos();
                this.f3543c = this.f3544d;
            }
            if (this.f3534G != null) {
                this.f3534G.onPreviewFrame(fuCopy, this.f3554o, this.f3555p, m2182g(), captureTimeNs);
            }
            this.f3561v.addFrame();
            this.f3529B.onByteBufferFrameCaptured(fuCopy, this.f3554o, this.f3555p, m2182g(), captureTimeNs);
        }
    }

    public void onTextureFrameAvailable(int oesTextureId, float[] transformMatrix, long timestampNs) {
        m2176c();
        if (this.f3553n.get()) {
            if (!(this.f3549j == null || this.f3530C)) {
                this.f3549j.onFirstFrameAvailable();
                this.f3530C = true;
            }
            int rotation = m2182g();
            if (this.f3560u.facing == 1) {
                transformMatrix = RendererCommon.multiplyMatrices(transformMatrix, RendererCommon.horizontalFlipMatrix());
            }
            this.f3561v.addFrame();
            this.f3529B.onTextureFrameCaptured(this.f3565z.width, this.f3565z.height, oesTextureId, transformMatrix, rotation, timestampNs);
            return;
        }
        Logging.e("VideoCapturerAndroid", "onTextureFrameAvailable: Camera is stopped");
        this.f3531D.returnTextureFrame();
    }

    public void enableFaceUnity(boolean enabled) {
        this.f3537J = enabled;
    }

    public void enableBlackAndWhite(boolean enabled) {
        this.f3536I = enabled;
        this.f3536I = false;
    }

    public boolean isBlackAndWhite() {
        return this.f3536I;
    }

    public int getPreviewWidth() {
        return this.f3554o;
    }

    public int getPreviewHeight() {
        return this.f3555p;
    }

    public void setPreviewCallback(PreviewCallback previewCallback) {
        this.f3534G = previewCallback;
    }

    public void onFrameAvail(byte[] data, int width, int height, int rotation, long timeNs) {
        m2174a(BiuVideoCapturer2$$Lambda$7.lambdaFactory$(this, data, width, height, rotation, timeNs));
    }

    /* synthetic */ void m2188a(byte[] data, int width, int height, int rotation, long timeNs) {
        this.f3561v.addFrame();
        this.f3529B.onByteBufferFrameCaptured(data, width, height, rotation, timeNs);
    }

    public void setStartedCallback(Runnable onStarted) {
        this.f3538K = onStarted;
    }

    public void setTextureCallback(TextureCallback textureCallback) {
        this.f3540M = textureCallback;
    }
}
