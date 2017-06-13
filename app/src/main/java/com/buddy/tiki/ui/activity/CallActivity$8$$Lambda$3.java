package com.buddy.tiki.ui.activity;

import com.buddy.tiki.ui.activity.CallActivity.C04258;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$8$$Lambda$3 implements Function {
    private final C04258 f1239a;

    private CallActivity$8$$Lambda$3(C04258 c04258) {
        this.a = c04258;
    }

    public static Function lambdaFactory$(C04258 c04258) {
        return new CallActivity$8$$Lambda$3(c04258);
    }

    @Hidden
    public Object apply(Object obj) {
        return this.a.m473a((byte[]) obj);
    }
}
