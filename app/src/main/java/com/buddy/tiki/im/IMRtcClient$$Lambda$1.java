package com.buddy.tiki.im;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class IMRtcClient$$Lambda$1 implements Runnable {
    private final IMRtcClient f812a;
    private final String f813b;
    private final boolean f814c;

    private IMRtcClient$$Lambda$1(IMRtcClient iMRtcClient, String str, boolean z) {
        this.a = iMRtcClient;
        this.b = str;
        this.c = z;
    }

    public static Runnable lambdaFactory$(IMRtcClient iMRtcClient, String str, boolean z) {
        return new IMRtcClient$$Lambda$1(iMRtcClient, str, z);
    }

    @Hidden
    public void run() {
        this.a.m235a(this.b, this.c);
    }
}
