package com.buddy.tiki.wertc;

import java.lang.invoke.LambdaForm.Hidden;
import org.webrtc.StatsObserver;
import org.webrtc.StatsReport;

final /* synthetic */ class PeerConnectionClient$$Lambda$8 implements StatsObserver {
    private final PeerConnectionClient f3660a;

    private PeerConnectionClient$$Lambda$8(PeerConnectionClient peerConnectionClient) {
        this.a = peerConnectionClient;
    }

    public static StatsObserver lambdaFactory$(PeerConnectionClient peerConnectionClient) {
        return new PeerConnectionClient$$Lambda$8(peerConnectionClient);
    }

    @Hidden
    public void onComplete(StatsReport[] statsReportArr) {
        this.a.m2303a(statsReportArr);
    }
}
