package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$1 implements Consumer {
    private final CallActivity f1147a;

    private CallActivity$$Lambda$1(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static Consumer lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$1(callActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m625c(obj);
    }
}
