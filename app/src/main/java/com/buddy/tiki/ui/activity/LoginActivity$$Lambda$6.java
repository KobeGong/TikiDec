package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class LoginActivity$$Lambda$6 implements Consumer {
    private final LoginActivity f1421a;

    private LoginActivity$$Lambda$6(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public static Consumer lambdaFactory$(LoginActivity loginActivity) {
        return new LoginActivity$$Lambda$6(loginActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m754a((Boolean) obj);
    }
}
