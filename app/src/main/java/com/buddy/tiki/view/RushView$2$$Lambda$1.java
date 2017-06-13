package com.buddy.tiki.view;

import com.buddy.tiki.view.RushView.C04972;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class RushView$2$$Lambda$1 implements Consumer {
    private final C04972 f2806a;

    private RushView$2$$Lambda$1(C04972 c04972) {
        this.a = c04972;
    }

    public static Consumer lambdaFactory$(C04972 c04972) {
        return new RushView$2$$Lambda$1(c04972);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1750a((Long) obj);
    }
}
