package com.buddy.tiki.ui.activity;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$14 implements Function {
    private final CallActivity f1141a;

    private CallActivity$$Lambda$14(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static Function lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$14(callActivity);
    }

    @Hidden
    public Object apply(Object obj) {
        return this.a.m592a((Observable) obj);
    }
}
