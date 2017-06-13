package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;
import org.webrtc.IceCandidate;

final /* synthetic */ class PeerConnectionClient$PCObserver$$Lambda$2 implements Runnable {
    private final PCObserver f3669a;
    private final IceCandidate f3670b;

    private PeerConnectionClient$PCObserver$$Lambda$2(PCObserver pCObserver, IceCandidate iceCandidate) {
        this.a = pCObserver;
        this.b = iceCandidate;
    }

    public static Runnable lambdaFactory$(PCObserver pCObserver, IceCandidate iceCandidate) {
        return new PeerConnectionClient$PCObserver$$Lambda$2(pCObserver, iceCandidate);
    }

    @Hidden
    public void run() {
        this.a.m2249a(this.b);
    }
}
