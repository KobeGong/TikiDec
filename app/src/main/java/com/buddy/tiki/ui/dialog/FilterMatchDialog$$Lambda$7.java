package com.buddy.tiki.ui.dialog;

import com.buddy.tiki.model.user.User;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FilterMatchDialog$$Lambda$7 implements Consumer {
    private final FilterMatchDialog f1884a;

    private FilterMatchDialog$$Lambda$7(FilterMatchDialog filterMatchDialog) {
        this.a = filterMatchDialog;
    }

    public static Consumer lambdaFactory$(FilterMatchDialog filterMatchDialog) {
        return new FilterMatchDialog$$Lambda$7(filterMatchDialog);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1106a((User) obj);
    }
}
