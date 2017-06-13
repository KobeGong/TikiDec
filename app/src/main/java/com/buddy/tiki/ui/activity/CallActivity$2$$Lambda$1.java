package com.buddy.tiki.ui.activity;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$2$$Lambda$1 implements Runnable {
    private final CallActivity f1232a;

    private CallActivity$2$$Lambda$1(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static Runnable lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$2$$Lambda$1(callActivity);
    }

    @Hidden
    public void run() {
        this.a.m501X();
    }
}
