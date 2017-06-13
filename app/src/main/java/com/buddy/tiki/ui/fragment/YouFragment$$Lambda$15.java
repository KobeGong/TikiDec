package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class YouFragment$$Lambda$15 implements Consumer {
    private final YouFragment f2276a;

    private YouFragment$$Lambda$15(YouFragment youFragment) {
        this.a = youFragment;
    }

    public static Consumer lambdaFactory$(YouFragment youFragment) {
        return new YouFragment$$Lambda$15(youFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1490a((Boolean) obj);
    }
}
