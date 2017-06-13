package com.buddy.tiki.ui.dialog;

import com.buddy.tiki.model.app.OperInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FilterMatchDialog$$Lambda$11 implements Consumer {
    private final FilterMatchDialog f1873a;

    private FilterMatchDialog$$Lambda$11(FilterMatchDialog filterMatchDialog) {
        this.a = filterMatchDialog;
    }

    public static Consumer lambdaFactory$(FilterMatchDialog filterMatchDialog) {
        return new FilterMatchDialog$$Lambda$11(filterMatchDialog);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1105a((OperInfo) obj);
    }
}
