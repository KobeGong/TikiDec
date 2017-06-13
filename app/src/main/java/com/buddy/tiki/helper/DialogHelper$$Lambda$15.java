package com.buddy.tiki.helper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$15 implements OnClickListener {
    private static final DialogHelper$$Lambda$15 f560a = new DialogHelper$$Lambda$15();

    private DialogHelper$$Lambda$15() {
    }

    public static OnClickListener lambdaFactory$() {
        return a;
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        DialogHelper.lambda$showConfirmDeleteDialogInMessage$34(dialogInterface, i);
    }
}
