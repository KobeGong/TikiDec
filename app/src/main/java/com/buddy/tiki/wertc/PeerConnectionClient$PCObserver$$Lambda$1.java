package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;
import org.webrtc.IceCandidate;

final /* synthetic */ class PeerConnectionClient$PCObserver$$Lambda$1 implements Runnable {
    private final PCObserver f3667a;
    private final IceCandidate[] f3668b;

    private PeerConnectionClient$PCObserver$$Lambda$1(PCObserver pCObserver, IceCandidate[] iceCandidateArr) {
        this.a = pCObserver;
        this.b = iceCandidateArr;
    }

    public static Runnable lambdaFactory$(PCObserver pCObserver, IceCandidate[] iceCandidateArr) {
        return new PeerConnectionClient$PCObserver$$Lambda$1(pCObserver, iceCandidateArr);
    }

    @Hidden
    public void run() {
        this.a.m2252a(this.b);
    }
}
