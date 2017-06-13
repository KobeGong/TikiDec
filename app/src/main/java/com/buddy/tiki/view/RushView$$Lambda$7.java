package com.buddy.tiki.view;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class RushView$$Lambda$7 implements Consumer {
    private final RushView f2804a;

    private RushView$$Lambda$7(RushView rushView) {
        this.a = rushView;
    }

    public static Consumer lambdaFactory$(RushView rushView) {
        return new RushView$$Lambda$7(rushView);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1764c((Long) obj);
    }
}
