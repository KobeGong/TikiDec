package com.buddy.tiki.wertc;

import android.content.Context;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PeerConnectionClient$$Lambda$1 implements Runnable {
    private final PeerConnectionClient f3646a;
    private final Context f3647b;

    private PeerConnectionClient$$Lambda$1(PeerConnectionClient peerConnectionClient, Context context) {
        this.a = peerConnectionClient;
        this.b = context;
    }

    public static Runnable lambdaFactory$(PeerConnectionClient peerConnectionClient, Context context) {
        return new PeerConnectionClient$$Lambda$1(peerConnectionClient, context);
    }

    @Hidden
    public void run() {
        this.a.m2295a(this.b);
    }
}
