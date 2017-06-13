package com.buddy.tiki.ui.dialog;

import android.view.View;
import android.view.View.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ConfirmDialog$$Lambda$2 implements OnClickListener {
    private final ConfirmDialog f1841a;

    private ConfirmDialog$$Lambda$2(ConfirmDialog confirmDialog) {
        this.a = confirmDialog;
    }

    public static OnClickListener lambdaFactory$(ConfirmDialog confirmDialog) {
        return new ConfirmDialog$$Lambda$2(confirmDialog);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m1074a(view);
    }
}
