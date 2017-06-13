package com.buddy.tiki.helper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$13 implements OnClickListener {
    private static final DialogHelper$$Lambda$13 f555a = new DialogHelper$$Lambda$13();

    private DialogHelper$$Lambda$13() {
    }

    public static OnClickListener lambdaFactory$() {
        return a;
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        DialogHelper.lambda$showConfirmDeleteDialog$29(dialogInterface, i);
    }
}
