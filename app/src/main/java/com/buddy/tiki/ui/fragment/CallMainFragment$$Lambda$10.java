package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallMainFragment$$Lambda$10 implements Function {
    private final CallMainFragment f2017a;
    private final String f2018b;

    private CallMainFragment$$Lambda$10(CallMainFragment callMainFragment, String str) {
        this.a = callMainFragment;
        this.b = str;
    }

    public static Function lambdaFactory$(CallMainFragment callMainFragment, String str) {
        return new CallMainFragment$$Lambda$10(callMainFragment, str);
    }

    @Hidden
    public Object apply(Object obj) {
        return this.a.m1222a(this.b, obj);
    }
}
