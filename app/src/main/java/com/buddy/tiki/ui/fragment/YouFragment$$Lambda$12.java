package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class YouFragment$$Lambda$12 implements Consumer {
    private final YouFragment f2273a;

    private YouFragment$$Lambda$12(YouFragment youFragment) {
        this.a = youFragment;
    }

    public static Consumer lambdaFactory$(YouFragment youFragment) {
        return new YouFragment$$Lambda$12(youFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1496c(obj);
    }
}
