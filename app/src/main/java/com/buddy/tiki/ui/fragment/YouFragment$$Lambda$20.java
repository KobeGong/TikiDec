package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.model.app.OperInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class YouFragment$$Lambda$20 implements Consumer {
    private final YouFragment f2282a;

    private YouFragment$$Lambda$20(YouFragment youFragment) {
        this.a = youFragment;
    }

    public static Consumer lambdaFactory$(YouFragment youFragment) {
        return new YouFragment$$Lambda$20(youFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1488a((OperInfo) obj);
    }
}
