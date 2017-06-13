package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.view.superrecyclerview.OnMoreListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SystemMessageFragment$$Lambda$5 implements OnMoreListener {
    private final SystemMessageFragment f2222a;

    private SystemMessageFragment$$Lambda$5(SystemMessageFragment systemMessageFragment) {
        this.a = systemMessageFragment;
    }

    public static OnMoreListener lambdaFactory$(SystemMessageFragment systemMessageFragment) {
        return new SystemMessageFragment$$Lambda$5(systemMessageFragment);
    }

    @Hidden
    public void onMoreAsked(int i, int i2, int i3) {
        this.a.m1417a(i, i2, i3);
    }
}
