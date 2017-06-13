package com.buddy.tiki.im;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class IMRtcClient$$Lambda$5 implements Runnable {
    private final IMRtcClient f821a;

    private IMRtcClient$$Lambda$5(IMRtcClient iMRtcClient) {
        this.a = iMRtcClient;
    }

    public static Runnable lambdaFactory$(IMRtcClient iMRtcClient) {
        return new IMRtcClient$$Lambda$5(iMRtcClient);
    }

    @Hidden
    public void run() {
        this.a.m231f();
    }
}
