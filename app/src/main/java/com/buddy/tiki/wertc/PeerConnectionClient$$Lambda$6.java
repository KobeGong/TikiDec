package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;
import org.webrtc.PeerConnection.IceServer;
import org.webrtc.VideoRenderer.Callbacks;

final /* synthetic */ class PeerConnectionClient$$Lambda$6 implements Runnable {
    private final PeerConnectionClient f3655a;
    private final Callbacks f3656b;
    private final IceServer f3657c;

    private PeerConnectionClient$$Lambda$6(PeerConnectionClient peerConnectionClient, Callbacks callbacks, IceServer iceServer) {
        this.a = peerConnectionClient;
        this.b = callbacks;
        this.c = iceServer;
    }

    public static Runnable lambdaFactory$(PeerConnectionClient peerConnectionClient, Callbacks callbacks, IceServer iceServer) {
        return new PeerConnectionClient$$Lambda$6(peerConnectionClient, callbacks, iceServer);
    }

    @Hidden
    public void run() {
        this.a.m2301a(this.b, this.c);
    }
}
