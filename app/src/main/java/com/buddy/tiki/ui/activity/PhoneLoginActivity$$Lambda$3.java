package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PhoneLoginActivity$$Lambda$3 implements Consumer {
    private final PhoneLoginActivity f1445a;

    private PhoneLoginActivity$$Lambda$3(PhoneLoginActivity phoneLoginActivity) {
        this.a = phoneLoginActivity;
    }

    public static Consumer lambdaFactory$(PhoneLoginActivity phoneLoginActivity) {
        return new PhoneLoginActivity$$Lambda$3(phoneLoginActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m783c(obj);
    }
}
