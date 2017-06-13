package com.buddy.tiki.helper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$18 implements OnClickListener {
    private static final DialogHelper$$Lambda$18 f569a = new DialogHelper$$Lambda$18();

    private DialogHelper$$Lambda$18() {
    }

    public static OnClickListener lambdaFactory$() {
        return a;
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        DialogHelper.lambda$showModifyGender$42(dialogInterface, i);
    }
}
