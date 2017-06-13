package com.buddy.tiki.ui.activity;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallFriendActivity$$Lambda$35 implements Runnable {
    private final CallFriendActivity f1329a;

    private CallFriendActivity$$Lambda$35(CallFriendActivity callFriendActivity) {
        this.a = callFriendActivity;
    }

    public static Runnable lambdaFactory$(CallFriendActivity callFriendActivity) {
        return new CallFriendActivity$$Lambda$35(callFriendActivity);
    }

    @Hidden
    public void run() {
        this.a.m684o();
    }
}
