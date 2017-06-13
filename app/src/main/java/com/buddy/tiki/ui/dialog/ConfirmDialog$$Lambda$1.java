package com.buddy.tiki.ui.dialog;

import android.view.View;
import android.view.View.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class ConfirmDialog$$Lambda$1 implements OnClickListener {
    private final ConfirmDialog f1840a;

    private ConfirmDialog$$Lambda$1(ConfirmDialog confirmDialog) {
        this.a = confirmDialog;
    }

    public static OnClickListener lambdaFactory$(ConfirmDialog confirmDialog) {
        return new ConfirmDialog$$Lambda$1(confirmDialog);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m1075b(view);
    }
}
