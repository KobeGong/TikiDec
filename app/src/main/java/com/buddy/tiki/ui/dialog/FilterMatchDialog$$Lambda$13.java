package com.buddy.tiki.ui.dialog;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FilterMatchDialog$$Lambda$13 implements OnClickListener {
    private final FilterMatchDialog f1875a;

    private FilterMatchDialog$$Lambda$13(FilterMatchDialog filterMatchDialog) {
        this.a = filterMatchDialog;
    }

    public static OnClickListener lambdaFactory$(FilterMatchDialog filterMatchDialog) {
        return new FilterMatchDialog$$Lambda$13(filterMatchDialog);
    }

    @Hidden
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.m1108b(dialogInterface, i);
    }
}
