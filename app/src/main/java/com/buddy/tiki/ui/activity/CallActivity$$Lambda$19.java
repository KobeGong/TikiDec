package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$19 implements Consumer {
    private final CallActivity f1146a;

    private CallActivity$$Lambda$19(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static Consumer lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$19(callActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m608a(obj);
    }
}
