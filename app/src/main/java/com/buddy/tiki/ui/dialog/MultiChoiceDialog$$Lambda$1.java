package com.buddy.tiki.ui.dialog;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class MultiChoiceDialog$$Lambda$1 implements OnItemClickListener {
    private final MultiChoiceDialog f1954a;

    private MultiChoiceDialog$$Lambda$1(MultiChoiceDialog multiChoiceDialog) {
        this.a = multiChoiceDialog;
    }

    public static OnItemClickListener lambdaFactory$(MultiChoiceDialog multiChoiceDialog) {
        return new MultiChoiceDialog$$Lambda$1(multiChoiceDialog);
    }

    @Hidden
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.a.m1161a(adapterView, view, i, j);
    }
}
