package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;
import org.webrtc.IceCandidate;

final /* synthetic */ class PeerConnectionClient$$Lambda$11 implements Runnable {
    private final PeerConnectionClient f3632a;
    private final IceCandidate f3633b;

    private PeerConnectionClient$$Lambda$11(PeerConnectionClient peerConnectionClient, IceCandidate iceCandidate) {
        this.a = peerConnectionClient;
        this.b = iceCandidate;
    }

    public static Runnable lambdaFactory$(PeerConnectionClient peerConnectionClient, IceCandidate iceCandidate) {
        return new PeerConnectionClient$$Lambda$11(peerConnectionClient, iceCandidate);
    }

    @Hidden
    public void run() {
        this.a.m2299a(this.b);
    }
}
