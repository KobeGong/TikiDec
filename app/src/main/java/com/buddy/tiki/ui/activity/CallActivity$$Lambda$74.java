package com.buddy.tiki.ui.activity;

import com.buddy.tiki.ChatApp;
import com.igexin.sdk.PushManager;
import im.facechat.Rtc;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$74 implements Consumer {
    private static final CallActivity$$Lambda$74 f1219a = new CallActivity$$Lambda$74();

    private CallActivity$$Lambda$74() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        Rtc.setApnsToken(ChatApp.getInstance(), 3, PushManager.getInstance().getClientid(ChatApp.getInstance()), null);
    }
}
