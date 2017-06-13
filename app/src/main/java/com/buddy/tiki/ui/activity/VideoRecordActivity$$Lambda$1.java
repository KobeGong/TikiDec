package com.buddy.tiki.ui.activity;

import com.buddy.tiki.wertc.PeerConnectionClient;
import com.buddy.tiki.wertc.PeerConnectionClient.CallBack;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoRecordActivity$$Lambda$1 implements CallBack {
    private final VideoRecordActivity f1505a;

    private VideoRecordActivity$$Lambda$1(VideoRecordActivity videoRecordActivity) {
        this.a = videoRecordActivity;
    }

    public static CallBack lambdaFactory$(VideoRecordActivity videoRecordActivity) {
        return new VideoRecordActivity$$Lambda$1(videoRecordActivity);
    }

    @Hidden
    public void call(Object obj) {
        this.a.m896a((PeerConnectionClient) obj);
    }
}
