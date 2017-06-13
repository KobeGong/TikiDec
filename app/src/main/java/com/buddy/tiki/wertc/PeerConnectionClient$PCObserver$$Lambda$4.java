package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;
import org.webrtc.MediaStream;

final /* synthetic */ class PeerConnectionClient$PCObserver$$Lambda$4 implements Runnable {
    private final PCObserver f3673a;
    private final MediaStream f3674b;

    private PeerConnectionClient$PCObserver$$Lambda$4(PCObserver pCObserver, MediaStream mediaStream) {
        this.a = pCObserver;
        this.b = mediaStream;
    }

    public static Runnable lambdaFactory$(PCObserver pCObserver, MediaStream mediaStream) {
        return new PeerConnectionClient$PCObserver$$Lambda$4(pCObserver, mediaStream);
    }

    @Hidden
    public void run() {
        this.a.m2250a(this.b);
    }
}
