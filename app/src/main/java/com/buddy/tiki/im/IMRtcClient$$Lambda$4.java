package com.buddy.tiki.im;

import java.lang.invoke.LambdaForm.Hidden;
import org.webrtc.IceCandidate;

final /* synthetic */ class IMRtcClient$$Lambda$4 implements Runnable {
    private final IMRtcClient f819a;
    private final IceCandidate f820b;

    private IMRtcClient$$Lambda$4(IMRtcClient iMRtcClient, IceCandidate iceCandidate) {
        this.a = iMRtcClient;
        this.b = iceCandidate;
    }

    public static Runnable lambdaFactory$(IMRtcClient iMRtcClient, IceCandidate iceCandidate) {
        return new IMRtcClient$$Lambda$4(iMRtcClient, iceCandidate);
    }

    @Hidden
    public void run() {
        this.a.m236a(this.b);
    }
}
