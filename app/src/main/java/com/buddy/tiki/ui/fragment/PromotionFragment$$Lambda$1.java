package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PromotionFragment$$Lambda$1 implements Consumer {
    private final PromotionFragment f2192a;

    private PromotionFragment$$Lambda$1(PromotionFragment promotionFragment) {
        this.a = promotionFragment;
    }

    public static Consumer lambdaFactory$(PromotionFragment promotionFragment) {
        return new PromotionFragment$$Lambda$1(promotionFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1384b(obj);
    }
}
