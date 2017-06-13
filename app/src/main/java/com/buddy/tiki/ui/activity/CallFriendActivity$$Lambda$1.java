package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallFriendActivity$$Lambda$1 implements Consumer {
    private final CallFriendActivity f1311a;

    private CallFriendActivity$$Lambda$1(CallFriendActivity callFriendActivity) {
        this.a = callFriendActivity;
    }

    public static Consumer lambdaFactory$(CallFriendActivity callFriendActivity) {
        return new CallFriendActivity$$Lambda$1(callFriendActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m706d((Boolean) obj);
    }
}
