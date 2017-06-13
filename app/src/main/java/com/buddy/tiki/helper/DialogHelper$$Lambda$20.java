package com.buddy.tiki.helper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$20 implements OnClickListener {
    private static final DialogHelper$$Lambda$20 f575a = new DialogHelper$$Lambda$20();

    private DialogHelper$$Lambda$20() {
    }

    public static OnClickListener lambdaFactory$() {
        return a;
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        DialogHelper.lambda$showLackBalanceDialog$44(dialogInterface, i);
    }
}
