package com.buddy.tiki.ui.activity;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallFriendActivity$$Lambda$29 implements Runnable {
    private final CallFriendActivity f1321a;

    private CallFriendActivity$$Lambda$29(CallFriendActivity callFriendActivity) {
        this.a = callFriendActivity;
    }

    public static Runnable lambdaFactory$(CallFriendActivity callFriendActivity) {
        return new CallFriendActivity$$Lambda$29(callFriendActivity);
    }

    @Hidden
    public void run() {
        this.a.m703c();
    }
}
