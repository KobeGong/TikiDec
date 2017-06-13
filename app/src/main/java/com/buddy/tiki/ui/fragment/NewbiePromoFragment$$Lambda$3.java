package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class NewbiePromoFragment$$Lambda$3 implements Consumer {
    private final NewbiePromoFragment f2169a;

    private NewbiePromoFragment$$Lambda$3(NewbiePromoFragment newbiePromoFragment) {
        this.a = newbiePromoFragment;
    }

    public static Consumer lambdaFactory$(NewbiePromoFragment newbiePromoFragment) {
        return new NewbiePromoFragment$$Lambda$3(newbiePromoFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1350a(obj);
    }
}
