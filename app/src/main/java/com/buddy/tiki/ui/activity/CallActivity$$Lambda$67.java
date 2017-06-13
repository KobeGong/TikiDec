package com.buddy.tiki.ui.activity;

import android.graphics.Bitmap;
import com.buddy.tiki.model.user.User;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CallActivity$$Lambda$67 implements Runnable {
    private final CallActivity f1209a;
    private final User f1210b;
    private final Bitmap f1211c;

    private CallActivity$$Lambda$67(CallActivity callActivity, User user, Bitmap bitmap) {
        this.a = callActivity;
        this.b = user;
        this.c = bitmap;
    }

    public static Runnable lambdaFactory$(CallActivity callActivity, User user, Bitmap bitmap) {
        return new CallActivity$$Lambda$67(callActivity, user, bitmap);
    }

    @Hidden
    public void run() {
        this.a.m617b(this.b, this.c);
    }
}
