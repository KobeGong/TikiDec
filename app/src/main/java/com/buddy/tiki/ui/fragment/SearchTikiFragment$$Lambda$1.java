package com.buddy.tiki.ui.fragment;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SearchTikiFragment$$Lambda$1 implements Consumer {
    private final SearchTikiFragment f2197a;

    private SearchTikiFragment$$Lambda$1(SearchTikiFragment searchTikiFragment) {
        this.a = searchTikiFragment;
    }

    public static Consumer lambdaFactory$(SearchTikiFragment searchTikiFragment) {
        return new SearchTikiFragment$$Lambda$1(searchTikiFragment);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1391a(obj);
    }
}
