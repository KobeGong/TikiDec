package com.buddy.tiki.im;

import com.buddy.tiki.model.user.User;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class IMRtcClient$$Lambda$9 implements Transaction {
    private final User f827a;

    private IMRtcClient$$Lambda$9(User user) {
        this.a = user;
    }

    public static Transaction lambdaFactory$(User user) {
        return new IMRtcClient$$Lambda$9(user);
    }

    @Hidden
    public void execute(Realm realm) {
        IMRtcClient.m219a(this.a, realm);
    }
}
