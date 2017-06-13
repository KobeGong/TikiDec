package com.buddy.tiki.service;

import com.buddy.tiki.model.user.SyncFriends;
import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FollowManager$$Lambda$5 implements Predicate {
    private static final FollowManager$$Lambda$5 f922a = new FollowManager$$Lambda$5();

    private FollowManager$$Lambda$5() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return FollowManager.m293a((SyncFriends) obj);
    }
}
