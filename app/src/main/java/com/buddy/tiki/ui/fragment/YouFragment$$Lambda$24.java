package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class YouFragment$$Lambda$24 implements Consumer {
    private final YouFragment f2287a;
    private final Boolean f2288b;

    private YouFragment$$Lambda$24(YouFragment youFragment, Boolean bool) {
        this.a = youFragment;
        this.b = bool;
    }

    public static Consumer lambdaFactory$(YouFragment youFragment, Boolean bool) {
        return new YouFragment$$Lambda$24(youFragment, bool);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1492a(this.b, (Throwable) obj);
    }
}
