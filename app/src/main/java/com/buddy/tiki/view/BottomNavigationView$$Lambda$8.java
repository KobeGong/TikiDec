package com.buddy.tiki.view;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class BottomNavigationView$$Lambda$8 implements Runnable {
    private final BottomNavigationView f2520a;

    private BottomNavigationView$$Lambda$8(BottomNavigationView bottomNavigationView) {
        this.a = bottomNavigationView;
    }

    public static Runnable lambdaFactory$(BottomNavigationView bottomNavigationView) {
        return new BottomNavigationView$$Lambda$8(bottomNavigationView);
    }

    @Hidden
    public void run() {
        this.a.m1657a();
    }
}
