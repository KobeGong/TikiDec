package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PhoneLoginActivity$$Lambda$8 implements Consumer {
    private final PhoneLoginActivity f1450a;

    private PhoneLoginActivity$$Lambda$8(PhoneLoginActivity phoneLoginActivity) {
        this.a = phoneLoginActivity;
    }

    public static Consumer lambdaFactory$(PhoneLoginActivity phoneLoginActivity) {
        return new PhoneLoginActivity$$Lambda$8(phoneLoginActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m778a(obj);
    }
}
