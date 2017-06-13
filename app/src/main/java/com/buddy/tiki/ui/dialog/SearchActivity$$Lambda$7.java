package com.buddy.tiki.ui.dialog;

import java.lang.invoke.LambdaForm.Hidden;
import java.util.concurrent.Callable;

final /* synthetic */ class SearchActivity$$Lambda$7 implements Callable {
    private final SearchActivity f1979a;
    private final CharSequence f1980b;

    private SearchActivity$$Lambda$7(SearchActivity searchActivity, CharSequence charSequence) {
        this.a = searchActivity;
        this.b = charSequence;
    }

    public static Callable lambdaFactory$(SearchActivity searchActivity, CharSequence charSequence) {
        return new SearchActivity$$Lambda$7(searchActivity, charSequence);
    }

    @Hidden
    public Object call() {
        return this.a.m1172b(this.b);
    }
}
