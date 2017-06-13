package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;
import org.webrtc.SessionDescription;

final /* synthetic */ class PeerConnectionClient$SDPObserver$$Lambda$1 implements Runnable {
    private final SDPObserver f3699a;
    private final SessionDescription f3700b;

    private PeerConnectionClient$SDPObserver$$Lambda$1(SDPObserver sDPObserver, SessionDescription sessionDescription) {
        this.a = sDPObserver;
        this.b = sessionDescription;
    }

    public static Runnable lambdaFactory$(SDPObserver sDPObserver, SessionDescription sessionDescription) {
        return new PeerConnectionClient$SDPObserver$$Lambda$1(sDPObserver, sessionDescription);
    }

    @Hidden
    public void run() {
        this.a.m2254a(this.b);
    }
}
