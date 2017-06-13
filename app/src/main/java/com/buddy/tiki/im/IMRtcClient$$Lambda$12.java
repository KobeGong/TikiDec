package com.buddy.tiki.im;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class IMRtcClient$$Lambda$12 implements Runnable {
    private final IMRtcClient f811a;

    private IMRtcClient$$Lambda$12(IMRtcClient iMRtcClient) {
        this.a = iMRtcClient;
    }

    public static Runnable lambdaFactory$(IMRtcClient iMRtcClient) {
        return new IMRtcClient$$Lambda$12(iMRtcClient);
    }

    @Hidden
    public void run() {
        this.a.m233a();
    }
}
