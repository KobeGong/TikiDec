package com.buddy.tiki.ui.activity;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$54 implements Runnable {
    private final CallActivity f1194a;

    private CallActivity$$Lambda$54(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static Runnable lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$54(callActivity);
    }

    @Hidden
    public void run() {
        this.a.m620c();
    }
}
