package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class LoginActivity$$Lambda$1 implements Consumer {
    private final LoginActivity f1416a;

    private LoginActivity$$Lambda$1(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public static Consumer lambdaFactory$(LoginActivity loginActivity) {
        return new LoginActivity$$Lambda$1(loginActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m762d(obj);
    }
}
