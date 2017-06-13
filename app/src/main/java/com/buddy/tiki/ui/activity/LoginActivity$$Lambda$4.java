package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class LoginActivity$$Lambda$4 implements Consumer {
    private final LoginActivity f1419a;

    private LoginActivity$$Lambda$4(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public static Consumer lambdaFactory$(LoginActivity loginActivity) {
        return new LoginActivity$$Lambda$4(loginActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m755a(obj);
    }
}
