package com.buddy.tiki.service;

import com.buddy.tiki.model.user.User;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FollowManager$$Lambda$2 implements Consumer {
    private final FollowManager f917a;
    private final User f918b;

    private FollowManager$$Lambda$2(FollowManager followManager, User user) {
        this.a = followManager;
        this.b = user;
    }

    public static Consumer lambdaFactory$(FollowManager followManager, User user) {
        return new FollowManager$$Lambda$2(followManager, user);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m295a(this.b, (Boolean) obj);
    }
}
