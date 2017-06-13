package com.buddy.tiki.ui.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import com.buddy.tiki.model.user.FollowApply;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ApplyListAdapter$$Lambda$1 implements OnClickListener {
    private final ApplyListAdapter f1644a;
    private final FollowApply f1645b;
    private final ApplyHolder f1646c;

    private ApplyListAdapter$$Lambda$1(ApplyListAdapter applyListAdapter, FollowApply followApply, ApplyHolder applyHolder) {
        this.a = applyListAdapter;
        this.b = followApply;
        this.c = applyHolder;
    }

    public static OnClickListener lambdaFactory$(ApplyListAdapter applyListAdapter, FollowApply followApply, ApplyHolder applyHolder) {
        return new ApplyListAdapter$$Lambda$1(applyListAdapter, followApply, applyHolder);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m985b(this.b, this.c, view);
    }
}
