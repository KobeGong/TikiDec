package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PeerConnectionClient$5$$Lambda$1 implements Runnable {
    private final PeerConnectionClient f3665a;

    private PeerConnectionClient$5$$Lambda$1(PeerConnectionClient peerConnectionClient) {
        this.a = peerConnectionClient;
    }

    public static Runnable lambdaFactory$(PeerConnectionClient peerConnectionClient) {
        return new PeerConnectionClient$5$$Lambda$1(peerConnectionClient);
    }

    @Hidden
    public void run() {
        this.a.m2283j();
    }
}
