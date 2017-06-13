package com.buddy.tiki.ui.activity;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$53 implements Runnable {
    private final CallActivity f1193a;

    private CallActivity$$Lambda$53(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static Runnable lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$53(callActivity);
    }

    @Hidden
    public void run() {
        this.a.m627d();
    }
}
