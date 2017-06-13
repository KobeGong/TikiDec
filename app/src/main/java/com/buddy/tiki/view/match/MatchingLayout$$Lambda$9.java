package com.buddy.tiki.view.match;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class MatchingLayout$$Lambda$9 implements Runnable {
    private final MatchingLayout f3142a;
    private final int f3143b;
    private final Runnable f3144c;

    private MatchingLayout$$Lambda$9(MatchingLayout matchingLayout, int i, Runnable runnable) {
        this.a = matchingLayout;
        this.b = i;
        this.c = runnable;
    }

    public static Runnable lambdaFactory$(MatchingLayout matchingLayout, int i, Runnable runnable) {
        return new MatchingLayout$$Lambda$9(matchingLayout, i, runnable);
    }

    @Hidden
    public void run() {
        this.a.m1990a(this.b, this.c);
    }
}
