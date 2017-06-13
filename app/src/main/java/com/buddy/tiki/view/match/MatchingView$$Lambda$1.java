package com.buddy.tiki.view.match;

import com.buddy.tiki.model.app.OperInfo;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class MatchingView$$Lambda$1 implements Consumer {
    private final MatchingView f3162a;

    private MatchingView$$Lambda$1(MatchingView matchingView) {
        this.a = matchingView;
    }

    public static Consumer lambdaFactory$(MatchingView matchingView) {
        return new MatchingView$$Lambda$1(matchingView);
    }

    @Hidden
    public void accept(Object obj) {
        this.a.m2011a((OperInfo) obj);
    }
}
