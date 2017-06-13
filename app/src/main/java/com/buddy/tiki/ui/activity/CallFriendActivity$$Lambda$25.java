package com.buddy.tiki.ui.activity;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallFriendActivity$$Lambda$25 implements Runnable {
    private final CallFriendActivity f1315a;
    private final int f1316b;

    private CallFriendActivity$$Lambda$25(CallFriendActivity callFriendActivity, int i) {
        this.a = callFriendActivity;
        this.b = i;
    }

    public static Runnable lambdaFactory$(CallFriendActivity callFriendActivity, int i) {
        return new CallFriendActivity$$Lambda$25(callFriendActivity, i);
    }

    @Hidden
    public void run() {
        this.a.m691a(this.b);
    }
}
