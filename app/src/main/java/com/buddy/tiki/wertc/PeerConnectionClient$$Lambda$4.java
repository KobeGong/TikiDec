package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PeerConnectionClient$$Lambda$4 implements Runnable {
    private final PeerConnectionClient f3651a;

    private PeerConnectionClient$$Lambda$4(PeerConnectionClient peerConnectionClient) {
        this.a = peerConnectionClient;
    }

    public static Runnable lambdaFactory$(PeerConnectionClient peerConnectionClient) {
        return new PeerConnectionClient$$Lambda$4(peerConnectionClient);
    }

    @Hidden
    public void run() {
        this.a.m2281i();
    }
}
