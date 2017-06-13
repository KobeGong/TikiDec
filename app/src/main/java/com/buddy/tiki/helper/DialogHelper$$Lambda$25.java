package com.buddy.tiki.helper;

import android.support.v7.app.AlertDialog;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$25 implements Consumer {
    private final AlertDialog f583a;
    private final Runnable f584b;

    private DialogHelper$$Lambda$25(AlertDialog alertDialog, Runnable runnable) {
        this.a = alertDialog;
        this.b = runnable;
    }

    public static Consumer lambdaFactory$(AlertDialog alertDialog, Runnable runnable) {
        return new DialogHelper$$Lambda$25(alertDialog, runnable);
    }

    @Hidden
    public void accept(Object obj) {
        DialogHelper.lambda$showPassportEditDialog$49(this.a, this.b, obj);
    }
}
