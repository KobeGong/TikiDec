package com.buddy.tiki.view;

import com.buddy.tiki.model.im.CallReceiveMessage;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class IncomingCallView$$Lambda$6 implements Consumer {
    private final IncomingCallView f2677a;

    private IncomingCallView$$Lambda$6(IncomingCallView incomingCallView) {
        this.a = incomingCallView;
    }

    public static Consumer lambdaFactory$(IncomingCallView incomingCallView) {
        return new IncomingCallView$$Lambda$6(incomingCallView);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1715a((CallReceiveMessage) obj);
    }
}
