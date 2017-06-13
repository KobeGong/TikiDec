package com.buddy.tiki.helper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$31 implements OnClickListener {
    private static final DialogHelper$$Lambda$31 f597a = new DialogHelper$$Lambda$31();

    private DialogHelper$$Lambda$31() {
    }

    public static OnClickListener lambdaFactory$() {
        return a;
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        DialogHelper.lambda$showTooManyFriendsDialog$55(dialogInterface, i);
    }
}
