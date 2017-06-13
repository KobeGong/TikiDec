package com.buddy.tiki.view;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class RushView$$Lambda$2 implements Consumer {
    private final RushView f2799a;

    private RushView$$Lambda$2(RushView rushView) {
        this.a = rushView;
    }

    public static Consumer lambdaFactory$(RushView rushView) {
        return new RushView$$Lambda$2(rushView);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1765c(obj);
    }
}
