package com.buddy.tiki.wertc;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.camera2.CameraManager;
import java.util.List;
import org.webrtc.CameraCapturer;
import org.webrtc.CameraEnumerationAndroid.CaptureFormat;
import org.webrtc.CameraSession.CreateSessionCallback;
import org.webrtc.CameraVideoCapturer.CameraEventsHandler;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoCapturer.CapturerObserver;

@TargetApi(21)
public class Camera2Capturer extends CameraCapturer {
    private final Context f3568a;
    private final CameraManager f3569b;

    public Camera2Capturer(Context context, String cameraName, CameraEventsHandler eventsHandler) {
        super(cameraName, eventsHandler, new Camera2Enumerator(context));
        this.f3568a = context;
        this.f3569b = (CameraManager) context.getSystemService("camera");
    }

    public List<CaptureFormat> getSupportedFormats() {
        return Camera2Enumerator.m2202a(this.f3569b, getCameraName());
    }

    protected void createCameraSession(CreateSessionCallback createSessionCallback, CameraEventsHandler eventsHandler, Context applicationContext, CapturerObserver capturerObserver, SurfaceTextureHelper surfaceTextureHelper, String cameraName, int width, int height, int framerate) {
        Camera2Session.create(this.f3569b, createSessionCallback, eventsHandler, applicationContext, capturerObserver, surfaceTextureHelper, cameraName, width, height, framerate);
    }
}
