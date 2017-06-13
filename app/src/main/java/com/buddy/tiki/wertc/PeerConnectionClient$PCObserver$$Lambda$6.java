package com.buddy.tiki.wertc;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PeerConnectionClient$PCObserver$$Lambda$6 implements Consumer {
    private final PCObserver f3676a;

    private PeerConnectionClient$PCObserver$$Lambda$6(PCObserver pCObserver) {
        this.a = pCObserver;
    }

    public static Consumer lambdaFactory$(PCObserver pCObserver) {
        return new PeerConnectionClient$PCObserver$$Lambda$6(pCObserver);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m2248a((Long) obj);
    }
}
