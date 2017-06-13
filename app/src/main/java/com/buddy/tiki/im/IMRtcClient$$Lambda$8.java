package com.buddy.tiki.im;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class IMRtcClient$$Lambda$8 implements Runnable {
    private final IMRtcClient f825a;
    private final String f826b;

    private IMRtcClient$$Lambda$8(IMRtcClient iMRtcClient, String str) {
        this.a = iMRtcClient;
        this.b = str;
    }

    public static Runnable lambdaFactory$(IMRtcClient iMRtcClient, String str) {
        return new IMRtcClient$$Lambda$8(iMRtcClient, str);
    }

    @Hidden
    public void run() {
        this.a.m234a(this.b);
    }
}
