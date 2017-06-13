package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class YouFragment$$Lambda$22 implements Consumer {
    private final YouFragment f2284a;
    private final Boolean f2285b;

    private YouFragment$$Lambda$22(YouFragment youFragment, Boolean bool) {
        this.a = youFragment;
        this.b = bool;
    }

    public static Consumer lambdaFactory$(YouFragment youFragment, Boolean bool) {
        return new YouFragment$$Lambda$22(youFragment, bool);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1491a(this.b, (Boolean) obj);
    }
}
