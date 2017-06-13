package com.buddy.tiki.view;

import com.buddy.tiki.model.im.CallReceiveMessage;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class IncomingCallView1$$Lambda$5 implements Consumer {
    private final IncomingCallView1 f2683a;

    private IncomingCallView1$$Lambda$5(IncomingCallView1 incomingCallView1) {
        this.a = incomingCallView1;
    }

    public static Consumer lambdaFactory$(IncomingCallView1 incomingCallView1) {
        return new IncomingCallView1$$Lambda$5(incomingCallView1);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1707a((CallReceiveMessage) obj);
    }
}
