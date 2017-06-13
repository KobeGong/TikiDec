package com.buddy.tiki.ui.activity;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class IntroduceActivity$$Lambda$2 implements OnCompletionListener {
    private final IntroduceActivity f1408a;

    private IntroduceActivity$$Lambda$2(IntroduceActivity introduceActivity) {
        this.a = introduceActivity;
    }

    public static OnCompletionListener lambdaFactory$(IntroduceActivity introduceActivity) {
        return new IntroduceActivity$$Lambda$2(introduceActivity);
    }

    @Hidden
    public void onCompletion(MediaPlayer mediaPlayer) {
        this.a.m726a(mediaPlayer);
    }
}
