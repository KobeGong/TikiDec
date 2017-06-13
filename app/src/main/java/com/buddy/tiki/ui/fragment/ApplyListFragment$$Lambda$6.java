package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.model.user.FlwApplyResponse;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ApplyListFragment$$Lambda$6 implements Consumer {
    private final ApplyListFragment f2006a;

    private ApplyListFragment$$Lambda$6(ApplyListFragment applyListFragment) {
        this.a = applyListFragment;
    }

    public static Consumer lambdaFactory$(ApplyListFragment applyListFragment) {
        return new ApplyListFragment$$Lambda$6(applyListFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1212a((FlwApplyResponse) obj);
    }
}
