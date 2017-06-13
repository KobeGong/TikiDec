package com.buddy.tiki.helper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$30 implements OnClickListener {
    private static final DialogHelper$$Lambda$30 f596a = new DialogHelper$$Lambda$30();

    private DialogHelper$$Lambda$30() {
    }

    public static OnClickListener lambdaFactory$() {
        return a;
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        DialogHelper.lambda$showFreeTimeOutDialog$54(dialogInterface, i);
    }
}
