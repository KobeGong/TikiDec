package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PeerConnectionClient$$Lambda$10 implements Runnable {
    private final PeerConnectionClient f3631a;

    private PeerConnectionClient$$Lambda$10(PeerConnectionClient peerConnectionClient) {
        this.a = peerConnectionClient;
    }

    public static Runnable lambdaFactory$(PeerConnectionClient peerConnectionClient) {
        return new PeerConnectionClient$$Lambda$10(peerConnectionClient);
    }

    @Hidden
    public void run() {
        this.a.m2306c();
    }
}
