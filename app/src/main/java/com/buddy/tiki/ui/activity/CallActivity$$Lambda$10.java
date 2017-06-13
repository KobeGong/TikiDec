package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.user.User;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$10 implements Consumer {
    private final CallActivity f1137a;

    private CallActivity$$Lambda$10(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static Consumer lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$10(callActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m634g((User) obj);
    }
}
