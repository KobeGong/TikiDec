package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PeerConnectionClient$$Lambda$9 implements Runnable {
    private final PeerConnectionClient f3661a;

    private PeerConnectionClient$$Lambda$9(PeerConnectionClient peerConnectionClient) {
        this.a = peerConnectionClient;
    }

    public static Runnable lambdaFactory$(PeerConnectionClient peerConnectionClient) {
        return new PeerConnectionClient$$Lambda$9(peerConnectionClient);
    }

    @Hidden
    public void run() {
        this.a.m2307d();
    }
}
