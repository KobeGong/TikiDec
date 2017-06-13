package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallMainFragment$$Lambda$1 implements Predicate {
    private final CallMainFragment f2023a;

    private CallMainFragment$$Lambda$1(CallMainFragment callMainFragment) {
        this.a = callMainFragment;
    }

    public static Predicate lambdaFactory$(CallMainFragment callMainFragment) {
        return new CallMainFragment$$Lambda$1(callMainFragment);
    }

    @Hidden
    public boolean test(Object obj) {
        return this.a.m1234e(obj);
    }
}
