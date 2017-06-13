package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;
import org.webrtc.PeerConnection.IceConnectionState;

final /* synthetic */ class PeerConnectionClient$PCObserver$$Lambda$3 implements Runnable {
    private final PCObserver f3671a;
    private final IceConnectionState f3672b;

    private PeerConnectionClient$PCObserver$$Lambda$3(PCObserver pCObserver, IceConnectionState iceConnectionState) {
        this.a = pCObserver;
        this.b = iceConnectionState;
    }

    public static Runnable lambdaFactory$(PCObserver pCObserver, IceConnectionState iceConnectionState) {
        return new PeerConnectionClient$PCObserver$$Lambda$3(pCObserver, iceConnectionState);
    }

    @Hidden
    public void run() {
        this.a.m2251a(this.b);
    }
}
