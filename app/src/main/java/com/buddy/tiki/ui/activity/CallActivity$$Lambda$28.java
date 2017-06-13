package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Action;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$28 implements Action {
    private final CallActivity f1156a;

    private CallActivity$$Lambda$28(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static Action lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$28(callActivity);
    }

    @Hidden
    public void run() {
        this.a.m632f();
    }
}
