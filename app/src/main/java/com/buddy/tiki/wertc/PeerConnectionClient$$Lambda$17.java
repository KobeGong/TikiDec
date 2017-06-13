package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PeerConnectionClient$$Lambda$17 implements Runnable {
    private final PeerConnectionClient f3642a;
    private final int f3643b;
    private final int f3644c;
    private final int f3645d;

    private PeerConnectionClient$$Lambda$17(PeerConnectionClient peerConnectionClient, int i, int i2, int i3) {
        this.a = peerConnectionClient;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    public static Runnable lambdaFactory$(PeerConnectionClient peerConnectionClient, int i, int i2, int i3) {
        return new PeerConnectionClient$$Lambda$17(peerConnectionClient, i, i2, i3);
    }

    @Hidden
    public void run() {
        this.a.m2294a(this.b, this.c, this.d);
    }
}
