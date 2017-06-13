package com.buddy.tiki.ui.adapter;

import com.buddy.tiki.model.user.FollowApply;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ApplyListAdapter$$Lambda$5 implements Consumer {
    private final FollowApply f1652a;
    private final ApplyHolder f1653b;

    private ApplyListAdapter$$Lambda$5(FollowApply followApply, ApplyHolder applyHolder) {
        this.a = followApply;
        this.b = applyHolder;
    }

    public static Consumer lambdaFactory$(FollowApply followApply, ApplyHolder applyHolder) {
        return new ApplyListAdapter$$Lambda$5(followApply, applyHolder);
    }

    @Hidden
    public void accept(Object obj) {
        ApplyListAdapter.m978a(this.a, this.b, (Boolean) obj);
    }
}
