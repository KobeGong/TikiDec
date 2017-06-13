package com.buddy.tiki.view;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class RushView$$Lambda$6 implements Consumer {
    private final RushView f2803a;

    private RushView$$Lambda$6(RushView rushView) {
        this.a = rushView;
    }

    public static Consumer lambdaFactory$(RushView rushView) {
        return new RushView$$Lambda$6(rushView);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1761a((Long) obj);
    }
}
