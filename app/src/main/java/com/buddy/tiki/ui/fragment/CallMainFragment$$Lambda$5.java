package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallMainFragment$$Lambda$5 implements Consumer {
    private final CallMainFragment f2027a;

    private CallMainFragment$$Lambda$5(CallMainFragment callMainFragment) {
        this.a = callMainFragment;
    }

    public static Consumer lambdaFactory$(CallMainFragment callMainFragment) {
        return new CallMainFragment$$Lambda$5(callMainFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1232d((Boolean) obj);
    }
}
