package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.app.ConfigInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SplashActivity$$Lambda$3 implements Consumer {
    private final SplashActivity f1462a;
    private final ConfigInfo f1463b;

    private SplashActivity$$Lambda$3(SplashActivity splashActivity, ConfigInfo configInfo) {
        this.a = splashActivity;
        this.b = configInfo;
    }

    public static Consumer lambdaFactory$(SplashActivity splashActivity, ConfigInfo configInfo) {
        return new SplashActivity$$Lambda$3(splashActivity, configInfo);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m812a(this.b, (Long) obj);
    }
}
