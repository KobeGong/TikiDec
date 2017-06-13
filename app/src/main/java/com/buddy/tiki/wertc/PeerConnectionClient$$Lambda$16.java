package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;
import org.webrtc.CameraVideoCapturer.CameraSwitchHandler;

final /* synthetic */ class PeerConnectionClient$$Lambda$16 implements Runnable {
    private final PeerConnectionClient f3640a;
    private final CameraSwitchHandler f3641b;

    private PeerConnectionClient$$Lambda$16(PeerConnectionClient peerConnectionClient, CameraSwitchHandler cameraSwitchHandler) {
        this.a = peerConnectionClient;
        this.b = cameraSwitchHandler;
    }

    public static Runnable lambdaFactory$(PeerConnectionClient peerConnectionClient, CameraSwitchHandler cameraSwitchHandler) {
        return new PeerConnectionClient$$Lambda$16(peerConnectionClient, cameraSwitchHandler);
    }

    @Hidden
    public void run() {
        this.a.m2297a(this.b);
    }
}
