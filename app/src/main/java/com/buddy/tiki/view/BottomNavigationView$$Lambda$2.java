package com.buddy.tiki.view;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class BottomNavigationView$$Lambda$2 implements OnSharedPreferenceChangeListener {
    private final BottomNavigationView f2514a;

    private BottomNavigationView$$Lambda$2(BottomNavigationView bottomNavigationView) {
        this.a = bottomNavigationView;
    }

    public static OnSharedPreferenceChangeListener lambdaFactory$(BottomNavigationView bottomNavigationView) {
        return new BottomNavigationView$$Lambda$2(bottomNavigationView);
    }

    @Hidden
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        this.a.m1658a(sharedPreferences, str);
    }
}
