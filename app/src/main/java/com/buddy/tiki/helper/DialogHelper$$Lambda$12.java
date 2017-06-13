package com.buddy.tiki.helper;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.buddy.tiki.service.TikiManager;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class DialogHelper$$Lambda$12 implements OnClickListener {
    private static final DialogHelper$$Lambda$12 f554a = new DialogHelper$$Lambda$12();

    private DialogHelper$$Lambda$12() {
    }

    public static OnClickListener lambdaFactory$() {
        return a;
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        TikiManager.getInstance().logout(true);
    }
}
