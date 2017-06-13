package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$23 implements Function {
    private final CallActivity f1151a;

    private CallActivity$$Lambda$23(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static Function lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$23(callActivity);
    }

    @Hidden
    public Object apply(Object obj) {
        return this.a.m593a((Long) obj);
    }
}
