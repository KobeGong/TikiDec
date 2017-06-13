package com.buddy.tiki.ui.activity;

import android.graphics.Bitmap;
import com.buddy.tiki.ui.activity.CallActivity.C04258;
import im.facechat.view.FCSurfaceView.CaptureCallBack;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$8$$Lambda$1 implements CaptureCallBack {
    private final C04258 f1236a;
    private final String f1237b;

    private CallActivity$8$$Lambda$1(C04258 c04258, String str) {
        this.a = c04258;
        this.b = str;
    }

    public static CaptureCallBack lambdaFactory$(C04258 c04258, String str) {
        return new CallActivity$8$$Lambda$1(c04258, str);
    }

    @Hidden
    public void captureBmp(Bitmap bitmap) {
        this.a.m474a(this.b, bitmap);
    }
}
