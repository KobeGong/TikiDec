package com.buddy.tiki.ui.adapter;

import com.buddy.tiki.model.user.FollowApply;
import com.buddy.tiki.service.base.DataLayer;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ApplyListAdapter$$Lambda$4 implements Function {
    private final FollowApply f1651a;

    private ApplyListAdapter$$Lambda$4(FollowApply followApply) {
        this.a = followApply;
    }

    public static Function lambdaFactory$(FollowApply followApply) {
        return new ApplyListAdapter$$Lambda$4(followApply);
    }

    @Hidden
    public Object apply(Object obj) {
        return DataLayer.getInstance().getFollowManager().insertUser(this.a.getUser());
    }
}
