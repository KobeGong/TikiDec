package com.buddy.tiki.service;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$$Lambda$7 implements Consumer {
    private final UserManager f996a;
    private final String f997b;
    private final String f998c;
    private final int f999d;

    private UserManager$$Lambda$7(UserManager userManager, String str, String str2, int i) {
        this.a = userManager;
        this.b = str;
        this.c = str2;
        this.d = i;
    }

    public static Consumer lambdaFactory$(UserManager userManager, String str, String str2, int i) {
        return new UserManager$$Lambda$7(userManager, str, str2, i);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m341a(this.b, this.c, this.d, (Boolean) obj);
    }
}
