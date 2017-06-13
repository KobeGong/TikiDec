package com.buddy.tiki.service;

import com.buddy.tiki.model.user.SessionInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$SessionHandler$$Lambda$1 implements Consumer {
    private final SessionHandler f1002a;

    private UserManager$SessionHandler$$Lambda$1(SessionHandler sessionHandler) {
        this.a = sessionHandler;
    }

    public static Consumer lambdaFactory$(SessionHandler sessionHandler) {
        return new UserManager$SessionHandler$$Lambda$1(sessionHandler);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m320c((SessionInfo) obj);
    }
}
