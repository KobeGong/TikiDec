package com.buddy.tiki.ui.fragment;

import com.buddy.tiki.model.payment.SendGiftResult;
import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ChatMessageFragment$$Lambda$26 implements Predicate {
    private static final ChatMessageFragment$$Lambda$26 f2057a = new ChatMessageFragment$$Lambda$26();

    private ChatMessageFragment$$Lambda$26() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return ChatMessageFragment.m1244a((SendGiftResult) obj);
    }
}
