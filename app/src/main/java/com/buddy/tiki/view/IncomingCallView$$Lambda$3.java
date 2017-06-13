package com.buddy.tiki.view;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class IncomingCallView$$Lambda$3 implements Consumer {
    private final IncomingCallView f2674a;

    private IncomingCallView$$Lambda$3(IncomingCallView incomingCallView) {
        this.a = incomingCallView;
    }

    public static Consumer lambdaFactory$(IncomingCallView incomingCallView) {
        return new IncomingCallView$$Lambda$3(incomingCallView);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1717a(obj);
    }
}
