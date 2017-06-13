package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class LoginActivity$$Lambda$2 implements Consumer {
    private final LoginActivity f1417a;

    private LoginActivity$$Lambda$2(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public static Consumer lambdaFactory$(LoginActivity loginActivity) {
        return new LoginActivity$$Lambda$2(loginActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m761c(obj);
    }
}
