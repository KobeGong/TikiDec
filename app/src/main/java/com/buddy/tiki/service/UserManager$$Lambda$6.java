package com.buddy.tiki.service;

import io.reactivex.functions.Action;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$$Lambda$6 implements Action {
    private final UserManager f992a;
    private final String f993b;
    private final String f994c;
    private final int f995d;

    private UserManager$$Lambda$6(UserManager userManager, String str, String str2, int i) {
        this.a = userManager;
        this.b = str;
        this.c = str2;
        this.d = i;
    }

    public static Action lambdaFactory$(UserManager userManager, String str, String str2, int i) {
        return new UserManager$$Lambda$6(userManager, str, str2, i);
    }

    @Hidden
    public void run() {
        this.a.m344b(this.b, this.c, this.d);
    }
}
