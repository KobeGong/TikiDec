package com.buddy.tiki.view.match;

import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class MatchingLayout$$Lambda$2 implements Runnable {
    private final MatchingLayout f3131a;
    private final String f3132b;
    private final int f3133c;

    private MatchingLayout$$Lambda$2(MatchingLayout matchingLayout, String str, int i) {
        this.a = matchingLayout;
        this.b = str;
        this.c = i;
    }

    public static Runnable lambdaFactory$(MatchingLayout matchingLayout, String str, int i) {
        return new MatchingLayout$$Lambda$2(matchingLayout, str, i);
    }

    @Hidden
    public void run() {
        this.a.m1993a(this.b, this.c);
    }
}
