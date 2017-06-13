package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PeerConnectionClient$$Lambda$7 implements Runnable {
    private final PeerConnectionClient f3658a;
    private final boolean f3659b;

    private PeerConnectionClient$$Lambda$7(PeerConnectionClient peerConnectionClient, boolean z) {
        this.a = peerConnectionClient;
        this.b = z;
    }

    public static Runnable lambdaFactory$(PeerConnectionClient peerConnectionClient, boolean z) {
        return new PeerConnectionClient$$Lambda$7(peerConnectionClient, z);
    }

    @Hidden
    public void run() {
        this.a.m2302a(this.b);
    }
}
