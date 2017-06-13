package com.buddy.tiki.ui.activity;

import com.buddy.tiki.model.msg.RoomMessage;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$36 implements Consumer {
    private final CallActivity f1170a;

    private CallActivity$$Lambda$36(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static Consumer lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$36(callActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m603a((RoomMessage) obj);
    }
}
