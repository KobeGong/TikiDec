package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.payment.SendGiftResult;
import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$56 implements Predicate {
    private static final CallActivity$$Lambda$56 f1196a = new CallActivity$$Lambda$56();

    private CallActivity$$Lambda$56() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return CallActivity.m519a((SendGiftResult) obj);
    }
}
