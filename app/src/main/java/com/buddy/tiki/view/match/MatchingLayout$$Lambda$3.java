package com.buddy.tiki.view.match;

import com.buddy.tiki.model.user.User;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class MatchingLayout$$Lambda$3 implements Runnable {
    private final MatchingLayout f3134a;
    private final User f3135b;

    private MatchingLayout$$Lambda$3(MatchingLayout matchingLayout, User user) {
        this.a = matchingLayout;
        this.b = user;
    }

    public static Runnable lambdaFactory$(MatchingLayout matchingLayout, User user) {
        return new MatchingLayout$$Lambda$3(matchingLayout, user);
    }

    @Hidden
    public void run() {
        this.a.m1991a(this.b);
    }
}
