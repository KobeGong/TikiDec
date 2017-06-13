package com.buddy.tiki.view;

import android.view.View;
import android.view.View.OnClickListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class BottomNavigationView$$Lambda$5 implements OnClickListener {
    private final BottomNavigationView f2517a;

    private BottomNavigationView$$Lambda$5(BottomNavigationView bottomNavigationView) {
        this.a = bottomNavigationView;
    }

    public static OnClickListener lambdaFactory$(BottomNavigationView bottomNavigationView) {
        return new BottomNavigationView$$Lambda$5(bottomNavigationView);
    }

    @Hidden
    public void onClick(View view) {
        this.a.m1662c(view);
    }
}
