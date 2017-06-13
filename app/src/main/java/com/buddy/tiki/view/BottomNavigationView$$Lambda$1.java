package com.buddy.tiki.view;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class BottomNavigationView$$Lambda$1 implements RealmChangeListener {
    private final BottomNavigationView f2513a;

    private BottomNavigationView$$Lambda$1(BottomNavigationView bottomNavigationView) {
        this.a = bottomNavigationView;
    }

    public static RealmChangeListener lambdaFactory$(BottomNavigationView bottomNavigationView) {
        return new BottomNavigationView$$Lambda$1(bottomNavigationView);
    }

    @Hidden
    public void onChange(Object obj) {
        this.a.m1660a((RealmResults) obj);
    }
}
