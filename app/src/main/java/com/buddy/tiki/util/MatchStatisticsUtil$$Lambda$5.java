package com.buddy.tiki.util;

import com.buddy.tiki.model.im.VideoStatics;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class MatchStatisticsUtil$$Lambda$5 implements Consumer {
    private final VideoStatics f2422a;

    private MatchStatisticsUtil$$Lambda$5(VideoStatics videoStatics) {
        this.a = videoStatics;
    }

    public static Consumer lambdaFactory$(VideoStatics videoStatics) {
        return new MatchStatisticsUtil$$Lambda$5(videoStatics);
    }

    @Hidden
    public void accept(Object obj) {
        MatchStatisticsUtil.m1559a(this.a, (Throwable) obj);
    }
}
