package com.buddy.tiki.service;

import com.buddy.tiki.model.app.ConfigInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class AppManager$$Lambda$1 implements Consumer {
    private final AppManager f899a;

    private AppManager$$Lambda$1(AppManager appManager) {
        this.a = appManager;
    }

    public static Consumer lambdaFactory$(AppManager appManager) {
        return new AppManager$$Lambda$1(appManager);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m281a((ConfigInfo) obj);
    }
}
