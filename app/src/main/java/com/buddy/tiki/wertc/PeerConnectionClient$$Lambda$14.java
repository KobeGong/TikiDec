package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PeerConnectionClient$$Lambda$14 implements Runnable {
    private final PeerConnectionClient f3637a;

    private PeerConnectionClient$$Lambda$14(PeerConnectionClient peerConnectionClient) {
        this.a = peerConnectionClient;
    }

    public static Runnable lambdaFactory$(PeerConnectionClient peerConnectionClient) {
        return new PeerConnectionClient$$Lambda$14(peerConnectionClient);
    }

    @Hidden
    public void run() {
        this.a.m2293a();
    }
}
