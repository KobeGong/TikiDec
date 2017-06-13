package com.buddy.tiki.service;

import com.buddy.tiki.model.user.User;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FollowManager$$Lambda$1 implements Consumer {
    private final FollowManager f915a;
    private final User f916b;

    private FollowManager$$Lambda$1(FollowManager followManager, User user) {
        this.a = followManager;
        this.b = user;
    }

    public static Consumer lambdaFactory$(FollowManager followManager, User user) {
        return new FollowManager$$Lambda$1(followManager, user);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m298b(this.b, (Boolean) obj);
    }
}
