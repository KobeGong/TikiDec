package com.buddy.tiki.helper;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$27 implements Consumer {
    private final DialogHelper f586a;
    private final AppCompatEditText f587b;
    private final AlertDialog f588c;
    private final Runnable f589d;
    private final Context f590e;

    private DialogHelper$$Lambda$27(DialogHelper dialogHelper, AppCompatEditText appCompatEditText, AlertDialog alertDialog, Runnable runnable, Context context) {
        this.a = dialogHelper;
        this.b = appCompatEditText;
        this.c = alertDialog;
        this.d = runnable;
        this.e = context;
    }

    public static Consumer lambdaFactory$(DialogHelper dialogHelper, AppCompatEditText appCompatEditText, AlertDialog alertDialog, Runnable runnable, Context context) {
        return new DialogHelper$$Lambda$27(dialogHelper, appCompatEditText, alertDialog, runnable, context);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.lambda$showPassportEditDialog$51(this.b, this.c, this.d, this.e, obj);
    }
}
