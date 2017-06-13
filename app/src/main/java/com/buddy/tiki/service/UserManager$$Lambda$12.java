package com.buddy.tiki.service;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class UserManager$$Lambda$12 implements Runnable {
    private final String f960a;
    private final String f961b;
    private final int f962c;

    private UserManager$$Lambda$12(String str, String str2, int i) {
        this.a = str;
        this.b = str2;
        this.c = i;
    }

    public static Runnable lambdaFactory$(String str, String str2, int i) {
        return new UserManager$$Lambda$12(str, str2, i);
    }

    @Hidden
    public void run() {
        UserManager.m327a(this.a, this.b, this.c);
    }
}
