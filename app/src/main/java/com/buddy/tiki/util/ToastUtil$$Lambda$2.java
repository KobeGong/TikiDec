package com.buddy.tiki.util;

import android.content.Context;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ToastUtil$$Lambda$2 implements Runnable {
    private final ToastUtil f2469a;
    private final Context f2470b;
    private final String f2471c;
    private final int f2472d;

    private ToastUtil$$Lambda$2(ToastUtil toastUtil, Context context, String str, int i) {
        this.a = toastUtil;
        this.b = context;
        this.c = str;
        this.d = i;
    }

    public static Runnable lambdaFactory$(ToastUtil toastUtil, Context context, String str, int i) {
        return new ToastUtil$$Lambda$2(toastUtil, context, str, i);
    }

    @Hidden
    public void run() {
        this.a.m1620a(this.b, this.c, this.d);
    }
}
