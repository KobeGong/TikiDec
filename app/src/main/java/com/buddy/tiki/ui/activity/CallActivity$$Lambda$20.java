package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$20 implements Consumer {
    private final CallActivity f1148a;

    private CallActivity$$Lambda$20(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static Consumer lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$20(callActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m630d((Boolean) obj);
    }
}
