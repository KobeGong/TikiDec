package com.buddy.tiki.ui.activity;

import im.facechat.Rtc.SimpleActionCallback;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallFriendActivity$$Lambda$27 implements SimpleActionCallback {
    private final CallFriendActivity f1319a;

    private CallFriendActivity$$Lambda$27(CallFriendActivity callFriendActivity) {
        this.a = callFriendActivity;
    }

    public static SimpleActionCallback lambdaFactory$(CallFriendActivity callFriendActivity) {
        return new CallFriendActivity$$Lambda$27(callFriendActivity);
    }

    @Hidden
    public void onResult(boolean z, String str) {
        this.a.m699a(z, str);
    }
}
