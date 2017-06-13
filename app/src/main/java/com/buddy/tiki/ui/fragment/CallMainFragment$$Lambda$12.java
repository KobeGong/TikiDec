package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallMainFragment$$Lambda$12 implements Consumer {
    private final CallMainFragment f2020a;
    private final String f2021b;

    private CallMainFragment$$Lambda$12(CallMainFragment callMainFragment, String str) {
        this.a = callMainFragment;
        this.b = str;
    }

    public static Consumer lambdaFactory$(CallMainFragment callMainFragment, String str) {
        return new CallMainFragment$$Lambda$12(callMainFragment, str);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1225a(this.b, (Boolean) obj);
    }
}
