package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$RtcReceiver$$Lambda$2 implements Consumer {
    private static final CallActivity$RtcReceiver$$Lambda$2 f1247a = new CallActivity$RtcReceiver$$Lambda$2();

    private CallActivity$RtcReceiver$$Lambda$2() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        RtcReceiver.m477a((Throwable) obj);
    }
}
