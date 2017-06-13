package com.buddy.tiki.service;

import com.buddy.tiki.model.app.OperInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class AppManager$$Lambda$3 implements Consumer {
    private final AppManager f901a;

    private AppManager$$Lambda$3(AppManager appManager) {
        this.a = appManager;
    }

    public static Consumer lambdaFactory$(AppManager appManager) {
        return new AppManager$$Lambda$3(appManager);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m282a((OperInfo) obj);
    }
}
