package com.buddy.tiki.service;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$$Lambda$2 implements Consumer {
    private final UserManager f978a;
    private final String f979b;
    private final int f980c;

    private UserManager$$Lambda$2(UserManager userManager, String str, int i) {
        this.a = userManager;
        this.b = str;
        this.c = i;
    }

    public static Consumer lambdaFactory$(UserManager userManager, String str, int i) {
        return new UserManager$$Lambda$2(userManager, str, i);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m340a(this.b, this.c, (Boolean) obj);
    }
}
