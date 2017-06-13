package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;
import org.webrtc.PeerConnection.IceServer;
import org.webrtc.VideoRenderer.Callbacks;

final /* synthetic */ class PeerConnectionClient$$Lambda$5 implements Runnable {
    private final PeerConnectionClient f3652a;
    private final Callbacks f3653b;
    private final IceServer f3654c;

    private PeerConnectionClient$$Lambda$5(PeerConnectionClient peerConnectionClient, Callbacks callbacks, IceServer iceServer) {
        this.a = peerConnectionClient;
        this.b = callbacks;
        this.c = iceServer;
    }

    public static Runnable lambdaFactory$(PeerConnectionClient peerConnectionClient, Callbacks callbacks, IceServer iceServer) {
        return new PeerConnectionClient$$Lambda$5(peerConnectionClient, callbacks, iceServer);
    }

    @Hidden
    public void run() {
        this.a.m2305b(this.b, this.c);
    }
}
