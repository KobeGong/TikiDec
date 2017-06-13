package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$27 implements Consumer {
    private static final CallActivity$$Lambda$27 f1155a = new CallActivity$$Lambda$27();

    private CallActivity$$Lambda$27() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        CallActivity.f1249a.m264e("poll fail", (Throwable) obj);
    }
}
