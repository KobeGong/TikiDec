package com.buddy.tiki.ui.dialog;

import io.reactivex.functions.Predicate;
import io.realm.RealmResults;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class SearchActivity$$Lambda$2 implements Predicate {
    private static final SearchActivity$$Lambda$2 f1974a = new SearchActivity$$Lambda$2();

    private SearchActivity$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return a;
    }

    @Hidden
    public boolean test(Object obj) {
        return ((RealmResults) obj).isLoaded();
    }
}
