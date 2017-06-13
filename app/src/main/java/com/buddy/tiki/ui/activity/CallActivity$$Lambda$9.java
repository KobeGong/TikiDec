package com.buddy.tiki.ui.activity;

import im.facechat.Rtc.SimpleActionCallback;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$9 implements SimpleActionCallback {
    private final CallActivity f1223a;

    private CallActivity$$Lambda$9(CallActivity callActivity) {
        this.a = callActivity;
    }

    public static SimpleActionCallback lambdaFactory$(CallActivity callActivity) {
        return new CallActivity$$Lambda$9(callActivity);
    }

    @Hidden
    public void onResult(boolean z, String str) {
        this.a.m609a(z, str);
    }
}
