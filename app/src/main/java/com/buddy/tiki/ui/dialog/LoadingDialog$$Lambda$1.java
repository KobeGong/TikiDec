package com.buddy.tiki.ui.dialog;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class LoadingDialog$$Lambda$1 implements Runnable {
    private static final LoadingDialog$$Lambda$1 f1949a = new LoadingDialog$$Lambda$1();

    private LoadingDialog$$Lambda$1() {
    }

    public static Runnable lambdaFactory$() {
        return a;
    }

    @Hidden
    public void run() {
        LoadingDialog.stopLoading();
    }
}
