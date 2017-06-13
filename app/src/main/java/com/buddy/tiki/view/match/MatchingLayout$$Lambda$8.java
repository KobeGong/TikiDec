package com.buddy.tiki.view.match;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class MatchingLayout$$Lambda$8 implements Runnable {
    private final MatchingLayout f3140a;
    private final Runnable f3141b;

    private MatchingLayout$$Lambda$8(MatchingLayout matchingLayout, Runnable runnable) {
        this.a = matchingLayout;
        this.b = runnable;
    }

    public static Runnable lambdaFactory$(MatchingLayout matchingLayout, Runnable runnable) {
        return new MatchingLayout$$Lambda$8(matchingLayout, runnable);
    }

    @Hidden
    public void run() {
        this.a.m1992a(this.b);
    }
}
