package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$RtcReceiver$$Lambda$1 implements Consumer {
    private static final CallActivity$RtcReceiver$$Lambda$1 f1246a = new CallActivity$RtcReceiver$$Lambda$1();

    private CallActivity$RtcReceiver$$Lambda$1() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        RtcReceiver.m476a((Boolean) obj);
    }
}
