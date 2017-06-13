package com.buddy.tiki.service;

import com.buddy.tiki.model.user.SessionInfo;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$SessionHandler$$Lambda$3 implements Runnable {
    private final SessionInfo f1004a;

    private UserManager$SessionHandler$$Lambda$3(SessionInfo sessionInfo) {
        this.a = sessionInfo;
    }

    public static Runnable lambdaFactory$(SessionInfo sessionInfo) {
        return new UserManager$SessionHandler$$Lambda$3(sessionInfo);
    }

    @Hidden
    public void run() {
        SessionHandler.m314a(this.a);
    }
}
