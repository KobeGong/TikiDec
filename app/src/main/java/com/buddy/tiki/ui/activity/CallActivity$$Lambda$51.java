package com.buddy.tiki.ui.activity;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$51 implements Runnable {
    private final CallActivity f1189a;
    private final int f1190b;

    private CallActivity$$Lambda$51(CallActivity callActivity, int i) {
        this.a = callActivity;
        this.b = i;
    }

    public static Runnable lambdaFactory$(CallActivity callActivity, int i) {
        return new CallActivity$$Lambda$51(callActivity, i);
    }

    @Hidden
    public void run() {
        this.a.m595a(this.b);
    }
}
