package com.buddy.tiki.util;

import java.lang.invoke.LambdaForm.Hidden;
import java.util.concurrent.Callable;

final /* synthetic */ class FileUtil$$Lambda$1 implements Callable {
    private final String f2400a;

    private FileUtil$$Lambda$1(String str) {
        this.a = str;
    }

    public static Callable lambdaFactory$(String str) {
        return new FileUtil$$Lambda$1(str);
    }

    @Hidden
    public Object call() {
        return FileUtil.m1540a(this.a);
    }
}
