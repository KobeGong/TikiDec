package com.buddy.tiki.ui.activity;

import android.graphics.Bitmap;
import com.buddy.tiki.model.user.User;
import im.facechat.view.FCSurfaceView.CaptureCallBack;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$52 implements CaptureCallBack {
    private final CallActivity f1191a;
    private final User f1192b;

    private CallActivity$$Lambda$52(CallActivity callActivity, User user) {
        this.a = callActivity;
        this.b = user;
    }

    public static CaptureCallBack lambdaFactory$(CallActivity callActivity, User user) {
        return new CallActivity$$Lambda$52(callActivity, user);
    }

    @Hidden
    public void captureBmp(Bitmap bitmap) {
        this.a.m607a(this.b, bitmap);
    }
}
