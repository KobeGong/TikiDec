package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.model.user.FlwApplyResponse;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ApplyListFragment$$Lambda$4 implements Consumer {
    private final ApplyListFragment f2004a;

    private ApplyListFragment$$Lambda$4(ApplyListFragment applyListFragment) {
        this.a = applyListFragment;
    }

    public static Consumer lambdaFactory$(ApplyListFragment applyListFragment) {
        return new ApplyListFragment$$Lambda$4(applyListFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1215c((FlwApplyResponse) obj);
    }
}
