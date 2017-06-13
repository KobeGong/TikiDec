package com.buddy.tiki.service;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class TikiResManager$$Lambda$2 implements Runnable {
    private final String[] f949a;
    private final long f950b;
    private final long f951c;
    private final int f952d;
    private final int f953e;
    private final long f954f;

    private TikiResManager$$Lambda$2(String[] strArr, long j, long j2, int i, int i2, long j3) {
        this.a = strArr;
        this.b = j;
        this.c = j2;
        this.d = i;
        this.e = i2;
        this.f = j3;
    }

    public static Runnable lambdaFactory$(String[] strArr, long j, long j2, int i, int i2, long j3) {
        return new TikiResManager$$Lambda$2(strArr, j, j2, i, i2, j3);
    }

    @Hidden
    public void run() {
        TikiResManager.m311a(this.a, this.b, this.c, this.d, this.e, this.f);
    }
}
