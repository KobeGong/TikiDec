package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.app.ConfigInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$3 implements Consumer {
    private final CallActivity f1174a;

    private CallActivity$$Lambda$3(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static Consumer lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$3(callActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m616b((ConfigInfo) obj);
    }
}
