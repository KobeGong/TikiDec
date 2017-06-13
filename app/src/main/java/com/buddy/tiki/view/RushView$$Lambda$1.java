package com.buddy.tiki.view;

import com.buddy.tiki.service.view.RushNotificationManager;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class RushView$$Lambda$1 implements Consumer {
    private static final RushView$$Lambda$1 f2798a = new RushView$$Lambda$1();

    private RushView$$Lambda$1() {
    }

    public static Consumer lambdaFactory$() {
        return a;
    }

    @Hidden
    public void accept(Object obj) {
        RushNotificationManager.getInstance().doAction();
    }
}
