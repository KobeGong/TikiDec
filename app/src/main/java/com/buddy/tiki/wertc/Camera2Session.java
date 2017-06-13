package com.buddy.tiki.wertc;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCaptureSession.CaptureCallback;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraDevice.StateCallback;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureRequest.Builder;
import android.media.Image;
import android.media.Image.Plane;
import android.media.ImageReader;
import android.media.ImageReader.OnImageAvailableListener;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Range;
import android.view.Surface;
import android.view.WindowManager;
import com.buddy.tiki.util.FUUtil;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.swscale;
import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.CameraEnumerationAndroid.CaptureFormat;
import org.webrtc.CameraEnumerationAndroid.CaptureFormat.FramerateRange;
import org.webrtc.CameraSession;
import org.webrtc.CameraSession.CreateSessionCallback;
import org.webrtc.CameraVideoCapturer.CameraEventsHandler;
import org.webrtc.CameraVideoCapturer.CameraStatistics;
import org.webrtc.Logging;
import org.webrtc.Size;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.ThreadUtils;
import org.webrtc.VideoCapturer.CapturerObserver;
import tv.danmaku.ijk.media.player.IMediaPlayer;

@TargetApi(21)
public class Camera2Session implements CameraSession {
    private final Handler f3581a;
    private final CameraManager f3582b;
    private final CreateSessionCallback f3583c;
    private final CameraEventsHandler f3584d;
    private final Context f3585e;
    private final CapturerObserver f3586f;
    private final SurfaceTextureHelper f3587g;
    private final String f3588h;
    private final int f3589i;
    private final int f3590j;
    private final int f3591k;
    private CameraCharacteristics f3592l;
    private int f3593m;
    private boolean f3594n;
    private int f3595o;
    private CaptureFormat f3596p;
    private CameraDevice f3597q;
    private Surface f3598r;
    private CameraCaptureSession f3599s;
    private CameraStatistics f3600t;
    private SessionState f3601u = SessionState.RUNNING;
    private boolean f3602v = false;
    private ImageReader f3603w;

    class C05581 implements Runnable {
        final /* synthetic */ Camera2Session f3573a;

        C05581(Camera2Session this$0) {
            this.f3573a = this$0;
        }

        public void run() {
            this.f3573a.m2221d();
        }
    }

    private class CameraCaptureCallback extends CaptureCallback {
        final /* synthetic */ Camera2Session f3576a;

        private CameraCaptureCallback(Camera2Session camera2Session) {
            this.f3576a = camera2Session;
        }

        public void onCaptureFailed(CameraCaptureSession session, CaptureRequest request, CaptureFailure failure) {
            Logging.d("Camera2Session", "Capture failed: " + failure);
        }
    }

    private class CameraStateCallback extends StateCallback {
        final /* synthetic */ Camera2Session f3578a;

        class C05601 implements OnImageAvailableListener {
            final /* synthetic */ CameraStateCallback f3577a;

            C05601(CameraStateCallback this$1) {
                this.f3577a = this$1;
            }

            public void onImageAvailable(ImageReader reader) {
                Image image = reader.acquireLatestImage();
                if (image != null) {
                    long captureTimeNs = TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
                    int length = image.getWidth() * image.getHeight();
                    Plane[] planes = image.getPlanes();
                    byte[] data = new byte[((length * 6) / 4)];
                    ByteBuffer yPlane = planes[0].getBuffer();
                    yPlane.position(0);
                    yPlane.get(data, 0, Math.min(yPlane.capacity(), data.length));
                    ByteBuffer uPlane = planes[2].getBuffer();
                    int uLength = Math.min(length / 2, uPlane.capacity());
                    uPlane.position(0);
                    uPlane.get(data, length, uLength);
                    ByteBuffer vPlane = planes[1].getBuffer();
                    int vLength = Math.min(length / 2, vPlane.capacity());
                    vPlane.position(0);
                    for (int i = 0; i < vLength; i++) {
                        if ((i + length) + 1 < data.length) {
                            data[(i + length) + 1] = vPlane.get(i);
                        }
                    }
                    FUUtil.getInstance().applyFaceUnity(data, image.getWidth(), image.getHeight());
                    this.f3577a.f3578a.f3586f.onByteBufferFrameCaptured(data, image.getWidth(), image.getHeight(), this.f3577a.f3578a.m2224f(), captureTimeNs);
                    image.close();
                }
            }
        }

        private CameraStateCallback(Camera2Session camera2Session) {
            this.f3578a = camera2Session;
        }

        private String m2205a(int errorCode) {
            switch (errorCode) {
                case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                    return "Camera device is in use already.";
                case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                    return "Camera device could not be opened because there are too many other open camera devices.";
                case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                    return "Camera device could not be opened due to a device policy.";
                case swscale.SWS_CS_FCC /*4*/:
                    return "Camera device has encountered a fatal error.";
                case swscale.SWS_CS_SMPTE170M /*5*/:
                    return "Camera service has encountered a fatal error.";
                default:
                    return "Unknown camera error: " + errorCode;
            }
        }

