package com.buddy.tiki.helper;

import com.buddy.tiki.service.view.RushNotificationManager;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CustomMessageHelper$$Lambda$3 implements Runnable {
    private final String f533a;

    private CustomMessageHelper$$Lambda$3(String str) {
        this.a = str;
    }

    public static Runnable lambdaFactory$(String str) {
        return new CustomMessageHelper$$Lambda$3(str);
    }

    @Hidden
    public void run() {
        RushNotificationManager.getInstance().showRushNotification(null, this.a);
    }
}
