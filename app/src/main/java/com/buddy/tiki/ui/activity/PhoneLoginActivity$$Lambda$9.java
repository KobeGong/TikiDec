package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PhoneLoginActivity$$Lambda$9 implements Consumer {
    private final PhoneLoginActivity f1451a;
    private final String f1452b;
    private final String f1453c;

    private PhoneLoginActivity$$Lambda$9(PhoneLoginActivity phoneLoginActivity, String str, String str2) {
        this.a = phoneLoginActivity;
        this.b = str;
        this.c = str2;
    }

    public static Consumer lambdaFactory$(PhoneLoginActivity phoneLoginActivity, String str, String str2) {
        return new PhoneLoginActivity$$Lambda$9(phoneLoginActivity, str, str2);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m779a(this.b, this.c, (Boolean) obj);
    }
}
