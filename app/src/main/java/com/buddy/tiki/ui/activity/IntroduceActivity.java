package com.buddy.tiki.ui.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.OnClick;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.ui.activity.base.BaseActivity;
import com.buddy.tiki.util.PreferenceUtil;
import com.buddy.tiki.view.FullScreenVideoView;

public class IntroduceActivity extends BaseActivity {
    private int f1409a;
    @BindView(2131820757)
    FullScreenVideoView mVideo;

    protected int mo2115a() {
        return C0376R.layout.activity_introduce;
    }

    protected void mo2116a(Bundle savedInstanceState) {
        m723c();
    }

    protected int mo2117b() {
        return 0;
    }

    protected void onResume() {
        super.onResume();
        if (this.mVideo != null && !this.mVideo.isPlaying()) {
            this.mVideo.seekTo(this.f1409a);
            this.mVideo.start();
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.mVideo != null) {
            this.mVideo.pause();
            this.f1409a = this.mVideo.getCurrentPosition();
        }
    }

    private void m723c() {
        this.mVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + C0376R.raw.tiki_launch));
        this.mVideo.setOnPreparedListener(IntroduceActivity$$Lambda$1.lambdaFactory$());
        this.mVideo.setOnCompletionListener(IntroduceActivity$$Lambda$2.lambdaFactory$(this));
        this.mVideo.start();
    }

    /* synthetic */ void m726a(MediaPlayer mp) {
        m724d();
    }

    @OnClick({2131820758})
    public void skipVideo() {
        m724d();
    }

    private synchronized void m724d() {
        PreferenceUtil.setShowIntroduceVideo();
        launchActivity(LoginActivity.class);
        finish();
    }

    public void onBackPressed() {
        m724d();
    }
}
