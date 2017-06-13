package com.buddy.tiki.helper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$32 implements OnClickListener {
    private static final DialogHelper$$Lambda$32 f598a = new DialogHelper$$Lambda$32();

    private DialogHelper$$Lambda$32() {
    }

    public static OnClickListener lambdaFactory$() {
        return a;
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        DialogHelper.lambda$showExplainDataUsageDialog$56(dialogInterface, i);
    }
}
