package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.user.User;
import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$40 implements Predicate {
    private static final CallActivity$$Lambda$40 f1175a = new CallActivity$$Lambda$40();

    private CallActivity$$Lambda$40() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return CallActivity.m526b((User) obj);
    }
}
