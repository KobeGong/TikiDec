package com.buddy.tiki.view;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class RushView$$Lambda$5 implements Consumer {
    private final RushView f2802a;

    private RushView$$Lambda$5(RushView rushView) {
        this.a = rushView;
    }

    public static Consumer lambdaFactory$(RushView rushView) {
        return new RushView$$Lambda$5(rushView);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1762b((Long) obj);
    }
}
