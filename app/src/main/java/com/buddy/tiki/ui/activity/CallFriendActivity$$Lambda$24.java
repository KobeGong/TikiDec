package com.buddy.tiki.ui.activity;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallFriendActivity$$Lambda$24 implements Runnable {
    private final CallFriendActivity f1314a;

    private CallFriendActivity$$Lambda$24(CallFriendActivity callFriendActivity) {
        this.a = callFriendActivity;
    }

    public static Runnable lambdaFactory$(CallFriendActivity callFriendActivity) {
        return new CallFriendActivity$$Lambda$24(callFriendActivity);
    }

    @Hidden
    public void run() {
        this.a.m707e();
    }
}
