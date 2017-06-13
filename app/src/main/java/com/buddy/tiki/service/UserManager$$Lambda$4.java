package com.buddy.tiki.service;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$$Lambda$4 implements Consumer {
    private final UserManager f985a;
    private final String f986b;
    private final String f987c;
    private final int f988d;

    private UserManager$$Lambda$4(UserManager userManager, String str, String str2, int i) {
        this.a = userManager;
        this.b = str;
        this.c = str2;
        this.d = i;
    }

    public static Consumer lambdaFactory$(UserManager userManager, String str, String str2, int i) {
        return new UserManager$$Lambda$4(userManager, str, str2, i);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m345b(this.b, this.c, this.d, (Boolean) obj);
    }
}
