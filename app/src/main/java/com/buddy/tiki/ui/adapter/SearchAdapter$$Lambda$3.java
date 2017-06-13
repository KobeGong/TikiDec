package com.buddy.tiki.ui.adapter;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SearchAdapter$$Lambda$3 implements Consumer {
    private final SearchHolder f1818a;

    private SearchAdapter$$Lambda$3(SearchHolder searchHolder) {
        this.a = searchHolder;
    }

    public static Consumer lambdaFactory$(SearchHolder searchHolder) {
        return new SearchAdapter$$Lambda$3(searchHolder);
    }

    @Hidden
    public void accept(Object obj) {
        SearchAdapter.m1039a(this.a, (Boolean) obj);
    }
}
