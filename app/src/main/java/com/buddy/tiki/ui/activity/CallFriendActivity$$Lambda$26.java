package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.resource.Gift;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallFriendActivity$$Lambda$26 implements Runnable {
    private final CallFriendActivity f1317a;
    private final Gift f1318b;

    private CallFriendActivity$$Lambda$26(CallFriendActivity callFriendActivity, Gift gift) {
        this.a = callFriendActivity;
        this.b = gift;
    }

    public static Runnable lambdaFactory$(CallFriendActivity callFriendActivity, Gift gift) {
        return new CallFriendActivity$$Lambda$26(callFriendActivity, gift);
    }

    @Hidden
    public void run() {
        this.a.m695a(this.b);
    }
}
