package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PeerConnectionClient$$Lambda$2 implements Runnable {
    private final PeerConnectionClient f3648a;

    private PeerConnectionClient$$Lambda$2(PeerConnectionClient peerConnectionClient) {
        this.a = peerConnectionClient;
    }

    public static Runnable lambdaFactory$(PeerConnectionClient peerConnectionClient) {
        return new PeerConnectionClient$$Lambda$2(peerConnectionClient);
    }

    @Hidden
    public void run() {
        this.a.m2308e();
    }
}
