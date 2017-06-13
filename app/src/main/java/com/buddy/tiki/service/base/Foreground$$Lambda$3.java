package com.buddy.tiki.service.base;

import com.buddy.tiki.model.user.User;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class Foreground$$Lambda$3 implements Consumer {
    private static final Foreground$$Lambda$3 f1037a = new Foreground$$Lambda$3();

    private Foreground$$Lambda$3() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        Foreground.m395a((User) obj);
    }
}
