package com.buddy.tiki.helper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$9 implements OnClickListener {
    private static final DialogHelper$$Lambda$9 f653a = new DialogHelper$$Lambda$9();

    private DialogHelper$$Lambda$9() {
    }

    public static OnClickListener lambdaFactory$() {
        return a;
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        DialogHelper.lambda$showEditNickDialog$22(dialogInterface, i);
    }
}
