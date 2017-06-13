package com.buddy.tiki.service;

import com.buddy.tiki.model.user.User;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$$Lambda$13 implements Runnable {
    private final User f963a;

    private UserManager$$Lambda$13(User user) {
        this.a = user;
    }

    public static Runnable lambdaFactory$(User user) {
        return new UserManager$$Lambda$13(user);
    }

    @Hidden
    public void run() {
        UserManager.m321a(this.a);
    }
}
