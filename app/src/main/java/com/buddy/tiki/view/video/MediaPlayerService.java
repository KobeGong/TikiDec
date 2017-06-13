package com.buddy.tiki.view.video;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class MediaPlayerService extends Service {
    private static IMediaPlayer f3424a;

    public static Intent newIntent(Context context) {
        return new Intent(context, MediaPlayerService.class);
    }

    public static void intentToStart(Context context) {
        context.startService(newIntent(context));
    }

    public static void intentToStop(Context context) {
        context.stopService(newIntent(context));
    }

    public static IMediaPlayer getMediaPlayer() {
        return f3424a;
    }

    public static void setMediaPlayer(IMediaPlayer mp) {
        if (!(f3424a == null || f3424a == mp)) {
            if (f3424a.isPlaying()) {
                f3424a.stop();
            }
            f3424a.release();
            f3424a = null;
        }
        f3424a = mp;
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }
}
