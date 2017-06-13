package com.buddy.tiki.service;

import com.buddy.tiki.model.user.User;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$$Lambda$20 implements Transaction {
    private final User f975a;

    private UserManager$$Lambda$20(User user) {
        this.a = user;
    }

    public static Transaction lambdaFactory$(User user) {
        return new UserManager$$Lambda$20(user);
    }

    @Hidden
    public void execute(Realm realm) {
        UserManager.m330b(this.a, realm);
    }
}
