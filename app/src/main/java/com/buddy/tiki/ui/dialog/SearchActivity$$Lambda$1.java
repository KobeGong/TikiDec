package com.buddy.tiki.ui.dialog;

import io.reactivex.functions.Function;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SearchActivity$$Lambda$1 implements Function {
    private final SearchActivity f1973a;

    private SearchActivity$$Lambda$1(SearchActivity searchActivity) {
        this.a = searchActivity;
    }

    public static Function lambdaFactory$(SearchActivity searchActivity) {
        return new SearchActivity$$Lambda$1(searchActivity);
    }

    @Hidden
    public Object apply(Object obj) {
        return this.a.m1167a((CharSequence) obj);
    }
}
