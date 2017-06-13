package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$37 implements Consumer {
    private static final CallActivity$$Lambda$37 f1171a = new CallActivity$$Lambda$37();

    private CallActivity$$Lambda$37() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        CallActivity.f1249a.m264e("room message error", (Throwable) obj);
    }
}
