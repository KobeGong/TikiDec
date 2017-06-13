package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;
import org.webrtc.EglBase.Context;

final /* synthetic */ class PeerConnectionClient$$Lambda$3 implements Runnable {
    private final PeerConnectionClient f3649a;
    private final Context f3650b;

    private PeerConnectionClient$$Lambda$3(PeerConnectionClient peerConnectionClient, Context context) {
        this.a = peerConnectionClient;
        this.b = context;
    }

    public static Runnable lambdaFactory$(PeerConnectionClient peerConnectionClient, Context context) {
        return new PeerConnectionClient$$Lambda$3(peerConnectionClient, context);
    }

    @Hidden
    public void run() {
        this.a.m2298a(this.b);
    }
}
