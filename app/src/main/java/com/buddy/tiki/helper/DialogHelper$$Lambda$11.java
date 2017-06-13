package com.buddy.tiki.helper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$11 implements OnClickListener {
    private static final DialogHelper$$Lambda$11 f553a = new DialogHelper$$Lambda$11();

    private DialogHelper$$Lambda$11() {
    }

    public static OnClickListener lambdaFactory$() {
        return a;
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        DialogHelper.lambda$showLogoutDialog$27(dialogInterface, i);
    }
}
