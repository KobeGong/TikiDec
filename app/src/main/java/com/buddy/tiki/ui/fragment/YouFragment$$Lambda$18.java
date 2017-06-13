package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.model.app.ConfigInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class YouFragment$$Lambda$18 implements Consumer {
    private final YouFragment f2279a;

    private YouFragment$$Lambda$18(YouFragment youFragment) {
        this.a = youFragment;
    }

    public static Consumer lambdaFactory$(YouFragment youFragment) {
        return new YouFragment$$Lambda$18(youFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1487a((ConfigInfo) obj);
    }
}
