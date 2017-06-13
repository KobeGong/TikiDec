package com.buddy.tiki.helper;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class CustomMessageHelper$$Lambda$4 implements Runnable {
    private final CustomMessageHelper f534a;
    private final String f535b;
    private final String f536c;

    private CustomMessageHelper$$Lambda$4(CustomMessageHelper customMessageHelper, String str, String str2) {
        this.a = customMessageHelper;
        this.b = str;
        this.c = str2;
    }

    public static Runnable lambdaFactory$(CustomMessageHelper customMessageHelper, String str, String str2) {
        return new CustomMessageHelper$$Lambda$4(customMessageHelper, str, str2);
    }

    @Hidden
    public void run() {
        this.a.m116a(this.b, this.c);
    }
}
