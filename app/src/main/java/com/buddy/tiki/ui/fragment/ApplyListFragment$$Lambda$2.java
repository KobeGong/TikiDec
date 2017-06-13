package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ApplyListFragment$$Lambda$2 implements Consumer {
    private final ApplyListFragment f2002a;

    private ApplyListFragment$$Lambda$2(ApplyListFragment applyListFragment) {
        this.a = applyListFragment;
    }

    public static Consumer lambdaFactory$(ApplyListFragment applyListFragment) {
        return new ApplyListFragment$$Lambda$2(applyListFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1214b(obj);
    }
}
