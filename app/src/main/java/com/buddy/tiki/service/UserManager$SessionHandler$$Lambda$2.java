package com.buddy.tiki.service;

import com.buddy.tiki.model.user.SessionInfo;
import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$SessionHandler$$Lambda$2 implements Function {
    private static final UserManager$SessionHandler$$Lambda$2 f1003a = new UserManager$SessionHandler$$Lambda$2();

    private UserManager$SessionHandler$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return a;
    }

    @Hidden
    public Object apply(Object obj) {
        return SessionHandler.m319b((SessionInfo) obj);
    }
}
