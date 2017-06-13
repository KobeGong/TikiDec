package com.buddy.tiki.service;

import com.buddy.tiki.model.user.User;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FollowManager$$Lambda$7 implements Function {
    private final FollowManager f924a;

    private FollowManager$$Lambda$7(FollowManager followManager) {
        this.a = followManager;
    }

    public static Function lambdaFactory$(FollowManager followManager) {
        return new FollowManager$$Lambda$7(followManager);
    }

    @Hidden
    public Object apply(Object obj) {
        return this.a.insertUser((User) obj);
    }
}
