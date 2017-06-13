package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.app.ConfigInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$4 implements Consumer {
    private final CallActivity f1186a;

    private CallActivity$$Lambda$4(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static Consumer lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$4(callActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m599a((ConfigInfo) obj);
    }
}
