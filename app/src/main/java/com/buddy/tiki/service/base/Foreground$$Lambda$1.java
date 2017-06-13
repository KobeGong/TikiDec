package com.buddy.tiki.service.base;

import com.buddy.tiki.model.user.SyncFriends;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class Foreground$$Lambda$1 implements Consumer {
    private static final Foreground$$Lambda$1 f1035a = new Foreground$$Lambda$1();

    private Foreground$$Lambda$1() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        Foreground.m394a((SyncFriends) obj);
    }
}
