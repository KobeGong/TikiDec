package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.model.promo.PromoResult;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UpdateProfileFragment$$Lambda$16 implements Consumer {
    private static final UpdateProfileFragment$$Lambda$16 f2233a = new UpdateProfileFragment$$Lambda$16();

    private UpdateProfileFragment$$Lambda$16() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        UpdateProfileFragment.m1435b((PromoResult) obj);
    }
}
