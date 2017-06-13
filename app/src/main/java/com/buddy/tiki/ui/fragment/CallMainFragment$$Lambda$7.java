package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallMainFragment$$Lambda$7 implements Consumer {
    private final CallMainFragment f2029a;

    private CallMainFragment$$Lambda$7(CallMainFragment callMainFragment) {
        this.a = callMainFragment;
    }

    public static Consumer lambdaFactory$(CallMainFragment callMainFragment) {
        return new CallMainFragment$$Lambda$7(callMainFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1228b((Boolean) obj);
    }
}
