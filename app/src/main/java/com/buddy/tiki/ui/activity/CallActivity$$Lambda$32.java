package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.msg.MatchMessage;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$32 implements Runnable {
    private final CallActivity f1161a;
    private final MatchMessage f1162b;

    private CallActivity$$Lambda$32(CallActivity callActivity, MatchMessage matchMessage) {
        this.a = callActivity;
        this.b = matchMessage;
    }

    public static Runnable lambdaFactory$(CallActivity callActivity, MatchMessage matchMessage) {
        return new CallActivity$$Lambda$32(callActivity, matchMessage);
    }

    @Hidden
    public void run() {
        this.a.m602a(this.b);
    }
}
