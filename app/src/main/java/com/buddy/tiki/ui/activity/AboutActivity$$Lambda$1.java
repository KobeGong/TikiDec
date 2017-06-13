package com.buddy.tiki.ui.activity;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class AboutActivity$$Lambda$1 implements Consumer {
    private final AboutActivity f1111a;

    private AboutActivity$$Lambda$1(AboutActivity aboutActivity) {
        this.a = aboutActivity;
    }

    public static Consumer lambdaFactory$(AboutActivity aboutActivity) {
        return new AboutActivity$$Lambda$1(aboutActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m437a(obj);
    }
}
