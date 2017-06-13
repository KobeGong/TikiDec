package com.buddy.tiki.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import com.buddy.tiki.C0376R;
import com.buddy.tiki.log.TikiLog;
import com.jakewharton.rxbinding2.view.RxView;
import java.util.concurrent.TimeUnit;
import org.bytedeco.javacpp.swresample;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class FaceDetectCover extends RelativeLayout {
    private static final TikiLog f2649a = TikiLog.getInstance(FaceDetectCover.class.getSimpleName());
    private AppCompatTextView f2650b;
    private AppCompatTextView f2651c;
    private int f2652d;
    private OnClickListener f2653e;

    public FaceDetectCover(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.f2652d = 0;
        inflate(context, C0376R.layout.view_face_detect_cover, this);
        m1696a();
    }

    public FaceDetectCover(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FaceDetectCover(Context context) {
        this(context, null);
    }

    private void m1696a() {
        this.f2650b = (AppCompatTextView) findViewById(C0376R.id.display_text);
        this.f2651c = (AppCompatTextView) findViewById(C0376R.id.de_mosaic);
        this.f2652d = 0;
        m1697b();
        RxView.clicks(this.f2651c).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(FaceDetectCover$$Lambda$1.lambdaFactory$(this));
    }

    /* synthetic */ void m1698a(Object aVoid) throws Exception {
        f2649a.m261d("mDeMosaic clicked:" + this.f2653e);
        if (this.f2653e != null) {
            this.f2653e.onClick(this);
        }
    }

    private void m1697b() {
        switch (this.f2652d) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                this.f2650b.setVisibility(0);
                this.f2650b.setText(C0376R.string.local_no_face);
                this.f2651c.setVisibility(8);
                return;
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                this.f2650b.setVisibility(0);
                this.f2650b.setText(C0376R.string.remote_no_face);
                this.f2651c.setVisibility(0);
                return;
            case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START /*3*/:
                setVisibility(8);
                return;
            default:
                return;
        }
    }

    public void setState(int state) {
        if (state == 3 || state == 0 || state == 1 || state == 2) {
            this.f2652d = state;
            m1697b();
        }
    }

    public void setUncoverListener(OnClickListener uncoverListener) {
        this.f2653e = uncoverListener;
    }
}
