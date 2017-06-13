package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallMainFragment$$Lambda$3 implements Consumer {
    private final CallMainFragment f2025a;

    private CallMainFragment$$Lambda$3(CallMainFragment callMainFragment) {
        this.a = callMainFragment;
    }

    public static Consumer lambdaFactory$(CallMainFragment callMainFragment) {
        return new CallMainFragment$$Lambda$3(callMainFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1231c(obj);
    }
}
