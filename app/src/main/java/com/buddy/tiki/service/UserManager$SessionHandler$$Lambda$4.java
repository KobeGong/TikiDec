package com.buddy.tiki.service;

import com.buddy.tiki.model.user.SessionInfo;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$SessionHandler$$Lambda$4 implements Transaction {
    private final SessionInfo f1005a;

    private UserManager$SessionHandler$$Lambda$4(SessionInfo sessionInfo) {
        this.a = sessionInfo;
    }

    public static Transaction lambdaFactory$(SessionInfo sessionInfo) {
        return new UserManager$SessionHandler$$Lambda$4(sessionInfo);
    }

    @Hidden
    public void execute(Realm realm) {
        SessionHandler.m315a(this.a, realm);
    }
}
