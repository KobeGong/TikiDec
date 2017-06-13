package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PeerConnectionClient$$Lambda$15 implements Runnable {
    private final PeerConnectionClient f3638a;
    private final String f3639b;

    private PeerConnectionClient$$Lambda$15(PeerConnectionClient peerConnectionClient, String str) {
        this.a = peerConnectionClient;
        this.b = str;
    }

    public static Runnable lambdaFactory$(PeerConnectionClient peerConnectionClient, String str) {
        return new PeerConnectionClient$$Lambda$15(peerConnectionClient, str);
    }

    @Hidden
    public void run() {
        this.a.m2296a(this.b);
    }
}
