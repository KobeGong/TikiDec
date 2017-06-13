package com.buddy.tiki.service;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$$Lambda$3 implements Consumer {
    private final UserManager f981a;
    private final String f982b;
    private final String f983c;
    private final int f984d;

    private UserManager$$Lambda$3(UserManager userManager, String str, String str2, int i) {
        this.a = userManager;
        this.b = str;
        this.c = str2;
        this.d = i;
    }

    public static Consumer lambdaFactory$(UserManager userManager, String str, String str2, int i) {
        return new UserManager$$Lambda$3(userManager, str, str2, i);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m347c(this.b, this.c, this.d, (Boolean) obj);
    }
}
