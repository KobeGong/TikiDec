package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.resource.Gift;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$50 implements Runnable {
    private final CallActivity f1187a;
    private final Gift f1188b;

    private CallActivity$$Lambda$50(CallActivity callActivity, Gift gift) {
        this.a = callActivity;
        this.b = gift;
    }

    public static Runnable lambdaFactory$(CallActivity callActivity, Gift gift) {
        return new CallActivity$$Lambda$50(callActivity, gift);
    }

    @Hidden
    public void run() {
        this.a.m604a(this.b);
    }
}
