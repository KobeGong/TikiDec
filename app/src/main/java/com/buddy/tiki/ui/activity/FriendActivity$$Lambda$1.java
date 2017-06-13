package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.user.SyncFriends;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FriendActivity$$Lambda$1 implements Consumer {
    private static final FriendActivity$$Lambda$1 f1405a = new FriendActivity$$Lambda$1();

    private FriendActivity$$Lambda$1() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        FriendActivity.m718a((SyncFriends) obj);
    }
}
