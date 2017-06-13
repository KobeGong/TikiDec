package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PeerConnectionClient$$Lambda$13 implements Runnable {
    private final PeerConnectionClient f3636a;

    private PeerConnectionClient$$Lambda$13(PeerConnectionClient peerConnectionClient) {
        this.a = peerConnectionClient;
    }

    public static Runnable lambdaFactory$(PeerConnectionClient peerConnectionClient) {
        return new PeerConnectionClient$$Lambda$13(peerConnectionClient);
    }

    @Hidden
    public void run() {
        this.a.m2304b();
    }
}
