package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SystemMessageFragment$$Lambda$4 implements Consumer {
    private final SystemMessageFragment f2221a;

    private SystemMessageFragment$$Lambda$4(SystemMessageFragment systemMessageFragment) {
        this.a = systemMessageFragment;
    }

    public static Consumer lambdaFactory$(SystemMessageFragment systemMessageFragment) {
        return new SystemMessageFragment$$Lambda$4(systemMessageFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1419a(obj);
    }
}
