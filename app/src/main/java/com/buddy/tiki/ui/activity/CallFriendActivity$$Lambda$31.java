package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.payment.SendGiftResult;
import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallFriendActivity$$Lambda$31 implements Predicate {
    private static final CallFriendActivity$$Lambda$31 f1324a = new CallFriendActivity$$Lambda$31();

    private CallFriendActivity$$Lambda$31() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return CallFriendActivity.m646a((SendGiftResult) obj);
    }
}
