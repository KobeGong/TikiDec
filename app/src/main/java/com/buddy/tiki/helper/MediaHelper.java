package com.buddy.tiki.helper;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.buddy.tiki.log.TikiLog;
import java.io.IOException;

public enum MediaHelper {
    INSTANCE;
    
    private boolean mIsStartPlaying;
    private MediaPlayer mMediaPlayer;
    private TikiLog tikiLog;

    public synchronized void playMusic(@NonNull String path, boolean loop) {
        if (!TextUtils.isEmpty(path)) {
            if (this.mMediaPlayer == null) {
                this.mMediaPlayer = new MediaPlayer();
            }
            this.mIsStartPlaying = true;
            try {
                this.mMediaPlayer.reset();
                this.mMediaPlayer.setDataSource(path);
                this.mMediaPlayer.setLooping(loop);
                this.mMediaPlayer.setOnPreparedListener(MediaHelper$$Lambda$1.lambdaFactory$(this));
                this.mMediaPlayer.prepareAsync();
            } catch (IOException e) {
                this.tikiLog.m264e("playMusic ", e);
            }
        }
    }

    public synchronized void playMusic(Context context, Uri uri, boolean loop) {
        if (!(context == null || uri == null)) {
            if (this.mMediaPlayer == null) {
                this.mMediaPlayer = new MediaPlayer();
            }
            this.mIsStartPlaying = true;
            try {
                this.mMediaPlayer.reset();
                this.mMediaPlayer.setDataSource(context, uri);
                this.mMediaPlayer.setLooping(loop);
                this.mMediaPlayer.setOnPreparedListener(MediaHelper$$Lambda$2.lambdaFactory$(this));
                this.mMediaPlayer.prepareAsync();
            } catch (IOException e) {
                this.tikiLog.m264e("playMusic ", e);
            }
        }
    }

    public void stopMusic() {
        this.mIsStartPlaying = false;
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.stop();
        }
    }

    public void release() {
        this.mIsStartPlaying = false;
        if (this.mMediaPlayer != null) {
            if (this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.stop();
            }
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
        }
    }
}
