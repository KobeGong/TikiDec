package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallMainFragment$$Lambda$13 implements Consumer {
    private final CallMainFragment f2022a;

    private CallMainFragment$$Lambda$13(CallMainFragment callMainFragment) {
        this.a = callMainFragment;
    }

    public static Consumer lambdaFactory$(CallMainFragment callMainFragment) {
        return new CallMainFragment$$Lambda$13(callMainFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1226a((Throwable) obj);
    }
}
