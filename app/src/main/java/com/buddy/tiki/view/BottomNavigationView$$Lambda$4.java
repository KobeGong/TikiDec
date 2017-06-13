package com.buddy.tiki.view;

import android.view.View;
import android.view.View.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class BottomNavigationView$$Lambda$4 implements OnClickListener {
    private final BottomNavigationView f2516a;

    private BottomNavigationView$$Lambda$4(BottomNavigationView bottomNavigationView) {
        this.a = bottomNavigationView;
    }

    public static OnClickListener lambdaFactory$(BottomNavigationView bottomNavigationView) {
        return new BottomNavigationView$$Lambda$4(bottomNavigationView);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m1663d(view);
    }
}
