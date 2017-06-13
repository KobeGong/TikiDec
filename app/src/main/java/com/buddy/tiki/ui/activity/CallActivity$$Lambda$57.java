package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.payment.SendGiftResult;
import com.buddy.tiki.model.resource.Gift;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$57 implements Consumer {
    private final CallActivity f1197a;
    private final Gift f1198b;

    private CallActivity$$Lambda$57(CallActivity callActivity, Gift gift) {
        this.a = callActivity;
        this.b = gift;
    }

    public static Consumer lambdaFactory$(CallActivity callActivity, Gift gift) {
        return new CallActivity$$Lambda$57(callActivity, gift);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m605a(this.b, (SendGiftResult) obj);
    }
}
