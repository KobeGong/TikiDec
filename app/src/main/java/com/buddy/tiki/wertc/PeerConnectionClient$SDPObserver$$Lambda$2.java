package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PeerConnectionClient$SDPObserver$$Lambda$2 implements Runnable {
    private final SDPObserver f3701a;

    private PeerConnectionClient$SDPObserver$$Lambda$2(SDPObserver sDPObserver) {
        this.a = sDPObserver;
    }

    public static Runnable lambdaFactory$(SDPObserver sDPObserver) {
        return new PeerConnectionClient$SDPObserver$$Lambda$2(sDPObserver);
    }

    @Hidden
    public void run() {
        this.a.m2253a();
    }
}
