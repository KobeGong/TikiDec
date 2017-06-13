package com.buddy.tiki.im;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class IMRtcClient$$Lambda$6 implements Runnable {
    private final IMRtcClient f822a;
    private final String f823b;

    private IMRtcClient$$Lambda$6(IMRtcClient iMRtcClient, String str) {
        this.a = iMRtcClient;
        this.b = str;
    }

    public static Runnable lambdaFactory$(IMRtcClient iMRtcClient, String str) {
        return new IMRtcClient$$Lambda$6(iMRtcClient, str);
    }

    @Hidden
    public void run() {
        this.a.m239b(this.b);
    }
}
