package com.buddy.tiki.ui.dialog;

import io.reactivex.functions.Consumer;
import io.realm.RealmResults;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SearchActivity$$Lambda$3 implements Consumer {
    private final SearchActivity f1975a;

    private SearchActivity$$Lambda$3(SearchActivity searchActivity) {
        this.a = searchActivity;
    }

    public static Consumer lambdaFactory$(SearchActivity searchActivity) {
        return new SearchActivity$$Lambda$3(searchActivity);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1169a((RealmResults) obj);
    }
}
