package com.buddy.tiki.im;

import java.lang.invoke.LambdaForm.Hidden;
import org.webrtc.SessionDescription;

final /* synthetic */ class IMRtcClient$$Lambda$2 implements Runnable {
    private final IMRtcClient f815a;
    private final SessionDescription f816b;

    private IMRtcClient$$Lambda$2(IMRtcClient iMRtcClient, SessionDescription sessionDescription) {
        this.a = iMRtcClient;
        this.b = sessionDescription;
    }

    public static Runnable lambdaFactory$(IMRtcClient iMRtcClient, SessionDescription sessionDescription) {
        return new IMRtcClient$$Lambda$2(iMRtcClient, sessionDescription);
    }

    @Hidden
    public void run() {
        this.a.m240b(this.b);
    }
}
