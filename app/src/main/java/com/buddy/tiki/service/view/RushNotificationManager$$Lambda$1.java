package com.buddy.tiki.service.view;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class RushNotificationManager$$Lambda$1 implements Consumer {
    private final RushNotificationManager f1102a;

    private RushNotificationManager$$Lambda$1(RushNotificationManager rushNotificationManager) {
        this.a = rushNotificationManager;
    }

    public static Consumer lambdaFactory$(RushNotificationManager rushNotificationManager) {
        return new RushNotificationManager$$Lambda$1(rushNotificationManager);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m424a((Long) obj);
    }
}
