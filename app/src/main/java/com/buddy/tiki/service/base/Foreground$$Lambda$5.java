package com.buddy.tiki.service.base;

import com.buddy.tiki.model.app.ConfigInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class Foreground$$Lambda$5 implements Consumer {
    private static final Foreground$$Lambda$5 f1039a = new Foreground$$Lambda$5();

    private Foreground$$Lambda$5() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        Foreground.m393a((ConfigInfo) obj);
    }
}
