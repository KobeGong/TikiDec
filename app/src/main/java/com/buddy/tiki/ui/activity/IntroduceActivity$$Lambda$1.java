package com.buddy.tiki.ui.activity;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class IntroduceActivity$$Lambda$1 implements OnPreparedListener {
    private static final IntroduceActivity$$Lambda$1 f1407a = new IntroduceActivity$$Lambda$1();

    private IntroduceActivity$$Lambda$1() {
    }

    public static OnPreparedListener lambdaFactory$() {
        return a;
    }

    @Hidden
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }
}
