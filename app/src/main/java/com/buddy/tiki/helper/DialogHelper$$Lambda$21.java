package com.buddy.tiki.helper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$21 implements OnClickListener {
    private static final DialogHelper$$Lambda$21 f576a = new DialogHelper$$Lambda$21();

    private DialogHelper$$Lambda$21() {
    }

    public static OnClickListener lambdaFactory$() {
        return a;
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        DialogHelper.lambda$showPromotionDialog$45(dialogInterface, i);
    }
}
