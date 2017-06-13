package com.buddy.tiki.ui.activity;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$38 implements Runnable {
    private final CallActivity f1172a;

    private CallActivity$$Lambda$38(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static Runnable lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$38(callActivity);
    }

    @Hidden
    public void run() {
        this.a.m631e();
    }
}
