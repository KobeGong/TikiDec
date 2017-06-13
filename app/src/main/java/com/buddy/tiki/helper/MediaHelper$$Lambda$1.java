package com.buddy.tiki.helper;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class MediaHelper$$Lambda$1 implements OnPreparedListener {
    private final MediaHelper f694a;

    private MediaHelper$$Lambda$1(MediaHelper mediaHelper) {
        this.a = mediaHelper;
    }

    public static OnPreparedListener lambdaFactory$(MediaHelper mediaHelper) {
        return new MediaHelper$$Lambda$1(mediaHelper);
    }

    @Hidden
    public void onPrepared(MediaPlayer mediaPlayer) {
        this.a.lambda$playMusic$0(mediaPlayer);
    }
}
