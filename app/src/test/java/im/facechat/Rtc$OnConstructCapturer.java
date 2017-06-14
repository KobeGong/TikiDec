package im.facechat;

import im.facechat.common.protocol.FacechatCapturer;
import org.webrtc.CameraVideoCapturer.CameraEventsHandler;

public interface Rtc$OnConstructCapturer<F extends FacechatCapturer> {
    F newInstance(String str, CameraEventsHandler cameraEventsHandler, boolean z);
}
