package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;
import java.util.List;

final /* synthetic */ class SystemMessageFragment$$Lambda$2 implements Consumer {
    private final SystemMessageFragment f2219a;

    private SystemMessageFragment$$Lambda$2(SystemMessageFragment systemMessageFragment) {
        this.a = systemMessageFragment;
    }

    public static Consumer lambdaFactory$(SystemMessageFragment systemMessageFragment) {
        return new SystemMessageFragment$$Lambda$2(systemMessageFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1420a((List) obj);
    }
}
