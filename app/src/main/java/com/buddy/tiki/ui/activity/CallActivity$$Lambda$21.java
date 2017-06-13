package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$21 implements Consumer {
    private static final CallActivity$$Lambda$21 f1149a = new CallActivity$$Lambda$21();

    private CallActivity$$Lambda$21() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        CallActivity.f1249a.m264e("request permission", (Throwable) obj);
    }
}
