package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallFriendActivity$$Lambda$3 implements Consumer {
    private final CallFriendActivity f1334a;

    private CallFriendActivity$$Lambda$3(CallFriendActivity callFriendActivity) {
        this.a = callFriendActivity;
    }

    public static Consumer lambdaFactory$(CallFriendActivity callFriendActivity) {
        return new CallFriendActivity$$Lambda$3(callFriendActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m697a((String) obj);
    }
}
