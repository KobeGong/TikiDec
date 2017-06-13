package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PromotionFragment$$Lambda$5 implements Consumer {
    private final PromotionFragment f2194a;

    private PromotionFragment$$Lambda$5(PromotionFragment promotionFragment) {
        this.a = promotionFragment;
    }

    public static Consumer lambdaFactory$(PromotionFragment promotionFragment) {
        return new PromotionFragment$$Lambda$5(promotionFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1383a(obj);
    }
}
