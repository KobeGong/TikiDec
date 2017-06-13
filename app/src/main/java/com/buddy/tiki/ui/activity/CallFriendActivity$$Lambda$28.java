package com.buddy.tiki.ui.activity;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallFriendActivity$$Lambda$28 implements Runnable {
    private final CallFriendActivity f1320a;

    private CallFriendActivity$$Lambda$28(CallFriendActivity callFriendActivity) {
        this.a = callFriendActivity;
    }

    public static Runnable lambdaFactory$(CallFriendActivity callFriendActivity) {
        return new CallFriendActivity$$Lambda$28(callFriendActivity);
    }

    @Hidden
    public void run() {
        this.a.m705d();
    }
}
