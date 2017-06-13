package com.buddy.tiki.helper;

import com.buddy.tiki.service.view.RushNotificationManager;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CustomMessageHelper$$Lambda$1 implements Runnable {
    private static final CustomMessageHelper$$Lambda$1 f529a = new CustomMessageHelper$$Lambda$1();

    private CustomMessageHelper$$Lambda$1() {
    }

    public static Runnable lambdaFactory$() {
        return a;
    }

    @Hidden
    public void run() {
        RushNotificationManager.getInstance().grabOrderSuccess();
    }
}
