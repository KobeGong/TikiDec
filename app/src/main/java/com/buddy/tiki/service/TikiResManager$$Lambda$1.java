package com.buddy.tiki.service;

import com.buddy.tiki.request.base.ProgressRequestBody.Listener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class TikiResManager$$Lambda$1 implements Listener {
    private final TikiResManager f945a;
    private final String[] f946b;
    private final int f947c;
    private final int f948d;

    private TikiResManager$$Lambda$1(TikiResManager tikiResManager, String[] strArr, int i, int i2) {
        this.a = tikiResManager;
        this.b = strArr;
        this.c = i;
        this.d = i2;
    }

    public static Listener lambdaFactory$(TikiResManager tikiResManager, String[] strArr, int i, int i2) {
        return new TikiResManager$$Lambda$1(tikiResManager, strArr, i, i2);
    }

    @Hidden
    public void onRequestProgress(long j, long j2, long j3) {
        this.a.m312a(this.b, this.c, this.d, j, j2, j3);
    }
}
