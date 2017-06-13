package com.buddy.tiki.ui.dialog;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SearchActivity$$Lambda$5 implements Consumer {
    private final SearchActivity f1977a;

    private SearchActivity$$Lambda$5(SearchActivity searchActivity) {
        this.a = searchActivity;
    }

    public static Consumer lambdaFactory$(SearchActivity searchActivity) {
        return new SearchActivity$$Lambda$5(searchActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1170a(obj);
    }
}
