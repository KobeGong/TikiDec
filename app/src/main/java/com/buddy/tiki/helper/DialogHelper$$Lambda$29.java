package com.buddy.tiki.helper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$29 implements OnClickListener {
    private static final DialogHelper$$Lambda$29 f592a = new DialogHelper$$Lambda$29();

    private DialogHelper$$Lambda$29() {
    }

    public static OnClickListener lambdaFactory$() {
        return a;
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        DialogHelper.lambda$showForbiddenModifyGenderDialog$53(dialogInterface, i);
    }
}
