package com.buddy.tiki.view;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class IncomingCallView$$Lambda$2 implements Consumer {
    private static final IncomingCallView$$Lambda$2 f2673a = new IncomingCallView$$Lambda$2();

    private IncomingCallView$$Lambda$2() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        IncomingCallView.f2695a.m263e("out error:" + ((Throwable) obj).getMessage());
    }
}
