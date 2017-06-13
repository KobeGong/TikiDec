package com.buddy.tiki.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import com.buddy.tiki.C0376R;
import org.bytedeco.javacpp.avutil;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class WebSocketConnectionHint extends AppCompatTextView {
    private DotTailTextView f2920a;
    private volatile int f2921b;

    public WebSocketConnectionHint(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.f2921b = 0;
        this.f2920a = new DotTailTextView(this, false);
        this.f2921b = 0;
        m1815a();
    }

    public WebSocketConnectionHint(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WebSocketConnectionHint(Context context) {
        this(context, null);
    }

    private void m1815a() {
        switch (this.f2921b) {
            case avutil.AV_SAMPLE_FMT_NONE /*-1*/:
                this.f2921b = -1;
                setVisibility(0);
                setBackgroundResource(C0376R.color.fail_text_background);
                setText(C0376R.string.web_socket_connection_lost);
                this.f2920a.stop();
                return;
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
                this.f2921b = 1;
                setVisibility(0);
                setBackgroundResource(C0376R.color.web_socket_connection_hint_bg_connecting);
                setText(C0376R.string.web_socket_connection_connecting);
                this.f2920a.start();
                return;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                this.f2921b = 2;
                setVisibility(8);
                this.f2920a.stop();
                return;
            default:
                return;
        }
    }

    public int getState() {
        return this.f2921b;
    }

    public void setState(int state) {
        switch (state) {
            case avutil.AV_SAMPLE_FMT_NONE /*-1*/:
            case IMediaPlayer.MEDIA_INFO_UNKNOWN /*1*/:
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                this.f2921b = state;
                post(WebSocketConnectionHint$$Lambda$1.lambdaFactory$(this));
                return;
            default:
                return;
        }
    }
}