        public void onDisconnected(CameraDevice camera) {
            this.f3578a.m2227g();
            this.f3578a.m2215a("Camera disconnected.");
        }

        public void onError(CameraDevice camera, int errorCode) {
            this.f3578a.m2227g();
            this.f3578a.m2215a(m2205a(errorCode));
        }

        public void onOpened(CameraDevice camera) {
            this.f3578a.m2227g();
            Logging.d("Camera2Session", "Camera opened.");
            this.f3578a.f3597q = camera;
            SurfaceTexture surfaceTexture = this.f3578a.f3587g.getSurfaceTexture();
            surfaceTexture.setDefaultBufferSize(this.f3578a.f3596p.width, this.f3578a.f3596p.height);
            this.f3578a.f3598r = new Surface(surfaceTexture);
            this.f3578a.f3603w = ImageReader.newInstance(this.f3578a.f3596p.width, this.f3578a.f3596p.height, 35, 3);
            this.f3578a.f3603w.setOnImageAvailableListener(new C05601(this), this.f3578a.f3581a);
            try {
                camera.createCaptureSession(Arrays.asList(new Surface[]{this.f3578a.f3598r, this.f3578a.f3603w.getSurface()}), new CaptureSessionCallback(), this.f3578a.f3581a);
            } catch (CameraAccessException e) {
                this.f3578a.m2215a("Failed to create capture session. " + e);
            }
        }

        public void onClosed(CameraDevice camera) {
            this.f3578a.m2227g();
            Logging.d("Camera2Session", "Camera device closed.");
            this.f3578a.f3584d.onCameraClosed();
        }
    }

    private class CaptureSessionCallback extends CameraCaptureSession.StateCallback {
        final /* synthetic */ Camera2Session f3579a;

        private CaptureSessionCallback(Camera2Session camera2Session) {
            this.f3579a = camera2Session;
        }

        public void onConfigureFailed(CameraCaptureSession session) {
            this.f3579a.m2227g();
            session.close();
            this.f3579a.m2215a("Failed to configure capture session.");
        }

