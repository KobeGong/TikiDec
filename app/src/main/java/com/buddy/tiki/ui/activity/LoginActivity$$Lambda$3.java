package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class LoginActivity$$Lambda$3 implements Consumer {
    private final LoginActivity f1418a;

    private LoginActivity$$Lambda$3(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public static Consumer lambdaFactory$(LoginActivity loginActivity) {
        return new LoginActivity$$Lambda$3(loginActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m759b(obj);
    }
}
