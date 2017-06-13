package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.im.MatchResult;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$15 implements Function {
    private final CallActivity f1142a;

    private CallActivity$$Lambda$15(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static Function lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$15(callActivity);
    }

    @Hidden
    public Object apply(Object obj) {
        return this.a.m591a((MatchResult) obj);
    }
}
