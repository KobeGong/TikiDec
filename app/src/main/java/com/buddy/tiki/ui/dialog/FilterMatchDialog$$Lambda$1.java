package com.buddy.tiki.ui.dialog;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FilterMatchDialog$$Lambda$1 implements Consumer {
    private final FilterMatchDialog f1878a;

    private FilterMatchDialog$$Lambda$1(FilterMatchDialog filterMatchDialog) {
        this.a = filterMatchDialog;
    }

    public static Consumer lambdaFactory$(FilterMatchDialog filterMatchDialog) {
        return new FilterMatchDialog$$Lambda$1(filterMatchDialog);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1111c(obj);
    }
}
