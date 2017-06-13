package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$5 implements Consumer {
    private static final CallActivity$$Lambda$5 f1201a = new CallActivity$$Lambda$5();

    private CallActivity$$Lambda$5() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        CallActivity.f1249a.m264e("getConfigInfo failed", (Throwable) obj);
    }
}
