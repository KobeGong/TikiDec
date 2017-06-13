package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallMainFragment$$Lambda$11 implements Function {
    private final CallMainFragment f2019a;

    private CallMainFragment$$Lambda$11(CallMainFragment callMainFragment) {
        this.a = callMainFragment;
    }

    public static Function lambdaFactory$(CallMainFragment callMainFragment) {
        return new CallMainFragment$$Lambda$11(callMainFragment);
    }

    @Hidden
    public Object apply(Object obj) {
        return this.a.m1221a((Boolean) obj);
    }
}
