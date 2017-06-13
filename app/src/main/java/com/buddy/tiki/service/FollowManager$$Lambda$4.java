package com.buddy.tiki.service;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FollowManager$$Lambda$4 implements Consumer {
    private final String f921a;

    private FollowManager$$Lambda$4(String str) {
        this.a = str;
    }

    public static Consumer lambdaFactory$(String str) {
        return new FollowManager$$Lambda$4(str);
    }

    @Hidden
    public void accept(Object obj) {
        FollowManager.m290a(this.a, (Boolean) obj);
    }
}
