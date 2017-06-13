package com.buddy.tiki.util;

import com.buddy.tiki.model.im.VideoStatics;
import io.reactivex.functions.Consumer;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class MatchStatisticsUtil$$Lambda$4 implements Consumer {
    private final VideoStatics f2421a;

    private MatchStatisticsUtil$$Lambda$4(VideoStatics videoStatics) {
        this.a = videoStatics;
    }

    public static Consumer lambdaFactory$(VideoStatics videoStatics) {
        return new MatchStatisticsUtil$$Lambda$4(videoStatics);
    }

    @Hidden
    public void accept(Object obj) {
        MatchStatisticsUtil.m1558a(this.a, (Boolean) obj);
    }
}
