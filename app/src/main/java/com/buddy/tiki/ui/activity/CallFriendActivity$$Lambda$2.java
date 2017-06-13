package com.buddy.tiki.ui.activity;

import com.buddy.tiki.wertc.BiuVideoCapturer2;
import im.facechat.Rtc.OnConstructCapturer;
import im.facechat.common.protocol.FacechatCapturer;
import java.lang.invoke.LambdaForm.Hidden;
import org.webrtc.CameraVideoCapturer.CameraEventsHandler;

final /* synthetic */ class CallFriendActivity$$Lambda$2 implements OnConstructCapturer {
    private static final CallFriendActivity$$Lambda$2 f1322a = new CallFriendActivity$$Lambda$2();

    private CallFriendActivity$$Lambda$2() {
    }

    public static OnConstructCapturer lambdaFactory$() {
        return a;
    }

    @Hidden
    public FacechatCapturer newInstance(String str, CameraEventsHandler cameraEventsHandler, boolean z) {
        return BiuVideoCapturer2.create(str, cameraEventsHandler, z);
    }
}
