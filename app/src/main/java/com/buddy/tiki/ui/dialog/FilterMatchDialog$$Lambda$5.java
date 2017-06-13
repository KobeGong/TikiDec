package com.buddy.tiki.ui.dialog;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FilterMatchDialog$$Lambda$5 implements Consumer {
    private final FilterMatchDialog f1882a;

    private FilterMatchDialog$$Lambda$5(FilterMatchDialog filterMatchDialog) {
        this.a = filterMatchDialog;
    }

    public static Consumer lambdaFactory$(FilterMatchDialog filterMatchDialog) {
        return new FilterMatchDialog$$Lambda$5(filterMatchDialog);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1107a(obj);
    }
}
