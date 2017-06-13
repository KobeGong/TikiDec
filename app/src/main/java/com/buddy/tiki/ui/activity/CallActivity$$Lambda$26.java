package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.user.User;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$26 implements Consumer {
    private final CallActivity f1154a;

    private CallActivity$$Lambda$26(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static Consumer lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$26(callActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m629d((User) obj);
    }
}
