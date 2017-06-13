package com.buddy.tiki.view;

import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class BubbleView$$Lambda$3 implements Consumer {
    private final BubbleView f2555a;
    private final int f2556b;
    private final int f2557c;

    private BubbleView$$Lambda$3(BubbleView bubbleView, int i, int i2) {
        this.a = bubbleView;
        this.b = i;
        this.c = i2;
    }

    public static Consumer lambdaFactory$(BubbleView bubbleView, int i, int i2) {
        return new BubbleView$$Lambda$3(bubbleView, i, i2);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m1669a(this.b, this.c, (Long) obj);
    }
}
