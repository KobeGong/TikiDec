package com.buddy.tiki.view;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class IncomingCallView$$Lambda$1 implements Consumer {
    private final IncomingCallView f2672a;

    private IncomingCallView$$Lambda$1(IncomingCallView incomingCallView) {
        this.a = incomingCallView;
    }

    public static Consumer lambdaFactory$(IncomingCallView incomingCallView) {
        return new IncomingCallView$$Lambda$1(incomingCallView);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1718b(obj);
    }
}
