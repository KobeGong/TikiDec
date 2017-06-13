package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.payment.SendGiftResult;
import com.buddy.tiki.model.resource.Gift;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallFriendActivity$$Lambda$32 implements Consumer {
    private final CallFriendActivity f1325a;
    private final Gift f1326b;

    private CallFriendActivity$$Lambda$32(CallFriendActivity callFriendActivity, Gift gift) {
        this.a = callFriendActivity;
        this.b = gift;
    }

    public static Consumer lambdaFactory$(CallFriendActivity callFriendActivity, Gift gift) {
        return new CallFriendActivity$$Lambda$32(callFriendActivity, gift);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m696a(this.b, (SendGiftResult) obj);
    }
}
