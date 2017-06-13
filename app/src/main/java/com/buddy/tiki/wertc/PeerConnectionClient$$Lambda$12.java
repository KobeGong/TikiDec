package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;
import org.webrtc.SessionDescription;

final /* synthetic */ class PeerConnectionClient$$Lambda$12 implements Runnable {
    private final PeerConnectionClient f3634a;
    private final SessionDescription f3635b;

    private PeerConnectionClient$$Lambda$12(PeerConnectionClient peerConnectionClient, SessionDescription sessionDescription) {
        this.a = peerConnectionClient;
        this.b = sessionDescription;
    }

    public static Runnable lambdaFactory$(PeerConnectionClient peerConnectionClient, SessionDescription sessionDescription) {
        return new PeerConnectionClient$$Lambda$12(peerConnectionClient, sessionDescription);
    }

    @Hidden
    public void run() {
        this.a.m2300a(this.b);
    }
}
