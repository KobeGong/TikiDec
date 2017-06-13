package com.buddy.tiki.view;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class IncomingCallView$$Lambda$7 implements Consumer {
    private static final IncomingCallView$$Lambda$7 f2678a = new IncomingCallView$$Lambda$7();

    private IncomingCallView$$Lambda$7() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        IncomingCallView.f2695a.m264e("inner error:" + ((Throwable) obj).getMessage(), (Throwable) obj);
    }
}
