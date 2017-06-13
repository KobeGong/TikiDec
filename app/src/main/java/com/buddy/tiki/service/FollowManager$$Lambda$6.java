package com.buddy.tiki.service;

import com.buddy.tiki.model.user.SyncFriends;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FollowManager$$Lambda$6 implements Consumer {
    private final FollowManager f923a;

    private FollowManager$$Lambda$6(FollowManager followManager) {
        this.a = followManager;
    }

    public static Consumer lambdaFactory$(FollowManager followManager) {
        return new FollowManager$$Lambda$6(followManager);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m294b((SyncFriends) obj);
    }
}
