package com.buddy.tiki.util;

import android.content.Context;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ToastUtil$$Lambda$1 implements Runnable {
    private final ToastUtil f2465a;
    private final Context f2466b;
    private final int f2467c;
    private final int f2468d;

    private ToastUtil$$Lambda$1(ToastUtil toastUtil, Context context, int i, int i2) {
        this.a = toastUtil;
        this.b = context;
        this.c = i;
        this.d = i2;
    }

    public static Runnable lambdaFactory$(ToastUtil toastUtil, Context context, int i, int i2) {
        return new ToastUtil$$Lambda$1(toastUtil, context, i, i2);
    }

    @Hidden
    public void run() {
        this.a.m1619a(this.b, this.c, this.d);
    }
}
