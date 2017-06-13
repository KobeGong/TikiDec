package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class YouFragment$$Lambda$13 implements Consumer {
    private final YouFragment f2274a;

    private YouFragment$$Lambda$13(YouFragment youFragment) {
        this.a = youFragment;
    }

    public static Consumer lambdaFactory$(YouFragment youFragment) {
        return new YouFragment$$Lambda$13(youFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1495b(obj);
    }
}
