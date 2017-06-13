package com.buddy.tiki.ui.activity;

import java.lang.invoke.LambdaForm.Hidden;
import java.util.concurrent.Callable;

final /* synthetic */ class CallActivity$$Lambda$35 implements Callable {
    private final CallActivity f1167a;
    private final String f1168b;
    private final String f1169c;

    private CallActivity$$Lambda$35(CallActivity callActivity, String str, String str2) {
        this.a = callActivity;
        this.b = str;
        this.c = str2;
    }

    public static Callable lambdaFactory$(CallActivity callActivity, String str, String str2) {
        return new CallActivity$$Lambda$35(callActivity, str, str2);
    }

    @Hidden
    public Object call() {
        return this.a.m594a(this.b, this.c);
    }
}
