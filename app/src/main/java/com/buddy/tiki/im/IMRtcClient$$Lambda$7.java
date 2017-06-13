package com.buddy.tiki.im;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class IMRtcClient$$Lambda$7 implements Runnable {
    private final IMRtcClient f824a;

    private IMRtcClient$$Lambda$7(IMRtcClient iMRtcClient) {
        this.a = iMRtcClient;
    }

    public static Runnable lambdaFactory$(IMRtcClient iMRtcClient) {
        return new IMRtcClient$$Lambda$7(iMRtcClient);
    }

    @Hidden
    public void run() {
        this.a.m238b();
    }
}
