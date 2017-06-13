package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ApplyListFragment$$Lambda$3 implements Consumer {
    private final ApplyListFragment f2003a;

    private ApplyListFragment$$Lambda$3(ApplyListFragment applyListFragment) {
        this.a = applyListFragment;
    }

    public static Consumer lambdaFactory$(ApplyListFragment applyListFragment) {
        return new ApplyListFragment$$Lambda$3(applyListFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1213a(obj);
    }
}
