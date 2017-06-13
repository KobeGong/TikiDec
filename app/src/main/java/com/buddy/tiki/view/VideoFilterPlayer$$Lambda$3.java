package com.buddy.tiki.view;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class VideoFilterPlayer$$Lambda$3 implements OnPreparedListener {
    private final VideoFilterPlayer f2901a;

    private VideoFilterPlayer$$Lambda$3(VideoFilterPlayer videoFilterPlayer) {
        this.a = videoFilterPlayer;
    }

    public static OnPreparedListener lambdaFactory$(VideoFilterPlayer videoFilterPlayer) {
        return new VideoFilterPlayer$$Lambda$3(videoFilterPlayer);
    }

    @Hidden
    public void onPrepared(MediaPlayer mediaPlayer) {
        this.a.m1813a(mediaPlayer);
    }
}
