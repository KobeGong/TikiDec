package com.buddy.tiki.ui.dialog;

import com.buddy.tiki.model.user.User;
import io.reactivex.functions.Predicate;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class FilterMatchDialog$$Lambda$6 implements Predicate {
    private static final FilterMatchDialog$$Lambda$6 f1883a = new FilterMatchDialog$$Lambda$6();

    private FilterMatchDialog$$Lambda$6() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return FilterMatchDialog.m1099b((User) obj);
    }
}
