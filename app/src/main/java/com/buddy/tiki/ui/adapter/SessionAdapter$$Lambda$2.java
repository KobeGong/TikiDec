package com.buddy.tiki.ui.adapter;

import com.buddy.tiki.model.user.User;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SessionAdapter$$Lambda$2 implements Consumer {
    private static final SessionAdapter$$Lambda$2 f1828a = new SessionAdapter$$Lambda$2();

    private SessionAdapter$$Lambda$2() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        SessionAdapter.m1049a((User) obj);
    }
}
