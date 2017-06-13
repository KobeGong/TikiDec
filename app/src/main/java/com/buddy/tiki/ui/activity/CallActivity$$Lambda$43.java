package com.buddy.tiki.ui.activity;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$43 implements Runnable {
    private final CallActivity f1178a;
    private final int f1179b;

    private CallActivity$$Lambda$43(CallActivity callActivity, int i) {
        this.a = callActivity;
        this.b = i;
    }

    public static Runnable lambdaFactory$(CallActivity callActivity, int i) {
        return new CallActivity$$Lambda$43(callActivity, i);
    }

    @Hidden
    public void run() {
        this.a.m612b(this.b);
    }
}
