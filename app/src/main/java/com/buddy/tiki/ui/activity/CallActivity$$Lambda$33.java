package com.buddy.tiki.ui.activity;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$33 implements Runnable {
    private final CallActivity f1163a;

    private CallActivity$$Lambda$33(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static Runnable lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$33(callActivity);
    }

    @Hidden
    public void run() {
        this.a.m501X();
    }
}
