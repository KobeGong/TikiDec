package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.user.User;
import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$24 implements Predicate {
    private final CallActivity f1152a;

    private CallActivity$$Lambda$24(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static Predicate lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$24(callActivity);
    }

    @Hidden
    public boolean test(Object obj) {
        return this.a.m633f((User) obj);
    }
}
