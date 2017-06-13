package com.buddy.tiki.view;

import com.buddy.tiki.view.TimerTextView.C04991;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class TimerTextView$1$$Lambda$1 implements Runnable {
    private final C04991 f2886a;

    private TimerTextView$1$$Lambda$1(C04991 c04991) {
        this.a = c04991;
    }

    public static Runnable lambdaFactory$(C04991 c04991) {
        return new TimerTextView$1$$Lambda$1(c04991);
    }

    @Hidden
    public void run() {
        this.a.m1799a();
    }
}
