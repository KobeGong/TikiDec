package im.facechat.common.protocol;

import android.hardware.Camera.PreviewCallback;
import android.support.annotation.CheckResult;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.CameraVideoCapturer.CameraEventsHandler;
import org.webrtc.SurfaceTextureHelper.OnTextureFrameAvailableListener;

public abstract class FacechatCapturer implements PreviewCallback, CameraVideoCapturer, OnTextureFrameAvailableListener {

    public interface TextureCallback {
        void onTextureAvailable(int i, int i2, float[] fArr, int i3, long j, Runnable runnable);
    }

    private FacechatCapturer() {
    }

    public FacechatCapturer(String str, CameraEventsHandler cameraEventsHandler) {
    }

    public FacechatCapturer(String str, CameraEventsHandler cameraEventsHandler, boolean z) {
    }

    @CheckResult
    public static CameraVideoCapturer create(String str, CameraEventsHandler cameraEventsHandler) {
        return null;
    }

    @CheckResult
    public static CameraVideoCapturer create(String str, CameraEventsHandler cameraEventsHandler, boolean z) {
        return null;
    }
}
