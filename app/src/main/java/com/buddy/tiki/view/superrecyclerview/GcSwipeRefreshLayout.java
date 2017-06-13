package com.buddy.tiki.view.superrecyclerview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import org.bytedeco.javacpp.swresample;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class GcSwipeRefreshLayout extends SwipeRefreshLayout {
    private int f3213a;
    private float f3214b;

    public GcSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.f3213a = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isRefreshing()) {
            return true;
        }
        switch (ev.getAction()) {
            case swresample.SWR_FILTER_TYPE_CUBIC /*0*/:
                this.f3214b = MotionEvent.obtain(ev).getX();
                break;
            case IMediaPlayer.MEDIA_INFO_STARTED_AS_NEXT /*2*/:
                if (Math.abs(ev.getX() - this.f3214b) > ((float) this.f3213a)) {
                    return false;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
