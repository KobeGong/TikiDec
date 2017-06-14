package im.facechat;

import org.webrtc.CameraVideoCapturer.CameraSwitchHandler;

class Rtc$5 implements CameraSwitchHandler {
    final /* synthetic */ Rtc$SimpleActionCallback f11159a;

    Rtc$5(Rtc$SimpleActionCallback rtc$SimpleActionCallback) {
        this.a = rtc$SimpleActionCallback;
    }

    public void onCameraSwitchDone(boolean z) {
        if (this.a != null) {
            this.a.onResult(true, null);
        }
    }

    public void onCameraSwitchError(String str) {
        if (this.a != null) {
            this.a.onResult(false, str);
        }
    }
}
