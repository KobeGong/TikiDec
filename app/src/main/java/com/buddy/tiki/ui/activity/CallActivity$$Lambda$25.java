package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.user.User;
import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$25 implements Predicate {
    private static final CallActivity$$Lambda$25 f1153a = new CallActivity$$Lambda$25();

    private CallActivity$$Lambda$25() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return CallActivity.m544e((User) obj);
    }
}
