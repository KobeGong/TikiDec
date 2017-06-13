package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.app.OperInfo;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$45 implements Function {
    private final CallActivity f1181a;

    private CallActivity$$Lambda$45(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static Function lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$45(callActivity);
    }

    @Hidden
    public Object apply(Object obj) {
        return this.a.m611b((OperInfo) obj);
    }
}
