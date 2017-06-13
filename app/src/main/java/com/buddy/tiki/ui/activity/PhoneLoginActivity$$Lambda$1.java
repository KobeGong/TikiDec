package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.app.ConfigInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class PhoneLoginActivity$$Lambda$1 implements Consumer {
    private final PhoneLoginActivity f1443a;

    private PhoneLoginActivity$$Lambda$1(PhoneLoginActivity phoneLoginActivity) {
        this.a = phoneLoginActivity;
    }

    public static Consumer lambdaFactory$(PhoneLoginActivity phoneLoginActivity) {
        return new PhoneLoginActivity$$Lambda$1(phoneLoginActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m777a((ConfigInfo) obj);
    }
}
