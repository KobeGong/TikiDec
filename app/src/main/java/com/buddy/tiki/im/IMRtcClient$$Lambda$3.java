package com.buddy.tiki.im;

import java.lang.invoke.LambdaForm.Hidden;
import org.webrtc.SessionDescription;

final /* synthetic */ class IMRtcClient$$Lambda$3 implements Runnable {
    private final IMRtcClient f817a;
    private final SessionDescription f818b;

    private IMRtcClient$$Lambda$3(IMRtcClient iMRtcClient, SessionDescription sessionDescription) {
        this.a = iMRtcClient;
        this.b = sessionDescription;
    }

    public static Runnable lambdaFactory$(IMRtcClient iMRtcClient, SessionDescription sessionDescription) {
        return new IMRtcClient$$Lambda$3(iMRtcClient, sessionDescription);
    }

    @Hidden
    public void run() {
        this.a.m237a(this.b);
    }
}
