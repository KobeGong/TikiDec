package com.buddy.tiki.service;

import com.buddy.tiki.model.user.User;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$$Lambda$14 implements Transaction {
    private final User f964a;

    private UserManager$$Lambda$14(User user) {
        this.a = user;
    }

    public static Transaction lambdaFactory$(User user) {
        return new UserManager$$Lambda$14(user);
    }

    @Hidden
    public void execute(Realm realm) {
        UserManager.m322a(this.a, realm);
    }
}
