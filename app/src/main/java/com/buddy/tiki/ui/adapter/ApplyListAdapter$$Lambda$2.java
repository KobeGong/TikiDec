package com.buddy.tiki.ui.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import com.buddy.tiki.model.user.FollowApply;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ApplyListAdapter$$Lambda$2 implements OnClickListener {
    private final ApplyListAdapter f1647a;
    private final FollowApply f1648b;
    private final ApplyHolder f1649c;

    private ApplyListAdapter$$Lambda$2(ApplyListAdapter applyListAdapter, FollowApply followApply, ApplyHolder applyHolder) {
        this.a = applyListAdapter;
        this.b = followApply;
        this.c = applyHolder;
    }

    public static OnClickListener lambdaFactory$(ApplyListAdapter applyListAdapter, FollowApply followApply, ApplyHolder applyHolder) {
        return new ApplyListAdapter$$Lambda$2(applyListAdapter, followApply, applyHolder);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m983a(this.b, this.c, view);
    }
}
