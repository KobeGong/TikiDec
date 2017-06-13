package com.buddy.tiki.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class YouFragment$$Lambda$1 implements OnRefreshListener {
    private final YouFragment f2281a;

    private YouFragment$$Lambda$1(YouFragment youFragment) {
        this.a = youFragment;
    }

    public static OnRefreshListener lambdaFactory$(YouFragment youFragment) {
        return new YouFragment$$Lambda$1(youFragment);
    }

    @Hidden
    public void onRefresh() {
        this.a.m1494b();
    }
}
