package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallMainFragment$$Lambda$8 implements Consumer {
    private final CallMainFragment f2030a;

    private CallMainFragment$$Lambda$8(CallMainFragment callMainFragment) {
        this.a = callMainFragment;
    }

    public static Consumer lambdaFactory$(CallMainFragment callMainFragment) {
        return new CallMainFragment$$Lambda$8(callMainFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1229b((Throwable) obj);
    }
}
