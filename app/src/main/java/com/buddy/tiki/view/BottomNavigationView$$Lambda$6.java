package com.buddy.tiki.view;

import android.view.View;
import android.view.View.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class BottomNavigationView$$Lambda$6 implements OnClickListener {
    private final BottomNavigationView f2518a;

    private BottomNavigationView$$Lambda$6(BottomNavigationView bottomNavigationView) {
        this.a = bottomNavigationView;
    }

    public static OnClickListener lambdaFactory$(BottomNavigationView bottomNavigationView) {
        return new BottomNavigationView$$Lambda$6(bottomNavigationView);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m1661b(view);
    }
}
