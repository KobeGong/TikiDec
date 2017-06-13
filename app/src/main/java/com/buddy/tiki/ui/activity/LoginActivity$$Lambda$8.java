package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.app.ConfigInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class LoginActivity$$Lambda$8 implements Consumer {
    private final LoginActivity f1423a;

    private LoginActivity$$Lambda$8(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public static Consumer lambdaFactory$(LoginActivity loginActivity) {
        return new LoginActivity$$Lambda$8(loginActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m758b((ConfigInfo) obj);
    }
}
