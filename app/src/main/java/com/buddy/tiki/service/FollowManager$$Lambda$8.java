package com.buddy.tiki.service;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FollowManager$$Lambda$8 implements Consumer {
    private final FollowManager f925a;
    private final String f926b;
    private final String f927c;

    private FollowManager$$Lambda$8(FollowManager followManager, String str, String str2) {
        this.a = followManager;
        this.b = str;
        this.c = str2;
    }

    public static Consumer lambdaFactory$(FollowManager followManager, String str, String str2) {
        return new FollowManager$$Lambda$8(followManager, str, str2);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m296a(this.b, this.c, (Boolean) obj);
    }
}
