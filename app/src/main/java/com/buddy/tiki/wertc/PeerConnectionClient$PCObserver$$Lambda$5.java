package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PeerConnectionClient$PCObserver$$Lambda$5 implements Runnable {
    private final PCObserver f3675a;

    private PeerConnectionClient$PCObserver$$Lambda$5(PCObserver pCObserver) {
        this.a = pCObserver;
    }

    public static Runnable lambdaFactory$(PCObserver pCObserver) {
        return new PeerConnectionClient$PCObserver$$Lambda$5(pCObserver);
    }

    @Hidden
    public void run() {
        this.a.m2247a();
    }
}
