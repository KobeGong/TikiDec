package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$8$$Lambda$6 implements Consumer {
    private static final CallActivity$8$$Lambda$6 f1243a = new CallActivity$8$$Lambda$6();

    private CallActivity$8$$Lambda$6() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        CallActivity.f1249a.m263e("upload error " + ((Throwable) obj));
    }
}
