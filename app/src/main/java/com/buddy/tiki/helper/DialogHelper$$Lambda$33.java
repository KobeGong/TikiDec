package com.buddy.tiki.helper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$33 implements OnDismissListener {
    private final DialogHelper f599a;

    private DialogHelper$$Lambda$33(DialogHelper dialogHelper) {
        this.a = dialogHelper;
    }

    public static OnDismissListener lambdaFactory$(DialogHelper dialogHelper) {
        return new DialogHelper$$Lambda$33(dialogHelper);
    }

    @Hidden
    public void onDismiss(DialogInterface dialogInterface) {
        this.a.lambda$showLackInFilterDialog$57(dialogInterface);
    }
}
