package com.buddy.tiki.service;

import com.buddy.tiki.model.user.User;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$$Lambda$11 implements Runnable {
    private final User f959a;

    private UserManager$$Lambda$11(User user) {
        this.a = user;
    }

    public static Runnable lambdaFactory$(User user) {
        return new UserManager$$Lambda$11(user);
    }

    @Hidden
    public void run() {
        UserManager.m329b(this.a);
    }
}
