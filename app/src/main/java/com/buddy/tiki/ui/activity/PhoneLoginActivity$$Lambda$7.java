package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PhoneLoginActivity$$Lambda$7 implements Consumer {
    private final PhoneLoginActivity f1449a;

    private PhoneLoginActivity$$Lambda$7(PhoneLoginActivity phoneLoginActivity) {
        this.a = phoneLoginActivity;
    }

    public static Consumer lambdaFactory$(PhoneLoginActivity phoneLoginActivity) {
        return new PhoneLoginActivity$$Lambda$7(phoneLoginActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m781b(obj);
    }
}
