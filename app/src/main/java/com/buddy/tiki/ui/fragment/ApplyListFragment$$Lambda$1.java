package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.view.superrecyclerview.OnMoreListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ApplyListFragment$$Lambda$1 implements OnMoreListener {
    private final ApplyListFragment f2001a;

    private ApplyListFragment$$Lambda$1(ApplyListFragment applyListFragment) {
        this.a = applyListFragment;
    }

    public static OnMoreListener lambdaFactory$(ApplyListFragment applyListFragment) {
        return new ApplyListFragment$$Lambda$1(applyListFragment);
    }

    @Hidden
    public void onMoreAsked(int i, int i2, int i3) {
        this.a.m1210a(i, i2, i3);
    }
}
