package com.buddy.tiki.ui.activity;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$34 implements Runnable {
    private final CallActivity f1164a;
    private final String f1165b;
    private final String f1166c;

    private CallActivity$$Lambda$34(CallActivity callActivity, String str, String str2) {
        this.a = callActivity;
        this.b = str;
        this.c = str2;
    }

    public static Runnable lambdaFactory$(CallActivity callActivity, String str, String str2) {
        return new CallActivity$$Lambda$34(callActivity, str, str2);
    }

    @Hidden
    public void run() {
        this.a.m619b(this.b, this.c);
    }
}