        public void onConfigured(CameraCaptureSession session) {
            this.f3579a.m2227g();
            Logging.d("Camera2Session", "Camera capture session configured.");
            this.f3579a.f3599s = session;
            try {
                Builder captureRequestBuilder = this.f3579a.f3597q.createCaptureRequest(3);
                captureRequestBuilder.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, new Range(Integer.valueOf(this.f3579a.f3596p.framerate.min / this.f3579a.f3595o), Integer.valueOf(this.f3579a.f3596p.framerate.max / this.f3579a.f3595o)));
                captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(1));
                captureRequestBuilder.set(CaptureRequest.CONTROL_AE_LOCK, Boolean.valueOf(false));
                captureRequestBuilder.addTarget(this.f3579a.f3598r);
                captureRequestBuilder.addTarget(this.f3579a.f3603w.getSurface());
                session.setRepeatingRequest(captureRequestBuilder.build(), new CameraCaptureCallback(), this.f3579a.f3581a);
                this.f3579a.f3586f.onCapturerStarted(true);
                this.f3579a.f3600t = new CameraStatistics(this.f3579a.f3587g, this.f3579a.f3584d);
                Logging.d("Camera2Session", "Camera device successfully started.");
                this.f3579a.f3583c.onDone(this.f3579a);
            } catch (CameraAccessException e) {
                this.f3579a.m2215a("Failed to start capture request. " + e);
            }
        }
    }

    private enum SessionState {
        RUNNING,
        STOPPED
    }

    public static void create(CameraManager cameraManager, CreateSessionCallback callback, CameraEventsHandler eventsHandler, Context applicationContext, CapturerObserver capturerObserver, SurfaceTextureHelper surfaceTextureHelper, String cameraId, int width, int height, int framerate) {
        Camera2Session camera2Session = new Camera2Session(cameraManager, callback, eventsHandler, applicationContext, capturerObserver, surfaceTextureHelper, cameraId, width, height, framerate);
    }

    private Camera2Session(CameraManager cameraManager, CreateSessionCallback callback, CameraEventsHandler eventsHandler, Context applicationContext, CapturerObserver capturerObserver, SurfaceTextureHelper surfaceTextureHelper, String cameraId, int width, int height, int framerate) {
        Logging.d("Camera2Session", "Create new camera2 session on camera " + cameraId);
        this.f3581a = new Handler();
        this.f3582b = cameraManager;
        this.f3583c = callback;
        this.f3584d = eventsHandler;
        this.f3585e = applicationContext;
        this.f3586f = capturerObserver;
        this.f3587g = surfaceTextureHelper;
        this.f3588h = cameraId;
        this.f3589i = width;
        this.f3590j = height;
        this.f3591k = framerate;
        m2212a();
    }

    private void m2212a() {
        m2227g();
        Logging.d("Camera2Session", "start");
        try {
            this.f3592l = this.f3582b.getCameraCharacteristics(this.f3588h);
        } catch (CameraAccessException e) {
            m2215a("getCameraCharacteristics(): " + e.getMessage());
        }
        this.f3593m = ((Integer) this.f3592l.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
        this.f3594n = ((Integer) this.f3592l.get(CameraCharacteristics.LENS_FACING)).intValue() == 0;
        m2217b();
        m2219c();
    }

    private void m2217b() {
        m2227g();
        Range[] fpsRanges = (Range[]) this.f3592l.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
        this.f3595o = Camera2Enumerator.m2199a(fpsRanges);
        List<FramerateRange> framerateRanges = Camera2Enumerator.m2203a(fpsRanges, this.f3595o);
        List<Size> sizes = Camera2Enumerator.m2201a(this.f3592l);
        if (framerateRanges.isEmpty() || sizes.isEmpty()) {
            m2215a("No supported capture formats.");
        }
        FramerateRange bestFpsRange = CameraEnumerationAndroid.getClosestSupportedFramerateRange(framerateRanges, this.f3591k);
        Size bestSize = CameraEnumerationAndroid.getClosestSupportedSize(sizes, this.f3589i, this.f3590j);
        this.f3596p = new CaptureFormat(bestSize.width, bestSize.height, bestFpsRange);
        Logging.d("Camera2Session", "Using capture format: " + this.f3596p);
    }

    private void m2219c() {
        m2227g();
        Logging.d("Camera2Session", "Opening camera " + this.f3588h);
        int cameraIndex = -1;
        try {
            cameraIndex = Integer.parseInt(this.f3588h);
        } catch (NumberFormatException e) {
            Logging.d("Camera2Session", "External camera with non-int identifier: " + this.f3588h);
        }
        this.f3584d.onCameraOpening(cameraIndex);
        try {
            this.f3582b.openCamera(this.f3588h, new CameraStateCallback(), this.f3581a);
        } catch (CameraAccessException e2) {
            m2215a("Failed to open camera: " + e2);
        }
    }

    public void stop() {
        Logging.d("Camera2Session", "Stop camera2 session on camera " + this.f3588h);
        if (Thread.currentThread() != this.f3581a.getLooper().getThread()) {
            final CountDownLatch stopLatch = new CountDownLatch(1);
            this.f3581a.post(new Runnable(this) {
                final /* synthetic */ Camera2Session f3575b;

                public void run() {
                    if (this.f3575b.f3601u != SessionState.STOPPED) {
                        this.f3575b.f3601u = SessionState.STOPPED;
                        this.f3575b.f3586f.onCapturerStopped();
                        stopLatch.countDown();
                        this.f3575b.m2221d();
                    }
                }
            });
            ThreadUtils.awaitUninterruptibly(stopLatch);
        } else if (this.f3601u != SessionState.STOPPED) {
            this.f3601u = SessionState.STOPPED;
            this.f3586f.onCapturerStopped();
            this.f3581a.post(new C05581(this));
        }
    }

    private void m2221d() {
        Logging.d("Camera2Session", "Stop internal");
        m2227g();
        this.f3587g.stopListening();
        this.f3600t.release();
        this.f3599s.close();
        this.f3599s = null;
        this.f3598r.release();
        this.f3598r = null;
        this.f3597q.close();
        this.f3597q = null;
        Logging.d("Camera2Session", "Stop done");
    }

    private void m2215a(String error) {
        m2227g();
        Logging.e("Camera2Session", "Error: " + error);
        if (this.f3599s == null) {
            if (this.f3597q != null) {
                this.f3597q.close();
                this.f3597q = null;
            }
            this.f3601u = SessionState.STOPPED;
            this.f3583c.onFailure(error);
            this.f3586f.onCapturerStarted(false);
            return;
        }
        this.f3584d.onCameraError(error);
    }

    private int m2222e() {
        switch (((WindowManager) this.f3585e.getSystemService("window")).getDefaultDisplay().getRotation()) {
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                return 90;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                return avcodec.AV_CODEC_ID_VP7;
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                return 270;
            default:
                return 0;
        }
    }

    private int m2224f() {
        int rotation = m2222e();
        if (!this.f3594n) {
            rotation = 360 - rotation;
        }
        return (this.f3593m + rotation) % 360;
    }

    private void m2227g() {
        if (Thread.currentThread() != this.f3581a.getLooper().getThread()) {
            throw new IllegalStateException("Wrong thread");
        }
    }
}
