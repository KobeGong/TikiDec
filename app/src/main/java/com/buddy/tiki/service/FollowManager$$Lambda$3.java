package com.buddy.tiki.service;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FollowManager$$Lambda$3 implements Consumer {
    private final String[] f919a;
    private final String[] f920b;

    private FollowManager$$Lambda$3(String[] strArr, String[] strArr2) {
        this.a = strArr;
        this.b = strArr2;
    }

    public static Consumer lambdaFactory$(String[] strArr, String[] strArr2) {
        return new FollowManager$$Lambda$3(strArr, strArr2);
    }

    @Hidden
    public void accept(Object obj) {
        FollowManager.m292a(this.a, this.b, (Boolean) obj);
    }
}
