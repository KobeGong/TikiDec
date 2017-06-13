package com.buddy.tiki.view;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import org.bytedeco.javacpp.swresample;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class TikiNestedScrollView extends NestedScrollView {
    private int f2883a;
    private int f2884b;
    private int f2885c;

    public TikiNestedScrollView(Context context) {
        this(context, null);
    }

    public TikiNestedScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TikiNestedScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.f2885c = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                this.f2883a = (int) ev.getRawX();
                this.f2884b = (int) ev.getRawY();
                break;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                if (Math.abs(((int) ev.getRawY()) - this.f2884b) > this.f2885c) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
