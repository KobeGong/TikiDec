package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.app.OperInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$61 implements Consumer {
    private final CallActivity f1203a;

    private CallActivity$$Lambda$61(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static Consumer lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$61(callActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m600a((OperInfo) obj);
    }
}
