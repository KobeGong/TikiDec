package com.buddy.tiki.ui.activity;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$29 implements Runnable {
    private final CallActivity f1157a;

    private CallActivity$$Lambda$29(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static Runnable lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$29(callActivity);
    }

    @Hidden
    public void run() {
        this.a.m490M();
    }
}
