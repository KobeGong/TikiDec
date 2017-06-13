package com.buddy.tiki.ui.dialog;

import com.buddy.tiki.model.app.ConfigInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FilterMatchDialog$$Lambda$9 implements Consumer {
    private final FilterMatchDialog f1886a;

    private FilterMatchDialog$$Lambda$9(FilterMatchDialog filterMatchDialog) {
        this.a = filterMatchDialog;
    }

    public static Consumer lambdaFactory$(FilterMatchDialog filterMatchDialog) {
        return new FilterMatchDialog$$Lambda$9(filterMatchDialog);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1104a((ConfigInfo) obj);
    }
}
